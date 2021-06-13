#Author: Harjinder Singh Banga
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
@tag1 
Feature: Verify the wishlist functionality

@westwingnow
Scenario: PQ-TC-2144 - Verify the wishlist functionality
	Given I am on the WestwingNow home page
     When I search for "mobel" 
     Then I should see product listing page with a list of products 
     When I click on wishlist icon of the first found product 
     Then I should see the login or registration overlay 
     When I switch to login form of the overlay 
      And I log in with email "hsbanga@yahoo.com" and password "Harry@2426" credentials 
     Then the product should be added to the wishlist
      And I go to the wishlist page
      And I delete the product from my wishlist