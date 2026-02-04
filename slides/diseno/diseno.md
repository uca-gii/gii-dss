## Índice

- [Principios de Diseño](#principios-de-diseño)
- [Patrones de Diseño](#patrones-de-diseño)

<!-- Source: principios.md -->
# DISEÑO DE SISTEMAS SOFTWARE


## INTRODUCCIÓN


![Background image](./img/design-problems.png)

### Problemáticas

- Variabilidad
- Acoplamiento
- Complejidad
- Robustez
- Reutilización
- Flexibilidad


![Background image](./img/design-principles.png)

### Principios

- Ocultación: OCP, ISP, LSP
- Cohesión: SRP
- Ortogonalidad: DIP


![Background image](./img/design-techniques.png)

### Técnicas

- Refactoring
- Bibliotecas y frameworks
- Contratos
- Inyección de dependencias
- Patrones


### Paradigmas

- Estructurado (E. W. Dijsktra)
- Orientado a Objetos (O. J. Dahl & K. Nygaard)
- Funcional (J. McCarthy)
- Orientado a Aspectos (G. Kiczales)
- [Orientado a Datos](https://www.dataorienteddesign.com/dodbook/) (R. Fabian)


#### Preguntas

_¿De qué fecha data cada paradigma?_
_Ordenar cronológicamente_


#### Respuesta

![Background image](./img/timeline_paradigmas.png)

_¿De qué fecha data cada paradigma?_

- Funcional (1958)
- Orientado a Objetos (1966)
- Estructurado (1968)
- Orientado a Aspectos (1997)
- Orientado a Datos (2018)


### Bloques

I. Principios de diseño OO
II. Patrones de diseño
III. Arquitectura de software


# PRINCIPIOS DE DISEÑO

## Orientado a objetos


<div class="cols">
<div>

## Casos prácticos

Caso 1. Identificadores
Caso 2. Framework de pruebas unitarias
Caso 3. Caballeros de la mesa redonda
Caso 4. Figuras geométricas

</div>
<div>


## Conceptos teóricos

- Bibliotecas y frameworks
- Inyección de dependencias
- Reutilización
- Principios SOLID

</div>
</div>


## CASO PRÁCTICO 1

### Identificadores


¿Cómo diseñar la identificación de los empleados de una empresa?


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


#### Críticas:

- __Flexibilidad__ / __Reutilización__: Posiblemente haya más situaciones (algunas futuras) donde hagan falta _identificadores_ que incluso pueden cambiar.
- Ejemplo: UUID, números de la seguridad social, tarjetas de identidad, números de cuenta corriente, IBAN, etc.

### Hacemos refactoring: patrón _handler_

- Manejo de _identificadores_ de forma independiente de la implementación del objeto identificado.

- Cambio fácil de implementación de los identificadores (`int`, `String`, etc.) hacia cualquier tipo básico o clase primitiva, sencilla o compuesta.


## Diseño de un Handler

<div class="cols">
<div>

![PlantUML diagram](https://kroki.io/plantuml/svg/eNp9U01P4zAQvfMr5ratUCPgiKoKhJCoBCe4IQ5Te9JMceziDwTd5VftT9g_tuMkQJMAh1jRvDfvPXvssxDRx1SbA4DwyHaLHmtQBkM4j9HzKkVaKmdveUdw1CMZthRftwTOx8oJ1HTBUpONXDKuDMFvKQPMgDWcwhVabcg3pUNYU1zqyXRU3--fdNBUsDf52EbyJSp67-kMDiG6W0lr141g-9shytWSl-7ch5gwRKiTbENfOKs8Reoox_3gLTuLDXkTgabfYYPwXzqd9J16yYfUSYtOv4N7frL8gssQHZSJ_A4B-5NBeEqk0Yf8axB495SYvEbQtDeRXs_svmIthQfP6yrOFnu8xu58k-Q61S5kwdKQqhq1FBzkK5MdAbdJhth6avKZM7QpGvniQ17OZT5PgcJi0W7rGsG6iIr__bUw_1MUMmTjFAIZMdKe4HPW6OUSI7yCkVgVb2TFFW4kkfMSoCQVXSGq716N3Og65ARcbw3VkjPn-LnhZNzQBL_zSTmgl-jxFG5QDsKKwl4yAzWH2oHlZ9nLM4eE-WWO8owmMQxwcEZWy7P-D1vuQj8)

<details>
<summary>PlantUML source</summary>

```plantuml
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
```

</details>

</div>
<div>

- __Identifiable__: Clase cliente que necesita identificar a sus objetos a través de algún atributo identificador

- __Handler__: Interfaz para declarar los identificadores de los objetos de la clase `Identifiable`

- __ConcreteHandler__: Implementación concreta de la interfaz `Handler`

</div>
</div>


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


### Conceptos de diseño

- __Responsabilidad única__: La lógica de generación de identificadores (ej. limpiar caracteres, validar formato) debe estar aislada de la lógica de negocio principal.

- __Acoplamiento__ (bajo): El sistema principal debe usar interfaces para generar IDs, sin depender de si se usa un UUID, una secuencia o un algoritmo custom.

- __Cohesión__ (alta): Agrupar métodos relacionados con la manipulación de IDs en un único componente.

- __Encapsulamiento__: Ocultar los detalles algorítmicos de la construcción del identificador tras los métodos públicos.

- __Reutilización y flexibilidad__: Permitir la reutilización del componente de identificación en otros sistemas y facilitar la adaptación a futuros cambios.


### Implementación en los lenguajes

#### Java: Identificadores con `java.lang.Comparable`

`Comparable<T>` es una interfaz que define el orden natural entre objetos del mismo tipo. La implementan `String`, `File`, `Date`, etc. y las _clases de envoltura_ del JDK (i.e. `Integer`, `Long`, etc.)

__Método de la interfaz__:

```java
  public int compareTo(T o)
```

El tipo `T` garantiza seguridad de tipos: un `Comparable<Empleado>` solo se compara con `Empleado`, evitando casts y errores en tiempo de ejecución.


__Invariantes:__ las debe asegurar cualquier implementación de `compareTo`

`sgn(x.compareTo(y)) = -sgn(y.compareTo(x))`

`(x.compareTo(y)>0 and y.compareTo(z)>0)` $\Rightarrow$ `x.compareTo(z)>0`

`x.compareTo(y)=0` $\Rightarrow$ `sgn(x.compareTo(z))=sgn(y.compareTo(z))` $\forall$ `z`

__Consistencia con `equals`__: recomendable pero no exigible

`(x.compareTo(y)=0)` $\Leftrightarrow$ `(x.equals(y))`

> [!NOTE]
> En Java no se puede definir un método `default` en una `interface` que sea override‑equivalent a un método público de Object (como equals, hashCode, toString). Puedes declararlo de forma abstracta en la interfaz, pero no darle implementación default.


#### C++: Comparación de identificadores

Cómo implementar la interfaz de comparación de un Handler en C++

¿Tienen sentido las siguientes implementaciones?

```c++
   static int compare(const Handler&, const Handler&);
   int compareTo(const Handler&); // member function
```

Ver __[stackoverflow](https://stackoverflow.com/questions/20005392/is-there-a-compareto-method-in-c-similar-to-java-where-you-can-use-opera)__


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


<div class="cols">
<div>

## Reutilización y flexibilidad

- __Reutilización__: Construir software fácil de reutilizar sin tener que cambiar los módulos ya escritos (afecta a la fase de __desarrollo__)
- __Flexibilidad__: Adaptarse a cambios de requisitos y construir software fácil de cambiar (afecta a la fase de __mantenimiento adaptativo__)

</div>
<div>

### El árbol de la calidad del software

![](./img/sqa-tree.png)

</div>
</div>


## CASO PRÁCTICO 2

### Pruebas unitarias


### jUnit: Framework de pruebas unitarias

- JUnit es un framework en Java que sirve para diseñar, construir y ejecutar __pruebas unitarias__
- Una prueba unitaria comprueba la corrección de un _módulo_ de software en cuanto a funcionalidades que ofrece.
- En el caso de Java, las pruebas unitarias comprueban la corrección de cada uno de los métodos de _cada clase_.


¿Por qué? ¿Cómo funciona?


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


#### Pegas

- Cuanto más grande sea la interfaz de la clase, mayor será el `main`

- El tamaño del código de la clase crece por las pruebas

- Poco fiable, porque `main` forma parte de la misma clase y tiene acceso a los elementos privados

- Difícil de automatizar las pruebas, incluso pasando argumentos a `main`


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


¿De qué están hechas las anotaciones como `@Test`?


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

<!--
 ![](./img/junit-design-1.png)
-->

![PlantUML diagram](https://kroki.io/plantuml/svg/eNqNUkGKwzAMvPsVPu5SnBcsJUtLP7BdyFU4KgRSu0gyPTT9-8Z2GndND71ZM9LMSLhlAZJwHpUanCCdwKI-Iou-Ka031gcnsdwBI398RoyCmx93pewIzPqbGSm332YxGexdbyCBRwqYZmriACO_ZrquexaP1j9hEMxxoO8jknMsRaIrLUsIgqU1R86PgkrcKy5YSq7N497Z-7UGo_xeHvNAe391tcQerScQT2-cdLU04WKm7XLcBOuvqWnWTBWUjqDKtbwx20RkyRKhaaaF-I-bJzfVouvnL_EHZsWsZA)

<details>
<summary>PlantUML source</summary>

```plantuml
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
```

</details>

</div>
</div>


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


<div class="cols">
<div>

#### Utilización del framework jUnit

<!--
 ![](./img/junit-design-2.png)
-->

![PlantUML diagram](https://kroki.io/plantuml/svg/eNp9kE0OgkAMhfc9xQsrjeECLIyRG6gXmAwFR6BDZkpYKHd3RE2MJu6a7_007S6qCTr2HXVcK9QjuOasqFxgq84L0WBsaxpGdhnFaVHUwfQ8-dBmuBLgRDnUxjJOHHVBwMb6UfQBShM5rtZPGkZZxpnm394kCodnqe1MjNin7KPjsCi4phC-YZ5vl8U0A0THsx8GJ02ZjnpvR175SfLby_fHssWnSLRjqdJr7seiYek)

<details>
<summary>PlantUML source</summary>

```plantuml
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
```

</details>

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

#### EcommerceTestSuite con jUnit 4

```java
  @RunWith(Suite.class)
  @SuiteClasses({ ShoppingCartTest.class, CreditCardTest.class })
  public class EcommerceTestSuite {
      //...
  }
```


#### Pregunta

¿Qué hemos conseguido con las anotaciones `@Test` del JDK $\geq$ 1.5?


¿Qué hemos conseguido con las anotaciones `@Test`?

#### Respuesta:

- No necesitar de la incómoda herencia (i.e. mecanismo de implementación)


### Ejercicio propuesto: CreditCardTest

Diseñar y codificar una suite de casos de prueba unitaria para `CreditCard` usando jUnit versión 4.


### Arquitectura del framework jUnit

<div class="cols">
<div>

![PlantUML diagram](https://kroki.io/plantuml/svg/eNp9U0tu2zAQ3fMUA28iN5DRz84QjBh2gbZogyBOuwm6GFMjmShFqiSFwE5ymJ6hR8jFOqT8R1sJkMjH4bw3fMMrH9CFrtHC_1CmRYcN-LDWxF-n5NmK1Oj9NPDKsgv0UVqzUBuC10chWhkK65bAurCyx1lXWNoHZWqoUHsS4gLejGBOlTJKqpffBkqCa1taL5QJ5CqUBHfkAxTFzDat9SrQOI0MmTCZwKMAfi7BdSaLgbfkOx2G4lmIJBQOYMqhNckQBdxEQcQcKQmH45KrRRngsG-GnnrmBk3JgUVxR02rMRB8IS6txw7KPhNWCbrRXV3jko9wQZHRur3WHKprZv6H7h593Il5jgFxPdsteQpf2_0sELq5fTDZWcWLjuUcS_vPUfUolmXiiZ-U7AJmOtaPhn1RDYIF9lo6CgjRTSAN0xJbPsIt8YBf9DtwlrCi2E4hS8DwVMi2tET3dgS3pFEq9tbDJ3Ivv9zPTklOmX0jF3ikyQ9F3w9Po9HeozMoFS8OBj7l-YmoSPbuhOyDdWpjTYgEsIb3fPgd972Lcw6exobGvkEr6zZY4vjQIAgaQW1YKrkSYwf_RUR-v1JlSea7U_Uq5JOjmEhQO6q3BNnes77U3kqbdy3vGrwa9PdhDFX8p1rm1JLh3FLhEaGmasfTOy3EFYfxZf4D5tBT8g)

<details>
<summary>PlantUML source</summary>

```plantuml
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
```

</details>

<!--
 ![](./img/junit-patterns.png)
-->

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


## Bibliotecas y frameworks

#### Flujo de control en una biblioteca

![](./img/biblioteca.png)


#### Flujo de control en un framework

![](./img/framework.png)


### Frameworks

#### Definición de *framework*

> Colección de clases e interfaces que cooperan para formar un diseño reutilizable de un tipo específico de software
>
> –– [E. Gamma et al.](bibliografia.html#gamma)

- El framework proporciona unas guías arquitectónicas (diseño empaquetado) para dividir el diseño en clases abstractas y definir sus _responsabilidades_ y _colaboraciones_.
- El framework se debe personalizar definiendo subclases y combinando instancias, o bien configurando valores que definen el comportamiento por defecto


### Principios de diseño de un framework OO

- Datos encapsulados
- Interfaces y clases abstractas
- Métodos polimórficos
- Delegación

### Herramientas de diseño OO

- __Patrones__: elementos reutilizables de diseño
- __Frameworks__: colecciones de patrones abstractos a aplicar


### Framework vs. biblioteca

- API orientado a objetos vs. API generalmente funcional
- Flujo de control invertido
- Programador _cliente_ (código específico) vs. programador de _API_ (código reutilizable)


### Principios y técnicas de un framework

- __Abstracción__
  - Clases y componentes abstractos
  - Interfaces abiertas
  - Uso de patrones de diseño
  - Componentes de un dominio específico

- Máxima __cohesión__, mínimo __acoplamiento__
  - Minimizar dependencias: Una clase A presenta una dependencia con otra clase B (A $\rightarrow$ B) si la primera usa una instancia de la segunda.
  - Cuando no se pueden eliminar las dependencias, mantener las abstractas e __inyectar__ las concretas.


> Coupling is the enemy of change, because it links together things that must change in parallel
>
> D. Thomas & A. Hunt, [The Pragmatic Programmer](https://pragprog.com/titles/tpp20/the-pragmatic-programmer-20th-anniversary-edition/), 20th Anniversary Edition, 2019


#### Inyección de dependencias

Una clase o módulo no debería configurar sus dependencias estáticamente, sino ser configurada desde fuera


## CASO PRÁCTICO 3

### Caballeros de la mesa redonda


## Ejemplo: tomado de [Spring in Action](bibliografia.html#spring)


Añadir pruebas unitarias al programa siguiente:

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


```java
public class HolyGrailQuest {
  public HolyGrailQuest() { /*...*/ }

  public HolyGrail embark() throws GrailNotFoundException {
    HolyGrail grail = null;
    // Look for grail ...
    return grail;
  }

}
```


### Diseño de pruebas con jUnit 3


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


### Diagrama de clases


¿Dónde está el acoplamiento?

![PlantUML diagram](https://kroki.io/plantuml/svg/eNqFUkEKgzAQvOcVwVN70Ad4KN5a6EFavJUeolk1GGObREop_r1JKmKtpIHAMjszO1mSKE2k7luO0I0UDakAB7LrBc1IziHAL4QwLjhRCh8Fq2qdllkN54lhCNgcQVpwxb0HpV0FbU5kk4qTRTZbgw3mTm6Hjj_3kjDu-qNNZYGZ2iMbFUxZZM5bjxlyKHW4W4z9KBZRIto9RDSj-nyjLyIaVtcYx9oY_19m5jbhfYijRNJ2zORVil96qRmlIK4_uRMQ1PyDNxbuqps)

<details>
<summary>PlantUML source</summary>

```plantuml
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
```

</details>


### Acoplamiento


No deseable

![PlantUML diagram](https://kroki.io/plantuml/svg/eNqNUssKwyAQvPsVkl7aQ_yAHEpuLfQQWvIDJm4exJhWDaWU_HvVhiBB0grCMjszOy6mSlOpx54jdKdlR2vAkRxGwXJacIjwGyGMS06VwhfR1o3OqryB28IwBGyOoD244jGC0q6CvqCyy8TVIvuDwSZzF7fzwF8nSVvu-rNNbQFPvSGbFa2yiM8Lx4w5VDo-rsZ-FasohA1PQTzqli_xiRjvJDCEpuAyk0Qb-98rzd0-Np_jKETajpkfpPwnDUVPQTDzIT7JCKzR)

<details>
<summary>PlantUML source</summary>

```plantuml
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
```

</details>


#### Pegas:

- Instanciación de `HolyGrail`

- Cada vez que se prueba `KnightOfTheRoundTable`, también se prueba `HolyGrailQuest`.

- No se puede pedir a `HolyGrailQuest` que se comporte de otra forma (v.g. devolver null o elevar una excepción)


Ocultar la implementación detrás de una interfaz:

```java
public interface Knight {
  Object embarkOnQuest() throws QuestFailedException;
}

public class KnightOfTheRoundTable implements Knight {
  private String name;
  private Quest quest;

  public KnightOfTheRoundTable(String name) {
    this.name = name;
    quest = new HolyGrailQuest();
  }
  public Object embarkOnQuest() throws QuestFailedException {
    return quest.embark();
  }
}
```


```java
public interface Quest {
  abstract Object embark()
    throws QuestFailedException;
}

public class HolyGrailQuest implements Quest {
  public HolyGrailQuest() { /*...*/ }
  public Object embark() throws QuestFailedException {
    // Do whatever it means to embark on a quest
    return new HolyGrail();
  }
}
```


#### Dependencias

![PlantUML diagram](https://kroki.io/plantuml/svg/eNp9Ut0KgjAUvt9TDLupC_cEEd4VdBGFL3DUo0lz1jaJKN-9bYpMMwfC-M73d9RIaZC6qTghd0hvUCANZN2ILIaEY0DfhFBaCo0yhxTpUZTFVRuUmoNVAvJ2EucGlV5vDNaaZ8R3oxHd56UclOo9T3l8xcuQ3GsEVOguD2u0nNq5HWr-2ksouR9dWGCxxCDrFaWyiM_rV99-GJuv3NG6WMcaV_FdpsuG0qLhbtp-JTHrdJMBy-qnYB7fo85HsF82aQlZ-ASxe39mffJ_bF3n81zCsnBaJ0KRmT_xC4k00Qs)

<details>
<summary>PlantUML source</summary>

```plantuml
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
```

</details>


#### Pegas:

- El `KnightOfTheRoundTable` aún depende de un tipo específico de `Quest` (i.e. `HolyGrailQuest`) obtenido mediante `new`


¿Debe ser el caballero responsable de obtener un desafío?


```java
public class KnightOfTheRoundTable implements Knight {
  private String name;
  private Quest quest;

  public KnightOfTheRoundTable(String name) {
    this.name = name;
  }
  public Object embarkOnQuest() throws QuestFailedException {
    return quest.embark();
  }
  public void setQuest(Quest quest) {
    this.quest = quest;
  }
}
```


- El caballero sólo sabe del desafío a través de su interfaz `Quest`.

- Puede asignársele cualquier implementación de `Quest`
 (`HolyGrailQuest`, `KillDragonQuest`, etc.)


![PlantUML diagram](https://kroki.io/plantuml/svg/eNp1kLEKwjAURfd8xaOTDkIadckgHQQHJz8htKGNxpeSvCL-vVECMUG3B_eew-V1gZSn5W4Zm-NhejMrJDB41T0534Lzg_bQ8iKenH2evDL2suhAqSP4T4VI8baMb8bao1ejw2_Hriw18oxmnKhJ8Z4DY3nb5lAvkYD6UVWyRELQ9CmuSm6dEfFG6nGVVvzVVmD0dhqH-N4Xo7N8xg)

<details>
<summary>PlantUML source</summary>

```plantuml
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
```

</details>

<!--
![](./img/di-knight.png)
-->


- Parece que no hay dependencia entre `KnightOfTheRoundTable` y `HolyGrail` porque `embark()` se ha definido como que devuelve un `Object`

Ejercicio: Discutir el tipo de retorno `Object` de `embarkOnQuest`:

- Puede provocar `ClassCastException`
- Solución propuesta: rediseñar la interfaz `Quest`

<!--
## Inyección de dependencias

![](./img/dep-injection.png)
-->


### Inversión de control

Es la base de la inyección de dependencias

> The question is: _what aspect of control are they inverting?_ [...] Early __user interfaces__ were controlled by the application program. You would have a sequence of commands like "Enter name", "enter address"; your program would drive the prompts and pick up a response to each one. With __graphical__ (or even screen based) UIs the UI framework would contain this main loop and your program instead provided event handlers for the various fields on the screen. The main control of the program was inverted, moved away from you to the framework.
>
> –– Martin Fowler, [IoC containers and the DI pattern](http://martinfowler.com/articles/injection.html) [1]

[1] http://martinfowler.com/articles/injection.html


#### IoC–Inversion of Control / DI–Dependency Injection

- Una aplicación está compuesta por dos o más clases que colaboran.
- Los objetos deben recibir las dependencias en su creación, por parte de una entidad externa o __contenedor__ que los coordina.
- IoC = Inversión de la responsabilidad de cómo un objeto obtiene referencias a los objetos con los que colabora
- Ventaja = __bajo acoplamiento__: un objeto sólo sabe de sus dependencias por su _interfaz_, no por su _implementación_, ni por cómo fueron instanciados.
- Entonces la dependencia puede cambiarse por una implementación distinta (incluso en __tiempo de ejecución__)
- _Hollywood Principle: Don't call us, we'll call you"._


### Factorías

Una factoría proporciona un mecanismo de inyección de dependencias, visto desde el lado opuesto (los clientes adquieren las dependencias, no se les inyecta)

Ejemplo: [Spring FactoryBean](http://www.baeldung.com/spring-factorybean)


## Discusión sobre la reutilización

> We most likely would have been better off not attempting to create a reusable function in the first place
>
> –– Roger Sessions, [The Misuse of Reuse](http://simplearchitectures.blogspot.com.es/2012/07/misuse-of-reuse.html) [2]

[2] http://simplearchitectures.blogspot.com.es/2012/07/misuse-of-reuse.html


### Factorizar una función

![](./img/misuse-reuse-1.png)


![](./img/misuse-reuse-2.png)

### Ventajas (supuestas) de reutilizar:

__Ahorro__: Si $\exists$ $s$ sistemas $\wedge ~ coste(Function~1) = c$ $\Rightarrow$ ahorro = $c \times (s-1)$


### Amenazas (reales) a la reutilización:

![](./img/misuse-reuse-3.png)


- Realmente el ahorro depende de la __complejidad__. Y muchas veces, la complejidad de la función está exponencialmente relacionada con el número de sistemas.
- Con un único punto de fallo, si `Function 1` falla, todos los sistemas pueden fallar a la vez.
- La seguridad es inversamente proporcional a la complejidad del sistema.
<!--
 - Se incrementan los costes de llevar los sistemas a la nube.
-->


### Conclusión sobre la reutilización

- No crear funciones reutilizables en primer lugar

- Aplicar el principio __YAGNI__: __You Ain't Gonna Need It__


## CASO PRÁCTICO 4

### Figuras geométricas


## Principios SOLID

Los principios SOLID de [Uncle Bob Martin](https://en.wikipedia.org/wiki/SOLID_(object-oriented_design)) nos dicen:

- Cómo organizar en __módulos__ (en OO, __clases__) las estructuras de datos y las funciones
- Cómo deben quedar _interconectadas_ las clases (vía __dependencias__)

El objetivo de SOLID es crear estructuras software de nivel intermedio que sean:

- _flexibles_: tolerantes a los cambios
- _poco complejas_: fáciles de comprender
- _reutilizables_: la base de componentes útiles para muchos sistemas software

En C++: [Breaking Dependencies: The SOLID Principles](https://www.youtube.com/watch?v=Ntraj80qN2k) by Klaus Iglberger


## Principio de responsabilidad única

### SRP: *Single responsibility Principle*

> A class should have only one reason to change
> –– Bob Martin

- Una clase que modela múltiples aspectos genera acoplamiento entre los distintos aspectos
- Un cambio en algún aspecto obligará a cambios accidentales en los clientes que no dependen de dicho aspecto

SRP es lo mismo que el principio de __cohesión__ de [DeMarco](bibliografia.html#demarco)


<!--
**Notes**

 Los módulos enmarañados que nunca cambian no son problemáticos
-->

SRP es aplicación directa de la [ley de Conway](http://www.melconway.com/Home/Conways_Law.html):
> Any organization that designs a system (...) will produce a design whose structure is a copy of the organization's communication structure.
> –– M. Conway, _Datamation_, April 1968

- Cuando se diseña software, hace falta conocer los grupos/equipos/roles a los que éste sirve, y dividir el sistema en componentes separados, de forma similar a como estos grupos de personas se comunican normalmente en la vida real.

- Es necesario tener conocimiento del __dominio__ para poder dividir bien las responsabilidades

- Tiene que ver con la __variabilidad__ de los requisitos


### Ejemplo: Shapes versión 1 en Java

```java
package shapes;

interface Shape {
  double area();
  void draw();
}

class Point {
  double getX() {...}
  double getY() {...}
}

abstract class Polygon implements Shape {
  Point getVertex(index i) {...}
  void draw() {...}
  String toString() {...}
}

class Triangle extends Polygon {
  double area() {...}
}
abstract class RectParallelogram extends Polygon {
  double area() {...}
}
```


```java
class Square extends RectParallelogram {...}

class Rectangle extends RectParallelogram {...}

abstract class ClosedCurve implements Shape {...}

class Circle extends ClosedCurve {
  double getRadius() {...}
  Point getCenter() {...}
  double area() {...}
  void draw() {...}
  String toString() {...}
}

class Ellipse extends ClosedCurve {
  double getApogeeRadius() {...}
  double getPerigeeRadius() {...}
  Point getFocus1() {...}
  Point getFocus2() {...}
  Point getCenter() {...}
  double area() {...}
  void draw() {...}
  String toString() {...}
}
```


#### Preguntas

- ¿Cuántas responsabilidades tienen las clases que implementan la interfaz `Shape`?
- ¿Cuáles son estas responsabilidades?
- ¿Qué parte no cumple SRP en el ejemplo?


#### Respuestas

- Dos responsabilidades: geometría computacional + dibujo en pantalla
- Todas las figuras tienen métodos `draw` y `toString` (dibujar en pantalla) además del método `area` que calcula el área (geometría computacional) $\rightarrow$ Violación del SRP

#### Solución

Patrón de diseño __Visitor__


### Ejercicio

- Buscar información de los patrones _ActiveRecord_ y _Data Access Object (DAO)_.
- Discutir si cumplen o violan el SRP.

<!--
**Notes**

En general, ActiveRecord tiene la responsabilidad de modelar los datos en la base de datos, proporcionar una interfaz para acceder y manipular esos datos, y también puede incluir la lógica de negocio necesaria para trabajar con los datos.

Desde una perspectiva del principio de responsabilidad única (SRP), ActiveRecord no cumple completamente con este principio porque tiene varias responsabilidades. Específicamente, ActiveRecord tiene la responsabilidad de:

Representar y manipular datos en la base de datos
Proporcionar una interfaz para acceder y manipular esos datos
Incluir la lógica de negocio necesaria para trabajar con los datos
Sin embargo, a menudo se considera que ActiveRecord sigue una variante del principio de responsabilidad única, llamada "Principio de responsabilidad única de dominio" (Single Responsibility Principle of Domain, en inglés), que establece que una clase debe tener una única responsabilidad dentro del dominio de la aplicación. En este sentido, ActiveRecord tiene la responsabilidad de modelar los datos dentro del dominio de la aplicación.

El patrón Data Access Object (DAO) es un patrón de diseño de software que se utiliza comúnmente en el desarrollo de aplicaciones para separar la lógica de negocio de la lógica de acceso a datos.

El objetivo principal del patrón DAO es proporcionar una interfaz unificada para acceder a los datos desde una variedad de fuentes de datos, como una base de datos, un archivo o un servicio web, entre otros. La clase DAO encapsula la lógica de acceso a datos y proporciona métodos para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) en la fuente de datos correspondiente.

Desde una perspectiva del principio de responsabilidad única (SRP), el patrón DAO cumple con este principio. Esto se debe a que la clase DAO tiene una única responsabilidad, que es la de encapsular la lógica de acceso a datos y proporcionar una interfaz unificada para acceder a los datos. La lógica de negocio se encuentra en otra clase o conjunto de clases, lo que permite separar las responsabilidades y facilita la reutilización del código.
-->


### Example: Circle class

```cpp
class Circle
{
  public:
    explicit Circle (double rad )
      : radius { rad }
      , //... remaining data members
    {}

    double getRadius() const noexcept;
    //... getCenter(), getRotation(), ...

    void translate (Vector3D const& );
    void rotate ( Quaternion const& );

    void draw ( Screen& s, /*...*/ );
    void draw ( Printer& p, /*...*/ );
    void serialize ( ByteStream& bs, /*...*/ );
    //...

  private:
    double radius;
    ///... remaining data members
}
```


## Principio de Abierto-Cerrado

### OCP: *Open-Closed Principle*

> Toda clase, módulo, aspecto o función debe quedar abierto para extensiones pero cerrado para modificaciones
>
> ––B. Meyer, [Object Oriented Software Construction](#meyer)

Para que un sistema software sea fácil de cambiar, debe diseñarse para que permita cambiar su comportamiento añadiendo código, no cambiando código existente.

- Si un cambio en un sitio origina una cascada de cambios en otros puntos del sistema, el resultado es un sistema frágil y rígido
- Es difícil averiguar todos los puntos que requieren cambios
- Código cerrado para modificaciones, pero abierto para extensión mediante delegación en vertical (subclases) u horizontal (composición)


### Ejemplo: Shapes versión 2 en C++

¿Qué parte no cumple OCP en el ejemplo?


#### Versión imperativa (sin objetos):

```cpp
enum ShapeType {circle, square};
struct Shape
{
  ShapeType itsType;
};

struct Circle
{
  ShapeType itsType;
  double itsRadius;
  Point itsCenter;
};
void DrawCircle(struct Circle*);

struct Square
{
  ShapeType itsType;
  double itsSide;
  Point itsTopLeft;
};
```


```cpp
void DrawSquare(struct Square*);

typedef struct Shape *ShapePointer;

void DrawAllShapes(ShapePointer list[], int n)
{
  int i;
  for (i=0; i<n; i++)
  {
    struct Shape* s = list[i];
    switch (s->itsType)
    {
      case square:
        DrawSquare((struct Square*)s);
        break;
      case circle:
        DrawCircle((struct Circle*)s);
        break;
    }
  }
}
```

#### Problema:

- `DrawAllShapes` no está cerrado para modificaciones cuando aparecen nuevos tipos de `Shape`

#### Solución

- __Abstracción__ (ocultación de la implementación): clase abstracta y métodos polimórficos.
- __Patrones de diseño__: _template method_ y/o _strategy_


Aplicando el OCP...

```csharp
public interface Shape
{
  void Draw();
}

public class Square: Shape
{
  public void Draw()
  {
    //draw a square
  }
}

public class Circle: Shape
{
  public void Draw()
  {
    //draw a circle
  }
}
```


```csharp
public void DrawAllShapes(IList shapes)
{
  foreach(Shape shape in shapes)
    shape.Draw();
}
```

- Si queremos ampliar el comportamiento de `DrawAllShapes`, solo tenemos que añadir una nueva clase derivada de `Shape`
- Si se aplica bien OCP, los cambios de un cierto tipo obligan a añadir nuevo código, no a modificar el existente


### Ejercicio: Shapes and Circles

Arreglar para que cumpla OCP

```cpp
enum ShapeType
{
  circle,
  square,
  rectangle
};

class Shape
{
  public:
    explicit Shape ( ShapeType t )
      : type { t }
    {}
    virtual ~Shape() = default;
    ShapeType getType() const noexcept;

  private:
    ShapeType type;
};
```
```cpp
class Circle: public Shape
{
  public:
    explicit Circle ( double rad )
      : Shape{ circle }
      , radius { rad }
      , //... remaining data members
    {}

    virtual ~Circle() = default;
    double getRadius() const noexcept;
    //... getCenter(), getRotation(), ...

  private:
    double radius;
    ///... remaining data members
};

void translate ( Circle&, Vector3D const& );
void rotate ( Circle&, Quaternion const& ) ;
void draw ( Circle const& );
```
```cpp
class Square: public Shape
{
  public:
    explicit Square ( double s )
      : Shape{ square }
      , side { s }
      , // ... remaining data members
    {}

    virtual ~Square() = default;
    double getSide() const noexcept;
    //... getCenter(), getRotation(), ...

  private:
    double side;
    // ... remaining data members
};

void translate ( Square&, Vector3D const& );
void rotate ( Square&, Quaternion const& ) ;
void draw ( Square const& );
```
```cpp
void draw ( std::vector<std::unique_ptr<<Shape>>>) const & shapes )
{
  for ( auto const& s : shapes )
  {
    switch ( s-> getType() )
    {
      case circle:
        draw ( *static_cast<Circle const*>( s.get() ) );
        break;
      case square:
        draw ( *static_cast<Square const*>( s.get() ) );
        break;
      case rectangle:
        draw ( *static_cast<Rectangle const*>( s.get() ) );
        break;
    }
  }
}

int main()
{
  using Shapes = std::vector<std::unique_ptr<Shape>>;
  // Creating some shapes
  Shapes shapes;
  shapes.push_back (std::make:unique<Circle>( 2.0 ));
  shapes.push_back (std::make:unique<Square>( 1.5 ));
  shapes.push_back (std::make:unique<Circle>( 4.2 ));
  // Drawing all shapes
  draw ( shapes );
}
```


### Open-Closed Principle

> In general, no matter how _closed_ a module is, there will always be some kind of change against which it is not closed. There is no model that is natural to all contexts!
>
> Since closure cannot be complete, it must be strategic. That is, the designer must choose the kinds of changes against which to close the design, must guess at the kinds of changes that are most likely, and then construct abstractions to protect against those changes.
>
> –– Bob C. Martin

### Implicaciones arquitectónicas

OCP es un principio más arquitectónico que de diseño de clases y módulos.


### Solución al ejercicio: Shapes and Circles

Versión en C++ que cumple el OCP

```cpp
class Shape
{
  public:
    Shape() = default;
    virtual ~Shape() = default;

    virtual void translate ( Vector3D const& ) = 0;
    virtual void rotate ( Quaternion const& ) = 0;
    virtual void draw() const = 0; // check!
};
```


```cpp
class Circle : public Shape
{
  public:
    explicit Circle (double rad )
      : radius { rad }
      , //... remaining data members
    {}

    virtual ~Circle() = default;

    double getRadius() const noexcept;
    //... getCenter(), getRotation(), ...

    void translate ( Vector3D const& ) override;
    void rotate ( Quaternion const& ) override;
    void draw () const override;

  private:
    double radius;
    ///... remaining data members
}
```


```cpp
class Square : public Shape
{
  public:
    explicit Square (double s )
      : side { s }
      , //... remaining data members
    {}

    virtual ~Square() = default;

    double getSide() const noexcept;
    //... getCenter(), getRotation(), ...

    void translate ( Vector3D const& ) override;
    void rotate ( Quaternion const& ) override;
    void draw () const override;

  private:
    double side;
    ///... remaining data members
}
```


```cpp
void draw ( std::vector<std::unique_ptr<<Shape>>>) const & shapes )
{
  for ( auto const& s : shapes )
  {
      s->draw();
  }
}

int main()
{
  using Shapes = std::vector<std::unique_ptr<Shape>>;

  // Creating some shapes
  Shapes shapes;
  shapes.push_back (std::make:unique<Circle>( 2.0 ));
  shapes.push_back (std::make:unique<Square>( 1.5 ));
  shapes.push_back (std::make:unique<Circle>( 4.2 ));
  // Drawing all shapes
  draw ( shapes );
}
```


## OCP versus SRP

Cumple el OCP, pero ¿y el SRP?

```cpp
class Shape
{
  public:
    Shape() = default;
    virtual ~Shape() = default;

    virtual void translate ( Vector3D const& ) = 0;
    virtual void rotate ( Quaternion const& ) = 0;
    virtual void draw() const = 0; // drawing is again inside the Shapes
};
```


## Principo de segregación de interfaces

### ISP: *Interface Segregation Principle*

> Los clientes no deben depender de métodos que no usan.
>
> Bob C. Martin

- Las interfaces son para los __clientes__, no para hacer jerarquías
- Evitar interfaces __gruesas__ con muchos métodos (descohesionadas)
- Los cambios en los métodos ignorados pueden provocar cambios en un cliente que no los usa


- La interfaz de una clase puede dividirse en __bloques__ de métodos relacionados. Unos clientes usan un bloque y otros clientes usan otro bloque. Si un cliente necesita conocer una interfaz no cohesionada, debe hacerlo combinando una o más clases (o mejor, sus interfaces)
- ISP es a las interfaces lo que SRP es a clases y métodos
- Violar el ISP es muy común en lenguajes de tipos estáticos (C++, Java, C#). Los lenguajes dinámicos (Ruby, Scala) ayudan algo más a no violar el ISP (v.g. con los _mixins_)

<!--
**Notes**

En los lenguajes de tipos estáticos, los tipos deben ser declarados y especificados en tiempo de compilación. Esto significa que las interfaces deben ser definidas de antemano, antes de que se implementen las clases que las utilizan. En algunas ocasiones, esto puede llevar a la definición de interfaces grandes y complejas que contienen muchos métodos que no son necesarios para todos los clientes que utilizan la interfaz.

En cambio, en los lenguajes de tipos dinámicos, las interfaces pueden ser definidas en tiempo de ejecución. Esto permite que las interfaces sean más pequeñas y específicas para cada cliente, ya que solo contienen los métodos necesarios para cada caso de uso particular.
-->

<!--
### Ejemplo: acoplamiento contexto-strategy

- ¿Cómo se comunican _Strategy_ y _Context_ (acoplado/desacoplado)?
- _Context_ Puede definir una interfaz que le permita a _Strategy_ acceder a sus datos
-->


### Ejemplo: puertas de seguridad

Una implementación de puertas de seguridad con temporizador (`TimedDoor`) que hace sonar una alarma cuando la puerta está abierta durante un cierto tiempo.

#### Diseño:

<!--
![](./img/isp-timer-door.png)
-->

![PlantUML diagram](https://kroki.io/plantuml/svg/eNpVj0EOAiEMRfc9RZdj4nAEM4kewQsQphoSBkwpK_XuU6gLZ1f-e_0NSxXP0rYEIfla8R43YnwDItMzViGeYhYUjUuTs_FriqRhSPEEXwAViB8-0IH2jt_aNDQ7cCvF-suLsgLUmlLJlAFdc5_LfxVAf6wDzm1W2EdLVWd3tBfKq35oB3KIR_g)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
class Timer {
  register(int timeout, TimerClient cli)
}

interface TimerClient {
  timeout()
}

class Door {
  open()
  close()
}

Door .u.|> TimerClient

TimedDoor -u-|> Door

Timer .r.> TimerClient
@enduml
```

</details>


- `TimedDoor` se comunica con `Timer` para registrar un temporizador
- Cuando salta el temporizador, avisa a un `TimerClient`
- Con la solución diseñada, un `TimerClient` puede registrarse a sí mismo en un `Timer` y recibir de éste un mensaje mediante `timeout()`.

![PlantUML diagram](https://kroki.io/plantuml/svg/eNpzKC5JLCopzc3h0tW1UwjJzE1NccnPL1KwUsgvSM3T0ORCCMEUgCSLUtMzi0tSizRKDHQUSjIyizUx9Cfn5BenQg1AaIbJlgDZ-aUlQPmU1OKSovxKhCyXQ2peCtBFADI7MrU)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
--> TimedDoor : open()
TimedDoor --> Timer : register(t0, this)
--> TimedDoor : close()
Timer --> TimedDoor : timeout()
destroy TimedDoor
@enduml
```

</details>


#### Implementación inicial

```csharp
public class Timer {
  public void register(int timeout, TimerClient client) {
    /*code*/
  }
}

public interface TimerClient {
    void timeout();
}
```

- Si se cierra la puerta antes de que venza el timeout $t_0$ y se vuelve a abrir, se registra uno nuevo $t_1$ antes de que el antiguo haya expirado.
- Cuando el primer temporizador $t_0$ expira, se produce la llamada a `timeout()` de `TimedDoor` y no debería.
- Así que cambiamos la implementación:


#### Implementación mejorada

```csharp
public class Timer {
  public void register(int timeout, int timeOutId, TimerClient client) {
    /*code*/
  }
}
public interface TimerClient {
  void timeout(int timeOutID);
}
```

¿En qué ha afectado el __cambio en la implementación__ de `TimerClient`?

![PlantUML diagram](https://kroki.io/plantuml/svg/eNpzKC5JLCopzc3h0tW1UwjJzE1NccnPL1KwUsgvSM3T0OTiQojBVIBki1LTM4tLUos0Sgx1FDJTdBRKMjKLNTEMSc7JL04FmgLRhi5bAmTnl5ZoZKZocjmk5qUAnQEA7DMt6w)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
--> TimedDoor : open()

TimedDoor --> Timer : register(t1, id, this)
--> TimedDoor : close()
Timer --> TimedDoor : timeout(id)
@enduml
```

</details>


- El cambio afecta a los usuarios de `TimerClient`, pero también a `Door` y a los clientes de `Door` (y no debería)
- El problema es que `Door` depende de `TimerClient` y no todas las variedades de puerta son de seguridad (con temporizador)
- Si hacen falta más variedades de puerta, todas ellas deberán implementar implementaciones degeneradas de `timeout()`
- Las interfaces empiezan a engrosarse. Esto puede acabar violando también el LSP


#### Rediseño: puertas de seguridad

__Delegación__ a través del patrón adapter (de objetos o de clases)

- Versión adaptador de clases (por herencia):

   ![](./img/isp-timer-door-class-adapter.png)


- Versión adaptador de objetos (por composición):

  ![](./img/isp-timer-door-object-adapter.png)


### Example: Shapes and Circles (1 de 2)

```cpp
class Circle;
class Square;

class DrawStrategy
{
  public:
    virtual ~DrawStrategy() {}

    virtual void draw ( const Circle& circle ) const = 0;
    virtual void draw ( const Square& square ) const = 0;
};

class Shape
{
  public:
    Shape() = default;
    virtual ~Shape() = default;

    virtual void translate ( Vector3D const& ) = 0;
    virtual void rotate ( Quaternion const& ) = 0;
    virtual void draw() const = 0;
};
```


### Example: Shapes and Circles (2 de 2)

```cpp
class Circle : public Shape
{
  public:
    explicit Circle ( double rad, std::unique_ptr<DrawStrategy> ds )
      : radius { rad }
      , //... remaining data members
      , drawing { std::move(ds) }
    {}

    virtual ~Circle() = default;

    double getRadius() const noexcept;
    //... getCenter(), getRotation(), ...

    void translate ( Vector3D const& ) override;
    void rotate ( Quaternion const& ) override;
    void draw () const override;

  private:
    double radius;
    ///... remaining data members
    std::unique_ptr<DrawStrategy> drawing;
};

class Square: public Shape
{
  //...
}
```


## Aplicación de OCP y SRP

### Ejemplo: Shapes versión 1 en Java (misma versión que en SRP)

```java
interface Shape {
  double area();
  void draw();
}

class Point {
  double getX() {...}
  double getY() {...}
}

abstract class Polygon implements Shape {
  Point getVertex(index i) {...}
  void draw() {...}
  String toString() {...}
}

class Triangle extends Polygon {
  double area() {...}
}

abstract class RectParallelogram extends Polygon {
  double area() {...}
}
```


```java
class Square extends RectParallelogram {...}

class Rectangle extends RectParallelogram {...}

abstract class ClosedCurve implements Shape {...}

class Circle extends ClosedCurve {
  double getRadius() {...}
  Point getCenter() {...}
  double area() {...}
  void draw() {...}
  String toString() {...}
}

class Ellipse extends ClosedCurve {
  double getApogeeRadius() {...}
  double getPerigeeRadius() {...}
  Point getFocus1() {...}
  Point getFocus2() {...}
  Point getCenter() {...}
  double area() {...}
  void draw() {...}
  String toString() {...}
}
```


- Las funcionalidades para pintar (`draw`) y para imprimir (`toString`) pueden descohesionar las clases y atentar contra OCP y SRP.
- Saquémoslas fuera utilizando **aspectos**...


### Orientación a aspectos

La __orientación a aspectos__ (_AOD_/_AOP_) es un paradigma cuyo objetivo es incrementar la modularidad (__ortogonalidad__) de los componentes mediante la separación de aspectos __transversales__ (_cross-cutting concerns_).

![terminología sobre AOP](./img/aspectj-terminology.png)


#### Terminología:

- __aspect__ = modularización de un aspecto de interés (_concern_) que afecta a varias clases o módulos
- __joinpoint__ = especificación declarativa de un punto en la ejecución de un programa (por ejemplo, la ejecución de un método, el manejo de una excepción, etc.)
- __advice__ = acción a tomar por la especificación de un aspecto dado en un determinado _joinpoint_
- __pointcut__ = predicado que define cuándo se aplica un _advice_ de un aspecto en un _jointpoint_ determinado. Se asocia un _advice_ con la expresión de un _pointcut_ y se ejecuta el _advice_ en todos los _joinpoint_ que cumplan la expresión del _pointcut_.


### Ejemplo: Shapes versión 2 (misma versión que en OCP), pero con aspectos

```aspectj
// Ficheros <X>ToString.aj (uno por aspecto)
package shapes.tostring; // para todos los toString()
aspect PolygonToString {
  String Polygon.toString() {
    StringBuffer buff = new StringBuffer();
    buff.append(getClass().getName());
     //... añadir nombre y área...
     //... añadir cada línea desde un vértice al siguiente
    return buff.toString();
  }
}
aspect CircleToString {
  String Circle.toString() {...}
}
aspect EllipseToString {
  String Ellipse.toString() {...}
}

// Drawable.java
package drawing;
interface Drawable {
  void draw();
}
```


```aspectj
// Ficheros Drawable<X>.aj
package shapes.drawing; // para todos los draw()...
import drawing.Drawable;
abstract aspect DrawableShape {
  declare parents: Shape implements Drawable;
  void Shape.draw () //template method
  {
    String drawCommand = makeDrawCommand();
    // enviar orden al motor gráfico...
  }
  String Shape.makeDrawCommand() {
    return getClass().getName() + "\n" + makeDetails("\t");
  }
  abstract String Shape.makeDetails (String indent);
}
aspect DrawablePolygon extends DrawableShape {
  String Polygon.makeDetails (String indent){...}
}
aspect DrawableCircle extends DrawableShape {
  String Circle.makeDetails (String indent){...}
}
aspect DrawableEllipse extends DrawableShape {
  String Ellipse.makeDetails (String indent){...} }
```


## Principio de sustitución de Liskov

### LSP: *Liskov Substitution Principle*

> Un subtipo debe poder ser sustituible por sus tipos base
>
> ––Barbara Liskov,

Si una función $f$ depende de una clase base $B$ y hay una $D$ derivada de $B$, las instancias de $D$ no deben alterar el comportamiento definido por $B$ de modo que $f$ deje de funcionar

<!--
-   Posibilidad de sustitución depende del contexto: En otro programa
    P2, los objetos D pueden no ser sustituibles por objetos B
-->


### Ejemplo: Shapes versión 3

```csharp
struct Point {double x, y;}
public enum ShapeType {square, circle};

public class Shape {
  private ShapeType type;
  public Shape(ShapeType t){type = t;}
  public static void DrawShape(Shape s) {
    if(s.type == ShapeType.square)
      (s as Square).Draw();
    else if(s.type == ShapeType.circle)
      (s as Circle).Draw();
  }
}
```


```csharp
public class Circle: Shape {
  private Point center;
  private double radius;

  public Circle(): base(ShapeType.circle) {}
  public void Draw() {/* draws the circle */}
}

public class Square: Shape {
  private Point topLeft;
  private double side;
  public Square(): base(ShapeType.square) {}
  public void Draw() {/* draws the square */}
}
```


#### Problemas:

- `DrawShape` viola claramente el OCP
- Además `Square` y `Circle` no son sustuibles por `Shape`: no redefinen ninguna función de `Shape`, sino que añaden `Draw()` (violación del LSP)
- Esta violación de LSP es la que provoca la violación de OCP en `DrawShape`
- A continuación, una violación más sutil del LSP...


### Ejemplo: Rectángulos versión 1

De momento solo necesitamos rectángulos y escribimos esta versión:

```csharp
public class Rectangle {
  private Point topLeft;
  private double width;
  private double height;

  public double Width {
    get { return width; }
    set { width = value; }
  }

  public double Height {
    get { return height; }
    set { height = value; }
  }
}
```


Un día hace falta manejar cuadrados además de rectángulos.

Geométricamente, un cuadrado es un rectángulo, así que hacemos uso de la herencia (relación **es-un**):

```java
public class Square: Rectangle {
   ...
}
```


#### Problema: cuadrados como rectángulos

- Un cuadrado podría ser matemáticamente un rectángulo, pero definitivamente un objeto `Square` **no es un** objeto `Rectangle`

- Un `Square` no tiene propiedades `height`y `width`. Pero supongamos que no nos importa el desperdicio de memoria.
- `Square` heredará los métodos accesores de `Rectangle`.
- Así que hacemos lo siguiente...


### Ejemplo: rectángulos versión 2

```csharp
public class Square: Rectangle {
  public new double Width
  {
    set {
      base.Width = value;
      base.Height = value;
    }
  }
  public new double Height
  {
    set {
      base.Height = value;
      base.Width = value;
    }
  }
}
```

Nota: [Diferencia entre `new` y `override` en C#](https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide/classes-and-structs/knowing-when-to-use-override-and-new-keywords)


- El comportamiento de un objeto `Square` no es consistente con el de un objeto `Rectangle`:

  ```csharp
  Square s = new Square();
  s.SetWidth(1);   // fija ambos
  s.SetHeight(2);  // fija ambos

  void f(Rectangle r)
  {
    r.SetWidth(32); // calls Rectangle.SetWidth
  }
  ```

- ¿Qué sucede si pasamos un `Square` a la función `f`?

  ¡No cambia `Height`!

- Podría argumentarse que el error era que los métodos `Width`y `Height` no se declararon `virtual` en `Rectangle`.


### Ejemplo: rectángulos versión 3

```csharp
public class Rectangle
{
  private Point topLeft;
  private double width;
  private double height;
  public virtual double Width
  {
    get { return width; }
    set { width = value; }
  }
  public virtual double Height
  {
    get { return height; }
    set { height = value; }
  }
}
```

Nota: [Métodos redefinibles con `virtual` en C#](https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/virtual)


```csharp
public class Square: Rectangle
{
  public override double Width
  {
    set {
      base.Width = value;
      base.Height = value;
    }
  }
  public override double Height
  {
    set {
      base.Height = value;
      base.Width = value;
    }
  }
}
```


#### Extensión y ocultación de métodos

- La [diferencia entre `new` y `override` en un método en C#](https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide/classes-and-structs/knowing-when-to-use-override-and-new-keywords) es que `new` oculta la implementación de la clase base y `override` la extiende.

Sin embargo, cuando la creación de una clase derivada provoca cambios en la clase base, es síntoma de un __mal diseño__.

El LSP pone en evidencia que la relación **es-un** tiene que ver con el comportamiento público extrínseco, del que los clientes dependen.


Ahora parece que funcionan `Square` y `Rectangle`, que matemáticamente quedan bien definidos.

Pero consideremos esto:

```csharp
void g(Rectangle r)
{
  r.Width = 5;    // cree que es un Rectangle
  r.Height = 4;   // cree que es un Rectangle
  if(r.Area() != 20)
    throw new Exception("Bad area!");
}
```


¿Qué pasa si llamamos a `g(new Square(3))`?

El autor de `g` asumió que cambiar el ancho de un rectángulo deja intacto el alto. Si pasamos un cuadrado esto no es así

__Violación de LSP__: Si pasamos una instancia de una clase derivada (`Square`), se altera el comportamiento definido por la clase base (`Rectangle`) de forma que `g` deja de funcionar.


¿Quién tiene la culpa?

- ¿El autor de `g` por asumir que "en un rectángulo su ancho y alto son independientes" (_invariante_)?
- ¿El autor de `Square` por violar el invariante?
- ¿De qué clase se ha violado el invariante? ¡De `Rectangle` y no de `Square`!

Para evaluar si un diseño es apropiado, no se debe tener en cuenta la solución por sí sola, sino en términos de los _supuestos razonables_ que hagan los usuarios del diseño.


### Ejercicios de LSP

- Robert C. Martin & Micah Martin: [Agile Principles, Patterns and Practices in C#](#unclebob), Prentice Hall, 2006
- Ejemplo de violación de LSP en [frameworks de videojuegos](https://medium.com/ingeniouslysimple/entities-components-and-systems-89c31464240d): Las interfaces HasPhysics, Collidable, Controllable incluyen una dependencia (por herencia) hacia Renderable, que no siempre es cierta (por ejemplo, para un objeto invisible). Solución: arquitectura Entity-Component-System.


### Diseño por Contrato

Relación entre LSP y el **_Design-By-Contract_** (DBC) de *Bertrand
Meyer*:

> A routine redeclaration [in a derivative] may only replace the original precondition by one equal or weaker, and the original post-condition by one equal or stronger
>
> –– ––B. Meyer

- Métodos de clase declaran *precondiciones* y *postcondiciones* al redefinir una operación en una subclase derivada
  - las **precondiciones** sólo pueden sustituirse por otras más débiles/laxas
  - las **postcondiciones** sólo pueden sustituirse por otras más fuertes/estrictas


#### Ejemplo: rectángulos

- Postcondición del _setter_ de `Rectangle.Width`
  (En C++ sería `Rectangle::SetWidth(double w)`):
    `assert((Width == w) && (Height == old.Height));`

- Postcondición del setter de `Square.Witdh`
  (En C++ sería `Square::SetWidth(double w)`):
    `assert(Width==w);`

- La postcondición de `Square::SetWidth(double w)` viola el  contrato de la clase base porque es más débil que la de `Rectangle`


## Principio de Inversión de Dependencias

### DIP: *Dependency Inversion Principle*

- Los módulos de alto nivel no deben depender de módulos de bajo nivel.
Ambos deben depender de abstracciones.

- Las abstracciones no deben depender de los detalles, sino los detalles de las abstracciones

> Depend on abstractions
>
> –– Robert C. Martin


### Ejemplo: estructura en capas

__Diseño inicial__:

![](./img/dip-1.png)

- Las dependencias son transitivas
- _Policy_ depende de todo lo que depende _Mechanism_.


__Diseño invertido__:

![](./img/dip-2.png)


- Cada nivel declara una interfaz para lo que necesita de otros niveles inferiores
- Los niveles inferiores dependen de interfaces definidas en los superiores
- El cliente puede definir la abstracción que necesita (ISP)
- Cada nivel es intercambiable por un sustituto


### Heurística _ingenua_

- Ninguna variable debería guardar una referencia a una clase concreta
- Ninguna clase debería ser derivada de una clase concreta
- Ningún método debería redefinir un método ya implementado de ninguna de sus clases base

Hay que violar alguna vez estas heurísticas, pues alguien tiene que crear las instancias de las clases concretas. El módulo que lo haga presentará una dependencia de dichas clases concretas.

Gracias a la __introspección__ o la carga dinámica de clases, los lenguajes de programación pueden indicar el nombre de la clase a instanciar (por ejemplo, en un fichero de configuración).

Hay clases concretas que no cambian, como `String`, así que no hace ningún daño depender de ellas.

<!--
# Para profundizar sobre patrones

- Martin Fowler – [Patterns in Enterprise Software](https://martinfowler.com/articles/enterprisePatterns.html): Catálogos de patrones a distintos niveles
    - Martin Fowler – [Patterns of Enterprise Application Architecture (EAA)](https://martinfowler.com/eaaCatalog/)
    - Hohpe y Woolf – [Enterprise Integration Patterns (EIP)](http://www.enterpriseintegrationpatterns.com/)
    - Buschmann y otros – [Pattern-Oriented Software Architecture (POSA)](http://www.amazon.com/exec/obidos/ASIN/0471958697) Volume 1: A system of patterns
- Peter Norvig – [Design Patterns in Dynamic Programming](http://www.norvig.com/design-patterns/design-patterns.pdf): Implementaciones más simples para los patrones de diseño del GoF en lenguajes dinámicos
- David Arno – [Are design patterns compatible with modern software techniques?](http://www.davidarno.org/2013/06/17/are-design-patterns-compatible-with-modern-software-techniques/)
- Implementaciones de los patrones de diseño del GoF en diversos lenguajes de programación:
    - Kamran Ahmed – [Design Patterns for Humans!](https://github.com/kamranahmedse/design-patterns-for-humans/blob/master/README.md): Explicación de los patrones de diseño del GoF implementados en PHP
    - Márk Török – [Design Patterns in TypeScript](https://github.com/torokmark/design_patterns_in_typescript)
    - Bogdab Vliv - [Design Patterns in Ruby](https://bogdanvlviv.com/posts/ruby/patterns/design-patterns-in-ruby.html)
- Lewis y Fowler – [Microservicios](https://martinfowler.com/articles/microservices.html)
-->
<!-- Source: patrones.md -->
# DISEÑO DE SISTEMAS SOFTWARE


## Bloques

1. Principios de diseño OO
2. **Patrones de diseño**
3. Arquitectura de software


# PATRONES DE DISEÑO

1. Introducción
2. Patrones del GoF
3. Otros patrones específicos


## Introducción


### Origen de los patrones de diseño

![](./img/pattern_language.png)

<!--
- Los patrones de diseño surgen a partir del libro *A Pattern Language: Towns, Buildings, Construction* de Cristopher Alexander.

- La inspiración del libro fueron las ciudades medievales, atractivas y armoniosas, que fueron construidas según regulaciones locales que requerían ciertas características, pero que permitían al arquitecto adaptarlas a situaciones particulares.

- En el libro se suministran reglas e imágenes y  se describen métodos exactos para construir diseños prácticos, seguros y atractivos a cualquier escala. También recopila modelos anteriores (con ventajas/desventajas) con el fin de usarlos en un futuro.

- El libro recomienda que las decisiones sobre la construcción de edificios se tomen de acuerdo al entorno preciso de cada proyecto.
-->

### Diseño de software con patrones

![Background image](./img/patronesGOF.jpg)

- Conocer un lenguaje OO no te hace un buen diseñador. ¿Qué diferencia hay entre los diseñadores expertos y los novatos? Que los primeros usan recetas exitosas para los problemas habituales y no reinventan la rueda continuamente.

- Un grupo de expertos (_Gang of Four_) se basó en el trabajo de Alexander y lo aplicó al diseño de software, presentando el libro *Design Patterns* con un total de 23 patrones.

### Patrones de diseño

- Patrón de diseño: Una **solución general** a un **problema general** que puede adaptarse a un problema concreto

- La aplicación de patrones depende del **contexto**.

- Ofrece un **vocabulario** de patrones (una jerga entre ingenieros de software)

- Los patrones **clásicos** son ampliamente conocidos: algunos muy aceptados y otros más discutidos...

- Deben usarse con cuidado. Deben simplificar el modelo, **no complicarlo**, por lo que deben surgir de manera natural.

- Han surgido nuevos patrones **específicos** de dominio: patrones de interfaces de usuario, patrones para la integración de aplicaciones empresariales, patrones de flujos de trabajo BPMN, patrones de concurrencia, etc.


## Patrones del Gang of Four

![](./img/patronesGOF.jpg)


### Patrones creacionales

Corresponden a patrones de diseño de software que solucionan problemas de creación de instancias. Nos ayudan a encapsular y abstraer dicha creación. Vamos a ver:

- Factory Method
- Abstract Factory

Pero hay más...

- *Prototype*
- *Builder*
- *Singleton*


### Patrones estructurales

Son los patrones de diseño software que solucionan problemas de composición/agregación de clases y objetos. Vamos a ver:

- Composite
- Decorator
- Adapter

Pero hay más...

- *Facade*
- *Bridge*
- *Flyweight*
- *Proxy*


### Patrones de comportamiento

Son los relativos a la interacción y responsabilidades entre clases y objetos. Vamos a ver:

- Command
- Observer
- Strategy
- Visitor

Pero hay más...

- *Template method*, *Chain of Responsibility*, *Interpreter*
- *Iterator*, *Mediator*, *Memento*, *State*


### [Factory Method](https://refactoring.guru/es/design-patterns/factory-method)

![Factory Method, center](./img/guru/factory-method-mini-2x.png)


#### Ejemplo: Juego de laberinto

![PlantUML diagram](https://kroki.io/plantuml/svg/eNqFU8FO4zAQvfsrRj21iFRdtHBYraoiwYEVAkThA5x4SCxiu7Inqljg3xnbUZN0peUSy89v3nszdjaBpKfOtILcDshB6YicAaU9VqSdFaGSLcKP1dlP2GtFTQ9cnK-gQV03JMKrtjvppYFWW6S3HYLz1LjRQdXKEC6JvC47wpvK2a3-i7ASAm1n4Cq6VewG7wLg7v7x6ZrX7fMjf6-3aXOf1k8hkhT86bB2t7JEry2njmXvBtlUfYKR2s4XY6TyKP2BzWesI8tAXlaUs8FWc7cTGbR87jO558hWJsqLxpYZHD1C46Ia6VYqNz90tPiVpcekcEw6zZzF_-0fOvQ0CYB8e5eljvC41JF30eAKg8J5jPidsvSovmn-ML4JT9Yea-mjx79GPIyI3XVmzoVJ6-jelsrt7XI9iAsRK2DWcvwwg5Oi2xXrfoLpC78_ihZfqMihB9DHt1j0QxrgaFCkmxOD7SzwPsnnqnUmiEYrBIOGeUGExu0ht9Jvhmc64aTEEySHOGBig1bxP_YFgSoriA)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
top to bottom direction
scale 1024 width
scale 650 height
skinparam linetype ortho
skinparam classAttributeIconSize 0

enum Direccion {
  NORTE
  SUR
  ESTE
  OESTE
}

class JuegoLaberinto {
  {method} main()
  {method} crearLaberinto()
}

abstract class Sitio{
  {method} entrar()
}

class Sala{
  {field} numSala
  {method} getLado(Direccion): Sitio
  {method} setLado(Direccion, Sitio)
  {method} entrar()
}

class Puerta{
  {field} estaAbierta
  {method} otroLadoDesde(Sala)
  {method} entrar()
}

class Pared{
  {method} entrar()
}

class Laberinto{
  {method} agregarSala(Sala)
  {method} getSalaNum(int)
}

JuegoLaberinto .down.> Laberinto

Sala "lados" *-up-> Sitio
Sitio <|-left- Pared
Sitio <|-right- Puerta
Sitio <|-down- Sala
Laberinto "salas" *-right-> Sala

hide members
show methods
show Direccion members
show Sala members
show Puerta members

@enduml
```

</details>


```java
public interface Direccion {   // Cuando no existía "enumerate" en Java
    int NORTE = 0;             // se implementaban así :-)
    int ESTE = 1;
    int SUR = 2;
    int OESTE = 3;
}

public class Laberinto {
    Laberinto() {};
    void agregarSala(Sala sala) {};
    Sala getSalaNum(int numSala) { ... };
}
```

```java
public abstract class Sitio {  // Podría ser una interfaz
    boolean entrar() {};
}

public class Sala extends Sitio {
    private Sitio lados[];
    int numSala;

    Sala() {};
    Sala(int numSala) {};
    Sitio getLado(int dir) { return lados[dir]; };
    void setLado(int dir, Sitio sitio) {};
    boolean entrar() {};
}
```


```java
public class Pared extends Sitio {
    Pared() {};
    boolean entrar() {};
}

public class Puerta extends Sitio {
    private Sala sala1;
    private Sala sala2;
    boolean estaAbierta;

    Puerta(Sala sala1, Sala sala2) { ... };
    boolean entrar() {};
    Sala otroLadoDesde(Sala unaSala) { ... };
}
```


```java
Laberinto crearLaberinto () {
  Laberinto miLab = new Laberinto();
  Sala hab1 = new Sala(1);
  Sala hab2 = new Sala(2);
  Puerta unaPuerta = new Puerta(hab1, hab2);
  miLab.agregarSala(hab1);
  miLab.agregarSala(hab2);
  hab1.setLado(Direccion.NORTE, new Pared());
  hab1.setLado(Direccion.ESTE, unaPuerta);
  hab1.setLado(Direccion.SUR, new Pared());
  hab1.setLado(Direccion.OESTE, new Pared());
  hab2.setLado(Direccion.NORTE, new Pared());
  hab2.setLado(Direccion.ESTE, new Pared());
  hab2.setLado(Direccion.SUR, new Pared());
  hab2.setLado(Direccion.OESTE, unaPuerta);
  return miLab;
}
```


#### Críticas

- Creación poco flexible: instancias concretas cableadas.
- Supongamos $\exists$ SalaHechizada, PuertaHechizada. ¿Cómo cambiamos `crearLaberinto`?


#### Método de factoría

- El patrón _factory method_ define una interfaz para la creación de un objeto, pero dejando en manos de las subclases la decisión de qué clase concreta instanciar.

- Permite que una clase delegue en sus subclases las instanciaciones.


#### Factory method: Estructura

![](./img/guru/factory-method-structure-2x.png)


1. El **Producto** declara la interfaz, que es común a todos los objetos que puede producir la clase creadora y sus subclases.

2. Los **Productos Concretos** son distintas implementaciones de la interfaz de producto.

3. La clase **Creadora** declara el método fábrica que devuelve nuevos objetos de producto. Es importante que el tipo de retorno de este método coincida con la interfaz de producto.

4. Los **Creadores Concretos** sobrescriben el Factory Method base, de modo que devuelva un tipo diferente de producto.


<!--
![PlantUML diagram](https://kroki.io/plantuml/svg/eNp9lL2O2zAMx3c9BccWSIO7G1qguOGKTh0O7dDRi2wzkVCbMkQaboAOeYeu7cvlSUrJH5ezg24ixY8f_5T9xGKj9G1jJHQgAcogElqofcRKfCDDlW0QPtzdweBrcZP9Xm2H_ujEmKqxzPAthrqvZLI-R7QS4mwFqiIKrmIm7xxrpgN8hIOt9HB6RnGhfvP26sbS1w6jTWjJ_7rEjcwl9fHX5fy7DgO92zSesG6FzMQrGx63VYzzNUKLbYmRDbswqJEo2BgKgpAUDodFmu8O5zN4BgujKuKsQBVIrCc2okG-7RpsUT1pajhovG2aVEpvzdQj7a615Lu-sdqrGzl5Z_BnhZ3krFRs0mdC2xukGhLdxBjTSteUtmSJmrcRV7GHhJtw5lG4L_MgyKbtWV7o_9NrpWRWZuVbynCeekWyUw5fOdUxKzYON0qpgb3yncwoCfKizQbocv5zo3VaTkTuArEv9emrkkUK0G3QEQIhaEwbIqat5cSXDvBFCvIZWSOb0_WOnR39PygMDdZHjTgUlJ6NrjLXx3TPV-Uu57-gWXS_eY_7_bV3Jh-9y3CfdE1zLXi9nIISCttWnxsJRtVXzTCS6pVOPC51ErrnDLeU03tLKtMBY8LPz3ausytICbJvUShXW-Z5MPQA-81Hl52zYZ50W_qj-gevjrUL)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
top to bottom direction
scale 700 width
scale 600 height

class Product
class Creator
class ConcreteProduct
class ConcreteCreator

Creator : factoryMethod()
Creator : anOperation()
ConcreteCreator : factoryMethod()

Creator <|–down- ConcreteCreator
Product <|–down- ConcreteProduct
ConcreteProduct <- ConcreteCreator

hide members
show methods

note top of Creator
The Creator is a class that contains
the implementation for all of the
methods to manipulate products,
except for the factory method.
end note

note right of Creator
The abstract factoryMethod()
is what all Creator subclasses
must implement.
end note

note right of ConcreteCreator
The ConcreteCreator
implements the
factoryMethod(), which is
the method that actually
produces products.
end note

note “The ConcreteCreator is responsible for\ncreating one or more concrete products. It\nis the only class that has the knowledge of\nhow to create these products.” as n1
ConcreteProduct .. n1
ConcreteCreator .. n1

note “All products must implement\nthe same interface so that the\nclasses which use the products\ncan refer to the interface,\nnot the concrete class.” as n2
n2 . ConcreteProduct
n2 . Product

@enduml
```

</details>
-->

#### Factory method: Ventajas

- Se evita un acoplamiento fuerte entre el creador y los productos concretos.
- SRP: Se puede mover el código de creación de producto a un lugar del programa, haciendo que el código sea más fácil de mantener.
- OCP: Se pueden incorporar nuevos tipos de productos en el programa sin descomponer el código cliente existente.


#### Implementación de `JuegoLaberinto`

```java
public class JuegoLaberinto {
  JuegoLaberinto() {};
  // factory methods:
  Laberinto makeLaberinto() { return new Laberinto(); }
  Sala makeSala(int numSala) { return new Sala(numSala); }
  Pared makePared() { return new Pared(); }
  Puerta makePuerta(Sala sala1, Sala sala2) {
    return new Puerta(sala1, sala2);
  }
  Laberinto crearLaberinto () { ... }
}
```


```java
Laberinto crearLaberinto () {
  Laberinto miLab = makeLaberinto();
  Sala hab1 = makeSala(1);
  Sala hab2 = makeSala(2);
  Puerta unaPuerta = makePuerta(hab1, hab2);
  miLab.agregarSala(hab1);
  miLab.agregarSala(hab2);
  hab1.setLado(Direccion.NORTE, makePared());
  hab1.setLado(Direccion.ESTE, unaPuerta);
  hab1.setLado(Direccion.SUR, makePared());
  hab1.setLado(Direccion.OESTE, makePared());
  hab2.setLado(Direccion.NORTE, makePared());
  hab2.setLado(Direccion.ESTE, makePared());
  hab2.setLado(Direccion.SUR, makePared());
  hab2.setLado(Direccion.OESTE, unaPuerta);
  return miLab;
}
```


```java
public class JuegoLaberintoMinado extends JuegoLaberinto {
  Pared makePared() {
    return new ParedMinada();
  }
  Sala makeSala(int numSala) {
    return new SalaMinada(numSala);
  }
}

public class JuegoLaberintoHechizado extends JuegoLaberinto {
  Sala makeSala(int numSala) {
    return new SalaHechizada(numSala, lanzarHechizo());
  }
  Puerta makePuerta(Sala sala1, Sala sala2) {
    return new PuertaHechizada(sala1, sala2);
  }
  private Hechizo lanzarHechizo() { ... }
}
```


### [Strategy](https://refactoring.guru/es/design-patterns/strategy)

![Strategy, center](./img/guru/strategy-mini-2x.png)


#### Strategy: Estructura

![](./img/guru/strategy-structure-2x.png)


#### Strategy

- Define una familia de algoritmos, encapsula cada uno de ellos y los hace intercambiables
- Permite que el algoritmo varíe de forma independiente a quienes lo usan (el **Contexto**)

**Ventajas:**

- Ayuda a sacar factor común (factorizar) funcionalidades
- La estrategia es sustituible en tiempo de ejecución
- Alternativa a la herencia estática

**Desventajas:**

- Sobrecarga de la comunicación _Context_-_Strategy_


### [Command](https://refactoring.guru/es/design-patterns/command)

![Command, center](./img/guru/command-mini-2x.png)

<!--
![PlantUML diagram](https://kroki.io/plantuml/svg/eNqNVEGO2zAMvOsVPHaBbh6QBsEWPfVa9AOyTceCbdEr0XGDtn8vJUty4rRAbzYpDmeGlN48a8fzOKh60N7Dl8Gg5fTz1V6pR5dTNI7aNnA6GcvoWl3j-Zxy37BGc707amuHjKlEqYQER_DIKfrhRWXII-APrGfGh9hsG5JAxpaIrtmQjYceGuwA9rkEpFZx8Hre-G6hPefS9vT6lMtyYtkaKu78Ohye9VtihAFbBmqzx987TJ9gPDj0E1lvqgGhJTHSoWZjL6D3aBAQxcaYNbyZf1Ao2dAqNayImcbQsugNTYuy3tLioaMFmNQkIyU3AsuJhVwPFrHBJqRq7dwNaOaYdPg-o-cDfLY3iPOWAzbMBrQXuv9BZ-9PtGKnssHWWAyAlbFN0FohL4jSyaZFUNGNrSEEnDybUfexOtGFSsjqYRAgVXYlWBk17ZsHxUbKDUfZ1U2lWiCLQIJODhMLL7EI8k_hefD5Pt3T7GhovNJQb8MFzcrTiDCR3DQxtfcBX-UjTJHfTQVq9wILybAV24UYkTtqnlgxTessHhe4QZmpC9ZZKBc9rCQIeKbpZfqFj2yviWqCl47mS_dIAFYCH9XSmbpb9QTaaQtFTt69ULaa-sTWmUvHf9md43ETGnxNf6lnIhb9y6-Hf1FpYIVCWXVo56E1ojP4nYz9pNQ0V4Op4UqmuZP1U0GBOJSn6bfaqL_Jl7ytfwD_TeSY)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
class Client
class Invoker
class Command <<interface>>
class Receiver
class ConcreteCommand

Invoker : setCommand()
Command : execute()
Command : undo()
Receiver : action()
ConcreteCommand : execute()
ConcreteCommand : undo()

Client -> Receiver
Client -> ConcreteCommand
Receiver <- ConcreteCommand
Invoker -> Command
Command <|.. ConcreteCommand

note left of Client
The Client is responsible for
creating a ConcreteCommand and
setting its Receiver.
end note

note bottom of Receiver
The Receiver knows how to
perform the work needed to
carry out the request. Any class
can act as a Receiver.
end note

note bottom of ConcreteCommand
The ConcreteCommand defines a binding between an action
and a Receiver. The Invoker makes a request by calling
execute() and the ConcreteCommand carries it out by
calling one or more actions on the Receiver.
end note

note left of Invoker
The Invoker holds
a command and at
some point asks the
command to carry
out a request by
calling its execute()
method.
end note

note top of Command
Command declares an interface for all commands. A
command is invoked through its execute() method,
which asks a receiver to perform its action.
end note

note right of ConcreteCommand::execute()
The execute method invokes the action(s)
on the receiver needed to fulfill the
request;

public void execute() {
  receiver.action()
}

end note
@enduml
```

</details>
-->


#### Command: Estructura

![](./img/guru/command-structure-2x.png)


#### Command: Comportamiento

![PlantUML diagram](https://kroki.io/plantuml/svg/eNptkrGOwyAMhneewup0HRp16klRFVVql663dkLwq0EXSEWc5B7_aAIJOd0Ctvls_7a4dCw997YRnZIN6PN4pNForqN_Cn4N86xZiFcgjTIv6Zh20IZbX9K1MXC8I9nRHNpiyuqbl2NJ7_PaWiudnuD4sKUtXH9n2JLubmi_4Sc0RbessfKJkr6gYIZITjEhHodDRUng-aw8JKOqhFRshmAmpfNFbzrKoZIcxlzsx1Rzv7AVrSI7cKJi_n7tsajW-Ns3iyyUKIoiKl8b4AeqZ6Qm_1UXyaJ1iiUxz0gLT5MGPK5QBzcn5y1mIlOquMDp8Fd-AQLiwx0)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
scale 700 width
scale 600 height

participant "editor: Client" as editor
participant "cmdDraw: DrawCommand" as cmdDraw
participant "menuItem: Invoker" as menuItem
participant "image: Receiver" as image

[--> editor: <<create>>
activate editor
editor --> cmdDraw : new DrawCommand(image)
editor -> menuItem: setCommand(cmdDraw)
activate menuItem
deactivate editor
deactivate menuItem

...

[--> menuItem: executeCommand()
activate menuItem

menuItem -> cmdDraw: execute()
activate cmdDraw
cmdDraw -> image: draw()
activate image
deactivate cmdDraw

@enduml
```

</details>


#### Versión cliente/servidor

![PlantUML diagram](https://kroki.io/plantuml/svg/eNp9UkFuwyAQvPOKVU7OwVJOrWRFVaT0kmv7gi2sYhQbLLxx8vxCbTAoVk-IYXZ2ZpfTyOj43ndilNgRvB8O8NCK2-X-5u8t6WvLQgyeqaUe0DDITpPhAkJzMZO9kRPixz5h901uIrcrOWfb92hUCc7MEvsiSTqgZBR4PSFQsp6QKfaeD6g_kmoDhh5wtkY6YlrAar9WpvaKXrFcMEZpAJWq_iiBk0ultJnW4kykx9LdlfgTGTcdZTXHuk44QAP29q_fY72a8ezRD6yKpP3L1HKldWFZ9HkZXgilpIG3pOLCtpJH38HVTGuAniTvTBW3etzOHquCgbj54IC1NcW40rcQJ5_T_9tf89X2iA)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
scale 700 width
scale 600 height

participant client
participant anInvoker

box "Server"
participant aCommand
participant aServer
participant aReceiver
end box

activate client
client -> aCommand: new ConcreteCommand()
activate aCommand
deactivate aCommand

client -> anInvoker: add(aCommmand)
activate anInvoker
deactivate client

anInvoker -> aCommand: getData()
activate aCommand

anInvoker <-- aCommand  : ok
deactivate aCommand

client <- anInvoker : send(aCommand)
activate client
deactivate anInvoker

client -> aServer : accept(aCommand)
activate aServer
deactivate client

aCommand <- aServer: execute(this)
activate aCommand

aCommand -> aReceiver: action()
activate aReceiver

@enduml
```

</details>


### [Adapter](https://refactoring.guru/es/design-patterns/adapter)

![Adapter, center](./img/guru/adapter-mini-2x.png)

<!--
![PlantUML diagram](https://kroki.io/plantuml/svg/eNptUTtuwzAM3XUKju0QA1kDw0jQGxQeuygSnQjVxzUZBAE65CDt5XKS0rXkpEY3kXx8H2pLrAc-Ba-M10Tw4h1GzkWrhwMy1LWLjEOnDTZNHu2s7qX3p0KVFzYw4McJiZ-eVQb-00PpUY_Gdc68zjM1OYBVk-ULaf1ZVbNsYRVU0Y6JEVIE7-L7PHcEJoU-EVp1dnwEPmIhqRRGC-Oampb3iTkFSF25QitgM9khRBJ2fxkZiqX5LEum2_WrvQuBC73HIDT0FkcDy_Xqdv0GTRDXhVmiShHX8JB5abIE33lfbkswHsyix4NmtMDp125GPgTeyks-_QeIqbFT)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
class Client
class Target <<interface>>
class Adapter
class Adaptee
Target : request()
Adapter : request()
Adaptee : specificRequest()

Client -> Target
Target <|.. Adapter
Adapter -> Adaptee
note on link
Adapter is composed
with the Adapter.
end note

note bottom of Client
The client sees only the
Target interface
end note

note “The Adapter implements\nthe Target interface.” as n1
Target .. n1
n1 .. Adapter

note bottom of Adaptee
All requests get
delegated to the
Adaptee.
end note
@enduml
```

</details>
-->


#### Adaptador de objetos: Estructura

![](./img/guru/object-adapter-structure-2x.png)


#### Adaptador de clases: Estructura

![](./img/guru/class-adapter-structure-2x.png)


#### Adaptador de clases vs. objetos

**Class adapter:**

- No sirve para adaptar una clase y sus subclases
- Se crea un único objeto, sin indirecciones adicionales

**Object adapter:**

- Un adapter puede funcionar con varios objetos _Service_ o _Adaptee_
- Es más complicado heredar el comportamiento del objeto adaptado


### [Composite](https://refactoring.guru/es/design-patterns/composite)

![Composite, center](./img/guru/composite-mini-2x.png)

<!--
![PlantUML diagram](https://kroki.io/plantuml/svg/eNqVVcFu2zAMvesrdEyGNNiuRTF06GnAsFOBnRWbjrTKoiHK3QrskH_YacD2c_mSkbItO0lbbKeYIqn3yPeE3FIyMfWtV5U3RPrOOwhpCrDtMMzxJzDNMkUugVKlSl9r7CCa5DCs1ifnpq5XJT5NRWjxEV7K7iHdWefrlZOMEgZnMKpQeQ5_PH8Of0y9gD9mz_CH9eir94vdzHRvfhwPP4ctnR_O-5ozx8Pvt9vtm-Phj77hGpSDdxItlhuQaSTsNDaTNvcWxk_dE5BOdnkp84TYmEq6VGuC63pv5A7uwt1XqBJxTW6qRhhe11ZBqLWAnUGWKTNqQamhcYGxTVAzYINRG-8XMEpQFzDXeofJnoAD31Fndl60DVgD_S-b1jxp13YeWomMYnam90nvwJpHx6wyM7bAejPJvd6oIu06U3DMuNjnkgITT9gKi6zvh_yjrSEuUpXcE-FyjR6aVHo-51GsSXnc3G88IW_QQhT4FpLFmrR3DzDyVRPfzHGmvNHfrKusqlHgdIAKiEx0_om3Id3aoyArgkCjNPOGt_oLl0RQe3Rhz-sVkXhGUz1IkKxj9Yh6-Mc1THaQuU6WLkrDoMtku6UftvojewmBVIbcLWTMvMSzRZJspvltUt91GNNrSs3v6H7Zejz8Ih3RA88o4w7sVSHOnYWn-IuUJJjPJPNgWdSUMMJwqubqC0LR7W16hc_ogmlyKva4YvXl8dYnxpxtRCIaa8z1rLG8AvGC6D_Kjkx1xtkowkEE7q0MydNT8L2CTm7Wbaa5Y1NAEDSoF5Pc8hf_S_wFVPsy7Q)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
class Client
class Component
class Leaf
class Composite

Component : operation()
Component : add(Component)
Component : remove(Component)
Component : getChild(int)

Leaf : operation()

Composite : operation()
Composite : add(Component)
Composite : remove(Component)
Composite : getChild(int)

Client -> Component
Component <|– Leaf
Component <|– Composite
Component “0..*” <–o “1” Composite

note top of Client
The Client uses the
Component interface to
manipulate the objects in the
composition.
end note

note top of Component
The Component defines an
interface for all objects in
the composition: both the
composite and the leaf nodes.
end note

note top of Component
The Component may implement a
default behavior for add(), remove(),
getChild() and its operations.
end note

note bottom of Leaf
A Leaf has no
children.
end note

note left of Leaf
Note that the Leaf also
inherits methods like add(),
remove() and getChild(), which
do not necessarily make a lot of
sense for a leaf node. We are
going to come back to this issue.
end note

note bottom of Leaf
A Leaf defines the behavior for the
elements in the composition. It does
this by implementing the operations
the Composite supports.
end note

note bottom of Composite
The Composite’s role is to define
behavior of the components
having children and to store child
components.
end note

note right of Composite
The Composite also
implements the Leaf-
related operations.
Note that some of
these may not make
sense on a Composite,
so in that case an
exception might be
generated.
end note
@enduml
```

</details>
-->


#### Composite: Estructura

![](./img/guru/composite-structure-2x.png)


#### Composite

- Permite construir objetos complejos componiendo de forma recursiva objetos similares en una estructura de **árbol**.
- Permite manipular **uniformemente** todos los objetos contenidos en el árbol, ya que todos ellos poseen una interfaz común definida en la clase raíz.

**Ventajas:**

- El cliente trata a todos los objetos de la misma forma
- La inclusión de nuevos tipos de hojas o compuestos no afecta a la estructura anterior

**Desventajas:**

- Si se desea restringir el tipo de objetos que pueden formar parte de otros $\Rightarrow$ Necesidad de comprobaciones dinámicas


### [Decorator](https://refactoring.guru/es/design-patterns/decorator)

![Decorator, center](./img/guru/decorator-mini-2x.png)

<!--
![PlantUML diagram](https://kroki.io/plantuml/svg/eNqlVctunEAQvM9X9NGWrLXkYxJFXhIfcohycH6ggWYZG2bQTHsJUg75h_xhviQ9wPJaWMfKbemu6a6pKpZ7z-j4pSyUf9amQoclJLasrCHDj9wUBNK8Uyop0Hv4dGoNzyZxxLSsf6bEOmTrFrihvt9qREoNw-AdlMS5TfdX1yvVaFG9vQXLObmvbddLV53xW4zc6kYb3ZUVA_PZ6PNqtKheYDuqJLhxfe2wqij9Fj9Rwuvg87uttKOttqE6ohyP2rotyL-wjt7COoC7Xlj_yMi0Drpwtejy1aIt3ieKH37--fV7JctLxJjq0cj52Um-X4VEE4gAPsI0aMM7qIxlAqcPOYM1UGjzrB4wyUcEJGgglvfUUxogmr2ytbkBmduLD3EDCOn4Ug7TyaQQNqhuT2yZbQk2W1Hje06hOi9qDyIr2M7BmgAdwcFqc1BsIW0MljrBopD9aRocVnGfMJC-5h18YaAfLDz8qPdum9eKBx50WRVUBi2EjPJYEmjD5DJMKMiAsWeHyenfCTvSo4Ly1KiBeWDWa0W71xVaM9S3pnT36jiFYIdTs8X_M7zXsw--f69yW9OR3E1bHVQOBjVV54GSM10Y0vaegceLENPWiC2ZdZ1YmSgHLX3tOeC6FUqbOfu3aLM_xWdehly8QCOjRSAjZh3RaYzlq5OFhMgRzkOWNA-OeLjidtTEvMmbFBLvryfU7uWXfML-ApQXe0A)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
skinparam componentStyle uml2

class Component
class ConcreteComponent
class Decorator
class ConcreteDecoratorA
class ConcreteDecoratorB

Component : methodA()
Component : methodB()
Component : // otherMethods()

ConcreteComponent : methodA()
ConcreteComponent : methodB()
ConcreteComponent : // otherMethods()

Decorator : methodA()
Decorator : methodB()
Decorator : // otherMethods()

ConcreteDecoratorA : Component wrappedObject
ConcreteDecoratorA : methodA()
ConcreteDecoratorA : methodB()
ConcreteDecoratorA : newBehavior()
ConcreteDecoratorA : // otherMethods()

ConcreteDecoratorB : Component wrappedObject
ConcreteDecoratorB : Object newState
ConcreteDecoratorB : methodA()
ConcreteDecoratorB : methodB()
ConcreteDecoratorB : // otherMethods()

Component <|– ConcreteComponent
Component <|– Decorator
Decorator <|– ConcreteDecoratorA
Decorator <|– ConcreteDecoratorB
Decorator –> Component : component
note right on link
Each component can be used on its
own, or wrapped by a decorator
component
end note

note bottom of ConcreteComponent
The ConreteComponent
is the object we are going
to dynamically add new
behavior to it. It extends
Component.
end note

note bottom of Decorator
Decorators implement the
same interface or abstract
class as the component they
are going to decorate.
end note

note bottom of ConcreteDecoratorB
Decorators can extend the
state of the component
end note

note bottom of ConcreteDecoratorB
Decorators can add new methods;
however, new behavior is typically
added by doing computation
before or after an existing method
in the component.
end note

note bottom of ConcreteDecoratorA
The ConcreteDecorator has an
instance variable for the thing
it decorates (the Component the
Decorator wraps).
end note
@enduml
```

</details>
-->


#### Decorator: Estructura

![](./img/guru/decorator-structure-2x.png)


#### Ejemplo: `EnhancedWriter` original

![PlantUML diagram](https://kroki.io/plantuml/svg/eNqVkTFLBEEMhfv8ipRaHKyiVhYnYiGIjYL17Ey4CbeZLJMsi573391lLeTYwmuT9-W9R7bmofogHbj26Iqtuqtg4krRWQtYDB3hVXN9gyMnz7-Du9sGM_EuO9ieSx9qEOy4kH_2hFo9659F7ILZg3vldnB6jlre-IuwAQiteQ3RFwk-lRxKpPRR2aniARAPQtOxdMRxnr1MFhezzyUcARbodZCWKpfdWdQ7C03tpT8XfMwU9zaI_B88qXX_vdmcpl7VrGRc1a1EAsicCIVmFwPLOuKS0AC2VNL09B-FvrZ9)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
top to bottom direction
scale 1024 width
scale 650 height
skinparam linetype ortho
skinparam classAttributeIconSize 0

abstract class EnhancedWriter {
  {method} writeLine(line)
}

class NumberingWriter {
  {method} writeLine(line)
}

class TimestampingWriter {
  {method} writeLine(line)
}

class ChecksummingWriter {
  {method} writeLine(line)
}

EnhancedWriter <|-- NumberingWriter
EnhancedWriter <|-- TimestampingWriter
EnhancedWriter <|-- ChecksummingWriter

hide members
show methods

@enduml
```

</details>


#### Ejemplo: `EnhancedWriter` ampliado – herencia fuera de control

![PlantUML diagram](https://kroki.io/plantuml/svg/eNqVk7FOwzAQhvd7ihthaBUqYOpQhBiQKhaQmB37VJ8a25F9UQRt3x1HYaDBitr1fN_939nyJomK0rkGJLQoAesgEhwajqSFg4ekVUN4V63usWcj9rfw-FChJd5ZgbRn36qoHDbsSb5awhDFhj8HulEpPYlErjuhVx38O38TVgCqThKVlrEFX7xVXpP5jCwU8QCIB0d5mDlhP9S2OeJmyLmFE8AIvXWupsh-dxX1wY7y9q69Fny2pPepc-5ycLLW-rhYTK2LPQXHYl9BCWB6LeepGTknoDBkfVwu55CC3z_vqUbBK4fMEkWzydqXxcwSAJYNoaOhniDZ0OP4qAlgQ97kf_IDmbApTQ)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
top to bottom direction
scale 1024 width
scale 650 height
skinparam linetype ortho
skinparam classAttributeIconSize 0

abstract class EnhancedWriter {
  {method} writeLine(line)
}

class NumberingWriter {
  {method} writeLine(line)
}

class TimestampingWriter {
  {method} writeLine(line)
}

class ChecksummingWriter {
  {method} writeLine(line)
}

EnhancedWriter <|-- NumberingWriter
EnhancedWriter <|-- TimestampingWriter
EnhancedWriter <|-- ChecksummingWriter

NumberingWriter <|-- NumberingCheksummingWriter
ChecksummingWriter <|.. NumberingCheksummingWriter
TimestampingWriter <|-- TimestampingNumberingWriter
NumberingWriter <|.. TimestampingNumberingWriter
ChecksummingWriter <|-- ChecksummingNumberingWriter
NumberingWriter <|.. ChecksummingNumberingWriter


hide members
show methods

@enduml
```

</details>


#### Ejemplo: `EnhancedWriter` ampliado –  herencia fuera de control

![PlantUML diagram](https://kroki.io/plantuml/svg/eNq9lD1PwzAQhvf7FTfC0CogYOpQVDEgIRaQmB37VFuN7ci-KIK2_51EYYDEtZKF9XyP3w_J3kYWgRtbAfsa2WPpmb1FZQJJNt5BlKIivClu77A1ivXP4OG-QE1mrxniwbhaBGGxMo74syb0gbX_dSArEeMjczBlw_QsvXszX4QFgCgjByF5WMEnp4WTpD6CYQp4BMSjpe4ydca2n710Ele9zjWcAQbotbElBeP2i6h3Y6lLb-ul4E6TPMTG2vngKNbmtFqNXSd3Eh6TewlLAONa_qpOEUjcsjmt11km4XDifGwk4axTyRJJa6Pg82SyBGSiZvqblpDu5VKXCf6_ioVcH5crXpB4Lg-gjSK01K9EiNq3ODypCLAlp7pf6hsU3rsQ)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
top to bottom direction
scale 1024 width
scale 650 height
skinparam linetype ortho
skinparam classAttributeIconSize 0

abstract class EnhancedWriter {
  {method} writeLine(line)
}

class NumberingWriter {
  {method} writeLine(line)
}

class TimestampingWriter {
  {method} writeLine(line)
}

class ChecksummingWriter {
  {method} writeLine(line)
}

EnhancedWriter <|-- NumberingWriter
EnhancedWriter <|-- TimestampingWriter
EnhancedWriter <|-- ChecksummingWriter

NumberingWriter <|-- NumberingChecksummingWriter
ChecksummingWriter <|.. NumberingChecksummingWriter
TimestampingWriter <|-- TimestampingNumberingWriter
NumberingWriter <|.. TimestampingNumberingWriter
ChecksummingWriter <|-- ChecksummingNumberingWriter
NumberingWriter <|.. ChecksummingNumberingWriter

NumberingChecksummingWriter <|-- NumberingChecksummingTimestampingWriter
TimestampingWriter <|.. NumberingChecksummingTimestampingWriter

TimestampingWriter <|-- TimestampingNumberingWriter
NumberingWriter <|.. TimestampingNumberingWriter

ChecksummingNumberingWriter <|-- ChecksummingNumberingTimestampingWriter
TimestampingWriter <|.. ChecksummingNumberingTimestampingWriter


hide members
show methods

@enduml
```

</details>


#### Decorator

- El patrón decorator permite añadir responsabilidades a objetos concretos de forma **dinámica**.
- Los decoradores ofrecen una **alternativa** más flexible que la herencia para extender funcionalidades.

**Ventajas:**
- Permite añadir o quitar responsabilidades a los objetos sin afectar a otros objetos

**Desventajas:**
- Rompe la identidad de objetos: un componente y su decorador no son el mismo objeto
- Provoca la creación de muchos objetos pequeños y complica la depuración


#### ¿Diferencia entre Strategy y Decorator?


#### Diferencia entre Strategy y Decorator

El _decorator_ cambia la piel, el _strategy_ cambia las tripas


### [Observer](https://refactoring.guru/es/design-patterns/observer)

![Observer, center](./img/guru/observer-mini-2x.png)


<!--
![PlantUML diagram](https://kroki.io/plantuml/svg/eNqNVEGO2zAMvOsVPHaBbh6QBsEWPfVa9AOyTceCbdEr0XGDtn8vJUty4rRAbzYpDmeGlN48a8fzOKh60N7Dl8Gg5fTz1V6pR5dTNI7aNnA6GcvoWl3j-Zxy37BGc707amuHjKlEqYQER_DIKfrhRWXII-APrGfGh9hsG5JAxpaIrtmQjYceGuwA9rkEpFZx8Hre-G6hPefS9vT6lMtyYtkaKu78Ohye9VtihAFbBmqzx987TJ9gPDj0E1lvqgGhJTHSoWZjL6D3aBAQxcaYNbyZf1Ao2dAqNayImcbQsugNTYuy3tLioaMFmNQkIyU3AsuJhVwPFrHBJqRq7dwNaOaYdPg-o-cDfLY3iPOWAzbMBrQXuv9BZ-9PtGKnssHWWAyAlbFN0FohL4jSyaZFUNGNrSEEnDybUfexOtGFSsjqYRAgVXYlWBk17ZsHxUbKDUfZ1U2lWiCLQIJODhMLL7EI8k_hefD5Pt3T7GhovNJQb8MFzcrTiDCR3DQxtfcBX-UjTJHfTQVq9wILybAV24UYkTtqnlgxTessHhe4QZmpC9ZZKBc9rCQIeKbpZfqFj2yviWqCl47mS_dIAFYCH9XSmbpb9QTaaQtFTt69ULaa-sTWmUvHf9md43ETGnxNf6lnIhb9y6-Hf1FpYIVCWXVo56E1ojP4nYz9pNQ0V4Op4UqmuZP1UxWEQ3mZfquN-Zt8ydP6ByLe5Hg)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
class Client
class Invoker
class Command <<interface>>
class Receiver
class ConcreteCommand

Invoker : setCommand()
Command : execute()
Command : undo()
Receiver : action()
ConcreteCommand : execute()
ConcreteCommand : undo()

Client -> Receiver
Client -> ConcreteCommand
Receiver <- ConcreteCommand
Invoker -> Command
Command <|.. ConcreteCommand

note left of Client
The Client is responsible for
creating a ConcreteCommand and
setting its Receiver.
end note

note bottom of Receiver
The Receiver knows how to
perform the work needed to
carry out the request. Any class
can act as a Receiver.
end note

note bottom of ConcreteCommand
The ConcreteCommand defines a binding between an action
and a Receiver. The Invoker makes a request by calling
execute() and the ConcreteCommand carries it out by
calling one or more actions on the Receiver.
end note

note left of Invoker
The Invoker holds
a command and at
some point asks the
command to carry
out a request by
calling its execute()
method.
end note

note top of Command
Command declares an interface for all commands. A
command is invoked through its execute() method,
which asks a receiver to perform its action.
end note

note right of ConcreteCommand::execute()
The execute method invokes the action(s)
on the receiver needed to fulfill the
request;

public void execute() {
 receiver.action()
}

end note
@enduml
```

</details>
-->

#### Observer

- Define una dependencia 1:N entre objetos de modo que cuando el estado de un objeto cambia, se les notifica el cambio a todos los que de él dependen y estos se actualizan de forma automática.


#### Observer: Estructura

![](./img/guru/observer-structure-2x.png)


#### Observer: roles

Sin distinguir entre `Observable` y `Subject`:
- `Publisher` = `Observable` = `Subject`
- `ConcreteSubscriber` $\dashrightarrow$ `Subject`

Con `Subject` separado:
- `Subscriber` = `Observer`
- Distinguir entre `Observable` y `Subject`
- Definir `Subscriber.update(Observable, Subject)`
  - `ConcreteSubscriber` $\dashrightarrow$ `Observable`
  - `ConcreteSubscriber` $\dashrightarrow$ `Subject`


#### Observer: Detalles de implementación

- ¿Quién dispara la actualización?
    - El publicador, tras cambiar de estado: menos eficiente si hay muchas notificaciones
    - El cliente, tras una serie de cambios de estado: si se olvida puede provocar inconsistencias


#### Oberver: Comportamiento (síncrono) – disparo externo

![PlantUML diagram](https://kroki.io/plantuml/svg/eNqVkkGOwjAMRfc-hcUKFlRdDJsIMdwAJE6QtIZYKp0qcUFz-8kgEHVokVgm8ft-trKNYoP05wZiZRvCr1WJV67F38-rskRPfPIC0KVKrrizreDMtjsXKVysa8jgvncNR09hhjbi8G2comDw0LtYBXYZRCFHfiQFv-HUO8AzCJcb5YIG4wOei-e4AFsJX6zQsH1NY7eQNfosXDuqDpl-URQA31m4wVTGx9_5iPFty6BUBnQSNdh3dSLejKzo9VLTJ5KD_POL6dXkzfWqpg308K8aec6US7bELbV1-tR_J4wHwA)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
scale 450 width
scale 500 height

participant "anObservable: Publisher" as anObservable
participant "anObserver: Subscriber" as anObserver
participant "anotherObserver: Subscriber" as anotherObserver

anObserver -> anObservable : subscribe(this)
activate anObserver
deactivate anObserver

anotherObserver -> anObservable : subscribe(this)
activate anotherObserver
deactivate anotherObserver

...

?-> anObservable: notify()
activate anObservable

anObservable -> anObserver : update(this)
activate anObserver
anObservable <- anObserver : getState()
deactivate anObserver

anObservable -> anotherObserver : update(this)
activate anotherObserver
anObservable <- anotherObserver : getState()
deactivate anotherObserver

@enduml
```

</details>


#### Oberver: Comportamiento (síncrono) – autodisparo

![PlantUML diagram](https://kroki.io/plantuml/svg/eNqVk0FuAjEMRfc-hcWKLhjNAjZRRbkBlThBMuMSS8N0lHiouD0BtQKnSaUuE_v9__Ol7KLYIPNpgNjZgXC9afGLe_Hf503boic-egGY0iZ3PNlRcGHHvYsUztYNZPB9dgNHT2GBNuLzrExRMHiYXewCuwyikCOfkoT_4NQc4CGEq63KggbjD7wUz_EFbCd8tkLP9j2VbiEz-p-4zqgcsvhN0wC8FcRJDpKIZSH0vWi1n_EGkw1_XBJc3UtPMjhP_c2jXo6iX1eaPj4yVkvMzXWp9QS6pt8xcp1alqzuQs57m7CjsU__4gqXLx9X)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
scale 450 width
scale 500 height

participant "anObservable: Publisher" as anObservable
participant "anObserver: Subscriber" as anObserver
participant "anotherObserver: Subscriber" as anotherObserver

anObserver -> anObservable : subscribe(this)
activate anObserver
deactivate anObserver

anotherObserver -> anObservable : subscribe(this)
activate anotherObserver
deactivate anotherObserver

...

?-> anObservable : setState()
activate anObservable
anObservable -> anObservable: notify()

anObservable -> anObserver : update(this)
activate anObserver
anObservable <- anObserver : getState()
deactivate anObserver

anObservable -> anotherObserver : update(this)
activate anotherObserver
anObservable <- anotherObserver : getState()
deactivate anotherObserver

deactivate anObservable

@enduml
```

</details>


#### Observer: Detalles de implementación

- Los suscriptores necesitan información para hacer la actualización:
  - `update(context)` para pasar la información necesaria al suscriptor
  - `update(this)` para que el suscriptor extraiga la información necesaria pidiéndosela al publicador
  - `ConcreteSubscriber.setPublisher()` para vincularlos permanentemente (opción menos flexible)


### [Visitor](https://refactoring.guru/es/design-patterns/visitor)

![Visitor, center](./img/guru/visitor-mini-2x.png)


#### Visitor

- Representa una **operación** que se lleva a cabo sobre los elementos de una **estructura** de objetos
- Permite **definir nuevas** operaciones sin modificar las clases de los **elementos** sobre las que opera.


#### Visitor: Estructura

![](./img/guru/visitor-structure-2x.png)


#### Visitor: Comportamiento

![PlantUML diagram](https://kroki.io/plantuml/svg/eNqNk0FPwzAMhe_-FdZO2WFSkbZLhWAz2pkDEveQWjSoS6vEHX-fDkjWsqzslsT-nv2elG0Q7aU_NBCMbhjXmwI_bSX1731TFFizfa8FoBs6rbGddoLaPb99sJEX8b2R3vOkutBu3_CBnexKjKcF6oDnwhWAEkBTgP4ArzZYaf1diU-tM56F48sPF28Aj6uHy32xxLZjr8W2Ti1BG7FHLZwxBpfst2A0MihpY7gTlWZO9JLhEXISiM0DfzwdY1FJbcMSKs5qpLezv5Hu_WqimxzuVH6l8ZAkmJ_8Twx0YwwEI2QmBpqJgeZjoKsxkMqvdHMMBLBlVw3_5QtHUSOE)

<details>
<summary>PlantUML source</summary>

```plantuml
@startuml
scale 450 width
scale 500 height

participant anObjectStructure
participant "anElementA: ElementA" as anElementA
participant "anElementB: ElementB" as anElementB
participant "aVisitor1: ConcreteVisitor1" as aVisitor1

?-> anObjectStructure : operation()
activate anObjectStructure

anObjectStructure -> anElementA : accept(aVisitor1)
activate anElementA
anElementA -> aVisitor1 : visitElementA(this)
deactivate anElementA
activate aVisitor1

anElementA <- aVisitor1 : operationA()
activate anElementA
deactivate aVisitor1
deactivate anElementA

anObjectStructure -> anElementB : accept(aVisitor1)
activate anElementB
anElementB -> aVisitor1 : visitElementB(this)
deactivate anElementB
activate aVisitor1

anElementB <- aVisitor1 : operationB()
activate anElementB
deactivate aVisitor1
deactivate anElementB

@enduml
```

</details>


**Ventajas:**
- Permite implementar el _double dispatch_: la operación que se ejecuta tras el `accept()` depende del tipo de `Visitor` y del tipo de `Element`
- Separa los datos y las operaciones de los elementos visitados, facilitando la inclusión de nuevas operaciones sin tener que cambiar las clases
- Permite acumular el estado de una operación global sobre una estructura

**Desventajas:**
- Rompe la encapsulación (?)
- Los tipos de `Element` visitados deben ser estables


### [State](https://refactoring.guru/design-patterns/state)

![State, center](./img/guru/state-mini-2x.png)


#### State: Ejemplo

![](./img/guru/state-example.png)


```scala
class Document {
  var state: String = _
  var expirationDate: Date = _
  // ...

  def publish(currentUser: User): Unit = state match {
    case "draft" =>
      if (currentUser.role == "admin") {
        state = "published"
      } else {
        state = "moderation"
      }
    case "moderation" =>
      if (currentUser.role == "admin") {
        state = "published"
      }
    case "published" =>
      if (new Date().after(expirationDate)) {
        state = "draft"
      }
    case _ =>
      // Handle any unexpected state.
      throw new IllegalStateException(s"Invalid document state: $state")
  }
}
```


#### State: Estructura

![](./img/guru/state-structure.png)


#### Diferencia con Strategy

- Cada estado puede ser consciente de la existencia de otros estados e iniciar transiciones de estado
- Cada estrategia desconoce a las otras

## Otros patrones específicos


### Data Acess Object (DAO)

- Se usa para abstraer y encapsular los accesos a las fuentes de datos, con independencia del soporte concreto de almacenamiento. Su alternativa es el patrón *Active Record*.

![](./img/dao_uml.webp)

<!--
-
![](./img/dao_code.png)
-->

### Data Transfer Object (DTO)

- Se usa para crear objetos planos (POJO) que puedan ser enviados o recuperados desde servidores remotos en una única invocación. Un DTO no tiene más comportamiento que almacenar y entregar sus propios datos (métodos *getters* y *setters*).

![](./img/dto_uml.png)

<!--
-
![](./img/dto_code.png)
-->


# Para profundizar sobre patrones
- Martin Fowler – [Patterns in Enterprise Software](https://martinfowler.com/articles/enterprisePatterns.html): Catálogos de patrones a distintos niveles
    - Martin Fowler – [Patterns of Enterprise Application Architecture (EAA)](https://martinfowler.com/eaaCatalog/)
    - Hohpe y Woolf – [Enterprise Integration Patterns (EIP)](http://www.enterpriseintegrationpatterns.com/)
    - Buschmann y otros – [Pattern-Oriented Software Architecture (POSA)](http://www.amazon.com/exec/obidos/ASIN/0471958697) Volume 1: A system of patterns
- Peter Norvig – [Design Patterns in Dynamic Programming](http://www.norvig.com/design-patterns/design-patterns.pdf): Implementaciones más simples para los patrones de diseño del GoF en lenguajes dinámicos

# Para profundizar sobre patrones

- David Arno – [Are design patterns compatible with modern software techniques?](http://www.davidarno.org/2013/06/17/are-design-patterns-compatible-with-modern-software-techniques/)
- Implementaciones de los patrones de diseño del GoF en diversos lenguajes de programación:
    - Kamran Ahmed – [Design Patterns for Humans!](https://github.com/kamranahmedse/design-patterns-for-humans/blob/master/README.md): Explicación de los patrones de diseño del GoF implementados en PHP
    - Márk Török – [Design Patterns in TypeScript](https://github.com/torokmark/design_patterns_in_typescript)
    - Bogdab Vliv - [Design Patterns in Ruby](https://bogdanvlviv.com/posts/ruby/patterns/design-patterns-in-ruby.html)
- Lewis y Fowler – [Microservicios](https://martinfowler.com/articles/microservices.html)
- Chris Richardson - [Microservices patterns](https://microservices.io/)
