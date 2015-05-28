package extraction;

import java.util.regex.Pattern;

public class Utilitaires 
{
	
	public <T> Object recupererPositionValeur(T valeur, T [] tableauValeur)
	{
		int position = 0;
		
		boolean valeurTrouve = false;
		
		T valeurDuTableau;
		
		for(int i = 0 ; i < tableauValeur.length; i++)
		{
			valeurDuTableau = tableauValeur[i];
			
			if(valeurDuTableau.equals(valeur))
			{
				position = i;
				valeurTrouve = true;
				break;
			}
			
			if(i == tableauValeur.length)
			{
				if(!valeurTrouve)
				{
					return null;
				}
			}
		}
		
		return position;
		
	}
	
	public boolean chaineEstNumerique(String valeur)
	{
		final Pattern PATTERN_INTEGER = Pattern.compile("[0-9]+");
		
		return PATTERN_INTEGER.matcher(valeur).matches();
	}
	
}
