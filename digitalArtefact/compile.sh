#!/bin/bash 

echo "Compiling Child Classes"

javac classes/Profile.java classes/Person.java classes/Company.java classes/WorkoutPlan.java classes/Powerlifting.java classes/FileManager.java classes/Validator.java classes/Cardio.java classes/Bodybuilding.java
 
echo "Compiling Main Class"

javac digitalArtefact.java

echo "Running Digital Artefact"

java digitalArtefact

echo "Removing binaries"

rm classes/Profile.class classes/Person.class classes/Company.class classes/WorkoutPlan.class classes/Powerlifting.class classes/FileManager.class classes/Validator.class digitalArtefact.class classes/Cardio.class classes/Bodybuilding.class
