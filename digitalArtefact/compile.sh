#!/bin/bash 
# Running in Linux with command ./compile.sh
echo "Compiling Child Classes"

javac classes/Misc/AdminControls.java classes/Profile.java classes/Person.java classes/Company.java interfaces/Workouts/WorkoutBTS.java classes/Workouts/WorkoutPlan.java classes/Workouts/Powerlifting.java classes/Misc/FileManager.java classes/Misc/Validator.java classes/Workouts/Bodybuilding.java testers/Tester.java interfaces/testers/TesterBTS.java testers/Logging/Logger.java testers/Misc/T_FileManager.java testers/Misc/T_Validator.java testers/Profile/T_Profile.java testers/Workouts/T_Workout.java testers/Tester.java

echo "Compiling Main Class"

javac digitalArtefact.java

echo "Running Digital Artefact"

java digitalArtefact

echo "Removing binaries"

rm classes/Profile.class classes/Person.class classes/Company.class classes/Workouts/WorkoutPlan.class classes/Workouts/Powerlifting.class classes/Misc/FileManager.class classes/Misc/Validator.class digitalArtefact.class classes/Workouts/Bodybuilding.class testers/Tester.class interfaces/Workouts/WorkoutBTS.class classes/Misc/AdminControls.class interfaces/testers/TesterBTS.class testers/Logging/Logger.class testers/Misc/T_FileManager.class testers/Misc/T_Validator.class testers/Profile/T_Profile.class testers/Workouts/T_Workout.class
