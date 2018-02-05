import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectClientForNewVisit extends JFrame {

	public SelectClientForNewVisit() {
		setTitle("Hair with a Flair. Your Client-Based Management System.");

		JPanel panel_select_client_for_new_visit = new JPanel();
		
		// ************************************************************************************//
		// allow user to select user from list of clients
		//the click saves the database reference and carries it to the CreateNewVisit page
		
		add(panel_select_client_for_new_visit);
		pack();
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		JFrame SelectClientForNewVisit = new HomePage();
		SelectClientForNewVisit.setVisible(true);
	}

}
