package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tierramedia.Atraccion;
import tierramedia.ClaseDePromo;
import tierramedia.Impresora;
import tierramedia.PromoAbsoluta;
import tierramedia.Promocion;
import tierramedia.Tipo;
import tierramedia.Usuario;

public class Main {

	public static void main(String[] args) throws IOException {
		Usuario u = new Usuario("Faramir", 100, 10, Tipo.PAISAJE);
		Atraccion atraccion1 = new Atraccion("Lindon", 9, 3, 10, Tipo.PAISAJE);
		Atraccion atraccion2 = new Atraccion("Belerian", 10, 3, 6, Tipo.PAISAJE);
		List<Atraccion> atraccionesAbsTest = new ArrayList<Atraccion>();
		atraccionesAbsTest.add(atraccion1);
		atraccionesAbsTest.add(atraccion2);
		Promocion promocionAbs = new PromoAbsoluta("Naturaleza", ClaseDePromo.PROMO_ABSOLUTA, Tipo.PAISAJE,
				atraccionesAbsTest, 15);
		
		u.reservarProducto(promocionAbs);
		String archivoDeSalida = "salida/" + u.getNombre() + ".txt";
		Impresora.imprimirItinerarioDeUsuario(u, archivoDeSalida);

	}

}
