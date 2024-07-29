@AddUser
  Feature: Add new user

    Background:
      Given I create a create new user request
      And I provide the header information new
      And I provide the body information for new user "BullyJoe02" and "pass@1231"

    Scenario:
      When I make a POST call to create new user endpoint
      Then validate the status code is 201
      And validate that body contains "Created"
      And validate that the value of the "result" is "User created"