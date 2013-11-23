/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Stack;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author troelshenningsen
 */
public class MoveGeneratorTest {

    public MoveGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of generateMoves method, of class MoveGenerator.
     */
    @Test
    public void testGenerateMovesForKing() {
        System.out.println("generateMoves");
        Board board = new Board();
        boolean player = true;
        MoveGenerator instance = new MoveGenerator();

        // Set up board values for test
        int[] testboarddata = {
            7, 7, 7, 7, 6, 1, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
        };

        Piece bking = new Piece(6, 4);

        ArrayList<Piece> whites = new ArrayList<>(); whites.add(bking);
        board.board = testboarddata; board.PiecesWhite = whites;
        
        Stack<Move> expResult = new Stack<>();  
        //Move move1 = new Move(4, 5);
        Move move2 = new Move(4, 3);Move move3 = new Move(4, 19);Move move4 = new Move(4, 20);Move move5 = new Move(4, 21);
        //expResult.add(move1);
        expResult.add(move2);expResult.add(move3);expResult.add(move4);expResult.add(move5);

        Stack<Move> result = instance.generateMoves(board, player);
        
        int b = expResult.size();
        for(int a=0;a<b;a++) {
            Move expRes = expResult.pop();
            Move res = result.pop();
            assertEquals(expRes.positionFrom, res.positionFrom);
            assertEquals(expRes.positionTo, res.positionTo);
        }
    }

    /**
     * Test of isValidMove method, of class MoveGenerator.
     */
    //@Test
    public void testIsValidMove() {
        System.out.println("isValidMove");
        MoveGenerator instance = new MoveGenerator();
        boolean expResult = false;
        boolean result = instance.isValidMove();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
