Feature: Payment

  Scenario: Successful Payment
    Given a customer with id "cid1"
    And a merchant with id "mid1"
    When the merchant initiates a payment for 10 kr by the customer
    Then the payment is successful

#  Scenario: Customer is not known
#    Given a customer with id "cid2"
#    And a merchant with id "mid1"
#    When the merchant initiates a payment for "10" kr by the customer
#    Then the payment is not successful
#    And an error message is returned saying "customer with id cid2 is unknown"
