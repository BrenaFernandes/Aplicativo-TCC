package model.dao;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;
import model.bean.Demanda;
import model.bean.Equipamento;
public class DemandaDAO {
    public void create(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO demanda(id,contrato,mes,ano,demandamed,equipamento,"
                    + "alimentacao,demandacalc)VALUES(?,?,?,?,?,?,?,?)");
            stmt.setInt(1, d.getId());
            stmt.setString(2, d.getContrato());
            stmt.setInt(3, d.getMes());
            stmt.setInt(4, d.getAno());
            stmt.setDouble(5, d.getDemandamed());
            stmt.setString(6, c.getTransformador());
            stmt.setString(7, c.getAlimentacao());
            stmt.setDouble(8, d.getDemandacalc());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);        }
    }
    public void ImportacaoEquipamento(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE demanda SET equipamento=?, alimentacao=? WHERE contrato=?");
            stmt.setString(1, c.getTransformador());
            stmt.setString(2, c.getAlimentacao());
            stmt.setString(3, d.getContrato());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void DemandaCalc(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE demanda SET demandacalc=? WHERE ano=? and mes=? and contrato=?");
            stmt.setDouble(1, d.getDemandacalc());
            stmt.setInt(2,d.getAno());
            stmt.setInt(3,d.getMes());
            stmt.setString(4,d.getContrato());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public List<Demanda> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Demanda> demandas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM demanda GROUP BY ano");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Demanda demanda = new Demanda();
                demanda.setContrato(rs.getString("contrato"));
                demanda.setMes(rs.getInt("mes"));
                demanda.setAno(rs.getInt("ano"));
                demanda.setDemandamed(rs.getDouble("demandamed"));
                demandas.add(demanda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return demandas;
    }
     public List<Demanda> readForDesc(String desc){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Demanda> demandas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT contrato, mes, ano, demandamed FROM demanda WHERE "
                    + "contrato LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Demanda d = new Demanda();
                d.setContrato(rs.getString("contrato"));
                d.setMes(rs.getInt("mes"));
                d.setAno(rs.getInt("ano"));
                d.setDemandamed(rs.getDouble("demandamed"));
                demandas.add(d);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return demandas;
    }
     public List<Demanda> ConsultaDemanda(int mes, int ano, String equi){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Demanda> demandas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT d.contrato, d.mes, d.ano, d.demandamed FROM demanda as d INNER"
                    + " JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and d.contrato=c.contrato and c.transformador=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            stmt.setString(3, equi);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Demanda d = new Demanda();
                d.setContrato(rs.getString("contrato"));
                d.setMes(rs.getInt("mes"));
                d.setAno(rs.getInt("ano"));
                d.setDemandamed(rs.getDouble("demandamed"));
                demandas.add(d);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return demandas;
    }
     public double consumoTotalMensal(int mes, int ano, String equi){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda as d INNER JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and d.contrato=c.contrato and c.transformador=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            stmt.setString(3, equi);
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
                    } catch (SQLException ex) {
            Logger.getLogger(EquipamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return soma;
    }
    public List<Demanda> readDemanda() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Demanda> demandas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM demanda");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Demanda demanda = new Demanda();
                demanda.setContrato(rs.getString("contrato"));
                demanda.setMes(rs.getInt("mes"));
                demanda.setAno(rs.getInt("ano"));
                demanda.setDemandamed(rs.getDouble("demandamed"));
                demandas.add(demanda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return demandas;
    }
    public void update(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE demanda SET mes=?,ano=?,demandamed=? WHERE contrato=?");
            stmt.setInt(1, d.getMes());
            stmt.setInt(2, d.getAno());
            stmt.setDouble(3, d.getDemandamed());
            stmt.setString(4, d.getContrato());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void delete(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE * FROM demanda WHERE contrato=?");
            stmt.setString(1, d.getContrato());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void deletebanco() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("TRUNCATE TABLE demanda");
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dados excluídos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void Somademanda(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) FROM demanda WHERE mes=? and ano=?");
            stmt.setDouble(1, d.getDemandacalc());
            stmt.setInt(2, d.getMes());
            stmt.setInt(3, d.getAno());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public double carregamentoA(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double somatorioA = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE "
                    + "alimentacao='Monofasico A' and equipamento=? and ano=? and mes=?");
            stmt.setString(1, d.getEquipamento());
            stmt.setInt(2, d.getAno());
            stmt.setInt(3, d.getMes());
            rs = stmt.executeQuery();
            while (rs.next()) {
                somatorioA = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return somatorioA;
    }
    public double carregamentoB(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double somatorioB = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE "
                    + "alimentacao='Monofasico B' and equipamento=? and ano=? and mes=?");
            stmt.setString(1, d.getEquipamento());
            stmt.setInt(2, d.getAno());
            stmt.setInt(3, d.getMes());
            rs = stmt.executeQuery();
            while (rs.next()) {
                somatorioB = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return somatorioB;
    }
    public double carregamentoC(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double somatorioC = 0;
        try {
            stmt = con.prepareStatement("SELECT Sum(demandacalc) AS total FROM demanda WHERE "
                    + "alimentacao='Monofasico C' and equipamento=? and ano=? and mes=?");
            stmt.setString(1, d.getEquipamento());
            stmt.setInt(2, d.getAno());
            stmt.setInt(3, d.getMes());
            rs = stmt.executeQuery();
            while (rs.next()) {
                somatorioC = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return somatorioC;
    }
    public double carregamentotri(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double somatoriotri = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE "
                    + "alimentacao='Trifasico' and equipamento=? and ano=? and mes=?");
            stmt.setString(1, d.getEquipamento());
            stmt.setInt(2, d.getAno());
            stmt.setInt(3, d.getMes());
            rs = stmt.executeQuery();
            while (rs.next()) {
                somatoriotri = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return somatoriotri;
    }
    public List<Demanda> LercargaA(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Demanda> demandaA = new ArrayList<>();
        String fase = "Monofasico A";
        try {
            stmt = con.prepareStatement("SELECT contrato, demandacalc, alimentacao FROM demanda "
                    + "WHERE alimentacao=? and mes=? and ano=? and equipamento=? ORDER BY demandacalc");
            stmt.setString(1, fase);
            stmt.setInt(2, d.getMes());
            stmt.setInt(3, d.getAno());
            stmt.setString(4, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Demanda demanda = new Demanda();
                demanda.setContrato(rs.getString("contrato"));
                demanda.setDemandacalc(rs.getDouble("demandacalc"));
                demanda.setAlimentacao(rs.getString("alimentacao"));
                demandaA.add(demanda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return demandaA;
    }
    public List<Demanda> LercargaB(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Demanda> demandaA = new ArrayList<>();
        String fase = "Monofasico B";
        try {
            stmt = con.prepareStatement("SELECT contrato, demandacalc, alimentacao FROM demanda WHERE "
                    + "alimentacao=? and mes=? and ano=? and equipamento=? ORDER BY demandacalc DESC");
            stmt.setString(1, fase);
            stmt.setInt(2, d.getMes());
            stmt.setInt(3, d.getAno());
            stmt.setString(4, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Demanda demanda = new Demanda();
                demanda.setContrato(rs.getString("contrato"));
                demanda.setDemandacalc(rs.getDouble("demandacalc"));
                demanda.setAlimentacao(rs.getString("alimentacao"));
                demandaA.add(demanda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return demandaA;
    }
    public List<Demanda> LercargaC(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Demanda> demandaA = new ArrayList<>();
        String fase = "Monofasico C";
        try {
            stmt = con.prepareStatement("SELECT contrato, demandacalc, alimentacao FROM demanda "
                    + "WHERE alimentacao=? and mes=? and ano=? and equipamento=? ORDER BY demandacalc DESC");
            stmt.setString(1, fase);
            stmt.setInt(2, d.getMes());
            stmt.setInt(3, d.getAno());
            stmt.setString(4, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Demanda demanda = new Demanda();
                demanda.setContrato(rs.getString("contrato"));
                demanda.setDemandacalc(rs.getDouble("demandacalc"));
                demanda.setAlimentacao(rs.getString("alimentacao"));
                demandaA.add(demanda);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DemandaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return demandaA;
    }
    public void salvar(List<String> linhas) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO demanda(contrato, mes, ano, demandamed, equipamento, alimentacao)"
                + "VALUES(?,?,?,?,?,?)";
        con.setAutoCommit(false);
        PreparedStatement psmt = con.prepareStatement(sql);
        int contador = 0;
        for (String linha : linhas) {
            String[] colunas = linha.split(";");
            psmt.setString(1, colunas[0]);
            psmt.setString(2, colunas[1]);
            psmt.setString(3, colunas[2]);
            psmt.setString(4, colunas[3]);
            psmt.setString(5, colunas[4]);
            psmt.setString(6, colunas[5]);
            psmt.addBatch();
            contador++;
            if (contador == 1000) {
                psmt.executeBatch();
                con.commit();
                contador = 0;
            }
        }
        psmt.executeBatch();
        con.commit();
        con.close();
    }
    public double SomademandaJaneiro(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 01;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE mes=? "
                    + "and ano=? and equipamento=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaFevereiro(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 02;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE mes=? "
                    + "and ano=? and equipamento=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaMarco(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 03;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE mes=? "
                    + "and ano=? and equipamento=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaAbril(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 04;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda "
                    + "WHERE mes=? and ano=? and equipamento=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaMaio(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 05;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE mes=? "
                    + "and ano=? and equipamento=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaJunho(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 06;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE mes=? "
                    + "and ano=? and equipamento=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaJulho(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 07;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE mes=? and "
                    + "ano=? and equipamento=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaAgosto(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 8;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE mes=? and "
                    + "ano=? and equipamento=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaSetembro(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 9;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE mes=? and "
                    + "ano=? and equipamento=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaOutubro(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 10;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE mes=? and ano=? "
                    + "and equipamento=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaNovembro(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 11;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE mes=? and ano=?"
                    + " and equipamento=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaDezembro(Demanda d) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 12;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(demandacalc) AS total FROM demanda WHERE mes=? and "
                    + "ano=? and equipamento=?");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, d.getEquipamento());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaJaneiroBairro(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 01;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda AS d INNER JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and c.bairro=? ");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, c.getBairro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaFevereiroBairro(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 02;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda AS d INNER JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and c.bairro=? ");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, c.getBairro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaMarcoBairro(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 03;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda AS d INNER JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and c.bairro=? ");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, c.getBairro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaAbrilBairro(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 04;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda AS d INNER JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and c.bairro=? ");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, c.getBairro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaMaioBairro(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 05;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda AS d INNER JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and c.bairro=? ");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, c.getBairro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaJunhoBairro(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 06;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda AS d INNER JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and c.bairro=? ");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, c.getBairro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaJulhoBairro(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 07;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda AS d INNER JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and c.bairro=? ");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, c.getBairro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaAgostoBairro(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 8;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda AS d INNER JOIN"
                    + " cliente AS c ON d.mes=? and d.ano=? and c.bairro=? ");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, c.getBairro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaSetembroBairro(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 9;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda AS d INNER JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and c.bairro=? ");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, c.getBairro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaOutubroBairro(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 10;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda AS d INNER JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and c.bairro=? ");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, c.getBairro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaNovembroBairro(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 11;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda AS d INNER JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and c.bairro=? ");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, c.getBairro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
    public double SomademandaDezembroBairro(Demanda d, Cliente c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int mes = 12;
        double soma = 0;
        try {
            stmt = con.prepareStatement("SELECT SUM(d.demandamed) AS total FROM demanda AS d INNER JOIN "
                    + "cliente AS c ON d.mes=? and d.ano=? and c.bairro=? ");
            stmt.setInt(1, mes);
            stmt.setInt(2, d.getAno());
            stmt.setString(3, c.getBairro());
            rs = stmt.executeQuery();
            while (rs.next()) {
                soma = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return soma;
    }
}
