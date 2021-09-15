package main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import tierramedia.Impresora;
import tierramedia.Producto;
import tierramedia.Reloj;
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

		List<Usuario> usuarios = tm.obtenerListaUsuarios();

		// Consola
		for (Usuario u : usuarios) {
			List<Producto> productosOrdenados = tm.ordenarProductosDeLista(u);
			String respuesta;
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);

			System.out.println("                 ¡Bienvenid@ " + u.getNombre() + " a Tierra Media!");
			System.out.println("\nTus datos y perfil son los siguientes: \n\n" + u);
			System.out
					.println("A continuación te mostraremos atracciones y promos turísticas que ofrece Tierra Media.");
			System.out
					.println("Algunos de estos packs ofrecen descuentos y reducciones del precio total del paquete.\n");
			System.out.println(
					"============================================================================================");

			System.out.println("\n                         Presiona enter para continuar");

			if (sc.hasNextLine()) {
				sc.nextLine();
			}
			System.out.println(
					"============================================================================================\n");

			for (Producto p : productosOrdenados) {

				if (p.puedeSerOfertadoA(u)) {
					if (u.comproElProducto(p)) {
						continue;
					} else {
						String tipoDeProducto = p.esPromocion() ? "promo" : "atracción";
						System.out.println(p);
						System.out.println(" Tu presupuesto actual es de " + u.getPresupuesto()
								+ " monedas de oro \n y tu tiempo disponible es de "
								+ Reloj.conversor(u.getTiempoDisponible()) + ".");
						System.out.println("\n");
						do {

							System.out.println(
									"                     ¿Querés comprar esta " + tipoDeProducto + "? (si/no)");

							respuesta = sc.nextLine();
							System.out.println("\n");

						} while (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no"));

						if (respuesta.equalsIgnoreCase("si")) {
							u.reservarProducto(p);
							System.out.println(
									"¡Felicitaciones! Adquiriste la " + tipoDeProducto + " " + p.getNombre() + ".");
							System.out.println("\n                         Presiona enter para continuar");

							if (sc.hasNextLine()) {
								sc.nextLine();
							}
							System.out.println(
									"============================================================================================\n");
						} else {
							continue;
						}
					}

				}
			}
			if (u.productosReservados.size() == 0) {
				System.out.println("==========================================================================");
				System.out.println("\n                 No compraste ninguna atracción. \n");
				System.out.println("                 ¡Podes volver cuando quieras! \n");
				System.out.println("\n");
				System.out.println("==========================================================================");
				String archivoDeSalida = "salida/" + u.getNombre() + ".txt";
				try {
					Impresora.imprimirElNoItinerario(u, archivoDeSalida);
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("\n                         Presiona enter para continuar");

				if (sc.hasNextLine()) {
					sc.nextLine();
				}
				System.out.println(
						"============================================================================================\n");
				continue;
			}

			System.out.println(
					"¡Ya no podemos ofrecerte atracciones que respondan a tu tiempo o presupuesto disponible!");
			System.out.println("\nContrataste los siguientes productos:");
			System.out.println(u.obtenerNombresdeProductosComprados());
			System.out.println("El monto total abonado es: " + u.obtenerCostoTotalItinerario() + " monedas de oro.");
			System.out.println(
					"Tu itinerario requiere un tiempo de " + Reloj.conversor(u.obtenerTiempoTotalItinerario()) + ".\n");
			System.out.println(
					"============================================================================================\n");
			String archivoDeSalida = "salida/" + u.getNombre() + ".txt";
			try {
				Impresora.imprimirItinerarioDeUsuario(u, archivoDeSalida);
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("\n                         Presiona enter para continuar");

			if (sc.hasNextLine()) {
				sc.nextLine();
			}
			System.out.println(
					"============================================================================================\n");
		}

	}

}