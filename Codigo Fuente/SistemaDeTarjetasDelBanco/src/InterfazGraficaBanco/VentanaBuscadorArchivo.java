/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGraficaBanco;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaBuscadorArchivo extends javax.swing.JDialog {
    //variables locales
    private String archivoALeer;
    private FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de imagen", "bin");
    String direccionImagen;
    int tiempoAleerCadaArchivo;
    // getters and setters
    public VentanaBuscadorArchivo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
//        buscadorArchivos.setFileFilter(filtro);
    }
    
    public String getArchivoALeer(){
        return this.archivoALeer;
    }
    
    public void setArchivoALeer(String archivoALeer){
        this.archivoALeer = archivoALeer;
    }
    
    public int getTiempoALeerCadaArchivo(){
        return this.tiempoAleerCadaArchivo;
    }
    
    public void setTiempoALeerCadaArchivo(int tiempoALeerCadaArchivo){
        this.tiempoAleerCadaArchivo = tiempoALeerCadaArchivo;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscadorArchivos = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        buscadorArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscadorArchivosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(buscadorArchivos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(buscadorArchivos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscadorArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscadorArchivosActionPerformed
        // file chooser personalizado, muy parecido al normal
        String tiempoMilisegundos;
        int tiempoMilisegundosArchivo;
        int opcion = buscadorArchivos.showOpenDialog(this);
        if(opcion == JFileChooser.APPROVE_OPTION){
            String archivo = buscadorArchivos.getSelectedFile().getAbsolutePath();
            String archivo1 = buscadorArchivos.getSelectedFile().toString();
            this.archivoALeer = archivo;
            System.out.println("Se ha encontrado el archivo: "+archivo1);
            tiempoMilisegundos = JOptionPane.showInputDialog("Introduce el tiempo que quieres que tarde en leer cada linea del archivo (Milisegundos): ");
            try {
                tiempoMilisegundosArchivo = Integer.parseInt(tiempoMilisegundos);
                this.tiempoAleerCadaArchivo = tiempoMilisegundosArchivo;
                System.out.println("tiempo: "+tiempoMilisegundos);
                this.archivoALeer = archivo;
                
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, "No metiste un dato entero");
                System.out.println("No se introdujo un dato entero");
                
            }finally{
                this.setVisible(false);
            }
            
            
                    
        }
        
        else if(opcion == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null, "No se ha cargado ningun archivo");
            System.out.println("No se ha cargado ningun archivo");
        }
    
    
    }//GEN-LAST:event_buscadorArchivosActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser buscadorArchivos;
    // End of variables declaration//GEN-END:variables
}
