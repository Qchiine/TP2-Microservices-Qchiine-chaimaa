package metier;

/**
 * Contrat de la couche metier.
 */
public interface IMetier {

    /**
     * Applique le traitement metier a la valeur issue de la source injectee.
     *
     * @return le resultat du calcul.
     */
    double calcul();
}