# Whitepages PROAPI Client Library Examples

Robert Noble

©2015, Whitepages Inc.

## Description

Example code using the PROAPI Java Client Library.

## Getting Started

Note: These examples are still in progress, detailed instructions will be
provided upon completion.

To compile from source, include in your classpath the jars in the lib
directory, along with the `proapi-client-java.jar`.
For example:
```bash
javac -cp lib/*:path/to/proapi-client-1.0.0.jar *.java
```

To run, provide your API key as an argument. For example, given the API Key
`ffffffffffffffffffffffffffffffff`, you can run the reverse phone example with:
```bash
java -cp .:lib/*:path/to/proapi-client-1.0.0.jar ReversePhone ffffffffffffffffffffffffffffffff
```
