#!/bin/bash --login

echo "Compiling Child Classes"

javac classes/Profile.java classes/Person.java classes/Company.java

echo "Compiling Main Class"

javac digitalArtefact.java

echo "Running Digital Artefact"

java digitalArtefact
