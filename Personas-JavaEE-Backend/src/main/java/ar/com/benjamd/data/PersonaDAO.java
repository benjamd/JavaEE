package ar.com.benjamd.data;

import ar.com.benjamd.domain.Persona;
import java.util.List;

public interface PersonaDAO {

    public List<Persona> encontrarTodasLasPersonas();
    
    public Persona encontrarPersona(Persona persona);
    
    public void insertarPersona(Persona persona);

    public void actualizarPersona(Persona persona);

    public void eliminarPersona(Persona persona);    
    
}
