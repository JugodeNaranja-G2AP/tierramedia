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
import model.Tipo;
import model.Usuario;

public class UserDAOTests {

	private static String direccion = "jdbc:sqlite:database/tierramediaTest.db";

	Usuario legolas = new Usuario("Legolas", 100, 10, Tipo.AVENTURA);
	Usuario gimli = new Usuario("Gimli", 95, 8, Tipo.DEGUSTACION);
	Usuario arwen = new Usuario("Arwen", 70, 6, Tipo.PAISAJE);
	Usuario bilbo = new Usuario("Bilbo", 120, 9, Tipo.DEGUSTACION);
	Usuario elrond = new Usuario("Elrond", 73, 7, Tipo.PAISAJE);
	Usuario aragorn = new Usuario("Aragorn", 112, 11, Tipo.AVENTURA);

	List<Usuario> usuarios = new ArrayList<Usuario>();
	UserDAO userDAO = DAOFactory.getUserDAO();

	@Before
	public void setUp() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection(direccion);
		conexion.setAutoCommit(false);

		usuarios.add(legolas);
		usuarios.add(gimli);
		usuarios.add(arwen);
		usuarios.add(bilbo);
		usuarios.add(elrond);
		usuarios.add(aragorn);

	}

	@After
	public void teardown() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection(direccion);
		conexion.rollback();
		conexion.setAutoCommit(true);
	}

	@Test
	public void obtieneTodosLosUsuariosTest() {
		List<Usuario> listaUsuariosTest = new ArrayList<Usuario>();
		listaUsuariosTest = userDAO.findAll();

		assertEquals(usuarios, listaUsuariosTest);
	}

	@Test
	public void cuentaTodosLosUsuarios() {
		int cantidadDeUsuarios = userDAO.countAll();
		assertEquals(6, cantidadDeUsuarios);
	}

	@Test
	public void creaUnUsuarioYloElimina() {
		Usuario mariana = new Usuario(7, "Mariana", 90, 8, Tipo.PAISAJE);

		assertEquals(6, userDAO.countAll());
		userDAO.insert(mariana);
		assertEquals(7, userDAO.countAll());
		userDAO.delete(mariana);
		assertEquals(6, userDAO.countAll());
	}

	@Test
	public void actualizaElPresupuestoYtiempoDeUsuario() {
		Usuario usuarioDeBd = userDAO.findByUserId(6);
		assertEquals(aragorn.getPresupuesto(), usuarioDeBd.getPresupuesto());
		assertEquals(aragorn.getTiempoDisponible(), usuarioDeBd.getTiempoDisponible(), 0);
		Usuario aragorn = new Usuario(6, "Aragorn", 90, 8, Tipo.AVENTURA);
		userDAO.update(aragorn);
		usuarioDeBd = userDAO.findByUserId(6);
		assertEquals(aragorn.getPresupuesto(), usuarioDeBd.getPresupuesto());
		assertEquals(aragorn.getTiempoDisponible(), usuarioDeBd.getTiempoDisponible(), 0);
	}

}
