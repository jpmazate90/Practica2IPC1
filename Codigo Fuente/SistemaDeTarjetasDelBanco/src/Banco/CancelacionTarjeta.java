
package Banco;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jpmazate
 */
public class CancelacionTarjeta implements Serializable{
    //atributos privados de cancelacion
    private String tarjetaACancelar;
    private boolean tieneSaldoPendiente;
    private boolean estaActiva;
    private Date fechaCancelada;
    
    //getters and setters
    public CancelacionTarjeta(String tarjetaACancelar) {
        this.tarjetaACancelar = tarjetaACancelar;
        
    }

    public String getTarjetaACancelar() {
        return tarjetaACancelar;
    }

    public void setTarjetaACancelar(String tarjetaACancelar) {
        this.tarjetaACancelar = tarjetaACancelar;
    }

    public boolean isTieneSaldoPendiente() {
        return tieneSaldoPendiente;
    }

    public void setTieneSaldoPendiente(boolean tieneSaldoPendiente) {
        this.tieneSaldoPendiente = tieneSaldoPendiente;
    }

    public boolean isEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }

    public Date getFechaCancelada() {
        return fechaCancelada;
    }

    public void setFechaCancelada(Date fechaCancelada) {
        this.fechaCancelada = fechaCancelada;
    }
    
    


}
