package metier;

import dao.IDao;
import org.junit.Assert;
import org.junit.Test;

/**
 * Verifie le calcul metier en isolant completement la couche d'acces : la source
 * est remplacee par une doublure injectee a la main, sans conteneur Spring.
 */
public class MetierImplTest {

    /** Doublure de test renvoyant une valeur connue. */
    private static final class SourceFictive implements IDao {

        private final double valeur;

        SourceFictive(double valeur) {
            this.valeur = valeur;
        }

        @Override
        public double getValue() {
            return valeur;
        }
    }

    @Test
    public void leCalculDoubleLaValeurDeLaSource() {
        MetierImpl metier = new MetierImpl();
        metier.setDao(new SourceFictive(10.0));

        Assert.assertEquals(20.0, metier.calcul(), 0.001);
    }

    @Test
    public void leCalculReproduitLaValeurDeProduction() {
        MetierImpl metier = new MetierImpl();
        metier.setDao(new SourceFictive(150.0));

        Assert.assertEquals(300.0, metier.calcul(), 0.001);
    }

    @Test(expected = IllegalStateException.class)
    public void leCalculSansSourceEchoue() {
        new MetierImpl().calcul();
    }
}