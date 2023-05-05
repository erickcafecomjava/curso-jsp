package repository.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBancoBiblioteca {
   
	private static String url = "jdbc:postgresql://localhost:5432/biblioteca";
	private static String senha = "123";
	private static String usuario = "postgres";

	private static Connection connection  = null;
	
	//padrao singleton 
	 static {
		abriConexao();
	}
	@SuppressWarnings("unused")
	private static void abriConexao() {
		try {
			Class.forName("org.postgresql.Driver");
			if (connection == null) {
				connection = DriverManager.getConnection(url, usuario, senha);
				connection.setAutoCommit(false);
				System.out.println("Conexao Efetuada Com Sucesso");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erro ao abrir conexao" + e.getMessage());
		}
		
	}

	
	
	public static Connection getConexao() {
		
		return connection;
	
	}

	
	
			
}
