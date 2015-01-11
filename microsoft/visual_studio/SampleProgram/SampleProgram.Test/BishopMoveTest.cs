using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;
using ChessLib;

namespace SampleProgram.Test
{
    [TestFixture]
    public class BishopMoveTest
    {
        [Test]
        public void TestBishopMoveWithinBoundary()
        {
            var pos = new Position(1, 1);
            var moves = getBishopValidMoves(pos);

            Assert.IsNotNull(moves);

            foreach (var move in moves)
            {
                Assert.IsTrue(move.X <= 8 && move.X >= 1);
                Assert.IsTrue(move.Y <= 8 && move.Y >= 1);
            }
        }

        [Test]
        public void TestBishopMoveIsDiagonal()
        {
            var pos = new Position(4, 4);
            var moves = getBishopValidMoves(pos);

            Assert.IsNotNull(moves);
            Assert.AreEqual(13, moves.Length);

            foreach (var move in moves)
            {
                Assert.AreEqual(Math.Abs(move.X - pos.X), Math.Abs(move.Y - pos.Y));
            }
        }

        [Test]
        public void TestBishopNotInOriginalPosition()
        {
            var pos = new Position(4, 4);
            var moves = getBishopValidMoves(pos);

            Assert.IsNotNull(moves);

            foreach (var move in moves)
            {
                Assert.AreNotEqual(pos, move);
            }
        }

        [Test]
        public void TestBishopMoveFromCorner()
        {
            var pos = new Position(1, 8);
            var moves = getBishopValidMoves(pos);

            Assert.IsNotNull(moves);
            Assert.AreEqual(7, moves.Length);

            foreach (var move in moves)
            {
                Assert.AreEqual(Math.Abs(move.X - pos.X), Math.Abs(move.Y - pos.Y));
            }
        }

        private Position[] getBishopValidMoves(Position pos)
        {
            var bishop = new BishopMove();

            var moves = bishop.ValidMovesFor(pos).ToArray();
            return moves;
        }
    }
}
