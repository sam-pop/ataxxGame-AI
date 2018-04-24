# ataxxGame_MachineLearning
<a href="https://en.wikipedia.org/wiki/Ataxx" target="_blank">Ataxx</a> game using the Minimax algorithm.


# the game:
<b>Initial Board Setup -</b> The board is a seven-by-seven square grid. Each player – black and white – starts
the game with 2 pieces. The black pieces occupy the top-left and bottom-right corners and the white ones
occupy the top-right and bottom-left corners.

1:0:0:0:0:0:2:<br>
0:0:0:0:0:0:0:<br>
0:0:0:0:0:0:0:<br>
0:0:0:0:0:0:0:<br>
0:0:0:0:0:0:0:<br>
0:0:0:0:0:0:0:<br>
2:0:0:0:0:0:1:

<b>The rules of the game -</b> Players alternate moves, with black is the first player. On each turn, a player
moves a single piece of his color in either one of three possible ways:<br>
• Move the selected piece one square to a neighboring blank square–horizontally, vertically or diagonally.
In this case, the piece replicates. (The original piece stays, and another one is created in the target
square.)<br>
• The piece can jump horizonally, vertically or diagonally two squares. In this case, it vacates its original
location. When the piece moves to its destination, it converts all neighboring pieces to its own color
(horizontally, vertically as well as diagonally).
• It is also possible to jump three squares vertically and horizontally (but not diagonally). The result
is the same as for the 2-step jump (the piece vacates its original location, and the neighboring pieces
around the destination are coverted to its color).

Players <em>must move</em> unless no legal move is possible, in which case they must pass.

<b>Goal -</b> The game ends when one of the following situations happen: 1) the board is filled; 2) All the pieces
on the board are of a single color–either black or white, or 3) the game reaches its 100th turn (a turn is
defined as a pair of moves from both players). The winner ends the game with more pieces on the board.

In this program we will play the black player. 

Given the current board, it should decide on its move. It should then print out the selected move, and the resulting board.
Suppose that at the start of the game the program decided to move the top-left black piece one step to the
right. The output should look like this:

1:1:0:0:0:0:2:<br>
0:0:0:0:0:0:0:<br>
0:0:0:0:0:0:0:<br>
0:0:0:0:0:0:0:<br>
0:0:0:0:0:0:0:<br>
0:0:0:0:0:0:0:<br>
2:0:0:0:0:0:1:

Empty spaces are represented by ‘0’s. Black pieces are represented by ‘1’s, and white pieces are represented
by ‘2’s. The move decision should also be printed using the (x-y) coordinate system shown in the board
examples. For example, the move demonstrated above from 0,0 to 1,0 would be printed out as a single line
with 8 characters:
0:0:1:0:

It is not allowed to skip moves.

<b>The program uses has a tree of a limited length, and a utility function to evaluate how good a board is at the leaf nodes.<br>
The best move is decided using the Minimax algorithm.</b>
