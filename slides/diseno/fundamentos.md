---
marp: true
author:
- Juan Manuel Dodero
date: Enero 2026
subject: Diseño de Sistemas Software, curso 2025/26
title: Fundamentos de Diseño
description: Apuntes de Diseño de Sistemas Software - Principios de diseño
---

<!-- size: 16:9 -->
<!-- theme: default -->

<style>
h1 {
  text-align: center;
  color: #005877;
}
h2 {
  color: #E87B00;
}
h3 {
  color: #005877;
}
img[alt~="center"] {
  display: block;
  margin: 0 auto;
}
emph {
  color: #E87B00;
}
.cols {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 1rem;
}
.cols > div {
  align-self: start;
}
.no-bullets {
  list-style: none;
  padding-left: 0;
}
.no-bullets li {
  list-style: none;
}  
</style>

# DISEÑO DE SISTEMAS SOFTWARE

---

<!-- paginate: false -->
<style scoped>
section { text-align: center; }
</style>

## INTRODUCCIÓN

---

<!-- paginate: true -->

<style scoped>
h3 {
  text-align: center;
}

section {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;  /* alinea al inicio (arriba) */
}
.two-cols {
  display: flex;
  gap: 2rem;
}
.two-cols .left,
.two-cols .right {
  flex: 1;
}
.two-cols .right {
  text-align: right;
}
li {
  list-style: none;
  padding-left: 0;
}
</style>

### Problemáticas

![bg bottom Problemas 70%](./img/design-problems.png)

<div class="two-cols">
<div class="left">

- Variabilidad
- Acoplamiento
- Complejidad
- Robustez
- Reutilización
- Flexibilidad

</div>
<div class="right">

- Sobreingeniería
- Deuda técnica
- Carga cognitiva
- Rendimiento
- Resiliencia
- Costes

</div>
</div>

---

<div class="cols">
<div>

### Técnicas

- Refactoring
- Inyección de dependencias
- Contratos
- Aspectos
- Patrones
- Frameworks

</div>
<div>

### Principios

- SRP: Single Responsibility Principle
- OCP: Open/Closed Principle
- ISP: Interface Segregation Principle
- LSP: Liskov Substitution Principle
- DIP: Dependency Inversion Principle

</div>
</div>

---

### Paradigmas

- Estructurado (E. W. Dijsktra)
- Orientado a objetos (O. J. Dahl & K. Nygaard)
- Funcional (J. McCarthy)
- Orientado a aspectos (G. Kiczales)
- [Orientado a datos](https://www.dataorienteddesign.com/dodbook/) (R. Fabian)

---

<style scoped>
p {
  color: red;
  text-align: center;
}
h4 {
  text-align: center;
}
</style>

#### Preguntas

_¿De qué fecha data cada paradigma?_
_Ordenar cronológicamente_

---

#### Respuesta

![bg left:45% 85% Timeline de paradigmas](./img/timeline_paradigmas.png)

_¿De qué fecha data cada paradigma?_

- Funcional (1958)
- Orientado a Objetos (1966)
- Estructurado (1968)
- Orientado a Aspectos (1997)
- Orientado a Datos (2018)

---

### Bloques

- I. Fundamentos de diseño
- II. Principios de diseño OO
- III. Patrones de diseño
- IV. Arquitectura de software

---

<!-- paginate: false -->

<style scoped>
h2 {
  text-align: center;
}
</style>

# FUNDAMENTOS DE DISEÑO

---
<style scoped>
.cols {
  display: grid;
  grid-template-columns: 45% 55%;
}
</style>

<!-- paginate: true -->

<div class="cols">
<div>

## Casos prácticos

<ul class="no-bullets">
<li> Caso 1. Identificadores </li>
<li> Caso 2. Framework de pruebas unitarias </li>
<li> Caso 3. Caballeros de la mesa redonda </li>
<li> Caso 4. Figuras geométricas </li>
</ul>

</div>
<div>

## Conceptos teóricos

- Cohesión y acoplamiento
- Bibliotecas y frameworks
- Inyección de dependencias / Inversión de control
- Reutilización y flexibilidad
- Principios SOLID
- Orientación a aspectos
- Diseño por contratos

</div>
</div>

---

<!-- paginate: false -->

<style scoped>
h2,h3 {
  text-align: center;
}
</style>

## CASO PRÁCTICO 1

### Identificadores

---

<!-- paginate: true -->

<style scoped>
p {
  color: red;
  text-align: center;
}
h2, h3 {
  text-align: center;
}
</style>

¿Cómo diseñar la identificación de los empleados de una empresa? 

<style scoped>
.cols {
  display: grid;
  grid-template-columns: 55% 45%;
}
</style>

### Versión inicial: Identificadores v0.1

<div class="cols">
<div>

```java
class Empleado implements Comparable<Empleado> {
  private final int dni;

  Empleado(String dni) {
    this.dni = Integer.parseInt(dni);
  }

  public int getDni() {
    return dni;
  }

  @Override
  public String toString() {
    return Integer.toString(dni);
  }
```

</div>
<div>

```java
  @Override
  public int compareTo(Empleado otro) {
    return Integer.compare(this.dni,
                           otro.getDni());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Empleado otro))
      return false;
    return dni == otro.dni;
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(dni);
  }
}
```

</div>
</div>

---

#### Nuevos requisitos:

- El Real Decreto 338/1990 regula el uso de NIFs en lugar de DNIs. ¡Hay que cambiar toda la implementación!

### Implementación alternativa: Identificadores v0.2

<div class="cols">
<div>

```java
class Empleado implements Comparable<Empleado> {
  private final String nif;

  Empleado(String nif) {
    this.nif = nif;
  }

  public String getNif() {
    return nif;
  }

  @Override
  public String toString() {
    return nif;
  }
```

</div>
<div>

```java
  @Override
  public int compareTo(Empleado otro) {
    return this.nif.compareTo(otro.getNif());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Empleado otro)) return false;
    return nif.equals(otro.nif);
  }

  @Override
  public int hashCode() {
    return nif.hashCode();
  }
}
```

</div>
</div>

---

#### Críticas:

- __Flexibilidad__ / __Reutilización__: Posiblemente haya más situaciones (algunas futuras) donde hagan falta _identificadores_ que incluso pueden cambiar.
- Ejemplo: UUID, números de la seguridad social, tarjetas de identidad, números de cuenta corriente, IBAN, etc.

### Hacemos refactoring: patrón _handler_

- Manejo de _identificadores_ de forma independiente de la implementación del objeto identificado.

- Cambio fácil de implementación de los identificadores (`int`, `String`, etc.) hacia cualquier tipo básico o clase primitiva, sencilla o compuesta.

---

<style scoped>
.cols {
  display: grid;
  grid-template-columns: 50% 50%;
}
</style>

## Diseño de un Handler

<div class="cols">
<div>

@startuml
  skinparam classAttributeIconSize 0
  skinparam linetype ortho
  class Identifiable {
    - id : Handler
    + getId() : Handler
    + Identifiable(Handler)
  }
  interface Handler {
    + toString() : String
    + compareTo(Handler) : int
  }
  class ConcreteHandler1 {
    - id : int
    + ConcreteHandler1(int)
    + ConcreteHandler1(Handler)
  }
  class ConcreteHandler2 {
    - id : String
    + ConcreteHandler2(String)
    + ConcreteHandler2(Handler)
  }

  ' Esto fuerza a Identifiable a quedarse a la izquierda de Handler
  Identifiable -[hidden]right-> Handler
  
  ' Ajustamos la flecha de uso para que apunte a la derecha
  Identifiable .right.> Handler : <<uses>>

  ' La notación <|.. coloca el padre (Handler) arriba y los hijos abajo por defecto.
  Handler <|.. ConcreteHandler1 : <<implements>>
  Handler <|.. ConcreteHandler2 : <<implements>>

  ' Truco extra: Mantener los hijos al mismo nivel visual
  ConcreteHandler1 -[hidden]right-> ConcreteHandler2
@enduml

</div>
<div>

- __Identifiable__: Clase cliente que necesita identificar a sus objetos a través de algún atributo identificador

- __Handler__: Interfaz para declarar los identificadores de los objetos de la clase `Identifiable`

- __ConcreteHandler__: Implementación concreta de la interfaz `Handler`

</div>
</div>

---

<div class="cols">
<div>

### Implementación del diseño en Java

```java
interface Handler<T extends Comparable<? super T>>
    extends Comparable<Handler<T>> {
  T getId();

  @Override
  public boolean equals(Object o);

  @Override
  default int compareTo(Handler<T> otro) {
    return getId().compareTo(otro.getId());
  }
}
```

</div>
<div>

```java
final class IdentificadorNumerico
    implements Handler<Integer> {
  private final Integer id;

  IdentificadorNumerico(String id) {
    this.id = Integer.valueOf(id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof IdentificadorNumerico otro))
      return false;
    return id.equals(otro.id);
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public String toString() {
    return id.toString();
  }
}
```

</div>
</div>

---

### Conceptos de diseño

- __Responsabilidad única__: La lógica de generación de identificadores (ej. limpiar caracteres, validar formato) debe estar aislada de la lógica de negocio principal.

- __Acoplamiento__ (bajo): El sistema principal debe usar interfaces para generar IDs, sin depender de si se usa un UUID, una secuencia o un algoritmo custom.
  
- __Cohesión__ (alta): Agrupar métodos relacionados con la manipulación de IDs en un único componente.

- __Encapsulamiento__: Ocultar los detalles algorítmicos de la construcción del identificador tras los métodos públicos. 

- __Reutilización y flexibilidad__: Permitir la reutilización del componente de identificación en otros sistemas y facilitar la adaptación a futuros cambios.

---

### Implementación en los lenguajes

#### Java: Identificadores con `java.lang.Comparable`

`Comparable<T>` es una interfaz que define el orden natural entre objetos del mismo tipo. La implementan `String`, `File`, `Date`, etc. y las _clases de envoltura_ del JDK (i.e. `Integer`, `Long`, etc.)

__Método de la interfaz__:

```java
  public int compareTo(T o)
```

El tipo `T` garantiza seguridad de tipos: un `Comparable<Empleado>` solo se compara con `Empleado`, evitando casts y errores en tiempo de ejecución.

---

__Invariantes:__ las debe asegurar cualquier implementación de `compareTo`

`sgn(x.compareTo(y)) = -sgn(y.compareTo(x))`

`(x.compareTo(y)>0 and y.compareTo(z)>0)` $\Rightarrow$ `x.compareTo(z)>0`

`x.compareTo(y)=0` $\Rightarrow$ `sgn(x.compareTo(z))=sgn(y.compareTo(z))` $\forall$ `z`

__Consistencia con `equals`__: recomendable pero no exigible

`(x.compareTo(y)=0)` $\Leftrightarrow$ `(x.equals(y))`

> [!NOTE]
> En Java no se puede definir un método `default` en una `interface` que sea override‑equivalent a un método público de Object (como equals, hashCode, toString). Puedes declararlo de forma abstracta en la interfaz, pero no darle implementación default.

---

#### C++: Comparación de identificadores

Cómo implementar la interfaz de comparación de un Handler en C++

¿Tienen sentido las siguientes implementaciones?

```c++
   static int compare(const Handler&, const Handler&);
   int compareTo(const Handler&); // member function
```

Ver __[stackoverflow](https://stackoverflow.com/questions/20005392/is-there-a-compareto-method-in-c-similar-to-java-where-you-can-use-opera)__

---

__Sobrecarga de operadores en C++__:

```c++
template <typename T>
struct Handler {
    T id;
    explicit Handler(T v) : id(std::move(v)) {}
    bool operator==(const Handler& other) const = default;
    auto operator<=>(const Handler& other) const = default;
};
using NumericHandler = Handler<int>;
using StringHandler  = Handler<std::string>;
```

```c++
int main() {
    StringHandler a{"EMP-001"};
    StringHandler b{"EMP-002"};
    auto ord = (a <=> b);  // OK
    NumericHandler c{123};
    // bool same = (a == c);  // ERROR: tipos distintos
}
```

---
<style scoped>
h2 {
  color: blue;
}
</style>

<div class="cols">
<div>

## Reutilización y flexibilidad

- __Reutilización__: Construir software fácil de reutilizar sin tener que cambiar los módulos ya escritos (afecta a la fase de __desarrollo__)
- __Flexibilidad__: Adaptarse a cambios de requisitos y construir software fácil de cambiar (afecta a la fase de __mantenimiento adaptativo__)

</div>
<div>

### El árbol de la calidad del software

![Reutilización vs flexibilidad, width:600px](./img/sqa-tree.png)

</div>
</div>

---

<!-- paginate: false -->

<style scoped>
h2,h3 {
  text-align: center;
}
</style>

## CASO PRÁCTICO 2

### Pruebas unitarias

---

<!-- paginate: true -->

### jUnit: Framework de pruebas unitarias

- JUnit es un framework en Java que sirve para diseñar, construir y ejecutar __pruebas unitarias__
- Una prueba unitaria comprueba la corrección de un _módulo_ de software en cuanto a funcionalidades que ofrece.
- En el caso de Java, las pruebas unitarias comprueban la corrección de cada uno de los métodos de _cada clase_.

<style scoped>
p {
  color: red;
  text-align: center;
}
</style>

¿Por qué? ¿Cómo funciona?

---

<style scoped>
.cols {
  display: grid;
  grid-template-columns: 45% 55%;
}
</style>

### ¿Cómo se probaba `Saludo.java` sin bibliotecas de pruebas unitarias?

<div class="cols">
<div>

```java
class Saludo {
  /**
  * Imprime "Hola Mundo!"
  */
  void saludar() {
    System.out.println("Hola Mundo!");
  }
  
  /**
  * Imprime un mensaje
  */
  void saludar(String mensaje) {
    System.out.println(mensaje);
  }
  
```

</div>
<div>

Incluir un método `main` que pruebe la funcionalidad de la clase:

```java
  /**
  * Tests
  */
  public static void main( String[] args ) {
    Saludo saludo1 = new Saludo();
    saludo1.saludar();

    Saludo saludo2 = new Saludo("Hola caracola!");
    saludo2.saludar();
  }
}
```

</div>
</div>

---

#### Pegas

- Cuanto más grande sea la interfaz de la clase, mayor será el `main`

- El tamaño del código de la clase crece por las pruebas

- Poco fiable, porque `main` forma parte de la misma clase y tiene acceso a los elementos privados

- Difícil de automatizar las pruebas, incluso pasando argumentos a `main`

---

### Ejemplo: software _cliente_ del framework jUnit

#### Caso de prueba con jUnit 4

```java
import org.junit.*;
import static org.junit.Assert.*;

public class SaludoTest {
  public static void main(String args[]) {
    junit.textui.TestRunner.run(SaludoTest.class);
  }
  @Test
  public void saludar() {
    Saludo hola = new Saludo();
    assert( hola!=null );
    assertEquals("Hola Mundo!", hola.saludar() );
  }
}
```

---

#### Ejecución de los tests:

```java
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MyTestRunner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(SaludoTest.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
  }
}
```

<style scoped>
p {
  color: red;
  text-align: center;
}
</style>

¿De qué están hechas las anotaciones como `@Test`?

---

<div class="cols">
<div>

#### Caso de prueba con jUnit 3

Veamos una versión anterior de jUnit, que expone más claramente las _tripas_ del framework

```java
import junit.framework.TestCase;
import junit.framework.Assert;
  
public class SaludoTest extends TestCase {
    public SaludoTest(String nombre) {
      super(nombre);
    }
    public void testSaludar() {
      Saludo hola = new Saludo();
      assert( hola!=null );
      assertEquals("Hola Mundo!", hola.saludar() );
    }
}
```

</div>
<div>

### Diseño del framework jUnit

@startuml

interface Test {
  +countTestCases()
  +run()
}

class Assert {
  {static} +assertTrue()
  {static} +assertFalse()
  {static} +assertXXX()
}

class TestSuite {
  +addTest()
  +addTestSuite()
  {static} +createTest()
  +run()
  +runTest()
  +testCount()
  +tests()
}

class TestCase {
  +run()
  +runTest()
  +setUp()
  +tearDown()
}

class TestDecorator {
  +countTestCases()
  +run()
}

TestCase -up-|> Assert
Test <|.. TestCase
Test <|.. TestSuite
TestSuite o--> Test

TestDecorator ..|> Test
TestDecorator --|> Assert

@enduml

</div>
</div>

---

### Ejemplo: aplicación de comercio electrónico

Diseño de una aplicación de comercio electrónico:

- `ShoppingCart` - carrito de la compra
- `CreditCard` - tarjeta de crédito
- `Product`- artículos
- Etc.

Diseño de pruebas unitarias de `ShoppingCart` para:

- Probar carrito de la compra (añadir/eliminar artículos)
- Probar validación de tarjetas de crédito
- Probar manejo de varias monedas
- Etc.

---

<div class="cols">
<div>

#### Utilización del framework jUnit

@startuml
left to right direction

package "junit::framework" {
  interface Test {
    +countTestCases()
    +run()
  }
}

package "junit::runner" {
  class BaseTestRunner {}

  BaseTestRunner --> Test
}  

ShoppingCartTestCase -down-|> Test
ShoppingCartTestCase -down-> ShoppingCart

@enduml

</div>
<div>

#### ShoppingCart

```java
public class ShoppingCart {
  private ArrayList items;
  public ShoppingCart() { ... }
  public double getBalance() { ... }
  public void addItem(Product p) { ... }
  public void removeItem(Product p)
      throws ProductNotFoundException { ... }
  public int getItemCount() { ... }
  public void empty() { ... }
  public boolean isEmpty() { ... }
}
```

</div>
</div>

---

#### ShoppingCartTestCase con jUnit 3

<div class="cols">
<div>

```java
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Assert;

public class ShoppingCartTest extends TestCase {
  private ShoppingCart bookCart;
  private Product defaultBook;
  //...
  protected void setUp() {
      bookCart = new ShoppingCart();
      defaultBook = new Product("Extreme Programming", 23.95);
      bookCart.addItem(defaultBook);
  }
  protected void tearDown() {
      bookCart = null;
  }  
  public void testEmpty() {
      bookCart.empty();
      assertTrue(bookCart.isEmpty());
  }
  public void testProductAdd() {
      Product book = new Product("Refactoring", 53.95);
      bookCart.addItem(book);
      double expectedBalance = defaultBook.getPrice() + book.getPrice();
      assertEquals(expectedBalance, bookCart.getBalance(), 0.0);
      assertEquals(2, bookCart.getItemCount());
  }
```

</div>
<div>

```java  
  public void testProductRemove() throws ProductNotFoundException {
      bookCart.removeItem(defaultBook);
      assertEquals(0, bookCart.getItemCount());
      assertEquals(0.0, bookCart.getBalance(), 0.0);
  }
  public void testProductNotFound() {
      try {
          Product book = new Product("Ender's Game", 4.95);
          bookCart.removeItem(book);
          fail("Should raise a ProductNotFoundException");
      } catch(ProductNotFoundException success) {
          ...
      }
  }
  public static Test suite() {
      // Use reflection to add all testXXX() methods
         TestSuite suite = new TestSuite(ShoppingCartTest.class);
      // Alternatively, but prone to error when adding more
      // test case methods...
      // TestSuite suite = new TestSuite();
      // suite.addTest(new ShoppingCartTest("testProductAdd"));
      // suite.addTest(new ShoppingCartTest("testEmpty"));
      // suite.addTest(new ShoppingCartTest("testProductRemove"));
      // suite.addTest(new ShoppingCartTestCase("testProductNotFound"));
         return suite;
  }
}
```

</div>
</div>

---

Podemos agrupar varios casos de prueba en una misma _suite_:

```java
import junit.framework.Test;
import junit.framework.TestSuite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class EcommerceTestSuite extends TestSuite {
    //...
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(ShoppingCartTest.suite());
        return suite;
    }
}

public class MyTestRunner {
  public static void main(String[] args) {
    Result result = JUnitCore.runClasses(EcommerceTestSuite.class);
    for (Failure failure : result.getFailures()) {
      System.out.println(failure.toString());
    }
  }
}
```

---

#### ShoppingCartTestCase con jUnit 4

<div class="cols">
<div>

```java
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
  private ShoppingCart bookCart;
  private Product defaultBook;
  //...
  @Before
  protected void setUp() {
      bookCart = new ShoppingCart();
      defaultBook = new Product("Extreme Programming", 23.95);
      bookCart.addItem(defaultBook);
  }
  @After
  protected void tearDown() {
      bookCart = null;
  }
```

</div>
<div>

```java  
  @Test
  public void testEmpty() {
      bookCart.empty();
      assertTrue(bookCart.isEmpty());
  }
  @Test
  public void testProductAdd() {
      Product book = new Product("Refactoring", 53.95);
      bookCart.addItem(book);
      double expectedBalance = defaultBook.getPrice() + book.getPrice();
      assertEquals(expectedBalance, bookCart.getBalance(), 0.0);
      assertEquals(2, bookCart.getItemCount());
  }
  @Test
  public void testProductRemove() {
      bookCart.removeItem(defaultBook);
      assertEquals(0, bookCart.getItemCount());
      assertEquals(0.0, bookCart.getBalance(), 0.0);
  }
  @Test(expected = ProductNotFoundException.class)
  public void testProductNotFound() {
      Product book = new Product("Ender's Game", 4.95);
      bookCart.removeItem(book);
      fail("Should raise a ProductNotFoundException");
  }
}
```

</div>
</div>

---

<style scoped>
.cols {
  display: grid;
  grid-template-columns: 60% 40%;
}
</style>

<div class="cols">
<div>

#### EcommerceTestSuite con jUnit 3

```java
public class EcommerceTestSuite extends TestSuite {
  //...
  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTest(ShoppingCartTest.suite());
    suite.addTest(CreditCardTest.suite());
    // etc.
    return suite;
  }
}
```

</div>
<div>

#### EcommerceTestSuite con jUnit 4

```java
@RunWith(Suite.class)
@SuiteClasses({
    ShoppingCartTest.class,
    CreditCardTest.class
})
public class EcommerceTestSuite {
    //...
}
```

</div>
</div>

---

<style scoped>
p, h4 {
  color: red;
  text-align: center;
}
</style>

#### Pregunta 

¿Qué hemos conseguido con las anotaciones `@Test` del JDK $\geq$ 1.5?

---

¿Qué hemos conseguido con las anotaciones `@Test`?

#### Respuesta:

- No necesitar de la incómoda herencia (i.e. mecanismo de implementación)

<style scoped>
p, h3 {
  color: red;
  text-align: center;
}
</style>

### Ejercicio propuesto: CreditCardTest

Diseñar y codificar una suite de casos de prueba unitaria para `CreditCard` usando jUnit versión 4.

---

### Arquitectura del framework jUnit

<div class="cols">
<div>

@startuml
skinparam style strictuml
skinparam classAttributeIconSize 0
skinparam linetype ortho
skinparam shadowing false

' 1. Definición de Nodos
interface Test <<Composite:Component>> {
    + run(TestResult)
}

class TestResult <<Collecting Parameter>> {
}

abstract class TestCase <<Command>> <<Template Method>> <<Composite:Leaf>> <<Pluggable Selector>> {
    - fName
    + run(TestResult)
    + {abstract} runTest()
    + setUp()
    + tearDown()
}

class TestSuite <<Composite>> {
    + run(TestResult)
    + addTest(Test)
}

' Clase anónima o concreta para el Adapter
class " " as AdapterClass <<Adapter (Class)>> {
    + runTest()
}

' 2. Relaciones Jerárquicas (Verticales)
Test <|.. TestCase
Test <|.. TestSuite
TestCase <|-- AdapterClass

' 3. Relaciones Horizontales y Estructurales
' Alineación forzada: TestCase a la izquierda de TestSuite
TestCase -[hidden]right-> TestSuite

' Agregación (Composite)
TestSuite o-up-> "*" Test : fTests

' Dependencia
TestCase -left-> TestResult

@enduml

</div>
<div>

En la arquitectura del framework se observan diversos patrones:

- Composite
- Command
- Adapter
- Factory
- Decorator
- etc.

</div>
</div>

---

<style scoped>
h2 {
  color: blue;
}
</style>

## Bibliotecas y frameworks

#### Flujo de control en una biblioteca

![Flujo de control en una biblioteca, width:800px](./img/biblioteca.png)

---

#### Flujo de control en un framework

![Flujo de control en una framework, width:1200px](./img/framework.png)

---
<style scoped>
h3 {
  color: blue;
}
</style>

### Frameworks

#### Definición de *framework*

> Colección de clases e interfaces que cooperan para formar un diseño reutilizable de un tipo específico de software
> 
> –– [E. Gamma et al.](bibliografia.html#gamma)

- El framework proporciona unas guías arquitectónicas (diseño empaquetado) para dividir el diseño en clases abstractas y definir sus _responsabilidades_ y _colaboraciones_.
- El framework se debe personalizar definiendo subclases y combinando instancias, o bien configurando valores que definen el comportamiento por defecto

---

### Principios de diseño de un framework OO

- Datos encapsulados
- Interfaces y clases abstractas
- Métodos polimórficos
- Delegación

### Herramientas de diseño OO

- __Patrones__: elementos reutilizables de diseño
- __Frameworks__: colecciones de patrones abstractos a aplicar

---

### Framework vs. biblioteca

- API orientado a objetos vs. API basado en funciones (en general)
- Flujo de control invertido
- Programador _cliente_ (código específico) vs. programador de _API_ (código reutilizable)

---

### Principios y técnicas de un framework

- __Abstracción__
  - Clases y componentes abstractos
  - Interfaces abiertas
  - Uso de patrones de diseño
  - Componentes de un dominio específico

- Máxima __cohesión__, mínimo __acoplamiento__
  - Minimizar dependencias: Una clase A presenta una dependencia con otra clase B (A $\rightarrow$ B) si la primera usa una instancia de la segunda.
  - Cuando no se pueden eliminar las dependencias, mantener las abstractas e __inyectar__ las concretas.

---

#### Dependencias

> Coupling is the enemy of change, because it links together things that must change in parallel
>
> D. Thomas & A. Hunt, [The Pragmatic Programmer](https://pragprog.com/titles/tpp20/the-pragmatic-programmer-20th-anniversary-edition/), 20th Anniversary Edition, 2019

- La reducción de dependencias debe ser estratégica, es decir, centrada en los puntos del sistema que cambian con distinta frecuencia.

- Si A y B cambian al mismo tiempo, no hay demasiado problema porque A $\rightarrow$ B

- Si cambian con distinta frecuencia...

---

#### Inyección de dependencias

Una clase o módulo no debería configurar sus dependencias estáticamente, sino ser configurada desde fuera

---

<!-- paginate: false -->

<style scoped>
h2,h3 {
  text-align: center;
}
</style>

## CASO PRÁCTICO 3

### Caballeros de la mesa redonda

---

<!-- paginate: true -->

<style scoped>
.cols {
  display: grid;
  grid-template-columns: 53% 47%;
}
</style>

Añadir pruebas unitarias al programa siguiente:

<div class="cols">
<div>

```java
public class KnightOfTheRoundTable {
  private String name;
  private HolyGrailQuest quest;

  public KnightOfTheRoundTable(String name) {
    this.name = name;
    quest = new HolyGrailQuest();
  }
  public HolyGrail embarkOnQuest()
      throws GrailNotFoundException {
    return quest.embark();
  }
}
```

 </div>
<div>

```java
public class HolyGrailQuest {
  public HolyGrailQuest() {
    /*...*/
  }

  public HolyGrail embark()
          throws GrailNotFoundException {
    HolyGrail grail = null;
    // Look for grail ...
    return grail;
  }

}
```

</div>
</div>

---

### Diseño de pruebas con jUnit 3

<style scoped>
p {
  color: red;
  text-align: center;
}
</style>

¿Dónde está el acoplamiento?

```java
import junit.framework.TestCase;

public class KnightOfTheRoundTableTest extends TestCase {
  public void testEmbarkOnQuest() throws GrailNotFoundException {
    KnightOfTheRoundTable knight =
        new KnightOfTheRoundTable("CruzadoMagico");
    HolyGrail grail = knight.embarkOnQuest();
    assertNotNull(grail);
    assertTrue(grail.isHoly());
  }
}
```

---

### Diagrama de clases

<style scoped>
p {
  color: red;
  text-align: center;
}
</style>

¿Dónde está el acoplamiento?

@startuml

package "roundTable" {

  class KnightOfTheRoundTable {
    name
    quest
    embarkOnQuest()
  }
  
  class HolyGrailQuest {
    grail
    embark()
  }
  
  class HolyGrail {
    isHoly()
  }
  
  KnightOfTheRoundTable -left-> HolyGrailQuest
  
  HolyGrailQuest .down.> HolyGrail
  
  KnightOfTheRoundTable ..> HolyGrail

}

package "roundTable::test" {

  class KnightOfTheRoundTableTest { }
  
  KnightOfTheRoundTableTest .right.> KnightOfTheRoundTable
  KnightOfTheRoundTableTest .[hidden].> HolyGrail

}

@enduml

---

### Acoplamiento

<style scoped>
p {
  color: red;
  text-align: center;
}
</style>

No deseable

@startuml

package "roundTable" {

  class KnightOfTheRoundTable {
    name
    quest
    embarkOnQuest()
  }
  
  class HolyGrailQuest {
    grail
    embark()
  }
  
  class HolyGrail {
    isHoly()
  }
  
  KnightOfTheRoundTable -left-> HolyGrailQuest
  
  HolyGrailQuest .down.> HolyGrail
  
  KnightOfTheRoundTable ..> HolyGrail  #red

}

package "roundTable::test" {

  class KnightOfTheRoundTableTest { }
  
  KnightOfTheRoundTableTest .right.> KnightOfTheRoundTable
  KnightOfTheRoundTableTest .right.> HolyGrail  #red

}

@enduml

---

#### Pegas:

- Instanciación de `HolyGrail`

- Cada vez que se prueba `KnightOfTheRoundTable`, también se prueba `HolyGrailQuest`.

- No se puede pedir a `HolyGrailQuest` que se comporte de otra forma (v.g. devolver null o elevar una excepción)

---

Ocultar la implementación detrás de una interfaz:

<div class="cols">
<div>

```java
public interface Knight {
  Object embarkOnQuest()
          throws QuestFailedException;
}

public class KnightOfTheRoundTable
             implements Knight {
  private String name;
  private Quest quest;

  public KnightOfTheRoundTable(String name) {
    this.name = name;
    quest = new HolyGrailQuest();
  }
  public Object embarkOnQuest()
          throws QuestFailedException {
    return quest.embark();
  }
}
```

</div>
<div>

```java
public interface Quest {
  abstract Object embark()
    throws QuestFailedException;
}

public class HolyGrailQuest implements Quest {
  public HolyGrailQuest() { /*...*/ }
  public Object embark()
          throws QuestFailedException {
    // Do whatever it means
    // to embark on a quest
    return new HolyGrail();
  }
}
```

</div>
</div>

---

<div class="cols">
<div>

#### Dependencias

@startuml

package "roundTable" {

  interface Knight {
    embarkOnQuest()
  }
  
  interface Quest {
    embark()
  }
  
  class KnightOfTheRoundTable {
    name
    quest
    embarkOnQuest()
  }
  
  class HolyGrailQuest {
    grail
    embark()
  }
  
  class HolyGrail {
    isHoly()
  }
  
  Knight <|.. KnightOfTheRoundTable
  
  Quest <|.. HolyGrailQuest
  
  KnightOfTheRoundTable -right-> HolyGrailQuest #red
  
  HolyGrailQuest .down.> HolyGrail #red
  
  KnightOfTheRoundTable ..> HolyGrail #red

}

class KnightOfTheRoundTableTest { }

KnightOfTheRoundTableTest ..> KnightOfTheRoundTable #red
KnightOfTheRoundTableTest ..> HolyGrail #red

@enduml

</div>
<div>

#### Pegas:

- El `KnightOfTheRoundTable` aún depende de un tipo específico de `Quest` (i.e. `HolyGrailQuest`) obtenido mediante `new`

<style scoped>
p {
  color: red;
}
</style>

¿Debe ser el caballero responsable de obtener un desafío?

</div>
</div>

---

<style scoped>
.cols {
  display: grid;
  grid-template-columns: 40% 60%;
}
</style>

<div class="cols">
<div>

```java
public class KnightOfTheRoundTable
               implements Knight {
  private String name;
  private Quest quest;
  
  public KnightOfTheRoundTable(String name) {
    this.name = name;
  }
  public Object embarkOnQuest()
          throws QuestFailedException {
    return quest.embark();
  }
  public void setQuest(Quest quest) {
    this.quest = quest;
  }
}
```

</div>
<div>

@startuml

participant injector1 order 10
participant holyGrailQuest order 20
participant injector2 order 30
participant killDragonQuest order 40
participant ":Knight" order 50

injector1 -> holyGrailQuest : new

injector1 -> ":Knight" : setQuest(holyGrailQuest)

injector2 -> killDragonQuest : new

injector2 -> ":Knight" : setQuest(killDragonQuest)

@enduml

</div>
</div>

- El caballero sólo sabe del desafío a través de su interfaz `Quest`.

- Puede asignársele cualquier implementación de `Quest`
 (`HolyGrailQuest`, `KillDragonQuest`, etc.)

---

<style scoped>
p {
  color: red;
  text-align: center;
}
</style>

- Parece que no hay dependencia entre `KnightOfTheRoundTable` y `HolyGrail` porque `embark()` se ha definido como que devuelve un `Object`

Ejercicio: Discutir el tipo de retorno `Object` de `embarkOnQuest`:

- Puede provocar `ClassCastException`
- Solución propuesta: rediseñar la interfaz `Quest`

---

### Inversión de control

Es la base de la inyección de dependencias

> The question is: _what aspect of control are they inverting?_ [...] Early __user interfaces__ were controlled by the application program. You would have a sequence of commands like "Enter name", "enter address"; your program would drive the prompts and pick up a response to each one. With __graphical__ (or even screen based) UIs the UI framework would contain this main loop and your program instead provided event handlers for the various fields on the screen. The main control of the program was inverted, moved away from you to the framework.
>
> –– Martin Fowler, [IoC containers and the DI pattern](http://martinfowler.com/articles/injection.html) [1]

[1] http://martinfowler.com/articles/injection.html  

---

#### IoC–Inversion of Control / DI–Dependency Injection

- Una aplicación está compuesta por dos o más clases que colaboran.
- Los objetos deben recibir las dependencias en su creación, por parte de una entidad externa o __contenedor__ que los coordina.
- IoC = Inversión de la responsabilidad de cómo un objeto obtiene referencias a los objetos con los que colabora
- Ventaja = __bajo acoplamiento__: un objeto sólo sabe de sus dependencias por su _interfaz_, no por su _implementación_, ni por cómo fueron instanciados.
- Entonces la dependencia puede cambiarse por una implementación distinta (incluso en __tiempo de ejecución__)
- _Hollywood Principle: Don't call us, we'll call you"._

---

### Factorías

Una factoría proporciona un mecanismo de inyección de dependencias, visto desde el lado opuesto (los clientes adquieren las dependencias, no se les inyecta)

Ejemplo: [Spring FactoryBean](http://www.baeldung.com/spring-factorybean)

---

<style scoped>
h2 {
  color: blue;
}
</style>

## Discusión sobre la reutilización

> We most likely would have been better off not attempting to create a reusable function in the first place
>
> –– Roger Sessions, [The Misuse of Reuse](http://simplearchitectures.blogspot.com.es/2012/07/misuse-of-reuse.html) [2]

[2] http://simplearchitectures.blogspot.com.es/2012/07/misuse-of-reuse.html

---
<style scoped>
p,h3,h4 {
  text-align: center;
}  
</style>

### Factorizar una función

<div class="cols">
<div>

![Reutilización de una función, width:700px](./img/misuse-reuse-1.png)

</div>
<div>

![Reutilización de una función, width:700px](./img/misuse-reuse-2.png)

</div>
</div>

#### Ventajas (supuestas) de reutilizar

__Ahorro__: Si $\exists$ $s$ sistemas $\wedge ~ coste(Function~1) = c$ $\Rightarrow$ ahorro = $c \times (s-1)$

---

### Amenazas (reales) a la reutilización

<div class="cols">
<div>

![Reutilización de una función, width:500px](./img/misuse-reuse-3.png)

</div>
<div>

- Realmente el ahorro depende de la __complejidad__ de la función, que suele estar relacionada exponencialmente con el número de sistemas.
- Con un único punto de fallo, si $Function 1$ falla, todos los sistemas pueden fallar a la vez.
- La seguridad es inversamente proporcional a la complejidad del sistema.

</div>
</div>

---

### Conclusión sobre la reutilización

- Aplicar el principio __YAGNI__ (_You Ain't Gonna Need It_)
- No crear funciones reutilizables en primer lugar
- No hacer sobreingeniería
- Cuidado con las abstracciones prematuras
- Comprobar el acoplamiento semántico entre sistemas
- No aplicar patrones de diseño antes de tiempo
- Aplicar YAGNI es un pedir un _préstamo_ (asumir una deuda técnica conscientemente)

<!--
**Acoplamiento semántico**: A veces, dos componentes parecen idénticos, pero cambian por razones de negocio diferentes. Si los unificas para reutilizar, creas un acoplamiento semántico. En fases iniciales, es más barato duplicar que crear una mala abstracción, porque el código duplicado es fácil de borrar o cambiar independientemente, mientras que una abstracción incorrecta ata de manos a todo el sistema
-->
