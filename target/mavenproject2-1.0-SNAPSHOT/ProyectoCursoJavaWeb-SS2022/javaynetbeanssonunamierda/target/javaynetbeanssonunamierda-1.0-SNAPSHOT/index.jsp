<%-- 
    Document   : index
    Created on : 15 jun. 2022, 00:56:22
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
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container ">
       <div class="row justify-content-center align-items-center vh-100 ">
        <div class="col-6 bg-light border border rounded">
            <form class="p-5" id="login">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Usuario</label>
                    <input type="text" class="form-control" id="InputUsuario" aria-describedby="emailHelp">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="InputContraseña">
                </div>
                <div class="mb-3 pb-3">
                    <label for="exampleInputPassword1" class="form-label">Tipo usuario</label>
                    <select class="form-select" id="selectTipoUsuario" aria-label="Default select example">
                        <option selected value="null">Selecciona una opcion</option>
                        <option value="1">Admin</option>
                        <option value="2">Maestro</option>
                        <option value="3">Alumno</option>
                      </select>
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
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
