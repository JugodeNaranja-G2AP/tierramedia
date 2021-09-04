package tierramedia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CargarUsuarios {
	
	public static List<Usuario> obtener(String archivo) {
		FileReader fr = null;
		BufferedReader br = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea = br.readLine();

			while (linea != null) {
				try {
					String[] datosUsuarios = linea.split(";");
					String nombre = datosUsuarios[0];
					int presupuesto = Integer.parseInt(datosUsuarios[1]);
					double tiempo = Double.parseDouble(datosUsuarios[2]);
					Tipo tipo = Tipo.valueOf(Tipo.class, datosUsuarios[3].trim().toUpperCase());
					Usuario usuario = new Usuario(nombre, presupuesto, tiempo, tipo);

					usuarios.add(usuario);
					linea = br.readLine();

				} catch (NumberFormatException e) {
					System.err.println("Uno de los datos leídos no es un double o un entero");
				}

			}
			return usuarios;

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

		return usuarios;

	}
}