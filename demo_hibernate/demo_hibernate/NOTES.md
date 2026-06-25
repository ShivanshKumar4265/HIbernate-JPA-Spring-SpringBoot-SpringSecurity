1. JPQL and Native query
   - JPL is a library that allows you to write Prolog queries in Java. It provides a way to interact with Prolog from Java code.
   - Native query refers to writing Prolog queries directly in Prolog syntax, without using any Java library.
2. JPQL : Jakarta Persistence Query Language
   - JPQL is a query language used in Java Persistence API (JPA) to query entities stored in a relational database. It is similar to SQL but operates on the entity objects rather than the database tables.
   - JPQL allows you to write queries that are independent of the underlying database, making it easier to switch between different database systems without changing the query syntax.
   - JPQL supports various operations such as selecting, updating, and deleting entities, as well as performing joins and aggregations.
3. Native query
   - A native query is a SQL query that is written directly in the database's native SQL syntax. It allows you to execute raw SQL queries against the database, bypassing the abstraction provided by JPA.
   - Native queries can be useful when you need to perform complex operations that are not easily expressed in JPQL, or when you want to take advantage of specific features of the underlying database.
   - However, using native queries can make your code less portable, as it may not work



# Entity Manage method 
   - find();
   - merge();
   - remove();
   - persist();
   - createQuery(); to read jpql
   - createNamedQuery();
   - executeUpdate();


---------------------------------------------------------------------------------------------------------
# JPQL (Jakarta Persistence Query Language)
   - used in jpa to retrieve and manipulate data from a database using entity class objects, instead of database tables

-------------------------------------------------------------------

### Difference between and JPQL and SQL.

------------------------------------------------------------------------
# Why JPQL
it is platform independent and every query is not present in jpa. Some time we have to write the sql query using jpa.
it communicates with entity not with db

# Syntax for JPQL
 ## syntax for select query


      -  1. SELECT your_alias FROM table_name your_alias; 
         (the table_name should be entity class name, 
         not table name. example here entity class is Person but the table name is person[used annoation]. coz it deal wiht 
         entity not,  direct database)
      -  E.g SELECT e FROM Employee e;

      -  2. from Employee

# Named Query / Native Query
   - positional parameter, named parameter

# Hibernate Relationship Mapping (owner  and holder)
   
   - Association between more than one entity classes know as hibernate relationship mapping
   - we can connect one entity class with another entity class and with the help of JPA annotation 
   - @one to one
   - many to many
   - one to many
   - many to one

   ## Type of hibernate relationship 
      
      - Bi- Directional
      - Uni- directional

   ## Uni- Directional mapping
      -  Assosciation bwtween more than one entity class form any side
   Types of unidirectional mapping
1. one to one 
2. one to many
3. many to one 
4. many to many 



## One to one mapping 
   
   - association between  more than one entity class where one object  can be associated with only one object\
   - example user and card, person and aadhar 

## one to many mapping (third table creaed by compile)

   - association between more than one entity class, one entity connected with multiple entities, know as one to many
   - relationships. Example: One country has multiple states, one company has multiple employee

## many to one mapping
    - association between more than one entity class, 
      multiple entities connected with one entity, know as many to one relationships.
      Example: Multiple employee work in one company, multiple students study in one school
      
    - only two table and owning entiyt will have foreign key,  and here foreignt key will not be uniquie
    - no need of third table

## Cascading 

   - It is feature of hibernate/jpa the automatically propagate the dml operation perform on the parent entity(owing, who have foreign key), 
   - to its child entity (non oening side, whoe doent have foreign key)
   - example : when se say, state automatically country will get saved without passing inside persist method,but country 
   - reference should be connected with state object

     -  NOte: parent - have foregin key
     -  child have  = no foregin key
   
   ## casade types
      1. it is an enum which contains multiple final variable to perform various operation

         a. @CascadeType.PERSIST
            - When  parent is saved automatically  the child is also saved
         
         b. @CascadeType.MERGE
            - When parent is updated, child is automatically  updataed
         
         c. @CascadeType.REMOVE
            - When parent is deleted, child is also deleted automatically
         
         d. @CascadeType.REFRESH
            - reload parent and child entity form database

         d. @CascadeType.DETACH
            - It detaches parent and child form // persistent context
   
         e. @CascadeType.ALL
            - combbination of all above 4 i.e a, b ,c, d
   
      2. @JoinColumn
      3. @JoinTable
      4. @GenerateValue
      5. @FetchType
      6. @Comosite Kkey

--------------------------------------------------------------------------

## Why we use bidirectional mapping??
   
   1. we use this to navigate the relationship from both side of entity classes

   ## How to decide which is owning or non owning
      - with the help of @"mappedBy" attibute, we tell hibernate jpa, to declare the owning or non owning side
         for example, we two class person and aadhar, if we want to decide owner between them, 
         we have to mapped by above refrence variable. Suppose i want to declare person as an owner   

      - we have to write mappedBy at the top of person reference variable
      - jisme likha hain mapped by wo non - owning hain

      - Aadhar uses mappedBy = "aadhar" to say: "I don't control the relationship, Person does."

--> Db Migration



