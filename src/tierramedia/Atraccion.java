package tierramedia;

import java.util.List;
import java.util.Objects;

public class Atraccion implements Producto {

	private String nombre;
	private int costoDeVisita;
	private double tiempoDeVisita;
	private int cupoDePersonas;
	private Tipo tipo;

	public Atraccion(String nombre, int costoDeVisita, double tiempoDeVisita, int cupoDePersonas, Tipo tipo) {
		this.nombre = nombre;
		this.costoDeVisita = costoDeVisita;
		this.tiempoDeVisita = tiempoDeVisita;
		this.cupoDePersonas = cupoDePersonas;
		this.tipo = tipo;
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
		return " La atracción " + nombre + " tiene un costo de  " + costoDeVisita
				+ " monedas de oro, y un tiempo de visita de " + Reloj.conversor(tiempoDeVisita) + "." + "\n";
	}
}
