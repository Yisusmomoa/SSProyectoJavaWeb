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
                          setTimeout(()=>{
                              //location.href="servletprueba"
                              //location.href="./ProyectoCursoJavaWeb-SS2022/paginaPrincipal/PaginaPrincipal.jsp"
                              location.href="paginaPrincipalController";
                          },2000);
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
                                                        icon: 'error',
                                                        title: 'Oops...',
                                                        text: 'Usuario o contraseña incorrecta!'
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
                                                        icon: 'error',
                                                        title: 'Oops...',
                                                        text: 'Usuario o contraseña incorrecta!'
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
    
});