
package ReportesBanco;

import Banco.AutorizacionTarjeta;
import Banco.CancelacionTarjeta;
import Banco.Tarjeta;
import java.util.ArrayList;

/**
 *
 * @author jpmazate
 */
public class ListadoTarjetas {
    
    //variables de la clase(privadas)
    private AutorizacionTarjeta fechaTarjetaAutorizada;
    private CancelacionTarjeta fechaTarjetaCancelada;
    //constructor
    public ListadoTarjetas(ArrayList<Tarjeta> tarjetasEnElSistema) {
        
    }
    // getters and setters
    public AutorizacionTarjeta getFechaTarjetaAutorizada() {
        return fechaTarjetaAutorizada;
    }

    public void setFechaTarjetaAutorizada(AutorizacionTarjeta fechaTarjetaAutorizada) {
        this.fechaTarjetaAutorizada = fechaTarjetaAutorizada;
    }

    public CancelacionTarjeta getFechaTarjetaCancelada() {
        return fechaTarjetaCancelada;
    }

    public void setFechaTarjetaCancelada(CancelacionTarjeta fechaTarjetaCancelada) {
        this.fechaTarjetaCancelada = fechaTarjetaCancelada;
    }
}
