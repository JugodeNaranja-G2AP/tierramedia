package tierramedia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TierraMedia {

	@SuppressWarnings("unused")
	private List<Usuario> usuarios;
	private Atraccion[] atracciones;
	@SuppressWarnings("unused")
	private Promocion[] promos;

	public TierraMedia() {
	}

	public void agregarTodasAtracciones(String archivoAtracciones) {
		this.atracciones = obtenerAtraccionesDesdeArchivo(archivoAtracciones);
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
		this.usuarios = obtenerUsuariosDesdeArchivo(archivo);
	}

	public List<Usuario> obtenerUsuariosDesdeArchivo(String archivo) {
		FileReader fr = null;
		BufferedReader br = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea = br.readLine();

			while (linea != null) {
				try {
					String[] datosUsuarios = linea.split(";");
					String nombre = datosUsuarios[0];
					int presupuesto = Integer.parseInt(datosUsuarios[1]);
					double tiempo = Double.parseDouble(datosUsuarios[2]);
					Tipo tipo = Tipo.valueOf(Tipo.class, datosUsuarios[3].trim().toUpperCase());
					Usuario usuario = new Usuario(nombre, presupuesto, tiempo, tipo);

					usuarios.add(usuario);
					linea = br.readLine();

				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leídos no es un double o un entero");
				}

			}
			return usuarios;

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

		return usuarios;

	}

	public Atraccion[] obtenerAtraccionesDesdeArchivo(String archivo) {

		Atraccion[] atracciones = null;

		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(new File(archivo));
			br = new BufferedReader(fr);

			int cantidad = Integer.parseInt(br.readLine());
			atracciones = new Atraccion[cantidad];
			int indice = 0;

			String linea = br.readLine();

			while (linea != null) {
				String[] datosAtraccion = linea.split(";");
				String nombre = datosAtraccion[0];
				int costo = Integer.parseInt(datosAtraccion[1]);
				double tiempo = Double.parseDouble(datosAtraccion[2]);
				int cupo = Integer.parseInt(datosAtraccion[3]);
				String t = datosAtraccion[4].trim().toUpperCase();
				Tipo tipo = Tipo.valueOf(Tipo.class, t);
				atracciones[indice++] = new Atraccion(nombre, costo, tiempo, cupo, tipo);
				linea = br.readLine();
			}
			return atracciones;

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
		return atracciones;
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

		TierraMedia tm = new TierraMedia();
		List<Usuario> usuarios = tm.obtenerUsuariosDesdeArchivo("entrada/usuarios.csv");
		Atraccion[] atracciones = tm.obtenerAtraccionesDesdeArchivo("entrada/atracciones.csv");
		// Usuarios y atracciones.
		System.out.println(Arrays.toString(atracciones));
		System.out.println(usuarios);
	}

}























