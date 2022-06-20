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
import models.Grupo;
import models.Materia;
import utilis.DbConnection;

/**
 *
 * @author bjls2
 */
public class grupoDAO {
    
    /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `addGrupo`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `addGrupo` (
IN `pclaveMateriaGrupo` int,
IN `pnumAlumnos` int
)
BEGIN
INSERT INTO `proyectojavawebss`.`grupo`(`claveMateriaGrupo`,`numAlumnos`)
VALUES(pclaveMateriaGrupo,pnumAlumnos);

END$$

DELIMITER ;
     */
    
    
    public static int addGrupo(Grupo grupo) throws SQLException{
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call addGrupo(?,?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setInt(1, grupo.getClaveMateriaGrupo());
            statement.setInt(2, grupo.getNumAlumnos());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();
        }
        
        return 0;
    }
    
    /**
     USE `proyectojavawebss`;
    DROP procedure IF EXISTS `getGrupos`;

    DELIMITER $$
    USE `proyectojavawebss`$$
    CREATE PROCEDURE `getGrupos` ()
    BEGIN
    SELECT `grupo`.`idGrupo`,
        `grupo`.`claveMateriaGrupo`,
        `grupo`.`numAlumnos`
    FROM `proyectojavawebss`.`grupo`;

    END$$

    DELIMITER ;
     */
    
    public static List<Grupo> getGrupos() throws SQLException{
        List<Grupo> listaGrupos=new ArrayList<>();
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call getGrupos()";
            CallableStatement statement=con.prepareCall(sql);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                int idGrupo=resultSet.getInt("idGrupo");
                int claveMateriaGrupo=resultSet.getInt("claveMateriaGrupo");
                int numAlumnos=resultSet.getInt("numAlumnos");
                Materia materia=MateriaDAO.getMateriaByID(claveMateriaGrupo);
                listaGrupos.add(new Grupo(idGrupo,numAlumnos,materia));
            }
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();
        }
        return listaGrupos;
    }
    
}
