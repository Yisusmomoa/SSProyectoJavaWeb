<%-- 
    Document   : AltaGrupo
    Created on : 16 jun. 2022, 13:29:22
    Author     : bjls2
--%>

<%@page import="java.util.List"%>
<%@page import="models.Materia"%>
<%@page import="models.Materia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Materia> listaMaterias=(List<Materia>)request.getAttribute("listaMaterias");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <title>Alta grupo</title>
    </head>
    <body>
         <jsp:include page="navBarAdmin.jsp" />

    <h1 class="mt-5 pt-5 pb-2 text-center">Alta grupo</h1>

    <div class="container ">
      <div class="row justify-content-center 
           align-items-center vh-75 ">
       <div class="col-6 bg-light border border rounded">
           <form class="p-5" id="addGrupo">
                <div class="mb-3 pb-3">
                    <label for="numAlumnos">Numero de alumnos 
                        (10-60) </label>
                    <input type="number" 
                           id="numAlumnos" name="numAlumnos"
                        min="10" max="60">
                </div>
                <div class="mb-3 pb-3">
                    <label for="exampleInputPassword1"
                           class="form-label">Materia</label>
                    <select class="form-select" id="materiaId" aria-label="Default select example">
                        <option selected value="null" >Selecciona una opcion</option>
                        <%
                            for(Materia materia:listaMaterias){
                        %>
                        <option value="<%= materia.getClaveMateria() %>"><%= materia.getNombreMateria() %></option>
                        <%}%>
                    </select>
                </div>
              <div class="d-grid gap-2 col-4 mx-auto">
                <button class="btn btn-primary" type="submit">Alta</button>
              </div>
           </form>
       </div>
           
      </div>
    </div>


      <!-- Modal busqueda-->
        <jsp:include page="ModalBusqueda.jsp"/>
 
 
     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js" 
     integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk" crossorigin="anonymous"></script>
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.min.js" 
     integrity="sha384-kjU+l4N0Yf4ZOJErLsIcvOU2qSb74wXpOhqTvwVx3OElZRweTnQ6d31fXEoRD1Jy" crossorigin="anonymous"></script>
      
     <script src="https://code.jquery.com/jquery-3.6.0.min.js" 
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        
    <script src="./JS/OperacionesBD.js"></script>
    </body>
</html>
