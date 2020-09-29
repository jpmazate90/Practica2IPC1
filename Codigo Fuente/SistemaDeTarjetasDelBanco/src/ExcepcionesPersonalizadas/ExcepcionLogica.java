/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExcepcionesPersonalizadas;

/**
 *
 * @author jpmazate
 */
public class ExcepcionLogica extends Exception {
    // constante necesaria para la excepcion
    public static final long serialVersionUID = 700L;
    //constructor
    public ExcepcionLogica(String mensaje){
        super(mensaje);
    }
}
