package dao;

import java.util.List;

import model.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {

	public abstract Promocion findByPromocionId(int promocionId);

	public abstract List<Promocion> obtenerPromociones(String promociones_id);
}
