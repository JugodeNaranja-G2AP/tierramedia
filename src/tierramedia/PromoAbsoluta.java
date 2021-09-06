package tierramedia;

public class PromoAbsoluta extends Promocion {

	private int costoDePromo;

	public PromoAbsoluta(String nombre, Tipo tipo,int costoAbsolutoDePromo, Atraccion[] atracciones) {
		super(nombre, tipo, atracciones);
		this.costoDePromo = costoAbsolutoDePromo;
	}

	@Override
	public int getCosto() {
		return this.costoDePromo;
	}

	@Override
	public Double getTiempo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean puedeSerOfertadoA(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}
}