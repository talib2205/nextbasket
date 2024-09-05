@browser
Feature: Order the first in-stock non-promo product

  Scenario: Successfully order the first in-stock non-promo product
    Given I navigate to the test store
    When I search for the first non-promo in-stock product
    And I add the product to the cart
    And I proceed to checkout
    And I enter shipping details with "South Africa" as country and "Alberton" as city
    And I complete the order
    Then I should see the order confirmation
