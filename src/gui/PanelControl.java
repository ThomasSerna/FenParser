// PanelControl.java
package gui;

import parse.FenParseException;
import parse.FenParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent; //permite usar eventos

/**
 * Panel con el campo FEN, botones y mensajes de estado.
 */
public class PanelControl extends JPanel {

    private final PanelTablero boardPanel;
    private final JTextField fenField; //donde el usuario escribe la cadena
    private final JButton cargarBtn;//botón que intenta cargar esa cadena.
    private final JButton limpiarBtn;//botón que limpia el tablero.

    // --- LABELS DE INFO FEN ---
    private final JLabel turnoLabel;
    private final JLabel enroqueLabel;
    private final JLabel alPasoLabel;
    private final JLabel mediaJugadaLabel;
    private final JLabel jugadaCompletaLabel;
    // -------------------------


    private final JLabel estadoLabel;

    public PanelControl(PanelTablero boardPanel) { //constructor que recibe el panelTablero
        this.boardPanel = boardPanel;
        setLayout(new BorderLayout(8, 8)); // <-- El layout principal
        setPreferredSize(new Dimension(300, 0));

        // --- 1. ZONA SUPERIOR (Norte) ---
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout(5,5));
        top.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

        JLabel instr = new JLabel("<html><b>Ingrese cadena FEN</b><br/>Ej: 2r3k1/... b - - 1 27</html>");//texto explicativo
        top.add(instr, BorderLayout.NORTH);

        fenField = new JTextField();
        top.add(fenField, BorderLayout.CENTER);

        add(top, BorderLayout.NORTH); // <-- Se añade al panel principal

        // --- 2. ZONA CENTRAL (Botones + Info) ---

        // Panel para los botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        cargarBtn = new JButton("Cargar FEN");
        limpiarBtn = new JButton("Limpiar tablero");
        botones.add(cargarBtn);
        botones.add(limpiarBtn);

        // --- CAMBIO: Nuevo panel solo para la info FEN ---
        JPanel fenInfoPanel = new JPanel();
        fenInfoPanel.setLayout(new BoxLayout(fenInfoPanel, BoxLayout.Y_AXIS));//coloca los JLabel uno debajo del otro (como una columna vertical).
        fenInfoPanel.setBorder(BorderFactory.createEmptyBorder(15, 8, 8, 8)); // Espacio arriba


        // Inicializar los labels
        turnoLabel = new JLabel("Turno: Blancas");
        enroqueLabel = new JLabel("Enroque: -");
        alPasoLabel = new JLabel("Al paso: -");
        mediaJugadaLabel = new JLabel("Media jugada: 0");
        jugadaCompletaLabel = new JLabel("Jugada completa: 1");

        // Alineación
        turnoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        enroqueLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        alPasoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mediaJugadaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        jugadaCompletaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Añadirlos al panel de información
        fenInfoPanel.add(turnoLabel);
        fenInfoPanel.add(enroqueLabel);
        fenInfoPanel.add(alPasoLabel);
        fenInfoPanel.add(mediaJugadaLabel);
        fenInfoPanel.add(jugadaCompletaLabel);

        // Contenedor central que une Botones e Info ---
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.add(botones, BorderLayout.NORTH); // Botones arriba
        centerContainer.add(fenInfoPanel, BorderLayout.CENTER); // Info debajo

        add(centerContainer, BorderLayout.CENTER); // <-- Se añade al panel principal

        // --- 3. ZONA INFERIOR (Sur) ---
        //  solo contiene el label de estado ---
        estadoLabel = new JLabel(" ");
        estadoLabel.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        add(estadoLabel, BorderLayout.SOUTH); // <-- Se añade al panel principal



        // --- 4. ACCIONES (Sin cambios) ---
        cargarBtn.addActionListener(this::onCargar);

        limpiarBtn.addActionListener(e -> {
            boardPanel.limpiarTablero();

            // Limpiar también los labels de info
            turnoLabel.setText("Turno: Blancas");
            enroqueLabel.setText("Enroque: -");
            alPasoLabel.setText("Al paso: -");
            mediaJugadaLabel.setText("Media jugada: 0");
            jugadaCompletaLabel.setText("Jugada completa: 1");

            // Actualizar estado
            estadoLabel.setText("Tablero limpiado.");
        });
    }

    /**
     * Lógica de carga
     */
    private void onCargar(ActionEvent e) {
        String fen = fenField.getText().trim();
        if (fen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una cadena FEN.", "Atención", JOptionPane.WARNING_MESSAGE);
            return; //jOptionPane muestra mensajitos emergentes

        }

        try {
            FenParser analizador = new FenParser();
            analizador.parser(fen);

            boardPanel.cargarDatos(analizador.getTablero()); //actualiza el tablero

            //obtiene la info
            String turno = analizador.getColorActivo().equals("w") ? "Blancas" : "Negras";
            turnoLabel.setText("Turno: " + turno);

            enroqueLabel.setText("Enroque: " + analizador.getDisponibilidadEnroque());
            alPasoLabel.setText("Al paso: " + analizador.getCasillaCapturaAlPaso());
            mediaJugadaLabel.setText("Media jugada: " + analizador.getRelojMediasJugadas());
            jugadaCompletaLabel.setText("Jugada completa: " + analizador.getNumeroJugadaCompleta());

            estadoLabel.setText("FEN válida. Tablero cargado.");

            //manejo de excepciones
        } catch (FenParseException ex) {
            JOptionPane.showMessageDialog(this, "FEN inválida:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            estadoLabel.setText("FEN inválida: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error no esperado:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            estadoLabel.setText("Error inesperado.");
            ex.printStackTrace();
        }
    }
}