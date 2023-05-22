import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

/**
 * 
 * Esta es la clase de la ventana menu
 * @author aiuoki
 *
 */
public class VentanaMenu extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -1115610363139312385L;
	
	/**
	 * Boton nueva partida
	 */
	private JButton btnNuevaPartida;
	/**
	 * Boton cargar partida
	 */
	private JButton btnCargarPartida;
	/**
	 * Boton informacion
	 */
	private JButton btnInformacion;
	/**
	 * Boton resumen puntuaciones
	 */
	private JButton btnResumenPuntuaciones;
	/**
	 * Boton autores
	 */
	private JButton btnAutores;
	/**
	 * Boton version
	 */
	private JButton btnVersion;
	/**
	 * Boton salir
	 */
	private JButton btnSalir;
	
	/**
	 * Ventana menu
	 */
	public VentanaMenu() {
		
    	// ------------------------------------------------------ VENTANA ------------------------------------------------------ //
    	
    	// CONFIGURAR VENTANA
    	
        setTitle("Pandemic"); // TITULO VENTANA
        setExtendedState(JFrame.MAXIMIZED_BOTH); // VENTANA MAXIMIZADA POR DEFECTO
        setSize(1200, 800); // TAMAÑO DE LA VENTANA AL MINIMIZARLA
        setMinimumSize(new Dimension(1000, 700)); // MINIMO ESCALABLE
        setLocationRelativeTo(null); // CENTRAR VENTANA
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // btnSalir AL PULSAR LA X
        
        // ICONO VENTANA
        
		ImageIcon imgLogo = new ImageIcon("imgLogo.png"); // IMAGEN LOGO
		setIconImage(imgLogo.getImage()); // LA AÑADIMOS A LA VENTANA
		
		// ------------------------------------------------------ PANEL CENTRAL ------------------------------------------------------ //
		
		// PANEL CENTRAL
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(33, 33, 33)); // COLOR FONDO
		panelCentral.setLayout(new GridBagLayout());
		add(panelCentral);
		
		// PANEL SECUNDARIO
		
		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(new BoxLayout(panelSecundario, BoxLayout.Y_AXIS));
		panelSecundario.setPreferredSize(new Dimension(480, 305));
		panelSecundario.setBackground(new Color(33, 33, 33)); // COLOR FONDO
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 10;
		gbc.fill = GridBagConstraints.CENTER;
		panelCentral.add(panelSecundario, gbc);
		
		// PANEL BOTON NUEVA PARTIDA
		
		JPanel panelBtnNuevaPartida = new JPanel() {

			private static final long serialVersionUID = 2559139272726227482L;

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
        panelBtnNuevaPartida.setMaximumSize(new Dimension(940, 65));
        panelBtnNuevaPartida.setBackground(new Color(0x7F8487)); // COLOR FONDO
		panelSecundario.add(panelBtnNuevaPartida); // LO AÑADIMOS AL PANEL SECUNDARIO
		
		// BOTON BUEVA PARTIDA
		
		btnNuevaPartida = new JButton("Nueva partida");
		btnNuevaPartida.setBackground(new Color(0x7F8487)); // COLOR FONDO
		btnNuevaPartida.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
		btnNuevaPartida.setFont(new Font("Arial", Font.BOLD, 18)); // FUENTE Y TAMAÑO DE LA FUENTE
		btnNuevaPartida.addActionListener(this); // ACTIONLISTENER
		btnNuevaPartida.setPreferredSize(new Dimension(470, 55));
		panelBtnNuevaPartida.add(btnNuevaPartida);
		
		// ESPACIO
		
		panelSecundario.add(Box.createRigidArea(new Dimension(0, 15)));
		
		// PANEL BOTON CARGAR PARTIDA
		
		JPanel panelBtnCargarPartida = new JPanel() {

			private static final long serialVersionUID = -2559436865909401151L;

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
        panelBtnCargarPartida.setMaximumSize(new Dimension(940, 65));
        panelBtnCargarPartida.setBackground(new Color(0x7F8487)); // COLOR FONDO
		panelSecundario.add(panelBtnCargarPartida); // LO AÑADIMOS AL PANEL SECUNDARIO
		
		// BOTON CARGAR PARTIDA
		
		btnCargarPartida = new JButton("Cargar partida");
		btnCargarPartida.setBackground(new Color(0x7F8487)); // COLOR FONDO
		btnCargarPartida.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
		btnCargarPartida.setFont(new Font("Arial", Font.BOLD, 18)); // FUENTE Y TAMAÑO DE LA FUENTE
		btnCargarPartida.addActionListener(this); // ACTIONLISTENER
		btnCargarPartida.setPreferredSize(new Dimension(470, 55));
		panelBtnCargarPartida.add(btnCargarPartida);
		
		// ESPACIO
		
		panelSecundario.add(Box.createRigidArea(new Dimension(0, 15)));
		
		// PANEL BOTON INFORMACION
		
		JPanel panelBtnInformacion = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 7594867270005954043L;

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
        panelBtnInformacion.setMaximumSize(new Dimension(940, 65));
        panelBtnInformacion.setBackground(new Color(0x7F8487)); // COLOR FONDO
		panelSecundario.add(panelBtnInformacion); // LO AÑADIMOS AL PANEL SECUNDARIO
		
		// BOTON INFORMACION
		
		btnInformacion = new JButton("Información");
		btnInformacion.setBackground(new Color(0x7F8487)); // COLOR FONDO
		btnInformacion.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
		btnInformacion.setFont(new Font("Arial", Font.BOLD, 18)); // FUENTE Y TAMAÑO DE LA FUENTE
		btnInformacion.addActionListener(this); // ACTIONLISTENER
		btnInformacion.setPreferredSize(new Dimension(470, 55));
		panelBtnInformacion.add(btnInformacion);
		
		// ESPACIO
		
		panelSecundario.add(Box.createRigidArea(new Dimension(0, 15)));
		
		// PANEL BOTON INFORMACION
		
		JPanel panelBtnResumenPuntuaciones = new JPanel() {

			private static final long serialVersionUID = -8144880915905592479L;

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
        panelBtnResumenPuntuaciones.setMaximumSize(new Dimension(940, 65));
        panelBtnResumenPuntuaciones.setBackground(new Color(0x7F8487)); // COLOR FONDO
		panelSecundario.add(panelBtnResumenPuntuaciones); // LO AÑADIMOS AL PANEL SECUNDARIO
		
		// BOTON RESUMEN PUNTUACIONES
		
		btnResumenPuntuaciones = new JButton("Resumen de puntuaciones");
		btnResumenPuntuaciones.setBackground(new Color(0x7F8487)); // COLOR FONDO
		btnResumenPuntuaciones.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
		btnResumenPuntuaciones.setFont(new Font("Arial", Font.BOLD, 18)); // FUENTE Y TAMAÑO DE LA FUENTE
		btnResumenPuntuaciones.addActionListener(this); // ACTIONLISTENER
		btnResumenPuntuaciones.setPreferredSize(new Dimension(470, 55));
		panelBtnResumenPuntuaciones.add(btnResumenPuntuaciones);
		
		
		// BOTON AUTORES
		
		btnAutores = new JButton("Autores");
		btnAutores.setBackground(new Color(0x7F8487));
		btnAutores.setForeground(new Color(255, 255, 255));
		btnAutores.setPreferredSize(new Dimension(100, 40));
		btnAutores.addActionListener(this); // ACTIONLISTENER
		GridBagConstraints gbcbtnAutores = new GridBagConstraints();
		gbcbtnAutores.gridx = 0;
		gbcbtnAutores.gridy = 1;
		gbcbtnAutores.weightx = 1;
		gbcbtnAutores.weighty = 1;
		gbcbtnAutores.anchor = GridBagConstraints.SOUTHWEST;
		gbcbtnAutores.insets = new Insets(20, 130, 10, 20);
		panelCentral.add(btnAutores, gbcbtnAutores);
		
		// BOTON VERSION
		
		btnVersion = new JButton("Versión");
		btnVersion.setBackground(new Color(0x7F8487));
		btnVersion.setForeground(new Color(255, 255, 255));
		btnVersion.setPreferredSize(new Dimension(100, 40));
		btnVersion.addActionListener(this); // ACTIONLISTENER
		GridBagConstraints gbcbtnVersion = new GridBagConstraints();
		gbcbtnVersion.gridx = 0;
		gbcbtnVersion.gridy = 1;
		gbcbtnVersion.weightx = 1;
		gbcbtnVersion.weighty = 1;
		gbcbtnVersion.anchor = GridBagConstraints.SOUTHWEST;
		gbcbtnVersion.insets = new Insets(20, 10, 10, 10);
		panelCentral.add(btnVersion, gbcbtnVersion);
		
		// BOTON SALIR
		
		btnSalir = new JButton("Salir");
		btnSalir.setBackground(new Color(0x7F8487));
		btnSalir.setForeground(new Color(255, 255, 255));
        btnSalir.setPreferredSize(new Dimension(100, 40));
        btnSalir.addActionListener(this); // ACTIONLISTENER
		GridBagConstraints gbcExit = new GridBagConstraints();
		gbcExit.gridx = 0;
		gbcExit.gridy = 1;
		gbcExit.weightx = 1;
		gbcExit.weighty = 1;
		gbcExit.anchor = GridBagConstraints.SOUTHEAST;
		gbcExit.insets = new Insets(10, 10, 10, 10);
		panelCentral.add(btnSalir, gbcExit);
		
		
		
		
		// ------------------------------------------------------ HACEMOS LA VENTANA VISIBLE ------------------------------------------------------ //
		
		setVisible(true);
		
    }
	
	/**
	 * Metodo action listener para saber que boton hemos pulsado
	 */
	@Override
    public void actionPerformed(ActionEvent e) {
    	
    	if (e.getSource() == btnNuevaPartida) {
    		new VentanaDificultad();
    		this.setVisible(false);
    	}
    	
    	if (e.getSource() == btnCargarPartida) {
    		new VentanaCargarPartida();
    		this.setVisible(false);
    	}
    	
    	if (e.getSource() == btnInformacion) {
    		new VentanaInformacion();
    		this.setVisible(false);
    	}
    	
    	if (e.getSource() == btnResumenPuntuaciones) {
    		new VentanaResumenPuntuaciones();
    		this.setVisible(false);
    	}
    	
    	if (e.getSource() == btnAutores) {
    		new VentanaAutores();
    		this.setVisible(false);
    	}
    	
    	if (e.getSource() == btnVersion) {
    		new VentanaVersion();
    		this.setVisible(false);
    	}
    	
    	if (e.getSource() == btnSalir) {
    		System.exit(EXIT_ON_CLOSE);
    	}
    	
	}
	
    /**
     * Metodo main
     * @param args. Argumentos
     */
    public static void main(String[] args) {
		new VentanaMenu();
    }
    
}