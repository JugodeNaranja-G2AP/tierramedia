package tierramedia;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ArchivoDeSalidaTests {
	
	Usuario u = new Usuario("Faramir", 100, 10, Tipo.PAISAJE);
	Atraccion atraccion1 = new Atraccion("Lindon", 9, 3, 10, Tipo.PAISAJE);
	Atraccion atraccion2 = new Atraccion("Belerian", 10, 3, 6, Tipo.PAISAJE);
	List<Atraccion> atraccionesAbsTest = new ArrayList<Atraccion>();
	Promocion promocionAbs = new PromoAbsoluta("Naturaleza", ClaseDePromo.PROMO_ABSOLUTA, Tipo.PAISAJE,
			atraccionesAbsTest, 15);
	
	@Before
	public void setUp() {
		atraccionesAbsTest.add(atraccion1);
		atraccionesAbsTest.add(atraccion2);
	}
	

	@Test
	public void imprimeItinerarioYSonIgualesTest() throws IOException {
		u.reservarProducto(promocionAbs);
		String archivoDeSalida = "salida/" + u.getNombre() + "Itinerario.txt";
		Impresora.imprimirItinerarioDeUsuario(u, archivoDeSalida);
		
		Path archivo1 = Paths.get("salida/FaramirItinerario.txt");
		Path archivo2 = Paths.get("salida/itinerarioTest.txt");
		
		long mismatch = Files.mismatch(archivo1, archivo2);
		assertEquals(-1, mismatch);
		
	}

	@Test
	public void imprimeNoItinerarioYSonIgualesTest() throws IOException {
		String archivoDeSalida = "salida/" + u.getNombre() + ".txt";
		Impresora.imprimirElNoItinerario(u, archivoDeSalida);
		
		Path archivo1 = Paths.get("salida/Faramir.txt");
		Path archivo2 = Paths.get("salida/noItinerarioTest.txt");
		
		long mismatch = Files.mismatch(archivo1, archivo2);
		assertEquals(-1, mismatch);
		
	}
}
