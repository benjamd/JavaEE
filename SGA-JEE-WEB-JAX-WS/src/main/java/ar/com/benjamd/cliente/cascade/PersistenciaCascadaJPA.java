/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.benjamd.cliente.cascade;

import ar.com.benjamd.domain.Persona;
import ar.com.benjamd.domain.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author benja
 */
public class PersistenciaCascadaJPA {
    
    static Logger log = LogManager.getRootLogger();
    
    public static void main(String[] args) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em =  emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        
        //Paso 1 crea nuevo objeto
        //objeto en estado transitivo
        Persona persona1 = new Persona("Hugo", "hernandez", "huguitoh@yahoo.com.ar", "1533491395");
        
        //Paso 2 Crear usuario (tiene dependencia con el objeto persona
        Usuario usuario1 = new Usuario("hugo", "arcade",persona1);
        
        //Paso 3 persistimos unicamente el objeto usuario
        em.persist(usuario1);
        
        //Paso 4 commit transaccion
        tx.commit();
        
        //objetos en estado detached
        log.debug(("objeto persistido persona: " + persona1));
        log.debug(("objeto persistido usaurio: " + usuario1));
    }
    
    
}
