 
package test;

import ar.com.benjamd.domain.Persona;
import com.sun.enterprise.security.ee.auth.login.ProgrammaticLogin;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ar.com.benjamd.servicio.IPersonaServiceRemote;

public class ClientePersonaService {
    
    public static void main(String[] args) {
        System.out.println("Iniciando llamada al EJB desde el cliente");
        
        String authFile ="login.conf";
        System.setProperty("java.security.auth.login.config", authFile);
        ProgrammaticLogin programmaticLogin = new ProgrammaticLogin();
        
        programmaticLogin.login("admin", "admin".toCharArray());
        
        try {
            Context jndi = new InitialContext();
            IPersonaServiceRemote personaService = (IPersonaServiceRemote) jndi.lookup("java:global/SGA-JEE-WEB-SECURITY/PersonaServiceImpl!ar.com.benjamd.servicio.IPersonaServiceRemote");
            
            List<Persona> personas = personaService.listarPersonas();
            
            for(Persona p: personas){
                System.out.println("persona = " + p);
            }
            System.out.println("\nFin de la llamada al EJB desde el cliente");
            
            
        
        } catch (NamingException ex) {
            ex.printStackTrace(System.out);
        }
        
    }
    
}
