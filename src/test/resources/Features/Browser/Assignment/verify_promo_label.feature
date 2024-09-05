Feature: Verify "50% Off" label visibility

  Scenario: Verify that the "50% Off" label is visible on products
    Given I navigate to the test store
    When I search for a product with a "50% Off" label
    Then I should see the "50% Off" label displayed