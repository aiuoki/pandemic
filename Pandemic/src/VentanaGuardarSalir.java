import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
 * 
 * Esta es la clase de la ventana guardar y salir
 * @author aiuoki
 *
 */
public class VentanaGuardarSalir extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -4165187079660251398L;
	
	/**
	 * Boton volver, que nos lleva al menu
	 */
	private JButton btnVolver;
	
	/**
	 * Ventana guardar y salir
	 */
	public VentanaGuardarSalir() {
		
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
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelPrincipal.setLayout(new GridBagLayout()); // USAMOS GridBagLayout PARA CENTRAR EL PANEL SECUNDARIO
        add(panelPrincipal, BorderLayout.CENTER); //  LO AÑADIMOS A LA VENTANA
        
        
        // PANEL SECUNDARIO
        
        JPanel panelSecundario = new JPanel() {

			private static final long serialVersionUID = 1707835917337080675L;

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
        panelSecundario.setPreferredSize(new Dimension(650, 120)); // TAMAÑO DEL PANEL
        panelSecundario.setBackground(new Color(13, 13, 13)); // COLOR FONDO
        
        GridBagConstraints gbc = new GridBagConstraints(); // PARAMETROS PARA POSICIONAR EL PANEL DEBAJO DEL TITULO
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 10);
        
        panelPrincipal.add(panelSecundario, gbc); // LO AÑADIMOS AL PANEL PRINCIPAL
        
        // JLABEL MENSAJE
        
        JLabel mensaje = new JLabel("La partida se ha guardado correctamente!"); // TEXTO
        mensaje.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        mensaje.setFont(new Font("Arial", Font.PLAIN, 30)); // FUENTE Y TAMAÑO DE LA FUENTE
        panelSecundario.add(mensaje); // LO AÑADIMOS AL PANEL SECUNDARIO
        
        
        
        
        // ------------------------------------------------------ PANEL INFERIOR ------------------------------------------------------ //
        
        // PANEL INFERIOR
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        add(panelInferior, BorderLayout.PAGE_END); // AÑADIMOS EL PANEL DEL BOTON AL FINAL DE LA VENTANA
        
        // BOTON VOLVER
        
        btnVolver = new JButton("Volver"); // NOMBRE BOTÓN
        btnVolver.setBackground(new Color(0x7F8487));
        btnVolver.setForeground(new Color(255, 255, 255));
        btnVolver.setPreferredSize(new Dimension(100, 40));
        btnVolver.addActionListener(this); // ACTIONLISTENER
        panelInferior.add(btnVolver); // AÑADIMOS EL BOTON VOLVER AL PANEL
        
        
        
        
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
        new VentanaGuardarSalir();
    }
    
}