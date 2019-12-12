package aplicacaoSwing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fachada.Fachada;

public class TelaExcluirCliente {

	private JFrame frame;
	private JLabel lblNome;
	private JTextField textField;
	private JButton btnAtualizar;
	private JLabel lblMsg;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaExcluirCliente window = new TelaExcluirCliente();
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
	public TelaExcluirCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Excluir");
		frame.setBounds(100, 100, 425, 260);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNome = new JLabel("nome:");
		lblNome.setBounds(10, 22, 46, 13);
		frame.getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(48, 19, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnAtualizar = new JButton("Excluir");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty())
						lblMsg.setText("campo vazio");
					else {
						String nomecliente = textField.getText();
						Fachada.excluirCliente(nomecliente);
						textField.setText("");
						lblMsg.setText("exlusão concluída");
					}
					
				} catch (Exception erro) {
					lblMsg.setText(erro.getMessage());
				}
			}
		});
		btnAtualizar.setBounds(48, 58, 85, 21);
		frame.getContentPane().add(btnAtualizar);
		
		lblMsg = new JLabel("");
		lblMsg.setBounds(10, 158, 391, 13);
		frame.getContentPane().add(lblMsg);
		
		
		
		//mostrar a janela
		frame.setVisible(true);
	}

}
