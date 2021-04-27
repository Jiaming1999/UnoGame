# sp21-cs242-assignment1

## Uno Card Game


A java-based Uno card game.

```java
game.start(); //start game
game.draw(); //draw card
game.playCard(); //play card
```
Table of Contents
-----------------
  * [Card](#card)
  * [Deck](#deck)
  * [Players](#players)
  * [Player](#player)
  * [PlayerAI](#playerAI)
  * [Game](#game)
  * [Test](#test)

  ---

Card
-----
Card Class is the class for all Uno Card type consisting of a Color and a Number
```java
enum Color{
    red,yello,blue,green,wild,ANY
}
enum Number{
    zero,one,two,three,four,five,six,seven,eight,night,add2,reverse,skip,changeColor,add4,ANY
}
```
Representing all cards in a Uno game.<br />

To construct
```java
Card card = new Card(color,number);
```
To get Card information
```java
card.getColor();
card.getNumber();
card.getCard(); //return "color:number"
```


----
Deck
------
Deck Class is ithe class for storing cards and relevant actions for the drawpile of the game.

To set the Deck:
```java
Deck deck = new Deck()
deck.setup();
deck.shuffle();
```

Relevant actions
```java
deck.drawCard() //draw one card from top
deck.drawCards(2) // draw two card from top
deck.rebuildDeck(discardPile) // replace deck with discard pile
deck.printDeck() //Test purpose, print the deck
```

To check the Deck:
```java
deck.isEmpty(); //check if deck runs out of cards
deck.getInDeck(); //check how many cards remains
```

---

Game
----
Game Class storing rules interactions and players' status.

To begin a game:
```java
Game game = new Game(players,0,true);//players is an array of players' id; 0 is first player index; true meaning clockwise order
game.start(); //setup everything
game.isGameOver(); //check if anyone wins
```

In game interaction:
```java
game.draw(); //draw a card from the top of deck
game.play(player,index); //player plays ones indexth card.
game.takeEffect(player,card); //let special cards take effect
```

Currently there are enough functions implemented to make sure the game loop can be run for a full uno game match.

---

Players
----
The Class stored players information

Including direction, Ids, and handCard

```java
players.getPlayerHandCard();
players.isClockWised();
players.getCurrentPlayerId();
```
---

Player
----
The Class stored single player information

Including name, index, and handCard

```java
player.getPlayerCard();
player.getPlayerId();
```
---
PlayerAI
----
The Class a playe AI with following logic

	 * advanced AI play as following order:
	 * if several valid card:
	 * First play wild add4 if valid
	 * then wild change color
	 * Wild played change to maxcolor
	 * valid add2,skip,reverse
	 * If no then play a valid number card with color player has the most of 
	 * None of above applied then play a random valid color card.



Test
----
The test is focusing are mainly 6 part, each part refers to its class:
 1. CardTest
 2. DeckTest
 3. GameTest
 4. PlayersTest
 5. GUI Manual Test
 6. AI test
<br />

The test includes:<br />
-Constructor valid testing for card, deck, game.<br />
-Test for all special ability cards run normal such as skip, reverse, add2, add4, wildchange color.<br />
-Test for draw card from empty deck.<br />
-Test for consecutively stack drawpile and then draw them.<br />
-Test for if the card played is valid.<br />
-Test for valid game over condition.<br />

----
License
-------

Copyright &copy; 2021, Jiaming Zhang
