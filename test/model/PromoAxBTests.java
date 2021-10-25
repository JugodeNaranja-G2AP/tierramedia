package model;

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
		promo = new PromoAxB("Nuevas Maravillas", ClaseDePromo.PROMOAXB, Tipo.PAISAJE, atracciones, atraccionGratis);

	}

	@Test
	public void getCostoDePackTest() {
		int costoDePromoEsperado = 10;
		assertEquals(costoDePromoEsperado, promo.getCosto());
	}

	@Test
	public void getTiempoDePackTest() {
		double tiempoEsperado = 7.00;
		assertEquals(tiempoEsperado, promo.getTiempo(), 0);
	}

	@Test
	public void ahorroTest() {
		int ahorroEsperado = 12;
		assertEquals(ahorroEsperado, promo.ahorro());
	}
}
