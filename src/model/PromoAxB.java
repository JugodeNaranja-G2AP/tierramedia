package model;

import java.util.List;

public class PromoAxB extends Promocion {

	private Atraccion atraccionGratis;

	public PromoAxB(int id, String nombre, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones,
			Atraccion atraccionGratis) {
		super(id, nombre, clase, tipo, atracciones);
		this.atraccionGratis = atraccionGratis;
	}

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
		String perfil = "\t|\t\t\t\t\t      |  Descuento:\t " + ahorro() + " monedas de oro" + "\n";
		perfil += "\t×¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
		return perfil;
	}
}
