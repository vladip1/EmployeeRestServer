# EmployeeRestServer
Employees management REST service 

Instructions

1. Download and install the Geode binary distribution from http://geode.apache.org/releases/. 

2. Add gfsh to the PATH environment variable

3. Activate gfsh utility from command line and then run following commands:
start locator
start server
create region --name=employees --type=REPLICATE

4. Save employees_list.json file under C:/

5. Build and Run EmployeeRestServer project

6. To test EmployeeRestServer service use Postman application (https://www.getpostman.com/)

7. Following paths are available:

---------------------------------------------------------------------
To receive all the active employees:

GET /employee/all HTTP/1.1
Host: localhost:8080
Content-Type: application/json
---------------------------------------------------------------------
To add a new employee(s):
POST /employee/ HTTP/1.1
Host: localhost:8080
Content-Type: application/json
[
  {
        
        "id": 1182291,
        "firstName": "Eyalush",
        "MiddleInitial": "none",
        "lastName": "Rot",
        "DateOfBirth": "2001-11-11",
        "DateOfEmployment": "2001-11-11",
        "Status": 1
    }
]
---------------------------------------------------------------------
To update existent employee(s):
PUT /employee/ HTTP/1.1
Host: localhost:8080
Content-Type: application/json
[
  {   
        "id": 10118,
        "firstName": "EyalushNew",
        "MiddleInitial": "none",
        "lastName": "Rot",
        "DateOfBirth": "2001-11-11",
        "DateOfEmployment": "2001-11-11",
        "Status": 1
    } 
]
---------------------------------------------------------------------
To get information of a particular employee:

GET /employee/{employee id} HTTP/1.1
Host: localhost:8080
Content-Type: application/json
---------------------------------------------------------------------
To make employees inactive (demands authorization: user=user, password should be taked from RestServer console log:e.g. Using default security password: 90b74cec-4c25-4562-a7d0-077fec8a1d3f):

DELETE /delete HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Authorization: Basic dXNlcjo5MGI3NGNlYy00YzI1LTQ1NjItYTdkMC0wNzdmZWM4YTFkM2Y=
[
  {
    "id": 10118
  },
  {
  	"id": 11891
  }
]
