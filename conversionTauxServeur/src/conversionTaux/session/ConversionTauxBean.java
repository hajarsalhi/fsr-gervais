package conversionTaux.session;

import jakarta.ejb.*;
import jakarta.persistence.*;
import conversionTaux.entity.*;
import conversionTaux.helper.*;

@Stateless (mappedName="ConversionTauxJNDI")
public class ConversionTauxBean implements ConversionTauxItf, ConversionTauxCste {
    @PersistenceContext(unitName="ConversionTauxPU")
    private EntityManager em;
    private AbonneEntity ae  ;

    /*  Remonter de la BD en mémoire la valeur du taux correspondant à la paire (monnaieA, monnaieB)
        Retourner la string RESULTAT + montant * taux trouvé
     */
	public String convertir(String monnaieA, String monnaieB, double montant) {
		try {
            Double rate = (Double)em.createQuery("SELECT t.taux FROM Taux t where t.monnaieA = :monnaieA and t.monnaieB = :monnaieB")
			.setParameter("monnaieA", monnaieA)
			.setParameter("monnaieB", monnaieB)
			.getSingleResult();
            return RESULTAT + montant * rate;
		}
		catch (NoResultException nre) {
            //  exception si la paire (monnaieA, monnaieB) n'est pas trouvée
			return NO_RESULTAT;
		}
		catch (NonUniqueResultException nure) {
            //  exception si plusieurs paires (monnaieA, monnaieB) sont trouvées
			return RESULTAT_MULTIPLE;
		}
	}
    
    public String creerCompte(String login, String passwd) {
        //verifier que l'abonné n'existe pas
        try {
            AbonneEntity a = (AbonneEntity) em.createQuery("SELECT a FROM Abonne a where a.login = :l")
            .setParameter("l", login)
            .getSingleResult();
            //On a trouve un abonne avec ce login => pas bon, login existe deja => on n'insere pas
            return NO_INSERTION;
        }
        catch (NonUniqueResultException nure) {
            //On a trouve un abonne avec ce login => pas bon, login existe  deja => on n'insere pas
            return RESULTAT_MULTIPLE+"\n"+NO_INSERTION;
        }
        catch (NoResultException nre) {
            //c'est bon, l'abonne n'existe pas déjà => on le crée
            AbonneEntity a = new AbonneEntity(login, passwd);
            try {
                // faire passer l'entité à l'état "managed"
                em.persist(a);
                return SUCCESS;
            }
            catch (EntityExistsException eee) {
                //pas bon, cet abonne existe deja => on n'insere pas
                return NO_INSERTION;    
            }
        }        
    }


    


}

