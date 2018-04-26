/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;


import Conexiones.ConexionLmfaEspias;
import DAOImpl.CategoriausuarioDAOImpl;
import DAOImpl.PartidoDAOImpl;
import DAOImpl.RecorddeequipoDAOImpl;
import DAOImpl.UsuarioDAOImpl;
import Interfaces.InterfazLoginControl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Modelo.Persona;
import Modelo.Usuario;
import Modelo.UsuarioKey;

import Vista.PanelLogin;
import Vista.PanelAdminEquipo;

/**
 *
 * @author manuel
 */
public class ControlLogin implements InterfazLoginControl{
    
    private ConexionLmfaEspias conn;
    private UsuarioDAOImpl unUsuarioDAOImpl;
    private CategoriausuarioDAOImpl unaCategoriausuarioDAOImpl;
    private PanelLogin unPanelLogin;
    private Usuario unUsuario;
    private Persona unaPersona;
    private  JFrame miFrame;
    
    private ControladorAdmin unControladorAdmin;
    private ControladorEspia unControladorEspia;
    
    
    //private ControladorPartido unControladorPartido;
    
    
    
    
    public ControlLogin() {

        conn = new ConexionLmfaEspias();
        miFrame = new JFrame();
        miFrame.setDefaultCloseOperation(3);
        unUsuarioDAOImpl = new UsuarioDAOImpl();
        unaCategoriausuarioDAOImpl = new CategoriausuarioDAOImpl();                
        unPanelLogin = new PanelLogin(this);
        
        
        
        //cargarComboTipoDeUsuarios();
        ejecutarGUI();

    }
    
    
            
    public void cargarComboTipoDeUsuarios(){
            List<Modelo.Categoriausuario> categoriaDeUsuarios;
            try {
                categoriaDeUsuarios = unaCategoriausuarioDAOImpl.loadCategorias(conn.crearConexion());
                if (categoriaDeUsuarios!=null) {
                
//                   unPanelLogin.cargarComboTipoDeUsuarios(categoriaDeUsuarios);
                }else{
                    System.out.println("FUUUUUUUUUUUUUUUUUUUCK DESDE CARGAR CATEGORIA DE USUARIOS");
                }
                
            } catch (SQLException ex) {
                //Logger.getLogger(ControladorPersona.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }

    @Override
    public void ingresar(String userName, String pass, String categoriaUsuario) {
        
        Object[] resultado = null;
        
        try {
            resultado=unUsuarioDAOImpl.LoadUsuario(userName,pass, conn.crearConexion());
            if(resultado!=null){
                iniciarPanel(resultado);
            }else
                JOptionPane.showMessageDialog(null, "USUARIO NO RECONOCIDO");
        } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "USUARIO Y CONTRASENIA NO VALIDOSSSSSSSSSSSSSSSS");
        }
    }
    
    public void iniciarPanel(Object[] registro){
        
        String aliasEspia = String.valueOf(registro[0]);
        String categoriaUsuario = String.valueOf(registro[2]);
        
        switch(categoriaUsuario){
            case "ADMINISTRADOR":{
                  System.out.println("BIENVENIDO ADMIN");              
                  unControladorAdmin = new ControladorAdmin(this, categoriaUsuario);
                   cambiarPanel(unControladorAdmin.getUnPanelAdminEquipo());
                break;
            }
            case "ESPIA":{
                
                unControladorEspia = new ControladorEspia(this,aliasEspia);
                cambiarPanel(unControladorEspia.getUnPanelEspia());
               
                
                break;
            }
 
        }
        
        
    }
    
    public void cambiarPanel(JPanel unPanel){
        
        
        miFrame.getContentPane().removeAll();
        miFrame.getContentPane().invalidate();
        
        miFrame.getContentPane().add(unPanel);
        miFrame.getContentPane().revalidate();
        miFrame.pack();
        
        miFrame.setVisible(true);
               
        
    }
    
    public void ejecutarGUI(){

        miFrame.add(unPanelLogin);
        miFrame.pack();
        miFrame.setVisible(true);
       
    }
     
    
    
    
}
