﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ChessLib
{
    public class BishopMove : MoveBase
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
            }
        }
    }
}
