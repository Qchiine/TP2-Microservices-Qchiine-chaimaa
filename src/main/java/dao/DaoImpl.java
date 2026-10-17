package dao;

import org.springframework.stereotype.Component;

/**
 * Source de reference : valeur figee a 100.0.
 *
 * <p>Bean toujours disponible, quel que soit le profil actif. Il sert de choix
 * par defaut lorsque aucune autre source n'est enregistree.</p>
 */
@Component("dao")
public class DaoImpl implements IDao {

    private static final double REFERENCE = 100.0;

    @Override
    public double getValue() {
        return REFERENCE;
    }
}