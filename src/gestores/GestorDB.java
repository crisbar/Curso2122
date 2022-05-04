/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestores;

import excepciones.MyException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author AluDAM
 */
public class GestorDB {
    private String user;
    private String password;
    private String db;
    private String connexion;
    private Connection conn;
    private boolean status;

    public GestorDB(String user, String password, String db, String connexion) {
        this.user = user;
        this.password = password;
        this.db = db;
        this.connexion = connexion;
        conn = null;
        this.status = false;
    }

    public void initDataBase() throws MyException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(connexion + db, user, password);
            this.status = true;
        }catch (ClassNotFoundException ex) {
            throw new MyException("Se ha producido un error al importar la calse " + ex.getMessage());
        }catch (SQLException ex) {
            throw new MyException("Hay un fallo al conectarse a la base de datos: " + ex.getErrorCode() + "    " + ex.getSQLState());        
        } 
    }
    
    public boolean getStatusDB(){
        return this.status;
    }
    
    public int altaArticulo(String cod_art, String desc, float price, int stock) throws MyException{
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("INSERT INTO articulos "
                    + "VALUES (?,?,?,?);");
            ps.setString(1, cod_art);
            ps.setString(2, desc);
            ps.setFloat(3, price);
            ps.setInt(4, stock);
            return ps.executeUpdate();
        }catch(SQLException ex){
            throw new MyException("Se ha producido un error al insertar los datos: " + ex.getSQLState() + "  " + ex.getMessage());
        }finally{
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    throw new MyException("Revise los datos en su tabla: " + ex.getSQLState());
                }
            }
        }
    }
    
    public int modificarArticulos(String cod_art, String desc, float price, int stock) throws MyException{
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("UPDATE articulos "
                    + "SET descripcion = ?, "
                    + "precio = ?, "
                    + "stock = ? "
                    + "WHERE cod_art = ?;");
            ps.setString(4, cod_art);
            ps.setString(1, desc);
            ps.setFloat(2, price);
            ps.setInt(3, stock);
            return ps.executeUpdate();
        }catch(SQLException ex){
            throw new MyException("Se ha producido un error al actualizar los datos: " + ex.getSQLState() + "  " + ex.getMessage());
        }finally{
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    throw new MyException("Revise los datos en su tabla: " + ex.getSQLState());
                }
            }
        }
    }
    
    public String[][] listado() throws MyException {
        PreparedStatement ps = null;
        String [][] array = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM articulos");
            rs = ps.executeQuery();
            //inicializar el array ¿tamaño?
            rs.last();
            array = new String[rs.getRow()][4];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                array[i][0] = rs.getString(1);
                array[i][1] = rs.getString(2);
                array[i][2] = String.format("%.2f", rs.getFloat("precio"));
                array[i][3] = String.format("%d", rs.getInt("stock"));
                i++;
            }
            return array;
        } catch (SQLException ex) {
            throw new MyException("NO se pueden recuperar datos " + ex.getMessage() 
                    + " " + ex.getSQLState());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    throw new MyException(ex.getErrorCode(), "Revise los datos en su tabla: " + ex.getSQLState());
                }
            }
        }
    }
    
    public int bajaArticulo(String a) throws MyException{
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement("DELETE FROM articulos "
                    + "WHERE cod_art = ? ;"); 
            ps.setString(1, a);
            return ps.executeUpdate();
        }catch(SQLException ex){
            throw new MyException("Error borrando articulos  "+ ex.getSQLState()+ ex.getMessage());
        }finally{
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException ex) {
                    throw new MyException("Error comprueba la base de datos " + ex.getSQLState() + ex.getMessage());
                }
            }
        }
    }
    
    public String[] buscaArticulo(String n) throws MyException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.prepareCall("SELECT * FROM articulos WHERE cod_art = ?;");
            ps.setString(1, n);
            rs = ps.executeQuery();
            String [] a = new String[4];
            a[0] = n;
            if (rs.next()) {
                System.out.println("a");
                a[1] = rs.getString(2);
                System.out.println(a[1]);
                a[2] = rs.getFloat(3) + "";
                System.out.println(a[2]);
                a[3] = rs.getInt(4) + "";
                System.out.println(a[3]);
                return a;
            } else {
                return null;
            }
           
        }catch(SQLException ex){
            throw new MyException("Error buscando al alumno "+ex.getSQLState());
        }finally{
            if (ps!=null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    throw new MyException("Error revise si ha obtenido los datos." + ex.getSQLState());
                }
            }else{
                return null;
            }
        }
    }
    
    public void closeDataBase() throws MyException{
        try {
            System.out.println("Cerrando la base de datos...");
            conn.close();
            this.status = false;
        } catch (SQLException ex) {
            throw new MyException("Se ha producido un error al cerrar la base de datos"+ ex.getSQLState());
        }
    }

}

