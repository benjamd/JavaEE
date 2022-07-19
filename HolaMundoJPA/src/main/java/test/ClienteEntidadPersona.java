
package test;

import ar.com.benjamd.domain.Persona;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

 
public class ClienteEntidadPersona {
    static Logger log = LogManager.getRootLogger();
     
    public static void main(String[] args) {
        //para abrir el objeto manager de jpa relacionado a la unidad de persistencia definido en persistence.xml
        
        
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersonaPU");
        //instanciamos el objeto EntityManager
        EntityManager em = emf.createEntityManager();
        //abrimos una transaccion por medio del objeto EntityManager
        EntityTransaction tx = em.getTransaction();
        
        //Inicia la transaccion
        tx.begin();
        //no se debe especificar el ID de la base de datos
        Persona persona1 = new Persona("Maria", "Gutierrez", "maguig@gmail.com", "155493321");
        log.debug("Objeto a persistir: " + persona1 );
        //Persistimos el objeto
        em.persist(persona1);
        //terminamos el commit de la transaccion
        tx.commit();
         log.debug("Objeto a persistido: " + persona1 );
         //cerramos el objeto EntityManager
         em.close();
        
    }
    
}
