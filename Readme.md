# Carrito de Compras - Spring Boot MVC

## Requisitos previos
- Java 17 instalado y disponible en tu `PATH`
- Maven 3.8+ instalado
- Git para clonar el repositorio

## Cómo descargar el proyecto
1. Clona el repositorio: `git clone <URL_DEL_REPOSITORIO>`
2. Entra a la carpeta clonada: `cd ecommerce-mvc`

> Si descargaste un `.zip`, descomprímelo y sitúate dentro de la carpeta del proyecto antes de continuar.

## Cómo levantar la aplicación
1. Instala Maven si no lo tienes disponible (ver sección de problemas frecuentes).
2. Instala las dependencias y compila: `mvn clean package`
3. Ejecuta la aplicación con Maven: `mvn spring-boot:run`
   - Asegúrate de escribir `mvn` (no `mvm`).
   - Alternativa: `java -jar target/carrito-1.0.0.jar` después del empaquetado.
4. Abre el navegador y visita `http://localhost:8080`

## Ejecución desde VS Code
- Abre el panel `Run and Debug` y selecciona la configuración `Spring Boot-CarritoApplication<carrito>` incluida en `.vscode/launch.json`.
- También puedes usar el `Spring Boot Dashboard` del plugin oficial de VS Code para lanzar el proyecto; el botón `Run` usará la misma clase principal (`com.tienda.carrito.CarritoApplication`).
- Si el dashboard intenta usar el wrapper (`./mvnw`) y el proyecto no lo tiene, edita la configuración del plugin para ejecutar `mvn spring-boot:run` o lanza la app desde el panel `Run and Debug`.
- El plugin puede levantar la app aunque `mvn` no esté disponible porque ejecuta directamente la clase principal usando el runtime de Java; aun así instalar Maven es recomendable para tareas de build, empaquetado o pruebas desde la terminal.

## Arquitectura MVC aplicada
- **Modelo**: las clases `Producto` y `ItemCarrito` (`src/main/java/com/tienda/carrito/model/`) describen los datos que maneja la aplicación: el catálogo y los ítems que va acumulando el usuario.
- **Vista**: las plantillas Thymeleaf ubicadas en `src/main/resources/templates/` (`login.html`, `catalogo.html`, `carrito.html`) renderizan la interfaz HTML utilizando la información que envían los controladores.
- **Controlador**: `CarritoController` (`src/main/java/com/tienda/carrito/controller/CarritoController.java`) expone las rutas, recibe las acciones del usuario y prepara el `Model` con los datos a mostrar en la vista.
- **Capa de servicio**: `CarritoService` (`src/main/java/com/tienda/carrito/service/CarritoService.java`) concentra la lógica del negocio (gestión del carrito, cálculo de totales) para que los controladores permanezcan ligeros.
- Esta separación permite cambiar la capa de presentación o extender la lógica de negocio sin acoplar directamente la vista con los datos.

## Comandos útiles
- Ejecutar pruebas: `mvn test`
- Limpiar artefactos generados: `mvn clean`

## Problemas frecuentes
- **Java no encontrado**: verifica que `java -version` responde con la versión 17.
- **`mvn` no se reconoce**: confirma que Maven esté instalado y que el comando correcto sea `mvn` (sin la letra intermedia `m`).
- **Instalar Maven en macOS**: `brew install maven`
- **Instalar Maven en Windows**: descarga el ZIP desde https://maven.apache.org/install.html, descomprímelo y agrega la ruta `bin` a la variable `PATH`.
- Después de instalar ejecuta `mvn -v` para comprobar que el comando funciona.
- **Puerto ocupado (8080)**: detén el proceso que usa ese puerto o modifica el puerto en `application.properties`.
