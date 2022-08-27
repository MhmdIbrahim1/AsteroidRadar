## Asteroid radar app Using open-source Nasa Api
Asteroid Radar is an app to view the asteroids detected by NASA that pass near Earth, you can view all the detected asteroids given a period of time with data such as the size, velocity, distance to earth and if they are potentially hazardous. In this project, you will apply the skills such as fetching data from the internet, saving data to a database, and display the data in a clear, compelling UI.

You will need the NEoWs API which is a free, open source API provided by NASA JPL Asteroid team, as they explain it: “Is a RESTful web service for near earth Asteroid information. With NeoWs a user can: search for Asteroids based on their closest approach date to Earth, lookup a specific Asteroid with its NASA JPL small body id, as well as browse the overall data-set.api key is set into local.properties file .create your own api key and put it to the local.properties for security


## The App Feature:
- Includes the Main screen with a list of clickable asteroids
- Includes a Details screen that displays the selected asteroid data once it’s clicked in the Main screen
- Downloads and parses data from the NASA NeoWS (Near Earth Object Web Service) API.
- Once an asteroid is saved in the database, the list of asteroids is displayed
- The asteroids data is cached by using a worker, so it downloads and saves week asteroids in background when device is charging and wifi is enabled, as well as deleted the asteroids data of the previous day
- App works in multiple screen sizes and orientations, also it provides talk back and push button navigation.

## Instructions for using API

To build this project the NASA NeoWS (Near Earth Object Web Service) API is used, which can be found here:
https://api.nasa.gov/

In order to run the app, you need an API Key which is provided for you in that same link, just fill the fields in the form and click Signup.
put your key on Constant file and it will work.
## Dependencies
The most important dependencies used are:
- Retrofit to download the data from the Internet.
- Moshi to convert the JSON data we are downloading to usable data in form of custom classes.
- GLide to download and cache images.
- RecyclerView to display the asteroids in a list.
The following components from the Jetpack library are used:
- ViewModel
- Room
- LiveData
- Data Binding
- Navigation
## Built With
* [Android Studio](https://developer.android.com/studio) - Default IDE used to build android apps
* [Kotlin](https://kotlinlang.org/) - Default language used to build this project
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) - Android Jetpack's Navigation component, used for Fragment-based navigation 
* [Retrofit](https://github.com/square/retrofit) - a type-safe HTTP client for Android and Java
* [Moshi](https://github.com/square/moshi) - a modern JSON library for Android and Java, that makes it easy to parse JSON into Java or Kotlin objects
* [Glide](https://github.com/bumptech/glide) - a powerful image downloading and caching library for Android
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - a collection of libraries that help design robust, testable, and maintainable apps: Room (a SQLite object mapping library), LiveData (builds data objects that notify views when the underlying database changes), ViewModel (stores UI-related data that isn't destroyed on app rotations)
* [Data Binding](https://developer.android.com/topic/libraries/data-binding) - a Jetpack support library that allows  to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically
* [MVVM](https://developer.android.com/jetpack/guide) - the architecture pattern used in the app (Model-View-ViewModel), that incorporates the Android Architecture Components

## Code review from udacity :D 
![Screenshot 2022-08-27 at 22-58-10 Look at my Udacity Project Feedback!](https://user-images.githubusercontent.com/107378571/187047769-9316cb1c-4857-4a9b-a1dd-cd70aba0bb7e.png)

## App review :D
<img width="214" alt="image" src="https://user-images.githubusercontent.com/107378571/187047818-aea056fa-f1f6-4a81-8f42-6f5b30f4d562.png">

<img width="197" alt="image" src="https://user-images.githubusercontent.com/107378571/187047867-a712efff-beb2-4a69-a345-5b63b5b0f0fd.png">

<img width="196" alt="image" src="https://user-images.githubusercontent.com/107378571/187047829-295dc7bd-ec8a-4350-801d-09b1ccd49699.png">

<img width="208" alt="image" src="https://user-images.githubusercontent.com/107378571/187047850-6fbd4920-6604-4e09-85db-fe14cc14803a.png">
