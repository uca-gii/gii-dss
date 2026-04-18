---
marp: true
title: "Architecture: Architectural patterns"
author: "Ivan Ruiz, Andres Muñoz & Juan Manuel Dodero"
date: 2021-05-05
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

# Architectural patterns

---
# Lesson contents
Introduction
Architectural patterns for development
Architectural patterns for deployment
Architectural patterns for software integration

<!-- paginate: true -->


---
<!-- _class: lead -->

# Introduction


---

## Evolving software systems

- All long-lived systems evolve, so the **code** and the **architecture** both **evolve**. 

- Code evolves in an **ad hoc** manner due to business pressures and developer turnover.

- It allows us to obtain results **quickly**, and maybe it is a cheap solution for **short-term** projects.

- This phenomenon is quite **common** in practice. 

  
---
## Big ball of mud

- Systems that lacks a perceivable architecture and highlights its haphazardly structured, sloppy, duplicated spaghetti-code jungle.  

- Significant loss of the system's quality. 

- High maintenance costs, and it may even be very expensive to introduce new changes to the system. 

---
## Big ball of mud

- This kind of software is an **anti-pattern** we must avoid. 

- To avoid that, it is really important to apply the principles, tactics and patterns of good software design. 
  
- We must keep continuous attention to architecture’s quality, which implies the need to maintain architectural conformance.



---

## Pattern (software development)

- A **solution** to a **problem** in a **context** and codifies specific knowledge collected from **experience** in a domain. 
  
- These patterns form a **catalog** of predefined successful solution **skeletons** that architects can use for their new software solutions.
  
---

## Pattern scopes

  - **Idiom**: low-level patterns specific to a programming language. Describes how to implement particular aspects of components using the features of the given language.
  
  - **Design patterns**: provide a scheme for refining the subsystems or components of a software system, or the relationships between them.
  
  - **Architectural patterns**: express a fundamental structural organization schema for software systems. 
  

---

## Architectural Pattern


- Collection of **architectural design decisions** that are applicable to a **recurrent design problem** in different software development contexts. 

- Provides a set of predefined **architectural elements**, specifies their **responsibilities**, and includes rules and guidelines for organizing the **relationships** between them.

- Patterns are **not** mutually **exclusive**, so they can be **combined**. 

- Applicating architectural patterns provides systems with an **architectural style**.


---
<!-- _class: lead -->

# Architectural patterns for development 




---
## Conway’s Law

*“Organizations which design systems are constrained to produce designs which are copies of the communication structures of these organizations”*. 
 
 *"Team assignments are the first draft of the architecture"* 
 (M. Nygaard)

<!-- Architecture is influenced by the organizational structure of the development team.-->



---
## Classification schemes

- Language-Based Systems
- Runtime behaviour Patterns
- Repository-Based Systems
- Adaptable Systems
- ...
---

## Language-Based Systems

The programming paradigm of the language selected to develop the software system has a significant influence on the resulting architectural style.

- Main Program and Subroutine
- Object-Oriented Systems
- Aspect-Oriented Systems
- Event-Driven Architectures


---
### Main Program and Subroutine

![w:600](./figures/Style_MainProgram.png)


---

#### Main Program and Subroutine

- Used with **procedural programming languages**, such as C.
   
- Each **subroutine** may have its own local variables and to access data outside its scope, data may be passed into the subroutines as parameters.

- This style is best suited for **computation-focused systems** and promotes modularity and function reuse. 



---
### Object-Oriented Systems
![w:1100](./figures/Style_OO.jpeg)
 

---
#### Object-Oriented Systems

- Systems to support real-world processes are commonly **complex problems** and require the use of **abstract data types** (e.g.: classes),

- Systems are composed of **objects** that are instances of certain classes. Objects interact with each other through the use of their **methods**. 

- Our pieces of source code can be **named** using meaningful names in the domain context. However, not all situations will have easily identifiable classes.

- Use of **object-oriented languages**, such as C++, Java, C#.


---

### Aspect-Oriented Systems 
![w:750px](./figures/Style_AOP.png)


---
#### Aspect-Oriented Systems

- Aspect-oriented programming (AOP) enables to add behavior to existing code **without modifying** the code itself. 

- Separation of **cross-cutting concerns**: logging, monitorization, security, internationalization, transaction management.

- So, instead of including these features in every required module, we include them in a special module that later is mixed with the production code in **compilation** time.

- This **interweaving** process is done by means of language extensions, such as AspectC++ or Java Spring. 



---
### Event-driven architectures

![w:1100px](./figures/Style_EventBased.png)

--- 
#### Event-driven architectures

- **Asynchronous** communication via an **event bus** (message channel).
  
- This follows the **event-driven programming paradigm**, in which functions take the form of event generators and even consumers. 
  
- **Events** are signals, user inputs, commands, messages, or data.

- **Low coupling**: the event producers do not know anything about the consumers. 

- **High efficiency**: events are published without waiting for the termination of any process. 

- **Complexity**: race conditions and more difficult to debug.
  
<!--
---
#### EDA: *related patterns*

##### Command Query Responsibility Segregation (CQRS)

 - Different models to update and to read information from a datastore, so a command bus and query bus are needed.
 - Recommendable for handling high-performance applications

##### Event Sourcing (ES)
- Every change to the state of an application is captured in an event object and stored in the sequence they were applied
- This leads to a number of facilities, such as event replies or temporal queries.

-->




---

## Runtime behavior patterns

Under this umbrella, we consider the patterns that show components and connectors:
- Pipes and Filters
- Model View Controller
- Process control systems.

---

### Pipes and filters

![w:1000px](./figures/Style_PipesFilters.png)


---

#### Pipes and filters

- Suitable for systems of data **flow processing**, compilers, unix pipelines, etc. 
  
- Components (filters) **read** from an input source, **transform** data, and **write** to an output store by means of buffers (pipes)
  
- **Agreement** on the exchange data format and comms. protocol  
	
- Easy to **change** filters without altering the rest of the components. 
  
- It enables **concurrent** execution and **unit testing** in an easy way. 
  
- These systems do **not** provide any kind of **user interaction**

- They can suffer **backpressure**,
  

---

### Model View Controller (MVC)
![w:450px](./figures/Style_MVC.png)


---

#### Model View Controller 

- Separation of concerns: 
  - **models** contain the data and the business logic, 
  - **views** are in charge of presenting data and the set of available actions for the user, 
  - **controllers** handle user events. 

- We could provide **multiple views** for the same model.

- The most popular architecture style used on the **web**, and most common **web development frameworks**.

- Variants: *Model View Presenter, Model View ViewModel, Model View Update, Presentation-Abstraction-Control,* etc. 






---

### Process Control Systems
![w:1100px](./figures/Style_ControlProcess.png)

Feedforward control: MAPE-K structure



---
#### Process Control Systems 
- Used for managing **processes** with impact on the **physical** world and video game development.
  
- **Feedback loop** is the most basic form of process control.  
  - a sensor monitoring an external measurement or data input, 
  - a controller (running continuously) managing the system logic, 
  - and an actuator in charge of manipulating the process. 

- **Feedforward control**: processes in series in which information from an upstream process can be used to control a downstream process.   



---

## Repository-based Systems

Modern software systems require sharing data among their components. These systems commonly use a data store to keep persistent the state of the components. 
- Blackboard
- Share data architecture
- Layered architecture
- Clean architectures



---
### Blackboard 
![w:900px](./figures/Style_Blackboard.png)

~~

---
#### Blackboard 

- Useful for complex problems (AI) for which **no deterministic** solution strategies are known: artificial vision, natural language processing, or voice recognition. This pattern does not provide warranties to find the right solution.
   
- **Knowledge sources** are subsystems that run algorithms that read outside information and solve part of the problem.

- The **blackboard** is a central data repository that aggregates data from the sources and provides approximate solutions that continually are getting better.

- The **control** component to manage tasks and check the work state. 


---
### Share data architecture
![w:850px](./figures/Style_ShareData.png)

---
#### Share data architecture

- Data is often stored in a **centralized** repository, usually a database management system. 
- The repository is in charge of maintaining **data consistency**. 
- The software components are **independent** of each other, avoiding any coupling, and interact among themselves not directly, but through the data store. 
- The components launch queries and transactions against the data repository. 




---
### Layered architecture

![w:700px](./figures/Style_Layers_Linux.png)
Linux architecture

---

![w:700px](./figures/Style_Layers_VBox.jpg)
Virtual Box architecture

---

![w:270px](./figures/Style_Layers_3Simple.png)

Three-Layers

---

![w:400px](./figures/Style_Layers_3Java.png)
Layered architecture and MVC


---
![w:400px](./figures/Style_Layers_3DSS.png)

Three-Layers (*DSS*-style)

---

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


---
![bg fit](./figures/Style_Clean.jpeg)
![bg fit](./figures/Style_Hexagonal.png)
![bg fit](./figures/Style_Onion.png)

---

#### Clean architectures

- **Domain-driven design**: 
  - Intensive usage of a common vocabulary for both the software documentation and source code.
  - Organization by domain rather than by technological capabilities. 
  - Domain classes must be responsible for implementing the business logic, not only being mere data containers.


- **Dependency inversion**: 
  - Domain model must not have dependencies with any kind of technology, infrastructure or external systems. 


----
#### Clean architectures

![w:850px](./figures/Style_PortsAdapters.png)


---
![w:500px](./figures/Style_Clean_DSS.png)

*Dependency diagram* of a Clean Architecture (DSS-style) 


---

## Adaptable systems
Adaptable systems are those whose architectures can dynamically adapt to changing requirements. We consider the folllowing:

- Plugin architecture
- Metaprogramming systems

---

### Plugin architecture

![w:1100px](./figures/Style_Plugin.png)


---
#### Plugin architecture

- a.k.a *microkernel architecture*. 
  
- The **kernel** only provides the basic features, whereas the rest are provided by the external components (**plugins**). 

- Enables **extending** our systems to provide new features initially not foreseen by the developers. 

- The base system must include a **runtime** **engine** in charge registrating, removing, starting, and stoping the plugins. 
  
- The global system may be affected by security vulnerabilities coming from external plugins. 

- Used on IDEs, ERPs, LMSs, browsers, etc.


---
### Metaprogramming systems

![w:1100px](./figures/Style_Metaprogramming.png)

---
#### Metaprogramming systems

- Reflective programming or metaprogramming is the ability of a process to examine, introspect, and modify its own structure and behavior. 
  
- Most programming languages support reflection.

- Systems able to adapt their behavior without changing  code.

- Related to Model-Driven Development (MDD): automatically generate code from a (visual/textual) model created with a general-purpose (UML/SysML) o Domain-Specific Language. 
  - Non-technical expert users can write their own programs (or part of them) by using DSLs




---
<!-- _class: lead -->

# Architectural patterns for deployment 

---
## Designing the deployment architecture
 
 - Selecting the **hardware** instances (physical or virtualized) where software components will be deployed and run. 
 - Decide the **number** of nodes and their **characteristics**, namely: *CPU, memory, storage, and network bandwidth, among other features*.

---

### Centralized architecture
- The most **simple** architecture for software deployment 
  
- The software is installed on a single computer (laptop or desktop). 
  
- Applications can be based on a command-line interface or on a graphical user interface. 

- With portable languages, we can create OS-independent apps. 

- **Good performance**: there is not any network delay. 
  
- Software **updates** are more complicated to perform.

- Rarely operate in **isolation**.

---
#### Centralized architecture

![w:450px](./figures/Style_Centralized.png)

---

### Peer to Peer (P2P) architecture
- **Distributed** application architecture that partitions tasks or workloads between peers. 
- There is no main node, but all nodes are **equally privileged** and autonomous. 
- This approach **avoids** having a single point of **failure**. 
- It may be more complex to maintain the system **state**.
- Successfully used in s*ensor networks, collaborative systems, video conferencing systems, blockchain, file exchanging systems, etc*.

---
#### Peer to Peer (P2P) architecture

![w:700px](./figures/Style_Peer2peer.png)

---

### Master-slave architecture
- This pattern  promotes dividing the work in **identical subtasks** deployed on different nodes (slaves). 
- The **main node** (master) is in charge of coordinating execution, collecting results and combining them. 
- This enables **parallel computation** and improves **fault tolerance**. 
- This deployment architecture can be suitable for process control systems, embedded systems, search systems, etc.

---

#### Master-slave architecture
![w:850px](./figures/Style_MasterSlave.png)


---

### Client-server architecture
- Very common in **legacy** applications and the most recent **apps**. 
- Systems are split into several **physical tiers**, so 
- Typically, a client launches **requests** to the server that processes them and returns a **response** through the **network** protocol.  

- Common technologies are CORBA, RMI, **HTTP**, SOAP, REST, etc. 

- **Easy software distribution** 
  
- It exposes a **single point of failure** that must be protected. 
  
- Unpredictable performance, because it depends on the network **quality of service**.


---
#### Client-server architecture (2-tiers)

![w:650px](./figures/Style_ClientServer.png)
Classic architecture for web apps. With **vertical scaling**, we can cope with new demands by adding more power (CPU, memory, etc.)

---
#### Client-server architecture (4-tiers)

![w:700px](./figures/Style_ClientServerNTier.png)

Architecture for high-demand environments with  **horizontal scaling** to increase resilience, fault tolerance, and performance. 

---


### Monoliths
- The previous systems can be considered **monoliths**: the app servers run the **same program** in all of them. 

- They are relatively **easy to develop and deploy**. 

- **No suitable for very big projects** with enormous workloads and lots of features developed by many functional areas.
  
- It forces the use of the **same technology stack** for the project lifetime, **hinders scaling** the application effectively,
  
- The code base may **grow** dramatically, slowing IDEs, making continuous deployment difficult, and intimidating new devolopers. 

 
 ---
 ### Microservices

- Martin Fowler defined **microservices** as *small, autonomous services that work together*.

- Promotes creating applications based on **independent services** written in **different languages** running on different **machines** and using separate **databases**. 

- Microservices brought us **new problems** and a set of solutions in the form of **design patterns**, such as *Service Registration, Service Discovery, Circuit Breaker, API Composition, etc.*
---

#### Microservices
![w:700px](./figures/Style_Microservices.png)



---
<!-- _class: lead -->

# Architectural patterns for software integration

---

## Interoperability

Interoperability is the degree to which two or more systems, products, or components can exchange information and use the information that has been exchanged. 

*ISO/IEC 25000*

---
## System integration purposes

- Create new applications by combining existing ones (software mashups)
- Provide a unique, curated data repository by unifying data coming from heterogeneous sources; 
- Maintain data consistency among different systems; 
- Orchestrate business process deployed on different applications. 

---
## Interoperability strategies

- Interoperability can be visualized from different dimensions:
  - **Organizational**: collaboration among different stakeholders involved. 
  - **Semantic**: meaning of the exchanging data. 
  - **Syntactic**: data formats and structure. 
 
- From the syntactic interoperability perspective, we can distinguish some **integration styles**. These styles result from the application of the existing **architectural patterns** for single systems.

---

### File transfer

- One application **generates** a data file (XML, JSON, CSV, or a binary file) which is later **consumed** by another application. 
- Applications can be developed regardless of one another. 
- They have only to **agree** with the format of the **file to exchange**. 
- The import/export processes can be developed by using the **pipes and filters pattern**. 

---

### Shared database
- **To maintain data consistency among different systems** 
  - Using the **same database** for the different applications.  
  - **Conflicts** may arise among the different development teams. 
  - **Bottleneck** when multiple applications access the same data.   
  - It is the **shared data pattern** but for independent systems. 

- **To provide a unique, curated data repository** 

  - Data warehouses (DWH) that unifies data coming from heterogeneous source for analytical purposes 
  - The **pipes and filters pattern** is commonly used to create the integration process for populating DWHs.

---

### Remote procedure call (RPC)

- Applications (probably created with different technologies) **expose** services that are **consumed** by other applications by issuing calls through a network.
  
- That is also called a **service-oriented architecture**, which shares the idea and the technology with the **microservices architecture**, but is targeted to different systems.

- There are two alternatives to enable RPC communication: 
  - **Direct** connection: using SOAP WS, REST, gRPC, etc,
  - Indirect communication: using an **Enterprise Service Bus** (ESB) middleware, such as Open ESB, Mule ESB, etc. 

---

### Messaging

- It is an **event-driven architecture** for multiple systems. 
- Applications communicate **asynchronously** with each other by producing and consuming messages to/from a message channel. 
- The message channel can be implemented via messaging platforms such as ZeroMQ, RabbitMQ, and ActiveMQ, or with data distribution services for real-time systems, such as OpenDDS.  



---
<!-- _class: lead -->

# Thanks

ivan.ruiz@uca.es