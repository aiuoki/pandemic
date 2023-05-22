import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

/**
 * 
 * Esta es la clase de la ventana continuar partida
 * @author aiuoki
 *
 */
public class VentanaContinuarPartida extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1116923523730086506L;
	
	/**
	 * Usuario del jugador con el que hemos iniciado sesion
	 */
	static String usuario;
	
	/**
	 * Id de la partida
	 */
	static int idPartida;
	
	/**
	 * Dificultad de la partida
	 */
	static String dificultad;
	
	// VARIABLES PARTIDA
	
	/**
	 * Parametros de la partida
	 */
	static int [] parametros = new int [5];
	
	/**
	 * Ciudades
	 */
	static ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
	
	/**
	 * Vacuna alfa
	 */
	static Vacuna alfa = new Vacuna("Alfa");
	
	/**
	 * Vacuna beta
	 */
	static Vacuna beta = new Vacuna("Beta");
	
	/**
	 * Vacuna gama
	 */
	static Vacuna gama = new Vacuna("Gama");
	
	/**
	 * Vacuna delta
	 */
	static Vacuna delta = new Vacuna("Delta");
	
	/**
	 * Ronda de la partida
	 */
	static int ronda = 1;
	
	/**
	 * Acciones de la partida
	 */
	static int acciones = 4;
	
	/**
	 * Numero de enfermedades activas en la partida
	 */
	static int numEnfermedadesActivas = 0;
	
	/**
	 * Numero de brotes en la partida
	 */
	static int numBrotes = 0;
	
	/**
	 * Nombre de la ciudad seleccionada
	 */
	static String ciudad;
	
	/**
	 * Nivel de infeccion de la ciudad seleccionada
	 */
	static int nivelInfeccion;
	
	/**
	 * Boolean que indica el estado de la victoria
	 */
	static boolean victoria = false;
	
	/**
	 * Boolean que indica el estado de la derrota
	 */
	static boolean derrota = false;
	
	// BOTONES Y LABELS
	/**
	 * Boton para investigar la vacuna alfa
	 */
	static JButton btnAlfa;
	/**
	 * Barra que indica el progreso de la vacuna alfa
	 */
	static JProgressBar barraAlfa;
	
	/**
	 * Boton para investigar la vacuna beta
	 */
	static JButton btnBeta;
	
	/**
	 * Barra que indica el progreso de la vacuna beta
	 */
	static JProgressBar barraBeta;
	
	/**
	 * Boton para investigar la vacuna gama
	 */
	static JButton btnGama;
	
	/**
	 * Barra que indica el progreso de la vacuna gama
	 */
	static JProgressBar barraGama;
	
	/**
	 * Boton para investigar la vacuna delta
	 */
	static JButton btnDelta;
	
	/**
	 * Barra que indica el progreso de la vacuna delta
	 */
	static JProgressBar barraDelta;
	
	/**
	 * Label que muestra el numero de ronda
	 */
	static JLabel labelNumRonda;
	
	/**
	 * Label que muestra el numero de enfermedades activas
	 */
	static JLabel labelNumEnfermedadesActivas;
	
	/**
	 * Label que muestra el numero de acciones
	 */
	static JLabel labelNumAcciones;
	
	/**
	 * Label que muestra el numero de brotes
	 */
	static JLabel labelNumBrotes;
	
	/**
	 * Label que muestra el nombre de la ciudad seleccionada
	 */
	static JLabel labelNombreCiudad;
	
	/**
	 * Label que muestra el numero de nivel de infeccion de la ciudad seleccionada
	 */
	static JLabel labelNumNivelInfeccion;
	
	/**
	 * Array de botones de las 48 ciudades
	 */
	static JButton [] botonesCiudades = new JButton [48];
	/**
	 * Array de labels de los nombres de las 48 ciudades
	 */
	static JLabel [] nombreCiudades = new JLabel [48];
	
	/**
	 * Boton curar ciudad
	 */
	static JButton btnCurarCiudad;
	
	// BOTON GUARDAR Y SALIR
	
	/**
	 * Boton guardar y salir
	 */
	static JButton btnGuardarSalir;
	
	/**
	 * Ventana continuar partida
	 */
	public VentanaContinuarPartida() {
		
    	// ------------------------------------------------------ VENTANA ------------------------------------------------------ //
    	
    	// CONFIGURAR VENTANA
    	
        setTitle("Pandemic"); // TITULO VENTANA
        setExtendedState(JFrame.MAXIMIZED_BOTH); // VENTANA MAXIMIZADA POR DEFECTO
        setSize(1850, 1010); // TAMAÑO DE LA VENTANA AL MINIMIZARLA
        setMinimumSize(new Dimension(1850, 1010)); // MINIMO ESCALABLE
        setLocationRelativeTo(null); // CENTRAR VENTANA
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // btnSalir AL PULSAR LA X
        
        // ICONO VENTANA
        
		ImageIcon imgLogo = new ImageIcon("imgLogo.png"); // IMAGEN LOGO
		setIconImage(imgLogo.getImage()); // LA AÑADIMOS A LA VENTANA
		
		if(ronda == 1) cargarPartida();
		
		// ------------------------------------------------------ PANEL CENTRAL ------------------------------------------------------ //
		
		// PANEL PRINCIPAL
        
		JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        panelPrincipal.setBackground(new Color(33, 33, 33)); // COLOR FONDO
		add(panelPrincipal, BorderLayout.CENTER); // LO AÑADIMOS A LA VENTANA
		
		// PANEL IMAGEN
		
        JPanel panelImgMapa = new JPanel();
        panelImgMapa.setLayout(new GridBagLayout());
        panelImgMapa.setBackground(Color.WHITE); // COLOR FONDO
        panelPrincipal.add(panelImgMapa); // LO AÑADIMOS A LA VENTANA
        		
		// LABEL IMAGEN
		
		JLabel labelImgMapa = new JLabel();
		labelImgMapa.setIcon(new ImageIcon("imgMapa.png"));
		labelImgMapa.setBorder(new MatteBorder(4, 4, 4, 4, new Color(0x65647C)));
		labelImgMapa.setBounds(0, 0, 1904, 850);
		panelImgMapa.add(labelImgMapa);
		
        // BARRA ALFA
        
		barraAlfa = new JProgressBar();
		barraAlfa.setStringPainted(true);
		barraAlfa.setBounds(10, 10, 150, 25);
		barraAlfa.setBackground(new Color(0x19376D));
		barraAlfa.setForeground(new Color(0x576CBC));
		barraAlfa.setFont(new Font("Arial", Font.BOLD, 14));
		labelImgMapa.add(barraAlfa);
		
		// BOTON ALFA
		
		btnAlfa = new JButton("Investigar Alfa");
		btnAlfa.setBackground(Color.GRAY); // COLOR FONDO
		btnAlfa.setForeground(Color.WHITE);
		btnAlfa.setFont(new Font("Arial", Font.BOLD, 14));
		btnAlfa.setBounds(10, 40, 150, 25);
		btnAlfa.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				acciones -= 4;
								
				alfa.investigarVacuna(parametros[4]);
				actualizarBarra();
				if(alfa.getNivelInvestigacion() == 100) btnAlfa.setEnabled(false);
				
				System.out.println("\n---------------------------------------------------------------------");
				System.out.println("\nSe ha investigado un " + parametros[4] + "% de la vacuna alfa!");
				
				victoria = comprobarVictoria(alfa, beta, gama, delta);
				
				if(victoria == true) {
					
					guardarVictoria(usuario);
		    		guardarPartida();
		    		new VentanaVictoria();
		    		ronda = 1;
		    		salir();
					
				} else {
					
					accionesRonda();
					actualizarInformacion();
					for (int i = 0; i < ciudades.size(); i++) {
						if(ciudades.get(i).getNivelInfeccion() > 0) {
							nombreCiudades[i].setForeground(Color.red);
							nombreCiudades[i].setText(ciudades.get(i).getNombre() + " [" + ciudades.get(i).getNivelInfeccion() + "]");
						}
					}
					
				}
				
			}
			
		});
		labelImgMapa.add(btnAlfa);
		
		// BARRA BETA
		
		barraBeta = new JProgressBar();
		barraBeta.setStringPainted(true);
		barraBeta.setBounds(170, 10, 150, 25);
		barraBeta.setForeground(new Color(0xF48484));
		barraBeta.setBackground(new Color(0xF55050));
		barraBeta.setFont(new Font("Arial", Font.BOLD, 14));
		labelImgMapa.add(barraBeta);
		
		// BOTON BETA
		
		btnBeta = new JButton("Investigar Beta");
		btnBeta.setBackground(Color.GRAY); // COLOR FONDO
		btnBeta.setForeground(Color.WHITE);
		btnBeta.setFont(new Font("Arial", Font.BOLD, 14));
		btnBeta.setBounds(170, 40, 150, 25);
		labelImgMapa.add(btnBeta);
		btnBeta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				acciones -= 4;
								
				beta.investigarVacuna(parametros[4]);
				actualizarBarra();
				if(beta.getNivelInvestigacion() == 100) btnBeta.setEnabled(false);
				
				System.out.println("\n---------------------------------------------------------------------");
				System.out.println("\nSe ha investigado un " + parametros[4] + "% de la vacuna beta!");
				
				victoria = comprobarVictoria(alfa, beta, gama, delta);
				
				if(victoria == true) {
					
					guardarVictoria(usuario);
		    		guardarPartida();
		    		new VentanaVictoria();
		    		ronda = 1;
		    		salir();
					
				} else {
					
					accionesRonda();
					actualizarInformacion();
					for (int i = 0; i < ciudades.size(); i++) {
						if(ciudades.get(i).getNivelInfeccion() > 0) {
							nombreCiudades[i].setForeground(Color.red);
							nombreCiudades[i].setText(ciudades.get(i).getNombre() + " [" + ciudades.get(i).getNivelInfeccion() + "]");
						}
					}
					
				}
				
			}
			
		});
		
		// BARRA GAMA
		
		barraGama = new JProgressBar();
		barraGama.setStringPainted(true);
		barraGama.setBounds(330, 10, 150, 25);
		barraGama.setForeground(new Color(0xA0D8B3));
		barraGama.setBackground(new Color(0xAAC8A7));
		barraGama.setFont(new Font("Arial", Font.BOLD, 14));
		labelImgMapa.add(barraGama);
		
		// BOTON GAMA
		
		btnGama = new JButton("Investigar Gama");
		btnGama.setBackground(Color.GRAY); // COLOR FONDO
		btnGama.setForeground(Color.WHITE);
		btnGama.setFont(new Font("Arial", Font.BOLD, 14));
		btnGama.setBounds(330, 40, 150, 25);
		labelImgMapa.add(btnGama);
		btnGama.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				acciones -= 4;
								
				gama.investigarVacuna(parametros[4]);
				actualizarBarra();
				if(gama.getNivelInvestigacion() == 100) btnGama.setEnabled(false);
				
				System.out.println("\n---------------------------------------------------------------------");
				System.out.println("\nSe ha investigado un " + parametros[4] + "% de la vacuna gama!");
				
				victoria = comprobarVictoria(alfa, beta, gama, delta);
				
				if(victoria == true) {
					
					guardarVictoria(usuario);
		    		guardarPartida();
		    		new VentanaVictoria();
		    		ronda = 1;
		    		salir();
					
				} else {
					
					accionesRonda();
					actualizarInformacion();
					for (int i = 0; i < ciudades.size(); i++) {
						if(ciudades.get(i).getNivelInfeccion() > 0) {
							nombreCiudades[i].setForeground(Color.red);
							nombreCiudades[i].setText(ciudades.get(i).getNombre() + " [" + ciudades.get(i).getNivelInfeccion() + "]");
						}
					}
					
				}
				
			}
			
		});
		
		// BARRA DELTA

		barraDelta = new JProgressBar();
		barraDelta.setStringPainted(true);
		barraDelta.setBounds(490, 10, 150, 25);
		barraDelta.setForeground(new Color(0xFCFFB2));
		barraDelta.setBackground(new Color(0xFEE8B0));
		barraDelta.setFont(new Font("Arial", Font.BOLD, 14));
		labelImgMapa.add(barraDelta);
		
		// BOTON DELTA
		
		btnDelta = new JButton("Investigar Delta");
		btnDelta.setBackground(Color.GRAY); // COLOR FONDO
		btnDelta.setForeground(Color.WHITE);
		btnDelta.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelta.setBounds(490, 40, 150, 25);
		labelImgMapa.add(btnDelta);
		btnDelta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				acciones -= 4;
								
				delta.investigarVacuna(parametros[4]);
				actualizarBarra();
				if(delta.getNivelInvestigacion() == 100) btnDelta.setEnabled(false);
				
				System.out.println("\n---------------------------------------------------------------------");
				System.out.println("\nSe ha investigado un " + parametros[4] + "% de la vacuna delta!");
				
				victoria = comprobarVictoria(alfa, beta, gama, delta);
				
				if(victoria == true) {
					
					guardarVictoria(usuario);
		    		guardarPartida();
		    		new VentanaVictoria();
		    		ronda = 1;
		    		salir();
					
				} else {
					
					accionesRonda();
					actualizarInformacion();
					for (int i = 0; i < ciudades.size(); i++) {
						if(ciudades.get(i).getNivelInfeccion() > 0) {
							nombreCiudades[i].setForeground(Color.red);
							nombreCiudades[i].setText(ciudades.get(i).getNombre() + " [" + ciudades.get(i).getNivelInfeccion() + "]");
						}
					}
					
				}
				
			}
			
		});
		
		actualizarBarra();
		estadoBtnVacunas();
		
		// BOTONES CIUDADES
		
		for (int i = 0; i < ciudades.size(); i++) {
						
			final int j = i;
			
			botonesCiudades[i] = new JButton();
			
			// boton ciudad
			
			botonesCiudades[i].setBounds(ciudades.get(i).getCoordX(), ciudades.get(i).getCoordY(), 10, 10);
			if(ciudades.get(i).getEnfermedad() == 0) {
				botonesCiudades[i].setBackground(Color.BLUE);
			}
			if(ciudades.get(i).getEnfermedad() == 1) {
				botonesCiudades[i].setBackground(Color.RED);
			}
			if(ciudades.get(i).getEnfermedad() == 2) {
				botonesCiudades[i].setBackground(Color.GREEN);
			}
			if(ciudades.get(i).getEnfermedad() == 3) {
				botonesCiudades[i].setBackground(Color.YELLOW);
			}
			
			labelImgMapa.add(botonesCiudades[i]);
						
			// nombre ciudad
			
			nombreCiudades[i] = new JLabel();
			
			nombreCiudades[i] = new JLabel(ciudades.get(i).getNombre() + " [" + ciudades.get(i).getNivelInfeccion() + "]"); // texto
			int nombreCoordX = ciudades.get(i).getCoordX() + botonesCiudades[i].getWidth() / 2 - nombreCiudades[i].getPreferredSize().width / 2;
			int nombreCoordY = ciudades.get(i).getCoordY() + botonesCiudades[i].getHeight();
			nombreCiudades[i].setBounds(nombreCoordX, nombreCoordY, nombreCiudades[i].getPreferredSize().width, nombreCiudades[i].getPreferredSize().height);
			
			if(ciudades.get(i).getNivelInfeccion() > 0) {
				nombreCiudades[i].setForeground(Color.red);
				nombreCiudades[i].setText(ciudades.get(i).getNombre() + " [" + ciudades.get(i).getNivelInfeccion() + "]");
			}
			
			botonesCiudades[i].addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					btnCurarCiudad.setEnabled(false);
					if(ciudades.get(j).getNivelInfeccion() > 0 && acciones > 0) {
						btnCurarCiudad.setEnabled(true);
					}
					
					ciudad = ciudades.get(j).getNombre();
					nivelInfeccion = ciudades.get(j).getNivelInfeccion();
					actualizarInformacionCiudad();
					
				}
				
			});
			labelImgMapa.add(nombreCiudades[i]);
			
		}
		
		// ------------------------------------------------------ PANEL INFERIOR ------------------------------------------------------ //
		
        // PANEL INFERIOR
        
		JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelInferior.setPreferredSize(new Dimension(0, 140)); // ESTABLECEMOS LA ALTURA DEL PANEL
        add(panelInferior, BorderLayout.SOUTH); // LO AÑADIMOS A LA VENTANA
        
        // ========================================================================================================================================================== //
        
		// PANEL INFERIOR IZQUIERDA
        
        JPanel panelInferiorIzquierda = new JPanel(new GridBagLayout());
        panelInferiorIzquierda.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelInferiorIzquierda.setPreferredSize(new Dimension(140, 0)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInferior.add(panelInferiorIzquierda, BorderLayout.WEST); // LO AÑADIMOS A LA VENTANA
        
        // PANEL BOTON AJUSTES
        
        JPanel panelBtnGuardarSalir = new JPanel();
        panelBtnGuardarSalir.setBackground(new Color(33, 33, 33));
        panelBtnGuardarSalir.setPreferredSize(new Dimension(120, 120));
        panelInferiorIzquierda.add(panelBtnGuardarSalir);
        
        // LABEL IMAGEN 1
        
        btnGuardarSalir = new JButton();
        btnGuardarSalir.setBackground(Color.GRAY);
        btnGuardarSalir.setPreferredSize(new Dimension(110, 110));
        btnGuardarSalir.addActionListener(this);
        panelBtnGuardarSalir.add(btnGuardarSalir);
        
        // IMAGEN 1
        
        BufferedImage imgBtnGuardarSalir = null;
        try {
        	imgBtnGuardarSalir = ImageIO.read(new File("imgBtnGuardarSalir.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Image dimgBtnGuardarSalir = imgBtnGuardarSalir.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        
        ImageIcon imageIconBtnGuardarSalir = new ImageIcon(dimgBtnGuardarSalir);
        
        btnGuardarSalir.setIcon(imageIconBtnGuardarSalir);
		
        // ========================================================================================================================================================== //
        
		// PANEL INFERIOR CENTRAL
        
        JPanel panelInferiorCentral = new JPanel();
        panelInferiorCentral.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelInferiorCentral.setPreferredSize(new Dimension(930, 0)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInferior.add(panelInferiorCentral, BorderLayout.CENTER); // LO AÑADIMOS A LA VENTANA
        
        // PANEL INFORMACION 1
        
        JPanel panelInformacion1 = new JPanel();
        panelInformacion1.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelInformacion1.setPreferredSize(new Dimension(416, 130)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInferiorCentral.add(panelInformacion1); // LO AÑADIMOS A LA VENTANA
        
        // --------------------------------------------- RONDA --------------------------------------------- //
        
        JPanel panelRonda = new JPanel();
        panelRonda.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelRonda.setPreferredSize(new Dimension(458, 57)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInformacion1.add(panelRonda); // LO AÑADIMOS A LA VENTANA
        
        // PANEL LABEL RONDA
        
        JPanel panelLabelRonda = new JPanel(new BorderLayout());
        panelLabelRonda.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelLabelRonda.setPreferredSize(new Dimension(80, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelRonda.add(panelLabelRonda); // LO AÑADIMOS A LA VENTANA
        
        // LABEL RONDA
        
        JLabel labelRonda = new JLabel("Ronda: ");
        labelRonda.setFont(new Font("Arial", Font.BOLD, 20));
        labelRonda.setForeground(Color.WHITE);
        panelLabelRonda.add(labelRonda);
        
        // PANEL NUMERO RONDA
        
        JPanel panelNumRonda = new JPanel(new BorderLayout());
        panelNumRonda.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelNumRonda.setPreferredSize(new Dimension(320, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelRonda.add(panelNumRonda); // LO AÑADIMOS A LA VENTANA
        
        // LABEL NUMERO RONDA
        
        labelNumRonda = new JLabel(Integer.toString(ronda));
        labelNumRonda.setFont(new Font("Arial", Font.BOLD, 20));
        labelNumRonda.setForeground(Color.WHITE);
        panelNumRonda.add(labelNumRonda);
        
        // --------------------------------------------- PANEL CIUDADES INFECTADAS --------------------------------------------- //
        
        JPanel panelEnfermedadesActivas = new JPanel();
        panelEnfermedadesActivas.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelEnfermedadesActivas.setPreferredSize(new Dimension(458, 57)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInformacion1.add(panelEnfermedadesActivas); // LO AÑADIMOS A LA VENTANA
        
        // PANEL LABEL CIUDADES INFECTADAS
        
        JPanel panelLabelEnfermedadesActivas = new JPanel(new BorderLayout());
        panelLabelEnfermedadesActivas.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelLabelEnfermedadesActivas.setPreferredSize(new Dimension(230, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelEnfermedadesActivas.add(panelLabelEnfermedadesActivas); // LO AÑADIMOS A LA VENTANA
        
        // LABEL CIUDADES INFECTADAS
        
        JLabel labelEnfermedadesActivas = new JLabel("Enfermedades activas: ");
        labelEnfermedadesActivas.setFont(new Font("Arial", Font.BOLD, 20));
        labelEnfermedadesActivas.setForeground(Color.WHITE);
        panelLabelEnfermedadesActivas.add(labelEnfermedadesActivas);
        
        // PANEL NUMERO CIUDADES INFECTADAS
        
        JPanel panelNumEnfermedadesActivas = new JPanel(new BorderLayout());
        panelNumEnfermedadesActivas.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelNumEnfermedadesActivas.setPreferredSize(new Dimension(35, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelEnfermedadesActivas.add(panelNumEnfermedadesActivas); // LO AÑADIMOS A LA VENTANA
        
        // LABEL NUMERO CIUDADES INFECTADAS
        
        labelNumEnfermedadesActivas = new JLabel(Integer.toString(numEnfermedadesActivas));
        labelNumEnfermedadesActivas.setFont(new Font("Arial", Font.BOLD, 20));
        labelNumEnfermedadesActivas.setForeground(Color.WHITE);
        panelNumEnfermedadesActivas.add(labelNumEnfermedadesActivas);
        
        // PANEL MAXIMO CIUDADES INFECTADAS
        
        JPanel panelMaxEnfermedadesActivas = new JPanel(new BorderLayout());
        panelMaxEnfermedadesActivas.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelMaxEnfermedadesActivas.setPreferredSize(new Dimension(130, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelEnfermedadesActivas.add(panelMaxEnfermedadesActivas); // LO AÑADIMOS A LA VENTANA
        
        // LABEL MAXIMO CIUDADES INFECTADAS
        
        JLabel labelMaxEnfermedadesActivas = new JLabel("/ " + Integer.toString(parametros[2]));
        labelMaxEnfermedadesActivas.setFont(new Font("Arial", Font.BOLD, 20));
        labelMaxEnfermedadesActivas.setForeground(Color.WHITE);
        panelMaxEnfermedadesActivas.add(labelMaxEnfermedadesActivas);
        
        // --------------------------------------------------------------------------------------------------------------------------------------- //
        
        // PANEL INFORMACION 2
        
        JPanel panelInformacion2 = new JPanel();
        panelInformacion2.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelInformacion2.setPreferredSize(new Dimension(416, 130)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInferiorCentral.add(panelInformacion2); // LO AÑADIMOS A LA VENTANA
        
        // --------------------------------------------- PANEL ACCIONES --------------------------------------------- //
        
        JPanel panelAcciones = new JPanel();
        panelAcciones.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelAcciones.setPreferredSize(new Dimension(458, 57)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInformacion2.add(panelAcciones); // LO AÑADIMOS A LA VENTANA
        
        // PANEL LABEL ACCIONES
        
        JPanel panelLabelAcciones = new JPanel(new BorderLayout());
        panelLabelAcciones.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelLabelAcciones.setPreferredSize(new Dimension(105, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelAcciones.add(panelLabelAcciones); // LO AÑADIMOS A LA VENTANA
        
        // LABEL ACCIONES
        
        JLabel labelAcciones = new JLabel("Acciones: ");
        labelAcciones.setFont(new Font("Arial", Font.BOLD, 20));
        labelAcciones.setForeground(Color.WHITE);
        panelLabelAcciones.add(labelAcciones);
        
        // PANEL NUMERO ACCIONES
        
        JPanel panelNumAcciones = new JPanel(new BorderLayout());
        panelNumAcciones.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelNumAcciones.setPreferredSize(new Dimension(15, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelAcciones.add(panelNumAcciones); // LO AÑADIMOS A LA VENTANA
        
        // LABEL NUMERO ACCIONES
        
        labelNumAcciones = new JLabel(Integer.toString(acciones));
        labelNumAcciones.setFont(new Font("Arial", Font.BOLD, 20));
        labelNumAcciones.setForeground(Color.WHITE);
        panelNumAcciones.add(labelNumAcciones);
        
        // PANEL MAXIMO ACCIONES
        
        JPanel panelMaxAcciones = new JPanel(new BorderLayout());
        panelMaxAcciones.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelMaxAcciones.setPreferredSize(new Dimension(275, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelAcciones.add(panelMaxAcciones); // LO AÑADIMOS A LA VENTANA
        
        // LABEL MAXIMO ACCIONES
        
        JLabel labelMaxAcciones = new JLabel("/ 4");
        labelMaxAcciones.setFont(new Font("Arial", Font.BOLD, 20));
        labelMaxAcciones.setForeground(Color.WHITE);
        panelMaxAcciones.add(labelMaxAcciones);
        
        // --------------------------------------------- PANEL BROTES --------------------------------------------- //
        
        JPanel panelBrotes = new JPanel();
        panelBrotes.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelBrotes.setPreferredSize(new Dimension(458, 57)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInformacion2.add(panelBrotes); // LO AÑADIMOS A LA VENTANA
        
        // PANEL LABEL BROTES
        
        JPanel panelLabelBrotes = new JPanel(new BorderLayout());
        panelLabelBrotes.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelLabelBrotes.setPreferredSize(new Dimension(85, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelBrotes.add(panelLabelBrotes); // LO AÑADIMOS A LA VENTANA
        
        // LABEL BROTES
        
        JLabel labelBrotes = new JLabel("Brotes: ");
        labelBrotes.setFont(new Font("Arial", Font.BOLD, 20));
        labelBrotes.setForeground(Color.WHITE);
        panelLabelBrotes.add(labelBrotes);
        
        // PANEL NUMERO BROTES
        
        JPanel panelNumBrotes = new JPanel(new BorderLayout());
        panelNumBrotes.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelNumBrotes.setPreferredSize(new Dimension(25, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelBrotes.add(panelNumBrotes); // LO AÑADIMOS A LA VENTANA
        
        // LABEL NUMERO BROTES
        
        labelNumBrotes = new JLabel(Integer.toString(numBrotes));
        labelNumBrotes.setFont(new Font("Arial", Font.BOLD, 20));
        labelNumBrotes.setForeground(Color.WHITE);
        panelNumBrotes.add(labelNumBrotes);
        
        // PANEL MAXIMO BROTES
        
        JPanel panelMaxBrotes = new JPanel(new BorderLayout());
        panelMaxBrotes.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelMaxBrotes.setPreferredSize(new Dimension(285, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelBrotes.add(panelMaxBrotes); // LO AÑADIMOS A LA VENTANA
        
        // LABEL MAXIMO BROTES
        
        JLabel labelMaxBrotes = new JLabel("/ " + parametros[3]);
        labelMaxBrotes.setFont(new Font("Arial", Font.BOLD, 20));
        labelMaxBrotes.setForeground(Color.WHITE);
        panelMaxBrotes.add(labelMaxBrotes);
        
        // ========================================================================================================================================================== //
        
		// PANEL INFERIOR DERECHA
        
        JPanel panelInferiorDerecha = new JPanel();
        panelInferiorDerecha.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelInferiorDerecha.setPreferredSize(new Dimension(930, 0)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInferior.add(panelInferiorDerecha, BorderLayout.EAST); // LO AÑADIMOS A LA VENTANA
        
        // --------------------------------------------- PANEL CIUDAD --------------------------------------------- //
        
        JPanel panelInformacion3 = new JPanel();
        panelInformacion3.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelInformacion3.setPreferredSize(new Dimension(458, 130)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInferiorDerecha.add(panelInformacion3); // LO AÑADIMOS A LA VENTANA
        
        // PANEL CIUDAD
        
        JPanel panelCiudad = new JPanel();
        panelCiudad.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelCiudad.setPreferredSize(new Dimension(458, 57)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInformacion3.add(panelCiudad); // LO AÑADIMOS A LA VENTANA
        
        // PANEL LABEL CIUDAD
        
        JPanel panelLabelCiudad = new JPanel(new BorderLayout());
        panelLabelCiudad.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelLabelCiudad.setPreferredSize(new Dimension(85, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelCiudad.add(panelLabelCiudad); // LO AÑADIMOS A LA VENTANA
        
        // LABEL CIUDAD
        
        JLabel labelCiudad = new JLabel("Ciudad: ");
        labelCiudad.setFont(new Font("Arial", Font.BOLD, 20));
        labelCiudad.setForeground(Color.WHITE);
        panelLabelCiudad.add(labelCiudad);
        
        // PANEL NOMBRE CIUDAD
        
        JPanel panelNombreCiudad = new JPanel(new BorderLayout());
        panelNombreCiudad.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelNombreCiudad.setPreferredSize(new Dimension(350, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelCiudad.add(panelNombreCiudad); // LO AÑADIMOS A LA VENTANA
        
        // LABEL NOMBRE CIUDAD
        
        labelNombreCiudad = new JLabel();
        labelNombreCiudad.setFont(new Font("Arial", Font.BOLD, 20));
        labelNombreCiudad.setForeground(Color.WHITE);
        panelNombreCiudad.add(labelNombreCiudad);
        
        // --------------------------------------------- PANEL NIVEL INFECCION --------------------------------------------- //
        
        JPanel panelNivelInfeccion = new JPanel();
        panelNivelInfeccion.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelNivelInfeccion.setPreferredSize(new Dimension(458, 57)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInformacion3.add(panelNivelInfeccion); // LO AÑADIMOS A LA VENTANA
        
        // PANEL LABEL NIVEL INFECCION
        
        JPanel panelLabelNivelInfeccion = new JPanel(new BorderLayout());
        panelLabelNivelInfeccion.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelLabelNivelInfeccion.setPreferredSize(new Dimension(185, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelNivelInfeccion.add(panelLabelNivelInfeccion); // LO AÑADIMOS A LA VENTANA
        
        // LABEL NIVEL INFECCION
        
        JLabel labelNivelInfeccion = new JLabel("Nivel de infeccion: ");
        labelNivelInfeccion.setFont(new Font("Arial", Font.BOLD, 20));
        labelNivelInfeccion.setForeground(Color.WHITE);
        panelLabelNivelInfeccion.add(labelNivelInfeccion);
        
        // PANEL NUMERO NIVEL INFECCION
        
        JPanel panelNumNivelInfeccion = new JPanel(new BorderLayout());
        panelNumNivelInfeccion.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelNumNivelInfeccion.setPreferredSize(new Dimension(250, 48)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelNivelInfeccion.add(panelNumNivelInfeccion); // LO AÑADIMOS A LA VENTANA
        
        // LABEL NUMERO NIVEL INFECCION
        
        labelNumNivelInfeccion = new JLabel();
        labelNumNivelInfeccion.setFont(new Font("Arial", Font.BOLD, 20));
        labelNumNivelInfeccion.setForeground(Color.WHITE);
        panelNumNivelInfeccion.add(labelNumNivelInfeccion);
        
        // --------------------------------------------- BOTON CURAR CIUDAD --------------------------------------------- //
        
        // PANEL BOTON CURAR CIUDAD
        
        JPanel panelBtnCurarCiudad = new JPanel();
        panelBtnCurarCiudad.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelBtnCurarCiudad.setPreferredSize(new Dimension(458, 130)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelInferiorDerecha.add(panelBtnCurarCiudad); // LO AÑADIMOS A LA VENTANA
        
        // PANEL INVISIBLE
        
        JPanel panelInvisible = new JPanel();
        panelInvisible.setBackground(new Color(33, 33, 33)); // COLOR FONDO
        panelInvisible.setPreferredSize(new Dimension(350, 15)); // ESTABLECEMOS LA ALTURA DEL PANEL
        panelBtnCurarCiudad.add(panelInvisible); // LO AÑADIMOS A LA VENTANA
        
        // BOTON CURAR CIUDAD
        
        btnCurarCiudad = new JButton("Curar ciudad");
        btnCurarCiudad.setBackground(Color.GRAY); // COLOR FONDO
        btnCurarCiudad.setForeground(Color.WHITE);
        btnCurarCiudad.setFont(new Font("Arial", Font.BOLD, 22));
        btnCurarCiudad.setPreferredSize(new Dimension(350, 80)); // ESTABLECEMOS LA ALTURA DEL PANELbtnCurarCiudad
        btnCurarCiudad.setEnabled(false);
        btnCurarCiudad.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < ciudades.size(); i++) {
					
					if(ciudades.get(i).getNombre().equals(ciudad)) {
						
						acciones -= 1;
						
						if((ciudades.get(i).getEnfermedad() == 0 && alfa.getNivelInvestigacion() == 100) || (ciudades.get(i).getEnfermedad() == 1 && beta.getNivelInvestigacion() == 100)
								|| (ciudades.get(i).getEnfermedad() == 2 && gama.getNivelInvestigacion() == 100) || (ciudades.get(i).getEnfermedad() == 3 && delta.getNivelInvestigacion() == 100)) {
							ciudades.get(i).reiniciarCiudad(); // SI LA VACUNA DE ESA CIUDAD YA EXISTE LA VAMOS A CURAR POR COMPLETO
						} else {
							ciudades.get(i).curarCiudad(); // CURAMOS LA CIUDAD
							nombreCiudades[i].setText(ciudades.get(i).getNombre() + " [" + ciudades.get(i).getNivelInfeccion() + "]");
						}
						
						System.out.println("\n---------------------------------------------------------------------");
						System.out.println("\nSe ha curado la ciudad: " + ciudades.get(i).getNombre() + " | Nivel de infeccion actual [" + ciudades.get(i).getNivelInfeccion() + "]");
						
						
						if(ciudades.get(i).getNivelInfeccion() == 0) { // SI EL NIVEL DE INFECCION DE LA CIUDAD UNA VEZ CURADA ES 0
							nombreCiudades[i].setForeground(Color.black); // PONDREMOS EL COLOR DEL NOMBRE DE LA CIUDAD A NEGRO
							nombreCiudades[i].setText(ciudades.get(i).getNombre() + " [" + ciudades.get(i).getNivelInfeccion() + "]");
						}
																		
						actualizarInformacion();
						
					}
				}
				
				if(acciones == 0) {
					
					accionesRonda();
					actualizarInformacion();
					for (int i = 0; i < ciudades.size(); i++) {
						if(ciudades.get(i).getNivelInfeccion() > 0) {
							nombreCiudades[i].setForeground(Color.red);
							nombreCiudades[i].setText(ciudades.get(i).getNombre() + " [" + ciudades.get(i).getNivelInfeccion() + "]");
						}
					}
					
				}
				
				btnCurarCiudad.setEnabled(false);
				
			}
			
		});
        panelBtnCurarCiudad.add(btnCurarCiudad); // LO AÑADIMOS A LA VENTANA
		
		// ------------------------------------------------------ HACEMOS LA VENTANA VISIBLE ------------------------------------------------------ //
		
		setVisible(true);
		
	}
	
	/**
	 * Metodo action listener para saber que boton hemos pulsado
	 */
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if (e.getSource() == btnGuardarSalir) {
    		
    		guardarPartida();
    		new VentanaGuardarSalir();
    		ronda = 1;
    		salir();
    		
    	}
    	
    }
    
    /**
     * Metodo main
     * @param args. Argumentos
     */
    public static void main(String[] args) {
    	new VentanaContinuarPartida();
    }
    
    /**
     * Metodo encargado de leer los datos de la partida desde la base de datos y cargarlos en las variables
     */
    static void leerPartida() {
    	
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.conectarBaseDatos();
    	
        try {
        	
            String consulta = "SELECT * FROM PARTIDA WHERE idPartida = " + idPartida;
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            if(rs.next()) {
            	
            	dificultad = rs.getString("dificultad");
            	ronda = rs.getInt("ronda");
            	acciones = rs.getInt("acciones");
            	numEnfermedadesActivas = rs.getInt("enfermedadesActivas");
            	numBrotes = rs.getInt("brotes");
            	
            	// NIVEL INFECCION CIUDADES
            	
            	java.sql.Array ciudadesBD = rs.getArray("infeccionCiudades");
            	Object[] ciudadBD = (Object[]) ciudadesBD.getArray();
            	
                for (int i = 0; i < ciudades.size(); i++) {
                	
                	java.sql.Struct atributosCiudadBD = (java.sql.Struct) ciudadBD[i];
                	Object[] atributo = atributosCiudadBD.getAttributes();
                	ciudades.get(i).setNivelInfeccion(((BigDecimal) atributo[1]).intValue());
                	
				}
                
                // NIVEL INVESTIGACION VACUNAS
                
                java.sql.Array vacunasBD = rs.getArray("investigacionVacunas");
                Object[] vacunaBD = (Object[]) vacunasBD.getArray();
                
                for (int i = 0; i < 4; i++) {
                	
                    java.sql.Struct atributosVacunaBD = (java.sql.Struct) vacunaBD[i];
                    Object[] atributo = atributosVacunaBD.getAttributes();
                    
                    if(i == 0) alfa.setNivelInvestigacion(((BigDecimal) atributo[1]).intValue());
                    if(i == 1) beta.setNivelInvestigacion(((BigDecimal) atributo[1]).intValue());
                    if(i == 2) gama.setNivelInvestigacion(((BigDecimal) atributo[1]).intValue());
                    if(i == 3) delta.setNivelInvestigacion(((BigDecimal) atributo[1]).intValue());
                    
				}
                
            }
            
            System.out.println("Partida cargada correctamente");
            
        } catch (SQLException e) {
        	System.out.println("Error al cargar la partida: " + e);
        }
    	
    }
    
    /**
     * Metodo encargado de cargar los otros datos como, leer las ciudades, leer los parametros...
     */
    static void cargarPartida() {
    	usuario = leerUsuario();
    	idPartida = leerIdPartida();
    	ciudades = leerCiudades();
    	leerPartida();
    	if(dificultad.equals("Facil")) {
    		modificarParametros("9", "4", "55", "9", "25");
    	} else if(dificultad.equals("Normal")) {
    		modificarParametros("13", "4", "50", "8", "25");
    	} else if(dificultad.equals("Dificil")) {
    		modificarParametros("17", "5", "70", "6", "20");
    	}
    	parametros = leerParametros();
    	
    	victoria = false;
    	derrota = false;
    	imprimirInformacion(ciudades, parametros, ronda, numEnfermedadesActivas, numBrotes, alfa, beta, gama, delta); // IMPRIMIR INFORMACION
    }
    
    /**
     * Metodo leer usuario, lee el archivo que guarda el usuario con el que hemos iniciado sesion
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
     * Metodo leer id de la partida, que lee el archivo que guarda el id de la partida
     * @return idPartida. Devuelve el id de la partida
     */
    static int leerIdPartida() {
    	
    	String idPartida = "";
    	
    	try {
    		
    		BufferedReader br = new BufferedReader(new FileReader("idPartida.txt"));
    		idPartida = br.readLine();
    		br.close();
    		
    	} catch (IOException e) {
    		System.out.println("\nError al leer el id de la partida: " + e);
    	}	
    	
    	return Integer.parseInt(idPartida);
    	
    }
	
    /**
     * Metodo que modifica el arcivo parametos.xml con los parametros que le pasemos
     * @param numCiudadesInfectadasInicio. Este parametro indica el numero de ciudades que se infectaran al inicio de la partida
     * @param numCuidadesInfectadasRonda. Este parametro indica el numero de ciudades que se infectaran por cada ronda que pase
     * @param numEnfermedadesActivasDerrota. Este parametro indica el numero maximo de enfemedades activas que pueden haber
     * @param numBrotesDerrota. Este parametro indica el numero maximo de brotes que pueden haber
     * @param porcentajeInvestigacion. Este parametro indica el porcentaje de investigacion de la vacuna
     */
	static void modificarParametros(String numCiudadesInfectadasInicio, String numCuidadesInfectadasRonda, String numEnfermedadesActivasDerrota, String numBrotesDerrota, String porcentajeInvestigacion) {
		
		// PROGRAMA
		
		try {
			
			File archivo = new File("parametros.xml");
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(archivo);
			document.getDocumentElement().normalize();
			Element elemento = (Element) document.getElementsByTagName("parametros").item(0);
			
			// APLICAMOS LOS NUEVOS DATOS A CADA UNO DE LOS ELEMENTOS DENTRO DE "parametros"
			
			elemento.getElementsByTagName("numCiudadesInfectadasInicio").item(0).setTextContent(numCiudadesInfectadasInicio);
			elemento.getElementsByTagName("numCuidadesInfectadasRonda").item(0).setTextContent(numCuidadesInfectadasRonda);
			elemento.getElementsByTagName("numEnfermedadesActivasDerrota").item(0).setTextContent(numEnfermedadesActivasDerrota);
			elemento.getElementsByTagName("numBrotesDerrota").item(0).setTextContent(numBrotesDerrota);
			elemento.getElementsByTagName("porcentajeInvestigacion").item(0).setTextContent(porcentajeInvestigacion);
			
			FileOutputStream archivoSalida = new FileOutputStream(archivo); // CREAMOS UN OBJETO FILEOUTPUTSTREAM PARA ESCRIBIR BYTES
			
			javax.xml.transform.TransformerFactory.newInstance().newTransformer().transform(new javax.xml.transform.dom.DOMSource(document), new javax.xml.transform.stream.StreamResult(archivoSalida));
			archivoSalida.close();
	        
			System.out.println("\nParametros modificados!");
			
		} catch(Exception e) {
			
			System.out.println("\nError al modificar los parametros: " + e);
			
		}
		
	}
	
	/**
	 * Metodo que lee el archivo parametros.xml y devuelve un array con los parametros
	 * @return parametros. Devuelve un array con los parametros
	 */
	static int[] leerParametros() {
		
		// VARIABLES
		
		int [] parametros  = new int [5];
		
		
		// PROGRAMA
		
		try {
			File archivo = new File("parametros.xml"); // CREAMOS UN OBJETO FILE QUE REPRESENTA EL ARCHIVO "parametros.xml"
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance(); // CREAMOS UN OBJETO DOCUMENTBUILDERFACTORY PARA LUEGO CREAR UN OBJETO DOCUMENTBUILDER
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder(); // CREAMOS UN OBJETO DOCUMENTBUILDER PARA LUEGO CREAR UN OBJETO DOCUMENT
			Document document = documentBuilder.parse(archivo); // ANALIZAMOS EL ARCHIVO Y CREAMOS UN OBJETO DOCUMENT QUE REPRESENTA LA ESTRUCTURA DEL ARCHIVO "parametros.xml"
			document.getDocumentElement().normalize(); // UTILIZAMOS EL METODO 'getDocumentElement()' PARA OBTENER LA RAIZ DEL ARCHIVO "parametros.xml" Y LA NORMALIZAMOS CON EL METODO 'normalize()'
			Element elemento = (Element) document.getElementsByTagName("parametros").item(0); // CREAMOS UN OBJETO ELEMENT QUE REPRESENTA EL ELEMENTO "parametros" DEL ARCHIVO "parametros.xml"
			
			// MOSTRAMOS POR PANTALLA EL CONTENIDO DE CADA UNO DE LOS ELEMENTOS DENTRO DE "parametros"
			
			parametros[0] = Integer.parseInt(elemento.getElementsByTagName("numCiudadesInfectadasInicio").item(0).getTextContent());
			parametros[1] = Integer.parseInt(elemento.getElementsByTagName("numCuidadesInfectadasRonda").item(0).getTextContent());
			parametros[2] = Integer.parseInt(elemento.getElementsByTagName("numEnfermedadesActivasDerrota").item(0).getTextContent());
			parametros[3] = Integer.parseInt(elemento.getElementsByTagName("numBrotesDerrota").item(0).getTextContent());
			parametros[4] = Integer.parseInt(elemento.getElementsByTagName("porcentajeInvestigacion").item(0).getTextContent());
		} catch(Exception e) {
			System.out.println("\nError E/S: " + e);
		}
		
		return parametros;
		
	}
	
	/**
	 * Metodo encargado de leer el archivo ciudades.txt y devolver un arraylist de las ciudades
	 * @return ciudades. Devuelve un arraylist con las ciudades
	 */
	static ArrayList<Ciudad> leerCiudades() {
		
		// VARIABLES
		
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
		Ciudad ciudad;
		
		String archivo = "ciudades.txt";
		String linea;
				
		String [] partes = new String [4];
		String nombre;
		int enfermedad;
		String [] coords = new String [2];
		int coordX;
		int coordY;
		String [] ciudadesColindantes;
		
		
		// PROGRAMA
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader (archivo));
			
			while ((linea = br.readLine()) != null) {
				
				partes = linea.split(";");
				
				nombre = partes[0];
				
				enfermedad = Integer.parseInt(partes[1]);
				
				coords = partes[2].split(",");
				coordX = Integer.parseInt(coords[0]);
				coordY = Integer.parseInt(coords[1]);
				
				ciudadesColindantes = partes[3].split(",");
				
				ciudad = new Ciudad(nombre, enfermedad, coordX, coordY, ciudadesColindantes);
				ciudades.add(ciudad);
				
			}
			
			br.close();
			
		} catch (Exception e) {
			
			System.out.println("\nError E/S: " + e);
			
		}
		
		return ciudades;
		
	}
	
	/**
	 * Metodo que imprime la informacion de la partida por consola
	 * @param ciudades. Este parametro indica el arraylist de ciudades
	 * @param parametros. Este parametro indica el array de parametros
	 * @param ronda. Este parametro indica la ronda actual
	 * @param numEnfermedadesActivas. Este parametro indica en numero de enfermedades activas
	 * @param numBrotes. Este parametro indica el numero de brotes
	 * @param alfa. Este parametro indica la vacuna alfa
	 * @param beta. Este parametro indica la vacuna beta
	 * @param gama. Este parametro indica la vacuna gama
	 * @param delta. Este parametro indica la vacuna delta
	 */
	static void imprimirInformacion(ArrayList<Ciudad> ciudades, int [] parametros, int ronda, int numEnfermedadesActivas, int numBrotes, Vacuna alfa, Vacuna beta, Vacuna gama, Vacuna delta) {
		
		System.out.println("\n---------------------------------------------------------------------");
		
		System.out.println("\nNivel infeccion | Ciudad\n");
		
		for (int i = 0; i < ciudades.size(); i++) {
			
			System.out.println("[" + ciudades.get(i).getNivelInfeccion() + "] " + ciudades.get(i).getNombre());
			
		}
		
		System.out.println("\n---------------------------------------------------------------------");
		
		System.out.println("\nRonda: " + ronda);
		
		System.out.println("\nEnfermedades activas: " + numEnfermedadesActivas + " / " + parametros[2]);
		
		System.out.println("\nBrotes: " + numBrotes + " / " + parametros[3]);
		
		System.out.println("\nEstado de las vacunas:\n\nAlfa: " + alfa.getNivelInvestigacion() + " / 100\nBeta: " + beta.getNivelInvestigacion() + " / 100\n"
				+ "Gama: " + gama.getNivelInvestigacion() + " / 100\nDelta: " + delta.getNivelInvestigacion() + " / 100");
		
	}
	
	/**
	 * Metodo que comprueba si hay brotes activos
	 * @param ciudades. Este parametro indica el arraylist de ciudades
	 * @return numBrotes. Devuelve el numero de brotes
	 */
	static int comprobarBrotes(ArrayList<Ciudad> ciudades) {
		
		// VARIABLES
		
		int numBrotes = 0;
		
		
		// PROGRAMA
		
		for (int i = 0; i < ciudades.size(); i++) if (ciudades.get(i).getNivelInfeccion() > 3) numBrotes++;
		
		return numBrotes;
		
	}
	
	/**
	 * Metodo que va a infectar las ciudades coolindantes en caso de brote
	 * @param ciudades. Este parametro indica el arraylist de ciudades
	 * @return ciudades. Este parametro indica el arraylist de ciudades
	 */
	static ArrayList<Ciudad> infectarCiudadesColindantes(ArrayList<Ciudad> ciudades) {
		
		for (int i = 0; i < ciudades.size(); i++) { // RECORREMOS LAS CIUDADES
			
			if(ciudades.get(i).getNivelInfeccion() > 3) { // SI EL NIVEL DE INFECCION DE LA CIUDAD ES MAYOR A 3
				
				ciudades.get(i).curarCiudad(); // BAJAR UN NIVEL DE INFECCION DE LA CIUDAD
				
				System.out.println("\n---------------------------------------------------------------------");
				
				System.out.println("\nBrote en la ciudad: " + ciudades.get(i).getNombre()); // IMPRIMIMOS EN NOMBRE DE LA CIUDAD EN LA QUE SE ORIGINA EL BROTE
				
				for (int j = 0; j < ciudades.get(i).getCiudadesColindantes().length; j++) { // RECORREMOS LAS CIUDADES COLINDANTES
					
					for (int k = 0; k < ciudades.size(); k++) { // RECORREMOS LAS CIUDADES
						
						if(ciudades.get(k).getNombre().equals(ciudades.get(i).getCiudadesColindantes()[j])) { // SI EL NOMBRE DE LA CIUDAD ES EL MISMO NOMBRE QUE LA CIUDAD COLINDANTE ACTUAL
							
							if(ciudades.get(k).getNivelInfeccion() < 3) {
								
								ciudades.get(k).infectarCiuad(); // SI EL NIVEL DE INFECCION DE LA CIUDADE ES MENOR A 3 LA INFECTAREMOS
								
								System.out.println("\t- El brote ha afectado a la ciudad: " + ciudades.get(k).getNombre() + " | Nivel infeccion: " + ciudades.get(k).getNivelInfeccion()); // IMPRIMIMOS LA CIUDAD AFECTADA POR EL BROTE
								
							}
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return ciudades;
		
	}
	
	/**
	 * Metodo que calcula el numero de enfermedades activas
	 * @param ciudades. Este parametro indica el arraylist de ciudades
	 * @return numEnfermedadesActivas. Devuelve el numero de enfermedades activas
	 */
	static int calcularEnfermedadesActivas(ArrayList<Ciudad> ciudades) {
		
		// VARIABLES
		
		int numEnfermedadesActivas;
		
		
		// PROGRAMA
		
		numEnfermedadesActivas = ciudades.stream().mapToInt(Ciudad::getNivelInfeccion).sum(); // SUMAMOS EL TOTAL DE ENFERMEDADES ACTIVAS
		
		return numEnfermedadesActivas;
		
	}
	
	/**
	 * Metodo que se ocupa de infectar las ciudades de la ronda, comprobar si hay brotes en caso de que haya infectar las ciudades coolindantes, imprime la informacion por consola y comprueba si hay derrota
	 */
	void accionesRonda() {
    	infectarCiudadesRonda(ciudades, parametros);
    	numBrotes += comprobarBrotes(ciudades);
		while(comprobarBrotes(ciudades) > 0) {
			infectarCiudadesColindantes(ciudades);
		}
		numEnfermedadesActivas = calcularEnfermedadesActivas(ciudades);
		ronda++;
		acciones = 4;
		imprimirInformacion(ciudades, parametros, ronda, numEnfermedadesActivas, numBrotes, alfa, beta, gama, delta); // IMPRIMIR INFORMACION
		derrota = comprobarDerrota(parametros, numBrotes, numEnfermedadesActivas);
		if(derrota == true) {
			guardarPartida();
    		new VentanaDerrota();
    		ronda = 1;
    		salir();
		}
    }
    
	/**
	 * Metodo que infecta las ciudades por cada ronda que pase
	 * @param ciudades. Este parametro indica el arraylist de ciudades
	 * @param parametros. Este parametro indica el array de parametros
	 * @return ciudades. Devuelve el array de ciudades con las ciudades infectadas
	 */
	static ArrayList<Ciudad> infectarCiudadesRonda(ArrayList<Ciudad> ciudades, int [] parametros) {
		
		// VARIABLES
		
		int ciudadInfectar;
		
		
		// PROGRAMA
		
		System.out.println("\n---------------------------------------------------------------------");
		
		for (int i = 0; i < parametros[1]; i++) {
			
			ciudadInfectar = (int) (Math.random()*49);
			
			for (int j = 0; j < ciudades.size(); j++) {
				
				if (j == ciudadInfectar) {
					
					ciudades.get(j).infectarCiuad();
					System.out.println("\nSe ha infectado la ciudad: " + ciudades.get(j).getNombre() + " | Nivel infeccion: " + ciudades.get(j).getNivelInfeccion());
					
				}
				
			}
			
		}
		
		return ciudades;
		
	}
	
	/**
	 * Metodo encargado de actulizar la barra de progreso de las vacunas
	 */
	private void actualizarBarra() {
		barraAlfa.setValue(alfa.getNivelInvestigacion());
		barraBeta.setValue(beta.getNivelInvestigacion());
		barraGama.setValue(gama.getNivelInvestigacion());
		barraDelta.setValue(delta.getNivelInvestigacion());
	}
	
	/**
	 * Metodo encargado de actualizar la informacion por pantalla, numero de brotes, numero de enfermedades activas, numero de ronda y el metodo estadoBtnVacunas
	 */
	void actualizarInformacion() {
    	numBrotes += comprobarBrotes(ciudades);
    	numEnfermedadesActivas = calcularEnfermedadesActivas(ciudades);
    	
		labelNumRonda.setText(Integer.toString(ronda));
		labelNumEnfermedadesActivas.setText(Integer.toString(numEnfermedadesActivas));
		labelNumAcciones.setText(Integer.toString(acciones));
		labelNumBrotes.setText(Integer.toString(numBrotes));
		labelNombreCiudad.setText("");
		labelNumNivelInfeccion.setText("");
		estadoBtnVacunas();
	}
	
	/**
	 * Metodo que comprueba si hay suficientes acciones para usar el boton de investigar una vacuna si hay menos de 4 los botones estaran inactivos en caso contrario estaran activos
	 */
	void estadoBtnVacunas() {
		
		if(acciones < 4) {
			btnAlfa.setEnabled(false);
			btnBeta.setEnabled(false);
			btnGama.setEnabled(false);
			btnDelta.setEnabled(false);
		} else {
			if(alfa.getNivelInvestigacion() == 100) {
				btnAlfa.setEnabled(false);
			} else {
				btnAlfa.setEnabled(true);
			}
			if(beta.getNivelInvestigacion() == 100) {
				btnBeta.setEnabled(false);
			} else {
				btnBeta.setEnabled(true);
			}
			if(gama.getNivelInvestigacion() == 100) {
				btnGama.setEnabled(false);
			} else {
				btnGama.setEnabled(true);
			}
			if(delta.getNivelInvestigacion() == 100) {
				btnDelta.setEnabled(false);
			} else {
				btnDelta.setEnabled(true);
			}
		}
		
	}
	
	/**
	 * Metodo que actualiza la informacion de la ciudad seleccionada, el nombre y el nivel de infeccion
	 */
	void actualizarInformacionCiudad() {
		labelNombreCiudad.setText(ciudad);
		labelNumNivelInfeccion.setText(Integer.toString(nivelInfeccion));
	}
    
	/**
	 * Metodo comprobar victoria
	 * @param alfa. Este parametro indica la vacuna alfa
	 * @param beta. Este parametro indica la vacuna beta
	 * @param gama. Este parametro indica la vacuna gama
	 * @param delta. Este parametro indica la vacuna delta
	 * @return boolean. En caso de victoria devuelve true en caso contrario devuelve false
	 */
	static boolean comprobarVictoria(Vacuna alfa, Vacuna beta, Vacuna gama, Vacuna delta) {
		
		if(alfa.getNivelInvestigacion() == 100 && beta.getNivelInvestigacion() == 100 && gama.getNivelInvestigacion() == 100 && delta.getNivelInvestigacion() == 100) {
			
			imprimirInformacion(ciudades, parametros, ronda, numEnfermedadesActivas, numBrotes, alfa, beta, gama, delta);
			System.out.println("\n---------------------------------------------------------------------");
			System.out.println("\nHas ganado!");
			return true;
			
		}
		
		return false;
		
	}
    
	/**
	 * Metodo comprobar derrota
	 * @param parametros. Este parametro indica el array de parametros
	 * @param numBrotes. Este parametro indica el numero de brotes
	 * @param numEnfermedadesActivas. Este parametro indica el numero de enfermedades activas
	 * @return boolean. En caso de derrota devuelve true en caso contrario devuelve false
	 */
	static boolean comprobarDerrota(int [] parametros, int numBrotes, int numEnfermedadesActivas) {
		
		// PROGRAMA
		
		if(numEnfermedadesActivas >= parametros[2]) {
			
			imprimirInformacion(ciudades, parametros, ronda, numEnfermedadesActivas, numBrotes, alfa, beta, gama, delta);
			System.out.println("\n---------------------------------------------------------------------");
			System.out.println("\nHas perdido!");
			return true;
			
		}
		if(numBrotes >= parametros[3]) {
			
			imprimirInformacion(ciudades, parametros, ronda, numEnfermedadesActivas, numBrotes, alfa, beta, gama, delta);
			System.out.println("\n---------------------------------------------------------------------");
			System.out.println("\nHas perdido!");
			return true;
			
		}
				
		return false;
		
	}
    
	/**
	 * Metodo encargado de guardar la partida al darle al boton de guardar y salir
	 */
    static void guardarPartida() {
    	
		char estado;
		
		if(victoria == true) {
			estado = 'G'; // ESTADO DE LA PARTIDA GANADA
		} else if(derrota == true) {
			estado = 'P'; // ESTADO DE LA PARTIDA PERDIDA
		} else {
			estado = 'E'; // ESTADO DE LA PARTIDA EMPEZADA
		}
    	
		String sql = "UPDATE PARTIDA SET fecha = SYSDATE, estado = '" + estado + "', ronda = " + ronda + ", acciones = " + acciones + ", enfermedadesActivas = " + numEnfermedadesActivas + ", brotes = " + numBrotes + ", investigacionVacunas = vacunas(";
		
		sql += "vacuna('" + alfa.getNombre() + "', " + alfa.getNivelInvestigacion() + "), vacuna('" + beta.getNombre() + "', " + beta.getNivelInvestigacion() + "), vacuna('";
		sql += gama.getNombre() + "', " + gama.getNivelInvestigacion() + "), vacuna('" + delta.getNombre() + "', " + delta.getNivelInvestigacion() + ")), infeccionCiudades = ciudades(";
		
		for (int i = 0; i < ciudades.size(); i++) {
			
			if(i == 0) {
				sql += "ciudad('" + ciudades.get(i).getNombre() + "', " + ciudades.get(i).getNivelInfeccion() + ")";
			} else {
				sql += ", ciudad('" + ciudades.get(i).getNombre() + "', " + ciudades.get(i).getNivelInfeccion() + ")";
			}
			
		}
		
		sql += ") WHERE idPartida = " + idPartida;
		
		//System.out.println(sql);
		
		System.out.println("\n---------------------------------------------------------------------\n");
		
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.conectarBaseDatos();
		
		System.out.println("\n---------------------------------------------------------------------");
		
		try {
			Statement st = con.createStatement();
			st.execute(sql);
			
			System.out.println("\nPartida guardada correctamente!");
		} catch (SQLException exc) {
			System.out.println("\nError al guardar la partida: " + exc);
		}
		
    }
    
    /**
     * Metodo encargado de guardar la victoria en caso de haber una
     * @param usuario. Este parametro indica el usuario al que se le va a sumar una victoria
     */
	static void guardarVictoria(String usuario) {
		
		String sql = "UPDATE JUGADOR SET numVictorias = (numVictorias + 1) WHERE usuario = '" + usuario + "'";
		
		System.out.println("\n---------------------------------------------------------------------\n");
		
		ConexionBD conexion = new ConexionBD();
		Connection con = conexion.conectarBaseDatos();
		
		System.out.println("\n---------------------------------------------------------------------");
		
		try {
			
			Statement st = con.createStatement();
			st.execute(sql);
			
			System.out.println("\nVictoria guardada correctamente!");
		} catch (SQLException e) {
			System.out.println("\nError al guardar la victoria: " + e);
		}
		
	}
	
	/**
	 * Metodo salir
	 */
    void salir() {
    	
		this.setVisible(false);
    	
    }
    
}