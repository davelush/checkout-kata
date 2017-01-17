#Checkout Kata

##Brief

Implement the code for a supermarket checkout that calculates the total price of a number of
items. In a normal supermarket, things are identified using Stock Keeping Units, or SKUs. In our
store, we’ll use individual letters of the alphabet (A, B, C, and so on as the SKUs). Our goods are
priced individually. In addition, some items are multi­priced: buy n of them, and they’ll cost you y.
For example, item ‘A’ might cost 50 pence individually, but this week we have a special offer: buy
three ‘A’s and they’ll cost you £1.30. In fact this week’s prices are:

| Item | Unit Price | Special Price |
|------|------------|---------------|
|    A | 50         | 3 for 130     |
|    B | 30         | 2 for 45      |
|    C | 20         |               |
|    D | 15         |               |

Our checkout accepts items in any order, so that if we scan a B, an A, and another B, we’ll
recognise the two B’s and price them at 45 (for a total price so far of 95).
Extra points: Because the pricing changes frequently, we need to be able to pass in a set of
pricing rules each time we start handling a checkout transaction.

##How to Run

Build can be run with the command below and is [![Build Status](https://travis-ci.org/davelush/checkout-kata.svg?branch=master)](https://travis-ci.org/davelush/checkout-kata) on Travis CI
```bash
mvn clean install
```

In order to run mutation coverage with [Pitest](http://pitest.org/) use the following command. We're aiming for 100% of mutations to be killed
```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

##Improvements

Here are a list of improvements that could be made to the implementation
 - Redesign the Checkout class to lookup an Item, add it to a List and calculate a running total based on PricingRules. This will give us the basis of an actual receipt. More future proof design as the requirements expand
 - Extract a Checkout.offersCheck() method that can be re-used when you scan an item, remove an item, change the pricing rules etc
 - In the Checkout constructor the check for a key matching Item.getSku() is clunky. One solution would be to encapsulate in a PricingRules class or look at using something else instead of a Map to hold the pricing rules.
 - Should have had a good commit history to tell the story of TDD & refactor but I made a mistake. Probably one for conversation
  