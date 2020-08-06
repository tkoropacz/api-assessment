Feature: Latest Foreign Exchange Rates with Symbols and Base

  Scenario Outline: [Positive] Assert response code for get latest date data with base "<base>" and symbols "<symbols>"
    Given user creates request with resource path "latest"
    And with base parameter "<base>"
    And with symbols parameter "<symbols>"
    When user sends get request
    Then response status code should be 200
    Examples:
      | base | symbols |
      |      |         |
      | EUR  |         |
      | USD  |         |
      |      | USD     |
      | USD  | USD     |
      | USD  | GBP,HKD |
      |      | GBP,HKD |

  Scenario Outline: [Positive] Assert response body for get latest date data with base "<base>" and symbols "<symbols>"
    Given user creates request with resource path "latest"
    And with base parameter "<base>"
    And with symbols parameter "<symbols>"
    When user sends get request
    Then response status code should be 200
    And base should be set to "<expectedBase>"
    And rates count should be <ratesCount>
    Examples:
      | base | symbols | expectedBase | ratesCount |
      |      |         | EUR          | 32         |
      | EUR  |         | EUR          | 32         |
      | USD  |         | USD          | 33         |
      |      | USD     | EUR          | 1          |
      | USD  | USD     | USD          | 1          |
      | USD  | GBP,HKD | USD          | 2          |
      |      | GBP,HKD | EUR          | 2          |

  Scenario Outline: [Negative] Get latest date data with base "<base>" and symbols "<symbols>"
    Given user creates request with resource path "<path>"
    And with base parameter "<base>"
    And with symbols parameter "<symbols>"
    When user sends get request
    Then response status code should be 400
    And response body should contain error message "<message>"
    Examples:
      | path   | base | symbols | message                                           |
      | late   |      |         | time data 'late' does not match format '%Y-%m-%d' |
      | latest | AAA  |         | EUR                                               |
      | latest | AAA  | AAA     | USD                                               |
      | latest |      | USD,EUR | EUR                                               |
      | latest | USD  | USD     | USD                                               |
      | latest | USD  | GBP,HKD | USD                                               |
      | latest |      | GBP,HKD | EUR                                               |