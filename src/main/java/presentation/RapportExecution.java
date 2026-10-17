package presentation;

import java.util.List;
import outils.JournalExecution;

/**
 * Met en forme le journal produit par le post-processeur de tracage.
 *
 * <p>C'est la fonctionnalite ajoutee au TP : aucun scenario n'appelle ce
 * formateur explicitement dans sa logique metier, le rapport est construit a
 * partir des appels reellement interceptes par le conteneur.</p>
 */
public final class RapportExecution {

    private static final String LIGNE = "-" + "-".repeat(72);

    private RapportExecution() {
        // classe utilitaire
    }

    /**
     * Affiche le rapport d'un journal.
     *
     * @param titre   titre du scenario.
     * @param journal journal a restituer.
     */
    public static void afficher(String titre, JournalExecution journal) {
        System.out.println(LIGNE);
        System.out.println("RAPPORT D'INSTRUMENTATION  |  " + titre);
        System.out.println(LIGNE);

        List<JournalExecution.Entree> entrees = journal.getEntrees();
        if (entrees.isEmpty()) {
            System.out.println("(aucun appel intercepte)");
            return;
        }

        System.out.printf("  %-28s %-16s %10s %12s%n", "BEAN", "METHODE", "DUREE(us)", "RESULTAT");
        for (JournalExecution.Entree e : entrees) {
            System.out.printf("  %-28s %-16s %10d %12s%n",
                    e.getCible(),
                    e.getMethode(),
                    e.getDureeMicros(),
                    Double.isNaN(e.getResultat()) ? "-" : String.format("%.1f", e.getResultat()));
        }
        System.out.println("  total : " + journal.taille() + " appel(s) intercepte(s)");
    }
}