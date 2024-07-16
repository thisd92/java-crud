package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    private int id = 0;
    private String nome, cpf, rg;
    private Date dataNasc;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public User() {

    }

    public User(String nome, String cpf, String rg, Date dataNasc) {
        id++;
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNasc = dataNasc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public String getDataString(Date date) {
        String data = new SimpleDateFormat("dd/MM/yyyy").format(date);
        return data;
    }

    public void setDataNasc(String dataNasc) {
        try {
            this.dataNasc = dateFormat.parse(dataNasc);
        } catch (ParseException e) {
            e.printStackTrace();
            // Trate o erro apropriadamente aqui, por exemplo, lançando uma exceção customizada
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", dataNasc=" + dataNasc + '}';
    }

}
