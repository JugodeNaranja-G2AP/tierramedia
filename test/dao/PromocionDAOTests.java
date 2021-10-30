package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.ClaseDePromo;
import model.PromoAbsoluta;
import model.PromoAxB;
import model.PromoPorcentual;
import model.Promocion;
import model.Tipo;

public class PromocionDAOTests {

	private static String direccion = "jdbc:sqlite:database/tierramediaTest.db";

	Atraccion moria = new Atraccion("Moria", 6, 2.8, 5, Tipo.AVENTURA);
	Atraccion minasTirith = new Atraccion("Minas Tirith", 4, 3.4, 8, Tipo.PAISAJE);
	Atraccion laComarca = new Atraccion("La Comarca", 5, 1, 10, Tipo.DEGUSTACION);
	Atraccion mordor = new Atraccion("Mordor", 10, 3, 4, Tipo.AVENTURA);
	Atraccion abismoDeHelm = new Atraccion("Abismo de Helm", 3, 2, 6, Tipo.PAISAJE);
	Atraccion lothlorien = new Atraccion("Lothlorien", 20, 1.3, 5, Tipo.DEGUSTACION);
	Atraccion erebor = new Atraccion("Erebor", 14, 3.5, 12, Tipo.PAISAJE);
	Atraccion bosqueNegro = new Atraccion("Bosque Negro", 4, 2, 9, Tipo.AVENTURA);
	Atraccion doriath = new Atraccion("Doriath", 5, 2, 6, Tipo.PAISAJE);
	Atraccion valinor = new Atraccion("Valinor", 12, 2.5, 8, Tipo.AVENTURA);
	Atraccion eregion = new Atraccion("Eregion", 6, 2.4, 8, Tipo.DEGUSTACION);
	Atraccion lindon = new Atraccion("Lindon", 9, 3, 10, Tipo.PAISAJE);
	Atraccion eriador = new Atraccion("Eriador", 6, 2.8, 6, Tipo.AVENTURA);
	Atraccion rohan = new Atraccion("Rohan", 25, 0.5, 16, Tipo.DEGUSTACION);
	Atraccion belerian = new Atraccion("Belerian", 10, 2.4, 6, Tipo.PAISAJE);
	Atraccion gondor = new Atraccion("Gondor", 8, 4.1, 10, Tipo.AVENTURA);
	Atraccion rivendell = new Atraccion("Rivendell", 3, 2.2, 6, Tipo.DEGUSTACION);
	Atraccion rauros = new Atraccion("Rauros", 20, 2.6, 15, Tipo.PAISAJE);
	Atraccion bosqueFangorn = new Atraccion("Bosque Fangorn", 6, 3, 6, Tipo.AVENTURA);

	List<Atraccion> atraccionesDelicias = new ArrayList<Atraccion>();
	List<Atraccion> atraccionesElHorizonte = new ArrayList<Atraccion>();
	List<Atraccion> atraccionesElDesafio = new ArrayList<Atraccion>();
	List<Atraccion> atraccionesBonAppetit = new ArrayList<Atraccion>();
	List<Atraccion> atraccionesEspadaYEscudo = new ArrayList<Atraccion>();
	List<Atraccion> atraccionesNaturaleza = new ArrayList<Atraccion>();
	List<Atraccion> atraccionescamposFloridos = new ArrayList<Atraccion>();
	List<Atraccion> atraccionesElixir = new ArrayList<Atraccion>();
	List<Atraccion> atraccionesVelozComoElViento = new ArrayList<Atraccion>();

	List<Promocion> promos = new ArrayList<Promocion>();

	Promocion delicias = new PromoAxB("Delicias", ClaseDePromo.PROMOAXB, Tipo.DEGUSTACION, atraccionesDelicias,
			rivendell);
	Promocion elHorizonte = new PromoAxB("El Horizonte", ClaseDePromo.PROMOAXB, Tipo.PAISAJE, atraccionesElHorizonte,
			doriath);
	Promocion elDesafio = new PromoAxB("El Desafio", ClaseDePromo.PROMOAXB, Tipo.AVENTURA, atraccionesElDesafio,
			bosqueNegro);

	Promocion bonappetit = new PromoAbsoluta("Bon Appetit", ClaseDePromo.PROMO_ABSOLUTA, Tipo.DEGUSTACION,
			atraccionesBonAppetit, 23);
	Promocion espadaYEscudo = new PromoAbsoluta("Espada y Escudo", ClaseDePromo.PROMO_ABSOLUTA, Tipo.AVENTURA,
			atraccionesEspadaYEscudo, 19);
	Promocion naturaleza = new PromoAbsoluta("Naturaleza", ClaseDePromo.PROMO_ABSOLUTA, Tipo.PAISAJE,
			atraccionesNaturaleza, 15);
	Promocion camposFloridos = new PromoAbsoluta("Campos Floridos", ClaseDePromo.PROMO_ABSOLUTA, Tipo.PAISAJE,
			atraccionescamposFloridos, 25);

	Promocion elixir = new PromoPorcentual("Elixir", ClaseDePromo.PROMO_PORCENTUAL, Tipo.DEGUSTACION, atraccionesElixir,
			30);
	Promocion velozComoElViento = new PromoPorcentual("Veloz como el viento", ClaseDePromo.PROMO_PORCENTUAL,
			Tipo.AVENTURA, atraccionesVelozComoElViento, 20);

	PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();

	@Before
	public void setUp() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection(direccion);
		conexion.setAutoCommit(false);

		atraccionesDelicias.add(laComarca);
		atraccionesDelicias.add(rohan);
		atraccionesDelicias.add(rivendell);

		atraccionesElHorizonte.add(minasTirith);
		atraccionesElHorizonte.add(doriath);
		atraccionesElHorizonte.add(belerian);

		atraccionesElDesafio.add(moria);
		atraccionesElDesafio.add(bosqueNegro);
		atraccionesElDesafio.add(gondor);

		atraccionesBonAppetit.add(lothlorien);
		atraccionesBonAppetit.add(eregion);

		atraccionesEspadaYEscudo.add(mordor);
		atraccionesEspadaYEscudo.add(valinor);

		atraccionesNaturaleza.add(lindon);
		atraccionesNaturaleza.add(belerian);

		atraccionescamposFloridos.add(lindon);
		atraccionescamposFloridos.add(rauros);

		atraccionesElixir.add(lothlorien);
		atraccionesElixir.add(rohan);

		atraccionesVelozComoElViento.add(valinor);
		atraccionesVelozComoElViento.add(bosqueFangorn);

		promos.add(delicias);
		promos.add(elHorizonte);
		promos.add(elDesafio);
		promos.add(bonappetit);
		promos.add(espadaYEscudo);
		promos.add(naturaleza);
		promos.add(camposFloridos);
		promos.add(elixir);
		promos.add(velozComoElViento);

	}

	@After
	public void teardown() throws SQLException {

		Connection conexion = ConnectionProvider.getConnection(direccion);
		conexion.rollback();
		conexion.setAutoCommit(true);
	}

	@Test
	public void obtieneTodasLasPromos() {
		List<Promocion> promosTest = new ArrayList<Promocion>();
		promosTest = promocionDAO.findAll();
		assertEquals(promos, promosTest);
	}

	@Test
	public void cuentaTodasLasPromos() {
		assertEquals(promos.size(), promocionDAO.countAll());
	}

	@Test
	public void obtenerPromoPorId() {
		assertEquals(naturaleza, promocionDAO.findByPromocionId(6));
	}

}
