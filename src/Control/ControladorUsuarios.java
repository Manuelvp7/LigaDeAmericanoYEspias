package Control;

import Conexiones.ConexionLmfaEspias;

import DAOImpl.CategoriausuarioDAOImpl;

import DAOImpl.UsuarioDAOImpl;
import Interfaces.InterfazAdministrador;


import Interfaces.InterfazVistaControladorAdministrarUsuarios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import Modelo.Usuario;
import Modelo.UsuarioKey;


import Vista.PanelAdminUsuarioDelSistema;
import java.util.ArrayList;

public class ControladorUsuarios implements InterfazVistaControladorAdministrarUsuarios{

	private PanelAdminUsuarioDelSistema panelUsuariosDelSistema;
	
        private Usuario unUsuario;
	
        private ConexionLmfaEspias conn;
        
        
	//private ConsultorBD consultor;
	//private InsertorBD insertor;
        private CategoriausuarioDAOImpl unaCategoriausuarioDAOImpl;
        
        private UsuarioDAOImpl unUsuarioDAOImpl;

        
        
        
            
    
        int tipoInterface;

        private Interfaces.InterfazAdministrador unaInterfazAdministrador;
                    

        
	
	public ControladorUsuarios(InterfazAdministrador unaInterfazAdministrador){

            unUsuarioDAOImpl = new UsuarioDAOImpl();
            panelUsuariosDelSistema = new PanelAdminUsuarioDelSistema(this);
            unaCategoriausuarioDAOImpl = new CategoriausuarioDAOImpl();
            conn = new ConexionLmfaEspias();
            cargarTablaUsuarios();
            this.unaInterfazAdministrador = unaInterfazAdministrador;
            
            
            	
	}

        
        public void cargarTablaUsuarios(){
            
           
            ArrayList<Usuario> usuarios;
            try {
                usuarios = unUsuarioDAOImpl.load(conn.crearConexion());
                if(usuarios!=null)
                    panelUsuariosDelSistema.actualizarTabla(usuarios);
                else
                    System.out.println("FUUUUUUUUUUUUUUUUUUUUCK EN CONTROLADOR PERSONA CARGAR TABLA DE PERSONA");
            } catch (SQLException ex) {
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }

    public void agregar(String userName,String password) {
        
            try {
                
                
                
                unUsuario = new Usuario();
                unUsuario.setCategoriausuario("ADMINISTRADOR");
                unUsuario.setNombreusuario(userName);
                unUsuario.setContrasena(password); 
                unUsuarioDAOImpl.create(unUsuario,conn.crearConexion());
                 
                cargarTablaUsuarios();
                
            } catch (SQLException ex) {
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public PanelAdminUsuarioDelSistema getPanelUsuariosDelSistema() {
        return panelUsuariosDelSistema;
    }

    



    @Override
    public void borrar(String userName) {
        
     
            try {
                   UsuarioKey unKey = new UsuarioKey();
                   unKey.setNombreusuario(userName);
                   unUsuarioDAOImpl.delete(unKey, conn.crearConexion());
                   cargarTablaUsuarios();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    


    @Override
    public void actualizar(String userName,String password) {
        
        
        
            try {
                
                Usuario unUsuario = new Usuario();
                unUsuario.setCategoriausuario("ADMINISTRADOR");
                unUsuario.setContrasena(password);
                unUsuario.setNombreusuario(userName);
                
                System.out.println("EL USUARIO ACTUALIZADO ES:" + unUsuario.toString());

                unUsuarioDAOImpl.update(unUsuario, conn.crearConexion());
                
//                unEmpleadoDAOImpl.update(unEmpleado, conn.crearConexion());

                cargarTablaUsuarios();
                
            } catch (SQLException ex) {
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    
    
    @Override
    public void regresar(){
        
        switch(tipoInterface){
            
            case 1:{
                
                unaInterfazAdministrador.regresarAlPanelPadre();
                
                break;
            }
            case 2:{
                unaInterfazAdministrador.regresarAlPanelPadre();
                break;
                
            }
            case 3:{
                //unaInterfazTableroDeMercado.regresarAlPanelPadre();
                break;
            }
        }
        
   
    }
    
    



	



}
