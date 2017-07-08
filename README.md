# Deservation

### Usability

### Technical stuff

The app was written in Kotlin (it rocks!) and developed following a MVVM architecture. For that, I used a mix of the new Android Architecture Components (ViewModel and LiveData) and RxJava.

All Android code is confined in the view classes (fragments and activities). The screens works in a reactive way: they subscribe to observe the "live data". When data changes, the screens are updated automatically.

I used Dagger for dependency injection and Mockito for unit tests. For image loading I used Picasso.
