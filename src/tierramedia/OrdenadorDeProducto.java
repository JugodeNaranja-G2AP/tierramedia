package tierramedia;

import java.util.Comparator;

public class OrdenadorDeProducto implements Comparator<Producto> {
	Tipo tipoAtraccionPreferida;

	public OrdenadorDeProducto(Tipo tipoAtraccionPreferida) {
		this.tipoAtraccionPreferida = tipoAtraccionPreferida;
	}

	@Override
	public int compare(Producto o1, Producto o2) {
		// Seg�n tipo preferido de atracci�n
		if (o1.getTipo() == tipoAtraccionPreferida && o2.getTipo() != tipoAtraccionPreferida) {
			return -1;
		} else if (o1.getTipo() != tipoAtraccionPreferida && o2.getTipo() == tipoAtraccionPreferida) {
			return 1;
		}

		// Seg�n sea o no promocion
		if (o1.esPromocion() && !o2.esPromocion()) {
			return -1;
		} else if (!o1.esPromocion() && o2.esPromocion()) {
			return 1;
		}

		// Seg�n presupuesto disponible
		if (o1.getCosto() > o2.getCosto()) {
			return -1;
		} else if (o1.getCosto() < o2.getCosto()) {
			return 1;
		}

		// Seg�n tiempo
		return (int) (o1.getTiempo() - o2.getTiempo());

	}

}
