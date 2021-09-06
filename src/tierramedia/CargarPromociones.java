package tierramedia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargarPromociones {

	public static List<Promocion> obtener(String archivo) {
		FileReader fr = null;
		BufferedReader br = null;
		List<Promocion> promos = new ArrayList<Promocion>();

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea = br.readLine();

			while(linea != null) {
				try {
					String [] divisor = linea.split("/");
					String claseDePromo = divisor [0];
					String infoDePromo = divisor [1];
					String atracciones = divisor [2];
					
					if(claseDePromo.equals("PromoAxB")) {
						String [] datosAxB = infoDePromo.split(",");
						String nombre = datosAxB [0];
						Tipo tipo = Tipo.valueOf(Tipo.class, datosAxB[1].trim().toUpperCase());
						String [] atraccionesDePromo = atracciones.split(";");
						String atraccionGratis = atraccionesDePromo[3];
						Promocion promo = new PromoAxB(nombre, tipo, atraccionesDePromo, atraccionGratis); 
						
						promos.add(promo);
						linea = br.readLine();
						
					} else if(claseDePromo.equals("PromoAbsoluta")) {
						String [] datosAbsoluta = infoDePromo.split(",");
						String nombre = datosAbsoluta [0];
						Tipo tipo = Tipo.valueOf(Tipo.class, datosAbsoluta[1].trim().toUpperCase());
						int costo = Integer.parseInt(datosAbsoluta[2]);
						String [] atraccionesDePromo = atracciones.split(";");
						Promocion promo = new PromoAbsoluta(nombre, tipo, costo, atraccionesDePromo); 
					
						promos.add(promo);
						linea = br.readLine();
						
					} else {
						String [] datosDescuento = infoDePromo.split(",");
						String nombre = datosDescuento [0];
						Tipo tipo = Tipo.valueOf(Tipo.class, datosDescuento[1].trim().toUpperCase());
						int porcentaje = Integer.parseInt(datosDescuento[2]);
						String [] atraccionesDePromo = atracciones.split(";");
						Promocion promo = new PromoPorcentual(nombre, tipo, porcentaje, atraccionesDePromo); 
					
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
}