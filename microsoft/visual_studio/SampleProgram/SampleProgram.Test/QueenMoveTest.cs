using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;
using ChessLib;

namespace SampleProgram.Test
{
    [TestFixture]
    public class QueenMoveTest
    {
        [Test]
        public void TestQueenMoveWithinBoundary()
        {
            var pos = new Position(1, 1);
            var moves = getQueenValidMoves(pos);

            Assert.IsNotNull(moves);

            foreach (var move in moves)
            {
                Assert.IsTrue(move.X <= 8 && move.X >= 1);
                Assert.IsTrue(move.Y <= 8 && move.Y >= 1);
            }
        }

        [Test]
        public void TestQueenMoveDiagonalOrVerticalOrHorizontal()
        {
            var pos = new Position(4, 4);
            var moves = getQueenValidMoves(pos);

            Assert.IsNotNull(moves);
            Assert.AreEqual(27, moves.Length);

            foreach (var move in moves)
            {
                Assert.IsTrue(IsMoveDiagonal(pos, move) || IsMoveVertical(pos, move) || IsMoveHorizontal(pos, move));
            }
        }

        [Test]
        public void TestQueenNotInOriginalPosition()
        {
            var pos = new Position(4, 4);
            var moves = getQueenValidMoves(pos);

            Assert.IsNotNull(moves);

            foreach (var move in moves)
            {
                Assert.AreNotEqual(pos, move);
            }
        }

        [Test]
        public void TestQueenMoveFromCorner()
        {
            var pos = new Position(8, 1);
            var moves = getQueenValidMoves(pos);

            Assert.IsNotNull(moves);
            Assert.AreEqual(21, moves.Length);

            foreach (var move in moves)
            {
                Assert.IsTrue(IsMoveDiagonal(pos, move) || IsMoveVertical(pos, move) || IsMoveHorizontal(pos, move));
            }
        }

        private Position[] getQueenValidMoves(Position pos)
        {
            var queen = new QueenMove();

            var moves = queen.ValidMovesFor(pos).ToArray();
            return moves;
        }

        private bool IsMoveHorizontal(Position pos, Position move)
        {
            return move.X == pos.X;
        }

        private bool IsMoveVertical(Position pos, Position move)
        {
            return move.Y == pos.Y;
        }

        private bool IsMoveDiagonal(Position pos, Position move)
        {
            return Math.Abs(move.X - pos.X) == Math.Abs(move.Y - pos.Y);
        }
    }
}
