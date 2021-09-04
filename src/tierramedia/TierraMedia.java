package tierramedia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TierraMedia {

	protected List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	@SuppressWarnings("unused")
	private Promocion[] promos;

	public TierraMedia() {
	}

	public void agregarTodasAtracciones(String archivoAtracciones) {
		this.atracciones = CargarAtracciones.obtener(archivoAtracciones);
	}

	public void agregarTodasPromociones(String archivoPromos) {
		this.promos = obtenerPromosDesdeArchivo(archivoPromos);
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

	public Promocion[] obtenerPromosDesdeArchivo(String archivo) {

		FileReader fr = null;
		BufferedReader br = null;

		Promocion[] promos = null;

		try {
			fr = new FileReader(new File(archivo));
			br = new BufferedReader(fr);

			int cantidad = Integer.parseInt(br.readLine());
			promos = new Promocion[cantidad];
			int indice = 0;

			String claseDePromo = br.readLine();

			if ((claseDePromo).equals("PromoAxB")) {

				

			}
			if ((claseDePromo).equals("PromoAbsoluta")) {

				
			}
			
			if ((claseDePromo).equals("PromoDescuento")) {
				
				
			}

			return promos;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return promos;
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
		/* Usuarios y atracciones.
		System.out.println(Arrays.toString(atracciones));
		System.out.println(usuarios);
		*/
	}
}