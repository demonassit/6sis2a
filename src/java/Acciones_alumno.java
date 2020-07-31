/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author demon
 */

import java.util.*;
import java.sql.*;

/*
esta clase nos va a representar todas las operaciones que se pueden 
realizar con el alumno, dentro del programa, para poder establecer sus metodos y/o
operaciones conforme al encapsulamiento
*/

public class Acciones_alumno {
    
    //una clase conexion
    public static Connection getConnection(){
        String url, user, password;
        //establecemos la ruta donde esta la bd
        url = "jdbc:mysql:3306//localhost/Alumnos";
        user = "root";
        password = "n0m3l0";
        
        //creo un objeto de conexion
        Connection con = null;
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            //establezco la conexion
            url="jdbc:mysql://localhost/Alumnos";
            con = DriverManager.getConnection(url, user, password);
            
            //para responder si se conecto
            System.out.println("Si conecto a la BD");
        
        }catch(Exception e){
            System.out.println("No conecto a la BD");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        
        }
        
        return con;
    }
    
    //guardar un nuevo alumno
    
    public static int Guardar_alumno(Alumno a){
        int estatus = 0;
        
        try{
            
            //crear primero la conexion
            Connection con = Acciones_alumno.getConnection();
            //creo querry
            String q = "insert into Alumnos (nom_alu, pass_alu, email_alu, pais_alu)"
                    + " values (?,?,?,?)";
            
            //preparamos la sentencia
            PreparedStatement ps = con.prepareStatement(q);
            //obtenemos los elementos de la tabla alumno get y set
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getPais());
            
            //ejecuto la querry
            estatus = ps.executeUpdate();
            
            //cerramos conexion
            con.close();
            
            System.out.println("Registro Exitoso");
            
        
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        
        }
        
        return estatus;
    }
    
    
    //editar los datos del alumno
    
    public static int Actualizar_alumno(Alumno a){
        int estatus = 0;
        
        try{
            
            //crear primero la conexion
            Connection con = Acciones_alumno.getConnection();
            //creo querry
            String q = "update Alumnos set nom_alu = ?,"
                    + " pass_alu = ?,"
                    + " email_alu = ?,"
                    + " pais_alu = ? "
                    + " where id_alu = ?";
            
            //preparamos la sentencia
            PreparedStatement ps = con.prepareStatement(q);
            //obtenemos los elementos de la tabla alumno get y set
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getPais());
            ps.setInt(5, a.getId());
            
            //ejecuto la querry
            estatus = ps.executeUpdate();
            
            //cerramos conexion
            con.close();
            
            System.out.println("Actualizacion Exitosa");
            
        
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        
        }
        
        return estatus;
    }
    
    //borrar un alumno
    
    public static int Borrar_alumno(int id){
        int estatus = 0;
        
        try{
            
            //crear primero la conexion
            Connection con = Acciones_alumno.getConnection();
            //creo querry
            String q = "delete from Alumnos where id_alu = ?";
            //si fuera procedimiento almacenado    q = "call borraralu(?)"
            
            //preparamos la sentencia
            PreparedStatement ps = con.prepareStatement(q);
            //obtenemos los elementos de la tabla alumno get y set
            ps.setInt(1, id);
            
            //ejecuto la querry
            estatus = ps.executeUpdate();
            
            //cerramos conexion
            con.close();
            
            System.out.println("Eliminacion Exitosa");
            
        
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        
        }
        
        return estatus;
    }
    
    //consultar un alumno por id
    
    public static Alumno getAlumnoById(int id){
        Alumno a = new Alumno();
        
        try{
            
            //crear primero la conexion
            Connection con = Acciones_alumno.getConnection();
            //creo querry
            String q = "select * from Alumnos where id_alu = ?";
            
            //preparamos la sentencia
            PreparedStatement ps = con.prepareStatement(q);
            //obtenemos los elementos de la tabla alumno get y set
            ps.setInt(1, id);
            
            //ejecutar la consulta de la querry
            ResultSet rs = ps.executeQuery();
            
            //buscar el alumno por id
            if(rs.next()){
                //obtener los elementos de la tabla a partir de mi objeto
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setPassword(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setPais(rs.getString(5));
            }
            
            
            //cerramos conexion
            con.close();
            
            System.out.println("Busqueda Exitosa");
            
        
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        
        }
        
        return a;
    }
    
    //consulta general de alumnos
    
    public static List<Alumno> getAllAlumnos(){
        //lista a partir de una array
        List<Alumno> lista = new ArrayList<Alumno>();
        
        try{
            
            //crear primero la conexion
            Connection con = Acciones_alumno.getConnection();
            //creo querry
            String q = "select * from Alumnos";
            
            //preparamos la sentencia
            PreparedStatement ps = con.prepareStatement(q);
            //obtenemos los elementos de la tabla alumno get y set
           
            
            //ejecutar la consulta de la querry
            ResultSet rs = ps.executeQuery();
            
            //obtener toda la lista de la tabla
            while(rs.next()){
                Alumno a = new Alumno();
                //obtener los elementos de la tabla a partir de mi objeto
                
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setPassword(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setPais(rs.getString(5));
                lista.add(a);
            }
            
            
            //cerramos conexion
            con.close();
            
            System.out.println("Busqueda Exitosa");
            
        
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        
        }
        
        return lista;
    }
    
}
