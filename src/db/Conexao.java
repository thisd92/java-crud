package db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Conexao {

    protected String servidor;
    protected String banco;
    protected String url;
    protected String usuario;
    protected String senha;
    protected Connection conexao;

    public Conexao() throws SQLException {
        this.servidor = "localhost";
        this.banco = "db_aula_java";
        this.url = "jdbc:mysql://" + servidor + "/" + banco;
        this.usuario = "root";
        this.senha = "";
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (this.conexao == null || this.conexao.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
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

    public boolean isConexaoAberta() {
        try {
            return conexao != null && !conexao.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> listarTabelas() throws SQLException {
        List<String> tabelas = new ArrayList<>();
        if (isConexaoAberta()) {
            DatabaseMetaData metaData = conexao.getMetaData();
            String[] types = {"TABLE"};
            try (ResultSet rs = metaData.getTables(null, null, "%", types)) {
                while (rs.next()) {
                    tabelas.add(rs.getString("TABLE_NAME"));
                }
            }
        }
        return tabelas;
    }

    public List<String> listarColunas(String tabela) throws SQLException {
        List<String> colunas = new ArrayList<>();
        if (isConexaoAberta()) {
            DatabaseMetaData metaData = conexao.getMetaData();
            try (ResultSet rs = metaData.getColumns(null, null, tabela, null)) {
                while (rs.next()) {
                    colunas.add(rs.getString("COLUMN_NAME"));
                }
            }
        }
        return colunas;
    }

}
