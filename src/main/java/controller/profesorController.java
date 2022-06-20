/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
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
import models.Maestro;
import org.json.JSONObject;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "profesorController", urlPatterns = {"/profesorController"})
public class profesorController extends HttpServlet {

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
            out.println("<title>Servlet profesorController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet profesorController at " + request.getContextPath() + "</h1>");
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
        
        String idMaestro=request.getParameter("idMaestro");
        String contraseña=request.getParameter("contraseña");
        String nombreMaestro=request.getParameter("nombreMaestro");
        String usuario=request.getParameter("usuario");
        String estatus=request.getParameter("estatus");
        
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        
        Maestro maestro;
        
        switch(opcion){
                case "Eliminar":{
                    try {
                        if (maestroDAO.eliminarMaestro(Integer.parseInt(idMaestro))==1){
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
                        Logger.getLogger(profesorController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case "EditarMaestroSetInfo":{
                    try {
                        maestro=maestroDAO.getMaestroById(Integer.parseInt(idMaestro));
                        if (maestro!=null) {
                            if (nombreMaestro!=maestro.getNombreMaestro()) {
                                maestro.setNombreMaestro(nombreMaestro);
                            }
                            if (usuario!=maestro.getUsuario()) {
                                maestro.setUsuario(usuario);
                            }
                            if (Boolean.parseBoolean(estatus)!=maestro.isEstatus()) {
                                maestro.setEstatus(Boolean.parseBoolean(estatus));
                            }
                            maestro.setContraseña(contraseña);
                            if (maestroDAO.editarMaestro(maestro)==1) {
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
                        Logger.getLogger(profesorController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case "EditarGetInfo":{
                    try {
                        maestro=maestroDAO.getMaestroById(Integer.parseInt(idMaestro));
                        if (maestro!=null) {
                            //convierte el obj a un json, luego a un string
                            //luego en js lo vuelvo a convertir a un json
                            String maestroJsonString = new Gson().toJson(maestro);
                            json.put("Maestro", maestroJsonString);
                            json.put("status", 200);
                            out.println(json);
                        }
                        else{
                            json.put("msj", "Error ");
                            json.put("status", 400);
                            out.println(json);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(profesorController.class.getName()).log(Level.SEVERE, null, ex);
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
