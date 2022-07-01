<%-- 
    Document   : ModalBusqueda
    Created on : 1 jul. 2022, 11:34:41
    Author     : bjls2
--%>
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
                            <div class="form-floating ">
                                <select class="form-select" id="floatingSelect" name="floatingSelect" aria-label="Floating label select example">
                                    <option selected value="null">Selecciona una opción</option>
                                    <option value="1">Maestro</option>
                                    <option value="2">Alumno</option>
                                    <option value="3">Grupo</option>
                                    <option value="4">Materia</option>
                                </select>
                                <label for="floatingSelect">Works with selects</label>
                            </div>
                            <div class="input-group mb-3 mt-3" id="boxBusquedaTxt">
                                <input type="text" class="form-control" placeholder="Busqueda" 
                                       id="inputBusqueda" name="inputBusqueda"
                                aria-label="Username" aria-describedby="basic-addon1">
                            </div>
                            <div class="input-group mb-3 mt-3 d-none" id="boxBusquedaInt">
                                <input type="number" id="inputBusquedaGrupo" class="form-control"
                                       placeholder="idgrupo" name="inputBusquedaGrupo"
                                aria-label="Username" aria-describedby="basic-addon1">
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