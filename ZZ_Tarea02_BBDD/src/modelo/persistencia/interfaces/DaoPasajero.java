package modelo.persistencia.interfaces;

import java.util.List;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;

public interface DaoPasajero {
	
	public boolean altaPasajero(Pasajero p);
	public boolean bajaPasajero(String id);
	public Pasajero consultaPasajero(String id);
	public List<Pasajero> listarPasajeros();
	
	public boolean addPasajeroCoche(String idP, int idC);
	public boolean deletePasajeroCoche(String idP, int idC);
	
	public List<Coche> cochesDisponibles();
	public List<Pasajero> pasajerosAsociados(int idC);
	public boolean cocheDisponible(int idC);

}
