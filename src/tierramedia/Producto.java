package tierramedia;

import java.util.List;

public interface Producto {
	Tipo getTipo();
	int getCosto();
	double getTiempo();
	boolean puedeSerOfertadoA(Usuario u);
	boolean esPromocion();
	void descontarCupo();
	String getNombre();
	List<Atraccion> obtenerAtracciones();
}
