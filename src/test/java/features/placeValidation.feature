Feature: Validating Place API's

  Scenario: Verify if place is being successfully added using AddPlaiceAPI
    Given Add Place payload
    When user calls AddPlaceAPI wiht post http request
    Then the API call is success with status code 200
    And status in response body is OK