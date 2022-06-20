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
import models.Materia;
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
    
    /**     USE `proyectojavawebss`;
DROP procedure IF EXISTS `getBusquedaMaestro`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `getBusquedaMaestro` (
IN `pnombreMaestro` varchar(255)
)
BEGIN
	SELECT `maestro`.`noEmpleado`,
    `maestro`.`nombreMaestro`,
    `maestro`.`usuario`,
    `maestro`.`contraseña`,
    `maestro`.`tipo`,
    `maestro`.`estatus`
FROM `proyectojavawebss`.`maestro`
WHERE nombreMaestro LIKE CONCAT('%',pnombreMaestro,'%');

END$$

DELIMITER ;
*/
    
    public static List<Maestro> getBusquedaMaestro(String inputBusqueda) throws SQLException {
        List<Maestro> listaMaestros=new ArrayList<>();
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call getBusquedaMaestro(?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setString(1,inputBusqueda );
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
    
    /**
     USE `proyectojavawebss`;
    DROP procedure IF EXISTS `eliminarMaestro`;

    DELIMITER $$
    USE `proyectojavawebss`$$
    CREATE PROCEDURE `eliminarMaestro` (
    IN `pnoEmpleado` int
    )
    BEGIN
    DELETE FROM `proyectojavawebss`.`maestro`
    WHERE pnoEmpleado=noEmpleado;

    END$$

    DELIMITER ;
     */
    
    public static int eliminarMaestro(int idMaestro) throws SQLException{
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call eliminarMaestro(?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setInt(1, idMaestro);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(maestroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();
        }
        
        return 0;
    }
    
    /**
     USE `proyectojavawebss`;
        DROP procedure IF EXISTS `editarMaestro`;

        DELIMITER $$
        USE `proyectojavawebss`$$
        CREATE PROCEDURE `editarMaestro` (
        IN `pnoEmpleado` int,
        IN `pnombreMaestro` varchar(255),
        IN `pusuario` varchar(255),
        IN `pcontraseña` varchar(255),
        IN `pestatus` boolean
        )
        BEGIN
        declare contraseñaAux varchar(255);
        select contraseña into contraseñaAux from `proyectojavawebss`.`maestro` WHERE `noEmpleado` = pnoEmpleado;
        IF contraseñaAux=pcontraseña THEN
                UPDATE `proyectojavawebss`.`maestro`
                SET
                `nombreMaestro` = pnombreMaestro,
                `usuario` = pusuario,
                `estatus` = pestatus
                WHERE `noEmpleado` = pnoEmpleado;
        ELSE 
                UPDATE `proyectojavawebss`.`maestro`
                SET
                `nombreMaestro` = pnombreMaestro,
                `usuario` = pusuario,
                `contraseña` = pcontraseña,
                `estatus` = pestatus
                WHERE `noEmpleado` = pnoEmpleado;
        END IF;


        END$$

        DELIMITER ;
     */
    
     /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `editarMaestro2`;

USE `proyectojavawebss`;
DROP procedure IF EXISTS `proyectojavawebss`.`editarMaestro2`;
;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `editarMaestro2`(
IN `pnoEmpleado` int,
IN `pnombreMaestro` varchar(255),
IN `pusuario` varchar(255),
IN `pcontraseña` varchar(255),
IN `pestatus` boolean)
BEGIN
IF pcontraseña="" THEN
	UPDATE `proyectojavawebss`.`maestro`
	SET
	`nombreMaestro` = pnombreMaestro,
	`usuario` = pusuario,
	`estatus` = pestatus
	WHERE `noEmpleado` = pnoEmpleado;
ELSE 
    UPDATE `proyectojavawebss`.`maestro`
	SET
	`nombreMaestro` = pnombreMaestro,
	`usuario` = pusuario,
	`contraseña` = pcontraseña,
	`estatus` = pestatus
	WHERE `noEmpleado` = pnoEmpleado;
END IF;
END$$

DELIMITER ;
;     
     */
    
    
    public static int editarMaestro (Maestro maestro) throws SQLException{
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="CALL editarMaestro2(?,?,?,?,?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setInt(1, maestro.getNoEmpleado());
            statement.setString(2, maestro.getNombreMaestro());
            statement.setString(3, maestro.getUsuario());
            statement.setString(4, maestro.getContraseña());
            statement.setBoolean(5, maestro.isEstatus());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(maestroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();
        }
        return 0;
    }
    
    /**
     USE `proyectojavawebss`;
    DROP procedure IF EXISTS `getMaestroById`;

    DELIMITER $$
    USE `proyectojavawebss`$$
    CREATE PROCEDURE `getMaestroById` (
    IN `pnoEmpleado` int
    )
    BEGIN
    SELECT `maestro`.`noEmpleado`,
        `maestro`.`nombreMaestro`,
        `maestro`.`usuario`,
        `maestro`.`tipo`,
        `maestro`.`estatus`
    FROM `proyectojavawebss`.`maestro`
    WHERE noEmpleado=pnoEmpleado;

    END$$

    DELIMITER ;
     */
    
    public static Maestro getMaestroById(int idMaestro) throws SQLException{
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="call getMaestroById(?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setInt(1,idMaestro);
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
        } catch (SQLException ex) {
            Logger.getLogger(maestroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();    
        }
        
        return null;
    }
    
   
    
}
