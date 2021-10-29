package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jdbc.ConnectionProvider;
import model.Usuario;

public class ItinerarioTest {

	private static String direccion = "jdbc:sqlite:database/tierramediaTest.db";

	Usuario usuarioTest;
	UserDAO userDAO = DAOFactory.getUserDAO();

	@Before
	public void setUp() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection(direccion);
		conexion.setAutoCommit(false);
	}

	@Test
	public void test() {

	}

	@After
	public void tearDown() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection(direccion);
		conexion.rollback();
		conexion.setAutoCommit(true);
	}
}