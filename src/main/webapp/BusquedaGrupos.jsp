<%-- 
    Document   : BusquedaGrupos
    Created on : 27 jun. 2022, 12:50:39
    Author     : bjls2
--%>

<%@page import="models.Materia"%>
<%@page import="models.Grupo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
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
        
        <title>Busqueda Grupos</title>
    </head>
    <body>
        <%
        if (session.getAttribute("noEmpleado")!=null) {
            if (session.getAttribute("tipo").equals("Administrador")) {
        %>
            <jsp:include page="navBarAdmin.jsp" />
        <%  }
            else{ %>
            <jsp:include page="navBarMaestro.jsp" />
        <%
            }
        }
        else
        { 
        %>
            <jsp:include page="navBarAlumno.jsp" />
        <%
        }
        %>
        
        
        
        <%
            if(listaGrupos.isEmpty()){
        %>
            <h1 class="mt-5 pt-5">Sin resultados</h1>
        <%
            } else {
        %>
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
        <%
            }
        %>
        
        <!-- Modal busqueda-->
        <jsp:include page="ModalBusqueda.jsp"/>
         
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
