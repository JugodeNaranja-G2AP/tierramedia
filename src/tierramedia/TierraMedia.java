package tierramedia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import dao.AtraccionDAO;
import dao.DAOFactory;
import dao.PromocionDAO;
import dao.UserDAO;
import model.Atraccion;
import model.Promocion;
import model.Usuario;

public class TierraMedia {

	protected List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	private List<Promocion> promos;
	private List<Producto> productos;
	private static AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
	private static PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
	private static UserDAO userDAO = DAOFactory.getUserDAO();

	public TierraMedia() {
		this.productos = new ArrayList<Producto>();
		this.usuarios = new ArrayList<Usuario>();
		this.atracciones = new ArrayList<Atraccion>();
		this.promos = new ArrayList<Promocion>();
	}

	public void agregarTodasAtracciones() {
		this.atracciones = atraccionDAO.findAll();
	}

	public void agregarTodasPromociones() {
		this.promos = promocionDAO.findAll();
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

	public void agregarUsuarios() {
		this.usuarios = userDAO.findAll();
	}

	public void obtenerItinerarios() {
		List<Producto> itinerario = new ArrayList<Producto>();
		for (Usuario usuario : this.usuarios) {
			itinerario = userDAO.obtenerItinerario(usuario);
			usuario.setProductosComprados(itinerario);
			cargarListaNoOfertable(usuario, itinerario);
		}
	}

	public void cargarListaNoOfertable(Usuario usuario, List<Producto> listaProductos) {
		List<Atraccion> atraccionesDePromo = new ArrayList<Atraccion>();
		List<Producto> productosNoOfertables = new ArrayList<Producto>();
		if (listaProductos.size() != 0) {
			for (Producto producto : listaProductos) {
				if (producto.esPromocion()) {
					atraccionesDePromo = producto.obtenerAtracciones();
				}
			}
			for (Atraccion atraccion : atraccionesDePromo) {
				productosNoOfertables.add(atraccion);
			}
		}
		usuario.setProductosReservados(productosNoOfertables);
	}
	
	public List<Producto> ordenarProductosDeLista(Usuario u) {
		Collections.sort(this.productos, new OrdenadorDeProducto(u.getTipoDeAtraccionPreferida()));
		return this.productos;
	}
	
	private String displayBienvenida(Usuario u) {
		String bienvenida = " ";
		bienvenida += "                 ¡Bienvenid@ " + u.getNombre() + " a Tierra Media! \n";
		bienvenida += "\nTus datos y perfil son los siguientes: \n\n" + u + "\n";
		bienvenida += "A continuación te mostraremos atracciones y promos turísticas que ofrece Tierra Media.\n";
		bienvenida += "Algunos de estos packs ofrecen descuentos y reducciones del precio total del paquete.\n\n";
		bienvenida += "============================================================================================ \n";

		bienvenida += "\n                         Presiona enter para continuar \n";

		return bienvenida;
	}
	
	public void ofertarProductos() {
		agregarUsuarios();
		agregarTodasAtracciones();
		agregarTodasPromociones();
		agregarAtraccionesaListaProductos();
		agregarPromosaListaProductos();
		obtenerItinerarios();
		Scanner sc = new Scanner(System.in);
		for (Usuario u : usuarios) {

			List<Producto> productosOrdenados = ordenarProductosDeLista(u);
			String respuesta;

			System.out.println(displayBienvenida(u));

			if (sc.hasNextLine()) {
				sc.nextLine();
			}
			System.out.println(
					"============================================================================================\n");

			for (Producto p : productosOrdenados) {
				if (p.puedeSerOfertadoA(u) && !u.comproElProducto(p)) {
					String tipoDeProducto = p.esPromocion() ? "promo" : "atracción";
					System.out.println(p);
					System.out.println(" Tu presupuesto actual es de " + u.getPresupuesto()
							+ " monedas de oro \n y tu tiempo disponible es de "
							+ Reloj.conversor(u.getTiempoDisponible()) + ".");
					System.out.println("\n");
					do {

						System.out.println("                     ¿Querés comprar esta " + tipoDeProducto + "? (si/no)");

						respuesta = sc.nextLine();
						System.out.println("\n");

					} while (!respuesta.equalsIgnoreCase("si") && !respuesta.equalsIgnoreCase("no"));

					if (respuesta.equalsIgnoreCase("si")) {
						u.reservarProducto(p);
						userDAO.registrarCompra(u, p);
						userDAO.update(u);
						if (!p.esPromocion()) {
							atraccionDAO.update((Atraccion) p);
						}
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
				continue;
			}
			if (u.productosReservados.size() == 0) {
				System.out.println("==========================================================================");
				System.out.println("\n                 No compraste ninguna atracción. \n");
				System.out.println("                 ¡Podes volver cuando quieras! \n");
				System.out.println("\n");
				System.out.println("==========================================================================");

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

			System.out.println("\n                         Presiona enter para continuar");

			if (sc.hasNextLine()) {
				sc.nextLine();
			}
			System.out.println(
					"============================================================================================\n");

		}

		sc.close();
	}

}