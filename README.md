# Chatting_App-Kotlin_JetpackCompose_Firebase
A modern Android Chat Application built with Kotlin and Jetpack Compose, featuring real-time messaging powered by Firebase. The app is designed with clean architecture, dependency injection, and scalable Firebase services.

## 🚀 Features

1) Jetpack Compose UI – Modern, responsive, and declarative Android UI
2) Firebase Authentication – Secure user sign-in and sign-up
3) Realtime Database – Instant message synchronization across devices
4) Cloud Firestore – Scalable storage for chat and user data
5) Navigation Compose – Smooth and type-safe navigation between screens
6) Kotlinx Serialization – JSON parsing and object mapping
7) Hilt Dependency Injection – Clean, testable, and maintainable architecture

## 🛠️ Tech Stack

- Language: Kotlin
- UI: Jetpack Compose
- Navigation: Navigation Compose
- Serialization: Kotlinx Serialization
- Dependency Injection: Hilt
- Backend: Firebase Authentication, Firebase Realtime Database, Firestore
- Architecture: MVVM with DI (Hilt)

## 📦 Dependencies
1.) For Navigation- 
```kotlin
implementation("androidx.navigation:navigation-compose:2.9.3")
```
2.) For Serialization- 
```kotlin
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
```

3.) For Hilt-
```kotlin
implementation("com.google.dagger:hilt-android:2.56.2")
kapt("com.google.dagger:hilt-android-compiler:2.56.2")
implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
```
4.) For FireBase- 
```kotlin
implementation(platform("com.google.firebase:firebase-bom:34.1.0"))
implementation("com.google.firebase:firebase-auth")
implementation("com.google.firebase:firebase-database")
implementation("com.google.firebase:firebase-firestore")
```

## 🔧 Setup & Installation
### Step 1- Clone the repository:
```bash
git clone https://github.com/your-username/chatting-app.git
```

### Step 2- Open in Android Studio

### Step 3- Create your own Firebase project at [Firebase Console]-
(https://console.firebase.google.com/)

### Step 4- Enable the following services:
   - Firebase Authentication
   - Realtime Database
   - Cloud Firestore

### Step 5- Download the `google-services.json` file from your Firebase project and place it in the `app/` directory of the project.

### Step 6- Sync Gradle and Run the app 🚀

### Note : 🤝 For Contributors
- Contributions are welcome and appreciated! Whether it's fixing bugs, improving UI, or enhancing documentation — every bit helps.

#### 1. **Fork the Repository**
   - Click on the **Fork** button on the top-right of this repo
   - This creates a copy of the project under your GitHub account.
     
#### 2. Clone the Project
```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```
##### 3.🔁 Create a New Branch

```bash
git checkout -b my-feature-branch
```
#### 4.Make Changes
- Modify code or documentation using Android Studio or your preferred editor

#### 5. ✅ Commit Your Changes
```bash
git add .
git commit -m "✨ Add new feature or fix"
```
#### 6. Push to GitHub
```bash
git push origin my-feature-branch
```
#### 7. Create Pull Request
- Go to your fork on GitHub
- Click on Compare & pull request
- Write a meaningful title and description
- Click Create pull request

## 🌟 Future Improvements

- 🔔 Push Notifications with Firebase Cloud Messaging (FCM)
- 🖼 Media Sharing (images, videos, files) in chats
- 🌙 Dark Mode support for better UX
- 👤 User Profiles with avatars and status updates
- 📱 Group Chats and broadcast messaging
- 🛡 End-to-End Encryption for enhanced security


## 📜 License
This project is licensed under the MIT License – feel free to use and modify.
