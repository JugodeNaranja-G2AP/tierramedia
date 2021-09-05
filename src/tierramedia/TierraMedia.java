package tierramedia;

import java.io.IOException;
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

	public void agregarTodasPromociones(String archivoPromos) {
		this.promos = CargarPromociones.obtener(archivoPromos);
	}

	public Atraccion agregarAtraccionPorNombre(String nombre) {
		for (Atraccion atraccion : this.atracciones) {
			if (atraccion.getNombre().equals(nombre)) {
				return atraccion;
			}
		}
		return null;
	}

	public void agregarUsuariosDesdeArchivo(String archivo) {
		this.usuarios = CargarUsuarios.obtener(archivo);
	}

	public void escibirItinerarioDeUsuario() throws IOException {
		// TODO: Finalizado el procesamiento, se obtendrá un archivo de salida para cada
		// usuario,
//		con los datos del usuario, su compra, los totales a pagar y el tiempo que deberá
//		invertir en dicha salida.

	}

	public static void main(String[] args) {

		 System.out.println(CargarUsuarios.obtener("entrada/usuarios.csv"));
		 System.out.println(CargarAtracciones.obtener("entrada/atracciones.csv"));
		 //System.out.println(CargarPromociones.obtener("entrada/promos.txt"));
		 /* Usuarios y atracciones.
		System.out.println(Arrays.toString(atracciones));
		System.out.println(usuarios);
		*/
	}
}