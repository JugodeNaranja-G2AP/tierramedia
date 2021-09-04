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
					String [] divisor = linea.split("|");
					String claseDePromo = divisor [0];
					String infoDePromo = divisor [1];
					
					if((claseDePromo).equals("PromoAxB")) {
						String [] datosAxB = infoDePromo.split(",");
						
					}
				}catch (NumberFormatException e) {
					System.err.println("Uno de los datos leídos no es un double o un entero");
				}
			}
/*		}
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
		return promos;
	}*/
	
/*			int cantidad = Integer.parseInt(br.readLine());
			promos = new Promocion[cantidad];
			int indice = 0;

			String claseDePromo = br.readLine();

			if ((claseDePromo).equals("PromoAxB")) {

				

			}
			if ((claseDePromo).equals("PromoAbsoluta")) {

				
			}
			
			if ((claseDePromo).equals("PromoDescuento")) {
				
				
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
	}*/
}
