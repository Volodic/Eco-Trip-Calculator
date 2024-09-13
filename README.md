# Eco-Trip Calculator

### Description

Simple Android application to calculate the environmental impact of a trip with implemented CO2 emission calculation engine based on distance and transport mode.

### Architecture

The application is built on the basis of the **MVVM (Model-View-ViewModel)** architectural pattern:

- **Model**: The database, which is implemented using Room, contains tables with trip data (`TripData`).
- **View**: All user interfaces are created using Jetpack Compose. Navigation between screens is done using the Navigation Compose library.
- **ViewModel**: Used for managing UI state, processing business logic, and interacting with repositories. The `Hilt` is used to inject dependencies (`Dependency Injection`).

MVVM was chosen for this project because this architecture allows for a clear separation of the UI and application logic, which simplifies testing and maintenance of the code. Using Jetpack Compose allows you to write the UI declaratively, which makes it more flexible and easy to maintain.

### Third-party libraries

1. **Jetpack Compose** - to create a declarative-style user interface.
2. **Compose Navigation** - to control navigation between screens.
3. **Hilt** - for managing and simplifying dependencies.
4. **Hilt Compose Navigation** - to provide integration between `Hilt` and `Compose Navigation`.
5. **Room** - to store and manage data in a local database.
6. **KSP (Kotlin Symbol Processing)** - used as a replacement for `kapt` for processing annotations, which works faster and more optimally with Kotlin.

### Why these libraries were chosen

- **Jetpack Compose** provides flexibility in user interface development through a declarative approach.
- **Compose Navigation** allows you to easily manage navigation within Jetpack Compose.
- **Hilt** integrates Dependency Injection, which simplifies dependency management and improves testing.
- **Room** is an effective solution for working with SQLite database in Android.
- **KSP** significantly speeds up annotation processing, which improves performance and reduces compilation time.

### Assumptions

- All calculations are performed in kilograms per kilometer for a more optimized output.
- The type of transport is determined using the enum class TransportType.

### Assembly instructions

1. Clone repository:
```bash
git clone https://github.com/Volodic/Eco-Trip-Calculator.git
```
2. Open project in AndroidStudio.
3. Make sure you have the latest version of the Android SDK and JDK installed.
4. Run the application on an emulator or a real device.
