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
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="./paginaPrincipal/PaginaPrincipal.css">
        <title>Pagina principal alumno</title>
    </head>
    <body>
      
    <jsp:include page="navBarAlumno.jsp" />
    
        <h2 class="mt-5 pt-5">Mis materias</h2>
    <div class="container-fluid mt-2 
    my-custom-scrollbar">
      <table class="table table-striped 
          table-hover table-bordered  
          table-responsive caption-top 
          mx-auto align-middle table-wrapper-scroll-y" id="table">
        <thead>
          <tr>
            <th scope="col">id</th>
            <th scope="col">nombre maestro</th>
            <th scope="col">usuario</th>
            <th scope="col" id="acciones">acciones</th>
          </tr>
        </thead>
        <tbody id="tbodyid">
            <%for(Materia materia: listaMaterias){%>
           <tr>
            <th scope="row"><%= materia.getClaveMateria() %></th>
            <td><%= materia.getNombreMateria() %></td>
            <td><%= materia.isEstatus() %></td>
            <td class="text-center align-middle">
                <a href="" class="btn btn-danger">Eliminar</a>
                <a href="" class="btn btn-secondary">Editar</a>
            </td>
          </tr>
          <%}%>
        </tbody>
      </table>
    </div>
    
            <h2 class="mt-5 pt-5">Materias</h2>
            <div class="container-fluid mt-2 mb-5 
    my-custom-scrollbar">
      <table class="table table-striped 
          table-hover table-bordered 
          table-responsive caption-top 
          mx-auto align-middle table-wrapper-scroll-y" id="table">
       
        <thead>
          <tr>
            <th scope="col">id</th>
            <th scope="col">nombre</th>
            <th scope="col">usuario</th>
            <th scope="col" id="acciones">acciones</th>
          </tr>
        </thead>
        <tbody id="tbodyid">
            <%for(Materia materia: listaMaterias){%>
           <tr>
            <th scope="row"><%= materia.getClaveMateria() %></th>
            <td><%= materia.getNombreMateria() %></td>
            <td><%= materia.isEstatus() %></td>
            <td class="text-center align-middle">
                <a href="" class="btn btn-danger">Eliminar</a>
                <a href="" class="btn btn-secondary">Editar</a>
            </td>
          </tr>
          <%}%>
        </tbody>
      </table>
    </div>

    <!-- Modal -->
    <div class="modal fade " id="exampleModal" tabindex="-1" 
      aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog ">
          <div class="modal-content">
              <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">Ventana de busqueda</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
              </div>
              <div class="modal-body">
                <div class="input-group mb-3">
                  <input type="text" class="form-control" placeholder="Busqueda" 
                  aria-label="Username" aria-describedby="basic-addon1">
                </div>
                <div class="form-floating">
                  <select class="form-select" id="floatingSelect" aria-label="Floating label select example">
                    <option selected value="null">Selecciona una opci??n</option>
                    <option value="1">Maestro</option>
                    <option value="2">Alumno</option>
                    <option value="3">Materia</option>
                    <option value="3">Grupo</option>
                  </select>
                  <label for="floatingSelect">Works with selects</label>
                </div>
              </div>
              <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-primary">Search</button>
              </div>
          </div>
        </div>
    </div>

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
