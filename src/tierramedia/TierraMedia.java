package tierramedia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TierraMedia {

	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private Atraccion[] atracciones;
	private List<Promocion> promos = new ArrayList<Promocion>();
	private List<Producto> productos = new ArrayList<Producto>();
	@SuppressWarnings("unused")
	private OrdenadorDeProducto ordenadorDeProductos;

	public TierraMedia() {
	}

	public void agregarAtraccionesDesdeArchivo(String archivoAtracciones) {
		this.atracciones = obtenerAtraccionesDesdeArchivo(archivoAtracciones);
	}

	public void agregarPromocionesDesdeArchivo(String archivoPromos) {
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
		this.usuarios = obtenerUsuariosDesdeArchivo(archivo);
	}

	public void OrdenarProductosDeListaSegunUsuario(Usuario u) {
		Collections.sort(this.productos, new OrdenadorDeProducto(u.getTipoDeAtraccionPreferida()));
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

	public List<Promocion> obtenerPromosDesdeArchivo(String archivo) {
		FileReader fr = null;
		BufferedReader br = null;
		List<Promocion> promos = new ArrayList<Promocion>();

		try {
			fr = new FileReader(new File(archivo));
			br = new BufferedReader(fr);

			String linea2 = br.readLine();

			while (linea2 != null) {
				String[] datosPromos = linea2.split(",");
				Integer costo = Integer.parseInt(datosPromos[1]);
				String nombre = datosPromos[2];
				Tipo tipo = Tipo.valueOf(datosPromos[3].toUpperCase());
				String[] atraccionesString = datosPromos[4].split(";");
				int indiceBase = atraccionesString.length;
				Atraccion[] atracciones = new Atraccion[indiceBase];
				for (int i = 0; i < indiceBase; i++) {
					atracciones[i] = agregarAtraccionPorNombre(atraccionesString[i]);
				}

				Promocion promo = new PromoAbsoluta(nombre, tipo, atracciones, costo);
				promos.add(promo);
				linea2 = br.readLine();
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
		// Obtiene usuarios, atracciones y promos desde archivo y los agrega a tm.
		tm.agregarUsuariosDesdeArchivo("entrada/usuarios.csv");
		tm.agregarAtraccionesDesdeArchivo("entrada/atracciones.csv");
		tm.agregarPromocionesDesdeArchivo("entrada/PromoAbsoluta.in");
		tm.agregarPromosaListaProductos();
		tm.agregarAtraccionesaListaProductos();
		List<Producto> productos = tm.obtenerListaProductos();
		List<Usuario> usuarios = tm.obtenerListaUsuarios();
		// Ordena las promos y atracciones según tipo de atracción
		// favorita de usuario y otros dif. criterios
		tm.OrdenarProductosDeListaSegunUsuario(usuarios.get(2));

		// Usuarios - Usuario Arwen y su lista de productos a mostrar .
		System.out.println(usuarios);
		System.out.println(productos);

	}

}
