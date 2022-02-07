package modelo.entidad;

public class Pasajero {
	
	private String idPasajero;
	private String nombre;
	private int edad;
	private float peso;
	
	public Pasajero() {
		super();
	}

	public Pasajero(String idPasajero, String nombre, int edad, float peso) {
		super();
		this.idPasajero = idPasajero;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
	}

	public String getIdPasajero() {
		return idPasajero;
	}

	public void setIdPasajero(String idPasajero) {
		this.idPasajero = idPasajero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	@Override
	public String toString() {
		return "Pasajero -> [ID_Pasajero: " + idPasajero + " / Nombre: " + nombre + " / Edad: " + edad + " / Peso: " + peso + "]";
	}
}
