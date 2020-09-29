
package Banco;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author jpmazate
 */
public class Movimiento implements Serializable{
    // atributos de la clase movimiento
    private String numeroTarjeta;
    private String fecha;
    private String tipoDeMovimiento;
    private String descripcion;
    private String establecimiento;
    private double monto;
    // constructor de la clase
    public Movimiento(String numeroTarjeta, String fecha, String tipoDeMovimiento, String descripcion, String establecimiento, double monto) {
        this.numeroTarjeta = numeroTarjeta;
        this.fecha = fecha;
        this.tipoDeMovimiento = tipoDeMovimiento;
        this.descripcion = descripcion;
        this.establecimiento = establecimiento;
        this.monto = monto;
    }
    // getters and setters
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoDeMovimiento() {
        return tipoDeMovimiento;
    }

    public void setTipoDeMovimiento(String tipoDeMovimiento) {
        this.tipoDeMovimiento = tipoDeMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
    
}
