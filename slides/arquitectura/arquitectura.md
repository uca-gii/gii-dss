# ARQUITECTURA SOFTWARE

## Índice

- [Architecture: Introduction](#architecture-introduction)
- [Architecture: Communicating architectures](#architecture-communicating-architectures)
- [Architecture: Architectural patterns](#architecture-architectural-patterns)
- [Architecture: Design principles](#architecture-design-principles)
- [Architecture: Tactics](#architecture-tactics)


<!-- Source: arquitectura-intro.md -->
# Architecture: Introduction

## Software Architecture
### Software Design


# Module contents

1. Introduction to software architectures
2. Communicating architectures
3. Architectural principles
4. Architectural patterns
5. Architectural tactics


# Introduction to software architectures

# Lesson contents
Definitions
Architecture design process
Process inputs
Process outputs
The role of the software architect
Benefits


# Definitions


## Architecture (I)

The **process** and the **product** of planning, designing, and constructing buildings or **other** structures.

## Architecture (II)

The **style of design** and **method** of construction of buildings and other physical structures.


![Background image](./figures/AthensParthenon.jpg)


![Background image](./figures/SydneyOperaHouse.jpg)


## Architecture applies to several **disciplines**:

- landscape architecture
- interior architecture,
- urban design,
- mechanical engineering,
- naval architecture,
- etc.


![Background image](./figures/Naval.png)


## ¿buildings == software?

both are developed by teams using **tools**, **patterns** and **tactics** and are affected by **trends**


## ¿buildings != software?

buildings are **stable** environments with **physical** limits and many difficulties to change, software is a **virtual** artifact with evolving nature and it is easier to **change**


## The architecture of **anything**

Fundamental organization embodied in its **components** and their **relationships** to each other and their environment. The **principles** of its design and evolution.

*ISO/IEC/IEEE 42010*
*Samuel Holcman*


## Architecture of a **system**

A **system architecture** makes use of elements of both **software** and **hardware** and is used to enable the design of such a composite system.

*ISO/IEC/IEEE 42010*


## The architecture of a **software system**

It is the shape given to that system by those who build it. The form of that shape is in the division of that system into **components**, the **arrangement** of those components, and the ways in which those components **communicate** with each other.

*Bob Martin*


## **Software** architecture

The **set of significant decisions** about the **organization of a software system**, the selection of the **structural elements and their interfaces** ...together with their **behavior** ..., the **composition** of these ... elements ..., and the **architectural style** ...

*Kruchten, : The Rational Unified Process*



## Architecture represents the **significant decisions**, where significance is measured by **cost of change**.

*Grady Booch*


## The **software architecture** can be considered the **blueprint** to build and maintain a software system.


## **Reference** architecture

The **set of design decisions** that can be simultaneously applied to **several related systems** within an application **domain** and with explicitly defined **variation points**.


<small>A **Software Product Line** is a set of software-intensive systems that share a **common**, managed set of **features** satisfying the specific needs of a particular **market segment** or mission.</small>


# Architecture design process


## Waterfall development
![](./figures/Waterfall.png)

## Waterfall development

- **Requirements** should be **fixed** before the coding phase began.

- **Big design up front**: Software architecture ought to be developed and completed before writing the first line of code.

- Creating a perfect software design blueprint can be a waste of time and resources because it could be **changed** many times.


## Agile development
![](./figures/Scrum.png)
<small>The *Scrum* framework</small>

## Agile development

- Agile manifesto values:
  - **Individuals and interactions** over *processes and tools*
  - **Customer collaboration** over *contract negotiation*
  - **Responding to change** over *following a plan*
  - **Working software** over *comprehensive documentation*

- Many organizations started to not create any design at all, causing long-term maintainability issues.


# How much up front design should you do?


# **0\%** ?


# **100\%** ?


## Big design up front is dumb.
## Doing no design up front is even dumber.

*Dave Thomas*


## Evolutionary architecture (I)

- An approach to architecture that embraces **change** in an agile manner.

- The key is to create **enough** pieces of architectural design before coding.

- The software architecture represents the set of **significant decisions**, where significance is measured by the cost of changes.

- Major **decisions** have to do with **technology** choices, selection of the proper **infrastructure**, application of certain **tactics** and design **patterns**, etc


## Evolutionary architecture (II)

- Propose **hypotheses** and then write some code to validate whether the solution posed is feasible and fulfills the expected requirements.

- Prove the architecture with concrete **experiments**, and proof of concepts.

- Identify and control **risks** and tackle the highest priority ones in the early stages.

- However, not all decisions have to be made in the beginning. In fact, it is much better to defer decisions until they are strictly needed. We should **keep the options open** for as long as possible.

## Laws of Software Architecture

**1 Everything in software architecture is a trade off**
All meaningful decisions have advantages and downsides

**#2: 'Why' is more important than 'How'**
Question everything


![Background image](./figures/ArchitectureDesignProcess.png)


# Process inputs


## Process inputs

The creation process of the software architecture starts with a set of inputs:
- Business objectives,
- Functional requirements,
- Information requirements,
- Non-functional requirements,
- and constraints.


## Business objectives

#### What are the **goals** (measurable) of the system to develop or maintain?

- What are the stakeholders' **expectations**?
- How **critical** will the software be?
- What is the **time-to-market**?
- Are the project **calendar** and costs well established?
- How **variable** are the rules that will govern the software?


![Background image](./figures/UML_UseCase.png)

### Functional requirements
Some requirements engineering techniques should be conducted prior to designing the architecture, to capture and analyze the behavior of the software to develop.


![Background image](./figures/UML_DomainModel.png)

### Information requirements
The static nature of the system is modeled with a conceptual model considering the business vocabulary.


![Background image](./figures/SysML_Requirements.png)

### Non-functional requirements
Quality attribute requirements, namely performance efficiency, compatibility, operability, reliability, security, portability, and maintainability.



### Constraints
- Both the **customer** and **development organization** can make certain technical or organizational decisions.

- These constraints limit the number of potential **alternatives**

- **Software** constraints: programming languages, development frameworks, database providers, etc.

- **Hardware** constraints: sensor and actuator manufacturers, execution platforms, etc.


# Process outputs


## Software architecture is **embedded** in the **source code** itself,
However, there are other aspects that are **not** directly (easily) **observable** in the code.


## Alternatives for describing architectures

- **Architectural decision records**, by using structured templates
- **Textual representations**, by using Architecture Description Languages (ADLs)
- **Visual models**, according to well-known specifications like UML or SysML


# The role of the software architect


## The software architect is in charge of creating and maintaining the software architecture.

## Skills

- On many occasions, this role is assumed by one of the **senior** developers.

- Possess good **interpersonal skills** to deal with stakeholders who may have different (even contradictory) needs

- Have a solid knowledge of the **business domain**.

- Aware of **new** development techniques, practices, and tools.

- **Self-experience** for correctly applying best practices, design principles, and patterns.

## Responsibilities (I)

- Software architects usually **code** as other developers and perform **code reviews**, so code low-level understanding is mandatory.
- Architects must **help** developers understand the overall system architecture.
- They must continually **analyze** the architecture and ensure **compliance** of the code with existing decisions during software evolution.

## Responsibilities (II)

- Define **guidelines** and checklists to ensure the technical success of the project.
- Analyze the **pros** and **cons** of the different alternatives and keep decision records.
- Explicitly **document** the software architectures by means of visual models or textual representations.


# Benefits


## Benefits (I)

- The way of programming should always be the same and follow the same structure, the resulting code will be easily recognizable and ...

  - facilitates the development and maintenance of the software.
  - simplifies the deployment and operation of the systems.

## Benefits (II)

- The software architecture documentation provides...
  - a clear vision and roadmap for the team to follow.
  - a common language to express, negotiate and resolve the stakeholder's expectations.
  - a valuable resource to be used as the basis for the training of new project members.


# Key ideas


- **Definition**: *the set of significant decisions, the style of design, the organization into components and the way of communication.*
- **Process**: *evolutionary, addressing risks, proofs of concepts*
- **Inputs**: *business objectives, software requirements and know-how*
- **Outputs**: *source code, decision records and textual/visual representations*
- **Skills**: *interpersonal, business and technical knowledge and experience*
- **Responsabilities**: *code, review, help, analyze, define, document.*
- **Benefits**: *code easy to maintain, documentation helpful for all.*

<!-- Source: arquitectura-comunicando.md -->
# Architecture: Communicating architectures

## Software Architecture

### Software Design


# Module contents

1. Introduction to software architectures
2. Communicating architectures
3. Architectural principles
4. Architectural patterns
5. Architectural tactics


# Communicating architectures

# Lesson contents
Introduction
Architectural decision records
Architecture description languages
Architecture models
Frameworks for creating architectures


# Lesson contents
Introduction
Architectural decision records: *Y-statement and MADR*
Architecture description languages: *AADL*
Architecture models: *UML*
Frameworks for creating architectures: *C4 model*


# Introduction


## **Documenting** software architectures helps us understand the **big picture** of the systems, providing a **shared** vision and a common **vocabulary** for all stakeholders.


## Architecture description

- In the **building** industry, architecture is usually documented with site plans, floor plans, elevation views, cross-section views, and detailed drawings.
- In **Software Engineering**, architecture description is the explicit work product expressing an architecture of a system, usually via code, texts and graphics.


## Software architecture is **embedded** in the **source code** itself,
However, there are other aspects that are **not** directly (easily) **observable** in the code.


## Missing aspects...
- Why certain **technologies** were chosen?
- Which **tactics**, **principles**, and **patterns** have been used?
- How the structural **elements** are **deployed** at runtime?
- How they **communicate** themselves?


# Architectural decision records


## Architectural decision records (ADRs)

- The architecture of a software system can be seen as the set of **major design decisions** on the system.

- It is very common to **forget** why certain decisions were made. That may lead to **problems** when the work team changes.

- Architectures can **evolve** to satisfy further requirements, so not all decisions have to be made in the beginning. We should **keep the options open** for as long as possible.


- Let's see some examples of decisions...


### Examples of feature decisions

- *Create a system from scratch or extend a base platform?*

- *Authentication via a biometric scanner or authentication based on login and password?*


### Examples of decisions on the technology decisions

- *C++ or Java for programming the system?*

- *Qt or GTK for developing the user interface?*

- *Eclipse IDE, Visual Studio Code or Qt Creator as development environment?*


### Examples of infrastructure decisions

- *A relational database (e.g., Oracle, MySQL, PostgreSQL, etc.) or a NoSQL database (MongoDB, Redis, Neo4J, etc.)*

- *ZeroMQ, RabbitMQ, and ActiveMQ for messaging?*

- *WebSocket, HTTP, DDS, MQTT as communication data protocol?*


### Examples of decisions on tactics for improving availability and performance
- Using ping/echo or heartbeat strategy for detecting faults with external systems?

- In case of system exhaustion when receiving external events, do we reduce the sampling rate or prioritize the source events?


### Examples of decisions on patterns/styles

- *Model View Controller (MVC) or Pipe and Filters for structuring our software components?*

- *Monolith N-tier application or a microservice architecture?*


### Examples of decisions on coding

- *Google/Microsoft/Mozilla C++ coding styles?*

- *Code organization by domain responsibilities or technical responsibilities?*


## Architectural decision log

- It is very important to **document** the **architecture decisions** on a repository (e.g.: document, wiki page, plain file, structured data store, etc.) that collects them.

- These decisions must be defined using a proper **format** and **structure**, for example, using Y-statements or MADRs.


## Y-statement template
*In the context of **\<use case\/user story u\>**, facing **\<concern c\>** we decided for **\<option o\>** and neglected **\<other options\>**, to achieve **\<system qualities/desired consequences>**, accepting **<downside/undesired consequences>**, because **\<additional rationale\>**.*

### Example of a Y-statement template

*In the context of the **Web shop service**, facing the need to **keep user session data consistent and current across shop instances**, we decided for the **Database Session State pattern** and against **Client Session State** or **Server Session State** to achieve **data consistency and cloud elasticity**, accepting **that a session database needs to be designed and implemented**.*


## Markdown Architecture Decision Records (MADR)

- This is a proposal for documenting Architecture Decision Records using Markdown, a lightweight markup language for creating formatted text using a plain-text editor.


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


# Architecture description languages

## Architecture description languages (ADLs)

- **Formal languages** targeted at designing software architectures

- Define the **components** conforming to our system, the **arrangement** of those structural elements, and the ways they **communicate** with each other.

- Examples: AADL, ABACUS, ACME, or xADL.

### Architecture Analysis and Design Language (**AADL**)

- A specification dedicated to the modeling and analysis of **real-time, safety-critical, embedded systems**.

- This standard provides software, hardware, and system component **abstractions** to specify and analyze the **systems** and map onto computational **hardware elements**.
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


# Architecture models


### Sketches

The first approach to software architecture


![](./figures/Sketch.jpg)


### Diagrams

Sketches are later beautified with diagramming tools *PowerPoint\**.


![](./figures/SampleArchitecture.png)


## Problems with sketches

- Boxes and arrows with inconsistent notation: colors, shapes
- Ambiguous naming,
- Unlabelled relationships,
- Mixed abstractions,
- etc.


## Modeling languages

- Provide well-defined **semantics** and unambiguous **understanding**.
- Examples: **UML** and **SysML**
- **Model-Based** Software/System Engineering (MBSE)
  -  **Model-Driven** Software/System Engineering (MDSE)
- Architectural **models** created with those languages capture totally or partially the **design decisions** during the creation of the system architecture.


## Unified Modeling Language (UML)

- **Open** standard for software development **lifecycle modeling**.
- Supported by many open and proprietary (web/desktop) **tools**.
- Provides a set of **visual** notations to represent system  **structure** and **behavior**.
- **Use case diagrams** or **class diagrams** are commonly used for requirements engineering purposes.


## Tool support
### Graphical modeling environments
- Creation of visual models by dragging and dropping modeling elements
- Examples: *ArgoUML, Enterprise Architect, Visual Paradigm, Modelio, etc.*

### Modelio
![](./figures/Modelio.png)


## Tool support
### Textual modeling environments

- Creation of visual models by writing code
- Examples: *PlantUML or yUML*

### PlantUML

![](./figures/PlantUML.png)


## Problems with models

- One only representation is **not** usually **enough** to correctly represent the static and dynamic nature of the architecture
    - Harmonize the models at different levels of abstraction and points of view.


- They are often **not in sync** with the code.
  >[!NOTE]
>Source code rapidly changes to address changes in requirements, visual models are not updated at the same pace.


- There are tools for generating visual models directly from the source code via reverse engineering, but they generally include too many **details**.
  >[!NOTE]
> However, the diagrams generated usually include too many details, hiding seeing the overall architecture.


![Background image](./figures/UML_Diagrams.jpeg)
### UML diagrams
There are 14 types of diagrams. We need for guidelines regarding the number and type of notations to use.


# Frameworks for creating architectures

## Approaches for conducting the architecture design process
- Propose the use of a specific set of models and textual descriptions for representing different software **views** at different levels of **abstraction** and **viewpoints**.
- The most prominent ones:
  - **4+1 architectural view model**, by *Philippe Kruchten*;
  - **Arc42 templates**, by *Starke, Hruschka and Müller*;
  - and ...


## C4 model

- C4 model is an approach, devised by *Simon Brown*, for describing and **communicating** software architectures to different types of **audiences**.

- Follows the idea of *Google Maps**, i.e., to see the system as a **map** at various levels of detail.

- **Abstraction**-first approach and **notation independent**

- Simple diagrams based on **boxes** and **lines**, but can be also created with **UML/SysML** with the appropriate use of packages, components and stereotypes.


![Background image](./figures/C4_Abstractions.png)


## Level 1: System Context diagram

This diagram focuses on **people** (actors, roles, personas, etc) and **software systems** rather than technologies, protocols, and other low-level details. It's the sort of diagram that you could show to **non-technical people**. This includes the software system you are modeling, and the other software systems upon which your software system **depends** (or vice versa).

## Software System
The highest level of abstraction and describes something that delivers value to its users, whether they are human or not.

### Example of a System Context diagram
![](./figures/C4_SystemContext.png)


![Background image](./figures/C4_Abstractions.png)


## Level 2: Container Diagram
This diagram shows the **high-level shape** of the software architecture and how **responsibilities** are distributed across it. It is useful for software developers and support/operations staff alike. The diagram shows the **major technology choices** and how the containers **communicate** with one another.


## Container

A separately runnable/deployable unit

- Not *Docker*!
- **GUI apps**: desktop apps, mobile apps, web apps
- **Non-GUI apps**: console apps, shell scripts, serverless functions, etc.
- **Datastores**: database, blob or content stores, file systems.


### Example of a Container Diagram
![](./figures/C4_Containers.png)


![Background image](./figures/C4_Abstractions.png)


## Level 3: Component Diagram
This diagram shows how a container is made up of **components** (the major structural building blocks), their **responsibilities**, and the **technology/implementation details**.

Its intended audience is software architects and developers.

Alternatively, we can use **UML component diagrams or UML package diagrams**.


## Component

A software component is a **unit of composition with contractually specified interfaces and explicit context dependencies**. A software component can be **deployed independently** and is subject to **third-party composition**.

*Clemens Szyperski*


## Component

Software components are libraries, i.e., **things that are independently replaceable and upgradeable**. Examples would include Java's jars, C#'s assemblies, Ruby's gems, and Javascript's modules.

*Martin Fowler*


### Example of a Component diagram  (Jar files)

![](./figures/JAR_Components.png)
*UML component diagram*


## Component <small>(alternative definition)</small>

A component is a **grouping of related functionality (classes) encapsulated behind a well-defined interface**.

Aspects such as how those components are organized (packages, modules, JAR file, DLL, namespaces, shared library, etc) is a separate and orthogonal concern. In the C4 model, components are not separately deployable units.

*Simon Brown, C4 model*


### Example of a Component diagram</small>
![](./figures/C4_Components_Modified.png)
*C4 version*


### Example of a Component diagram

![](./figures/UML_Components.png)
*UML component diagram*


### Example of a Component diagram
![](./figures/UML_Packages.png)
*UML package diagram*


![Background image](./figures/C4_Abstractions.png)


## Code

Code elements (e.g. classes, interfaces, objects, functions, database tables, etc) within the component in scope.

## Level 4: Code Diagram
This level of detail shows how each component is implemented as code, using **UML class diagrams**.

Ideally, these diagrams would be **automatically generated** using tooling (e.g. an IDE or UML modeling tool). This diagram is not recommended for the whole codebase but for the most important or complex components.

### Example of a Code Diagram (UML class diagram)
![](./figures/C4_Code.png)



## More diagrams?


## System Landscape diagram

This diagram shows the system landscape from an IT perspective. It is a high-level map of the software systems at the **enterprise-level**, considering the **organizational boundary**, **internal/external users**, and **internal/external systems**.

### Example of a System Landscape diagram
![](./figures/C4_Landscape.png)


##  Dynamic diagram
This diagram can be useful when you want to show how elements in a static model **collaborate at runtime** to implement a **user story, use case, feature**, etc.

The diagram is based upon *UML communication diagram*.

### Example of a Dynamic diagram
![](./figures/C4_Dynamic.png)


## Deployment diagram
This diagram illustrates how software systems and/or containers in the static model are mapped to **deployment nodes**. They are something like **physical infrastructure** (e.g. a physical server or device) or **virtualized/containerized infrastructure** (e.g. IaaS, PaaS, a virtual machine, a Docker container).

This deployment diagram is based upon *UML deployment diagram*.

### Example of a Deployment diagram
![](./figures/C4_Deployment.png)

##  Tooling for C4 model

- Diagramming tools:
  - Visual: *Microsoft Visio, Visual Paradigm*
  - Textual: *C4-PlantUML, c4builder*

- Modeling tools:
  - Visual: *IcePanel, Enterprise Architect, Gaphor*
  - Textual: *Structurizr*


## Let's practise..


![](./figures/Structurizr.png)
### Software architecture as code


### Practical activity

1. Create a user and a new workspace in Structurizr to design the architecture of the "*ACME Access Control System*".
2. Create the **C4 context diagram** to show that the security staff is in charge of registering and authorizing new users in the system and providing them with ID cards based on NFC technology. Both external users and invited will be able to enter ACME's premises using their cards.

### Practical activity (cont.)

3. Create the **C4 container diagram** to depict that the system is based on a headless app written in Python that reads NFC cards and communicates with an Oracle database to authorize users and log user activity. In addition, there is a Qt-desktop application for managing users stored in the database.

4. Create the **deployment diagram** to depict that the Pythons apps will run on two PLC Raspberry Pi, the database will be hosted and replicated in two machines on the ACME datacenter, and the desktop application will be running on the Security staff's computer.


<!-- Source: arquitectura-patrones.md -->
# Architecture: Architectural patterns

## Software Architecture
### Software Design

![](./figures/UCA.png)
##### Iván Ruiz Rube


# Module contents

1. Introduction to software architectures
2. Communicating architectures
3. Architectural principles
4. Architectural patterns
5. Architectural tactics


# Architectural patterns

# Lesson contents
Introduction
Architectural patterns for development
Architectural patterns for deployment
Architectural patterns for software integration


# Introduction


## Evolving software systems

- All long-lived systems evolve, so the **code** and the **architecture** both **evolve**.

- Code evolves in an **ad hoc** manner due to business pressures and developer turnover.

- It allows us to obtain results **quickly**, and maybe it is a cheap solution for **short-term** projects.

- This phenomenon is quite **common** in practice.


## Big ball of mud

- Systems that lacks a perceivable architecture and highlights its haphazardly structured, sloppy, duplicated spaghetti-code jungle.

- Significant loss of the system's quality.

- High maintenance costs, and it may even be very expensive to introduce new changes to the system.

## Big ball of mud

- This kind of software is an **anti-pattern** we must avoid.

- To avoid that, it is really important to apply the principles, tactics and patterns of good software design.

- We must keep continuous attention to architecture’s quality, which implies the need to maintain architectural conformance.


## Pattern (software development)

- A **solution** to a **problem** in a **context** and codifies specific knowledge collected from **experience** in a domain.

- These patterns form a **catalog** of predefined successful solution **skeletons** that architects can use for their new software solutions.


## Pattern scopes

  - **Idiom**: low-level patterns specific to a programming language. Describes how to implement particular aspects of components using the features of the given language.

  - **Design patterns**: provide a scheme for refining the subsystems or components of a software system, or the relationships between them.

  - **Architectural patterns**: express a fundamental structural organization schema for software systems.



## Architectural Pattern


- Collection of **architectural design decisions** that are applicable to a **recurrent design problem** in different software development contexts.

- Provides a set of predefined **architectural elements**, specifies their **responsibilities**, and includes rules and guidelines for organizing the **relationships** between them.

- Patterns are **not** mutually **exclusive**, so they can be **combined**.

- Applicating architectural patterns provides systems with an **architectural style**.


# Architectural patterns for development


## Conway’s Law

*“Organizations which design systems are constrained to produce designs which are copies of the communication structures of these organizations”*.

 *"Team assignments are the first draft of the architecture"*
 (M. Nygaard)

>[!NOTE]
> Architecture is influenced by the organizational structure of the development team.


## Classification schemes

- Language-Based Systems
- Runtime behaviour Patterns
- Repository-Based Systems
- Adaptable Systems
- ...

## Language-Based Systems

The programming paradigm of the language selected to develop the software system has a significant influence on the resulting architectural style.

- Main Program and Subroutine
- Object-Oriented Systems
- Aspect-Oriented Systems
- Event-Driven Architectures


### Main Program and Subroutine

![](./figures/Style_MainProgram.png)


#### Main Program and Subroutine

- Used with **procedural programming languages**, such as C.

- Each **subroutine** may have its own local variables and to access data outside its scope, data may be passed into the subroutines as parameters.

- This style is best suited for **computation-focused systems** and promotes modularity and function reuse.


### Object-Oriented Systems
![](./figures/Style_OO.jpeg)


#### Object-Oriented Systems

- Systems to support real-world processes are commonly **complex problems** and require the use of **abstract data types** (e.g.: classes),

- Systems are composed of **objects** that are instances of certain classes. Objects interact with each other through the use of their **methods**.

- Our pieces of source code can be **named** using meaningful names in the domain context. However, not all situations will have easily identifiable classes.

- Use of **object-oriented languages**, such as C++, Java, C#.


### Aspect-Oriented Systems
![](./figures/Style_AOP.png)


#### Aspect-Oriented Systems

- Aspect-oriented programming (AOP) enables to add behavior to existing code **without modifying** the code itself.

- Separation of **cross-cutting concerns**: logging, monitorization, security, internationalization, transaction management.

- So, instead of including these features in every required module, we include them in a special module that later is mixed with the production code in **compilation** time.

- This **interweaving** process is done by means of language extensions, such as AspectC++ or Java Spring.


### Event-driven architectures

![](./figures/Style_EventBased.png)

#### Event-driven architectures

- **Asynchronous** communication via an **event bus** (message channel).

- This follows the **event-driven programming paradigm**, in which functions take the form of event generators and even consumers.

- **Events** are signals, user inputs, commands, messages, or data.

- **Low coupling**: the event producers do not know anything about the consumers.

- **High efficiency**: events are published without waiting for the termination of any process.

- **Complexity**: race conditions and more difficult to debug.

#### EDA: *related patterns*

##### Command Query Responsibility Segregation (CQRS)

 - Different models to update and to read information from a datastore, so a command bus and query bus are needed.
 - Recommendable for handling high-performance applications

##### Event Sourcing (ES)
- Every change to the state of an application is captured in an event object and stored in the sequence they were applied
- This leads to a number of facilities, such as event replies or temporal queries.


## Runtime behavior patterns

Under this umbrella, we consider the patterns that show components and connectors:
- Pipes and Filters
- Model View Controller
- Process control systems.


### Pipes and filters

![](./figures/Style_PipesFilters.png)


#### Pipes and filters

- Suitable for systems of data **flow processing**, compilers, unix pipelines, etc.

- Components (filters) **read** from an input source, **transform** data, and **write** to an output store by means of buffers (pipes)

- **Agreement** on the exchange data format and comms. protocol

- Easy to **change** filters without altering the rest of the components.

- It enables **concurrent** execution and **unit testing** in an easy way.

- These systems do **not** provide any kind of **user interaction**

- They can suffer **backpressure**,



### Model View Controller (MVC)
![](./figures/Style_MVC.png)


#### Model View Controller

- Separation of concerns:
  - **models** contain the data and the business logic,
  - **views** are in charge of presenting data and the set of available actions for the user,
  - **controllers** handle user events.

- We could provide **multiple views** for the same model.

- The most popular architecture style used on the **web**, and most common **web development frameworks**.

- Variants: *Model View Presenter, Model View ViewModel, Model View Update, Presentation-Abstraction-Control,* etc.


### Process Control Systems
![](./figures/Style_ControlProcess.png)

Feedforward control: MAPE-K structure


#### Process Control Systems
- Used for managing **processes** with impact on the **physical** world and video game development.

- **Feedback loop** is the most basic form of process control.
  - a sensor monitoring an external measurement or data input,
  - a controller (running continuously) managing the system logic,
  - and an actuator in charge of manipulating the process.

- **Feedforward control**: processes in series in which information from an upstream process can be used to control a downstream process.


## Repository-based Systems

Modern software systems require sharing data among their components. These systems commonly use a data store to keep persistent the state of the components.
- Blackboard
- Share data architecture
- Layered architecture
- Clean architectures


### Blackboard
![](./figures/Style_Blackboard.png)

~~

#### Blackboard

- Useful for complex problems (AI) for which **no deterministic** solution strategies are known: artificial vision, natural language processing, or voice recognition. This pattern does not provide warranties to find the right solution.

- **Knowledge sources** are subsystems that run algorithms that read outside information and solve part of the problem.

- The **blackboard** is a central data repository that aggregates data from the sources and provides approximate solutions that continually are getting better.

- The **control** component to manage tasks and check the work state.


### Share data architecture
![](./figures/Style_ShareData.png)

#### Share data architecture

- Data is often stored in a **centralized** repository, usually a database management system.
- The repository is in charge of maintaining **data consistency**.
- The software components are **independent** of each other, avoiding any coupling, and interact among themselves not directly, but through the data store.
- The components launch queries and transactions against the data repository.


### Layered architecture

![](./figures/Style_Layers_Linux.png)
Linux architecture


![](./figures/Style_Layers_VBox.jpg)
Virtual Box architecture


![](./figures/Style_Layers_3Simple.png)

Three-Layers


![](./figures/Style_Layers_3Java.png)
Layered architecture and MVC


![](./figures/Style_Layers_3DSS.png)

Three-Layers (*DSS*-style)


#### Layered architecture

- One of the most **widely used** architectural patterns.

- Divide the software into (relaxed/strict) **layers** so that each layer exposes an interface to be used from the above layers.

- A layer is a **conceptual separation** of the software, which may be composed of several modules.

- Easy layer **replaceability**

- Good **testability**

- This style can lead to the **sinkhole anti-pattern**


----


### **Clean** architectures

**hexagonal** architecture
**ports and adapter** pattern
**onion** architecture


![Background image](./figures/Style_Clean.jpeg)
![Background image](./figures/Style_Hexagonal.png)
![Background image](./figures/Style_Onion.png)


#### Clean architectures

- **Domain-driven design**:
  - Intensive usage of a common vocabulary for both the software documentation and source code.
  - Organization by domain rather than by technological capabilities.
  - Domain classes must be responsible for implementing the business logic, not only being mere data containers.


- **Dependency inversion**:
  - Domain model must not have dependencies with any kind of technology, infrastructure or external systems.


----
#### Clean architectures

![](./figures/Style_PortsAdapters.png)


![](./figures/Style_Clean_DSS.png)

*Dependency diagram* of a Clean Architecture (DSS-style)


## Adaptable systems
Adaptable systems are those whose architectures can dynamically adapt to changing requirements. We consider the folllowing:

- Plugin architecture
- Metaprogramming systems


### Plugin architecture

![](./figures/Style_Plugin.png)


#### Plugin architecture

- a.k.a *microkernel architecture*.

- The **kernel** only provides the basic features, whereas the rest are provided by the external components (**plugins**).

- Enables **extending** our systems to provide new features initially not foreseen by the developers.

- The base system must include a **runtime** **engine** in charge registrating, removing, starting, and stoping the plugins.

- The global system may be affected by security vulnerabilities coming from external plugins.

- Used on IDEs, ERPs, LMSs, browsers, etc.


### Metaprogramming systems

![](./figures/Style_Metaprogramming.png)

#### Metaprogramming systems

- Reflective programming or metaprogramming is the ability of a process to examine, introspect, and modify its own structure and behavior.

- Most programming languages support reflection.

- Systems able to adapt their behavior without changing  code.

- Related to Model-Driven Development (MDD): automatically generate code from a (visual/textual) model created with a general-purpose (UML/SysML) o Domain-Specific Language.
  - Non-technical expert users can write their own programs (or part of them) by using DSLs


# Architectural patterns for deployment

## Designing the deployment architecture

 - Selecting the **hardware** instances (physical or virtualized) where software components will be deployed and run.
 - Decide the **number** of nodes and their **characteristics**, namely: *CPU, memory, storage, and network bandwidth, among other features*.


### Centralized architecture
- The most **simple** architecture for software deployment

- The software is installed on a single computer (laptop or desktop).

- Applications can be based on a command-line interface or on a graphical user interface.

- With portable languages, we can create OS-independent apps.

- **Good performance**: there is not any network delay.

- Software **updates** are more complicated to perform.

- Rarely operate in **isolation**.

#### Centralized architecture

![](./figures/Style_Centralized.png)


### Peer to Peer (P2P) architecture
- **Distributed** application architecture that partitions tasks or workloads between peers.
- There is no main node, but all nodes are **equally privileged** and autonomous.
- This approach **avoids** having a single point of **failure**.
- It may be more complex to maintain the system **state**.
- Successfully used in s*ensor networks, collaborative systems, video conferencing systems, blockchain, file exchanging systems, etc*.

#### Peer to Peer (P2P) architecture

![](./figures/Style_Peer2peer.png)


### Master-slave architecture
- This pattern  promotes dividing the work in **identical subtasks** deployed on different nodes (slaves).
- The **main node** (master) is in charge of coordinating execution, collecting results and combining them.
- This enables **parallel computation** and improves **fault tolerance**.
- This deployment architecture can be suitable for process control systems, embedded systems, search systems, etc.


#### Master-slave architecture
![](./figures/Style_MasterSlave.png)


### Client-server architecture
- Very common in **legacy** applications and the most recent **apps**.
- Systems are split into several **physical tiers**, so
- Typically, a client launches **requests** to the server that processes them and returns a **response** through the **network** protocol.

- Common technologies are CORBA, RMI, **HTTP**, SOAP, REST, etc.

- **Easy software distribution**

- It exposes a **single point of failure** that must be protected.

- Unpredictable performance, because it depends on the network **quality of service**.


#### Client-server architecture (2-tiers)

![](./figures/Style_ClientServer.png)
Classic architecture for web apps. With **vertical scaling**, we can cope with new demands by adding more power (CPU, memory, etc.)

#### Client-server architecture (4-tiers)

![](./figures/Style_ClientServerNTier.png)

Architecture for high-demand environments with  **horizontal scaling** to increase resilience, fault tolerance, and performance.


### Monoliths
- The previous systems can be considered **monoliths**: the app servers run the **same program** in all of them.

- They are relatively **easy to develop and deploy**.

- **No suitable for very big projects** with enormous workloads and lots of features developed by many functional areas.

- It forces the use of the **same technology stack** for the project lifetime, **hinders scaling** the application effectively,

- The code base may **grow** dramatically, slowing IDEs, making continuous deployment difficult, and intimidating new devolopers.


 ### Microservices

- Martin Fowler defined **microservices** as *small, autonomous services that work together*.

- Promotes creating applications based on **independent services** written in **different languages** running on different **machines** and using separate **databases**.

- Microservices brought us **new problems** and a set of solutions in the form of **design patterns**, such as *Service Registration, Service Discovery, Circuit Breaker, API Composition, etc.*

#### Microservices
![](./figures/Style_Microservices.png)


# Architectural patterns for software integration


## Interoperability

Interoperability is the degree to which two or more systems, products, or components can exchange information and use the information that has been exchanged.

*ISO/IEC 25000*

## System integration purposes

- Create new applications by combining existing ones (software mashups)
- Provide a unique, curated data repository by unifying data coming from heterogeneous sources;
- Maintain data consistency among different systems;
- Orchestrate business process deployed on different applications.

## Interoperability strategies

- Interoperability can be visualized from different dimensions:
  - **Organizational**: collaboration among different stakeholders involved.
  - **Semantic**: meaning of the exchanging data.
  - **Syntactic**: data formats and structure.

- From the syntactic interoperability perspective, we can distinguish some **integration styles**. These styles result from the application of the existing **architectural patterns** for single systems.


### File transfer

- One application **generates** a data file (XML, JSON, CSV, or a binary file) which is later **consumed** by another application.
- Applications can be developed regardless of one another.
- They have only to **agree** with the format of the **file to exchange**.
- The import/export processes can be developed by using the **pipes and filters pattern**.


### Shared database
- **To maintain data consistency among different systems**
  - Using the **same database** for the different applications.
  - **Conflicts** may arise among the different development teams.
  - **Bottleneck** when multiple applications access the same data.
  - It is the **shared data pattern** but for independent systems.

- **To provide a unique, curated data repository**

  - Data warehouses (DWH) that unifies data coming from heterogeneous source for analytical purposes
  - The **pipes and filters pattern** is commonly used to create the integration process for populating DWHs.


### Remote procedure call (RPC)

- Applications (probably created with different technologies) **expose** services that are **consumed** by other applications by issuing calls through a network.

- That is also called a **service-oriented architecture**, which shares the idea and the technology with the **microservices architecture**, but is targeted to different systems.

- There are two alternatives to enable RPC communication:
  - **Direct** connection: using SOAP WS, REST, gRPC, etc,
  - Indirect communication: using an **Enterprise Service Bus** (ESB) middleware, such as Open ESB, Mule ESB, etc.


### Messaging

- It is an **event-driven architecture** for multiple systems.
- Applications communicate **asynchronously** with each other by producing and consuming messages to/from a message channel.
- The message channel can be implemented via messaging platforms such as ZeroMQ, RabbitMQ, and ActiveMQ, or with data distribution services for real-time systems, such as OpenDDS.


<!-- Source: arquitectura-principios.md -->
# Architecture: Design principles

## Software Architecture
### Software Design

![](./figures/UCA.png)
##### Iván Ruiz Rube


# Module contents

1. Introduction to software architectures
2. Communicating architectures
3. Architectural principles
4. Architectural patterns
5. Architectural tactics


# Architectural design principles

# Lesson contents
Introduction
Software components
Component cohesion principles
Component coupling principles


# Introduction

## Introduction

- The quality of a system is the degree to which the system satisfies the stated and implied **needs** of its various **stakeholders**, and thus provides value.

- **Team members** (developers, testers, etc.) are also stakeholders in the project.

- This section is about principles to improve system **maintainability**, which is key to the success of good software architecture.


## Maintainability

- **Modularity**: Degree to which a system or computer program is composed of discrete components such that a change to one component has minimal impact on other components.

- **Reusability**: Degree to which an asset can be used in more than one system, or in building other assets.

- **Analysability**: Degree of effectiveness and efficiency with which it is possible to assess the impact on a product or system of an intended change to one or more of its parts.

## Maintainability (cont.)

- **Modifiability**: Degree to which a product or system can be effectively and efficiently modified without introducing defects or degrading existing product quality.

- **Testability**: Degree of effectiveness and efficiency with which test criteria can be established for a system, product or component and tests can be performed to determine whether those criteria have been met.


# Components

## Achieving modularity...

- The **class** is too fine-grained as a unit of organization.

- Components are **logical groupings** of statements that can be imported into other programs.

- Pieces of software providing a set of **responsibilities**: Each component must have an **interface** describing how to use it and the body with its **implementation**.

## A definition of components?

- The word **component** is a hugely overloaded term in the software development industry,

- Szyperski defined as: a *unit of composition (binary) with contractually specified interfaces and explicit context dependencies only. A software component can be deployed independently and is subject to composition by third parties.*

- C4 model: *grouping of related functionality encapsulated behind a well-defined interface, and it is not separately deployable*.

## A common terminology?

- Each programming language and each developer community have their own terminology to define the mechanisms to create and reuse components.
- Examples:
  - Java: source code is organized into packages. Source code can be packed into JAR files.
  - C++: source code is organized via namespaces. Source code can be packed into C++ libraries (.a, .dll, .dylib files).



![Background image](./figures/Lego.jpg)

## Component-based Software Engineering
Connecting bricks together and following certain rules about how they can and cannot be interconnected is not unlike writing program code and using software interfaces.


# Component cohesion principles


## Cohesion principles

- **High cohesion** and **low coupling** as the main design drivers for simple system architectures, which have to be easy to understand, change, and maintain.

- **Cohesion** represents the degree to which a part of a codebase forms a **logically single, atomic software unit** (a method, a class, a group of classes, a component, etc.).

- Low cohesion makes components more difficult to maintain, test, and reuse.


### REP: Reuse/Release Equivalence Principle

"The granule of reuse is the granule of release. In other words, either all of the classes inside the package are reusable, or none of them are."

*Robert C. Martin*

#### Releasing software components

- Reusing software components is **not copying and pasting** source code
- Developers of the reusable code must **package** source code, **distribute** them as products and **maintain** them.
- The release process must produce the appropriate notifications and release documentation.
- These processes can be automated by using **DevOps** strategies.

#### Reusing software components

- Components must be shared via specific (public/private) repositories.

- Developers to use these components must only download and import the required dependencies and start to use them.

- There are lots of dependencies management tools to help developers reuse software components:
  - CMake and Conan, for the C/C++ language
  - Maven and Gradle, for the Java language
  - etc.

#### Semantic versioning

- The components should be tracked through a release process and are given release numbers like MAJOR.MINOR.PATCH-LABEL,
- Increment the version...

  - MAJOR: when you make incompatible changes on its API
  - MINOR: when you add functionality (backward-compatible)
  - PATCH: when you make backward-compatible bug fixes.
  - LABEL: Optional metadata for pre-release.
- Example: *1.0.0-alpha < 1.0.0 < 2.0.0 < 2.1.0 < 2.1.1*


### CCP: Common Closure Principle

"Gather into components those classes that change for the same reasons and at the same times. Separate into different components those classes that change at different times and for different reasons."

*Robert C. Martin*


#### High cohesive components

- Components must be responsible for a **single mission** (maybe one only thing...) Components should **not have multiple reasons to change**.

- Thus, we should gather together into the same component those classes that are **closed to the most common types of changes** that we expect or have experienced.

- If we need to alter our code and the changes are confined to a single component, then we **redeploy only the one changed component** whilst other components do not need to be revalidated or redeployed.


### CRP: Common Reuse Principle

“Don’t force users of a component to depend on things they don’t need”.

*Robert C. Martin*


#### High cohesive components

- **Those classes that are not tightly bound to each other should not be in the same component**.
- When we depend on a component, we want to make sure we depend on every class in that component.

- Suppose that the *using* component uses only one class within the *used* component. Every time the *used* component is changed, the *using* component will likely still need to be recompiled, revalidated, and redeployed. This is true even if the *using* component doesn’t care about the change made in the *used* component.



#### Example of low cohesive component
![](./figures/LowCohesion.png)

#### Example of high cohesive components
![](./figures/HighCohesion.png)


# Component coupling principles


## Coupling principles

- **Coupling** refers to the degree of interdependence that two software units have on each other, again meaning by software unit: classes, subtypes, methods, modules, functions, libraries, etc.

- If two software units are completely independent of each other, we say that they are decoupled. Although this is the ideal situation, it rarely occurs. Therefore, the aim is to **achieve the lowest possible level of coupling**.

#### High coupling vs Low coupling

![](./figures/Coupling.jpg)

### Code dependencies

- **A component *Comp1* depends on another component *Comp2*** when one of *Comp1*'s classes has a dependency with a *Comp2*'s class.

- In turn, **a class *Class1* depends on a class *Class2*** when:

  - *Class1* inherits from the base class *Class2*
  - *Class1* has an attribute of class *Class2*
  - *Class2* is used as an input or output parameter of one the *Class1*'s functions or their functions' body.


### ADP: Acyclic Dependencies Principle

“There must be no cycles in the component dependency graph”.

*Robert C. Martin*


#### Components as units of work

- The components can be the responsibility of a single developer, or a team of developers.

- When developers get a component working they release it for use by the other developers.

- As new releases of a component are made available, other teams can decide whether they will immediately adopt the new release.

- The dependency structure of the components of a software project can be visualized as a dependency graph, but this graph should be a **Directed Acyclic Graph** (DAG), i.e., there can be no cycles.

#### Component diagram of a sample system

![](./figures/adpp-1.png)


#### Component diagram with a dependency cycle

![](./figures/adpp-2.png)

#### Breaking the cycle with the **Dependency Inversion Principle**

![](./figures/adpp-3.png)


#### Extracting the new classes into a new component

![](./figures/adpp-4.png)


### SDP: Stable Dependencies Principle

“Depend in the direction of stability”.

*Robert C. Martin*

#### Code stability
- The software architect should mold a component dependency graph to **protect stable high-value components from volatile components**.

- We must **isolate that volatile code**. For example, we don’t want that our business rules (highest-level policies) are affected by...
  - cosmetic changes to the graphical user interfaces
  - changes on data persistence technologies
  - mechanisms to communicate with external systems

![Background image](./figures/SDP_StableComponent.png)

A component with lots of incoming dependencies is very **stable** because it requires a great deal of work to reconcile any changes with all the dependent components

![Background image](./figures/SDP_UnstableComponent.png)

A component with a lot of outgoing dependencies is very **unstable**, because changes may come from many external sources

![Background image](./figures/SDP_Configuration.png)

**Ideal configuration** of component dependencies


![Background image](./figures/SDP_Configuration2.png)

Modules that are intended to be easy to change are not dependent on modules that are harder to change.


### SDP: Stable Abstractions Principle

“A component should be as abstract as it is stable”.

*Robert C. Martin*

#### Providing controlled flexibility to our components...
- Business logic and policy decisions of the system should **not change** very often due to technological **details**.
- The code that encapsulates those pieces of the software should be placed into **stable** components.
- However, this would make the overall **architecture inflexible** and the high-level policies would be difficult to change.
- The solution is using **interfaces/abstract classes** for the stable elements. So we will be able to modify the policies by implementing new classes.

**Open Closed Principle**
open for extension, but closed for modification

![](./figures/SAP_Example.png)


<!-- Source: arquitectura-tacticas.md -->
# Architecture: Tactics

## Software Architecture
### Software Design

![](./figures/UCA.png)
##### Iván Ruiz Rube


# Module contents

1. Introduction to software architectures
2. Communicating architectures
3. Architectural principles
4. Architectural patterns
5. Architectural tactics


# Architectural tactics

# Lesson contents
Introduction
Quality attribute requirements
Tactics for improving quality


# Introduction

## Introduction
- The **quality** of a system is the degree to which the system satisfies the stated and implied needs of its various stakeholders, and thus provides value.

- Those stakeholders' needs are precisely what is represented in a quality **model**, which categorizes the product quality into characteristics and sub-characteristics.

- Functionality and quality characteristics of software architecture are orthogonal.


### ISO/IEC 25010 Product quality model
![](./figures/iso25010.png)


### Functional Suitability
Degree to which a product or system provides functions that meet stated and implied needs when used under specified conditions.

- Functional completness
- Functional correctness
- Functional appropriatness


### Performance efficiency
  Performance relative to the amount of resources used under stated conditions.

- Time behaviour
- Resource utilization
- Capacity

### Compatibility
Degree to which a product, system or component can exchange information with other products, systems or components, and/or perform its required functions while sharing the same hardware or software environment.

- Co-existence
- Interoperability


### Usability
Degree to which a product or system can be used by specified users to achieve specified goals with effectiveness, efficiency and satisfaction in a specified context of use.

- Appropriateness recognizability
- Learnability
- Operability
- User error protection
- User interface aesthetics
- Accessibility


### Reliability
Degree to which a system, product or component performs specified functions under specified conditions for a specified period of time.

- Maturity
- Availability
- Fault tolerance
- Recoverability


### Security

Degree to which a product or system protects information and data so that persons or other products or systems have the degree of data access appropriate to their types and levels of authorization.

- Confidentiality
- Integrity
- Non-repudiation
- Accountability
- Authenticity


### Portability
Degree of effectiveness and efficiency with which a system, product or component can be transferred from one hardware, software or other operational or usage environment to another.

- Adaptability
- Installability
- Replaceability


### Maintainability

Degree of effectiveness and efficiency with which a product or system can be modified to improve it, correct it or adapt it to changes in environment, and in requirements.

- Modularity
- Reusability
- Analysability
- Modifiability
- Testability


# Quality attribute requirements


As known as...
# Non-functional requirements

## Requiments for the non-functional requirements
The quality attribute requirements should be...
**unambiguous**,
**measurable**,
**testable**,
and defined using **scenarios**...


## Scenarios for quality attribute requirements

- **Stimulus**: an event arriving at the system or the development team
- **Stimulus source**: the source of the event may affect how it is treated by the system
- **Response**: how the system should respond in response to the stimulus for runtime qualities or what developers should do for development-time qualities
- **Response time**: for determining whether a response is satisfactory
- **Environment**: the set of circumstances in which the scenario takes place
- **Artifact**: the portion of the system to which the req. applies

## Quality scenario

![](./figures/QualityScenario.jpeg)


### Example of a **performance** scenario

*Users initiate transactions under normal operations. The system processes the transactions with an average latency of two seconds.*

### Example of a **modifiability** scenario

*The developer wishes to change the user interface by modifying the code at design time. The modifications are made with no side effects within three hours.*




# Architectural tactics


## Architectural tactics

- A tactic is a design decision that influences the achievement of a quality attribute requirement.

- Tactics directly affect the system's response to some stimulus.

- These tactics are, to a large extent, dependent on the kind of system and they are continuously evolving.


### Let's see an incomplete catalog of tactics...


![Background image](./figures/Tactics_Availability.png)

![Background image](./figures/Tactics_Performance.png)

![Background image](./figures/Tactics_Security.png)


![Background image](./figures/Tactics_Interoperability.png)

![Background image](./figures/Tactics_Usability.png)

![Background image](./figures/Tactics_Testability.png)

![Background image](./figures/Tactics_Modifiability.png)

