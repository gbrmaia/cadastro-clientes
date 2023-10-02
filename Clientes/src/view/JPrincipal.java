package view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import controller.GerarRelatorio;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;

import dao.DAO;
import model.Cliente;
import model.ModelTabela;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldBusca;
	private JTable table;
	private ArrayList<Cliente> clientes;
	private JPrincipal jPrincipal;
	private TableRowSorter<ModelTabela> rowSorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JPrincipal frame = new JPrincipal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JPrincipal() throws SQLException {
		this.jPrincipal = this;
		DAO dao = new DAO();
		try {

			clientes = dao.listarCliente();
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 942, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("CADASTRAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JCadastro jCadastro;
				try {
					jCadastro = new JCadastro(null, jPrincipal);
					jCadastro.setLocationRelativeTo(jCadastro);
					jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					jCadastro.setVisible(true);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(48, 39, 118, 23);
		contentPane.add(btnNewButton);

		textFieldBusca = new JTextField();
		textFieldBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filtrar();
			}

		});
		textFieldBusca.setBounds(212, 40, 393, 20);
		contentPane.add(textFieldBusca);
		textFieldBusca.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 100, 822, 317);
		contentPane.add(scrollPane);

		ModelTabela modelTabela = new ModelTabela(clientes);

		table = new JTable();
		table.setModel(modelTabela);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 3) {
					try {
						Cliente clienteSelecionado = dao
								.consultarCliente(modelTabela.getValueAt(table.getSelectedRow(), 0).toString());
						JCadastro jCadastro = new JCadastro(clienteSelecionado, jPrincipal);
						jCadastro.setLocationRelativeTo(jCadastro);
						jCadastro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						jCadastro.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		rowSorter = new TableRowSorter<>(modelTabela);
		table.setRowSorter(rowSorter);
		scrollPane.setViewportView(table);

		JButton btnNewButtonRelatorio = new JButton("GERAR RELATORIO");
		btnNewButtonRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new GerarRelatorio();
			}
		});
		btnNewButtonRelatorio.setBounds(670, 39, 204, 23);
		contentPane.add(btnNewButtonRelatorio);
	}

	private void filtrar() {
		String busca = textFieldBusca.getText().trim();
		if (busca.length() == 0) {
			rowSorter.setRowFilter(null);
		} else {
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + busca));
		}
	}
}
