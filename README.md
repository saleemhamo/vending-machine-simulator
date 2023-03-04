# Snacks Vending Machine Simulation using Spring Boot and Angular

# Description
This project is a simulation of a Snacks Vending Machine built using Spring Boot and Angular frameworks. The aim of the project is to demonstrate the design and implementation of a vending machine system that allows users to select snacks and make payments using a digital platform.

The backend of the system was developed using Spring Boot, a Java-based framework that provides a robust and scalable infrastructure for building enterprise-level applications. The backend was designed with a focus on modularity, extensibility, and maintainability, and adheres to the principles of object-oriented programming.

To provide a clear understanding of the system's architecture and design, a UML diagram was created to represent the backend's structure. The UML diagram displays the various classes and their relationships, including controllers, services, repositories, and models.

The frontend of the system was developed using Angular, a TypeScript-based framework that provides a powerful set of tools for building dynamic web applications. The frontend was designed with a clean and intuitive user interface, allowing users to select snacks, make payments, and receive their orders seamlessly.

To ensure the system's reliability and functionality, a suite of unit tests was added to the project. The unit tests cover various aspects of the system, including the backend's controllers, services, and repositories, as well as the frontend's components and services.

# Backend Apllication: UML

1. Models


![Models UML](https://github.com/saleemhamo/vending-machine-simulator/blob/main/images/ModelsUML.png?raw=true)

2. Services


![Services UML](https://github.com/saleemhamo/vending-machine-simulator/blob/main/images/ServicesUML.png?raw=true)

3. Controllers


![Controllers UML](https://github.com/saleemhamo/vending-machine-simulator/blob/main/images/ControllersUML.png?raw=true)

4. Objects Factory


![Factory UML](https://github.com/saleemhamo/vending-machine-simulator/blob/main/images/FactoryUML.png?raw=true)

# Installation
To install and run this project locally, please follow the instructions below:

1. Clone the repository to your local machine:

```
git clone <repository-url>
```
2. Navigate to the backend directory and build the project using Maven:
```
cd vending_machine_simulator-backend
mvn clean install
```
3. Start the Spring Boot server:
```
mvn spring-boot:run
```
4. Navigate to the frontend directory and install the dependencies:
```
cd vending-machine-simulator-frontend
npm install
```
5. Start the Angular server:
```
ng serve
```
6. Open your web browser and navigate to http://localhost:4200
