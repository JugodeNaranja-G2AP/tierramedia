package model;

import java.util.List;

public class PromoPorcentual extends Promocion {

	private int porcentajeDescuento;

	public PromoPorcentual(int id, String nombre, ClaseDePromo clase, Tipo tipo, List<Atraccion> atracciones,
			int porcentajeDescuento) {
		super(id, nombre, clase, tipo, atracciones);
		this.porcentajeDescuento = porcentajeDescuento;
	}

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

	public int getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	@Override
	public int ahorro() {
		return realizarDescuento(super.getCosto());
	}

	@Override
	public String descripcion() {
		String perfil = "\t|\t\t\t\t\t     |  Descuento:\t " + ahorro() + " monedas de oro" + "\n";
		perfil += "\t×¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
		return perfil;
	}

}