
package ExcepcionesPersonalizadas;

import LogicaJuego.LogicaJuego;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jpmazate
 */
public class ExcepcionPersonalizada {
    
    
    
    //constructor
    public ExcepcionPersonalizada(){
        
    }
    
    // verifica si cumple con la fecha
    public void verificarSiCumpleFormatoFecha(String fecha1) throws ExcepcionFormato, ParseException{
       
        // variables necesarias para la comprobacion
        final int AÑO_INICIO=1900;
        final int MES_INICIO=1;
        String[] fechaDesglosada = fecha1.split("/");
        int dia= Integer.parseInt(fechaDesglosada[0]);
        int mes= Integer.parseInt(fechaDesglosada[1])-MES_INICIO;
        int año= Integer.parseInt(fechaDesglosada[2])-AÑO_INICIO;
        System.out.println(dia);
        System.out.println(mes);
        System.out.println(año);
        
        
        
        int diasQueDebeDeTener=0;
        //verifica si cumple el rango de mes
        if(mes>=0 && mes<=11){
            // dependiendo del mes dice cuantos dias debe de tener el mes
            diasQueDebeDeTener= LogicaJuego.verCantidadDias(mes);
            if(dia>=0 && dia<=diasQueDebeDeTener){
                if(año!=0){
                    // si es fecha valida no genera ninguna excepcion
                    System.out.println("Si es fecha valida");
                    Date fecha = new Date(año, mes, dia);
                    SimpleDateFormat fecha2 = new SimpleDateFormat("dd/MM/yyyy");
                    System.out.println(fecha2.format(fecha));
                }else{// excepcion formato
                    throw new ExcepcionFormato("Fecha Incorrecta, año incorrecto");
                }
            }else{// excepcion formato
                throw new ExcepcionFormato("Fecha Incorrecta, dia incorrecto");
            }
        }else{// excepcion formato
            throw new ExcepcionFormato("Fecha incorrecta, mes incorrecto");
        }
            
    }
    // verifica si cumple con el  limite de caracteres segun el limite ingresado
    public  boolean verificarSiCumpleConLimiteCaracteres(String caracteres, int limiteCaracteres) {
        boolean cumple=true;
        if(caracteres.length()> limiteCaracteres){
            try {// excepcion formato
                throw new ExcepcionFormato("El numero de caracteres sobrepasa al permitido. Caracteres permitidos: {limiteCaracteres}");
            } catch (ExcepcionFormato ex) {
                ex.printStackTrace();
                cumple=false;
            }
        }else{
            return cumple;
        }
        return cumple;
        
    }
    // verifica si ya existe la solicitud
    public void verificarNumeroSolicitud(int numeroSolicitud, ArrayList<Integer> solicitudes) throws ExcepcionLogica{
        for (Integer elemento : solicitudes) {
            if(numeroSolicitud == solicitudes.get(elemento)){
                //lanza excepcion logica
                throw new ExcepcionLogica("Ya existe esa solicitud");
                
                
            }
        }
    }
    
    
    
    
    
}
