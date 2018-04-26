/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Conexiones.ConexionLmfa;
import DAOImpl.EquipoDAOImpl;
import DAOImpl.PartidoDAOImpl;
import DAOImpl.RecorddeequipoDAOImpl;
import Interfaces.InterfazAdministrarEquipo;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import Interfaces.InterfazLoginControl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Modelo.Partido;
import Modelo.Recorddeequipo;

import Vista.PanelAdminEquipo;
import Interfaces.InterfazAdministrador;

/**
 *
 * @author manuel
 */
public class ControladorAdmin implements InterfazAdministrador{
    
    //INTERFAZ
    //private interfaces.interfazAdministrador unaInterfazAdministrarEquipo;
    
    //CONTROL
    
    
    
    private ControladorUsuarios unControladorUsuarios;
    private ControladorPartido unControladorPartido;
    
    private InterfazLoginControl unaInterfazLoginControl;
    
    //PANEL
    private PanelAdminEquipo unPanelAdminEquipo;
    
    //DAO
    private PartidoDAOImpl unPartidoDAOImpl;
    private RecorddeequipoDAOImpl unRecorddeequipoDAOImpl;
    
    //CONEXION
    private Conexiones.ConexionLmfa conn;
    
    
    
    public ControladorAdmin( InterfazLoginControl unaInterfazLoginControl,String nombre){

        this.unaInterfazLoginControl = unaInterfazLoginControl;
        unPartidoDAOImpl = new PartidoDAOImpl();
        unRecorddeequipoDAOImpl = new RecorddeequipoDAOImpl();
        unPanelAdminEquipo = new PanelAdminEquipo(this);        
        unPanelAdminEquipo.cargarTablas();
        unControladorUsuarios = new ControladorUsuarios(this);
        conn = new ConexionLmfa();
        unControladorPartido = new ControladorPartido(unaInterfazLoginControl,unPanelAdminEquipo);
        
    }

    @Override
    public void cargarProximasFechas(String temporada,String equipo) {
        
        
        try {
            System.out.println("CARGANDO PROXIMAS FECHAS ");
            List<Object[]> registros;
            registros = unPartidoDAOImpl.loadProximosPartidos(temporada, equipo, conn.crearConexion());
            if(registros==null)
                System.out.println("COCK SUUUUUUUUUUUUUUUCKER PARA PROXIMAS FECHAS");
                
            else{
                                    
                System.out.println("REGRESARON LOS REGISTROS DE PROXIMAS FECHAS");
                System.out.println("PRIMER REGISTRO "+ registros.get(0).toString());
                unPanelAdminEquipo.cargarProximasFechas(registros);
            }

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO HAY TABLA DE PROXIMAS FECHAS");
        }
        
        
    }

    @Override
    public void cargarTablaDePosiciones(String temporada) {

        try {
            List<Recorddeequipo> registros;
            registros=unRecorddeequipoDAOImpl.load(temporada, conn.crearConexion());
            if(registros!=null)
                
                unPanelAdminEquipo.cargarTablaDePosiciones(registros);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO HAY TABLA DE POSICIONES");
        }
        
        
    }

    @Override
    public void cargarTablaDeResultados(String temporada, String equipo) {
        try {
            List<Partido> registros;
            registros=unPartidoDAOImpl.loadResultados(temporada, equipo, conn.crearConexion());
            
            if(registros!=null)
                unPanelAdminEquipo.cargarTablaDeResultados(registros);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO HAY TABLA DE RESULTADOS");
        }
    }

    public PanelAdminEquipo getUnPanelAdminEquipo() {
        return unPanelAdminEquipo;
    }

    @Override
    public void irA(int panel) {
        JPanel elPanel = null;
        switch ( panel){
            case 1:{
                
                elPanel= unControladorUsuarios.getPanelUsuariosDelSistema();
                break;
            }
            case 2:{
                
                
                elPanel=unControladorPartido.getPanelAdminDeLiga();
                break;
            }
            case 3:{
                //elPanel=unControladorPersona.getPanelUsuariosDelSistema();
                
                break;
            }
            default:{
                break;
            }

        }
        unaInterfazLoginControl.cambiarPanel(elPanel);
    }

    @Override
    public void regresarAlPanelPadre() {
        
        unaInterfazLoginControl.cambiarPanel(unPanelAdminEquipo);
        
    }
        

    


    
}
