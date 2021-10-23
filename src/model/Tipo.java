package model;

public enum Tipo {

	DEGUSTACION(1), AVENTURA(2), PAISAJE(3);

	private final int id;

	Tipo(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
