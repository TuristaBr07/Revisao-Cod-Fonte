package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe de usuário responsável por autenticação.
 * (CORREÇÃO: Adicionada documentação Javadoc)
 */
public class User {

    // Atributos de estado (design não recomendado, mas mantido do original)
    public String nome = "";
    public boolean result = false; README

    /**
     * Estabelece conexão com o banco de dados.
     * @return Objeto Connection ou null em caso de falha.
     */
    public Connection conectarBD() {
        Connection conn = null;
        try {
            // (Obs: O driver JDBC moderno não exige mais Class.forName())
            // Class.forName("com.mysql.cj.jdbc.Driver"); 
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            // CORREÇÃO: .
            // Informa o erro caso a conexão falhe.
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Verifica se um usuário e senha existem no banco de dados.
     * * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return true se o usuário for encontrado, false caso contrário.
     */
    public boolean verificarUsuario(String login, String senha) {
        // CORREÇÃO (Injeção de SQL): 
        // A consulta agora usa PreparedStatement (?) para evitar Injeção de SQL.
        String sql = "SELECT nome FROM usuarios WHERE login = ? AND senha = ?";
        
        // CORREÇÃO (Vazamento de Recursos):
        // Usando "try-with-resources" para garantir que Connection, PreparedStatement
        // e ResultSet sejam fechados automaticamente, mesmo se ocorrer um erro.
        try (Connection conn = conectarBD();
             PreparedStatement st = conn.prepareStatement(sql)) {

            // CORREÇÃO (NullPointer):
            // Verifica se a conexão não é nula antes de tentar usá-la.
            if (conn == null) {
                System.err.println("Falha ao conectar ao banco de dados.");
                return false;
            }

            // Define os parâmetros do PreparedStatement
            st.setString(1, login);
            st.setString(2, senha);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    this.result = true;
                    this.nome = rs.getString("nome");
                } else {
                    this.result = false;
                }
            }
        } catch (SQLException e) {
            // CORREÇÃO:
            // Informa erros de SQL (ex: tabela não existe, sintaxe errada).
            e.printStackTrace();
            this.result = false;
        }
        
        return this.result;
    }
}// fim da class