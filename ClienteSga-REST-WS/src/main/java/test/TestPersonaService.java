/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import domain.Persona;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author benja
 */
public class TestPersonaService {

    //Variables a utilizar
    private static final String URL_BASE = "http://localhost:8080/SGA-JEE-WEB-JAX-RS/webservice";
    private static Client cliente;
    private static WebTarget webTarget;
    private static Persona persona;
    private static List<Persona> personas;
    private static Invocation.Builder invocationBuilder;
    private static Response response;

    public static void main(String[] args) {
        cliente = ClientBuilder.newClient();

        //Leer una persona (metodo get)
        webTarget = cliente.target(URL_BASE).path("/personas");
        //Proporcionamos un idPersona valido
        persona = webTarget.path("/21").request(MediaType.APPLICATION_XML).get(Persona.class);
        System.out.println("Persona recuperada: " + persona);

        //Leer todas las personas (metodo get con readEntity de tipo List<>
        personas = webTarget.request(MediaType.APPLICATION_XML).get(Response.class).readEntity(new GenericType<List<Persona>>() {
        });
        System.out.println("\nPersonas Recuperadas:");
        imprimirPersonas(personas);

/*        
        //Agregar una Persona
        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre("Damian");
        nuevaPersona.setApellido("Sanchez");
        nuevaPersona.setEmail("dsanchez@hotmail.com");
        nuevaPersona.setTelefono("1159529873");
        
        invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        response = invocationBuilder.post(Entity.entity(nuevaPersona,MediaType.APPLICATION_XML));
        System.out.println("");
        System.out.println("Response Status: " + response.getStatus());
        //Recuperamos la persona recien agregada para despues modificarla y final eliminarla
        
        Persona personaRecuperada = response.readEntity(Persona.class);
        System.out.println("personaRecuperada: " + personaRecuperada);
        
        //Modificar persona agregada (metodo Put)
        //persona recuperada anteriormente
        Persona personaModificar = personaRecuperada;
        personaModificar.setApellido("James");
        String pathId = "/" + personaModificar.getIdPersona();
        invocationBuilder = webTarget.path(pathId).request(MediaType.APPLICATION_XML);
        response = invocationBuilder.put(Entity.entity(personaModificar, MediaType.APPLICATION_XML));
        
        System.out.println("");
        System.out.println("Response Status: " + response.getStatus());
        System.out.println("personaRecuperada: " + response.readEntity(Persona.class));
    
        //eliminar una persona (metodo delete)
        //persona recuperada anteriormente
        Persona personaEliminar = personaModificar;
        String pathEliminarId = "/" + personaEliminar.getIdPersona();
        invocationBuilder = webTarget.path(pathEliminarId).request(MediaType.APPLICATION_XML);
        response = invocationBuilder.delete();
        
        System.out.println("");
        System.out.println("Response Status: " + response.getStatus());
        System.out.println("persona eliminada: " + personaEliminar);
*/
        
        //eliminar una persona (metodo delete)
        //persona recuperada anteriormente

        invocationBuilder = webTarget.path("/21").request(MediaType.APPLICATION_XML);
        response = invocationBuilder.delete();
        
        System.out.println("");
        System.out.println("Response Status: " + response.getStatus());
        System.out.println("persona eliminada: " + persona);

        
        
        
    }

    private static void imprimirPersonas(List<Persona> personas) {
        for (Persona p : personas) {
            System.out.println("Persona: " + p);
        }
    }

}
