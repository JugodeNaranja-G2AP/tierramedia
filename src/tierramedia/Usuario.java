package tierramedia;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private Tipo tipoAtraccionPreferida;
	public List<Producto> productosReservados;
	private List<Producto> productosComprados;

	public Usuario(String nombre, int presupuesto, double tiempoDisponible, Tipo tipoDeAtraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoAtraccionPreferida = tipoDeAtraccionPreferida;
		this.productosReservados = new ArrayList<Producto>();
		this.productosComprados = new ArrayList<Producto>();
	}

	public String getNombre() {
		return nombre;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public Tipo getTipoDeAtraccionPreferida() {
		return tipoAtraccionPreferida;
	}

	public List<Producto> obtenerProductosReservados() {
		return this.productosReservados;
	}

	public List<Producto> obtenerProductosComprados() {
		return this.productosComprados;
	}

	public int obtenerCostoTotalItinerario() {
		int costoTotal = 0;
		for (Producto p : productosComprados) {
			costoTotal += p.getCosto();
		}
		return costoTotal;
	}

	public double obtenerTiempoTotalItinerario() {
		double tiempoTotal = 0;
		for (Producto p : productosComprados) {
			tiempoTotal += p.getTiempo();
		}
		return tiempoTotal;
	}

	public boolean comproElProducto(Producto producto) {
		List<Producto> productosComprados = obtenerProductosReservados();
		return productosComprados.contains(producto);
	}

	private void setPresupuesto(int monto) {
		if (monto > 0) {
			this.presupuesto -= monto;
		}
	}

	public void restarPresupuesto(int monto) {
		setPresupuesto(monto);
	}

	private void setTiempoDisponible(double tiempo) {
		if (tiempo > 0) {
			this.tiempoDisponible -= tiempo;
		}
	}

	public void restarTiempoDisponible(double tiempo) {
		setTiempoDisponible(tiempo);
	}

	public void reservarProducto(Producto producto) {
		List<Atraccion> atracciones = new ArrayList<>();
		double tiempo = producto.getTiempo();
		int costo = producto.getCosto();
		productosReservados.add(producto);
		productosComprados.add(producto);
		producto.descontarCupo();
		if (producto.esPromocion()) {
			atracciones = producto.obtenerAtracciones();
			for (Atraccion a : atracciones) {
				productosReservados.add(a);
			}
		}
		restarTiempoDisponible(tiempo);
		restarPresupuesto(costo);

	}

	@Override
	public String toString() {
		return "Usuario \n Nombre: " + nombre + "\n Presupuesto: " + presupuesto
				+ " monedas de oro\n Tiempo disponible: " + tiempoDisponible + " horas\n Tipo atraccion preferida: "
				+ tipoAtraccionPreferida + "\n\n";
	}
}
