---
marp: true
title: "Architecture: Communicating architectures"
author: "Ivan Ruiz, Andres Muñoz & Juan Manuel Dodero"
date: 2021-04-26
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
ivan.ruiz@uca.es

---

# Module contents

1. Introduction to software architectures
2. Communicating architectures
3. Architectural principles
4. Architectural patterns
<!-- 3. Architectural tactics -->



---
<!-- _class: lead -->

# Communicating architectures

---
# Lesson contents
Introduction
Architectural decision records
Architecture description languages
Architecture models
Frameworks for creating architectures


---
# Lesson contents
Introduction
Architectural decision records: *Y-statement and MADR*
Architecture description languages: *AADL*
Architecture models: *UML*
Frameworks for creating architectures: *C4 model*




<!-- paginate: true -->


---
<!-- _class: lead -->

# Introduction


---

## **Documenting** software architectures helps us understand the **big picture** of the systems, providing a **shared** vision and a common **vocabulary** for all stakeholders.


---

## Architecture description

- In the **building** industry, architecture is usually documented with site plans, floor plans, elevation views, cross-section views, and detailed drawings. 
- In **Software Engineering**, architecture description is the explicit work product expressing an architecture of a system, usually via code, texts and graphics. 

---

## Software architecture is **embedded** in the **source code** itself, 
However, there are other aspects that are **not** directly (easily) **observable** in the code.

---
 
## Missing aspects...
- Why certain **technologies** were chosen?
- Which **tactics**, **principles**, and **patterns** have been used?
- How the structural **elements** are **deployed** at runtime? 
- How they **communicate** themselves?



---
<!-- _class: lead -->

# Architectural decision records


---

## Architectural decision records (ADRs)

- The architecture of a software system can be seen as the set of **major design decisions** on the system.

- It is very common to **forget** why certain decisions were made. That may lead to **problems** when the work team changes. 

- Architectures can **evolve** to satisfy further requirements, so not all decisions have to be made in the beginning. We should **keep the options open** for as long as possible. 
  

- Let's see some examples of decisions...


---

### Examples of feature decisions

- *Create a system from scratch or extend a base platform?*

- *Authentication via a biometric scanner or authentication based on login and password?*

--- 

### Examples of decisions on the technology decisions

- *C++ or Java for programming the system?* 
  
- *Qt or GTK for developing the user interface?* 

- *Eclipse IDE, Visual Studio Code or Qt Creator as development environment?*

---

### Examples of infrastructure decisions

- *A relational database (e.g., Oracle, MySQL, PostgreSQL, etc.) or a NoSQL database (MongoDB, Redis, Neo4J, etc.)*

- *ZeroMQ, RabbitMQ, and ActiveMQ for messaging?*

- *WebSocket, HTTP, DDS, MQTT as communication data protocol?*

---

### Examples of decisions on tactics for improving availability and performance
- Using ping/echo or heartbeat strategy for detecting faults with external systems?

- In case of system exhaustion when receiving external events, do we reduce the sampling rate or prioritize the source events?


---
### Examples of decisions on patterns/styles

- *Model View Controller (MVC) or Pipe and Filters for structuring our software components?*

- *Monolith N-tier application or a microservice architecture?*
  

---
### Examples of decisions on coding

- *Google/Microsoft/Mozilla C++ coding styles?*

- *Code organization by domain responsibilities or technical responsibilities?*


---

## Architectural decision log 

- It is very important to **document** the **architecture decisions** on a repository (e.g.: document, wiki page, plain file, structured data store, etc.) that collects them.

- These decisions must be defined using a proper **format** and **structure**, for example, using Y-statements or MADRs.


---
## Y-statement template
*In the context of **\<use case\/user story u\>**, facing **\<concern c\>** we decided for **\<option o\>** and neglected **\<other options\>**, to achieve **\<system qualities/desired consequences>**, accepting **<downside/undesired consequences>**, because **\<additional rationale\>**.*

---
### Example of a Y-statement template

*In the context of the **Web shop service**, facing the need to **keep user session data consistent and current across shop instances**, we decided for the **Database Session State pattern** and against **Client Session State** or **Server Session State** to achieve **data consistency and cloud elasticity**, accepting **that a session database needs to be designed and implemented**.*



---
## Markdown Architecture Decision Records (MADR)

- This is a proposal for documenting Architecture Decision Records using Markdown, a lightweight markup language for creating formatted text using a plain-text editor.




---
### Example of a MADR


```
# Choose a format and structure for managing ADRs

## Context and Problem Statement

We want to record architectural decisions made in this project.
Which format and structure should these records follow?

## Considered Options

* [MADR]
(https://adr.github.io/madr/) 2.1.0 - The Markdown Architectural Decision Records
* [Y-Statements] (https://www.infoq.com/articles/sustainable-architectural-design-decisions) 
* [Michael Nygard's template]
(http://thinkrelevance.com/blog/2011/11/15/documenting-architecture-decisions) 
* [Other templates listed at] (https://github.com/joelparkerhenderson/architecture_decision_record)
* Formless - No conventions for file format and structure
```

---

### Example of a MADR (cont.)

```
## Decision Outcome

Chosen option: "MADR 2.1.0", because

* Implicit assumptions should be made explicit.
  Design documentation is important to enabling people to understand the decisions later on. 
  See also 
  [A rational design process: How and why to fake it](https://doi.org/10.1109/TSE.1986.6312940).
* The MADR format is lean and fits our development style.
* The MADR structure is comprehensible and facilitates usage & maintenance.
* The MADR project is vivid.
* Version 2.1.0 is the latest one available when starting to document ADRs.

```

---
<!-- _class: lead -->

# Architecture description languages

---
## Architecture description languages (ADLs)

- **Formal languages** targeted at designing software architectures
  
- Define the **components** conforming to our system, the **arrangement** of those structural elements, and the ways they **communicate** with each other. 

- Examples: AADL, ABACUS, ACME, or xADL.

---  
### Architecture Analysis and Design Language (**AADL**) 

- A specification dedicated to the modeling and analysis of **real-time, safety-critical, embedded systems**. 

- This standard provides software, hardware, and system component **abstractions** to specify and analyze the **systems** and map onto computational **hardware elements**.
---
### AADL example

```
package Hello_World                                 -- Entities are attached to a package
public
  subprogram Hello_Spg_1
   properties                 --  Simple subprogram: actual behavior, not modeled in AADL
    Source_Language => (C);                              --  Implementation language is C
    Source_Name     => "user_hello_spg_1";       --  Name of the corresponding C function
    Source_Text     => ("hello.c");                               --  Implementation file
  end Hello_Spg_1;

  thread Task    --  A task: a concurrent flow of execution                                    
   properties
    Priority                => 1;     --  Priority, interpretation given by the processor
    Dispatch_Protocol       => Periodic;                                     --  Periodic
    Period                  => 1000 ms;                            --  Period of the task
    Compute_Execution_Time  => 0 ms .. 3 ms;                           --  Execution time
    Compute_Entrypoint      => classifier (Hello_Spg_1);      --  Hello_Spg_1 is executed 
  end Task;                                                           -- at each dispatch
  
```
---


```
  process node_a
                                  --  A process, gathers several threads as subcomponents
  end node_a;

  process implementation node_a.impl
   subcomponents
    Task1 : thread Task;
  end node_a.impl;

  processor cpu                              --  A processor provides execution resources
   properties                       -- Threads are given access to the CPU according to a
    Scheduling_Protocol => (RMS);           -– Rate-Monotonic Scheduling: a shorter cycle
  end cpu;                                   –- duration results in a higher job priority

  system rma
                                --  A system combines both hardware and software elements
  end rma;

  system implementation rma.impl
   subcomponents
    node_a : process node_a.impl;
    cpu	   : processor cpu;
   properties
    Actual_Processor_Binding           -- Binding relations between hardware and software
       => (reference (cpu)) applies to node_a;    -- node_a is allocated resources on cpu
  end rma.impl;

end Hello_World;
```

---
<!-- _class: lead -->

# Architecture models


---
### Sketches

The first approach to software architecture 

---

![w:700](./figures/Sketch.jpg)


---
### Diagrams

Sketches are later beautified with diagramming tools *PowerPoint\**.

---

![w:900](./figures/SampleArchitecture.png)



--- 
## Problems with sketches

- Boxes and arrows with inconsistent notation: colors, shapes
- Ambiguous naming,
- Unlabelled relationships,
- Mixed abstractions, 
- etc. 

---

## Modeling languages

- Provide well-defined **semantics** and unambiguous **understanding**.
- Examples: **UML** and **SysML** 
- **Model-Based** Software/System Engineering (MBSE)
  -  **Model-Driven** Software/System Engineering (MDSE)
- Architectural **models** created with those languages capture totally or partially the **design decisions** during the creation of the system architecture.


---
## Unified Modeling Language (UML) 

- **Open** standard for software development **lifecycle modeling**. 
- Supported by many open and proprietary (web/desktop) **tools**. 
- Provides a set of **visual** notations to represent system  **structure** and **behavior**.
- **Use case diagrams** or **class diagrams** are commonly used for requirements engineering purposes.


--- 
## Tool support
### Graphical modeling environments 
- Creation of visual models by dragging and dropping modeling elements 
- Examples: *ArgoUML, Enterprise Architect, Visual Paradigm, Modelio, etc.*  

---
### Modelio
![w:700](./figures/Modelio.png)

---

## Tool support
### Textual modeling environments 

- Creation of visual models by writing code
- Examples: *PlantUML or yUML*

--- 
### PlantUML

![w:900](./figures/PlantUML.png)




--- 
## Problems with models

- One only representation is **not** usually **enough** to correctly represent the static and dynamic nature of the architecture 
   <!-- - Harmonize the models at different levels of abstraction and points of view.-->


- They are often **not in sync** with the code. 
  <!--Source code rapidly changes to address changes in requirements, visual models are not updated at the same pace.--> 
  
- There are tools for generating visual models directly from the source code via reverse engineering, but they generally include too many **details**.
  <!-- However, the diagrams generated usually include too many details, hiding seeing the overall architecture.-->


---

![bg left](./figures/UML_Diagrams.jpeg)
### UML diagrams
There are 14 types of diagrams. We need for guidelines regarding the number and type of notations to use.


---
<!-- _class: lead -->

# Frameworks for creating architectures

---
## Approaches for conducting the architecture design process
- Propose the use of a specific set of models and textual descriptions for representing different software **views** at different levels of **abstraction** and **viewpoints**. 
- The most prominent ones: 
  - **4+1 architectural view model**, by *Philippe Kruchten*; 
  - **Arc42 templates**, by *Starke, Hruschka and Müller*; 
  - and ...

---

## C4 model

- C4 model is an approach, devised by *Simon Brown*, for describing and **communicating** software architectures to different types of **audiences**.

- Follows the idea of *Google Maps**, i.e., to see the system as a **map** at various levels of detail. 

- **Abstraction**-first approach and **notation independent**

- Simple diagrams based on **boxes** and **lines**, but can be also created with **UML/SysML** with the appropriate use of packages, components and stereotypes.


---

![bg w:85%](./figures/C4_Abstractions.png)


---
## Level 1: System Context diagram 
 
This diagram focuses on **people** (actors, roles, personas, etc) and **software systems** rather than technologies, protocols, and other low-level details. It's the sort of diagram that you could show to **non-technical people**. This includes the software system you are modeling, and the other software systems upon which your software system **depends** (or vice versa). 

---
## Software System
The highest level of abstraction and describes something that delivers value to its users, whether they are human or not. 

---
### Example of a System Context diagram
![w:800px](./figures/C4_SystemContext.png) 


---

![bg w:85%](./figures/C4_Abstractions.png)



---
## Level 2: Container Diagram
This diagram shows the **high-level shape** of the software architecture and how **responsibilities** are distributed across it. It is useful for software developers and support/operations staff alike. The diagram shows the **major technology choices** and how the containers **communicate** with one another. 


---
## Container

A separately runnable/deployable unit

- Not *Docker*!
- **GUI apps**: desktop apps, mobile apps, web apps
- **Non-GUI apps**: console apps, shell scripts, serverless functions, etc.
- **Datastores**: database, blob or content stores, file systems.



---
### Example of a Container Diagram
![w:800px](./figures/C4_Containers.png) 


---

![bg w:85%](./figures/C4_Abstractions.png)




---
## Level 3: Component Diagram
This diagram shows how a container is made up of **components** (the major structural building blocks), their **responsibilities**, and the **technology/implementation details**. 

Its intended audience is software architects and developers. 

Alternatively, we can use **UML component diagrams or UML package diagrams**.



---
## Component

A software component is a **unit of composition with contractually specified interfaces and explicit context dependencies**. A software component can be **deployed independently** and is subject to **third-party composition**. 

*Clemens Szyperski*



---

## Component

Software components are libraries, i.e., **things that are independently replaceable and upgradeable**. Examples would include Java's jars, C#'s assemblies, Ruby's gems, and Javascript's modules. 

*Martin Fowler* 



---
### Example of a Component diagram  (Jar files) 

![w:700px](./figures/JAR_Components.png) 
*UML component diagram*



---
## Component <small>(alternative definition)</small>

A component is a **grouping of related functionality (classes) encapsulated behind a well-defined interface**. 

Aspects such as how those components are organized (packages, modules, JAR file, DLL, namespaces, shared library, etc) is a separate and orthogonal concern. In the C4 model, components are not separately deployable units. 

*Simon Brown, C4 model* 



---
### Example of a Component diagram</small>
![w:500px](./figures/C4_Components_Modified.png) 
*C4 version*



---
### Example of a Component diagram

![w:600](./figures/UML_Components.png)
*UML component diagram*


---
### Example of a Component diagram 
![w:1100](./figures/UML_Packages.png)
*UML package diagram*







---

![bg w:85%](./figures/C4_Abstractions.png)


---
## Code 

Code elements (e.g. classes, interfaces, objects, functions, database tables, etc) within the component in scope.

---
## Level 4: Code Diagram
This level of detail shows how each component is implemented as code, using **UML class diagrams**. 

Ideally, these diagrams would be **automatically generated** using tooling (e.g. an IDE or UML modeling tool). This diagram is not recommended for the whole codebase but for the most important or complex components. 

---
### Example of a Code Diagram (UML class diagram)
![w:800px](./figures/C4_Code.png) 


 
---
## More diagrams?


---
## System Landscape diagram

This diagram shows the system landscape from an IT perspective. It is a high-level map of the software systems at the **enterprise-level**, considering the **organizational boundary**, **internal/external users**, and **internal/external systems**.

---
### Example of a System Landscape diagram
![w:800px](./figures/C4_Landscape.png) 

 
---
##  Dynamic diagram
This diagram can be useful when you want to show how elements in a static model **collaborate at runtime** to implement a **user story, use case, feature**, etc. 

The diagram is based upon *UML communication diagram*.

---
### Example of a Dynamic diagram
![w:800px](./figures/C4_Dynamic.png) 

 
---
## Deployment diagram
This diagram illustrates how software systems and/or containers in the static model are mapped to **deployment nodes**. They are something like **physical infrastructure** (e.g. a physical server or device) or **virtualized/containerized infrastructure** (e.g. IaaS, PaaS, a virtual machine, a Docker container).

This deployment diagram is based upon *UML deployment diagram*.

---
### Example of a Deployment diagram
![w:800px](./figures/C4_Deployment.png) 
 
---
##  Tooling for C4 model

- Diagramming tools:
  - Visual: *Microsoft Visio, Visual Paradigm*
  - Textual: *C4-PlantUML, c4builder*
  
- Modeling tools:
  - Visual: *IcePanel, Enterprise Architect, Gaphor*
  - Textual: *Structurizr*


---
## Let's practise..


---
![w:800px](./figures/Structurizr.png) 
### Software architecture as code

---

### Practical activity

1. Create a user and a new workspace in Structurizr to design the architecture of the "*ACME Access Control System*".
2. Create the **C4 context diagram** to show that the security staff is in charge of registering and authorizing new users in the system and providing them with ID cards based on NFC technology. Both external users and invited will be able to enter ACME's premises using their cards.
---

### Practical activity (cont.)

3. Create the **C4 container diagram** to depict that the system is based on a headless app written in Python that reads NFC cards and communicates with an Oracle database to authorize users and log user activity. In addition, there is a Qt-desktop application for managing users stored in the database.  

4. Create the **deployment diagram** to depict that the Pythons apps will run on two PLC Raspberry Pi, the database will be hosted and replicated in two machines on the ACME datacenter, and the desktop application will be running on the Security staff's computer.

---
<!-- _class: lead -->

# Thanks

ivan.ruiz@uca.es