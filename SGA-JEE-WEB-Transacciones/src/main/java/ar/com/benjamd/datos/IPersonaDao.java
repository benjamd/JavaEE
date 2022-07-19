
package ar.com.benjamd.datos;

import ar.com.benjamd.domain.Persona;
import java.util.List;


public interface IPersonaDao {

    public List<Persona> findAllPersonas();
    
    public Persona findAllPersonaById(Persona persona);
    
    public Persona findAllPersonaByEmail(Persona persona);
    
    public void insertPersona(Persona persona);
    
    public void updatePersona(Persona persona);
    
    public void deletePersona(Persona persona);
    
}
