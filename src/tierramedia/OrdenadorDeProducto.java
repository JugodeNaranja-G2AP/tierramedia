package tierramedia;

import java.util.Comparator;

import model.Tipo;

public class OrdenadorDeProducto implements Comparator<Producto> {
	Tipo tipoAtraccionPreferida;

	public OrdenadorDeProducto(Tipo tipoAtraccionPreferida) {
		this.tipoAtraccionPreferida = tipoAtraccionPreferida;
	}

	@Override
	public int compare(Producto o1, Producto o2) {
		// Según tipo preferido de atracción
		if (o1.getTipo() == tipoAtraccionPreferida && o2.getTipo() != tipoAtraccionPreferida) {
			return -1;
		} else if (o1.getTipo() != tipoAtraccionPreferida && o2.getTipo() == tipoAtraccionPreferida) {
			return 1;
		}

		// Según sea o no promoción
		if (o1.esPromocion() && !o2.esPromocion()) {
			return -1;
		} else if (!o1.esPromocion() && o2.esPromocion()) {
			return 1;
		}

		// Según presupuesto disponible
		if (o1.getCosto() > o2.getCosto()) {
			return -1;
		} else if (o1.getCosto() < o2.getCosto()) {
			return 1;
		}

		// Según tiempo
		return (int) (o1.getTiempo() - o2.getTiempo());

	}

}
