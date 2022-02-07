package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

public class DaoPasajeroMySql implements DaoPasajero {
	
	private Connection conexion;
	
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/bbdd_coches";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean altaPasajero(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into pasajeros (ID_PASAJERO,NOMBRE,EDAD,PESO) "
				+ "values(?,?,?,?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, p.getIdPasajero());
			ps.setString(2, p.getNombre());
			ps.setInt(3, p.getEdad());
			ps.setDouble(4, p.getPeso());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			System.out.println("Alta -> Error al insertar el pasajero: " + p);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		return alta;
	}

	@Override
	public boolean bajaPasajero(String id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from pasajeros where ID_PASAJERO=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			borrado = false;
			System.out.println("Baja -> No se ha podido dar de baja el pasajero con id: " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}

	@Override
	public Pasajero consultaPasajero(String id) {
		if(!abrirConexion()){
			return null;
		}		
		Pasajero pasajero = null;
		
		String query = "select ID_PASAJERO,NOMBRE,EDAD,PESO from pasajeros "
				+ "where ID_PASAJERO=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pasajero = new Pasajero();
				pasajero.setIdPasajero(rs.getString(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getFloat(4));
			}
		} catch (SQLException e) {
			System.out.println("Consulta -> Error al obtener el "
					+ "pasajero con id: " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return pasajero;
	}

	@Override
	public List<Pasajero> listarPasajeros() {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> listaPasajeros = new ArrayList<>();
		
		String query = "select ID_PASAJERO,NOMBRE,EDAD,PESO from pasajeros";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero.setIdPasajero(rs.getString(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getFloat(4));
				
				listaPasajeros.add(pasajero);
			}
		} catch (SQLException e) {
			System.out.println("Listar -> error al obtener los "
					+ "coches");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaPasajeros;
	}

	@Override
	public boolean addPasajeroCoche(String idP, int idC) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean alta = true;
		
		String query01 = "update pasajeros set ID_COCHE=? WHERE ID_PASAJERO=?";
		String query02 = "insert into detalles (ID_COCHE,ID_PASAJERO) "
				+ "values(?,?)";
		try {
			conexion.setAutoCommit(false);
			
			PreparedStatement ps01 = conexion.prepareStatement(query01);
			ps01.setInt(1, idC);
			ps01.setString(2, idP);
			
			int numeroFilasAfectadas01 = ps01.executeUpdate();
			if(numeroFilasAfectadas01 == 0)
				alta = false;
			
			PreparedStatement ps02 = conexion.prepareStatement(query02);
			ps02.setInt(1, idC);
			ps02.setString(2, idP);
			
			int numeroFilasAfectadas02 = ps02.executeUpdate();
			if(numeroFilasAfectadas02 == 0)
				alta = false;
			
			conexion.commit();
			
		}catch (SQLException e) {
			alta = false;
			System.out.println("Ha ocurrido un error al ejecutar la transaccion");
			System.out.println(e.getMessage()); 
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}finally {
			cerrarConexion();
		}
		return alta;
	}

	@Override
	public boolean deletePasajeroCoche(String idP, int idC) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean baja = true;
		
		String query01 = "update pasajeros set ID_COCHE=NULL WHERE ID_PASAJERO=?";
		String query02 = "delete from detalles WHERE ID_COCHE=? AND ID_PASAJERO=?";
		
	
		try {
			conexion.setAutoCommit(false);
			
			PreparedStatement ps01 = conexion.prepareStatement(query01);
			ps01.setString(1, idP);
			
			int numeroFilasAfectadas01 = ps01.executeUpdate();
			if(numeroFilasAfectadas01 == 0)
				baja = false;
			
			PreparedStatement ps02 = conexion.prepareStatement(query02);
			ps02.setInt(1, idC);
			ps02.setString(2, idP);
			
			int numeroFilasAfectadas02 = ps02.executeUpdate();
			if(numeroFilasAfectadas02 == 0)
				baja = false;
			
			conexion.commit();
			
		}catch (SQLException e) {
			baja = false;
			System.out.println("Ha ocurrido un error al ejecutar la transaccion");
			System.out.println(e.getMessage()); 
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				System.out.println("Rollback -> Error al realizar "
						+ "el rollback");
				e1.printStackTrace();
			}
			
		}finally {
			cerrarConexion();
		}
		return baja;		
	}

	@Override
	public List<Coche> cochesDisponibles() {
		if(!abrirConexion()){
			return null;
		}		
		
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select ID_PASAJERO,MATRICULA,MARCA,MODELO,COLOR "
				+ "from COCHES as a " + "INNER JOIN detalles as b " 
				+ "ON a.ID_COCHE=b.ID_COCHE "
				+ "HAVING COUNT(*)<5";
		
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Coche coche = new Coche();
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setColor(rs.getString(5));
				
				listaCoches.add(coche);
			}
		} catch (SQLException e) {
			System.out.println("Listar -> Error al obtener los "
					+ "coches disponibles");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaCoches;
	}

	@Override
	public List<Pasajero> pasajerosAsociados(int idC) {
		if(!abrirConexion()){
			return null;
		}		
		
		List<String> listaIdPasajeros = new ArrayList<>();
		List<Pasajero> listaPasajerosAsociados = new ArrayList<>();
		
		String query01 = "select ID_PASAJERO from detalles WHERE ID_COCHE=?";
		String query02 = "select ID_PASAJERO,NOMBRE,EDAD,PESO from pasajeros WHERE ID_PASAJERO=?";
		try {
			
			conexion.setAutoCommit(false);
			
			PreparedStatement ps01 = conexion.prepareStatement(query01);
			ps01.setInt(1, idC);
			
			ResultSet rs01 = ps01.executeQuery();
			
			while(rs01.next()){
				listaIdPasajeros.add(rs01.getString(1));
			}
			
			for (String s: listaIdPasajeros) {
				try {
					PreparedStatement ps02 = conexion.prepareStatement(query02);
					ps02.setString(1, s);
					ResultSet rs02 = ps02.executeQuery();
					
					while(rs02.next()){
						Pasajero pasajero = new Pasajero();
						pasajero.setIdPasajero(rs02.getString(1));
						pasajero.setNombre(rs02.getString(2));
						pasajero.setEdad(rs02.getInt(3));
						pasajero.setPeso(rs02.getFloat(4));
						
						listaPasajerosAsociados.add(pasajero);
					}
				}catch (SQLException e) {
					System.out.println("Listar -> Error al obtener la lista "
							+ "de pasajeros asociados");
					e.printStackTrace();
				
				}
			}
			
			conexion.commit();
			
		} catch (SQLException e) {
			System.out.println("Ha ocurrido un error al ejecutar la transaccion");
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				System.out.println("Rollback -> Error al realizar "
						+ "el rollback");
				e1.printStackTrace();
			}
		} finally {
			cerrarConexion();
		}
		return listaPasajerosAsociados;
	}
	
	@Override
	public boolean cocheDisponible(int idC) {
		if(!abrirConexion()){
			return false;
		}		
		int cuenta = 0;
		
		String query = "select COUNT(*) from detalles WHERE ID_COCHE=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idC);
			
			ResultSet rs = ps.executeQuery();
			
			//while(rs.next()){
				cuenta = rs.getInt(1);
			//}
		}catch (SQLException e) {
			System.out.println("Ha ocurrido un error al ejecutar la consulta de cuenta");
			e.printStackTrace();
		}finally {
			cerrarConexion();
		}
		
		if (cuenta < 5) {
			return true;
		}else {
			return false;
		}
	}
}
