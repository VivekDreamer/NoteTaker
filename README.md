# NoteTaker API

NoteTaker API is a secure and scalable RESTful API that allows users to manage notes, share them with others, and search based on keywords.

## Table of Contents

- [Introduction](#introduction)
- [Technical Stack](#technical-stack)
- [API Endpoints](#api-endpoints)
  - [Authentication Endpoints](#authentication-endpoints)
  - [Note Endpoints](#note-endpoints)
- [PreRequisite To Test With DB](#DB-Setup)

## Introduction

NoteTaker API is designed to provide users with the ability to create, read, update, and delete notes. It also supports sharing a particular note with other user and searching for notes based on keywords.

## Technical Stack

- **Framework:** [Spring Boot](https://spring.io/projects/spring-boot)
- **Database:** [PostgreSQL](https://www.postgresql.org/)
- **Authentication:** JSON Web Token (JWT)
- **Rate Limiting & Request Throttling:** Not Implemented
- **Search Functionality:** Not used any Text indexing for high performance

I chose Spring Boot for its ease of development, robustness, and wide community support. PostgreSQL was selected as the database for its relational capabilities. JWT provides a secure authentication mechanism. Text indexing is implemented for efficient note searches.

## API Endpoints

### Authentication Endpoints

- **POST /api/auth/signup:** Create a new user account.
- **POST /api/auth/login:** Log in to an existing user account and receive an access token.

### Note Endpoints

- **GET /api/notes:** Get a list of all notes for the authenticated user.
- **GET /api/notes/:id:** Get a note by ID for the authenticated user.
- **POST /api/notes:** Create a new note for the authenticated user.
- **PUT /api/notes/:id:** Update an existing note by ID for the authenticated user.
- **DELETE /api/notes/:id:** Delete a note by ID for the authenticated user.
- **POST /api/notes/:noteid/share?emailToShared=:email:** Share a note of noteid with another user for the authenticated user.
- **GET /api/search?q=:query:** Search for notes based on keywords for the authenticated user.

## DB-Setup
- Create a db named as note_taker in PostgreSQL having username and password.
- Just start the application locally and hit apis.
- Just saw linkedin post, due to time constraint not able to  apply other functionality..This is completed in 3.5 hours.


