package accesDonnees;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import enumerations.Mois;

public class RequeteSqlVerifierMigrationDetailsFacturation 
{
	
	private Connection connection;
	private LocalDate date;
	private Long nombreLigne;
	
	
	public RequeteSqlVerifierMigrationDetailsFacturation(String mois, String anne)
	{
		
		this.setDate(LocalDate.of(Integer.parseInt(anne), Mois.getNumeroMois(mois), 27));
		
		DetailTelephone base = new DetailTelephone();
		
		this.setConnection(base.getConnection());
		
	}
	
	private String creerRequete()
	{
		
		StringBuilder requete = new StringBuilder("");
		
		requete.append("SELECT COUNT (*)");
		
		requete.append("tbl_detail_facturation ");
		
		requete.append("WHERE ");
		
		requete.append("df_date = ");
		
		requete.append(this.getDate());
		
		requete.append(";");
		
		return requete.toString();
		
	}
	
	
	public Boolean estDejaFaite() throws SQLException
	{
		Boolean effectue = false;
		
		Statement state = connection.createStatement();
		
		ResultSet result = state.executeQuery(creerRequete());
		
		this.setNombreLigne(result.getLong(1));
		
		if(nombreLigne > 0)
		{
			effectue = true;
		}
		
		result.close();
		
		state.close();
		
		return effectue;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public LocalDate getDate() {
		return this.date;
	}

	public Long getNombreLigne() {
		return nombreLigne;
	}

	public void setNombreLigne(Long nombreLigne) {
		this.nombreLigne = nombreLigne;
	}
}
