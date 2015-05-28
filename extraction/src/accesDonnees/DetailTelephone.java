package accesDonnees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import configuration.GestionConfiguration;

public class DetailTelephone
{
	
	private String urlBase;
	//private String urlBase = "jdbc:postgresql://localhost:5432/Db_Detail_telephone";
	//private String urlBase = "jdbc:postgresql://localhost:5432/postgres";
	private String utilisateur;
	private String motDePasse;
	//private String motDePasse = "test";
	private Connection connection;
	
	private boolean valide;
	
	private GestionConfiguration configuration;
	
	public DetailTelephone()
	{
		
		try 
		{
			
			configuration = new GestionConfiguration();
			
			HashMap<String, String> config = this.configuration.recupererConfiguration();
			
			urlBase = construireUrlBase(config);
			
			configurerAcces(config);
			
			connection = DriverManager.getConnection(urlBase, utilisateur, motDePasse);
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
	public DetailTelephone(boolean test)
	{
		
		try 
		{
			
			configuration = new GestionConfiguration();
			
			HashMap<String, String> config = this.configuration.recupererConfiguration();
			
			urlBase = construireUrlBase(config);
			
			configurerAcces(config);
			
			connection = DriverManager.getConnection(urlBase, utilisateur, motDePasse);
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		if(test && (connection != null))
		{
			valide = true;
		}
		else if(test && (connection == null))
		{
			valide = false;
		}
		
		
	}
	
	
	private String construireUrlBase(HashMap<String, String> config)
	{
		
		String url = "";
		
		final String base = "jdbc:postgresql://";
		
		String port = config.get("base_donnees.port");
		
		String adresse = config.get("base_donnees.adresse");
		
		String nomBase= config.get("base_donnees.nom");
		
		url = base + adresse + ":" + port + "/" + nomBase;
		
		return url;
		
	}
	
	private void configurerAcces(HashMap<String, String> config)
	{
		
		utilisateur = config.get("base_donnees.utilisateur");
		
		motDePasse = config.get("base_donnees.mot_de_passe");
		
	}

	public String getUrlBase() 
	{
		return urlBase;
	}

	public void setUrlBase(String urlBase) 
	{
		this.urlBase = urlBase;
	}

	public String getUtilisateur() 
	{
		return utilisateur;
	}

	public void setUtilisateur(String utilisateur) 
	{
		this.utilisateur = utilisateur;
	}

	public String getMotDePasse() 
	{
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) 
	{
		this.motDePasse = motDePasse;
	}

	public Connection getConnection() 
	{
		return connection;
	}

	public void setConnection(Connection connection) 
	{
		this.connection = connection;
	}


	public boolean isValide() {
		return valide;
	}


	public void setValide(boolean valide) {
		this.valide = valide;
	}
	
}