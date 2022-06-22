/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
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
@WebServlet(name = "AlumnoController", urlPatterns = {"/AlumnoController"})
public class AlumnoController extends HttpServlet {

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
            out.println("<title>Servlet AlumnoController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AlumnoController at " + request.getContextPath() + "</h1>");
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
        
        String idAlumno=request.getParameter("idAlumno");
        String inputUsuarioAlumno=request.getParameter("inputUsuarioAlumno");
        String inputNombreAlumno=request.getParameter("inputNombreAlumno");
        String inputContraseñaAlumno=request.getParameter("inputContraseñaAlumno");
        String inputEstatusAlumno=request.getParameter("inputEstatusAlumno");
        
        
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        List<Alumno> listaAlumnos=new ArrayList<>();
        Alumno alumno;
        if (session.getAttribute("noEmpleado")!=null) {
                switch(opcion){
                case "Eliminar":{
                    try {
                        if (AlumnoDAO.elimimnarAlumno(Integer.parseInt(idAlumno))==1) {
                            json.put("msj", "Registro eliminado con éxito");
                            json.put("status", 200);

                            listaAlumnos=AlumnoDAO.getAlumnos();
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
                                Tabla=Tabla.concat("</tbody>");
                                Tabla=Tabla.concat("</table>");
                                json.put("listaAlumnos", Tabla);

                            out.println(json);
                        }
                        else{
                            json.put("msj", "Error ");
                            json.put("status", 400); 
                            out.println(json); 
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AlumnoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        break;
                }
                case "EditarGetInfoAlumno":{
                    try {
                        alumno=AlumnoDAO.getAlumnoByID(Integer.parseInt(idAlumno));
                        if (alumno!=null) {
                            //convierte el obj a un json, luego a un string
                            //luego en js lo vuelvo a convertir a un json
                            String alumnoJsonString = new Gson().toJson(alumno);
                            json.put("Alumno", alumnoJsonString);
                            json.put("status", 200);
                            out.println(json);
                        }
                        else{
                            json.put("msj", "Error ");
                            json.put("status", 400); 
                            out.println(json); 
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AlumnoController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                }
                case "EditarAlumnoSetInfo":{
                    try {
                        alumno=AlumnoDAO.getAlumnoByID(Integer.parseInt(idAlumno));
                        if (alumno!=null) {
                            if (inputUsuarioAlumno!=alumno.getUsuario()) {
                                alumno.setUsuario(inputUsuarioAlumno);
                            }
                            if (inputNombreAlumno!=alumno.getNombre()) {
                                alumno.setNombre(inputNombreAlumno);
                            }
                            if (Boolean.parseBoolean(inputEstatusAlumno)!=alumno.isEstatus()) {
                                alumno.setEstatus(Boolean.parseBoolean(inputEstatusAlumno));
                            }
                            alumno.setContraseña(inputContraseñaAlumno);
                            if (AlumnoDAO.editarAlumno(alumno)==1) {
                                json.put("msj", "Registro editado con éxito");
                                json.put("status", 200);

                                listaAlumnos=AlumnoDAO.getAlumnos();
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
                                Tabla=Tabla.concat("</tbody>");
                                Tabla=Tabla.concat("</table>");
                                json.put("listaAlumnos", Tabla);
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
                        Logger.getLogger(AlumnoController.class.getName()).log(Level.SEVERE, null, ex);
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
