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
List<Maestro> listaMaestros= (List<Maestro>)request.getAttribute("listaMaestros");
List<Alumno> listaAlumnos=(List<Alumno>)request.getAttribute("listaAlumnos");
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
        <title>JSP Page</title>
    </head>
    <body>
       <nav class="navbar navbar-expand-lg 
    navbar-dark bg-primary fixed-top ">
        <div class="container-fluid">
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
          data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" 
          aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <a class="navbar-brand" href="./PaginaPrincipal.jsp">Inicio</a>
          <div class="collapse navbar-collapse" 
               id="navbarTogglerDemo03">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                       role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Alta
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                      <li><a class="dropdown-item" href="./AltaAlumno.jsp">Alumno</a></li>
                      <li><hr class="dropdown-divider"></li>
                      <li><a class="dropdown-item" href="./AltaMaestro.jsp">Maestro</a></li>
                      <li><hr class="dropdown-divider"></li>
                      <li><a class="dropdown-item" href="altaGrupoController">Grupo</a></li>
                      <li><hr class="dropdown-divider"></li>
                      <li><a class="dropdown-item" href="./AltaMateria.jsp">Materia</a></li>
                    </ul>
                </li>
              <li class="nav-item">
                <a class="nav-link" href="./InscribirMateria">Inscribir materia</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Catálogo Maestro</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Catálogo Alumnos</a>
              </li>
              <form action="logOfController" method="POST">
                <li class="nav-item">
                  <button type="submit" 
                          class="nav-link">Salir </button>
                </li>
              </form>
            </ul>
               <h3 class="navbar-text me-4 fw-bold">
                   <%= session.getAttribute("usuario") %> </h3>
                   
            <!-- Button trigger modal -->
            <button class="btn btn-secondary" type="button"
              data-bs-toggle="modal" 
              data-bs-target="#exampleModal" 
              data-bs-target="#staticBackdrop">Search</button>  
          </div>
        </div>
    </nav>

    <% String tipoUsuario="";
    if (session.getAttribute("tipo")!=null) {
        tipoUsuario=session.getAttribute("tipo").toString();
        if(tipoUsuario.equals("Administrador")) {
    %>
        <h2 class="mt-5 pt-5">Catalogo maestros</h2>
    <% } else if(tipoUsuario.equals("maestro")) { %>
        <h2 class="mt-5 pt-5">Catalogo materias</h2>
    <% } 
    } else {%>
        <h2 class="mt-5 pt-5">Mis materias</h2>
    <%}%>
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
            <%for(Maestro maestro: listaMaestros){%>
          <tr>
            <th scope="row"><%= maestro.getNoEmpleado() %></th>
            <td><%= maestro.getNombreMaestro() %></td>
            <td><%= maestro.getUsuario() %></td>
            <td class="text-center align-middle">
                <a href="" class="btn btn-danger">Eliminar</a>
                <a href="" class="btn btn-secondary">Editar</a>
            </td>
          </tr>
          <%}%>
        </tbody>
      </table>
    </div>
    
        <% if (session.getAttribute("tipo")!=null) {
            if(tipoUsuario.equals("Administrador")){%>
            <h2 class="mt-5 pt-5">Catalogo alumnos</h2>
        <% } else { %>
            <h2 class="mt-5 pt-5">Catalogo grupos</h2>
        <%}%>
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
            <% for(Alumno alumno: listaAlumnos){%>
            <tr>
              <th scope="row"><%= alumno.getMatricula() %></th>
              <td><%= alumno.getNombre() %></td>
              <td><%= alumno.getUsuario() %></td>
              <td class="text-center align-middle">
                  <a href="" class="btn btn-danger">Eliminar</a>
                  <a href="" class="btn btn-secondary">Editar</a>
              </td>
            </tr>
          <%}%>
        </tbody>
      </table>
    </div>

        <%}%>
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
                    <option selected value="null">Selecciona una opción</option>
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