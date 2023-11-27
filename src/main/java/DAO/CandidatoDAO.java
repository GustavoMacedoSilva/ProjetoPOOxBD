package DAO;
import Model.Candidato;

import java.sql.SQLException;
import java.util.ArrayList;
public class CandidatoDAO extends ConnectionDAO{

    boolean sucesso = false;

    //INSERT
    public boolean insertCandidato(Candidato candidato){
        connectToDB();
        String sql = "Insert INTO candidato (nome, email, nome_convencao) values (?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, candidato.getNome());
            pst.setString(2, candidato.getEmail());
            pst.setString(3, candidato.getNome_convecao());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc){
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
    public boolean updateCandidato(int id, Candidato candidato) {
        connectToDB();
        String sql = "UPDATE candidato SET nome=?, email=?, nome_convencao=? where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, candidato.getNome());
            pst.setString(2, candidato.getEmail());
            pst.setString(3, candidato.getNome_convecao());
            pst.setInt(4, id);
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
    public boolean deleteCandidato(int id) {
        connectToDB();
        String sql = "DELETE FROM candidato where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
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
    public ArrayList<Candidato> selectCandidato() {
        ArrayList<Candidato> users = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM candidato";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de candidatos: ");

            while (rs.next()) {

                Candidato candidatoAux = new Candidato(rs.getString("nome"),rs.getString("email"), rs.getString("nome_convencao"));

                System.out.println("nome = " + candidatoAux.getNome());
                System.out.println("email = " + candidatoAux.getEmail());
                System.out.println("nome_convencao = " + candidatoAux.getNome_convecao());
                System.out.println("--------------------------------");

                users.add(candidatoAux);
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
