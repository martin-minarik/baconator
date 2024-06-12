# Baconator

- This is a very simple Spring Boot application that exposes one REST endpoint that
returns a specified number of "bacon" strings.

- The application fetches bacon data from [Bacon Ipsum](https://baconipsum.com/api/?type=all-meat&paras=1)


## Endpoint

- `give-me-bacon/{howmuch}`
  - http://localhost:8080/give-me-bacon/1
  - **Method:** GET
  - **Path Parameter:** howmuch - The number of "bacon" strings to return.
  - **Response:** JSON
