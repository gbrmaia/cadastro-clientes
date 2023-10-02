package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ModelTabela extends AbstractTableModel{

	
	public static final String[ ] colunas = {
			"ID", "Nome", "Cpf/Cnpj", "Email", "Telefone", "Endereço", "Estado"
		};
	
	
	public ModelTabela(ArrayList<Cliente> clientes) {
		super();
		this.clientes = clientes;
	}

	private ArrayList<Cliente> clientes;
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return clientes.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente cliente = clientes.get(rowIndex);
		if(columnIndex == 0) {
			return cliente.getId();	
		} else if(columnIndex == 1) {
			return cliente.getNome();			
		} else if(columnIndex == 2) {
			return cliente.getCpfcnpj();			
		} else if(columnIndex == 3) {
			return cliente.getEmail();			
		} else if(columnIndex == 4) {
			return cliente.getTelefone();			
		} else if(columnIndex == 5) {
			return cliente.getEndereco();			
		} else if(columnIndex == 6) {
			return cliente.getEstado();			
		} 
		return null;
	}
	
	public String getColumnName(int column) {
		return colunas[column];
	}

}
