package model;

public enum ClaseDePromo {

	PROMOAXB(1), PROMO_ABSOLUTA(2), PROMO_PORCENTUAL(3);

	private final int id;

	ClaseDePromo(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
