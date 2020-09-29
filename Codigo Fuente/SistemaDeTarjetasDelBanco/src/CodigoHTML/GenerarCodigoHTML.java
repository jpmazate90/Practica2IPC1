
package CodigoHTML;

import Banco.AutorizacionTarjeta;
import Banco.Interes;
import Banco.Movimiento;
import Banco.Tarjeta;
import SolicitudTarjeta.Solicitud;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerarCodigoHTML {
    //atributos de la clase necesarios para crear la sintaxis del codigo HTML
    private static final String ESTILO_TABLA="<table style=\"border-collapse: collapse;\">";
    private static final String INICIO_OPERADOR_TR="<tr>";
    private static final String FIN_OPERADOR_TR="</tr>";
    private static final String ESTILO_LINEA_INICIAL="<th style=\"border: 1px solid #000000;\">";
    private static final String FIN_OPERADOR_TH="</th>";
    private static final String ESTILO_LINEA_ATRIBUTO="<td style=\"border: 1px solid #000000;\">";
    private static final String FIN_OPERADOR_TD="</td>";
    private static final String FIN_OPERADOR_TABLA="</table>";
    private static final String INICIO_OPERADOR_HTML="<html>";
    private static final String FIN_OPERADOR_HTML="</html>";
    private static final String PLANTILLA_NUMERO_TARJETA="NUMERO DE TARJETA";
    private static final String PLANTILLA_TIPO_TARJETA="TIPO DE TARJETA";
    private static final String PLANTILLA_LIMITE="LIMITE";
    private static final String PLANTILLA_NOMBRE= "NOMBRE";
    private static final String PLANTILLA_DIRECCION = "DIRECCION";
    private static final String PLANTILLA_ESTADO_TARJETA= "ESTADO TARJETA";
    private static final String INICIO_OPERADOR_H="<h1>";
    private static final String FIN_OPERADOR_H="</h1>";
    private static final String INICIO_OPERADOR_P="<p>";
    private static final String FIN_OPERADOR_P="</p>";
    private static final String PLANTILLA_FECHA="FECHA";
    private static final String PLANTILLA_TIPO_MOVIMIENTO="TIPO MOVIMIENTO";
    private static final String PLANTILLA_DESCRIPCION="DESCRIPCION";
    private static final String PLANTILLA_ESTABLECIMIENTO="ESTABLECIMIENTO";
    private static final String PLANTILLA_MONTO="MONTO";
    private static final String PLANTILLA_MONTO_TOTAL="MONTO TOTAL";
    private static final String PLANTILLA_INTERESES="INTERESES";
    private static final String PLANTILLA_SALDO_TOTAL="SALDO TOTAL";
    private static final String PLANTILLA_TITULO_ESTADO_CUENTA="ESTADOS DE CUENTA";
    private static final String PLANTILLA_TITULO_LISTADO_TARJETAS="LISTADO DE TARJETAS";
    private static final String PlANTILLA_TITULO_LISTADO_SOLICITUDES="LISTADO DE SOLICITUDES";
    private static final String PlANTILLA_TITULO_NUMERO_SOLICITUD="NUMERO SOLICITUD";
    private static final String PlANTILLA_TITULO_SALARIO="SALARIO";
    private static final String PlANTILLA_TITULO_ESTADO="ESTADO";
    private static final String PLANTILLA_TARJETA="TARJETA";
   
    
    // crea un archivoHTML
    public static void crearArchivoHTML(){
        File archivoReportes = new File("reportes.html");
        FileWriter escritura = null;
        try {
            escritura = new FileWriter(archivoReportes, true);
        } catch (IOException ex) {
            Logger.getLogger(GenerarCodigoHTML.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter br = new BufferedWriter(escritura);
    }
    
    // genera un reporte de consulta tarjeta
    public static void generarConsultaTarjeta(Tarjeta tarjeta){
            try{//genera la via para escribir
                File archivoReportes = new File("reportes.html");
                FileWriter escritura = new FileWriter(archivoReportes, true);
                BufferedWriter escritor = new BufferedWriter(escritura);
                // inicia la operacion de escritura
                escritor.write(INICIO_OPERADOR_HTML);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_H);
                escritor.write(PLANTILLA_TARJETA);
                escritor.write(FIN_OPERADOR_H);
                escritor.newLine(); 
                
                escritor.write(ESTILO_TABLA);
                escritor.newLine();
                escritor.write(INICIO_OPERADOR_TR);
                escritor.newLine();
                //agarra todo lo de la tabla en un arreglo
                String[] plantillas ={PLANTILLA_NUMERO_TARJETA, PLANTILLA_TIPO_TARJETA, PLANTILLA_LIMITE,PLANTILLA_NOMBRE, PLANTILLA_DIRECCION, PLANTILLA_ESTADO_TARJETA};
            //ciclo para agilizar la escritura
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            escritor .write(FIN_OPERADOR_TR);
            escritor .newLine();
            //arreglo que guarda los datos a ingresar en la tabla
            String[] datosTarjetas={tarjeta.getNumeroTarjeta(), tarjeta.getTipoTarjeta(), Double.toString(tarjeta.getLimite()),tarjeta.getNombre(),  tarjeta.getDireccion()};
                escritor.write(INICIO_OPERADOR_TR);
                //ciclo para agilizar la escritura de datos
                  for(int k=0; k<datosTarjetas.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datosTarjetas[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                  //dependiendo del estado de la tarjeta pone una u otra cosa
                  escritor .write(ESTILO_LINEA_ATRIBUTO);
                  if(tarjeta.isEstadoTarjeta()==true){
                      escritor .write("ACTIVA");
                  }else{
                      escritor.write("CANCELADA");
                  }
                  // termina la sintaxis con los operadores de fin
                  escritor .write(FIN_OPERADOR_TD);
                  escritor .newLine();
                  
                  escritor .write(FIN_OPERADOR_TR);
            
                  escritor .write(FIN_OPERADOR_TABLA);
            
                  escritor .write(FIN_OPERADOR_HTML);
            
            // hace que se pasen todos los bytes que quedaron en el stream
                  escritor .flush();
                
            
        
        }
        catch(IOException e){
            e.printStackTrace();
            
        }    
    }
    
    public static void generarTituloEstadoCuenta(){
        try{// abre la via para escribir en el archivo
            File archivoReportes = new File("reportes.html");
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // crea unicamente el titulo de estado de cuenta
            escritor.write(INICIO_OPERADOR_HTML);
            escritor.write(INICIO_OPERADOR_H);
            escritor.write(PLANTILLA_TITULO_ESTADO_CUENTA);
            escritor.write(FIN_OPERADOR_H);
            escritor.newLine(); 
            
            escritor.write(FIN_OPERADOR_HTML);
                
            escritor.flush();
            escritor.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void generarEstadoDeCuenta(Tarjeta tarjeta, Movimiento[] movimientos ){
        
        double montoTotal=0;
        
        try{// abre la via para escribir en el archivo
            File archivoReportes = new File("reportes.html");
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // inicia la sintaxis
            escritor.write(INICIO_OPERADOR_HTML);
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_NUMERO_TARJETA+": "+ tarjeta.getNumeroTarjeta());
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_NOMBRE+": "+tarjeta.getNombre());
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            
            escritor.write(INICIO_OPERADOR_P);
            escritor.write(PLANTILLA_DIRECCION+": "+tarjeta.getDireccion());
            escritor.write(FIN_OPERADOR_P);
            escritor.newLine();
            // inicia la tabla 
            escritor.write(ESTILO_TABLA);
            escritor.write(INICIO_OPERADOR_TR);
            escritor.write(ESTILO_LINEA_INICIAL);
            escritor.write(PLANTILLA_FECHA);
            escritor.write(FIN_OPERADOR_TH);
            escritor.newLine();
            
            escritor.write(ESTILO_LINEA_INICIAL);
            escritor.write(PLANTILLA_TIPO_MOVIMIENTO);
            escritor.write(FIN_OPERADOR_TH);
            escritor.newLine();
            
            escritor.write(ESTILO_LINEA_INICIAL);
            escritor.write(PLANTILLA_DESCRIPCION);
            escritor.write(FIN_OPERADOR_TH);
            escritor.newLine();
            
            escritor.write(ESTILO_LINEA_INICIAL);
            escritor.write(PLANTILLA_ESTABLECIMIENTO);
            escritor.write(FIN_OPERADOR_TH);
            escritor.newLine();
            
            escritor.write(ESTILO_LINEA_INICIAL);
            escritor.write(PLANTILLA_MONTO);
            escritor.write(FIN_OPERADOR_TH);
            escritor.newLine();
            
            escritor.write(FIN_OPERADOR_TR);
            
            escritor.write(INICIO_OPERADOR_TR);
            //si no hay movimientos no escribe nada en esta tarjeta
            if(movimientos!=null){
                //ciclo para introducir todos los movimientos en la tabla
            for(int i=0;i<movimientos.length;i++){
                
                
                escritor.write(ESTILO_LINEA_ATRIBUTO);
                escritor.write(movimientos[i].getFecha());
                escritor.write(FIN_OPERADOR_TD);
                escritor.newLine();
                
       
                
                escritor.write(ESTILO_LINEA_ATRIBUTO);
                escritor.write(movimientos[i].getTipoDeMovimiento());
                escritor.write(FIN_OPERADOR_TD);
                escritor.newLine();
                
                escritor.write(ESTILO_LINEA_ATRIBUTO);
                escritor.write(movimientos[i].getDescripcion());
                escritor.write(FIN_OPERADOR_TD);
                escritor.newLine();
                
                escritor.write(ESTILO_LINEA_ATRIBUTO);
                escritor.write(movimientos[i].getEstablecimiento());
                escritor.write(FIN_OPERADOR_TD);
                escritor.newLine();
                
                escritor.write(ESTILO_LINEA_ATRIBUTO);
                escritor.write(Double.toString(movimientos[i].getMonto()));
                escritor.write(FIN_OPERADOR_TD);
                escritor.newLine();
                
                escritor.write(FIN_OPERADOR_TR);
                if(movimientos[i].getTipoDeMovimiento().equals("CARGO")){
                    montoTotal+= movimientos[i].getMonto();
                }else{
                    montoTotal-=movimientos[i].getMonto();
                }
            }
            }else{// si no hay movimientos crea una en blanco
                for(int l=0;l<6;l++){
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                }
                
            }
            
                escritor.write(FIN_OPERADOR_TABLA);
                // escribe el monto total, los interes y el saldo total
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_MONTO_TOTAL+ ": "+ montoTotal);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                double intereses;
                if(montoTotal<=0){
                    intereses=0;
                }else{
                    intereses= Interes.calcularIntereses(montoTotal, tarjeta.getTipoTarjeta());
                }
                
                double saldoTotal=0;
                if(intereses>0){
                    saldoTotal= intereses+montoTotal;
                }else{
                    saldoTotal=montoTotal-intereses;
                }
                
                
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_INTERESES+": "+intereses);
                escritor.write(FIN_OPERADOR_P);
                escritor.newLine();
                
                escritor.write(INICIO_OPERADOR_P);
                escritor.write(PLANTILLA_SALDO_TOTAL+": "+saldoTotal);
                escritor.write(FIN_OPERADOR_P);
                
                escritor.write(FIN_OPERADOR_HTML);
                // cierra el stream y refresca
                escritor.flush();
                escritor.close();
                
            
            
            
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
    }
    
    
    
    public static void generarListadoTarjetas(ArrayList<Tarjeta> tarjetas){
        try {// abre la via para escribir 
            File archivoReportes = new File("reportes.html");
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            //inicia la sintaxis del HTML
            escritor.write(INICIO_OPERADOR_HTML);
            escritor.write(INICIO_OPERADOR_H);
            escritor.write(PLANTILLA_TITULO_LISTADO_TARJETAS);
            escritor.write(FIN_OPERADOR_H);
            escritor.newLine();
            
            escritor.write(ESTILO_TABLA);
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();
            //arreglo para agilizar la escritura
            String[] plantillas ={PLANTILLA_NUMERO_TARJETA, PLANTILLA_TIPO_TARJETA, PLANTILLA_LIMITE,PLANTILLA_NOMBRE, PLANTILLA_DIRECCION,PLANTILLA_FECHA, PLANTILLA_ESTADO_TARJETA};
            
            for(int j=0;j<plantillas.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            
            escritor.write(FIN_OPERADOR_TR);
            // si no hay tarjetas salta esta parte
            if(!tarjetas.isEmpty()){
                // dependiendo de la cantidad de tarjetas crea el ciclo
            for(int i=0; i<tarjetas.size();i++){
                String[] datosTarjetas={tarjetas.get(i).getNumeroTarjeta(), tarjetas.get(i).getTipoTarjeta(), Double.toString(tarjetas.get(i).getLimite()),tarjetas.get(i).getNombre(), tarjetas.get(i).getDireccion()};
                escritor.write(INICIO_OPERADOR_TR);
                // ciclo para agilizar la escritura
                  for(int k=0; k<datosTarjetas.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datosTarjetas[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                  }
                
                escritor.write(ESTILO_LINEA_ATRIBUTO);
                // dependiendo del tipo de estado de tarjeta
                if(tarjetas.get(i).isEstadoTarjeta()==true){
                    escritor.write(Tarjeta.pasarFechaComoString(tarjetas.get(i).getFechaAutorizada()));
                }else{
                    escritor.write(Tarjeta.pasarFechaComoString(tarjetas.get(i).getFechaCancelada()));
                }
                escritor.write(FIN_OPERADOR_TD);
                escritor.newLine();
                
                escritor.write(ESTILO_LINEA_ATRIBUTO);
                //dependiendo del tipo de estado de tarjeta
                if(tarjetas.get(i).isEstadoTarjeta()==true){
                    escritor.write("ACTIVA");
                }else{
                    escritor.write("CANCELADA");
                }
                escritor.write(FIN_OPERADOR_TD);
                escritor.newLine();
                
                escritor.write(FIN_OPERADOR_TR);
                
                
            
            }
            }else{
                for(int y=0;y<8;y++){
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();
                    
                }
                
            }
            // cierra la sintaxis, refresca y cierra el stream
            escritor.write(FIN_OPERADOR_TABLA);
            
            escritor.write(FIN_OPERADOR_HTML);
            
            escritor.flush();
            escritor.close();
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void generarListadoSolicitudes(ArrayList<Solicitud> solicitudes){
        try {// abre la via para escribir en el archivo
            File archivoReportes = new File("reportes.html");
            FileWriter escritura = new FileWriter(archivoReportes, true);
            BufferedWriter escritor = new BufferedWriter(escritura);
            // inicia la sintaxis 
            escritor.write(INICIO_OPERADOR_HTML);
            escritor.write(INICIO_OPERADOR_H);
            escritor.write(PlANTILLA_TITULO_LISTADO_SOLICITUDES);
            escritor.write(FIN_OPERADOR_H);
            escritor.newLine();
            //inicia la tabla
            escritor.write(ESTILO_TABLA);
            escritor.write(INICIO_OPERADOR_TR);
            escritor.newLine();
            //arreglo
            String[] plantillas2 = {PlANTILLA_TITULO_NUMERO_SOLICITUD, PLANTILLA_FECHA, PLANTILLA_TIPO_TARJETA, PLANTILLA_NOMBRE, PlANTILLA_TITULO_SALARIO, PLANTILLA_DIRECCION, PLANTILLA_FECHA, PlANTILLA_TITULO_ESTADO};
            //ciclo para  agilizar 
            for(int j=0;j<plantillas2.length;j++){
                escritor.write(ESTILO_LINEA_INICIAL);
                escritor.write(plantillas2[j]);
                escritor.write(FIN_OPERADOR_TH);
                escritor.newLine();
            }
            
            escritor.write(FIN_OPERADOR_TR);
            // si no hay solicitudes salta
            if(!solicitudes.isEmpty()){
                // ciclo para agilizar
            for(int i=0; i<solicitudes.size();i++){
                String[] datosTarjetas={Integer.toString(solicitudes.get(i).getNumeroDeSolicitud()), solicitudes.get(i).getFecha(), AutorizacionTarjeta.tipoTarjeta(solicitudes.get(i).getTipo()), solicitudes.get(i).getNombre(), Integer.toString(solicitudes.get(i).getSalario()), solicitudes.get(i).getDireccion()};
                escritor.write(INICIO_OPERADOR_TR);
                //ciclo para agilizar
                for(int k=0; k<datosTarjetas.length;k++){
                      escritor.write(ESTILO_LINEA_ATRIBUTO);
                      escritor.write(datosTarjetas[k]);
                      escritor.write(FIN_OPERADOR_TD);
                      escritor.newLine();
                }
                
                escritor.write(ESTILO_LINEA_ATRIBUTO);
                //dependiendo del estado de la solicitud
                if(solicitudes.get(i).isEstadoSolicitud()==true){
                    escritor.write(Tarjeta.pasarFechaComoString(solicitudes.get(i).getFechaAutorizada()));
                }else{
                    if(solicitudes.get(i).getFechaRechazada()==null){
                        escritor.write("No tiene fecha de autorizacion y/o rechazo");
                    }else{
                        escritor.write(Tarjeta.pasarFechaComoString(solicitudes.get(i).getFechaRechazada()));
                    }
                }
                escritor.write(FIN_OPERADOR_TD);
                escritor.newLine();
                
                escritor.write(ESTILO_LINEA_ATRIBUTO);
                // dependiendo del estado de la solicitud
                if(solicitudes.get(i).isEstadoSolicitud()==true){
                    escritor.write("Autorizada");
                }else{
                    if(solicitudes.get(i).getFechaRechazada()==null){
                        escritor.write("No esta ni autorizada ni rechazada");
                    }else{
                        escritor.write("Rechazada");
                    }
                }
                escritor.write(FIN_OPERADOR_TD);
                escritor.newLine();
                
                escritor.write(FIN_OPERADOR_TR);
            }
            }else{
                for(int q=0;q<9;q++){
                    escritor.write(ESTILO_LINEA_ATRIBUTO);
                    escritor.write(FIN_OPERADOR_TD);
                    escritor.newLine();                    
                }
                
            }
            //termina la tabla
            
            escritor.write(FIN_OPERADOR_TABLA);
            
            escritor.write(FIN_OPERADOR_HTML);
            
            escritor.newLine();
            escritor.newLine();
            escritor.newLine();
            escritor.newLine();
            //refresca y cierra el stream
            escritor.flush();
            escritor.close();
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
    
    
    
    
    
    
    
}

