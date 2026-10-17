package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import outils.Trace;

/**
 * Variante a injection par constructeur, active sous le profil « constructeur ».
 *
 * <p>L'objet est complet des l'instanciation : aucun setter, aucun champ
 * mutable. C'est la forme recommandee lorsque la dependance est obligatoire.</p>
 */
@Component("metierConstructeur")
@Profile("constructeur")
@Trace("metier-constructeur")
public class MetierConstructeur implements IMetier {

    private static final double COEFFICIENT = 2.0;

    private final IDao dao;

    /**
     * @param dao la source de reference, designee explicitement par son identifiant.
     */
    public MetierConstructeur(@Qualifier("dao") IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        return dao.getValue() * COEFFICIENT;
    }
}