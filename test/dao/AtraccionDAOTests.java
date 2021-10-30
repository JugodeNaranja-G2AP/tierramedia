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
import model.Tipo;

public class AtraccionDAOTests {

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

	List<Atraccion> atracciones = new ArrayList<Atraccion>();
	AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();

	@Before
	public void setUp() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection(direccion);
		conexion.setAutoCommit(false);

		atracciones.add(moria);
		atracciones.add(minasTirith);
		atracciones.add(laComarca);
		atracciones.add(mordor);
		atracciones.add(abismoDeHelm);
		atracciones.add(lothlorien);
		atracciones.add(erebor);
		atracciones.add(bosqueNegro);
		atracciones.add(doriath);
		atracciones.add(valinor);
		atracciones.add(eregion);
		atracciones.add(lindon);
		atracciones.add(eriador);
		atracciones.add(rohan);
		atracciones.add(belerian);
		atracciones.add(gondor);
		atracciones.add(rivendell);
		atracciones.add(rauros);
		atracciones.add(bosqueFangorn);

	}

	@After
	public void teardown() throws SQLException {

		Connection conexion = ConnectionProvider.getConnection(direccion);
		conexion.rollback();
		conexion.setAutoCommit(true);
	}

	@Test
	public void obtenerTodasLasAtraccionestest() {
		List<Atraccion> atraccionesTest = new ArrayList<Atraccion>();
		atraccionesTest = atraccionDAO.findAll();
		assertEquals(atracciones, atraccionesTest);
	}

	@Test
	public void cuentaTodasLasAtracciones() {
		assertEquals(19, atraccionDAO.countAll());

	}

	@Test
	public void agregaUnaAtraccionYlaElimina() {
		Atraccion rocaDragon = new Atraccion(20, "Roca Dragon", 20, 2, 6, Tipo.AVENTURA);
		assertEquals(19, atraccionDAO.countAll());
		atraccionDAO.insert(rocaDragon);
		assertEquals(20, atraccionDAO.countAll());
		atraccionDAO.delete(rocaDragon);
		assertEquals(19, atraccionDAO.countAll());
	}

	@Test
	public void encuentraUnaAtraccionPorId() {
		assertEquals(moria, atraccionDAO.findByAtraccionId(1));
	}

	@Test
	public void actualizaElCupoDeUnaAtraccion() {
		bosqueFangorn = atraccionDAO.findByAtraccionId(19);
		assertEquals(6, bosqueFangorn.getCupoDePersonas());
		Atraccion bosqueFangorn = new Atraccion(19, "Bosque Fangorn", 6, 3, 5, Tipo.AVENTURA);
		atraccionDAO.update(bosqueFangorn);
		bosqueFangorn = atraccionDAO.findByAtraccionId(bosqueFangorn.getId());
		assertEquals(5, bosqueFangorn.getCupoDePersonas());
	}

}
