/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testclientews;

import clientews.servicio.Persona;
import clientews.servicio.PersonaServiceImplService;
import clientews.servicio.PersonaServiceWS;
import java.util.List;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author benja
 */
public class TestPersonaServiceWS {
    
    public static void main(String[] args) {
        PersonaServiceWS personaService = new PersonaServiceImplService().getPersonaServiceImplPort();
        
        ((BindingProvider)personaService).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "admin");
        ((BindingProvider)personaService).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "admin");
                
                
        System.out.println("Ejecutando servicio listar personas ws");
        List<Persona> personas = personaService.listarPersonas();
        for(Persona persona: personas) {
            System.out.println(persona.toString());
            
        }
        
        System.out.println("\nDesde Consola ejecutando curl:");
        System.out.println("\ncurl -v -u admin:admin --request POST --header \"Content-Type: text/xml;charset=UTF-8\" "
                + "--data ' <S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" "
                + "xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"> "
                + "<SOAP-ENV:Header/> "
                + "<S:Body xmlns:ns2=\"http://servicio.benjamd.com.ar/\"> "
                + "<ns2:listarPersonas/> "
                + "</S:Body> "
                + "</S:Envelope>' http://localhost:8080/PersonaServiceImplService/PersonaServiceImpl | xmllint --format -");
    }
    
}
