package aplicacaoSwing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fachada.Fachada;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastroTipo {

	private JFrame frame;
	private JLabel lblTipo;
	private JTextField textField;
	private JButton btnCadastrarTipo;
	private JLabel lblMsg;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroTipo window = new TelaCadastroTipo();
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
	public TelaCadastroTipo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Cadastro");
		frame.setBounds(100, 100, 425, 248);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblTipo = new JLabel("tipo:");
		lblTipo.setBounds(10, 22, 46, 13);
		frame.getContentPane().add(lblTipo);
		
		textField = new JTextField();
		textField.setBounds(52, 19, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnCadastrarTipo = new JButton("Cadastrar Tipo");
		btnCadastrarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty())
						lblMsg.setText("digite o nome");
					else {
						String nome = textField.getText();
						Fachada.cadastrarTipo(nome);
						textField.setText("");
						lblMsg.setText("cadastro concluido");
					}		
				} catch (Exception erro) {
					lblMsg.setText(erro.getMessage());
				}
			}
		});
		btnCadastrarTipo.setBounds(52, 61, 128, 21);
		frame.getContentPane().add(btnCadastrarTipo);
		
		lblMsg = new JLabel("");
		lblMsg.setBounds(10, 122, 354, 13);
		frame.getContentPane().add(lblMsg);
		
		
		
		//mostrar a janela
		frame.setVisible(true);
	}

}
