/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AlumnoDAO;
import dao.maestroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Alumno;
import models.Maestro;
import org.json.JSONObject;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "logInController", urlPatterns = {"/logInController"})
public class logInController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet logInController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet logInController at " + request.getContextPath() + "</h1>");
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
        String InputUsuario=request.getParameter("InputUsuario");
        String InputContraseña=request.getParameter("InputContraseña");
        String selectTipoUsuario=request.getParameter("selectTipoUsuario");
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        if (selectTipoUsuario.equals("1")) {
            Maestro maestro=new Maestro(InputContraseña,InputUsuario);
                Maestro maestroResult;
                maestroResult = maestroDAO.logInMaestro(maestro);
                if (maestroResult!=null){
                    HttpSession session=request.getSession();
                    session.setAttribute("noEmpleado", maestroResult.getNoEmpleado());
                    session.setAttribute("nombreMaestro", maestroResult.getNombreMaestro());
                    session.setAttribute("usuario", maestroResult.getUsuario());
                    session.setAttribute("tipo", maestroResult.getTipo());
                    session.setAttribute("estatus", maestroResult.isEstatus());
                    
                    json.put("usuario", maestroResult.getUsuario());
                    json.put("Tipo", maestroResult.getTipo());
                    json.put("status", 200);
                    out.println(json);
                    
                    //response.sendRedirect("./ProyectoCursoJavaWeb-SS2022/paginaPrincipal/PaginaPrincipal.jsp");
                }
                else{
                    json.put("usuario", "");
                    json.put("status", 400);
                    out.println(json);
                    //response.sendRedirect("Fail.jsp");
                } 
        }
        else if(selectTipoUsuario.equals("3")){
            Alumno alumno=new Alumno(InputUsuario,InputContraseña);
            Alumno AlumnoResult =AlumnoDAO.logInEstudiante(alumno);
                if (AlumnoResult!=null){
                    HttpSession session=request.getSession();
                    session.setAttribute("matricula", AlumnoResult.getMatricula());
                    session.setAttribute("usuario", AlumnoResult.getUsuario());
                    session.setAttribute("nombre", AlumnoResult.getNombre());
                    session.setAttribute("estatus", AlumnoResult.isEstatus());
                    
                    json.put("usuario", AlumnoResult.getUsuario());
                    json.put("Tipo", "Alumno");
                    json.put("status", 200);
                    out.println(json);
                    
                    //response.sendRedirect("./ProyectoCursoJavaWeb-SS2022/paginaPrincipal/PaginaPrincipal.jsp");
                }
                else{
                    json.put("usuario", "");
                    json.put("status", 400);
                    out.println(json);
                    //response.sendRedirect("Fail.jsp");
                } 
        }
        else{
            Maestro maestro=new Maestro(InputContraseña,InputUsuario);
                Maestro maestroResult;
                maestroResult = maestroDAO.logInMaestro(maestro);
                if (maestroResult!=null){
                    HttpSession session=request.getSession();
                    session.setAttribute("noEmpleado", maestroResult.getNoEmpleado());
                    session.setAttribute("nombreMaestro", maestroResult.getNombreMaestro());
                    session.setAttribute("usuario", maestroResult.getUsuario());
                    session.setAttribute("tipo", maestroResult.getTipo());
                    session.setAttribute("estatus", maestroResult.isEstatus());
                    
                    json.put("usuario", maestroResult.getUsuario());
                    json.put("Tipo", maestroResult.getTipo());
                    json.put("status", 200);
                    out.println(json);
                    
                    //response.sendRedirect("./ProyectoCursoJavaWeb-SS2022/paginaPrincipal/PaginaPrincipal.jsp");
                }
                else{
                    json.put("usuario", "");
                    json.put("status", 400);
                    out.println(json);
                    //response.sendRedirect("Fail.jsp");
                }  
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
