import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.sql.Connection;

/**
 * 
 * Esta es la clase de la ventana iniciar sesion
 * @author aiuoki
 *
 */
public class VentanaIniciarSesion extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 5613125996102109237L;
	
	/**
	 * Panel que muestra el mensaje de error
	 */
	private JPanel panelError;
	/**
	 * Mensaje de error
	 */
	private JTextArea mensajeError;
	
	/**
	 * Area de texto para el usuario del jugador
	 */
	public static JTextField textFieldUsuario;
	/**
	 * Area de contrasena para la contrasena del jugador
	 */
	private JPasswordField passwordFieldContrasena;
	
	/**
	 * Boton salir para volver a la ventana principal
	 */
	private JButton btnSalir;
	
	/**
	 * Boton iniciar sesion
	 */
	private JButton btnIniciarSesion;
	
	/**
	 * Ventana iniciar sesion
	 */
    public VentanaIniciarSesion() {
    	
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
        panelSuperior.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelSuperior.setPreferredSize(new Dimension(0, 180)); // ESTABLECEMOS LA ALTURA DEL PANEL
        add(panelSuperior, BorderLayout.NORTH); // LO AÑADIMOS A LA VENTANA
        
        // PANEL SUPERIOR IZQUIERDA
        
        JPanel panelSuperiorIzquierda = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelSuperiorIzquierda.setPreferredSize(new Dimension(600, 180)); // TAMAÑO
        panelSuperiorIzquierda.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelSuperior.add(panelSuperiorIzquierda, BorderLayout.WEST); // LO AÑADIMOS AL OESTE DEL PANEL SUPERIOR
        
        // PANEL ERROR
        
        panelError = new JPanel();
        panelError.setPreferredSize(new Dimension(560, 160)); // TAMAÑO
        panelError.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelError.setVisible(false); // HACEMOS QUE NO SEA VISIBLE
        panelSuperiorIzquierda.add(panelError); // LO AÑADIMOS AL PANEL SUPERIOR IZQUIERDA
        
        // PANEL ERROR SECUNDARIO
        
        JPanel panelErrorSecundario = new JPanel(new BorderLayout());
        panelErrorSecundario.setPreferredSize(new Dimension(550, 150)); // TAMAÑO
        panelError.add(panelErrorSecundario); // LO AÑADIMOS AL PANEL ERROR
        
        // PANEL IMAGEN ERROR
        
        JPanel panelImgError = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 35));
        panelImgError.setPreferredSize(new Dimension(110, 160)); // TAMAÑO
        panelImgError.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelErrorSecundario.add(panelImgError, BorderLayout.WEST); // LO AÑADIMOS AL PANEL ERROR SECUNDARIO
        
        // LABEL IMAGEN ERROR
        
        JLabel labelImgError = new JLabel();
        labelImgError.setPreferredSize(new Dimension(70, 70)); // TAMAÑO
        panelImgError.add(labelImgError, BorderLayout.WEST); // LO AÑADIMOS AL PANEL IMAGEN ERROR
        
        // IMAGEN ERROR
        
        BufferedImage imgError = null;
        try {
        	imgError = ImageIO.read(new File("imgError.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Image dimgError = imgError.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        
        ImageIcon imageIconError = new ImageIcon(dimgError);
        
        labelImgError.setIcon(imageIconError);
        
        // PANEL TEXTOS ERROR
        
        JPanel panelTextosError = new JPanel();
        panelTextosError.setPreferredSize(new Dimension(450, 160)); // TAMAÑO
        panelTextosError.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelErrorSecundario.add(panelTextosError, BorderLayout.CENTER); // LO AÑADIMOS AL PANEL ERROR SECUNDARIO
        
        // PANEL TITULO ERROR
        
        JPanel panelTituloError = new JPanel();
        panelTituloError.setPreferredSize(new Dimension(450, 60)); // TAMAÑO
        panelTituloError.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelTextosError.add(panelTituloError); // LO AÑADIMOS AL PANEL TEXTOS ERROR
        
        // LABEL TITULO ERROR
        
        JLabel tituloError = new JLabel("Error"); // TEXTO
        tituloError.setForeground(Color.WHITE); // COLOR FUENTE
        tituloError.setFont(new Font("Arial", Font.BOLD, 30));  // FUENTE Y TAMAÑO DE LA FUENTE
        tituloError.setPreferredSize(new Dimension(420, 60)); // TAMAÑO LABEL
        tituloError.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelTituloError.add(tituloError); // LO AÑADIMOS AL PANEL TITULO ERROR
        
        // PANEL MENSAJE ERROR
        
        JPanel panelMensajeError = new JPanel();
        panelMensajeError.setPreferredSize(new Dimension(450, 80)); // TAMAÑO
        panelMensajeError.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelTextosError.add(panelMensajeError); // LO AÑADIMOS AL PANEL TEXTOS ERROR
        
        // MENSAJE ERROR
        
        mensajeError = new JTextArea("");
        mensajeError.setForeground(Color.WHITE); // COLOR FUENTE
        mensajeError.setFont(new Font("Arial", Font.PLAIN, 20));  // FUENTE Y TAMAÑO DE LA FUENTE
        mensajeError.setPreferredSize(new Dimension(420, 75)); // TAMAÑO
        mensajeError.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelMensajeError.add(mensajeError); // LO AÑADIMOS AL PANEL MENSAJE ERROR
        
        // ------------------------------------------------------ PANEL CENTRAL ------------------------------------------------------ //
        
        // PANEL CENTRAL
        
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(33, 33, 33)); // COLOR FONDO
		add(panelCentral, BorderLayout.CENTER); // LO AÑADIMOS A LA VENTANA
		
		// PANEL INICIAR SESION
        
        JPanel panelIniciarSesion = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8911201144805296270L;

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
        panelIniciarSesion.setLayout(new GridBagLayout());
        panelIniciarSesion.setPreferredSize(new Dimension(525, 350)); // TAMAÑO DEL PANEL
        panelIniciarSesion.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelCentral.add(panelIniciarSesion); // LO AÑADIMOS AL PANEL INICIAR SESION
        
        // PANEL SECUNDARIO
        
        JPanel panelSecundario = new JPanel();
        panelSecundario.setPreferredSize(new Dimension(515, 340)); // TAMAÑO
        panelSecundario.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelIniciarSesion.add(panelSecundario); // LO AÑADIMOS AL PANEL INICIAR SESION
        
        // PANEL SECUNDARIO SUPERIOR
        
        JPanel panelSecundarioSuperior = new JPanel();
        panelSecundarioSuperior.setPreferredSize(new Dimension(505, 120)); // TAMAÑO
        panelSecundarioSuperior.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelSecundario.add(panelSecundarioSuperior); // LO AÑADIMOS AL PANEL SECUNDARIO
        
        // PANEL TITULO
        
        JPanel panelTitulo = new JPanel();
        panelTitulo.setPreferredSize(new Dimension(430, 55)); // TAMAÑO
        panelTitulo.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelSecundarioSuperior.add(panelTitulo); // LO AÑADIMOS AL PANEL SECUNDARIO SUPERIOR
        
        // PANEL SECUNDARIO TITULO
        
        JPanel panelSecundarioTitulo = new JPanel();
        panelSecundarioTitulo.setPreferredSize(new Dimension(200, 45)); // TAMAÑO
        panelSecundarioTitulo.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelTitulo.add(panelSecundarioTitulo); // LO AÑADIMOS AL PANEL TITULO
        
        // LABEL TITULO
        
        JLabel titulo = new JLabel("Iniciar Sesion"); // TEXTO
        titulo.setPreferredSize(new Dimension(180, 35)); // TAMAÑO
        titulo.setForeground(Color.WHITE); // COLOR FUENTE
        titulo.setFont(new Font("Arial", Font.BOLD, 22)); // FUENTE Y TAMAÑO DE LA FUENTE
        panelSecundarioTitulo.add(titulo); // LO AÑADIMOS AL PANEL SECUNDARIO TITULO
        
        // PANEL INVISIBLE, PARA QUE EL TITULO QUEDE A LA IZQUIERDA DEL PANEL TITULO
        
        JPanel panelInvisible = new JPanel(); 
        panelInvisible.setPreferredSize(new Dimension(210, 45)); // TAMAÑO
        panelInvisible.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelTitulo.add(panelInvisible); // LO AÑADIMOS AL PANEL TITULO
        
        // PANEL BOTÓN SALIR
        
        JPanel panelBtnSalir = new JPanel();
        panelBtnSalir.setPreferredSize(new Dimension(55, 55)); // TAMAÑO
        panelBtnSalir.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelSecundarioSuperior.add(panelBtnSalir); // LO AÑADIMOS AL PANEL SECUNDARIO SUPERIOR
        
        // BOTÓN SALIR
        
        btnSalir = new JButton("X"); // NOMBRE BOTÓN
        btnSalir.setPreferredSize(new Dimension(45, 45)); // TAMAÑO BOTON
        btnSalir.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnSalir.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnSalir.setFont(new Font("Arial", Font.BOLD, 16)); // FUENTE Y TAMAÑO DE LA FUENTE
        btnSalir.addActionListener(this); // ACTIONLISTENER
        panelBtnSalir.add(btnSalir); // LO AÑADIMOS AL PANEL BOTON SALIR
        
        // PANEL SUBTITULO
        
        JPanel panelSubtitulo = new JPanel();
        panelSubtitulo.setPreferredSize(new Dimension(490, 50)); // TAMAÑO
        panelSubtitulo.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelSecundarioSuperior.add(panelSubtitulo); // LO AÑADIMOS AL PANEL SECUNDARIO SUPERIOR
        
        // LABEL SUBTITULO
        
        JLabel subtitulo = new JLabel("Inicie sesion con su cuenta de Pandemic!"); // TEXTO
        subtitulo.setPreferredSize(new Dimension(460, 35)); // TAMAÑO
        subtitulo.setForeground(Color.WHITE); // COLO FUENTE
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 19)); // FUENTE Y TAMAÑO DE LA FUENTE
        panelSubtitulo.add(subtitulo); // LO AÑADIMOS AL PANEL SUBTITULO
        
        // PANEL SECUNDARIO CENTRAL
        
        JPanel panelSecundarioCentral = new JPanel();
        panelSecundarioCentral.setPreferredSize(new Dimension(505, 205)); // TAMAÑO
        panelSecundarioCentral.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelSecundario.add(panelSecundarioCentral); // LO AÑADIMOS AL PANEL SECUNDARIO
        
        // PANEL DATOS
        
        JPanel panelDatos = new JPanel();
        panelDatos.setPreferredSize(new Dimension(490, 135)); // TAMAÑO
        panelDatos.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelSecundarioCentral.add(panelDatos); // LO AÑADIMOS AL PANEL SECUNDARIO CENTRAL
        
        // PANEL USUARIO
        
        JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelUsuario.setPreferredSize(new Dimension(480, 60)); // TAMAÑO
        panelUsuario.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelDatos.add(panelUsuario); // LO AÑADIMOS AL PANEL DATOS
        
        // PANEL IMAGEN USUARIO
        
        JLabel panelImgUsuario = new JLabel();
        panelImgUsuario.setPreferredSize(new Dimension(60, 60)); // TAMAÑO
        panelImgUsuario.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelUsuario.add(panelImgUsuario, BorderLayout.EAST); // LO AÑADIMOS AL PANEL USUARIO
        
        // IMAGEN USUARIO
        
        BufferedImage imgUsuario = null;
        try {
        	imgUsuario = ImageIO.read(new File("imgUsuario.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Image dimgUsuario = imgUsuario.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        
        ImageIcon imageIconUsuario = new ImageIcon(dimgUsuario);
        
        panelImgUsuario.setIcon(imageIconUsuario);
        
        // PANEL LABEL USUARIO
        
        JPanel panelLabelUsuario = new JPanel();
        panelLabelUsuario.setPreferredSize(new Dimension(420, 60)); // TAMAÑO
        panelLabelUsuario.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelUsuario.add(panelLabelUsuario, BorderLayout.CENTER); // LO AÑADIMOS AL PANEL USUARIO
        
        // LABEL USUARIO
        
        textFieldUsuario = new JTextField();
        textFieldUsuario.setPreferredSize(new Dimension(410, 50)); // TAMAÑO
        textFieldUsuario.setBackground(Color.WHITE); // COLOR FONDO
        textFieldUsuario.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, Color.WHITE)); // BORDE, TAMAÑO Y COLOR DEL BORDE
        panelLabelUsuario.add(textFieldUsuario); // LO AÑADIMOS AL PANEL LABEL USUARIO
        
        // PANEL CONTRASEÑA
        
        JPanel panelContrasena = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelContrasena.setPreferredSize(new Dimension(480, 60)); // TAMAÑO
        panelContrasena.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelDatos.add(panelContrasena); // LO AÑADIMOS AL PANEL DATOS
        
        // PANEL IMAGEN CONTRASEÑA
        
        JLabel panelImgContrasena = new JLabel();
        panelImgContrasena.setPreferredSize(new Dimension(60, 60)); // TAMAÑO
        panelImgContrasena.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelContrasena.add(panelImgContrasena, BorderLayout.EAST); // LO AÑADIMOS AL PANEL CONTRASEÑA
        
        // IMAGEN CONTRASEÑA
        
        BufferedImage imgContrasena = null;
        try {
        	imgContrasena = ImageIO.read(new File("imgContrasena.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Image dimgContrasena = imgContrasena.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        
        ImageIcon imageIconContrasena = new ImageIcon(dimgContrasena);
        
        panelImgContrasena.setIcon(imageIconContrasena);
        
        // PANEL LABEL CONTRASEÑA
        
        JPanel panelLabelContrasena = new JPanel();
        panelLabelContrasena.setPreferredSize(new Dimension(420, 60)); // TAMAÑO
        panelLabelContrasena.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelContrasena.add(panelLabelContrasena, BorderLayout.CENTER); // LO AÑADIMOS AL PANEL CONTRASEÑA
        
        // LABEL CONTRASEÑA
        
        passwordFieldContrasena = new JPasswordField();
        passwordFieldContrasena.setPreferredSize(new Dimension(410, 50)); // TAMAÑO
        passwordFieldContrasena.setBackground(Color.WHITE); // COLOR FONDO
        passwordFieldContrasena.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, Color.WHITE)); // BORDE, TAMAÑO Y COLOR DEL BORDE
        panelLabelContrasena.add(passwordFieldContrasena); // LO AÑADIMOS AL PANEL LABEL CONTRASEÑA
                
        // PANEL BOTÓN INICIAR SESION
        
        JPanel panelBtnIniciarSesion = new JPanel() {

			private static final long serialVersionUID = -8948274014198817909L;

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
        panelBtnIniciarSesion.setPreferredSize(new Dimension(470, 55)); // TAMAÑO
        panelBtnIniciarSesion.setBackground(new Color(0x7F8487)); // COLOR FONDO
        panelSecundarioCentral.add(panelBtnIniciarSesion); // LO AÑADIMOS AL PANEL SECUNDARIO CENTRAL
        
        // BOTÓN INICIAR SESION
        
        btnIniciarSesion = new JButton("Iniciar Sesion"); // NOMBRE BOTÓN
        btnIniciarSesion.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnIniciarSesion.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 20)); // FUENTE Y TAMAÑO DE LA FUENTE
        btnIniciarSesion.setPreferredSize(new Dimension(460, 45)); // DIMENSIONES DEL BOTÓN
        btnIniciarSesion.addActionListener(this); // ACTIONLISTENER
        panelBtnIniciarSesion.add(btnIniciarSesion); // LO AÑADIMOS AL PANEL BOTÓN INICIAR SESION
        
        
        
        
        // ------------------------------------------------------ HACEMOS LA VENTANA VISIBLE ------------------------------------------------------ //
        
        setVisible(true);
        
    }
    
    static void guardarUsuario(JTextField labelUsuario) {
    	
    	try {
    		
    		BufferedWriter bw = new BufferedWriter(new FileWriter("usuario.txt", false));
    		bw.write(labelUsuario.getText().toLowerCase());
    		bw.close();
    		
    	} catch (IOException e) {
    		System.out.println("\nError al guardar el usuario: " + e);
    	}	
    	
    }
	
    /**
     * Metodo action listener para saber que boton hemos pulsado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	panelError.setVisible(false);
    	
    	if (e.getSource() == btnSalir) {
    		new VentanaPrincipal();
    		this.setVisible(false);
    	}
    	
    	if (e.getSource() == btnIniciarSesion) {
    		
    		String usuario = textFieldUsuario.getText().toLowerCase();
    		
    		char [] charContrasena = passwordFieldContrasena.getPassword();
    		String contrasena = new String(charContrasena);
    		
    		if(usuario.length() == 0 || contrasena.length() == 0) {
    			mensajeError.setText("No pueden haber campos vacios!");
    			panelError.setVisible(true);
    		} else {
    			ConexionBD conexion = new ConexionBD();
    			Connection con = conexion.conectarBaseDatos();
    			if(conexion.iniciarSesionJugador(con, usuario, contrasena) == false) {
        			mensajeError.setText("Usuario o contrasena invalidos!");
        			panelError.setVisible(true);
    			} else {
    				guardarUsuario(textFieldUsuario);
    				new VentanaMenu();
    	    		this.setVisible(false);
    			}
    		}
    		
    	}
    	
	}
    
    /**
     * Metodo main
     * @param args. Argumentos
     */
    public static void main(String[] args) {
        new VentanaIniciarSesion();
    }
    
}