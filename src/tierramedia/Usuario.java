package tierramedia;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private Tipo tipoAtraccionPreferida;
	protected List<Producto> productosReservados; //cambi� el nombre de la variable, se llamaba "sugerenciasDiarias"

	public Usuario(String nombre, int presupuesto, double tiempoDisponible, Tipo tipoDeAtraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoAtraccionPreferida = tipoDeAtraccionPreferida;
		this.productosReservados = new ArrayList<Producto>();
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
		Double tiempo = producto.getTiempo();
		int costo = producto.getCosto();

		productosReservados.add(producto);
		restarTiempoDisponible(tiempo);
		restarPresupuesto(costo);

	}

	@Override
	public String toString() {
		return "Usuario \n nombre: " + nombre + "\n Presupuesto: " + presupuesto
				+ " monedas de oro\n Tiempo disponible: " + tiempoDisponible + " horas\n Tipo atraccion preferida: "
				+ tipoAtraccionPreferida + "\n\n";
	}
}
