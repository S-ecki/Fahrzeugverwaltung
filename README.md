# Fahrzeugverwaltung
 Program to manage cars of an online retailer. <br/>
 The projects main aim was build a Java Application with Dependencies that can persist data through Serialisation.
 It was created for my class [Programmiersprachen und -konzepte](https://ufind.univie.ac.at/de/course.html?lv=051030&semester=2020W) at the University of Vienna.

## Structure
The 2 classes `PKW` and `LKW` extend the abstract class `Fahrzeug`

`SerializedFahrzeugDAO` implements the interface `FahrzeugDAO` and provides the functionality to read, write and delete from persistent files.
This is done through Javas Data Access Object pattern in combination with Serialiation of the Fahrzeug objects.

`FahrzeugManagement` supplies the client with different methods. On the one hand for accessing/altering the data (does not have to work with serialisation directly), and on the other hand for useful functionality (e.g. counting of stock)

`FahrzeugClient` makes all of the implemented logic accessible through the main method. <br/>
Here is a list of valid calls:
* `java FahrzeugClient <File> show` <br/> prints all cars
* `java FahrzeugClient <File> show <id>` <br/> prints car with specific id
* `java FahrzeugClient <File> add lkw <id> <marke> <modell> <baujahr> <grundpreis>`  <br/> adds LKW with parameter
* `java FahrzeugClient <File> add pkw <id> <marke> <modell> <baujahr> <grundpreis> <service>`  <br/> adds PKW with parameters
* `java FahrzeugClient <File> del <id>`  <br/> deletes car with given id
* `java FahrzeugClient <File> count` <br/> counts all cars
* `java FahrzeugClient <File> count <type>` <br/> counts cars per type (lkw/pkw)
* `java FahrzeugClient <File> meanprice` <br/> calculates average price of cars
* `java FahrzeugClient <File> oldest` <br/> prints oldest car
* `java FahrzeugClient <File> service <begin> <end>` <br/> prints all PKWs with last service between given years

## What I´ve learned
* "*Program to an Interface, not to an Implementation.*" <br/>
-my prof, and probably lots of other smart people
* Dependency Injection/ Inversion of Control <br/>
It would be easy to change out my implementation of Serialisation, because I used the DAO Interface for Field variables
* Delegation instead of Inheritance for dynamic usage
* How to persist data in Java
