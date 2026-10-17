package presentation;

import java.util.Arrays;
import metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Etape 5 de l'enonce : injection automatique sans qualifiant.
 *
 * <p>Sous le profil « prod », une seule source est presente : Spring resout la
 * dependance par type et injecte {@code dao} (100.0). Le resultat vaut 200.0.</p>
 */
public class PresentationProd {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext contexte = ContexteSpring.demarrer("prod")) {
            System.out.println("profil actif   : " + Arrays.toString(contexte.getEnvironment().getActiveProfiles()));
            System.out.println("sources vues   : " + Arrays.toString(contexte.getBeanNamesForType(dao.IDao.class)));

            IMetier metier = contexte.getBean(IMetier.class);
            System.out.println("Résultat = " + metier.calcul());

            RapportExecution.afficher("etape 5 - resolution par type", ContexteSpring.journal(contexte));
        }
    }
}