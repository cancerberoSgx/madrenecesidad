for deploy: edit MNConstants.develMode = false; 

an application for personal place administration, search and publishing tools based on 
google maps and google places. 

2 main modes: 
 - if not logged in only place search usable
 - if logged in the user can define places, places categories, places websites. 
 

objectives: 
 - personalized place administrator
 - easy place locator (using geo, coords, streets, user favourite places) 
 - easy search of places around our location

facts: 

1) google maps has similar functionality. There are concrete and asbtract place type definitions 
in google maps API (https://developers.google.com/places/documentation/supported_types).
we reuse those, and add the ability of add new ones.  

https://developers.google.com/places/challenge/





model: 

channel, User, Service, Tag, Place




