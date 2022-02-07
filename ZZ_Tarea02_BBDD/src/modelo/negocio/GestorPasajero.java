package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.persistencia.DaoPasajeroMySql;
import modelo.persistencia.interfaces.DaoPasajero;

public class GestorPasajero {

private DaoPasajero daoPasajero = new DaoPasajeroMySql(); 
	
	public int alta(Pasajero p){
		boolean alta = daoPasajero.altaPasajero(p);
		if(alta) {
				return 0;
			}else {
				return 1;
			}
	}
	
	public int baja(String id){
		boolean baja = daoPasajero.bajaPasajero(id);
		if(baja) {
			return 0;
		}else {
			return 1;
		}
	}
	
	public Pasajero consulta(String id) {
		Pasajero consulta = daoPasajero.consultaPasajero(id);
		return consulta;
	}
	
	public List<Pasajero> listar() {
		List<Pasajero> listaPasajeros = daoPasajero.listarPasajeros();
		return listaPasajeros;
	}
	
	public int addPasajeroCoche(String idP, int idC) {
		boolean add = daoPasajero.addPasajeroCoche(idP, idC);
		if (add) {
			return 0;
		}else {
			return 1;
		}
	}
	
	public int deletePasajeroCoche(String idP, int idC) {
		boolean delete = daoPasajero.deletePasajeroCoche(idP, idC);
		if (delete) {
			return 0;
		}else {
			return 1;
		}
	}
	
	public List<Pasajero> pasajerosAsociados(int idC) {
		List<Pasajero> listaPasajeros = daoPasajero.pasajerosAsociados(idC);
		return listaPasajeros;
	}
	
	public int cocheDisponible(int idC) {
		boolean cuenta = daoPasajero.cocheDisponible(idC);
		if (cuenta) {
			return 0;
		}else {
			return 1;
		}
	}
	
	public List<Coche> cochesDisponibles() {
		List<Coche> listaCoches = daoPasajero.cochesDisponibles();
		return listaCoches;
	}
}
