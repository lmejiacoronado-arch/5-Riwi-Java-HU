# Corporate Talent Hub

Proyecto académico desarrollado en Java como evolución progresiva semanal, integrando conceptos desde arquitectura base hasta persistencia relacional con JDBC bajo patrón MVC.

## Tecnologías utilizadas

* Java 21
* Maven
* SQLite
* JDBC
* IntelliJ IDEA

## Estructura del proyecto

com.riwi.talent

* model
  Contiene entidades, records, DAO y lógica de persistencia.

* controller
  Contiene controladores que median entre vista y modelo.

* view
  Contiene la interacción por consola y flujo principal del sistema.

## Arquitectura aplicada

El proyecto fue refactorizado bajo patrón MVC para separar responsabilidades:

* Modelo: representa datos y acceso a base de datos.
* Vista: captura entradas del usuario mediante Scanner.
* Controlador: recibe datos de la vista y coordina operaciones del modelo.

## Evolución por semanas

### Semana 1

* Uso de los 8 tipos primitivos.
* Comparación entre clase tradicional y Record.
* Text Blocks.
* Operadores aritméticos y lógicos.
* Notas sobre JVM y Garbage Collector.

### Semana 2

* Switch tradicional y Switch Expression.
* Scanner con validaciones.
* Matrices bidimensionales.
* Casting explícito.
* Manejo de excepciones.

### Semana 3

* ArrayList y HashMap.
* List.of() y Map.of().
* Sequenced Collections de Java 21.
* removeIf().
* Reportes con colecciones dinámicas.

### Semana 4

* Sealed Classes.
* Records.
* Herencia y polimorfismo.
* Pattern Matching for instanceof.
* Interfaces con métodos default.

### Semana 5

* Persistencia relacional con SQLite.
* JDBC con try-with-resources.
* CRUD completo.
* DAO pattern.
* PreparedStatement.
* Integración MVC completa.
* Records aplicados a reportes de consulta.

## Base de datos

El sistema utiliza SQLite y genera automáticamente:

talent_hub.db

Tabla principal:

employees

Campos:

* id
* full_name
* email
* role
* base_salary
* score

## CRUD implementado

* Crear empleado
* Listar empleados
* Actualizar empleado
* Eliminar empleado

## Seguridad aplicada

Todas las consultas SQL usan PreparedStatement para prevenir SQL Injection.

## Gestión moderna de recursos

Se usa try-with-resources para:

* Connection
* PreparedStatement
* ResultSet

Esto evita fugas de memoria al cerrar automáticamente recursos JDBC.

## Record utilizado

EmployeeReport

Se utiliza para construir reportes inmutables desde consultas SQL.

## Cómo ejecutar

### Desde IntelliJ

Abrir el proyecto y ejecutar:

com.riwi.talent.view.ApplicationView

### Desde terminal

mvn clean install

mvn exec:java -Dexec.mainClass="com.riwi.talent.view.ApplicationView"

## Requisitos

* Java 21 configurado
* Maven instalado

## Autor

Proyecto desarrollado para Riwi como ejercicio progresivo de arquitectura Java moderna comparando prácticas Legacy (Java 8) y modernas (Java 17/21).
