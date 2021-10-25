package dao;

import java.util.List;

import model.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion> {

	public abstract Atraccion findByAtraccionId(int atraccionId);

	public abstract List<Atraccion> obtenerAtracciones(String atracciones_id);

}
