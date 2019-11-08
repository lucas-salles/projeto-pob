package aplicacaoSwing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import fachada.Fachada;
import modelo.Conta;

public class TelaFecharConta {

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
//					TelaFecharConta window = new TelaFecharConta();
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
	public TelaFecharConta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Fechar");
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
		
		btnCadastrarConta = new JButton("Fechar Conta");
		btnCadastrarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty())
						lblMsg.setText("digite o seu nome");
					else {
						String nomecliente = textField.getText();
						Conta conta = Fachada.fecharConta(nomecliente);
						textField.setText("");
						lblMsg.setText("Total: R$" + conta.getTotal());
						JOptionPane.showMessageDialog(null,"Total: R$" + conta.getTotal());
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
