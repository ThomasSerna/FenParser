/*
*
* Clase principal
*
*/

import parse.FenParser;

public class FenParserMain {

    private static void testParse(){

        // Instanciar FenParser para pruebas internas de funcionamiento
        FenParser toParce = new FenParser();
        toParce.parser("");

        // Ciclo para revisar arreglo que define el tablero
        for (int[] i : toParce.getTablero()){
            for (int k : i) {
                System.out.print(k);
            }
            System.out.println();

        }

    }


    public static void main(String[] args) {

        // Metodo de prueba para clase parse.FenParser
        testParse();

    }

}
