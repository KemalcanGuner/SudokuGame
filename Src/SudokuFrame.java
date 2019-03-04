import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class SudokuFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Sudoku Sudoku; 
	private int[][] board = new int [9][9];
	
	private JFrame frame = new JFrame("Sudoku");
	private JPanel SudokuPanel = new JPanel();
	private GridPanel gridPanel = new GridPanel(new GridLayout(9,9,3,3));
	private JTextField[][] Cells = new JTextField[9][9]; 
	
	private JLabel time = new JLabel();
	private JButton undoButton = new JButton ("Undo");
		
	private ArrayList<Integer> moveListRow = new ArrayList<Integer>();
	private ArrayList<Integer> moveListCol = new ArrayList<Integer>();
	private ArrayList<Integer> moveListNum = new ArrayList<Integer>();
	private int moveIndex = -1;

	private boolean cellCheck;
	private boolean nightMode;
	private boolean showTime = true;
	private boolean hint = false;
	private boolean firstHint = true;
	private int seconds = 0;
	
	private Font sudokuFont = new Font("Mosk",Font.BOLD, 17);
	private Font timeFont = new Font("Mosk",Font.PLAIN, 25);
	private Font btnFont = new Font("Mosk",Font.BOLD, 14);

	
	private Color editText = Color.BLACK;
	private Color editBG = Color.WHITE;
	private Color properBG = Color.GRAY;
	private Color properText = Color.WHITE;
	private Color nonEditText = Color.WHITE;
	private Color nonEditBG = Color.DARK_GRAY;
	private Color falseText = Color.RED;
	private Color falseBG = Color.LIGHT_GRAY;
	private Color solveBG = Color.GREEN;
	private Color panelBG = Color.LIGHT_GRAY;
	private Color lineColor = Color.BLACK;
	private Color btnText = Color.BLACK;
	private Color btnBG = new JButton().getBackground();
	
	public SudokuFrame(Sudoku sudoku, boolean autoCheck, boolean isClassic) 
	{
		Sudoku = sudoku;
		for (int x = 0; x < 9; x++)
		{
			for (int y = 0; y < 9; y++)
			{
				Cells[x][y] = new JTextField();
				gridPanel.add(Cells[x][y]);
			}
		} 
		Timer stopWatch = new Timer();
		TimerTask task = new TimerTask() 
		{
			public void run() 
			{
				sudoku.seconds++;
				if (sudoku.seconds == 60) {
					sudoku.minutes++;
					sudoku.seconds = 0;
				}
				if (sudoku.minutes == 60) {
					sudoku.hours++;
					sudoku.minutes = 0;
				}
				String timeText = String.format("%02d:%02d:%02d", sudoku.hours, sudoku.minutes, sudoku.seconds );
				time.setText(timeText);
			}
		};
		stopWatch.schedule(task, 0, 1000);
		cellCheck = autoCheck;
		nightMode = !isClassic;
		if(nightMode) 
		{
			editText = Color.WHITE;
			editBG = Color.BLACK;
			properBG = Color.GRAY;
			properText = Color.WHITE;
			nonEditText = Color.WHITE;
			nonEditBG = Color.DARK_GRAY;
			falseText = Color.RED.brighter();
			falseBG = Color.LIGHT_GRAY;
			solveBG = Color.GREEN.darker();
			panelBG = Color.BLUE.darker().darker().darker().darker();
			lineColor = Color.WHITE;
			btnText = Color.WHITE;
			btnBG = Color.BLACK;
		}
		printBoard();
		setFrame();
	}
	
	private void setFrame() {
		SudokuPanel.setBackground(panelBG);
		SudokuPanel.setForeground(btnText);
		
		SudokuPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 1;
		c.weightx = 1;
		c.anchor = GridBagConstraints.NORTH;
		
		//time
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.weighty = 0.05;
		c.weightx = 0.05;
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.insets = new Insets(0, 0, 0, 10);
		c.ipadx = 10;
		c.ipady = 20;
		c.fill = GridBagConstraints.CENTER;
		time.setFont(timeFont);
		time.setForeground(btnText);
		SudokuPanel.add(time, c);
		
		JButton hideButton = new JButton("Hide");
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.weighty = 0.05;
		c.weightx = 0.05;
		c.ipadx = 0;
		c.ipady = -5;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.fill = GridBagConstraints.FIRST_LINE_END;
		SudokuPanel.add(hideButton, c);
		hideButton.setBackground(btnBG);
		hideButton.setForeground(btnText);
		hideButton.setFont(btnFont);
		hideButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				showTime = !showTime;
				if(!showTime) 
				{
					hideButton.setText("Show");
					time.setForeground(SudokuPanel.getBackground());
				}
				else 
				{
					hideButton.setText("Hide");
					time.setForeground(hideButton.getForeground());

				}
			}
		});
		
		JButton checkButton = new JButton ("Check");
		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 1;
		c.weighty = 0.05;
		c.weightx = 0.05;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 20;
		c.insets = new Insets(0, 0, 0, 10);
		SudokuPanel.add(checkButton, c);
		checkButton.setBackground(btnBG);
		checkButton.setForeground(btnText);
		checkButton.setFont(btnFont);
		checkButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				if(Sudoku.checkPuzzle())                		 
            	{
            		 for (int i = 0; i < 9; ++i) 
            	     {
            			 for (int j = 0; j < 9; ++j) 
            			 {
            	        	Cells[i][j].setBackground(solveBG);;   
            			 }
            	     }
            	}
				if(moveIndex != -1) 
				{
					int row,col;
					for(int i = 0, j = 0; i < moveListRow.size() && j < moveListCol.size() ; i++, j++) 
					{
						row = moveListRow.get(i);					
						col = moveListCol.get(j);
						if(Sudoku.isValid(Sudoku.getProblem(), row, col, board[row][col])) 
						{
							Cells[row][col].setBackground(properBG);
							Cells[row][col].setForeground(properText);
						}
						else
						{
							Cells[row][col].setForeground(falseText);
							Cells[row][col].setBackground(falseBG);
						}
					}
				}
			}
		});
		/*   */
		undoButton = new JButton ("Undo");
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weighty = 0.05;
		c.weightx = 0.05;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 10);
		SudokuPanel.add(undoButton, c);
		undoButton.setBackground(btnBG);
		undoButton.setForeground(btnText);
		undoButton.setFont(btnFont);
		undoButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				undo();
			}
		});
		
		JButton hintButton = new JButton ("Hint");
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 1;
		c.weighty = 0.05;
		c.weightx = 0.05;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 10);
		SudokuPanel.add(hintButton, c);
		hintButton.setBackground(btnBG);
		hintButton.setForeground(btnText);
		hintButton.setFont(btnFont);
		hintButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(firstHint) 
				{
					if(nightMode)
						JOptionPane.showMessageDialog(hintButton, "When cells become red, you can move your cursor to peek numbers on cells just for 3 seconds.\nRemember, the one you are looking for is the one who is looking.", "Tutorial", 2);
					else
						JOptionPane.showMessageDialog(hintButton, "When cells become black, you can move your cursor to peek numbers on cells just for 3 seconds.\nRemember, the one you are looking for is the one who is looking.", "Tutorial", 2);
	
					firstHint = false;
				}
				seconds = 0;
				Timer stopWatch = new Timer();
				TimerTask task = new TimerTask() 
				{
					public void run() 
					{
						if(seconds < 3)
							hint = true;
						else 
						{
							stopWatch.cancel();
							hint = false;
							printBoard();
						}
						seconds++;
					}
				};
				stopWatch.schedule(task, 0, 1000);
			}
		});
		
		JButton yieldButton = new JButton ("Give Up");
		c.gridx = 4;
		c.gridy = 4;
		c.gridwidth = 1;
		c.weighty = 0.05;
		c.weightx = 0.05;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;		
		c.insets = new Insets(0, 0, 0, 10);
		SudokuPanel.add(yieldButton, c);
		yieldButton.setBackground(btnBG);
		yieldButton.setForeground(btnText);
		yieldButton.setFont(btnFont);
		yieldButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int sure = JOptionPane.showConfirmDialog(null, "Do you really want to give up?\nYou can do it, bro-uh.", "Sure?", 0, 3);
				if(sure == 0) 
				{
					for(int i = 0; i < 9; i++)
						for(int j = 0; j < 9; j++)
							Cells[i][j].setText(Sudoku.getSolution()[i][j] + "");
					int cont = JOptionPane.showConfirmDialog(null, "Do you want to continue with Main Menu?", "Confirm", 0, 3);
					switch(cont) 
					{
						case 0:
							frame.setVisible(false);
							new MainMenu();
							break;
						default:
							frame.setVisible(false);
							System.exit(0);
							break;
					}
				}
			}
		});
		
		JButton quitButton = new JButton ("Save&Quit");
		c.gridx = 4;
		c.gridy = 5;
		c.gridwidth = 1;
		c.weighty = 0.05;
		c.weightx = 0.05;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 10);
		SudokuPanel.add(quitButton, c);
		quitButton.setBackground(btnBG);
		quitButton.setForeground(btnText);
		quitButton.setFont(btnFont);
		quitButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int sure = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Sure?", 0, 3);
				if(sure == 0) 
				{
					Sudoku.writeTxt();
					JOptionPane.showMessageDialog(null, "Your board is successfully saved.", "Save", 1);
					frame.dispose();
					System.exit(0);
				}
			}
		});
		
		c.gridx = 0;
		c.gridy = 1;
		c.weighty = 1;
		c.weightx = 1;
		c.gridwidth = 4;
		c.gridheight = 5;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.NORTH;
		SudokuPanel.add(gridPanel, c);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(799, 782);
		frame.getContentPane().add(SudokuPanel);
		frame.setLocation(240, 135);
		frame.setMinimumSize(new Dimension(300,300));
		frame.setResizable(false);
		frame.setAutoRequestFocus(true);
		frame.setVisible(true);
		} 

	private void printBoard()
	{
		board = Sudoku.getBoard();
		int[][] problem = Sudoku.getProblem(); 
		for (int i = 0; i < 9; i++ ) 
		{
			for(int j = 0; j < 9; j++) 
			{
				Cells[i][j].setFont(sudokuFont);
				if(board[i][j] == 0 || board[i][j] != problem[i][j]) 
				{
					Cells[i][j].setEditable(true);
					Cells[i][j].setForeground(editText);
					Cells[i][j].addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) 
						{
							JTextField source = (JTextField)e.getSource();
					        for (int row = 0; row < 9; ++row) 
					        {
					           for (int col = 0; col < 9; ++col) 
					           {
					              if (Cells[row][col] == source) 
					              {
					            	  
					            	  try {
					            		  if(Integer.parseInt(Cells[row][col].getText()) > 9 || Integer.parseInt(Cells[row][col].getText()) < 0)
					            			  JOptionPane.showMessageDialog(null, "You should enter a number between 0 - 9!", "Error", JOptionPane.ERROR_MESSAGE);
					            		  else 
					            		  {
					            			  if(Sudoku.isValid(board, row, col, Integer.parseInt(Cells[row][col].getText()))) 
								              {
								                	 Cells[row][col].setBackground(properBG);
								                	 if(cellCheck)
									                	 Cells[row][col].setForeground(properText);
								                	 else
									                	 Cells[row][col].setForeground(editText); 
								                	 board[row][col] = Integer.parseInt(Cells[row][col].getText());
								                	 Sudoku.setBoard(board);
								                	 moveListRow.add(row);
								                	 moveListCol.add(col);
								                	 moveListNum.add(0);
								                	 moveIndex++;
								                	 if(Sudoku.checkPuzzle())                		 
								                	 {
								                		 for (int i = 0; i < 9; ++i) 
								                	     {
								                			 for (int j = 0; j < 9; ++j) 
								                			 {
								                	        	Cells[i][j].setBackground(solveBG);   
								                	        	JOptionPane.showMessageDialog(null, "CONGRULATIONS!", "Congrats", JOptionPane.PLAIN_MESSAGE);
								                			 }
								                	     }
								                	 }
								                 }
								                 else 
								                 {
								                	 if(Integer.parseInt(Cells[row][col].getText()) == 0)
								                     {
								                    	 moveListRow.add(row);
								                    	 moveListCol.add(col);
								                    	 moveListNum.add(board[row][col]);
								                    	 moveIndex++;					                   
								                    	 board[row][col] = 0;
								                    	 Cells[row][col].setBackground(editBG);
								                    	 Cells[row][col].setText("");
								                    	 Cells[row][col].setForeground(editText);
								                     }
								                	 else 
								                	 {
								                		 moveListRow.add(row);
								                    	 moveListCol.add(col);
								                    	 moveListNum.add(0);
								                    	 moveIndex++;
								                		 if(cellCheck) 
								                		 {
								                			 Cells[row][col].setForeground(falseText);
									                		 Cells[row][col].setBackground(falseBG);
								                		 }
								                		 else 
									                	 {
									                		 Cells[row][col].setBackground(properBG);
										                	 Cells[row][col].setForeground(editText);
									                	 }
								                		 board[row][col] = Integer.parseInt(Cells[row][col].getText());
								                	 }
								                 }
					            		  }
					            	  }
					            	  catch(Exception exception){
					            		  JOptionPane.showMessageDialog(null, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					            	  }
					              }
						        }
					         }
						}
					});
					Cells[i][j].addMouseListener(new MouseListener() {
						public void mouseClicked(MouseEvent e){}
						public void mouseEntered(MouseEvent e) 
						{
							if(hint) 
							{
								JTextField source = (JTextField)e.getSource();
								for(int row = 0; row < 9; row++) 
								{
									for(int col = 0; col < 9; col++) 
									{
										if(board[row][col] == 0 || board[row][col] != problem[row][col]) 
										{
											Cells[row][col].setBackground(Color.BLACK);
											Cells[row][col].setForeground(Color.BLACK);
											if(nightMode)
											{
												Cells[row][col].setBackground(Color.RED.darker().darker());
												Cells[row][col].setForeground(Color.RED.darker().darker());
											}
											if(source == Cells[row][col]) 
											{
												Cells[row][col].setText(Sudoku.getSolution()[row][col]+"");
												Cells[row][col].setForeground(Color.WHITE);
											}
										}
									}
								}
							}
						}
						public void mouseExited(MouseEvent arg0){}
						public void mousePressed(MouseEvent arg0){}
						public void mouseReleased(MouseEvent arg0){}
					});
					if(board[i][j] != problem[i][j]) 
					{
						Cells[i][j].setText(board[i][j] + "");
						Cells[i][j].setBackground(properBG);
					}
					else 
					{
						Cells[i][j].setText("");
						Cells[i][j].setBackground(editBG);
					}
				}
				else 
				{
					Cells[i][j].setText(board[i][j] + "");
					Cells[i][j].setForeground(nonEditText);
					Cells[i][j].setBackground(nonEditBG);
					Cells[i][j].setEditable(false);
				}
				Cells[i][j].setHorizontalAlignment(JTextField.CENTER);
			}
		}
		SudokuPanel.setPreferredSize(new Dimension(722, 752));
		pack();			
	}

	public void undo() 
	{
		if(moveIndex != -1) 
		{
			int row = moveListRow.get(moveIndex);
			int col = moveListCol.get(moveIndex);
			if(moveListNum.get(moveIndex) == 0) 
			{
				board[row][col] = 0;
				Cells[row][col].setBackground(editBG);
            	Cells[row][col].setText("");
            	Cells[row][col].setForeground(editText);
            	moveListRow.remove(moveIndex);
            	moveListCol.remove(moveIndex);
            	moveListNum.remove(moveIndex);
            	moveIndex--;
			}
			else 
			{
				String str = moveListNum.get(moveIndex) + "";
				Cells[row][col].setText(str);
				Cells[row][col].setBackground(properBG);
				if(cellCheck)
				{ 
					 if(Sudoku.isValid(board, row, col, moveListNum.get(moveIndex)))
						 Cells[row][col].setForeground(properText);
					 else 
					 {
						 Cells[row][col].setBackground(falseBG);
						 Cells[row][col].setForeground(falseText); 
					 }
                	 board[row][col] = moveListNum.get(moveIndex);
				}
				else
					Cells[row][col].setForeground(editText);
				moveListRow.remove(moveIndex);
            	moveListCol.remove(moveIndex);
            	moveListNum.remove(moveIndex);
				moveIndex--;
			}
		}
		else 
			JOptionPane.showMessageDialog(undoButton,"There is no previous number.", "Warning", 2);
	}

	private class GridPanel extends JPanel{	
		private static final long serialVersionUID = 1L;

		GridPanel(GridLayout layout){
			super(layout);
		}
		public void paintComponent(Graphics g){
			//set background color
			g.setColor(panelBG);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			//lines between boxes
			g.setColor(lineColor);
			g.fillRect(getWidth()/3 ,0,3,getHeight());     //first vertical line
			g.fillRect(2*getWidth()/3 - 2,0,3,getHeight());	//second vertical line
			g.fillRect(0,getHeight()/3  -1,getWidth(),3);			//first horizontal line	
			g.fillRect(0,2*getHeight()/3 -1,getWidth(),3);		//second horizontal line
		}

	}

}
