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
            7, 7, 7, 1, 6, 8, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
            7, 7, 7, 7, 7, 7, 7, 7,  7, 7, 7, 7, 7, 7, 7, 7,
        };

        Piece wking = new Piece(6, 4);
        Piece bpawn = new Piece(8, 5);
        ArrayList<Piece> whites = new ArrayList<>(); whites.add(wking);
        ArrayList<Piece> blacks = new ArrayList<>(); blacks.add(bpawn);
        board.board = testboarddata; board.PiecesWhite = whites; board.PiecesBlack = blacks;
        
        Stack<Move> expResult = new Stack<>();  
        CaptureMove move2 = new CaptureMove(4, 5, new Piece(6, 4),new Piece(8,5));Move move3 = new Move(4, 19,new Piece(6, 4));Move move4 = new Move(4, 20,new Piece(6, 4));Move move5 = new Move(4, 21,new Piece(6, 4));
        expResult.add(move2);expResult.add(move3);expResult.add(move4);expResult.add(move5);

        Stack<Move> result = instance.generateMoves(board, player);
        
        //CaptureMove expC = (CaptureMove)expResult.pop();
        //CaptureMove resC = (CaptureMove)result.pop();
        
        
       
        int b = expResult.size()-1;
        for(int a=0;a<b;a++) {
            Move expRes = expResult.pop();
            Move res = result.pop();
            if (res instanceof CaptureMove) {
                CaptureMove expResC = (CaptureMove)expRes;
                CaptureMove resC = (CaptureMove)res;
                assertEquals(expResC.pieceToMove.position, resC.pieceToMove.position);
                assertEquals(expResC.pieceToMove.type, resC.pieceToMove.type);
                assertEquals(expResC.pieceToCapture.position, resC.pieceToCapture.position);
                assertEquals(expResC.pieceToCapture.type, resC.pieceToCapture.type);
            }
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
