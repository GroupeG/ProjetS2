package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import IHM.IHMExport;
import Main.database;

public class ControleurImport implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		IHMExport impihm = new IHMExport();
		database.importt(impihm);
	}

}
