package aplicacaoSwing;

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
import modelo.Tipo;

public class TelaCadastroProduto {

	private JFrame frame;
	private JLabel lblNome;
	private JTextField textField;
	private JLabel lblPreo;
	private JTextField textField_1;
	private JLabel lblTipo;
	private JButton btnCadastrarProduto;
	private JLabel lblMsg;
	private JList<String> list;
	private DefaultListModel<String> model;
	private JScrollPane scroll;
	private String nometipo = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroProduto window = new TelaCadastroProduto();
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
	public TelaCadastroProduto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Cadastro");
		frame.setBounds(100, 100, 413, 375);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNome = new JLabel("nome:");
		lblNome.setBounds(10, 20, 46, 13);
		frame.getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(66, 17, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblPreo = new JLabel("pre\u00E7o:");
		lblPreo.setBounds(10, 46, 46, 13);
		frame.getContentPane().add(lblPreo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(66, 43, 96, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblTipo = new JLabel("tipo:");
		lblTipo.setBounds(10, 72, 46, 13);
		frame.getContentPane().add(lblTipo);
		
		btnCadastrarProduto = new JButton("Cadastrar Produto");
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().isEmpty() || textField_1.getText().isEmpty())
						lblMsg.setText("um dos campos esta vazio");
					else if(nometipo == null)
						lblMsg.setText("selecione o tipo do produto");
					else {
						String valor = textField_1.getText();
						Pattern pattern = Pattern.compile("^\\d++$");
						java.util.regex.Matcher matcher = pattern.matcher(valor);
						if(!matcher.find())
							lblMsg.setText("Preço inválido");
						else {
							String nome = textField.getText();
							double preco = Double.parseDouble(valor);
							Fachada.cadastrarProduto(nome, preco, nometipo);
							textField.setText("");
							textField_1.setText("");
							lblMsg.setText("cadastro concluido");
						}
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
		scroll.setBounds(65, 70, 163, 140);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scroll);
		
		List<Tipo> tipos = Fachada.getTipos();
		if (tipos.isEmpty()) {
			model.clear();
			lblMsg.setText("nenhum tipo cadastrado");
		}
		else {
			model.clear();
			for(Tipo t: tipos) 
				model.addElement(t.getNome());
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
				nometipo = list.getSelectedValue();
			}
		});
		
		
		
		//mostrar a janela
		frame.setVisible(true);
	}
}
