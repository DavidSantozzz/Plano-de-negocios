package banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Banco {
		private final String nomeBanco = "dbAula20261014";
		private final String usuario = "root";
		private final String senha  = "root";
		
		private boolean conectou;
		
		private String url;
		
		public Banco() {
			System.out.println("Execultando o construtor de Banco");
			conectou = false;
		
			url="jdbc:mysql://localhost:3306/" + nomeBanco + "?useTimezone=true&serverTimezone=UTC"; 
			
			try (Connection conexao = conectar()){
				System.out.println("Conexão bem-sucedida com o banco" + nomeBanco);
				conectou = true;
			} catch (SQLException exc) {
				System.err.println("Deu erro mano");
				exc.printStackTrace();
			}
		}
		
		private Connection conectar() throws SQLException{
			return DriverManager.getConnection(url,usuario,senha);
		}
		
		public void executarComando(String comando) {
			if(conectou) {
				try(Connection conexao = conectar();
						Statement stmt = conexao.createStatement()){
					int linhasAfetadas = stmt.executeUpdate(comando);
					System.out.println(linhasAfetadas + "linha(s) afetadas(s).");
					System.out.println("ao executar o comando'" + comando  + "'.");
				} catch (SQLException exc) {
					System.err.println("Erro ao executar" + comando + "'");
				}
			}
		}
}
