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

