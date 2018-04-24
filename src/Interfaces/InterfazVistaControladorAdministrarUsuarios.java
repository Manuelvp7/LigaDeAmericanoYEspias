/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import java.sql.Date;

/**
 *
 * @author manuel
 */
public interface InterfazVistaControladorAdministrarUsuarios {
    
    public void agregar(String userName,String Password);
    public void borrar(String username);
    public void actualizar(String userName,String Password);
    public void cargarTablaUsuarios();
    public void regresar();
}
