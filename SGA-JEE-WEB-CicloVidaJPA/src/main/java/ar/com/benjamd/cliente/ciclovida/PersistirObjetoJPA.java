/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.benjamd.cliente.ciclovida;

import ar.com.benjamd.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PersistirObjetoJPA {

    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction tx = em.getTransaction();
        
        //Iniciamos la transacción
        
        //Paso 1 Crear nuevo objeto
        //Objeto en estado transitivo (Transient)
        Persona persona1 = new Persona("Marta", "Sanchez", "marsan@yahoo.com.es", "32414542");
        
        //Paso 2 Inicia la transacción
        tx.begin();
        
        //Paso 3 Ejecutamos el SQL (hacemos el insert del nuevo entity persona1)
        //Objeto en estado Syncronized
        em.persist(persona1);
        
        log.debug("Objeto persistido aún sin commit: " + persona1);
        
        //Paso 4 Commit/Rollback Se graba en la base de datos (si no ocurren errores)
        tx.commit();
        
        // Objeto en estado Detached
        log.debug("Objeto persistido: " + persona1);
        
        //Cerramos el EntityManager
        em.close();
        
        
        
        
    }


    
}
