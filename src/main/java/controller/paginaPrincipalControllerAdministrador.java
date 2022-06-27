/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AlumnoDAO;
import dao.MateriaDAO;
import dao.grupoDAO;
import dao.maestroDAO;
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
import models.Alumno;
import models.Grupo;
import models.Maestro;
import models.Materia;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "paginaPrincipalControllerAdministrador", urlPatterns = {"/paginaPrincipalControllerAdministrador"})
public class paginaPrincipalControllerAdministrador extends HttpServlet {

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
            out.println("<title>Servlet paginaPrincipalControllerAdministrador</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet paginaPrincipalControllerAdministrador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("noEmpleado")!=null){
            List<Maestro> listaMaestros=new ArrayList<>();
            List<Alumno> listaAlumnos=new ArrayList<>();
            List<Materia> listaMaterias=new ArrayList<>();
            List<Grupo> listaGrupos=new ArrayList<>();
            try {
                listaMaestros=maestroDAO.getMaestros();
                listaAlumnos=AlumnoDAO.getAlumnos();
                listaMaterias=MateriaDAO.getMaterias();
                listaGrupos=grupoDAO.getGrupos();
            } catch (SQLException ex) {
                Logger.getLogger(paginaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("listaMaestros", listaMaestros);
            request.setAttribute("listaAlumnos", listaAlumnos);
            request.setAttribute("listaMaterias", listaMaterias);
            request.setAttribute("listaGrupos", listaGrupos);
            request.getRequestDispatcher("PaginaPrincipalAdmin.jsp").forward(request, response);
        }
        else{
            response.setContentType("text/html;charset=UTF-8");
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>botate alv .l.</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Botate alv .l." + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        
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
