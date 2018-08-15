
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RivasJr
 */
public class Conexao {
    
    Connection conexao = null;
    
    public void Connection (){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/projetoweb1", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String verificarMatricula(String matricula){
        String nome = null;
        try {
            Statement st = conexao.createStatement();
            ResultSet matriculaAluno = st.executeQuery("select nomeAluno from alunos where matriculaAluno = '" + matricula + "'");
              
            while(matriculaAluno.next()){    
                nome = matriculaAluno.getString("nomeAluno");
            }
            
            return nome;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nome;
    }
    public ArrayList<Turmas> disciplinas(){
        ArrayList<Turmas> tabelaDisc = new ArrayList();
        Turmas turmas = null;
        try {
            Statement st = conexao.createStatement();
            ResultSet solicitarDisc = st.executeQuery("select codDisciplina, nomeDis, cargaHoraria, codTurma, horario1, horario2, horario3 from turmas t inner join disciplina d on (t.codDisc = d.codDisciplina)");
              
            while(solicitarDisc.next()){  
                turmas = new Turmas();
                
                turmas.setCodDisciplina(solicitarDisc.getString("codDisciplina"));
                turmas.setNomeDis(solicitarDisc.getString("nomeDis"));
                turmas.setCargaHoraria(solicitarDisc.getString("cargaHoraria"));
                turmas.setCodTurma(solicitarDisc.getString("codTurma"));
                turmas.setHorario1(solicitarDisc.getString("horario1"));
                turmas.setHorario2(solicitarDisc.getString("horario2"));
                turmas.setHorario3(solicitarDisc.getString("horario3"));
                
                tabelaDisc.add(turmas);
            }
            
            return tabelaDisc;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("sdfasdf");
        }
        return tabelaDisc;
    }
    
    public ArrayList<Aluno> matriculasSolic(String matricula){
        ArrayList<Aluno> dadosAlunos = new ArrayList();
        Aluno dAluno = null;
        try {
            Statement st = conexao.createStatement();
            ResultSet aluno = st.executeQuery("select nomeAluno, matriculaAluno, nomeCurso, codCurso from alunos a inner join curso c on (a.curso = c.codCurso) where a.matriculaAluno = '" + matricula + "'");
              
            while(aluno.next()){
                dAluno = new Aluno();
                
                dAluno.setNomeAluno(aluno.getString("nomeAluno"));
                dAluno.setMatriculaAluno(aluno.getString("matriculaAluno"));
                dAluno.setNomeCurso(aluno.getString("nomeCurso"));
                dAluno.setCodCurso(aluno.getString("codCurso"));
                
                dadosAlunos.add(dAluno);
            }
            
            return dadosAlunos;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dadosAlunos;
    }
    
    public Turmas solicitacao(String codTurma){
        Turmas turmas = null;
        try {
            Statement st = conexao.createStatement();
            ResultSet solicitarDisc = st.executeQuery("select codDisciplina, nomeDis, cargaHoraria, codTurma, horario1, horario2, horario3 from turmas t inner join disciplina d on (t.codDisc = d.codDisciplina) where t.codTurma = '" + codTurma +"'");
              
            while(solicitarDisc.next()){  
                turmas = new Turmas();
                
                turmas.setCodDisciplina(solicitarDisc.getString("codDisciplina"));
                turmas.setNomeDis(solicitarDisc.getString("nomeDis"));
                turmas.setCargaHoraria(solicitarDisc.getString("cargaHoraria"));
                turmas.setCodTurma(solicitarDisc.getString("codTurma"));
                turmas.setHorario1(solicitarDisc.getString("horario1"));
                turmas.setHorario2(solicitarDisc.getString("horario2"));
                turmas.setHorario3(solicitarDisc.getString("horario3"));
            }
            
            return turmas;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("sdfasdf");
        }
        return turmas;
    }
    
}
