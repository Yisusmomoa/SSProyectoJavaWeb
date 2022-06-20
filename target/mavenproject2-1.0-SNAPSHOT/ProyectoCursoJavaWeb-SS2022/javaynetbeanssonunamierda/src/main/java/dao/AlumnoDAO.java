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
import models.Alumno;
import models.Grupo;
import utilis.DbConnection;

/**
 *
 * @author bjls2
 */
public class AlumnoDAO {
    
    /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `addAlumno`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `addAlumno` (
IN `pusuario` varchar(255),
IN `pnombre` varchar(255),
IN `pcontraseña` varchar(255)

)
BEGIN
INSERT INTO `proyectojavawebss`.`alumno`(`usuario`,`nombre`,`contraseña`)
VALUES(pusuario,pnombre,pcontraseña);

END$$

DELIMITER ;

     */
    public static int addAlumno(Alumno alumno){
        Connection con;
        try {
            con=DbConnection.getConnection();
            String sql="CALL addAlumno(?,?,?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setString(1, alumno.getUsuario());
            statement.setString(2, alumno.getNombre());
            statement.setString(3, alumno.getContraseña());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `getAlumnos`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `getAlumnos` ()
BEGIN
SELECT `alumno`.`matricula`,
    `alumno`.`usuario`,
    `alumno`.`nombre`,
    `alumno`.`contraseña`,
    `alumno`.`estatus`
FROM `proyectojavawebss`.`alumno`;

END$$

DELIMITER ;

     */
    
    
    public static List<Alumno> getAlumnos() throws SQLException{
        List<Alumno> listaAlumnos=new ArrayList<>();
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="Call getAlumnos()";
            CallableStatement statement=con.prepareCall(sql);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                int matricula=resultSet.getInt("matricula");
                String usuario=resultSet.getString("usuario");
                String nombre=resultSet.getString("nombre");
                Boolean estatus=resultSet.getBoolean("estatus");
                listaAlumnos.add(new Alumno(matricula,usuario,
                        nombre, estatus));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            con.close();
        }
        return listaAlumnos;
    }
    
    /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `logInEstudiante`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `logInEstudiante` (
IN `pusuario` varchar(255),
IN `pcontraseña` varchar(255) 
)
BEGIN
SELECT `alumno`.`matricula`,
    `alumno`.`usuario`,
    `alumno`.`nombre`,
    `alumno`.`estatus`
FROM `proyectojavawebss`.`alumno`
WHERE pusuario=usuario AND pcontraseña=contraseña;

END$$

DELIMITER ;
     */
    
    public static Alumno logInEstudiante(Alumno alumno){
        Connection con = null;
        try {
            con=DbConnection.getConnection();
            String sql="call logInEstudiante(?,?)";
            CallableStatement statement=con.prepareCall(sql);
            statement.setString(1, alumno.getUsuario());
            statement.setString(2, alumno.getContraseña());
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                int matricula=resultSet.getInt("matricula");
                String usuario=resultSet.getString("usuario");
                String nombre=resultSet.getString("nombre");
                Boolean estatus=resultSet.getBoolean("estatus");
                return new Alumno(matricula,usuario,nombre,estatus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    /**
     USE `proyectojavawebss`;
DROP procedure IF EXISTS `inscribirMateria`;

DELIMITER $$
USE `proyectojavawebss`$$
CREATE PROCEDURE `inscribirMateria` (
IN `pidGrupoInscrito` int,
IN `pidAlumnoInscrito` int 
)
BEGIN
INSERT INTO `proyectojavawebss`.`materiasinscritas`
(`idGrupoInscrito`,
`idAlumnoInscrito`)
VALUES
(pidGrupoInscrito,pidAlumnoInscrito);

END$$

DELIMITER ;


     
     */
    
    
    public static int inscribirMateria(Grupo grupo, Alumno alumno){
         Connection con;
        try {
            con=DbConnection.getConnection();
            String sql="CALL inscribirMateria(?,?)";
            CallableStatement statement=con.prepareCall(sql);
             statement.setInt(1, grupo.getMateria().getClaveMateria());
              statement.setInt(2,alumno.getMatricula());
             return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return 0;
    }
    
    
    
    
    
    
    
}
