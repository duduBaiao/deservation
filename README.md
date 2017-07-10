# Deservation

### Technical stuff

The app was written in Kotlin (it rocks!) and developed following a MVVM architecture. For that, I used a mix of the new Android Architecture Components (ViewModel, LiveData, and Room) and RxJava.

All Android code is confined in the view classes (fragments and activities). The screens works in a reactive way: they subscribe to observe the "live data". When data changes, the screens are updated automatically.

I used Dagger for dependency injection, Expressor for instrumented tests and Mockito for unit tests. For image loading I used Picasso.

### Disclaimer

Since the tables list url returns only the reservation status (as boolean values) there is no way to know who reserved each table. So, in the reservations activity, there are no relation between the customer selected on the previous screen and the reservations.

In a real world situation we should register, for exemple, the customer id in the table registration record.
