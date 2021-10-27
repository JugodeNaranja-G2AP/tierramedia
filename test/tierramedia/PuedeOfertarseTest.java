package tierramedia;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import model.Atraccion;
import model.ClaseDePromo;
import model.PromoPorcentual;
import model.Promocion;
import model.Tipo;
import model.Usuario;

public class PuedeOfertarseTest {

	Promocion promo;

	Usuario user1 = new Usuario("Carlets", 20, 10, Tipo.AVENTURA);
	Usuario user2 = new Usuario("Martina", 55, 10, Tipo.AVENTURA);
	Usuario user3 = new Usuario("Celestial", 28, 10, Tipo.DEGUSTACION);

	Atraccion atraccion1 = new Atraccion("Bosque Negro", 25, 3, 4, Tipo.AVENTURA);
	Atraccion atraccion2 = new Atraccion("Mordor", 3, 4, 12, Tipo.AVENTURA);
	Atraccion atraccion3 = new Atraccion("Quesería Thurdon", 3, 4, 0, Tipo.DEGUSTACION);

	@Before
	public void setUp() {
		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(atraccion1);
		atracciones.add(atraccion2);

		promo = new PromoPorcentual("La Gran Aventura", ClaseDePromo.PROMO_PORCENTUAL, Tipo.AVENTURA, atracciones, 20);
	}

	@Test
	public void atraccionSiPuedeOfrecerseTest() {

		// el usuario cuenta con dinero y tiempo para hacer la atracción
		assertTrue(atraccion2.puedeSerOfertadoA(user1));
	}

	@Test
	public void atraccionNoPuedeOfrecerseTest() {

		// el usuario no puede costear la atracción
		assertFalse(atraccion1.puedeSerOfertadoA(user1));

		// no hay cupo para atracción
		assertFalse(atraccion3.puedeSerOfertadoA(user2));
	}

	@Test
	public void promoSiPuedeOfrecerseTest() {

		// el usuario dispone de dinero y tiempo, pero la atracción no es de su preferencia
		assertTrue(promo.puedeSerOfertadoA(user3));
	}

	@Test
    public void atraccionReservadaEnPromoEsGuardadaTest() {
        List<Producto> productosNoOfertables = new ArrayList<Producto>();
        assertTrue(promo.puedeSerOfertadoA(user3));
        user3.reservarProducto(promo);
        productosNoOfertables = user3.obtenerListaNoOfertable();
        assertTrue(productosNoOfertables.contains(atraccion1));
        assertTrue(productosNoOfertables.contains(atraccion2));
    } 

}
