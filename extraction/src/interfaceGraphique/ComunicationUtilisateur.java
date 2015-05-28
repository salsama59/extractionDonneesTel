package interfaceGraphique;

import javax.swing.Icon;
import javax.swing.JOptionPane;

public class ComunicationUtilisateur 
{
	public ComunicationUtilisateur()
	{
		
	}
	
	
	public static void afficherMessageUtilisateur(String titre, String message, int typeDeMessage, Object icone)
	{
		JOptionPane.showMessageDialog(null, message, titre, typeDeMessage, (Icon) icone);
	}
	
}
