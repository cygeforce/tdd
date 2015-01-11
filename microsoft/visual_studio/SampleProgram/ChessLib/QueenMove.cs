using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ChessLib
{
    public class QueenMove : MoveBase
    {
        public IEnumerable<Position> ValidMovesFor(Position pos)
        {
            for (var i = -7; i < 8; i++)
            {
                Position diagonalPosition1 = new Position(pos.X + i, pos.Y + i);
                if (IsInBoundaries(diagonalPosition1) && IsMoving(pos, diagonalPosition1))
                    yield return diagonalPosition1;

                Position diagonalPosition2 = new Position(pos.X + i, pos.Y - i);
                if (IsInBoundaries(diagonalPosition2) && IsMoving(pos, diagonalPosition2))
                    yield return diagonalPosition2;

                Position verticalPosition = new Position(pos.X + i, pos.Y);
                if (IsInBoundaries(verticalPosition) && IsMoving(pos, verticalPosition))
                    yield return verticalPosition;

                Position horizontalPosition = new Position(pos.X, pos.Y + i);
                if (IsInBoundaries(horizontalPosition) && IsMoving(pos, horizontalPosition))
                   yield return horizontalPosition;
            }
        }
    }
}
