Installation
============

Dependencies
------------

* Java 1.6
* Scala 2.8.1
* Jetty 7.3.1
* Ant 1.8.2

Building
--------

Assuming you have all of the dependencies installed.

Edit appropriate values for your system into build.properties.sample and save as build.properties in the same directory.

In this directory, run:

    ant

This will create a directory called 'dist'.

Running standalone
------------------

Edit appropriate values for your system into trumpet.properties.sample ans save as trumpet.properties in the same directory.

In this directory, run:

    ant run

Deploying in an app server
--------------------------

You will find a .war file in the dist directory which can be deployed directly.