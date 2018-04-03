import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Update_Profile extends JFrame {
	public Update_Profile() throws ClassNotFoundException {
		try {
			ConnectionHandler.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTitle("Hair with a Flair. Your Client-Based Management System | Update Client Profile");

		JPanel panel_update_client_profile = new JPanel();
		Box vertical_box = Box.createVerticalBox();

		Box first_horizontal_box = Box.createHorizontalBox();
		Box last_horizontal_box = Box.createHorizontalBox();
		Box phone_horizontal_box = Box.createHorizontalBox();
		Box address_horizontal_box = Box.createHorizontalBox();
		Box email_horizontal_box = Box.createHorizontalBox();
		Box buttons_horizontal_box = Box.createHorizontalBox();

		Font font = new Font("Bookman Old Style", Font.PLAIN, 20);
		JLabel first_name_prompt = new JLabel("First Name: ");
		first_name_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		first_name_prompt.setForeground(Color.white);
		JTextField first_name_input = new JTextField(20);
		first_name_input.setFont(font);
		first_name_input.setText(View_List_Of_Clients.get_first_name());
		JLabel last_name_prompt = new JLabel("Last Name: ");
		last_name_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		last_name_prompt.setForeground(Color.white);
		JTextField last_name_input = new JTextField(20);
		last_name_input.setFont(font);
		last_name_input.setText(View_List_Of_Clients.get_last_name());
		JLabel phone_number_prompt = new JLabel("Phone Number: ");
		phone_number_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		phone_number_prompt.setForeground(Color.white);
		JTextField phone_number_input = new JTextField(20);
		phone_number_input.setFont(font);
		phone_number_input.setText(ConnectionHandler.select_info("clients", "Phone"));
		JLabel address_prompt = new JLabel("Address: ");
		address_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		address_prompt.setForeground(Color.white);
		JTextField address_input = new JTextField(20);
		address_input.setFont(font);
		address_input.setText(ConnectionHandler.select_info("clients", "Address"));
		JLabel email_prompt = new JLabel("Email: ");
		email_prompt.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
		email_prompt.setForeground(Color.white);
		JTextField email_input = new JTextField(20);
		email_input.setFont(font);
		email_input.setText(ConnectionHandler.select_info("clients", "Email"));

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;

		JButton save_profile = new JButton("SAVE");
		save_profile.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		JButton cancel = new JButton("CANCEL");
		cancel.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

		first_horizontal_box.add(first_name_prompt);
		first_horizontal_box.add(first_name_input);
		last_horizontal_box.add(last_name_prompt);
		last_horizontal_box.add(last_name_input);
		phone_horizontal_box.add(phone_number_prompt);
		phone_horizontal_box.add(phone_number_input);
		address_horizontal_box.add(address_prompt);
		address_horizontal_box.add(address_input);
		email_horizontal_box.add(email_prompt);
		email_horizontal_box.add(email_input);
		buttons_horizontal_box.add(save_profile);
		buttons_horizontal_box.add(Box.createHorizontalStrut(20));
		buttons_horizontal_box.add(cancel);

		vertical_box.add(first_horizontal_box);
		vertical_box.add(Box.createVerticalStrut(20));
		vertical_box.add(last_horizontal_box);
		vertical_box.add(Box.createVerticalStrut(20));
		vertical_box.add(phone_horizontal_box);
		vertical_box.add(Box.createVerticalStrut(20));
		vertical_box.add(address_horizontal_box);
		vertical_box.add(Box.createVerticalStrut(20));
		vertical_box.add(email_horizontal_box);
		vertical_box.add(Box.createVerticalStrut(20));
		vertical_box.add(buttons_horizontal_box);

		panel_update_client_profile.add(vertical_box);

		save_profile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel label = new JLabel("Update this client's profile?");
				JLabel empty = new JLabel("All fields must be filled.");
				label.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
				empty.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));

				int confirm = JOptionPane.showConfirmDialog(null, label, "Save?", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					try {
						String first_name = first_name_input.getText().toUpperCase();
						String last_name = last_name_input.getText().toUpperCase();
						String phone_number = phone_number_input.getText().toUpperCase();
						String address = address_input.getText().toUpperCase();
						String email = email_input.getText().toUpperCase();
						if (first_name.equals("") || last_name.equals("") || phone_number.equals("")
								|| address.equals("") || email.equals("")) {
							JOptionPane.showMessageDialog(null, empty, "Empty Field(s)", JOptionPane.PLAIN_MESSAGE);

						} else {
							// verify that there isn't a person with that name
							// already and address already
							ConnectionHandler.update_clients_profile(first_name, last_name, phone_number, address,
									email);

							Home_Page.go_to_view_list_of_clients = new View_List_Of_Clients();
							Home_Page.go_to_view_list_of_clients.setVisible(true);
							Home_Page.update_profile.dispose();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
				}
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel label = new JLabel("Cancel Client Profile Update?");
				label.setFont(new Font("Bookman Old Style", Font.PLAIN, 20));
				int confirm = JOptionPane.showConfirmDialog(null, label, "Cancel?", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					Home_Page.go_to_new_client_profile.dispose();
				} else {
				}
			}
		});

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) screenSize.getHeight() - 100;
		int width = (int) screenSize.getWidth() - 100;

		add(panel_update_client_profile);
		panel_update_client_profile.setBackground(Color.decode("#660033"));
		getContentPane().setBackground(Color.decode("#660033"));
		pack();
		setSize(width - 1000, height - 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

}