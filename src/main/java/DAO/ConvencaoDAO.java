package DAO;

import Model.Convencao;

import java.sql.SQLException;
import java.util.ArrayList;

public class ConvencaoDAO extends ConnectionDAO{
    boolean sucesso = false;

    //INSERT
    public boolean insertConvencao(Convencao convencao) {

        connectToDB();

        String sql = "INSERT INTO convencao (nome,endereco, telefone) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, convencao.getNome());
            pst.setString(2, convencao.getEndereco());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //UPDATE
    public boolean updateConvencao(String nome, Convencao convencao) {
        connectToDB();
        String sql = "UPDATE convencao SET nome=?, endereco=?, telefone=? where nome=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, convencao.getEndereco());
            pst.setString(3, convencao.getTelefone());
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //DELETE
    public boolean deleteConvencao(String nome) {
        connectToDB();
        String sql = "DELETE FROM convencao where nome=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //SELECT
    public ArrayList<Convencao> selectConvencao() {
        ArrayList<Convencao> users = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM convencao";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de convencoes: ");

            while (rs.next()) {

                Convencao convencaoAux = new Convencao(rs.getString("nome"),rs.getString("endereco"), rs.getString("telefone"));

                System.out.println("nome = " + convencaoAux.getNome());
                System.out.println("endereco = " + convencaoAux.getEndereco());
                System.out.println("teleone = " + convencaoAux.getTelefone());
                System.out.println("--------------------------------");

                users.add(convencaoAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return users;
    }

}
