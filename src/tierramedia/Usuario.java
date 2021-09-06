package tierramedia;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String nombre;
	private int presupuesto;
	private double tiempoDisponible;
	private Tipo tipoAtraccionPreferida;
	private List<Producto> sugerenciasDiarias;

	public Usuario(String nombre, int presupuesto, double tiempoDisponible, Tipo tipoDeAtraccionPreferida) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoAtraccionPreferida = tipoDeAtraccionPreferida;
		this.sugerenciasDiarias = new ArrayList<Producto>();
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
	
	public List<Producto> obtenerProductosComprados() {
		return this.sugerenciasDiarias;
	}
	
	public boolean comproElProducto(Producto producto) {
		List<Producto> productosComprados = obtenerProductosComprados();
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
		double tiempo = producto.getTiempo();
		int costo = producto.getCosto();
		sugerenciasDiarias.add(producto);
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
