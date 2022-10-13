package conversionTaux.entity;

import jakarta.persistence.*;

//classe java à transformer en classe entity JPA
@Entity
@Table(name ="Taux")
public class TauxEntity  {

    @Id
    private int idTaux;

    @ManyToOne
    private FavoriEntity favori;
    
    public int getIdTaux() {
        return this.idTaux;
    }
    
    public void setIdTaux(int idTaux) {
        this.idTaux = idTaux;
    }

    private String monnaieA;

    public String getMonnaieA() {
        return this.monnaieA;
    }
    
    public void setMonnaieA(String MonnaieA) {
        this.monnaieA = MonnaieA;
    }

    private String monnaieB;
    public String getMonnaieB() {
        return this.monnaieB;
    }

    public void setMonnaieB(String MonnaieB) {
        this.monnaieB = MonnaieB;
    }

    private double rate;
    public double getRate() {
        return this.rate;
    }

     public void setRate(double taux) {
        this.rate= taux;
    }

    public FavoriEntity getFavori(){
        this.listfavori;
    }

    public void setFvori(FavoriEntity favori){
        this.favori = favori;
    }

    public TauxEntity() {
    }

    public TauxEntity(int idTaux, String monnaieA, String monnaieB, double taux) {
        this.idTaux = idTaux;
        this.monnaieA = monnaieA;
        this.monnaieB = monnaieB;
        this.taux = taux;
    }
    public TauxEntity(String monnaieA, String monnaieB) {
        
        this.monnaieA = monnaieA;
        this.monnaieB = monnaieB;
        
    }
}
