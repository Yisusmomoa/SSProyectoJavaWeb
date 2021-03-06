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
import models.Alumno;
import models.Grupo;
import models.Maestro;
import models.Materia;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "paginaPrincipalController", urlPatterns = {"/paginaPrincipalController"})
public class paginaPrincipalController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet paginaPrincipalController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet paginaPrincipalController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Maestro> listaMaestros=new ArrayList<>();
        List<Alumno> listaAlumnos=new ArrayList<>();
        List<Grupo> listaGrupos=new ArrayList<>();
        List<Materia> listaMaterias=new ArrayList<>();
        
        try {
            listaMaestros=maestroDAO.getMaestros();
            listaAlumnos=AlumnoDAO.getAlumnos();
            listaGrupos=grupoDAO.getGrupos();
            listaMaterias=MateriaDAO.getMaterias();
        } catch (SQLException ex) {
            Logger.getLogger(paginaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaMaestros", listaMaestros);
        request.setAttribute("listaAlumnos", listaAlumnos);
        request.setAttribute("listaGrupos", listaGrupos);
        request.setAttribute("listaMaterias", listaMaterias);
        request.getRequestDispatcher("PaginaPrincipal.jsp").forward(request, response);
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
