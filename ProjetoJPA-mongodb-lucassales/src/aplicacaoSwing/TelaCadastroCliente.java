package aplicacaoSwing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fachada.Fachada;

public class TelaCadastroCliente {

	private JFrame frame;
	private JLabel label1;
	private JTextField textField;
	private JButton button2;
	private JLabel label2;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastro window = new TelaCadastro();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public TelaCadastroCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Cadastro");
		frame.setBounds(100, 100, 425, 331);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label1 = new JLabel("nome:");
		label1.setBounds(25, 34, 46, 14);
		frame.getContentPane().add(label1);
		
		label2 = new JLabel("");
		label2.setBounds(25, 178, 318, 14);
		frame.getContentPane().add(label2);
		
		textField = new JTextField();
		textField.setBounds(62, 31, 152, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		button2 = new JButton("Cadastrar Cliente");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(textField.getText().isEmpty() || textField_1.getText().isEmpty()) 
						label2.setText("um dos campos esta vazio");
					else {
						String nome = textField.getText();
						String cpf = textField_1.getText();
						Fachada.cadastrarCliente(nome, cpf);
						textField.setText("");
						textField_1.setText("");
						label2.setText("cadastro concluido");
					}
				}
				catch(Exception e) {
					label2.setText(e.getMessage());
				}
			}
		});
		button2.setBounds(62, 119, 145, 23);
		frame.getContentPane().add(button2);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(25, 58, 46, 13);
		frame.getContentPane().add(lblCpf);
		
		textField_1 = new JTextField();
		textField_1.setBounds(62, 55, 152, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		//mostrar a janela
		frame.setVisible(true);
	}
}
