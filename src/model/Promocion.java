package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import tierramedia.Producto;
import tierramedia.Reloj;

public abstract class Promocion implements Producto {

	protected int id;
	private String nombre;
	protected List<Atraccion> atracciones = new ArrayList<Atraccion>();
	private double tiempoTotal;
	private int costoDePack;
	private Tipo tipo;
	private ClaseDePromo clase;

	public Promocion(int id, String nombre, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.atracciones = atracciones;
		this.clase = clase;
	}

	public Promocion(String nombre, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.atracciones = atracciones;
		this.clase = clase;
	}

	public int getId() {
		return id;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	public ClaseDePromo getClase() {
		return clase;
	}

	@Override
	public double getTiempo() {
		tiempoTotal = 0;
		for (Atraccion cadaAtraccion : atracciones) {
			tiempoTotal += cadaAtraccion.getTiempo();
		}
		return tiempoTotal;
	}

	@Override
	public int getCosto() {
		costoDePack = 0;
		for (Atraccion cadaAtraccion : atracciones) {
			costoDePack += cadaAtraccion.getCosto();
		}
		return costoDePack;
	}

	public boolean hayCupo() {
		boolean hayCupo = true;
		for (Atraccion cadaAtraccion : atracciones) {
			if (!(cadaAtraccion.getCupoDePersonas() > 0)) {
				hayCupo = false;
				break;
			}
		}
		return hayCupo;
	}

	@Override
	public void descontarCupo() {
		for (Atraccion cadaAtraccion : atracciones) {
			if (cadaAtraccion.getCupoDePersonas() > 0) {
				cadaAtraccion.descontarCupo();
			}
		}
	}

	@Override
	public boolean esPromocion() {
		return true;
	}

	@Override
	public List<Atraccion> obtenerAtracciones() {
		return atracciones;
	}

	@Override
	public boolean puedeSerOfertadoA(Usuario u) {
		return hayCupo() && u.getPresupuesto() >= this.getCosto() && u.getTiempoDisponible() >= this.getTiempo();
	}

	@Override
	public int hashCode() {
		return Objects.hash(atracciones, costoDePack, nombre, tiempoTotal, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(atracciones, other.atracciones) && costoDePack == other.costoDePack
				&& Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(tiempoTotal) == Double.doubleToLongBits(other.tiempoTotal)
				&& tipo == other.tipo;
	}
	
	private String formatearAtracciones(List<Atraccion> atracciones) {
        String atraccionesFormateadas = "";
        for(Atraccion a: atracciones){
          atraccionesFormateadas +=  "- " + a + "\r\n ";
        }
        return atraccionesFormateadas;
    }

	@Override
	public String toString() {
		return "PROMOCION \n Nombre de la Promo: " + nombre + "\n Atracciones que incluye:\n\n " + formatearAtracciones(atracciones)
				+ "\n Tiempo Total = " + Reloj.conversor(getTiempo()) + ".\n" + descripcion() + " Costo del pack = "
				+ getCosto() + " monedas de oro\n Tipo = " + tipo + "\n\n";
	}

	protected abstract String descripcion();

	public abstract int ahorro();

}
