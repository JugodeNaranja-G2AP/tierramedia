package tierramedia;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TierraMedia {

	protected List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	private List<Promocion> promos;
	private List<Producto> productos; 
	

	public TierraMedia() {
		this.productos = new ArrayList<Producto>();
	}

	public void agregarTodasAtracciones(String archivoAtracciones) {
		this.atracciones = CargarAtracciones.obtener(archivoAtracciones);
	}

	public void agregarTodasPromociones(String archivoPromos, String archivoAtracciones) {
		this.promos = CargarPromociones.obtener(archivoPromos, archivoAtracciones);
	}

	public void agregarAtraccionesaListaProductos() {
		for (Atraccion atraccion : atracciones) {
			productos.add(atraccion);
		}
	}

	public void agregarPromosaListaProductos() {
		for (Promocion promo : this.promos) {
			productos.add(promo);
		}
	}
	
	public List<Promocion> obtenerListaDePromos() {
		return this.promos;
	}

	public List<Producto> obtenerListaProductos() {
		return this.productos;
	}

	public List<Usuario> obtenerListaUsuarios() {
		return this.usuarios;
	}

	public void agregarUsuariosDesdeArchivo(String archivo) {
		this.usuarios = CargarUsuarios.obtener(archivo);
	}
	
	public List<Producto> OrdenarProductosDeLista(Usuario u) {
		Collections.sort(this.productos, new OrdenadorDeProducto(u.getTipoDeAtraccionPreferida()));
		return this.productos;
	}

	public void imprimirItinerarioDeUsuario(Usuario u, String archivo) throws IOException {
		PrintWriter salida = new PrintWriter(new FileWriter(archivo));
		List<Producto> productosComprados = u.obtenerProductosComprados();
		
		salida.println("Muchas gracias "+ u.getNombre()  +" por su visita a Tierra Media");
		salida.println(" ");
		salida.println("El resumen de su cuenta:");
		salida.println("Su presupuesto actual: " + u.getPresupuesto() + " monedas de oro.");
		salida.println("Su tiempo disponible actual: " + u.getTiempoDisponible() + " horas.");
		salida.println("=========================================================");
		salida.println("Usted ha adquirido:");
		salida.println(" ");
		for (Producto p: productosComprados) {
			salida.println("Nombre de promo/atracción: "+ p.getNombre());
			salida.println("Costo: "+ p.getCosto() + " monedas de oro.");
			salida.println("Tiempo requerido de visita: "+ p.getTiempo() + " horas.");
			salida.println(" ");
		}	
		salida.println("=========================================================");
		salida.println(" ");
		salida.println("Total de su compra: "+ u.obtenerCostoTotalItinerario() + " monedas de oro.");
		salida.close();

	}

}
