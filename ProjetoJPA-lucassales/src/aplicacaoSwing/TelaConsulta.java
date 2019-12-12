package aplicacaoSwing;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

public class TelaConsulta extends JFrame {
	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnCriar;
	private JButton btnPessoasComN;
	private JButton btnTelefonesPorPrefixo;
	private JButton btnClientesComN;
	private JButton btnProdutoPorTipo;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaListagem window = new TelaListagem();
//					window.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaConsulta() {
		setTitle("Consultar");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 734, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCriar = new JButton("Clientes por parte do nome");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String nom = JOptionPane.showInputDialog("digite parte do nome");
					textArea.setText(Fachada.consultaClientesPorParteNome(nom));
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(429, 13, 272, 23);
		contentPane.add(btnCriar);

		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(20, 11, 399, 160);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		
		btnPessoasComN = new JButton("Clientes com N contas");
		btnPessoasComN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int n = Integer.parseInt( JOptionPane.showInputDialog("quantas contas?") );
					textArea.setText(Fachada.consultaClientesNContas(n));			
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnPessoasComN.setBounds(429, 47, 272, 23);
		contentPane.add(btnPessoasComN);
		
		btnTelefonesPorPrefixo = new JButton("Clientes por tipo de produto consumido");
		btnTelefonesPorPrefixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String tipo = JOptionPane.showInputDialog("digite o tipo");
					textArea.setText(Fachada.consultaClientesPorTipo(tipo));					
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}

			
			}
		});
		btnTelefonesPorPrefixo.setBounds(429, 81, 272, 23);
		contentPane.add(btnTelefonesPorPrefixo);
		
		btnClientesComN = new JButton("Clientes com N ou mais produtos");
		btnClientesComN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int n = Integer.parseInt( JOptionPane.showInputDialog("quantas produtos?") );
					textArea.setText(Fachada.consultaClientesNProdutos(n));					
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnClientesComN.setBounds(429, 114, 272, 21);
		contentPane.add(btnClientesComN);
		
		btnProdutoPorTipo = new JButton("Produto por tipo");
		btnProdutoPorTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String tipo = JOptionPane.showInputDialog("digite o tipo");
					textArea.setText(Fachada.consultaProdutoPorTipo(tipo));					
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnProdutoPorTipo.setBounds(429, 145, 272, 21);
		contentPane.add(btnProdutoPorTipo);
	}
}
