/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$(document).ready(function() { 
    
    $('#login').submit((event)=>{
        event.preventDefault();
        const InputUsuario=$('#InputUsuario').val();
        const InputContraseña=$('#InputContraseña').val();
        const selectTipoUsuario=$('#selectTipoUsuario').val();
       
        debugger;
        if(InputUsuario!="" && InputContraseña!="" && selectTipoUsuario!="null"){
            $.ajax({
                type:"POST",
                datatype:"json",
                url:"logInController",
                data:{
                    "InputUsuario":InputUsuario,
                    "InputContraseña":InputContraseña,
                    "selectTipoUsuario":selectTipoUsuario
                },
                success:(data,textStatus,jqXHR)=>{
                    debugger;
                    let obj=JSON.parse(data);
                    if (obj.status==200) {
                         Swal.fire({
                            position: 'top-end',
                            icon: 'success',
                            title: 'Bienvenido ' +obj.usuario,
                            showConfirmButton: false,
                            timer: 2000
                          });
                          debugger;
                            if (obj.Tipo=="Administrador") {
                                setTimeout(()=>{
                                  //location.href="servletprueba"
                                  //location.href="./ProyectoCursoJavaWeb-SS2022/paginaPrincipal/PaginaPrincipal.jsp"
                                  location.href="paginaPrincipalControllerAdministrador";
                                },2000);
                            }
                            else if(obj.Tipo=="maestro"){
                                setTimeout(()=>{
                                  //location.href="servletprueba"
                                  //location.href="./ProyectoCursoJavaWeb-SS2022/paginaPrincipal/PaginaPrincipal.jsp"
                                  location.href="paginaPrincipalControllerMaestro";
                                },2000);
                            }
                            else if(obj.Tipo=="Alumno"){
                                 setTimeout(()=>{
                                  //location.href="servletprueba"
                                  //location.href="./ProyectoCursoJavaWeb-SS2022/paginaPrincipal/PaginaPrincipal.jsp"
                                  location.href="paginaPrincipalControllerAlumno";
                                },2000);
                                
                            }
                          
                    }
                    else{
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Usuario o contraseña incorrecta!'
                          });
                    }
                },
                error:(error)=>{
                    console.error(error);
                }
                
            })
        }
        else{
            Swal.fire({
                icon: 'error',
                title: 'Error...',
                text: 'No puedes dejar campos vacios!',
              })
        }
    });
    
    const UnaLetraMayus = new RegExp("^(?=.*[A-Z])");
    const UnNumero = new RegExp("^(?=.*[0-9])");
    const UnaLetraMinus = new RegExp("^(?=.*[a-z])");
    const UnSignoPuntuacion = new RegExp("^(?=.*[!@#$&*''<>&=])");
    const r = /[!@#$&*''<>&=]/i; 
    $('#altaMaestro').submit((event)=>{
         event.preventDefault();
         const InputNombre=$('#InputNombre').val();
         const InputUsuario=$('#InputUsuario').val();
         const Inputcontraseña=$('#Inputcontraseña').val();
         const tipoMaestro=$('#tipoMaestro').val();
         debugger;
        if (InputNombre!="" && InputUsuario!=""
                && Inputcontraseña!="" && tipoMaestro!="null") {
            if (Inputcontraseña.length>8) {
                Swal.fire({
                icon: 'error',
                title: 'Error...',
                text: 'la contraseña debe de \n\
                ser como maximo 8 caracteres!',
              })
            }
            else{
                //Abcd5
                if (UnaLetraMayus.test(Inputcontraseña)) {
                    if (UnNumero.test(Inputcontraseña)) {
                           if (UnaLetraMinus.test(Inputcontraseña)) {
                               
                                       $.ajax({
                                            type:"POST",
                                            datatype:"json",
                                            url:"addMaestro",
                                            data:{
                                                "InputNombre":InputNombre,
                                                "InputUsuario":InputUsuario,
                                                "Inputcontraseña":Inputcontraseña,
                                                "tipoMaestro":tipoMaestro
                                            },
                                            success:(data,textStatus,jqXHR)=>{
                                                let obj=JSON.parse(data);
                                                if (obj.status==200) {
                                                    Swal.fire({
                                                        position: 'center',
                                                        icon: 'success',
                                                        title: `${obj.msj}`,
                                                        showConfirmButton: false,
                                                        timer: 2000
                                                      });
                                                      
                                                      $('#InputNombre').val('');
                                                      $('#InputUsuario').val('');
                                                      $('#Inputcontraseña').val('');
                                                }
                                                else{
                                                    Swal.fire({
                                                        position:'center',
                                                        icon: 'error',
                                                        title: 'Error...',
                                                        text: `${obj.msj}`,
                                                        showConfirmButton: false,
                                                        timer: 1000
                                                    });
                                                }
                                            },
                                            error:(error)=>{
                                                Swal.fire({
                                                    icon: 'error',
                                                    title: 'Error...',
                                                    text: 'Error inesperado: '+error.toString(),
                                                  })
                                            }
                                    })                  
                            }
                            else{
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error...',
                                    text: 'la contraseña debe de tener una letra en minuscula!',
                                  })
                            }
                    }
                    else{
                        Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'la contraseña debe de tener un numero!',
                      })
                    }
                }
                else{
                    Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'la contraseña debe de tener una letra mayuscula!',
                      })
                }
            }
        }
        else{
            Swal.fire({
                icon: 'error',
                title: 'Error...',
                text: 'No puedes dejar campos vacios!',
              })
        }
    });
    
    
    $('#addAlumno').submit((event)=>{
         event.preventDefault();
         const InputNombre=$('#InputNombre').val();
         const InputUsuario=$('#InputUsuario').val();
         const Inputcontraseña=$('#Inputcontraseña').val();
         debugger;
        if (InputNombre!="" && InputUsuario!=""
                && Inputcontraseña!="") {
            if (Inputcontraseña.length>8) {
                Swal.fire({
                icon: 'error',
                title: 'Error...',
                text: 'la contraseña debe de \n\
                ser como maximo 8 caracteres!',
              })
            }
            else{
                //Abcd5
                if (UnaLetraMayus.test(Inputcontraseña)) {
                    if (UnNumero.test(Inputcontraseña)) {
                           if (UnaLetraMinus.test(Inputcontraseña)) {
                               
                                       $.ajax({
                                            type:"POST",
                                            datatype:"json",
                                            url:"addAlumno",
                                            data:{
                                                "InputNombre":InputNombre,
                                                "InputUsuario":InputUsuario,
                                                "Inputcontraseña":Inputcontraseña
                                            },
                                            success:(data,textStatus,jqXHR)=>{
                                                let obj=JSON.parse(data);
                                                if (obj.status==200) {
                                                    Swal.fire({
                                                        position: 'center',
                                                        icon: 'success',
                                                        title: `${obj.msj}`,
                                                        showConfirmButton: false,
                                                        timer: 2000
                                                      });
                                                      
                                                      $('#InputNombre').val('');
                                                      $('#InputUsuario').val('');
                                                      $('#Inputcontraseña').val('');
                                                }
                                                else{
                                                    Swal.fire({
                                                        position:'center',
                                                        icon: 'error',
                                                        title: 'Error...',
                                                        text: `${obj.msj}`,
                                                        showConfirmButton: false,
                                                        timer: 1000
                                                    });
                                                    setTimeout(()=>{
                                                        location.href="index.jsp";
                                                    },2000);
                                                }
                                            },
                                            error:(error)=>{
                                                Swal.fire({
                                                    icon: 'error',
                                                    title: 'Error...',
                                                    text: 'Error inesperado: '+error.toString(),
                                                  })
                                            }
                                    })                  
                            }
                            else{
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error...',
                                    text: 'la contraseña debe de tener una letra en minuscula!',
                                  })
                            }
                    }
                    else{
                        Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'la contraseña debe de tener un numero!',
                      })
                    }
                }
                else{
                    Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'la contraseña debe de tener una letra mayuscula!',
                      })
                }
            }
        }
        else{
            Swal.fire({
                icon: 'error',
                title: 'Error...',
                text: 'No puedes dejar campos vacios!',
              })
        }
    });
    
    
    $('#addMateria').submit((event)=>{
        event.preventDefault();
        const inputNombre=$('#inputNombre').val();
       
        debugger;
        if(inputNombre!=""){
            $.ajax({
                type:"POST",
                datatype:"json",
                url:"addMateria",
                data:{
                    "inputNombre":inputNombre
                },
                success:(data,textStatus,jqXHR)=>{
                    debugger;
                    let obj=JSON.parse(data);
                    if (obj.status==200) {
                        
                         Swal.fire({
                            position: 'center',
                            icon: 'success',
                            title: `${obj.msj}`,
                            showConfirmButton: false,
                            timer: 1000
                          });
                          $('#InputNombre').val('');
                    }
                    else{
                        Swal.fire({
                            position:'center',
                            icon: 'error',
                            title: 'Error...',
                            text: `${obj.msj}`,
                            showConfirmButton: false,
                            timer: 1000
                        });
                    }
                },
                error:(error)=>{
                    Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'Error inesperado: '+error.toString(),
                      })
                }
                
            })
        }
        else{
            Swal.fire({
                icon: 'error',
                title: 'Error...',
                text: 'No puedes dejar campos vacios!',
              })
        }
    });
    
    
    $('#addGrupo').submit((event)=>{
        event.preventDefault();
        const numAlumnos=$('#numAlumnos').val();
        const materiaId=$('#materiaId').val();
       
        debugger;
        if(numAlumnos!="" && materiaId!="null"){
            
            $.ajax({
                type:"POST",
                datatype:"json",
                url:"altaGrupoController",
                data:{
                    "numAlumnos":numAlumnos,
                    "materiaId":materiaId
                },
                success:(data,textStatus,jqXHR)=>{
                    debugger;
                    let obj=JSON.parse(data);
                    if (obj.status==200) {
                         Swal.fire({
                            position: 'center',
                            icon: 'success',
                            title: `${obj.msj}`,
                            showConfirmButton: false,
                            timer: 1000
                          });
                    }
                    else{
                        Swal.fire({
                            position:'center',
                            icon: 'error',
                            title: 'Error...',
                            text: `${obj.msj}`,
                            showConfirmButton: false,
                            timer: 1000
                        });
                    }
                },
                error:(error)=>{
                    Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'Error inesperado: '+error.toString(),
                      })
                }
                
            })
        }
        else{
            Swal.fire({
                icon: 'error',
                title: 'Error...',
                text: 'No puedes dejar campos vacios!',
              })
        }
    });
    
    $('#floatingSelect').on('change',function(){
        if (this.value==3) {
            $('#boxBusquedaTxt').addClass('d-none');
            $('#boxBusquedaInt').removeClass('d-none');
        }
        else{
            $('#boxBusquedaTxt').removeClass('d-none');
            $('#boxBusquedaInt').addClass('d-none');
        }
        
    });
    //Ni lo uso xd
    $('#Busqueda').submit((event)=>{
        event.preventDefault();
        const inputBusqueda=$('#inputBusqueda').val();
        const inputBusquedaGrupo=$('#inputBusquedaGrupo').val();
        const floatingSelect=$('#floatingSelect').val();
        debugger;
        if(inputBusqueda!="" && floatingSelect!="null"){
            $.ajax({
                type:"POST",
                datatype:"json",
                url:"",
                data:{
                    "inputBusqueda":inputBusqueda,
                    "floatingSelect":floatingSelect
                },
                success:(data,textStatus,jqXHR)=>{
                    debugger;
                    let obj=JSON.parse(data);
                    if (obj.status==200) {
                        
                         Swal.fire({
                            position: 'center',
                            icon: 'success',
                            title: `${obj.msj}`,
                            showConfirmButton: false,
                            timer: 1000
                          });
                    }
                    else{
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Error'
                          });
                    }
                },
                error:(error)=>{
                    Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'Error inesperado: '+error.toString(),
                      })
                }
                
            })
        }
        else{
            Swal.fire({
                icon: 'error',
                title: 'Error...',
                text: 'No puedes dejar campos vacios!',
              })
        }
    });
    
    $('#FiltrarNombreMaestrotxt').on('keyup',function(){
       const search=this.value;
       console.log(search);
       $.ajax({
            type:"POST",
                datatype:"json",
                url:"FiltrarNombreMaestro",
                data:{
                    "search":search
                },
                success:(data,textStatus,jqXHR)=>{
                    debugger;
                    let obj=JSON.parse(data);
                    if (obj.status==200) {
                        $('#tbodyidMaestros').html(obj.listaMaestros);
                    }
                    else{
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Error'
                          });
                    }
                },
                error:(error)=>{
                    Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'Error inesperado: '+error.toString(),
                      })
                }
       });
    });
    
    //AJAX
        $('#FiltrarNombreAlumnotxt').on('keyup',function(){
       const search=this.value;
       console.log(search);
       $.ajax({
                type:"POST",
                datatype:"json",
                url:"FiltrarNombreAlumno",
                data:{
                    "search":search
                },
                success:(data,textStatus,jqXHR)=>{
                    debugger;
                    let obj=JSON.parse(data);
                    if (obj.status==200) {
                        $('#tbodyidAlumnos').html(obj.listaAlumnos);
                    }
                    else{
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Error'
                          });
                    }
                },
                error:(error)=>{
                    Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'Error inesperado: '+error.toString(),
                      })
                }
       });
    });
    
        $('#FiltrarMateriaAlumnotxt').on('keyup',function(){
       const search=this.value;
       debugger;
       $.ajax({
                type:"POST",
                datatype:"json",
                url:"FiltrarMateriasAlumno",
                data:{
                    "search":search
                },
                success:(data,textStatus,jqXHR)=>{
                    debugger;
                    let obj=JSON.parse(data);
                    if (obj.status==200) {
                        $('#tbodyidMateriasAlumno').html(obj.listaGrupos);
                    }
                    else{
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Error'
                          });
                    }
                },
                error:(error)=>{
                    debugger;
                    Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'Error inesperado: '+error.responseText,
                      })
                }
       });
    });
    
        $('#FiltrarGrupostxt').on('keyup',function(){
            const search=this.value;
            debugger;
            $.ajax({
                type:"POST",
                datatype:"json",
                url:"FiltrarIdGrupo",
                data:{
                    "search":search
                },
                success:(data,textStatus,jqXHR)=>{
                    debugger;
                    let obj=JSON.parse(data);
                    if (obj.status==200) {
                        $('#tbodyidgrupos').html(obj.listaGrupos);
                    }
                    else{
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Error'
                          });
                    }
                },
                error:(error)=>{
                    debugger;
                    Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'Error inesperado: '+error.responseText,
                      })
                }
            });
        });
    
        
    //AJAX
    
    //Busqueda
    
    /*MATERIAS*/
        //inscribir materia
        $('tbody').on('click','#Inscribir',function (){
            let currow=$(this).closest('tr');
            let idgrupo=currow.find('td:eq(2)').text();

            $.ajax({
                type:"POST",
                datatype:"json",
                url:"InsrcibirMateriaController",
                data:{
                    "idgrupo":idgrupo
                },
                success:(data,textStatus,jqXHR)=>{
                    let obj=JSON.parse(data);
                    debugger;
                    if (obj.status==200) {
                        Swal.fire({
                           position: 'center',
                           icon: 'success',
                           title: `${obj.msj}`,
                           showConfirmButton: false,
                           timer: 1000
                         });
                    }
                    else{
                        Swal.fire({
                                icon: 'error',
                                title: 'Oops...',
                                text: `${obj.msj}`
                        });
                    }
                },
                error:(error)=>{
                    Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'Error inesperado: '+error.toString(),
                      })
                }
            });

        })

        //dar de baja materia
        $('tbody').on('click','#Eliminar',function (){
            let currow=$(this).closest('tr');
            let idgrupo=currow.find('td:eq(2)').text();
            debugger;
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
              }).then((result) => {
                if (result.isConfirmed) {
                    $.ajax({
                        type:"POST",
                        datatype:"json",
                        url:"darBajaMateriaController",
                        data:{
                            "idgrupo":idgrupo
                        },
                        success:(data,textStatus,jqXHR)=>{
                            let obj=JSON.parse(data);
                            debugger;
                            if (obj.status==200) {                            
                                Swal.fire({
                                   position: 'center',
                                   icon: 'success',
                                   title: `${obj.msj}`,
                                   showConfirmButton: false,
                                   timer: 1000
                                 });
                            }
                            else{
                                Swal.fire({
                                        icon: 'error',
                                        title: 'Oops...',
                                        text: 'Error intenta más tarde'
                                });
                            }
                        },
                        error:(error)=>{
                            debugger;
                            Swal.fire({
                                icon: 'error',
                                title: 'Error...',
                                text: 'Error inesperado: '+error.toString(),
                              })
                        }
                    });

                }
              })



            /*currow.find('td:eq(0)').css( "color", "red" );
            currow.find('td:eq(1)').css( "color", "blue" );
            currow.find('td:eq(2)').css( "color", "yellow" );
            currow.find('td:eq(-1)').css( "color", "pink" );*/
            debugger;
        })
    
    
    
    
        $('#tbodyidMateriasAlumno').on('click',"#DarBajaMateria",function(){
            let currow=$(this).closest('tr');
            let idgrupo=currow.find('th').text();
            debugger;
            Swal.fire({
                title: 'Are you sure?',
                text: "¿Está seguro de eliminar este registro?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
              }).then((result) => {
                if (result.isConfirmed) {
                    $.ajax({
                        type:"POST",
                        datatype:"json",
                        url:"darBajaMateriaController",
                        data:{
                            "idgrupo":idgrupo
                        },
                        success:(data,textStatus,jqXHR)=>{
                            let obj=JSON.parse(data);
                            debugger;
                            if (obj.status==200) {                            
                                Swal.fire({
                                   position: 'center',
                                   icon: 'success',
                                   title: `${obj.msj}`,
                                   showConfirmButton: false,
                                   timer: 1000
                                 });
                                 $('#tbodyidMateriasAlumno').html(obj.listaGrupos)
                            }
                            else{
                                Swal.fire({
                                        icon: 'error',
                                        title: 'Oops...',
                                        text: 'Error intenta más tarde'
                                });
                            }
                        },
                        error:(error)=>{
                            debugger;
                            Swal.fire({
                                icon: 'error',
                                title: 'Error...',
                                text: 'Error inesperado: '+error.toString(),
                              })
                        }
                    });
                }
              })
        });
    
    //traer solo las materias que el alumno no tenga inscritas
        $('#tbodyidgrupos').on('click',"#InscribirMateria",function(){
            let currow=$(this).closest('tr');
            let idgrupo=currow.find('th').text();
            debugger;
            $.ajax({
                type:"POST",
                datatype:"json",
                url:"InsrcibirMateriaController",
                data:{
                    "idgrupo":idgrupo
                },
                success:(data,textStatus,jqXHR)=>{
                    let obj=JSON.parse(data);
                    debugger;
                    if (obj.status==200) {
                        $('#tbodyidMateriasAlumno').html(obj.listaGrupos)
                        Swal.fire({
                           position: 'center',
                           icon: 'success',
                           title: `${obj.msj}`,
                           showConfirmButton: false,
                           timer: 1000
                         });
                    }
                    else{
                        Swal.fire({
                                icon: 'error',
                                title: 'Oops...',
                                text: `${obj.msj}`
                        });
                    }
                },
                error:(error)=>{
                    Swal.fire({
                        icon: 'error',
                        title: 'Error...',
                        text: 'Error inesperado: '+error.toString(),
                      })
                }
            });
        });
    
    /*MATERIAS*/
    
    /*MAESTRO*/
        //Eliminar maestro
        $('#tbodyidMaestros').on('click', '#EliminarMaestro',function (){
        let currow=$(this).closest('tr');
        let idMaestro=currow.find('th').text();
        let opcion=$('#EliminarMaestro').val();
        debugger;
        Swal.fire({
                title: 'Are you sure?',
                text: "¿Está seguro de eliminareste registro?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
              }).then((result) => {
                if (result.isConfirmed) {
                    $.ajax({
                        type:"POST",
                        datatype:"json",
                        url:"profesorController",
                        data:{
                            "idMaestro":idMaestro,
                            "opcion":opcion
                        },
                        success:(data,textStatus,jqXHR)=>{
                            let obj=JSON.parse(data);
                            debugger;
                            if (obj.status==200) {   
                                 $('#tbodyidMaestros').html(obj.listaMaestros);
                                Swal.fire({
                                   position: 'center',
                                   icon: 'success',
                                   title: `${obj.msj}`,
                                   showConfirmButton: false,
                                   timer: 1000
                                 });
                                
                            }
                            else{
                                Swal.fire({
                                    position:'center',
                                    icon: 'error',
                                    title: 'Error...',
                                    text: `${obj.msj}`,
                                    showConfirmButton: false,
                                    timer: 1000
                                });
                            }
                        },
                        error:(error)=>{
                            debugger;
                            Swal.fire({
                                icon: 'error',
                                title: 'Error...',
                                text: 'Error inesperado: '+error.toString(),
                              })
                        }
                    })
                }
              })
        
    });
    
        //Editar maestro
            //get info maestro
            $('tbody').on('click', '#EditarMaestro',function (){
                let currow=$(this).closest('tr');
                let idMaestro=currow.find('th').text();
                let opcion=$('#EditarMaestro').val();
                $.ajax({
                    type:"POST",
                    datatype:"json",
                    url:"profesorController",
                    data:{
                        "idMaestro":idMaestro,
                        "opcion":opcion
                    },
                    success:(data,textStatus,jqXHR)=>{
                        const obj=JSON.parse(data);
                        if (obj.status==200) {
                            const maestro=JSON.parse(obj.Maestro);
                            $('#inputIdUsuario').val(maestro.noEmpleado);
                            $('#inputUsuario').val(maestro.usuario);
                            $('#inputNombre').val(maestro.nombreMaestro);
                            if (maestro.estatus) {
                                $('#inputEstatus').prop('checked',maestro.estatus);
                            }
                            else{
                                $('#inputEstatus').prop('checked',maestro.estatus);
                            }
                        }
                        else{
                            Swal.fire({
                                    position:'center',
                                    icon: 'error',
                                    title: 'Error...',
                                    text: `${obj.msj}`,
                                    showConfirmButton: false,
                                    timer: 1000
                            });
                        }
                        
                    },
                    error:(error)=>{
                        debugger;
                        Swal.fire({
                            icon: 'error',
                            title: 'Error...',
                            text: 'Error inesperado: '+error.toString(),
                          })
                    }
                })

            });
            //actualizar info maestro
            $('#EditarMaestroForm').submit((event)=>{
                event.preventDefault();
                const inputIdUsuario=$('#inputIdUsuario').val();
                const inputUsuario=$('#inputUsuario').val();
                const inputNombre=$('#inputNombre').val();
                const inputContraseña=$('#inputContraseña').val();
                const inputEstatus=$('#inputEstatus').prop('checked');
                const opcion=$('#EditarMaestroSetInfo').val();
                debugger;
                $.ajax({
                    type:"POST",
                    datatype:"json",
                    url:"profesorController",
                    data:{
                        "idMaestro":inputIdUsuario,
                        "usuario":inputUsuario,
                        "nombreMaestro":inputNombre,
                        "contraseña":inputContraseña,
                        "estatus":inputEstatus,
                        "opcion":opcion
                    },
                    success:(data,textStatus,jqXHR)=>{
                        const obj=JSON.parse(data);
                        if (obj.status==200) {
                            Swal.fire({
                                position: 'center',
                                icon: 'success',
                                title: `${obj.msj}`,
                                showConfirmButton: false,
                                timer: 1000
                            });
                            setTimeout(()=>{
                                $("#editMaestro").modal('hide');  
                            },1000);
                            $('#tbodyidMaestros').html(obj.listaMaestros)
                        }
                        else{
                          Swal.fire({
                                    position:'center',
                                    icon: 'error',
                                    title: 'Error...',
                                    text: `${obj.msj}`,
                                    showConfirmButton: false,
                                    timer: 1000
                            });
                        }
                    },
                    error:(error)=>{
                        debugger;
                        Swal.fire({
                            icon: 'error',
                            title: 'Error...',
                            text: 'Error inesperado: '+error.toString(),
                          })
                    }
                });
        });
        //Editar maestro
    /*MAESTRO*/
    
    
    /*ALUMNO*/
        
        //Eliminar alumno
            $('#tbodyidAlumnos').on('click', '#EliminarAlumno',function (){
            let currow=$(this).closest('tr');
            let idAlumno=currow.find('th').text();
            let opcion=$('#EliminarMaestro').val();
            Swal.fire({
                title: 'Are you sure?',
                text: "¿Está seguro de eliminar este registro?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
              }).then((result) => {
                   if (result.isConfirmed) {
                       $.ajax({
                          type:"POST",
                          url:"AlumnoController",
                          datatype:"json",
                          data:{
                              "idAlumno":idAlumno,
                                "opcion":opcion
                          },
                          success:(data,textStatus,jqXHR)=>{
                              let obj=JSON.parse(data);
                              if (obj.status==200) {
                                  $("#tbodyidAlumnos").html(obj.listaAlumnos);
                                    Swal.fire({
                                     position: 'center',
                                     icon: 'success',
                                     title: `${obj.msj}`,
                                     showConfirmButton: false,
                                     timer: 1000
                                   });
                                   
                                }
                                else{
                                    Swal.fire({
                                        position:'center',
                                        icon: 'error',
                                        title: 'Error...',
                                        text: `${obj.msj}`,
                                        showConfirmButton: false,
                                        timer: 1000
                                    });
                                }
                          }, error:(error)=>{
                                debugger;
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error...',
                                    text: 'Error inesperado: '+error.toString(),
                                  })
                            }
                       });
                   }
              });
        });
        
        //Editar alumno
            $('#tbodyidAlumnos').on('click', '#EditarAlumno',function (){
                let currow=$(this).closest('tr');
                let idAlumno=currow.find('th').text();
                let opcion=$('#EditarAlumno').val();
                 $.ajax({
                    type:"POST",
                    datatype:"json",
                    url:"AlumnoController",
                    data:{
                        "idAlumno":idAlumno,
                        "opcion":opcion
                    },success:(data,textStatus,jqXHR)=>{
                        const obj=JSON.parse(data);
                        if (obj.status==200) {
                            const alumno=JSON.parse(obj.Alumno);
                            $('#inputIdAlumno').val(alumno.matricula);
                            $('#inputUsuarioAlumno').val(alumno.usuario);
                            $('#inputNombreAlumno').val(alumno.nombre);
                             if (alumno.estatus) {
                                $('#inputEstatusAlumno').prop('checked',alumno.estatus);
                            }
                            else{
                                $('#inputEstatusAlumno').prop('checked',alumno.estatus);
                            }
                        }
                        else{
                            Swal.fire({
                                position:'center',
                                icon: 'error',
                                title: 'Error...',
                                text: `${obj.msj}`,
                                showConfirmButton: false,
                                timer: 1000
                            });
                        }
                        
                        
                    },error:(error)=>{
                        debugger;
                        Swal.fire({
                            icon: 'error',
                            title: 'Error...',
                            text: 'Error inesperado: '+error.toString(),
                          })
                    }
                });
                
                
            });
            
            $('#editAlumnoForm').submit((event)=>{
                 event.preventDefault();
                 const idAlumno=$('#inputIdAlumno').val();
                 const inputUsuarioAlumno=$('#inputUsuarioAlumno').val();
                 const inputNombreAlumno=$('#inputNombreAlumno').val();
                 const inputContraseñaAlumno=$('#inputContraseñaAlumno').val();
                 const inputEstatusAlumno=$('#inputEstatusAlumno').prop('checked');
                let opcion=$('#EditarAlumnoSetInfo').val(); 
                debugger;
                $.ajax({
                    type:"POST",
                    datatype:"json",
                    url:"AlumnoController",
                    data:{
                        "idAlumno":idAlumno,
                        "inputUsuarioAlumno":inputUsuarioAlumno,
                        "inputNombreAlumno":inputNombreAlumno,
                        "inputContraseñaAlumno":inputContraseñaAlumno,
                        "inputEstatusAlumno":inputEstatusAlumno,
                        "opcion":opcion
                    }
                    ,success:(data,textStatus,jqXHR)=>{
                            const obj=JSON.parse(data);
                            if (obj.status==200) {
                                Swal.fire({
                                    position: 'center',
                                    icon: 'success',
                                    title: `${obj.msj}`,
                                    showConfirmButton: false,
                                    timer: 1000
                                  });
                                  setTimeout(()=>{
                                        $("#editAlumno").modal('hide');  
                                    },1000);
                                   $("#tbodyidAlumnos").html(obj.listaAlumnos);
                            }
                            else{
                               Swal.fire({
                                    position:'center',
                                    icon: 'error',
                                    title: 'Error...',
                                    text: `${obj.msj}`,
                                    showConfirmButton: false,
                                    timer: 1000
                                });
                            }
                    }
                    ,error:(error)=>{
                        debugger;
                        Swal.fire({
                            icon: 'error',
                            title: 'Error...',
                            text: 'Error inesperado: '+error.toString(),
                          })
                    }
                })
            });
        //Editar alumno
            
    /*ALUMNO*/
    
    /*MATERIA*/
        //Eliminar materia
        $('#tbodyidMateria').on('click', '#EliminarMateria',function(){
            let currow=$(this).closest('tr');
            let idMateria=currow.find('th').text();
            let opcion=$('#EliminarMateria').val();
            debugger;
            Swal.fire({
                title: 'Are you sure?',
                text: "¿Está seguro de eliminar este registro?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
              }).then((result) => {
                   if (result.isConfirmed) {
                       $.ajax({
                          type:"POST",
                          url:"materiaController",
                          datatype:"json",
                          data:{
                                "idMateria":idMateria,
                                "opcion":opcion
                          },
                          success:(data,textStatus,jqXHR)=>{
                              let obj=JSON.parse(data);
                              if (obj.status==200) {
                                    Swal.fire({
                                     position: 'center',
                                     icon: 'success',
                                     title: `${obj.msj}`,
                                     showConfirmButton: false,
                                     timer: 1000
                                   });
                                   $("#tbodyidMateria").html(obj.listaMaterias);
                                }
                                else{
                                    Swal.fire({
                                        position:'center',
                                        icon: 'error',
                                        title: 'Error...',
                                        text: `${obj.msj}`,
                                        showConfirmButton: false,
                                        timer: 1000
                                    });
                                }
                          }, error:(error)=>{
                                debugger;
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error...',
                                    text: 'Error inesperado: '+error.toString(),
                                  })
                            }
                       });
                   }
              });
        });
        
        //Editar materia
            //get info materia
             $('#tbodyidMateria').on('click', '#EditarMateria',function(){
                let currow=$(this).closest('tr');
                let idMateria=currow.find('th').text();
                let opcion=$('#EditarMateria').val();
                debugger;
                $.ajax({
                    type:"POST",
                    datatype:"json",
                    url:"materiaController",
                    data:{
                        "idMateria":idMateria,
                        "opcion":opcion
                    },success:(data,textStatus,jqXHR)=>{
                        const obj=JSON.parse(data);
                        if (obj.status==200) {
                            const materia=JSON.parse(obj.Materia);
                            $('#inputClaveMateria').val(materia.claveMateria);
                            $('#inputNombreMateria').val(materia.nombreMateria);
                             if (materia.estatus) {
                                $('#inputEstatusMateria').prop('checked',materia.estatus);
                            }
                            else{
                                $('#inputEstatusMateria').prop('checked',materia.estatus);
                            }
                        }
                        else{
                            Swal.fire({
                                position:'center',
                                icon: 'error',
                                title: 'Error...',
                                text: `${obj.msj}`,
                                showConfirmButton: false,
                                timer: 1000
                            });
                        }
                        
                        
                    },error:(error)=>{
                        debugger;
                        Swal.fire({
                            icon: 'error',
                            title: 'Error...',
                            text: 'Error inesperado: '+error.toString(),
                          })
                    }
                });
             });
             
             //set info materia
             $('#editMateriaForm').submit((event)=>{
                event.preventDefault();
                const idMateria=$('#inputClaveMateria').val();
                const inputNombreMateria=$('#inputNombreMateria').val();
                const inputEstatusMateria=$('#inputEstatusMateria').prop('checked');
                let opcion=$('#EditarMateriaSetInfo').val(); 
                debugger;
                if (inputNombreMateria!="") {
                        $.ajax({
                            type:"POST",
                            datatype:"json",
                            url:"materiaController",
                            data:{
                                "idMateria":idMateria,
                                "inputNombreMateria":inputNombreMateria,
                                "inputEstatusMateria":inputEstatusMateria,
                                "opcion":opcion
                            },success:(data,textStatus,jqXHR)=>{
                                const obj=JSON.parse(data);
                                debugger;
                                if (obj.status==200) {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'success',
                                        title: `${obj.msj}`,
                                        showConfirmButton: false,
                                        timer: 1000
                                      });
                                      setTimeout(()=>{
                                            $("#EditMateria").modal('hide'); 
                                            $("#tbodyidMateria").html(obj.listaMaterias);
                                        },1000);
                                        
                                }
                                else{
                                   Swal.fire({
                                        position:'center',
                                        icon: 'error',
                                        title: 'Error...',
                                        text: `${obj.msj}`,
                                        showConfirmButton: false,
                                        timer: 1000
                                    });
                                }
                            },
                            error:(error)=>{
                                debugger;
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error...',
                                    text: 'Error inesperado: '+error.toString(),
                                  })
                            }
                        });
                }
                else{
                    Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'No puedes dejar el nombre vacio'
                    });
                }
             });
    
        //Editar materia
    /*MATERIA*/

    /*GRUPO*/
        //Eliminar grupo
        $('#tbodyidGrupo').on('click','#EliminarGrupo',function(){
            let currow=$(this).closest('tr');
            let idGrupo=currow.find('th').text();
            let opcion=$('#EliminarGrupo').val();
            debugger;
            Swal.fire({
                title: 'Are you sure?',
                text: "¿Está seguro de eliminar este registro?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
              }).then((result) => {
                   if (result.isConfirmed) {
                       $.ajax({
                          type:"POST",
                          url:"grupoController",
                          datatype:"json",
                          data:{
                                "idGrupo":idGrupo,
                                "opcion":opcion
                          },
                          success:(data,textStatus,jqXHR)=>{
                              let obj=JSON.parse(data);
                              if (obj.status==200) {
                                    Swal.fire({
                                     position: 'center',
                                     icon: 'success',
                                     title: `${obj.msj}`,
                                     showConfirmButton: false,
                                     timer: 1000
                                   });
                                   $("#tbodyidGrupo").html(obj.listaGrupos);
                                }
                                else{
                                    Swal.fire({
                                        position:'center',
                                        icon: 'error',
                                        title: 'Error...',
                                        text: `${obj.msj}`,
                                        showConfirmButton: false,
                                        timer: 1000
                                    });
                                }
                          }, error:(error)=>{
                                debugger;
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error...',
                                    text: 'Error inesperado: '+error.toString(),
                                  })
                            }
                       });
                   }
              });
        });
    
        //Editar grupo getinfo
        $('#tbodyidGrupo').on('click','#EditarGrupo',function(){
            let currow=$(this).closest('tr');
            let idGrupo=currow.find('th').text();
            let opcion=$('#EditarGrupo').val();
            debugger;
            $.ajax({
                    type:"POST",
                    datatype:"json",
                    url:"grupoController",
                    data:{
                        "idGrupo":idGrupo,
                        "opcion":opcion
                    },success:(data,textStatus,jqXHR)=>{
                            const obj=JSON.parse(data);
                            debugger;
                        if (obj.status==200) {
                            const grupo=JSON.parse(obj.Grupo);
                            $('#inputidGrupo').val(grupo.idGrupo);
                            $('#inputnumAlumnosGrupo').val(grupo.numAlumnos);
                            $('#materiaId').val(grupo.claveMateriaGrupo);
                            if (grupo.estatus) {
                                $('#inputEstatusGrupo').prop('checked',grupo.estatus);
                            }
                            else{
                                $('#inputEstatusGrupo').prop('checked',grupo.estatus);
                            }
                        }
                        else{
                            Swal.fire({
                                position:'center',
                                icon: 'error',
                                title: 'Error...',
                                text: `${obj.msj}`,
                                showConfirmButton: false,
                                timer: 1000
                          });
                        }

                        
                    },error:(error)=>{
                        debugger;
                        Swal.fire({
                            icon: 'error',
                            title: 'Error...',
                            text: 'Error inesperado: '+error.toString(),
                          })
                    }
                });
        });
        
        $('#editGrupoForm').submit((event)=>{
            event.preventDefault();
            const idGrupo=$('#inputidGrupo').val();
            const opcion=$('#EditarGrupoSetInfo').val();
            const materiaId=$('#materiaId').val();
            const inputnumAlumnosGrupo=$('#inputnumAlumnosGrupo').val();
            const inputEstatusGrupo=$('#inputEstatusGrupo').prop("checked");
            debugger;
            $.ajax({
                type:"POST",
                datatype:"json",
                url:"grupoController",
                data:{
                    "idGrupo":idGrupo,
                    "opcion":opcion,
                    "materiaId":materiaId,
                    "inputnumAlumnosGrupo":inputnumAlumnosGrupo,
                    "inputEstatusGrupo":inputEstatusGrupo
                },success:(data,textStatus,jqXHR)=>{
                                const obj=JSON.parse(data);
                                if (obj.status==200) {
                                    Swal.fire({
                                        position: 'center',
                                        icon: 'success',
                                        title: `${obj.msj}`,
                                        showConfirmButton: false,
                                        timer: 500
                                      });
                                      setTimeout(()=>{
                                            $("#EditGrupo").modal('hide'); 
                                        },1000);
                                        $('#inputidGrupo').val('null');
                                        $('#inputnumAlumnosGrupo').val('');
                                        $('#inputEstatusGrupo').prop('checked',false);
                                            
                                       $("#tbodyidGrupo").html(obj.listaGrupos);
                                }
                                else{
                                    Swal.fire({
                                        position:'center',
                                        icon: 'error',
                                        title: 'Error...',
                                        text: `${obj.msj}`,
                                        showConfirmButton: false,
                                        timer: 1000
                                    });
                                }
                            },
                            error:(error)=>{
                                debugger;
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Error...',
                                    text: 'Error inesperado: '+error.toString(),
                                  })
                            }
            });
        });
    
    /*GRUPO*/
    
    
});

/*currow.find('td:eq(0)').css( "color", "red" );
        currow.find('td:eq(1)').css( "color", "blue" );
        currow.find('td:eq(2)').css( "color", "yellow" );
        currow.find('td:eq(-1)').css( "color", "pink" );*/

