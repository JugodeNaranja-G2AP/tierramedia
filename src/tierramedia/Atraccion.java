package tierramedia;

public class Atraccion  implements Producto {
	
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

	public String getNombre() {
		return nombre;
	}

	@Override
	public int getCosto() {
		return costoDeVisita;
	}

	@Override
	public Double getTiempo() {
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

	public void descontarCupo() {
		this.cupoDePersonas--;
	}

	@Override
	public Boolean puedeSerOfertadoA(Usuario u) {
		return this.tipo.equals(u.getTipoDeAtraccionPreferida()) && this.hayCupo() &&
				 u.getPresupuesto() >= costoDeVisita && u.getTiempoDisponible() >= tiempoDeVisita;
	}

	@Override
	public String toString() {
		return "Atraccion:\n nombre = " + nombre + ",\n Costo de visita = " + costoDeVisita
				+ " Monedas de oro,\n Tiempo de visita = " + tiempoDeVisita + " horas,\n Cupo de personas = "
				+ cupoDePersonas + ",\n Tipo = " + tipo + "] \n\n";
	}


}
