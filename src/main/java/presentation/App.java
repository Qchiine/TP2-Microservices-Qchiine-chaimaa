package presentation;

/**
 * Point d'entree unique de la demonstration.
 *
 * <p>Le parcours suit l'enonce, puis ajoute deux apports personnels :</p>
 * <ol>
 *   <li>une variante par injection de constructeur, absente de l'enonce ;</li>
 *   <li>un rapport d'instrumentation produit par un {@code BeanPostProcessor}
 *       maison, qui chronometre et journalise les beans sans les modifier.</li>
 * </ol>
 */
public final class App {

    private static final String TRAIT = "=".repeat(74);

    private App() {
        // classe utilitaire
    }

    public static void main(String[] args) {
        titre(" TP 2 - Spring IoC / Injection de dependances");
        titre(" Scenario 1/3 - resolution par type (profil prod)");
        PresentationProd.main(args);

        titre(" Scenario 2/3 - selection par @Qualifier (profil dev)");
        Presentation2.main(args);

        titre(" Scenario 3/3 - apport personnel : constructeur + instrumentation");
        PresentationConstructeur.main(args);

        titre(" Exercice complementaire - configuration declarative XML");
        PresentationXML.main(args);

        System.out.println();
        System.out.println(TRAIT);
        System.out.println(" Fin de la demonstration.");
        System.out.println(TRAIT);
    }

    private static void titre(String texte) {
        System.out.println();
        System.out.println(TRAIT);
        System.out.println(texte);
        System.out.println(TRAIT);
    }
}