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
		int costoEsperado = 36;
		assertEquals(costoEsperado, promo.getCosto());
	}

	@Test
	public void getTiempoDePackTest() {
		double tiempoEsperado = 7.5;
		assertEquals(tiempoEsperado, promo.getTiempo(), 0);
	}

	@Test
	public void esPromoTest() {
		assertTrue(promo.esPromocion());
		assertFalse(atraccion1.esPromocion());
	}

	@Test
	public void hayCupoEnPromoTest() {
		assertTrue(promo.hayCupo());
	}

	@Test
	public void hayCupoEnAtraccionTest() {
		assertTrue(atraccion1.hayCupo());
	}
	
	@Test
	public void ahorroTest() {
		int ahorroEsperado = 2;
		assertEquals(ahorroEsperado, promo.ahorro());
	}

}
