package tierramedia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PromoPorcentualTests {

	Promocion promo;
	//String nombre, double costoDeVisita, int tiempoDeVisita, int cupoDePersonas, Tipo tipo
	Atraccion atraccion1 = new Atraccion("Bosque Negro", 25, 3, 4, Tipo.AVENTURA);
	Atraccion atraccion2 = new Atraccion("Mordor", 3, 4, 12, Tipo.AVENTURA);
	Atraccion[] atracciones = { atraccion1, atraccion2 };
	
	@Before
	public void setUp() {
		//String nombre, int cantidadAtracciones, Tipo tipo, Producto[] atracciones, int porcentajeDescuento
		promo = new PromoPorcentual("La Gran Aventura", Tipo.AVENTURA, atracciones , 20);
	}

	@Test
	public void obtenerCostoTest() {
		double costoDePromoEsperado = 23;
		assertEquals(costoDePromoEsperado, promo.getCosto(), 0);
	}
	
	@Test
	public void obtenerTiempoTotalTest() {
		double tiempoTotalEsperado = 7.00;
		assertEquals(tiempoTotalEsperado, promo.getTiempo(), 0);
	}

}
