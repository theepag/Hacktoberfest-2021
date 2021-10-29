package homeinverntory;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.*;

import java.awt.print.*;
import java.awt.event.WindowEvent;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeInventory extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JToolBar inventoryToolBar;
	private JButton newButton;
	private JButton deleteButton;
	private JButton saveButton;
	private JButton prevButton;
	private JButton nextButton;
	private JButton printButton;
	private JButton exitButton;
	private JComboBox locationComboBox;
	private JTextField itemTextField;
	private JCheckBox markedCheckBox;
	private JTextField serialTextField;
	private JTextField priceTextField;
	private JDateChooser datePurchasedDateChooser;
	private JTextField storeWebsiteTextField;
	private JTextField noteTextField;
	static JTextArea photoTextArea;
	private PhotoPanel photoPanel;
	static InventoryItem[] myInventory;
	static int currentEntry;
	static final int maximumEntries = 300;
	static int numberEntries;
	static final int entriesPerPage = 2;
	static int lastPage;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					HomeInventory frame = new HomeInventory();
					frame.setTitle("Home Inventory  Manager");
					frame.setVisible(true);
					
					
//					InventoryItem myItem;
//					myItem = new InventoryItem();
					
					
					myInventory = new InventoryItem[maximumEntries];
					
					
					
//					try{
//						
//											
//						currentEntry=1;
//						frame.showEntry(currentEntry);
//						
//					}catch(Exception e)
//					{
//						numberEntries = 0;
//						currentEntry = 0;
//					}
					
					if(numberEntries == 0)
					{
						frame.newButton.setEnabled(false);
						frame.deleteButton.setEnabled(false);
						frame.nextButton.setEnabled(false);
						frame.prevButton.setEnabled(false);
						frame.printButton.setEnabled(false);
					}
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		

	}
	
	public HomeInventory()
	{
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250,130,1014,597);
		setResizable(false);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setLayout(null);
		
		Dimension bSize = new Dimension(105,75);
		
		inventoryToolBar = new JToolBar();
		inventoryToolBar.setFloatable(false);
		inventoryToolBar.setBounds(1,0,100,597);
		inventoryToolBar.setBackground(Color.BLUE);
		inventoryToolBar.setOrientation(SwingConstants.VERTICAL);
		inventoryToolBar.addSeparator();
		contentPane.add(inventoryToolBar);
		
		
		newButton = new JButton(new ImageIcon("new.png"));
		newButton.setText("New");
		newButton.setFont(new Font("Tahoma",Font.BOLD,15));
		sizeButton(newButton,bSize);
		newButton.setToolTipText("Add New Item");
		newButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	    newButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    newButton.setBorder(BorderFactory.createEmptyBorder());
	    newButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    inventoryToolBar.add(newButton);
	    
	    newButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newButtonActionPerformed(e);
				
			}

			private void newButtonActionPerformed(ActionEvent e) {
				
				checkSave();
				blankValues();
				
			}
	    	
	    });
		
		deleteButton = new JButton(new ImageIcon("delete.jpg"));
		deleteButton.setText("Delete");
		deleteButton.setFont(new Font("Tahoma",Font.BOLD,15));
		sizeButton(deleteButton,bSize);
		deleteButton.setToolTipText("Delete Current Item");
		deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	    deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    deleteButton.setBorder(BorderFactory.createEmptyBorder());
	    deleteButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    inventoryToolBar.add(deleteButton);
	    
	    deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButtonActionPerformed(e);
				
			}

			private void deleteButtonActionPerformed(ActionEvent e) {
				
				if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?","Delete Inventory Item",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
					return;
				deleteEntry(currentEntry);
				
				if(numberEntries == 0)
				{
					currentEntry = 0;
					blankValues();
				}
				else
				{
					currentEntry--;
					if(currentEntry == 0)
					{
						currentEntry =1;
						showEntry(currentEntry);
					}
				}
					
				
			}
	    	
	    });
	    
		saveButton = new JButton(new ImageIcon("save.png"));
		saveButton.setText("save");
		saveButton.setFont(new Font("Tahoma",Font.BOLD,15));
		sizeButton(saveButton,bSize);
		saveButton.setToolTipText("Save Current Item");
		saveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	    saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    saveButton.setBorder(BorderFactory.createEmptyBorder());
	    saveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    inventoryToolBar.add(saveButton);
	    
	    saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveButtonActionPerformed(e);
				
			}

			private void saveButtonActionPerformed(ActionEvent e) {
			
				itemTextField.setText(itemTextField.getText().trim());
				if(itemTextField.getText().equals(""))
				{
					JOptionPane.showConfirmDialog(null, "Must have item decription.", "Error", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
					itemTextField.requestFocus();
					return;
				}
				
				if(newButton.isEnabled())
				{
					deleteEntry(currentEntry);
				}
				
				String s = itemTextField.getText();
				itemTextField.setText(s.substring(0,1).toUpperCase()+s.substring(1));
				numberEntries++;
				currentEntry = 1;
				if(numberEntries != 1)
				{
					do {
						if(itemTextField.getText().compareTo(myInventory[currentEntry-1].description)<0)
							break;
						currentEntry++;
					}
					while(currentEntry<numberEntries);
				}
				
				if(currentEntry != numberEntries)
				{
					for(int i=numberEntries;i>=currentEntry+1;i--)
					{
						myInventory[i-1] = myInventory[i-2];
						myInventory[i-2] = new InventoryItem();
					}
				}
				myInventory[currentEntry-1] = new InventoryItem();
				myInventory[currentEntry-1].description = itemTextField.getText();
				myInventory[currentEntry-1].location = locationComboBox.getSelectedItem().toString();
				myInventory[currentEntry-1].marked = markedCheckBox.isSelected();
				myInventory[currentEntry-1].serialNumber = serialTextField.getText();
				myInventory[currentEntry-1].purchasedPrice = priceTextField.getText();
				myInventory[currentEntry-1].purchasedDate = dateToString(datePurchasedDateChooser.getDate());
				myInventory[currentEntry-1].storeWebsite = storeWebsiteTextField.getText();
				myInventory[currentEntry-1].note = noteTextField.getText();
				myInventory[currentEntry-1].photoFile = photoTextArea.getText();
				showEntry(currentEntry);
				
				if(numberEntries<maximumEntries)
					newButton.setEnabled(true);
				else
					newButton.setEnabled(false);
				deleteButton.setEnabled(true);
				printButton.setEnabled(true);
				
			}
	    	
	    });
	    
	    inventoryToolBar.addSeparator();
	    
		prevButton = new JButton(new ImageIcon("prev.png"));
		prevButton.setText("Previous");
		prevButton.setFont(new Font("Tahoma",Font.BOLD,15));
		sizeButton(prevButton,bSize);
		prevButton.setToolTipText("Display Previous Item");
		prevButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    prevButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	    prevButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    prevButton.setBorder(BorderFactory.createEmptyBorder());
	    prevButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    inventoryToolBar.add(prevButton);
	    
	    prevButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				prevButtonActionPerformed(e);
				
			}

			private void prevButtonActionPerformed(ActionEvent e) {
				
				checkSave();
				currentEntry--;
				
				showEntry(currentEntry);
				
			}
	    	
	    });
	    
		nextButton = new JButton(new ImageIcon("next.png"));
		nextButton.setText("Next");
		nextButton.setFont(new Font("Tahoma",Font.BOLD,15));
		sizeButton(nextButton,bSize);
		nextButton.setToolTipText("Display Next Item");
		nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    nextButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	    nextButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    nextButton.setBorder(BorderFactory.createEmptyBorder());
	    nextButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    inventoryToolBar.add(nextButton);
	    
	    nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nextButtonActionPerformed(e);
				
			}

			private void nextButtonActionPerformed(ActionEvent e) {
				checkSave();
				currentEntry++;
				showEntry(currentEntry);
				
				
			}
	    	
	    });
	    
	    inventoryToolBar.addSeparator();
	    
		printButton = new JButton(new ImageIcon("print.png"));
		printButton.setText("Print");
		printButton.setFont(new Font("Tahoma",Font.BOLD,15));
		sizeButton(printButton,bSize);
		printButton.setToolTipText("Print All Inventory Item");
		printButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    printButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	    printButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    printButton.setBorder(BorderFactory.createEmptyBorder());
	    printButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    inventoryToolBar.add(printButton);
	    
	    printButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				printButtonActionPerformed(e);
				
			}

			private void printButtonActionPerformed(ActionEvent e) {
				
				lastPage = (int) (1+(numberEntries)/entriesPerPage);
				
				PrinterJob inventoryPrinterJob = PrinterJob.getPrinterJob();
				inventoryPrinterJob.setPrintable(new InventoryDocument());
				if(inventoryPrinterJob.printDialog())
				{
					try {
						inventoryPrinterJob.print();
					}
					catch(PrinterException ex)
					{
						JOptionPane.showConfirmDialog(null, ex.getMessage(),"Print Error", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
					}
				}
	    
				
			}
	    	
	    });
	    
	    
		exitButton = new JButton();
		exitButton.setText("Exit");
		exitButton.setFont(new Font("Tahoma",Font.BOLD,15));
		sizeButton(exitButton,bSize);
		exitButton.setToolTipText("Exit Program");
		exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    exitButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	    exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    exitButton.setBorder(BorderFactory.createEmptyBorder());
	    exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    inventoryToolBar.add(exitButton);
	    
	    exitButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				exitButtonActionPerformed(e);
				
			}

			private void exitButtonActionPerformed(ActionEvent e) {
				exitForm(null);
				
			}
	    	
	    });
	    
	    
	    JLabel itemLabel = new JLabel();
	    itemLabel.setText("Inventory Item");
	    itemLabel.setBounds(110,15,150,35);
	    itemLabel.setFont(new Font("Tahoma",Font.BOLD,15));
	    itemLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    contentPane.add(itemLabel);
	    
	    itemTextField = new JTextField();
	    itemTextField.setBounds(280,15,670,35);
	    itemTextField.setFont(new Font("Tahoma",Font.PLAIN,18));
	    contentPane.add(itemTextField);
	    
	    JLabel locationLabel = new JLabel();
	    locationLabel.setText("Location");
	    locationLabel.setBounds(110,70,150,35);
	    locationLabel.setFont(new Font("Tahoma",Font.BOLD,15));
	    locationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    contentPane.add(locationLabel);
	    
	    
	    locationComboBox = new JComboBox();
	    locationComboBox.setBounds(280,70,450,35);
	    locationComboBox.setFont(new Font("Tahoma",Font.PLAIN,18));
	    locationComboBox.setEditable(true);
	    contentPane.add(locationComboBox);
	    
	    locationComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				locationComboBoxActionPerformed(e);
				
			}

			private void locationComboBoxActionPerformed(ActionEvent e) {
				if(locationComboBox.getItemCount()!=0)
				{
					for(int i=0;i<locationComboBox.getItemCount();i++)
					{
						if(locationComboBox.getSelectedItem().toString().toLowerCase().equals(locationComboBox.getItemAt(i).toString().toLowerCase()))
						{
							serialTextField.requestFocus();
							return;
						}
					}
				}
				locationComboBox.addItem(locationComboBox.getSelectedItem());
				serialTextField.requestFocus();
				
			}
	    	
	    });
	    
	    markedCheckBox = new JCheckBox();
	    markedCheckBox.setText("Marked?");
	    markedCheckBox.setFont(new Font("Tahoma",Font.PLAIN,18));
	    markedCheckBox.setBackground(Color.LIGHT_GRAY);
	    markedCheckBox.setBounds(780,70,150,35);
	    contentPane.add(markedCheckBox);
	    
	    JLabel serialLabel = new JLabel();
	    serialLabel.setText("Serial Number");
	    serialLabel.setBounds(110,125,150,35);
	    serialLabel.setFont(new Font("Tahoma",Font.BOLD,15));
	    serialLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    contentPane.add(serialLabel);
	    
	    serialTextField = new JTextField();
	    serialTextField.setBounds(280,125,450,35);
	    serialTextField.setFont(new Font("Tahoma",Font.BOLD,15));
	    contentPane.add(serialTextField);
	    
	    JLabel priceLabel = new JLabel();
	    priceLabel.setText("Purchase Price");
	    priceLabel.setBounds(110,180,150,35);
	    priceLabel.setFont(new Font("Tahoma",Font.BOLD,15));
	    priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    contentPane.add(priceLabel);
	    
	    priceTextField = new JTextField();
	    priceTextField.setBounds(280,180,300,35);
	    priceTextField.setFont(new Font("Tahoma",Font.BOLD,15));
	    contentPane.add(priceTextField);
	    
	    JLabel datePurchaseLabel = new JLabel();
	    datePurchaseLabel.setText("Date Purchased");
	    datePurchaseLabel.setBounds(530,180,200,35);
	    datePurchaseLabel.setFont(new Font("Tahoma",Font.BOLD,15));
	    datePurchaseLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    contentPane.add(datePurchaseLabel);
	    
	    datePurchasedDateChooser = new JDateChooser();
	    datePurchasedDateChooser.setBounds(750,180,200,35);
	    datePurchasedDateChooser.setFont(new Font("Tahoma",Font.BOLD,15));
	    datePurchasedDateChooser.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    contentPane.add(datePurchasedDateChooser);
	    
	    JLabel storeWebsiteLabel = new JLabel();
	    storeWebsiteLabel.setText("Store/Website");
	    storeWebsiteLabel.setBounds(110,235,150,35);
	    storeWebsiteLabel.setFont(new Font("Tahoma",Font.BOLD,15));
	    storeWebsiteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    contentPane.add(storeWebsiteLabel);
	    
	    storeWebsiteTextField = new JTextField();
	    storeWebsiteTextField.setBounds(280,235,670,35);
	    storeWebsiteTextField.setFont(new Font("Tahoma",Font.PLAIN,18));
	    contentPane.add(storeWebsiteTextField);
	    
	    JLabel noteLabel = new JLabel();
	    noteLabel.setText("Note");
	    noteLabel.setBounds(110,290,150,35);
	    noteLabel.setFont(new Font("Tahoma",Font.BOLD,15));
	    noteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    contentPane.add(noteLabel);
	    
	    noteTextField = new JTextField();
	    noteTextField.setBounds(280,290,670,35);
	    noteTextField.setFont(new Font("Tahoma",Font.PLAIN,18));
	    contentPane.add(noteTextField);
	    
	    JLabel photoLabel = new JLabel();
	    photoLabel.setText("Photo");
	    photoLabel.setBounds(110,345,150,35);
	    photoLabel.setFont(new Font("Tahoma",Font.BOLD,15));
	    photoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    contentPane.add(photoLabel);
	    
	    photoTextArea = new JTextArea();
	    photoTextArea.setBounds(280,345,600,35);
	    photoTextArea.setFont(new Font("Tahoma",Font.PLAIN,18));
	    photoTextArea.setEditable(false);
	    photoTextArea.setLineWrap(true);
	    photoTextArea.setWrapStyleWord(true);
	    photoTextArea.setBackground(new Color(255,255,192));
	    photoTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    contentPane.add(photoTextArea);
	    
	    JButton photoButton = new JButton();
	    photoButton.setText("...");
	    photoButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    photoButton.setBounds(890,345,60,35);
	    contentPane.add(photoButton);
	    
	    photoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				photoButtonActionPerformed(e);
				
			}

			private void photoButtonActionPerformed(ActionEvent e) {
				JFileChooser openChooser = new JFileChooser();
				openChooser.setDialogType(JFileChooser.OPEN_DIALOG);
				openChooser.setDialogTitle("Open Photo File");
				openChooser.addChoosableFileFilter(new FileNameExtensionFilter("Photo Files","jpg"));
				if(openChooser.showOpenDialog(new JFileChooser()) == JFileChooser.APPROVE_OPTION)
					showPhoto(openChooser.getSelectedFile().toString());
				
			}
	    	
	    });
	    
	    JPanel searchPanel = new JPanel();
		searchPanel.setBorder(BorderFactory.createTitledBorder("Item Search"));
		searchPanel.setFont(new Font("Tahoma",Font.BOLD,15));
		searchPanel.setBounds(150,400,350,145);
		contentPane.add(searchPanel);
		
		JButton[] searchButton = new JButton[26];
		
		int x=0,y=0;
		
		for (int i = 0; i < 26; i++)
		{
			searchButton[i] = new JButton();
			searchButton[i].setText(String.valueOf((char) (65 + i)));
			searchButton[i].setFont(new Font("Tahoma", Font.BOLD, 12));
			searchButton[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			searchButton[i].setMargin(new Insets(-10, -10, -10, -10));
			sizeButton(searchButton[i], new Dimension(37, 27));
			searchButton[i].setBackground(Color.YELLOW);
			searchPanel.add(searchButton[i]);
			searchButton[i].addActionListener(new ActionListener ()
			{
				public void actionPerformed(ActionEvent e)
				{
					searchButtonActionPerformed(e);
				}
	
				private void searchButtonActionPerformed(ActionEvent e) {
					int i;
					if(numberEntries == 0)
					{
						return;
					}
					String letterClicked = e.getActionCommand();
					i=0;
					do {
						if(myInventory[i].description.substring(0,1).equals(letterClicked))
						{
							currentEntry = i+1;
							showEntry(currentEntry);
							return;
						}
						i++;
					}
					while(i<numberEntries);
					JOptionPane.showConfirmDialog(null, "No "+letterClicked+" inventory items","None Found",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
				}
			});
			x++;
			
			if (x % 8 == 0)
			{
				x = 0;
				y++;
			}
		}
			
		photoPanel = new PhotoPanel();
		photoPanel.setBounds(680,400,270,145);
		contentPane.add(photoPanel);
		
		
		
		itemTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				itemTextFieldActionPerformed(e);
					
			}

			private void itemTextFieldActionPerformed(ActionEvent e) {
				locationComboBox.requestFocus();
					
			}
		    	
		 });
		
		locationComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				locationComboBoxActionPerformed(e);
					
			}

			private void locationComboBoxActionPerformed(ActionEvent e) {
				serialTextField.requestFocus();
					
			}
		    	
		 });
		
		serialTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				serialTextFieldActionPerformed(e);
				
			}

			private void serialTextFieldActionPerformed(ActionEvent e) {
				priceTextField.requestFocus();
				
			}
			
		});
		
		priceTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				priceTextFieldActionPerformed(e);
				
			}

			private void priceTextFieldActionPerformed(ActionEvent e) {
				datePurchasedDateChooser.requestFocus();
				
			}
			
		});
		
		datePurchasedDateChooser.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				datePurchasedDateChooserpropertyChange(evt);
				
			}

			private void datePurchasedDateChooserpropertyChange(PropertyChangeEvent evt) {
				storeWebsiteTextField.requestFocus();
				
			}
			
		});
		
		storeWebsiteTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				storeWebsiteTextFieldActionPerformed(e);
				
			}

			private void storeWebsiteTextFieldActionPerformed(ActionEvent e) {
				noteTextField.requestFocus();
				
			}
			
		});
		
		noteTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				noteTextFieldActionPerformed(e);
				
			}

			private void noteTextFieldActionPerformed(ActionEvent e) {
				photoButton.requestFocus();
				
			}
			
		});
			
	}
	
	private void sizeButton(JButton b, Dimension d)
	{
		b.setPreferredSize(d);
		b.setMinimumSize(d);
		b.setMaximumSize(d);
	}
	
	private void showEntry(int j)
	{
		itemTextField.setText(myInventory[j-1].description);
		locationComboBox.setSelectedItem(myInventory[j-1].location);
		markedCheckBox.setSelected(myInventory[j-1].marked);
		serialTextField.setText(myInventory[j-1].serialNumber);
		priceTextField.setText(myInventory[j-1].purchasedPrice);
		datePurchasedDateChooser.setDate(stringToDate(myInventory[j-1].purchasedDate));
		storeWebsiteTextField.setText(myInventory[j-1].storeWebsite);
		noteTextField.setText(myInventory[j-1].note);
		photoTextArea.setText(myInventory[j-1].photoFile);
		
		nextButton.setEnabled(true);
		prevButton.setEnabled(true);
		if(j==1)
			prevButton.setEnabled(false);
		else
			nextButton.setEnabled(false);
		
		itemTextField.requestFocus();
		showPhoto(myInventory[j-1].photoFile);
	}
	
	private Date stringToDate(String s)
	{
		int m = Integer.valueOf(s.substring(0,2)).intValue()-1;
		int d = Integer.valueOf(s.substring(3,5)).intValue();
		int y = Integer.valueOf(s.substring(6)).intValue()-1900;
		return (new Date(y,m,d));
	}
	
	private String dateToString(Date dd)
	{
		String yString = String.valueOf(dd.getYear()+1900);
		int m = dd.getMonth()+1;
		String mString = new DecimalFormat("00").format(m);
		int d = dd.getDate();
		String dString = new DecimalFormat("00").format(d);
		
		return (mString+"/"+dString+"/"+yString);
	}

	private void showPhoto(String photoFile)
	{
		if(!photoFile.equals(""))
		{
			try {
				photoTextArea.setText(photoFile);
			}
			catch(Exception e)
			{
				photoTextArea.setText("");
			}
		}
		else
		{
			photoTextArea.setText("");
		}
		photoPanel.repaint();
	}
	
	private void exitForm(WindowEvent evt) {
		
		if(JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost.\n Are you sure you want to exit?","Exit Program",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
			return;
		System.exit(0);
	}
	
	private void blankValues()
	{
		newButton.setEnabled(false);
		deleteButton.setEnabled(false);
		saveButton.setEnabled(true);
		prevButton.setEnabled(false);
		nextButton.setEnabled(false);
		printButton.setEnabled(false);
		itemTextField.setText("");
		locationComboBox.setSelectedItem("");
		markedCheckBox.setSelected(false);
		serialTextField.setText("");
		priceTextField.setText("");
		datePurchasedDateChooser.setDate(new Date());
		storeWebsiteTextField.setText("");
		noteTextField.setText("");
		photoTextArea.setText("");
		photoPanel.repaint();
		itemTextField.requestFocus();
	}
	
	private void deleteEntry(int j)
	{
		if(j != numberEntries)
		{
			for(int i=j; i<numberEntries;i++)
			{
				myInventory[i-1] = new InventoryItem();
				myInventory[i-1] = myInventory[i];
			}
		}
		numberEntries--;
	}
	
	
	private void checkSave()
	{
		boolean edited = false;
		if(!myInventory[currentEntry-1].description.equals(itemTextField.getText()))
			edited = true;
		else if(!myInventory[currentEntry-1].location.equals(locationComboBox.getSelectedItem().toString()))
			edited = true;
		else if(myInventory[currentEntry-1].marked != markedCheckBox.isSelected())
			edited = true;
		else if(!myInventory[currentEntry-1].serialNumber.equals(serialTextField.getText()))
			edited = true;
		else if(!myInventory[currentEntry-1].purchasedPrice.equals(priceTextField.getText()))
			edited = true;
		else if(!myInventory[currentEntry-1].purchasedDate.equals(dateToString(datePurchasedDateChooser.getDate())))
			edited = true;
		else if(!myInventory[currentEntry-1].storeWebsite.equals(storeWebsiteTextField.getText()))
			edited = true;
		else if(!myInventory[currentEntry-1].note.equals(noteTextField.getText()))
			edited = true;
		else if(!myInventory[currentEntry-1].photoFile.equals(photoTextArea.getText()))
			edited = true;
		
		if(edited)
		{
			if(JOptionPane.showConfirmDialog(null, "You have edited this item. Do you want to save the changes?","Save Item", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)== JOptionPane.YES_OPTION)
				saveButton.doClick();
		}
		
	}
	
}
class PhotoPanel extends JPanel{
	public void paintComponent(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		super.paintComponent(g2D);
		
		g2D.setPaint(Color.BLACK);
		g2D.draw(new Rectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1));
		
		Image photoImage = new ImageIcon(HomeInventory.photoTextArea.getText()).getImage();
		int w = getWidth();
		int h = getHeight();
		
		double rWidth = (double) getWidth() / (double) photoImage.getWidth(null);
		double rHeight = (double) getHeight() / (double) photoImage.getHeight(null);
		
		if(rWidth > rHeight)
		{
			w = (int)(photoImage.getWidth(null)*rHeight);
		}
		else
		{
			h = (int) (photoImage.getWidth(null)*rWidth);
		}
		
		g2D.drawImage(photoImage,(int)(0.5*(getWidth()-w)),(int)(0.5*(getHeight()-h)),w,h,null);
		
		g2D.dispose();
	}
}

class InventoryDocument implements Printable{

	@Override
	public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
		
		Graphics2D g2D = (Graphics2D)g;
		if((pageIndex+1)>HomeInventory.lastPage)
		{
			return NO_SUCH_PAGE;
		}
		int i, iEnd;
		
		g2D.setFont(new Font("Tahoma",Font.BOLD,14));
		g2D.drawString("Home Inventory Items - Page "+String.valueOf(pageIndex+1),(int)pf.getImageableX()+250,(int)(pf.getImageableY()+25));
		
		int dy = (int)g2D.getFont().getStringBounds("S",g2D.getFontRenderContext()).getHeight();
		int y = (int) (pf.getImageableY()+4*dy);
		iEnd = HomeInventory.entriesPerPage*(pageIndex+1);
		if(iEnd>HomeInventory.numberEntries)
			iEnd = HomeInventory.numberEntries;
		for(i=0+HomeInventory.entriesPerPage*pageIndex;i<iEnd;i++)
		{
			Line2D.Double dividingLine = new Line2D.Double(pf.getImageableX(),y,pf.getImageableX()+pf.getImageableWidth(),y);
			g2D.draw(dividingLine);
			y += dy;
			
			g2D.setFont(new Font("Tahoma",Font.BOLD,12));
			g2D.drawString(HomeInventory.myInventory[i].description,(int)pf.getImageableX(),y);
			y += dy;

			g2D.setFont(new Font("Tahoma",Font.BOLD,12));
			g2D.drawString("Location: "+HomeInventory.myInventory[i].location,(int)(pf.getImageableX()+25),y);
			y += dy;
			
			if(HomeInventory.myInventory[i].marked)
				g2D.drawString("Item is marked with identifying information.",(int)(pf.getImageableX()+25),y);
			else
				g2D.drawString("Item is NOT marked ith identifying information.",(int)(pf.getImageableX()+25),y);
			y += dy;
			g2D.drawString("Serial Number: "+HomeInventory.myInventory[i].serialNumber,(int)(pf.getImageableX()+25),y);
			y += dy;
			g2D.drawString("Price: $"+HomeInventory.myInventory[i].purchasedPrice+", Purchased on: "+HomeInventory.myInventory[i].purchasedDate,(int)(pf.getImageableX()+25),y);
			y += dy;
			
			g2D.drawString("Purchased at: "+HomeInventory.myInventory[i].storeWebsite,(int)(pf.getImageableX()+25),y);
			y += dy;
			g2D.drawString("Note: "+HomeInventory.myInventory[i].note,(int)(pf.getImageableX()+25),y);
			y += dy;
			
			try {
				Image inventoryImage = new ImageIcon(HomeInventory.myInventory[i].photoFile).getImage();
				double ratio = (double) (inventoryImage.getWidth(null))/(double)inventoryImage.getHeight(null);
				g2D.drawImage(inventoryImage,(int)(pf.getImageableX()+25),y,(int)(100*ratio),100,null);
			}
			catch(Exception e)
			{
				
			}
			y += 2*dy+100;
		}
		return PAGE_EXISTS;
	}
	
}
