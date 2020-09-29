
package SolicitudTarjeta;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jpmazate
 */
public class Solicitud implements Serializable{
    // variables privadas de la clase
    private int numeroDeSolicitud;
    private String fecha;
    private int tipo;
    private String nombre;
    private int salario;
    private String direccion;
    private Date fechaAutorizada=null;
    private Date fechaRechazada=null;
    private boolean estadoSolicitud;
// constructor de la solicitud
    public Solicitud(int numeroDeSolicitud, String fecha, int tipo, String nombre, int salario, String direccion) {
        this.numeroDeSolicitud = numeroDeSolicitud;
        this.fecha = fecha;
        this.tipo = tipo;
        this.nombre = nombre;
        this.salario = salario;
        this.direccion = direccion;
        estadoSolicitud=false;
    }
    // getters and setters
    public int getNumeroDeSolicitud() {
        return numeroDeSolicitud;
    }

    public void setNumeroDeSolicitud(int numeroDeSolicitud) {
        this.numeroDeSolicitud = numeroDeSolicitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaAutorizada() {
        return fechaAutorizada;
    }

    public void setFechaAutorizada(Date fechaAutorizada) {
        this.fechaAutorizada = fechaAutorizada;
    }

    public Date getFechaRechazada() {
        return fechaRechazada;
    }

    public void setFechaRechazada(Date fechaRechazada) {
        this.fechaRechazada = fechaRechazada;
    }

    public boolean isEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(boolean estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
    
    
    
    
    
}
