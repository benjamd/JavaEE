/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ar.com.benjamd.servicio;

import ar.com.benjamd.domain.Persona;
import java.util.List;
import javax.ejb.Remote;


@Remote
public interface IPersonaServiceRemote {
    
    public List<Persona> listarPersonas();
    
    public Persona encontarPersonaPorId(Persona persona);
    
    public Persona encontarPersonaPorEmail(Persona persona);
    
    public void registarPersona(Persona persona);
    
    public void modificarPersona(Persona persona);
    
    public void eliminarPersona(Persona persona);
}
