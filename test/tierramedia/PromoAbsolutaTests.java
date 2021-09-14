package tierramedia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromoAbsolutaTests {

	Promocion promo;
	Atraccion atraccion1 = new Atraccion("Lothlörien", 35, 1, 30, Tipo.DEGUSTACION);
	Atraccion atraccion2 = new Atraccion("La Comarca", 3, 6.5, 150, Tipo.DEGUSTACION);

	@Before
	public void setUp() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(atraccion1);
		atracciones.add(atraccion2);
		promo = new PromoAbsoluta("Bon Apetit", ClaseDePromo.PROMO_ABSOLUTA, Tipo.DEGUSTACION, atracciones, 36);
	}

	@Test
	public void getCostoDePacktest() {
		double costoEsperado = 36.00;
		assertEquals(costoEsperado, promo.getCosto(), 0);
	}

}
