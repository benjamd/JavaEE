
package ar.com.benjamd.servicio;

import ar.com.benjamd.domain.Persona;
import java.util.List;
import javax.ejb.Local;


@Local
public interface IPersonaService {
    
        
    public List<Persona> listarPersonas();
    
    public Persona encontarPersonaPorId(Persona persona);
    
    public Persona encontarPersonaPorEmail(Persona persona);
    
    public void registarPersona(Persona persona);
    
    public void modificarPersona(Persona persona);
    
    public void eliminarPersona(Persona persona);
    
}
