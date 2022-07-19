/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.benjamd.cliente;

import ar.com.benjamd.domain.Persona;
import javax.naming.*;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ar.com.benjamd.servicio.IPersonaServiceRemote;


/**
 *
 * @author benja
 */
public class PruebaManejoTransacciones {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        try {
            Context jndi = new InitialContext();
            IPersonaServiceRemote  personaService =  (IPersonaServiceRemote) jndi.lookup("java:global/SGA-JEE-WEB-Transacciones/PersonaServiceImpl!ar.com.benjamd.servicio.IPersonaServiceRemote");
                   
            
            log.debug("Iniciamos prueba Manejo Transacciones PresonaServide");
            
            //Buscar un objeto persona
            Persona persona1 = personaService.encontarPersonaPorId(new Persona(1));
            
            log.debug("Persona recuperada: " + persona1);
            
            //cambiar el apellido
            persona1.setApellido("altavista");
            
            personaService.modificarPersona(persona1);
            log.debug("Objeto modificado: " + persona1);
            log.debug("Fin de prueba EJB Transaccional");
            
            
        } catch (NamingException ex) {
            log.debug(ex.getMessage());
        }
        
    }
    
}
