
## Drones Service REST API


---

:scroll: **START**


### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

### Task description

We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

Each **Medication** has: 
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

Develop a service via REST API that allows clients to communicate with the drones (i.e. **dispatch controller**). 
The specific communication with the drone is outside the scope of this task. 

The service should allow:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 
- checking available drones for loading;
- check drone battery level for a given drone;

> Feel free to make assumptions for the design approach. 

---

### Requirements

While implementing your solution **please take care of the following requirements**: 

#### Functional requirements

- There is no need for UI;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.

---

#### Non-functional requirements

- Input/output data must be in JSON format;
- Your project must be buildable and runnable;
- Your project must have a README file with build/run/test instructions (use DB that can be run locally, e.g. in-memory, via container);
- Required data must be preloaded in the database.
- JUnit tests are optional but advisable (if you have time);
- Advice: Show us how you work through your commit history.

---
### How to build

#### Requirements

- Java 16
- Java IDE Intellij
- MYSQL databse
- Postman(For testing ) 

### Steps by step for building and runing the project locally

- Clone the from the link git clone https://github.com/mohammedTotah41/Drones.git

- Open the cloned project in Intellij

- Go to maven the update Project to update all the maven dependencies

- Maven Build the project and run

- Before running you can run the JUnit test cases to assert that everything is working correctly
(I have included some of the JUnit tests)


---


## techs applied 

- **JUnit 5**
- some test methods are added to test application expected behaviour
------------------------
- **Spring security**
- basic auth is added with username "admin" password "admin" so please make sure to add them in post man requests
------------------------
- **Scheduled task**
- Scheduled task that starts evey 15 Minuts to check drones battery levels and log data for that.
------------------------
- **Exception handler**
- Custom exception to throw Runtime Exception is there is un match data in insertion or with calling APIS
------------------------
- **Local Utils**
- is used to map some data from request to application Entities
------------------------
- **Spring Validation**
- @valid , @Validation spring validation are used.

### Testing the API
- Some of the assumption made for the purpose of this API design are:-

- Drone must not have more than weight it can carry .
- Drone Serial is unique
- drone should be in IDLE so it can carry medications 

Open Postman
For testing purpose the API is secured and you will have to specify the Authorization in the headers as Basic Auth

Username **admin**

Password **admin**

Note: the ContentType is application/json

- **Init Default database items (Drone, Medications, and Fleets)**

**http://localhost:8082/drone/initData** (POST)

- creates 10 Drones , 20 Medication Items , and five fleets
---

- **Get All Drones** (GET)

**http://localhost:8082/drone/getAllDrones**

- return all Drones with full information
---

- **Get All Medication Items** (GET)

**http://localhost:8082/drone/getAllMedications**

- return all Medication items with full information
---

- **Get All Fleets Items** (GET)

**http://localhost:8082/drone/getAllFleets**

- return all Fleets of drones with all loaded medication items
---


- **insert/register new Drone** (POST)

**http://localhost:8082/drone/registerDrone**

- take DroneRegisterRequest of (SerialNumber, Model, weightLimit, battery, state) , maps it to Drone object and save it.
---


- **Get All Available Drones** (GET)

**http://localhost:8082/drone/getAvailableDrones**

- check all Available Drones that is idle and battery level is above 25%
---

- **Load Drone with Medication Items** (POST)

**http://localhost:8082/drone/loadDrone**

- check if drone serial number exists
- check if drone is Available (Weight and status)
- load drones with medications and creates new fleet
- return inserted fleet
---

- **Get All Loaded Medications for a specific Drone by serial number** (GET)

**http://localhost:8082/drone/getDroneMedications/SER_503**

- check if drone serial number exists
- get medication items from fleet by drone id.
- return medication items.
---

- **Get Drone Battery level of a drone by serial number** (GET)

**http://localhost:8082/drone/getDroneBatteryLevel/SER_501**

- check if drone serial number exists
- get drone battery level
---

- **Update Drone Status to be DELIVERED after fleet ends** (PUT)

**http://localhost:8082/drone/deliverDrone/SER_501**

- check if drone serial number exists
- Update drone state to be DELIVERED.
---


:scroll: **END** 


