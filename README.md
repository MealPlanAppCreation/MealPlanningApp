### Description
An app designed to make meal preparations by providing recipe suggestions, and customizable meal plans with ingredients and materials for all meals.

## Product Spec
### 1. User Features
**Required Features**

1. [x] Users can create accounts and log in
2. [x] User can view list of suggested recipes from an API
3. [x] User can select meals from a personalized library to create weekly plans.

**Optional Features**
1. [x] User can save favorite meals in a personalized library
2. [x] User can add their own recipes
3. [x] User have Navigation UI Views (BottomNavigation) to move between the fragments and components easily

### 2. Screen Archetypes
#### Login Screen
* User can login

#### Registration Screen
* User can create a new account

#### Home Screen
* User can view a list of suggested meals and Calendar

#### Favorite Meal Screen
* User can select meals to create a weekly plan
* User can view the created meal plan
* User can add recipe to personalize library

#### Detail Recipe Screen
* User can fully view the details of each each meal
* User can add or remove from favorite personalize library
* User can add to calendar

#### Adding Recipe Screen
* User can input recipe details
* User can select photos from phone libary

### 3. Navigation
**1. Tab Navigation** (Fragment Switching)

#### Recipe List Fragment
* Displays a list of recipes fetched from an API.
* Recipes are clickable to navigate to their Detail View.
* Can view calendar

#### Favorites Fragment
* Shows a list of user-favorite meals.
* Favorites are clickable to navigate to their Detail View.
* can view calendar
* can add personalized recipes

**2. Flow Navigation** (Screen to Screen)
### Login Screen
* Navigates to the Home Screen upon successful login.

### Home Screen
* Contains the tab navigation (Recipe List and Favorites).
* Recipes on RecycleView and Calendar can be click to view Detail View (when a meal/recipe is clicked).

### Detail View Screen
* Displays detailed information about a selected recipe/meal.
* Can navigate back to Home Screen.


<br>

## Completed user stories
1. **Minimum Viable Product (MVP)**:
   - [x] **Required Features**: 3 essential features critical to the app's main purpose.
     - [x] Users can create accounts and log in
       
       ![login](https://github.com/user-attachments/assets/791f50a5-5707-4547-9fe6-134bfed89580)
     - [x] User can view list of suggested recipes from an API
       
       ![api](https://github.com/user-attachments/assets/5cbbfff0-2e5a-444b-98f8-37cfb172e4b4)
     - [x] User can select meals from a personalized library to create weekly plans.
       
       ![addCal](https://github.com/user-attachments/assets/ab2edb1a-2834-4532-a1d2-1259cbd5f67c)

   - **Secondary Features**: 3 additional features that enhance the app.
     - [x] User can save favorite meals in a personalized library
       
       ![saveF](https://github.com/user-attachments/assets/afcdd33d-7a94-4ed4-ad4a-2c83856a1e87)
     - [x] User can add their own recipes
       
       ![addR](https://github.com/user-attachments/assets/6295c8d5-dc40-433c-9757-023d90cc1df6)
     - [x] User have Navigation UI Views (BottomNavigation) to move between the fragments and components easily
       
       ![Nav](https://github.com/user-attachments/assets/39e21813-42a4-4343-9d1e-23daf3d8c501)


2. **Firebase Integration**:
   - [x] Use **Firebase** for two key functionality:
     - [x] Use **User Authentication** to save email/password and authenticate users.
            ![image](https://github.com/user-attachments/assets/47020890-d54c-4756-b2ca-b6ad55c6dad5) ![auth](https://github.com/user-attachments/assets/2c82c96a-5e7c-48fe-886c-0b315779ff7d)

     - [x] Use **Real-time Database/Cloud Firestore** to save user calendar data.

3. **API Integration**:
   - [x] Integrate at least one **external API** to enhance the app.
     - API USE: https://www.themealdb.com/api.php

      ![api](https://github.com/user-attachments/assets/86e8a60d-f522-4385-b2a9-5d2e0bbf9462)


4. **Database Usage**:
   - [x] Use **Room Database** and **Firebase Firestore** to store user data persistently
      - [x] Use **Room Database** to store a list of favorite recipes
      - [x] Use **Firebase Firestore** to store user calendar data.
       ![image](https://github.com/user-attachments/assets/f4546cfe-acd8-4fee-8bed-3664e6121ce6)


5. **Screen Archetypes**:
   - [x] Design with at least three main screen types to cover the core functions and ensure a clear user journey.
     - Home/Favorite Fragments
     - Detail Recipe Screen
     - Adding personalize Recipe Screen

      ![screen](https://github.com/user-attachments/assets/0484e01b-2cb0-4feb-bbbc-5e38e3667061)

6. **Mobile-Specific Features**:
   - [x] Incorporate at least one mobile-specific capability that adds unique value to the mobile experience.
     - User can select photos from their phone to add to the app.
     
       ![GALLERY](https://github.com/user-attachments/assets/1eb60fe0-ed89-4901-9a50-88891f6b6a72)

