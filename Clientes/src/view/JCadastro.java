package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import dao.DAO;
import model.Cliente;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class JCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCpfCnpj;
	private JTextField textFieldTelefone;
	private JTextField textFieldEmail;
	private JComboBox<String> comboBoxEstado;
	private JTextArea textAreaEndereco;
	private JPrincipal jPrincipal;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCadastro frame = new JCadastro(null, null);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private String[] estados = { "Selecione um estado", "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará",
			"Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul",
			"Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro",
			"Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo",
			"Sergipe", "Tocantins" };
	/**
	 * Create the frame.
	 */
	public JCadastro(Cliente clienteSelecionado, JPrincipal jPrincipal) throws SQLException{
		this.jPrincipal = jPrincipal;
		DAO dao = new DAO();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(224, 58, 72, 14);
		contentPane.add(lblTelefone);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(10, 27, 414, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldCpfCnpj = new JTextField();
		textFieldCpfCnpj.setBounds(10, 73, 200, 20);
		contentPane.add(textFieldCpfCnpj);
		textFieldCpfCnpj.setColumns(10);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(224, 73, 200, 20);
		contentPane.add(textFieldTelefone);

		JLabel lblNewLabel_1_1 = new JLabel("Cpf/Cnpj:");
		lblNewLabel_1_1.setBounds(10, 58, 58, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Email:");
		lblNewLabel_1_1_1.setBounds(10, 106, 46, 14);
		contentPane.add(lblNewLabel_1_1_1);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(10, 121, 200, 20);
		contentPane.add(textFieldEmail);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Endereço:");
		lblNewLabel_1_1_1_2.setBounds(10, 152, 58, 14);
		contentPane.add(lblNewLabel_1_1_1_2);

		textAreaEndereco = new JTextArea();
		textAreaEndereco.setBorder(new LineBorder(new Color(0, 0, 0)));
		textAreaEndereco.setBounds(10, 166, 414, 36);
		contentPane.add(textAreaEndereco);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(224, 104, 46, 14);
		contentPane.add(lblEstado);

		
		comboBoxEstado = new JComboBox<>(estados);
		comboBoxEstado.setBounds(224, 120, 200, 22);
		contentPane.add(comboBoxEstado);

		JButton btnNewButton = new JButton(clienteSelecionado == null ? "Cadastrar" : "Alterar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				// String id, String nome, String email, String telefone, String endereco,
				// String estado, String cpfcnpj
				Cliente cliente = new Cliente(null, textFieldNome.getText(), textFieldCpfCnpj.getText(),
						textFieldEmail.getText(), textFieldTelefone.getText(), textAreaEndereco.getText(),
						comboBoxEstado.getSelectedItem().toString());
				if (clienteSelecionado == null) {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCpfCnpj.getText())){
						try {
							dao.cadastrarCliente(cliente);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						abrirTelaPrincipal(jPrincipal);
					} else {
						JOptionPane.showMessageDialog(null, "Confira os dados Nome/CpfCnpj.");
					}
				
				} else {
					if(!"".equalsIgnoreCase(textFieldNome.getText()) && !"".equalsIgnoreCase(textFieldCpfCnpj.getText())){
						dao.alterarCliente(clienteSelecionado.getId(), cliente);
						abrirTelaPrincipal(jPrincipal);
					} else {
						JOptionPane.showMessageDialog(null, "Confira os dados Nome/CpfCnpj.");
					}
					dao.alterarCliente(clienteSelecionado.getId(), cliente);
					
				}

			}
		});
		btnNewButton.setBounds(300, 213, 124, 23);
		contentPane.add(btnNewButton);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setForeground(new Color(255, 255, 255));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.excluirCliente(clienteSelecionado.getId());
				abrirTelaPrincipal(jPrincipal);
			}
		});
		btnExcluir.setBackground(new Color(128, 0, 0));
		btnExcluir.setBounds(10, 213, 124, 23);
		btnExcluir.setVisible(false);
		contentPane.add(btnExcluir);

		if (clienteSelecionado != null) {
			preencherCampos(clienteSelecionado);
			btnExcluir.setVisible(true);
		}


	}
	
	
	private String indiceParaString(int indice) {
        if (indice >= 0 && indice < estados.length) {
            return estados[indice];
        }
        return "Selecione um estado";
    }
	
	private void preencherCampos(Cliente clienteSelecionado) {
		textFieldNome.setText(clienteSelecionado.getNome());
		textFieldCpfCnpj.setText(clienteSelecionado.getCpfcnpj());
		textFieldEmail.setText(clienteSelecionado.getEmail());
		textFieldTelefone.setText(clienteSelecionado.getTelefone());
		textAreaEndereco.setText(clienteSelecionado.getEndereco());
		
		   String estadoSelecionado = clienteSelecionado.getEstado();
	        int indiceEstado = stringParaIndice(estadoSelecionado);
	        comboBoxEstado.setSelectedItem(estados[indiceEstado]);
	}
	
	 private int stringParaIndice(String estado) {
	        for (int i = 0; i < estados.length; i++) {
	            if (estados[i].equals(estado)) {
	                return i;
	            }
	        }
	        return 0;
	    }
	 
	 private void abrirTelaPrincipal(JPrincipal jPrincipal) {
		 jPrincipal.dispose();
		 dispose();
		 try {
			jPrincipal = new JPrincipal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 jPrincipal.setLocationRelativeTo(jPrincipal);
		 jPrincipal.setVisible(true);
	 }

}
