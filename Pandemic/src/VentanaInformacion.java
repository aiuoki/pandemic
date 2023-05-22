import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * 
 * Esta es la clase de la ventana informacion
 * @author aiuoki
 *
 */
public class VentanaInformacion extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 944468929271396331L;
	
	/**
	 * Boton volver, que nos lleva al menu
	 */
	private JButton btnVolver;
	
	/**
	 * Ventana informacion
	 */
    public VentanaInformacion() {
    	
    	// ------------------------------------------------------ VENTANA ------------------------------------------------------ //
    	
    	// CONFIGURAR VENTANA
    	
        setTitle("Pandemic"); // TITULO VENTANA
        setExtendedState(JFrame.MAXIMIZED_BOTH); // VENTANA MAXIMIZADA POR DEFECTO
        setSize(1200, 800); // TAMAÑO DE LA VENTANA AL MINIMIZARLA
        setMinimumSize(new Dimension(1000, 700)); // MINIMO ESCALABLE
        setLocationRelativeTo(null); // CENTRAR VENTANA
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // SALIR AL PULSAR LA X
        
        // ICONO VENTANA
        
		ImageIcon imgLogo = new ImageIcon("imgLogo.png"); // IMAGEN LOGO
		setIconImage(imgLogo.getImage()); // LA AÑADIMOS A LA VENTANA
        
		
		
		
		// ------------------------------------------------------ PANEL CENTRAL ------------------------------------------------------ //
		
        // CONFIGURAR PANEL PRINCIPAL
        
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelCentral.setLayout(new GridBagLayout()); // USAMOS GridBagLayout PARA CENTRAR EL PANEL SECUNDARIO
        add(panelCentral, BorderLayout.CENTER); //  LO AÑADIMOS A LA VENTANA
                
        // JLABEL TITULO
        
        JLabel titulo = new JLabel("Información"); // TEXTO
        titulo.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        titulo.setFont(new Font("Arial", Font.BOLD, 36)); // FUENTE Y TAMAÑO
        panelCentral.add(titulo); // LO AÑADIMOS AL PANEL
        
        // PANEL SECUNDARIO
        
        JPanel panelSecundario = new JPanel() {

			private static final long serialVersionUID = -5345111863437633699L;

			@Override
            protected void paintComponent(Graphics g) { // USAMOS EL METODO paintComponent PARA DIBUJAR UN BORDE REDONDEADO
                Graphics2D g2 = (Graphics2D) g.create();
                int x = 0;
                int y = 0;
                int w = getWidth();
                int h = getHeight();
                int arc = 15;
                RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, w-1, h-1, arc, arc);
                g2.setClip(rect);
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        panelSecundario.setLayout(new GridBagLayout());
        panelSecundario.setPreferredSize(new Dimension(800, 450)); // TAMAÑO DEL PANEL
        panelSecundario.setBackground(new Color(13, 13, 13)); // COLOR FONDO
        
        GridBagConstraints gbc = new GridBagConstraints(); // PARAMETROS PARA POSICIONAR EL PANEL DEBAJO DEL TITULO
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 10);
        
        panelCentral.add(panelSecundario, gbc); // LO AÑADIMOS AL PANEL PRINCIPAL
        
        // JTEXTAREA INFORMACION
        
        JTextArea informacion = new JTextArea("La partida empieza con varias ciudades infectadas y por cada ronda que pase se infectarán más ciudades."
        		+ "\n\nVerás que hay 4 tipos de curas, una para cada enfermedad, las cuales deberás ir investigando."
        		+ "\n\nTe preguntarás cómo puedes investigar las curas y aquí te va la respuesta."
        		+ "\n\nPor cada ronda contarás con 4 acciones con las cuales podrás hacer lo siguiente:"
        		+ "\n\n•  Usar las 4 acciones para investigar un porcentaje de la vacuna que desees."
        		+ "\n•  Usar 1 acción para curar un nivel de infección de una ciudad."
        		+ "\n\nVerás que también hay brotes. Un brote se produce cuando se infecta una ciudad cuyo nivel de infección"
        		+ "\nestaba en 3, entonces se sumará +1 al total de brotes y sus ciudades colindantes se infectarán con un nivel más."
        		+ "\n\nSi superas el número máximo de ciudades infectadas permitidas ¡perderás!"
        		+ "\n\nSi superas el número máximo de brotes permitidos ¡perderás!"
        		+ "\n\n¡Hazte con las 4 curas y vencerás!"); // TEXTO
        informacion.setBackground(new Color(13, 13, 13)); // COLOR FONDO
        informacion.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        informacion.setFont(new Font("Arial", Font.PLAIN, 15)); // FUENTE Y TAMAÑO DE LA FUENTE
        informacion.setEditable(false); // HECEMOS QUE NO SE PUEDA EDITAR
        panelSecundario.add(informacion); // LO AÑADIMOS AL PANEL SECUNDARIO
        
        
        
        
        // ------------------------------------------------------ PANEL INFERIOR ------------------------------------------------------ //
        
        // PANEL INFERIOR
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        add(panelInferior, BorderLayout.PAGE_END); // AÑADIMOS EL PANEL A LA PARTE INFERIOR DE LA VENTANA
        
        // BOTON VOLVER
        
        btnVolver = new JButton("Volver"); // NOMBRE BOTÓN
        btnVolver.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnVolver.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnVolver.setPreferredSize(new Dimension(100, 40)); // TAMAÑO BOTON
        btnVolver.addActionListener(this); // ACTIONLISTENER
        panelInferior.add(btnVolver); // AÑADIMOS EL BOTON VOLVER AL PANEL INFERIOR
        
        
        
        
        // ------------------------------------------------------ HACEMOS LA VENTANA VISIBLE ------------------------------------------------------ //
        
        setVisible(true);
        
    }
    
    /**
     * Metodo action listener para saber que boton hemos pulsado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if (e.getSource() == btnVolver) {
    		new VentanaMenu();
    		this.setVisible(false);
    	}
    	
	}
    
    /**
     * Metodo main
     * @param args. Argumentos
     */
    public static void main(String[] args) {
        new VentanaInformacion();
    }
    
}