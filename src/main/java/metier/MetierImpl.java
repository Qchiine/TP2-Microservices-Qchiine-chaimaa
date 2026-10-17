package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import outils.Trace;

/**
 * Couche metier a injection par setter, avec selection explicite de la source.
 *
 * <p>Sous le profil « dev », deux implementations de {@link IDao} coexistent.
 * Le qualifiant tranche : c'est la source simulee (150.0) qui est injectee, et
 * le resultat vaut donc 300.0.</p>
 */
@Component("metier")
@Profile("dev")
@Trace("metier-qualifie[dev]")
public class MetierImpl implements IMetier {

    private static final double COEFFICIENT = 2.0;

    private IDao dao;

    public MetierImpl() {
        // constructeur explicite : requis pour l'injection par setter et pour les tests
    }

    /**
     * Point d'injection. Le qualifiant leve l'ambiguite entre les deux sources.
     *
     * @param dao la source retenue par le conteneur.
     */
    @Autowired
    @Qualifier("dao2")
    public void setDao(IDao dao) {
        this.dao = dao;
    }

    /**
     * @return la source effectivement injectee.
     */
    public IDao getDao() {
        return dao;
    }

    @Override
    public double calcul() {
        if (dao == null) {
            throw new IllegalStateException("Aucune source DAO n'a ete injectee");
        }
        return dao.getValue() * COEFFICIENT;
    }
}