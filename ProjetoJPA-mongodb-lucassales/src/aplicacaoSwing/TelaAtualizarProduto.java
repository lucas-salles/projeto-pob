package aplicacaoSwing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fachada.Fachada;

public class TelaAtualizarProduto {

	private JFrame frame;
	private JLabel lblNome;
	private JTextField textField;
	private JLabel lblNovoNome;
	private JTextField textField_1;
	private JButton btnAtualizar;
	private JLabel lblMsg;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaAtualizarProduto window = new TelaAtualizarProduto();
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
	public TelaAtualizarProduto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Atualizar");
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
		
		lblNovoNome = new JLabel("novo nome:");
		lblNovoNome.setBounds(10, 45, 77, 13);
		frame.getContentPane().add(lblNovoNome);
		
		textField_1 = new JTextField();
		textField_1.setBounds(82, 42, 96, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty() || textField_1.getText().isEmpty())
						lblMsg.setText("um dos campos está vazio");
					else {
						String nomeproduto = textField.getText();
						String novonome = textField_1.getText();
						Fachada.alterarProduto(nomeproduto, novonome);
						textField.setText("");
						textField_1.setText("");
						lblMsg.setText("atualização concluída");
					}
					
				} catch (Exception erro) {
					lblMsg.setText(erro.getMessage());
				}
			}
		});
		btnAtualizar.setBounds(48, 93, 85, 21);
		frame.getContentPane().add(btnAtualizar);
		
		lblMsg = new JLabel("");
		lblMsg.setBounds(10, 158, 391, 13);
		frame.getContentPane().add(lblMsg);
		
		
		
		//mostrar a janela
		frame.setVisible(true);
	}

}
