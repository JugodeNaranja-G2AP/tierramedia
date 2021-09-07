package main;

import java.util.List;
import java.util.Scanner;

import tierramedia.Producto;
import tierramedia.TierraMedia;
import tierramedia.Usuario;

public class App {

	public static void main(String[] args) {
		TierraMedia tm = new TierraMedia();
		tm.agregarUsuariosDesdeArchivo("entrada/usuarios.csv");
		tm.agregarTodasAtracciones("entrada/atracciones.csv");
		tm.agregarTodasPromociones("entrada/promos.txt", "entrada/atracciones.csv");
		tm.agregarPromosaListaProductos();
		tm.agregarAtraccionesaListaProductos();
		
		List<Usuario> usuarios =  tm.obtenerListaUsuarios();
		
		// Consola
		for (Usuario u : usuarios) {
			List<Producto> productosOrdendos = tm.OrdenarProductosDeLista(u);
			String respuesta;
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);

			System.out.println("Bienvenid@ " + u.getNombre() + " a Tierra Media.");
			System.out.println("Sus datos y perfil son los siguientes: \n\n" + u);
			System.out
					.println("A continuación se le mostrará atracciones y promos turísticas que ofrece Tierra Media.");
			System.out
					.println("Algunos de estos packs ofrecen descuentos y reducciones del precio total del paquete.\n");
			System.out.println("=========================================================================\n");

			for (Producto p : productosOrdendos) {

				if (p.puedeSerOfertadoA(u)) {
					if (u.comproElProducto(p)) {
						continue;
					} else {
						System.out.println(p);
						System.out.println("Su presupuesto: "+ u.getPresupuesto() + ""
								+ " monedas de oro \nSu tiempo disponible: "+ u.getTiempoDisponible() + " horas.");
						System.out.println("\n");
							do {

								System.out.println("Ingrese si acepta con: si o no ");
								
								respuesta = sc.nextLine();
								System.out.println("\n");

							} while (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no"));

							if (respuesta.equalsIgnoreCase("si")) {
								u.reservarProducto(p);
							} else {
								continue;
							}					
					}

				}
			}
			
		}
		

	}

}
