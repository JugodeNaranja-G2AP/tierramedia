package tierramedia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargarAtracciones {

	public static List<Atraccion> obtener(String archivo) {
		FileReader fr = null;
		BufferedReader br = null;
		List<Atraccion> atracciones = new ArrayList<Atraccion>();

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea = br.readLine();
			System.out.println("dividi el coso en 3");


			while (linea != null) {
				try {
					String[] datosAtraccion = linea.split(";");
					String nombre = datosAtraccion[0];
					int costo = Integer.parseInt(datosAtraccion[1]);
					double tiempo = Double.parseDouble(datosAtraccion[2]);
					int cupo = Integer.parseInt(datosAtraccion[3]);
					Tipo tipo = Tipo.valueOf(Tipo.class, datosAtraccion[4].trim().toUpperCase());
					Atraccion atraccion = new Atraccion(nombre, costo, tiempo, cupo, tipo);
					linea = br.readLine();
					
					atracciones.add(atraccion);
					linea = br.readLine();
					
				}catch (NumberFormatException e) {
					System.err.println("Uno de los datos leídos no es un double o un entero");
				}
			}
			return atracciones;

		} catch (IOException e) {
			e.printStackTrace();
		
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return atracciones;
	}
}