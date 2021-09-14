package tierramedia;

import java.util.List;

public class PromoPorcentual extends Promocion {

	private int porcentajeDescuento;

	public PromoPorcentual(String nombre, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones,
			int porcentajeDescuento) {
		super(nombre, clase, tipo, atracciones);
		this.porcentajeDescuento = porcentajeDescuento;
	}

	private int realizarDescuento(int costo) {
		int descuento = (int) Math.floor((costo * this.porcentajeDescuento) / 100);
		return descuento;
	}

	@Override
	public int getCosto() {
		int costoDePack = super.getCosto();
		return costoDePack - realizarDescuento(costoDePack);
	}

	public int ahorro() {
		return realizarDescuento(super.getCosto());
	}

	public String descripcion() {
		return " Esta promo tiene un descuento del " + porcentajeDescuento + "% sobre el precio total.\n"
				+ " Te ahorrás " + ahorro() + " monedas de oro.\n";
	}

}