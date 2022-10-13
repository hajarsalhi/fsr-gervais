import javax.naming.InitialContext;
import java.io.*;
import java.util.*;

public class Main {
public static void main(String[] args) throws Exception {

    /*
    doit permettre de tester les services. Il faut garantir que tous les scénarios possibles s'exécutent correctement.
    
     scenario 1
     1) un abonne a1 se connecte => ok
     2) l'abonne a1 se déconnecte => ok
     3) un abonne a2 se connecte => ok
     4) un admin se connecte => ok
     5) l'admin connecté consulte le nb de connexion => ok = 2
     6) deconnexion de tous
     
     scenario 2
     1) un admin ad1 consulte le nb de connexion => nok
     2) l'admin ad1 se connecte => ok
     3) l'admin connecté ad1 consulte le nb de connexion => ok = 2
     4) un admin ad2 consulte le nb de connexion => nok
     5) deconnexion de l'admin ad1
     
     Tous les autres services des deux itérations précédentes
     doivent encore être opérationnels, vous pouvez donc
     ajouter leurs scénarios une fois ceux de cette itération ok.
     Vérifier que tout est resté ok.
    
     */
}
