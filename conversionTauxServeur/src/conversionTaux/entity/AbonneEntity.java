package conversionTaux.entity;

import jakarta.persistence.*;
import java.util.*;

/*
 cf l'exo du CM :
 classe java à completer et a transformer en classe entity JPA
- attributs : idAbonne, login, passwd et l'attribut resultant de la traduction de l'association entre AbonneEntity et FavoriEntity
- getters-setters de chaque attribut et au moins un constructeur vide
- annotations JPA
 */
@Entity
@Table(name ="Abonne")
public class AbonneEntity {

    @Id
    @GeneratedValue
    private int idAbonne;
    private String login;
    private String passwd;

    @OneToMany
    private List<FavoriEntity> Listfavori ;

    public int getIdAbonne (){
        return this.idAbonne;
    }

    public String getLogin(){
        return this.login;
    }

    public String getPasswd(){
        return this.passwd;
    }
    
    public List<FavoriEntity> getListfavori(){
        return this.Listfavori;
    }
    

    public void setIdAbonne( int id){
        this.idAbonne = id ;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public void setPasswd(String passwd){
        this.passwd = passwd;
    }

    public void setListfavori(List<FavoriEntity> listfavori){
        this.Listfavori = listfavori;
    }

}
