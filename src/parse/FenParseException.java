/*
*
* Clase encargada de manejar los errores de la sintaxis FEN
*
* */


package parse;

public class FenParseException extends Exception {
    public FenParseException(String message) {
        super(message);
    }

}
