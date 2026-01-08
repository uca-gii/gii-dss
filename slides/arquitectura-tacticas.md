---
marp: true
title: "Architecture: Tactics"
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

# Architectural tactics

---
# Lesson contents   
Introduction
Quality attribute requirements
Tactics for improving quality


<!-- paginate: true -->


---
<!-- _class: lead -->

# Introduction

---
## Introduction
- The **quality** of a system is the degree to which the system satisfies the stated and implied needs of its various stakeholders, and thus provides value. 

- Those stakeholders' needs are precisely what is represented in a quality **model**, which categorizes the product quality into characteristics and sub-characteristics.

- Functionality and quality characteristics of software architecture are orthogonal.

---

### ISO/IEC 25010 Product quality model
![w:1100px](./figures/iso25010.png) 

---

### Functional Suitability
Degree to which a product or system provides functions that meet stated and implied needs when used under specified conditions. 

- Functional completness
- Functional correctness 
- Functional appropriatness

---

### Performance efficiency 
  Performance relative to the amount of resources used under stated conditions. 

- Time behaviour 
- Resource utilization 
- Capacity 

---
### Compatibility 
Degree to which a product, system or component can exchange information with other products, systems or components, and/or perform its required functions while sharing the same hardware or software environment. 

- Co-existence
- Interoperability

--- 

### Usability
Degree to which a product or system can be used by specified users to achieve specified goals with effectiveness, efficiency and satisfaction in a specified context of use. 

- Appropriateness recognizability
- Learnability 
- Operability
- User error protection
- User interface aesthetics
- Accessibility

---

### Reliability 
Degree to which a system, product or component performs specified functions under specified conditions for a specified period of time.

- Maturity
- Availability
- Fault tolerance
- Recoverability


---
### Security 

Degree to which a product or system protects information and data so that persons or other products or systems have the degree of data access appropriate to their types and levels of authorization. 

- Confidentiality
- Integrity
- Non-repudiation
- Accountability
- Authenticity



---
### Portability
Degree of effectiveness and efficiency with which a system, product or component can be transferred from one hardware, software or other operational or usage environment to another. 

- Adaptability
- Installability
- Replaceability


---
### Maintainability 
  
Degree of effectiveness and efficiency with which a product or system can be modified to improve it, correct it or adapt it to changes in environment, and in requirements. 

- Modularity
- Reusability
- Analysability
- Modifiability
- Testability



---

<!-- _class: lead -->

# Quality attribute requirements


---
As known as...
# Non-functional requirements

---
## Requiments for the non-functional requirements
The quality attribute requirements should be... 
**unambiguous**,
**measurable**,
**testable**, 
and defined using **scenarios**...

---

## Scenarios for quality attribute requirements

- **Stimulus**: an event arriving at the system or the development team
- **Stimulus source**: the source of the event may affect how it is treated by the system
- **Response**: how the system should respond in response to the stimulus for runtime qualities or what developers should do for development-time qualities
- **Response time**: for determining whether a response is satisfactory
- **Environment**: the set of circumstances in which the scenario takes place
- **Artifact**: the portion of the system to which the req. applies

---
## Quality scenario

![w:750px](./figures/QualityScenario.jpeg) 


---
### Example of a **performance** scenario

*Users initiate transactions under normal operations. The system processes the transactions with an average latency of two seconds.*

---
### Example of a **modifiability** scenario

*The developer wishes to change the user interface by modifying the code at design time. The modifications are made with no side effects within three hours.*

  

---
<!-- _class: lead -->

# Architectural tactics

---


## Architectural tactics

- A tactic is a design decision that influences the achievement of a quality attribute requirement. 
 
- Tactics directly affect the system's response to some stimulus. 

- These tactics are, to a large extent, dependent on the kind of system and they are continuously evolving.


---

### Let's see an incomplete catalog of tactics...

---

![bg w:700](./figures/Tactics_Availability.png) 

---
![bg w:800](./figures/Tactics_Performance.png) 

---
![bg w:900](./figures/Tactics_Security.png) 

---

![bg w:800](./figures/Tactics_Interoperability.png) 

---
![bg w:900](./figures/Tactics_Usability.png) 

---
![bg w:800](./figures/Tactics_Testability.png) 

<!--
---


![bg w:800](./figures/Tactics_Modifiability.png) 
-->

---
<!-- _class: lead -->

# Thanks

ivan.ruiz@uca.es