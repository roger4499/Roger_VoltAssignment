@SauceDemo @test1
Feature: Add To Cart

 	Scenario: Login to Swag labs
 			Given I open sauce demo on web
			When I am on the login page
			When I login with "standard_user"
			Then I am on the "inventory" Page
			
	Scenario: Add an item to cart, verify cart badge count and the item is added to cart
			Given I am on the "inventory" Page
			When I add "Sauce Labs Bolt T-Shirt" to the cart
			Then The cart icon badge increments by 1
			When I click on the cart icon
			Then I am on the "cart" Page
			And "Sauce Labs Bolt T-Shirt" item appears in cart with the same price and badge icon is unchanged
			
	Scenario: Click continue shopping on cart page and logout
			Given I am on the "cart" Page
			When I click on continue shopping
			Then I am on the "inventory" Page
			When I click on hamburger icon and logout
			Then I am on the login page
			
			