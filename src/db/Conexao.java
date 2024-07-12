package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    protected String servidor;
    protected String banco;
    protected String url;
    protected String usuario;
    protected String senha;
    protected String tabela;
    protected Connection conexao;

    public Conexao() throws SQLException {
        this.servidor = "localhost";
        this.banco = "db_aula_java";
        this.url = "jdbc:mysql://" + servidor + "/" + banco;
        this.usuario = "root";
        this.senha = "AdmRootDevTH22*";
        this.tabela = "contatos";
        this.conexao = DriverManager.getConnection(url, usuario, senha);
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (this.conexao == null || this.conexao.isClosed()) {
            conectar();
        }
        return this.conexao;
    }

    public void fecharConexao() {
        if (this.conexao != null) {
            try {
                this.conexao.close();
                this.conexao = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void conectar() throws ClassNotFoundException, SQLException {
        if (this.conexao == null || this.conexao.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
        }
    }

}
