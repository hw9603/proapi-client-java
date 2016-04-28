# Whitepages PROAPI Client Library

Jeffrey C Reinecke <jreinecke@whitepages.com>, Amy Zou <azou@whitepages.com>

©2015-2016, Whitepages Inc.

## Description

The native Java client library to Whitepages PROAPI.

## Getting Started

### A short example

```java
Client client = new Client(ClientIntegrationTestHelper.getApiKey());

PersonQuery personQuery = new PersonQuery("Rory", null, "Williams", "Seattle", "WA", "98101");

Response<Person> response = null;
try {
    response = client.findPeople(personQuery);
} catch (FindException e) {
    e.printStackTrace();
}

if (response != null && response.isSuccess()) {
    List<Person> results = response.getResults();
    for (Person p : results) {
        System.out.println(p.getBestName());
    }
}
```

### Dependencies

This library depends on the following resources:

* [FasterXML Jackson Core](https://github.com/FasterXML/jackson-core)
* [FasterXML Jackson Annotations](https://github.com/FasterXML/jackson-annotations)
* [FasterXML Jackson Data-Bind](https://github.com/FasterXML/jackson-databind)

This library has been tested against Jackson 2.4.4, 2.5.3, and 2.7.3.

### Compiling the Repo

This repository is managed by sbt. You can compile using:
```bash
sbt compile
```
This will download the Jackson dependency jars, and compile the sources to class files.

To assemble into a jar file that does not contain dependency jars, use:
```bash
sbt package
```

### Running Test

To run the tests, use
```bash
sbt test
```

### Compiling Documentation

To compile the JavaDoc documentation, use:
```bash
sbt doc
```

Changes to the project [Github.io Documentation](http://whitepages.github.io/proapi-client-java/)
should be committed to the `gh-pages` branch. This typically involves copying
the compiled documentation directory into the project, replacing the existing
javadocs directory, when in the `gh-pages` branch.

## Class Structure Overview

The starting point for all interactions is the class __Client__. Once a Client instance is
obtained, the appropriate `find` method can be given a __Query__ instance. It returns a
__Response__ instance that contains a result list and a messages list.

The result list contains zero or more __Entity__ instances of the appropriate type.
Entities have accessors for retrieving lists of __Association__ instances by Entity
type (e.g. Person, Phone, etc). Associations can be followed to retrieve their
associated entities.

The messages list, though usually empty, may contain __Message__ instances. Note
that many errors that may occur are given here as messages with severity error.

## License

See [LICENSE.txt](LICENSE.txt)
