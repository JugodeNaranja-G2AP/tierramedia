package tierramedia;

public interface Producto {
	Tipo getTipo();
	int getCosto();
	double getTiempo();
	boolean puedeSerOfertadoA(Usuario u);
	boolean esPromocion();
	void descontarCupo();
}
