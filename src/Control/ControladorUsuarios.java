package Control;

import Conexiones.ConexionEspias;
import Conexiones.ConexionLmfaEspias;

import DAOImpl.CategoriausuarioDAOImpl;
import DAOImpl.EspiasDAOImpl;

import DAOImpl.UsuarioDAOImpl;
import Interfaces.InterfazAdministrador;


import Interfaces.InterfazVistaControladorAdministrarUsuarios;
import Modelo.Categoriausuario;
import Modelo.Espias;
import Modelo.EspiasKey;

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
        EspiasDAOImpl unEspiasDAOImpl ;
        
        private UsuarioDAOImpl unUsuarioDAOImpl;

        
        
        
            
    
        

        private Interfaces.InterfazAdministrador unaInterfazAdministrador;
                    

        
	
	public ControladorUsuarios(InterfazAdministrador unaInterfazAdministrador){

            unUsuarioDAOImpl = new UsuarioDAOImpl();
            unEspiasDAOImpl = new EspiasDAOImpl();
            panelUsuariosDelSistema = new PanelAdminUsuarioDelSistema(this);
            unaCategoriausuarioDAOImpl = new CategoriausuarioDAOImpl();
            conn = new ConexionLmfaEspias();
            cargarTablaUsuarios();
            cargarCategorias();
            
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

    public void agregar(String userName,String password,String categoria) {
        
            try {
                
                
                
                unUsuario = new Usuario();
                unUsuario.setCategoriausuario(categoria);
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
    public void borrar(String userName,boolean esEspia) {
        
     
            try {
                   UsuarioKey unKey = new UsuarioKey();
                   unKey.setNombreusuario(userName);
                   EspiasKey espiaKey = new EspiasKey();
                   espiaKey.setAlias(userName);
                   if(esEspia){
                       unEspiasDAOImpl.delete(espiaKey,ConexionEspias.crearConexion());
                       
                   }
                   
                   unUsuarioDAOImpl.delete(unKey, conn.crearConexion());
                   cargarTablaUsuarios();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    


    @Override
    public void actualizar(String userName,String password,String categoria) {
        
        
        
            try {
                
                Usuario unUsuario = new Usuario();
                unUsuario.setCategoriausuario(categoria);
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
        
        unaInterfazAdministrador.regresarAlPanelPadre();
    }
    
        
    public void cargarCategorias(){
        
        
        try {
            List<Categoriausuario> categorias;
            
            categorias = unaCategoriausuarioDAOImpl.loadCategorias(Conexiones.ConexionLmfaEspias.crearConexion());
            
            if (categorias!=null) {

                System.out.println("VANOS A IMPRIMIR CATEGORIAS");
                for (Categoriausuario categoria : categorias) {
                
                    System.out.println("CATE "+categoria.getCategoria());
                }
                panelUsuariosDelSistema.cargarComboEquipos(categorias);
            }   

        
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void agregarEspia(String userName) {
            try {
                Espias unEspia  = new Espias();
                unEspia.setAlias(userName);
                unEspia.setEstado((byte)0);
                unEspia.setGenero(JOptionPane.showInputDialog("Ingrese el genero M/F"));
                unEspiasDAOImpl.create(unEspia, Conexiones.ConexionEspias.crearConexion());
                
            } catch (SQLException ex) {
                Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    



	



}
