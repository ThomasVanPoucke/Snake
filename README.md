# Snake
In this game you are a snake that needs to eat to get bigger. You can decorate the snake and his food with images.

Prerequisites:
Java 1.8

Technical details:

Created with JavaFX.
The game has two scenes. 
One scene for the selection of an image for the snake and the food and one scene for the actual game.
Each scene is designed with FXML and has one controller. There is also one extra controller that acts as the central controller for the game.

The GardenOfEden class represents the play field.
A Snake object is responsible for moving the snake, eating, etc. A snake exists of several SnakeDot object each represented by a square image in the game.
When the snake eats an extra SnakeDot is added to the Snake.
