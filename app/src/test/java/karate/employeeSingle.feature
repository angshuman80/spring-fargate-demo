Feature: Create and read Employee

  Background:
    * url baseUrl = 'http://localhost:8089'
    * def employeeBase = '/api/employees/'

    @CreateEmp
  Scenario: create Employee
    Given path employeeBase
    And request { name: "Raman", age: 40 , dob: "1980-3-12" }
    When method POST
    Then status 201
    And match $.name == "Raman"



  Scenario: Get Employee by name
    Given path employeeBase + "Raman"
    When method GET
    Then status 200
    And match $.name == "Raman"





