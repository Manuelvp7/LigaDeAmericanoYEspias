/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Interfaces.InterfazAdministrarPartidos;
import Modelo.Espias;
import Modelo.Usuario;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author manuel
 */
public class PanelAsignarEspiaAPartido extends javax.swing.JPanel {
    
    private InterfazAdministrarPartidos unaInterfazAdministrarPartidos;
    
            
    private DefaultTableModel modelo;

    /**
     * Creates new form PanelAsignarEspiaAPartido
     */
    public PanelAsignarEspiaAPartido() {
        initComponents();
    }

    public PanelAsignarEspiaAPartido(InterfazAdministrarPartidos unaInterfazAdministrarPartidos) {
        
        this.unaInterfazAdministrarPartidos = unaInterfazAdministrarPartidos;

        initComponents();
                
        modelo = (DefaultTableModel)tablaDeEspias.getModel();
        
        
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDeEspias = new javax.swing.JTable();
        btnAsignar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        jButton1.setText("jButton1");

        tablaDeEspias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Alias Espia"
            }
        ));
        jScrollPane2.setViewportView(tablaDeEspias);

        btnAsignar.setText("Asignar");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAsignar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAsignar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        unaInterfazAdministrarPartidos.cogelarCampos(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed

        int i = tablaDeEspias.getSelectedRow();
        unaInterfazAdministrarPartidos.asignarEspiaAPartido((String)tablaDeEspias.getValueAt(i, 0));
        unaInterfazAdministrarPartidos.cogelarCampos(false);
    
    }//GEN-LAST:event_btnAsignarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaDeEspias;
    // End of variables declaration//GEN-END:variables

    public void actualizarTabla(List<Espias> espias){
        
        modelo.setRowCount(0);
        
        if(espias!=null){
            Object[] registro = new Object[1];
            for(int i=0;i<espias.size();i++){
                System.out.println("ALIAS "+espias.get(i).getAlias());
                registro[0]=espias.get(i).getAlias();
                modelo.addRow(registro);
            }
            
        }  

	
        tablaDeEspias.setModel(modelo);
		
        
    }

}
