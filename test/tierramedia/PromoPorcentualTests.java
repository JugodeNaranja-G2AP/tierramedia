package tierramedia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromoPorcentualTests {

	Promocion promo;

	Atraccion atraccion1 = new Atraccion("Bosque Negro", 25, 3, 4, Tipo.AVENTURA);
	Atraccion atraccion2 = new Atraccion("Mordor", 3, 4, 12, Tipo.AVENTURA);

	@Before
	public void setUp() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(atraccion1);
		atracciones.add(atraccion2);

		promo = new PromoPorcentual("La Gran Aventura", ClaseDePromo.PROMO_PORCENTUAL, Tipo.AVENTURA, atracciones, 20);
	}

	@Test
	public void obtenerCostoTest() {
		int costoDePromoEsperado = 23;
		assertEquals(costoDePromoEsperado, promo.getCosto());
	}

	@Test
	public void obtenerTiempoTotalTest() {
		double tiempoTotalEsperado = 7.00;
		assertEquals(tiempoTotalEsperado, promo.getTiempo(), 0);
	}
	
	@Test
	public void ahorroTest() {
		int ahorroEsperado = 5;
		assertEquals(ahorroEsperado, promo.ahorro());
	}

}
