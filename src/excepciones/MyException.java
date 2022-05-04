/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author isaac
 */
public class MyException extends Exception {

    public MyException(String string) {
        super(string);
    }

    public MyException(int sqlError, String string) {
        super(string);
        //guardar en el fichero
    }
    
    
    
    
}
