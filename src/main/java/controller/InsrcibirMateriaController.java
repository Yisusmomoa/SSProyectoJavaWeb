/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.MateriaDAO;
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
import models.Materia;
import org.json.JSONObject;

/**
 *
 * @author bjls2
 */
@WebServlet(name = "InsrcibirMateriaController", urlPatterns = {"/InsrcibirMateriaController"})
public class InsrcibirMateriaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsrcibirMateriaController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsrcibirMateriaController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         List<Grupo> listaGrupos = null;
        try {
            listaGrupos = grupoDAO.getGrupos();
        } catch (SQLException ex) {
            Logger.getLogger(altaGrupoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaGrupos", listaGrupos);
        
        
        request.getRequestDispatcher("InscribirMateria.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session=request.getSession(false);
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
        if (session.getAttribute("matricula")!=null) {
            HttpSession session2=request.getSession();
            Object matricula=session2.getAttribute("matricula");
            String idgrupo=request.getParameter("idgrupo");
           

            List<Grupo> listaGrupos=new ArrayList<>();

            try {
                int result=grupoDAO.inscribirMateria(Integer.parseInt(idgrupo), (int) matricula);
                if (result==1) {
                    String Tabla;
                    Tabla="";
                    listaGrupos=grupoDAO.getMateriasByAlumno((int) matricula);
                    for(Grupo grupoAux:listaGrupos){
                            Tabla=Tabla.concat("<tr>");

                            Tabla=Tabla.concat("<th scope='row'>");
                                Tabla=Tabla.concat(Integer.toString(grupoAux.getIdGrupo()));
                            Tabla=Tabla.concat("</th>");

                            Tabla=Tabla.concat("<td>");
                                Tabla=Tabla.concat(grupoAux.getMateria().getNombreMateria());
                            Tabla=Tabla.concat("</td>");

                            Tabla=Tabla.concat("<td>");
                                Tabla=Tabla.concat(Boolean.toString(grupoAux.isEstatus()));
                            Tabla=Tabla.concat("</td>");

                            Tabla=Tabla.concat("<td>");
                                Tabla=Tabla.concat(Boolean.toString(grupoAux.getMateria().isEstatus()));
                            Tabla=Tabla.concat("</td>");

                            Tabla=Tabla.concat("<td class='text-center align-middle'>");
                                Tabla=Tabla.concat("<button type='submit'\n" +
    "                        value='DarBaja'\n" +
    "                        id='DarBajaMateria' \n" +
    "                        class='btn btn-danger'>Dar de baja</button>");
                            Tabla=Tabla.concat("</td>");

                            Tabla=Tabla.concat("</tr>");
                    }
                    json.put("msj", "Alumno inscrito con éxito");
                    json.put("status", 200);
                    json.put("listaGrupos", Tabla);
                    out.println(json);
                }
                else if(result==-1){
                    json.put("msj", "Error, ya tienes inscrita está materia");
                    json.put("status", 400); 
                    out.println(json);
                }
                else{
                    json.put("msj", "Error ");
                    json.put("status", 400); 
                    out.println(json);
                }
            } catch (SQLException ex) {
                Logger.getLogger(InsrcibirMateriaController.class.getName()).log(Level.SEVERE, null, ex);
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
