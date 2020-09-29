
package LogicaJuego;

import Banco.AutorizacionTarjeta;
import Banco.CancelacionTarjeta;
import Banco.ConsultarTarjeta;
import Banco.EstadoCuenta;
import Banco.Movimiento;
import Banco.Tarjeta;
import CodigoHTML.GenerarCodigoHTML;
import ExcepcionesPersonalizadas.ExcepcionFormato;
import ExcepcionesPersonalizadas.ExcepcionLogica;
import ExcepcionesPersonalizadas.ExcepcionPersonalizada;
import ReportesBanco.ListadoTarjetas;
import SolicitudTarjeta.Solicitud;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import InterfazGraficaBanco.InterfazGrafica;
import java.io.BufferedWriter;
import java.io.FileWriter;
/**
 *
 * @author jpmazate
 */
public class LogicaJuego {
    // variables privadas de la clase
    private static String fechaActual;
    private static Calendar historial = Calendar.getInstance();
    private static final int LIMITE_CARACTERES_1=100;
    private static final int LIMITE_CARACTERES_2=50;
    private static final int LIMITE_CREDITO_NACIONAL=2000;
    private static final int LIMITE_CREDITO_REGIONAL=5000;
    private static final int LIMITE_CREDITO_INTERNACIONAL=12000;
    private static final String CARGO ="CARGO";
    private static final String ABONO ="ABONO";
    
    private static ExcepcionPersonalizada validacion = new ExcepcionPersonalizada();
    
    // cargar datos de solicitudes para comprobar 
    // cargar datos de las autorizaciones
    public static ArrayList<Tarjeta> verListadoTarjetas(ArrayList<AutorizacionTarjeta> tarjetasAutorizadas ){
        
        // carga los datos de todas las tarjetas creadas
        ArrayList<Tarjeta> tarjetas = cargarDatosTarjetas();
        //llama a la creacion del html
        GenerarCodigoHTML.generarListadoTarjetas(tarjetas);
        
        
        
        return tarjetas;
        
    }
    // cargarDatos de todas las solicitudes
    public static ArrayList<Solicitud> verListadoSolicitudes(ArrayList<Solicitud> solicitudes){
       //carga los datos de las solicitudes
        ArrayList<Solicitud> solicitudes1= cargarDatosSolicitudes();
               //llama a la creacion del html
        GenerarCodigoHTML.generarListadoSolicitudes(solicitudes1);
        
        return solicitudes1;
    }
    // crea el reporte de la tarjeta de html
    public static void crearTarjetaHTML(String numeroTarjeta){
        //  carga la tarjeta seleccionada
        Tarjeta tarjeta = cargarDatosTarjetas(numeroTarjeta);
        if(tarjeta==null){
            // si no existe la tarjeta lo muestra en la interfaz
            System.out.println("No se puede crear html, no existe la tarjeta");
            InterfazGrafica.introducirDatosALaLista("Error Logico: la tarjeta: "+numeroTarjeta+" no existe");
        }else{
            // si si esta la tarjeta llama a la creacion del html
            GenerarCodigoHTML.generarConsultaTarjeta(tarjeta);
            InterfazGrafica.introducirDatosALaLista("Se genero el reporte de la tarjeta: "+numeroTarjeta+", con exito");

        }
    }
    
    public static void crearEstadoCuentaHTML(){
        //carga todas las tarjetas del sistema, ya que se tienen que evaluar todas
        ArrayList <Tarjeta> tarjetasSistema = new ArrayList<Tarjeta>();
        ArrayList <Tarjeta> tarjetasActivas = new ArrayList<Tarjeta>();
        boolean bandera=false;
        ArrayList<Movimiento> movimientosTarjeta= null;
        Movimiento[] movimientosAEnviar;
        tarjetasSistema = cargarDatosTarjetas();
        if(tarjetasSistema.isEmpty()){
            // si no hay tarjetas lo muestra en la pantalla
            System.out.println("NO hay nada que mostrar en el reporte");
            InterfazGrafica.introducirDatosALaLista("Error Logico: no se puede hacer el estado de cuenta ya que no hay tarjetas registradas.");
        }else{
            // evalua cuales estan activas
            for(int i=0; i<tarjetasSistema.size();i++){
                if(tarjetasSistema.get(i).isEstadoTarjeta()==true){
                   tarjetasActivas.add(tarjetasSistema.get(i));
                }
            }// si no hay tarjetas activas no hace nada 
            if(tarjetasActivas.isEmpty()){
                System.out.println("No hay tarjetas activas");
                InterfazGrafica.introducirDatosALaLista("Error Logico: no se puede hacer el estado de cuenta ya que no hay tarjetas activas");
            }else{// genera el titulo del reporte en html
                GenerarCodigoHTML.generarTituloEstadoCuenta();
                for (int j = 0; j < tarjetasActivas.size(); j++) {
                    movimientosTarjeta = cargarMovimientos(tarjetasActivas.get(j).getNumeroTarjeta());                    
                          // guarda en un arreglo los movimietnos
                          movimientosAEnviar = new Movimiento[movimientosTarjeta.size()];
                          // si esta vacio la tabla del html estara vacia
                          if(movimientosTarjeta.isEmpty()){
                              
                          }else{
                              // si no lo contrario al if anterior
                            for(int k=0;k<movimientosTarjeta.size();k++){
                              movimientosAEnviar[k] = movimientosTarjeta.get(k);
                            }
                          }// genera el html
                          GenerarCodigoHTML.generarEstadoDeCuenta(tarjetasActivas.get(j), movimientosAEnviar);
                          bandera=true;
                    
                }
                
                if(bandera==true){
                    InterfazGrafica.introducirDatosALaLista("Se creo satisfactoriamente el estado de cuenta");
                }
            }

        }
                
        
    }
    
    public static void crearListadoTarjetasHTML(){
        // crea el listado de todas las tarjetas 
        ArrayList<Tarjeta> tarjetas = new ArrayList<Tarjeta>();
        //carga las tarjetas
        tarjetas = cargarDatosTarjetas();
        //genera el codigo
        GenerarCodigoHTML.generarListadoTarjetas(tarjetas);
        InterfazGrafica.introducirDatosALaLista("Se creo satisfactoriamente el listado de tarjetas");
    
                
    }
    
    public static void crearListadoSolicitudesHTML(){
        // carga las solicitudes
        ArrayList<Solicitud> solicitudes = new ArrayList<Solicitud>();
        solicitudes= cargarDatosSolicitudes();
        //genera el html
        GenerarCodigoHTML.generarListadoSolicitudes(solicitudes);
        InterfazGrafica.introducirDatosALaLista("Se creo satisfactoriamente el listado de solicitudes");
        
    }
    
    // cargar datos de solicitudes
    public static boolean sePuedeCrearSolicitud(int numeroSolicitud, String fecha, int tipo, String nombre, String direccion){
        
        boolean sePuedeCrear=true;
        try {
            
            Solicitud solicitud = null;
            //carga la solicitud en curso
            solicitud = cargarDatosSolicitudes(numeroSolicitud);
            if(solicitud!=null){
                sePuedeCrear=false;
                // si es nula muestra el error
                InterfazGrafica.introducirDatosALaLista("Error Logico: ya existe la solicitud: "+numeroSolicitud);
            }else{
                
            // verifica si cumple el formato si no cumple lo muestra en pantalla
            if(!verificarSiCumpleConLimiteCaracteres(direccion,LIMITE_CARACTERES_1 )){
                sePuedeCrear=false;
                InterfazGrafica.introducirDatosALaLista("Error Formato: No cumple con el limite de caracteres: "+direccion+" La solicitud: "+numeroSolicitud);
                return sePuedeCrear;
            }
            if(!verificarSiCumpleConLimiteCaracteres(nombre, LIMITE_CARACTERES_1)){
                sePuedeCrear=false;
                InterfazGrafica.introducirDatosALaLista("Error Formato: No cumple con el limite de caracteres: "+nombre+" La solicitud: "+numeroSolicitud);
                return sePuedeCrear;
            }
            if(!verificarTipoTarjeta(tipo)){
                sePuedeCrear=false;
                InterfazGrafica.introducirDatosALaLista("Error Formato: No cumple el tipo de tarjeta, tipo ingresado: "+ tipo + ", La solicitud: "+numeroSolicitud);
                return sePuedeCrear;
            }
            // valida la fecha sino lanza una excepcion
            validacion.verificarSiCumpleFormatoFecha(fecha);
            }
            
            
            return sePuedeCrear;
            // manejador de excepciones
        } catch (ExcepcionFormato ex) {
            InterfazGrafica.introducirDatosALaLista("Error Formato: no esta bien escrita la fecha de la solicitud: "+numeroSolicitud);
            System.out.println("hubo error en fecha");
            sePuedeCrear=false;
        } catch (ParseException ex) {
            ex.getMessage();
            InterfazGrafica.introducirDatosALaLista("Error Formato: no esta bien escrita la fecha de la solicitud: "+numeroSolicitud);
            sePuedeCrear=false;
        }
        return sePuedeCrear;
        
       
        
    }
    // verifica si se puede autorizar una tarjeta
    public static boolean sePuedeAutorizarTarjeta(int numeroSolicitud) throws IOException{
        // variables necesarias
        boolean sePuedeAutorizar=false;
        boolean bandera=false;
        Tarjeta tarjetaACrear;
        Date fechaActual= new Date();
        try{
        AutorizacionTarjeta autorizacionTarjeta= null;
        Solicitud solicitud = null;
        //carga las solicitud y busca si existe la autorizacion, 
            solicitud= cargarDatosSolicitudes(numeroSolicitud);
            autorizacionTarjeta = cargarDatosAutorizaciones(numeroSolicitud);
            
                if(solicitud!=null && solicitud.getNumeroDeSolicitud()==numeroSolicitud ){
                    if(autorizacionTarjeta == null){
                        bandera=true;
                    }else{
                        // si la autorizacion no es nula dice que ya existe la autorizacion
                            if(solicitud.getNumeroDeSolicitud()== autorizacionTarjeta.getNumeroDeSolicitud()){
                                System.out.println("no se puede autorizar, ya se autorizo previamente");
                                InterfazGrafica.introducirDatosALaLista("Error Logico: ya se autorizo la tarjeta con solicitud "+numeroSolicitud+ " previamente");
                                throw new ExcepcionLogica("ya se autorizo");
                            }else{
                                bandera=true;
                            }
                        
                        
                    }// verifica si cumple el salario
                    if(AutorizacionTarjeta.verificarSiCumpleSalario(solicitud.getSalario(), solicitud.getTipo()) && bandera==true){
                                sePuedeAutorizar=true;
                                // si si es posible autorizarla crea la tarjeta 
                                tarjetaACrear = new Tarjeta(AutorizacionTarjeta.generarNumeroDeTarjeta(solicitud.getTipo()), AutorizacionTarjeta.tipoTarjeta(solicitud.getTipo()),
                                                            AutorizacionTarjeta.limiteTarjeta(solicitud.getTipo()) , solicitud.getNombre(), solicitud.getDireccion(), 
                                                            solicitud.getSalario());
                                // guarda la tarjeta
                                guardarTarjeta(tarjetaACrear, tarjetaACrear.getNombre(), tarjetaACrear.getNumeroTarjeta());
                                //actualiza la fecha de autorizacion de la solicitud
                                solicitud.setFechaAutorizada(fechaActual);
                                solicitud.setEstadoSolicitud(true);
                                //guarda la solicitud
                                guardarSolicitud(solicitud);
                                
                    }else{
                        if(bandera==true){
                            // si la bandera es verdadera y entro aqui significa que no cumplio con el salario minimo
                            InterfazGrafica.introducirDatosALaLista("Error Logico: No cumple con el salario minimo la solicitud: "+numeroSolicitud);
                        }
                        System.out.println("No cumple con el salario");
                    }
                    
                }else{// si la solicitud es nula, significa que nunca se creo la solicitud
                    InterfazGrafica.introducirDatosALaLista("Error Logico: No existe la solicitud "+numeroSolicitud);
                    System.out.println("No existe la solicitud");
                    sePuedeAutorizar=false;
                    solicitud.setFechaRechazada(fechaActual);
                    solicitud.setEstadoSolicitud(false);
                    guardarSolicitud(solicitud);
                    
                    throw new ExcepcionLogica("No existe la solicitud");
                }
                
            
        } catch (ExcepcionLogica ex) {
            sePuedeAutorizar=false;
            ex.getMessage();
            ex.printStackTrace();
            
        }
        return sePuedeAutorizar;
    }
    
    public static boolean sePuedeCancelarTarjeta(String numeroTarjeta){
        //variables a utilizar
        Date fechaACancelar = new Date();
        SimpleDateFormat fecha2 = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaCancelada = new Date(fecha2.format(fechaACancelar));
        boolean sePuedeCancelar=false;
        Tarjeta tarjetas = null;
        //carga la tarjeta
        tarjetas = cargarDatosTarjetas(numeroTarjeta);
        if(tarjetas == null){// si la tarjeta es nula significa que no existe
            InterfazGrafica.introducirDatosALaLista("Error Logico: no existe la tarjeta: "+numeroTarjeta+ " no se puede cancelar");
            System.out.println("no existe la tarjeta");
            return sePuedeCancelar;
            // si no es nula procede a verificar
        }else{
            if(tarjetas.getNumeroTarjeta().equals(numeroTarjeta)){
                // verifica si el saldo es menor a 0 significa que no debe nada 
                if(tarjetas.getSaldo()<=0){
                    System.out.println("Se puede cancelar");
                    sePuedeCancelar=true;
                    tarjetas.setEstadoTarjeta(false);
                    tarjetas.setFechaCancelada(fechaCancelada);
                    //guarda la tarjeta
                    guardarTarjeta(tarjetas, tarjetas.getNombre(), tarjetas.getNumeroTarjeta());
                    return sePuedeCancelar;
                }
                else{// si no cumple con el saldo menor que 0 entra aqui
                    System.out.println("No se puede cancelar, tiene saldo pendiente");
                    InterfazGrafica.introducirDatosALaLista("Error Logico: tiene saldo pendiente la tarjeta: "+numeroTarjeta+" no se puede cancelar");
                    return sePuedeCancelar;
                }
            }else{
                // no es el mismo numero de tarjeta
                System.out.println("no es el mismo numero de tarjeta");
                InterfazGrafica.introducirDatosALaLista("Error Logico: no existe la tarjeta: "+numeroTarjeta+" no se puede cancelar");
            }
        }
            
        
        
        
        return sePuedeCancelar;
        
    }
    // hace un movimiento
    public static boolean hacerUnMovimiento(String numeroTarjeta, String fecha, String tipoMovimiento, String descripcion, String Establecimiento, double monto){
        boolean existeTarjeta = existeTarjeta(numeroTarjeta);
        Tarjeta tarjeta;
        boolean cumpleFormato=true;
        boolean puedeHacerseMovimiento=false;
        double saldoTarjeta;
        // si existe la tarjeta es verdadero entra
        if(existeTarjeta==true){
            try {// valida si cumple con limite de caracteres las diferentes partes de la linea
                if(!validacion.verificarSiCumpleConLimiteCaracteres(descripcion, LIMITE_CARACTERES_1)){
                    cumpleFormato=false;
                    InterfazGrafica.introducirDatosALaLista("Error Formato: No cumple el limite de caracteres la linea de descripcion: "+descripcion);
                    return puedeHacerseMovimiento;
                }
                if(!validacion.verificarSiCumpleConLimiteCaracteres(Establecimiento, LIMITE_CARACTERES_2)){
                    cumpleFormato=false;
                    InterfazGrafica.introducirDatosALaLista("Error Formato: No cumple el limite de caracteres la linea de Establecimiento: "+Establecimiento);
                    return puedeHacerseMovimiento;
                }// valida si cumple la fecha
                validacion.verificarSiCumpleFormatoFecha(fecha);
                // si el formato sigue siendo true entra
                if(cumpleFormato == true){
                    tarjeta = buscarTarjeta(numeroTarjeta);
                    if(tarjeta!=null){
                        if(tarjeta.isEstadoTarjeta()==true){
                        
                            saldoTarjeta = tarjeta.getSaldo();
                            // si es cargo entra
                            if(tipoMovimiento.equals(CARGO)){
                                // si el cargo es mayor al limite no genera el cargo
                                if(saldoTarjeta+monto>tarjeta.getLimite()){
                                    System.out.println("NO se puede usar la tarjeta, sobrepasa el limite");
                                    InterfazGrafica.introducirDatosALaLista("Error Logico: la tarjeta "+numeroTarjeta+" llego a su limite");
                                    return puedeHacerseMovimiento;
                                }else{// si es posible guarda el movimiento
                                    tarjeta.setSaldo(tarjeta.getSaldo()+monto);
                                    guardarTarjeta(tarjeta, tarjeta.getNombre(), tarjeta.getNumeroTarjeta());
                                    puedeHacerseMovimiento=true;
                                }// si es abono guarda directamente esto
                            }else if(tipoMovimiento.equals(ABONO)) {
                                tarjeta.setSaldo(tarjeta.getSaldo()-monto);
                                guardarTarjeta(tarjeta, tarjeta.getNombre(), tarjeta.getNumeroTarjeta());
                                puedeHacerseMovimiento=true;
                            }else{// entra si no esta bien escrito si es un cargo o abono
                                InterfazGrafica.introducirDatosALaLista("Error Formato: No esta bien escrito si es cargo o abono en el movimiento de: "+numeroTarjeta);
                                return puedeHacerseMovimiento;
                            }
                        }else{// si ya esta cancelada no crea el movimiento
                            InterfazGrafica.introducirDatosALaLista("Error Logico: la tarjeta: "+numeroTarjeta+" ha sido cancelada anteriormente");
                            return puedeHacerseMovimiento;
                        }
                    }else{// si no existe la tarjeta muestra que no existe
                        InterfazGrafica.introducirDatosALaLista("Error Logico: La tarjeta "+numeroTarjeta+" no existe");
                        System.out.println("la tarjeta no existe");
                        return puedeHacerseMovimiento;
                    }
                    
                }else{// no cumple el formato
                    InterfazGrafica.introducirDatosALaLista("No cumple con el formato de movimiento");
                    return puedeHacerseMovimiento;
                }
                
            } catch (ExcepcionFormato ex) {
                InterfazGrafica.introducirDatosALaLista("Error Formato: La fecha no esta correcta: "+fecha);
            } catch (ParseException ex) {
                ex.printStackTrace();
            } 
           
            
        }else{// no existe la tarjeta
            InterfazGrafica.introducirDatosALaLista("Error Logico: la tarjeta: "+numeroTarjeta+ " no existe");
            System.out.println("No existe tarjeta");
            cumpleFormato=false;
        }
        return puedeHacerseMovimiento;
        
        
    } 
            
    // cargar datos de tarjetas
    public static boolean existeTarjeta(String tarjeta){
        Tarjeta tarjetaSistema = cargarDatosTarjetas(tarjeta);
        // solo mira si existe la tarjeta;
        if(tarjetaSistema==null){
            return false;
        }else{
            if(tarjetaSistema.getNumeroTarjeta().equals(tarjeta)){
                return true;
            }
            
        }
        
        return false;
        
    }
    //cargar datos de solicitudes
    public static boolean existeSolicitud(int solicitud){
        Solicitud solicitud1 = null;
        // solo mira si existe la solicitud
        if(solicitud1.getNumeroDeSolicitud()== solicitud){
            return false;
        }else{
            return true;
        }
        
    }
    
    

    public static boolean tieneSaldoPendiente(String tarjeta){
        // verifica si tiene saldo pendiente
        ArrayList<Tarjeta> tarjetas = cargarDatosTarjetas();
        for (Tarjeta tarjeta1 : tarjetas) {
            if(tarjeta1.getNumeroTarjeta()== tarjeta){
                if(tarjeta1.getSaldo()<0){
                    return true;
                }
                
            }
            
        }
        return false;
        
    }


    public static Tarjeta buscarTarjeta(String tarjeta){
        // busca una tarjeta especifica
        Tarjeta tarjetasSistema = null;
        tarjetasSistema= cargarDatosTarjetas(tarjeta);
        if(tarjetasSistema==null){
            System.out.println("No existe tarjeta");
        }
            if(tarjetasSistema.getNumeroTarjeta().equals(tarjeta)){
                return tarjetasSistema;
            }
            
        
        
        
        return null;
        
    }

public  static Solicitud cargarDatosSolicitudes(int numeroSolicitud){
        Solicitud solicitudPedida=null;
        File folder = new File(".");
        //busca en la direccion de ejecucioon
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empieza con solicitud y el numero la carga
                if(nombreArchivo.startsWith("solicitud"+Integer.toString(numeroSolicitud))){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        solicitudPedida= (Solicitud) inputStream.readObject();
                        break;
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                        System.out.println(" No es una solicitud");
                    }
                    
                }
                
            }
            
            
        }
        
        return solicitudPedida;
        
    }
    // carga todas las solicitudes del sistema
public  static ArrayList<Solicitud> cargarDatosSolicitudes(){
        ArrayList<Solicitud> solicitudesGuardadas = new ArrayList<Solicitud>();
        File folder = new File(".");
        // busca en la direccion de la ejecucion 
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // si empiza con solicitud entra
                if(nombreArchivo.startsWith("solicitud")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        //lee el archivo
                        solicitudesGuardadas.add((Solicitud) inputStream.readObject());
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                        ex.printStackTrace();
                    }catch (ClassNotFoundException ef){
                        System.out.println(" No es una solicitud");
                    }
                    
                }
                
            }
            
            
        }
        
        return solicitudesGuardadas;
        
    }

    

public static AutorizacionTarjeta cargarDatosAutorizaciones(int numeroSolicitud){
        AutorizacionTarjeta autorizacionGuardada = null;
        File folder = new File(".");
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                if(nombreArchivo.startsWith("autorizaciontarjeta"+numeroSolicitud)){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        autorizacionGuardada = (AutorizacionTarjeta) inputStream.readObject();
                        break;
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                    }catch (ClassNotFoundException ef){
                        System.out.println(" No es una autorizacion");
                    }
                    
                }
                
            }
            
        }
        
        return autorizacionGuardada;
        
    }

    public static Tarjeta cargarDatosTarjetas(String numeroTarjeta){
        boolean bandera=false;
        Tarjeta tarjetasEnElSistema = null;
        //busca en la ejecucion del programa
        File folder = new File(".");
        if(folder.isDirectory()){
            // crea arreglo con los archivos que existen
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // busca una determinada tarjeta
                if(nombreArchivo.startsWith("tarjeta"+numeroTarjeta)){
                   
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        // si existe la carga
                        tarjetasEnElSistema = (Tarjeta) inputStream.readObject();
                        bandera=true;
                        break;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.out.println("Error con el archivo, conexion");
                    }catch (ClassNotFoundException ef){
                        System.out.println(" No es una tarjeta");
                    }
                    
                }
                
                
            }
            if(bandera==false){
                System.out.println("no se encontro la tarjeta");
            }
            
        }
        return tarjetasEnElSistema;
    }

    public static ArrayList<Movimiento> cargarMovimientos(String numeroTarjeta){
        // carga todos los movimientos de una determinada tarjeta
        ArrayList<Movimiento> movimientos = new ArrayList<Movimiento>();
        File folder = new File(".");
        if(folder.isDirectory()){
            //arreglo de strings
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                if(nombreArchivo.startsWith("movimiento"+numeroTarjeta)){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        // si encuentra el movimiento lo carga
                        movimientos.add((Movimiento) inputStream.readObject());
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                    }catch (ClassNotFoundException ef){
                        System.out.println(" No es un movimiento");
                    }
                    
                }
                
            }
            
        }
        return movimientos;
    }
    //sobrecarga del metodo ya que este servira para cargar todas las tarjetas
    public static ArrayList<Tarjeta> cargarDatosTarjetas(){
        ArrayList<Tarjeta> tarjetasEnElSistema = new ArrayList<Tarjeta>();
        File folder = new File(".");
        if(folder.isDirectory()){
            String[] archivos = folder.list();
            for (String nombreArchivo : archivos) {
                // busca todas las tarjetas del sistema y las carga
                if(nombreArchivo.startsWith("tarjeta")){
                    File archivoNuevo = new File(nombreArchivo);
                    try(FileInputStream flujoEntrada = new FileInputStream(archivoNuevo);
                        ObjectInputStream inputStream = new ObjectInputStream(flujoEntrada);){
                        // carga una a una la tarjeta
                        tarjetasEnElSistema.add((Tarjeta) inputStream.readObject());
                    } catch (IOException ex) {
                        System.out.println("Error con el archivo, conexion");
                    }catch (ClassNotFoundException ef){
                        System.out.println(" No es una tarjeta");
                    }
                    
                }
                
            }
            
        }
        return tarjetasEnElSistema;
    }
    
    
    // quita las comillas de la linea
    public static String[] quitarComillasALaLinea(String lineaTexto){
        int posicion1;
        String texto;
        // busca donde esta el ultimo parentesis
        posicion1 = lineaTexto.lastIndexOf(")");
        //guarda la posicion
        texto=lineaTexto.substring(1, posicion1);
        // genera el arreglo 
        String[] atributosDeLinea = texto.split(",");
        int posicion;
        // segun el arreglo quita las comillas
        for (int i = 0; i < atributosDeLinea.length; i++) {
            if(atributosDeLinea[i].contains("\"")){
                posicion = atributosDeLinea[i].lastIndexOf("\"");
                atributosDeLinea[i] = atributosDeLinea[i].substring(1, posicion);
            }
            
        }        
        return atributosDeLinea;
    }
    // verifica si los dias son de 31 o 30, o en ultimo caso 28
    public static int verCantidadDias(int mes){
        int dias=0;
        if(mes==3|| mes==5|| mes== 8|| mes==10){
            dias=30;
        }else if(mes==1){
            dias=28;
            
        }else{
            dias=31;
        }
    return dias;    
    }
    // guarda una solicitud
    public static void guardarSolicitud(Solicitud solicitud){
        // abre el camino para guardarla y pide el objeto a guardar
        File archivoAGuardar= new File("solicitud"+solicitud.getNumeroDeSolicitud() +""+ solicitud.getNombre()+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(solicitud);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
        
    }
    
    public static void guardarMovimiento(Movimiento movimiento) throws FileNotFoundException, IOException{
                // abre el camino para guardarla y pide el objeto a guardar

        String fecha=convertirFechaAGuiones(movimiento.getFecha());
        File archivoAGuardar = new File("movimiento"+movimiento.getNumeroTarjeta()+fecha+movimiento.getDescripcion()+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(movimiento);
            
        }
    }
    
    public static void guardarAutorizacionTarjeta(AutorizacionTarjeta tarjeta){
                // abre el camino para guardarla y pide el objeto a guardar
        File archivoAGuardar= new File("autorizaciontarjeta"+ tarjeta.getNumeroDeSolicitud()+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(tarjeta);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    public static void guardarCancelacionTarjeta(CancelacionTarjeta tarjeta){
                        // abre el camino para guardarla y pide el objeto a guardar
        File archivoAGuardar= new File("cancelacionTarjeta"+ tarjeta.getTarjetaACancelar()+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(tarjeta);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    public static void guardarConsultarTarjeta(ConsultarTarjeta tarjeta){
                        // abre el camino para guardarla y pide el objeto a guardar
        File archivoAGuardar= new File("consulta"+tarjeta.getTarjetaAConsultar()+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(tarjeta);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    public void guardarEstadoDeCuenta(EstadoCuenta estado){
                        // abre el camino para guardarla y pide el objeto a guardar
        fechaActual = historial.get(Calendar.DATE)+"/"+ historial.get(Calendar.MONTH)+"/"+historial.get(Calendar.YEAR);
        File archivoAGuardar= new File("estadoCuenta"+fechaActual+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(estado);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    public void guardarListadoTarjetas(ListadoTarjetas tarjetas){
                        // abre el camino para guardarla y pide el objeto a guardar
        fechaActual = historial.get(Calendar.DATE)+"/"+ historial.get(Calendar.MONTH)+"/"+historial.get(Calendar.YEAR);
        File archivoAGuardar= new File("listadoTarjetas"+fechaActual+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(tarjetas);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    public void guardarListadoSolicitudes(Solicitud solicitud){
                        // abre el camino para guardarla y pide el objeto a guardar

        fechaActual = historial.get(Calendar.DATE)+"/"+ historial.get(Calendar.MONTH)+"/"+historial.get(Calendar.YEAR);
        File archivoAGuardar= new File("listadoSolicitudes"+fechaActual+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(solicitud);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    public static void guardarTarjeta(Tarjeta tarjeta,  String nombre, String numero){
                        // abre el camino para guardarla y pide el objeto a guardar
        fechaActual = historial.get(Calendar.DATE)+"/"+ historial.get(Calendar.MONTH)+"/"+historial.get(Calendar.YEAR);
        File archivoAGuardar= new File("tarjeta"+numero+nombre+".jp");
        try(FileOutputStream out = new FileOutputStream(archivoAGuardar); ObjectOutputStream archivoSalida = new ObjectOutputStream(out);){
            archivoSalida.writeObject(tarjeta);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }
        
    }
    // verifica si cumple con el limite de caracteres, pide la cadena y el limite y solo compara
    public static boolean verificarSiCumpleConLimiteCaracteres(String caracteres, int limiteCaracteres){
        if(caracteres.length()> limiteCaracteres){
            return false;
        }else{
            return true;
            //hacer algo si se valida correctamente el numero de caracteres
        }
    }
    // verifica si esta dentro del rango el tipo de tarjeta
    public static boolean verificarTipoTarjeta(int tipo){
        if(tipo<=0 || tipo>3){
            return false;
        }else{
            return true;
        }
    }
    
    
    // convierte la fecha sin diagonales
    public static String convertirFechaAGuiones(String fecha){
        int posicionPrimeraDiagonal= fecha.indexOf("/");
        int posicionUltimaDiagonal = fecha.lastIndexOf("/");
        String primerDato = fecha.substring(0, posicionPrimeraDiagonal);
        String segundoDato = fecha.substring(posicionPrimeraDiagonal+1,posicionUltimaDiagonal);
        String tercerDato = fecha.substring(posicionUltimaDiagonal+1);
        String fechaCorregida = primerDato+segundoDato+tercerDato;
        
        return fechaCorregida;
        
    }
    
    
    

}