
package Banco;

import SolicitudTarjeta.Solicitud;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jpmazate
 */
public class AutorizacionTarjeta implements Serializable {
    
    // Atributos de la clase autorizacion
    private int numeroDeSolicitud;
    private int limiteCredito;
    private String formatoTarjeta;
    private String tarjetaNacional;
    private String tarjetaRegional;
    private String tarjetaInternacional;
    private int limiteCreditoNacional;
    private int limiteCreditoRegional;
    private int limiteCreditoInternacional;
    private Date fechaAutorizada;
    private Solicitud autorizacionSolicitud;
    private static final int NACIONAL=1;
    private static final int REGIONAL=2;
    private static final int INTERNACIONAL=3;
    private static final String NOMBRE_NACIONAL="nacional";
    private static final String NOMBRE_REGIONAL="regional";
    private static final String NOMBRE_INTERNACIONAL="internacional";
    private static final int LIMITE_CREDITO_NACIONAL=2000;
    private static final int LIMITE_CREDITO_REGIONAL=5000;
    private static final int LIMITE_CREDITO_INTERNACIONAL=12000;
    private static final double PORCENTAJE_MINIMO=0.6;
    private static final String NUMERO_INICIAL_NACIONAL="4256-3102-6585-";
    private static final String NUMERO_INICIAL_REGIONAL="4256-3102-6590-";
    private static final String NUMERO_INICIAL_INTERNACIONAL="4256-3102-6595-";
    private static int cuentaTarjetaNacional=1;
    private static int cuentaTarjetaRegional=1;
    private static int cuentaTarjetaInternacional=1;
    private static ControlNumerosTarjetas controlNumeros = new ControlNumerosTarjetas();
            
    // constructor de la clase autorizaccion tarjeta
    public AutorizacionTarjeta(int numeroDeSolicitud) {
        this.numeroDeSolicitud = numeroDeSolicitud;
        this.fechaAutorizada= new Date();
    }
    
    
    //getters and setters
    public int getNumeroDeSolicitud() {
        return numeroDeSolicitud;
    }

    public void setNumeroDeSolicitud(int numeroDeSolicitud) {
        this.numeroDeSolicitud = numeroDeSolicitud;
    }

    public int getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(int limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getFormatoTarjeta() {
        return formatoTarjeta;
    }

    public void setFormatoTarjeta(String formatoTarjeta) {
        this.formatoTarjeta = formatoTarjeta;
    }

    public String getTarjetaNacional() {
        return tarjetaNacional;
    }

    public void setTarjetaNacional(String tarjetaNacional) {
        this.tarjetaNacional = tarjetaNacional;
    }

    public String getTarjetaRegional() {
        return tarjetaRegional;
    }

    public void setTarjetaRegional(String tarjetaRegional) {
        this.tarjetaRegional = tarjetaRegional;
    }

    public String getTarjetaInternacional() {
        return tarjetaInternacional;
    }

    public void setTarjetaInternacional(String tarjetaInternacional) {
        this.tarjetaInternacional = tarjetaInternacional;
    }

    public int getLimiteCreditoNacional() {
        return limiteCreditoNacional;
    }

    public void setLimiteCreditoNacional(int limiteCreditoNacional) {
        this.limiteCreditoNacional = limiteCreditoNacional;
    }

    public int getLimiteCreditoRegional() {
        return limiteCreditoRegional;
    }

    public void setLimiteCreditoRegional(int limiteCreditoRegional) {
        this.limiteCreditoRegional = limiteCreditoRegional;
    }

    public int getLimiteCreditoInternacional() {
        return limiteCreditoInternacional;
    }

    public void setLimiteCreditoInternacional(int limiteCreditoInternacional) {
        this.limiteCreditoInternacional = limiteCreditoInternacional;
    }

 
    public Solicitud getAutorizacionSolicitud() {
        return autorizacionSolicitud;
    }

    public void setAutorizacionSolicitud(Solicitud autorizacionSolicitud) {
        this.autorizacionSolicitud = autorizacionSolicitud;
    }

    public Date getFechaAutorizada() {
        return fechaAutorizada;
    }

    public void setFechaAutorizada(Date fechaAutorizada) {
        this.fechaAutorizada = fechaAutorizada;
    }

    public static int getCuentaTarjetaNacional() {
        return cuentaTarjetaNacional;
    }

    public static void setCuentaTarjetaNacional(int cuentaTarjetaNacional) {
        AutorizacionTarjeta.cuentaTarjetaNacional = cuentaTarjetaNacional;
    }

    public static int getCuentaTarjetaRegional() {
        return cuentaTarjetaRegional;
    }

    public static void setCuentaTarjetaRegional(int cuentaTarjetaRegional) {
        AutorizacionTarjeta.cuentaTarjetaRegional = cuentaTarjetaRegional;
    }

    public static int getCuentaTarjetaInternacional() {
        return cuentaTarjetaInternacional;
    }

    public static void setCuentaTarjetaInternacional(int cuentaTarjetaInternacional) {
        AutorizacionTarjeta.cuentaTarjetaInternacional = cuentaTarjetaInternacional;
    }
    
    
    // Es un metodo estatico en el cual se genera el numero de la tarjeta
    public static String generarNumeroDeTarjeta(int tipo) throws IOException{
        // se carga el contador de tarjetas para ver por que conteo se quedo
        ControlNumerosTarjetas NoTarjetas = cargarContadorTarjetas();
        String numeroTarjeta=null;
        //dependiendo de que tipo de tarjeta sea se le da el numero correspondiente
        switch(tipo){
            case 1: numeroTarjeta = NUMERO_INICIAL_NACIONAL+ generar4Numeros(NoTarjetas.getNacional());
                    NoTarjetas.setNacional(NoTarjetas.getNacional()+1);
                    break;
            case 2: numeroTarjeta= NUMERO_INICIAL_REGIONAL+ generar4Numeros(NoTarjetas.getRegional());
                    NoTarjetas.setRegional(NoTarjetas.getRegional()+1);
                    break;
            case 3: numeroTarjeta= NUMERO_INICIAL_INTERNACIONAL+ generar4Numeros(NoTarjetas.getInternacional());
                    NoTarjetas.setInternacional(NoTarjetas.getInternacional()+1);
                    break;
        }
        // guarda el contador de tarjetas
        guardarContadorTarjetas(NoTarjetas);
        
        
        
        return numeroTarjeta;
    }
    // genera los 4 numeros como tal, asigna los 0 correspondientes
    public static String generar4Numeros(int numeroTarjeta){
        
        String numero= Integer.toString(numeroTarjeta);
        String numeroARegresar="0000";
        switch(numero.length()){
            
            case 1: numeroARegresar="000"+numeroTarjeta; break;
            
            case 2: numeroARegresar="00"+numeroTarjeta; break;
            
            case 3: numeroARegresar="0"+numeroTarjeta; break;
            
            case 4: numeroARegresar= Integer.toString(numeroTarjeta); break;
            
        }
        
        return numeroARegresar;
        
    }
    // asigna el nombre del tipo de tarjeta segun el numero dado
    public static String tipoTarjeta(int tipo){
        String tipoTarjeta=null;
        switch(tipo){
            
            case 1: tipoTarjeta=NOMBRE_NACIONAL; break;
            
            case 2: tipoTarjeta=NOMBRE_REGIONAL; break;
            
            case 3: tipoTarjeta=NOMBRE_INTERNACIONAL; break;
            
            
        }
        return tipoTarjeta;
    }
    
    // asigna el limite de la tarjeta segun su tipo
    public static int limiteTarjeta(int tipo){
        int limite=0;
        switch(tipo){
            
            case 1: limite=LIMITE_CREDITO_NACIONAL; break;
            
            case 2: limite= LIMITE_CREDITO_REGIONAL; break;
            
            case 3: limite=LIMITE_CREDITO_INTERNACIONAL; break;
            
            
        }
        return limite;
       
    }
        
        // devuelve el saldo
        public static double saldoTarjeta(int tipo){
            double saldo=0;
            switch (tipo) {
            case 1: saldo=2000; break;
            case 2: saldo=5000; break;
            case 3: saldo=12000; break;
            }
        return saldo;
        }
        
        // verifica si cumple el salario minimo para autorizar la tarjeta correspondiente
        public static boolean verificarSiCumpleSalario(int salario, int tipo){
        boolean cumpleSalario=false;
        double salario1=(double)salario;
        double porcentaje= PORCENTAJE_MINIMO;
        switch (tipo) {
            // dependiendo el tipo verifica si cumple
            case 1:
                if((salario1*PORCENTAJE_MINIMO)>=LIMITE_CREDITO_NACIONAL){
                    
                    cumpleSalario=true;
                } break;
            case 2:
                if((salario1*PORCENTAJE_MINIMO)>=LIMITE_CREDITO_REGIONAL){
                    cumpleSalario=true;
                } break;
            case 3:
                if((salario1*PORCENTAJE_MINIMO)>=LIMITE_CREDITO_INTERNACIONAL){
                    cumpleSalario=true;
                } break;
        }
        return cumpleSalario;
    
    


        
    }
        
        // guarda el contador de tarjetas en un archivo para uso del software
        public static void guardarContadorTarjetas( ControlNumerosTarjetas tarjetas){
            File contadorTarjetas = new File("contadortarjetas.jp");
            
            try {
                FileOutputStream output = new FileOutputStream(contadorTarjetas);
                ObjectOutputStream objectOutput = new ObjectOutputStream(output);
            
                objectOutput.writeObject(tarjetas);
                
            
            
            } catch (IOException ex) {
                Logger.getLogger(AutorizacionTarjeta.class.getName()).log(Level.SEVERE, null, ex);
            }      
        }
        
        // carga el archivo contador de tarjetas para ver por cual nuero se quedo
        public static ControlNumerosTarjetas cargarContadorTarjetas() throws IOException{
            File file = new File("contadortarjetas.jp");
            if(!file.exists()){
                file.createNewFile();
                guardarContadorTarjetas(controlNumeros);
            }
            ControlNumerosTarjetas numeroTarjetas = null;
            try {
                FileInputStream input = new FileInputStream(file);
                ObjectInputStream objectInput = new ObjectInputStream(input);
                numeroTarjetas = (ControlNumerosTarjetas) objectInput.readObject();
   
            } catch (Exception e) {
                e.printStackTrace();
                
                
            }
        return numeroTarjetas;
        }
}
    
    
