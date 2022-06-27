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
import org.json.JSONObject;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "BusquedaController", urlPatterns = {"/BusquedaController"})
public class BusquedaController extends HttpServlet {

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
            out.println("<title>Servlet BusquedaController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BusquedaController at " + request.getContextPath() + "</h1>");
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
        String inputBusqueda=request.getParameter("inputBusqueda");
        String floatingSelect=request.getParameter("floatingSelect");
        
        List<Maestro> listaMaestros=new ArrayList<>();
        List<Alumno> listaAlumnos=new ArrayList<>();
        List<Grupo> listaGrupos=new ArrayList<>();
        List<Materia> listaMaterias=new ArrayList<>();
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        /*
        <option value="1">Maestro</option>
        <option value="2">Alumno</option>
        <option value="3">Materia</option>
        <option value="3">Grupo</option>
        */
        switch(floatingSelect){
            case "1":{
                try {
                    listaMaestros=maestroDAO.getBusquedaMaestro(inputBusqueda);
                    request.setAttribute("listaMaestros",listaMaestros); //?
                    request.getRequestDispatcher("BusquedaMaestros.jsp").forward(request, response);
                    /*json.put("lista", listaMaestros);
                    json.put("status", 200);
                    out.println(json);*/
                } catch (SQLException ex) {
                    Logger.getLogger(BusquedaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "2":{
                try {
                    listaAlumnos=AlumnoDAO.getBusquedaAlumno(inputBusqueda);
                    request.setAttribute("listaAlumnos",listaAlumnos); //?
                    request.getRequestDispatcher("BusquedaAlumnos.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(BusquedaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                /*json.put("lista", listaAlumnos);
                json.put("status", 200);
                out.println(json);*/
                break;
            }
            case "3":{
                try {
                    listaGrupos=grupoDAO.getBusquedaGrupos(inputBusqueda);
                    request.setAttribute("listaGrupos",listaGrupos); //?
                    
                    listaMaterias=MateriaDAO.getMaterias();
                    request.setAttribute("listaMaterias", listaMaterias);
                    
                    request.getRequestDispatcher("BusquedaGrupos.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(BusquedaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                /*json.put("lista", listaGrupos);
                json.put("status", 200);
                out.println(json);*/
                break;
            }
            case "4":{
                try {
                    listaMaterias=MateriaDAO.getBusquedaMateria(inputBusqueda);
                    request.setAttribute("listaMaterias",listaMaterias); //?
                    request.getRequestDispatcher("BusquedaMaterias.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(BusquedaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                /*json.put("lista", listaMaterias);
                json.put("status", 200);
                out.println(json);*/
                break;
            }
            default:{
                    json.put("Error", "No se encontró resultado alguno");
                    json.put("status", 400);
                    out.println(json); 
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
