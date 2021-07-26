@PS-10 @ui
  Feature: Expand and collapse
    @regression @smoke @tc1
      Scenario: Validating user can expand the rest method sections
      Given User navigates to pet store swagger page
      When User clicks on the post pet image section to expand
      Then User validates the post pet image section is expanded

    @regression @smoke @tc2
      Scenario: Validating user can collapse the rest method sections
      Given User navigates to pet store swagger page
      And User clicks on the post pet image section to expand
      When User clicks on the post pet image section to collapse
      Then User validates the post pet image section is collapsed