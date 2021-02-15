@CityBikes
  Feature: city bike api

  Scenario: base url should return all networks
    Given user is navigates to base url
    When makes get request
    Then see status code 200

  Scenario: verify that frankfurt is in Germany and it latitude and longitude
    Given user is navigates to base url
    And query with city "visa-frankfurt"
    When makes get request
    Then receive country "DE" latitude <50.1072> and longitude <8.66375>

  Scenario: filtering fields of api
    Given user is navigates to base url
    And applys filter for field "href" and "id"
    Then should only see these fields in result