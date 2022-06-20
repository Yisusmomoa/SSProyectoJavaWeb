/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import dao.grupoDAO;
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
import models.Grupo;
import org.json.JSONObject;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "grupoController", urlPatterns = {"/grupoController"})
public class grupoController extends HttpServlet {

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
            out.println("<title>Servlet grupoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet grupoController at " + request.getContextPath() + "</h1>");
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
        String opcion=request.getParameter("opcion");
        
        String idGrupo=request.getParameter("idGrupo");
        
        String materiaId=request.getParameter("materiaId");
        String inputnumAlumnosGrupo=request.getParameter("inputnumAlumnosGrupo");
        String inputEstatusGrupo=request.getParameter("inputEstatusGrupo");
        
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        Grupo grupo;
        switch(opcion){
            case "Eliminar":{
                try {
                    if (grupoDAO.eliminarGrupo(Integer.parseInt(idGrupo))==1) {
                        json.put("msj", "Registro eliminado con éxito");
                        json.put("status", 200);
                        out.println(json);
                    }
                    else{
                        json.put("msj", "Error ");
                        json.put("status", 400);
                        out.println(json); 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(grupoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "EditarGetInfoGrupo":{
                try {
                    grupo=grupoDAO.getGrupoByID(Integer.parseInt(idGrupo));
                    if (grupo!=null) {
                        //convierte el obj a un json, luego a un string
                        //luego en js lo vuelvo a convertir a un json
                        String grupoJsonString = new Gson().toJson(grupo);
                        json.put("Grupo", grupoJsonString);
                        json.put("status", 200);
                        out.println(json);
                    }
                    else{
                        json.put("msj", "Error ");
                        json.put("status", 400);
                        out.println(json);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(grupoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "EditarGrupoSetInfo":{
                try {
                    grupo=grupoDAO.getGrupoByID(Integer.parseInt(idGrupo));
                    if (grupo!=null) {
                        if (grupo.getClaveMateriaGrupo()!=Integer.parseInt(materiaId)) {
                            grupo.setClaveMateriaGrupo(Integer.parseInt(materiaId));
                        }
                        if (grupo.getNumAlumnos()!=Integer.parseInt(inputnumAlumnosGrupo)) {
                            grupo.setNumAlumnos(Integer.parseInt(inputnumAlumnosGrupo));
                        }
                        if (grupo.isEstatus()!=Boolean.parseBoolean(inputEstatusGrupo)) {
                            grupo.setEstatus(Boolean.parseBoolean(inputEstatusGrupo));
                        }
                        if (grupoDAO.editarGrupo(grupo)==1) {
                            json.put("msj", "Registro editado con éxito");
                            json.put("status", 200);
                            out.println(json);
                        }
                        else{
                            json.put("msj", "Error ");
                            json.put("status", 400);
                            out.println(json); 
                        }
                    }
                    else{
                        json.put("msj", "Error ");
                        json.put("status", 400);
                        out.println(json); 
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(grupoController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            default:{
                json.put("msj", "Error ");
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
