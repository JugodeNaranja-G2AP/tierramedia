package tierramedia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Impresora {
	public static void imprimirItinerarioDeUsuario(Usuario u, String archivo) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(archivo));

		salida.println("Muchas gracias " + u.getNombre() + " por tu visita a Tierra Media");
		salida.println(" ");
		salida.println("El resumen de tu cuenta:");
		salida.println("Tu presupuesto actual: " + u.getPresupuesto() + " monedas de oro.");
		salida.println("Tu tiempo disponible actual: " + Reloj.conversor(u.getTiempoDisponible()) + ".");
		salida.println("=========================================================");
		salida.println(" ");
		salida.println("Contrataste los siguientes productos:");
		salida.println(u.obtenerNombresdeProductosComprados());
		salida.println("El monto total abonado es: " + u.obtenerCostoTotalItinerario() + " monedas de oro.");
		salida.println(
				"Tu itinerario requiere un tiempo de " + Reloj.conversor(u.obtenerTiempoTotalItinerario()) + ".");
		salida.println(" ");
		salida.println("=========================================================");
		salida.println(" ");
		salida.println("Total de tu compra: " + u.obtenerCostoTotalItinerario() + " monedas de oro.");
		salida.close();
	}

	public static void imprimirElNoItinerario(Usuario u, String archivo) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(archivo));

		salida.println("Muchas gracias " + u.getNombre() + " por su visita a Tierra Media");
		salida.println(" ");
		salida.println("=========================================================");
		salida.println(" No contrataste ninguna atracción.");
		salida.println(" ");
		salida.println("¡Te esperamos la próxima!");
		salida.println("=========================================================");
		salida.println(" ");
		salida.close();

	}
}
