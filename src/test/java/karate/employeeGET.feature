Feature: Create and read Employee

  Background:
    * url baseUrl = 'http://localhost:8089'
    * def employeeBase = '/api/employees/'
    * def result = call read('employeeSingle.feature@createEmp')
    * def value = result.name
    * print "given result of A is: $result"


  Scenario: Get Employee by name
    Given path employeeBase + value
    When method GET
    Then status 200
    And match $.name == value





