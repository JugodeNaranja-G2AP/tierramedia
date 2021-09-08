package tierramedia;

import java.util.List;

public class PromoAbsoluta extends Promocion {

	private int costoDePromo;

	public PromoAbsoluta(String nombre, Tipo tipo, List<Atraccion> atracciones, int costoAbsolutoDePromo) {
		super(nombre, tipo, atracciones);
		this.costoDePromo = costoAbsolutoDePromo;
	}

	@Override
	public int getCosto() {
		return this.costoDePromo;
	}

}