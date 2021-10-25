package dao;

import java.util.List;

import model.Usuario;
import tierramedia.Producto;

public interface UserDAO extends GenericDAO<Usuario> {

	public abstract Usuario findByUserId(int userId);

	public abstract List<Producto> obtenerItinerario(Usuario usuario);

	public abstract int registrarCompra(Usuario usuario, Producto productoComprado);
}
