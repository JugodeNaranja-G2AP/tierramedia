package tierramedia;

import java.util.List;

public class PromoAbsoluta extends Promocion {

	private int costoDePromo;

	public PromoAbsoluta(String nombre, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones,
			int costoAbsolutoDePromo) {
		super(nombre, clase, tipo, atracciones);
		this.costoDePromo = costoAbsolutoDePromo;
	}

	@Override
	public int getCosto() {
		return this.costoDePromo;
	}

	@Override
	public int ahorro() {
		return super.getCosto() - costoDePromo;
	}

	@Override
	public String descripcion() {
		return " Esta promo tiene un precio único de " + costoDePromo + " monedas de oro. \n" + " Te ahorrás "
				+ ahorro() + " monedas de oro.\n";
	}

}