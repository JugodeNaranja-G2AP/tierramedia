package tierramedia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Impresora {
	public static void imprimirItinerarioDeUsuario(Usuario u, String archivo) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(archivo));
		List<Producto> productosComprados = u.obtenerProductosComprados();

		salida.println("Muchas gracias " + u.getNombre() + " por su visita a Tierra Media");
		salida.println(" ");
		salida.println("El resumen de su cuenta:");
		salida.println("Su presupuesto actual: " + u.getPresupuesto() + " monedas de oro.");
		salida.println("Su tiempo disponible actual: " + u.getTiempoDisponible() + " horas.");
		salida.println("=========================================================");
		salida.println("Usted ha adquirido:");
		salida.println(" ");
		for (Producto p : productosComprados) {
			salida.println("Nombre de promo/atracciÃ³n: " + p.getNombre());
			salida.println("Costo: " + p.getCosto() + " monedas de oro.");
			salida.println("Tiempo requerido de visita: " + p.getTiempo() + " horas.");
			salida.println(" ");
		}
		salida.println("=========================================================");
		salida.println(" ");
		salida.println("Total de su compra: " + u.obtenerCostoTotalItinerario() + " monedas de oro.");
		salida.close();
	}

	public static void imprimirElNoItinerario(Usuario u, String archivo) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(archivo));

		salida.println("Muchas gracias " + u.getNombre() + " por su visita a Tierra Media");
		salida.println(" ");
		salida.println("=========================================================");
		salida.println("Usted no ha contratado ninguna atracción");
		salida.println(" ");
		salida.println("Lo esperamos la próxima!");
		salida.println("=========================================================");
		salida.println(" ");
		salida.close();

	}
}
