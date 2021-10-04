package ihm.controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDialogClose implements ActionListener {
	private JDialog dialog;
	
	public JDialogClose(JDialog d) {
		dialog = d;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.dispose(); // Fermer le JDialog
	}
}
