import java.io.File

name := "proapi-client"

organization := "com.whitepages"

version := "1.0.1"

description := "Whitepages PROAPI Client Library for Java"

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
  "-footer", "&copy;2015, Whitepages, Inc."
)

mappings in (Compile, packageSrc) <+= baseDirectory map { base =>
  (base / "LICENSE.txt") -> "LICENSE.txt"
}

// ===== Maven Central Repo Configuration =====

publishMavenStyle := true

licenses := Seq("MIT" -> url("https://github.com/whitepages/proapi-client-java/blob/master/LICENSE.txt"))

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra :=
  <url>https://github.com/whitepages/proapi-client-java</url>
    <scm>
      <url>git@github.com:whitepages/proapi-client-java.git</url>
      <connection>scm:git:git@github.com:whitepages/proapi-client-java.git</connection>
    </scm>
    <developers>
      <developer>
        <id>whitepages</id>
        <name>Whitepages PRO</name>
        <organization>Whitepages, Inc.</organization>
        <organizationUrl>http://pro.whitepages.com/</organizationUrl>
        <timezone>-8</timezone>
      </developer>
    </developers>

// ===== Documentation Source Selection ===== //

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
