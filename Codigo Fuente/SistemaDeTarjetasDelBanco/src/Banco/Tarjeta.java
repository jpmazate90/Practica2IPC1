
package Banco;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jpmazate
 */
public class Tarjeta implements Serializable{
    //variables de la clase tarjeta
    private String numeroTarjeta;
    private String tipoTarjeta;
    private double limite;
    private String nombre;
    private String direccion;
    private boolean estadoTarjeta;
    private int salario;
    private double saldo;
    private Date fechaAutorizada;
    private Date fechaCancelada;
    
    // constructor de la clase, pide muchos parametros, los cuales neccesita para crear la tarjeta

    public Tarjeta(String numeroTarjeta, String tipoTarjeta, int limite, String nombre, String direccion, int salario) {
        this.numeroTarjeta = numeroTarjeta;
        this.tipoTarjeta = tipoTarjeta;
        this.limite = limite;
        this.nombre = nombre;
        this.direccion = direccion;
        this.estadoTarjeta = true;
        this.salario = salario;
        this.saldo= 0;
        fechaAutorizada = new Date();
        fechaCancelada=null;
    }
    
    //getters and setters
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isEstadoTarjeta() {
        return estadoTarjeta;
    }

    public void setEstadoTarjeta(boolean estadoTarjeta) {
        this.estadoTarjeta = estadoTarjeta;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getFechaAutorizada() {
        return fechaAutorizada;
    }

    public void setFechaAutorizada(Date fechaAutorizada) {
        this.fechaAutorizada = fechaAutorizada;
    }

    public Date getFechaCancelada() {
        return fechaCancelada;
    }

    public void setFechaCancelada(Date fechaCancelada) {
        this.fechaCancelada = fechaCancelada;
    }
    
    // metodo estatico que devuelve una cadena con la fecha 
    public static String pasarFechaComoString(Date fecha){
        String fechaEnString="";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        fechaEnString=formato.format(fecha);
        
        return fechaEnString;
    }
    
    
}
