using System;
using System.Collections.Generic;
using System.Linq;
using ChessLib;

namespace SampleProgram
{

    public class ComplexGame
    {
        private readonly Random _rnd = new Random();

        private Position knightStartPosition;
        private Position bishopStartPosition;
        private Position queenStartPosition;

        public void Play(int moves)
        {
            var knight = new KnightMove();
            var knightPos = knightStartPosition;

            var bishop = new BishopMove();
            var bishopPos = bishopStartPosition;

            var queen = new QueenMove();
            var queenPos = queenStartPosition;
            Console.WriteLine("0: Knight position is {0}, Bishop position is {1}, Queen position is {2}", knightPos, bishopPos, queenPos);

            for (var move = 1; move <= moves; move++)
            {
                var knightPossibleMoves = knight.ValidMovesFor(knightPos).ToArray();
                knightPos = knightPossibleMoves[_rnd.Next(knightPossibleMoves.Length)];

                var bishopPossibleMoves = bishop.ValidMovesFor(bishopPos).ToArray();
                do
                {
                    bishopPos = bishopPossibleMoves[_rnd.Next(bishopPossibleMoves.Length)];
                } while (bishopPos.Equals(knightPos));

                var queenPossibleMoves = queen.ValidMovesFor(queenPos).ToArray();
                do
                {
                    queenPos = queenPossibleMoves[_rnd.Next(queenPossibleMoves.Length)];
                } while (queenPos.Equals(knightPos) || queenPos.Equals(bishopPos));

                Console.WriteLine("{0}: Knight position is {1}, Bishop position is {2}, Queen position is {3}", move, knightPos, bishopPos, queenPos);
            }
        }

        public void Setup()
        {
            knightStartPosition = new Position(3, 3);
            bishopStartPosition = new Position(4, 4);
            queenStartPosition = new Position(5, 5);
        }
    }

}
