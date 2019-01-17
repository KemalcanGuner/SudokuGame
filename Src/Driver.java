public class Driver 
{	
	public static void main(String[] args) 
	{	
		new MainMenu();
		//Sudoku sudoku = new Sudoku("easy");
		//new SudokuFrame(sudoku, true, true);
	}
	
	//for debugging
	
	/*public static void writePuzzle(int[][] puzzle) 
	{
		for(int x = 0; x < 9; x++, System.out.println("")) 
		{
			if(x == 3 || x == 6) 
				System.out.println("—————— ——————— ——————");
			for(int y = 0; y < 9; y++) 
			{
			
				if(puzzle[x][y] == 0) 
				{
					if(y == 2 || y == 5)
						System.out.print("_ | ");  
					else
						System.out.print("_ ");
				}
				else if(y == 2 || y == 5)
					System.out.print(puzzle[x][y]+" | ");
				else
					System.out.print(puzzle[x][y]+" ");
			}
		}
		System.out.println();
	}*/
}

