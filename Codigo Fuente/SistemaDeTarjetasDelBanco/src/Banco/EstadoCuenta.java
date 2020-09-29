
package Banco;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jpmazate
 */
// estado cuenta
public class EstadoCuenta implements Serializable{
    
    //variables de atributos de la clase
    private ArrayList<Tarjeta> tarjetasClientes;
    private ArrayList<Movimiento> movimientos;
    private Movimiento movimientoCliente;
    private Interes intereses;
    private double montoTotal;
    private double saldoTotal;
    //constructor de la clase
    public EstadoCuenta(ArrayList<Movimiento> movimientos ) {
        this.movimientos = movimientos;
        
    }
    //getters and setters
    public ArrayList<Tarjeta> getTarjetasClientes() {
        return tarjetasClientes;
    }

    public void setTarjetasClientes(ArrayList<Tarjeta> tarjetasClientes) {
        this.tarjetasClientes = tarjetasClientes;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public Movimiento getMovimientoCliente() {
        return movimientoCliente;
    }

    public void setMovimientoCliente(Movimiento movimientoCliente) {
        this.movimientoCliente = movimientoCliente;
    }

    public Interes getIntereses() {
        return intereses;
    }

    public void setIntereses(Interes intereses) {
        this.intereses = intereses;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }
    
    
    
    
}
