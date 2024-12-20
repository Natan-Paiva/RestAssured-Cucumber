Feature: Validating Place API's
  @AddPlace #tag used on test runner to select specific test cases
  Scenario Outline: Verify if place is being successfully added using AddPlaiceAPI
    Given Add Place payload "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" wiht "Post" http request
    Then the API call is success with status code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceAPI"

  Examples:
    | name |  language  | address |
    |Test1 |  English   | city x  |
  #  |Test2 |  Portuguese| city z  |
  @DeletePlace
  Scenario: Verify if Delete Place functionality is working
    Given DeletePlace Payload
    When user calls "DeletePlaceAPI" wiht "Post" http request
    Then the API call is success with status code 200
    And "status" in response is "OK"
