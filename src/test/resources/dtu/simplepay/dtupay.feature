Feature: Payment

  Scenario: Successful Payment
    Given the customer "Ryan" "Anderson" with CPR "061100-7173" has a bank account
    And the balance of that account is 1000
    And the customer is registered with DTUPay
    And the merchant "Jo" "Kuckles" with CPR number "110561-2741" has a bank account
    And the balance of that account is 2000
    And the merchant is registered with DTUPay
    When the merchant initiates a payment for 10 kr by the customer
    Then the payment is successful
    And the balance of the customer at the bank is 990 kr
    And the balance of the merchant at the bank is 2010

  Scenario: List of transactions
    Given the customer "Ryan" "Anderson" with CPR "061100-7173" has a bank account
    And the balance of that account is 1000
    And the customer is registered with DTUPay
    And the merchant "Jo" "Kuckles" with CPR number "110561-2741" has a bank account
    And the balance of that account is 2000
    And the merchant is registered with DTUPay
    And the merchant initiates a payment for 10 kr by the customer
    When the manager asks for a list of transactions
    Then the list contains a transaction where the customer paid 10 kr to the merchant

  Scenario: Customer is not known
    Given a customer with id "cid2"
    And the merchant "Jo" "Kuckles" with CPR number "110561-2741" has a bank account
    And the balance of that account is 2000
    And the merchant is registered with DTUPay
    When the merchant initiates a payment for 10 kr by the customer
    Then the payment is not successful
    And an error message is returned saying "customer with id cid2 is unknown"

  Scenario: Merchant is not known
    Given the customer "Ryan" "Anderson" with CPR "061100-7173" has a bank account
    And the balance of that account is 1000
    And the customer is registered with DTUPay
    And a merchant with id "mid2"
    When the merchant initiates a payment for 10 kr by the customer
    Then the payment is not successful
    And an error message is returned saying "merchant with id mid2 is unknown"




