# queremosiramisa
Repositororio de ayuda para el proyecto queremosiramisa. 

### Pasos a seguir para añadir soporte de creación de códigos QR desde una aplicación JavaEE

1. Añadir las dependencias de maven de las librerías de Zing al archivo `pom.xml` adjunta en el repositorio.
2. Añadir al proyecto la clase *`GeneradorQR.java`* adjunta en el repositorio.
3. Dicha clase, tiene un método estático llamado *`.generarQR(String texto)`* donde texto es lo que queremos que ponga en el QR.
4. La clase devuelve un String que es la imagen directamente en formato Base64.
5. Asigna ese String devuelto directamente al atributo `src` de una imagen.

> **Nota:** La imagen generada será de 300x300 pixels. Si necesitas modificar el tamaño, puedes editar el método `.generarQR()` y cambiar esos valores.

### Ejemplo
##### Desde el servlet Java:
``` [Java]
//Convertir un texto en una imagen QR
String qrCode = GeneradorQR.generarQR("El texto que quieras en tu QR");
//Y ahora lo enviamos a la vista
request.setAttribute("imagen", qrCode);
```
##### Desde la vista:
``` [JSP]
<img src="${imagen}" alt="" title="QR generado" />
```
