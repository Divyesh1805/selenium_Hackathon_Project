Feature: Google Sign In
 
  Scenario: Sign in with google id
    Given Present on ZigWheel page 
    When Click on Login button and select Google
    And Try logging with invalid email
    Then Verify and capture the error message
 

