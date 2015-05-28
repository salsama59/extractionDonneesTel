package accesDonnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RequeteSqlSelectEtiquetteType 
{
	
	private Connection connection;
	
	public RequeteSqlSelectEtiquetteType()
	{
		DetailTelephone base = new DetailTelephone();
		
		setConnection(base.getConnection());
	}
	
	public String creerRequete()
	{
		StringBuilder requete = new StringBuilder("");
		requete.append("SELECT ");
		requete.append("et_id, et_libelle");
		requete.append(" FROM ");
		requete.append("tbl_etiquette_type");
		
		return requete.toString();
		
	}
	
	public List<String> recupererEtiquettes() throws SQLException
	{
		List<String> etiquettes = new ArrayList<String>();
		//Création d'un objet Statement
		Statement state = connection.createStatement();
		//L'objet ResultSet contient le résultat de la requête SQL
		ResultSet result = state.executeQuery(creerRequete());
		
		//récupérer le tableau d'étiquette en bouclant sur le set de résultat
		
		while(result.next())
		{
			etiquettes.add(result.getObject(2).toString());
		}
		
		result.close();
		
		state.close();
		
		return etiquettes;
		
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
