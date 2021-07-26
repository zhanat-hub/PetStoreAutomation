@PS-20 @api
  Feature: Creating a pet
    @regression @smoke @tc3
    Scenario: User creates a new pet with an image
      Given User creates a new pet with POST request
      Then User validates new pet is created with status code 200
      And User validates new pet is created with GET request

    @regression @smoke @tc4
    Scenario: Searching for created pet
      Given User creates a new pet with POST request
      Then User validates new pet is created with GET request
      And User validates new pet is created with status code 200
      When User searches for similar pets by status "pending" with GET request
      Then User validates status code of GET request is 200
      And User validates searched pets share the same status "pending"