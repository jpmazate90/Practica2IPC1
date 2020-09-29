
package SolicitudTarjeta;

import Banco.AutorizacionTarjeta;
import Banco.CancelacionTarjeta;
import Banco.ConsultarTarjeta;
import Banco.EstadoCuenta;
import Banco.Movimiento;
import Banco.Tarjeta;
import ExcepcionesPersonalizadas.ExcepcionFormato;
import ExcepcionesPersonalizadas.ExcepcionPersonalizada;
import InterfazGraficaBanco.InterfazGrafica;
import LogicaJuego.LogicaJuego;
import ReportesBanco.ListadoSolicitudes;
import ReportesBanco.ListadoTarjetas;
import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jpmazate
 */
public class CargaDatosThread extends Thread {
    //variables privadas de la calse 
    private File archivo;
    private FileReader archivoDeEntrada;
    private String nombreArchivo;
    private int tiempoMilisegundos;
    private static final String[] nombreSolicitudes1 = {"SOLICITUD", "MOVIMIENTO","AUTORIZACION_TARJETA", "CANCELACION_TARJETA", "CONSULTAR_TARJETA", "ESTADO_CUENTA","LISTADO_TARJETAS", "LISTADO_SOLICITUDES" };
    private static final String SOLICITUD = "SOLICITUD";
    private static final String MOVIMIENTO = "MOVIMIENTO";
    private static final String AUTORIZACION_TARJETA = "AUTORIZACION_TARJETA";
    private static final String CANCELACION_TARJETA = "CANCELACION_TARJETA";
    private static final String CONSULTAR_TARJETA = "CONSULTAR_TARJETA";
    private static final String ESTADO_CUENTA = "ESTADO_CUENTA";
    private static final String LISTADO_SOLICITUDES = "LISTADO_SOLICITUDES";
    private static String lineaCodigo=null;
    
    //constructor(vacio)
    public CargaDatosThread() {
    }
    
    
    

    // constructor que pide un archivo, un nombre y el tiempo 
    public CargaDatosThread(File archivo, String nombre, int tiempoMilisegundos){
        super(nombre);
        this.archivo = archivo;
        this.nombreArchivo = nombre;
        this.tiempoMilisegundos = tiempoMilisegundos;
        
        
    }
    // metodo que genera el hilo
    public void run(){
        try {// lee el archivo
            leerArchivo();
        } catch (FileNotFoundException ex) {
            InterfazGrafica.introducirDatosALaLista("No se puede conectar al archivo");
            System.out.println("No se puede leer el archivo");
        } catch (IOException ex) {
            Logger.getLogger(CargaDatosThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
    
    public void leerArchivo() throws FileNotFoundException, IOException{
        try{// abre la via para leer el archivo
            FileReader archivo1 = new FileReader(archivo);
            BufferedReader archivo= new BufferedReader(archivo1);
            // lee la primera linea y la guarda
            String auxiliar = archivo.readLine();
            this.lineaCodigo= auxiliar;
            //crear un try catch por si no tiene ningun "("
            int posicion;
            // variables para desarmar la linea ingresada
            String auxiliar3 ;
            String auxiliar4;
            String[] miembrosDeLinea;
        
            while(auxiliar !=null){
                try{
                    // guarda de donde a donde esta el nombre de la orden
                    
                posicion = auxiliar.indexOf("(");
                auxiliar3 = auxiliar.substring(0, posicion);
                
                auxiliar4 = auxiliar.substring(posicion);
                // quita las comillas 
                miembrosDeLinea = (String[])LogicaJuego.quitarComillasALaLinea(auxiliar4);
                // muestra en consola el resultado
                System.out.println(auxiliar);
                System.out.println(auxiliar3);
                System.out.println(auxiliar4);
                for (String string : miembrosDeLinea) {
                    System.out.println(string);
                }
                
                // llama segun la orden a cierta parte del metodo
                verificarObjetoALlamar(auxiliar3,miembrosDeLinea);
                    // lee la siguiente orden
                auxiliar= archivo.readLine();
                this.lineaCodigo=auxiliar;
            
                try {// lo que descansa el thread antes de continuar
                    Thread.sleep(tiempoMilisegundos);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CargaDatosThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                }catch(ArrayIndexOutOfBoundsException e){
                    InterfazGrafica.introducirDatosALaLista("No cumple con el formato valido la linea: "+lineaCodigo);
                    auxiliar= archivo.readLine();
                }
            
            
            }// muestra que se completo la carga del archivo 
            JOptionPane.showMessageDialog(null, "Se ha completado la carga del archivo");
            InterfazGrafica.introducirDatosALaLista("Se ha completado la carga del archivo");
        }catch(Exception e){
            // si surge algun error dice que no tiene el formato valido 
            JOptionPane.showMessageDialog(null, "No tiene formato valido el archivo");
            InterfazGrafica.introducirDatosALaLista("No cumple con el formato valido la linea: "+lineaCodigo);
            System.out.println("error de formato no valido");
            e.printStackTrace();
            
            
        }
        
        
    }    
    
    public void verificarObjetoALlamar(String cadenaAVerificar, String[] datosLinea){
        // variables locales
        Solicitud solicitudAGuardar;
        AutorizacionTarjeta autorizacionTarjetaAGuardar; 
        CancelacionTarjeta cancelacionTarjeta;
        Movimiento movimiento;
        // si la orden es una solicitud 
        if(cadenaAVerificar.equals(nombreSolicitudes1[0])){
            // si es una solicitud asigna valores a cada variable
            int solicitud = Integer.parseInt(datosLinea[0]); 
            String fecha = datosLinea[1];
            int tipo = Integer.parseInt(datosLinea[2]);
            String nombre = datosLinea[3];
            int salario = Integer.parseInt(datosLinea[4]);
            String direccion = datosLinea[5];
            // si se puede crear la solicitud 
            boolean crearSolicitud = LogicaJuego.sePuedeCrearSolicitud(solicitud, fecha, tipo, nombre, direccion);
            if(crearSolicitud==true){

                // guarda la solicitud si todo cumple
                solicitudAGuardar = new Solicitud(solicitud, fecha, tipo, nombre, salario, direccion);
                System.out.println("Se creo la solicitud");
                LogicaJuego.guardarSolicitud(solicitudAGuardar);
                InterfazGrafica.introducirDatosALaLista("Se creo la solicitud: "+solicitud+ " De: "+nombre);
            }else{
                // sino no guarda nada
                System.out.println("No se creo la solicitud");
                // hacer algo si no se puede crear la solicitud
            }
            
        }
        // si es un movimiento 
        if(cadenaAVerificar.equals(nombreSolicitudes1[1])){
            // asigna valores
            String tarjeta = datosLinea[0];
            String fecha = datosLinea[1];
            String tipoMovimiento = datosLinea[2];
            String descripcion = datosLinea[3];
            String establecimiento = datosLinea[4];
            double monto = Double.parseDouble(datosLinea[5]);
            // si cumple entra
            boolean existeTarjeta = LogicaJuego.hacerUnMovimiento(tarjeta, fecha, tipoMovimiento, descripcion, establecimiento, monto);
            if(existeTarjeta==true){
                // guarda el movimiento
                
                try {
                    movimiento = new Movimiento(tarjeta, fecha, tipoMovimiento, descripcion, establecimiento, monto);
                    LogicaJuego.guardarMovimiento(movimiento);
                    InterfazGrafica.introducirDatosALaLista("Se creo el movimiento de la tarjeta: "+tarjeta+" satisfactoriamente");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
            }
            else{
                //no guarda nada
                System.out.println("NO se pudo hacer el movimiento");
            }
        }
        // si es un autorizacion
        if(cadenaAVerificar.equals(nombreSolicitudes1[2])){
            try {
                int numeroSolicitud= Integer.parseInt(datosLinea[0]);
                // verifica si existe la solicitud y si se puede autorizar
                boolean existeSolicitud= LogicaJuego.sePuedeAutorizarTarjeta(numeroSolicitud);
                if(existeSolicitud==true){
                    // guarda la autorizacion
                    autorizacionTarjetaAGuardar= new AutorizacionTarjeta(numeroSolicitud);
                    LogicaJuego.guardarAutorizacionTarjeta(autorizacionTarjetaAGuardar);
                    InterfazGrafica.introducirDatosALaLista("Se Autorizo correctamente la tarjeta con solicitud numero: "+numeroSolicitud);

                    System.out.println("Se autorizo la tarjeta");
                }else{
                    
                    // no la guarda
                    System.out.println("No se autorizo la tarjeta");
                    //hacer algo si no existe esa solicitud
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // si es una cancelacion
        if(cadenaAVerificar.equals(nombreSolicitudes1[3])){
            try {
                String tarjetaACancelar = datosLinea[0];
                // mira si se puede cancelar
                boolean sePuedeCancelar= LogicaJuego.sePuedeCancelarTarjeta(tarjetaACancelar);
                if(sePuedeCancelar==true){
                // si es true la guarda
                cancelacionTarjeta = new CancelacionTarjeta(tarjetaACancelar);
                System.out.println("Se cancelo");
                LogicaJuego.guardarCancelacionTarjeta(cancelacionTarjeta);
                    InterfazGrafica.introducirDatosALaLista("Se cancelo la tarjeta: "+ tarjetaACancelar+ " correctamente");
                }else{
                    // sino no
                    System.out.println("no se puede cancelar, tiene saldo pendiente");//hacer algo si tiene saldo pendiente o no existe la tarjeta
                }
            } catch (Exception e) {
            }
        }
        // si es un html de una tarjeta
        if(cadenaAVerificar.equals(nombreSolicitudes1[4])){
            try {
                String numeroTarjeta = datosLinea[0];      
                // va a crear la tarjeta
                LogicaJuego.crearTarjetaHTML(numeroTarjeta);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // si es un estado de cuenta llama al metodo correspondiente
        if(cadenaAVerificar.equals(nombreSolicitudes1[5])){
              LogicaJuego.crearEstadoCuentaHTML();
        }
        // si es un listado de tarjeta llama al metodo correspondiente
        if(cadenaAVerificar.equals(nombreSolicitudes1[6])){
              LogicaJuego.crearListadoTarjetasHTML();
        }
        // si es un listado de solicitudes llama al metodo de solicitudes del html
        if(cadenaAVerificar.equals(nombreSolicitudes1[7])){
            
              LogicaJuego.crearListadoSolicitudesHTML();
        }
        
        
        
        
        
        
    }
}

// aqui hay que verificar si cumple las normas de sintaxis y los limites cada linea del archivo a procesar
// se debe de guardar la informacion del proceso de carga en un archivo con un formato legible
// crear los archivos de salida     
