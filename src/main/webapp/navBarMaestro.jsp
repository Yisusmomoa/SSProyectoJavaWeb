<%-- 
    Document   : navBarMaestro
    Created on : 22 jun. 2022, 12:36:35
    Author     : bjls2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
  <nav class="navbar navbar-expand-lg 
    navbar-dark bg-primary fixed-top ">
        <div class="container-fluid">
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
          data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" 
          aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <a class="navbar-brand" href="paginaPrincipalControllerMaestro">Inicio</a>
          <div class="collapse navbar-collapse" 
               id="navbarTogglerDemo03">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link" href="#">Catálogo materias</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">Catálogo Grupos</a>
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
              data-bs-toggle="modal" 
              data-bs-target="#exampleModal" 
              data-bs-target="#staticBackdrop">Search</button>  
          </div>
        </div>
    </nav>