using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ChessLib
{
    public class BishopMove
    {
        public IEnumerable<Position> ValidMovesFor(Position pos)
        {
            ISet<Position> positions = new HashSet<Position>();
            positions.Add(new Position(1, 2));
            return positions;
        }
    }
}
