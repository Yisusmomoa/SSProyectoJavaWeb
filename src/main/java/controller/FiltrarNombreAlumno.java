/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AlumnoDAO;
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
import org.json.JSONObject;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "FiltrarNombreAlumno", urlPatterns = {"/FiltrarNombreAlumno"})
public class FiltrarNombreAlumno extends HttpServlet {

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
            out.println("<title>Servlet FiltrarNombreAlumno</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FiltrarNombreAlumno at " + request.getContextPath() + "</h1>");
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
         HttpSession session = request.getSession();
        
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        
        String FiltrarNombreAlumno=request.getParameter("search");
        List<Alumno> listaAlumnos=new ArrayList<>();
        
        try {
            listaAlumnos=AlumnoDAO.getBusquedaAlumno(FiltrarNombreAlumno);
            json.put("status", 200);
            String Tabla;
            Tabla="";
            for(Alumno alumnoAux: listaAlumnos){
                Tabla=Tabla.concat("<tr>");

                  Tabla=Tabla.concat("<th scope='row'>");
                   Tabla=Tabla.concat(Integer.toString(alumnoAux.getMatricula()));
                  Tabla=Tabla.concat("</th>");

                  Tabla=Tabla.concat("<td>");
                   Tabla=Tabla.concat(alumnoAux.getNombre());
                  Tabla=Tabla.concat("</td>");

                  Tabla=Tabla.concat("<td>");
                   Tabla=Tabla.concat(alumnoAux.getUsuario());
                  Tabla=Tabla.concat("</td>");

                  Tabla=Tabla.concat("<td>");
                   Tabla=Tabla.concat(Boolean.toString(alumnoAux.isEstatus()));
                  Tabla=Tabla.concat("</td>");

                  Tabla=Tabla.concat("<td class='text-center align-middle'>");
                   Tabla=Tabla.concat("<button type='button' value='Eliminar' id='EliminarAlumno' class='btn btn-danger'>Eliminar</button>");
                   Tabla=Tabla.concat("<button type='button' id='EditarAlumno' value='EditarGetInfoAlumno' data-bs-toggle='modal' data-bs-target='#editAlumno' class='btn btn-secondary'>Editar</button>");
                  Tabla=Tabla.concat("</td>");

               Tabla=Tabla.concat("</tr>");
           }
            json.put("listaAlumnos", Tabla);
            out.println(json);
        } catch (SQLException ex) {
            Logger.getLogger(FiltrarNombreAlumno.class.getName()).log(Level.SEVERE, null, ex);
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
