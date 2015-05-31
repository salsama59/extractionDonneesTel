package extraction;

import interfaceGraphique.ComunicationUtilisateur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import Constantes.ConstanteMessagesUtilisateur;
import Constantes.ConstantesApplication;
import accesDonnees.RequeteSqlInsertDetailsFacturation;
import accesDonnees.RequeteSqlSelectEtiquetteType;
import configuration.GestionConfiguration;
import enumerations.EtiquetteDonnees;
import enumerations.Mois;
 
public class ExtracteurCSV extends ConstantesApplication
{
	
	private String cheminSource;
	private String cheminDestination;
	private String exerciceEnCours;
	private String moisEnCours;
	private FileReader fichierSource;
	private FileWriter fichierDestination;
	private List<String> donnees = new ArrayList<String>();
	private List<String> donneesCodeUf;
	private Utilitaires util = new Utilitaires();
	private HashMap<String, Integer> listePosition = new HashMap<String, Integer>();
	private Object[][] list = new Object[5][5];
	private List<String> listEtiquette;
	private List<DonneesExtraites> tableauMigration = new ArrayList<DonneesExtraites>();
	private GestionConfiguration configurationApplication;
	
	public ExtracteurCSV ()
	{
		
		super();
		
		configurationApplication = new GestionConfiguration();
		
		System.setProperty( "file.encoding", "UTF-8" );
		
		if(ConstantesApplication.isConnexionValide())
		{
			RequeteSqlSelectEtiquetteType requeteEtiquette = new RequeteSqlSelectEtiquetteType();
			
			try 
			{
				listEtiquette = requeteEtiquette.recupererEtiquettes();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			
			String titre = ConstanteMessagesUtilisateur.TITRE_INFO_CONNEXION;
			
			String message = ConstanteMessagesUtilisateur.MESSAGE_INFO_CONNEXION_FAIL;
			
			int typeDeMessage = JOptionPane.WARNING_MESSAGE;
			
			Object icone = null;
			
			ComunicationUtilisateur.afficherMessageUtilisateur(titre, message, typeDeMessage, icone);
		}
		
	}
	
	public void ExtractionMoisAnnee()
	{
		
		String chaineCible = null;
		
		String titre = new File(cheminSource).getName();
		
		titre = (titre != null) ? titre.substring(0, titre.indexOf('.')) : "";
		
		chaineCible = titre.substring(0, titre.length() - 5);
		
		this.exerciceEnCours = chaineCible.substring(chaineCible.length() - 4, chaineCible.length());
		
		this.moisEnCours = chaineCible.substring(0, chaineCible.length() - this.exerciceEnCours.length());
		
	}
	
	public boolean Extraction() throws Exception
	{
		
		if(listEtiquette == null)
		{
			if(ConstantesApplication.isConnexionValide())
			{
				
				RequeteSqlSelectEtiquetteType requeteEtiquette = new RequeteSqlSelectEtiquetteType();
				
				try 
				{
					listEtiquette = requeteEtiquette.recupererEtiquettes();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
					
					return false;
				}
				
			}
			else
			{
				return false;
			}
			
		}
		
		try 
		{
			fichierSource = new FileReader(this.cheminSource);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(this.fichierSource);
		
		
		String ligne = null;
		
		while ((ligne = br.readLine()) != null)
		{
			donnees.add(ligne);
		}
		
		br.close();
		
		return true;
		
	}
	
	public void preparerMigrationVersBaseDonnees()
	{
		
		String ligne = "";
		
		String [] etiquettesSpeciales;
		
		String [] tableauEntreeBase;
		
		String typeEtiquette = null;
		
		ExtractionMoisAnnee();
		
		for (Iterator<String> it = donnees.iterator(); it.hasNext();)
		{
			
			if(!contientLabel(ligne))
			{
				ligne = it.next();
			}
			else
			{
				typeEtiquette = recupererTypeDonnees(ligne);
				
				etiquettesSpeciales = ligne.split(";");
				
				ligne = it.next();
				
				while(!contientLabel(ligne))
				{
					
					DonneesExtraites donneBase = new DonneesExtraites();
					
					tableauEntreeBase = ligne.split(";");
					
					for(int i = 0; i < tableauEntreeBase.length; i++)
					{
						
						if(typeEtiquette.equals("C. Gestion (Code)"))
						{
							
							switch(etiquettesSpeciales[i])
							{
								
								case "C. Gestion (Code)":
								donneBase.setCode(tableauEntreeBase[i]);
								break;
								
								case "C. Gestion":
								donneBase.setNom(tableauEntreeBase[i]);
								break;
								
								case "Nombre d'appels":
								donneBase.setNbAppels(tableauEntreeBase[i]);
								break;
								
								case "Coûts T.T.C. en €":
								donneBase.setCout(tableauEntreeBase[i]);
								break;
								
								case "Durées de conversation":
								donneBase.setDuree(tableauEntreeBase[i]);
								break;
								
								default:
								break;
								
							}
						}
						else
						{
							
							switch(EtiquetteDonnees.getNom(i))
							{
							
								case "NOM":
								donneBase.setNom(tableauEntreeBase[i]);
								break;
								
								case "CODE":
								donneBase.setCode(tableauEntreeBase[i]);
								break;
								
								case "NBAPPELS":
								donneBase.setNbAppels(tableauEntreeBase[i]);
								break;
								
								case "COUT":
								donneBase.setCout(tableauEntreeBase[i]);
								break;
								
								case "DUREE":
								donneBase.setDuree(tableauEntreeBase[i]);
								break;
								
								default:
								break;
								
							}
						}
					}
					
					donneBase.setAnne(this.exerciceEnCours);
					donneBase.setMois(this.moisEnCours);
					donneBase.setType(typeEtiquette);
					tableauMigration.add(donneBase);
					
					if(it.hasNext())
					{
						ligne = it.next();
					}
					else
					{
						break;
					}
				}
			}
		}	
	}
	
	public void lancerMigration()
	{
		RequeteSqlInsertDetailsFacturation requeteFacturation = new RequeteSqlInsertDetailsFacturation(tableauMigration, listEtiquette);
		
		try 
		{
			requeteFacturation.executer();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void convertirDonneVersObjet()
	{
		
		String [] tab;
		
		int nbLignes, nbColonnes;
		
		nbLignes = donneesCodeUf.size();
		
		nbColonnes = donneesCodeUf.get(0).split(";").length;
		
		list = new Object[nbLignes][nbColonnes];
		
		int i = 0;
		
		for(String ligne : donneesCodeUf)
		{
			
			tab = ligne.split(";");
			list[i] = tab;
			i++;
			
		}
	}
	
	public List<String> recupererDonneesCodeUf()
	{
		String ligne = "";
		
		String [] listValeurs;
		
		donneesCodeUf = new ArrayList<String>();
		
		boolean positionRecuperees = false;
		
		String filtre = configurationApplication.recupererConfiguration().get("etiquette_Filtre");
		
		for (Iterator<String> it = donnees.iterator(); it.hasNext();)
		{
			
			if(!ligne.contains(filtre))
			{
				ligne = it.next();
			}
			
			if(ligne.contains(filtre))
			{
				
				if(!positionRecuperees)
				{
					listValeurs = ligne.split(";");
					listePosition.put(filtre, (int) util.recupererPositionValeur(filtre, listValeurs));
					listePosition.put("Coûts T.T.C. €", (int) util.recupererPositionValeur("Coûts T.T.C. €", listValeurs));
					positionRecuperees = true;
				}
				
				ligne = it.next();
				
				while(!contientLabel(ligne))
				{
					
					donneesCodeUf.add(creerLigneDTLtelephone(ligne));
					
					if(it.hasNext())
					{
						ligne = it.next();
					}
					else
					{
						break;
					}
					
				}
				
			}
			
		}
		
		return donneesCodeUf;
	}
	
	public String creerLigneDTLtelephone(String ligneFichier)
	{
		
		//Format du mois = "00";
		//Format du code uf= "0000";
		//Format du montant= "0.00";
		
		ExtractionMoisAnnee();
		
		HashMap<String, String> configuration = configurationApplication.recupererConfiguration();
		
		String separateur = configuration.get("type_separateur");
		
		String filtre = configuration.get("etiquette_Filtre");
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		String nouvelleLigne = "";
		
		String [] tableauDonnees;
		
		int positionColonne = 0;
		
		String annee = this.getExerciceEnCours();
		
		String mois = conversionMoisEnchaine(this.getMoisEnCours());
		
		String codeUf = "";
		
		String type = "Téléphone";
		
		String montant = "";
		
		tableauDonnees = ligneFichier.split(separateur);
		
		positionColonne = listePosition.get(filtre);
		
		codeUf = codeUf + tableauDonnees[positionColonne];
		
		positionColonne = listePosition.get("Coûts T.T.C. €");
		
		montant = montant + tableauDonnees[positionColonne];
		
		montant = montant.replace("€", "");
		
		montant = montant.replace(",", ".");
		
		montant = df.format(Float.parseFloat(montant));
		
		montant = montant.replace(",", ".");
		
		return nouvelleLigne + annee + separateur + mois + separateur + codeUf + separateur + type + separateur + montant;
		
	}
	
	private String conversionMoisEnchaine(String mois)
	{
		
		int chiffreMois = Mois.getNumeroMois(mois) + 1;
		
		String chaineMois = "";
		
		chaineMois = chaineMois + chiffreMois;
		
		int tailleChaine = chaineMois.length();
		
		if(tailleChaine <= 1)
		{
			chaineMois = "0" + chaineMois;
		}
		
		return chaineMois;
		
	}
	
	public boolean contientLabel(String ligneFichier)
	{
		boolean contient = false;
		
		String typeDonnees = null;
		
		for (int i = 0; i < ((ArrayList<String>) listEtiquette).size(); i++)
		{
			typeDonnees = ((ArrayList<String>) listEtiquette).get(i);
					
			if(ligneFichier.contains(typeDonnees))
			{
				contient = true;
			}
		}
		
		return contient;
	}
	
	public String recupererTypeDonnees(String ligne)
	{
		int position = -1;
		
		String typeDonnees = null;
		
		for (int i = 0; i < ((ArrayList<String>) listEtiquette).size(); i++)
		{
			typeDonnees = ((ArrayList<String>) listEtiquette).get(i);
					
			if(ligne.contains(typeDonnees))
			{
				position = i;
				break;
			}
		}
		
		return typeDonnees;
	}
	
	public void ecriture() throws IOException
	{
		
		String ligne;
		
		try 
		{
			fichierDestination = new FileWriter(this.cheminDestination, true);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		BufferedWriter bw = new BufferedWriter(this.fichierDestination);
		
		bw.newLine();
		
		for (Iterator<String> it = donneesCodeUf.iterator(); it.hasNext();)
		{
			ligne = it.next();
			
			bw.write(ligne);
			
			if(it.hasNext())
			{
				bw.newLine();
			}
		}
		
		bw.close();
		
	}

	public String getCheminSource() 
	{
		return cheminSource;
	}

	public void setCheminSource(String cheminSource) 
	{
		this.cheminSource = cheminSource;
	}

	public String getCheminDestination() 
	{
		return cheminDestination;
	}

	public void setCheminDestination(String cheminDestination) 
	{
		this.cheminDestination = cheminDestination;
	}

	public FileReader getFichierSource() 
	{
		return fichierSource;
	}

	public void setFichierSource(FileReader fichierSource) 
	{
		this.fichierSource = fichierSource;
	}

	public FileWriter getFichierDestination() 
	{
		return fichierDestination;
	}

	public void setFichierDestination(FileWriter fichierDestination) 
	{
		this.fichierDestination = fichierDestination;
	}

	public Object[][] getList() {
		return list;
	}

	public void setList(Object[][] list) {
		this.list = list;
	}

	public List<String> getListEtiquette() {
		return listEtiquette;
	}

	public void setListEtiquette(List<String> listEtiquette) {
		this.listEtiquette = listEtiquette;
	}

	public String getExerciceEnCours() {
		return exerciceEnCours;
	}

	public void setExerciceEnCours(String exerciceEnCours) {
		this.exerciceEnCours = exerciceEnCours;
	}

	public String getMoisEnCours() {
		return moisEnCours;
	}

	public void setMoisEnCours(String moisEnCours) {
		this.moisEnCours = moisEnCours;
	}
}