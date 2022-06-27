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
List<Grupo> listaGrupos= (List<Grupo>)request.getAttribute("listaGrupos");
List<Materia> listaMaterias=(List<Materia>)request.getAttribute("listaMaterias");

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
      <jsp:include page="navBarAdmin.jsp" />

   
        <h2 class="mt-5 pt-5">Catalogo maestros</h2>
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
                <th scope="col">Estatus</th>
                <th scope="col" id="acciones">acciones</th>
              </tr>
            </thead>
            <tbody id="tbodyidMaestros">
                <%for(Maestro maestro: listaMaestros){%>
                    <tr>
                      <th scope="row"><%= maestro.getNoEmpleado() %></th>
                      <td><%= maestro.getNombreMaestro() %></td>
                      <td><%= maestro.getUsuario() %></td>
                      <td><%= maestro.isEstatus() %></td>
                      <td class="text-center align-middle">
                          <button type="submit"
                                  value="Eliminar"
                                  id="EliminarMaestro" 
                                  class="btn btn-danger">Eliminar</button>
                          <button type="button" id="EditarMaestro"
                                  value="EditarGetInfo"
                                  data-bs-toggle="modal" 
                                  data-bs-target="#editMaestro"
                                  class="btn btn-secondary">Editar</button>
                      </td>
                    </tr>
                <%}%>
            </tbody>
          </table>
        </div>

        <h2 class="mt-5 pt-5">Catalogo alumnos</h2>
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

        <h2 class="mt-5 pt-5">Catalogo materias</h2>
        <div class="container-fluid mt-2 
        my-custom-scrollbar" id="divtableMaterias">
          <table class="table table-striped 
              table-hover table-bordered  
              table-responsive caption-top 
              mx-auto align-middle
              table-wrapper-scroll-y" id="table">
            <thead>
              <tr>
                <th scope="col">id</th>
                <th scope="col">nombre materia</th>
                <th scope="col">Estatus</th>
                <th scope="col" id="acciones">acciones</th>
              </tr>
            </thead>
            <tbody id="tbodyidMateria">
                <%for(Materia materia: listaMaterias){%>
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
                            class="btn btn-secondary">Editar</button>
                </td>
              </tr>
              <%}%>
            </tbody>
          </table>
        </div>

        <h2 class="mt-5 pt-5 ">Catalogo grupos</h2>
        <div class="container-fluid mt-2 
        my-custom-scrollbar mb-5 pb-5" id="divtableGrupos">
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
            <tbody id="tbodyidGrupo">
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
        <div class="modal fade " id="exampleModal" tabindex="-1" 
          aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog ">
                <<form method="POST" action="BusquedaController">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Ventana de busqueda</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                          <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Busqueda" id="inputBusqueda" name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
                          </div>
                          <div class="form-floating">
                            <select class="form-select" id="floatingSelect" name="floatingSelect" aria-label="Floating label select example">
                              <option selected value="null">Selecciona una opción</option>
                              <option value="1">Maestro</option>
                              <option value="2">Alumno</option>
                              <option value="3">Grupo</option>
                              <option value="4">Materia</option>
                            </select>
                            <label for="floatingSelect">Works with selects</label>
                          </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Search</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        
        <!-- Modal edit maestro-->
        <div class="modal fade" id="editMaestro"
             tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog">
            <form id="EditarMaestroForm">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Edit maestro</h5>
                    <button type="button" class="btn-close"
                            data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                            <label for="inputIdUsuario" class="form-label">idUsuario</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="idusuario" 
                                   id="inputIdUsuario" disabled name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                            
                            <label for="inputUsuario" class="form-label">Usuario</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Usaurio" 
                                   id="inputUsuario" name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                            
                            <label for="inputNombre" class="form-label">Nombre</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="nombre" 
                                   id="inputNombre" name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                            
                            <label for="inputContraseña" class="form-label">Contraseña</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="contraseña" 
                                   id="inputContraseña" name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                            
                        <div class="form-check form-switch">
                            <input class="form-check-input"
                                   type="checkbox" role="switch" 
                                   id="inputEstatus">
                            <label class="form-check-label" 
                                   for="flexSwitchCheckChecked">Estatus</label>
                        </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" id="EditarMaestroSetInfo" value="EditarMaestroSetInfo"
                            class="btn btn-primary">Save changes</button>
                  </div>
                </div>
            </form>
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
         
         
          <!-- Modal edit materia-->
        <div class="modal fade" id="EditMateria"
             tabindex="-1" aria-labelledby="exampleModalLabel" 
             aria-hidden="true">
          <div class="modal-dialog">
            <form id="editMateriaForm">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Edit materia</h5>
                    <button type="button" class="btn-close"
                            data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                            <label for="inputClaveMateria" class="form-label">clave Materia</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Clave materia" 
                                   id="inputClaveMateria" disabled name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                            
                            <label for="inputNombreMateria" class="form-label">Nombre materia</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Nombre materia" 
                                   id="inputNombreMateria" name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                            
                        <div class="form-check form-switch">
                            <input class="form-check-input"
                                   type="checkbox" role="switch" 
                                   id="inputEstatusMateria">
                            <label class="form-check-label" 
                                   for="inputEstatusMateria">Estatus materia</label>
                        </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" id="EditarMateriaSetInfo" 
                            value="EditarMateriaSetInfo"
                            class="btn btn-primary">Save changes</button>
                  </div>
                </div>
            </form>
          </div>
        </div>
          
          
          <!-- Modal edit grupo-->
        <div class="modal fade" id="EditGrupo"
             tabindex="-1" aria-labelledby="exampleModalLabel" 
             aria-hidden="true">
          <div class="modal-dialog">
            <form id="editGrupoForm">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Edit grupo</h5>
                    <button type="button" class="btn-close"
                            data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                            <label for="inputidGrupo" class="form-label">idGrupo</label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="idGrupo" 
                                   id="inputidGrupo" disabled name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
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
                            
                            <label for="inputnumAlumnosGrupo" class="form-label">numAlumnos </label>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="numAlumnos" 
                                   id="inputnumAlumnosGrupo" name="inputBusqueda"
                            aria-label="Username" aria-describedby="basic-addon1">
                        </div>
                            
                        <div class="form-check form-switch">
                            <input class="form-check-input"
                                   type="checkbox" role="switch" 
                                   id="inputEstatusGrupo">
                            <label class="form-check-label" 
                                   for="inputEstatusGrupo">Estatus grupo</label>
                        </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" id="EditarGrupoSetInfo" 
                            value="EditarGrupoSetInfo"
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
