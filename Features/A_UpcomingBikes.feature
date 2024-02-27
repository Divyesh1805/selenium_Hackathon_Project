#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: UpComing Bikes  
  Scenario: Upcoming Bikes with thier detail for Honda
    Given Be on the zigwheel site
    
    When Hover to new bikes and select upcoming bikes
    And Select the manufacture as Honda from DropDown
    
    Then Get the deatils for each bike and store in excel
    
 
