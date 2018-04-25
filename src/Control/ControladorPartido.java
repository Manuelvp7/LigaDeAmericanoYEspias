/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;




import Conexiones.ConexionEspias;
import Conexiones.ConexionLmfa;
import Conexiones.ConexionLmfaEspias;
import DAOImpl.EquipoDAOImpl;
import DAOImpl.EspiasDAOImpl;
import DAOImpl.EspiatienepartidoDAOImpl;
import DAOImpl.PartidoDAOImpl;

import DAOImpl.RecorddeequipoDAOImpl;

import Interfaces.InterfazAdministrador;
import Interfaces.InterfazAdministrarPartidos;
import Interfaces.InterfazLoginControl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Modelo.Equipo;
import Modelo.Espias;
import Modelo.EspiasKey;
import Modelo.Espiatienepartido;
import Modelo.Partido;
import Modelo.PartidoKey;
import Modelo.Recorddeequipo;
import Modelo.RecorddeequipoKey;

import Vista.PanelAdminDePartidos;
import Vista.PanelAdminEquipo;
import Vista.PanelAsignarEspiaAPartido;
import java.util.ArrayList;
import javax.swing.JFrame;



/**
 *
 * @author manuel
 */
public class ControladorPartido implements InterfazAdministrarPartidos{
    
    private InterfazLoginControl unaInterfazLoginControl;
    
    private Partido unPartido;
    private PartidoDAOImpl unPartidoDAOImpl;
    private RecorddeequipoDAOImpl unRecorddeequipoDAOImpl;
    private EspiatienepartidoDAOImpl unEspiatienepartidoDAO;
    private EspiasDAOImpl unEspiasDAOImpl;
    
    
    private PanelAdminDePartidos unPanelAdminDePartidos;
    private PanelAdminEquipo unPanelAdminEquipo;
    
    private Conexiones.ConexionLmfa conn;
    private Conexiones.ConexionLmfaEspias connLmfEspias;
    
    private EquipoDAOImpl unEquipoDAOImpl;
    private Recorddeequipo unRecordDeEquipo;
    
    private JFrame unFrame;
    private PanelAsignarEspiaAPartido panelAsignarEspiaAPartido;
    
    
                    
    
    public ControladorPartido(InterfazLoginControl  unaInterfazLoginControl, PanelAdminEquipo unPanelAdminEquipo){

                this.unaInterfazLoginControl = unaInterfazLoginControl;
                unPartido = new Partido();
                unRecorddeequipoDAOImpl = new RecorddeequipoDAOImpl();
                unPartidoDAOImpl = new PartidoDAOImpl();
                unPanelAdminDePartidos = new PanelAdminDePartidos(this);
                unEquipoDAOImpl = new EquipoDAOImpl();
                unRecordDeEquipo = new Recorddeequipo();
                cargarEquipos();
                cargarTablaPartidos();
                unPanelAdminDePartidos.setVisible(true);
                this.unPanelAdminEquipo = unPanelAdminEquipo;
                unFrame = new JFrame();
                panelAsignarEspiaAPartido = new PanelAsignarEspiaAPartido(this);
                unEspiatienepartidoDAO = new EspiatienepartidoDAOImpl();
                unEspiasDAOImpl = new EspiasDAOImpl();
                
            

}

    
    public void cargarTablaPartidos() {
        
        
        try {
            
            List<Partido> partidos;
            partidos=unPartidoDAOImpl.load(conn.crearConexion());
            if(partidos!=null)
                unPanelAdminDePartidos.actualizarTabla(partidos);

        } catch (SQLException ex) {
            Logger.getLogger(ControladorPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarEquipos(){
        
        
        try {
            List<Equipo> equipos;
            equipos = unEquipoDAOImpl.load(ConexionLmfa.crearConexion());
            
            if (equipos==null) {
                System.out.println("FUUUUUUUK");
            }            
            
            unPanelAdminDePartidos.cargarComboEquipos(equipos);
        
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public void mostrarPanel(){
        unPanelAdminDePartidos.setVisible(true);
    }
    
    public void ocultarPanel(){
        unPanelAdminDePartidos.setVisible(false);
    }

    @Override
    public void actualizar(String temporada, int noJornada, String equipolocal, String equipovisitante, Date fecha, Timestamp hora,
            int ml, int mv,byte partidoFinalizado) {

        try {
             
            int aux=0;
            
            if(partidoFinalizado==1){
                
                           
                
                RecorddeequipoKey keyLocal = new RecorddeequipoKey(temporada,equipolocal);
                RecorddeequipoKey keyVisitante = new RecorddeequipoKey(temporada,equipovisitante);

                Recorddeequipo recordEquipoLocal = new Recorddeequipo();
                Recorddeequipo recordEquipoVisitante = new Recorddeequipo();

                recordEquipoLocal = buscarRecordEquipo(keyLocal);
                recordEquipoVisitante = buscarRecordEquipo(keyVisitante);
                
                
                
                if(ml>mv){
                    aux = recordEquipoLocal.getPartidosganados();
                    recordEquipoLocal.setPartidosganados(aux+1);
                    unRecorddeequipoDAOImpl.update(recordEquipoLocal, conn.crearConexion());


                    aux = recordEquipoVisitante.getPartidosperdidos();
                    recordEquipoVisitante.setPartidosperdidos(aux+1);
                    unRecorddeequipoDAOImpl.update(recordEquipoVisitante, conn.crearConexion());
                
                 }else if(ml<mv){
                

                    aux = recordEquipoVisitante.getPartidosganados();
                    recordEquipoVisitante.setPartidosganados(aux+1);
                    unRecorddeequipoDAOImpl.update(recordEquipoVisitante, conn.crearConexion());


                    aux = recordEquipoLocal.getPartidosperdidos();
                    recordEquipoLocal.setPartidosperdidos(aux+1);
                    unRecorddeequipoDAOImpl.update(recordEquipoLocal, conn.crearConexion());


            
            
                 }else if(mv==ml){
                
                                
                    aux = recordEquipoVisitante.getPartidosempatados();
                    recordEquipoVisitante.setPartidosempatados(aux+1);
                    unRecorddeequipoDAOImpl.update(recordEquipoVisitante, conn.crearConexion());


                    aux = recordEquipoLocal.getPartidosempatados();
                    recordEquipoLocal.setPartidosempatados(aux+1);
                    unRecorddeequipoDAOImpl.update(recordEquipoLocal, conn.crearConexion());
                 }
                
            
            }
            
            unPartido = new Partido(temporada, noJornada, equipolocal, equipovisitante, fecha, hora,partidoFinalizado);
            unPartido.setMarcadorlocal(ml);
            unPartido.setMarcadorvisitante(mv);
            System.out.println("EL PARTIDAZO "+unPartido.toString());
           

            unPartidoDAOImpl.update(unPartido, conn.crearConexion());
            

            cargarTablaPartidos();
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public Recorddeequipo buscarRecordEquipo(RecorddeequipoKey key){
        
           
        Recorddeequipo recordEquipo;
        try {
            recordEquipo = unRecorddeequipoDAOImpl.load(key, conn.crearConexion());
            
                        
            if(recordEquipo!=null){
                
                               
                unRecordDeEquipo = unRecorddeequipoDAOImpl.load(key, conn.crearConexion());
                return unRecordDeEquipo;

                
            }else{
                recordEquipo = new Recorddeequipo();
                recordEquipo.setNombreequipo(key.getNombreequipo());
                recordEquipo.setTemporada(key.getTemporada());
                recordEquipo.setPartidosempatados(0);
                recordEquipo.setPartidosganados(0);
                recordEquipo.setPartidosperdidos(0);
                unRecorddeequipoDAOImpl.create(recordEquipo, conn.crearConexion());
                
                                
                unRecordDeEquipo = unRecorddeequipoDAOImpl.load(key, conn.crearConexion());
                return unRecordDeEquipo;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }

    @Override
    public void borrar(String temporada, int noJornada, String equipolocal, String equipovisitante) {
        
        
        try {
            PartidoKey pk= new PartidoKey(temporada, noJornada, equipolocal, equipovisitante);
            unPartidoDAOImpl.delete(pk, conn.crearConexion());
            cargarTablaPartidos();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public PanelAdminDePartidos getPanelAdminDeLiga(){
    
        
        return unPanelAdminDePartidos;

    }

    @Override
    public void agregar(String temporada, int noJornada, String equipolocal, String equipovisitante, Date fecha, Timestamp hora, byte partidoFinalizado) {
                
        try {
            
            System.out.println("PARTIDO FINALIZADO EN EL CONTROLADOR "+partidoFinalizado);
            
            SimpleDateFormat hcn = new SimpleDateFormat("hh:mm"); 
            unPartido = new Partido(temporada, noJornada, equipolocal, equipovisitante, fecha, hora,partidoFinalizado);
            System.out.println("OBJETO PARTIDO DESDE CONTROLADOR PARTIDO"+unPartido.toString());
            unPartidoDAOImpl.create(unPartido, conn.crearConexion());
            
            cargarTablaPartidos();
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void regresarAlPanelPadre() {
        
        unaInterfazLoginControl.cambiarPanel(unPanelAdminEquipo);
    }

    @Override
    public void irA() {
        
       // unaInterfazLoginControl.cambiarPanel(unControladorPersona.getPanelUsuariosDelSistema());
        
    }
    

    @Override
    public void asignarEspiaAPartido( String aliasEspia) {
    
        System.out.println("ALIAS "+aliasEspia);
        try {
            Object [] partido = new Object[5];
            partido = unPanelAdminDePartidos.retornarValoresDePartido();
            Date fecha = new Date(String.valueOf(partido[4]));
            Espiatienepartido e = new Espiatienepartido(aliasEspia,String.valueOf(partido[0]) ,Integer.valueOf(String.valueOf(partido[1])) ,
                    String.valueOf(partido[2]),String.valueOf(partido[3]) ,fecha );
            unEspiatienepartidoDAO.create(e, ConexionLmfaEspias.crearConexion());
            
            EspiasKey espiakey = new EspiasKey();
            espiakey.setAlias(aliasEspia);
            Espias unEspia = new Espias();
            unEspia = unEspiasDAOImpl.load(espiakey, ConexionEspias.crearConexion());
            byte i = 1;
            unEspia.setEstado(i);
            System.out.println("EL ESPIA"+unEspia.toString());
            unEspiasDAOImpl.update(unEspia, ConexionEspias.crearConexion());
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public void asignarEspiaAPartido() {

        try {
            List<Espias> losAlias = unEspiasDAOImpl.loadAliasEspiasInactivos(ConexionEspias.crearConexion());
            if(losAlias!=null)
                panelAsignarEspiaAPartido.actualizarTabla(losAlias);
            else 
                System.out.println("NO HAY ALIAS");
            
            unFrame.getContentPane().removeAll();
            unFrame.getContentPane().invalidate();
            
            unFrame.getContentPane().add(panelAsignarEspiaAPartido);
            unFrame.getContentPane().revalidate();
            unFrame.pack();
            
            
            
            unFrame.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        


        
    }
     
    @Override
    public void cogelarCampos(boolean congelar) {
        unPanelAdminDePartidos.congelarCampos(!congelar);
        if(!congelar){
            unFrame.setVisible(congelar);
        }
    }
   
    
    
    
        
    
}

