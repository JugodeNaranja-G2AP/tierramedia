package tierramedia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CargaDeArchivosTest {

	Usuario user1 = new Usuario("Legolas", 100, 10, Tipo.AVENTURA);
	Usuario user2 = new Usuario("Gimli", 95, 8, Tipo.DEGUSTACION);
	Usuario user3 = new Usuario("Arwen", 70, 6, Tipo.PAISAJE);
	Usuario user4 = new Usuario("Bilbo", 120, 9, Tipo.DEGUSTACION);
	Usuario user5 = new Usuario("Elrond", 73, 7, Tipo.PAISAJE);
	Usuario user6 = new Usuario("Aragorn", 112, 11, Tipo.AVENTURA);
	List<Usuario> usuariosTest = new ArrayList<Usuario>();

	Atraccion atraccion1 = new Atraccion("La Comarca", 5, 1, 10, Tipo.DEGUSTACION);
	Atraccion atraccion2 = new Atraccion("Lothlorien", 20, 1.3, 5, Tipo.DEGUSTACION);
	Atraccion atraccion3 = new Atraccion("Lindon", 9, 3, 10, Tipo.PAISAJE);
	Atraccion atraccion4 = new Atraccion("Rohan", 25, 0.5, 16, Tipo.DEGUSTACION);
	Atraccion atraccion5 = new Atraccion("Belerian", 10, 2.4, 6, Tipo.PAISAJE);
	Atraccion atraccionGratis = new Atraccion("Rivendell", 3, 2.2, 6, Tipo.DEGUSTACION);
	List<Atraccion> atraccionesAxBTest = new ArrayList<Atraccion>();
	List<Atraccion> atraccionesAbsTest = new ArrayList<Atraccion>();
	List<Atraccion> atraccionesPorTest = new ArrayList<Atraccion>();

	Promocion promocionAxB = new PromoAxB("Delicias", ClaseDePromo.PROMOAXB, Tipo.DEGUSTACION, atraccionesAxBTest,
			atraccionGratis);
	Promocion promocionAbs = new PromoAbsoluta("Naturaleza", ClaseDePromo.PROMO_ABSOLUTA, Tipo.PAISAJE,
			atraccionesAbsTest, 15);
	Promocion promocionPor = new PromoPorcentual("Elixir", ClaseDePromo.PROMO_PORCENTUAL, Tipo.DEGUSTACION,
			atraccionesPorTest, 30);
	List<Promocion> promosTest = new ArrayList<Promocion>();

	@Before
	public void setUp() {
		usuariosTest.add(user1);
		usuariosTest.add(user2);
		usuariosTest.add(user3);
		usuariosTest.add(user4);
		usuariosTest.add(user5);
		usuariosTest.add(user6);

		atraccionesAxBTest.add(atraccion4);
		atraccionesAxBTest.add(atraccion1);
		atraccionesAxBTest.add(atraccionGratis);

		atraccionesAbsTest.add(atraccion5);
		atraccionesAbsTest.add(atraccion3);

		atraccionesPorTest.add(atraccion4);
		atraccionesPorTest.add(atraccion2);

		promosTest.add(promocionAxB);
		promosTest.add(promocionAbs);
		promosTest.add(promocionPor);

	}

	@Test
	public void cargaDeArchvoDeUsuarioTest() {

		String archivo = "entrada/usuarios.csv";
		assertEquals(CargarUsuarios.obtener(archivo), usuariosTest);
	}

	@Test
	public void cargaDePromosYAtraccionesTest() {

		String archivoPromos = "entrada/promosTest.txt";
		String archivoAtracciones = "entrada/atraccionesTest.csv";
		assertEquals(CargarPromociones.obtener(archivoPromos, archivoAtracciones), promosTest);
	}

}