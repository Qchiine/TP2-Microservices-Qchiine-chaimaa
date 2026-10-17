package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import outils.Trace;

/**
 * Couche metier a injection par champ, sans qualifiant.
 *
 * <p>Active sous le profil « prod », ou une seule source est enregistree : Spring
 * resout donc la dependance par type, sans ambiguite possible.</p>
 */
@Component("metierSimple")
@Profile("prod")
@Trace("metier-simple[prod]")
public class MetierSimple implements IMetier {

    private static final double COEFFICIENT = 2.0;

    @Autowired
    private IDao dao;

    @Override
    public double calcul() {
        return dao.getValue() * COEFFICIENT;
    }
}