---
author: Iván Ruiz, Andrés Muñoz & Juan Manuel Dodero
date: Enero 2026
description: Apuntes de Diseño de Sistemas Software - Patrones de diseño
marp: true
subject: Diseño de Sistemas Software, curso 2025/26
title: Architecture
---

<!-- size: 16:9 -->

<!-- theme: default -->

<!-- paginate: false -->

<style>
h1 {
  text-align: center;
}
h2 {
  color: darkblue;
  text-align: center;
}

<style>
img[alt~="center"] {
  display: block;
  margin: 0 auto;
}

emph {
  color: #E87B00;
}

# DISEÑO DE SISTEMAS SOFTWARE

<style scoped>
h2 {
  text-align: left;
}

## Bloques

1. Principios de diseño OO
2. Patrones de diseño
3. <emph>Arquitectura de software</emph>

---

# SOFTWARE ARCHITECTURE

## Module contents

1. Introduction to software architectures
2. Communicating architectures
3. Architectural principles
4. Architectural patterns

<!--3. Architectural tactics-->

---

## INTRODUCTION TO SOFTWARE ARCHITECTURES

---

## Introduction to software architectures

### Lesson contents

Definitions
Architecture design process
Process inputs
Process outputs
The role of the software architect
Benefits

---

## Definitions

---

<!-- _class: invert -->

<style scoped>
section {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;  /* alinea al inicio (arriba) */
}

![bg](./img/AthensParthenon.jpg)

### Architecture (I)

- The **process** and the **product** of planning, designing, and constructing buildings or **other** structures.

---

<!-- _class: invert -->

<style scoped>
section {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;  /* alinea al inicio (arriba) */
}

![bg](./img/SydneyOperaHouse.jpg)

### Architecture (II)

- The **style of design** and **method** of construction of buildings and other physical structures.

---

![bg right:50% 120%](./img/Naval.png)

### Architecture applies to several **disciplines**:

- landscape architecture
- interior architecture,
- urban design,
- mechanical engineering,
- naval architecture,
- etc.

---

### ¿buildings == software?

Both are developed by teams using **tools**, **patterns** and **tactics** and are affected by **trends**

---

### ¿buildings != software?

Buildings are **stable** environments with **physical** limits and many difficulties to change, software is a **virtual** artifact with evolving nature and it is easier to **change**

---

### The architecture of **anything**

> Fundamental organization embodied in its **components** and their **relationships** to each other and their environment. The **principles** of its design and evolution.

-- *ISO/IEC/IEEE 42010*
-- *Samuel Holcman*

---

### Architecture of a **system**

> A **system architecture** makes use of elements of both **software** and **hardware** and is used to enable the design of such a composite system.

-- *ISO/IEC/IEEE 42010*

---

### The architecture of a **software system**

> It is the shape given to that system by those who build it. The form of that shape is in the division of that system into **components**, the **arrangement** of those components, and the ways in which those components **communicate** with each other.

-- *Bob Martin*

---

### **Software** architecture

> The **set of significant decisions** about the **organization of a software system**, the selection of the **structural elements and their interfaces** ...together with their **behavior** ..., the **composition** of these ... elements ..., and the **architectural style** ...

-- *Kruchten: The Rational Unified Process*

---

> Architecture represents the **significant decisions**, where significance is measured by **cost of change**.

--*Grady Booch*

---

### The **software architecture** can be considered the **blueprint** to build and maintain a software system.

---

### **Reference** architecture

The **set of design decisions** that can be simultaneously applied to **several related systems** within an application **domain** and with explicitly defined **variation points**.

<small>A **Software Product Line** is a set of software-intensive systems that share a **common**, managed set of **features** satisfying the specific needs of a particular **market segment** or mission.</small>

---

## Architecture design process

---

### Waterfall development

![bg right:60% 90%](./img/Waterfall.png)

---

### Waterfall development

- **Requirements** should be **fixed** before the coding phase began.
- **Big design up front**: Software architecture ought to be developed and completed before writing the first line of code.
- Creating a perfect software design blueprint can be a waste of time and resources because it could be **changed** many times.

---

### Agile development

![w:1050](./img/Scrum.png)
<small>The *Scrum* framework</small>

---

### Agile development

- Agile manifesto values:

   - **Individuals and interactions** over *processes and tools*
   - **Customer collaboration** over *contract negotiation*
   - **Responding to change** over *following a plan*
   - **Working software** over *comprehensive documentation*

- Many organizations started to not create any design at all, causing long-term maintainability issues.

---

### How much up front design should you do?

#### **0\%** ?

#### **100\%** ?

---

> Big design up front is dumb.
> Doing no design up front is even dumber.

-- *Dave Thomas*

---

### Evolutionary architecture (I)

- An approach to architecture that embraces **change** in an agile manner.
- The key is to create **enough** pieces of architectural design before coding.
- The software architecture represents the set of **significant decisions**, where significance is measured by the cost of changes.
- Major **decisions** have to do with **technology** choices, selection of the proper **infrastructure**, application of certain **tactics** and design **patterns**, etc

---

### Evolutionary architecture (II)

- Propose **hypotheses** and then write some code to validate whether the solution posed is feasible and fulfills the expected requirements.
- Prove the architecture with concrete **experiments**, and proof of concepts.
- Identify and control **risks** and tackle the highest priority ones in the early stages.
- However, not all decisions have to be made in the beginning. In fact, it is much better to defer decisions until they are strictly needed. We should **keep the options open** for as long as possible.

---

### Laws of Software Architecture

**#1. Everything in software architecture is a trade off**
All meaningful decisions have advantages and downsides

**#2. 'Why' is more important than 'How'**
Question everything

![bg right:50% 120%](./img/ArchitectureDesignProcess.png)

---

## Process inputs

---

### Process inputs

The creation process of the software architecture starts with a set of inputs:

- Business objectives,
- Functional requirements,
- Information requirements,
- Non-functional requirements,
- and constraints.

---

### Business objectives

What are the **goals** (measurable) of the system to develop or maintain?

- What are the stakeholders' **expectations**?
- How **critical** will the software be?
- What is the **time-to-market**?
- Are the project **calendar** and costs well established?
- How **variable** are the rules that will govern the software?

---

![bg left:60% fit](./img/UML_UseCase.png)

### Functional requirements

Some requirements engineering techniques should be conducted prior to designing the architecture, to capture and analyze the behavior of the software to develop.

---

![bg left:60% fit](./img/UML_DomainModel.png)

### Information requirements

The static nature of the system is modeled with a conceptual model considering the business vocabulary.

---

![bg left:60% fit](./img/SysML_Requirements.png)

### Non-functional requirements

Quality attribute requirements, namely performance efficiency, compatibility, operability, reliability, security, portability, and maintainability.

---

### Constraints

- Both the **customer** and **development organization** can make certain technical or organizational decisions.
- These constraints limit the number of potential **alternatives**
- **Software** constraints: programming languages, development frameworks, database providers, etc.
- **Hardware** constraints: sensor and actuator manufacturers, execution platforms, etc.

---

## Process outputs

---

Software architecture is **embedded** in the **source code** itself,

However, there are other aspects that are **not** directly (easily) **observable** in the code.

---

### Alternatives for describing architectures

- **Architectural Decision Records (ADR)**, by using structured templates
- **Textual representations**, by using Architecture Description Languages (ADLs)
- **Visual models**, according to well-known specifications like UML or SysML

---

## The role of the software architect

---

### The role of the software architect

The software architect is in charge of creating and maintaining the software architecture

---

### Skills

- On many occasions, this role is assumed by one of the **senior** developers.
- Possess good **interpersonal skills** to deal with stakeholders who may have different (even contradictory) needs
- Have a solid knowledge of the **business domain**.
- Aware of **new** development techniques, practices, and tools.
- **Self-experience** for correctly applying best practices, design principles, and patterns.

---

### Responsibilities (I)

- Software architects usually **code** as other developers and perform **code reviews**, so code low-level understanding is mandatory.
- Architects must **help** developers understand the overall system architecture.
- They must continually **analyze** the architecture and ensure **compliance** of the code with existing decisions during software evolution.

---

### Responsibilities (II)

- Define **guidelines** and checklists to ensure the technical success of the project.
- Analyze the **pros** and **cons** of the different alternatives and keep decision records.
- Explicitly **document** the software architectures by means of visual models or textual representations.

---

## Benefits

---

### Benefits (I)

- The way of programming should always be the same and follow the same structure, the resulting code will be easily recognizable and ...

   - facilitates the development and maintenance of the software.
   - simplifies the deployment and operation of the systems.

---

### Benefits (II)

- The software architecture documentation provides...
   - a clear vision and roadmap for the team to follow.
   - a common language to express, negotiate and resolve the stakeholder's expectations.
   - a valuable resource to be used as the basis for the training of new project members.

---

## Key ideas

---

- **Definition**: *the set of significant decisions, the style of design, the organization into components and the way of communication.*
- **Process**: *evolutionary, addressing risks, proofs of concepts*
- **Inputs**: *business objectives, software requirements and know-how*
- **Outputs**: *source code, decision records and textual/visual representations*
- **Skills**: *interpersonal, business and technical knowledge and experience*
- **Responsabilities**: *code, review, help, analyze, define, document.*
- **Benefits**: *code easy to maintain, documentation helpful for all.*

