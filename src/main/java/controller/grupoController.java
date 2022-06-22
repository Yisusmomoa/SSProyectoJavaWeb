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
//session.getAttribute("usuario")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        
        if (session.getAttribute("noEmpleado")!=null ) {
            String opcion=request.getParameter("opcion");
        
            String idGrupo=request.getParameter("idGrupo");

            String materiaId=request.getParameter("materiaId");
            String inputnumAlumnosGrupo=request.getParameter("inputnumAlumnosGrupo");
            String inputEstatusGrupo=request.getParameter("inputEstatusGrupo");


            Grupo grupo=null;
            List<Grupo> listaGrupos=new ArrayList<>();
            String Tabla;
            Tabla="";
            switch(opcion){
                case "Eliminar":{
                    try {
                        if (grupoDAO.eliminarGrupo(Integer.parseInt(idGrupo))==1) {
                            listaGrupos=grupoDAO.getGrupos();
                            json.put("msj", "Registro eliminado con éxito");
                            json.put("status", 200);

                            for(Grupo grupoAux: listaGrupos){
                                    Tabla=Tabla.concat("<tr>");

                                       Tabla=Tabla.concat("<th scope='row'>");
                                        Tabla=Tabla.concat(Integer.toString(grupoAux.getIdGrupo()));
                                       Tabla=Tabla.concat("</th>");

                                       Tabla=Tabla.concat("<td>");
                                            Tabla=Tabla.concat(grupoAux.getMateria().getNombreMateria());
                                       Tabla=Tabla.concat("</td>");

                                        Tabla=Tabla.concat("<td>");
                                            Tabla=Tabla.concat(Integer.toString(grupoAux.getNumAlumnos()));
                                        Tabla=Tabla.concat("</td>");

                                        Tabla=Tabla.concat("<td>");
                                            Tabla=Tabla.concat(Integer.toString(grupoAux.getClaveMateriaGrupo()));
                                        Tabla=Tabla.concat("</td>");

                                        Tabla=Tabla.concat("<td>");
                                            Tabla=Tabla.concat(Boolean.toString(grupoAux.isEstatus()));
                                        Tabla=Tabla.concat("</td>");

                                        Tabla=Tabla.concat("<td class='text-center align-middle'>");
                                            Tabla=Tabla.concat("<button type='button' value='Eliminar' id='EliminarGrupo' class='btn btn-danger'>Eliminar</button>");
                                            Tabla=Tabla.concat("<button type='button' id='EditarGrupo' value='EditarGetInfoGrupo' data-bs-toggle='modal' data-bs-target='#EditGrupo' class='btn btn-secondary'>Editar</button>");
                                        Tabla=Tabla.concat("</td>");

                                    Tabla=Tabla.concat("</tr>");
                                }
                                Tabla=Tabla.concat("</tbody>");
                                Tabla=Tabla.concat("</table>");
                                json.put("listaGrupos", Tabla);

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
                                listaGrupos=grupoDAO.getGrupos();

                                json.put("msj", "Registro editado con éxito");
                                json.put("status", 200);

                                for(Grupo grupoAux: listaGrupos){
                                    Tabla=Tabla.concat("<tr>");

                                       Tabla=Tabla.concat("<th scope='row'>");
                                        Tabla=Tabla.concat(Integer.toString(grupoAux.getIdGrupo()));
                                       Tabla=Tabla.concat("</th>");

                                       Tabla=Tabla.concat("<td>");
                                            Tabla=Tabla.concat(grupoAux.getMateria().getNombreMateria());
                                       Tabla=Tabla.concat("</td>");

                                        Tabla=Tabla.concat("<td>");
                                            Tabla=Tabla.concat(Integer.toString(grupoAux.getNumAlumnos()));
                                        Tabla=Tabla.concat("</td>");

                                        Tabla=Tabla.concat("<td>");
                                            Tabla=Tabla.concat(Integer.toString(grupoAux.getClaveMateriaGrupo()));
                                        Tabla=Tabla.concat("</td>");

                                        Tabla=Tabla.concat("<td>");
                                            Tabla=Tabla.concat(Boolean.toString(grupoAux.isEstatus()));
                                        Tabla=Tabla.concat("</td>");

                                        Tabla=Tabla.concat("<td class='text-center align-middle'>");
                                            Tabla=Tabla.concat("<button type='button' value='Eliminar' id='EliminarGrupo' class='btn btn-danger'>Eliminar</button>");
                                            Tabla=Tabla.concat("<button type='button' id='EditarGrupo' value='EditarGetInfoGrupo' data-bs-toggle='modal' data-bs-target='#EditGrupo' class='btn btn-secondary'>Editar</button>");
                                        Tabla=Tabla.concat("</td>");

                                    Tabla=Tabla.concat("</tr>");
                                }
                                Tabla=Tabla.concat("</tbody>");
                                Tabla=Tabla.concat("</table>");
                                json.put("listaGrupos", Tabla);


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
