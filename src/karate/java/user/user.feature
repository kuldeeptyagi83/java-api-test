Feature: sample karate test script
for help, see: https://github.com/karatelabs/karate/wiki/IDE-Support

  Background:
    * url 'http://java-app:8080/api'

  Scenario: create a user and then get it by id
    * def user =
      """
      {
        "name": "Test User",
        "email": "test@user.com",
      }
      """

    Given path 'users'
    And request user
    When method post
    Then status 200

    * def id = response.id
    * print 'created id is: ', id

  Scenario: get all users and then get the first user by id
    Given path 'users'
    When method get
    Then status 200

    * def first = response[0]

    Given path 'users', first.id
    When method get
    Then status 200


