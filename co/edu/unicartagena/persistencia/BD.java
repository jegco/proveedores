/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.unicartagena.persistencia;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Estudiante
 */
public class BD {
    
    // atributos de la clase
        String driver, url, login, password;
        Connection conexion= null;

    /** Creates a new instance of BD */
    public BD() {
        
        driver = "com.mysql.jdbc.Driver";
        url = new String("jdbc:mysql://localhost/proveedores");
        login = new String("root");
        password = new String("");
        try {
             Class.forName(driver).newInstance();
             conexion = DriverManager.getConnection(url,login,password);
             System.out.println("Conexi�n con Base de datos Ok....");
        } catch (Exception exc){
                System.out.println("Error al tratar de abrir la base de datos");
                }
        
        //cerramos la conexi�n a la Base de Datos
        //conexion.close();
    }
    
    // Agrega informaci�n a la Base de Datos
        
      public  String AgregarProveedor(int codigo,String nombre,String telefono,String direccion){
            String ComandoSQL="INSERT INTO proveedores VALUES ("+codigo+",'"+nombre+"','"+telefono+"','"+direccion+"')";
            try{
                
                    Statement stmt = conexion.createStatement();
                    stmt.executeUpdate(ComandoSQL);
                    
                    // Cerramos la interfaz Statement
                    stmt.close();
                    return "Registro agregado!";
                } catch (java.sql.SQLException er) {
                System.out.printf(er+"");
                return "proveedor existente";
                }
            }
      public  String AgregarInventario(int codigoArticulo,int codigoProveedor,double precio){
          if(precio<91&&precio>69){
            String ComandoSQL="INSERT INTO inventario VALUES ("+codigoArticulo+","+codigoProveedor+","+precio+")";
            try{
                
                    Statement stmt = conexion.createStatement();
                    stmt.executeUpdate(ComandoSQL);
                    
                    // Cerramos la interfaz Statement
                    stmt.close();
                    
                    return "Registro agregado!";
                    
                } catch (java.sql.SQLException er) {
                return "codigo de producto y/o proveedor no existente";
                }
            }
          return "precio debe estar entre 70 y 90";
      }
      public  String AgregarProducto(int codigo,String descripcion,String unidad,int cantidad){

            String ComandoSQL="INSERT INTO productos VALUES ("+codigo+",'"+descripcion+"','"+unidad+"','"+cantidad+"')";
            try{
                
                    Statement stmt = conexion.createStatement();
                    stmt.executeUpdate(ComandoSQL);
                   
                    // Cerramos la interfaz Statement
                    stmt.close();
                    return "Registro agregado!";
                } catch (java.sql.SQLException er) {
                return "producto existente";
                }
            }
      public String subirIVA(){
          String ComandoSQL="UPDATE productos,inventario SET precio=precio*0.16 where productos.codigo=inventario.codigoA and precio>50";
            try{
                
                    Statement stmt = conexion.createStatement();
                    stmt.executeUpdate(ComandoSQL);
                   
                    // Cerramos la interfaz Statement
                    stmt.close();
                    return "se aumento satisfactoriamente";
                } catch (java.sql.SQLException er) {
                    System.out.printf(er+"");
                return "No se pudo realizar la operaci�n";
                }
      }
      public String borrarInventario(){
          String ComandoSQL="DELETE from inventario where precio>79 and precio<91";
            try{
                
                    Statement stmt = conexion.createStatement();
                    stmt.executeUpdate(ComandoSQL);
                   
                    // Cerramos la interfaz Statement
                    stmt.close();
                    return "se borro satisfactoriamente";
                } catch (java.sql.SQLException er) {
                return "No se pudo realizar la operaci�n";
                }
      }
      public double mayor(){
          double result=0;
          String ComandoSQL="SELECT MAX(precio) from inventario";
            try{
                
                    Statement stmt = conexion.createStatement();
                    ResultSet resultado = stmt.executeQuery(ComandoSQL);
                   while(resultado.next())
                    {
                        
                        result = Double.parseDouble(resultado.getString(1));
                        
                    }
                    // Cerramos la interfaz Statement
                    stmt.close();
                    return result;
                } catch (java.sql.SQLException er) {
                System.out.printf(er+"");
                    return -1;
                }
      }
      public double minimaCantidad(){
          double result=0;
          String ComandoSQL="SELECT MIN(cantidad) from productos";
            try{
                
                    Statement stmt = conexion.createStatement();
                    ResultSet resultado = stmt.executeQuery(ComandoSQL);
                   while(resultado.next())
                    {
                        
                        result = Double.parseDouble(resultado.getString(1));
                        
                    }
                    // Cerramos la interfaz Statement
                    stmt.close();
                    return result;
                } catch (java.sql.SQLException er) {
                return -1;
                }
      }
      

  // Revisa los proveedores
     public   String ListarProveedoresMAX(){
            double precio=mayor();
            String ComandoSQL="SELECT nombre FROM proveedores,inventario where precio="+precio+"";
            String nombre="";
            
            try{
                    
                    Statement stmt = conexion.createStatement();
                    ResultSet resultado = stmt.executeQuery(ComandoSQL);
                    
                    while(resultado.next())
                    {
                        
                        nombre = resultado.getString(1);
                        
                    }
                    
                    // Cerramos la interfaz Statement
                    stmt.close();
                    return nombre;
                } catch (java.sql.SQLException er) 
                {
                return ("No se pudo realizar la operaci�n");
                }
            
            
            
        }
     public ArrayList<Object> ListarProveedores()
        {
            String ComandoSQL="SELECT * FROM proveedores";
            ArrayList<Object> datos=new ArrayList<>();
            try{
                    
                    Statement stmt = conexion.createStatement();
                    ResultSet resultado = stmt.executeQuery(ComandoSQL);
                    while(resultado.next())
                    {
                        datos.add(resultado.getInt(1));
                        datos.add(resultado.getString(2));
                        datos.add(resultado.getString(3));
                        datos.add(resultado.getString(4));
                        }
                    
                    // Cerramos la interfaz Statement
                    stmt.close();
                    return datos;
                } catch (java.sql.SQLException er) 
                {
                datos.add("no hay proveedores");
                return datos;
                }
             
        }
     public ArrayList<Object> ListarProductos()
        {
            String ComandoSQL="SELECT * FROM productos";
            ArrayList<Object> datos=new ArrayList<>();
            try{
                    
                    Statement stmt = conexion.createStatement();
                    ResultSet resultado = stmt.executeQuery(ComandoSQL);
                    
                    while(resultado.next())
                    {
                        datos.add(resultado.getInt(1));
                        datos.add(resultado.getString(2));
                        datos.add(resultado.getString(3));
                        datos.add(resultado.getString(4));
                        }
                    
                    // Cerramos la interfaz Statement
                    stmt.close();
                    return datos;
                } catch (java.sql.SQLException er) 
                {
                datos.add("no hay productos");
                return datos;
                }
             
        }
    
}
