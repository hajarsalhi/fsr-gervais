package conversionTaux.session;

import jakarta.ejb.*;
import jakarta.persistence.*;
import conversionTaux.entity.*;
import conversionTaux.helper.*;
@Stateful(mappedName="ConversionAdminJNDI")
public class ConversionAdmin implements ConversionAdminItf,ConversionTauxCste{

    private AdminEntity ae  ;
    @PersistenceContext(unitName="ConversionAdminPU")
    private EntityManager em;

    public static int nbCnx = 0; 

    public String connecter(String login, String passwd){
        this.ae = (AdminEntity) em.createQuery("SELECT a FROM Admin a where a.login = :l AND a.passwd = :p")
            .setParameter("l", login)
            .setParameter("p",passwd)
            .getSingleResult();

            if(ae != null) 
                return SUCCESS;
            else 
                return FAILED ;
    }
    public String deconnecter(){
        if(ae != null){
            em.close();
            return SUCCESS;
        }

        else 
            return FAILED ;
    }
    public String consulterNbConnexions(){
        return RESULTAT_ADMIN + ConversionSingleton.getInstance().lire() ;
    }

}