/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.Jugador;
import civitas.TituloPropiedad;
import java.util.ArrayList;

/**
 *
 * @author ecast
 */
public class JugadorPanel extends javax.swing.JPanel {

    public Jugador jugador;
    /**
     * Creates new form JugadorPanel
     */
    public JugadorPanel() {
        initComponents();
    }

    public void setJugador(Jugador jugador){
        this.jugador = jugador;
        nombre.setText(jugador.getNombre());
        saldo.setText(Float.toString(jugador.getSaldo()));
        if(jugador.isEncarcelado())
            encarcelado.setText("SI");
        else
            encarcelado.setText("NO");
        if(jugador.getEspeculador())
            especulador.setText("SI");
        else
            especulador.setText("NO");
        repaint();
        revalidate();
        rellenaPropiedades(jugador.getPropiedades());
    }
    
    
    public void rellenaPropiedades(ArrayList<TituloPropiedad> lista){
        //se elimina la info antigua
        Propiedades.removeAll();
        //se recorre la lista de propiedades para ir creando sus vista individuales y añadirlas al panel
        for(TituloPropiedad t: lista){
            PropiedadPanel vistaPropiedad = new PropiedadPanel();
            vistaPropiedad.setPropiedad(t);
            
            Propiedades.add(vistaPropiedad);
            vistaPropiedad.setVisible(true);
        }
        //se fuerza la actualizacion visual del panelpropiedades y del panel del jugador.
        repaint();
        revalidate();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jnombre = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        jsaldo = new javax.swing.JLabel();
        saldo = new javax.swing.JTextField();
        jencarcelado = new javax.swing.JLabel();
        encarcelado = new javax.swing.JTextField();
        jespeculador = new javax.swing.JLabel();
        especulador = new javax.swing.JTextField();
        Propiedades = new javax.swing.JPanel();

        jnombre.setText("Jugador");
        jnombre.setEnabled(false);

        nombre.setText("nombre");
        nombre.setEnabled(false);

        jsaldo.setText("saldo");
        jsaldo.setEnabled(false);

        saldo.setText("saldo");
        saldo.setEnabled(false);

        jencarcelado.setText("¿Está encarcelado?");
        jencarcelado.setEnabled(false);

        encarcelado.setText("si/no");
        encarcelado.setEnabled(false);
        encarcelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encarceladoActionPerformed(evt);
            }
        });

        jespeculador.setText("¿Es especulador?");
        jespeculador.setEnabled(false);

        especulador.setText("si/no");
        especulador.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jnombre)
                            .addComponent(jsaldo))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jencarcelado)
                            .addComponent(jespeculador))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(especulador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(encarcelado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(77, Short.MAX_VALUE))
            .addComponent(Propiedades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jnombre)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsaldo)
                    .addComponent(saldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jencarcelado)
                    .addComponent(encarcelado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jespeculador)
                    .addComponent(especulador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Propiedades, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void encarceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encarceladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_encarceladoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Propiedades;
    private javax.swing.JTextField encarcelado;
    private javax.swing.JTextField especulador;
    private javax.swing.JLabel jencarcelado;
    private javax.swing.JLabel jespeculador;
    private javax.swing.JLabel jnombre;
    private javax.swing.JLabel jsaldo;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField saldo;
    // End of variables declaration//GEN-END:variables
}
