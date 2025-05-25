@tag
Feature: Purchase the order from Ecommerce Website
         I want to use this template for my feature file

Background:
Given I landed on Ecommerce page
 
@Regression
Scenario Outline: 
Positive test of Submitting Order
Given Log in with Username <Username> and Password <Password>
When  I add product <productName> from Cart
And   Checkout <productName> and submit the order
Then  "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page

Examples: 
| Username               | Password  | productName  |
| yusufjamil75@gmail.com | Yousuf1@  | ZARA COAT 3  |
