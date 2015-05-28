package extraction;

public class DonneesExtraites 
{
	private String anne;
	
	private String mois;
	
	private String code;
	
	private String type;
	
	private String cout;
	
	private String nom;
	
	private String nbAppels;
	
	private  String duree;
	
	public DonneesExtraites(String nom, String code, String nbAppels, String prix, String duree, String typeDonne, String exercice, String mois)
	{
		this.anne = exercice;
		
		this.mois = mois;
		
		this.code = code;
		
		this.type = typeDonne;
		
		this.cout = prix;
		
		this.nbAppels = nbAppels;
		
		this.duree = duree;
		
		this.nom = nom;
	}
	
	public DonneesExtraites()
	{
	}

	public String getAnne() {
		return anne;
	}

	public void setAnne(String anne) {
		this.anne = anne;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCout() {
		return cout;
	}

	public void setCout(String cout) {
		this.cout = cout;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNbAppels() {
		return nbAppels;
	}

	public void setNbAppels(String nbAppels) {
		this.nbAppels = nbAppels;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}
}
