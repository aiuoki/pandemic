import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.sql.Connection;
import java.util.regex.Pattern;

/**
 * 
 * Esta es la clase de la ventana registrarse
 * @author aiuoki
 *
 */
public class VentanaRegistrarse extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 26112968497333761L;
	
	/**
	 * Panel para el mensaje de error
	 */
	private JPanel panelError;
	/**
	 * Mensaje de error
	 */
	private JTextArea mensajeError;
	
	/**
	 * Area de texto para el usuario del jugador
	 */
	private JTextField textFieldUsuario;
	/**
	 * Area de contrasena para la contrasena del jugador
	 */
	private JPasswordField passwordFieldContrasena;
	/**
	 * Area de contrasena para la contrasena del jugador otra vez
	 */
	private JPasswordField passwordFieldContrasena2;
	
	/**
	 * Boton salir para volver a la ventana principal
	 */
	private JButton btnSalir;
	/**
	 * Boton registrarse
	 */
	private JButton btnRegistrarse;
	
	/**
	 * Ventana registrarse
	 */
    public VentanaRegistrarse() {
    	
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
		
		// PANEL GITHUB
        
        JPanel panelRegistrarse = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6807444795444745221L;

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
        panelRegistrarse.setLayout(new GridBagLayout());
        panelRegistrarse.setPreferredSize(new Dimension(525, 415)); // TAMAÑO DEL PANEL
        panelRegistrarse.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelCentral.add(panelRegistrarse); // LO AÑADIMOS AL PANEL PRINCIPAL
        
        // PANEL SECUNDARIO
        
        JPanel panelSecundario = new JPanel();
        panelSecundario.setPreferredSize(new Dimension(515, 405)); // TAMAÑO
        panelSecundario.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelRegistrarse.add(panelSecundario); // LO AMADIMOS AL PANEL REGISTRARSE
        
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
        
        JLabel titulo = new JLabel("Registrarse"); // TEXTO
        titulo.setPreferredSize(new Dimension(180, 35)); // TAMAÑO
        titulo.setForeground(Color.white); // COLOR FUENTE
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
        
        JLabel subtitulo = new JLabel("Crear una cuenta de Pandemic!"); // TEXTO
        subtitulo.setPreferredSize(new Dimension(460, 35)); // TAMAÑO
        subtitulo.setForeground(Color.white); // COLOR FUENTE
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 19)); // FUENTE Y TAMAÑO DE LA FUENTE
        panelSubtitulo.add(subtitulo); // LO AÑADIMOS AL PANEL SUBTITULO
        
        // PANEL SECUNDARIO CENTRAL
        
        JPanel panelSecundarioCentral = new JPanel();
        panelSecundarioCentral.setPreferredSize(new Dimension(505, 270)); // TAMAÑO
        panelSecundarioCentral.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelSecundario.add(panelSecundarioCentral); // LO AÑADIMOS AL PANEL SECUNDARIO
        
        // PANEL DATOS
        
        JPanel panelDatos = new JPanel();
        panelDatos.setPreferredSize(new Dimension(490, 200)); // TAMAÑO
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
        textFieldUsuario.setBackground(Color.white); // COLOR FONDO
        textFieldUsuario.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, Color.white)); // BORDE, TAMAÑO Y COLOR DEL BORDE
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
        passwordFieldContrasena.setBackground(Color.white); // COLOR FONDO
        passwordFieldContrasena.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, Color.white)); // BORDE, TAMAÑO Y COLOR DEL BORDE
        panelLabelContrasena.add(passwordFieldContrasena); // LO AÑADIMOS AL PANEL LABEL CONTRASEÑA
        
        // PANEL CONTRASEÑA 2
        
        JPanel panelContrasena2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelContrasena2.setPreferredSize(new Dimension(480, 60)); // TAMAÑO
        panelContrasena2.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelDatos.add(panelContrasena2); // LO AÑADIMOS AL PANEL DATOS
                
        // PANEL IMAGEN CONTRASEÑA
        
        JLabel panelImgContrasena2 = new JLabel();
        panelImgContrasena2.setPreferredSize(new Dimension(60, 60)); // TAMAÑO
        panelImgContrasena2.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelImgContrasena2.setIcon(imageIconContrasena); // AÑADIMOS LA IMAGEN CONTRASEÑA AL PANEL IMAGEN CONTRASEÑA 2
        panelContrasena2.add(panelImgContrasena2, BorderLayout.EAST); // LO AÑADIMOS AL PANEL CONTRASEÑA 2
        
        // PANEL LABEL CONTRASEÑA 2
        
        JPanel panelLabelContrasena2 = new JPanel();
        panelLabelContrasena2.setPreferredSize(new Dimension(420, 60)); // TAMAÑO
        panelLabelContrasena2.setBackground(Color.DARK_GRAY); // COLOR FONDO
        panelContrasena2.add(panelLabelContrasena2, BorderLayout.CENTER); // LO AÑADIMOS AL PANEL CONTRASEÑA 2
        
        // LABEL CONTRASEÑA 2
        
        passwordFieldContrasena2 = new JPasswordField();
        passwordFieldContrasena2.setPreferredSize(new Dimension(410, 50)); // TAMAÑO
        passwordFieldContrasena2.setBackground(Color.white); // COLOR FONDO
        passwordFieldContrasena2.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, Color.white)); // BORDE, TAMAÑO Y COLOR DEL BORDE
        panelLabelContrasena2.add(passwordFieldContrasena2); // LO AÑADIMOS AL PANEL LABEL CONTRASEÑA
        
        // PANEL BOTON REGISTRARSE
        
        JPanel panelBtnRegistrarse = new JPanel() {

			private static final long serialVersionUID = -8471582461956983944L;

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
        panelBtnRegistrarse.setPreferredSize(new Dimension(470, 55)); // TAMAÑO
        panelBtnRegistrarse.setBackground(new Color(0x7F8487)); // COLOR FONDO
        panelSecundarioCentral.add(panelBtnRegistrarse); // LO AÑADIMOS AL PANEL SECUNDARIO CENTRAL
        
        // BOTÓN REGISTRARSE
        
        btnRegistrarse = new JButton("Registrarse"); // NOMBRE BOTÓN
        btnRegistrarse.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnRegistrarse.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnRegistrarse.setFont(new Font("Arial", Font.BOLD, 20)); // FUENTE Y TAMAÑO DE LA FUENTE
        btnRegistrarse.setPreferredSize(new Dimension(460, 45)); // DIMENSIONES DEL BOTÓN
        btnRegistrarse.addActionListener(this); // ACTIONLISTENER
        panelBtnRegistrarse.add(btnRegistrarse); // LO AÑADIMOS AL PANEL SECUNDARIO
        
        
        
        
        // ------------------------------------------------------ HACEMOS LA VENTANA VISIBLE ------------------------------------------------------ //
        
        setVisible(true);
        
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
    	
    	if (e.getSource() == btnRegistrarse) {
    		
    		Pattern patternUsuario = Pattern.compile("^[a-zA-Z0-9]{1,35}$");
    		Pattern patternContrasena = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&+=])(?=\\S+$).{9,128}$");
    		
    		String usuario = textFieldUsuario.getText();
    		
    		char [] charContrasena = passwordFieldContrasena.getPassword();
    		String contrasena = new String(charContrasena);
    		
    		char [] charContrasena2 = passwordFieldContrasena2.getPassword();
    		String contrasena2 = new String(charContrasena2);
    		
    		if(usuario.length() == 0 || contrasena.length() == 0 || contrasena2.length() == 0) {
    			mensajeError.setText("No pueden haber campos vacios!");
    			panelError.setVisible(true);
    		} else if(usuario.length() > 35) {
    			mensajeError.setText("La longitud maxima para el usuario es 35!");
    			panelError.setVisible(true);
    		} else if(!patternUsuario.matcher(usuario).matches()) {
    			mensajeError.setText("El usuario solo admite letras y numeros!");
    			panelError.setVisible(true);
    		} else if(contrasena.length() < 9) {
    			mensajeError.setText("La longitud minima para la contrasena es 9!");
    			panelError.setVisible(true);
    		} else if(contrasena.length() > 128) {
    			mensajeError.setText("La longitud maxima para la contrasena es 128!");
    			panelError.setVisible(true);
    		} else if(!patternContrasena.matcher(contrasena).matches()) {
    			mensajeError.setText("La contrasena debe contener al menos una \nletra mayúscula, una letra minúscula, un dígito \ny un carácter especial!");
    			panelError.setVisible(true);
    		} else if(!contrasena2.equals(contrasena)) {
    			mensajeError.setText("Las dos contrasenas deben coincidir!");
    			panelError.setVisible(true);
    		} else {
    			ConexionBD conexion = new ConexionBD();
    			Connection con = conexion.conectarBaseDatos();
    			if(conexion.comprobarUsuario(con, usuario.toLowerCase()) == true) {
        			mensajeError.setText("El nombre de usuario ya esta en uso!");
        			panelError.setVisible(true);
    			} else {
    				conexion.registrarJugador(con, usuario.toLowerCase(), usuario, contrasena);
		    		new VentanaPrincipal();
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
        new VentanaRegistrarse();
    }
    
}