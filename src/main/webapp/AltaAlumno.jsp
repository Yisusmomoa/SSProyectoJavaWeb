<%-- 
    Document   : AltaAlumno
    Created on : 16 jun. 2022, 13:21:49
    Author     : bjls2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        
        <title>Alta alumno</title>
    </head>
    <body>
        <jsp:include page="navBarAdmin.jsp" />
    
    <h1 class="mt-5 pt-5 pb-2 text-center">Alta alumno</h1>
    
    <div class="container ">
      <div class="row justify-content-center align-items-center vh-75 ">
       <div class="col-6 bg-light border border rounded">
           <form class="p-5" id="addAlumno">
                <div class="mb-3">
                  <label for="exampleInputEmail1" class="form-label">Nombre</label>
                  <input type="text" class="form-control" id="InputNombre" aria-describedby="emailHelp">
                </div>
               <div class="mb-3">
                   <label for="exampleInputEmail1" class="form-label">Usuario</label>
                   <input type="text" class="form-control" id="InputUsuario" aria-describedby="emailHelp">
               </div>
               <div class="mb-3">
                   <label for="exampleInputPassword1" class="form-label">Contraseña</label>
                   <input type="password" class="form-control" id="Inputcontraseña">
               </div>
               <div class="d-grid gap-2 col-4 mx-auto">
                <button class="btn btn-primary" type="submit">Alta</button>
              </div>
           </form>
       </div>
           
      </div>
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
