/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author manuel
 */
public interface InterfazAdministrador {
    
    public void cargarProximasFechas(String temporada,String equipo);
    public void cargarTablaDePosiciones(String temporada);
    public void cargarTablaDeResultados(String temporada,String equipo);
    public void irA(int panel);
    public void regresarAlPanelPadre();
    
}
