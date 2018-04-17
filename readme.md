# Kata Texas Hold'em

This kata is based in [kata texas coding dojo](http://codingdojo.org/kata/TexasHoldEm/)

## The problem

## Problem description

You work for a cable network; specifically, you are the resident hacker for a Texas Hold’Em Championship show.

The show’s producer has come to you for a favor. It seems the play-by-play announcers just can’t think very fast. 
All beauty, no brains. The announcers could certainly flap their jaws well enough, if they just knew what hands the players were holding and which hand won the round. 
Since this is live TV, they need those answers quick. Time to step up to the plate. Bob, the producer, explains what you need to do.

**BOB**: Each player’s cards for the round will be on a separate line of the input. 
Each card is a pair of characters, the first character represents the face, the second is the suit. 
Cards are separated by exactly one space. Here’s a sample hand.

```bash
  Kc 9s Ks Kd 9d 3c 6d
  9c Ah Ks Kd 9d 3c 6d
  Ac Qc Ks Kd 9d 3c
  9h 5s
  4d 2d Ks Kd 9d 3c 6d
  7s Ts Ks Kd 9d
```

**YOU**: Okay, I was going ask what character to use for 10, but I guess ’T’ is it. 
And ‘c’, ’d’, ‘h’ and ’s’ for the suits, makes sense. Why aren’t seven cards listed for every player?

**BOB**: Well, if a player folds, only his hole cards and the community cards he’s seen so far are shown.

**YOU**: Right. And why did the fifth player play with a 4 and 2? They’re suited, but geez, talk about risk…

**BOB**: Stay on topic. Now, the end result of your code should generate output that looks like this:

```bash
  Kc 9s Ks Kd 9d 3c 6d Full House (winner)
  9c Ah Ks Kd 9d 3c 6d Two Pair
  Ac Qc Ks Kd 9d 3c 
  9h 5s 
  4d 2d Ks Kd 9d 3c 6d Flush
  7s Ts Ks Kd 9d
``` 
**YOU**: Okay, so I repeat the cards, list the rank or nothing if the player folded, and the word “winner” in parenthesis next to the winning hand. Do you want the cards rearranged at all?

**BOB**: Hmmm… we can get by without it, but if you have the time, do it. Don’t bother for folded hands, but for ranked hands, move the cards used to the front of the line, sorted by face. Kickers follow that, and the two unused cards go at the end, just before the rank is listed.

**YOU**: Sounds good. One other thing, I need to brush up on the hand ranks. You have any good references for Texas Hold’Em?

**BOB**: Yeah, do an internet search on Poker Hand Rankings. And if you need it, the Rules of Texas Hold’Em. While ranking, don’t forget the kicker, the next highest card in their hand if player’s are tied. And of course, if – even after the kicker – player’s are still tied, put “(winner)” on each appropriate line of output.

**YOU**: Ok. I still don’t understand one thing…

**BOB**: What’s that?

**YOU**: Why he stayed in with only the 4 and 2 of diamonds? That’s just…

**BOB**: Hey! Show’s on in ten minutes! Get to work!

### Texas Hold'em poker rules


Texas hold'em is a variation of the card game of poker. 

It is played with an English pack of 52 cards (jokers are not used). Tables join between 6 and 10 players.

In Texas Hold'em players receive two cards. Another five community cards are put face up on the table; these cards are used by all players to get their best 5 card combination.

This is the ranking of the possible poker hands:

<p align="center">
  <img src="texas_holdem_rankings.jpg">
</p>


### Out of scope / to improve

- General semantic validation:
  - Check card repetitions
  - Check invalid showdowns (only one line, all fold hands, ...) 
- Rearranging the cards extension
