TicTacToe - Kata

project best opened in AndroidStudio (arctic fox 2020.3.1) for editing
to run command line JAVA_HOME must be set to JDK 11 release

to run all unit tests and build the project:
`./gradlew clean build`

to run the UI tests have a running emulator or device attached and run:
`./gradlew connectedAndroidTest`

to install have a running emulator or device attached and run:
`./gradlew installDebug` or use AndroidStudio's run button

Followed strategy:
TDD, Red -> Green (-> Refactor). 

chosen architecture:
Compose UI using ViewModel
