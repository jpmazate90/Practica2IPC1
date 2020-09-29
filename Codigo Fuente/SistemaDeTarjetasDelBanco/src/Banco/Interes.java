
package Banco;

/**
 *
 * @author jpmazate
 */
public class Interes {
    // atributos de la clase interes
    private static final String NACIONAL= "nacional";
    private static final String REGIONAL= "regional";
    private static final String INTERNACIONAL= "internacional";
    private static final double INTERES_NACIONAL = 0.012;
    private static final double INTERES_REGIONAL= 0.023;
    private static final double INTERES_INTERNACIONAL=0.0375 ;
    // metodo estatico que calcula el interes segun el monto y el tipo de tarjeta que se ingrese
    public static double calcularIntereses(double monto, String tipo){
        double intereses=0;
        if(tipo.equals(NACIONAL)){
            intereses= monto*INTERES_NACIONAL;
        }else if(tipo.equals(REGIONAL)){
            intereses = monto*INTERES_REGIONAL;
        }else{
            intereses = monto*INTERES_INTERNACIONAL;
        }
        
        
        return intereses;
        
    }

}
