package tierramedia;

import java.util.ArrayList;
import java.util.List;

public abstract class Promocion implements Producto {

	private String nombre;
	private List<Atraccion> atracciones = new ArrayList<Atraccion>();
	private double tiempoTotal;
	private int costoDePack;
	private Tipo tipo;

	public Promocion(String nombre, Tipo tipo, List<Atraccion> atracciones) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.atracciones = atracciones;
		this.costoDePack = getCosto();
		this.tiempoTotal = getTiempo();
	}

	public String getNombre() {
		return this.nombre;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
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
	public String toString() {
		return "PROMOCION \n Nombre de la Promo: " + nombre + "\n Atracciones que incluye:\n " + atracciones
				+ "\n Tiempo Total = " + getTiempo() + " horas \n Costo del pack = " + getCosto()
				+ " monedas de oro\n Tipo = " + tipo + "\n\n";
	}

}
