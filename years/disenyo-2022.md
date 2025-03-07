---
marp: false
title: Apuntes DSS 2022
description: Apuntes de Diseño de Sistemas Software, curso 2021/22
---

<!-- size: 16:9 -->
<!-- theme: default -->

<style>
h1 {
  text-align: center;
}
h2 {
  color: darkblue;
  text-align: center;
}
</style>

# DISEÑO DE SISTEMAS SOFTWARE

<style scoped>
h2 {
  text-align: left;
}
</style>


---

<!-- paginate: false -->

## INTRODUCCIÓN

---

<!-- paginate: true -->

### Problemáticas

- Variabilidad
- Acoplamiento
- Complejidad
- Robustez
- Reutilización
- Flexibilidad

### Principios

- Ocultación: OCP, ISP, LSP
- Cohesión: SRP
- Ortogonalidad: ISP
- Delegación

---

### Técnicas

- [Refactoring](https://codegym.cc/groups/posts/196-how-refactoring-works-in-java)
- Bibliotecas y frameworks
- [Contratos](https://blog.frankel.ch/programming-by-contract-jvm/)
- [Inyección de dependencias](https://www.journaldev.com/2394/java-dependency-injection-design-pattern-example-tutorial)
- Patrones

---

### Paradigmas

- Estructurado (E. W. Dijsktra)
- Objetos (O. J. Dahl & K. Nygaard)
- Funcional (J. McCarthy)
- Aspectos (G. Kiczales)
- Eventos


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
_¿Cuál es el más antiguo?_
_¿Ejemplo de lenguaje de programación de cada paradigma?_
_¿Características más relevante de cada uno?_

---

#### Respuesta (I)

_¿De qué fecha data cada paradigma/ejemplo de lenguaje?_

- Estructurado (E. W. Dijsktra, 1968 / Pascal)
- Objetos (O. J. Dahl & K. Nygaard, 1966 / Java)
- Funcional (J. McCarthy, 1958 / Lisp)
- Aspectos (G. Kiczales, 1997 / AspectJ)
- Eventos (VisualBasic)

---



#### Respuesta (II)

_¿Características más relevante de cada uno?_

- [Estructurado:](https://safeswisscloud.com/wp-content/uploads/2019/01/LagrangePAS.jpg) Uso de estructuras básicas (secuencias, selección e iteración) y subrutinas
- Objetos: Modelado de entidades como clases (atributos +  métodos)
- [Funcional](http://www.lee-mac.com/lisp/gifs/style1.png): Programación declarativa con funciones (lambdas)
- [Aspectos](https://www.researchgate.net/profile/Erika-Madariaga/publication/327401137/figure/fig4/AS:666769157017602@1535981563702/AspectJ-HelloWorld-example.png): Modularización de aplicaciones, separación de responsabilidades (logging)
- [Eventos](https://upload.wikimedia.org/wikipedia/commons/c/cb/Event_driven_programming_Simply_Explained.jpg): Estructura y ejecución del programa determinados por sucesos (eventos) ocurridos en el sistema

<!--http://www.rosettacode.org/wiki/Fibonacci_sequence -->

<!--http://jarble.github.io/transpiler/ -->

---


### Bloques

I. Principios de diseño OO
II. Patrones de diseño
III. Arquitectura de software

---

<!-- paginate: false -->

# PRINCIPIOS DE DISEÑO <br> ORIENTADO A OBJETOS

---

<!-- paginate: true -->

## Casos prácticos

Caso 1. Identificadores
Caso 2. Framework de pruebas unitarias
Caso 3. Caballeros de la mesa redonda
Caso 4. Figuras geométricas
<!--Caso 4. [Guitarras Paco](#guitarras)-->

---

<style scoped>
li {
  color: blue;
}
</style>

## Conceptos teóricos

- Bibliotecas y frameworks
- Inyección de dependencias
- Reutilización
- Principios SOLID

---

<!-- paginate: false -->

# CASO PRÁCTICO 1: <br> Identificadores

---

<!-- paginate: true -->

## Identificadores

<style scoped>
p {
  color: red;
  text-align: center;
}
h2, h3 {
  text-align: center;
}
</style>

### Pregunta

¿Cómo diseñar la identificación de los empleados de una empresa? (¡incluyendo métodos para comparar dichos idenificadores!)

---

### Versión inicial: Identificadores v0.1

```java
  class Empleado {
    private int dni;
    
    Empleado (String dni) throws NumberFormatException {
      // this.id = new Integer(id).intValue(); // Integer constructor
                                               // deprecated since Java 9 
      this.dni = Integer.valueOf(dni);
    }
    int getDni() {    // obsérvese que no tiene modif. de acceso
      return dni;
    }
    public String toString() {
      return new Integer(dni).toString();
    }
    public int compareTo(Empleado otro) {
       return this.dni - otro.getDni();
    }
    public boolean equals(Empleado otro) {
       return dni==otro.getDni();
    }
  }
```

---

#### Críticas:

- __Flexibilidad__: El Real Decreto 338/1990 regula el uso de NIFs en lugar de DNIs. ¡Hay que cambiar toda la implementación!

### Implementación alternativa: Identificadores v0.2

```java
  class Empleado {
    private String nif;
    
    Empleado (String nif) {
      this.nif = nif
    }
    String getNif() { return nif; }
    public String toString() { return nif; }
    public int compareTo(Empleado otro) {
       return nif.compareTo(otro.getNif());
    }
    public boolean equals(Empleado otro) {
       return nif.equals(otro.getId());
    }
  }
```

---

#### Críticas:

- __Reutilización__: Posiblemente haya más situaciones (algunas futuras) donde hagan falta _identificadores_ que incluso pueden cambiar. Por ejemplo: números de la seguridad social, tarjetas de identidad, números de cuenta corriente, IBAN, etc.

### Hacemos refactoring: patrón *handler*

- Manejo de *identificadores* de forma independiente de la implementación del objeto identificado.

- Cambio fácil de implementación de los identificadores (`int`, `String`, etc.) hacia cualquier tipo básico o clase primitiva, sencilla o compuesta.

---

## Patrón Handler

![Diseño de un handler](./figuras/handler.png)

---

- __Identifiable__: Clase cliente que necesita identificar a sus objetos a través de algún atributo identificador

- __Handler__: Interfaz para declarar los identificadores de los objetos de la clase `Identifiable`

- __ConcreteHandler__: Implementación concreta de la interfaz `Handler`

---

### Implementación del patrón

```java
  interface Handler{
      String toString();
      int compareTo(Handler otro);
  }
  /**
   * Ejemplo de handler concreto (de tipo numérico)
   */	
  class IdentificadorNumerico implements Handler {
    private int id;
    
    IdentificadorNumerico (String id) throws NumberFormatException {      
      this.dni = Integer.valueOf(dni);
    }
    IdentificadorNumerico (Handler otro) throws NumberFormatException {
      this.id = Integer.valueOf(otro.toString());
    }
    
    public String toString() {
      return id.toString();
      
    }
    public int compareTo(Handler otro) {
      return toString().compareTo(otro.toString());
    }
  }
  
  class Identifiable {
    private Handler _id;
    public Identifiable(String id) { 
         _id = new IdentificadorNumerico(id); 
    }
}
```

---

### El patrón en los lenguajes

#### Java: Identificadores con `java.lang.Comparable`

`Comparable` es una interfaz implementada por `String`, `File`, `Date`, etc. y todas las llamadas _clases de envoltura_ del JDK (i.e. `Integer`, `Long`, etc.)

__Métodos de la interfaz__:

```java
  public int compareTo(Object o) throws ClassCastException
```

---

__Invariantes:__

`sgn(x.compareTo(y)) = -sgn(y.compareTo(x))`

`(x.compareTo(y)>0 and y.compareTo(z)>0)` $\Rightarrow$ `x.compareTo(z)>0`

`x.compareTo(y)=0` $\Rightarrow$ `sgn(x.compareTo(z))=sgn(y.compareTo(z))` $\forall$ `z`

__Consistencia con `equals`__:

`(x.compareTo(y)=0)` $\Leftrightarrow$ `(x.equals(y))`

---

#### C++: Comparación de identificadores

Cómo implementar la interfaz de comparación de un Handler en C++

¿Tienen sentido las siguientes implementaciones?

```c++
   static int compare(const Handler&, const Handler&);
   int compareTo(const Handler&); // member function
```

Ver __[stackoverflow](https://stackoverflow.com/questions/20005392/is-there-a-compareto-method-in-c-similar-to-java-where-you-can-use-opera)__ y ver __[Overloading C++ operators (sección "Comparsion operators")](https://en.cppreference.com/w/cpp/language/operators)__

---
<style scoped>
h2 {
  color: blue;
}
</style>

## Reutilización y flexibilidad

- __Reutilización__: Construir software fácil de reutilizar sin tener que cambiar los módulos ya escritos (afecta a la fase de __desarrollo__)
- __Flexibilidad__: Adaptarse a cambios de requisitos y construir software fácil de cambiar (afecta a la fase de __mantenimiento adaptativo__)


---

### El ciclo de vida del software

![Ciclo de vida del software, width:600px](./figuras/ciclos-de-vida-del-software.jpg)






---

### El árbol de la Calidad del Software (_SQA tree_)

![Reutilización vs flexibilidad, width:600px](./figuras/sqa-tree.png)

---

<!-- paginate: false -->

# CASO PRÁCTICO 2: <br> Pruebas Unitarias

---

<!-- paginate: true -->

## jUnit: Framework de pruebas unitarias

- JUnit es un framework en Java que sirve para diseñar, construir y ejecutar **pruebas unitarias**
- Una prueba unitaria comprueba la corrección de un _módulo_ de software en cuanto a funcionalidades que ofrece.
- En el caso de Java, las pruebas unitarias comprueban la corrección de cada uno de los métodos de _cada clase_.

<style scoped>
p {
  color: red;
  text-align: center;
}
</style>

¿Cómo funciona?

---

### ¿Cómo probar `Saludo.java`?  (sin jUnit)

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

---

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

---

#### Críticas

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

#### Ejecución de los tests (en clases diferentes):

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

---


#### Ejecución de los test con jUnit 3 


```java
import junit.framework.*; 

public class MyTestRunnerjU3 { 
      public static void main(String[] a) { 
         TestResult result = new TestResult(new SaludoTest("testSaludar")); 
         result.run(); 
         System.out.println("Was it successful? " +    	 
                             result.wasSuccessful());
          
      } 
}
```

---



### Diseño del framework jUnit 

#### Estructura de clases:

![Clases del framework jUnit, width:600px](./figuras/junit-design-1.png)

---

<!--

#### Ejecución de casos de prueba:

![Clases del framework jUnit, width:600px](./figuras/junit-design-2.png)

---

-->

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

---

#### ShoppingCartTestCase con jUnit 3

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
```

---

```java
  public void testProductAdd() {
      Product book = new Product("Refactoring", 53.95);
      bookCart.addItem(book);
      double expectedBalance = defaultBook.getPrice() + book.getPrice();
      assertEquals(expectedBalance, bookCart.getBalance(), 0.0);
      assertEquals(2, bookCart.getItemCount());
  }
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
```

---

```java
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

---

Ahora agrupamos varios casos de prueba en una misma _suite_:

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
        suite.addTest(CreditCardTest.suite()); // otra test suite para
                                               // credit cart
        // etc.
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

---

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

---

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

Diseñar y codificar una suite de casos de prueba unitaria para `CreditCard` usando jUnit4.

---

### Arquitectura del framework jUnit

![Clases del framework jUnit, width:700px](./figuras/junit-patterns.png)

En la arquitectura del framework se observan diversos patrones: Composite, Command, Adapter, Factory, Decorator, etc.

---

<style scoped>
h2 {
  color: blue;
}
</style>

## Bibliotecas y frameworks

#### Flujo de control en una biblioteca

![Flujo de control en una biblioteca, width:800px](./figuras/biblioteca.png)

---


#### Flujo de control en un framework

![Flujo de control en una framework, width:1200px](./figuras/framework.png)

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

- API orientado a objetos vs. API generalmente funcional
- Flujo de control invertido
- Programador _cliente_ (código específico) vs. programador de _API_ (código reutilizable)

---

### Principios y técnicas de un framework

- **Abstracción**
  - Clases y componentes abstractos
  - Interfaces abiertas
  - Uso de patrones de diseño
  - Componentes de un dominio específico

- Máxima **cohesión**, mínimo **acoplamiento**
  - Minimizar dependencias: Una clase A presenta una dependencia con otra clase B (A $\rightarrow$ B) si la primera usa una instancia de la segunda.
  - Cuando no se pueden eliminar las dependencias, mantener las abstractas e __inyectar__ las concretas.

---

> Coupling is the enemy of change, because it links together things that must change in parallel
> 
> D. Thomas & A. Hunt, [The Pragmatic Programmer](https://pragprog.com/titles/tpp20/the-pragmatic-programmer-20th-anniversary-edition/), 20th Anniversary Edition, 2019

---

- **Inyección de dependencias**: una clase o módulo no debería configurar sus dependencias estáticamente, sino ser configurada desde fuera

---

<!-- paginate: false -->

# CASO PRÁCTICO 3: <br> Caballeros de la mesa redonda

---

<!-- paginate: true -->

## Ejemplo: tomado de [Spring in Action](bibliografia.html#spring)

---

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

---

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

#### Pegas:

- Instanciación de `HolyGrail`

- Cada vez que se prueba `KnightOfTheRoundTable`, también se prueba `HolyGrailQuest`.

- No se puede pedir a `HolyGrailQuest` que se comporte de otra forma (v.g. devolver null o elevar una excepción)

---

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

---

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

---

#### Pegas:

- El `Knight` aún depende de un tipo específico de `Quest` obtenido mediante `new`

<style scoped>
p {
  color: red;
  text-align: center;
}
</style>

¿Debe ser el caballero responsable de obtener un desafío?

---

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

---

- El caballero sólo sabe del desafío a través de su interfaz `Quest`.

- Puede asignársele cualquier implementación de `Quest`
 (`HolyGrailQuest`, `RescueDamselQuest`, etc.)


![width:800px](./figuras/di-knight.png)

---

<style scoped>
p {
  color: red;
  text-align: center;
}
</style>

Ejercicio: Discutir el tipo de retorno `Object` de `embarkOnQuest`:

- Puede provocar `ClassCastException`
- Solución propuesta: rediseñar la interfaz `Quest`

---

<style scoped>
h2 {
  color: blue;
}
</style>

## Inyección de dependencias

![Inyección de dependencias, width:500px](./figuras/dep-injection.png)

---

### Inversión de control

Es la base de la inyección de dependencias

> The question is: _what aspect of control are they inverting?_ [...] Early **user interfaces** were controlled by the application program. You would have a sequence of commands like "Enter name", "enter address"; your program would drive the prompts and pick up a response to each one. With **graphical** (or even screen based) UIs the UI framework would contain this main loop and your program instead provided event handlers for the various fields on the screen. The main control of the program was inverted, moved away from you to the framework.
>
> –– Martin Fowler, [IoC containers and the DI pattern](http://martinfowler.com/articles/injection.html) [1]

[1] http://martinfowler.com/articles/injection.html  

---

#### IoC–Inversion of Control / DI–Dependency Injection

- Una aplicación está compuesta por dos o más clases que colaboran.
- Los objetos deben recibir las dependencias en su creación, por parte de una entidad externa o **contenedor** que los coordina.
- IoC = Inversión de la responsabilidad de cómo un objeto obtiene referencias a los objetos con los que colabora
- Ventaja = __bajo acoplamiento__: un objeto sólo sabe de sus dependencias por su _interfaz_, no por su _implementación_, ni por cómo fueron instanciados.
- Entonces la dependencia puede cambiarse por una implementación distinta (incluso en **tiempo de ejecución**)
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

### Factorizar una función

![Reutilización de una función, width:700px](./figuras/misuse-reuse-1.png)

---

![Reutilización de una función, width:700px](./figuras/misuse-reuse-2.png)

### Ventajas (supuestas) de reutilizar:

__Ahorro__: Si $\exists$ $s$ sistemas $\wedge ~ coste(Function~1) = c$ $\Rightarrow$ ahorro = $c \times (s-1)$

---

### Amenazas (reales) a la reutilización:

![Reutilización de una función, width:500px](./figuras/misuse-reuse-3.png)

---

- Realmente el ahorro depende de la __complejidad__. Y muchas veces, la complejidad de la función está exponencialmente relacionada con el número de sistemas.
- Con un único punto de fallo, si `Function 1` falla, todos los sistemas pueden fallar a la vez.
- La seguridad es inversamente proporcional a la complejidad del sistema.
<!-- - Se incrementan los costes de llevar los sistemas a la nube. -->

---

### Conclusión sobre la reutilización

- No crear funciones reutilizables en primer lugar

- Aplicar el principio __YAGNI__: __You Ain't Gonna Need It__

---

<!--

# CASO PRÁCTICO 4: <br> Guitarras Paco

---

## Guitarras Paco

El cliente (Paco) quiere:

- Mantener un inventario de guitarras

- Encontrar guitarras para sus clientes

Problemas de la aplicación heredada:

- Caso de uso: un cliente busca una guitarra flamenca ‘Valeriano Bernal’, pero no encuentra ninguna

- ¿Problemas?

---

### Una aplicación heredada

![Guitar e Inventory, width:700px](./figuras/guitar_m1b.png)

---

![Guitar, width:400px](./figuras/uml_guitar_m1.png)

---

![Inventory, width:1100px](./figuras/uml_inventory_m1.png)

---

### Implementación: Guitarra

```java
  public class Guitar {
     private String serialNumber, builder, model, type, backWood, topWood;
     private double price;

     public Guitar(String serialNumber, double price,
                        String builder, String model, String type,
                        String backWood, String topWood) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.backWood = backWood;
        this.topWood = topWood;
     }

     public String getSerialNumber() {return serialNumber;}
     public double getPrice() {return price;}
     public void setPrice(float newPrice) {
        this.price = newPrice;
     }
     public String getBuilder() {return builder;}
     public String getModel() {return model;}
     public String getType() {return type;}
     public String getBackWood() {return backWood;}
     public String getTopWood() {return topWood;}
  }
```

---

### Implementación: Inventario

```java
public class Inventory {
  private List guitars;
  public Inventory() { guitars = new LinkedList(); }
  public void addGuitar(String serialNumber, double price,
                        String builder, String model,
                        String type, String backWood, String topWood) {
    Guitar guitar = new Guitar(serialNumber, price, builder,
                               model, type, backWood, topWood);
    guitars.add(guitar);
  }
  public Guitar getGuitar(String serialNumber) {
    for (Iterator i = guitars.iterator(); i.hasNext(); ) {
      Guitar guitar = (Guitar)i.next();
      if (guitar.getSerialNumber().equals(serialNumber)) {
        return guitar;
      }
    }
    return null;
  }
```

---
```java
  public Guitar search(Guitar searchGuitar) {
    for (Iterator i = guitars.iterator(); i.hasNext(); ) {
      Guitar guitar = (Guitar)i.next();
      String builder = searchGuitar.getBuilder().toLowerCase();
      if ((builder != null) && (!builder.equals("")) &&
          (!builder.equals(guitar.getBuilder().toLowerCase())))
        continue;
      String model = searchGuitar.getModel().toLowerCase();
      if ((model != null) && (!model.equals("")) &&
          (!model.equals(guitar.getModel().toLowerCase())))
        continue;
      String type = searchGuitar.getType().toLowerCase();
      if ((type != null) && (!searchGuitar.equals("")) &&
          (!type.equals(guitar.getType().toLowerCase())))
        continue;
      String backWood = searchGuitar.getBackWood().toLowerCase();
      if ((backWood != null) && (!backWood.equals("")) &&
          (!backWood.equals(guitar.getBackWood().toLowerCase())))
        continue;
      String topWood = searchGuitar.getTopWood().toLowerCase();
      if ((topWood != null) && (!topWood.equals("")) &&
          (!topWood.equals(guitar.getTopWood().toLowerCase())))
        continue;
      return guitar;
    }
    return null;
  }
}
```

---

## Algunos problemas

- Se compara el fabricante sin tener en cuenta mayúsculas/minúsculas

- Se comparan todos los campos sin tener en cuenta
    mayúsculas/minúsculas

- No hay definidas constantes para cada fabricante

¿Estas soluciones abordan el verdadero problema?

Preguntar a Paco...

---

### Preguntar al cliente

Preguntemos a Paco, que no tiene por qué saber nada de objetos ni bases
de datos:

- ¿Sólo vendes guitarras?

- ¿Cómo actualizas el inventario?

- ¿Cómo funciona la búsqueda de guitarras?

- ¿Necesitarás informes de inventario y de ventas?

---

### Respuestas del cliente

Paco dice que:

- Los clientes no siempre conocen las características exactas de la guitarra que quieren

- Los clientes suelen buscar guitarras dentro de un rango de precios

- Suele haber más de una guitarra que casa con las necesidades del cliente

- Sí, necesito informes y demás, pero ¡la prioridad nº 1 es encontrar las guitarras!

---
<style scoped>
p, h2 {
  color: red;
  text-align: center;
}
</style>

## Ejercicio

Hacer refactoring de la aplicación heredada de Guitarras Paco

---
-->

<!-- paginate: false -->

# CASO PRÁCTICO 4: <br> Figuras geométricas

---

<!-- paginate: true -->

## Principios SOLID

Los principios SOLID nos dicen:

- Cómo organizar en __módulos__ (en OO, __clases__) las estructuras de datos y las funciones
- Cómo deben quedar _interconectadas_ las clases (vía __dependencias__)

El objetivo de SOLID es crear estructuras software de nivel intermedio que sean:

- _flexibles_: tolerantes a los cambios
- _poco complejas_: fáciles de comprender
- _reutilizables_: la base de componentes útiles para muchos sistemas software

[Uncle Bob Martin principles](https://en.wikipedia.org/wiki/SOLID_(object-oriented_design))

[comment]: http://butunclebob.com/ArticleS.UncleBob.PrinciplesOfOod

En C++: [Breaking Dependencies: The SOLID Principles](https://www.youtube.com/watch?v=Ntraj80qN2k) by Klaus Iglberger

---
<style scoped>
h3 {
  color: blue;
}
</style>

## Principio de responsabilidad única

### SRP: *Single responsibility Principle*

> A class should have only one reason to change
> –– Bob Martin

- Una clase que modela múltiples aspectos genera acoplamiento entre los distintos aspectos
- Un cambio en algún aspecto obligará a cambios accidentales en los clientes que no dependen de dicho aspecto

SRP es lo mismo que el principio de __cohesión__ de [DeMarco](bibliografia.html#demarco)

---

<!-- Los módulos enmarañados que nunca cambian no son problemáticos-->


SRP es aplicación directa de la [ley de Conway](http://www.melconway.com/Home/Conways_Law.html):
> Any organization that designs a system (...) will produce a design whose structure is a copy of the organization's communication structure.
> –– M. Conway, _Datamation_, April 1968

- Cuando se diseña software, hace falta conocer los grupos/equipos/roles a los que éste sirve, y dividir el sistema en componentes separados, de forma similar a como estos grupos de personas se comunican normalmente en la vida real.

- Es necesario tener conocimiento del __dominio__ para poder dividir bien las responsabilidades

- Tiene que ver con la __variabilidad__ de los requisitos

---

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

---

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

---

<style scoped>
h4 {
  color: red;
}
</style>

#### Preguntas

- ¿Cuántas responsabilidades tienen las clases que implementan la interfaz `Shape`?
- ¿Cuáles son estas responsabilidades?
- ¿Qué parte no cumple SRP en el ejemplo? 

---

#### Respuestas

- Dos responsabilidades: geometría computacional + dibujo en pantalla
- Todas las figuras tienen métodos `draw` (dibujar en pantalla) además del método `area` que calcula el área (geometría computacional) $\rightarrow$ Violación del SRP

#### Solución

Patrón de diseño __Visitor__

---

<style scoped>
h3 {
  color: red;
  text-align: center;
}
</style>

### Ejercicio

- Buscar información de los patrones _ActiveRecord_ y _Data Access Object (DAO)_.
- Discutir si cumplen o violan el SRP.

---

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

---

<style scoped>
h3 {
  color: blue;
}
</style>

## Principio de Abierto-Cerrado

### OCP: *Open-Closed Principle*

> Toda clase, módulo, aspecto o función debe quedar abierto para extensiones pero cerrado para modificaciones
> 
> ––B. Meyer, [Object Oriented Software Construction](#meyer)

Para que un sistema software sea fácil de cambiar, debe diseñarse para que permita cambiar su comportamiento añadiendo código, no cambiando código existente.

- Si un cambio en un sitio origina una cascada de cambios en otros puntos del sistema, el resultado es un sistema frágil y rígido
- Es difícil averiguar todos los puntos que requieren cambios
- Código cerrado para modificaciones, pero abierto para extensión mediante delegación en vertical (subclases) u horizontal (composición)

---

### Ejemplo: Shapes versión 2 en C++

¿Qué parte no cumple OCP en el ejemplo? 

---

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

---

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
---

#### Problema:

- `DrawAllShapes` no está cerrado para modificaciones cuando aparecen nuevos tipos de `Shape`

#### Solución

- __Abstracción__ (ocultación de la implementación): clase abstracta y métodos polimórficos.
- __Patrones de diseño__: _template method_ y/o _strategy_

---

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

---

```csharp
public void DrawAllShapes(IList shapes)
{
  foreach(Shape shape in shapes)
    shape.Draw();
}
```

- Si queremos ampliar el comportamiento de `DrawAllShapes`, solo tenemos que añadir una nueva clase derivada de `Shape`
- Si se aplica bien OCP, los cambios de un cierto tipo obligan a añadir nuevo código, no a modificar el existente

---

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
---
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
---
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
---
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
---
<style scoped>
h3 {
  color: blue;
}
</style>

### Open-Closed Principle

> In general, no matter how _closed_ a module is, there will always be some kind of change against which it is not closed. There is no model that is natural to all contexts!
> 
> Since closure cannot be complete, it must be strategic. That is, the designer must choose the kinds of changes against which to close the design, must guess at the kinds of changes that are most likely, and then construct abstractions to protect against those changes.
> 
> –– Bob C. Martin

### Implicaciones arquitectónicas

OCP es un principio más arquitectónico que de diseño de clases y módulos.

---

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
---
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
---
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
---
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
---

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
---
<style scoped>
h3 {
  color: blue;
}
</style>

## Principo de segregación de interfaces

### ISP: *Interface Segregation Principle*

> Los clientes no deben depender de métodos que no usan. 
>
> Bob C. Martin

- Las interfaces son para los __clientes__, no para hacer jerarquías
- Evitar interfaces __gruesas__ con muchos métodos (descohesionadas)
- Los cambios en los métodos ignorados pueden provocar cambios en un cliente que no los usa

---

- La interfaz de una clase puede dividirse en __bloques__ de métodos relacionados. Unos clientes usan un bloque y otros clientes usan otro bloque. Si un cliente necesita conocer una interfaz no cohesionada, debe hacerlo combinando una o más clases (o mejor, sus interfaces)
- ISP es a las interfaces lo que SRP es a clases y métodos
- Violar el ISP es muy común en lenguajes de tipos estáticos (C++, Java, C#). Los lenguajes dinámicos (Ruby, Scala) ayudan algo más a no violar el ISP (v.g. con los _mixins_)

<!--

---

### Ejemplo: acoplamiento contexto-strategy

- ¿Cómo se comunican _Strategy_ y _Context_ (acoplado/desacoplado)?
- _Context_ Puede definir una interfaz que le permita a _Strategy_ acceder a sus datos

-->

---

### Ejemplo ISP: puertas de seguridad

Una implementación de puertas de seguridad con temporizador (`TimedDoor`) que hace sonar una alarma cuando la puerta está abierta durante un cierto tiempo.

#### Diseño:

![Puertas de seguridad, width:400px](./figuras/isp-timer-door.png)

---

- `TimedDoor` se comunica con `Timer` para registrar un temporizador
- Cuando salta el temporizador, avisa a un `TimerClient`
- Con la solución diseñada, un `TimerClient` puede registrarse a sí mismo en un `Timer` y recibir de éste un mensaje `Timeout()`.

---

#### Implementación inicial

```csharp
public class Timer {
  public void Register(int timeout, TimerClient client) {
    /*code*/ 
  }
}

public interface TimerClient {
    void TimeOut();
}
```

- Si se cierra la puerta antes de que venza el timeout $t_0$ y se vuelve a abrir, se registra uno nuevo $t_1$ antes de que el antiguo haya expirado.
- Cuando el primer temporizador $t_0$ expira, se produce la llamada a `Timeout()` de `TimedDoor` y no debería.
- Así que cambiamos la implementación:

---

#### Implementación mejorada

```csharp
public class Timer {
  public void Register(int timeout, int timeOutId, TimerClient client) {
    /*code*/
  }
}
public interface TimerClient {
  void TimeOut(int timeOutID);
}
```

¿En qué ha afectado el __cambio en la implementación__ de `TimerClient`?

---

- El cambio afecta a los usuarios de `TimerClient`, pero también a `Door` y a los clientes de `Door` (y no debería)
- El problema es que `Door` depende de `TimerClient` y no todas las variedades de puerta son de seguridad (con temporizador)
- Si hacen falta más variedades de puerta, todas ellas deberán implementar implementaciones degeneradas de `Timeout`
- Las interfaces empiezan a engrosarse. Esto puede acabar violando también el LSP

---

#### Rediseño: puertas de seguridad

__Delegación__ a través del patrón adapter (de objetos o de clases)

- Versión adaptador de clases (por herencia):

   ![Puertas de seguridad - adaptador de clases, width:700px](./figuras/isp-timer-door-class-adapter.png)

---

- Versión adaptador de objetos (por composición):

  ![Puertas de seguridad - adaptador de objetos, width:700px](./figuras/isp-timer-door-object-adapter.png)  

---

### Ejemplo 2 ISP: Shapes and Circles (1 de 2) en C++

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

---

- `DrawStrategy` ofrece una interfaz para recoger todos los métodos relacionados con dibujar figuras.
- Aquellas clases que implementen esta interfaz ofreceran sus versiones específicas para dibujar dichas figuras (e.g. usando sus propias librerías).
- La interfaz `Shape` mantiene el método `draw()` pero las clases que hereden no tendrán una implementación específica ¿Por qué?
- Porque este método `draw()` llamará a los métodos de una clase tipo `DrawStrategy` inyectada como dependencia.

---



### Ejemplo 2 ISP: Shapes and Circles (2 de 2) en C++

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
    void draw () const override;  // call drawing.draw(*this) here!

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

---

- Críticas a esta solución
   - Todas las clases que implementen `DrawStrategy` están obligadas a saber dibujar todas las figuras indicadas en la interfaz. ¿Tiene sentido?
   - El método `draw()` sigue ofreciéndose en la interfaz de `Shape` (y en su jerarquía) y es posible que haya clientes que no lo usen
   - ¿Seguiríamos el mismo enfoque para otros aspectos de la clase, e.g. `serialize()`?
---


## Recordando: Resultado de aplicación de SRP y OCP sobre figuras

### Ejemplo 3 ISP: Shapes en Java (cumple OCP pero no SRP)

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
  void draw() {...}
}

abstract class RectParallelogram extends Polygon {
  double area() {...}
  void draw() {...}
}
```

---

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

---

- Las funcionalidades para pintar (`draw`) y para imprimir (`toString`) pueden descohesionar las clases y atentar contra OCP y SRP
- Ya hemos visto que solucionar uno (SRP) me hace violar otro (OCP) y viceversa.
- Saquémoslas fuera utilizando **aspectos**...

---
<style scoped>
h3 {
  color: blue;
}
</style>

### Orientación a aspectos

La __orientación a aspectos__ (_AOD_/_AOP_) es un paradigma cuyo objetivo es incrementar la modularidad (__ortogonalidad__) de los componentes mediante la separación de aspectos __transversales__ (_cross-cutting concerns_).

![terminología sobre AOP](./figuras/aspectj-terminology.png)

---

#### Terminología:

- __aspect__ = modularización de un aspecto de interés (_concern_) que afecta a varias clases o módulos
- __joinpoint__ = especificación declarativa de un punto en la ejecución de un programa (por ejemplo, la ejecución de un método, el manejo de una excepción, etc.)
- __advice__ = acción a tomar por la especificación de un aspecto dado en un determinado _joinpoint_
- __pointcut__ = predicado que define cuándo se aplica un _advice_ de un aspecto en un _joinpoint_ determinado. Se asocia un _advice_ con la expresión de un _pointcut_ y se ejecuta el _advice_ en todos los _joinpoint_ que cumplan la expresión del _pointcut_.
- __inter-type declaration (ITD)__ = Los aspectos pueden declarar miembros (atributos, métodos y constructores) que son propiedad de otros tipos. Estos se llaman _miembros inter-tipo (ITD)_. **Los aspectos también pueden declarar que otros tipos implementan nuevas interfaces o extienden una nueva clase**.


---

### Ejemplo 3 ISP: Shapes en JAVA versión 2 (misma versión que en OCP), pero con aspectos usando ITDs

```aspectj
// Ficheros <X>ToString.aj (uno por aspecto)
package shapes.tostring; // para todos los toString()
aspect PolygonToString {
  String Polygon.toString() {  // añadimos método toString a la clase Polygon y su jerarquía
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
```

---

```aspectj
// Drawable.java
package drawing;
interface Drawable {
  void draw();
}

// Ficheros Drawable<X>.aj
package shapes.drawing; // para todos los draw()...
import drawing.Drawable;
abstract aspect DrawableShape {
  declare parents: Shape implements Drawable;  // Shape ahora implementa Drawable
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
...
```
---

Las clases de la aplicación de Figuras quedan ahora así:

```java
interface Shape {
  double area();
  // draw() removed
}

// Point class is not modified

abstract class Polygon implements Shape {
  Point getVertex(index i) {...}
  // draw() & toString() methods removed
}

class Circle extends ClosedCurve {
  double getRadius() {...}
  Point getCenter() {...}
  double Area() {...}
  // draw() & toString() methods removed
}
...
```
---

### ¿Qué hemos conseguido con los aspectos? (I)
- Los métodos `toString()` y `draw()` se han trasladado a aspectos separados, en los que las declaraciones intertipo (ITD) se utilizan para extender las clases de `Shape` con los nuevos métodos.
   - Para los métodos `draw()` hemos necesitado la interfaz `Drawable` para mantener el OCP gracias al polimorfismo y uso de métodos plantilla (template method).
   - ¿Por qué no hemos necesitado una interfaz `Stringeable` para los métodos `toString()`?

---

### ¿Qué hemos conseguido con los aspectos? (II)

- Conseguimos mantener SRP y OCP:
   - **SRP**: Está mejor soportado por esta refactorización, ya que las clases de `Shape` se centran ahora en sus propiedades estructurales y comportamientos esenciales.
   - **OCP**: Podemos extender el comportamiento de la jerarquía `Shape` sin modificar las clases originales originales. Las declaraciones de intertipos (ITD) se utilizan para introducir nuevos métodos en las clases sin modificar el código de las figuras.
   
- **Desventajas**: El estado y el comportamiento de una clase ya no se definen en un solo lugar. De hecho, este enfoque es algo radical para los lenguajes de tipado estático, se necesitan herramientas para su manejo.

---




<style scoped>
h3 {
  color: blue;
}
</style>

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

---

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

---

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

---

#### Problemas: 

- `DrawShape()` viola claramente el OCP
- Además `Square` y `Circle` no son sustuibles por `Shape`: no redefinen ninguna función de `Shape`, sino que añaden `Draw()` (violación del LSP)
- Esta violación de LSP es la que provoca la violación de OCP en `DrawShape`
- A continuación, una violación más sutil del LSP...

---

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

---

Un día hace falta manejar cuadrados además de rectángulos.

Geométricamente, un cuadrado es un rectángulo, así que hacemos uso de la herencia (relación **es-un**):

```java
public class Square: Rectangle {
   ...
}
```

---

#### Problema: cuadrados como rectángulos

- Un cuadrado podría ser matemáticamente un rectángulo, pero definitivamente un objeto `Square` **no es un** objeto `Rectangle`

- Un `Square` no tiene propiedades `height`y `width`. Pero supongamos que no nos importa el desperdicio de memoria.
- `Square` heredará los métodos accesores de `Rectangle`.
- Así que hacemos lo siguiente...

---

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
 
---

- El comportamiento de un objeto `Square` no es consistente con el de un objeto `Rectangle`:

  ```csharp
  Square s = new Square();
  s.Width = 1;   // fija ambos lados a 1
  s.Height = 2;  // fija ambos lados a 2, machacando el valor anterior

  void f(Rectangle r)
  {
    r.Width=32; // llama a Rectangle.SetWidth
  }
  ```

- ¿Qué sucede si pasamos un `Square` a la función `f`?

  ¡No cambia `Height`!

- Podría argumentarse que el error era que los métodos `Width`y `Height` no se declararon `virtual` en `Rectangle`.

---

### Ejemplo: rectángulos versión 3

```csharp
public class Rectangle
{
  private Point topLeft;
  private double width;
  private double height;
  public virtual double Width  // ahora marcado como virtual
  {
    get { return width; }
    set { width = value; }
  }
  public virtual double Height  // ahora marcado como virtual
  {
    get { return height; }
    set { height = value; }
  }
}
```

Nota: [Métodos redefinibles con `virtual` en C#](https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/virtual)

---

```csharp
public class Square: Rectangle
{
  public override double Width  // ahora no es new, hacemos override
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

---

#### Extensión y ocultación de métodos

- La [diferencia entre `new` y `override` en un método en C#](https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide/classes-and-structs/knowing-when-to-use-override-and-new-keywords) es que `new` oculta la implementación de la clase base y `override` la extiende.

Sin embargo, cuando la creación de una clase derivada provoca cambios en la clase base, es síntoma de un __mal diseño__.

El LSP pone en evidencia que la relación **es-un** tiene que ver con el comportamiento público extrínseco, del que los clientes dependen.


---

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

---

¿Qué pasa si llamamos a `g(new Square())`?
   - El area devuelta será 16 != 20...!!! ¿Por qué?
   - El `r.Height = 4`, siendo ahora `r` un cuadrado, cambia ambos lados a tamaño 4

El autor de `g` asumió que cambiar el ancho de un rectángulo deja intacto el alto. Si pasamos un cuadrado esto no es así 

__Violación de LSP__: Si pasamos una instancia de una clase derivada (`Square`), se altera el comportamiento definido por la clase base (`Rectangle`) de forma que `g` deja de funcionar.

---

¿Quién tiene la culpa?

- ¿El autor de `g` por asumir que "en un rectángulo su ancho y alto son independientes" (_invariante_)?
- ¿El autor de `Square` por violar el invariante?
- ¿De qué clase se ha violado el invariante? ¡De `Rectangle` y no de `Square`!

Para evaluar si un diseño es apropiado, no se debe tener en cuenta la solución por sí sola, sino en términos de los _supuestos razonables_ que hagan los usuarios del diseño.

---

### Ejercicios de LSP

- Robert C. Martin & Micah Martin: [Agile Principles, Patterns and Practices in C#](#unclebob), Prentice Hall, 2006
- Ejemplo de violación de LSP en [frameworks de videojuegos](https://medium.com/ingeniouslysimple/entities-components-and-systems-89c31464240d): Las interfaces HasPhysics, Collidable, Controllable incluyen una dependencia (por herencia) hacia Renderable, que no siempre es cierta (por ejemplo, para un objeto invisible). Solución: arquitectura Entity-Component-System.

---
<style scoped>
h3 {
  color: blue;
}
</style>

### Diseño por Contrato

Relación entre LSP y el **_Design-By-Contract_** (DBC) de *Bertrand
Meyer*:

> A routine redeclaration [in a derivative] may only replace the original precondition by one equal or weaker, and the original post-condition by one equal or stronger
> 
> –– ––B. Meyer

- Métodos de clase declaran *precondiciones* y *postcondiciones* al redefinir una operación en una subclase derivada
  - las **precondiciones** sólo pueden sustituirse por otras más débiles/laxas
  - las **postcondiciones** sólo pueden sustituirse por otras más fuertes/estrictas

---

#### Ejemplo: rectángulos

- Postcondición del _setter_ de `Rectangle.Width`
  (En C++ sería `Rectangle::SetWidth(double w)`):
    `assert((Width == w) && (Height == old.Height));`

- Postcondición del setter de `Square.Witdh` 
  (En C++ sería `Square::SetWidth(double w)`):
    `assert(Width==w);`

- La postcondición de `Square::SetWidth(double w)` viola el  contrato de la clase base porque es más débil que la de `Rectangle`

---
<style scoped>
h3 {
  color: blue;
}
</style>

## Principio de Inversión de Dependencias

### DIP: *Dependency Inversion Principle*

- Los módulos de alto nivel no deben depender de módulos de bajo nivel.
Ambos deben depender de abstracciones.

- Las abstracciones no deben depender de los detalles, sino los detalles de las abstracciones

> Depend on abstractions
> 
> –– Robert C. Martin

---

### Ejemplo: estructura en capas 

__Diseño inicial__:

![estructura en capas, width:600px](./figuras/dip-1.png)

- Las dependencias son transitivas
- _Policy_ depende de todo lo que depende _Mechanism_. 

---

__Diseño invertido__:

![capas invertidas, width:600px](./figuras/dip-2.png)

---

- Cada nivel declara una interfaz para lo que necesita de otros niveles inferiores
- Los niveles inferiores dependen de interfaces definidas en los superiores
- El cliente puede definir la abstracción que necesita (ISP)
- Cada nivel es intercambiable por un sustituto

---

### Heurística _ingenua_

- Ninguna variable debería guardar una referencia a una clase concreta
- Ninguna clase debería ser derivada de una clase concreta
- Ningún método debería redefinir un método ya implementado de ninguna de sus clases base

Hay que violar alguna vez estas heurísticas, pues alguien tiene que crear las instancias de las clases concretas. El módulo que lo haga presentará una dependencia de dichas clases concretas.

Gracias a la __introspección__ o la carga dinámica de clases, los lenguajes de programación pueden indicar el nombre de la clase a instanciar (por ejemplo, en un fichero de configuración).

Hay clases concretas que no cambian, como `String`, así que no hace ningún daño depender de ellas.

<!--

---

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
