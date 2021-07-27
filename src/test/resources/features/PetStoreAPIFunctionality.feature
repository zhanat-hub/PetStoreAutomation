@PS-20 @api
  Feature: Creating a pet
    @regression @smoke @tc3      #(P) -> Positive Scenario
    Scenario: (P) User creates a new pet with an image
      Given User creates a new pet with POST request
      Then User validates new pet is created with status code 200
      And User validates new pet is created with GET request

    @regression @smoke @tc5
    Scenario: (P) Searching for created pet
      Given User creates a new pet with POST request
      Then User validates new pet is created with GET request
      And User validates new pet is created with status code 200
      When User searches for similar pets by status "ready" with GET request
      Then User validates status code of GET request is 200
      And User validates searched pets share the same status "ready"

    @regression @tc4             #(N) -> Negative Scenario
    Scenario: (N) User creates a new pet with an image
      Given User creates a new pet with POST request using corrupted jpeg
      Then User validates new pet is not created with status code 400
      And User validates new pet is not created with GET request
