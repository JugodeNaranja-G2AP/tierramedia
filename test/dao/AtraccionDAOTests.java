package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Tipo;

public class AtraccionDAOTests {
	
	private static String direccion = "jdbc:sqlite:database/tierramediaTest.db";

	static Atraccion atraccion1 = new Atraccion("Lothlorien", 35, 1, 30, Tipo.DEGUSTACION);
	static Atraccion atraccion2 = new Atraccion("Minas Tirith", 5, 2, 25, Tipo.PAISAJE);
	static Atraccion atraccion3 = new Atraccion("Abismo de Helm", 5, 2, 15, Tipo.PAISAJE);
	static Atraccion atraccion4 = new Atraccion("La Comarca", 3, 6.5, 150, Tipo.DEGUSTACION);
	static Atraccion atraccion5 = new Atraccion("Mordor", 3, 4, 12, Tipo.AVENTURA);
	static List<Atraccion> atracciones = new ArrayList<Atraccion>();
	static AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();

	@BeforeClass
	public static void setUp() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection(direccion);
		conexion.setAutoCommit(false);

		atracciones.add(atraccion1);
		atracciones.add(atraccion2);
		atracciones.add(atraccion3);
		atracciones.add(atraccion4);
		atracciones.add(atraccion5);

		for (Atraccion atraccion : atracciones) {
			atraccionDAO.insert(atraccion);
		}
	}

	@Test
	public void obtenerTodasLasAtraccionestest() {
		List<Atraccion> atraccionesTest = new ArrayList<Atraccion>();
		atraccionesTest = atraccionDAO.findAll();
		assertEquals(atracciones, atraccionesTest);
	}

	@Test
	public void modificarElcupoDeUnaAtraccion() {
		assertEquals(12, atraccion5.getCupoDePersonas());
		Atraccion atraccion5 = new Atraccion(5, "Mordor", 3, 4, 10, Tipo.AVENTURA);
		atraccionDAO.update(atraccion5);
		atraccion5 = atraccionDAO.findByAtraccionId(atraccion5.getId());
		assertEquals(10, atraccion5.getCupoDePersonas());
	}

	@Test
	public void cuentaTodasLasAtraccionesYagregaUna() {
		Atraccion rocaDragon = new Atraccion("Roca Dragon", 20, 2, 6, Tipo.AVENTURA);
		assertEquals(5, atraccionDAO.countAll());
		atraccionDAO.insert(rocaDragon);
		assertEquals(6, atraccionDAO.countAll());

	}

	@Test
	public void buscaUnaAtraccionPorId() {
		Atraccion lindon = new Atraccion(7, "Lindon", 15, 2, 4, Tipo.PAISAJE);
		atraccionDAO.insert(lindon);
		Atraccion atraccionEncontrada = atraccionDAO.findByAtraccionId(lindon.getId());
		assertEquals(lindon, atraccionEncontrada);

	}

	@AfterClass
	public static void teardown() throws SQLException {
		for (Atraccion atraccion : atracciones) {
			atraccionDAO.delete(atraccion);
		}

		Connection conexion = ConnectionProvider.getConnection(direccion);
		conexion.rollback();
		conexion.setAutoCommit(true);
	}

}
