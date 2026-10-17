package presentation;

import java.util.Arrays;
import metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Etape 6 de l'enonce : selection explicite de l'implementation injectee.
 *
 * <p>Sous le profil « dev », le conteneur voit deux sources ({@code dao} et
 * {@code dao2}). Le qualifiant porte par le setter de {@code MetierImpl} designe
 * {@code dao2}, d'ou un resultat de 300.0.</p>
 */
public class Presentation2 {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext contexte = ContexteSpring.demarrer("dev")) {
            System.out.println("profil actif   : " + Arrays.toString(contexte.getEnvironment().getActiveProfiles()));
            System.out.println("sources vues   : " + Arrays.toString(contexte.getBeanNamesForType(dao.IDao.class)));

            IMetier metier = contexte.getBean(IMetier.class);
            System.out.println("Résultat = " + metier.calcul());

            RapportExecution.afficher("etape 6 - qualifiant dao2", ContexteSpring.journal(contexte));
        }
    }
}