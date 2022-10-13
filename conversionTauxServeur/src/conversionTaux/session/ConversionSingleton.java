
package conversionTaux.session;

import jakarta.ejb.*;
import jakarta.persistence.*;


@Singleton
public class ConversionSingleton {
    public int cmpteur =0;
    private static ConversionSingleton instance = null;
    private ConversionSingleton(){
        
    }

    public static ConversionSingleton getInstance (){
        if (instance == null)
        {
            instance = new ConversionSingleton();
            return instance ;
        }
        else {
            return instance ;
        }
    }

    public int lire () {
        return this.cmpteur ;
    }

    @Lock(LockType.WRITE)
    public void ecrire(){
        this.cmpteur ++;
    }
}