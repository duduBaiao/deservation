# Deservation

### Technical stuff

The app was written in Kotlin, following a MVVM architecture. For that, I've used a mix of the Android Architecture Components (ViewModel, LiveData, and Room) and RxJava.

All Android code is confined in the view classes (fragments and activities). The screens work in a reactive way: they observe the "live data". When data changes, the screens are updated automatically.

I've used Dagger for dependency injection, Expresso for instrumented tests and Mockito for unit tests. For image loading I've used Picasso.

### Disclaimer

Since the tables list endpoint returns only the reservation status (as boolean values) there is no way to know who reserved each table. So, in the reservations activity, there are no relation between the customer selected on the previous screen and the reservations.

In a real world situation we should save the customer id in the reservation.
