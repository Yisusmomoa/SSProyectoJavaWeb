<%-- 
    Document   : BusquedaAlumnos
    Created on : 27 jun. 2022, 12:20:41
    Author     : bjls2
--%>

<%@page import="models.Alumno"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
List<Alumno> listaAlumnos=(List<Alumno>)request.getAttribute("listaAlumnos");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="./paginaPrincipal/PaginaPrincipal.css">
        <title>Busqueda Alumnos</title>
    </head>
    <body>
        <jsp:include page="navBarAdmin.jsp" />
        <%
            if (listaAlumnos.isEmpty()){
        %>
            <h1 class="mt-5 pt-5">Sin resultados</h1>
        <%
            } else{
        %>
             <div class="container-fluid mt-5 pt-5">
                <div class="row justify-content-md-center">
                  <div class="col-md-auto">
                      <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Filtrar</span>
                        <input type="text" class="form-control" placeholder="Nombre" 
                               aria-label="Username" aria-describedby="basic-addon1">
                      </div>
                  </div>
                  <div class="col-md-auto ">
                    <button type="button" class="btn btn-success "
                    data-bs-toggle="modal" data-bs-target="#añadirAlumnoModal"
                    data-bs-whatever="@mdo"
                    >Añadir registro</button>
                  </div>
                </div>
            </div>

            <h2 class="mt-5">Catalogo alumnos</h2>

            <div class="container-fluid mt-2 mb-5 
            my-custom-scrollbar" id="divtableAlumnos">
                <table class="table table-striped 
                table-hover table-bordered 
                table-responsive caption-top 
                mx-auto align-middle table-wrapper-scroll-y" id="table">
              <thead>
                <tr>
                  <th scope="col">id</th>
                  <th scope="col">nombre</th>
                  <th scope="col">usuario</th>
                  <th scope="col">Estatus</th>
                  <th scope="col" id="acciones">acciones</th>
                </tr>
              </thead>
              <tbody id="tbodyidAlumnos">
                  <% for(Alumno alumno: listaAlumnos){%>
                  <tr>
                    <th scope="row"><%= alumno.getMatricula() %></th>
                    <td><%= alumno.getNombre() %></td>
                    <td><%= alumno.getUsuario() %></td>
                    <td><%= alumno.isEstatus() %></td>
                    <td class="text-center align-middle">
                          <button type="submit"
                                  value="Eliminar"
                                  id="EliminarAlumno" 
                                  class="btn btn-danger">Eliminar</button>
                          <button type="button" id="EditarAlumno"
                                  value="EditarGetInfoAlumno"
                                  data-bs-toggle="modal" 
                                  data-bs-target="#editAlumno"
                                  class="btn btn-secondary">Editar</button>
                    </td>
                  </tr>
                <%}%>
              </tbody>
            </table>
            </div>
        <%
            }
        %>
        
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
         
         <!-- Modal edit alumno-->
        <div class="modal fade" id="editAlumno"
             tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <form id="editAlumnoForm">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Edit alumno</h5>
                    <button type="button" class="btn-close"
                            data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                            <label for="inputIdAlumno" class="form-label">Matricula alumno</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="idAlumno" 
                                   id="inputIdAlumno" disabled name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                            
                            <label for="inputUsuarioAlumno" class="form-label">Usuario</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Usaurio" 
                                   id="inputUsuarioAlumno" name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                            
                            <label for="inputNombreAlumno" class="form-label">Nombre alumno</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="nombre" 
                                   id="inputNombreAlumno" name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                            
                            <label for="inputContraseñaAlumno" class="form-label">Contraseña alumno</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="contraseña" 
                                   id="inputContraseñaAlumno" name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                            
                        <div class="form-check form-switch">
                            <input class="form-check-input"
                                   type="checkbox" role="switch" 
                                   id="inputEstatusAlumno">
                            <label class="form-check-label" 
                                   for="inputEstatusAlumno">Estatus alumno</label>
                        </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" id="EditarAlumnoSetInfo" value="EditarAlumnoSetInfo"
                            class="btn btn-primary">Save changes</button>
                  </div>
                </div>
            </form>
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
