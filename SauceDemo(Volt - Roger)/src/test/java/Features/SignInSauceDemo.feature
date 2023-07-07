@SauceDemo @test
Feature: Sign In

  Scenario: Login to Swag Labs
    
	Given I open sauce demo on web
	Then I am on the login page
	Then Get all usernames and password
	When I enter a random username and the given password it shows me an error
	When I enter a given username and random password it shows me an error
  Then Login with each given username and given password to verify all users are able to login
