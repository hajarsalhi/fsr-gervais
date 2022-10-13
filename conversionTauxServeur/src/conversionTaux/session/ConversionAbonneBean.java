package conversionTaux.session;

import jakarta.ejb.*;
import jakarta.persistence.*;
import conversionTaux.entity.*;
import conversionTaux.helper.*;


@Stateful(mappedName="ConversionAbonneJNDI")
public interface ConversionAbonneBean implements ConversionAbonneItf,ConversionTauxCste{
    @PersistenceContext(unitName="ConversionAbonnePU")
    private EntityManager em;
    private AbonneEntity ae  ;

    public String connecter(String login, String passwd){
      
            this.ae = (AbonneEntity) em.createQuery("SELECT a FROM Abonne a where a.login = :l AND a.passwd = :p")
            .setParameter("l", login)
            .setParameter("p",passwd)
            .getSingleResult();

            if(ae != null) {
                ConversionAdmin.nbCnx++;
                return SUCCESS;
            }
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

    public String ajouterFavori(String favori, String monnaieA, String monnaieB){

        //vérifier d'abord favori puis les monnaieA/B

        /*
            *** Creer favori 
            merge + refresh l'entité above
            chercher le taux 
                create query
                    from tauxEntity t
                new favori entity  
                persist 
                attacher fe dans l'instance AbonneEntity  |
                attacher l'instance taux entity dans fe   | => setters
        
        */
        try{
            if(this.ae != null){
            FavoriEntity fe = (FavoriEntity) em.createQuery("SELECT f FROM Favori f where f.favori = :fav ")
            .setParameter("fav",favori)
            .getSingleResult();

            }
            else 
                return ACCESS_DENIED ;
        }catch (NoResultException nre) {
            
            FavoriEntity f = new FavoriEntity(favori, new Taux(monnaieA,monnaieB));
            
            // faire passer l'entité à l'état "managed"
            em.persist(f);
            return SUCCESS;
            
        }   
        
        
    }
    public Double convertir(String favori, double montant){
        try{
            if(this.ae != null){
            FavoriEntity fe = (FavoriEntity) em.createQuery("SELECT f FROM Favori f where f.favori = :fav ")
            .setParameter("fav",favori)
            .getSingleResult()

            return RESULTAT +fe.getTaux().getRate()*montant;
            
            }else 
                return ACCESS_DENIED ;
        }
        }catch (NoResultException nre) {
            return NO_RESULTAT ;
        }
    }

}