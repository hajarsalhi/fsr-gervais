package conversionTaux.entity;

import jakarta.persistence.*;
import java.util.*;

/*
 classe java à completer et a transformer en classe entity JPA
- attributs : idAbonne, login, passwd 
- getters-setters de chaque attribut et au moins un constructeur vide
- annotations JPA
 */

@Entity
@Table(name="Admin")
public class AdminEntity {

    @Id
    @GeneratedValue
	private int idAdmin;
    private String login;
    private String passwd;

    public AdminEntity() {
    }

    public int getIdAdmin ()  {
        return this.idAdmin;
    }

    public void setIdAdmin (int value)  {
        this.idAdmin = value;
    }

    
    public String getLogin ()  {
        return this.login;
    }

    public void setLogin (String value)  {
        this.login = value;
    }

    
    public String getPasswd ()  {
        return this.passwd;
    }

    public void setPasswd (String value)  {
        this.passwd = value; 
    }

    

    
}
