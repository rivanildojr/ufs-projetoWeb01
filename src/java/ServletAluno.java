/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RivasJr
 */
@WebServlet(urlPatterns = {"/ServletAluno"})
public class ServletAluno extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String matricula = request.getParameter("matricula");
        String [] turmas = request.getParameterValues("turma");
        
        
        Conexao conect = new Conexao();
        conect.Connection();
        ArrayList<Aluno> nomeAluno = conect.matriculasSolic(matricula);
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Solicitação de Matrícula</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"disciplinas.css\">");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"solicitacao.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container aluno\">\n" +
"            <header>\n" +
"                <h1>Solicitação de Matrícula</h1>\n" +
"            </header>\n" +
"            \n" +
"           <div class=\"info\">\n" +
"                <h2>Dados do Aluno</h2>\n");
                for(Aluno aluno : nomeAluno){
                    out.println(
                        "<h3>" + aluno.getNomeAluno() + "</h3>" +
                        "<h3>Matricula: "+ aluno.getMatriculaAluno() + "</h3>" +
                        "<h3>Curso: " + aluno.getNomeCurso() + "</h3>" +
                        "<h3>Código do Curso: "+ aluno.getCodCurso() + "</h3>");
                }
             out.println(
"            </div>" +
"            <form>" +
"                <table>" +
"                    <thead>" +
"                        <tr class=\"cabecalho\">\n" +
"                            <th>Código da Disciplina</th>\n" +
"                            <th>Nome da Disciplina</th>\n" +
"                            <th>Carga horaria</th>\n" +
"                            <th>Código do Curso</th>\n" +
"                            <th>Horario1</th>\n" +
"                            <th>Horario2</th>\n" +
"                            <th>Horario3</th>\n" +
"                        </tr>\n" +
"                    </thead>\n" +
"\n" +
"                    <tbody>\n");
             for(int i=0; i<turmas.length; i++){
                 Turmas turma = conect.solicitacao(turmas[i]);
                 System.out.println(turmas[i]);
                out.println(" <tr><td>"+ turma.getCodDisciplina() + "</td>" +
                                  "<td>"+ turma.getNomeDis() + "</td>" +
                                  "<td>" + turma.getCargaHoraria() + "</td>" +
                                  "<td>"+ turma.getCodTurma() + "</td>" +
                                  "<td>"+ turma.getHorario1() + "</td>" +
                                  "<td>"+ turma.getHorario2() + "</td>" +
                                  "<td>"+ turma.getHorario3() +
"                        </td></tr>\n");
             }
             out.println(
"                    </tbody>\n" +
"                </table>\n" +
"                <button type=\"button\" name=\"voltar\" onclick=\"history.back(-1)\">Voltar</button>\n" +
"            </form>\n" +
"        </div>");
            out.println("</body>");
            out.println("<footer>&copy; Rivanildo Júnior - Todos os Direitos Reservados.</footer>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
