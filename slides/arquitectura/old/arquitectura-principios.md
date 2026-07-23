---
marp: true
title: "Architecture: Design principles"
author: "Ivan Ruiz, Andres Muñoz & Juan Manuel Dodero"
date: 2022-05-11
theme: gaia
class:
  - gaia
  - lead
  - invert
header: 'Software Design'
footer: 'Architecture'


---
<!-- _class: lead -->

## <!--fit-->SYS: Software Architecture
### Software Design

![w:450px](./figures/UCA.png) 
##### Iván Ruiz Rube


---

# Module contents

1. Introduction to software architectures
2. Communicating architectures
3. Architectural principles
4. Architectural patterns
<!-- 3. Architectural tactics -->



---
<!-- _class: lead -->

# Architectural design principles

---
# Lesson contents   
Introduction
Software components
Component cohesion principles
Component coupling principles



<!-- paginate: true -->


---
<!-- _class: lead -->

# Introduction

---
## Introduction

- The quality of a system is the degree to which the system satisfies the stated and implied **needs** of its various **stakeholders**, and thus provides value. 

- **Team members** (developers, testers, etc.) are also stakeholders in the project.
  
- This section is about principles to improve system **maintainability**, which is key to the success of good software architecture. 

---

## Maintainability

- **Modularity**: Degree to which a system or computer program is composed of discrete components such that a change to one component has minimal impact on other components.

- **Reusability**: Degree to which an asset can be used in more than one system, or in building other assets. 

- **Analysability**: Degree of effectiveness and efficiency with which it is possible to assess the impact on a product or system of an intended change to one or more of its parts.
  
---
## Maintainability (cont.)

- **Modifiability**: Degree to which a product or system can be effectively and efficiently modified without introducing defects or degrading existing product quality.

- **Testability**: Degree of effectiveness and efficiency with which test criteria can be established for a system, product or component and tests can be performed to determine whether those criteria have been met.


---
<!-- _class: lead -->

# Components

---
## Achieving modularity...

- The **class** is too fine-grained as a unit of organization. 

- Components are **logical groupings** of statements that can be imported into other programs. 

- Pieces of software providing a set of **responsibilities**: Each component must have an **interface** describing how to use it and the body with its **implementation**. 

---
## A definition of components?

- The word **component** is a hugely overloaded term in the software development industry, 

- Szyperski defined as: a *unit of composition (binary) with contractually specified interfaces and explicit context dependencies only. A software component can be deployed independently and is subject to composition by third parties.*

- C4 model: *grouping of related functionality encapsulated behind a well-defined interface, and it is not separately deployable*. 
  
---
## A common terminology?

- Each programming language and each developer community have their own terminology to define the mechanisms to create and reuse components. 
- Examples:
  - Java: source code is organized into packages. Source code can be packed into JAR files.
  - C++: source code is organized via namespaces. Source code can be packed into C++ libraries (.a, .dll, .dylib files).
   

---

![bg left](./figures/Lego.jpg)

## Component-based Software Engineering
Connecting bricks together and following certain rules about how they can and cannot be interconnected is not unlike writing program code and using software interfaces. 


---
<!-- _class: lead -->

# Component cohesion principles

---
  
## Cohesion principles

- **High cohesion** and **low coupling** as the main design drivers for simple system architectures, which have to be easy to understand, change, and maintain.

- **Cohesion** represents the degree to which a part of a codebase forms a **logically single, atomic software unit** (a method, a class, a group of classes, a component, etc.). 

- Low cohesion makes components more difficult to maintain, test, and reuse.


---
### REP: Reuse/Release Equivalence Principle

"The granule of reuse is the granule of release. In other words, either all of the classes inside the package are reusable, or none of them are."

*Robert C. Martin*

---
#### Releasing software components

- Reusing software components is **not copying and pasting** source code
- Developers of the reusable code must **package** source code, **distribute** them as products and **maintain** them.
- The release process must produce the appropriate notifications and release documentation. 
- These processes can be automated by using **DevOps** strategies. 

---
#### Reusing software components

- Components must be shared via specific (public/private) repositories. 

- Developers to use these components must only download and import the required dependencies and start to use them.

- There are lots of dependencies management tools to help developers reuse software components: 
  - CMake and Conan, for the C/C++ language
  - Maven and Gradle, for the Java language  
  - etc.

---
#### Semantic versioning

- The components should be tracked through a release process and are given release numbers like MAJOR.MINOR.PATCH-LABEL, 
- Increment the version...

  - MAJOR: when you make incompatible changes on its API 
  - MINOR: when you add functionality (backward-compatible)
  - PATCH: when you make backward-compatible bug fixes.
  - LABEL: Optional metadata for pre-release.
- Example: *1.0.0-alpha < 1.0.0 < 2.0.0 < 2.1.0 < 2.1.1*

---

### CCP: Common Closure Principle

"Gather into components those classes that change for the same reasons and at the same times. Separate into different components those classes that change at different times and for different reasons."  

*Robert C. Martin*


---
#### High cohesive components

- Components must be responsible for a **single mission** (maybe one only thing...) Components should **not have multiple reasons to change**.
  
- Thus, we should gather together into the same component those classes that are **closed to the most common types of changes** that we expect or have experienced. 

- If we need to alter our code and the changes are confined to a single component, then we **redeploy only the one changed component** whilst other components do not need to be revalidated or redeployed.

--- 


### CRP: Common Reuse Principle

“Don’t force users of a component to depend on things they don’t need”. 

*Robert C. Martin*


---
#### High cohesive components

- **Those classes that are not tightly bound to each other should not be in the same component**. 
- When we depend on a component, we want to make sure we depend on every class in that component.

- Suppose that the *using* component uses only one class within the *used* component. Every time the *used* component is changed, the *using* component will likely still need to be recompiled, revalidated, and redeployed. This is true even if the *using* component doesn’t care about the change made in the *used* component.
  


---
#### Example of low cohesive component
![w:800](./figures/LowCohesion.png) 

---
#### Example of high cohesive components
![w:1100](./figures/HighCohesion.png)   


---
<!-- _class: lead -->

# Component coupling principles

---

## Coupling principles

- **Coupling** refers to the degree of interdependence that two software units have on each other, again meaning by software unit: classes, subtypes, methods, modules, functions, libraries, etc. 

- If two software units are completely independent of each other, we say that they are decoupled. Although this is the ideal situation, it rarely occurs. Therefore, the aim is to **achieve the lowest possible level of coupling**.

---
#### High coupling vs Low coupling

![w:600](./figures/Coupling.jpg) 

---
### Code dependencies

- **A component *Comp1* depends on another component *Comp2*** when one of *Comp1*'s classes has a dependency with a *Comp2*'s class. 
  
- In turn, **a class *Class1* depends on a class *Class2*** when: 

  - *Class1* inherits from the base class *Class2*
  - *Class1* has an attribute of class *Class2*
  - *Class2* is used as an input or output parameter of one the *Class1*'s functions or their functions' body.

---

### ADP: Acyclic Dependencies Principle

“There must be no cycles in the component dependency graph”. 

*Robert C. Martin*

--- 

#### Components as units of work

- The components can be the responsibility of a single developer, or a team of developers.
  
- When developers get a component working they release it for use by the other developers. 

- As new releases of a component are made available, other teams can decide whether they will immediately adopt the new release. 
  
- The dependency structure of the components of a software project can be visualized as a dependency graph, but this graph should be a **Directed Acyclic Graph** (DAG), i.e., there can be no cycles.

---
#### Component diagram of a sample system

![w:800](./figures/adpp-1.png) 



---
#### Component diagram with a dependency cycle

![w:800](./figures/adpp-2.png) 

---
#### Breaking the cycle with the **Dependency Inversion Principle**

![w:800](./figures/adpp-3.png) 

---

#### Extracting the new classes into a new component

![w:800](./figures/adpp-4.png) 

---


### SDP: Stable Dependencies Principle

“Depend in the direction of stability”. 

*Robert C. Martin*

---
#### Code stability
- The software architect should mold a component dependency graph to **protect stable high-value components from volatile components**. 
  
- We must **isolate that volatile code**. For example, we don’t want that our business rules (highest-level policies) are affected by...
  - cosmetic changes to the graphical user interfaces
  - changes on data persistence technologies 
  - mechanisms to communicate with external systems

---
![bg left fit](./figures/SDP_StableComponent.png)

A component with lots of incoming dependencies is very **stable** because it requires a great deal of work to reconcile any changes with all the dependent components

---
![bg left fit](./figures/SDP_UnstableComponent.png)

A component with a lot of outgoing dependencies is very **unstable**, because changes may come from many external sources

---
![bg left fit](./figures/SDP_Configuration.png)

**Ideal configuration** of component dependencies

---

![bg left fit](./figures/SDP_Configuration2.png)

Modules that are intended to be easy to change are not dependent on modules that are harder to change. 

---


### SDP: Stable Abstractions Principle

“A component should be as abstract as it is stable”. 

*Robert C. Martin*

---
#### Providing controlled flexibility to our components...
- Business logic and policy decisions of the system should **not change** very often due to technological **details**. 
- The code that encapsulates those pieces of the software should be placed into **stable** components. 
- However, this would make the overall **architecture inflexible** and the high-level policies would be difficult to change. 
- The solution is using **interfaces/abstract classes** for the stable elements. So we will be able to modify the policies by implementing new classes. 

---
**Open Closed Principle**
open for extension, but closed for modification

![w:650](./figures/SAP_Example.png)



---
<!-- _class: lead -->

# Thanks

ivan.ruiz@uca.es