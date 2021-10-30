package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.ClaseDePromo;
import model.PromoAbsoluta;
import model.PromoAxB;
import model.PromoPorcentual;
import model.Promocion;
import model.Tipo;

public class PromocionDAOImpl implements PromocionDAO {

	private static String direccion = "jdbc:sqlite:database/tierramedia.db";

	@Override
	public List<Promocion> findAll() {
		try {
			String sql = "SELECT promociones.id, promociones.nombre, tipo_de_promo.nombre_tipo_promo, tipo_de_atracciones.tipo,\r\n"
					+ "group_concat(lista_atracciones_por_promo.atraccion_id) 'Lista_de_atracciones',\r\n"
					+ "promociones.atributo_de_promo\r\n" + "FROM lista_atracciones_por_promo\r\n"
					+ "JOIN promociones ON promociones.id = lista_atracciones_por_promo.promo_id\r\n"
					+ "JOIN tipo_de_promo ON tipo_de_promo.id = promociones.tipo_de_promo_id\r\n"
					+ "JOIN tipo_de_atracciones ON tipo_de_atracciones.id = promociones.Tipo\r\n"
					+ "GROUP BY promociones.nombre\r\n" + "ORDER BY promociones.id";

			Connection conn = ConnectionProvider.getConnection(direccion);
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promos = new ArrayList<Promocion>();
			while (resultados.next()) {
				promos.add(toPromocion(resultados));
			}

			return promos;
		} catch (Exception e) {
			throw new MissingDataException(e);

		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(*) AS 'Total_Promociones' FROM promociones";
			Connection conn = ConnectionProvider.getConnection(direccion);
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("Total_Promociones");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int insert(Promocion promocion) {

		try {
			String sql = "INSERT INTO promociones (nombre, tipo, tipo_de_promo_id, atributo_de_promo)\r\n"
					+ " VALUES (?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection(direccion);
			PreparedStatement statement = conn.prepareStatement(sql);

			if (promocion.getClase().equals(ClaseDePromo.PROMO_ABSOLUTA)) {
				statement.setString(1, promocion.getNombre());
				statement.setInt(2, promocion.getTipo().getId());
				statement.setInt(3, promocion.getClase().getId());
				statement.setInt(4, promocion.getCosto());
			}
			if (promocion.getClase().equals(ClaseDePromo.PROMO_PORCENTUAL)) {
				statement.setString(1, promocion.getNombre());
				statement.setInt(2, promocion.getTipo().getId());
				statement.setInt(3, promocion.getClase().getId());
				statement.setInt(4, ((PromoPorcentual) promocion).getPorcentajeDescuento());
			}
			if (promocion.getClase().equals(ClaseDePromo.PROMOAXB)) {
				statement.setString(1, promocion.getNombre());
				statement.setInt(2, promocion.getTipo().getId());
				statement.setInt(3, promocion.getClase().getId());
				statement.setInt(4, promocion.getId());
			}

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promocion promocion) {
		try {
			String sql = "UPDATE promociones SET nombre = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection(direccion);

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, promocion.getNombre());
			statement.setInt(2, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Promocion promocion) {
		try {
			String sql = "DELETE FROM promociones WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection(direccion);

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Promocion findByPromocionId(int promocionId) {
		try {
			String sql = "SELECT promociones.id, promociones.nombre, tipo_de_promo.nombre_tipo_promo,\r\n"
					+ "tipo_de_atracciones.tipo, group_concat(lista_atracciones_por_promo.atraccion_id) 'Lista_de_atracciones',\r\n"
					+ "promociones.atributo_de_promo\r\n" + "FROM lista_atracciones_por_promo\r\n"
					+ "JOIN promociones ON promociones.id = lista_atracciones_por_promo.promo_id\r\n"
					+ "JOIN tipo_de_promo ON tipo_de_promo.id = promociones.tipo_de_promo_id\r\n"
					+ "JOIN tipo_de_atracciones ON tipo_de_atracciones.id = promociones.tipo\r\n"
					+ "WHERE promociones.id = ?";

			Connection conn = ConnectionProvider.getConnection(direccion);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocionId);
			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;

			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}

			return promocion;

		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Promocion> obtenerPromociones(String promociones_id) {
		List<Promocion> promociones = new ArrayList<Promocion>();
		String[] promociones_id_str = null;
		promociones_id_str = promociones_id.split(",");

		for (String promocion_id : promociones_id_str) {
			promociones.add(findByPromocionId(Integer.parseInt(promocion_id)));
		}

		return promociones;
	}

	private Promocion toPromocion(ResultSet resultados) throws SQLException {
		Promocion promo = null;
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();

		if (resultados.getString(3).equals("Promo_Absoluta")) {

			promo = new PromoAbsoluta(resultados.getInt(1), resultados.getString(2),
					ClaseDePromo.valueOf(resultados.getString(3).toUpperCase()),
					Tipo.valueOf(resultados.getString(4).toUpperCase()),
					atraccionDAO.obtenerAtracciones(resultados.getString(5)), resultados.getInt(6));
		}

		if (resultados.getString(3).equals("PromoAXB")) {

			promo = new PromoAxB(resultados.getInt(1), resultados.getString(2),
					ClaseDePromo.valueOf(resultados.getString(3).toUpperCase()),
					Tipo.valueOf(resultados.getString(4).toUpperCase()),
					atraccionDAO.obtenerAtracciones(resultados.getString(5)),
					atraccionDAO.findByAtraccionId(resultados.getInt(6)));
		}

		if (resultados.getString(3).equals("Promo_Porcentual")) {

			promo = new PromoPorcentual(resultados.getInt(1), resultados.getString(2),
					ClaseDePromo.valueOf(resultados.getString(3).toUpperCase()),
					Tipo.valueOf(resultados.getString(4).toUpperCase()),
					atraccionDAO.obtenerAtracciones(resultados.getString(5)), resultados.getInt(6));
		}
		return promo;
	}

}
