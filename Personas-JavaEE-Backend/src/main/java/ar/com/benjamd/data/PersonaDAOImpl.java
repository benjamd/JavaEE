package ar.com.benjamd.data;

import ar.com.benjamd.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



@Stateless
public class PersonaDAOImpl implements PersonaDAO {

    @PersistenceContext(unitName = "PersonaPU")
    EntityManager em;
    
    
    @Override
    public List<Persona> encontrarTodasLasPersonas() {
        return  em.createNamedQuery("Persona.encontrarTodasPersonas").getResultList();
    }

    @Override
    public Persona encontrarPersona(Persona persona) {
         return  em.find(Persona.class, persona.getIdPersona());
    }

    @Override
    public void insertarPersona(Persona persona) {
        em.persist(persona);
        em.flush();
    }

    @Override
    public void actualizarPersona(Persona persona) {
        em.merge(persona);
    }

    @Override
    public void eliminarPersona(Persona persona) {
        em.refresh(em.merge(persona));
    }
    
}
