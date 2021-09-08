package tierramedia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromoAxBTests {

	Promocion promo;

	Atraccion atraccion1 = new Atraccion("Minas Tirith", 5, 2, 25, Tipo.PAISAJE);
	Atraccion atraccion2 = new Atraccion("Abismo de Helm", 5, 2, 15, Tipo.PAISAJE);
	Atraccion atraccionGratis = new Atraccion("Erebor", 12, 3, 32, Tipo.PAISAJE);

	@Before
	public void setUp() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(atraccion1);
		atracciones.add(atraccion2);
		atracciones.add(atraccionGratis);
		promo = new PromoAxB("Nuevas Maravillas", Tipo.PAISAJE, atracciones, atraccionGratis);

	}

	@Test
	public void getCostoDePackTest() {
		double costoDePromoEsperado = 10;
		assertEquals(costoDePromoEsperado, promo.getCosto(), 0);
	}

}
