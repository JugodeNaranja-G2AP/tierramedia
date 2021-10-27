package model;

import java.util.List;

import tierramedia.Reloj;

public class PromoAbsoluta extends Promocion {

	private int costoDePromo;

	public PromoAbsoluta(int id, String nombre, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones,
			int costoAbsolutoDePromo) {
		super(id, nombre, clase, tipo, atracciones);
		this.costoDePromo = costoAbsolutoDePromo;
	}

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
		String perfil = "\t|\t\t\t\t\t      |  Descuento:\t "  + ahorro() + " monedas de oro" + "\n";
		perfil += "\t×¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
		return perfil;

	}

}