# AGENDAS API

## Descripción

AGENDAS API es una aplicación que permite a los usuarios agendar citas dentro de un rango de horario establecido.

---

## Características

- Gestiona horarios disponibles para citas.
- Permite a los usuarios agendar, modificar y cancelar citas.

---

## Tecnologías

Este proyecto utiliza las siguientes tecnologías:

- **Java**: Lenguaje principal para la implementación de la lógica de negocio.
- **Spring Boot**: Framework para desarrollar la API REST.
- **H2**: Base de datos utilizada en memoria para almacenar los datos de las citas y evitar que el usuario tenga que crear la base de datos y de esta forma poder probar la aplicacion mas facilmente.
- **Maven**: Herramienta para la gestión de dependencias.
- **Swagger**: Para la documentación interactiva de la API.

---

## Instalación

1. Clona el repositorio:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd agendas-api
   ```
   
2. Construye el proyecto con Maven:
   ```bash
   mvn clean install
   ```

3. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

---

## Uso

Se adjunta una coleccion de postman en la raiz del proyecto
```
Nombre del archivo : Agendas.postman_collection.json
```

Para una documentación más detallada de los endpoints, accede al panel de Swagger en:
```
http://localhost:8080/swagger-ui/index.html
```