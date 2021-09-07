package tierramedia;

import java.io.IOException;
<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collections;
=======
>>>>>>> promoSync
import java.util.List;

public class TierraMedia {

	protected List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	@SuppressWarnings("unused")
	private List<Promocion> promos;
	

	public TierraMedia() {
	}

	public void agregarTodasAtracciones(String archivoAtracciones) {
		this.atracciones = CargarAtracciones.obtener(archivoAtracciones);
	}

	public void agregarTodasPromociones(String archivoPromos, String archivoAtracciones) {
		this.promos = CargarPromociones.obtener(archivoPromos, archivoAtracciones);
	}

	public Atraccion agregarAtraccionPorNombre(String nombre) {
		for (Atraccion atraccion : this.atracciones) {
			if (atraccion.getNombre().equals(nombre)) {
				return atraccion;
			}
		}
		return null;
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

	public List<Producto> obtenerListaProductos() {
		return this.productos;
	}

	public List<Usuario> obtenerListaUsuarios() {
		return this.usuarios;
	}

	public void agregarUsuariosDesdeArchivo(String archivo) {
		this.usuarios = CargarUsuarios.obtener(archivo);
	}

	public void escibirItinerarioDeUsuario() throws IOException {
		// TODO: Finalizado el procesamiento, se obtendr� un archivo de salida para cada
		// usuario,
//		con los datos del usuario, su compra, los totales a pagar y el tiempo que deber�
//		invertir en dicha salida.

	}

	public static void main(String[] args) {

		//System.out.println(CargarUsuarios.obtener("entrada/usuarios.csv"));
		//System.out.println(CargarAtracciones.obtener("entrada/atracciones.csv"));
		System.out.println(CargarPromociones.obtener("entrada/promos.txt", "entrada/atracciones.csv"));
		 /* Usuarios y atracciones.
		System.out.println(Arrays.toString(atracciones));
		System.out.println(usuarios);
		*/
	}
}
