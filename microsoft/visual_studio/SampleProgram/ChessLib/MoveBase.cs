using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ChessLib
{
    public abstract class MoveBase
    {
        protected bool IsMoving(Position pos, Position newPos)
        {
            return (pos.X != newPos.X) || (pos.Y != newPos.Y);
        }

        protected bool IsInBoundaries(Position position)
        {
            return (position.X <= 8) && (position.X >= 1) && (position.Y <= 8) && (position.Y >= 1);
        }
    }
}
