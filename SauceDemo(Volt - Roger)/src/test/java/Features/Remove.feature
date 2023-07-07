@SauceDemo @test2
Feature: Remove button to remove item from cart

 	Scenario: Login to Swag labs
 			Given I open sauce demo on web
			When I am on the login page
			When I login with "standard_user"
			Then I am on the "inventory" Page
			
	Scenario: Add an item to the cart and verify remove button appears on inventory page for the given item
			Given I am on the "inventory" Page
			When I add "Sauce Labs Bolt T-Shirt" to the cart, Remove button appears for given item
			When I add "Sauce Labs Bike Light" to the cart, Remove button appears for given item
			When I click Remove for "Sauce Labs Bike Light" then add to cart button appears
			And The icon badge decrements by 1
			And I click on the cart icon
			Then "Sauce Labs Bike Light" is not found in cart
			
	Scenario: Removing from cart removes option to remove item from inventory page as well
			Given I am on the "cart" Page
			When I click Remove for "Sauce Labs Bolt T-Shirt" on cart page
			Then I click on continue shopping
			Then I am on the "inventory" Page
			And Remove button does not appear for "Sauce Labs Bolt T-Shirt" on inventory page	
			Then I click on hamburger icon and logout
			