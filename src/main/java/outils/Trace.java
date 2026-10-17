package outils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marque une classe dont les methodes publiques doivent etre chronometrees.
 *
 * <p>Annotation maison, interpretee par {@link PostProcesseurTrace}. Elle n'est
 * pas reconnue nativement par Spring : c'est le post-processeur qui lui donne du
 * sens, en enveloppant le bean dans un proxy dynamique.</p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Trace {

    /**
     * Nom affiche dans le rapport final.
     *
     * @return l'etiquette du bean dans le journal.
     */
    String value();
}