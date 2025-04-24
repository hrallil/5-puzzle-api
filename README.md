# 15-puzzle-api
API for the Java Tech challenge given by Danske Bank for creating an API allowing for a frontend application to play the 15 puzzle game

This API primarily works on two objects; Game and User. These two can be affected using the endpoints described in the Endpoint section.

# Endpoints
Base URL is `localhost:80` as this is hosted locally.
- `localhost:80/get/game/{gameId}`: Returns a game, given a Game ID
- `localhost:80/move/game`: applies a move to a game, given a Game ID. 
- `localhost:80/create/game`: Creates a game attached to a user, given a user ID.
- `localhost:80/login`: Creates a JWT token that the user can use to access the other endpoints with, given a username and a password of an existing user.
- `localhost:80/create/user`: Creates a new user, given a password and a username.
- `localhost:80/get/user/{userId}`: Returns a specific user, given a user ID. 
- `localhost:80/get/user/all`: Returns a list of all users 
- `localhost:80/info`: Currently simply returns "*15 Puzzle API - Version 1.0*". This can be expanded to show other useful information about the application.
- `localhost:80/health`: Always returns "*OK!*". This is to allow the application to be connected to some sort of monitoring system, like grafana. 
## Request 
Yet to be standardized through DTO
## Response
Yet to be standardized through DTO
## Error
- Game not found
- User not found
- Invalid Token
- No token found
- Invalid move
