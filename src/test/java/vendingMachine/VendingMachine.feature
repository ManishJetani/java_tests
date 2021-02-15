@vendingMachine
Feature: vending machine should work as expected

  Scenario: machine accepts only valid coins
    Given machine is ready
    When inserts following coins
      |1|5|10|25|
    Then user should see total 41 paid

  Scenario Outline: allows user to select product Coke(25), Pepsi(35), Soda(45)
    Given machine is ready
    When user press <button> on machine
    Then product is "<selected>" and "<amount>" displayed
    Examples:
      | button | selected | amount|
    | 0       | Coke    | 25    |
    | 1       | Pepsi   | 35    |
    | 2       | Soda    |45     |

    Scenario: allows user to take refund by cancelling request
      Given machine is ready
      And user press 1 on machine
      And inserts following coins
      |25|25|
      And cancels request
      Then machine refunds 50 cents

  Scenario: Returns selected product and remaining change
    Given machine is ready
    And user press 2 on machine
    And inserts following coins
    |25|25|
    And confirm request
    Then machine returns "Soda" and <5> cent change

  Scenario: Reset operation
    Given machine is ready
    And user press 1 on machine
    But supplier reset it
    Then it gets reset