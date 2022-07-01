/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession();
        
        PrintWriter out=response.getWriter();
        JSONObject json=new JSONObject();
         List<Maestro> listaMaestros=new ArrayList<>();
        if (session.getAttribute("noEmpleado")!=null) {
            String InputNombre=request.getParameter("InputNombre");
            String InputUsuario=request.getParameter("InputUsuario");
            String Inputcontraseña=request.getParameter("Inputcontraseña");
            String tipoMaestro=request.getParameter("tipoMaestro");
            Maestro maestro=new Maestro(InputNombre,Inputcontraseña,
                    InputUsuario,tipoMaestro);
            if (maestroDAO.addMaestro(maestro)==1) {
                String Tabla;
                Tabla="";
                try {
                    listaMaestros=maestroDAO.getMaestros();
                    for(Maestro maestroAux: listaMaestros){
                        Tabla=Tabla.concat("<tr>");

                           Tabla=Tabla.concat("<th scope='row'>");
                            Tabla=Tabla.concat(Integer.toString(maestroAux.getNoEmpleado()));
                           Tabla=Tabla.concat("</th>");

                           Tabla=Tabla.concat("<td>");
                            Tabla=Tabla.concat(maestroAux.getNombreMaestro());
                           Tabla=Tabla.concat("</td>");

                           Tabla=Tabla.concat("<td>");
                            Tabla=Tabla.concat(maestroAux.getUsuario());
                           Tabla=Tabla.concat("</td>");

                           Tabla=Tabla.concat("<td>");
                            Tabla=Tabla.concat(Boolean.toString(maestroAux.isEstatus()));
                           Tabla=Tabla.concat("</td>");

                           Tabla=Tabla.concat("<td class='text-center align-middle'>");
                            Tabla=Tabla.concat("<button type='button' value='Eliminar' id='EliminarMaestro' class='btn btn-danger'>Eliminar</button>");
                            Tabla=Tabla.concat("<button type='button' id='EditarMaestro' value='EditarGetInfo' data-bs-toggle='modal' data-bs-target='#editMaestro' class='btn btn-secondary'>Editar</button>");
                           Tabla=Tabla.concat("</td>");

                        Tabla=Tabla.concat("</tr>");
                    }
                    json.put("listaMaestros", Tabla);
                } catch (SQLException ex) {
                    Logger.getLogger(addMaestro.class.getName()).log(Level.SEVERE, null, ex);
                }
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
