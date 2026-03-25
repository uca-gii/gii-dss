# DAO Pattern - Ejemplo Clásico

Este directorio contiene una implementación clásica del patrón **Data Access Object (DAO)** usando JDBC puro.

## Contenido

- `Usuario.java` - Entidad de dominio (POJO)
- `UsuarioDAO.java` - Interfaz que define el contrato del DAO
- `UsuarioDAOImpl.java` - Implementación JDBC del DAO
- `PersistenceException.java` - Excepción personalizada para persistencia

## Características

✓ Separación de responsabilidades: lógica de negocio desacoplada del acceso a datos
✓ Interfaz clara: contrato bien definido entre negocio y persistencia
✓ Encapsulación SQL: toda la lógica JDBC está en `UsuarioDAOImpl`
✓ Testeable: fácil crear mocks de `UsuarioDAO` para tests unitarios
✓ Manejo de excepciones: SQL exceptions wrapped en `PersistenceException`

## Ventajas de este patrón

- **Mantenibilidad**: cambios en SQL no afectan al código de negocio
- **Flexibilidad**: cambiar BD relacional a NoSQL solo afecta `UsuarioDAOImpl`
- **Testabilidad**: inyectar mocks de `UsuarioDAO` en tests
- **Centralización**: toda la lógica de queries SQL en un único lugar

## Desventajas

- **Boilerplate**: mucho código repetitivo (CRUD methods)
- **Rigidez**: consultas complejas requieren nuevos métodos en la interfaz
- **Mantenimiento**: cambios en la entidad requieren actualizar el DAO

## Evolución moderna

Este patrón clásico fue **evolucionando** hacia patrones más modernos como:
- **Repository Pattern**: proporciona operaciones CRUD genéricas sin implementación manual
- **Spring Data JPA**: automatiza la generación de queries basándose en convenciones de nombre

## Base de datos esperada

```sql
CREATE TABLE usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);
```

## Uso

```java
// Obtener una conexión
Connection conn = DriverManager.getConnection(dbUrl, user, password);

// Crear el DAO
UsuarioDAO dao = new UsuarioDAOImpl(conn);

// Usar el DAO
Usuario usuario = new Usuario("Juan", "juan@example.com");
dao.create(usuario);

List<Usuario> todos = dao.findAll();
Usuario encontrado = dao.read(1);
```
