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

USE `proyectojavawebss`;
DROP procedure IF EXISTS `proyectojavawebss`.`getGrupos`;
;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getGrupos`()
BEGIN
SELECT `grupo`.`idGrupo`,
    `grupo`.`claveMateriaGrupo`,
    `grupo`.`numAlumnos`,
    `grupo`.`estatus`
FROM `proyectojavawebss`.`grupo`;


END$$

DELIMITER ;
;


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
                Boolean estatus=resultSet.getBoolean("estatus");
                Materia materia=MateriaDAO.getMateriaByID(claveMateriaGrupo);
                listaGrupos.add(new Grupo(idGrupo,claveMateriaGrupo,numAlumnos,
                estatus,materia));
            }
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();
        }
        return listaGrupos;
    }
    
    /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `getBusquedaGrupos`;

USE `proyectojavawebss`;
DROP procedure IF EXISTS `proyectojavawebss`.`getBusquedaGrupos`;
;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getBusquedaGrupos`(
IN `pclaveMateriaGrupo` int
)
BEGIN
SELECT `grupo`.`idGrupo`,
    `grupo`.`claveMateriaGrupo`,
    `grupo`.`numAlumnos`,
    `grupo`.`estatus`
FROM `proyectojavawebss`.`grupo`;


END$$

DELIMITER ;
;




     */
    
    public static List<Grupo> getBusquedaGrupos(String inputBusqueda) throws SQLException{
        List<Grupo> listaGrupos=new ArrayList<>();
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call getBusquedaGrupos(?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setInt(1, Integer.parseInt(inputBusqueda));
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                int idGrupo=resultSet.getInt("idGrupo");
                int claveMateriaGrupo=resultSet.getInt("claveMateriaGrupo");
                int numAlumnos=resultSet.getInt("numAlumnos");
                Boolean estatus=resultSet.getBoolean("estatus");
                Materia materia=MateriaDAO.getMateriaByID(claveMateriaGrupo);
                listaGrupos.add(new Grupo(idGrupo,claveMateriaGrupo,numAlumnos,
                estatus,materia));
            }
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();
        }
        return listaGrupos;
    }
    /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `inscribirMateria`;

USE `proyectojavawebss`;
DROP procedure IF EXISTS `proyectojavawebss`.`inscribirMateria`;
;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `inscribirMateria`(
IN `pidGrupoInscrito` int,
IN `pidAlumnoInscrito` int 
)
BEGIN
IF (SELECT EXISTS(SELECT * FROM materiasinscritas 
		WHERE idAlumnoInscrito = pidAlumnoInscrito AND idGrupoInscrito = pidGrupoInscrito)=0)
THEN
	INSERT INTO `proyectojavawebss`.`materiasinscritas`
	(`idGrupoInscrito`,
	`idAlumnoInscrito`)
	VALUES
	(pidGrupoInscrito,pidAlumnoInscrito);
END IF;



END$$

DELIMITER ;
;


     
     */
    public static int inscribirMateria(int idGrupo, int matricula) throws SQLException{
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call getMateriaInscritaExists(?,?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setInt(1, idGrupo);
            statement.setInt(2, matricula);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()) {
                return -1;
            }
            else{
                sql="Call inscribirMateria(?,?)";
                statement=con.prepareCall(sql);
                statement.setInt(1, idGrupo);
                statement.setInt(2, matricula);
                return statement.executeUpdate();
            }
            
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
    DROP procedure IF EXISTS `darBajaMateria`;

    DELIMITER $$
    USE `proyectojavawebss`$$
    CREATE PROCEDURE `darBajaMateria` (
    IN `pidGrupoInscrito` int,
    IN `pidAlumnoInscrito` int 
    )
    BEGIN
    DELETE FROM `proyectojavawebss`.`materiasinscritas`
    WHERE idGrupoInscrito=pidGrupoInscrito AND idAlumnoInscrito=pidAlumnoInscrito;

    END$$

    DELIMITER ;

     */
    
    public static int darBajaMateria(int idGrupo, int matricula) throws SQLException{
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call darBajaMateria(?,?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setInt(1, idGrupo);
            statement.setInt(2, matricula);
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
DROP procedure IF EXISTS `eliminarGrupo`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `eliminarGrupo` (
IN `pidGrupo` int
)
BEGIN
	UPDATE `proyectojavawebss`.`grupo`
	SET
	`estatus` = 0
	WHERE `idGrupo` = pidGrupo;

END$$

DELIMITER ;


     */
    
    public static int eliminarGrupo(int idGrupo) throws SQLException{
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call eliminarGrupo(?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setInt(1, idGrupo);
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
    DROP procedure IF EXISTS `getGrupoByID`;

    DELIMITER $$
    USE `proyectojavawebss`$$
    CREATE PROCEDURE `getGrupoByID` (
    IN `pidGrupo` int
    )
    BEGIN
    SELECT `grupo`.`idGrupo`,
        `grupo`.`claveMateriaGrupo`,
        `grupo`.`numAlumnos`,
        `grupo`.`estatus`
    FROM `proyectojavawebss`.`grupo`
    WHERE pidGrupo=idGrupo;

    END$$

    DELIMITER ;
     */
    
    public static Grupo getGrupoByID(int pidGrupo) throws SQLException{
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call getGrupoByID(?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setInt(1, pidGrupo);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                int idGrupo=resultSet.getInt("idGrupo");
                int claveMateriaGrupo=resultSet.getInt("claveMateriaGrupo");
                int numAlumnos=resultSet.getInt("numAlumnos");
                Boolean estatus=resultSet.getBoolean("estatus");
                Materia materia=MateriaDAO.getMateriaByID(claveMateriaGrupo);
                return new Grupo(idGrupo, claveMateriaGrupo,
                        numAlumnos, estatus, materia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();
        } 
        return null;
    }
    
    
    /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `editarGrupo`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `editarGrupo` (
IN `pidGrupo` int,
IN `pclaveMateriaGrupo` int,
IN `pnumAlumnos` int,
IN `pestatus` boolean
)
BEGIN
UPDATE `proyectojavawebss`.`grupo`
SET
`claveMateriaGrupo` = pclaveMateriaGrupo,
`numAlumnos` = pnumAlumnos,
`estatus` = pestatus
WHERE `idGrupo` = pidGrupo;

END$$

DELIMITER ;     
     */
    
    public static int editarGrupo(Grupo grupo) throws SQLException{
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call editarGrupo(?,?,?,?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setInt(1, grupo.getIdGrupo());
            statement.setInt(2, grupo.getClaveMateriaGrupo());
            statement.setInt(3, grupo.getNumAlumnos());
            statement.setBoolean(4, grupo.isEstatus());
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
    DROP procedure IF EXISTS `getMateriasByAlumno`;

    USE `proyectojavawebss`;
    DROP procedure IF EXISTS `proyectojavawebss`.`getMateriasByAlumno`;
    ;

    DELIMITER $$
    USE `proyectojavawebss`$$
    CREATE DEFINER=`root`@`localhost` PROCEDURE `getMateriasByAlumno`(
    IN `pmatricula` int
    )
    BEGIN

    select idGrupo, m.claveMateria, nombreMateria, 
    g.estatus as 'estatus grupo' from `proyectojavawebss`.grupo g
    join `proyectojavawebss`.materiasinscritas mi on mi.idGrupoInscrito=g.idGrupo
    join `proyectojavawebss`.materia m on m.claveMateria=g.claveMateriaGrupo
    where mi.idAlumnoInscrito=pmatricula;


    END$$

    DELIMITER ;
    ;

     
     */
    
    public static List<Grupo> getMateriasByAlumno(int pmatricula) throws SQLException{
        List<Grupo> listaGrupos =new ArrayList<>();
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call getMateriasByAlumno(?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setInt(1, pmatricula);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                int idGrupo=resultSet.getInt("idGrupo");
                int claveMateria=resultSet.getInt("claveMateria");
                String nombreMateria=resultSet.getString("nombreMateria");
                Boolean estatusGrupo=resultSet.getBoolean("estatus grupo");
                Materia materia=MateriaDAO.getMateriaByID(claveMateria);
                listaGrupos.add(new Grupo(idGrupo,estatusGrupo,materia));
            }
            return listaGrupos;
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();
        }
            
        return listaGrupos;
    }
    
    
    
    
}
