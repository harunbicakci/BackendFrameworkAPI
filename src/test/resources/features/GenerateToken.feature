@Token
Feature: Generate Token API

  Background:
    Given I create a generate token request
    And I provide the header information
    And I provide the body information

  Scenario:
    When I make a POST call to generate token endpoint
    Then I validate the status code is 200
    And I validate that body contains "Success"
    And I validate that the value of the "result" is "User authorized successfully"