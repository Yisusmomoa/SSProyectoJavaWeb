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
import models.Grupo;
import models.Materia;
import org.json.JSONObject;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "altaGrupoController", urlPatterns = {"/altaGrupoController"})
public class altaGrupoController extends HttpServlet {

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
            out.println("<title>Servlet altaGrupoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet altaGrupoController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Materia> listaMaterias = null;
        try {
            listaMaterias = MateriaDAO.getMaterias();
        } catch (SQLException ex) {
            Logger.getLogger(altaGrupoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaMaterias", listaMaterias);
        request.getRequestDispatcher("AltaGrupo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int numAlumnos= Integer.parseInt(request.getParameter("numAlumnos")) ;
        int materiaId= Integer.parseInt(request.getParameter("materiaId"));
        Grupo grupo=new Grupo(materiaId,numAlumnos);
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        try {
            if (grupoDAO.addGrupo(grupo)==1) {
                json.put("msj", "Grupo creado con exito");
                json.put("status", 200);
                out.println(json);
            }
            else{
                json.put("msj", "Error ");
                json.put("status", 400); 
                out.println(json);
            }
        } catch (SQLException ex) {
            Logger.getLogger(altaGrupoController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
