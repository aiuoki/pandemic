import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * 
 * Esta es la clase de la ventana cargar partida
 * @author aiuoki
 *
 */
public class VentanaCargarPartida extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 5726097660100280514L;
	
	/**
	 * Usuario del jugador que ha iniciado sesion
	 */
	static String usuario;
	
	/**
	 * Area de texto para introducir el id de la partida que queremos continuar
	 */
	JTextField textFieldIdPartida;
	
	/**
	 * Label para mostrar error en caso de haberlo
	 */
	private JLabel labelError;
	
	/**
	 * Boton continuar que nos lleva a la ventana continuar partida
	 */
	private JButton btnContinuar;
	
	/**
	 * Boton volver que nos lleva a la ventana menu
	 */
	private JButton btnVolver;
	
	/**
	 * Ventana cargar partida
	 */
    public VentanaCargarPartida() {
    	
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
		
		// JLABEL TITULO
        
        JLabel titulo = new JLabel("Tus partidas"); // TEXTO
        titulo.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        titulo.setFont(new Font("Arial", Font.BOLD, 36)); // FUENTE Y TAMAÑO
        panelPrincipal.add(titulo); // LO AÑADIMOS AL PANEL
        
        // PANEL SECUNDARIO
        
        JPanel panelSecundario = new JPanel() {

			private static final long serialVersionUID = 1265307803496841702L;

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
        panelSecundario.setPreferredSize(new Dimension(540, 490)); // TAMAÑO DEL PANEL
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
		
		// PANEL PARTIDAS
		
		JPanel panelPartidas = new JPanel();
		panelPartidas.setLayout(new GridBagLayout());
		panelPartidas.setPreferredSize(new Dimension(480, 435)); // TAMAÑO DEL PANEL
		panelPartidas.setBackground(new Color(13, 13, 13)); // COLOR FONDO
		panelSecundario.add(panelPartidas);
		
		// TABLA RESUMEN PUNTUACIONES
		
		JTable tablaPartidas = new JTable();
		tablaPartidas.setPreferredSize(new Dimension(450, 450)); // TAMAÑO
		tablaPartidas.setBackground(new Color(13, 13, 13)); // COLOR FONDO
		tablaPartidas.setForeground(Color.WHITE); // COLOR FUENTE
		tablaPartidas.setFont(new Font("Arial", Font.PLAIN, 12)); // FUENTE Y TAMAÑO DE LA FUENTE
		tablaPartidas.setEnabled(false); // HACEMOS QUE NO SEA EDITABLE
        
        // PANEL SCROLL
        
        JScrollPane panelScroll = new JScrollPane(tablaPartidas); // AÑADIMOS LA TABLA RESUMEN PUNTUACIONES AL PANEL SCROLL
        panelPartidas.add(panelScroll); // LO AÑADIMOS AL PANEL SECUNDARIO
        
        // CONEXION CON LA BASE DE DATOS
        
        usuario = leerUsuario();
        
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.conectarBaseDatos();
        
        try {
        	// HACEMOS UN SELECT DEL NOMBRE Y EL NUMERO DE VICTORIAS DEL JUGADOR Y LO ORDENAMOS DE MAYOR A MENOR
            String consulta = "SELECT idPartida, dificultad, ronda FROM PARTIDA WHERE usuario LIKE '" + usuario + "' AND estado LIKE 'E' ORDER BY fecha DESC";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            DefaultTableModel tabla = new DefaultTableModel();
            
            tabla.addColumn("idPartida"); // AÑADIMOS LA COLUMNA POSICION
            tabla.addColumn("Dificultad"); // AÑADIMOS LA COLUMNA NOMBRE
            tabla.addColumn("Ronda"); // AÑADIMOS LA COLUMNA VICTORIAS
            
            Object[] fila = new Object[3];
            
            while (rs.next()) { // SALDREMOS DEL BUCLE CUANDO EL SELECT NO DEVUELVA MAS DATOS
                fila[0] = rs.getString(1); // EN LA PRIMERA FILA PONDREMOS LA POSICION
                fila[1] = rs.getString(2); // EN LA SEGUNA FILA PONDREMOS EL NOMBRE
                fila[2] = rs.getInt(3); // EN LA TERCERA FILA PONDREMOS EL NUMERO DE VICTORIAS
                tabla.addRow(fila); // AÑADIMOS LA FILA A LA TABLA
            }
            
            tablaPartidas.setModel(tabla); // AÑADIMOS LA TABLA A LA TABLA RESUMEN PUNTUACIONES
        } catch (SQLException e) {
        	System.out.println("Error: " + e);
        }
        
        //
        
        JPanel panelInput = new JPanel() {

			private static final long serialVersionUID = 8181521111382021848L;

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
        panelInput.setPreferredSize(new Dimension(320, 186)); // TAMAÑO DEL PANEL
        panelInput.setBackground(new Color(13, 13, 13)); // COLOR FONDO
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.insets = new Insets(15, 10, 10, 10);
        panelPrincipal.add(panelInput, gbc);
        
        //
        
        JPanel panelTituloIdPartida = new JPanel(new BorderLayout());
        panelTituloIdPartida.setPreferredSize(new Dimension(300, 30)); // TAMAÑO DEL PANEL
        panelTituloIdPartida.setBackground(new Color(13, 13, 13)); // COLOR FONDO
        panelInput.add(panelTituloIdPartida);
        
        //
        
        JLabel labelTituloIdPartida = new JLabel("Introduzca una partida:");
        labelTituloIdPartida.setFont(new Font("Arial", Font.BOLD, 20)); // FUENTE Y TAMAÑO DE LA FUENTE
        labelTituloIdPartida.setForeground(Color.white);
        panelTituloIdPartida.add(labelTituloIdPartida);
        
        //
        
        JPanel panelTextFieldIdPartida = new JPanel();
        panelTextFieldIdPartida.setPreferredSize(new Dimension(300, 50)); // TAMAÑO DEL PANEL
        panelTextFieldIdPartida.setBackground(new Color(13, 13, 13)); // COLOR FONDO
        panelInput.add(panelTextFieldIdPartida);
        
        //
        
        textFieldIdPartida = new JTextField();
        textFieldIdPartida.setPreferredSize(new Dimension(300, 40)); // TAMAÑO
        textFieldIdPartida.setBackground(Color.WHITE); // COLOR FONDO
        textFieldIdPartida.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, Color.WHITE)); // BORDE, TAMAÑO Y COLOR DEL BORDE
        panelTextFieldIdPartida.add(textFieldIdPartida); // LO AÑADIMOS AL PANEL LABEL USUARIO
        
        //
        
        JPanel panelLabelError = new JPanel(new BorderLayout());
        panelLabelError.setPreferredSize(new Dimension(300, 30)); // TAMAÑO DEL PANEL
        panelLabelError.setBackground(new Color(13, 13, 13)); // COLOR FONDO
        panelInput.add(panelLabelError);
        
        //
        
        labelError = new JLabel("Introduzca una partida valida!");
        labelError.setFont(new Font("Arial", Font.PLAIN, 16)); // FUENTE Y TAMAÑO DE LA FUENTE
        labelError.setForeground(Color.red);
        labelError.setVisible(false);
        panelLabelError.add(labelError);
        
        //
        
        JPanel panelBtnContinuar = new JPanel();
        panelBtnContinuar.setPreferredSize(new Dimension(300, 50)); // TAMAÑO DEL PANEL
        panelBtnContinuar.setBackground(new Color(13, 13, 13)); // COLOR FONDO
        panelInput.add(panelBtnContinuar);
        
        //
        
        btnContinuar = new JButton("Continuar"); // NOMBRE BOTÓN
        btnContinuar.setPreferredSize(new Dimension(100, 40)); // TAMAÑO BOTON
        btnContinuar.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnContinuar.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnContinuar.addActionListener(this); // ACTIONLISTENER
        panelBtnContinuar.add(btnContinuar); // AÑADIMOS EL BOTON VOLVER AL PANEL
        
        
        
        
        // ------------------------------------------------------ PANEL INFERIOR ------------------------------------------------------ //
        
        // PANEL INFERIOR
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        add(panelInferior, BorderLayout.PAGE_END); // AÑADIMOS EL PANEL DEL BOTON AL FINAL DE LA VENTANA
        
        // BOTON VOLVER
        
        btnVolver = new JButton("Volver"); // NOMBRE BOTÓN
        btnVolver.setPreferredSize(new Dimension(100, 40)); // TAMAÑO BOTON
        btnVolver.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnVolver.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
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
    	
    	if (e.getSource() == btnContinuar) {
    		
    		String p_usuario = leerUsuario();
    		String idPartida = textFieldIdPartida.getText();
    		
    		if(idPartida.length() == 0) {
    			labelError.setVisible(true);
    		} else {
    			
    			int p_idPartida = Integer.parseInt(idPartida);
    			
    			ConexionBD conexion = new ConexionBD();
    			Connection con = conexion.conectarBaseDatos();
    			
    			if(conexion.comprobarPartida(con, p_idPartida, p_usuario) == false) {
    				labelError.setVisible(true);
    			} else {
    				
    				guardarIdPartida(idPartida);
    				new VentanaContinuarPartida();
    				this.setVisible(false);
    				
    			}
    			
    		}
    		
    	}
		
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
        new VentanaCargarPartida();
    }
    
    /**
     * Metodo leer usuario, que lee el archivo que guarda el usuario con el que hemos iniciado sesion
     * @return usuario. Devuelve el usuario con el que hemos iniciado sesion
     */
    static String leerUsuario() {
    	
    	String usuario = "";
    	
    	try {
    		
    		BufferedReader br = new BufferedReader(new FileReader("usuario.txt"));
    		usuario = br.readLine();
    		br.close();
    		
    	} catch (IOException e) {
    		System.out.println("\nError al leer el usuario: " + e);
    	}	
    	
    	return usuario;
    	
    }
    
    /**
     * Metodo guardar id partida, en caso de continuar la partida vamos a guardar el id de la partida en un archivo txt
     * @param p_idPartida. Id de la partida que se va a continuar
     */
    static void guardarIdPartida(String p_idPartida) {
    	
    	try {
    		
    		BufferedWriter bw = new BufferedWriter(new FileWriter("idPartida.txt", false));
    		bw.write(p_idPartida);
    		bw.close();
    		
    	} catch (IOException e) {
    		System.out.println("\nError al guardar el id de la partida: " + e);
    	}	
    	
    }

}
