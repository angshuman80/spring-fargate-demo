Feature: Create and read Employee

  Background:
    * url baseUrl = 'http://localhost:8089'
    * def employeeBase = '/api/employees/'

  Scenario Outline: create Employee
    Given path employeeBase
    And request { name: '<name>', age: '<age>' , dob: '<dob>' }
    When method POST
    Then status 201
    And match $.name == '<name>'

    Examples:
      | name  | age | dob |
      | Tim   | 3   | 1980-12-3 |
      | Liz   | 3   | 1980-12-3 |
      | Selma | 5   | 1980-12-3 |
      | Ted   | 3   | 1980-12-3 |
      | Luise | 5   | 1980-12-3 |

  Scenario: Get Employee by name
    Given path employeeBase + "Tim"
    When method GET
    Then status 200
    And match $.name == "Tim"





