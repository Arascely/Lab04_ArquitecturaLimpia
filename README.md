# GUÍA DE LABORATORIO N.° 04
## Implementación de Arquitectura Hexagonal
- Asignatura: IS488 – Arquitectura de Software | Semestre: 2026-II
Laboratorio: N.° 04
- Tema: Implementación de Arquitectura Hexagonal en Java mediante IntelliJ IDEA
- Estudiante: Rodriguez Quispe Grissel Arascely
## I. TÍTULO DE LA PRÁCTICA
Implementación de Arquitectura Hexagonal (Ports and Adapters) en Java utilizando IntelliJ IDEA.

## II. CASO PRÁCTICO: SISTEMA DE GESTIÓN ACADÉMICA

La aplicación de consola administra estudiantes con los siguientes atributos:

| Atributo | Tipo | Descripción |
| --- | --- | --- |
| id | `int` | Identificador del estudiante |
| nombre | `String` | Nombres y apellidos |
| correo | `String` | Correo electrónico |

### Estado actual de las operaciones

| Operación | Estado | Observación |
| --- | --- | --- |
| Registrar estudiantes | Parcial | PostgreSQL realiza el `INSERT`, pero no existe una validación explícita de identificadores duplicados en el servicio. El adaptador JSON no agrega el estudiante a la lista antes de guardarla. |
| Listar estudiantes | Cumple con PostgreSQL | El adaptador PostgreSQL consulta los registros ordenados por identificador. El adaptador JSON puede listar, pero no representa una solución completa mientras sus demás operaciones estén incompletas. |
| Actualizar nombre y correo | Cumple con PostgreSQL | PostgreSQL actualiza ambos campos y devuelve si encontró el identificador. El adaptador JSON actualmente devuelve `true` sin actualizar datos. |
| Eliminar por identificador | Cumple con PostgreSQL | PostgreSQL elimina por `id` y devuelve si la operación afectó registros. El adaptador JSON actualmente devuelve `true` sin eliminar datos. |

### Arquitectura hexagonal

La estructura principal ya contiene un modelo de dominio, un puerto de entrada (`EstudianteUseCase`), un servicio de aplicación, un puerto de salida (`EstudianteRepository`) y adaptadores de persistencia JSON y PostgreSQL.

## III. RESPONSABILIDAD DE LOS ARCHIVOS

| Archivo | Responsabilidad | Estado |
| --- | --- | --- |
| `Estudiante.java` | Representa el modelo del estudiante con `id`, `nombre` y `correo`. | Cumple. |
| `EstudianteUseCase.java` | Define las operaciones disponibles para los adaptadores de entrada: registrar, listar, actualizar y eliminar. | Cumple. |
| `EstudianteRepository.java` | Define el contrato de salida para consultar y guardar estudiantes. Ahora está ubicado en `application.port.out`. | Cumple. |
| `EstudianteService.java` | Implementa los casos de uso y coordina las operaciones con el repositorio. | Cumple parcialmente: todavía no valida identificadores duplicados. |
| `EstudianteUI.java` | Interactúa con el usuario mediante la consola y utiliza el puerto de entrada. | Cumple. |
| `EstudianteRepositoryJson.java` | Adaptador que debe leer y escribir los estudiantes en formato JSON. | Parcial: listar funciona, pero registrar, buscar, actualizar y eliminar están incompletos. |
| `Main.java` | Construye los adaptadores, servicios y componentes de presentación, e inyecta sus dependencias. También activa la navegación de cursos. | Cumple. |
| `estudiantes.json` | Archivo de persistencia de los registros de estudiantes cuando se utiliza el adaptador JSON. | Cumple como archivo de datos, pero no es la persistencia activa actualmente. |


## IV. CONFIGURACIÓN DE PERSISTENCIA ACTUAL

La aplicación utiliza actualmente `EstudianteRepositoryPostgres` para la gestión de estudiantes. `EstudianteRepositoryJson` y `data/estudiantes.json` se conservan como un adaptador alternativo y evidencia de persistencia, pero no son instanciados por `Main`, por lo que no modifican la ejecución actual.

La gestión de cursos continúa utilizando el adaptador JSON. Por tanto, el proyecto mantiene dos mecanismos de persistencia: PostgreSQL para estudiantes y JSON para cursos.


## V. EVIDENCIAS A PRESENTAR

- Estructura de paquetes del proyecto en IntelliJ IDEA.
- Código del puerto de entrada `EstudianteUseCase`.
- Código del puerto de salida `EstudianteRepository`.
- Implementación de `EstudianteService`.
- Implementación de `EstudianteRepositoryJson`.
- Configuración de dependencias en `Main.java`.
- Capturas de las operaciones de registrar, listar, actualizar y eliminar.
- Archivo `estudiantes.json` generado durante las pruebas con la persistencia JSON.
- Respuestas del cuestionario.
- Conclusiones de la práctica.

Las capturas, respuestas y conclusiones deben prepararse como evidencias del informe; no forman parte del código fuente.

## VI. Informe

https://docs.google.com/document/d/1oQoODFOZRsz4iWZPOKHlEL1onfq8aSo2RonBKZrz-sk/edit?usp=sharing



