Feature: Login Into Application

  @Smoke1 @Smoke
  Scenario: To verify that user is able to login into Application with valid creds
    Given User logs in with username and password
    When User clicks on Stores -> store creation
    Then Store list should be displayed
    #@Smoke2 @Smoke
  #Scenario: To verify that user should not able to login into Application with invalid creds
   # Given I open the login page URL
    #When I enter the username in username field 
    #And I enter the password in password name field
    #And I click Login button
    #Then I should be able see error message
