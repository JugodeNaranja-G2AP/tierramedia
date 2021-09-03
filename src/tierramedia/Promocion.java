package tierramedia;

import java.util.Arrays;

public abstract class Promocion implements Producto {

	private String nombre;
	private Atraccion[] atracciones;  
	private double tiempoTotal;
	private int costoDePack;
	private Tipo tipo;

	public Promocion(String nombre, Tipo tipo, Atraccion[] atracciones) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.atracciones = atracciones;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public Double getTiempo() {
		tiempoTotal = 0;
		for (Atraccion cadaAtraccion : atracciones) {
			if (cadaAtraccion == null)
				break;
			tiempoTotal += cadaAtraccion.getTiempo();
		}
		return tiempoTotal;
	}

	@Override
	public int getCosto() { 
		costoDePack = 0;
		for (Atraccion cadaAtraccion : atracciones) {
			if (cadaAtraccion == null)
				break;
			costoDePack += cadaAtraccion.getCosto();
		}
		return costoDePack;
	}
	
	public boolean hayCupo() {
		boolean hayCupo = true;
		for (Atraccion cadaAtraccion : atracciones) {
			if (!(cadaAtraccion.getCupoDePersonas() > 0))  {
				hayCupo = false;
				break;
			}
		}
		return hayCupo;
	}
	
	public void descontarCupo() {
		for (Atraccion cadaAtraccion : atracciones) {
			cadaAtraccion.descontarCupo();
		}
	}

	@Override
	public Boolean puedeSerOfertadoA(Usuario u) {
		return this.tipo.equals(u.getTipoDeAtraccionPreferida()) && 
				hayCupo() && u.getPresupuesto() >= this.getCosto() && u.getTiempoDisponible() >= this.getTiempo();
	}

	@Override
	public String toString() {
		return "Promocion: \n\n Nombre de la Promo: " + nombre + ".\n\n Atracciones que incluye: \n\n " + Arrays.toString(atracciones) + ", Tiempo Total="
				+ tiempoTotal + "horas , costo del pack=" + costoDePack + " Monedas de oro, Tipo = " + tipo + " ] \n\n";
	}
	
}
