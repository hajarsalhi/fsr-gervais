package conversionTaux.session;

import jakarta.ejb.Remote;

@Remote()
public interface ConversionAdminItf{

    public String connecter(String login, String passwd);
    public String deconnecter();
    public String consulterNbConnexions();

}