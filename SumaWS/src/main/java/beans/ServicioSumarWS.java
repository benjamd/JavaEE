/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package beans;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ServicioSumarWS {
    
    @WebMethod
    public int sumar(int a, int b);
    
    
}
