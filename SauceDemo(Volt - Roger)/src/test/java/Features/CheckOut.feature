@SauceDemo @test3
Feature: Checkout to complete order

 	Scenario: Login to Swag labs
 			Given I open sauce demo on web
			When I am on the login page
			When I login with "standard_user"
			Then I am on the "inventory" Page
			
	Scenario: Add an item to cart, verify checkout option is displayed in cart and click cancel
			Given I am on the "inventory" Page
			When I add "Sauce Labs Bolt T-Shirt" to the cart
			When I click on the cart icon
			Then I am on the "cart" Page
			And Checkout button is displayed in cart and is clicked
			Then I am on the "checkout-step-one" Page
			When I click cancel
			Then I am on the "cart" Page
			
	Scenario: Click Checkout, do not enter any details and verify error
			Given Checkout button is displayed in cart and is clicked
			Then I am on the "checkout-step-one" Page
			When I do not enter any details and click continue then verify error
			
	Scenario: Enter details and checkout and verify details
			When I enter firstName as "Roger" lastName as "Alfred" and zip as 457001 and click continue
			Then I am on the "checkout-step-two" Page
			And "Sauce Labs Bolt T-Shirt" item exists with payment information
			When I click finish
			Then I am on the "checkout-complete" Page
			And verify checkout complete information and click back home
			Then I am on the "inventory" Page
			Then I click on hamburger icon and logout
			