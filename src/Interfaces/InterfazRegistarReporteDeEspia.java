/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.Date;

/**
 *
 * @author manuel
 */
public interface InterfazRegistarReporteDeEspia {
    
    public void guardarReporte(String temporada,String noJornada,String equipoLocal,String equipoVisitante,Date fecha,String reporte);
        
    
        
    public void irA(int panel);
    public void regresarAlPanelPadre();
}
