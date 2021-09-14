package main;

public class Reloj {
	
	public static String conversor(double horaDec) {
		
		if (minutos(horaDec)==0) {
			if (horas(horaDec)==1) {
				return horas(horaDec) + " hora";
			} else {
				return horas(horaDec) + " horas";
			}
			
		}else {
				if (horas(horaDec)==1) {
					return horas(horaDec) + " hora y " + minutos(horaDec) + " minutos";
				} else {
					return horas(horaDec) + " horas y " + minutos(horaDec) + " minutos";
				}
		}
	}
	
	private static int horas(double horaDec) {
		return (int) Math.round(horaDec);
	}
	
	private static int minutos(double horaDec) {
		return  (int) (horaDec * 60) % 60;
	}
	
}