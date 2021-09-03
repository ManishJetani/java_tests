@PostcodeInfo
Feature: postcode information

  Scenario: get valid postcode information for us
    Given api base url is "http://api.zippopotam.us/"
    And passes country "us" in path as first parameter
    And passes zipcode "90210" in path as second parameter
    When makes postcode get request with path "{country}/{zipcode}"
    Then postcode service status code should be 200
    And response country should be "United States"
    And response country abbreviation should be "US"
    And latitude should be 34.0901
    And state should be "California"
    And place name should be "Beverly Hills"
    And longitude should be -118.4065

  Scenario: get 404 not found when passing invalid post code
    Given api base url is "http://api.zippopotam.us/"
    And passes country "uk" in path as first parameter
    And passes zipcode "90210" in path as second parameter
    When makes postcode get request with path "{country}/{zipcode}"
    Then postcode service status code should be 404

  Scenario: get 404 not found when passing only country in base path
    Given api base url is "http://api.zippopotam.us/"
    And passes country "uk" in path as first parameter
    When makes postcode get request with path "{country}"
    Then postcode service status code should be 404

  Scenario: get 404 not found when passing only postcode in base path
    Given api base url is "http://api.zippopotam.us/"
    And passes zipcode "90210" in path as second parameter
    When makes postcode get request with path "{zipcode}"
    Then postcode service status code should be 404