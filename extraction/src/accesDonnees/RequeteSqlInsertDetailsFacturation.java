package accesDonnees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.text.DecimalFormat;

import enumerations.Mois;
import extraction.DonneesExtraites;

public class RequeteSqlInsertDetailsFacturation 
{
	
	private Connection connection;
	private int type;
	private String code;
	private String nom;
	private int nombreAppels;
	private String duree;
	private float cout;
	private Date date;
	private List<DonneesExtraites> listDonnes;
	private List<String> etiquettes;	
	
	public RequeteSqlInsertDetailsFacturation(List<DonneesExtraites> donnes, List<String> liste)
	{
		
		etiquettes = liste;
		
		DetailTelephone base = new DetailTelephone();
		
		this.setConnection(base.getConnection());
		
		this.setListDonnes(donnes);
		
	}
	
	public String creerRequete()
	{
		
		StringBuilder requete = new StringBuilder("");
		
		requete.append("INSERT INTO ");
		
		requete.append("tbl_detail_facturation ");
		
		requete.append("(df_id, df_type, df_code, df_nom, df_cout_ttc, df_nombre_appels, df_duree_appels, df_date)");
		
		requete.append("VALUES (nextval('tbl_detail_Facturation_df_id_seq'), ?, ?, ?, ?, ?, ?, ?)");
		
		return requete.toString();
		
	}
	
	private void formatageDonnes(DonneesExtraites donne)
	{
		
		String montantFormate = donne.getCout();
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		montantFormate = montantFormate.replace("€", "");
		
		montantFormate = montantFormate.replace(",", ".");
		
		montantFormate = df.format(Float.parseFloat(montantFormate));
		
		montantFormate = montantFormate.replace(",", ".");
		
		gererDate(donne.getAnne(), donne.getMois());
		
		this.setCout(Float.parseFloat(montantFormate));
		
		this.setType(etiquettes.indexOf(donne.getType()) + 1);
		
		this.setCode(donne.getCode());
		
		this.setDuree(donne.getDuree());
		
		
		
		this.setNom(donne.getNom());
		
		this.setNombreAppels(Integer.parseInt(donne.getNbAppels()));
		
		this.setCout(cout);
		
	}
	
	private void gererDate(String annee, String mois)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(annee));
		calendar.set(Calendar.DAY_OF_MONTH, 27);
		calendar.set(Calendar.MONTH, Mois.getNumeroMois(mois));

		Date dateFormate = new Date(calendar.getTime().getTime());
		
		this.setDate(dateFormate);
		
	}
	
	public int[] executer() throws SQLException
	{
		
		PreparedStatement prep = connection.prepareStatement(creerRequete());
		
		for(Iterator<DonneesExtraites> donne = listDonnes.iterator(); donne.hasNext();)
		{
			
			formatageDonnes(donne.next());
			
			prep.setInt(1, getType());
			
			prep.setString(2, getCode());
			
			prep.setString(3, getNom());
			
			prep.setFloat(4, getCout());
			
			prep.setInt(5, getNombreAppels());
			
			prep.setString(6, getDuree());
			
			prep.setDate(7, getDate());
			
			prep.addBatch();
			
		}
		
		int[] resultats = prep.executeBatch();
		
		prep.close();
		
		return resultats;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNombreAppels() {
		return nombreAppels;
	}

	public void setNombreAppels(int nombreAppels) {
		this.nombreAppels = nombreAppels;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return this.date;
	}

	public float getCout() {
		return cout;
	}

	public void setCout(float cout) {
		this.cout = cout;
	}

	public List<DonneesExtraites> getListDonnes() {
		return listDonnes;
	}

	public void setListDonnes(List<DonneesExtraites> listDonnes) {
		this.listDonnes = listDonnes;
	}
}
