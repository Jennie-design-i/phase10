# phase10
GUI implementation of Phase10 card game using APACHE NetBeans

Description:  Phase10 is a card game with 2-6 players (in this GUI, there are only 2-4 players). Similar to Uno, there is a deck of 108 cards in total; there are numbers 1 - 12, in four colors: Red, Blue, Green, Yellow, and 2 cards of each number for each color. There are also eight wild cards (which can represent any number or any color) and four skip cards (which when played will skip the turn of the next player). 

Goal: To be the first player to complete all 10 Phases. In the case of a tie, the player with the lowest score is the winner.

The phases are:

1) 2 sets of 3
2) 1 set of 3 + 1 run of 4
3) 1 set of 4 + 1 run of 4
4) 1 run of 7
5) 1 run of 8
6) 1 run of 9
7) 2 sets of 4
8) 7 cards of one color
9) 1 set of 5 + 1 set of 2
10) 1 set of 5 + 1 set of 3

Definition: 
A phase is a combination of cards.

A set is a group of the same numbers(three of a kind, four of a kind, five of a kind, etc.)

A run is a sequential group of numbers (2 3 4 5, 7 8 9 10 11 12, etc.)

Wilds can be played as any number in a set or run also any color.

The way to count Score:

num 1-9 = 5 points each
num 10-12 = 10 points each
skip card = 15 points
wild card = 25 points

(only counting the card on your hand)


Before playing: Choose a player to be the dealer (the first name typed into the game). The dealer shuffles the deck and deals 10 cards, faced down, one at a time, to each player. Those cards can only be visible to the player themselves! After that turn the top card of the draw pile over and place it next to the draw pile, to become the discard pile.

Playing: The player to the left of the dealer goes first (goes in order of how the name was put into GUI). Play continues in a clockwise direction. On your turn, you may draw a card either from the deck or the top cards of the discard pile and add it to your hand. Once you had drawn a card, if they have the required cards of their phase, they may choose to "lay down" their phase and face up those cards. The next step is to clear all cards on your hand to lower the score; the way to do so is to "hit" or add on to other players' phases (you MUST complete your phase before "hitting"). You MUST end your turn by discarding only one card on your hands to the top of the discard pile. You can only do ONE PHASE PER ROUND.

IMPORTANT: There will be countless rounds because we only care about who completed the last phase first. A round ends when a player clears all cards on hand.

HOW TO PLAY IN GUI:
 
Start the GUI by running the Main.java. 

The way the GUI works:
'->' means "calls
Main.java -> GuiManager.java -> Menu.java -> AddPlayerNames.java -> GameStage.java

Menu window:
<img width="601" alt="Menu" src="https://user-images.githubusercontent.com/83247047/129962153-34215ce9-e3fe-4969-bd96-3461adee642d.png">

AddPlayerNames window after clicking "PLAY":
<img width="1071" alt="AddPlayerName" src="https://user-images.githubusercontent.com/83247047/129962573-d129fc4d-a826-456a-9d0c-e4f05a873ba0.png">

GameStage window after clicking "DONE":
<img width="1440" alt="GameStage" src="https://user-images.githubusercontent.com/83247047/129962699-b15a3b3e-687f-4c49-8cba-2227c71fed03.png">




Citation:
https://github.com/eforbes/Phase10/tree/master/Phase10/src/phase10
http://www.tactic.net/site/rules/FIN/03140.pdf
https://www.instructables.com/How-to-Play-Phase-10/
