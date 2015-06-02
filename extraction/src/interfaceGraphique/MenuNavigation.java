package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import Constantes.ConstanteMessagesUtilisateur;
import accesDonnees.RequeteSqlVerifierMigrationDetailsFacturation;
import configuration.GestionConfiguration;
import extraction.ExtracteurCSV;


@SuppressWarnings("serial")
public class MenuNavigation extends JFrame
{
	private String cheminSelectionne;
	
	private ExtracteurCSV ext = new ExtracteurCSV();
	
	private int resolutionH;
	
	private int resolutionL;
	
	private JPanel panneauBarreOutil;
	
	private JPanel panneauPrincipal;
	
	private JPanel panneauPrevisualisation;
	
	private Container contenu;
	
	private JToolBar toolbar;
	
	private JButton boutonExtraction, boutonSauvegarder, boutonChoixSource, boutonChoixDestination, boutonMigration, boutonLire, boutonOptions;
	
	private JTextField champSource;
	
	private JTextField champDestination;
	
	private JLabel etiquetteSource;
	
	private JLabel etiquetteDestination;
	
	private JScrollPane pane;
	
	private EvenementBouton evenement = new EvenementBouton();
	
	private ImageIcon imgOuvrir = new ImageIcon("Ressources/folder-horizontal-open.png");
	
	private ImageIcon imgLire = new ImageIcon("Ressources/book-open-list.png");
	
	private ImageIcon imgExtraire = new ImageIcon("Ressources/eye.png");
	
	private ImageIcon imgSauvegarde = new ImageIcon("Ressources/disk-black.png");
	
	private ImageIcon imgMigration = new ImageIcon("Ressources/database-import.png");
	
	private ImageIcon imgOptions = new ImageIcon("Ressources/screwdriver.png");
	
	
	public MenuNavigation(String titre, boolean affichage)
	{
		
		resolutionH = 800;
		
		resolutionL = 500;
		
		this.setBounds(0, 0, resolutionL, resolutionH);
		
		this.setTitle(titre);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panneauBarreOutil = new JPanel();
		
		panneauPrevisualisation = new JPanel();
		
		panneauPrincipal = new JPanel();
		
		panneauPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
	 
		panneauBarreOutil.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		panneauPrevisualisation.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		 
		toolbar = new JToolBar();
		 
		boutonLire = new JButton(imgLire);
		boutonLire.setEnabled(true);
		
		boutonExtraction = new JButton(imgExtraire);
		boutonExtraction.setEnabled(false);
		
		 
		boutonSauvegarder = new JButton(imgSauvegarde);
		boutonSauvegarder.setEnabled(false);
		
		
		boutonMigration = new JButton(imgMigration);
		boutonMigration.setEnabled(false);
		
		
		boutonOptions = new JButton(imgOptions);
		boutonOptions.setEnabled(true);
		
		boutonChoixSource = new JButton(imgOuvrir);
		boutonChoixDestination = new JButton(imgOuvrir);
		 
		toolbar.add(boutonLire);
		 
		toolbar.add(boutonExtraction);
		 
		toolbar.add(boutonSauvegarder);
		
		toolbar.add(boutonMigration);
		
		toolbar.add(boutonOptions);
		 
		champSource = new JTextField();
		 
		champDestination = new JTextField();
		
		Font police = new Font("Arial", Font.BOLD, 10);
		
		champSource.setFont(police);
		champSource.setPreferredSize(new Dimension(320, 30));
		champSource.setForeground(Color.BLACK);
		champSource.setEditable(false);
		champSource.setHorizontalAlignment(JTextField.CENTER);
		champSource.setText("");
		
		champDestination.setPreferredSize(new Dimension(320, 30));
		champDestination.setForeground(Color.BLACK);
		champDestination.setFont(police);
		champDestination.setEditable(false);
		champDestination.setHorizontalAlignment(JTextField.CENTER);
		champDestination.setText("");
		
		etiquetteSource = new JLabel("Source");
		etiquetteSource.setLabelFor(champSource);
		
		etiquetteDestination = new JLabel("Destination");
		etiquetteDestination.setLabelFor(champDestination);
		
		etiquetteSource.setSize(etiquetteDestination.getSize());
	     
	    pane = new JScrollPane();
	     
	    panneauPrincipal.setBorder(BorderFactory.createEtchedBorder());
	    
	    panneauPrincipal.setPreferredSize (new Dimension(500, 50));
	    
	    panneauPrincipal.add(etiquetteSource, BorderLayout.WEST);
	    panneauPrincipal.add(champSource, BorderLayout.CENTER);
	    panneauPrincipal.add(boutonChoixSource, BorderLayout.EAST);
	    
	    panneauPrincipal.setBorder(BorderFactory.createEtchedBorder());
	    
	    panneauPrevisualisation.setPreferredSize (new Dimension(500, 620));
	    
	    panneauPrincipal.add(etiquetteDestination, BorderLayout.WEST);
	    panneauPrincipal.add(champDestination, BorderLayout.CENTER);
	    panneauPrincipal.add(boutonChoixDestination, BorderLayout.EAST);
	    
	    panneauPrevisualisation.setBorder(BorderFactory.createEtchedBorder());
		
	    panneauBarreOutil.add(toolbar, BorderLayout.NORTH);
	    
	    contenu = this.getContentPane();
	    
	    contenu.add(panneauBarreOutil, BorderLayout.NORTH);
	    
	    contenu.add(panneauPrincipal, BorderLayout.CENTER);
	    
	    this.add(panneauPrevisualisation, BorderLayout.SOUTH);
	     
	    this.setLocationRelativeTo(null);
	     
	    boutonLire.addActionListener(evenement);
	     
	    boutonExtraction.addActionListener(evenement);
	     
	    boutonSauvegarder.addActionListener(evenement);
	    
	    boutonChoixDestination.addActionListener(evenement);
	    
	    boutonChoixSource.addActionListener(evenement);
	    
	    boutonMigration.addActionListener(evenement);
	    
	    boutonOptions.addActionListener(evenement);
	    
	    this.setVisible(affichage);
	     
	}

    public String readFile(File file) 
    {

        StringBuffer fileBuffer = null;
        String fileString = null;
        String line = null;

        try 
        {
            FileReader in = new FileReader(file);
            
            BufferedReader brd = new BufferedReader(in);
            
            fileBuffer = new StringBuffer();

            while ((line = brd.readLine()) != null) 
            {
                fileBuffer.append(line).append(System.getProperty("line.separator"));
            }

            in.close();
            
            fileString = fileBuffer.toString();
            
        } 
        catch (IOException e) 
        {
            return null;
        }
        
        return fileString;
        
    }
    
    public String getCheminSelectionne() 
    {
		return cheminSelectionne;
	}


	public void setCheminSelectionne(String cheminSelectionne) 
	{
		this.cheminSelectionne = cheminSelectionne;
	}

	public class EvenementBouton implements ActionListener
    {

    	@Override
    	public void actionPerformed(ActionEvent event)
    	{
    		Object source = event.getSource();
    		
    		GestionConfiguration configurateur = new GestionConfiguration();
            HashMap<String, String> configuration = configurateur.recupererConfiguration();
    		
    		if(source == boutonLire || source == boutonChoixDestination || source == boutonChoixSource)
    		{
                
                String cheminSource = "";
                String cheminDestination = "";
    			
    			JFileChooser fileopen = new JFileChooser();
    			
    			int codeRetour = 0;
        		
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers csv", "csv");
                
                fileopen.addChoosableFileFilter(filter);
                
                cheminSource = configuration.get("url.fichier_source");
                
                cheminDestination = configuration.get("url.fichier_destination");
                
                if(source == boutonChoixSource)
                {
                	
                	fileopen.setName("Sélectionner le fichier source");
                	
                	if(!cheminSource.equals(""))
                	{
                		fileopen.setCurrentDirectory(new File(cheminSource));
                	}

                	codeRetour = fileopen.showDialog(panneauPrincipal, "Sélectionnner le fichier Source");
                }
                else if(source == boutonChoixDestination)
                {
                	
                	fileopen.setName("Sélectionner le fichier de destination");
                	
                	if(!cheminDestination.equals(""))
                	{
                		fileopen.setCurrentDirectory(new File(cheminDestination));
                	}

                	codeRetour = fileopen.showDialog(panneauPrincipal, "Sélectionner le fichier de destination");
                }
                else if(source == boutonLire)
                {
                	fileopen.setName("Sélectionner le fichier à lire");

                	codeRetour = fileopen.showDialog(panneauPrincipal, "Sélectionner le fichier à lire");
                }

                if (codeRetour == JFileChooser.APPROVE_OPTION) 
                {
                    File file = fileopen.getSelectedFile();
                    
                    setCheminSelectionne(file.getAbsolutePath());
                    
                    if(source == boutonChoixSource)
                    {
                    	
                    	champSource.setText(getCheminSelectionne());
                    	
                    	configuration.put("url.fichier_source", fileopen.getCurrentDirectory().getAbsolutePath().toString());
                    	
                    	ext.setCheminSource(getCheminSelectionne());
                    	
                    	boutonExtraction.setEnabled(true);
                    	
                    	boutonMigration.setEnabled(true);
                    	
                    }
                    else if(source == boutonChoixDestination)
                    {
                    	
                    	champDestination.setText(getCheminSelectionne());
                    	
                    	ext.setCheminDestination(getCheminSelectionne());
                    	
                    	configuration.put("url.fichier_destination", fileopen.getCurrentDirectory().getAbsolutePath().toString());
                    	
                    	if(champSource.getText().length() != 0 && champSource.getText() != null)
                    	{
                    		if(!boutonExtraction.isEnabled())
                    		{
                    			boutonSauvegarder.setEnabled(true);
                    		}
                    	}
                    	
                    }
                    else if(source == boutonLire)
                    {
                    	//String text = readFile(file);
                    	//area.setText(text);
                    }
                }
                
                configurateur.miseAjourConfiguration(configuration);
    		}
    		else if(source == boutonExtraction)
    		{
    			
    			boolean actionPossible = false;
    			
    			try 
    			{
    				
    				actionPossible = ext.Extraction();
    				
    				if(actionPossible)
    				{
    					ext.recupererDonneesCodeUf();
        				
        				if(champDestination.getText().length() != 0 && champDestination.getText() != null)
        				{
        					boutonSauvegarder.setEnabled(true);
        				}
        				
        				boutonExtraction.setEnabled(false);
        				
        				ext.convertirDonneVersObjet();
        				
        				String  title[] = {"Année", "Mois", configuration.get("etiquette_Filtre"), "Type de facturation", "Coût"};
        				
        			    JTable tableau = new JTable(ext.getList(), title);
        			    
        			    pane.getViewport().add(tableau);
        			    
        			    panneauPrevisualisation.add(pane, BorderLayout.CENTER);
        			    
        			    panneauPrevisualisation.revalidate();
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
    			catch (Exception e) 
    			{
    				e.printStackTrace();
    			}
    			
    		}
    		else if(source == boutonSauvegarder)
    		{
    			
    			try 
    			{
    				
					ext.ecriture();
					
					boutonSauvegarder.setEnabled(false);
					
				} 
    			catch (IOException e) 
    			{
					e.printStackTrace();
				}
    			
    		}
    		else if(source == boutonMigration)
    		{
    			boolean migrationDejaFaite = false;
    			boolean extractionFaite = false;
    			
    			RequeteSqlVerifierMigrationDetailsFacturation requeteVerification = new RequeteSqlVerifierMigrationDetailsFacturation (ext.getMoisEnCours(), ext.getExerciceEnCours());
    			
    			try 
    			{
    				
    				migrationDejaFaite = requeteVerification.estDejaFaite();
    				
    				if(!migrationDejaFaite)
    				{
    					
    					String titre = "";
    					
    					String message = "";
    					
    					int typeDeMessage = -1;
    					
    					Object icone = null;
    					
    					extractionFaite = ext.Extraction();
    					
    					if(extractionFaite)
    					{
    						
    						titre = ConstanteMessagesUtilisateur.TITRE_INFO_MIGRATION;
    						message = ConstanteMessagesUtilisateur.MESSAGE_INFO_MIGRATION_SUCCESS;
    						typeDeMessage = JOptionPane.INFORMATION_MESSAGE;
    						
    						ext.preparerMigrationVersBaseDonnees();
        					
        					ext.lancerMigration();
        					
        					ComunicationUtilisateur.afficherMessageUtilisateur(titre, message, typeDeMessage, icone);
        					
    					}
    					else
    					{
    						
    						titre = ConstanteMessagesUtilisateur.TITRE_INFO_CONNEXION;
    						message = ConstanteMessagesUtilisateur.MESSAGE_INFO_CONNEXION_FAIL;
    						typeDeMessage = JOptionPane.WARNING_MESSAGE;
    						
    						ComunicationUtilisateur.afficherMessageUtilisateur(titre, message, typeDeMessage, icone);
    					}
    					
    				}
    				else
    				{
    					String titre = ConstanteMessagesUtilisateur.TITRE_INFO_MIGRATION;
    					
    					String message = ConstanteMessagesUtilisateur.MESSAGE_INFO_MIGRATION_FAIL;
    					
    					int typeDeMessage = JOptionPane.WARNING_MESSAGE;
    					
    					Object icone = null;
    					
    					ComunicationUtilisateur.afficherMessageUtilisateur(titre, message, typeDeMessage, icone);
    				}
					
				} 
    			catch (Exception e) 
    			{
					e.printStackTrace();
				}
    		}
    		else if(source == boutonOptions)
    		{
    			@SuppressWarnings("unused")
				MenuOptions menuOption = new MenuOptions(ext.getListEtiquette());
    		}
    		
    	}
    	
    }
}
