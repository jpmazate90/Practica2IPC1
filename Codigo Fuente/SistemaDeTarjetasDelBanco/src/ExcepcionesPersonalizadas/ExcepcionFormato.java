
package ExcepcionesPersonalizadas;

/**
 *
 * @author jpmazate
 */
public class ExcepcionFormato extends Exception{
    // codigo obligatorio de la excepcion
    public static final long serialVersionUID= 700L;
    //constructor
    public ExcepcionFormato(String mensaje){
        super(mensaje);
    }
    
}
