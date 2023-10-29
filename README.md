# Spring Boot and Firebase Remote Config Integration Sample

## Table of Contents
- [Overview](#overview)
- [Built With](#built-with)
- [Author](#author)
- [Acknowledgments](#acknowledgements)

## Overview
<!-- TODO: Add a screenshot of the live project.
    1. Link to a 'live demo.'
    2. Describe your overall experience in a couple of sentences.
    3. List a few specific technical things that you learned or improved on.
    4. Share any other tips or guidance for others attempting this or something similar.
 -->
Sample application on integrating Firebase Remote Config with Spring Boot app.
It demonstrates:
* how to publish remote config parameters value using firebase-admin.
* how to create service class for the use-case that easy to test
* how to avoid the tests to connect to Firebase service by mocking Firebase services in integration tests

You will need to provide [google application credentials](https://firebase.google.com/docs/remote-config/automate-rc#:~:text=When%20authorizing%20via,is%20strongly%20recommended.) in order to run it.

TODO:
* create simple web ui to allow user input for changing remote config parameter. I'm planning to use htmx for this.
* setup build mechanism via CI server

## Built With
- Jdk21
- Spring Boot 3.1.5
- firebase-admin 9.2.0

## Author 
[Addin](www.linkedin.com/in/addinul-kintangi)

## Acknowledgements
* https://firebase.google.com/docs/remote-config/automate-rc
* https://github.com/firebase/quickstart-java

