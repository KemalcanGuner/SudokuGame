import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Sudoku 
{
	private static final int unsolved = 0;
	private static final int[] numbers = {1,2,3,4,5,6,7,8,9};
	
	private int[][] problem = new int[9][9]; 	//the puzzle that is given to player to solve
	private int[][] board = new int[9][9]; 		//the puzzle that is used by player
	private int[][] temp = new int[9][9]; 		//temp is using to solve
	private int[][] solution = new int[9][9]; 	//solution of sudoku

	public static File saveDir = new File(System.getProperty("user.home"),"Sudoku");
	
	public int seconds, minutes, hours; 
	
	private Random random = new Random();
	
	private int[][] god1 = {{8,0,0,0,0,0,0,0,0},
							{0,0,3,6,0,0,0,0,0},
							{0,7,0,0,9,0,2,0,0},
							{0,5,0,0,0,7,0,0,0},
							{0,0,0,0,4,5,7,0,0},
							{0,0,0,1,0,0,0,3,0},
							{0,0,1,0,0,0,0,6,8},
							{0,0,8,5,0,0,0,1,0},
							{0,9,0,0,0,0,4,0,0}};
	private int[][] god2 = {{1,0,0,0,0,7,0,9,0},
		   					{0,3,0,0,2,0,0,0,8},
		   					{0,0,9,6,0,0,5,0,0},
		   					{0,0,5,3,0,0,9,0,0},
		   					{0,1,0,0,8,0,0,0,2},
		   					{6,0,0,0,0,4,0,0,0},
		   					{3,0,0,0,0,0,0,1,0},
		   					{0,4,0,0,0,0,0,0,7},
		   					{0,0,7,0,0,0,3,0,0}};
	private int[][] god3 = {{9,7,0,0,0,0,0,0,0},
							{4,0,0,8,0,0,0,0,0},
							{0,0,6,0,4,0,7,0,0},
							{5,0,0,0,9,0,6,0,0},
							{0,0,0,2,0,0,0,3,0},
							{0,0,0,0,0,1,0,0,8},
							{0,4,0,0,6,0,9,0,0},
							{0,0,5,0,0,0,0,2,0},
							{0,0,0,0,0,3,0,0,1}};
	Sudoku(String difficulty) 
	{
		createSudoku(difficulty);
		for(int row = 0; row < 9; row++) 		
			for(int col = 0; col < 9; col++) 					
				board[row][col] = problem[row][col];
		seconds = 0; minutes = 0; hours = 0;
	}
	Sudoku(int index) 
	{
		File loadTxt = new File(saveDir + File.separator + savedFiles()[index]);
		Scanner keybfortxt = null;
		try {
				keybfortxt = new Scanner(loadTxt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int x = 0, i = 0, a = 0; x < 28; x++) 
		{
			for(int y = 0; y < 9; y++) 
			{
				if(x < 9)
					problem[x][y] = keybfortxt.nextInt();
				else if(x < 18) 
					board[i][y] = keybfortxt.nextInt();
				else if(x < 27)
					solution[a][y] = keybfortxt.nextInt();
			}
			if(x > 8 && x < 18)
				i++;
			else if(x > 17 && x < 27)
				a++;
			else if(x == 27)
			{
				seconds = keybfortxt.nextInt();
				minutes = keybfortxt.nextInt();
				hours = keybfortxt.nextInt();
			}
		}
	}
	public int[][] getProblem() {
		return problem;
	}
	public int[][] getBoard() {
		return board;
	}
	public int[][] getSolution() {
		return solution;
	}
	public void setBoard(int[][] board) {
		this.board = board;
	}
	private void createSudoku(String diff)
	{
		Random random = new Random();
		int box1=0, box2=0, box3=0;
		int option = random.nextInt(6);
		switch(option) {
			case 0:
				box1=1; box2=5; box3=9;
				break;
			case 1:
				box1=1; box2=6; box3=8;
				break;
			case 2:
				box1=2; box2=4; box3=9;
				break;
			case 3:
				box1=2; box2=6; box3=7;
				break;
			case 4:
				box1=3; box2=5; box3=7;
				break;
			case 5:
				box1=3; box2=4; box3=8;
				break;
		}
		fillBox(box1);
		fillBox(box2);
		fillBox(box3);
		for(int row = 0; row < 9; row++) 		
			for(int col = 0; col < 9; col++) 					
				temp[row][col] = problem[row][col];
		if(solveSudoku())
		{	
			for(int row = 0; row < 9; row++) 		
				for(int col = 0; col < 9; col++) 					
					solution[row][col] = temp[row][col];
		}
		for(int row = 0; row < 9; row++) 		
			for(int col = 0; col < 9; col++) 					
				problem[row][col] = 0;
		switch(diff.toLowerCase()) 
		{
		case "expert":
			int [] selectedRows = new int[2*(random.nextInt(3)+9)];//18-22
			int [] selectedCols = new int[selectedRows.length];
			for(int i = 0; i < selectedRows.length/2 ; i++) //diagonal symmetric
			{
				selectedRows[i] = random.nextInt(9);
				selectedRows[i+(selectedRows.length/2)] = 8 - selectedRows[i];
				selectedCols[i] = random.nextInt(9);
				selectedCols[i+(selectedRows.length/2)] = 8 - selectedCols[i];
			}
			
			for(int i = 0; i < selectedRows.length; i++) 
				problem[selectedRows[i]][selectedCols[i]] = solution[selectedRows[i]][selectedCols[i]];
			break;
		case "hard":
			selectedRows = new int[2*(random.nextInt(3)+13)];//26-30
			selectedCols = new int[selectedRows.length];
			for(int i = 0; i < selectedRows.length/2 ; i++) //diagonal symmetric
			{
				selectedRows[i] = random.nextInt(9);
				selectedRows[i+(selectedRows.length/2)] = 8 - selectedRows[i];
				selectedCols[i] = random.nextInt(9);
				selectedCols[i+(selectedRows.length/2)] = 8 - selectedCols[i];
			}
			
			for(int i = 0; i < selectedRows.length; i++) 
				problem[selectedRows[i]][selectedCols[i]] = solution[selectedRows[i]][selectedCols[i]];
			break;
		case "medium":
			selectedRows = new int[2*(random.nextInt(3)+17)];//34-38
			selectedCols = new int[selectedRows.length];
			for(int i = 0; i < selectedRows.length/2 ; i++) //diagonal symmetric
			{
				selectedRows[i] = random.nextInt(9);
				selectedRows[i+(selectedRows.length/2)] = 8 - selectedRows[i];
				selectedCols[i] = random.nextInt(9);
				selectedCols[i+(selectedRows.length/2)] = 8 - selectedCols[i];
			}
			
			for(int i = 0; i < selectedRows.length; i++) 
				problem[selectedRows[i]][selectedCols[i]] = solution[selectedRows[i]][selectedCols[i]];
			break;
		case "easy":
			selectedRows = new int[(random.nextInt(6)+41)];//41-46
			selectedCols = new int[selectedRows.length];
			for(int i = 0; i < selectedRows.length; i++) 
			{
				selectedRows[i] = random.nextInt(9);
				selectedCols[i] = random.nextInt(9);
			}
			
			for(int i = 0; i < selectedRows.length; i++) 
				problem[selectedRows[i]][selectedCols[i]] = solution[selectedRows[i]][selectedCols[i]];
			break;
		case "absolute easy":
			selectedRows = new int[(random.nextInt(6)+50)];//50-55
			selectedCols = new int[selectedRows.length];
			for(int i = 0; i < selectedRows.length ; i++) 
			{
				selectedRows[i] = random.nextInt(9);
				selectedCols[i] = random.nextInt(9);
			}
			
			for(int i = 0; i < selectedRows.length; i++) 
				problem[selectedRows[i]][selectedCols[i]] = solution[selectedRows[i]][selectedCols[i]];
			break;
		case "god mode":
			option = random.nextInt(3);			
			switch(option) 
			{
				case 0:
					problem = god1;
					break;
				case 1:
					problem = god2;
					break;
				case 2:
					problem = god3;
					break;
			}
			for(int row = 0; row < 9; row++) 		
				for(int col = 0; col < 9; col++) 					
					temp[row][col] = problem[row][col];			
			solveSudoku();
			for(int row = 0; row < 9; row++) 		
				for(int col = 0; col < 9; col++) 					
					solution[row][col] = temp[row][col];
			break;
		}
		
	}
	private void fillBox(int boxNumber) 
	{	
		int row=0, col=0;
		switch(boxNumber){
		case 1:
			row = 0; col = 0;
			break;
		case 2:
			row = 3; col = 0;
			break;
		case 3:
			row = 6; col = 0;
			break;
		case 4:
			row = 0; col = 3;
			break;
		case 5: 
			row = 3; col = 3;
			break;
		case 6:
			row = 6; col = 3;
			break;
		case 7:
			row = 0; col = 6;
			break;
		case 8:
			row = 3; col = 6;
			break;
		case 9:
			row = 6; col = 6;
			break;	
		}
		shuffle(numbers);
		for(int i = row; i < row+3; i++)
			for(int j = col; j < col+3; j++) 
				problem[i][j] = numbers[(i*3+ j)%9];
	}
	public boolean checkPuzzle() 
	{
		int count = 0;
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				if(isValid(board, i, j, board[i][j])) 
					count++;
		if(count == 81)
			return true;
		else 
			return false;
		
	}
	private boolean solveSudoku() 
	{
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++) 
			{
				if(temp[row][col] == unsolved) 
				{
					for(int i = 1; i <= 9; i++) 
					{
						if(isValid(temp,row,col,i)) 
						{
							temp[row][col] = i;
							if(solveSudoku())
								return true;
							else
								temp[row][col] = unsolved;
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	public boolean isValid(int[][] sudoku, int row, int col, int number) 
	{
		return isValidRow(sudoku,row,number) && isValidCol(sudoku,col,number) && isValidBox(sudoku,row, col, number);
	}
	private boolean isValidRow(int[][] sudoku,int row, int number) 
	{
		for(int col = 0; col < 9; col++)
			if(sudoku[row][col] == number)
				return false;
		return true;
	}
	private boolean isValidCol(int[][] sudoku,int col, int number) 
	{
		for(int row = 0; row < 9; row++) 
			if(sudoku[row][col] == number)
				return false;
		return true;
	}
	private boolean isValidBox(int[][] sudoku,int row, int col, int number) 
	{
		int row_box = row - row%3;
		int col_box = col - col%3;
		for(int i = row_box; i <= row_box +2; i++) 
			for(int j = col_box; j <= col_box +2; j++)
				if(sudoku[i][j] == number)
					return false;		
		return true;
	}
	private void shuffle(int []array) 
	{
		for(int i = array.length-1; i > 0; i--) 
		{
			int index = random.nextInt(i);
			int temp = array[i];
			array[i] = array[index];
			array[index] = temp;
		}	
	}
	public void writeTxt()
	{
		DateFormat dateFormat = new SimpleDateFormat("DD-MM-yyyy[HH.mm.ss]");
		Date date = new Date();
		String saveStr = dateFormat.format(date) + ".txt";
		File saveTxt = new File(saveDir + File.separator + saveStr);
		saveDir.mkdir();
		try {
			saveTxt.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(saveTxt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++)
				writer.print(problem[row][col]+" ");
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++)
				writer.print(board[row][col]+" ");
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++)
				writer.print(solution[row][col]+" ");
		writer.print(seconds + " " + minutes + " " + hours + " ");
		writer.close();
	}
	public static boolean isLoadable()
	{
		if(saveDir.exists())
			return true;
		else 
			return false;
	}
	public static String[] savedFiles()
	{
		return saveDir.list();
	}
	public static void writePuzzle(int[][] puzzle) 
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
	}
}