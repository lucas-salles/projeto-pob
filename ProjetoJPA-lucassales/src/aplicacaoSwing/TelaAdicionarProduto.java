package aplicacaoSwing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.regex.Pattern;

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

public class TelaAdicionarProduto {

	private JFrame frame;
	private JLabel lblNome;
	private JTextField textField;
	private JLabel lblTipo;
	private JButton btnCadastrarProduto;
	private JLabel lblMsg;
	private JList<String> list;
	private DefaultListModel<String> model;
	private JScrollPane scroll;
	private String nomeproduto = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaAdicionarProduto window = new TelaAdicionarProduto();
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
	public TelaAdicionarProduto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Adicionar");
		frame.setBounds(100, 100, 413, 375);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNome = new JLabel("cliente:");
		lblNome.setBounds(10, 20, 46, 13);
		frame.getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(66, 17, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblTipo = new JLabel("produto:");
		lblTipo.setBounds(10, 54, 56, 13);
		frame.getContentPane().add(lblTipo);
		
		btnCadastrarProduto = new JButton("Adicionar Produto");
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty())
						lblMsg.setText("digite o seu nome");
					else if(nomeproduto == null)
						lblMsg.setText("selecione o produto");
					else {
						String nomecliente = textField.getText();
						Fachada.adicionarProduto(nomecliente, nomeproduto);
						textField.setText("");
						lblMsg.setText("produto adicionado");
					}
					
				} catch (Exception erro) {
					lblMsg.setText(erro.getMessage());
				}
			}
		});
		btnCadastrarProduto.setBounds(66, 264, 144, 21);
		frame.getContentPane().add(btnCadastrarProduto);
		
		lblMsg = new JLabel("");
		lblMsg.setBounds(10, 311, 373, 13);
		frame.getContentPane().add(lblMsg);
		
		
		
		list = new JList();
		model = new DefaultListModel<String>();
		list.setModel(model);
		scroll = new JScrollPane(list);
		scroll.setBounds(66, 52, 163, 140);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scroll);
		
		List<Produto> produtos = Fachada.getProdutos();
		if (produtos.isEmpty()) {
			model.clear();
			lblMsg.setText("nenhum produto cadastrado");
		}
		else {
			model.clear();
			for(Produto p: produtos) 
				model.addElement(p.getNome() + " R$:" + p.getPreco());
		}
		
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
