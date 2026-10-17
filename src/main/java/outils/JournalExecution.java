package outils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Journal en memoire des appels interceptes.
 *
 * <p>Chaque entree conserve le bean appele, la methode, la duree mesuree en
 * microsecondes et la source de donnees qui a effectivement repondu. Ce journal
 * permet de produire un rapport de fin d'execution sans instrumenter le code
 * metier lui-meme.</p>
 */
@Component("journal")
public class JournalExecution {

    /** Une ligne du journal. */
    public static final class Entree {

        private final String cible;
        private final String methode;
        private final long dureeMicros;
        private final double resultat;

        Entree(String cible, String methode, long dureeMicros, double resultat) {
            this.cible = cible;
            this.methode = methode;
            this.dureeMicros = dureeMicros;
            this.resultat = resultat;
        }

        public String getCible() {
            return cible;
        }

        public String getMethode() {
            return methode;
        }

        public long getDureeMicros() {
            return dureeMicros;
        }

        public double getResultat() {
            return resultat;
        }
    }

    private final List<Entree> entrees = new ArrayList<>();

    /**
     * Enregistre un appel intercepte.
     *
     * @param cible        nom logique du bean.
     * @param methode      methode appelee.
     * @param dureeMicros  duree mesuree.
     * @param resultat     valeur retournee, ou {@code NaN} si non numerique.
     */
    public void consigner(String cible, String methode, long dureeMicros, double resultat) {
        entrees.add(new Entree(cible, methode, dureeMicros, resultat));
    }

    /**
     * @return une vue non modifiable du journal.
     */
    public List<Entree> getEntrees() {
        return Collections.unmodifiableList(entrees);
    }

    /**
     * @return le nombre d'appels interceptes.
     */
    public int taille() {
        return entrees.size();
    }

    /**
     * Efface le journal, utile entre deux scenarios de demonstration.
     */
    public void reinitialiser() {
        entrees.clear();
    }
}