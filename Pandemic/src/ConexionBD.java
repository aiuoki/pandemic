import java.sql.*;

/**
 * 
 * Esta es la clase encargada de hacer la conexion con la base de datos
 * @author aiuoki
 *
 */
public class ConexionBD {
	
	/**
	 * Usuario para la conexion a la base de datos
	 */
	private static final String USER = "DAW_PNDC22_23_PLEUDA";
	
	/**
	 * Credenciales para la conexion a la base de datos
	 */
	private static final String PWD = "PED123";
	
	// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
	/**
	 * URL para la conexion a la base de datos
	 */
	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";
	
	/**
	 * Metodo encargado de hacer la conexion a la base de datos
	 * @return con. Devuelve la conexion a la base de datos
	 */
	public Connection conectarBaseDatos() {
		Connection con = null;

		System.out.println("Intentando conectarse a la base de datos");

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PWD);
			System.out.println("Conectados a la base de datos");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}
		
		return con;
	}
	
	/**
	 * Metodo para comprobar la existencia de un usuario
	 * @param con. Este parametro indica la conexion con la base de datos
	 * @param p_usuario. Este parametro indica el parametro usuario
	 * @return boolean. Devuelve true si ya existe un usuario con el mismo nombre y false en caso contrario
	 */
	public boolean comprobarUsuario(Connection con, String p_usuario) {
		try {			
			// Se prepara el Statement
			CallableStatement st = con.prepareCall( "{?=call pf_fComprobarUsuario(?)}");

			// Se indica que el primer interrogante es de salida.
			st.registerOutParameter(1,Types.NUMERIC);

			// Se pasa un parámetro en el segundo interrogante.
			st.setString(2, p_usuario);

			// Se hace la llamada a la función.
			st.execute( );

			// Se recoge el resultado del primer interrogante.
			int resultado = st.getInt(1);
			
			if(resultado == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Error al comprobar la existencia del usuario: " + e);
		}
		
		return true;
	}
	
	/**
	 * Metodo para registrar un jugador en la base de datos
	 * @param con. Este parametro indica la conexion con la base de datos
	 * @param usuario. Este parametro indica el usuario del jugador
	 * @param nombre. Este parametro indica el nombre del jugador
	 * @param contrasena. Este parametro indica la contrasena del jugador
	 */
	public void registrarJugador(Connection con, String usuario, String nombre, String contrasena) {
		String sql = "INSERT INTO JUGADOR (usuario, nombre, contrasena) VALUES('" + usuario + "', '" + nombre +"', '" + contrasena + "')";

		try {
			Statement st = con.createStatement();
			st.execute(sql);
			
			System.out.println("Jugador registrado correctamente");
		} catch (SQLException e) {
			System.out.println("Error al registrar el jugador: " + e);
		}
	}
	
	/**
	 * Metodo para iniciar sesion
	 * @param con. Este parametro indica la conexion con la base de datos
	 * @param p_usuario. Este parametro indica el nombre de usuario del jugador
	 * @param p_contrasena. Este parametro indica la contrasena del jugador
	 * @return boolean. Devuelve true si las credenciales coinciden con algun jugador y false en caso contrario
	 */
	public boolean iniciarSesionJugador(Connection con, String p_usuario, String p_contrasena) {
		try {			
			// Se prepara el Statement
			CallableStatement st = con.prepareCall( "{?=call pf_fIniciarSesionJugador(?, ?)}");

			// Se indica que el primer interrogante es de salida.
			st.registerOutParameter(1,Types.NUMERIC);

			// Se pasa un parámetro en el segundo interrogante.
			st.setString(2, p_usuario);
			
			// Se pasa el segundo parámetro en el tercer interrogante.
			st.setString(3, p_contrasena);

			// Se hace la llamada a la función.
			st.execute( );

			// Se recoge el resultado del primer interrogante.
			int resultado = st.getInt(1);
			
			if(resultado == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Error al iniciar sesion: " + e);
		}
		
		return false;
	}
	
	/**
	 * Metodo para comprobar si el id de partida que le pasamos pertenece al usuario y esta empezada
	 * @param con. Este parametro indica la conexion con la base de datos
	 * @param p_idPartida. Este parametro indica el id de la partida
	 * @param p_usuario. Este parametro indica el nombre de usuario del jugador
	 * @return boolean. Devuelve true si exsiste una partida con ese id que pertenezca a ese jugador y este empezada, en caso contrario devulve false
	 */
	public boolean comprobarPartida(Connection con, int p_idPartida, String p_usuario) {
		try {			
			// Se prepara el Statement
			CallableStatement st = con.prepareCall( "{?=call pf_fComprobarPartida(?, ?)}");

			// Se indica que el primer interrogante es de salida.
			st.registerOutParameter(1,Types.NUMERIC);

			// Se pasa un parámetro en el segundo interrogante.
			st.setInt(2, p_idPartida);
			
			// Se pasa el segundo parámetro en el tercer interrogante.
			st.setString(3, p_usuario);

			// Se hace la llamada a la función.
			st.execute( );

			// Se recoge el resultado del primer interrogante.
			int resultado = st.getInt(1);
			
			if(resultado == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Error al iniciar sesion: " + e);
		}
		
		return false;
	}
	
}