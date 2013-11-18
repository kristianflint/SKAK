

package testaftranslate;
import testaftranslate.board;
import java.util.Scanner;

public class ABpruning
{
  //int alpha;
  //int beta;
  private int inf;
  private int lockBoard;
  private board BoardPointer;
  private board internalBoard = new board();
  private board nextMoveBoard = new board();
  java.util.Stack<board> childrenStack = new java.util.Stack<board>();
  
	public ABpruning(board boardObj)
	{
	  inf = 999999999;
	  lockBoard = 0;
	  //alpha = -inf;
	  //beta = inf;
	  BoardPointer = boardObj;
	  internalBoard = new board();
	}
	public void dispose()
	{

	}
	public void pruning(int level)
	{
	  board internalBoard = new board();
          internalBoard = BoardPointer;
	  AlphaBeta(internalBoard, -inf, inf, 1, 0, 9, 1);

	}

	//int nodeType = 1 max, 2 min
	public int AlphaBeta(board boardObj, int alphaSEND, int betaSEND, int nodeType, int level, int maxLevel, int turn)
	{
	  boolean isLeaf = false;
	  int V = 0;
	  int test;
	  int nextTurn = 2;
	  int alpha = alphaSEND;
	  int beta = betaSEND;
	  
	  board tempBoard = new board();



	  //Retunere static value
	  if (boardObj.getGameStatus() == 1)
	  {
		  return inf;
	  }
	  if (boardObj.getGameStatus() == 2)
	  {
		  return -inf;
	  }
	  if (boardObj.getGameStatus() == 3)
	  {
		  return 0;
	  }

	  //Tjekker om vi er leave
	  if (boardObj.getGameStatus() != 4)
	  {
		  isLeaf = true;
	  }
	  if (level == maxLevel)
	  {
		  isLeaf = true;
	  }

	  if (isLeaf)
	  {
	   return boardObj.getGameValue();
	  }


	  //Skifter turen til nÃ¦ste level
	  if (turn == 1)
	  {
		  nextTurn = 2;
	  }
	  if (turn == 2)
	  {
		  nextTurn = 1;
	  }


	  if (level == 0)
	  {
                
		createChildrenStack(boardObj, turn);
		while (!childrenStack.empty())
		{
		  tempBoard = childrenStack.peek();
		  childrenStack.pop();
		  if (tempBoard.getGameStatus() == 1)
		  {
                        
			nextMoveBoard = tempBoard;
                        tempBoard.printBoard();
			//goto endAll;
		  }
		}
	  }

//C++ TO JAVA CONVERTER WARNING: The following line was determined to be a copy constructor call - this should be verified and a copy constructor should be created if it does not yet exist:
//ORIGINAL LINE: createChildrenStack(boardObj, &childrenStack, turn);
	  createChildrenStack(boardObj, turn);

	  if (nodeType == 1)
	  {
		//MAX node
		while (alpha < beta && !childrenStack.empty()) // && children stack not empty
		{
		  if (childrenStack.empty())
			  break;
		  tempBoard = childrenStack.peek();
		  V = AlphaBeta(childrenStack.peek(), alpha, beta, 2, level + 1, maxLevel, nextTurn);
		  childrenStack.pop();
		  if (V > alpha)
		  {
			alpha = V;
			if (level == 0)
			{
			  nextMoveBoard = tempBoard;
			}
		  }
		}
		return alpha;


	  }
	  else
	  {
		//min node
		while (alpha < beta && !childrenStack.empty()) // && children stack not empty
		{
		  if (childrenStack.empty())
			  break;
		  V = AlphaBeta(childrenStack.peek(), alpha, beta, 1, level + 1, maxLevel, nextTurn);
		  childrenStack.pop();
		  if (V < beta)
		  {
			beta = V;
		  }
		}
		return beta;
	  }
//C++ TO JAVA CONVERTER TODO TASK: There are no gotos or labels in Java:
	//endAll:
	  //cout << "We are returning 0 \n";
	  //return 0;
	}
        
        
	public void createChildrenStack(board boardObj, int turn)
	{
	  int x;
	  int y;
	  int moreSpaceNoBoard;
	  board internalChildBoard = new board();
	  //board tempInternalChildBoard = new board();

	  board tempInternalChildBoard = boardObj;

	  while (boardObj.NextFreeSpace() != 0)
	  {
              
		internalChildBoard = tempInternalChildBoard;
               
		internalChildBoard.setValue(boardObj.localx, boardObj.localy, turn);
		boardObj.setValue(boardObj.localx, boardObj.localy, turn);
                
                
                System.out.println("boardObj\n");
                boardObj.printBoard(); 
                System.out.println("tempInternalChildBoard\n");
                tempInternalChildBoard.printBoard(); 
                
		childrenStack.push(internalChildBoard);
	  }


	}
	public  board getNextMoveBoard()
	{
		return nextMoveBoard;
	}
        
        
        
        
        
public static void main (String[] args) {
System.out.println( "WHAT!?");
  board tttBoard = new board();
  int x,y;
  int hX, hY;
  int starter;
  Scanner scan = new Scanner(System.in);
  ABpruning tester = new ABpruning(tttBoard);
  
  
  System.out.println( "who is strating? 1=you, 0=computer\n");
  starter = scan.nextInt();

  if (starter==0){
  
    while(tttBoard.getGameStatus() == 4){

          tester.pruning (1);
          tttBoard = tester.getNextMoveBoard();
          tttBoard.printBoard();
          if (tttBoard.getGameStatus() == 4){
            System.out.println("Your turn\n");
            hX = scan.nextInt();
            hY = scan.nextInt();
            while(tttBoard.getValue(hX,hY) != 0 ){
              System.out.println("Place taken\n");
                hX = scan.nextInt();
                hY = scan.nextInt();
            }
            tttBoard.setValue(hX, hY ,2);
          }
      }

  }else{

    while(tttBoard.getGameStatus() == 4){

        tttBoard.printBoard();
        if (tttBoard.getGameStatus() == 4){
          System.out.println( "Your turn\n");
            hX = scan.nextInt();
            hY = scan.nextInt();
          while(tttBoard.getValue(hX,hY) != 0 ){
            System.out.println("Place taken\n");
            hX = scan.nextInt();
            hY = scan.nextInt();
          }
          tttBoard.setValue(hX, hY ,2);
          tester.pruning (1);
          tttBoard = tester.getNextMoveBoard();
        }
    }
  }
  
  switch (tttBoard.getGameStatus())
  {
  case 1: System.out.println( "COMPUTER WON!!\n");
      tttBoard.printBoard();
      break;
  case 2: System.out.println( "YOU WON!!\n");
      break;
  case 3: System.out.println( "DRAW!!\n");
      break;
  default: System.out.println( "What tha fuck!?\n");
      break;
  }
  
 
}
}

