Encartados - Gestión de Stock y Ventas

Descripción del Proyecto

Encartados es una aplicación móvil para la gestión de stock y ventas en una tienda. La aplicación permite a los usuarios:

Registrar cuentas y autenticarse.

Gestionar productos en stock.

Agregar productos a un carrito de compras.

Realizar compras y manejar el inventario.

Acceder a funcionalidades extra como grabación de audio, detección de luz, resolución de pantalla y estado de la batería.

Características Principales

✅ Registro e inicio de sesión con almacenamiento en SQLite.
✅ Gestión de stock con una interfaz intuitiva.
✅ Carrito de compras funcional con actualización en tiempo real.
✅ Soporte para RecyclerView y LiveData.
✅ Módulo multifunción que usa sensores del dispositivo.
✅ Grabación y reproducción de audio integrada.
✅ Diseño adaptable a temas claro y oscuro.

Instalación y Configuración

Requisitos Previos

Android Studio (versión 2022.1 o superior)

SDK de Android (API nivel 24 o superior)

Gradle 7.0+

Dispositivo o emulador con Android 7.0+

Pasos de Instalación

Clonar el repositorio:

git clone https://github.com/usuario/encartados.git
cd encartados

Abrir el proyecto en Android Studio.

Configurar un emulador o conectar un dispositivo físico.

Ejecutar el proyecto con Run ▶.

Estructura del Proyecto

📂 app/src/main/java/com/example/encartados/

📁 database/

UserDatabaseHelper.kt: Maneja la base de datos SQLite.

📁 ui/auth/

LoginActivity.kt: Implementa autenticación de usuario.

📁 ui/home/

MainActivity.kt: Administra la Navigation Drawer y fragmentos.

BaseActivity.kt: Clase base para la navegación de la app.

📁 ui/multifunction/

LightSensorFragment.kt: Detecta la luz ambiental.

LocationFragment.kt: Obtiene la ubicación del usuario.

DeviceResolutionFragment.kt: Muestra la resolución del dispositivo.

PowerConnectionFragment.kt: Detecta el estado de carga de la batería.

📁 ui/stock/

StockActivity.kt: Muestra la lista de productos en stock.

StockAdapter.kt: Adaptador para mostrar productos en RecyclerView.

Cart.kt: Clase que maneja la lógica del carrito.

CartAdapter.kt: Muestra productos dentro del carrito.

AddToCartDialog.kt: Diálogo para agregar productos al carrito.

CartDialog.kt: Diálogo que permite ver y vaciar el carrito.

📁 utils/

AudioRecorderActivity.kt: Permite grabar y reproducir audio.

Uso del Proyecto

Registro e Inicio de Sesión

Introducir email y contraseña.

Presionar "Register" para crear una cuenta.

Para iniciar sesión, usar "Login".

Gestión de Stock

Acceder a la pestaña Stock.

Agregar productos con el botón "➕".

Editar o eliminar productos desde la lista.

Carrito de Compras

En la vista de stock, seleccionar un producto.

Especificar cantidad y presionar "Agregar al carrito".

Revisar los productos en CartDialog.

Finalizar compra con "Comprar".

Funciones Adicionales

Grabación de Audio: Ir a AudioRecorderActivity para grabar y reproducir sonidos.

Sensores del Dispositivo: Explorar MultiFunctionActivity para ver el estado de los sensores.

Gestión del Proyecto con GitHub

Creación de Issues

Ir a la pestaña Issues en el repositorio.

Hacer clic en New Issue.

Escribir un título y descripción detallada del problema.

Etiquetarlo con bug, enhancement o documentation.

Asignarlo a un colaborador.

Resolución de Issues

Clonar el repositorio y crear una nueva rama:

git checkout -b fix-bug-login

Solucionar el problema en el código.

Confirmar y subir los cambios:

git commit -m "Corrige error de login"
git push origin fix-bug-login

Crear un Pull Request y solicitar revisión.

Registro de 10 Issues Reales

#

Issue

Estado

1

Error al iniciar sesión

Resuelto

2

Problema con la base de datos

Resuelto

3

Implementar modo oscuro

Resuelto

4

Mejorar diseño de StockActivity.kt

Pendiente

5

Validación de email en LoginActivity.kt

Resuelto

6

Implementar autenticación con Firebase

Pendiente

7

Añadir soporte para varios idiomas

Pendiente

8

Optimizar carga de imágenes

Resuelto

9

Mejorar persistencia del carrito

Resuelto

10

Corregir errores en la UI de CartDialog.kt

Resuelto

Conclusión

Encartados es una aplicación completa y funcional para la gestión de stock y ventas, con funcionalidades avanzadas y una interfaz intuitiva.

Siguiendo esta documentación, los desarrolladores pueden entender la arquitectura del proyecto y contribuir con mejoras de manera eficiente. 🚀

