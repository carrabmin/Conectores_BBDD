package modelo.negocio;

import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySql;
import modelo.persistencia.interfaces.DaoCoche;

public class GestorCoche {
	
	private DaoCoche daoCoche = new DaoCocheMySql(); 
	
	public int alta(Coche c){
		boolean alta = daoCoche.altaCoche(c);
		if(alta) {
				return 0;
			}else {
				return 1;
			}
	}
	
	public int baja(int id){
		boolean baja = daoCoche.bajaCoche(id);
		if(baja) {
			return 0;
		}else {
			return 1;
		}
	}
	
	public Coche consulta(int id) {
		Coche consulta = daoCoche.consultaCoche(id);
		return consulta;	
	}
	
	public int modificar(int id, Coche c){
		boolean modificada = daoCoche.modificarCoche(id, c);
		
		if(modificada) {
			return 0;
		}else {
			return 1;
		}	
	}
	
	public List<Coche> listar(){
		List<Coche> listaCoches = daoCoche.listarCoches();
		return listaCoches;
	}
}
