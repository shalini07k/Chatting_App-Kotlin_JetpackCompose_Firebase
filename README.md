# **Chatting_App-Kotlin_JetpackCompose_Firebase**
A modern Android Chat Application built with Kotlin and Jetpack Compose, featuring real-time messaging powered by Firebase. The app is designed with clean architecture, dependency injection, and scalable Firebase services.

![WhatsApp Image 2025-09-15 at 01 47 22_83bd55d0](https://github.com/user-attachments/assets/c9ff65ca-8a4a-46c9-8dd6-a4ecdd146020)

![WhatsApp Image 2025-09-15 at 01 33 32_a5f54b48](https://github.com/user-attachments/assets/0ad1bde5-9557-471b-adf2-f98ab86b0b58)


![WhatsApp Image 2025-09-15 at 01 33 33_4ed55bc5](https://github.com/user-attachments/assets/e9adc927-1615-465c-98c6-15c5f6748e2b)

![WhatsApp Image 2025-09-15 at 01 33 33_56408846](https://github.com/user-attachments/assets/9b179e94-1a81-44cd-8b3f-0d2466fb9272)


![WhatsApp Image 2025-09-15 at 01 33 32_fb1f9981](https://github.com/user-attachments/assets/48533987-7d46-4265-b657-c1dea258cea4)
![WhatsApp Image 2025-09-15 at 01 33 31_f9c11e16](https://github.com/user-attachments/assets/3736069d-ca4a-4718-b9a3-166c61b6d742)


## 🚀 **Features**

1) **Jetpack Compose UI** – Modern, responsive, and declarative Android UI
2) **Firebase Authentication** – Secure user sign-in and sign-up
3) **Realtime Database** – Instant message synchronization across devices
4) **Cloud Firestore** – Scalable storage for chat and user data
5) **Navigation Compose** – Smooth and type-safe navigation between screens
6) **Kotlinx Serialization** – JSON parsing and object mapping
7) **Hilt Dependency Injection** – Clean, testable, and maintainable architecture

## 🛠️ **Tech Stack**

- **Language** : Kotlin
- **UI** : Jetpack Compose
- **Navigation** : Navigation Compose
- **Serialization** : Kotlinx Serialization
- **Dependency Injection** : Hilt
- **Backend** : Firebase Authentication, Firebase Realtime Database, Firestore
- **Architecture** : MVVM with DI (Hilt)

## 📦 **Dependencies**
#### 1.) For Navigation- 
``` build.gradle
implementation("androidx.navigation:navigation-compose:2.9.3")
```
#### 2.) For Serialization- 
``` build.gradle
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
```

#### 3.) For Hilt-
``` build.gradle
implementation("com.google.dagger:hilt-android:2.56.2")
kapt("com.google.dagger:hilt-android-compiler:2.56.2")
implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
```
#### 4.) For FireBase- 
``` build.gradle
implementation(platform("com.google.firebase:firebase-bom:34.1.0"))
implementation("com.google.firebase:firebase-auth")
implementation("com.google.firebase:firebase-database")
implementation("com.google.firebase:firebase-firestore")
```

## 🔧 Setup & Installation
#### **Step 1** - Clone the repository:
```bash
git clone https://github.com/your-username/chatting-app.git
```

#### **Step 2** - Open in Android Studio

#### **Step 3** - Create your own Firebase project at [Firebase Console]-
(https://console.firebase.google.com/)

#### **Step 4** - Enable the following services:
   - Firebase Authentication
   - Realtime Database
   - Cloud Firestore

#### **Step 5** - Download the `google-services.json` file from your Firebase project and place it in the `app/` directory of the project.

#### **Step 6** - Sync Gradle and Run the app 🚀

### **Note** : 🤝 For Contributors
- Contributions are welcome and appreciated! Whether it's fixing bugs, improving UI, or enhancing documentation — every bit helps.

#### 1. **Fork the Repository**
   - Click on the **Fork** button on the top-right of this repo
   - This creates a copy of the project under your GitHub account.
     
#### 2. **Clone the Project**
```bash
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
```
##### 3.🔁 **Create a New Branch**

```bash
git checkout -b my-feature-branch
```
#### 4. **Make Changes**
- Modify code or documentation using Android Studio or your preferred editor

#### 5. ✅ **Commit Your Changes**
```bash
git add .
git commit -m "✨ Add new feature or fix"
```
#### 6. **Push to GitHub**
```bash
git push origin my-feature-branch
```
#### 7. **Create Pull Request**
- Go to your fork on GitHub
- Click on Compare & pull request
- Write a meaningful title and description
- Click Create pull request

## 🌟 **Future Improvements**

- 🔔 Push Notifications with Firebase Cloud Messaging (FCM)
- 🖼 Media Sharing (images, videos, files) in chats
- 🌙 Dark Mode support for better UX
- 👤 User Profiles with avatars and status updates
- 📱 Group Chats and broadcast messaging
- 🛡 End-to-End Encryption for enhanced security


## 📜 **License**
This project is licensed under the MIT License – feel free to use and modify.
