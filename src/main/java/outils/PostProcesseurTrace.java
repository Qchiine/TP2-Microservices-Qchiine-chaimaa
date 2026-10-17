package outils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Post-processeur qui enveloppe dans un proxy les beans marques par {@link Trace}.
 *
 * <p>Point cle de la demonstration : ce comportement est entierement ajoute par
 * le conteneur. Les classes metier ignorent qu'elles sont chronometrees : elles
 * ne portent aucune annotation de chronometrage et ne dependent pas du journal.</p>
 */
@Component
public class PostProcesseurTrace implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext contexte;

    @Override
    public void setApplicationContext(ApplicationContext contexte) {
        this.contexte = contexte;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String nomBean) throws BeansException {
        Trace marqueur = bean.getClass().getAnnotation(Trace.class);
        if (marqueur == null) {
            return bean;
        }

        Class<?>[] contrats = Arrays.stream(bean.getClass().getInterfaces())
                .toArray(Class<?>[]::new);
        if (contrats.length == 0) {
            return bean;
        }

        JournalExecution journal = contexte.getBean(JournalExecution.class);
        return Proxy.newProxyInstance(
                bean.getClass().getClassLoader(),
                contrats,
                new Chronometreur(bean, marqueur.value(), journal));
    }

    /**
     * Invocation handler qui mesure la duree de chaque appel delegue.
     */
    private static final class Chronometreur implements InvocationHandler {

        private final Object cible;
        private final String etiquette;
        private final JournalExecution journal;

        Chronometreur(Object cible, String etiquette, JournalExecution journal) {
            this.cible = cible;
            this.etiquette = etiquette;
            this.journal = journal;
        }

        @Override
        public Object invoke(Object proxy, Method methode, Object[] arguments) throws Throwable {
            long debut = System.nanoTime();
            Object valeur;
            try {
                valeur = methode.invoke(cible, arguments);
            } catch (InvocationTargetException e) {
                journal.consigner(etiquette, methode.getName(),
                        (System.nanoTime() - debut) / 1_000L, Double.NaN);
                throw e.getCause();
            }
            long duree = (System.nanoTime() - debut) / 1_000L;
            double resultat = (valeur instanceof Number) ? ((Number) valeur).doubleValue() : Double.NaN;
            journal.consigner(etiquette, methode.getName(), duree, resultat);
            return valeur;
        }
    }
}