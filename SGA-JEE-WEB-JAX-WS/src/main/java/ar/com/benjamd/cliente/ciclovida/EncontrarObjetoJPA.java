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

public class EncontrarObjetoJPA {

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
        Persona persona1 = em.find(Persona.class,41);

        
        //Paso 3 termina la transacción 
        tx.commit();

        //imprimimos el objeto en estado Detached
        log.debug("Objeto recupedado: " + persona1);

        //Cerramos el EntityManager
        em.close();

    }

}
