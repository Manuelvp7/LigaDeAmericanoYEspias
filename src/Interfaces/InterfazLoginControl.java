/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import javax.swing.JPanel;

/**
 *
 * @author manuel
 */
public interface InterfazLoginControl {
    
    public void ingresar(String userName,String Pass,String categoriaUsuario);
    public void cambiarPanel(JPanel unPanel);
    
    
    
}
