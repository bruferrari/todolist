# ToDoList

## Overview

ToDoList is an Android application for managing your daily tasks. 
It is built using Kotlin and showcases a clean architecture approach to Android development.

## Features

- Add, edit, and delete tasks
- Mark tasks as completed
- Persist data locally
- Allow the user to visualize long text tasks

## Getting Started

### Prerequisites

- Android Studio
- Kotlin 1.9.24
- Android SDK 26+

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/bruferrari/todolist.git
    ```
2. Open the project in Android Studio.
3. Build the project and run it on an emulator or physical device.

## Usage

1. Launch the app.
2. Use the "+" button to add a new task.
3. Tap on checkbox to mark it as completed.
4. Tap on trash button to delete a task.
5. Tap on task to get details

## Architecture

The project follows the MVVM (Model-View-ViewModel) + clean architecture, ensuring a clean separation of concerns and easier maintainability.

### Components

- **Model**: Represents the data layer, including the database and repository classes. 
It handles data operations and abstracts the data sources (e.g., local database).

- **View**: The UI layer, consisting of Composable functions and a single activity. It displays data and captures user interactions. 
The View observes the ViewModel to get the latest data and updates the UI accordingly.

- **ViewModel**: Acts as a mediator between the View and the Model. It holds UI-related data that is lifecycle-aware and survives configuration changes. 
The ViewModel exposes LiveData objects for the View to observe, ensuring that the UI stays up-to-date with the underlying data.

### Dependency Injection

The app uses Dagger/Hilt for dependency injection. This helps in managing dependencies efficiently and supports better testability by allowing dependencies to be mocked or replaced as needed.

### Repository

The Repository pattern is used to abstract the data sources from the rest of the app. 
This allows the ViewModel to interact with a simple API and keeps data operations encapsulated.

### Navigation
This app uses the [Navigation Component](https://developer.android.com/guide/navigation) to navigate the user between screens.

### Room Persistence Library

The app uses Room for local database management. Room provides an abstraction layer over SQLite, making database operations more robust and easier to manage.

### Testing

The architecture supports both unit and UI testing. The separation of concerns allows for isolated testing of each component, ensuring a reliable and maintainable codebase.

The ToDoList app implements UI testing using [Robolectric](https://robolectric.org) which is a framework that allow running instrumentation tests in a specific sandbox that doesn't require an emulator of physical device to run.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or suggestions, please open an issue or contact [Bruno Ferrari](mailto:b.ferrari09@gmail.com).
