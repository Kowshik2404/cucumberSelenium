Feature: Login Into Application

  #@Smoke1 @Smoke
  #Scenario: To verify that user is able to login into Application with valid creds
  #When User enters username and password and clicks on Login
  #Then User should be able to see Dashboard
  @Smoke1 @Smoke
  Scenario: To verify user can create store
    Given User enters username and password and clicks on Login
    When User navigates Stores -> store creation
    Then Enter store details and clicks on submit
    Then Create store should be listed in store listing

  #@Smoke1 @Smoke
  #Scenario: To verify user can edit store
   # Given User enters username and password and clicks on Login
    #When User navigates Stores -> store creation
    #Then Enter store details and clicks on submit
    #Then Create store should be listed in store listing
    #Then Edit store

    #@Smoke1 @Smoke
  #Scenario: To verify user can delete store
   # Given User enters username and password and clicks on Login
    #When User navigates Stores -> store creation
    #Then Enter store details and clicks on submit
    #Then Create store should be listed in store listing
    #Then Delete store
    
    #@Smoke1 @Smoke
  #Scenario: To verify user can Inactivate store
   # Given User enters username and password and clicks on Login
    #When User navigates Stores -> store creation
    #Then Enter store details and clicks on submit
    #Then Create store should be listed in store listing
    #Then Inactivate store
    
    #@Smoke1 @Smoke
  #Scenario: To verify user can Activate store
   # Given User enters username and password and clicks on Login
    #When User navigates Stores -> store creation
    #Then Enter store details and clicks on submit
    #Then Create store should be listed in store listing
    #Then Inactivate store
    #Then Activate store
    
   @Smoke1 @Smoke
  Scenario: To verify user can open store catalogue
    Given User enters username and password and clicks on Login
    When User navigates Stores -> store creation
    Then Open store catalogue