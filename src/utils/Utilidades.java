package utils;

import java.util.Scanner;

public class Utilidades {
    
    public static void muestraArray(int [] array1) {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%02d", array1[i]);
        }
    }
    
    public static int [] generarArray (int tam){
        int [] array = new int [tam];
        for (int i = 0; i < tam; i++) {
            array[i] = Utilidades.generaEntero(1,99);
            
        }
        return array;
    }

    /**
     *
     * @param mensaje --> mensaje que se mostrará por pantalla
     * @param min --> valor minimo exigido
     * @param max --> valor máximo
     * @return Un número entero entre min y max
     */
    public static int pideNumCorrecto(String mensaje, int min, int max) {
        int num = pideEntero(mensaje);
        while (num > max || num < min) {
            System.out.println("Error: el número tiene que estar entre " + min + " y el " + max);
            System.out.print(mensaje);
            num = pideEntero(mensaje);
        }
        return num;
    }
    /**
     * 
     * @param str cadena que se supon es numérica
     * @return si todos los caracteres de la cadena son números,
     * excepto el primero, que puede ser + o -
     */
    private static boolean esEntero(String str) {
        // boolean isNumeric = str.matches("[+-]?\\d*(\\.\\d+)?");
        int longCadena;
        String numValidos = "0123456789";
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            str = str.substring(1);
        }
        longCadena = str.length();
        for (int i = 0; i < longCadena; i++) {
            if (!numValidos.contains(str.charAt(i) + "")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Genera un número entero aleatorio
     * @param min -- valor minimo
     * @param max -- valor máximo
     * @return un número aleatorio entre min y max
     */
    public static int generaEntero(int min, int max) {
        return (int)(Math.random()*max) + 1 ;
    }
    
    /**
     *
     * @param mensaje Muestra un mensaje por pantalla
     * @return un número entero, sin caracteres
     */
    public static int pideEntero(String mensaje){
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensaje);
        String num = scanner.nextLine();

        while ("".equals(num) ||!esEntero(num) ) {
            System.out.print("Has introducido caracteres erróneos. " + mensaje);
            num = scanner.nextLine();
        }
        
        return Integer.parseInt(num);
    }
    
        public static String pideCadena(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensaje);
        String frase = scanner.nextLine();
        while (frase == null && !frase.equals("")) {
            System.out.print("Has introducido caracteres erróneos. " + mensaje);
            frase = scanner.nextLine();
        }
        
        return frase;
    }
        public static float pideFloat(String mensaje){
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensaje);
        float num = scanner.nextFloat();
              
        return num;
    }
        
    public static double pideDouble(String mensaje){
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensaje);
        double num = scanner.nextDouble();
        
        return num;
    }
    
    public static String getCabecera(){
        return String.format("%15s %15s %5s %6s %6s\n","TITULO","AUTOR","Nº","PRESTABLE","PRESTADO");
    }

}
