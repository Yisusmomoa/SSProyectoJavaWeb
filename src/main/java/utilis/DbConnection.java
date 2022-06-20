/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilis;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author bjls2
 */
public class DbConnection {
      // Creamos el objeto conexion
    private static final BasicDataSource dataSource = new BasicDataSource();

    // Inicializamos la conexion
    static {
        // Tipo de Driver, este es el de mysql
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        // La URL a la conexion, especificando que es de mysql
        // la ruta que es localhost puerto 3306
        // el nombre de la base de datos que es pwDB
        // lo demas son parametros para que no tengan problemas con zonas horarias
        dataSource.setUrl("jdbc:mysql://localhost:3306/proyectojavawebss?useUnicode=true&useJDBCCompliantTimeZoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        // El nombre de usuario de su conexion
        dataSource.setUsername("root");
        // La contraseña del usuario de su conexion
        dataSource.setPassword("");
        // dataSource.setMaxIdle(0);
        // dataSource.setMaxActive(100);
    }

    /**
     * *
     * Metodo para obtener la conexion
     *
     * @return Conexion a Base de datos
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Metodo para cerrar la conexion
     *
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        dataSource.close();
    }
}
