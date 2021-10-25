package tierramedia;

public class Reloj {
	
	public static String conversor(double horaDec) {
		
		if (obtenerMinutos(horaDec)==0) {
			if (obtenerHoras(horaDec)==1) {
				return obtenerHoras(horaDec) + " hora";
			} else {
				return obtenerHoras(horaDec) + " horas";
			}
			
		}else {
				if (obtenerHoras(horaDec)==1) {
					return obtenerHoras(horaDec) + " hora y " + obtenerMinutos(horaDec) + " minutos";
				} else {
					return obtenerHoras(horaDec) + " horas y " + obtenerMinutos(horaDec) + " minutos";
				}
		}
	}
	
	private static int obtenerHoras(double horaDec) {
		return (int) Math.floor(horaDec);
	}
	
	private static int obtenerMinutos(double horaDec) {
		return  (int) (horaDec * 60) % 60;
	}
	
}