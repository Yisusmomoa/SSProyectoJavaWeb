/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import dao.MateriaDAO;
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
import models.Materia;
import org.json.JSONObject;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "materiaController", urlPatterns = {"/materiaController"})
public class materiaController extends HttpServlet {

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
            out.println("<title>Servlet materiaController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet materiaController at " + request.getContextPath() + "</h1>");
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
        
        String opcion=request.getParameter("opcion");
        
        String idMateria=request.getParameter("idMateria");
        String inputNombreMateria=request.getParameter("inputNombreMateria");
        String inputEstatusMateria=request.getParameter("inputEstatusMateria");
        
        List<Materia> listaMaterias=new ArrayList<>();
        Materia materia = null;
        
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        
        if (session.getAttribute("noEmpleado")!=null) {
                switch(opcion){
                    case "Eliminar":{
                        try {
                            if (MateriaDAO.eliminarMateria(Integer.parseInt(idMateria))==1) {
                                    json.put("msj", "Registro eliminado con éxito");
                                    json.put("status", 200);
                                    listaMaterias=MateriaDAO.getMaterias();

                                    String Tabla;
                                    Tabla="";
                                    for(Materia materiaAux: listaMaterias){
                                        Tabla=Tabla.concat("<tr>");

                                           Tabla=Tabla.concat("<th scope='row'>");
                                            Tabla=Tabla.concat(Integer.toString(materiaAux.getClaveMateria()));
                                           Tabla=Tabla.concat("</th>");

                                           Tabla=Tabla.concat("<td>");
                                            Tabla=Tabla.concat(materiaAux.getNombreMateria());
                                           Tabla=Tabla.concat("</td>");

                                           Tabla=Tabla.concat("<td>");
                                            Tabla=Tabla.concat(Boolean.toString(materiaAux.isEstatus()));
                                           Tabla=Tabla.concat("</td>");

                                           Tabla=Tabla.concat("<td class='text-center align-middle'>");
                                            Tabla=Tabla.concat("<button type='button' value='Eliminar' id='EliminarMateria' class='btn btn-danger'>Eliminar</button>");
                                            Tabla=Tabla.concat("<button type='button' id='EditarMateria' value='EditarGetInfoMateria' data-bs-toggle='modal' data-bs-target='#EditMateria' class='btn btn-secondary'>Editar</button>");
                                           Tabla=Tabla.concat("</td>");

                                        Tabla=Tabla.concat("</tr>");
                                    }
                                    Tabla=Tabla.concat("</tbody>");
                                    Tabla=Tabla.concat("</table>");
                                    json.put("listaMaterias", Tabla);
                                    out.println(json);
                            }
                            else{
                                json.put("msj", "Error ");
                                json.put("status", 400); 
                                out.println(json);  
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(materiaController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case "EditarGetInfoMateria":{
                        try {
                            materia=MateriaDAO.getMateriaByID(Integer.parseInt(idMateria));
                            if (materia!=null) {
                                String materiaJsonString = new Gson().toJson(materia);
                                json.put("Materia", materiaJsonString);
                                json.put("status", 200);
                                out.println(json);
                            }
                            else{
                                json.put("msj", "Error ");
                                json.put("status", 400); 
                                out.println(json);   
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(materiaController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        break;
                    }
                    case "EditarMateriaSetInfo":{

                        try {
                            materia=MateriaDAO.getMateriaByID(Integer.parseInt(idMateria));
                            if (materia.getNombreMateria()!=inputNombreMateria) {
                                materia.setNombreMateria(inputNombreMateria);
                            }
                            if (materia.isEstatus()!=Boolean.parseBoolean(inputEstatusMateria)) {
                                materia.setEstatus(Boolean.parseBoolean(inputEstatusMateria));
                            }
                            if (MateriaDAO.editarMateria(materia)==1) {
                                json.put("msj", "Registro editado con éxito");
                                json.put("status", 200);
                                listaMaterias=MateriaDAO.getMaterias();

                                String Tabla;

                                Tabla="";
                                    for(Materia materiaAux: listaMaterias){
                                        Tabla=Tabla.concat("<tr>");

                                           Tabla=Tabla.concat("<th scope='row'>");
                                            Tabla=Tabla.concat(Integer.toString(materiaAux.getClaveMateria()));
                                           Tabla=Tabla.concat("</th>");

                                           Tabla=Tabla.concat("<td>");
                                            Tabla=Tabla.concat(materiaAux.getNombreMateria());
                                           Tabla=Tabla.concat("</td>");

                                           Tabla=Tabla.concat("<td>");
                                            Tabla=Tabla.concat(Boolean.toString(materiaAux.isEstatus()));
                                           Tabla=Tabla.concat("</td>");

                                           Tabla=Tabla.concat("<td class='text-center align-middle'>");
                                            Tabla=Tabla.concat("<button type='button' value='Eliminar' id='EliminarMateria' class='btn btn-danger'>Eliminar</button>");
                                            Tabla=Tabla.concat("<button type='button' id='EditarMateria' value='EditarGetInfoMateria' data-bs-toggle='modal' data-bs-target='#EditMateria' class='btn btn-secondary'>Editar</button>");
                                           Tabla=Tabla.concat("</td>");

                                        Tabla=Tabla.concat("</tr>");
                                    }
                                Tabla=Tabla.concat("</tbody>");
                                Tabla=Tabla.concat("</table>");
                                json.put("listaMaterias", Tabla);
                                out.println(json);
                            }
                            else{
                                json.put("msj", "Error ");
                                json.put("status", 400);
                                out.println(json);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(materiaController.class.getName()).log(Level.SEVERE, null, ex);
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
        else{
            json.put("msj", "Error, ya caduco tu sesión ");
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
