package com.wmu.churchlogger;
// This is our project. and it is cool

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class ChurchLoggerWindow extends JFrame{
	private static final long serialVersionUID = 1L;

	private JLabel member_label, money_label, bible_label, attendance_icon;
	private JLabel selected_label;
	private JPanel card_panel, navigation_panel, member_panel, program_panel;
	private CardLayout cardLayout;
	private JTable table;

	private DBAccess database;
	private JPanel attendance_panel;
	private JButton btnAddRecord;
	private JScrollPane scrollPane_1;
	private JButton button_1;


	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public ChurchLoggerWindow(DBAccess database) {
		this.database = database;
		initialize();
	}

	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If they can't use nimbus...
		}
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.getContentPane().setBackground(SystemColor.text);
		this.setBounds(100, 100, 655, 505);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new CardLayout(0, 0));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		program_panel = new JPanel();
		program_panel.setBackground(SystemColor.text);
		this.getContentPane().add(program_panel, "name_7551650918463");

		navigation_panel = new JPanel();
		navigation_panel.setMaximumSize(new Dimension(20, 32767));
		navigation_panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		navigation_panel.setBackground(new Color(255, 255, 255));

		attendance_icon = new JLabel("");
		attendance_icon.setHorizontalAlignment(SwingConstants.RIGHT);
		attendance_icon.setBackground(SystemColor.text);
		attendance_icon.setOpaque(true);
		attendance_icon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				attendanceIconClicked();
			}
		});
		attendance_icon.setIcon(new ImageIcon(ChurchLoggerWindow.class.getResource("/res/attendance.png")));

		bible_label = new JLabel("");
		bible_label.setHorizontalAlignment(SwingConstants.RIGHT);
		bible_label.setBackground(SystemColor.text);
		bible_label.setOpaque(true);
		bible_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bibleIconClicked();
			}
		});
		bible_label.setIcon(new ImageIcon(ChurchLoggerWindow.class.getResource("/res/bible.png")));

		money_label = new JLabel("");
		money_label.setHorizontalAlignment(SwingConstants.RIGHT);
		money_label.setIcon(new ImageIcon(ChurchLoggerWindow.class.getResource("/res/money.png")));
		money_label.setBackground(SystemColor.text);
		money_label.setOpaque(true);
		money_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				moneyIconClicked();
			}
		});


		member_label = new JLabel("");
		member_label.setBorder(null);
		member_label.setHorizontalAlignment(SwingConstants.RIGHT);
		selected_label = member_label;
		member_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				memberIconClicked();
			}
		});
		member_label.setIcon(new ImageIcon(ChurchLoggerWindow.class.getResource("/res/member.png")));
		member_label.setBackground(SystemColor.text);
		GroupLayout gl_navigation_panel = new GroupLayout(navigation_panel);
		gl_navigation_panel.setHorizontalGroup(
			gl_navigation_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_navigation_panel.createSequentialGroup()
					.addContainerGap(22, Short.MAX_VALUE)
					.addGroup(gl_navigation_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(attendance_icon, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addComponent(bible_label, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addComponent(money_label, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addComponent(member_label, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
		);
		gl_navigation_panel.setVerticalGroup(
			gl_navigation_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_navigation_panel.createSequentialGroup()
					.addGap(66)
					.addComponent(member_label, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(attendance_icon, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(bible_label, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(money_label, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		navigation_panel.setLayout(gl_navigation_panel);

		card_panel = new JPanel();

		cardLayout = new CardLayout(0,0);
		card_panel.setLayout(cardLayout);

		member_panel = new JPanel();
		member_panel.setBorder(null);
		member_panel.setBackground(new Color(119, 136, 153));
		card_panel.add(member_panel, "member_panel");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("DejaVu Sans", Font.BOLD, 12));
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.getViewport().setBackground(Color.WHITE);

		JTextArea textArea = new JTextArea();
		textArea.setText("This is where any notes about the member will go.");
		textArea.setFont(new Font("Dialog", Font.PLAIN, 14));
		
				JButton button = new JButton("");
				button.setIcon(new ImageIcon(ChurchLoggerWindow.class.getResource("/res/plus.png")));
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						addMember();
					}


				});
				button.setFont(new Font("Dialog", Font.BOLD, 14));
		
		button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(ChurchLoggerWindow.class.getResource("/res/minus.png")));
		button_1.setFont(new Font("Dialog", Font.BOLD, 14));
		
		searchBox = new JTextField();
		searchBox.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search();
			}
		});
		GroupLayout gl_member_panel = new GroupLayout(member_panel);
		gl_member_panel.setHorizontalGroup(
			gl_member_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_member_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 396, Short.MAX_VALUE)
					.addComponent(searchBox, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnSearch)
					.addContainerGap())
				.addGroup(gl_member_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_member_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
						.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE))
					.addGap(6))
		);
		gl_member_panel.setVerticalGroup(
			gl_member_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_member_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_member_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_member_panel.createSequentialGroup()
							.addGroup(gl_member_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(button_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(8))
						.addGroup(gl_member_panel.createSequentialGroup()
							.addGroup(gl_member_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(searchBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearch))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
		);

		table = new JTable();
		table.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 14));
		table.setFont(new Font("Dialog", Font.BOLD, 12));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));


		// Fills member table with every member and information
		changeTableContents();
		

		scrollPane.setViewportView(table);
		member_panel.setLayout(gl_member_panel);
		
		attendance_panel = new JPanel();
		attendance_panel.setBackground(new Color(119, 136, 153));
		card_panel.add(attendance_panel, "attendance_panel");
		
		btnAddRecord = new JButton("Add Record");
		btnAddRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Add a new date
				addDate();
			}
		});
		btnAddRecord.setFont(new Font("Dialog", Font.BOLD, 14));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.BLACK);
		GroupLayout gl_attendance_panel = new GroupLayout(attendance_panel);
		gl_attendance_panel.setHorizontalGroup(
			gl_attendance_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_attendance_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_attendance_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
						.addComponent(btnAddRecord, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_attendance_panel.setVerticalGroup(
			gl_attendance_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_attendance_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(btnAddRecord)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		attendance_table = new JTable();
		scrollPane_1.setViewportView(attendance_table);
		attendance_panel.setLayout(gl_attendance_panel);
		program_panel.setLayout(new BoxLayout(program_panel, BoxLayout.X_AXIS));
		program_panel.add(navigation_panel);
		program_panel.add(card_panel);
		
		
 		verse_panel = new JPanel();
 		verse_panel.setBackground(new Color(119, 136, 153));
 		card_panel.add(verse_panel, "verse_panel");
 		
 		scrollPane_2 = new JScrollPane();
 		scrollPane_2.setBackground(Color.BLACK);
 		
 		button_2 = new JButton("Add Record");
 		button_2.setFont(new Font("Dialog", Font.BOLD, 14));
		GroupLayout gl_verse_panel = new GroupLayout(verse_panel);
 		gl_verse_panel.setHorizontalGroup(
 			gl_verse_panel.createParallelGroup(Alignment.LEADING)
 				.addGap(0, 619, Short.MAX_VALUE)
 				.addGroup(gl_verse_panel.createSequentialGroup()
 					.addGap(6)
 					.addGroup(gl_verse_panel.createParallelGroup(Alignment.LEADING)
 						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
 						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
 					.addContainerGap())
 		);
		gl_verse_panel.setVerticalGroup(
 			gl_verse_panel.createParallelGroup(Alignment.LEADING)
 				.addGap(0, 484, Short.MAX_VALUE)
 				.addGroup(gl_verse_panel.createSequentialGroup()
 					.addGap(6)
 					.addComponent(button_2)
 					.addPreferredGap(ComponentPlacement.RELATED)
 					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
					.addContainerGap())
 		);
 		
 		JTextArea verse_area = new JTextArea();
 		scrollPane_2.setViewportView(verse_area);
 		
 		/* Building the verse string for text area */
 		FileManager manager = new FileManager();
 		//String stuff = manager.readFileLineByLine("kjvdat.txt");
 		//verse_area.setText(stuff);
 		
 		verse_panel.setLayout(gl_verse_panel);

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File    ");
		mnFile.setFont(new Font("Dialog", Font.BOLD, 14));
		menuBar.add(mnFile);

		JMenu mnEdit = new JMenu("Edit    ");
		mnEdit.setFont(new Font("Dialog", Font.BOLD, 14));
		menuBar.add(mnEdit);

		JMenu mnHelp = new JMenu("Help    ");
		mnHelp.setFont(new Font("Dialog", Font.BOLD, 14));
		menuBar.add(mnHelp);
	}

	public void changeTableContents(){
		try {
			table.setModel(database.updateMemberTable());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.revalidate();
	}

	public String prevNoTab = "";
	private JPanel verse_panel;
	private JScrollPane scrollPane_2;
	private JButton button_2;
	private JTextField searchBox;
	private JTable attendance_table;
	
	public void memberIconClicked(){
		System.out.println("Member icon clicked");
		resetLabels(member_label, "member.png", "members_tab.png");
		//update the contents of the table
		changeTableContents();
		//show
		cardLayout.show(card_panel, "member_panel");
	}

	public void attendanceIconClicked(){
		System.out.println("Attendance Icon Clicked");
		resetLabels(attendance_icon, "attendance.png", "attendance_tab.png");
		cardLayout.show(card_panel, "attendance_panel");

	}

	public void moneyIconClicked(){
		System.out.println("Money icon clicked");
		resetLabels(money_label, "money.png", "money_tab.png");
	}

	public void bibleIconClicked(){
		System.out.println("Bible icon clicked");
		resetLabels(bible_label, "bible.png", "bible_tab.png");
		cardLayout.show(card_panel, "verse_panel");
	}

	public void resetLabels(JLabel new_selected_label, String noTab, String tab){
		//selected_label.setBorder(null);
		selected_label.setIcon(new ImageIcon(ChurchLoggerWindow.class.getResource("/res/" + prevNoTab)));
		selected_label.setBackground(Color.white);
		prevNoTab = noTab;
		selected_label = new_selected_label;
		//selected_label.setBackground(new Color(119,136,153));
		selected_label.setIcon(new ImageIcon(ChurchLoggerWindow.class.getResource("/res/" + tab)));
		//selected_label.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		//selected_label.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

	public void addMember() {
		ProgramManager.openWindow(new AddMemberWindow(database));
		//need a way to make sure ^ finishes before this next code runs
		changeTableContents();
	}
	
	public void addDate(){
		ProgramManager.openWindow(new AddDateWindow(database));
	}

	public JFrame getFrame() {
		return this;
	}
	
	public void search(){
		Searcher search = new Searcher();
		table.setModel(search.findMemberFromTable(table, searchBox.getText()));
	}
}
