import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

/**
 * 
 * Esta es la clase de la ventana dificultad
 * @author aiuoki
 *
 */
public class VentanaDificultad extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -5677897039771397772L;
	
	/**
	 * Boton facil, que cambia el parametro dificultad a facil e inicia una partida nueva
	 */
	private JButton btnFacil;
	
	/**
	 * Boton normal, que cambia el parametro dificultad a normal e inicia una partida nueva
	 */
	private JButton btnNormal;
	
	/**
	 * Boton dificil, que cambia el parametro dificultad a dificil e inicia una partida nueva
	 */
	private JButton btnDificil;
	
	/**
	 * Boton volver, que nos lleva al menu
	 */
	private JButton btnVolver;
	
	/**
	 * Ventana dificultad
	 */
	public VentanaDificultad() {
		
		// ------------------------------------------------------ VENTANA ------------------------------------------------------ //
    	
    	// CONFIGURAR VENTANA
    	
        setTitle("Pandemic"); // TITULO VENTANA
        setExtendedState(JFrame.MAXIMIZED_BOTH); // VENTANA MAXIMIZADA POR DEFECTO
        setSize(1600, 900); // TAMAÑO DE LA VENTANA AL MINIMIZARLA
        setMinimumSize(new Dimension(1550, 800)); // MINIMO ESCALABLE
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
        
        JLabel titulo = new JLabel("Dificultad"); // TEXTO
        titulo.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        titulo.setFont(new Font("Arial", Font.BOLD, 36)); // FUENTE Y TAMAÑO DE LA FUENTE
        panelCentral.add(titulo); // LO AÑADIMOS AL PANEL PRINCIPAL
        
        // PANEL SECUNDARIO
        
        JPanel panelSecundario = new JPanel();
        panelSecundario.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelSecundario.setPreferredSize(new Dimension(1500, 500)); // TAMAÑO DEL PANEL
        panelSecundario.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 200)); // ESPACIO ENTRE BOTONES
        
        GridBagConstraints gbc = new GridBagConstraints(); // PARAMETROS PARA POSICIONAR EL PANEL DEBAJO DEL TITULO
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 10);
        
        panelCentral.add(panelSecundario, gbc); //  LO AÑADIMOS AL PANEL PRINCIPAL
        
        // PANEL FACIL
        
        JPanel panelFacil = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -1908135127986741743L;

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
        panelFacil.setLayout(new GridBagLayout());
        panelFacil.setPreferredSize(new Dimension(380, 100)); // TAMAÑO DEL PANEL
        panelFacil.setBackground(new Color(0x7F8487)); // COLOR FONDO
        panelSecundario.add(panelFacil); //  LO AÑADIMOS AL PANEL SECUNDARIO
        
        // BOTON FACIL

        btnFacil = new JButton("Fácil"); // NOMBRE BOTÓN
        btnFacil.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnFacil.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnFacil.setFont(new Font("Arial", Font.BOLD, 20)); // FUENTE Y TAMAÑO DE LA FUENTE
        btnFacil.setPreferredSize(new Dimension(370, 90)); // DIMENSIONES DEL BOTON
        btnFacil.addActionListener(this); // ACTIONLISTENER
        panelFacil.add(btnFacil); //  LO AÑADIMOS AL PANEL FACIL
        
        // PANEL NORMAL
        
        JPanel panelNormal = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -8394275460019330253L;

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
        panelNormal.setLayout(new GridBagLayout());
        panelNormal.setPreferredSize(new Dimension(380, 100)); // TAMAÑO DEL PANEL
        panelNormal.setBackground(new Color(0x7F8487)); // COLOR FONDO
        panelSecundario.add(panelNormal); //  LO AÑADIMOS AL PANEL SECUNDARIO
        
        // BOTON NORMAL

        btnNormal = new JButton("Normal"); // NOMBRE BOTÓN
        btnNormal.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnNormal.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnNormal.setFont(new Font("Arial", Font.BOLD, 20)); // FUENTE Y TAMAÑO DE LA FUENTE
        btnNormal.setPreferredSize(new Dimension(370, 90)); // DIMENSIONES DEL BOTON
        btnNormal.addActionListener(this); // ACTIONLISTENER
        panelNormal.add(btnNormal); //  LO AÑADIMOS AL PANEL NORMAL
        
        // PANEL DIFICIL
        
        JPanel panelDificil = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6503460573955330663L;

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
        panelDificil.setLayout(new GridBagLayout());
        panelDificil.setPreferredSize(new Dimension(380, 100)); // TAMAÑO DEL PANEL
        panelDificil.setBackground(new Color(0x7F8487)); // COLOR FONDO
        panelSecundario.add(panelDificil); //  LO AÑADIMOS AL PANEL SECUNDARIO
        
        // BOTON DIFICIL

        btnDificil = new JButton("Difícil"); // NOMBRE BOTÓN
        btnDificil.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnDificil.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnDificil.setFont(new Font("Arial", Font.BOLD, 20)); // FUENTE Y TAMAÑO DE LA FUENTE
        btnDificil.setPreferredSize(new Dimension(370, 90)); // DIMENSIONES DEL BOTON
        btnDificil.addActionListener(this); // ACTIONLISTENER
        panelDificil.add(btnDificil); //  LO AÑADIMOS AL PANEL DIFICIL
        
        
        
        
        // ------------------------------------------------------ PANEL INFERIOR ------------------------------------------------------ //
        
        // PANEL INFERIOR
        
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        add(panelInferior, BorderLayout.PAGE_END); // AÑADIMOS EL PANEL DEL BOTON AL FINAL DE LA VENTANA
        
        // BOTON VOLVER
        
        btnVolver = new JButton("Volver"); // NOMBRE BOTÓN
        btnVolver.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnVolver.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnVolver.setPreferredSize(new Dimension(100, 40)); // TAMAÑO DEL PANEL
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
    	
    	if (e.getSource() == btnFacil) {
    		cambiarDificultad("Facil");
    		new VentanaPartida();
    		this.setVisible(false);
    		
    	}
    	
    	if (e.getSource() == btnNormal) {
    		cambiarDificultad("Normal");
    		new VentanaPartida();
    		this.setVisible(false);
    		
    	}
    	
    	if (e.getSource() == btnDificil) {
    		cambiarDificultad("Dificil");
    		new VentanaPartida();
    		this.setVisible(false);
    		
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
        new VentanaDificultad();
    }
    
    /**
     * Metodo encargado de cambiar el parametro dificultad en el archivo parametros.xml
     * @param dificultad. Este parametro indica la dificultad que se va a asignar al parametro
     */
	static void cambiarDificultad(String dificultad) {
		
		// PROGRAMA
		
		try {
			
			File archivo = new File("parametros.xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();
			Element elemento = (Element) document.getElementsByTagName("parametros").item(0);
			
			// APLICAMOS LOS NUEVOS DATOS A CADA UNO DE LOS ELEMENTOS DENTRO DE "parametros"
			
			elemento.getElementsByTagName("dificultad").item(0).setTextContent(dificultad);
			
			FileOutputStream archivoSalida = new FileOutputStream(archivo); // CREAMOS UN OBJETO FILEOUTPUTSTREAM PARA ESCRIBIR BYTES
			
			javax.xml.transform.TransformerFactory.newInstance().newTransformer().transform(new javax.xml.transform.dom.DOMSource(document), new javax.xml.transform.stream.StreamResult(archivoSalida));
			archivoSalida.close();
	        
			System.out.println("\nDificultad modificada!");
			
		} catch(Exception e) {
			
			System.out.println("\nError al modificar los parametros: " + e);
			
		}
		
	}
    
}