using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;
using ChessLib;

namespace SampleProgram.Test
{
    [TestFixture]
    public class KnightMoveTest
    {
        [Test]
        public void TestKnightMoveFromInsideBoard()
        {
            var pos = new Position(3, 3);
            var knight = new KnightMove();

            var moves = knight.ValidMovesFor(pos).ToArray();

            Assert.IsNotNull(moves);
            Assert.AreEqual(8, moves.Length);

            foreach (var move in moves)
            {
                switch (Math.Abs(move.X - pos.X))
                {
                    case 1:
                        Assert.AreEqual(2, Math.Abs(move.Y - pos.Y));
                        break;
                    case 2:
                        Assert.AreEqual(1, Math.Abs(move.Y - pos.Y));
                        break;
                    default:
                        Assert.Fail();
                        break;
                }
            }
        }

        [Test]
        public void TestKnightMoveFromCorner()
        {
            var pos = new Position(1, 1);
            var knight = new KnightMove();

            var moves = new HashSet<Position>(knight.ValidMovesFor(pos));

            Assert.IsNotNull(moves);
            Assert.AreEqual(2, moves.Count);

            var possibles = new[] { new Position(2, 3), new Position(3, 2) };

            foreach (var possible in possibles)
            {
                Assert.IsTrue(moves.Contains(possible));
            }
        }

    }
}
