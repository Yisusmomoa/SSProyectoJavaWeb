<%-- 
    Document   : PaginaPrincipal
    Created on : 16 jun. 2022, 01:48:32
    Author     : bjls2
--%>

<%@page import="models.Grupo"%>
<%@page import="models.Materia"%>
<%@page import="models.Alumno"%>
<%@page import="models.Maestro"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
List<Materia> listaMaterias=(List<Materia>)request.getAttribute("listaMaterias");
List<Grupo> listaGrupos=(List<Grupo>)request.getAttribute("listaGrupos");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="./paginaPrincipal/PaginaPrincipal.css">
        <title>Paginna principal maestro</title>
    </head>
    <body>
     
    <jsp:include page="navBarMaestro.jsp" />
   
    <h2 class="mt-5 pt-5">Catalogo materias</h2>
    
    <div class="container-fluid mt-2 
    my-custom-scrollbar">
      <table class="table table-striped 
          table-hover table-bordered  
          table-responsive caption-top 
          mx-auto align-middle table-wrapper-scroll-y" id="table">
        <thead>
          <tr>
            <th scope="col">id</th>
            <th scope="col">nombre materia</th>
            <th scope="col">Estatus</th>
            <th scope="col" id="acciones">acciones</th>
          </tr>
        </thead>
        <tbody id="tbodyidMateriasMaestro">
            <% for(Materia materia : listaMaterias){%>
          <tr>
            <th scope="row"><%= materia.getClaveMateria() %></th>
            <td><%= materia.getNombreMateria() %></td>
            <td><%= materia.isEstatus() %></td>
            <td class="text-center align-middle">
                <button type="button"
                        value="Eliminar"
                        id="EliminarMateria" 
                        class="btn btn-danger">Eliminar</button>
                <button type="button" id="EditarMateria"
                        value="EditarGetInfoMateria"
                        data-bs-toggle="modal" 
                        data-bs-target="#EditMateria"
                        class="btn btn-secondary">Editar</button>            </td>
          </tr>
          <%}%>
        </tbody>
      </table>
    </div>
    
        
    <h2 class="mt-5 pt-5">Catalogo grupos</h2>
        <div class="container-fluid mt-2 mb-5 
            my-custom-scrollbar">
            <table class="table table-striped 
                table-hover table-bordered 
                table-responsive caption-top 
                mx-auto align-middle table-wrapper-scroll-y" id="table">
                <thead>
                  <tr>
                    <th scope="col">id</th>
                    <th scope="col">nombre materia</th>
                    <th scope="col">Numero alumnos</th>
                    <th scope="col">Clave materia</th>
                    <th scope="col">Estatus</th>
                    <th scope="col" id="acciones">acciones</th>
                  </tr>
                </thead>
                <tbody id="tbodyidGruposMaestro">
                    <%for(Grupo grupo: listaGrupos){%>
                        <tr>
                          <th scope="row"><%= grupo.getIdGrupo() %></th>
                          <td><%= grupo.getMateria().getNombreMateria() %></td>
                          <td><%= grupo.getNumAlumnos() %></td>
                          <td><%= grupo.getMateria().getClaveMateria() %></td>
                          <td><%= grupo.isEstatus() %></td>
                          <td class="text-center align-middle">
                              <button type="submit"
                                            value="Eliminar"
                                            id="EliminarGrupo" 
                                            class="btn btn-danger">Eliminar</button>
                              <button type="button" id="EditarGrupo"
                                      value="EditarGetInfoGrupo"
                                      data-bs-toggle="modal" 
                                      data-bs-target="#EditGrupo"
                                      class="btn btn-secondary">Editar</button>
                          </td>
                        </tr>
                    <%}%>
                </tbody>
            </table>
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
