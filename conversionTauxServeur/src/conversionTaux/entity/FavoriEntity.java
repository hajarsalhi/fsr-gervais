package conversionTaux.entity;

import jakarta.persistence.*;
import java.util.*;

/*
 cf l'exo du CM :
 classe java à completer et a transformer en classe entity JPA
- attributs : idFavori, libelleFavori et l'attribut resultant de la traduction de l'association entre FavoriEntity et TauxEntity
- getters-setters de chaque attribut et au moins un constructeur vide
- annotations JPA
 */
@Entity
@Table(name ="Favori")
public class FavoriEntity {

    @Id
    @GeneratedValue
    private int idFavori;
    private String libelleFavori;
    private int leTaux_idTaux;

    @ManyToOne
    private TauxEntity Taux ;

    public FavoriEntity (){

    }

    public int getIdFavori(){
        return this.idFavori;
    }


    public String getLibelleFavori(){
        return this.idFavori;
    }
    
    public int getLeTaux_idTaux(){
        return this.idFavori;
    }

    
    public TauxEntity getTaux (){
        return this.Taux;
    }
    
    public void setIdFavori(int idFavori){
        this.idFavori= idFavori ;
    }
    

    public void setLibelleFavori(String LibelleFavori){
        this.libelleFavori= LibelleFavori ;
    }

    public void setLeTaux_idTaux(int LeTaux_idTaux){
        this.leTaux_idTaux = LeTaux_idTaux ;
    }

    public void setTaux (TauxEntity taux ){
         this.Taux = taux;
    }
    

    public FavoriEntity(String libelleFavori,TauxEntity Taux){
        this.libelleFavori = libelleFavori;
        this.Taux = Taux;
    }
}
