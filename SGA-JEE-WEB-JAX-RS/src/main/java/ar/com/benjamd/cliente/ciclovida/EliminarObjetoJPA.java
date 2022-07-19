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

/**
 *
 * @author benja
 */
public class EliminarObjetoJPA {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();

        //Iniciamos la transacción
        //Paso 1 Iniciar una transaccion
        //Objeto en estado transitivo (Transient)
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //Paso 2 Ejecutamos el SQL (hacemos el select del entity persona1)
        //Objeto en estado Syncronized
        Persona persona1 = em.find(Persona.class, 11);

        //imprimimos el objeto en estado Entity
        log.debug("Objeto recupedado: " + persona1);

        //Podriamos eliminamos el objeto en la misma transacción
        //em.remove(persona1);
        
        //Paso 3 terminamos la transacción aplicando sql delete
        //en la misma transacción
        tx.commit();
        
        //imprimimos el objeto en estado Detached
        log.debug("Objeto recupedado: " + persona1);

        //Paso 4 creamos la transacción para hacer el delete
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();

        //Paso 5 ejecutamos el sql delete, el merge se hace para sincronizar
        //la información en memoria con la base  de datos
        em.remove(em.merge(persona1));
        
        //Paso 6 temina la segunda transacción
        tx2.commit();
        
        //imprimimos el objeto en estado Detached
        log.debug("Objeto eliminado (solo existe en memoria): " + persona1);

        
        //Cerramos el EntityManager
        em.close();

    }

}
