package aplicacaoSwing;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fachada.Fachada;

public class TelaPrincipal {

	private JFrame frame;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmListar;
	private JMenu mnConsulta;
	private JMenu mnProduto;
	private JMenu mnCliente;
	private JMenu mnTipo;
	private JMenu mnConta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();


		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				Fachada.inicializar();
				JOptionPane.showMessageDialog(null, "sistema inicializado !");
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
				JOptionPane.showMessageDialog(null, "sistema finalizado !");
			}
		});
		frame.setTitle("Restaurante");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);

		mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroCliente j = new TelaCadastroCliente();
			}

		});
		mnCliente.add(mntmCadastrar);
		
		JMenuItem mntmAtualizar = new JMenuItem("Atualizar");
		mntmAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaAtualizarCliente j = new TelaAtualizarCliente();
			}

		});
		mnCliente.add(mntmAtualizar);

		mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemCliente j = new TelaListagemCliente();
				j.setVisible(true);
			}
		});
		mnCliente.add(mntmListar);
		
		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaExcluirCliente j = new TelaExcluirCliente();
			}

		});
		mnCliente.add(mntmExcluir);
		
		mnConta = new JMenu("Conta");
		menuBar.add(mnConta);

		JMenuItem mntmCadastrar_3 = new JMenuItem("Cadastrar");
		mntmCadastrar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroConta j = new TelaCadastroConta();
			}

		});
		mnConta.add(mntmCadastrar_3);
		
		JMenuItem mntmFecharConta = new JMenuItem("Fechar Conta");
		mntmFecharConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaFecharConta j = new TelaFecharConta();
			}

		});
		mnConta.add(mntmFecharConta);

		JMenuItem mntmListar_3 = new JMenuItem("Listar");
		mntmListar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemConta j = new TelaListagemConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmListar_3);



		mnProduto = new JMenu("Produto");
		JMenuItem mntmCadastrar_1 = new JMenuItem("Cadastrar");
		mntmCadastrar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroProduto j = new TelaCadastroProduto();
			}

		});
		mnProduto.add(mntmCadastrar_1);
		
		menuBar.add(mnProduto);
		JMenuItem mntmCriar = new JMenuItem("Adicionar na conta");
		mntmCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaAdicionarProduto j = new TelaAdicionarProduto();
			}
		});
		mnProduto.add(mntmCriar);
		
		menuBar.add(mnProduto);
		JMenuItem mntmRemover = new JMenuItem("Remover da conta");
		mntmRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaRemoverProduto j = new TelaRemoverProduto();
			}
		});
		mnProduto.add(mntmRemover);
		
		JMenuItem mntmAtualizar_1 = new JMenuItem("Atualizar");
		mntmAtualizar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaAtualizarProduto j = new TelaAtualizarProduto();
			}

		});
		mnProduto.add(mntmAtualizar_1);

		JMenuItem mntmListar_1 = new JMenuItem("Listar");
		mntmListar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListagemProduto j = new TelaListagemProduto();
				j.setVisible(true);
			}
		});
		mnProduto.add(mntmListar_1);
		
		JMenuItem mntmExcluir_1 = new JMenuItem("Excluir");
		mntmExcluir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaExcluirProduto j = new TelaExcluirProduto();
			}

		});
		mnProduto.add(mntmExcluir_1);
		
		
		mnTipo = new JMenu("Tipo");
		menuBar.add(mnTipo);

		JMenuItem mntmCadastrar_2 = new JMenuItem("Cadastrar");
		mntmCadastrar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroTipo j = new TelaCadastroTipo();
			}

		});
		mnTipo.add(mntmCadastrar_2);
		
		JMenuItem mntmAtualizar_2 = new JMenuItem("Atualizar");
		mntmAtualizar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaAtualizarTipo j = new TelaAtualizarTipo();
			}

		});
		mnTipo.add(mntmAtualizar_2);
		
		JMenuItem mntmListar_2 = new JMenuItem("Listar");
		mntmListar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemTipo j = new TelaListagemTipo();
				j.setVisible(true);
			}
		});
		mnTipo.add(mntmListar_2);
		
		JMenuItem mntmExcluir_2 = new JMenuItem("Excluir");
		mntmExcluir_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaExcluirTipo j = new TelaExcluirTipo();
			}

		});
		mnTipo.add(mntmExcluir_2);

		mnConsulta = new JMenu("Consultas");
		mnConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TelaConsulta j = new TelaConsulta();
				j.setVisible(true);

			
			}
		});
		menuBar.add(mnConsulta);
	}
}
