package tierramedia;

public class PromoAxB extends Promocion {

	private String atraccionGratis;

	public PromoAxB(String nombre, Tipo tipo, String[] atracciones, String atraccionGratis) {
		super(nombre, tipo, atracciones);
		this.atraccionGratis = atraccionGratis;
	}

	public String getAtraccionGratis() {
		return atraccionGratis;
	}

/*	@Override
	public int getCosto() {
		return super.getCosto() - this.atraccionGratis.getCosto();
	}*/
}
