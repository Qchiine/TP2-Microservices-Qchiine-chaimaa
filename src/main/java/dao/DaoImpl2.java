package dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Source alternative : valeur simulee de 150.0.
 *
 * <p>Le bean n'est enregistre que sous le profil « dev ». Des que ce profil est
 * actif, deux implementations de {@link dao.IDao} coexistent dans le conteneur :
 * un qualifiant devient alors obligatoire cote metier.</p>
 */
@Component("dao2")
@Profile("dev")
public class DaoImpl2 implements IDao {

    private static final double SIMULATION = 150.0;

    @Override
    public double getValue() {
        return SIMULATION;
    }
}