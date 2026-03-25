# DTO Pattern - Data Transfer Object con ModelMapper

Este directorio contiene una implementación completa del patrón **Data Transfer Object (DTO)** con **ModelMapper** en una aplicación Spring Boot.

## Contenido

- `Usuario.java` - Entidad JPA (contiene passwordHash sensible)
- `UsuarioDTO.java` - Data Transfer Object (solo datos públicos)
- `UsuarioMapper.java` - Mapper usando ModelMapper (Entity ↔ DTO)
- `UsuarioRepository.java` - Repository Spring Data JPA
- `UsuarioService.java` - Servicio (orquesta Repository, Mapper y lógica)
- `UsuarioController.java` - REST Controller (usa DTOs, no Entities)
- `Application.java` - Aplicación Spring Boot
- `Exceptions.java` - Excepciones personalizadas

## ¿Cuándo usar DTO?

**Problemas que resuelve:**

1. **Exposición de información sensible**: La Entity contiene passwordHash que NO debería estar en la API
2. **Acoplamiento cliente-servidor**: Si cambias la Entity, afecta a todos los clientes de la API
3. **Múltiples vistas**: Diferentes clientes necesitan diferentes conjuntos de datos
4. **Serialización selectiva**: Necesitas control sobre qué se serializa a JSON

## Flujo completo: Cliente → Servidor → BD

```
Cliente (REST)
    ↓
UsuarioController (recibe UsuarioDTO)
    ↓
usuarioService.crearUsuario(UsuarioDTO)
    ↓
usuarioMapper.toEntity(DTO) → Usuario Entity
    ↓
usuarioRepository.save(Entity) → BD
    ↓
Entity regresa del Repository
    ↓
usuarioMapper.toDTO(Entity) → UsuarioDTO
    ↓
Controller retorna UsuarioDTO al cliente
```

## ModelMapper - Mapeo automático

**ModelMapper** es una librería que mapea automáticamente entre objetos basándose en:
- Coincidencia de nombres de propiedades
- Jerarquía de paquetes
- Tipos de datos

### Ventajas

✓ **Menos boilerplate**: sin getters/setters manuales
✓ **Mantenibilidad**: cambios en Entity/DTO se mapean automáticamente
✓ **Flexibilidad**: configurable para casos complejos
✓ **Performance**: optimizado para mapeos masivos

### Desventajas

✗ **Debugging**: a veces difícil seguir qué se mapea
✗ **Overhead**: pequeño coste de performance
✗ **Configuración**: casos especiales requieren custom mapping

## Configuración Spring Boot (pom.xml)

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>3.1.1</version>
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
# Server
server.port=8080

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# H2 Database (en memoria)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.h2.console.enabled=true
```

## Ejemplo de uso

```bash
# Crear usuario
curl -X POST http://localhost:8080/api/usuarios \
  -H 'Content-Type: application/json' \
  -d '{
    "nombre": "Juan Pérez",
    "email": "juan@example.com"
  }'

# Obtener usuario
curl http://localhost:8080/api/usuarios/1

# Listar todos
curl http://localhost:8080/api/usuarios

# Actualizar
curl -X PUT http://localhost:8080/api/usuarios/1 \
  -H 'Content-Type: application/json' \
  -d '{
    "nombre": "Juan Carlos",
    "email": "juancarlos@example.com"
  }'

# Eliminar
curl -X DELETE http://localhost:8080/api/usuarios/1
```

## Ventajas del patrón DTO

✓ **Seguridad**: no expones passwordHash en la API
✓ **Contrato claro**: DTO actúa como interfaz del API
✓ **Independencia**: cambios en Entity/BD no rompen clientes
✓ **Versionado**: puedes tener UsuarioDTOv1, UsuarioDTOv2, etc.
✓ **Performance**: seleccionar solo datos necesarios (proyecciones)

## Desventajas del patrón DTO

✗ **Duplicación**: mantener Entity y DTO sincronizadas
✗ **Boilerplate**: Mapper, excepciones, etc. (aunque ModelMapper reduce esto)
✗ **Overhead**: conversión Entity ↔ DTO tiene coste CPU

## Patrón integrado: Repository + DTO

La combinación **Repository + DTO** es el estándar en aplicaciones Spring Boot modernas:

- **Repository**: proporciona acceso a datos sin SQL boilerplate
- **DTO**: expone datos públicos sin exponer la implementación interna
- **Mapper**: convierte entre Entity y DTO automáticamente
- **Service**: orquesta todo

## Referencias

- [ModelMapper Documentation](http://modelmapper.org/)
- [Spring Data JPA - Query Methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)
- [Spring Boot REST API Best Practices](https://spring.io/guides/tutorials/rest/)
