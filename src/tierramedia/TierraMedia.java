package tierramedia;

import java.io.IOException;
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

	public void escibirItinerarioDeUsuario() throws IOException {
		// TODO: Finalizado el procesamiento, se obtendr� un archivo de salida para cada
		// usuario,
		//con los datos del usuario, su compra, los totales a pagar y el tiempo que deber�
		//invertir en dicha salida.

	}

}
