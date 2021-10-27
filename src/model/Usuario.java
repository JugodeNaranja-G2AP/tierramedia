package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import tierramedia.Producto;
import tierramedia.Reloj;

public class Usuario {

	private int id;
	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private Tipo tipoAtraccionPreferida;
	public List<Producto> productosReservados;
	private List<Producto> productosComprados;

	public Usuario(int id, String nombre, int presupuesto, double tiempoDisponible, Tipo tipoDeAtraccionPreferida) {
		this.id = id;
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoAtraccionPreferida = tipoDeAtraccionPreferida;
		this.productosReservados = new ArrayList<Producto>();
		this.productosComprados = new ArrayList<Producto>();
	}

	public Usuario(String nombre, int presupuesto, double tiempoDisponible, Tipo tipoDeAtraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoAtraccionPreferida = tipoDeAtraccionPreferida;
		this.productosReservados = new ArrayList<Producto>();
		this.productosComprados = new ArrayList<Producto>();
	}

	public void setProductosReservados(List<Producto> productosNoOfertables) {
		this.productosReservados = productosNoOfertables;
	}

	public void setProductosComprados(List<Producto> productosComprados) {
		this.productosComprados = productosComprados;
	}

	public int getId() {
		return id;
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

	public String obtenerNombresdeProductosComprados() {
		String nombreProductosComprados = "";
		for (Producto p : productosComprados) {
			nombreProductosComprados += "\t\t\t\t\t" + p.getNombre() + "\r\n";
		}
		return nombreProductosComprados;
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

	private void restarPresupuesto(int monto) {
		if (monto > 0) {
			this.presupuesto -= monto;
		}
	}

	private void restarTiempoDisponible(double tiempo) {
		if (tiempo > 0) {
			this.tiempoDisponible -= tiempo;
		}
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
		String perfil  = " 			.\n";
			   perfil += "			|    Presupuesto de " + presupuesto + " monedas de oro \n";
			   perfil += "			|  Tiempo de " + Reloj.conversor(tiempoDisponible)+ " horas disponibles\n";
			   perfil += "			| Preferencia por atracciones de " + tipoAtraccionPreferida + "\n";
			   perfil += " 			×¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
		return perfil;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, presupuesto, tiempoDisponible, tipoAtraccionPreferida);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre) && presupuesto == other.presupuesto
				&& Double.doubleToLongBits(tiempoDisponible) == Double.doubleToLongBits(other.tiempoDisponible)
				&& tipoAtraccionPreferida == other.tipoAtraccionPreferida;
	}
}