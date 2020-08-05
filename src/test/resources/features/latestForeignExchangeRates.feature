Feature: Latest Foreign Exchange Rates with Symbols and Base

  Scenario: [Positive] Get latest foreign exchange rates status code
    Given user creates request with resource path "latest"
    And with base parameter "USD"
    And with symbols parameter "EUR"
    When user sends get request
    Then response status code should be 200