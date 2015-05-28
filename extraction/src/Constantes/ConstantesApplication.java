package Constantes;

import accesDonnees.DetailTelephone;

public class ConstantesApplication 
{
	
	public static boolean connexionValide = false;
	
	public ConstantesApplication()
	{
		DetailTelephone baseDeTest = new DetailTelephone(true);
		connexionValide = baseDeTest.isValide();
	}

	public static boolean isConnexionValide() {
		return connexionValide;
	}

	public static void setConnexionValide(boolean connexionValide) {
		ConstantesApplication.connexionValide = connexionValide;
	}
}
