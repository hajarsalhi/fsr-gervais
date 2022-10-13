@Singleton
public class ConversionSingleton {
    public int cmpteur =0;
    private ConversionSingleton instance = null;

    public ConversionSingleton getInstance (){
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