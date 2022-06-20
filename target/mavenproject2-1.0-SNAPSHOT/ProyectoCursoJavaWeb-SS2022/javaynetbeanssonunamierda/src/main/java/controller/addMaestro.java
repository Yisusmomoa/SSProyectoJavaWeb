/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.maestroDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Maestro;
import org.json.JSONObject;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "addMaestro", urlPatterns = {"/addMaestro"})
public class addMaestro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addMaestro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addMaestro at " + request.getContextPath() + "</h1>");
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
        String Inputcontraseña=request.getParameter("Inputcontraseña");
        String tipoMaestro=request.getParameter("tipoMaestro");
        Maestro maestro=new Maestro(InputNombre,Inputcontraseña,
                InputUsuario,tipoMaestro);
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        if (maestroDAO.addMaestro(maestro)==1) {
            json.put("msj", "Maestro agregado con exito");
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
