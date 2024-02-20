# Chess
This is our attempt at coding chess in Java. We'll see how it go :D Will update hopefully 

# Rules 
## General Rules
The player with the white pieces always moves first. Therefore, players generally decide who will get to be white by chance or luck such as flipping a coin or having one player guess the color of the hidden pawn in the other player's hand. White then makes a move, followed by black, then white again, then black, and so on until the end of the game.
## How to move the pieces
 ### King
 The king is the most important piece, but is one of the weakest. The king can only move one square in any direction - up, down, to the sides, and diagonally.
 The king may never move himself into check (where he could be captured). When the king is attacked by another piece this is called "check".
 ### Queen
 The queen is the most powerful piece. She can move in any one straight direction - forward, backward, sideways, or diagonally - as far as possible as long as she does not move through any of her own pieces.
 And, like with all pieces, if the queen captures an opponent's piece her move is over. 
 ### Rook 
 The rook may move as far as it wants, but only forward, backward, and to the sides.
 ### Bishop
 The bishop may move as far as it wants, but only diagonally. Each bishop starts on one color (light or dark) and must always stay on that color.
 ### Knight
 Knights move in a very different way from the other pieces – going two squares in one direction, and then one more move at a 90-degree angle, just like the shape of an “L”.
 Knights are also the only pieces that can move over other pieces.
 ### Pawn
 Pawns are unusual because they move and capture in different ways: they move forward but capture diagonally. Pawns can only move forward one square at a time, except for their very first move where they can move forward two squares.
 Pawns can only capture one square diagonally in front of them. They can never move or capture backward. If there is another piece directly in front of a pawn he cannot move past or capture that piece.
## Special Rules
 ### promoting a pawn
 Pawns have another special ability and that is that if a pawn reaches the other side of the board it can become any other chess piece (called promotion) excluding a king (or pawn, for that matter).
 A pawn may be promoted to a knight, bishop, rook, or queen. A common misconception is that pawns may only be exchanged for a piece that has been captured. That is NOT true. A pawn is usually promoted to a queen. Only pawns may be promoted.
 ### En Passant
 If a pawn moves out two squares on its first move, and by doing so lands to the side of an opponent's pawn (effectively jumping past the other pawn's ability to capture it), that other pawn has the option of capturing the first pawn as it passes by.
 This special move must be done immediately after the first pawn has moved past, otherwise the option to capture it is no longer available. 
 ### Castle  
 This move allows you to do two important things all in one move: get your king to safety (hopefully), and get your rook out of the corner and into the game. On a player's turn he may move his king two squares over to one side and then move the rook from that side's corner to right next to the king on the opposite side.
 However, in order to castle, the following conditions must be met:
 * it must be that king's very first move
 * it must be that rook's very first move
 * there cannot be any pieces between the king and rook to move
 * the king may not be in check or pass through check
 Notice that when you castle one direction the king is closer to the side of the board. That is called castling "**kingside**". Castling to the other side, through where the queen sat, is called castling "**queenside**". Regardless of which side, the king always moves only two squares when castling.
## how to win
The purpose of the game is to checkmate the opponent's king. This happens when the king is put into check and cannot get out of check.If a king cannot escape checkmate then the game is over. Customarily the king is not captured or removed from the board, the game is simply declared over.
## Ways of draws
* The players may simply agree to a draw and stop playing
* There are not enough pieces on the board to force a checkmate (example: a king and a bishop vs. a king)
* A player declares a draw if the same exact position is repeated three times (though not necessarily three times in a row)
* Fifty consecutive moves have been played where neither player has moved a pawn or captured a piece
* **Stalemate**: The position reaches a stalemate where it is one player's turn to move, but his king is NOT in check and yet he does not have another legal move.

For more info visit: https://www.chess.com/learn-how-to-play-chess#chess-pieces-move 
(yes we stole these stuff from chess.com)
