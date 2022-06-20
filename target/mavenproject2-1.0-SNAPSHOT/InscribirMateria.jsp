<%-- 
    Document   : InscribirMateria
    Created on : 16 jun. 2022, 13:31:50
    Author     : bjls2
--%>

<%@page import="models.Grupo"%>
<%@page import="java.util.List"%>
<%@page import="models.Materia"%>
<%@page import="models.Materia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Grupo> listaGrupos=(List<Grupo>)request.getAttribute("listaGrupos");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="./InscribirMateria/index.css">
        <title>Inscribir Materia</title>
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
          <a class="navbar-brand" href="paginaPrincipalControllerAlumno">Inicio</a>
          <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link" href="InsrcibirMateriaController">Inscribir materia</a>
              </li>              
               <form action="logOfController" method="POST">
                <li class="nav-item">
                  <button type="submit" 
                          class="nav-link btn btn-primary">Salir </button>
                </li>
              </form>
            </ul>
               <h3 class="navbar-text me-4 fw-bold">
                   <%= session.getAttribute("usuario") %> </h3>
            <!-- Button trigger modal -->
            <button class="btn btn-secondary" type="button"
              data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-target="#staticBackdrop">Search</button>  

          </div>
        </div>
    </nav>

    <h2 class="mt-5 pt-5">Grupos disponibles</h2>
    
    <div class="container-fluid mt-2 
    my-custom-scrollbar">
      <table class="table table-striped 
          table-hover table-bordered 
          table-responsive caption-top 
          mx-auto align-middle table-wrapper-scroll-y" id="table">
        <thead>
          <tr>
            <th scope="col">id materia</th>
            <th scope="col">nombre</th>
            <th scope="col">Num alumnos</th>
            <th scope="col">Grupo</th>
            <th scope="col" id="acciones">acciones</th>
          </tr>
        </thead>
        <tbody id="tbodyid">
             <% for(Grupo grupo: listaGrupos){ %>
                <tr>
                  <th scope="row"><%= grupo.getMateria().getClaveMateria() %></th>
                  <td><%=grupo.getMateria().getNombreMateria() %></td>
                  <td><%= grupo.getNumAlumnos() %></td>
                  <td><%= grupo.getIdGrupo()%></td>
                  <td class="text-center align-middle">
                      <button type="submit" id="Eliminar" 
                              class="btn btn-danger">Dar de baja</button>
                      <button type="submit" id="Inscribir" 
                              class="btn btn-secondary">Inscribir</button>
                  </td>
                </tr>
            <% } %>
        </tbody>
      </table>
    </div>

    <h2 class="mt-5 pt-5">Mis materias</h2>
    <div class="container-fluid mt-2 
    my-custom-scrollbar">
      <table class="table table-striped 
          table-hover table-bordered 
          table-responsive caption-top 
          mx-auto align-middle table-wrapper-scroll-y" >
        <thead>
          <tr>
            <th scope="col">id materia</th>
            <th scope="col">nombre</th>
            <th scope="col">escuela</th>
            <th scope="col" id="acciones">acciones</th>
          </tr>
        </thead>
        <tbody id="tbodyid">
          <tr>
            <th scope="row">1</th>
            <td>brandon</td>
            <td>escuela</td>
            <td class="text-center align-middle">
                <a href="" class="btn btn-danger">Dar de baja</a>
            </td>
          </tr>

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
