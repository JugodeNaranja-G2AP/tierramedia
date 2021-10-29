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
import model.Tipo;
import model.Usuario;

public class UserDAOTests {

	private static String direccion = "jdbc:sqlite:database/tierramediaTest.db";

	static Usuario usuario1 = new Usuario(1, "Daniel", 85, 6.5, Tipo.PAISAJE);
	static Usuario usuario2 = new Usuario(2, "Ana", 98, 9, Tipo.AVENTURA);
	static Usuario usuario3 = new Usuario(3, "Juan", 81, 7, Tipo.DEGUSTACION);
	Usuario usuario4 = new Usuario(4, "Nymeria", 91, 8.5, Tipo.AVENTURA);
	static List<Usuario> usuarios = new ArrayList<Usuario>();
	static UserDAO userDAO = DAOFactory.getUserDAO();

	@BeforeClass
	public static void setUp() throws SQLException {
		Connection conexion = ConnectionProvider.getConnection(direccion);
		conexion.setAutoCommit(false);

		usuarios.add(usuario1);
		usuarios.add(usuario2);
		usuarios.add(usuario3);

		for (Usuario usuario : usuarios) {
			userDAO.insert(usuario);
		}

	}

	@Test
	public void crearUnUsuarioTest() {
		int cantidadDeUsuarios = userDAO.countAll();
		assertEquals(3, cantidadDeUsuarios);
		userDAO.insert(usuario4);
		usuarios.add(usuario4);
		cantidadDeUsuarios = userDAO.countAll();
		assertEquals(4, cantidadDeUsuarios);

	}

	@Test
	public void obtenerTodosLosUsuariosTest() {
		List<Usuario> listaUsuariosTest = new ArrayList<Usuario>();

		listaUsuariosTest = userDAO.findAll();

		assertEquals(usuarios, listaUsuariosTest);

	}

	@Test
	public void puedeEncontrarUnUsuariosPorId() {
		Usuario mariana = new Usuario(5, "Mariana", 90, 8, Tipo.PAISAJE);
		userDAO.insert(mariana);
		int idUsuario = mariana.getId();
		Usuario usuarioEncontrado = userDAO.findByUserId(idUsuario);
		assertEquals(mariana, usuarioEncontrado);
		userDAO.delete(mariana);
	}

	@Test
	public void actualizaPresupuestoYtiempoDeUsuario() {
		assertEquals(91, usuario4.getPresupuesto());
		assertEquals(8.5, usuario4.getTiempoDisponible(), 0);
		usuarios.remove(usuario4);
		Usuario usuario4 = new Usuario(4, "Nymeria", 70, 5, Tipo.AVENTURA);
		usuarios.add(usuario4);
		userDAO.update(usuario4);
		usuario3 = userDAO.findByUserId(usuario4.getId());
		assertEquals(70, usuario3.getPresupuesto());
		assertEquals(5, usuario3.getTiempoDisponible(), 0);

	}

	@AfterClass
	public static void teardown() throws SQLException {
		for (Usuario usuario : usuarios) {
			userDAO.delete(usuario);
		}

		Connection conexion = ConnectionProvider.getConnection(direccion);
		conexion.rollback();
		conexion.setAutoCommit(true);
	}
}
