package dao;

/**
 * Contrat d'acces a une source de valeurs.
 *
 * <p>Le contrat est deliberement minimal : une seule valeur exposee. C'est ce
 * qui permet au conteneur IoC de substituer une implementation a une autre
 * sans que la couche metier ne change d'une virgule.</p>
 */
public interface IDao {

    /**
     * @return la valeur courante exposee par la source.
     */
    double getValue();

    /**
     * Etiquette lisible de la source, pratique pour les traces et les tests.
     *
     * @return le nom simple de la classe concrete.
     */
    default String etiquette() {
        return getClass().getSimpleName();
    }
}