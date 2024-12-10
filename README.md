# Milestone 1 - Meal Planner (Unit 7)

## Table of Contents

1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Project Requirements](#Project-Requirements)
## Overview

### Description

An app designed to make meal preparations by providing recipe suggestions, and customizable meal plans with ingredients and materials for all meals.

### App Evaluation

[Evaluation of your app across the following attributes]
- **Category**
  - (Health & Fitness)
* **Mobile**: How uniquely mobile is the product experience?
    * What makes your app more than a glorified website?
        * Push notifications for meal prep reminders
        * Provides recipes and ingrediants list
        * Customizable meal plans
* **Story**: How compelling is the story around this app once completed?
    * How clear is the value of this app to your audience?
        * The app offers meal planning and tip recipes that anyone who likes cooking would enjoy.
    * How well would your friends or peers respond to this product idea?
        * Peers would resopnd enthusiastically, as many struggle with meal preparations.
* **Market**: How large or unique is the market for this app?
    * What's the size and scale of your potential user base?
        *  The scale is worldwise as many people are aways focus on healthy eating habits, budgeting, and meal prepping.
    * Does this app provide huge value to a niche group of people?
        * Yes, it offers signincant value to those who enjoy cooking.
    * Do you have a well-defined audience of people for this app?
        * Yes,anyone who is looking a weekly meal planner who offer ideas would love this app/
* **Habit**: How habit-forming or addictive is this app?
    * How frequently would an average user open and use this app?
        * Most users would engage in weekly plan meals and follow on recipes.
    * Does an average user just consume your app or do they create?
        * Both, they consume meal suggestion and recipes while creating customized meal plans.
* **Scope**: How well-formed is the scope for this app?
    * How technically challenging will it be to complete this app by the end of the program?
        * Basic features like recipe suggesting and groceru list generation are straightforward.
    * Is a stripped-down version of this app still interesting to build?
        * Yes, even a basic version with meal plans and recipes would be of significant value.
    * How clearly defined is the product you want to build?
        * The product is clearly define as a tool used for meal planning with recipe suggestion, shoppoing list,etc.
  

## Product Spec

### 1. User Features (Required and Optional)

**Required Features**

1. [x] Users can create accounts and log in
2. [x] User can view list of suggested recipes from an API
3. [x] User can select meals from a personalized library to create weekly plans.

**Optional Features**
1. [x] User can save favorite meals in a personalized library
2. [x] User can add their own recipes
3. [x] User have Navigation UI Views (BottomNavigation) to move between the fragments and components easily
4. [ ] App sends reminders for meal prep times

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

# Milestone 2 - Build Sprint 1 (Unit 8)

## GitHub Project board
![image](https://github.com/user-attachments/assets/35a6a77d-62cd-4740-9323-78d8494ca562)


![image](https://github.com/user-attachments/assets/938c650d-8986-45f9-af29-ad76751f82c1)


## Issue cards
![image](https://github.com/user-attachments/assets/018eb9a5-d8a4-49d8-b72f-48102da3e1da)


## Issues worked on this sprint
- List the issues you completed this sprint
- ![image](https://github.com/user-attachments/assets/60e1ab9d-2966-40f0-a8c0-2676d8c032a0)

- ![MealPlanner](https://github.com/user-attachments/assets/3696d3ef-6af3-4fce-a74e-15951759b1c5)


<br>

# Milestone 3 - Build Sprint 2 (Unit 9)

## GitHub Project board
![image](https://github.com/user-attachments/assets/a629a2db-2925-40ca-b639-98710a0d1eef)

![image](https://github.com/user-attachments/assets/36dde0a3-468e-4ce1-8788-13a1f65a4aab)


## Completed user stories

### List the completed user stories from this unit
# Project-Requirements

## Core Functional Requirements
1. **Minimum Viable Product (MVP)**:
   - [x] **Required Features**: Implement at least 3 essential features critical to your app's main purpose.
     - [x] Users can create accounts and log in
       
       ![login](https://github.com/user-attachments/assets/791f50a5-5707-4547-9fe6-134bfed89580)
     - [x] User can view list of suggested recipes from an API
       
       ![api](https://github.com/user-attachments/assets/5cbbfff0-2e5a-444b-98f8-37cfb172e4b4)
     - [x] User can select meals from a personalized library to create weekly plans.
       
       ![addCal](https://github.com/user-attachments/assets/ab2edb1a-2834-4532-a1d2-1259cbd5f67c)

   - **Secondary Features**: Include at least 3 additional features that enhance the app.
     - [x] User can save favorite meals in a personalized library
       
       ![saveF](https://github.com/user-attachments/assets/afcdd33d-7a94-4ed4-ad4a-2c83856a1e87)
     - [x] User can add their own recipes
       
       ![addR](https://github.com/user-attachments/assets/6295c8d5-dc40-433c-9757-023d90cc1df6)
     - [x] User have Navigation UI Views (BottomNavigation) to move between the fragments and components easily
       
       ![Nav](https://github.com/user-attachments/assets/39e21813-42a4-4343-9d1e-23daf3d8c501)


2. **Firebase Integration**:
   - [x] Use **Firebase** for at least one key functionality:
     - [x] Use **User Authentication** to save email/password and authenticate users.
     - [x] Use **Real-time Database/Cloud Firestore** to save user calendar data.

3. **API Integration**:
   - [x] Integrate at least one **external API** to enhance the app (e.g., for data retrieval, messaging, location services). This should be a public API
     - API USE: https://www.themealdb.com/api.php

4. **Database Usage**:
   - [x] Use **Room Database** and **Firebase Firestore** to store user data persistently, supporting at least basic **CRUD operations** (Create, Read, Update, Delete).
      - [x] Use **Room Database** to store a list of favorite recipes
      - [x] Use **Firebase Firestore** to store user calendar data.

## UI and UX Requirements

5. **Navigation**:
   - [x] **Tab Navigation**: Include at least two primary navigation tabs for easy access to main features.
   - [x] **Flow Navigation**: Implement structured navigation flows (e.g., detail screens, screen transitions).

6. **Screen Archetypes**:
   - [x] Design with at least three main screen types to cover the core functions and ensure a clear user journey.
     - Home/Favorite Fragments
     - Detail Recipe Screen
     - Adding personalize Recipe Screen
 

## Additional Project Requirements
7. **Mobile-Specific Features**:
   - [x] Incorporate at least one mobile-specific capability that adds unique value to the mobile experience.
     - User can select photos from their phone to add to the app.

8. **UI Consistency**:
   - [x] Ensure a consistent look and feel across all screens, with consistent use of fonts, colors, and layouts.



- List any pending user stories / any user stories you decided to cut from the original requirements
   -  App sends reminders for meal prep times (Wanted to use the store date in firebase and send notification on the morning that the user was planning to do the recipe, but couldn't figure how in the time frame given)

## App Demo Video

- [App Demo YouTube link of Completed Demo](https://youtu.be/Sr1F0AbHiEc)
