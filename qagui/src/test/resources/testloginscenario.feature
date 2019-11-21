Feature: User panel

  @wordpress @login @userprofile
  Scenario: User login

    Given User start on main page
    When User logs to the user panel
    Then Use can modify user profile
