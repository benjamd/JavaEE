/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.benjamd.cliente;

import ar.com.benjamd.domain.Persona;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ar.com.benjamd.servicio.IPersonaServiceRemote;


public class ClientePersonaService {

    public static void main(String[] args) {
        
        System.out.println("Iniciamos la llamada al EJB desde el cliente");
        
        try {
             Context jndi = new InitialContext();
             IPersonaServiceRemote personaService = (IPersonaServiceRemote) jndi.lookup("java:global/SGA-JEE/PersonaServiceImpl!ar.com.benjamd.servicio.PersonaServiceRemote");
             
             List<Persona> personas = personaService.listarPersonas();
             
             for(Persona persona: personas){
                 System.out.println("persona = " + persona);
             }
             System.out.println("\n Fin de la llamada al EJB desde el cliente");
             
        } catch (NamingException ex) {
            ex.printStackTrace(System.out);
        }
        
        
    }
    
}
