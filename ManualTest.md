# Manual Test Plan
## 1.Prerequisite and Tools
A Java running environment is requested to run the application.

JDK 14.0.2 and later version is secure for running.
The Application is built with Eclipse SDE Version 2020-12

To install java at https://www.java.com/

## 2. Tests Plan

### TC1. To start the game:
The apperance of Insert Player Panel GUI should be:

With 3 players and none AI players

<img src="./Pic/start.png"
     alt="Layout"/>
<br />

With 3 players and 2 AI players

<img src="./Pic/3Player2AI.png"
     alt="Layout"/>

With invalid player insert window will pop out if too big or too small number or not a number

<img src="./Pic/invalidInput.png"
     alt="Layout"/>

### TC2. Start the game with 3 players and 2 AI players:

Players queue: "Player 1 - 5" with default clockwised order

The apperance of GUI with 5 players and started game should be:

<img src="./Pic/play1.png"
     alt="Layout"/>

When you play a valid card and switch to next Player

<img src="./Pic/afterPlay.png"
     alt="Layout"/>

And then close the window to continue:

<img src="./Pic/nextPlayer.png"
     alt="Layout"/>


### TC3. A Skip Card is played:

The current player should be:

<img src="./Pic/skip.png"
     alt="Layout"/>

and after skip played, player1 is skipped

<img src="./Pic/afterSkip.png"
     alt="Layout"/>

### TC4. A reverse Card is played:

Before reversed

<img src="./Pic/beforeRev.png"
     alt="Layout"/>

After reversed

<img src="./Pic/reved.png"
     alt="Layout"/>


### TC5. A Wild Card is played:
Poping out window should appear for player to choose the color or number as wild card is played:

<img src="./Pic/wildPanel.png"
     alt="Layout"/>

Then choose to continue:

<img src="./Pic/afterWild.png"
     alt="Layout"/>

### TC6. An invalid Card is played:
ErrorMessage window should appear with message of "invalid card is played, receiving penalty":

<img src="./Pic/noValid.png"
     alt="Layout"/>

Finishing penalty and draw one Card

### TC7 An add4 Card played:

<img src="./Pic/ADD4.png"
     alt="Layout"/>

### TC8 Then an add2 Card played:

<img src="./Pic/add2.png"
     alt="Layout"/>

### TC9. GameOver:

Game Over Message should pop out.

Player 1 is the winner

<img src="./Pic/winning.png"
     alt="Layout"/>

----------