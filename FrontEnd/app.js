function registerNewUser() {
  var newUser = document.getElementById('usuario').value;
  var newPassword = document.getElementById('newPassword').value;
  var confirmPassword = document.getElementById('confirmPassword').value;
  var role = document.getElementById('role').value;
  var nombre = document.getElementById('nombre').value;
  var apellido = document.getElementById('apellido').value;
  var altura = document.getElementById('altura').value;
  var calle = document.getElementById('calle').value;
  var ciudad = document.getElementById('ciudad').value;
  var provincia = document.getElementById('provincia').value;
  var pais = document.getElementById('pais').value;
  var email = document.getElementById('email').value;
  var telefono = document.getElementById('telefono').value;
  var empresa = document.getElementById('empresa').value; //n
  var Discapacidad = document.getElementById('discapacidad').value;
  if (role === "cliente") {
    var discapacidad = document.getElementById('discapacidad').value;
    if (discapacidad === "") {
      alert("Por favor, ingresa su discapacidad");
      return;
    }
  }// Verifica que las contraseñas coincidan
  if (newPassword !== confirmPassword) {
    alert("Las contraseñas no coinciden");
    return;
  }
  // Validación de formato de correo electrónico
  var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
  if (!emailPattern.test(email)) {
    alert("Por favor, ingresa un correo electrónico válido");
    return;
  }
  // Verifica si la contraseña contiene caracteres especiales
  var specialChars = /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]+/;
  if (specialChars.test(newPassword)) {
    alert("La contraseña no puede contener caracteres especiales");
    return;
  }


  // Verifica si se seleccionó el rol "vendedor" para mostrar el campo "empresa"
  if (role === "vendedor") {
    var empresa = document.getElementById('empresa').value;

    // Verifica que el campo de empresa no esté vacío
    if (empresa === "") {
      alert("Por favor, ingresa el nombre de la empresa");
      return;
    }
  }

  // Solicitud de registro
  const registerData = {
    id: int,
    password: newPassword,
    tipoUsuario: role,
    nombre: nombre,
    apellido: apellido,
    domicilio: calle + " " + altura + ", " + ciudad+ ", "+ provincia + " " +pais,
    email: mail,
    telefono: telefono,
    empresa: empresa, //n
    discapacidad: discapacidad,
  };
  if (role=="administrador"){
    registerNewAdmin(registerData)
    window.location.href = 'inicioadmin.html'; 
  }
  if (role=="vendedor"){
    registerNewVendedor(registerData)
  }
  if (role=="cliente"){
    registerNewCliente(registerData)
    alert("Usuario registrado con éxito como cliente");
    window.location.href = 'usuariosnoadministradores.html'; 
  }
}

function login() {
  var user = document.getElementById('user').value;
  var pass = document.getElementById('pass').value;

  // Solicitud de inicio de sesión
  const loginData = {
    user: user,
    password: pass
  };

  fetch('http://localhost:8081/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(loginData)
  })
    .then(response => {
      if (response.ok) {
        response.text().then(role => {
          if (role === 'admin') {
            // Redirige a la página del administrador
            window.location.href = 'inicioadmin.html';
          } else {
            // Redirige a la página de usuarios que no son administradores
            window.location.href = 'usuarios noadministradores.html';
          }
        });
      } else {
        response.text().then(errorMessage => {
          alert('Error al iniciar sesión: ' + errorMessage);
        });
      }
    });
}

function getAllUsers() {
  fetch('http://localhost:8081/getAll')
    .then(response => response.json())
    .then(users => {
      // Muestra la lista de usuarios en la página del administrador
      var userList = document.getElementById('userList');
      userList.innerHTML = '';
      users.forEach(user => {
        var listItem = document.createElement('li');
        listItem.textContent = user.user;
        userList.appendChild(listItem);
      });
    })
    .catch(error => {
      alert('Error al obtener los usuarios: ' + error.message);
    });
}

function mostrarEmpresa(){
  role = document.getElementById('role').value;
  

  if (role =="vendedor"){
    var vendedorDisplay = document.getElementById('rolVendedor');
    document.getElementById("rolVendedor").style.display = "block";
  }
  else {
    document.getElementById("rolVendedor").style.display = "none";
  }
}

//Funciones para crear usuarios Administrador,Vendedor,Cliente.

//Administrador

function registerNewAdmin(adminData) {
  fetch('http://localhost:8081/administradores/nuevo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(adminData)
  })
    .then(response => {
      if (response.ok) {
        alert('Administrador registrado correctamente');
      } else {
        response.text().then(errorMessage => {
          alert('Error al registrar administrador: ' + errorMessage);
        });
      }
    });
}

//Vendedor

function registerNewVendedor(vendedorData) {
  fetch('http://localhost:8081/vendedores/nuevo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(vendedorData)
  })
    .then(response => {
      if (response.ok) {
        alert('Vendedor registrado correctamente');
      } else {
        response.text().then(errorMessage => {
          alert('Error al registrar vendedor: ' + errorMessage);
        });
      }
    });
}

//Cliente

function registerNewClient(clientData) {
  fetch('http://localhost:8081/clientes/nuevo', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(clientData)
  })
    .then(response => {
      if (response.ok) {
        alert('Cliente registrado correctamente');
      } else {
        response.text().then(errorMessage => {
          alert('Error al registrar cliente: ' + errorMessage);
        });
      }
    });
}

