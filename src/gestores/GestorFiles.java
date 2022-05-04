/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestores;

import excepciones.MyException;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author ivanramonbolsa
 */
public class GestorFiles {
 
    public static void initFichero(File fichero) throws IOException{
        if (!fichero.exists()) {
            fichero.createNewFile();
        }
    }
    
    public static void addArticuloEliminado(String[] a) throws MyException{
        File historico = new File("historico.txt");
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            initFichero(historico);
            fos = new FileOutputStream(historico, true);
            dos = new DataOutputStream(fos);
            if (a != null){
                dos.writeUTF("cod_art: " + a[0] + ", descripcion: " + a[1] + ", precio: " + a[2] + ", stock: " + a[3]);
            }
        } catch (FileNotFoundException ex) {
            throw new MyException("No se encuentra el fichero " + historico.getAbsolutePath());
        } catch (IOException ex) {
            throw new MyException("Error al introducir los datos: " + ex.getMessage());
        }finally{
            try{
                if (fos != null) {
                    fos.close();
                }
                if (dos != null) {
                    dos.close();
                }
            }catch(IOException ex){
                throw new MyException("Error al cerrar el fichero " +ex.getMessage());
            }
        }
    }
    
    public static void addLogSQL(String sql) throws MyException{
        File log = new File("log.txt");
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            initFichero(log);
            fos = new FileOutputStream(log, true);
            dos = new DataOutputStream(fos);
            if (!sql.equals("") && !sql.isEmpty()) {
                dos.writeUTF(sql);
            }
        } catch (FileNotFoundException ex) {
            throw new MyException("No se encuentra el fichero " + log.getAbsolutePath());
        } catch (IOException ex) {
            throw new MyException("Error al introducir los datos: " + ex.getMessage());
        }finally{
            try{
                if (fos != null) {
                    fos.close();
                }
                if (dos != null) {
                    dos.close();
                }
            }catch(IOException ex){
                throw new MyException("Error al cerrar el fichero " +ex.getMessage());
            }
        }
    }
}
