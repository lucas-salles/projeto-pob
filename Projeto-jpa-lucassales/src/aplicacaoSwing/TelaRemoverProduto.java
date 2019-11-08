package aplicacaoSwing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import fachada.Fachada;
import modelo.Produto;

public class TelaRemoverProduto {

	private JFrame frame;
	private JLabel lblCliente;
	private JTextField textField;
	private JLabel lblMsg;
	private JButton btnBuscar;
	private JLabel lblProdutos;
	private JList<String> list;
	private DefaultListModel<String> model;
	private JScrollPane scroll;
	private String nomeproduto = null;
	private String nomecliente = null;
	private JButton btnRemoverProduto;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaRemoverProduto window = new TelaRemoverProduto();
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
	public TelaRemoverProduto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Remover");
		frame.setBounds(100, 100, 425, 331);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblCliente = new JLabel("cliente:");
		lblCliente.setBounds(10, 22, 46, 13);
		frame.getContentPane().add(lblCliente);
		
		textField = new JTextField();
		textField.setBounds(55, 19, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblMsg = new JLabel("");
		lblMsg.setBounds(10, 271, 391, 13);
		frame.getContentPane().add(lblMsg);
		
		btnBuscar = new JButton("Buscar Conta");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty())
						lblMsg.setText("digite o seu nome");
					else {
						nomecliente = textField.getText();
						List<Produto> produtos = Fachada.produtosConta(nomecliente);
						if (produtos.isEmpty()) {
							model.clear();
							lblMsg.setText("conta sem produtos");
						}
						else {
							model.clear();
							for(Produto p: produtos) 
								model.addElement(p.getNome());
						}

						textField.setText("");
					}
				} catch (Exception erro) {
					lblMsg.setText(erro.getMessage());
				}
			}
		});
		btnBuscar.setBounds(173, 18, 136, 21);
		frame.getContentPane().add(btnBuscar);
		
		lblProdutos = new JLabel("produtos:");
		lblProdutos.setBounds(10, 50, 68, 13);
		frame.getContentPane().add(lblProdutos);
		
		list = new JList();
		model = new DefaultListModel<String>();
		list.setModel(model);
		scroll = new JScrollPane(list);
		scroll.setBounds(33, 73, 163, 140);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scroll);
		
		btnRemoverProduto = new JButton("Remover Produto");
		btnRemoverProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(nomecliente == null || nomeproduto == null)
						lblMsg.setText("nenhum produto selecionado");
					else {
						Fachada.removerProduto(nomecliente, nomeproduto);
						lblMsg.setText("Produto removido");
						List<Produto> produtos = Fachada.produtosConta(nomecliente);
						if (produtos.isEmpty()) {
							model.clear();
							lblMsg.setText("conta sem produtos");
						}
						else {
							model.clear();
							for(Produto p: produtos) 
								model.addElement(p.getNome());
						}
					}
				} catch (Exception erro) {
					lblMsg.setText(erro.getMessage());
				}
			}
		});
		btnRemoverProduto.setBounds(33, 233, 143, 21);
		frame.getContentPane().add(btnRemoverProduto);
		
		
		list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] selecionado = list.getSelectedValue().split(" R\\$:"); 
				nomeproduto = selecionado[0];
			}
		});
		
		//mostrar a janela
		frame.setVisible(true);
	}
}
