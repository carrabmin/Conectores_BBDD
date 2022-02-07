package vista;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.entidad.Pasajero;
import modelo.negocio.GestorCoche;
import modelo.negocio.GestorPasajero;

public class MainGestionCoches {

	public static void main(String[] args) {
			
		System.out.println("Bienvenidos a nuestra CRUD de Coches\n");
		Scanner sc = new Scanner(System.in);
		boolean fin = false;
		GestorCoche gc = new GestorCoche();
		GestorPasajero gp = new GestorPasajero();
		
		do {
			menuCoches();
			int opcion01 = sc.nextInt();
			switch (opcion01) {
			case 1:
				System.out.println("\n*********** ALTA COCHE ***********");
				sc.nextLine();
				
				System.out.println("Introduzca la matrícula:");
				String mat01 = sc.nextLine();
				System.out.println("Introduzca la marca:");
				String marca01 = sc.nextLine();
				System.out.println("Introduzca el modelo:");
				String modelo01 = sc.nextLine();
				System.out.println("Introduzca el color");
				String color01 = sc.nextLine();
				
				Coche c01 = new Coche(mat01, marca01, modelo01, color01);
				
				int alta = gc.alta(c01);
				if(alta == 0) {
					System.out.println("Coche: " + c01 + " dado de alta\n");
				}else if(alta == 1) {
					System.out.println("Error de conexión con la BBDD");
				}
				break;
			
			case 2:
				System.out.println("\n*********** BAJA COCHE POR ID ***********");
				sc.nextLine();
				
				System.out.println("Introduzca el ID del coche:");
				int id01 = sc.nextInt();
				
				int baja = gc.baja(id01);
				if(baja == 0) {
					System.out.println("Coche con id: " + id01 + " dado de baja\n");
				}else if(baja == 1) { 
					System.out.println("Error de conexión con la BBDD");
				}
				break;
				
			case 3:
				System.out.println("\n*********** CONSULTA COCHE POR ID ***********");
				sc.nextLine();
				
				System.out.println("Introduzca el ID del coche:");
				int id02 = sc.nextInt();
				
				Coche coche = gc.consulta(id02);
				
				if (coche == null) {
					System.out.println("Error de conexión con la BBDD");
				}else {
					System.out.println(coche + "\n");
				}
				break;

			case 4:
				System.out.println("\n*********** MODIFICAR COCHE POR ID ***********");
				sc.nextLine();
				
				System.out.println("Introduzca el ID del coche a modificar:");
				int id03 = sc.nextInt();
				sc.nextLine();
				
				System.out.println("Introduzca la nueva matrícula:");
				String mat02 = sc.nextLine();
				System.out.println("Introduzca la nueva marca:");
				String marca02 = sc.nextLine();
				System.out.println("Introduzca el nuevo modelo:");
				String modelo02 = sc.nextLine();
				System.out.println("Introduzca el nuevo color");
				String color02 = sc.nextLine();
				
				Coche c02 = new Coche(mat02, marca02, modelo02, color02);
				
				int mod = gc.modificar(id03, c02);
				
				if(mod == 0) {
					System.out.println("Coche con id: " + id03 + " modificado\n");
				}else if(mod == 1) {
					System.out.println("Error de conexión con la BBDD");
				}
				break;
				
			case 5:
				System.out.println("\n*********** LISTADO DE COCHES ***********");
				sc.nextLine();
				
				List<Coche> listaCoches01 = gc.listar();
				
				if(listaCoches01 == null) {
					System.out.println("Error de conexión con la BBDD");
				}else {
					System.out.println(listaCoches01 + "\n");
				}
				break;
				
			case 6:
				menuPasajeros();
				int opcion02 = sc.nextInt();
				switch (opcion02) {
					case 1:
						System.out.println("\n*********** ALTA PASAJERO ***********");
						sc.nextLine();
						
						System.out.println("Introduzca el ID:");
						String id04 = sc.nextLine();
						System.out.println("Introduzca el nombre:");
						String nombre01 = sc.nextLine();
						System.out.println("Introduzca la edad:");
						int edad01 = sc.nextInt();
						System.out.println("Introduzca el peso");
						float peso01 = sc.nextFloat();
						
						Pasajero p01 = new Pasajero(id04, nombre01, edad01, peso01);
						
						int alta01 = gp.alta(p01);
						if(alta01 == 0) {
							System.out.println("Pasajero: " + p01 + " dado de alta\n");
						}else if(alta01 == 1) {
							System.out.println("Error de conexión con la BBDD");
						}
						break;
					
					case 2:
						System.out.println("\n*********** BAJA PASAJERO POR ID ***********");
						sc.nextLine();
						
						System.out.println("Introduzca el ID del pasajero:");
						String id05 = sc.nextLine();
						
						int baja02 = gp.baja(id05);
						if(baja02 == 0) {
							System.out.println("Pasajero con id: " + id05 + " dado de baja\n");
						}else if(baja02 == 1) { 
							System.out.println("Error de conexión con la BBDD");
						}
						break;
						
					case 3:
						System.out.println("\n*********** CONSULTA PASAJERO POR ID ***********");
						sc.nextLine();
						
						System.out.println("Introduzca el ID del pasajero:");
						String id06 = sc.nextLine();
						
						Pasajero pasajero = gp.consulta(id06);
						
						if (pasajero == null) {
							System.out.println("Error de conexión con la BBDD");
						}else {
							System.out.println(pasajero + "\n");
						}
						break;
						
					case 4:
						System.out.println("\n*********** LISTADO DE PASAJEROS ***********");
						sc.nextLine();
						
						List<Pasajero> listaPasajeros = gp.listar();
						
						if(listaPasajeros == null) {
							System.out.println("Error de conexión con la BBDD");
						}else {
							System.out.println(listaPasajeros + "\n");
						}
						break;
						
					case 5:
						System.out.println("\n*********** AÑADIR PASAJERO A COCHE ***********");
						sc.nextLine();
						/*
						System.out.println("\nCOCHES DISPOBILES:\n");
						
						List<Coche> listaCoches02 = gp.cochesDisponibles();
						
						if(listaCoches02 == null) {
							System.out.println("Error de conexión con la BBDD");
						}else {
							System.out.println(listaCoches02);
						}
						*/
						System.out.println("Introduzca el ID del pasajero:");
						String id07 = sc.nextLine();
						System.out.println("Introduzca el ID del coche:");
						int id08 = sc.nextInt();
						
						//int completo = gp.cocheDisponible(id08);
						
						//if (completo == 0) {
							int asoc01 = gp.addPasajeroCoche(id07, id08);
							
							if(asoc01 == 0) {
								System.out.println("Pasajero con id: " + id07 + " asociado al coche con id: " + id08 + "\n");
							}else if (asoc01 == 1) { 
								System.out.println("Error de conexión con la BBDD");
							}
							
						//}else if(completo == 1) {
						//	System.out.println("COCHE COMPLETO - No es posible asociar el pasajero");
						//}
						break;
						
					case 6:
						System.out.println("\n*********** ELIMINAR PASAJERO DE COCHE ***********");
						sc.nextLine();
						
						System.out.println("Introduzca el ID del coche:");
						int id09 = sc.nextInt();
						
						System.out.println("Pasajeros asociados al coche con id: " + id09 + "\n");
						
						List<Pasajero> listaPasajeros02 = gp.pasajerosAsociados(id09);

						if (listaPasajeros02 == null) {
							System.out.println("Error de conexión con la BBDD");
						}else {
							System.out.println(listaPasajeros02);
									
							System.out.println("\nIntroduzca el ID del pasajero a borrar:");
							sc.nextLine();
							String id10 = sc.nextLine();
							
							
							int delete = gp.deletePasajeroCoche(id10, id09);
							if (delete == 0) {
								System.out.println("Pasajero con id: " + id10 + " borrado del coche: " + id09 + "\n");
							}else if(delete == 1) {
								System.out.println("Error de conexión con la BBDD");
							}
							
						}
						break;
						
					case 7:
						System.out.println("\n*********** LISTADO DE PASAJEROS ASOCIADOS AL COCHE ***********");
						sc.nextLine();
						
						System.out.println("Introduzca el id del coche:");
						int id11 = sc.nextInt();
						
						List<Pasajero> listaPasajeros03 = gp.pasajerosAsociados(id11);
						
						if(listaPasajeros03 == null) {
							System.out.println("Error de conexión con la BBDD");
						}else {
							System.out.println(listaPasajeros03 + "\n");
						}
						break;
						
					default: 
						System.out.println("Opción elegida no válida (1-7)\n");
				}
				break;
					
			case 7:
				fin = true;
				break;
				
			default: 
				System.out.println("Opción elegida no válida (1-7)\n");
			}
			
		}while(!fin);
		
		sc.close();
		
		System.out.println("FIN DE PROGRAMA");

	}
	
	private static void menuCoches() {
		System.out.println("MENU COCHES - Elija una opción:");
		System.out.println("1. - Añadir un nuevo coche");
		System.out.println("2. - Borrar coche por ID");
		System.out.println("3. - Consultar coche por ID");
		System.out.println("4. - Modificar coche por ID");
		System.out.println("5. - Listado de coches");
		System.out.println("6. - Gestión de pasajeros");
		System.out.println("7. - Terminar programa");
	}
	
	private static void menuPasajeros() {
		System.out.println("GESTIÓN DE PASAJEROS - Elija una opción:");
		System.out.println("1. - Añadir un nuevo pasajero");
		System.out.println("2. - Borrar pasajero por ID");
		System.out.println("3. - Consultar pasajero por ID");
		System.out.println("4. - Listado de pasajeros");
		System.out.println("5. - Añadir pasajero a un coche");
		System.out.println("6. - Eliminar pasajero de un coche");
		System.out.println("7. - Listas los pasajeros de un coche");
	}

}
