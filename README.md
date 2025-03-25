# Description

Laser chicken is a game that is man vs computer. To begin the game you are asked to choose a chicken character each with its corresponding ability.
You can utilize the game grid to see where the character is currently and other information such as position, health, and latest action, for both yourself
and the computer. Have fun!

# Goals

The goal of Laser Chicken was to work with Object-Oriented Programming at an introductory level.

# Functionality

The two types of chickens, Lucky and Blast, inherit behavior from their parent classes defensive_chicken and offensive_chicken, and these classes inherit from the base class 'chicken_character'. Any offensive chicken class will have doubled damage on the third attack turn. Any defensive chicken will have a 25% chance of blocking 50% of the attacker's attack. Blast is an offensive_chicken type, with the ability to double its movement. Lucky is a defensive_chicken type, with the ability to regenerate health.

# Features

The computer player's moves are decided in the 'Game' class. It is a simple initialization, with a 'calc_random' function that chooses both the chicken type and the move of each turn. The 'Game' class also provides the ability to change the lucky and blasts chicken likelihood and strength of abilities. The user also can change the grid size upon initialization of the game in the 'Main' class.

# Drawbacks

The computer player in this program is not intelligent. Its decisions are entirely random, not based on any progress in the game.



