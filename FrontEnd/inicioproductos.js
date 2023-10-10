var crearProductoForm;
var verProductosBtn;
document.addEventListener("DOMContentLoaded", function () {
    const crearProductoBtn = document.getElementById("crearProductoBtn");
    verProductosBtn = document.getElementById("verProductosBtn");
    const guardarProductoBtn = document.getElementById("guardarProductoBtn");
    const listaProductosDiv = document.getElementById("listaProductos");
    const precioInput = document.getElementById("precio");
    const modeloInput = document.getElementById("modelo");
    const descripcionInput = document.getElementById("descripcion");
    const marcaInput = document.getElementById("marca");
    crearProductoForm = document.getElementById("crearProductoForm");
    const actualizarProductoBtn = document.getElementById("actualizarProductoBtn");

    function validarCampos() {
        const precio = precioInput.value.trim();
        const modelo = modeloInput.value.trim();
        const descripcion = descripcionInput.value.trim();
        const marca = marcaInput.value.trim();

        if (precio === "" || modelo === "" || descripcion === "" || marca === "") {
            alert("Por favor, complete todos los campos.");
            return false;
        }

        return true;
    }

    function mostrarProductos() {
        // ... (código anterior)
    }

    // ... (código anterior)

    guardarProductoBtn.addEventListener("click", function() {
        if (validarCampos()) {
            const nuevoProducto = {
                precio: parseFloat(precioInput.value),
                modelo: modeloInput.value,
                descripcion: descripcionInput.value,
                marca: marcaInput.value
            };

            crearProducto(nuevoProducto);
        }
    });

    // ... (código anterior)

    function crearProducto(producto) {
        fetch('http://localhost:8081/productos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(producto)
        })
        .then(response => {
            if (response.ok) {
                mostrarProductos();
                crearProductoForm.style.display= 'none';
            }
        })
        .catch(error => console.error('Error:', error));
    }                               
});



function verFormularioProducto(){
    if (crearProductoForm.style.display == "none"){
        crearProductoForm.style.display= 'block';
        verProductosBtn.style.display = 'none';
    }
}
