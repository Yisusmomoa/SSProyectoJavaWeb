/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.MateriaDAO;
import dao.grupoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Grupo;
import models.Materia;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "paginaPrincipalControllerAlumno", urlPatterns = {"/paginaPrincipalControllerAlumno"})
public class paginaPrincipalControllerAlumno extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet paginaPrincipalControllerAlumno</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet paginaPrincipalControllerAlumno at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Materia> listaMaterias=new ArrayList<>();
        List<Grupo> listaGrupos = new ArrayList<>();
        List<Grupo> listaGruposAlumno=new ArrayList<>();
        HttpSession session=request.getSession();
        Object matricula=session.getAttribute("matricula");
        try {
            listaMaterias=MateriaDAO.getMaterias();
            listaGrupos=grupoDAO.getGrupos();
            listaGruposAlumno=grupoDAO.getMateriasByAlumno((int)matricula);
        } catch (SQLException ex) {
            Logger.getLogger(paginaPrincipalControllerAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaMaterias", listaMaterias);
        request.setAttribute("listaGrupos", listaGrupos);
        request.setAttribute("listaGruposAlumno", listaGruposAlumno);
        request.getRequestDispatcher("PaginaPrincipalAlumno.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
