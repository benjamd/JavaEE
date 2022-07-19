/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.benjamd.cliente.jpql;

import ar.com.benjamd.domain.Persona;
import ar.com.benjamd.domain.Usuario;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PruebaJPQL {

    static Logger log = LogManager.getRootLogger();

    public static void main(String[] args) {
        String jpql = null;
        Query q = null;
        List<Persona> personas = null;
        Persona persona = null;
        Iterator iter = null;
        Object[] tupla = null;
        List<String> nombres = null;
        List<Usuario> usuarios = null;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SgaPU");
        EntityManager em = emf.createEntityManager();

        //1. Consulta de todso los objetso de tipo Persona
        //log.debug("\n1. Consulta de todas las Personas");
        jpql = "select p from Persona p";
        personas = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);

        //2.Consulta de la persona con id = 2 
        //log.debug("\n2. Consulta de Persona con id = 2");
        jpql = "select p from Persona p where p.idPersona = 2";
        persona = (Persona) em.createQuery(jpql).getSingleResult();
        //log.debug(persona);

        //3. Consulta objeto persona filtrando por nombre
        jpql = "select p from Persona p where p.nombre = 'manolo'";
        personas = em.createQuery(jpql).getResultList();
        mostrarPersonas(personas);

        //4. Consutla de datos individuales, se crea un arreglo (tupla) de tipo Object de 3 columnas
        log.debug("\n4. Consulta de datos individuales, se crea un arreglo (tupla) de tipo Object de 3 columnas");
        jpql = "select p.nombre as Nombre, p.apellido as Apellido, p.email as Email from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            String nombre = (String) tupla[0];
            String apellido = (String) tupla[1];
            String email = (String) tupla[2];
            //log.debug("nombre: " + nombre  +", apellido: " + apellido + ", email: " + email);
        }

        //5. Consulta objeto Persona y el id, se crea arreglo object con 2 columnas
        log.debug("\n5. Consulta de datos individuales, se crea un arreglo (tupla) de tipo Object de 3 columnas");
        jpql = "select p, p.idPersona from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            persona = (Persona) tupla[0];
            int idPersona = (int) tupla[1];
            //log.debug("IdPersona: " + idPersona  + "\n Objeto persona: " + persona);
        }

        //6. Consulta de todas las personas
        log.debug("\n6. Consulta de todas las personas usando codigo java ( el constructor para instanciar obj. Persona y obtener el idPersona");
        jpql = "select new ar.com.benjamd.domain.Persona( p.idPersona) from Persona p";
        personas = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);

        //7. Regresa el valor minimo y maximo de idPersona (Scalar Result)
        log.debug("\n7. Regresa el valor minimo y maximo de idPersona (Scalar Result)");
        jpql = "select min(p.idPersona) as MinId, max(p.idPersona) as MaxId, count(p.idPersona) as Contador from Persona p";
        iter = em.createQuery(jpql).getResultList().iterator();
        while (iter.hasNext()) {
            tupla = (Object[]) iter.next();
            Integer idMin = (Integer) tupla[0];
            Integer idMax = (Integer) tupla[1];
            Long contador = (Long) tupla[2];
            //log.debug("idMin: " + idMin  +", idMax: " + idMax + ", contador: " + contador);
        }

        //8. Cuenta los nombres de las personas que son distintos
        log.debug("\n8. Cuenta los nombres de las personas que son distintos");
        jpql = "select count (distinct p.nombre) from Persona p";
        Long contador = (Long) em.createQuery(jpql).getSingleResult();
        //log.debug("numero de personas con nombre distinto: " + contador);

        //9. Concatena y convierte a mayusculas el nombre y apellido
        log.debug("\n9. Concatena y convierte a mayusculas el nombre y apellido");
        jpql = "select CONCAT(p.nombre, ' ', p.apellido) as Nombre from Persona p";
        nombres = em.createQuery(jpql).getResultList();
        for (String nombreCompleto : nombres) {
            //  log.debug("nombre completo: " + nombreCompleto);
        }

        //10. Obtiene el objeto persona con id igual al parametro proporcionado
        log.debug("\n10. Obtiene el objeto persona con id igual al parametro proporcionado");
        int idPersona = 1;
        jpql = "select p from Persona p where p.idPersona = :id";
        q = em.createQuery(jpql);
        q.setParameter("id", idPersona);
        persona = (Persona) q.getSingleResult();
        //log.debug("persona: " + persona);

        //11. Obtiene las personas que contienen la letra 'a' en el nombre, sin importar si es mayuscula o minuscula
        log.debug("\n11. Obtiene las personas que contienen la letra 'a' en el nombre, sin importar si es mayuscula o minuscula");
        jpql = "select p from Persona p where upper(p.nombre) like upper(:parametro)";
        String parametro = "%a%";
        q = em.createQuery(jpql);
        q.setParameter("parametro", parametro);
        personas = q.getResultList();
        //mostrarPersonas(personas);

        //12. Uso de between
        log.debug("\n12. Uso de between");
        jpql = "select p from Persona p where p.idPersona between 1 and 10";
        personas = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);

        //13. Uso del ordenamiento 
        log.debug("\n13. Uso del ordenamiento");
        jpql = "select p from Persona p where p.idPersona > 3 order by p.nombre desc, p.apellido desc";
        personas = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);

        //14. Uso de subquery
        log.debug("\n14. Uso de subquery");
        jpql = "select p from Persona p where p.idPersona in (select min(p1.idPersona) from Persona p1)";
        personas = em.createQuery(jpql).getResultList();
        //mostrarPersonas(personas);

        //15.Uso de join con lazy loading
        log.debug("\n15.Uso de join con lazy loading");
        jpql = "select u from Usuario u join u.persona p";
        usuarios = em.createQuery(jpql).getResultList();
        //mostrarUsuarios(usuarios);
        
        //16.Uso de left join con eager loading
        log.debug("\n16.Uso de left join con eager loading");
        jpql = "select u from Usuario u left join fetch u.persona";
        usuarios = em.createQuery(jpql).getResultList();
        mostrarUsuarios(usuarios);
        
        
    }

    private static void mostrarPersonas(List<Persona> personas) {
        for (Persona p : personas) {
            log.debug(p);
        }
    }

    private static void mostrarUsuarios(List<Usuario> usuarios) {

        for (Usuario u : usuarios) {
            log.debug(u);
        }
    }

}
