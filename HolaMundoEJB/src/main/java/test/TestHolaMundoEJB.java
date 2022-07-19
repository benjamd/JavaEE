
package test;

import beans.HolaMundoEjbRemote;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author benja
 */
public class TestHolaMundoEJB {
    public static void main(String[] args) {
        System.out.println("Iniciando llamada al EJB al cliente\n");
        
        try{
            Context jndi = new InitialContext();
            HolaMundoEjbRemote holamundoEJB = (HolaMundoEjbRemote) jndi.lookup("java:global/HolaMundoEJB/HolaMundoEjbImpl!beans.HolaMundoEjbRemote");
            int resultado = holamundoEJB.sumar(5, 3);
            System.out.println("resultado EJB sumar 5 + 3 = " + resultado);
        } catch (NamingException ex){
            ex.printStackTrace(System.out);
        }
    }
    
}
