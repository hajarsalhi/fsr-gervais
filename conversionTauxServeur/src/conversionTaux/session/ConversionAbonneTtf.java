package conversionTaux.session;

import jakarta.ejb.Remote;

@Remote()
public interface ConversionAbonneItf{
    public String connecter(String login, String passwd);
    public String deconnecter();
    public String ajouterFavori(String favori, String monnaieA, String monnaieB);
    public Double convertir(String favori, double montant);
}
