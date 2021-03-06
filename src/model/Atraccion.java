package model;

import java.util.List;
import java.util.Objects;

import tierramedia.Producto;
import tierramedia.Reloj;

public class Atraccion implements Producto {

	private int id;
	private String nombre;
	private int costoDeVisita;
	private double tiempoDeVisita;
	private int cupoDePersonas;
	private Tipo tipo;

	public Atraccion(int id, String nombre, int costoDeVisita, double tiempoDeVisita, int cupoDePersonas, Tipo tipo) {
		this.id = id;
		this.nombre = nombre;
		this.costoDeVisita = costoDeVisita;
		this.tiempoDeVisita = tiempoDeVisita;
		this.cupoDePersonas = cupoDePersonas;
		this.tipo = tipo;
	}

	public Atraccion(String nombre, int costoDeVisita, double tiempoDeVisita, int cupoDePersonas, Tipo tipo) {
		this.nombre = nombre;
		this.costoDeVisita = costoDeVisita;
		this.tiempoDeVisita = tiempoDeVisita;
		this.cupoDePersonas = cupoDePersonas;
		this.tipo = tipo;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public int getCosto() {
		return costoDeVisita;
	}

	@Override
	public double getTiempo() {
		return tiempoDeVisita;
	}

	public int getCupoDePersonas() {
		return cupoDePersonas;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	public boolean hayCupo() {
		return this.cupoDePersonas > 0;
	}

	@Override
	public void descontarCupo() {
		this.cupoDePersonas--;
	}

	@Override
	public boolean esPromocion() {
		return false;
	}

	@Override
	public List<Atraccion> obtenerAtracciones() {
		return List.of(this);
	}

	@Override
	public boolean puedeSerOfertadoA(Usuario u) {
		return this.hayCupo() && u.getPresupuesto() >= costoDeVisita && u.getTiempoDisponible() >= tiempoDeVisita;
	}

	@Override
	public int hashCode() {
		return Objects.hash(costoDeVisita, nombre, tiempoDeVisita, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		return costoDeVisita == other.costoDeVisita && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempoDeVisita) == Double.doubleToLongBits(other.tiempoDeVisita)
				&& tipo == other.tipo;
	}

	@Override
	public String toString() {
		String perfil  = "\n";
		   perfil += "\t\t\t|               " + nombre + "\n";
		   perfil += "\t\t\t|Tiempo de visita:\t" + Reloj.conversor(tiempoDeVisita) + "\n";
		   perfil += "\t\t\t|Precio de visita:\t" + costoDeVisita+ " monedas de oro \n";
		   perfil += "\t\t\t??????????????????????????????????????????????????????????????????????????????????????????\n";
	return perfil;
	}
}
