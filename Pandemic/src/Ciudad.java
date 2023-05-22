/**
 * 
 * Esta es la clase del objeto ciudad
 * @author aiuoki
 *
 */
public class Ciudad {
	
	// ATRIBUTOS
	
	/**
	 * Nombre de la ciudad
	 */
	private String nombre;
	/**
	 * Enfermedad de la ciudad
	 */
	private int enfermedad;
	/**
	 * Coordenada X de la ciudad
	 */
	private int coordX;
	/**
	 * Coordenada Y de la ciudad
	 */
	private int coordY;
	/**
	 * Ciudades coolindantes de la ciudad
	 */
	private String [] ciudadesColindantes;
	/**
	 * Nivel de infeccion de la ciudad
	 */
	private int nivelInfeccion;	
	
	
	// CONSTRUCTOR
	
	/**
	 * Metodo constructor de la clase ciudad
	 * @param nombre. Este parametro indica el nombre de la ciudad
	 * @param enfermedad. Este parametro indica la enfermedad de la ciudad
	 * @param coordX. Este parametro indica la coordenada X de la ciudad
	 * @param coordY. Este parametro indica la coordenada Y de la ciudad
	 * @param ciudadesColindantes. Este parametro indica las ciudades coolindantes de la ciudad
	 */
	public Ciudad (String nombre, int enfermedad, int coordX, int coordY, String [] ciudadesColindantes) {
		this.nombre = nombre;
		this.enfermedad = enfermedad;
		this.coordX = coordX;
		this.coordY = coordY;
		this.ciudadesColindantes = ciudadesColindantes;
		this.nivelInfeccion = 0;
	}
	
	// GETTERS
	
	/**
	 * Metodo get del nombre de la ciudad
	 * @return Devuelve el nombre de la ciudad
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Metodo get de la enfermedad de la ciudad
	 * @return Devuelve la enfermedad de la ciudad
	 */
	public int getEnfermedad() {
		return enfermedad;
	}
	
	/**
	 * Metodo get de la coordenada X de la ciudad
	 * @return Devuelve la coordenada X de la ciudad
	 */
	public int getCoordX() {
		return coordX;
	}
	
	/**
	 * Metodo get de la coordenada Y de la ciudad
	 * @return Devuelve la coordenada Y de la ciudad
	 */
	public int getCoordY() {
		return coordY;
	}
	
	/**
	 * Metodo get de las ciudades coolindantes de la ciudad
	 * @return Devuelve las ciudades coolindantes de la ciudad
	 */
	public String[] getCiudadesColindantes() {
		return ciudadesColindantes;
	}
	
	/**
	 * Metodo get del nivel de infeccion de la ciudad
	 * @return Devuelve el nivel de infeccion de la ciudad
	 */
	public int getNivelInfeccion() {
		return nivelInfeccion;
	}
	
	// SETTERS
	
	/**
	 * Metodo set del nivel de infeccion de la ciudad
	 * @param nivelInfeccion. Este parametro indica el nivel de infeccion de la ciudad
	 */
	public void setNivelInfeccion(int nivelInfeccion) {
		this.nivelInfeccion = nivelInfeccion;
	}
	
	// REINICIAR CIUDAD
	
	/**
	 * Metodo reiniciar ciudad, este metodo cambia el nivel de infeccion de la ciudad a 0
	 */
	public void reiniciarCiudad() {
		this.nivelInfeccion = 0;
	}
	
	// INFECTAR CIUDAD
	
	/**
	 * Este metodo incrementa +1 el nivel de infeccion de la ciudad
	 */
	public void infectarCiuad() {
		this.nivelInfeccion += 1;
	}
	
	// CURAR CIUDAD
	
	/**
	 * Este metodo disminuye -1 el nivel de infeccion de la ciudad
	 */
	public void curarCiudad() {
		this.nivelInfeccion -= 1;
	}
	
}