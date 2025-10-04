package Conexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.nio.file.Path;
import java.nio.file.Paths;

// Clase que se encarga de crear la conexión con la base de datos
public class Conexion{
    
    // Objeto Properties donde se guardan las configuraciones de conexión
    static Properties config = new Properties();
    
    // Variables que almacenarán los valores leídos desde el archivo de configuración
    String hostname = null;
    String port = null;
    String database = null;
    String username = null;
    String password = null;
    String jndi  = null;   // (no se usa en este ejemplo, pero podría servir si usan JNDI)

    // Constructor de la clase: se ejecuta al crear un objeto CreateConection
    public Conexion(){
        // Ruta absoluta del archivo con los parámetros de conexión
        String path = "C:\\Users\\TDFM\\Documents\\MarinasRestaurant\\src\\Conexion\\db_config.properties";
        InputStream in = null;
        try {
            // Abre el archivo y lo carga dentro del objeto Properties
            in = Files.newInputStream(Paths.get(path));
            config.load(in);
            in.close();
            
        } catch (IOException ex) {
            // Si ocurre un error al leer el archivo, lo imprime en consola
            ex.printStackTrace();
        } finally {
            // Asegura que el archivo se cierre aunque ocurra un error
            try {
                in.close();
            } catch (IOException ex ) {
                ex.printStackTrace();
            }
        }
        // Una vez cargadas las propiedades, se asignan a las variables de la clase
        loadProperties();
    }

    // Método que asigna los valores del archivo de configuración a las variables de la clase
    public void loadProperties(){
        hostname = config.getProperty("hostname");
        port  = config.getProperty("port");
        database = config.getProperty("database");
        username = config.getProperty("username");
        password = config.getProperty("password");
    }
    
    // Método que crea la conexión a la base de datos PostgreSQL
    public Connection getConexion() throws SQLException {
        Connection conn = null;
        
        // Construcción de la URL de conexión con los parámetros cargados
        String jdbcUrl = "jdbc:postgresql://" + this.hostname + ":" +
                         this.port + "/" + this.database;
        
        // Se conecta a la BD usando DriverManager y las credenciales
        conn = DriverManager.getConnection(jdbcUrl, username, password);
        
        // Mensaje de confirmación
        System.out.println("Conexion establecida");
        
        // Retorna el objeto Connection para poder usarlo en consultas SQL
        return conn;
    }   
}