package presentation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import outils.JournalExecution;

/**
 * Fabrique de conteneurs Spring pour les scenarios de demonstration.
 *
 * <p>Centralise la creation du contexte afin que chaque scenario ne differe que
 * par son profil actif, ce qui rend les resultats comparables d'une ligne a
 * l'autre.</p>
 */
@Configuration
@ComponentScan(basePackages = {"dao", "metier", "outils"})
public class ContexteSpring {

    /**
     * Demarre un conteneur avec le ou les profils demandes.
     *
     * @param profils profils Spring a activer.
     * @return un conteneur pret a l'emploi.
     */
    public static AnnotationConfigApplicationContext demarrer(String... profils) {
        AnnotationConfigApplicationContext contexte = new AnnotationConfigApplicationContext();
        if (profils != null && profils.length > 0) {
            contexte.getEnvironment().setActiveProfiles(profils);
        }
        contexte.register(ContexteSpring.class);
        contexte.refresh();
        return contexte;
    }

    /**
     * @param contexte conteneur source.
     * @return le journal partage par ce conteneur.
     */
    public static JournalExecution journal(AnnotationConfigApplicationContext contexte) {
        return contexte.getBean(JournalExecution.class);
    }
}