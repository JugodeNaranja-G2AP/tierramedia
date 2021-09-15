package tierramedia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTests {

	Usuario usuarioPrueba = new Usuario("UsuarioPrueba", 12, 7, Tipo.DEGUSTACION);
	// String nombre, double costoDeVisita, int tiempoDeVisita, int cupoDePersonas,
	// Tipo tipo
	Atraccion atraccion1 = new Atraccion("Minas Tirith", 5, 2, 25, Tipo.PAISAJE);
	Atraccion atraccion2 = new Atraccion("Abismo de Helm", 5, 2, 15, Tipo.DEGUSTACION);
	Atraccion atraccion3 = new Atraccion("Erebor", 12, 3, 32, Tipo.AVENTURA);

	@Before
	public void setUp() {

		usuarioPrueba.reservarProducto(atraccion1);
		usuarioPrueba.reservarProducto(atraccion2);
	}

	@Test
	public void obtenerTiempoTotalItinerarioTest() {

		double tiempoEsperado = 4;
		assertEquals(tiempoEsperado, usuarioPrueba.obtenerTiempoTotalItinerario(), 0);
	}

	@Test
	public void obtenerCostoTotalItinerarioTest() {
		int costoEsperado = 10;
		assertEquals(costoEsperado, usuarioPrueba.obtenerCostoTotalItinerario(), 0);
	}
	
	@Test
	public void presupuestoTest() {
		int presupuestoEsperado = 2;
		assertEquals(presupuestoEsperado, usuarioPrueba.getPresupuesto());
		
	}
	
	@Test
	public void tiempoDisponibleTest() {
		double tiempoEsperado = 3;
		assertEquals(tiempoEsperado, usuarioPrueba.getTiempoDisponible(), 0);
	}
	
	@Test
	public void comproElProductoTest() {
		assertTrue(usuarioPrueba.comproElProducto(atraccion1));
		assertTrue(usuarioPrueba.comproElProducto(atraccion2));
		assertFalse(usuarioPrueba.comproElProducto(atraccion3));
	}
	
	@Test
	public void obtenerNombresdeProductosCompradosTest() {
		String nombresEsperados = "Minas Tirith\nAbismo de Helm\n";
		assertEquals(nombresEsperados, usuarioPrueba.obtenerNombresdeProductosComprados());
	}

}
