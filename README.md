# Simple REST API with Spring Boot, PostgreSQL and Docker
[![Build Status](https://travis-ci.com/sergeyskotarenko/contacts-api.svg?branch=master)](https://travis-ci.com/sergeyskotarenko/contacts-api)

## Contacts API

* ### URL

  `/hello/contacts`

* ### Method:
  
  `GET`
  
* ### URL Params

   #### Required:
 
   `nameFilter=[alphanumeric]` - a regular expression

   #### Optional:
 
   `limit=[integer]` - a limit of returned records

   `afterId=[integer]` - an ID of a contact after which search should start 

* ### Success Response:
  
  * #### Code: 200 <br />
  * #### Content:
  ```json
    {
    	"contacts": [{
    		"id": 3,
    		"name": "ROBERT"
    	}, {
    		"id": 4,
    		"name": "MICHAEL"
    	}, {
    		"id": 5,
    		"name": "MARY"
    	}],
    	"lastId": 5
    }
    ``` 
    `lastId` - an ID of last contact in returned page. In case when `lastId = -1` - no more records exist for given filter	

## Scripts
### Run next command from project root directory to build an app:
 ```bash
 mvn clean install
```
### Run next command from project root directory to start Docker containers:
```bash
 docker-compose up -d
```
### Application is available on localhost:8083
