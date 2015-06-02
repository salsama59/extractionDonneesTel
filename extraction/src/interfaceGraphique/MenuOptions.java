package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import Constantes.ConstantesApplication;
import accesDonnees.RequeteSqlSelectEtiquetteType;
import configuration.GestionConfiguration;

@SuppressWarnings("serial")
public class MenuOptions extends JFrame
{
	
	private int resolutionH;
	
	private int resolutionL;
	
	private JPanel panneauOptions;
	
	private Container contenu;
	
	private JButton boutonAnnuler, boutonSauvegarder;
	
	private JTextField champAdresseBase;
	
	private JTextField champNomBase;
	
	private JTextField champPortBase;
	
	private JTextField champUtilisateur;
	
	private JTextField champSeparateur;
	
	private JPasswordField champMotDePasse;
	
	private JTextField champCheminSourceDefaut;
	
	private JTextField champCheminDestinationDefaut;
	
	private JLabel etiquetteChampAdresseBase;
	
	private JLabel etiquetteChampPortBase;
	
	private JLabel etiquetteChampNomBase;
	
	private JLabel etiquetteChampUtilisateur;
	
	private JLabel etiquetteChampMotDePasse;
	
	private JLabel etiquetteChampCheminSourceDefaut;
	
	private JLabel etiquetteChampCheminDestinationDefaut;
	
	private JLabel etiquetteChampSeparateur;
	
	private JLabel etiquetteChampMenuDeroulantEtiquettes;
	
	private JComboBox<String> menuDeroulantEtiquettes;
	
	private EvenementBouton evenement = new EvenementBouton();
	
	private HashMap <String, String>listeValeursChamp;
	
	private GestionConfiguration configurationApplication;
	
	@SuppressWarnings("unused")
	private ConstantesApplication constante;
	
	public MenuOptions(List<String> etiquettes)
	{
		constante = new ConstantesApplication();
		contenu = this.getContentPane();
		boutonAnnuler = new JButton("Annuler");
		boutonSauvegarder = new JButton("Sauvegarder");
		panneauOptions = new JPanel();
		
		champAdresseBase = new JTextField();
		champNomBase = new JTextField();
		champPortBase = new JTextField();
		champUtilisateur = new JTextField();
		champMotDePasse = new JPasswordField();
		champCheminSourceDefaut = new JTextField();
		champCheminDestinationDefaut = new JTextField();
		champSeparateur = new JTextField();
		
		etiquetteChampAdresseBase = new JLabel("Adresse du serveur");
		etiquetteChampPortBase = new JLabel("Port");
		etiquetteChampNomBase = new JLabel("Nom de la base");
		etiquetteChampUtilisateur = new JLabel("Utilisateur");
		etiquetteChampMotDePasse = new JLabel("Mot de passe");
		etiquetteChampCheminSourceDefaut = new JLabel("Source");
		etiquetteChampCheminDestinationDefaut = new JLabel("Destination");
		etiquetteChampSeparateur= new JLabel("Separateur");
		etiquetteChampMenuDeroulantEtiquettes= new JLabel("Filtre de données");
		
		this.configurationApplication = new GestionConfiguration();
		
		this.initialiser(etiquettes);
	}
	
	private void initialiser(List<String> etiquettes)
	{
		
		majComboBox(etiquettes);
		
		resolutionH = 600;
		
		resolutionL = 300;
		
		this.setBounds(0, 0, resolutionL, resolutionH);
		
		this.setTitle("Options");
		
		//Changer par la fermeture de fenetre simple et pas l'appli entière
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panneauOptions.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		
		Font police = new Font("Arial", Font.BOLD, 12);
		
		champAdresseBase.setFont(police);
		champAdresseBase.setPreferredSize(new Dimension(200, 25));
		champAdresseBase.setForeground(Color.BLACK);
		champAdresseBase.setEditable(true);
		champAdresseBase.setHorizontalAlignment(JTextField.CENTER);
		champAdresseBase.setText("");
		champAdresseBase.setName("champAdresseBase");
		
		champPortBase.setFont(police);
		champPortBase.setPreferredSize(new Dimension(40, 25));
		champPortBase.setForeground(Color.BLACK);
		champPortBase.setEditable(true);
		champPortBase.setHorizontalAlignment(JTextField.CENTER);
		champPortBase.setText("");
		champPortBase.setName("champPortBase");
		
		champUtilisateur.setFont(police);
		champUtilisateur.setPreferredSize(new Dimension(320, 25));
		champUtilisateur.setForeground(Color.BLACK);
		champUtilisateur.setEditable(true);
		champUtilisateur.setHorizontalAlignment(JTextField.CENTER);
		champUtilisateur.setText("");
		champUtilisateur.setName("champUtilisateur");
		
		champMotDePasse.setFont(police);
		champMotDePasse.setPreferredSize(new Dimension(320, 25));
		champMotDePasse.setForeground(Color.BLACK);
		champMotDePasse.setEditable(true);
		champMotDePasse.setHorizontalAlignment(JTextField.CENTER);
		champMotDePasse.setText("");
		champMotDePasse.setName("champMotDePasse");
		
		champNomBase.setFont(police);
		champNomBase.setPreferredSize(new Dimension(320, 25));
		champNomBase.setForeground(Color.BLACK);
		champNomBase.setEditable(true);
		champNomBase.setHorizontalAlignment(JTextField.CENTER);
		champNomBase.setText("");
		champNomBase.setName("champNomBase");
		
		champCheminSourceDefaut.setFont(police);
		champCheminSourceDefaut.setPreferredSize(new Dimension(320, 25));
		champCheminSourceDefaut.setForeground(Color.BLACK);
		champCheminSourceDefaut.setEditable(true);
		champCheminSourceDefaut.setHorizontalAlignment(JTextField.CENTER);
		champCheminSourceDefaut.setText("");
		champCheminSourceDefaut.setName("champCheminSourceDefaut");
		
		champCheminDestinationDefaut.setFont(police);
		champCheminDestinationDefaut.setPreferredSize(new Dimension(320, 25));
		champCheminDestinationDefaut.setForeground(Color.BLACK);
		champCheminDestinationDefaut.setEditable(true);
		champCheminDestinationDefaut.setHorizontalAlignment(JTextField.CENTER);
		champCheminDestinationDefaut.setText("");
		champCheminDestinationDefaut.setName("champCheminDestinationDefaut");
		
		champSeparateur.setFont(police);
		champSeparateur.setPreferredSize(new Dimension(40, 25));
		champSeparateur.setForeground(Color.BLACK);
		champSeparateur.setEditable(true);
		champSeparateur.setHorizontalAlignment(JTextField.CENTER);
		champSeparateur.setText("");
		champSeparateur.setName("champSeparateur");
		
		etiquetteChampAdresseBase.setLabelFor(champAdresseBase);
		etiquetteChampPortBase.setLabelFor(champPortBase);
		etiquetteChampNomBase.setLabelFor(champNomBase);
		etiquetteChampUtilisateur.setLabelFor(champUtilisateur);
		etiquetteChampMotDePasse.setLabelFor(champMotDePasse);
		etiquetteChampCheminDestinationDefaut.setLabelFor(champCheminDestinationDefaut);
		etiquetteChampCheminSourceDefaut.setLabelFor(champCheminSourceDefaut);
		etiquetteChampSeparateur.setLabelFor(champSeparateur);
		etiquetteChampMenuDeroulantEtiquettes.setLabelFor(menuDeroulantEtiquettes);
		
		
		this.setLocationRelativeTo(null);
		
		GridLayout miseEnforme = new GridLayout(15, 1, 3, 3);
		panneauOptions.setLayout(miseEnforme);
		panneauOptions.applyComponentOrientation( ComponentOrientation.LEFT_TO_RIGHT); 
		
		panneauOptions.setBorder(BorderFactory.createEtchedBorder());
	    
		panneauOptions.setPreferredSize (new Dimension(950, 450));
		
		panneauOptions.add(etiquetteChampAdresseBase);
		panneauOptions.add(champAdresseBase);
		
		panneauOptions.add(etiquetteChampPortBase);
		panneauOptions.add(champPortBase);
		
		panneauOptions.add(etiquetteChampUtilisateur);
		panneauOptions.add(champUtilisateur);
		
		panneauOptions.add(etiquetteChampNomBase);
		panneauOptions.add(champNomBase);
		
		panneauOptions.add(etiquetteChampMotDePasse);
		panneauOptions.add(champMotDePasse);
		
		panneauOptions.add(etiquetteChampCheminSourceDefaut);
		panneauOptions.add(champCheminSourceDefaut);
		
		panneauOptions.add(etiquetteChampCheminDestinationDefaut);
		panneauOptions.add(champCheminDestinationDefaut);
		
		panneauOptions.add(etiquetteChampMenuDeroulantEtiquettes);
		panneauOptions.add(menuDeroulantEtiquettes);
		
		panneauOptions.add(etiquetteChampSeparateur);
		panneauOptions.add(champSeparateur);
		
		boutonSauvegarder.addActionListener(evenement);
		boutonAnnuler.addActionListener(evenement);
		
		panneauOptions.add(boutonSauvegarder, BorderLayout.SOUTH);
		panneauOptions.add(boutonAnnuler, BorderLayout.SOUTH);
		
		contenu.add(panneauOptions);
		
		initialiserChamps();
		
		
		this.setVisible(true);
	}
	
	private void majComboBox(List<String> etiquettes)
	{
		
		final DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		if(etiquettes == null)
		{
			
			if(ConstantesApplication.isConnexionValide())
			{
				
				RequeteSqlSelectEtiquetteType requeteEtiquette = new RequeteSqlSelectEtiquetteType();
				
				try 
				{
					
					etiquettes = requeteEtiquette.recupererEtiquettes();
					
				} 
				catch (SQLException e) 
				{
					
					e.printStackTrace();
					
				}
				
				for(String etiquette : etiquettes)
				{
					model.addElement(etiquette);
				}
				
			}
			else
			{
				model.addElement(configurationApplication.recupererConfiguration().get("etiquette_Filtre"));
			}
		}
		else
		{
			for(String etiquette : etiquettes)
			{
				model.addElement(etiquette);
			}
		}
		
		
		menuDeroulantEtiquettes = new JComboBox<String>(model);
		
		menuDeroulantEtiquettes.setName("menuDeroulantEtiquettes");
		
	}
	
	//Voir comment gérer les combo box
	@SuppressWarnings("unchecked")
	private void recupererValeurChamps()
	{
		listeValeursChamp = new HashMap <String, String>();
		
		int nb = panneauOptions.getComponentCount();
		
		Component[] listeComposant = panneauOptions.getComponents();
		
		String cle = "";
		String typeComposant;
		String valeur;
		
		for(int i = 0; i < nb; i++)
		{
			Component composant = listeComposant[i];
			
			typeComposant = composant.getClass().toString();
					
			if(typeComposant.contains("JTextField") || typeComposant.contains("JPasswordField") || typeComposant.contains("JComboBox"))
			{
				
				String nomComposant = composant.getName();
				
				if(typeComposant.contains("JTextField"))
				{
					valeur = ((JTextField) composant).getText();
				}
				else if((typeComposant.contains("JComboBox")))
				{
					valeur = ((JComboBox<String>) composant).getSelectedItem().toString();
				}
				else
				{
					valeur = recupererMotDePasse(((JPasswordField) composant).getPassword());
				}
				
				switch(nomComposant)
				{
					
					case "champAdresseBase":
						
					cle = "base_donnees.adresse";
					
					break;
					
					case "champPortBase":
						
					cle = "base_donnees.port";
						
					break;
					
					case "champNomBase":
						
					cle = "base_donnees.nom";
						
					break;
					
					case "champUtilisateur":
						
					cle = "base_donnees.utilisateur";
						
					break;
					
					case "champMotDePasse":
						
					cle = "base_donnees.mot_de_passe";
						
					break;
					
					case "champCheminSourceDefaut":
						
					cle = "url.fichier_source";
						
					break;
					
					case "champCheminDestinationDefaut":
						
					cle = "url.fichier_destination";
						
					break;
					
					case "champSeparateur":
						
					cle = "type_separateur";
							
					break;
					
					case "menuDeroulantEtiquettes":
						
					cle = "etiquette_Filtre";
								
					break;
					
					default:
					break;
					
				}
				
				listeValeursChamp.put(cle, valeur);
			}
		}
	}
	
	//Voir comment gérer les combo box
	@SuppressWarnings("unchecked")
	private void initialiserChamps()
	{
		
		boolean estMenuDeroulant = false;
		
		Component[] listeComposant = panneauOptions.getComponents();
		
		String cle = "";
		String typeComposant;
		
		int nb = panneauOptions.getComponentCount();
		
		HashMap<String, String> valeursConfiguration = configurationApplication.recupererConfiguration();
		
		for(int i = 0; i < nb; i++)
		{
			
			Component composant = listeComposant[i];
			
			typeComposant = composant.getClass().toString();
					
			if(typeComposant.contains("JTextField") || typeComposant.contains("JPasswordField") || typeComposant.contains("JComboBox"))
			{
				
				String nomComposant = composant.getName();
				
				switch(nomComposant)
				{
					
					case "champAdresseBase":
						
					cle = "base_donnees.adresse";
					
					break;
					
					case "champPortBase":
						
					cle = "base_donnees.port";
						
					break;
					
					case "champNomBase":
						
					cle = "base_donnees.nom";
						
					break;
					
					case "champUtilisateur":
						
					cle = "base_donnees.utilisateur";
						
					break;
					
					case "champMotDePasse":
						
					cle = "base_donnees.mot_de_passe";
						
					break;
					
					case "champCheminSourceDefaut":
						
					cle = "url.fichier_source";
						
					break;
					
					case "champCheminDestinationDefaut":
						
					cle = "url.fichier_destination";
						
					break;
					
					case "champSeparateur":
						
					cle = "type_separateur";
							
					break;
					
					case "menuDeroulantEtiquettes":
						
					cle = "etiquette_Filtre";
					
					estMenuDeroulant = true;
					
					break;
					
					default:
					break;
					
				}
				
				if(!estMenuDeroulant)
				{
					((JTextComponent) composant).setText(valeursConfiguration.get(cle));
				}
				else
				{
					((JComboBox<String>) composant).setSelectedItem(valeursConfiguration.get(cle));
					
					estMenuDeroulant = false;
				}
				
			}
		}
	}
	
	private String recupererMotDePasse(char[] tableauSecret)
	{
		String mdp = "";
		
		for (char caracteres : tableauSecret)
		{
			mdp = mdp + caracteres;
		}
		
		return mdp;
		
	}
	
	public class EvenementBouton implements ActionListener
    {

    	@Override
    	public void actionPerformed(ActionEvent event)
    	{
    		
    		Object source = event.getSource();
    		
    		if(source == boutonAnnuler)
    		{
    			dispose();
    		}
    		else if(source == boutonSauvegarder)
    		{
    			recupererValeurChamps();
    			
    			configurationApplication.miseAjourConfiguration(listeValeursChamp);
    			
    		}
    		
    	}
    	
    }
}
