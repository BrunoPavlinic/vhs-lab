# VHS Rental shop - Blast from the past

We are creating a cutting edge VHS rental application management system for our special clients that value the old time retro VHS experince. 

![img_1.png](backtothepast.png)

### VHS Rental Application

#### API
1. Implement a VHS resource RESTful API over http. 
Path to this resource should be: /api/vhs
3. Implement CRUD RESTful API for Rental resource. 
Path to this resource should be: /api/rental
#### JPA
- Model VHS as JPA Entity
- Model User as JPA Entity
- Model Rental as JPA Entity
#### Requirements
- RentalController should accept needed user ID data, vhs ID data, and rental date(use form or PATH parameters) 
- Make sure that the same vhs can't have multiple rentals on the same date
- handle rental due dates and late fees
- 4 HTTP methods should be implemented. (e.g. GET, POST, PUT and DELETE)
- Use Spring Data JPA Repositories
- Use Qualifiers when needed
- Use Multiple Profiles
- Try to use all Spring stereotype annotations.
- Using Value, annotate a method that takes a single String argument with any property, print the argument to console inside the method than note what happens on Spring context initialization
- Use the Value annotation on a method at parameter level to autowire arguments with property values
- Use Lombok annotations and Slf4j Logback logging with Lombok
- Implement @ExceptionHandler to catch and handle all exceptions
- Use Bean Validation on RentalForm to validate requests to RentalController
- Customize error messages from REST controller with Message Source
- Define renal durations
- Implement equals and hashCode methods for VHS JPA Entity
- Implement toString method for VHS JPA Entity

#### API&Security
- Write API specification for all controller endpoints you have in your application by using swagger lib
- Implement security model using Spring Security with two existing roles. ROLE_ADMIN, ROLE_USER
- Add ROLE_ADMIN to all rental shop workers in the system, and ROLE_USER to all existing rental shop clients in the system
- Implement login/logout and use with User and Workers credentials
- Forbid users with ROLE_User access to RentalController resources

