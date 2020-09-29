
package Banco;

import java.io.Serializable;

/**
 *
 * @author jpmazate
 */

public class ConsultarTarjeta implements Serializable{
    private String tarjetaAConsultar;
    private Tarjeta tarjetaConsultada;
    // constructor de la clase consultar tarjeta
    public ConsultarTarjeta(String tarjetaAConsultar) {
        this.tarjetaAConsultar = tarjetaAConsultar;
    }
    //getters and setters
    public String getTarjetaAConsultar() {
        return tarjetaAConsultar;
    }

    public void setTarjetaAConsultar(String tarjetaAConsultar) {
        this.tarjetaAConsultar = tarjetaAConsultar;
    }

    public Tarjeta getTarjetaConsultada() {
        return tarjetaConsultada;
    }

    public void setTarjetaConsultada(Tarjeta tarjetaConsultada) {
        this.tarjetaConsultada = tarjetaConsultada;
    }
    
    
}
