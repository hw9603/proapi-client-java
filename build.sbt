import java.io.File

name := "proapi-client"

organization := "com.whitepages"

version := "1.0.0"

description := "Whitepages PROAPI Client Library for Java"

// Enables publishing to maven repo
publishMavenStyle := true

// Do not append Scala versions to the generated artifacts
crossPaths := false

// This forbids including Scala related libraries into the dependency
autoScalaLibrary := false

libraryDependencies ++= Seq(
  "com.fasterxml.jackson.core" % "jackson-core" % "2.4.4",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.4.4",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.4",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test"
)

javacOptions in (Compile, compile) ++= Seq(
  "-Xlint:all",
  "-Xlint:-serial"
)

javacOptions in (Compile, doc) ++= Seq(
  "-public",
  "-windowtitle", "Whitepages PRO API Java Docs",
  "-doctitle", "Whitepages Pro API",
  "-footer", "&copy;2015, Whitepages, Inc.",
  "-exclude", "com.whitepages.proapi.api.client.datasources"
)

//A list of directories to exclude from documentation, as path substrings.
val excludedDocDirs = Seq(
  "api/client/datasources",
  "api/client/responsedecoders",
  "api/client/querycoders",
  "api/client/util"
)

//A list of files to exclude from the documentation, as path suffixes.
val excludedDocFiles = Seq(
  "api/response/ResponseDictionary.java"
)

//Calculates which files to exclude from documentation using the above defined lists.
val isExcludedDocFile = (file: File) => excludedDocDirs.foldLeft(false)(_ || file.getParent.contains(_)) || excludedDocFiles.foldLeft(false)(_ || file.getPath.endsWith(_))

sources in (Compile, doc) ~= (files => files.filter(file => !isExcludedDocFile(file)))
