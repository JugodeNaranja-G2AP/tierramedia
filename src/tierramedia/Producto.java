package tierramedia;

public interface Producto {

	Tipo getTipo();
	int getCosto(); 
	Double getTiempo();
	Boolean puedeSerOfertadoA(Usuario u);
}
