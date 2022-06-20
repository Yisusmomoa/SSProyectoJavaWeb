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
import models.Materia;
import utilis.DbConnection;

/**
 *
 * @author bjls2
 */
public class MateriaDAO {
    
    /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `addMateria`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `addMateria` (
IN `pnombreMateria` varchar(255)
)
BEGIN
INSERT INTO `proyectojavawebss`.`materia`(`nombreMateria`)
VALUES(pnombreMateria);

END$$

DELIMITER ;


     
     */
    
    public static int addMateria(Materia materia){
         Connection con;
        try {
            con=DbConnection.getConnection();
            String sql="Call addMateria(?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setString(1, materia.getNombreMateria());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
            USE `proyectojavawebss`;
       DROP procedure IF EXISTS `getMaterias`;

       DELIMITER $$
       USE `proyectojavawebss`$$
       CREATE PROCEDURE `getMaterias` ()
       BEGIN
       SELECT `materia`.`claveMateria`,
           `materia`.`nombreMateria`,
           `materia`.`estatus`
       FROM `proyectojavawebss`.`materia`;

       END$$

       DELIMITER ;
     */
    public static List<Materia> getMaterias() throws SQLException{
        List<Materia> listaMaterias=new ArrayList<>();
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call getMaterias()";
            CallableStatement statement=con.prepareCall(sql);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                int claveMateria=resultSet.getInt("claveMateria");
                String nombreMateria=resultSet.getString("nombreMateria");
                Boolean estatus=resultSet.getBoolean("estatus");
                listaMaterias.add(new Materia(claveMateria,nombreMateria,estatus));
            }
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();
        }
        return listaMaterias;
    }
    
    /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `getMateriaByID`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `getMateriaByID` (
IN `pclaveMateria` int 
)
BEGIN
SELECT `materia`.`claveMateria`,
    `materia`.`nombreMateria`,
    `materia`.`estatus`
FROM `proyectojavawebss`.`materia`
WHERE claveMateria=pclaveMateria;

END$$

DELIMITER ;


     */
    
    public static Materia getMateriaByID(int claveMateriaGrupo){
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="call getMateriaByID(?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setInt(1, claveMateriaGrupo);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                int claveMateria=resultSet.getInt("claveMateria");
                String nombreMateria=resultSet.getString("nombreMateria");
                Boolean estatus=resultSet.getBoolean("estatus");
                return new Materia(claveMateria,nombreMateria,estatus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
