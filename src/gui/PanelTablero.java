// PanelTablero.java
package gui;

import javax.swing.*; //se usa para componentes gráficos de Java Swing (como JPanel, JButton, etc.).
import java.awt.*; // → incluye clases para dibujo (Graphics, Color, Font, etc.).

/*
 * Panel que dibuja el tablero de ajedrez a partir del arreglo 8x8.
 */
public class PanelTablero extends JPanel { //Hereda de jpanel

    private int[][] tablero = new int[8][8];
    private final int margen = 20;

    public PanelTablero() {

        setPreferredSize(new Dimension(480, 480));
    } //define un tamaño preferido de eso pixeles


    public void cargarDatos(int[][] nuevoTablero) {
        this.tablero = nuevoTablero;
        repaint(); // Llama a paintcomponent() para redibujar el panel.
    }

    /**
     * Limpia el tablero (todas las casillas vacías).
     */
    public void limpiarTablero() {
        tablero = new int[8][8];
        // colorActivo = "w"; // <-- ELIMINADO
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) { //metodo heredado de jpanel
        super.paintComponent(g); //Limpi el fondo y prepara el panel

        Graphics2D g2 = (Graphics2D) g.create();  //creamos el objeto de dibujo

        // --- (Toda la lógica para calcular tamaños y dibujar casillas y piezas) ---

        int ancho = getWidth() - 2 * margen;
        int alto = getHeight() - 2 * margen;
        int tamanoTablero = Math.min(ancho, alto);
        int tamanoCasilla = tamanoTablero / 8;

        int inicioX = (getWidth() - tamanoCasilla * 8) / 2;
        int inicioY = (getHeight() - tamanoCasilla * 8) / 2;

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                boolean clara = (fila + columna) % 2 == 0;  // si es par la casilla es clara, sino oscura
                g2.setColor(clara ? new Color(240, 217, 181) : new Color(181, 136, 99));

                int x = inicioX + columna * tamanoCasilla;
                int y = inicioY + fila * tamanoCasilla;
                g2.fillRect(x, y, tamanoCasilla, tamanoCasilla);

                int valor = tablero[fila][columna];
                if (valor != 0) {  // si el valor es un numero lo convertimos a una ficha
                    String simbolo = convertirPiezaUnicode(valor);
                    Font fuenteOriginal = g2.getFont();
                    Font fuente = fuenteOriginal.deriveFont((float) (tamanoCasilla * 0.7));
                    g2.setFont(fuente);  //decide que la fuente sea el 70% del tamaño de la casilla

                    FontMetrics fm = g2.getFontMetrics();  //  mide el tamaño en pixeles
                    int anchoTexto = fm.stringWidth(simbolo);  //  mide el ancho que ocupa el símbolo
                    int altoTexto = fm.getAscent();    //Mide cuanto sube la letra desde la linea base


                  //coordenadas donde se dibujara el simbolo

                    int posX = x + (tamanoCasilla - anchoTexto) / 2;
                    int posY = y + (tamanoCasilla + altoTexto) / 2 - 4;

                    g2.setColor(valor > 0 ? Color.WHITE : Color.BLACK);
                    g2.drawString(simbolo, posX, posY);     //dibuja el simbolo

                    g2.setFont(fuenteOriginal);  //vuelve a la fuente original para noa fectar lo siguiente que se dibuje
                }
            }
        }

        g2.dispose();
    }

    /**
     * Convierte el número del arreglo a un símbolo Unicode de pieza.
     * (Este método no cambia)
     */
    private String convertirPiezaUnicode(int valor) {
        return switch (valor) {
            case 1 -> "♙";
            case 2 -> "♘";
            case 3 -> "♗";
            case 4 -> "♖";
            case 5 -> "♕";
            case 6 -> "♔";
            case -1 -> "♟";
            case -2 -> "♞";
            case -3 -> "♝";
            case -4 -> "♜";
            case -5 -> "♛";
            case -6 -> "♚";
            default -> "?";
        };
    }
}