package accesDonnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class RequeteSqlSelectDetailsFacturation
{
	
	private Connection connection;
	
	public RequeteSqlSelectDetailsFacturation()
	{
		DetailTelephone base = new DetailTelephone();
		
		setConnection(base.getConnection());
	}
	
	public String creerRequete()
	{
		StringBuilder requete = new StringBuilder("");
		requete.append("SELECT ");
		requete.append("df_id, df_id, df_type, df_code, df_nom, df_cout_ttc, df_nombre_appels, df_duree_appels, df_date");
		requete.append(" FROM ");
		requete.append("tbl_detail_facturation");
		requete.append(" WHERE ");
		requete.append("df_type = ");
		requete.append("3");
		
		return requete.toString();
		
	}
	
	public void executer() throws SQLException
	{
		//Création d'un objet Statement
		Statement state = connection.createStatement();
		//L'objet ResultSet contient le résultat de la requête SQL
		ResultSet result = state.executeQuery(creerRequete());
		//On récupère les MetaData
		ResultSetMetaData resultMeta = result.getMetaData();
		
		System.out.println("\n**********************************");
		//On affiche le nom des colonnes
		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
		     
		System.out.println("\n**********************************");
		     
		while(result.next())
		{
			
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
			{
				System.out.print("\t" + result.getObject(i).toString() + "\t |");
			}
		 
			System.out.println("\n---------------------------------");
		}
		
		result.close();
		state.close();
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
