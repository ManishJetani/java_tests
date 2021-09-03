@PostcodeInfo
Feature: postcode information

  Scenario: get postcode information
    Given api base url is "http://api.zippopotam.us/"
    And passes country "us" in path as first parameter
    And passes zipcode "90210" in path as second parameter
    When makes postcode get request
    Then postcode service status code should be 200
    And response country should be "United States"
    And response country abbreviation should be "US"