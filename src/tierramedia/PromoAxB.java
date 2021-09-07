package tierramedia;

public class PromoAxB extends Promocion {

	private Atraccion atraccionGratis;

	public PromoAxB(String nombre, Tipo tipo, Atraccion[] atracciones, Atraccion atraccionGratis) {
		super(nombre, tipo, atracciones);
		this.atraccionGratis = atraccionGratis;
	}

	public Atraccion getAtraccionGratis() {
		return atraccionGratis;
	}

	@Override
	public int getCosto() {
		return super.getCosto() - this.atraccionGratis.getCosto();
	}
}
