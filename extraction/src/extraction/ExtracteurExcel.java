/**
 * 
 */
package extraction;

import java.io.File; 
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import jxl.*; 

/**
 * @author yeponde
 *
 */
public class ExtracteurExcel 
{
	
	private String chemin;
	private File fichier;
	//private File nouveauFichier;
	private List<Cell[]> tableauCellules = new ArrayList<Cell[]>();
	
	public ExtracteurExcel(String cheminFichier)
	{
		
		System.setProperty( "file.encoding", "UTF-8" );
		
		chemin = cheminFichier;
		
		fichier = new File(this.chemin);
		
		//renseigner chemin fichier de sortie excel
		//nouveauFichier = new File("");
		
	}
	
	public void Extraction() throws JXLException, IOException
	{
		
		Workbook workbook = Workbook.getWorkbook(fichier);
		
		Sheet sheet = workbook.getSheet(0);
		
		//(collone, ligne)
		
		boolean finColonne = false;
		
		for(int i = 0; finColonne == false; i++)
		{
			Cell[] stock = sheet.getColumn(i);
			
			if(stock.length > 0)
			{
				tableauCellules.add(stock);
			}
			else
			{
				finColonne = true;
			}
		}
		
		affichage(tableauCellules);
	}
	
	public void affichage(List<Cell[]> tableau)
	{
		for(int i = 0; i < tableau.size(); i++)
		{
			Cell[] colonne = tableau.get(i);
			
			for(int j = 0; j < colonne.length; j++)
			{
				System.out.println(colonne[j]);
			}
		}
	}

}
