package tierramedia;

import java.util.List;

import model.Atraccion;
import model.Tipo;
import model.Usuario;

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
