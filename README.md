# Report generator application in pure Java

This project was made in purpose to review Java fonctionnalities without any framework like Spring, project manager like Maven or Gradle and any IDE use such as IntellJ or Eclipse, in fact, we are using only Notepad++.
We cover here many subjects, such as modularity, the Reactor project ( reactivity ), Locales, Streams and multi-threading ! 

## About 

The main functionnality of this application is to fuse multiples .csv files content into only one .csv file, this one will be use to genrate a report, available in multiple languages.

** put big picture image


## How to start this project in old-fashioned way ?

As said before, this project do not use any framework or project manager, we have to execute build and run "by ourselves" :).
First of all, you are invited to git clone the project.

### Section 1 : classpath-> basic compilation and interpretation

1. javac -d bin src/*.java
Allow to use java compilator and transform java source file to byte class files, -d is for specify the output localization which is bin.

2. java -cp bin HMI / java --class-path bin HMI
Allow to use JVM to interpret class files in bin, we specify the entry point file which is HMI file.

3. jar cfm myapp.jar MANIFEST.mf -C bin .
Allow to produce a jar file. 
c for create a jar
f to give a specific name to our jar
m to specify that we use a MANIFEST.mf file
-C bin to add all classes files in bin repository to our jar.

4. java -jar myapp.jar
The jar file is executed !

### Section 2 : modularity