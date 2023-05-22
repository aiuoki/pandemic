import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;

/**
 * 
 * Esta es la clase de la ventana autores
 * @author aiuoki
 *
 */
public class VentanaAutores extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = -2060280248887607474L;
	
	/**
	 * Boton que nos lleva al github del autor1
	 */
	private JButton btnAutor1;
	
	/**
	 * Boton que nos lleva al github del autor2
	 */
	private JButton btnAutor2;
	
	/**
	 * Boton que nos lleva al github del autor3
	 */
	private JButton btnAutor3;
	
	/**
	 * Boton para volver a la ventana menu
	 */
	private JButton btnVolver;
	
	/**
	 * Ventana autores
	 */
	public VentanaAutores() {
		
		// ------------------------------------------------------ VENTANA ------------------------------------------------------ //
    	
    	// CONFIGURAR VENTANA
    	
        setTitle("Pandemic"); // TITULO VENTANA
        setExtendedState(JFrame.MAXIMIZED_BOTH); // VENTANA MAXIMIZADA POR DEFECTO
        setSize(1700, 900); // TAMAÑO DE LA VENTANA AL MINIMIZARLA
        setMinimumSize(new Dimension(1600, 800)); // MINIMO ESCALABLE
        setLocationRelativeTo(null); // CENTRAR VENTANA
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // SALIR AL PULSAR LA X
        
        // ICONO VENTANA
        
		ImageIcon imgLogo = new ImageIcon("imgLogo.png"); // IMAGEN LOGO
		setIconImage(imgLogo.getImage()); // LA AÑADIMOS A LA VENTANA
        
		
		
		
		// ------------------------------------------------------ PANEL CENTRAL ------------------------------------------------------ //
		
        // CONFIGURAR PANEL CENTRAL
        
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelCentral.setLayout(new GridBagLayout()); // USAMOS GridBagLayout PARA CENTRAR EL PANEL SECUNDARIO
        add(panelCentral, BorderLayout.CENTER); //  LO AÑADIMOS A LA VENTANA
        
        // JLABEL TITULO
        
        JLabel titulo = new JLabel("Autores"); // TEXTO
        titulo.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        titulo.setFont(new Font("Arial", Font.BOLD, 36)); // FUENTE Y TAMAÑO DE LA FUENTE
        panelCentral.add(titulo); // LO AÑADIMOS AL PANEL PRINCIPAL
        
        // PANEL SECUNDARIO
        
        JPanel panelSecundario = new JPanel();
        panelSecundario.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelSecundario.setPreferredSize(new Dimension(1500, 600)); // TAMAÑO DEL PANEL
        panelSecundario.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 60)); // ESPACIO ENTRE BOTONES
        
        GridBagConstraints gbc = new GridBagConstraints(); // PARAMETROS PARA POSICIONAR EL PANEL DEBAJO DEL TITULO
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 10);
        
        panelCentral.add(panelSecundario, gbc); //  LO AÑADIMOS AL PANEL PRINCIPAL
        
        // PANEL AUTOR 1
        
        JPanel panelAutor1 = new JPanel();
        panelAutor1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 23));
        panelAutor1.setPreferredSize(new Dimension(400, 600)); // TAMAÑO DEL PANEL
        panelAutor1.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelSecundario.add(panelAutor1); //  LO AÑADIMOS AL PANEL SECUNDARIO
        
        // LABEL IMAGEN 1
        
        JLabel labelImgAutor1 = new JLabel();
        labelImgAutor1.setPreferredSize(new Dimension(330, 400));
        panelAutor1.add(labelImgAutor1);
        
        // IMAGEN 1
        
        BufferedImage imgAutor1 = null;
        try {
        	imgAutor1 = ImageIO.read(new File("imgAutor1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Image dimgAutor1 = imgAutor1.getScaledInstance(330, 400, Image.SCALE_SMOOTH);
        
        ImageIcon imageIconAutor1 = new ImageIcon(dimgAutor1);
        
        labelImgAutor1.setIcon(imageIconAutor1);
        
        // PANEL BOTON AUTOR 1
        
        JPanel panelBtnAutor1 = new JPanel() {

			private static final long serialVersionUID = -5853853983336407403L;

			@Override
            protected void paintComponent(Graphics g) { // USAMOS EL METODO paintComponent PARA DIBUJAR UN BORDE REDONDEADO
                Graphics2D g2 = (Graphics2D) g.create();
                int x = 0;
                int y = 0;
                int w = getWidth();
                int h = getHeight();
                int arc = 10;
                RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, w-1, h-1, arc, arc);
                g2.setClip(rect);
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        panelBtnAutor1.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBtnAutor1.setPreferredSize(new Dimension(330, 75)); // TAMAÑO DEL PANEL
        panelBtnAutor1.setBackground(new Color(0x7F8487)); // COLOR FONDO
        panelAutor1.add(panelBtnAutor1); //  LO AÑADIMOS AL PANEL SECUNDARIO
        
        // BOTON AUTOR 1
        
        btnAutor1 = new JButton("Alex Plamen"); // NOMBRE BOTÓN
        btnAutor1.setPreferredSize(new Dimension(320, 65)); // TAMAÑO BOTON
        btnAutor1.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnAutor1.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnAutor1.setFont(new Font("Arial", Font.BOLD, 18)); // FUENTE Y TAMAÑO DE LA FUENTE
        btnAutor1.addActionListener(this); // ACTIONLISTENER
        panelBtnAutor1.add(btnAutor1);
                
        // PANEL AUTOR 2
        
        JPanel panelAutor2 = new JPanel();
        panelAutor2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 23));
        panelAutor2.setPreferredSize(new Dimension(400, 600)); // TAMAÑO DEL PANEL
        panelAutor2.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelSecundario.add(panelAutor2); //  LO AÑADIMOS AL PANEL SECUNDARIO
        
        // LABEL IMAGEN 2
        
        JLabel labelImgAutor2 = new JLabel();
        labelImgAutor2.setPreferredSize(new Dimension(330, 400));
        panelAutor2.add(labelImgAutor2);
        
        // IMAGEN 2
        
        BufferedImage imgAutor2 = null;
        try {
        	imgAutor2 = ImageIO.read(new File("imgAutor2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Image dimgAutor2 = imgAutor2.getScaledInstance(330, 400, Image.SCALE_SMOOTH);
        
        ImageIcon imageIconAutor2 = new ImageIcon(dimgAutor2);
        
        labelImgAutor2.setIcon(imageIconAutor2);
        
        // PANEL BOTON AUTOR 2
        
        JPanel panelBtnAutor2 = new JPanel() {

			private static final long serialVersionUID = 4402908638202386113L;

			@Override
            protected void paintComponent(Graphics g) { // USAMOS EL METODO paintComponent PARA DIBUJAR UN BORDE REDONDEADO
                Graphics2D g2 = (Graphics2D) g.create();
                int x = 0;
                int y = 0;
                int w = getWidth();
                int h = getHeight();
                int arc = 10;
                RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, w-1, h-1, arc, arc);
                g2.setClip(rect);
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        panelBtnAutor2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBtnAutor2.setPreferredSize(new Dimension(330, 75)); // TAMAÑO DEL PANEL
        panelBtnAutor2.setBackground(new Color(0x7F8487)); // COLOR FONDO
        panelAutor2.add(panelBtnAutor2); //  LO AÑADIMOS AL PANEL SECUNDARIO
        
        // BOTON AUTOR 2
        
        btnAutor2 = new JButton("Daniel Ceban"); // NOMBRE BOTÓN
        btnAutor2.setPreferredSize(new Dimension(320, 65)); // TAMAÑO BOTON
        btnAutor2.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnAutor2.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnAutor2.setFont(new Font("Arial", Font.BOLD, 18)); // FUENTE Y TAMAÑO DE LA FUENTE
        btnAutor2.addActionListener(this); // ACTIONLISTENER
        panelBtnAutor2.add(btnAutor2);
        
        // PANEL AUTOR 3
        
        JPanel panelAutor3 = new JPanel();
        panelAutor3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 23));
        panelAutor3.setPreferredSize(new Dimension(400, 600)); // TAMAÑO DEL PANEL
        panelAutor3.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelSecundario.add(panelAutor3); //  LO AÑADIMOS AL PANEL SECUNDARIO
        
        // LABEL IMAGEN 3
        
        JLabel labelImgAutor3 = new JLabel();
        labelImgAutor3.setPreferredSize(new Dimension(330, 400));
        panelAutor3.add(labelImgAutor3);
        
        // IMAGEN 3
        
        BufferedImage imgAutor3 = null;
        try {
        	imgAutor3 = ImageIO.read(new File("imgAutor3.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Image dimgAutor3 = imgAutor3.getScaledInstance(330, 400, Image.SCALE_SMOOTH);
        
        ImageIcon imageIconAutor3 = new ImageIcon(dimgAutor3);
        
        labelImgAutor3.setIcon(imageIconAutor3);
        
        // PANEL BOTON AUTOR 3
        
        JPanel panelBtnAutor3 = new JPanel() {

			private static final long serialVersionUID = -5860972323349902282L;

			@Override
            protected void paintComponent(Graphics g) { // USAMOS EL METODO paintComponent PARA DIBUJAR UN BORDE REDONDEADO
                Graphics2D g2 = (Graphics2D) g.create();
                int x = 0;
                int y = 0;
                int w = getWidth();
                int h = getHeight();
                int arc = 10;
                RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, w-1, h-1, arc, arc);
                g2.setClip(rect);
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        panelBtnAutor3.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBtnAutor3.setPreferredSize(new Dimension(330, 75)); // TAMAÑO DEL PANEL
        panelBtnAutor3.setBackground(new Color(0x7F8487)); // COLOR FONDO
        panelAutor3.add(panelBtnAutor3); //  LO AÑADIMOS AL PANEL SECUNDARIO
        
        // BOTON AUTOR 3
        
        btnAutor3 = new JButton("Eudald Codina"); // NOMBRE BOTÓN
        btnAutor3.setPreferredSize(new Dimension(320, 65)); // TAMAÑO BOTON
        btnAutor3.setBackground(new Color(0x7F8487)); // COLOR FONDO
        btnAutor3.setForeground(new Color(255, 255, 255)); // COLOR FUENTE
        btnAutor3.setFont(new Font("Arial", Font.BOLD, 18)); // FUENTE Y TAMAÑO DE LA FUENTE
        btnAutor3.addActionListener(this); // ACTIONLISTENER
        panelBtnAutor3.add(btnAutor3);
        
        
        
        
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
    	
    	if (e.getSource() == btnAutor1) {
    		try {
    			Desktop.getDesktop().browse(new URI("https://github.com/alexPlamen123"));
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    	
    	if (e.getSource() == btnAutor2) {
    		try {
    			Desktop.getDesktop().browse(new URI("https://github.com/aiuoki"));
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    	
    	if (e.getSource() == btnAutor3) {
    		try {
    			Desktop.getDesktop().browse(new URI("https://github.com/eudaldcodinacomes"));
    		} catch (Exception ex) {
    			ex.printStackTrace();
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
        new VentanaAutores();
    }
    
}