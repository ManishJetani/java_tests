@StringOperations
Feature: count double letters in a string

  Scenario Outline: correct count
    Given when passed <input>
    Then it returns <expectedCount>
    Examples:
      | input    | expectedCount |
      | "banana" | 0             |
      | "apple"  | 1             |
      | "bamboo" | 1             |
      | "a"      | 0             |
      | ""       | 0             |
      | "baacaa" | 2             |

