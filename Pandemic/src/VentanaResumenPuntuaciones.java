import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * 
 * Esta es la clase de la ventana resumen de puntuaciones
 * @author aiuoki
 *
 */
public class VentanaResumenPuntuaciones extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 5726097660100280514L;
	
	/**
	 * Boton volver que nos lleva a la ventana menu
	 */
	private JButton btnVolver;
	
	/**
	 * Ventana resumen puntuaciones
	 */
    public VentanaResumenPuntuaciones() {
    	
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
        
        JLabel titulo = new JLabel("Resumen de puntuaciones"); // TEXTO
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
        panelSecundario.setPreferredSize(new Dimension(520, 470)); // TAMAÑO DEL PANEL
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
		
		// TABLA RESUMEN PUNTUACIONES
		
		JTable tablaResumenPuntuaciones = new JTable();
		tablaResumenPuntuaciones.setPreferredSize(new Dimension(450, 450)); // TAMAÑO
		tablaResumenPuntuaciones.setBackground(new Color(13, 13, 13)); // COLOR FONDO
		tablaResumenPuntuaciones.setForeground(Color.WHITE); // COLOR FUENTE
		tablaResumenPuntuaciones.setFont(new Font("Arial", Font.PLAIN, 12)); // FUENTE Y TAMAÑO DE LA FUENTE
		tablaResumenPuntuaciones.setEnabled(false); // HACEMOS QUE NO SEA EDITABLE
        
        // PANEL SCROLL
        
        JScrollPane panelScroll = new JScrollPane(tablaResumenPuntuaciones); // AÑADIMOS LA TABLA RESUMEN PUNTUACIONES AL PANEL SCROLL
        panelSecundario.add(panelScroll); // LO AÑADIMOS AL PANEL SECUNDARIO
        
        // CONEXION CON LA BASE DE DATOS
        
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.conectarBaseDatos();
        
        try {
        	// HACEMOS UN SELECT DEL NOMBRE Y EL NUMERO DE VICTORIAS DEL JUGADOR Y LO ORDENAMOS DE MAYOR A MENOR
            String consulta = "SELECT nombre, numVictorias FROM JUGADOR ORDER BY numVictorias DESC";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            DefaultTableModel tabla = new DefaultTableModel();
            
            tabla.addColumn("Posición"); // AÑADIMOS LA COLUMNA POSICION
            tabla.addColumn("Nombre"); // AÑADIMOS LA COLUMNA NOMBRE
            tabla.addColumn("Victorias"); // AÑADIMOS LA COLUMNA VICTORIAS
            
            int posicion = 1; // CONTADOR DE POSICION
            Object[] fila = new Object[3];
            
            while (rs.next()) { // SALDREMOS DEL BUCLE CUANDO EL SELECT NO DEVUELVA MAS DATOS
                fila[0] = posicion++; // EN LA PRIMERA FILA PONDREMOS LA POSICION
                fila[1] = rs.getString(1); // EN LA SEGUNA FILA PONDREMOS EL NOMBRE
                fila[2] = rs.getInt(2); // EN LA TERCERA FILA PONDREMOS EL NUMERO DE VICTORIAS
                tabla.addRow(fila); // AÑADIMOS LA FILA A LA TABLA
            }
            
            tablaResumenPuntuaciones.setModel(tabla); // AÑADIMOS LA TABLA A LA TABLA RESUMEN PUNTUACIONES
        } catch (SQLException e) {
        	System.out.println("Error: " + e);
        }
        
        
        
        
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
        new VentanaResumenPuntuaciones();
    }

}
