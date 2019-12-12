package aplicacaoSwing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import fachada.Fachada;

public class TelaCadastroConta {

	private JFrame frame;
	private JLabel lblNome;
	private JTextField textField;
	private JButton btnCadastrarConta;
	private JLabel lblMsg;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroConta window = new TelaCadastroConta();
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
	public TelaCadastroConta() {
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
		
		lblNome = new JLabel("nome:");
		lblNome.setBounds(10, 20, 46, 13);
		frame.getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(56, 17, 166, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnCadastrarConta = new JButton("Cadastrar Conta");
		btnCadastrarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty())
						lblMsg.setText("digite seu nome");
					else {
						String nome = textField.getText();
						Fachada.cadastrarConta(nome);
						textField.setText("");
						lblMsg.setText("cadastro concluido");
					}
					
				} catch (Exception erro) {
					lblMsg.setText(erro.getMessage());
				}
			}
		});
		btnCadastrarConta.setBounds(56, 64, 166, 21);
		frame.getContentPane().add(btnCadastrarConta);
		
		lblMsg = new JLabel("");
		lblMsg.setBounds(10, 139, 375, 13);
		frame.getContentPane().add(lblMsg);
		
		
		
		
		//mostrar a janela
		frame.setVisible(true);
	}
}
