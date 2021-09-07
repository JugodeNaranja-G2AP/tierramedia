package tierramedia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargarPromociones {

	public static List<Promocion> obtener(String archivoPromociones, String archivoAtracciones) {
		FileReader fr = null;
		BufferedReader br = null;
		List<Promocion> promos = new ArrayList<Promocion>();

		try {
			fr = new FileReader(archivoPromociones);
			br = new BufferedReader(fr);
			
			String linea = br.readLine();

			while(linea != null) {
				try {
					String [] divisor = linea.split("/");
					String claseDePromo = divisor [0];
					String infoDePromo = divisor [1];
					String atraccionesString = divisor [2];
					
					if(claseDePromo.equals("PromoAxB")) {
						String [] datosAxB = infoDePromo.split(",");
						String nombre = datosAxB [0];
						Tipo tipo = Tipo.valueOf(Tipo.class, datosAxB[1].trim().toUpperCase());
						String [] atraccionesDePromo = atraccionesString.split(";");
						
						int indiceBase = atraccionesDePromo.length;
						Atraccion[] atracciones = new Atraccion[indiceBase-1]; //ver de "emprolijar"
						for (int i = 0; i < indiceBase - 1; i++) {
							atracciones[i] = agregarAtraccionPorNombre(atraccionesDePromo[i], archivoAtracciones);
						}
						
						
						Atraccion atraccionGratis = atracciones[indiceBase-2];
						Promocion promo = new PromoAxB(nombre, tipo, atracciones, atraccionGratis); 

						promos.add(promo);
						linea = br.readLine();
						
					} else if(claseDePromo.equals("PromoAbsoluta")) {
						String [] datosAbsoluta = infoDePromo.split(",");
						String nombre = datosAbsoluta [0];
						Tipo tipo = Tipo.valueOf(Tipo.class, datosAbsoluta[1].trim().toUpperCase());
						int costo = Integer.parseInt(datosAbsoluta[2]);
						String [] atraccionesDePromo = atraccionesString.split(";");
						
						int indiceBase = atraccionesDePromo.length;
						Atraccion[] atracciones = new Atraccion[indiceBase];
						for (int i = 0; i < indiceBase; i++) {
							atracciones[i] = agregarAtraccionPorNombre(atraccionesDePromo[i], archivoAtracciones);
						}
						
						Promocion promo = new PromoAbsoluta(nombre, tipo, costo, atracciones); 
					
						promos.add(promo);
						linea = br.readLine();
						
					} else {
						String [] datosDescuento = infoDePromo.split(",");
						String nombre = datosDescuento [0];
						Tipo tipo = Tipo.valueOf(Tipo.class, datosDescuento[1].trim().toUpperCase());
						int porcentaje = Integer.parseInt(datosDescuento[2]);
						String [] atraccionesDePromo = atraccionesString.split(";");
						
						int indiceBase = atraccionesDePromo.length;
						Atraccion[] atracciones = new Atraccion[indiceBase];
						for (int i = 0; i < indiceBase; i++) {
							atracciones[i] = agregarAtraccionPorNombre(atraccionesDePromo[i], archivoAtracciones);
						}
						
						Promocion promo = new PromoPorcentual(nombre, tipo, porcentaje, atracciones); 
					
						promos.add(promo);
						linea = br.readLine();
					}
				}catch (NumberFormatException e) {
					System.err.println("Uno de los datos leídos no es un double o un entero");
				}
			}
			return promos;
			
			
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
		return promos;
	}

	public static Atraccion agregarAtraccionPorNombre(String nombre, String archivoAtracciones) {
		for (Atraccion atraccion : CargarAtracciones.obtener(archivoAtracciones)) {
			if (atraccion.getNombre().equals(nombre)) {
				return atraccion;
			}
		}
		return null;
	}
}