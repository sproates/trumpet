Installation
============

Dependencies
------------

To build the servlet, you will need:

* Java 1.6
* Scala 2.8.1
* Ant 1.8.2
* Servlet API jar

To run the servlet, you will also need:

* A servlet container such as Tomcat 1.7

If you want to build the standalone app, you will also need:

* Jetty 7.3.1

To run the unit tests you will also need:

* JUnit 4.8.2
* ScalaTest 1.3

Building
--------

Assuming you have all of the dependencies installed.

The build system reads a file called build.properties, located in the base
directory, from which it determines the location of various jars etc.

Edit appropriate values for your system into build.properties.sample and save as
build.properties in the same directory.

In this directory, run:

    ant

This will create a directory called 'dist'.

Deploying in an app server
--------------------------

You will find a .war file in the dist directory which can be deployed into your
servlet container.

Building the standalone app
---------------------------

In this directory, run:

    ant standalone

Running the standalone app
--------------------------

The standalone app reads its configuration from a file called trumpet.properties
in the base directory.

Edit appropriate values for your system into trumpet.properties.sample and save
as trumpet.properties in the same directory.

In this directory, run:

    ant run

Testing
-------

Edit appropriate values for your system into build.properties.sample and save as
build.properties in the same directory.

In this directory, run:

    ant run_unit_tests
