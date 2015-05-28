package configuration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class GestionConfiguration 
{
	
	private org.jdom2.Document document;
	private org.jdom2.Element racine;
	private final String urlFichierConfig;
	private HashMap<String, String> valeurConfig;
	private File fichierConfig;
	
	public GestionConfiguration()
	{
		urlFichierConfig = "Configuration/config_extracteur.xml";
		
		fichierConfig = new File(urlFichierConfig);
		
		try 
		{
		    
		    SAXBuilder sxb = new SAXBuilder();
		    
		    document = sxb.build(fichierConfig);
		    
		    racine = document.getRootElement();
		    
		}
		catch (final IOException e) 
		{
		    e.printStackTrace();
		}
		catch (JDOMException e) 
		{
			e.printStackTrace();
		}
	}
	
	private String contruireCleConfiguration(String partie1, String partie2)
	{
		if(partie2.equals(""))
		{
			return partie1;
		}
		else
		{
			return partie1 + "." + partie2;
		}
		
	}
	
	public void sauvegardeModifications()
	{
		
		try
		   {
			 XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	         sortie.output(document, new FileOutputStream(urlFichierConfig));
		   }
		   catch (java.io.IOException e)
		{
			   e.printStackTrace();
		}
		
	}
	
	public void miseAjourConfiguration(HashMap<String, String> nouvelleConfig)
	{
		
		valeurConfig = nouvelleConfig;
		
		org.jdom2.Element noeud;
		
		String nomNoeud;
		
		String nomSousNoeud;
		
		String valeur;
		
		int nb = 0;
		
		List<org.jdom2.Element> list;
		
		String cle;
		
		//Récupération de la liste des noeud du document xml
	    List<org.jdom2.Element> racineNoeuds = racine.getChildren();
	    
	    //Récupération du nombre d'élément de la liste de noeud
	    int nbRacineNoeuds = racineNoeuds.size();
		
	    //Boucle pour parcourir les éléments de la liste de noeud
	    for (int i = 0; i<nbRacineNoeuds; i++) 
	    {
	    	noeud = (org.jdom2.Element) racineNoeuds.get(i);
	    	
    		nomNoeud = noeud.getName();
    		
    		list = noeud.getChildren();
    		
    		nb = list.size();
    		
    		if(nb > 1)
    		{
    			
    			for(int j = 0; j < nb; j++)
	    		{
	    			
    				org.jdom2.Element sousNoeud = list.get(j);
    				nomSousNoeud = sousNoeud.getName();
    				cle = contruireCleConfiguration(nomNoeud, nomSousNoeud);
    				valeur = (String) valeurConfig.get(cle);
    				sousNoeud.setText(valeur);
	    			
	    		}
    			
    		}
    		else
    		{
    			cle = contruireCleConfiguration(nomNoeud, "");
    			valeur = (String) valeurConfig.get(cle);
    			noeud.setText(valeur);
    		}
    		
    	
	    }
	    
	    sauvegardeModifications();
	}
	
	public HashMap<String, String> recupererConfiguration()
	{
		
		valeurConfig = new HashMap<String, String>();
		
		org.jdom2.Element noeud;
		
		String nomNoeud;
		
		String nomSousNoeud;
		
		String valeur;
		
		int nb = 0;
		
		List<org.jdom2.Element> list;
		
		String cle;
		
		//Récupération de la liste des noeud du document xml
		List<org.jdom2.Element> racineNoeuds = racine.getChildren();
	    
	    //Récupération du nombre d'élément de la liste de noeud
	    int nbRacineNoeuds = racineNoeuds.size();
		
	    //Boucle pour parcourir les éléments de la liste de noeud
	    for (int i = 0; i<nbRacineNoeuds; i++) 
	    {
	    	
	    	noeud = racineNoeuds.get(i);
	    	
    		nomNoeud = noeud.getName();
    		
    		list = noeud.getChildren();
    		
    		nb = list.size();
    		
    		if(nb > 1)
    		{
    			
    			for(int j = 0; j < nb; j++)
	    		{
	    			
    				org.jdom2.Element sousNoeud = list.get(j);
	    			
	    			valeur = sousNoeud.getText();
	    			nomSousNoeud = sousNoeud.getName();
	    			cle = contruireCleConfiguration(nomNoeud, nomSousNoeud);
	    			
	    			valeurConfig.put(cle, valeur);
		    		
	    		}
    			
    		}
    		else
    		{
    			
    			valeur = noeud.getText();
    			cle = contruireCleConfiguration(nomNoeud, "");
    			
    			valeurConfig.put(cle, valeur);
    		}
    		
	    }
	    
	    return valeurConfig;
	}
}
