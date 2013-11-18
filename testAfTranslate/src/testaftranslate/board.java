package testaftranslate;


public class board
{
	private int[][] boardLayout = new int[3][3];
	private int[][] boardValues = new int[3][3];
        public int localx, localy;
        

	public board()
	{
	  //reset the board
	  for (int i = 0; i < 3; i++)
	  {
		boardLayout[i][0] = 0;
		boardLayout[i][1] = 0;
		boardLayout[i][2] = 0;
	  }

	  boardValues[0][0] = 3;
	  boardValues[0][1] = 2;
	  boardValues[0][2] = 3;

	  boardValues[1][0] = 2;
	  boardValues[1][1] = 4;
	  boardValues[1][2] = 2;

	  boardValues[2][0] = 3;
	  boardValues[2][1] = 2;
	  boardValues[2][2] = 3;
	}
	public void dispose()
	{

	}
        
	public void printBoard()
	{

	  String printString = "";
	   // cout << "|" << boardLayout[0][0] << "," << boardLayout[1][0] << "," << boardLayout[0][2] << "|\n";
	   // cout << "|" << boardLayout[0][1] << "," << boardLayout[1][1] << "," << boardLayout[1][2] << "|\n";
	   // cout << "|" << boardLayout[0][2] << "," << boardLayout[1][2] << "," << boardLayout[2][2] << "|\n";
		for (int x = 0; x < 3; x++)
		{
		  for (int y = 0; y < 3; y++)
		  {
			  if (boardLayout[y][x] == 1)
			  {
				  printString += "| x ";
			  }
			  if (boardLayout[y][x] == 2)
			  {
				  printString += "| o ";
			  }
			  if (boardLayout[y][x] == 0)
			  {
				  printString += "|   ";
			  }
		  }
		printString += "|\n";
		}
	  System.out.print(" -----------\n");
	  System.out.print(printString);
	  System.out.print(" -----------\n");
	}
	public int getGameValue()
	{
	  int tempValue = 0;

		for (int x = 0; x < 3; x++)
		{
		  for (int y = 0; y < 3; y++)
		  {
			if (boardLayout[x][y] == 1)
			{
				tempValue += boardValues[x][y];
			}
			if (boardLayout[x][y] == 2)
			{
				tempValue -= boardValues[x][y];
			}
		  }
		}
	  return tempValue;
	}

	//Computer = 1
	//Human = 2
	//returens codes 1 = computer wins! 2 = Human wins! 3 = draw! 4 = not finish!
	public int getGameStatus()
	{
	  int draw = 0;
		//Computer wins
		if ((boardLayout[0][0] == 1 && boardLayout[1][0] == 1 && boardLayout[2][0] == 1) || (boardLayout[0][0] == 1 && boardLayout[0][1] == 1 && boardLayout[0][2] == 1) || (boardLayout[0][2] == 1 && boardLayout[1][2] == 1 && boardLayout[2][2] == 1) || (boardLayout[2][2] == 1 && boardLayout[2][1] == 1 && boardLayout[2][0] == 1) || (boardLayout[0][1] == 1 && boardLayout[1][1] == 1 && boardLayout[2][1] == 1) || (boardLayout[1][0] == 1 && boardLayout[1][1] == 1 && boardLayout[1][2] == 1) || (boardLayout[0][0] == 1 && boardLayout[1][1] == 1 && boardLayout[2][2] == 1) || (boardLayout[0][2] == 1 && boardLayout[1][1] == 1 && boardLayout[2][0] == 1)) //cross - cross - middel - middel - right - buttom - left - top
		{
			return 1;
		}

		//Human wins
		if ((boardLayout[0][0] == 2 && boardLayout[1][0] == 2 && boardLayout[2][0] == 2) || (boardLayout[0][0] == 2 && boardLayout[0][1] == 2 && boardLayout[0][2] == 2) || (boardLayout[0][2] == 2 && boardLayout[1][2] == 2 && boardLayout[2][2] == 2) || (boardLayout[2][2] == 2 && boardLayout[2][1] == 2 && boardLayout[2][0] == 2) || (boardLayout[0][1] == 2 && boardLayout[1][1] == 2 && boardLayout[2][1] == 2) || (boardLayout[1][0] == 2 && boardLayout[1][1] == 2 && boardLayout[1][2] == 2) || (boardLayout[0][0] == 2 && boardLayout[1][1] == 2 && boardLayout[2][2] == 2) || (boardLayout[0][2] == 2 && boardLayout[1][1] == 2 && boardLayout[2][0] == 2)) //cross - cross - middel - middel - right - buttom - left - top
		{
			return 2;
		}

		  for (int x = 0; x < 3; x++)
		  {
			for (int y = 0; y < 3; y++)
			{
				if (boardLayout[x][y] == 0)
				{
					draw += 1;
				}
			}
		  }
		  if (draw == 0)
		  {
			  return 3;
		  }


		return 4;
	}
	public int getValue(int x, int y)
	{
	  return boardLayout[x][y];
	}
	public void setValue(int x, int y, int value)
	{
	  boardLayout[x][y] = value;
	}        
	public int NextFreeSpace()
	{
		int k = 0;

		for (int x = 0; x < 3; x++)
		{
		  for (int y = 0; y < 3; y++)
		  {
			if (boardLayout[x][y] == 0)
			{
			  k = 1;
			  localx = x;
			  localy = y;
			  if (k == 1){
                            return 1;
                          }else{
                            return 0;
                          }
			}
		  }
		}
                return 0;
	}
}