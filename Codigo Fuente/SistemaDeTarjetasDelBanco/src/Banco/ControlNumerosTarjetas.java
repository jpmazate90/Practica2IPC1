
package Banco;

import java.io.Serializable;

public class ControlNumerosTarjetas implements Serializable{
    //atributos privados de la clase
    private int nacional;
    private int regional;
    private int internacional;
    // constructor de la clase controlnumerostarjetas
    public ControlNumerosTarjetas(){
        // se asigna 1 al instanciar la clase
        this.nacional = 1;
        this.regional = 1;
        this.internacional = 1;
    }
    // getters and setters
    public int getNacional() {
        return nacional;
    }

    public void setNacional(int nacional) {
        this.nacional = nacional;
    }

    public int getRegional() {
        return regional;
    }

    public void setRegional(int regional) {
        this.regional = regional;
    }

    public int getInternacional() {
        return internacional;
    }

    public void setInternacional(int internacional) {
        this.internacional = internacional;
    }
    
    
    
}
