package IHM;

import java.awt.GridLayout;
import java.sql.ResultSet;

import javax.swing.*;

import Controleur.ControleurModificationMatch;
import Controleur.ControleurModificationPaireBD;
import Main.Tournois;
import Main.database;

public class ModifierMatch extends JFrame{
	
	private JComboBox paire1, paire2;
	private int id;
	private String[][] listeequipe;
	private String[] listesimple;
	
	
	public ModifierMatch(int idmatch, String pairenom1, String pairenom2, int tour) throws Exception{
		super("Modifier paire");
		
		ResultSet resultat = database.select("select e1.idequipe, j1.nom, j1.prenom, j2.nom as nom2, j2.prenom as prenom2"
				+ " from joueur j1, joueur j2, equipe e1"
				+ " where e1.idjoueur1 = j1.idjoueur"
				+ " and e1.idjoueur2 = j2.idjoueur"
				+ " and numerotour = "+tour+"");
		
		
		setListeequipe(new String[MainWindows.getListeequipe().getRowCount()][2]);
		setListesimple(new String[MainWindows.getListeequipe().getRowCount()]);
		
		int i = 0;
		while(resultat.next()){
			int y = 0;
			this.getListeequipe()[i][y++] = resultat.getString("idequipe");
			this.getListeequipe()[i][y++] = resultat.getString("nom") +" "+ resultat.getString("prenom") +" & "+resultat.getString("nom2") +" "+ resultat.getString("prenom2");
			this.getListesimple()[i]= this.getListeequipe()[i][1];
			i++;
		}
		
		
		
		this.paire1 = new JComboBox(getListesimple());
		this.paire2 = new JComboBox(getListesimple());
		this.id = idmatch;
		
		this.paire1.setSelectedItem(pairenom1);
		this.paire2.setSelectedItem(pairenom2);
		
		
		JPanel principal = new JPanel(new GridLayout(3, 2));
		
		JLabel numeroequipe = new JLabel("Paire Id : "+this.id);
		JLabel nompaire1 = new JLabel("Paire 1 : ");
		JLabel nompaire2 = new JLabel("Paire 2 : ");
		
		JButton valider = new JButton("Valider");
		valider.addActionListener(new ControleurModificationMatch(paire1, paire2, idmatch, this));
		
		//principal.add(numeroequipe);
		principal.add(nompaire1);
		principal.add(paire1);
		principal.add(nompaire2);
		principal.add(paire2);
		principal.add(valider);
		
		

		this.setContentPane(principal);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		
	}


	public String[] getListesimple() {
		return listesimple;
	}


	public void setListesimple(String[] listesimple) {
		this.listesimple = listesimple;
	}


	public String[][] getListeequipe() {
		return listeequipe;
	}


	public void setListeequipe(String[][] listeequipe) {
		this.listeequipe = listeequipe;
	}


}
