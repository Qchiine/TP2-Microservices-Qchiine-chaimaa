package presentation;

import metier.IMetier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Exercice 2 de l'enonce : configuration declarative equivalente.
 *
 * <p>Les beans sont decrits dans {@code applicationContext.xml} et la source
 * injectee est designee par reference ({@code ref="dao2"}), sans annotation.</p>
 */
public class PresentationXML {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext contexte =
                     new ClassPathXmlApplicationContext("applicationContext.xml")) {

            IMetier metier = contexte.getBean("metier", IMetier.class);
            System.out.println("Résultat (XML) = " + metier.calcul());
        }
    }
}