# Project: HotelBediaXApp

## Project Description

"HotelBediaXApp" is a mobile Android app that allows users to explore unique travel destinations around the world. The app presents a list of popular destinations, each with a brief description, and allows users to view more extensive details about each destination. Users can also add new destinations to the list and remove them with an intuitive swipe gesture. The app is built using Jetpack Compose, Kotlin, and follows the MVVM architecture, with Room integration for local data persistence.

## Key Features:
    
Display a list of predefined destinations.
View details of a selected destination.
Add new destinations to the list.
Remove destinations from the list with a swipe (Swipe to Dismiss).
Local storage using Room database.
Friendly animations and UI transitions.

## Installation Instructions
### Clone and Set Up the Project

1.	Clone the repository: Open your terminal and run the following command:

        git clone <REPOSITORY-URL>

2.	Open in Android Studio: Open Android Studio and select File > Open, then navigate to the folder where you cloned the project.

3.	 Install dependencies: Android Studio will automatically install the project dependencies via Gradle. If not, select Sync Project with Gradle Files from the File menu.

4.	 Build and run: Select a connected physical device or emulator, and click Run (Play icon) to build and run the app.

## Requirements

·	Android Studio: Version 4.2 or later.

·	Gradle: Used to manage dependencies and build the project.

·	Minimum Android Version: Android 5.0 (API Level 21).

·	Target Android Version: Android 12.0 (API Level 31).

·	Kotlin: Project developed using Kotlin.

·	Jetpack Compose: Used for UI development.

·	Room: For managing local database storage.

·	Navigation Component: To manage navigation between screens.

## Necessary Tools:

·	Android Studio (https://developer.android.com/studio)

·	JDK 8 or higher (latest version recommended)

·	Android device or Android emulator configured in Android Studio.

## Important Commands

·	 Build and run the app: To run the app on an emulator or device, simply click the Run button inside Android Studio or run the following:

        ./gradlew installDebug

·	Clean the project: To clean the project and remove temporary build files, run:

        ./gradlew clean
        
·	Update Gradle dependencies: To force a sync and update all project dependencies, run:

    ./gradlew build --refresh-dependencies

## Project Architecture

The app follows the MVVM (Model-View-ViewModel) pattern, using Android Jetpack components to ensure a modular and scalable structure. Data flow between the UI and the data layer is efficiently managed through StateFlow and ViewModels.

## Key Folders:

·	UI: Contains all the composable functions for the user interface.

·	Data: Manages the entities and persistence logic using Room.

·	ViewModel: Contains ViewModel classes that interact with repositories and expose data to the UI.


# HotelBediaXApp

## Short Video
https://github.com/user-attachments/assets/0eb71f8a-fc5e-4e7a-8f21-21bf743f7bed





