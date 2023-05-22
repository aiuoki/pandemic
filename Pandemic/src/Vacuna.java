/**
 * 
 * Esta es la clase del objeto vacuna
 * @author aiuoki
 *
 */
public class Vacuna {
	
	// ATRIBUTOS
	
	/**
	 * Nombre de la vacuna
	 */
	private String nombre;
	
	/**
	 * Nivel de investigacion de la vacuna
	 */
	private int nivelInvestigacion;
	
	
	
	
	// CONSTRUCTOR
	
	/**
	 * Metodo constructor de la clase vacuna
	 * @param nombre. Este parametro indica el nombre de la vacuna
	 */
	public Vacuna(String nombre) {
		this.nombre = nombre;
		this.nivelInvestigacion = 0;
	}
	
	// GETTERS
	
	/**
	 * Metodo get del nombre de la vacuna
	 * @return nombre. Devuelve el nombre de la vacuna
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Metodo get del nivel de investigacion de la vacuna
	 * @return nivelInvestigacion. Devuelve el nivel de investigacion de la vacuna
	 */
	public int getNivelInvestigacion() {
		return nivelInvestigacion;
	}
	
	// SETTERS
	
	/**
	 * Metodo set del nivel de investigacion de la vacuna
	 * @param nivelInvestigacion. Este parametro indica el nivel de invesitgacion de la vacuna
	 */
	public void setNivelInvestigacion(int nivelInvestigacion) {
		this.nivelInvestigacion = nivelInvestigacion;
	}
	
	// INVESTIGAR VACUNA
	
	/**
	 * Metodo para aumentar el nivel de investigacion de la vacuna
	 * @param porcentajeInvestigacion. Este parametro indica el porcentaje que le vamos a sumar al nivel de investigacion de la vacuna
	 */
	public void investigarVacuna(int porcentajeInvestigacion) {
		this.nivelInvestigacion += porcentajeInvestigacion;
	}
	
	// REINICIAR VACUNA
	
	/**
	 * Metodo reiniciar vacuna, este metodo cambia el nivel de investigacion de la vacuna a 0
	 */
	public void reiniciarVacuna() {
		this.nivelInvestigacion = 0;
	}
	
}