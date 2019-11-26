package ftt.ec.hw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ftt.ec.hw.config.BDConfig;
import ftt.ec.hw.entidade.Notas;

public class NotasDAO {
	
	public List<Notas> listarNotas() throws Exception {
		List<Notas> lista = new ArrayList<>();
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "SELECT * FROM TB_NOTAS";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			Notas notas = new Notas();
			notas.setId(rs.getInt("ID"));
			notas.setNome(rs.getString("NOME"));
			notas.setN1_1(rs.getDouble("N1_1"));
			notas.setN2_1(rs.getDouble("N2_1"));
			notas.setN1_2(rs.getDouble("N1_2"));
			notas.setN2_2(rs.getDouble("N2_2"));
			notas.setForma(rs.getDouble("FORMA"));
			notas.setNota_final(rs.getDouble("NOTA_FINAL"));
			
			lista.add(notas);
		}
		return lista;
	}
	
	public int addNotas (Notas notas) throws Exception {
		int idGerado = 0;
		Connection conexao = BDConfig.getConnection();
		
		String sql = "INSERT INTO TB_NOTAS (NOME, N1_1, N2_1,N1_2, N2_2, FORMA, NOTA_FINAL) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1,notas.getNome());
		statement.setDouble(2,notas.getN1_1());
		statement.setDouble(3,notas.getN1_2());
		statement.setDouble(4,notas.getN2_1());
		statement.setDouble(5,notas.getN2_2());
		statement.setDouble(6,notas.getForma());
		statement.setDouble(7,notas.getNota_final());
		statement.execute();
		
		ResultSet rs = statement.getGeneratedKeys();
		if(rs.next()) {
			idGerado = rs.getInt(1);
			
		}
		return idGerado;
		
	}
	
	public void updateNotas (Notas notas) throws Exception {
		Connection conexao = BDConfig.getConnection();
		
		String sql = "UPDATE TB_NOTAS SET NOME = ? , N1_1 =?, N1_2=?, N2_1=?, N2_2=?, FORMA=?, NOTA_FINAL=? WHERE ID=?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1,notas.getNome());
		statement.setDouble(2,notas.getN1_1());
		statement.setDouble(3,notas.getN1_2());
		statement.setDouble(4,notas.getN2_1());
		statement.setDouble(5,notas.getN2_2());
		statement.setDouble(6,notas.getForma());
		statement.setDouble(7,notas.getNota_final());
		statement.execute();
		
	}
	
	public void deleteNotas (Notas notas) throws Exception {
		Connection conexao = BDConfig.getConnection();
		
		String sql = "DELETE FROM TB_NOTAS WHERE ID=?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1,notas.getId());
		statement.execute();
	}
	
	 public Notas buscarNotaPorId(int idNota) throws Exception {
	        Notas notas = null;
	 
	        Connection conexao = BDConfig.getConnection();
	 
	        String sql = "SELECT * FROM TB_NOTAS WHERE ID = ?";
	 
	        PreparedStatement statement = conexao.prepareStatement(sql);
	        statement.setInt(1, idNota);
	        ResultSet rs = statement.executeQuery();
	 
	        if (rs.next()) {
	            notas= new Notas();
	            notas.setId(rs.getInt("ID"));
				notas.setNome(rs.getString("NOME"));
				notas.setN1_1(rs.getDouble("N1_1"));
				notas.setN2_1(rs.getDouble("N2_1"));
				notas.setN1_2(rs.getDouble("N1_2"));
				notas.setN2_2(rs.getDouble("N2_2"));
				notas.setForma(rs.getDouble("FORMA"));
				notas.setNota_final(rs.getDouble("NOTA_FINAL"));
	        }
	 
	        return notas;
	    }
	 public void removerNota(int idNota) throws Exception {
	        Connection conexao = BDConfig.getConnection();
	 
	        String sql = "DELETE FROM TB_NOTAS WHERE ID = ?";
	 
	        PreparedStatement statement = conexao.prepareStatement(sql);
	        statement.setInt(1, idNota);
	        statement.execute();
	    }
	 public void editarNotas(Notas notas, int idNota) throws Exception {
	        Connection conexao = BDConfig.getConnection();
	 
	        String sql = "UPDATE TB_NOTAS SET NOME = ? , N1_1 =?, N1_2=?, N2_1=?, N2_2=?, FORMA=?, NOTA_FINAL=? WHERE ID=?";
	 
	        PreparedStatement statement = conexao.prepareStatement(sql);
	        statement.setString(1,notas.getNome());
			statement.setDouble(2,notas.getN1_1());
			statement.setDouble(3,notas.getN1_2());
			statement.setDouble(4,notas.getN2_1());
			statement.setDouble(5,notas.getN2_2());
			statement.setDouble(6,notas.getForma());
			statement.setDouble(7,notas.getNota_final());
	        statement.execute();
	    }
	

}
