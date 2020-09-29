
package ReportesBanco;

import Banco.AutorizacionTarjeta;
import Banco.CancelacionTarjeta;
import Banco.Tarjeta;
import SolicitudTarjeta.Solicitud;
import java.util.ArrayList;

/**
 *
 * @author jpmazate
 */
public class ListadoSolicitudes {
    // variables privadas de la clase
    private Tarjeta datosTarjeta;
    private Solicitud solicitudTarjeta;
    private AutorizacionTarjeta tarjetaAEvaluar;
    private CancelacionTarjeta tarjetaCanceladaAEvaluar;
    //cONSTRUCTOR
    public ListadoSolicitudes(ArrayList<Solicitud> solicitudesAProcesar) {
       
    }
    //getters and setters
    public Tarjeta getDatosTarjeta() {
        return datosTarjeta;
    }

    public void setDatosTarjeta(Tarjeta datosTarjeta) {
        this.datosTarjeta = datosTarjeta;
    }

    public Solicitud getSolicitudTarjeta() {
        return solicitudTarjeta;
    }

    public void setSolicitudTarjeta(Solicitud solicitudTarjeta) {
        this.solicitudTarjeta = solicitudTarjeta;
    }

    public AutorizacionTarjeta getTarjetaAEvaluar() {
        return tarjetaAEvaluar;
    }

    public void setTarjetaAEvaluar(AutorizacionTarjeta tarjetaAEvaluar) {
        this.tarjetaAEvaluar = tarjetaAEvaluar;
    }

    public CancelacionTarjeta getTarjetaCanceladaAEvaluar() {
        return tarjetaCanceladaAEvaluar;
    }

    public void setTarjetaCanceladaAEvaluar(CancelacionTarjeta tarjetaCanceladaAEvaluar) {
        this.tarjetaCanceladaAEvaluar = tarjetaCanceladaAEvaluar;
    }
    
    
}
