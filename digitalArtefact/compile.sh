#!/bin/bash 
# Running in Linux with command ./compile.sh
echo "Compiling Child Classes"

javac classes/Profile.java classes/Person.java classes/Company.java interfaces/Workouts/WorkoutBTS.java classes/Workouts/WorkoutPlan.java classes/Workouts/Powerlifting.java classes/Misc/FileManager.java classes/Misc/Validator.java classes/Workouts/Bodybuilding.java classes/Misc/Tester.java
 
echo "Compiling Main Class"

javac digitalArtefact.java

echo "Running Digital Artefact"

java digitalArtefact

echo "Removing binaries"

rm classes/Profile.class classes/Person.class classes/Company.class classes/Workouts/WorkoutPlan.class classes/Workouts/Powerlifting.class classes/Misc/FileManager.class classes/Misc/Validator.class digitalArtefact.class classes/Workouts/Bodybuilding.class classes/Misc/Tester.class interfaces/Workouts/WorkoutBTS.class
