![](https://app.bitrise.io/app/237ba3e50e067fbe.svg?token=-dV482cctBUy3uv9uBImvA)
# No Bullshit - Perfect jobs only

No Bullshit is an open source project that wants to help developers **find the perfect job**. An expert developer reviews each job submitted through the platform. We publish only ***the best***... 😎

This project is intented to show how to create a **full project** using Kotlin. Therefore, you'll find two main modules :

 - **backend** : Contains the [Ktor](https://ktor.io/) backend configured to run on [Google App Engine](https://cloud.google.com/appengine/?hl=fr). It also uses [Freemarker](https://freemarker.apache.org/) for Java/html template. Data are persisted in [Firestore](https://cloud.google.com/firestore/).
 - **android** : Contains the Android app written with Kotlin. It also uses [FirebaseUI](https://github.com/firebase/FirebaseUI-Android), [Dagger2](https://google.github.io/dagger/android.html) & [Mockk](https://mockk.io/).

# Demo
Because a picture is worth a thousand words :
 - 🌏The backend : [https://www.nobullshit.io/](https://www.nobullshit.io/)
 - 📱The mobile app : [Playstore](https://play.google.com/store/apps/details?id=io.nobullshit.nobullshit)
 
# Posts
Some Medium posts about NoBullshit :
 - [Playing with Kotlin: <You know everything John Doe !>](https://medium.com/@Phil_Boisney/playing-with-kotlin-you-know-everything-john-doe-8275a6e98a96)

# Where to start ?
You want to contribute or understand what this is all about, but you don't know where to start? Here are some useful resources :

 **About the backend** :
 - [Ktor Quickstart](https://ktor.io/quickstart/index.html)
 - [Ktor on Google Cloud Appengine Standard](https://cloud.google.com/community/tutorials/kotlin-ktor-app-engine-java8)
 - [Ktor Samples](https://github.com/ktorio/ktor-samples) ❤️
 - [Firestore](https://cloud.google.com/firestore/docs/)

# Running samples
The backend sample can be run **locally** ([http://localhost:8080/](http://localhost:8080/)) using the following script :

    ./run-locally.sh
 If you want to deploy it to **your own GAE project**, you can use the following script :


    ./deploy.sh YOUR_GAE_PROJECT_ID
# Running tests
If you want to run the unit tests for the backend :

    ./gradlew :backend:test

If you want to run the instrumented tests for the android app :
    
    ./gradlew :android:connectedAndroidTest

