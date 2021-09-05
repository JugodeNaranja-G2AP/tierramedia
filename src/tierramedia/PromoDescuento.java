package tierramedia;

public class PromoDescuento extends Promocion {
	
	private int porcentajeDescuento;

	public PromoDescuento(String nombre, Tipo tipo, int porcentajeDescuento, Atraccion[] atracciones) {
		super(nombre, tipo, atracciones);
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
	
	
}
