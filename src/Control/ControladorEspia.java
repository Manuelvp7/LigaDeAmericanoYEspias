/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Conexiones.ConexionEspias;
import Conexiones.ConexionLmfaEspias;
import DAOImpl.EspiasDAOImpl;
import DAOImpl.EspiatienepartidoDAOImpl;
import Interfaces.InterfazLoginControl;
import Modelo.Espias;
import Modelo.Espiatienepartido;
import Vista.PanelEspia;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author manuel
 */
public class ControladorEspia implements Interfaces.InterfazRegistarReporteDeEspia{
    
    private PanelEspia unPanelEspia;
    private EspiatienepartidoDAOImpl unEspiatienepartidoDAOImpl;
    private Interfaces.InterfazLoginControl unaInterfazLoginControl;
    private String alias;
    private EspiasDAOImpl unEspiasDAOImpl;

    public ControladorEspia() {
    }

    public ControladorEspia(InterfazLoginControl unaInterfazLoginControl,String alias) {
        unPanelEspia = new PanelEspia(this);
        unEspiatienepartidoDAOImpl = new EspiatienepartidoDAOImpl();
        this.unaInterfazLoginControl = unaInterfazLoginControl;
        this.alias = alias;
        unPanelEspia.cargarLabels(cargarReportePendiente());
        unEspiasDAOImpl = new EspiasDAOImpl();
    }
    
    



    @Override
    public void irA(int panel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void regresarAlPanelPadre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PanelEspia getUnPanelEspia() {
        return unPanelEspia;
    }
    
    public Espiatienepartido cargarReportePendiente(){
        
        try {
            Espiatienepartido unEspia = unEspiatienepartidoDAOImpl.load(alias, ConexionLmfaEspias.crearConexion());
            
            return unEspia;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEspia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    @Override
    public void guardarReporte(String temporada, String noJornada, String equipoLocal, String equipoVisitante, java.util.Date fecha, String reporte) {
                
        try {
    
            Espiatienepartido unEspiatienepartido = new Espiatienepartido(alias,temporada, Integer.parseInt(noJornada), equipoLocal, equipoVisitante, fecha);
            unEspiatienepartido.setReporte(reporte);
            
            System.out.println("EL REPORTE DE ESPIA"+unEspiatienepartido.toString());
            
            unEspiatienepartidoDAOImpl.update(unEspiatienepartido, ConexionLmfaEspias.crearConexion());
            
            unEspiasDAOImpl.update(alias, ConexionEspias.crearConexion());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorEspia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    
    
    
}
