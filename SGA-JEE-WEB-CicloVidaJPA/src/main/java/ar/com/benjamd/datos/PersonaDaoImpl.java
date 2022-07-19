/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.benjamd.datos;

import ar.com.benjamd.domain.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 
@Stateless
public class PersonaDaoImpl implements IPersonaDao {

    @PersistenceContext(unitName = "PersonaPU")
    EntityManager em;
    
    @Override
    public List<Persona> findAllPersonas() {
        return em.createNamedQuery("Persona.findAll").getResultList();
    }

    @Override
    public Persona findAllPersonaById(Persona persona) {
        return em.find(Persona.class, persona.getIdPersona());
    }

    @Override
    public Persona findAllPersonaByEmail(Persona persona) {
        Query query = em.createQuery("FROM Persona p WHERE p.email =: email");
        query.setParameter("email", persona.getEmail());
        //el campo email en la base de datos es UNIQUE, sabemos que de existir solo regresara un unico valor 
        return (Persona) query.getSingleResult();
        
        
    }

    @Override
    public void insertPersona(Persona persona) {
        em.persist(persona);
    }

    @Override
    public void updatePersona(Persona persona) {
        em.merge(persona);  
    }

    @Override
    public void deletePersona(Persona persona) {
        //Previo a eliminar primero debemos sincronizar el objeto con la base de datos
        //Para eso actualizamos el estado em.merge(Object)
        em.remove(em.merge(persona));
    }
    
}
