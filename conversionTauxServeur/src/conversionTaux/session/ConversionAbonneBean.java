package conversionTaux.session;

import jakarta.ejb.*;
import jakarta.persistence.*;
import conversionTaux.entity.*;
import conversionTaux.helper.*;


@Stateful(mappedName="ConversionAbonneJNDI")
public interface ConversionAbonneBean implements ConversionAbonneItf,ConversionTauxCste{
    @PersistenceContext(unitName="ConversionAbonnePU")
    private EntityManager em;
    private AbonneEntity ae ,ae1;

    public String connecter(String login, String passwd){
      
            this.ae = (AbonneEntity) em.createQuery("SELECT a FROM Abonne a where a.login = :l AND a.passwd = :p")
            .setParameter("l", login)
            .setParameter("p",passwd)
            .getSingleResult();

            if(ae != null) {
                ConversionSingleton.getInstance().ecrire();
                return SUCCESS;
            }
            else 
                return FAILED ;
        
    }

    
    public String deconnecter(){
        if(ae != null){
            ae1 = em.merge(ae);
            em.refresh(ae1)
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
            merge + refresh l'entité abonne
            chercher le taux 
                create query
                    from tauxEntity t
                new favori entity  
                persist 
                attacher fe dans l'instance AbonneEntity  |
                attacher l'instance taux entity dans fe   | => setters
        
        */
        

        
            if(this.ae != null){
                ae1 = em.merge(ae);
                em.refresh(ae1);
                TauxEntity te = (TauxEntity) em.createQuery("SELECT t FROM Taux t where t.monnaieA = :mA and t.monnaieB =:mB ")
                .setParameter("mA",monnaieA)
                .setParameter("mB",monnaieB)
                .getSingleResult();
                FavoriEntity fe = new FavoriEntity(favori,te);
                em.persist(fe);

                ae.getListfavori().add(fe);
                fe.setTaux(te);
                return SUCCESS;
            }
            else 
                return ACCESS_DENIED ;
        
        
        
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

    /*
        *******public String ControllerTopTaux(){

            String aRenvoyer ;  //mA+mB+count
            List<[object]>resultat = new ArrayList<Object[]>();
                resultat = List<Object[]> em.createQuery(
            * select f.leTaux.monnaieA,f.leTaux.monnaieB,count(f)
            * 
            * from favoriEntity f 
            * group by f.leTaux
            * order by count(f) Desc 
            * 
            * ).setMaxResult(s)
            * .getResultList();
            * 
            * for(Object[] req : resultat){
        *          req[0]-> monnaieA , req[1]->monnaieB , req[2]-> count
        *         }
        * 
        * }
        */

}