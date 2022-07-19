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

public class ActualizarObjetoJPA {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();

        //Iniciamos la transacción
        //Paso 1 Iniciar una transaccion
        //Objeto en estado transitivo (Transient)
        EntityTransaction tx1 = em.getTransaction();
        tx1.begin();

        //Paso 2 Ejecutamos el SQL (hacemos el select del entity persona1)
        //Objeto en estado Syncronized
        Persona persona1 = em.find(Persona.class, 1);

        //Paso 3 termina la transacción 1
        tx1.commit();

        //imprimimos el objeto en estado Detached
        log.debug("Objeto recupedado: " + persona1);

        //Paso 4 modificamos el objeto setValue (nuevoValor)
        persona1.setApellido("Altavista");

        //Paso 5 Inicia transacción 2
        EntityTransaction tx2 = em.getTransaction();
        tx2.begin();

        //Paso 6 Modificamos el objeto entity 
        em.merge(persona1);
        
        //Se puede aplicar los cambios antes de terminar la transacción em.flush();
        //em.flush();
        
        //Paso 7 terminamos la transacción 2
        tx2.commit();
        
        //imprimimos el objeto en estado Detached
        log.debug("Objeto recupedado: " + persona1);

        
        
        //Cerramos el EntityManager
        em.close();

    }

}
