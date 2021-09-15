package tierramedia;

import java.util.List;

public class PromoAxB extends Promocion {

	private Atraccion atraccionGratis;

	public PromoAxB(String nombre, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones,
			Atraccion atraccionGratis) {
		super(nombre, clase, tipo, atracciones);
		this.atraccionGratis = atraccionGratis;
	}

	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}

	@Override
	public int getCosto() {
		return super.getCosto() - this.atraccionGratis.getCosto();
	}

	@Override
	public int ahorro() {
		return atraccionGratis.getCosto();
	}

	@Override
	public String descripcion() {
		return " ¡La atraccion " + atraccionGratis.getNombre() + " es completamente gratis!\n" + " Te ahorrás "
				+ ahorro() + " monedas de oro.\n";
	}
}
