# Repository Pattern - Spring Data JPA

Este directorio contiene una implementación moderna del patrón **Repository** usando Spring Data JPA.

## Contenido

- `Usuario.java` - Entidad JPA con anotaciones Lombok
- `UsuarioRepository.java` - Interfaz Repository (hereda de JpaRepository)
- `UsuarioService.java` - Capa de servicio (lógica de negocio)
- `Exceptions.java` - Excepciones personalizadas
- `Application.java` - Aplicación Spring Boot

## Características

✓ **Operaciones CRUD automáticas**: heredadas de `JpaRepository`
✓ **Query derivadas**: métodos generados automáticamente por Spring basándose en el nombre
✓ **Transacciones**: automáticas con `@Transactional`
✓ **Inyección de dependencias**: integrada con Spring
✓ **Testeable**: fácil crear mocks con `@MockBean`

## Comparación DAO vs Repository

| Aspecto | DAO (Clásico) | Repository (Moderno) |
|---------|---------------|----------------------|
| Implementación | Manual (JDBC) | Automática (Spring) |
| CRUD methods | Se implementan todos | Heredados de JpaRepository |
| Queries | SQL escrito a mano | Derivadas del nombre |
| Boilerplate | Alto | Bajo |
| Curva aprendizaje | Media | Baja |

## Query Derivation - Ejemplos

Spring Data JPA **genera automáticamente queries** basándose en la **convención de nombre**:

```
find + [By] + [Nombre del atributo] + [CondiciónOpcional]

Ejemplos:
- findByEmail(email) 
  → SELECT * FROM usuarios WHERE email = ?
  
- findByNombreContainingIgnoreCase(nombre)
  → SELECT * FROM usuarios WHERE LOWER(nombre) LIKE LOWER(?)
  
- findByCreatedAtAfter(fecha)
  → SELECT * FROM usuarios WHERE createdAt > ?
  
- existsByEmail(email)
  → SELECT COUNT(*) FROM usuarios WHERE email = ? (retorna boolean)
```

## Configuración Spring Boot (pom.xml)

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

## Configuración (application.properties)

```properties
# Base de datos H2 (en memoria)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# H2 Console (opcional)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## Uso

```java
// Inyectar el Repository
@Autowired
private UsuarioRepository usuarioRepository;

// CRUD automático (heredado de JpaRepository)
Usuario nuevo = new Usuario("Juan", "juan@example.com");
usuarioRepository.save(nuevo);                          // CREATE

Usuario encontrado = usuarioRepository.findById(1L);    // READ
List<Usuario> todos = usuarioRepository.findAll();      // listAR

usuarioRepository.deleteById(1L);                       // DELETE

// Query derivadas
Optional<Usuario> porEmail = usuarioRepository.findByEmail("juan@example.com");
List<Usuario> porNombre = usuarioRepository.findByNombreContainingIgnoreCase("juan");
boolean existe = usuarioRepository.existsByEmail("juan@example.com");
```

## Ventajas del patrón Repository

✓ **Menos boilerplate**: métodos CRUD heredados automáticamente
✓ **Productividad**: queries derivadas sin escribir SQL
✓ **Transacciones**: automáticas, sin código explícito
✓ **Testeable**: fácil mockear con `@MockBean`
✓ **Convenciones**: sigue estándares Spring Boot
✓ **Escalable**: fácil agregar métodos custom

## Desventajas

✗ **Binding a Spring**: acoplamiento a framework Spring
✗ **Queries complejas**: para queries muy específicas hay que usar `@Query`
✗ **Overhead**: pequeño coste de performance respecto a JDBC puro

## Evolución siguiente

Para **DTOs y mapeos**, consulta [../dto-example/](../dto-example/)
