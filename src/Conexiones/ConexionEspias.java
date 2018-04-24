/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author manuel
 */
public class ConexionEspias {
    
     
    public static Connection crearConexion() {
		

        Connection myConn = null;
	
        try{
                    
                        //myConn = DriverManager.getConnection("jdbc:mysql://10.100.76.236/lmfa?autoReconnect=true&useSSL=false", "user1" , "12345678");
			//myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lmfa?autoReconnect=true&useSSL=false", "root" , "manolito130");
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/espias?autoReconnect=true&useSSL=false", "root" , "manolito130");
                        if (myConn!=null) {
                    return myConn;
            }

		}
		catch (Exception exc) {
				exc.printStackTrace();
			}
                
        return null;
			
		}

    public static void cerrarConexion(Connection conn) throws SQLException{
        
        conn.close();
        
    }

}
