import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainMenu extends JFrame implements MouseListener, ActionListener
{
	private static final long serialVersionUID = 1L;
	
	public JFrame frame = new JFrame("Sudoku");
	private JPanel menuPanel = new JPanel();
	private JLabel imageLabel = new JLabel();
	private ImageIcon bgImage = new ImageIcon("src\\Sudoku.gif");

	JButton playButton = new JButton("Play Game");
	JButton howButton = new JButton("How to Play?");
	JButton settingsButton = new JButton("Settings");
	JButton creditsButton = new JButton("Credits");
	JButton exitButton = new JButton("Exit");
	
	JButton[] extraMenu = new JButton[8];
	JLabel [] labelGroup = new JLabel[6];
	JButton[] settingGroup = new JButton[8];
	
	JButton newGameButton = new JButton("New game");
	JButton loadGameButton = new JButton("Load game");
	JLabel difficultyLabel = new JLabel("Select");
	JLabel difficultyLabel2 = new JLabel("Difficulty");
	JButton absEzButton = new JButton("Absolute Easy");
	JButton ezButton = new JButton("Easy");
	JButton medButton = new JButton("Medium");
	JButton hardButton = new JButton("Hard");	
	JButton expertButton = new JButton("Expert");
	JButton godButton = new JButton("God Mode");
	
	JLabel set1Label = new JLabel("Setting1");
	JButton option1 = new JButton("On");
	JButton option2 = new JButton("Off");
    JLabel checkLabel = new JLabel("Auto-Check");
    JButton option3 = new JButton("On");
    JButton option4 = new JButton("Off");
	JLabel modeLabel2 = new JLabel("Theme");
	JButton option5 = new JButton("Classic");
	JButton option6 = new JButton("Dark");
	JLabel set4Label = new JLabel("Setting4");
	JButton option7 = new JButton("On");
	JButton option8 = new JButton("Off");
	
	private boolean newGameClicked = false;
	private boolean playButtonClicked = false;
	private boolean autoCheck = true;
	//private boolean setting1 = true;
	//private boolean setting4 = true;
	private boolean isClassic = true;

	private Font font = new Font("Lucida Console",Font.BOLD, 17);
	private Font font2 = new Font("Lucida Console",Font.BOLD, 66);
	private Color textColor_linen = new Color(247,235,232);
	private Color bgColor = new Color(36,72,87);
	private Color trueColor = Color.GREEN.darker();
	private Color falseColor = Color.ORANGE.darker().darker();
	private Color hlColor = Color.PINK;
	
	public MainMenu() 
	{
		setFrame();
	}
	private void setFrame() 
	{
		menuPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel cons = new JLabel();
		cons.setText("");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 6;
		c.gridheight = 10;
		c.weighty = 1;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 10, 0, 0);
		c.ipadx = 20;
		c.ipady = 300;
		menuPanel.add(cons,c);
	
		//Play Button
		playButton.setFont(font);
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.weighty = 0.05;
		c.weightx = 0.05;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10, 10, 0, 0);
		c.ipadx = 10;
		c.ipady = 10;
		c.fill = GridBagConstraints.LINE_START;
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);
		playButton.setFocusPainted(false);
		playButton.setForeground(textColor_linen);
		menuPanel.add(playButton,c);
		playButton.addMouseListener(this);
	
			//New Game
			c.gridx = 1;
			c.gridy = 10;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weighty = 0.05;
			c.weightx = 0.05;
			c.insets = new Insets(0, 0, 0, 0);
			c.fill = GridBagConstraints.LINE_START;
			c.anchor = GridBagConstraints.LINE_START;
			newGameButton.setFont(font);
			newGameButton.setOpaque(false);
			newGameButton.setContentAreaFilled(false);
			newGameButton.setBorderPainted(false);
			newGameButton.setFocusPainted(false);
			newGameButton.setForeground(textColor_linen);
			newGameButton.setVisible(true);
			menuPanel.add(newGameButton,c);
			newGameButton.addMouseListener(this);

			//Load game
			c.gridx = 1;
			c.gridy = 11;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weighty = 0.05;
			c.weightx = 0.05;
			c.insets = new Insets(0, 0, 0, 0);
			c.fill = GridBagConstraints.LINE_START;
			c.anchor = GridBagConstraints.LINE_START;
			loadGameButton.setFont(font);
			loadGameButton.setOpaque(false);
			loadGameButton.setContentAreaFilled(false);
			loadGameButton.setBorderPainted(false);
			loadGameButton.setFocusPainted(false);
			loadGameButton.setForeground(textColor_linen);
			loadGameButton.setVisible(true);
			menuPanel.add(loadGameButton,c);
			loadGameButton.addMouseListener(this);
			
				// Select 
				c.gridx = 2;
				c.gridy = 10;
				c.gridwidth = 2;
				c.gridheight = 1;
				c.weighty = 0.05;
				c.weightx = 0.05;
				c.insets = new Insets(0, 0, -10, 0);
				c.fill = GridBagConstraints.CENTER;
				c.anchor = GridBagConstraints.CENTER;
				difficultyLabel.setOpaque(false);
				difficultyLabel.setForeground(textColor_linen);
				difficultyLabel.setFont(font);
				menuPanel.add(difficultyLabel,c);
			    // Difficulty
				c.gridx = 2;
				c.gridy = 11;
				c.gridwidth = 2;
				c.gridheight = 1;
				c.weighty = 0.05;
				c.weightx = 0.05;
				c.insets = new Insets(-10, 0, 0, 0);
				c.fill = GridBagConstraints.CENTER;
				c.anchor = GridBagConstraints.CENTER;
				difficultyLabel2.setOpaque(false);
				difficultyLabel2.setForeground(textColor_linen);
				difficultyLabel2.setFont(font);
				menuPanel.add(difficultyLabel2,c);
					// Absolutely easy
					c.gridx = 4;
					c.gridy = 10;
					c.gridwidth = 1;
					c.gridheight = 1;
					c.weighty = 0.05;
					c.weightx = 0.05;
					c.fill = GridBagConstraints.LINE_START;
					c.anchor = GridBagConstraints.LINE_START;
					absEzButton.setOpaque(false);
					absEzButton.setContentAreaFilled(false);
					absEzButton.setBorderPainted(false);
					absEzButton.setFocusPainted(false);
					absEzButton.setForeground(textColor_linen);
					absEzButton.setFont(font);
					menuPanel.add(absEzButton,c);
					absEzButton.addMouseListener(this);

					//Easy
					c.gridx = 4;
					c.gridy = 11;
					c.gridwidth = 1;
					c.gridheight = 1;
					c.weighty = 0.05;
					c.weightx = 0.05;
					c.fill = GridBagConstraints.LINE_START;
					c.anchor = GridBagConstraints.LINE_START;
					ezButton.setOpaque(false);
					ezButton.setContentAreaFilled(false);
					ezButton.setBorderPainted(false);
					ezButton.setFocusPainted(false);
					ezButton.setForeground(textColor_linen);
					ezButton.setFont(font);
					menuPanel.add(ezButton,c);
					ezButton.addMouseListener(this);

			    	//Medium
					c.gridx = 4;
					c.gridy = 12;
					c.gridwidth = 1;
					c.gridheight = 1;
					c.weighty = 0.05;
					c.weightx = 0.05;
					c.fill = GridBagConstraints.LINE_START;
					c.anchor = GridBagConstraints.LINE_START;
					medButton.setOpaque(false);
					medButton.setContentAreaFilled(false);
					medButton.setBorderPainted(false);
					medButton.setFocusPainted(false);
					medButton.setForeground(textColor_linen);
					medButton.setFont(font);
					menuPanel.add(medButton,c);
					medButton.addMouseListener(this);

					//Hard
					c.gridx = 4;
					c.gridy = 13;
					c.gridwidth = 1;
					c.gridheight = 1;
					c.weighty = 0.05;
					c.weightx = 0.05;
					c.fill = GridBagConstraints.LINE_START;
					c.anchor = GridBagConstraints.LINE_START;
					hardButton.setOpaque(false);
					hardButton.setContentAreaFilled(false);
					hardButton.setBorderPainted(false);
					hardButton.setFocusPainted(false);
					hardButton.setForeground(textColor_linen);
					hardButton.setFont(font);
					menuPanel.add(hardButton,c);
					hardButton.addMouseListener(this);

					//Expert
					c.gridx = 4;
					c.gridy = 14;
					c.gridwidth = 1;
					c.gridheight = 1;
					c.weighty = 0.05;
					c.weightx = 0.05;
					c.fill = GridBagConstraints.LINE_START;
					c.anchor = GridBagConstraints.LINE_START;
					expertButton.setOpaque(false);
					expertButton.setContentAreaFilled(false);
					expertButton.setBorderPainted(false);
					expertButton.setFocusPainted(false);
					expertButton.setForeground(textColor_linen);
					expertButton.setFont(font);
					menuPanel.add(expertButton,c);
					expertButton.addMouseListener(this);

					//God mode
					c.gridx = 4;
					c.gridy = 15;
					c.gridwidth = 1;
					c.gridheight = 1;
					c.weighty = 0.05;
					c.weightx = 0.05;
					c.fill = GridBagConstraints.LINE_START;
					c.anchor = GridBagConstraints.LINE_START;
					godButton.setOpaque(false);
					godButton.setContentAreaFilled(false);
					godButton.setBorderPainted(false);
					godButton.setFocusPainted(false);
					godButton.setForeground(textColor_linen);
					godButton.setFont(font);
					menuPanel.add(godButton,c);
					godButton.addMouseListener(this);
					
				

		
		//How to Play
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 2;
		c.gridheight = 2;
		c.weighty = 0.05;
		c.weightx = 0.05;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(40, 10, 0, 0);
		c.ipadx = 10;
		c.ipady = 10;
		c.fill = GridBagConstraints.CENTER;
		howButton.setFont(font);
		howButton.setOpaque(false);
		howButton.setContentAreaFilled(false);
		howButton.setBorderPainted(false);
		howButton.setFocusPainted(false);
		howButton.setForeground(textColor_linen);
		menuPanel.add(howButton,c);
		howButton.addMouseListener(this);

		
		//Settings
		c.gridx = 0;
		c.gridy = 15;
		c.gridwidth = 2;
		c.gridheight = 2;
		c.weighty = 0.05;
		c.weightx = 0.05;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(-10, 10, 0, 0);
		c.ipadx = 10;
		c.ipady = 10;
		c.fill = GridBagConstraints.CENTER;
		settingsButton.setOpaque(false);
		settingsButton.setContentAreaFilled(false);
		settingsButton.setBorderPainted(false);
		settingsButton.setFocusPainted(false);
		settingsButton.setForeground(textColor_linen);
		settingsButton.setFont(font);
		menuPanel.add(settingsButton,c);
		settingsButton.addMouseListener(this);

								
			c.gridx = 1;
			c.gridy = 14;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weighty = 0.05;
			c.weightx = 0.05;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(0, 15, 0, 0);
			set1Label.setOpaque(false);
			set1Label.setForeground(bgColor);
			set1Label.setFont(font);
			menuPanel.add(set1Label,c); 
			   
		        c.gridx = 2;
		        c.gridy = 14;
		        c.anchor = GridBagConstraints.LINE_END;
				c.insets = new Insets(0, 15, 0, 0);
		        option1.setOpaque(false);
		        option1.setContentAreaFilled(false);
		        option1.setBorderPainted(false);
		        option1.setFocusPainted(false);
		        option1.setForeground(bgColor);
		        option1.setFont(font);
		        option1.addMouseListener(this);
		        menuPanel.add(option1,c);
		        
		        c.gridx = 3;
		        c.gridy = 14;
		        c.anchor = GridBagConstraints.LINE_START;
				c.insets = new Insets(0, -12, 0, 8);
		        option2.setOpaque(false);
		        option2.setContentAreaFilled(false);
		        option2.setBorderPainted(false);
		        option2.setFocusPainted(false);
		        option2.setForeground(bgColor);
		        option2.setFont(font);
		        option2.addMouseListener(this);
		        menuPanel.add(option2,c);
		        
			
			c.gridx = 1;
			c.gridy = 15;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weighty = 0.05;
			c.weightx = 0.05;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(0, 15, 0, 0);
			checkLabel.setOpaque(false);
			checkLabel.setForeground(textColor_linen);
			checkLabel.setFont(font);
			menuPanel.add(checkLabel,c);
		
				
	      
				c.gridx = 2;
				c.gridy = 15;
				c.anchor = GridBagConstraints.LINE_END;
				c.insets = new Insets(0, 15, 0, 0);
				option3.setOpaque(false);
				option3.setContentAreaFilled(false);
				option3.setBorderPainted(false);
				option3.setFocusPainted(false);
				option3.setForeground(textColor_linen);
				option3.setFont(font);
				option3.addMouseListener(this);
				menuPanel.add(option3,c);
	        
				c.gridx = 3;
				c.gridy = 15;
				c.anchor = GridBagConstraints.LINE_START;
				c.insets = new Insets(0, -12, 0, 8);
				option4.setOpaque(false);
				option4.setContentAreaFilled(false);
				option4.setBorderPainted(false);
				option4.setFocusPainted(false);
				option4.setForeground(textColor_linen);
				option4.setFont(font);
				option4.addMouseListener(this);
				menuPanel.add(option4,c);
			
			c.gridx = 1;
			c.gridy = 16;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weighty = 0.05;
			c.weightx = 0.05;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(0, 15, 0, 0);
			modeLabel2.setOpaque(false);
			modeLabel2.setForeground(textColor_linen);
			modeLabel2.setFont(font);
			menuPanel.add(modeLabel2,c);
				
		
				c.gridx = 2;
				c.gridy = 16;
				c.anchor = GridBagConstraints.LINE_END;
				c.insets = new Insets(0, 15, 0, 0);
				option5.setOpaque(false);
				option5.setContentAreaFilled(false);
				option5.setBorderPainted(false);
				option5.setFocusPainted(false);
				option5.setForeground(textColor_linen);
				option5.setFont(font);
				option5.addMouseListener(this);
				menuPanel.add(option5,c);
		    
				c.gridx = 3;
				c.gridy = 16;
				c.anchor = GridBagConstraints.LINE_START;
				c.insets = new Insets(0, -12, 0, 8);
				option6.setOpaque(false);
				option6.setContentAreaFilled(false);
				option6.setBorderPainted(false);
				option6.setFocusPainted(false);
				option6.setForeground(textColor_linen);
				option6.setFont(font);
				option6.addMouseListener(this);
				menuPanel.add(option6,c);
				
			c.gridx = 1;
			c.gridy = 17;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.weighty = 0.05;
			c.weightx = 0.05;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.anchor = GridBagConstraints.LINE_START;
			c.insets = new Insets(0, 15, 0, 0);
			set4Label.setOpaque(false);
			set4Label.setForeground(bgColor);
			set4Label.setFont(font);
			menuPanel.add(set4Label,c);
			
				c.gridx = 2;
				c.gridy = 17;
				c.anchor = GridBagConstraints.LINE_END;
				c.insets = new Insets(0, 15, 0, 0);
				option7.setOpaque(false);
				option7.setContentAreaFilled(false);
				option7.setBorderPainted(false);
				option7.setFocusPainted(false);
				option7.setForeground(bgColor);
				option7.setFont(font);
				option7.addMouseListener(this);
				menuPanel.add(option7,c);
		    
				c.gridx = 3;
				c.gridy = 17;
				c.anchor = GridBagConstraints.LINE_START;
				c.insets = new Insets(0, -12, 0, 8);
				option8.setOpaque(false);
				option8.setContentAreaFilled(false);
				option8.setBorderPainted(false);
				option8.setFocusPainted(false);
				option8.setForeground(bgColor);
				option8.setFont(font);
				option8.addMouseListener(this);
				menuPanel.add(option8,c);	
						
		creditsButton.setFont(font);
		c.gridx = 0;
		c.gridy = 18;
		c.gridwidth = 2;
		c.gridheight = 2;
		c.weighty = 0.05;
		c.weightx = 0.05;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0, 10, 30, 0);
		c.ipadx = 10;
		c.ipady = 10;
		c.fill = GridBagConstraints.CENTER;
		creditsButton.setOpaque(false);
		creditsButton.setContentAreaFilled(false);
		creditsButton.setBorderPainted(false);
		creditsButton.setFocusPainted(false);
		creditsButton.setForeground(textColor_linen);
		menuPanel.add(creditsButton,c);
		creditsButton.addMouseListener(this);
		
		exitButton.setFont(font);
		c.gridx = 0;
		c.gridy = 21;
		c.gridheight = 2;
		c.gridwidth = 2;
		c.weighty = 0.05;
		c.weightx = 0.05;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10, 10, 0, 0);
		c.ipadx = 10;
		c.ipady = 10;
		c.fill = GridBagConstraints.CENTER;
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setFocusPainted(false);
		exitButton.setForeground(textColor_linen);
		menuPanel.add(exitButton,c);
		exitButton.addMouseListener(this);
		
		imageLabel.setIcon(bgImage);
		menuPanel.setOpaque(false);
		frame.add(menuPanel);
		frame.pack();
		frame.add(imageLabel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(798, 781);
		frame.setSize(799, 782);
		frame.setLocation(240, 135);
		frame.setMinimumSize(new Dimension(300,300));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.addMouseListener(this);
		
		extraMenu[0] = newGameButton;
		extraMenu[1] = loadGameButton;
		extraMenu[2] = absEzButton;
		extraMenu[3] = ezButton;
		extraMenu[4] = medButton;
		extraMenu[5] = hardButton;
		extraMenu[6] = expertButton;
		extraMenu[7] = godButton;
		
		labelGroup[0] = difficultyLabel;
		labelGroup[1] = difficultyLabel2;
		labelGroup[2] = checkLabel;
		labelGroup[3] = checkLabel;
		labelGroup[4] = modeLabel2;
		labelGroup[5] = checkLabel;
		
		settingGroup[0] = option3;
		settingGroup[1] = option3;
		settingGroup[2] = option3;
		settingGroup[3] = option4;
		settingGroup[4] = option5;
		settingGroup[5] = option6;
		settingGroup[6] = option3;
		settingGroup[7] = option3;
		 
		for (int i = 0; i < 8; i++ )
			extraMenu[i].setForeground(bgColor);
		
		for (int i = 0; i < 6; i++ )
			labelGroup[i].setForeground(bgColor);

		for (int i = 0; i < 8; i++ )
			settingGroup[i].setForeground(bgColor);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
			if(e.getSource() == playButton && !newGameClicked)
			{ playButtonClicked = true;
				for (int i = 0; i < 8; i++ )
					if(i<2)
						extraMenu[i].setForeground(textColor_linen);
					else
						extraMenu[i].setForeground(bgColor);
				if(!Sudoku.isLoadable()) 	
					loadGameButton.setEnabled(false);	
				else
					loadGameButton.setForeground(textColor_linen);
				for (int i = 0; i < 6; i++ )
					labelGroup[i].setForeground(bgColor);

				for (int i = 0; i < 8; i++ )
					settingGroup[i].setForeground(bgColor);
			}
		
		if(e.getSource() == newGameButton)
		{
			newGameClicked = true;
			difficultyLabel.setForeground(hlColor);
			difficultyLabel2.setForeground(hlColor);
			newGameButton.setForeground(hlColor);
			for (int i = 2; i < 8; i++ )
				extraMenu[i].setForeground(textColor_linen);
		}
		
		if(e.getSource() == loadGameButton && Sudoku.isLoadable()) 
		{
			int index = 0;
			Object choose = JOptionPane.showInputDialog(null,"Choose one", "Input",JOptionPane.INFORMATION_MESSAGE, null,
														Sudoku.savedFiles(), Sudoku.savedFiles()[Sudoku.savedFiles().length-1]);
			if(choose == null) 
			{
				
			}
			else 
			{		
				for(int i = Sudoku.savedFiles().length - 1 ; i >= 0 ; i--)
					if(choose.equals(Sudoku.savedFiles()[i]))
					{
						index = i;
						break;
					}
			}
				Sudoku sudoku = new Sudoku(index);
				new SudokuFrame(sudoku, autoCheck, isClassic);
				frame.setVisible(false);
		
		
		}
		if(e.getSource() == absEzButton)
		{
			Sudoku sudoku = new Sudoku("absolute easy");
			new SudokuFrame(sudoku, autoCheck, isClassic);
			frame.setVisible(false);
		}
		if(e.getSource() == ezButton)
		{
			Sudoku sudoku = new Sudoku("easy");
			new SudokuFrame(sudoku, autoCheck, isClassic);
			frame.setVisible(false);
		}
		if(e.getSource() == medButton)
		{
			Sudoku sudoku = new Sudoku("medium");
			new SudokuFrame(sudoku, autoCheck, isClassic);
			frame.setVisible(false);
		}
	
		if(e.getSource() == hardButton)
		{
			Sudoku sudoku = new Sudoku("hard");
			new SudokuFrame(sudoku, autoCheck, isClassic);
			frame.setVisible(false);
		}
		if(e.getSource() == expertButton)
		{
			Sudoku sudoku = new Sudoku("expert");
			new SudokuFrame(sudoku, autoCheck, isClassic);
			frame.setVisible(false);
		}
		if(e.getSource() == godButton)
		{
			Sudoku sudoku = new Sudoku("god mode");
			new SudokuFrame(sudoku, autoCheck, isClassic);
			frame.setVisible(false);
		}
		
		if(e.getSource() == howButton) {
			
			for (int i = 0; i < 8; i++ )
				extraMenu[i].setForeground(bgColor);
			
			for (int i = 0; i < 6; i++ )
				labelGroup[i].setForeground(bgColor);

			for (int i = 0; i < 8; i++ )
				settingGroup[i].setForeground(bgColor);
			
			
			frame.getContentPane().removeAll();
			frame.repaint();
			JPanel howPanel = new JPanel(new GridLayout(11,1,0,10));
			 
			imageLabel.setIcon(bgImage);
			
			JLabel howfillerLabel = new JLabel();
			howfillerLabel.setText("   asd     ");
			howfillerLabel.setForeground(bgColor);
			howfillerLabel.setFont(font2);
			howfillerLabel.setVerticalAlignment(SwingConstants.CENTER);
			howfillerLabel.setVisible(true);
			howfillerLabel.setOpaque(false);
			howPanel.add(howfillerLabel);
				 
			JLabel howfiller2Label = new JLabel();
			howfiller2Label.setText("   asd     ");
			howfiller2Label.setForeground(bgColor);
			howfiller2Label.setFont(font2);
			howfiller2Label.setVerticalAlignment(SwingConstants.CENTER);
			howfiller2Label.setVisible(true);
			howfiller2Label.setOpaque(false);
			howPanel.add(howfiller2Label);
					 
			JLabel howfiller3Label = new JLabel();
			howfiller3Label.setText("asd        ");
			howfiller3Label.setForeground(bgColor);
			howfiller3Label.setFont(font2);
			howfiller3Label.setVerticalAlignment(SwingConstants.CENTER);
			howfiller3Label.setVisible(true);
			howfiller3Label.setOpaque(false);
			howPanel.add(howfiller3Label);
			
			JLabel howfiller4Label = new JLabel();
			howfiller4Label.setText("asd        ");
			howfiller4Label.setForeground(bgColor);
			howfiller4Label.setFont(font2);
			howfiller4Label.setVerticalAlignment(SwingConstants.CENTER);
			howfiller4Label.setVisible(true);
			howfiller4Label.setOpaque(false);
			howPanel.add(howfiller4Label);
				 
			JLabel how1Label = new JLabel();
			how1Label.setText("          The goal of sudoku is simple:");
			how1Label.setForeground(textColor_linen);
			how1Label.setFont(font);
			how1Label.setVerticalAlignment(SwingConstants.BOTTOM);
			how1Label.setVisible(true);
			how1Label.setOpaque(false);
			howPanel.add(how1Label);
		
			JLabel how2Label = new JLabel();
			how2Label.setText("          You should fill in the numbers 1-9 exactly once");
			how2Label.setForeground(textColor_linen);
			how2Label.setFont(font);
			how2Label.setVerticalAlignment(SwingConstants.CENTER);
			how2Label.setVisible(true);
			how2Label.setOpaque(false);
			howPanel.add(how2Label);
	
			JLabel how3Label = new JLabel();
			how3Label.setText("          in every row, column, and 3x3 region.");
			how3Label.setForeground(textColor_linen);
			how3Label.setFont(font);
			how3Label.setVerticalAlignment(SwingConstants.NORTH);
			how3Label.setVisible(true);
			how3Label.setOpaque(false);
			howPanel.add(how3Label);
	
			JLabel how4Label = new JLabel();
			how4Label.setText("          You can enter \"0\" to a cell to delete numbers.");
			how4Label.setForeground(Color.YELLOW.darker());
			how4Label.setFont(font);
			how4Label.setVerticalAlignment(SwingConstants.NORTH);
			how4Label.setVisible(true);
			how4Label.setOpaque(false);
			howPanel.add(how4Label);
		 
			JButton backButton = new JButton();
			backButton.setText("BACK TO MENU");
			backButton.setForeground(textColor_linen);
			backButton.setFont(font);
			backButton.setOpaque(false);
			backButton.setContentAreaFilled(false);
			backButton.setBorderPainted(false);
			backButton.setFocusPainted(false);
			backButton.setHorizontalAlignment(SwingConstants.RIGHT);
			backButton.setVisible(true);
			howPanel.add(backButton);
			howPanel.setOpaque(false);
			backButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					
					frame.getContentPane().removeAll();
					frame.repaint();
					validate();
					setFrame();
				}
			});
			frame.add(howPanel);	
			frame.pack();
			frame.add(imageLabel);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(798, 781);
			frame.setSize(799, 782);
			frame.setLocation(240, 135);
			frame.setMinimumSize(new Dimension(300,300));
			frame.setResizable(false);	
			validate();
		}
			
		if(e.getSource() == settingsButton)
		{
			playButtonClicked = false;
			newGameClicked = false;
			
			for (int i = 0; i < 8; i++ )
				extraMenu[i].setForeground(bgColor);
			
			for (int i = 0; i < 6; i++ )
				if(i>1)
					labelGroup[i].setForeground(textColor_linen);
				else
					labelGroup[i].setForeground(bgColor);
			
			if (autoCheck)
			{
				option3.setForeground(trueColor);
				option4.setForeground(falseColor);
			}
			else {
				option4.setForeground(trueColor);
				option3.setForeground(falseColor);
			}
			if (isClassic) 
			{
				option5.setForeground(trueColor);
				option6.setForeground(falseColor);
			}
			else 
			{
				option6.setForeground(trueColor);
				option5.setForeground(falseColor);
			}
		}
		
		if(e.getSource() == option3)
		{
			option3.setForeground(trueColor); 
			option4.setForeground(falseColor);
			autoCheck = true;
			
		}
		if(e.getSource() == option4)
		{
			option4.setForeground(trueColor);
			option3.setForeground(falseColor);
			autoCheck = false;
		}
		
		if(e.getSource() == option5)
		{
			option5.setForeground(trueColor); 
			option6.setForeground(falseColor);
			isClassic = true;
			
		}
		if(e.getSource() == option6)
		{
			option6.setForeground(trueColor);
			option5.setForeground(falseColor);
			isClassic = false;
		}
		
		if(e.getSource() == creditsButton)
		{
			for (int i = 0; i < 8; i++ )
				extraMenu[i].setForeground(bgColor);
			
			for (int i = 0; i < 6; i++ )
				labelGroup[i].setForeground(bgColor);

			for (int i = 0; i < 8; i++ )
				settingGroup[i].setForeground(bgColor);		
			
			frame.getContentPane().removeAll();
			frame.repaint();
			
			frame.getContentPane().removeAll();
			frame.repaint();
			JPanel creditsPanel = new JPanel(new GridLayout(11,1,0,10));
			
			imageLabel.setIcon(bgImage);
			
			JLabel creditsFillerLabel = new JLabel();
			creditsFillerLabel.setText("   asd     ");
			creditsFillerLabel.setForeground(bgColor);
			creditsFillerLabel.setFont(font2);
			creditsFillerLabel.setVerticalAlignment(SwingConstants.CENTER);
			creditsFillerLabel.setVisible(true);
			creditsFillerLabel.setOpaque(false);
			creditsPanel.add(creditsFillerLabel);
 
			JLabel creditsFiller2Label = new JLabel();
			creditsFiller2Label.setText("   asd     ");
			creditsFiller2Label.setForeground(bgColor);
			creditsFiller2Label.setFont(font2);
			creditsFiller2Label.setVerticalAlignment(SwingConstants.CENTER);
			creditsFiller2Label.setVisible(true);
			creditsFiller2Label.setOpaque(false);
			creditsPanel.add(creditsFiller2Label);
	 
			JLabel creditsFiller3Label = new JLabel();
			creditsFiller3Label.setText("asd        ");
			creditsFiller3Label.setForeground(bgColor);
			creditsFiller3Label.setFont(font2);
			creditsFiller3Label.setVerticalAlignment(SwingConstants.CENTER);
			creditsFiller3Label.setVisible(true);
			creditsFiller3Label.setOpaque(false);
			creditsPanel.add(creditsFiller3Label);

			JLabel creditsFiller4Label = new JLabel();
			creditsFiller4Label.setText("asd        ");
			creditsFiller4Label.setForeground(bgColor);
			creditsFiller4Label.setFont(font2);
			creditsFiller4Label.setVerticalAlignment(SwingConstants.CENTER);
			creditsFiller4Label.setVisible(true);
			creditsFiller4Label.setOpaque(false);
			creditsPanel.add(creditsFiller4Label);
		 
			JLabel createdby = new JLabel();
			createdby.setText("            Created By");
			createdby.setForeground(textColor_linen);
			createdby.setFont(font);
			createdby.setOpaque(false);
			createdby.setHorizontalAlignment(SwingConstants.CENTER);
			createdby.setVerticalAlignment(SwingConstants.BOTTOM);
			createdby.setVisible(true);
			creditsPanel.add(createdby);
			creditsPanel.setOpaque(false);
		 
			JLabel creditsEmir = new JLabel();
			creditsEmir.setText("             Emir YILMAZ");
			creditsEmir.setForeground(textColor_linen);
			creditsEmir.setFont(font);
		 	creditsEmir.setOpaque(false);
		 	creditsEmir.setHorizontalAlignment(SwingConstants.CENTER);
		 	creditsEmir.setVerticalAlignment(SwingConstants.CENTER);
		 	creditsEmir.setVisible(true);
		 	creditsPanel.add(creditsEmir);
		 	creditsPanel.setOpaque(false);
		 
		 	JLabel creditsKemal = new JLabel();
		 	creditsKemal.setText("              KemalCan GÜNER");
		 	creditsKemal.setForeground(textColor_linen);
		 	creditsKemal.setFont(font);
		 	creditsKemal.setOpaque(false);
		 	creditsKemal.setHorizontalAlignment(SwingConstants.CENTER);
		 	creditsKemal.setVerticalAlignment(SwingConstants.TOP);
		 	creditsKemal.setVisible(true);
		 	creditsPanel.add(creditsKemal);
		 	creditsPanel.setOpaque(false);
		 
		 	JButton backButton = new JButton();
		 	backButton.setText("                                               BACK TO MENU");
		 	backButton.setForeground(textColor_linen);
		 	backButton.setFont(font);
		 	backButton.setOpaque(false);
		 	backButton.setContentAreaFilled(false);
		 	backButton.setBorderPainted(false);
		 	backButton.setFocusPainted(false);
		 	backButton.setHorizontalAlignment(SwingConstants.CENTER);
		 	backButton.setVisible(true);
		
		 	creditsPanel.add(backButton);
		 	creditsPanel.setOpaque(false);
			backButton.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					frame.getContentPane().removeAll();
					frame.repaint();
					validate();
					setFrame();
				}
			});
			frame.add(creditsPanel);	
			frame.pack();
			frame.add(imageLabel);
			frame.pack();
			frame.setSize(798, 781);
			frame.setSize(799, 782);
			frame.setLocation(240, 135);
			frame.setMinimumSize(new Dimension(300,300));
			frame.setResizable(false);
			validate();
		}
		
		if (e.getSource() == exitButton) {
			int sure = JOptionPane.showConfirmDialog(difficultyLabel2, "Do you really want to exit?", "Sure?", 0, 3);
			if(sure == 0) 
			{
				frame.dispose();
				System.exit(0);
			}
		}	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == absEzButton || e.getSource() == ezButton || 
		   e.getSource() == medButton || e.getSource() == hardButton || 
		   e.getSource() == expertButton || e.getSource() == godButton) 
			if(newGameClicked)
				((Component) e.getSource()).setForeground(hlColor);
		
		if(e.getSource() == playButton || e.getSource() == howButton || 
		   e.getSource() == settingsButton || e.getSource() == creditsButton || e.getSource() == exitButton)
			((Component) e.getSource()).setForeground(hlColor);
			
		if(e.getSource() == newGameButton || e.getSource() == loadGameButton )
			if(playButtonClicked)
			((Component) e.getSource()).setForeground(hlColor);
		
		if(e.getSource() == playButton)
		{
			newGameClicked = false;
			
			for (int i = 0; i < 8; i++ )
				extraMenu[i].setForeground(bgColor);
			
			for (int i = 0; i < 6; i++ )
				labelGroup[i].setForeground(bgColor);

			for (int i = 0; i < 8; i++ )
				settingGroup[i].setForeground(bgColor);
			loadGameButton.setEnabled(true);
			loadGameButton.setForeground(bgColor);
		}
		
		if(e.getSource() == howButton)
		{
			newGameClicked = false;
			playButtonClicked = false;
			for (int i = 0; i < 8; i++ )
				extraMenu[i].setForeground(bgColor);
			
			for (int i = 0; i < 6; i++ )
				labelGroup[i].setForeground(bgColor);

			for (int i = 0; i < 8; i++ )
				settingGroup[i].setForeground(bgColor);
			loadGameButton.setEnabled(true);
			loadGameButton.setForeground(bgColor);
		}
		
		if(e.getSource() == settingsButton)
		{
			newGameClicked = false;
			playButtonClicked = false;
			for (int i = 0; i < 8; i++ )
				extraMenu[i].setForeground(bgColor);
			
			for (int i = 0; i < 6; i++ )
				labelGroup[i].setForeground(bgColor);

			for (int i = 0; i < 8; i++ )
				settingGroup[i].setForeground(bgColor);
			loadGameButton.setEnabled(true);
			loadGameButton.setForeground(bgColor);
		}
		
		if(e.getSource() == creditsButton)
		{
			newGameClicked = false;
			playButtonClicked = false;
			for (int i = 0; i < 8; i++ )
				extraMenu[i].setForeground(bgColor);
			
			for (int i = 0; i < 6; i++ )
				labelGroup[i].setForeground(bgColor);

			for (int i = 0; i < 8; i++ )
				settingGroup[i].setForeground(bgColor);
			loadGameButton.setEnabled(true);
			loadGameButton.setForeground(bgColor);
		}
		
		if(e.getSource() == exitButton)
		{
			newGameClicked = false;
			playButtonClicked = false;
			for (int i = 0; i < 8; i++ )
				extraMenu[i].setForeground(bgColor);
			
			for (int i = 0; i < 6; i++ )
				labelGroup[i].setForeground(bgColor);

			for (int i = 0; i < 8; i++ )
				settingGroup[i].setForeground(bgColor);
			loadGameButton.setEnabled(true);
			loadGameButton.setForeground(bgColor);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == absEzButton || e.getSource() == ezButton || e.getSource() == medButton || e.getSource() == hardButton || e.getSource() == expertButton || e.getSource() == godButton) {
		if(newGameClicked)
			((Component) e.getSource()).setForeground(textColor_linen);
		}
		if(e.getSource() == playButton || e.getSource() == howButton || e.getSource() == settingsButton || e.getSource() == creditsButton || e.getSource() == exitButton)
			((Component) e.getSource()).setForeground(textColor_linen);
		if(e.getSource() == playButton && !playButtonClicked ) {
			loadGameButton.setEnabled(true);
			loadGameButton.setForeground(bgColor);}
		if(e.getSource() == newGameButton || e.getSource() == loadGameButton )
			if(playButtonClicked)
			((Component) e.getSource()).setForeground(textColor_linen);	
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
}