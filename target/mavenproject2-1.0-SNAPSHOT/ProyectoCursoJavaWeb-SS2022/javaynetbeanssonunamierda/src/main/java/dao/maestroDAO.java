/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Maestro;
import utilis.DbConnection;

/**
 *
 * @author bjls2
 */
public class maestroDAO {
    
    /***
    USE `proyectojavawebss`;
DROP procedure IF EXISTS `logInMaestro`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `logInMaestro` (
IN `pusuario` varchar(255),
IN `pcontraseña` varchar(255)
)
BEGIN
SELECT `maestro`.`noEmpleado`,
    `maestro`.`nombreMaestro`,
    `maestro`.`usuario`,
    `maestro`.`tipo`,
    `maestro`.`estatus`
FROM `proyectojavawebss`.`maestro`
WHERE pusuario=usuario AND pcontraseña=contraseña;

END$$

DELIMITER ;


    
    */
    
    public static Maestro logInMaestro(Maestro maestro){
         Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="call logInMaestro(?,?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setString(1, maestro.getUsuario());
            statement.setString(2, maestro.getContraseña());
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                int noEmpleado=resultSet.getInt("noEmpleado");
                String nombreMaestro=resultSet.getString("nombreMaestro");
                String usuario=resultSet.getString("usuario");
                String tipo=resultSet.getString("tipo");
                Boolean estatus=resultSet.getBoolean("estatus");
                return new Maestro(noEmpleado,
                        nombreMaestro,usuario,tipo,estatus);
            }
        }
        catch (SQLException ex) {
            System.out.println("ERROR: "+ex.getMessage());
        }
        return null;
    }
    
    /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `addMaestro`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `addMaestro` (
IN `pnombreMaestro` varchar(255),
IN `pusuario` varchar(255),
IN `pcontraseña` varchar(255),
IN `ptipo` varchar(255)
)
BEGIN
INSERT INTO `proyectojavawebss`.`maestro`(`nombreMaestro`,`usuario`,`contraseña`,`tipo`)
VALUES(pnombreMaestro,pusuario,pcontraseña,ptipo);

END$$

DELIMITER ;
     */
    
    public static int addMaestro(Maestro maestro){
        Connection con;
        try {
            con=DbConnection.getConnection();
            String sql="CALL addMaestro(?,?,?,?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setString(1, maestro.getNombreMaestro());
            statement.setString(2, maestro.getUsuario());
            statement.setString(3, maestro.getContraseña());
            statement.setString(4, maestro.getTipo());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(maestroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `getMaestros`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `getMaestros` ()
BEGIN
SELECT `maestro`.`noEmpleado`,
    `maestro`.`nombreMaestro`,
    `maestro`.`usuario`,
    `maestro`.`contraseña`,
    `maestro`.`tipo`,
    `maestro`.`estatus`
FROM `proyectojavawebss`.`maestro`;

END$$

DELIMITER ;   
     */
    
    public static List<Maestro> getMaestros() throws SQLException{
        List<Maestro> listaMaestros=new ArrayList<>();
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call getMaestros()";
            CallableStatement statement=con.prepareCall(sql);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                int noEmpleado=resultSet.getInt("noEmpleado");
                String nombreMaestro=resultSet.getString("nombreMaestro");
                String usuario=resultSet.getString("usuario");
                String tipo=resultSet.getString("tipo");
                Boolean estatus=resultSet.getBoolean("estatus");
                listaMaestros.add(new Maestro(noEmpleado,nombreMaestro,
                    usuario,tipo,estatus));
            }
        } catch (SQLException ex) {
            Logger.getLogger(maestroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();
        }
        
        return listaMaestros;
    }
}
