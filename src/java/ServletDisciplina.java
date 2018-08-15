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
@WebServlet(urlPatterns = {"/ServletDisciplina"})
public class ServletDisciplina extends HttpServlet {

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
        
        Conexao conect = new Conexao();
        conect.Connection();
        String nomeAluno = conect.verificarMatricula(matricula);
        
        ArrayList<Turmas> turmas = conect.disciplinas();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Solicitação de Inclusão nas Disciplinas</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"disciplinas.css\">");
            out.println("</head>");
            out.println("<body>");
            
            
            if( nomeAluno == null){
                out.println("<div class=\"invalida\">");
                out.println("<h1>" + "O seguinte aluno não está matriculado!" + "</h1>");
                out.println("<h4>" + " Insira uma matrícula valida. " + "</h4>");
                out.println("</div>");
            } else{
                out.println(""
                      + "<section class=\"container\">\n" +
                "            \n" +
                "          <form action='ServletAluno' method='post'>" +
                "            <table>\n" +
                "                <thead>\n" +
                "                    <caption>Solicitação de Inclusão nas Disciplinas</caption>\n" +
                "                    <tr class=\"cabecalho\">\n" +
                "                        <th>Código da Disciplina</th>\n" +
                "                        <th>Nome da Disciplina</th>\n" +
                "                        <th>Carga horaria</th>\n" +
                "                        <th>Código do Curso</th>\n" +
                "                        <th>Horario1</th>\n" +
                "                        <th>Horario2</th>\n" +
                "                        <th>Horario3</th>\n" +
                "                        <th>Inclusão na Disciplina</th>\n" +
                "                    </tr>\n" +
                "                </thead>\n" +
                "                \n" +
                "                <tbody>\n"
                );
                for(Turmas turma : turmas){
                    out.println("<tr><td>"+ turma.getCodDisciplina() + "</td>" +
                                  "<td>"+ turma.getNomeDis() + "</td>" +
                                  "<td>" + turma.getCargaHoraria() + "</td>" +
                                  "<td>"+ turma.getCodTurma() + "</td>" +
                                  "<td>"+ turma.getHorario1() + "</td>" +
                                  "<td>"+ turma.getHorario2() + "</td>" +
                                  "<td>"+ turma.getHorario3() +
                                  "<td>"+ "<input type='checkbox' name ='turma' value="+ turma.getCodTurma() + ">" + "</td>" + "</td></tr>");
                }
                out.println(
                "                </tbody>\n" +
                "            </table>\n" +
                "            \n" +
                "                <input type=\"reset\" name=\"limpar\" id=\"limpar\" value=\"Limpar\">" +
                "                <input type = 'hidden' name = 'matricula' value ='" + matricula + "'/>" +
                "                <input type='submit' name='solicitarDisc' value='Solicitar'>");
                  
                            out.println(
                "         </form>" +
                "      </section>");
                }
                out.println("<footer>&copy; Rivanildo Júnior - Todos os Direitos Reservados.</footer>");
                out.println("</body>");
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
