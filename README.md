# GenerationC
demo base app using android best practices in, Multi Module, clean articulate, MVVM, XML , Testing , Navigator , Hilt , Navigation , Single Activity , Room , Retrofot , DSL 

# Overview

 use case :  fetch univeristes data by country from server and/or cash 

# Technologies

The project attempt to adapt best practices in android development flowing SOILD principles wih,
t lates modern Android tools and frameworks such as Kotlin, Jetpack , Coroutines, Flow, Hilt,
Clean code, Navigation ,
employs automatic state and error handling and wrappers to handle calls

## Testing

Junit,Mockito and Illustration integration with Espresso Testing

# Project Structure

The project uses a MVVM architecture, data is obtained form local data source, 
as the needed data is retrieved fresh from the file system,that contained in separate Data layer,
Business logic and use cases are placed in a separate Domain,
View Models are used to communicate with the UI layer,
which is primarily written with the Compose framework.

#Structure

adapting a Clean architecture. 
All UI related code can be found in the ".presentation"
package and is subdivided into packages according to view
".domain" package contains systems models , repositories, mappers..
".data" pancake contains implementation of data source "Retorfit" and date transfer objects. 

adapting MVVM as main beavioual design pattern , align with singlton object for as creations desin 

#Testing 

Junit test for local test
ex- Testing in local repository


![Screenshot_20240504_203802](https://github.com/eslamfahmy2/Invade/assets/74387512/91f59f7f-5d9e-4697-92bc-8c4b3e70ee9f)
![Screenshot_20240504_203819](https://github.com/eslamfahmy2/Invade/assets/74387512/32c6ddf7-c2cf-4ce6-9477-ea837de826da)


