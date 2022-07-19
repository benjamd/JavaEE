package ar.com.benjamd.servicio;

import ar.com.benjamd.data.PersonaDAO;
import ar.com.benjamd.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Stateless
@Path("/personas")
public class PersonaServiceRS {
    
    @Inject
    private PersonaDAO personaDAO;
    
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public List<Persona> listarPersonas(){
       List<Persona> personas = personaDAO.encontrarTodasLasPersonas();
        System.out.println("personas encontradas = " + personas);
        return personas;
    }
    
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}") //hace referencia al path: /personas/{id} ej. /personas/1
    public Persona econtrarPersona(@PathParam("id") int id){
        Persona persona = personaDAO.encontrarPersona(new Persona(id));
        System.out.println("persona encontrada = " + persona );
        return persona;
    }
    
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Persona agregarPersona(Persona persona){
        personaDAO.insertarPersona(persona);
        System.out.println("persona agregada = " + persona);
        return persona;
    }
    
    @PUT
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response modificarPersona(@PathParam("id") int id, Persona personaModificada){
        Persona persona = personaDAO.encontrarPersona(new Persona(id));
        
        
        if(persona != null) {
            personaDAO.actualizarPersona(personaModificada);
            System.out.println("personaModificada = " + personaModificada);
            return Response.ok().entity(personaModificada).build();
        }
        
        return Response.status(Status.NOT_FOUND).build();
    }
    
    @DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response eliminarPersona(@PathParam("id") int id){
        personaDAO.eliminarPersona(new Persona(id));
        System.out.println("persona eliminada con el id = " + id);
        return Response.ok().build();
    }
}
