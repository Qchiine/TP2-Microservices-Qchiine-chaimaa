package presentation;

import metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Variante a injection par constructeur, sous profil « prod ».
 *
 * <p>Le profil supplementaire « constructeur » isole cette variante : une seule
 * implementation de {@code IMetier} reste alors disponible, ce qui montre que
 * l'injection par constructeur produit exactement le meme calcul.</p>
 */
public class PresentationConstructeur {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext contexte = ContexteSpring.demarrer("prod", "constructeur")) {
            IMetier metier = contexte.getBean("metierConstructeur", IMetier.class);
            System.out.println("Résultat (constructeur) = " + metier.calcul());

            RapportExecution.afficher("variante - injection par constructeur", ContexteSpring.journal(contexte));
        }
    }
}