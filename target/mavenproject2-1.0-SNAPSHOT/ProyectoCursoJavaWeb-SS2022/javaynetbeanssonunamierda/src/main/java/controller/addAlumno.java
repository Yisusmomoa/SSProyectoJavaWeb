/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AlumnoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Alumno;
import org.json.JSONObject;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "addAlumno", urlPatterns = {"/addAlumno"})
public class addAlumno extends HttpServlet {

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
            out.println("<title>Servlet addAlumno</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addAlumno at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String InputNombre=request.getParameter("InputNombre");
        String InputUsuario=request.getParameter("InputUsuario");
        String Inputcontrase??a=request.getParameter("Inputcontrase??a");
        Alumno alumno=new Alumno(InputUsuario,InputNombre,Inputcontrase??a);
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        if (AlumnoDAO.addAlumno(alumno)==1) {
            json.put("msj", "Alumno agregado con exito");
            json.put("status", 200);
            out.println(json);
        }
        else{
            json.put("msj", "Error ");
            json.put("status", 400);
            out.println(json); 
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
