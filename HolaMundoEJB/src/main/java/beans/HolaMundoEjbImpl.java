/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import javax.ejb.Stateless;

/**
 *
 * @author benja
 */
@Stateless
public class HolaMundoEjbImpl implements HolaMundoEjbRemote {

    @Override
    public int sumar(int a, int b) {
        System.out.println("Ejecutando suma en el servidor");
        return a + b;
    }
    
    
}
