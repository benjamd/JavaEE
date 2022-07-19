/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.benjamd.cliente.criteria;

import ar.com.benjamd.domain.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

 
public class PruebaCriteriaAPI {
    
    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
         EntityManager em = emf.createEntityManager();
         
         CriteriaBuilder cb = em.getCriteriaBuilder();
         CriteriaQuery<Persona> criteriaQuery = null;
         Root<Persona> fromPersona = null;
         TypedQuery<Persona> query = null;
         Persona persona = null;
         List<Persona> personas = null;
         
         //Query usando api criteria
         
         //1.Cosulta de todas las personas
         log.debug("\n1. Cosulta de todas las personas");
         
         //Paso 1. EntityManager crea una isntancia de CriteriaBuilder
         cb = em.getCriteriaBuilder();
         
         //Paso 2. Se crea el objeto CriteriaQuery
         criteriaQuery = cb.createQuery(Persona.class);
         
         //Paso 3 Creamos el objeto raiz del query
         fromPersona = criteriaQuery.from(Persona.class);
         
         //Paso 4. Seleccionamos lo necesario del from
         criteriaQuery.select(fromPersona);
         
         //Paso 5 creamos el query TypeSafe
         query = em.createQuery(criteriaQuery);
         
         //Paso 6. ejecutamos las consultas
         personas = query.getResultList();
         
        //mostrarPersonas(personas);
         
        
        //2.a. Consulta de la persona con id = 1
        //jpql = "select p from Persona p where p.idPersona = 1";
        log.debug("\n2-a.  Consulta de la persona con id = 1");
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Persona.class);
        fromPersona = criteriaQuery.from(Persona.class);
        criteriaQuery.select(fromPersona).where(cb.equal(fromPersona.get("idPersona"), 1));
        persona = em.createQuery(criteriaQuery).getSingleResult();
        //log.debug(persona);
         
        //2-b.  
        cb = em.getCriteriaBuilder();
        criteriaQuery = cb.createQuery(Persona.class);
        fromPersona = criteriaQuery.from(Persona.class);
        criteriaQuery.select(fromPersona);
        
        //La clase Predicate nos permite agregar criterios dinamicamente
        List<Predicate> criterios = new ArrayList<Predicate>();
        
        //verificamos si tenemos criterios que agregar
        Integer idPersonaParam = 1;
        ParameterExpression<Integer> parameter = cb.parameter(Integer.class,"idPersona");
        criterios.add( cb.equal(fromPersona.get("idPersona"), parameter));
        
        
        //se agregan los criterios
        if(criterios.isEmpty()){
            throw new RuntimeException("Sin Criterios");
        } else if (criterios.size() == 1){
            criteriaQuery.where(criterios.get(0));
            
        } else {
          criteriaQuery.where(cb.and(criterios.toArray(new Predicate[0])));
        }
           
        query = em.createQuery(criteriaQuery);
        query.setParameter("idPersona", idPersonaParam);
                
        persona = query.getSingleResult();
        log.debug(persona);
         
    }

    private static void mostrarPersonas(List<Persona> personas) {
        for(Persona p: personas){
            log.debug(p);
        }
    }
    
}
