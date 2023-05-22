import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.net.URI;

/**
 * 
 * Esta es la clase de la ventana principal
 * @author aiuoki
 *
 */
public class VentanaPrincipal extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 9162931242086760747L;
	
	/**
	 * Boton iniciar sesion
	 */
	private JButton btnIniciarSesion;
	/**
	 * Boton registrarse
	 */
	private JButton btnRegistrarse;
	/**
	 * Boton github
	 */
	private JButton btnGitHub;
	
	/**
	 * Boton ventana principal
	 */
    public VentanaPrincipal() {
    	
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
		
		
		
		
		// ------------------------------------------------------ PANEL SUPERIOR ------------------------------------------------------ //
		
        // PANEL SUPERIOR
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(new Color(0x111214)); // COLOR FONDO
        panelSuperior.setPreferredSize(new Dimension(0, 65)); // ESTABLECEMOS LA ALTURA DEL PANEL
        add(panelSuperior, BorderLayout.NORTH); // LO AÑADIMOS A LA VENTANA
        
        // TITULO PANEL SUPERIOR
        
        JLabel titulo2 = new JLabel("  Pandemic"); // TEXTO
        titulo2.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        titulo2.setFont(new Font("Arial", Font.BOLD, 26));  // FUENTE Y TAMAÑO DE LA FUENTE
        panelSuperior.add(titulo2, BorderLayout.WEST); // LO AÑADIMOS AL OESTE DEL PANEL SUPERIOR
        
        // PANEL BOTONES
        
        JPanel panelBotones = new JPanel();
        panelBotones.add(Box.createHorizontalGlue());
        panelBotones.setBackground(new Color(0x111214)); // COLOR FONDO
        panelBotones.setPreferredSize(new Dimension(310, 0)); // ANCHO PANEL
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 11)); // ESPACIO ENTRE BOTONES
        panelSuperior.add(panelBotones, BorderLayout.EAST); // LO AÑADIMOS AL ESTE DEL PANEL SUPERIOR
        
        // BOTON INICIAR SESION
        
        btnIniciarSesion = new JButton("Iniciar sesión"); // NOMBRE BOTÓN
        btnIniciarSesion.setBackground(new Color(0x111214)); // COLOR FONDO
        btnIniciarSesion.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnIniciarSesion.setPreferredSize(new Dimension(130, 40)); // TAMAÑO DEL BOTON
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 14)); // FUENTE Y TAMAÑO DE LA FUENTE
        btnIniciarSesion.addActionListener(this); // ACTIONLISTENER
        panelBotones.add(btnIniciarSesion); // LO AÑADIMOS AL PANEL BOTONES
        
        // BOTON REGISTRARSE
        
        btnRegistrarse = new JButton("Registrarse"); // NOMBRE BOTÓN
        btnRegistrarse.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnRegistrarse.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnRegistrarse.setPreferredSize(new Dimension(130, 40)); // TAMAÑO DEL BOTON
        btnRegistrarse.setFont(new Font("Arial", Font.BOLD, 14)); // FUENTE Y TAMAÑO DE LA FUENTE
        btnRegistrarse.addActionListener(this); // ACTIONLISTENER
        panelBotones.add(btnRegistrarse); // LO AÑADIMOS AL PANEL BOTONES
        
        
        
        // ------------------------------------------------------ PANEL CENTRAL ------------------------------------------------------ //
        
        // PANEL PRINCIPAL
        
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        panelPrincipal.setBackground(new Color(33, 33, 33)); // COLOR FONDO
		add(panelPrincipal, BorderLayout.CENTER); // LO AÑADIMOS A LA VENTANA
		
		// JLABEL TITULO PANEL PRINCIPAL
        
        JLabel titulo = new JLabel("Pandemic"); // TEXTO
        titulo.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        titulo.setFont(new Font("Arial", Font.BOLD, 36)); // FUENTE Y TAMAÑO DE LA FUENTE
        panelPrincipal.add(titulo); // LO AÑADIMOS AL PANEL PRINCIPAL
        
        // PANEL GITHUB
        
        JPanel panelBtnGitHub = new JPanel() {

			private static final long serialVersionUID = 8462157189174618016L;

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
        panelBtnGitHub.setLayout(new GridBagLayout());
        panelBtnGitHub.setPreferredSize(new Dimension(380, 100)); // TAMAÑO DEL PANEL
        panelBtnGitHub.setBackground(new Color(0x7F8487)); // COLOR FONDO
        
        GridBagConstraints gbc = new GridBagConstraints(); // PARAMETROS PARA POSICIONAR EL PANEL DEBAJO DEL TITULO
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 10);
        
        panelPrincipal.add(panelBtnGitHub, gbc); // LO AÑADIMOS AL PANEL PRINCIPAL
        
        // BOTON GITHUB
        
        btnGitHub = new JButton("GitHub"); // NOMBRE BOTÓN
        btnGitHub.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnGitHub.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnGitHub.setFont(new Font("Arial", Font.BOLD, 20)); // FUENTE Y TAMAÑO DE LA FUENTE
        btnGitHub.setPreferredSize(new Dimension(370, 90)); // DIMENSIONES DEL BOTON
        btnGitHub.addActionListener(this); // ACTIONLISTENER
        panelBtnGitHub.add(btnGitHub); // LO AÑADIMOS AL PANEL SECUNDARIO
        
        
        
        
        // ------------------------------------------------------ HACEMOS LA VENTANA VISIBLE ------------------------------------------------------ //

        setVisible(true);
        
    }
	
    /**
     * Metodo action listener para saber que boton hemos pulsado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if (e.getSource() == btnIniciarSesion) {
    		new VentanaIniciarSesion();
    		this.setVisible(false);
    	}
    	
    	if (e.getSource() == btnRegistrarse) {
    		new VentanaRegistrarse();
    		this.setVisible(false);
    	}
    	
    	if (e.getSource() == btnGitHub) {
    		try {
    			Desktop.getDesktop().browse(new URI("https://github.com/aiuoki/pandemic"));
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    	
	}
    
    /**
     * Metodo main
     * @param args. Argumentos
     */
    public static void main(String[] args) {
        new VentanaPrincipal();
    }
    
}