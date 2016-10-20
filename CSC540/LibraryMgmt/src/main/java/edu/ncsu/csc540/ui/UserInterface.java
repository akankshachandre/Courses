
package edu.ncsu.csc540.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.Calendar;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.ncsu.csc540.ProvisionDao;
import edu.ncsu.csc540.dao.BookDao;
import edu.ncsu.csc540.dao.CameraDao;
import edu.ncsu.csc540.dao.CheckoutDao;
import edu.ncsu.csc540.dao.ConferenceProceedingDao;
import edu.ncsu.csc540.dao.EBookDao;
import edu.ncsu.csc540.dao.FacultyDao;
import edu.ncsu.csc540.dao.JournalDao;
import edu.ncsu.csc540.dao.PatronDao;
import edu.ncsu.csc540.dao.RoomDao;
import edu.ncsu.csc540.dao.StudentDao;
import edu.ncsu.csc540.model.Books;
import edu.ncsu.csc540.model.Faculty;
import edu.ncsu.csc540.model.LateFees;
import edu.ncsu.csc540.model.Patrons;
import edu.ncsu.csc540.model.Students;
import oracle.sql.DATE;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class UserInterface implements ActionListener {

	private JFrame frame;
	JPanel panelLogin, panelHome;
	JLabel lblUnity, lblPwd, lblWelcome, lblWelcomeUser;
	JTextPane txtUnity;
	JButton btnLogin, btnProfile, btnResources, btnCOResources, btnResreq, btnNotifs, btnDuebal, btnLogout, btnStdEdit,
			btnStdSave, btnFacEdit, btnFacSave;
	private static ProvisionDao provisionDao;

	private JPanel panelResources;
	private JPanel panelPublications;
	private JButton btnBooks;
	private JButton btnEbooks;
	private JButton btnJournals;
	private JButton btnRooms;
	private JScrollPane scrollPaneNotifications;
	private JScrollPane scrollPaneDues;
	private JScrollPane scrollPaneTableNotif;
	private JPanel panelNotifications;
	private JPanel panelBooks;
	private JPanel panelCamera;

	private JPanel panelStdProfile;
	private JPanel panelFacProfile;
	private JLabel lblProfileDetails;
	Patrons p = null;
	Students s = null;
	Faculty f = null;
	private JTextArea txtStdUnity;
	private JTextArea txtStdPwd;
	private JTextArea txtStdFirst;
	private JTextArea txtStdLast;
	private JTextArea txtStdDept;
	private JTextArea txtStdNat;
	private JTextArea txtStdSex;
	private JTextArea txtStdNum;
	private JTextArea txtStdClsf;
	private JTextArea txtStdCat;
	private JTextArea txtStdDeg;
	private JTextArea txtStdDob;
	private JTextArea txtStdStreet;
	private JTextArea txtStdCity;
	private JTextArea txtStdZip;
	private JTextArea txtStdPhone;
	private JTextArea txtStdAltPh;
	private JTextArea txtHold;
	private JTextArea txtFacUnity;
	private JTextArea textFacPwd;
	private JTextArea txtFacFirst;
	private JTextArea txtFacLast;
	private JTextArea txtFacDept;
	private JTextArea txtFacNat;
	private JTextArea txtFacSex;
	private JTextArea txtFacNum;
	private JTextArea txtFacCat;
	private JButton btnStdHome;
	private static StudentDao studentDao;
	private static PatronDao patronDao;
	private static FacultyDao facultyDao;
	private static BookDao bookDao;
	private static RoomDao roomDao;
	private static ConferenceProceedingDao confProcDao;

	private static EBookDao ebookDao;

	private JTextField txtCapacity;
	private JButton btnRoomsHome;
	private JButton btnRoomsEnter;
	private JPanel panelRooms;
	private JScrollPane scrollPaneRooms;
	private JPanel panelDisplayRooms;
	private JTable tblRooms;
	private JTable tblCheckedOutResources;
	private JRadioButton rdbtnJamesHuntLibrary;
	private JRadioButton rdbtnDhHillLibrary;
	private JButton btnDispRoomsHome;
	private static CameraDao cameraDao;
	private static CheckoutDao checkoutDao;
	private static JournalDao journalDao;
	private JScrollPane scrollPaneCameras;
	private JButton btnCamera;
	private JButton btnConfRooms;

	private JButton btnPublications;
	private JPasswordField passwordField;
	private JButton btnBookTheRoom;
	private JLabel lblSelectTheCorresponding;
	private JPanel panelBookRoomInput;
	private JTextField txtRoomCheckIn;
	private JTextField txtCheckoutTime;
	private JTextField txtCheckoutDate;
	private JButton btnEnterRoomInput;
	private int capacity;
	private String library;

	private JButton btnDisplayBooksHome;
	private JButton btnBookCheckOut;
	private JTable tableCameras;
	private JScrollPane scrollPaneTableBooks;
	private JScrollPane scrollPaneTableEBooks;
	private JTable tableBooks;
	protected int selectedRow;
	protected boolean available;
	private JPanel panelCheckOutBook;
	private JScrollPane scrollPaneConfProc;
	private JTable tableConfProc;
	private JPanel panelConfProceedings;
	private JButton btnRooms_1;
	private JComboBox comboBoxMonth_1;
	private JComboBox comboDate_1;
	private JComboBox comboBoxYear_1;
	private JButton btnBack_1;
	protected String checkoutMonth;
	protected String checkoutDays;
	protected String checkoutYear;
	private JComboBox comboDate_2;
	private JComboBox comboBoxMonth_2;
	private JComboBox comboBoxYear_2;
	protected String checkinMonth;
	protected String checkinDays;
	protected String checkinYear;
	protected boolean checkDate;
	protected int book_ResourceID;
	protected Books book;
	protected boolean checkOut;
	protected String renew;
	protected String renewDays;
	protected String renewMonth;
	protected String renewYear;
	protected boolean checkRenew;
	protected Date ndate;
	private Date date;
	protected String currentDays;
	protected String currentMonth;
	protected String currentYear;
	private JButton btnConfProc;
	private char patronType;
	protected int cid;
	private JPanel panelReserved;
	private Date currentDate;
	protected String checkoutReserveDay;
	private JComboBox comboReserveMonth;
	private JComboBox comboReserveDay;
	private JComboBox comboReserveYear;
	protected String checkoutReserveMonth;
	protected String checkoutReserveYear;
	private JComboBox comboReserveHours;
	private JComboBox comboBoxReserveMinutes;
	protected String checkoutReserveHours;
	protected String checkoutReserveMinutes;
	private JComboBox comboBoxHours;
	protected String checkinReserveHours;
	private JComboBox comboBoxCheckOutHours;
	private JComboBox comboBoxCheckinHours;
	private JComboBox comboBoxCheckoutMinutes;
	private JComboBox comboBoxCheckinMinutes;
	protected String checkinReserveMinutes;
	protected Date checkedOut;
	protected Date checkedIn;
	protected int checkinHours;
	protected boolean canCheckOut;
	private List<Students> students;
	private List<Faculty> faculty;

	private JButton btnCheckoutConfProc;
	private JPanel panelCheckoutConfProc;
	private JTextField txtCheckoutTimeConfProc;
	private JLabel lblEnterDueDate;
	private JTextField txtReturnTimeConfProc;
	private JLabel label_10;
	private JLabel lblEnterCheckoutDate_1;
	private JTextField txtCheckoutDateConfProc;
	private JLabel lblMmddyyyy;
	private JLabel lblEnterReturnDate;
	private JTextField txtReturnDateConfProc;
	private JLabel label_12;
	private JButton btnCheckOutConfEnter;
	private JButton btnOccupyARoom;
	private JPanel panelOccupyARoom;
	private JButton btnOccupy;
	private JPanel panelCheckedOutResources;
	private JScrollPane scrollPaneCheckedOutRes;
	private JButton btnCCResourcesHome;
	private JButton btnCCResourcesRenew;

	private JPanel panelLateFee;
	private JScrollPane scrollPaneLateFees;
	private JTable tableLateFees;
	Object dataDues[][];
	protected JButton btnTotalDues;
	protected List<LateFees> fees;
	protected int sum;
	protected LateFees latefee;
	private JPanel panelTotalDues;
	private JTextField textFieldTotalDues;
	private JLabel lblNewLabel;
	private JButton btnClearDues;
	protected int resource_id;
	protected char canRenew;

	private JButton btnResourceDetails;
	private JLabel lblSelectRowFrom;

	private JScrollPane scrollPaneCCResDetails;
	private JButton btnCCResDetailsBack;

	protected JTable tblCheckedOutResourcesDetails;
	private JPanel panelEbook;
	private JPanel panelCheckoutEbooks;
	private JTextField txtCheckOutDateEbook;
	private JTextField txtCheckOutTimeEbook;
	private JTable tableEBooks;

	private JButton btnDispCamHome;
	private JButton btnReqCamera;
	private JButton btnCheckOutCamera;
	private JTextField txtCamCODate;
	private JButton btnCamInputEnter;
	private JPanel panelCameraReqInput;

	protected JTable tableRequestedResources;
	protected JPanel panelRequestedResources;
	private JPanel panelReqResources;
	protected List requestedRes;

	private JScrollPane scrollPaneCOResDetails;
	private JPanel panelCCResourcesDetails;

	private JPanel panelRenew;
	private JTextField textFieldRenewDate;
	private JLabel lblNewLabel_2;
	protected String renewDay;
	private JTextField textFieldRenewTime;
	protected String renewTime;
	protected String renewDate;
	private JButton btnNewButton;
	protected Integer resource_id_1;

	private JLabel lblHoldNote;
	private JButton btnHomeReqResources;
	private JScrollPane scrollPaneRequestedResources;
	private JPanel panelJournals;
	private JButton btnCheckoutJournal;
	private JScrollPane scrollPanedisplayJournals;
	private Component tblJournals;
	private JPanel panelCheckoutJournals;
	private JLabel label_14;
	private JLabel label_15;
	private JTextField textCheckOutTimeJournal;
	private JLabel label_18;
	private JLabel label_19;
	private JTextField textReturnTimeJournal;
	private JLabel label_21;
	private JLabel label_22;
	private JTextField textCheckOutDateJournal;
	private JLabel label_23;
	private JLabel label_24;
	private JTextField textReturnDateJournal;
	private JLabel label_25;
	private JButton button;
	private JButton button_1;
	private JPanel panelNotification;

	protected JTable tblNotifications;
	private JComboBox comboBoxRoomNum;
	private JButton btnOccupyBack;
	private JButton btnReserve;
	private JPanel panelFacultyReserveBook;
	private JTextField textFieldReserveStart;
	private JTextField textFieldReserveStartTime;
	private JTextField textFieldReserveEnd;
	private JTextField textFieldReserveEndTime;
	protected String reserveStartDate;
	protected String reserveStartTime;
	protected String reserveEndDate;
	protected String reserveEndTime;
	protected Date reservedStartDate;
	protected Date reservedEndDate;
	protected boolean isValidReservation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

		provisionDao = (ProvisionDao) context.getBean("provisionDao");

		patronDao = (PatronDao) context.getBean("patronDao");
		studentDao = (StudentDao) context.getBean("studentDao");
		facultyDao = (FacultyDao) context.getBean("facultyDao");
		bookDao = (BookDao) context.getBean("bookDao");
		roomDao = (RoomDao) context.getBean("roomDao");
		cameraDao = (CameraDao) context.getBean("cameraDao");
		checkoutDao = (CheckoutDao) context.getBean("checkoutDao");
		ebookDao = (EBookDao) context.getBean("ebookDao");
		confProcDao = (ConferenceProceedingDao) context.getBean("confProcDao");
		journalDao = (JournalDao) context.getBean("journalDao");

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	@SuppressWarnings("deprecation")
	public UserInterface() {

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize(){
		
		Object columnNotifications[] = {"Notifications","Checked out date","Checked in Date","Due Date"};
		Object dataNotifications[][] = {{"Book is due","10-22-2015","","10-28-2015"}};
		
		Object columnBooks[] = {"ISBN","Title","Edition","Year Published","Publisher","Res_Id", "Author name"};
		Object dataBooks[][] = BooksData.getBookData(bookDao.getAllBooks());
		
		Object columnCameras[] = {"Camera Id","Make","Model", "Lens Configuration", "Available Memory"};
		Object dataCameras[][] = CamerasData.getCameraData(cameraDao.getAllCameras());
		
		final Object columnDues[] = {"Dues","Resource Id"};
		
		Object columnConfProceedings[] = {"Conference Number","Title","Name","Year of Publication","Resource Id", "Author name"};
		Object dataConfProceedings[][] = ConfProceedingData.getConfProceedingData(confProcDao.getAllConfProceedings());
		
		Object columnJournals[] = {"ISSN","Resource ID","Title","Year Published", "Author name"};
		Object dataJournals[][] = JournalsData.getJournalData(journalDao.getAllJournals());
		
		final Object columnResourceRequests[] = {"Title"};
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 694, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
	
		panelLogin = new JPanel();
		frame.getContentPane().add(panelLogin, "name_4976706330753");
		panelLogin.setLayout(null);

		lblWelcome = new JLabel("Welcome to LibMS");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(132, 11, 189, 46);
		panelLogin.add(lblWelcome);

		lblUnity = new JLabel("Unity ID:");
		lblUnity.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnity.setBounds(50, 92, 59, 14);
		panelLogin.add(lblUnity);

		lblPwd = new JLabel("Password:");
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setBounds(50, 142, 59, 14);
		panelLogin.add(lblPwd);

		txtUnity = new JTextPane();

		txtUnity.setBackground(Color.LIGHT_GRAY);
		txtUnity.setBounds(150, 86, 150, 20);
		panelLogin.add(txtUnity);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(211, 205, 89, 23);
		panelLogin.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(150, 139, 150, 20);
		panelLogin.add(passwordField);
		btnLogin.addActionListener(this);
		
		panelHome = new JPanel();
		frame.getContentPane().add(panelHome, "name_4984365151467");
		panelHome.setLayout(null);

		btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(s != null){
					panelStdProfile.setVisible(true);
				}
				else {
					panelFacProfile.setVisible(true);
				}
				panelHome.setVisible(false);
			}
		});
		btnProfile.setBounds(55, 40, 82, 23);
		panelHome.add(btnProfile);

		btnResources = new JButton("Resources");
		btnResources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelResources.setVisible(true);
				panelHome.setVisible(false);
			}
		});
		btnResources.setBounds(147, 40, 83, 23);
		panelHome.add(btnResources);
		btnResources.setMargin(new Insets(0,0,0,0));
		
		
		btnCOResources = new JButton("Checked-Out Resources");
		btnCOResources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] columnCCResources = {"Resource ID","Checked-out date","Due date"};
				Object[][] dataCCResources = CheckedOutResourcesData.getCCData(patronDao.getCheckedOutResources(p));
				tblCheckedOutResources = new JTable(dataCCResources, columnCCResources);
				scrollPaneCheckedOutRes.setViewportView(tblCheckedOutResources);
				panelCheckedOutResources.setVisible(true);
				panelHome.setVisible(false);
			}
		});
		btnCOResources.setBounds(240, 40, 149, 23);
		panelHome.add(btnCOResources);
		btnCOResources.setMargin(new Insets(0,0,0,0));
		
		panelReqResources = new JPanel();
		frame.getContentPane().add(panelReqResources, "name_1173081242563834");
		panelReqResources.setLayout(null);
		
		btnHomeReqResources = new JButton("Home");
		btnHomeReqResources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelHome.setVisible(true);
				panelReqResources.setVisible(false);
			}
		});
		btnHomeReqResources.setBounds(10, 11, 89, 23);
		panelReqResources.add(btnHomeReqResources);
		
		scrollPaneRequestedResources = new JScrollPane();
		scrollPaneRequestedResources.setBounds(10, 45, 329, 310);
		panelReqResources.add(scrollPaneRequestedResources);
		
		btnResreq = new JButton("Resource Requests");
		btnResreq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				requestedRes = patronDao.getRequestedResources(p);
				if(!requestedRes.isEmpty()){
					Object dataResourceRequests[][] = RequestedResourcesData.getRequestedResources(requestedRes);
					tableRequestedResources = new JTable(dataResourceRequests, columnResourceRequests);
					scrollPaneRequestedResources.setViewportView(tableRequestedResources);
					panelReqResources.setVisible(true);
					panelHome.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(new JFrame(), "There no requested resources at the moment");
				}
			}
		});
		btnResreq.setBounds(25, 74, 121, 23);
		panelHome.add(btnResreq);
		btnResreq.setMargin(new Insets(0,0,0,0));
		

		btnNotifs = new JButton("Notifications");
		btnNotifs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] columnNotifs = {"Resource ID","Unity ID", "Message","Time"};
				Object[][] dataNotifs = NotificationsData.getNotifData(patronDao.getNotifications(p));
				tblNotifications = new JTable(dataNotifs, columnNotifs);
				scrollPaneNotifications.setViewportView(tblNotifications);
				panelNotification.setVisible(true);
				panelHome.setVisible(false);
			}
		});
		btnNotifs.setBounds(158, 74, 91, 23);
		panelHome.add(btnNotifs);
		btnNotifs.setMargin(new Insets(0,0,0,0));

		btnDuebal = new JButton("Due Balance");
		btnDuebal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				latefee = null;
				sum = 0;
				fees = patronDao.calculateLateFee(p);
			if(fees.isEmpty()){
				JOptionPane.showMessageDialog(new JFrame(), "You have no dues");
			}
			else{
			dataDues = LateFeesData.getLateFeesData(patronDao.calculateLateFee(p));
			tableLateFees = new JTable(dataDues,columnDues);
			scrollPaneLateFees = new JScrollPane();
			scrollPaneLateFees.setViewportView(tableLateFees);
			
			btnTotalDues = new JButton("Total Dues");
			btnTotalDues.setBounds(571, 206, 117, 29);
			btnTotalDues.addActionListener(new ActionListener(){

				private JButton btnHome_1;

				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i = 0;i<fees.size();i++){
						latefee = fees.get(i);
						sum = sum + (latefee.getFees());
						
					}
					btnHome_1 = new JButton("Home");
					btnHome_1.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e) {
							panelTotalDues.setVisible(false);
							panelHome.setVisible(true);
						}
					});
					btnHome_1.setBounds(384, 153, 109, 43);
					
					
					btnClearDues = new JButton("Clear Dues");
					btnClearDues.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if(!patronDao.clearDues(p))
							{
								JOptionPane.showMessageDialog(new JFrame(), "Dues cannot be cleared");
							}
							else
							textFieldTotalDues.setText(Integer.toString(0));
							
							panelTotalDues.setVisible(true);
							panelHome.setVisible(false);
						}
					});
					btnClearDues.setBounds(284, 133, 89, 23);
					
					
					btnClearDues.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							panelTotalDues.setVisible(false);
							panelHome.setVisible(true);
						}
					});
					
					btnClearDues.setBounds(290, 153, 100, 40);
					
					panelTotalDues = new JPanel();
					lblNewLabel = new JLabel("Total Dues");
					lblNewLabel.setBounds(126, 89, 91, 14);
					
					textFieldTotalDues = new JTextField();
					textFieldTotalDues.setEditable(false);
					textFieldTotalDues.setBounds(281, 83, 86, 20);
					panelTotalDues.add(textFieldTotalDues);
					textFieldTotalDues.setColumns(10);
					
					
					textFieldTotalDues.setText(Integer.toString(sum));
					
					
					panelTotalDues.add(lblNewLabel);
					panelTotalDues.add(btnClearDues);
					panelTotalDues.add(btnHome_1);
					panelTotalDues.add(textFieldTotalDues);
					frame.getContentPane().add(panelTotalDues, "name_1141177288445362");
					
					panelTotalDues.setVisible(true);
					panelLateFee.setVisible(false);
					
					
					
					
					
				}
				
				
				
				
				
			});
			
			
			panelLateFee.add(scrollPaneLateFees);			
			panelLateFee.add(btnTotalDues);
			panelLateFee.setVisible(true);
			panelHome.setVisible(false);
			}
			}
		});
		btnDuebal.setBounds(261, 74, 91, 23);
		panelHome.add(btnDuebal);
		btnDuebal.setMargin(new Insets(0,0,0,0));

		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelHome.setVisible(false);
				panelLogin.setVisible(true);
				txtUnity.setText("");
				passwordField.setText("");
			}
		});
		btnLogout.setBounds(362, 74, 65, 23);
		panelHome.add(btnLogout);
		btnLogout.setMargin(new Insets(0,0,0,0));

		lblWelcomeUser = new JLabel("Welcome, XYZ!");
		lblWelcomeUser.setHorizontalAlignment(SwingConstants.CENTER);

		lblWelcomeUser.setBounds(25, 15, 121, 14);
		panelHome.add(lblWelcomeUser);
		
		panelResources = new JPanel();
		panelResources.setAutoscrolls(true);
		frame.getContentPane().add(panelResources, "name_175109919109465");
		panelResources.setLayout(null);
		
		btnPublications = new JButton("Publications");
		btnPublications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPublications.setVisible(true);
				panelResources.setVisible(false);
			}
		});
		btnPublications.setBounds(10, 84, 89, 23);
		panelResources.add(btnPublications);
		btnPublications.setMargin(new Insets(0,0,0,0));
		
		btnCamera = new JButton("Cameras");
		btnCamera.setBounds(128, 84, 96, 23);
		btnCamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCamera.setVisible(true);
				panelResources.setVisible(false);
			}
		});
		panelResources.add(btnCamera);
		btnCamera.setMargin(new Insets(0,0,0,0));
		

		btnRooms = new JButton("Conference/Study Rooms");
		btnRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelRooms.setVisible(true);
				panelResources.setVisible(false);
			}
		});
		btnRooms.setActionCommand("Conference Rooms");
		btnRooms.setBounds(257, 84, 226, 23);
		panelResources.add(btnRooms);

		btnConfRooms = new JButton("Conference Rooms");
		btnConfRooms.setActionCommand("Conference Rooms");
		btnConfRooms.setBounds(257, 84, 167, 23);
		panelResources.add(btnConfRooms);
		btnConfRooms.setMargin(new Insets(0,0,0,0));
		

		
		JButton btnResBack = new JButton("Back");
		btnResBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelResources.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		btnResBack.setBounds(0, 0, 71, 17);
		panelResources.add(btnResBack);
		
		panelPublications = new JPanel();
		frame.getContentPane().add(panelPublications, "name_178119537024656");
		panelPublications.setLayout(null);
		
		btnBooks = new JButton("Books");
		btnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!studentDao.isStudent(p.getUnityid())){
					btnReserve.setEnabled(true);
					
				}
				else
					btnReserve.setEnabled(false);
				
				panelBooks.setVisible(true);
				panelPublications.setVisible(false);
				
			}
		});
		btnBooks.setBounds(21, 59, 89, 23);
		panelPublications.add(btnBooks);
		
		btnEbooks = new JButton("E-books");		btnEbooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object columnEBooks[] = {"ISBN","Title","Edition","Year Published","Publisher","Res_Id", "Author name"};
				Object dataEbooks[][] = EbookData.getEbookData(ebookDao.getAllEBooks());
				tableEBooks = new JTable(dataEbooks, columnEBooks);
				scrollPaneTableEBooks.setViewportView(tableEBooks);
				panelEbook.setVisible(true);
				panelPublications.setVisible(false);	
			}
		});		btnEbooks.setBounds(173, 59, 89, 23);
		panelPublications.add(btnEbooks);
		
		btnJournals = new JButton("Journals");
		btnJournals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelJournals.setVisible(true);
				panelPublications.setVisible(false);
			}
		});
		btnJournals.setBounds(318, 59, 89, 23);
		panelPublications.add(btnJournals);
		
		btnConfProc = new JButton("Conference Proceedings");
		btnConfProc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelConfProceedings.setVisible(true);
				panelPublications.setVisible(false);
			}
		});
		btnConfProc.setBounds(136, 125, 166, 23);
		panelPublications.add(btnConfProc);
		
		
		JButton btnPubBack = new JButton("Back");
		btnPubBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelResources.setVisible(true);
				panelPublications.setVisible(false);
			}
		});
		btnPubBack.setBounds(0, 0, 71, 17);
		panelPublications.add(btnPubBack);
		
		
		scrollPaneDues = new JScrollPane();
		frame.getContentPane().add(scrollPaneDues, "name_191572532630860");
		
		
		
		
		

		lblWelcomeUser.setBounds(25, 15, 139, 14);
		panelHome.add(lblWelcomeUser);
		
		btnOccupyARoom = new JButton("Occupy a Room");
		btnOccupyARoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelOccupyARoom.setVisible(true);
				panelHome.setVisible(false);
			}
		});
		btnOccupyARoom.setBounds(399, 40, 121, 23);
		panelHome.add(btnOccupyARoom);
		btnOccupyARoom.setMargin(new Insets(0,0,0,0));
		
		lblHoldNote = new JLabel("<html>Note: Library privileges are supsended for your account as you have a hold. <br>\r\nPlease clear dues from 'Due Balance' to restore privilieges. </html>");
		lblHoldNote.setBounds(10, 129, 658, 45);
		panelHome.add(lblHoldNote);
		
		panelCameraReqInput = new JPanel();
		frame.getContentPane().add(panelCameraReqInput, "name_22433899357519");
		panelCameraReqInput.setLayout(null);
		
		JLabel lblEnterCheckoutDate_2 = new JLabel("Enter Check-Out Date:");
		lblEnterCheckoutDate_2.setBounds(10, 11, 141, 14);
		panelCameraReqInput.add(lblEnterCheckoutDate_2);
		
		txtCamCODate = new JTextField();
		txtCamCODate.setBounds(150, 8, 112, 20);
		panelCameraReqInput.add(txtCamCODate);
		txtCamCODate.setColumns(10);
		
		JLabel label_11 = new JLabel("(Enter date in MM/DD/YYYY format)");
		label_11.setBounds(272, 11, 232, 14);
		panelCameraReqInput.add(label_11);
		
		btnCamInputEnter = new JButton("Enter");
		btnCamInputEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String check_out = txtCamCODate.getText();
				Date checked_out = null;
				if(isValidDate(check_out)){
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					try {
						checked_out = sdf.parse(check_out);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				}
				int selectedRow = tableCameras.getSelectedRow();
				Object dataCameras[][] = CamerasData.getCameraData(cameraDao.getAllCameras());
				int resourceId = (Integer)dataCameras[selectedRow][5];
				String message = cameraDao.requestCamera(resourceId, p, checked_out);
				JOptionPane.showMessageDialog(panelCameraReqInput, message);
				panelCameraReqInput.setVisible(false);
				panelCamera.setVisible(true);
			}
		});
		btnCamInputEnter.setBounds(10, 51, 89, 23);
		panelCameraReqInput.add(btnCamInputEnter);
		
		panelStdProfile = new JPanel();
		frame.getContentPane().add(panelStdProfile, "name_24429904120318");
		panelStdProfile.setLayout(null);
		
		lblProfileDetails = new JLabel("Profile Details:");
		lblProfileDetails.setBounds(10, 11, 114, 14);
		panelStdProfile.add(lblProfileDetails);
		
		txtStdUnity = new JTextArea();
		txtStdUnity.setEditable(false);
		txtStdUnity.setBounds(109, 46, 98, 22);
		panelStdProfile.add(txtStdUnity);
		
		JLabel lblUnityId = new JLabel("Unity ID:");
		lblUnityId.setBounds(10, 51, 114, 14);
		panelStdProfile.add(lblUnityId);
		
		JLabel lblPwdProfile = new JLabel("Password:");
		lblPwdProfile.setBounds(10, 87, 114, 14);
		panelStdProfile.add(lblPwdProfile);
		
		txtStdPwd = new JTextArea();
		txtStdPwd.setEditable(false);
		txtStdPwd.setBounds(109, 82, 98, 22);
		panelStdProfile.add(txtStdPwd);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(10, 125, 114, 14);
		panelStdProfile.add(lblFirstName);
		
		txtStdFirst = new JTextArea();
		txtStdFirst.setEditable(false);
		txtStdFirst.setBounds(109, 120, 98, 22);
		panelStdProfile.add(txtStdFirst);
		
		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setBounds(10, 164, 114, 14);
		panelStdProfile.add(lblLastName);
		
		txtStdLast = new JTextArea();
		txtStdLast.setEditable(false);
		txtStdLast.setBounds(109, 159, 98, 22);
		panelStdProfile.add(txtStdLast);
		
		JLabel lblDept = new JLabel("Dept:");
		lblDept.setBounds(10, 194, 114, 14);
		panelStdProfile.add(lblDept);
		
		txtStdDept = new JTextArea();
		txtStdDept.setEditable(false);
		txtStdDept.setBounds(109, 189, 98, 22);
		panelStdProfile.add(txtStdDept);
		
		JLabel lblNationality = new JLabel("Nationality:");
		lblNationality.setBounds(10, 233, 114, 14);
		panelStdProfile.add(lblNationality);
		
		txtStdNat = new JTextArea();
		txtStdNat.setEditable(false);
		txtStdNat.setBounds(109, 228, 98, 22);
		panelStdProfile.add(txtStdNat);
		
		JLabel lblSex = new JLabel("Sex:");
		lblSex.setBounds(227, 51, 114, 14);
		panelStdProfile.add(lblSex);
		
		txtStdSex = new JTextArea();
		txtStdSex.setEditable(false);
		txtStdSex.setBounds(326, 46, 98, 22);
		panelStdProfile.add(txtStdSex);
		
		JLabel lblStudentNumber = new JLabel("Student Number:");
		lblStudentNumber.setBounds(227, 87, 114, 14);
		panelStdProfile.add(lblStudentNumber);
		
		txtStdNum = new JTextArea();
		txtStdNum.setEditable(false);
		txtStdNum.setBounds(326, 82, 98, 22);
		panelStdProfile.add(txtStdNum);
		
		JLabel lblStudentClassification = new JLabel("Classification:");
		lblStudentClassification.setBounds(227, 122, 114, 14);
		panelStdProfile.add(lblStudentClassification);
		
		txtStdClsf = new JTextArea();
		txtStdClsf.setBounds(326, 117, 98, 22);
		panelStdProfile.add(txtStdClsf);
		
		JLabel lblStudentCategory = new JLabel("Category:");
		lblStudentCategory.setBounds(227, 161, 114, 14);
		panelStdProfile.add(lblStudentCategory);
		
		txtStdCat = new JTextArea();
		txtStdCat.setBounds(326, 156, 98, 22);
		panelStdProfile.add(txtStdCat);
		
		JLabel lblDegreeProgram = new JLabel("Degree Program:");
		lblDegreeProgram.setBounds(227, 191, 114, 14);
		panelStdProfile.add(lblDegreeProgram);
		
		txtStdDeg = new JTextArea();
		txtStdDeg.setBounds(326, 186, 98, 22);
		panelStdProfile.add(txtStdDeg);
		
		JLabel lblDateOfBirth = new JLabel("Date of birth:");
		lblDateOfBirth.setBounds(227, 230, 114, 14);
		panelStdProfile.add(lblDateOfBirth);
		
		txtStdDob = new JTextArea();
		txtStdDob.setBounds(326, 225, 98, 22);
		panelStdProfile.add(txtStdDob);
		
		JLabel lblStreet = new JLabel("Street:");
		lblStreet.setBounds(454, 51, 114, 14);
		panelStdProfile.add(lblStreet);
		
		txtStdStreet = new JTextArea();
		txtStdStreet.setEditable(false);
		txtStdStreet.setBounds(553, 46, 98, 22);
		panelStdProfile.add(txtStdStreet);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(454, 81, 114, 14);
		panelStdProfile.add(lblCity);
		
		txtStdCity = new JTextArea();
		txtStdCity.setEditable(false);
		txtStdCity.setBounds(553, 76, 98, 22);
		panelStdProfile.add(txtStdCity);
		
		JLabel lblPostalCode = new JLabel("Postal code:");
		lblPostalCode.setBounds(454, 119, 114, 14);
		panelStdProfile.add(lblPostalCode);
		
		txtStdZip = new JTextArea();
		txtStdZip.setEditable(false);
		txtStdZip.setBounds(553, 114, 98, 22);
		panelStdProfile.add(txtStdZip);
		
		JLabel lblPhoneNumber = new JLabel("Phone number:");
		lblPhoneNumber.setBounds(454, 161, 114, 14);
		panelStdProfile.add(lblPhoneNumber);
		
		txtStdPhone = new JTextArea();
		txtStdPhone.setEditable(false);
		txtStdPhone.setBounds(553, 156, 98, 22);
		panelStdProfile.add(txtStdPhone);
		
		JLabel lblAlternatePhNo = new JLabel("Alternate Ph. No.:");
		lblAlternatePhNo.setBounds(454, 191, 114, 14);
		panelStdProfile.add(lblAlternatePhNo);
		
		txtStdAltPh = new JTextArea();
		txtStdAltPh.setEditable(false);
		txtStdAltPh.setBounds(553, 186, 98, 22);
		panelStdProfile.add(txtStdAltPh);
		
		btnStdEdit = new JButton("Edit");
		btnStdEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStdCity.setEditable(true);
				txtStdPhone.setEditable(true);
				txtStdStreet.setEditable(true);
				txtStdNat.setEditable(true);
				txtStdZip.setEditable(true);
				txtStdPwd.setEditable(true);
				txtStdAltPh.setEditable(true);
				btnStdSave.setEnabled(true);
			}
		});
		btnStdEdit.setBounds(118, 275, 89, 23);
		panelStdProfile.add(btnStdEdit);
		
		btnStdSave = new JButton("Save");
		btnStdSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				s.setCity(txtStdCity.getText());
				s.setPhoneNum(Integer.parseInt(txtStdPhone.getText()));
				s.setStreet(txtStdStreet.getText());
				s.setPostalCode(Integer.parseInt(txtStdZip.getText()));
				s.setAltPhone(Integer.parseInt(txtStdAltPh.getText()));
				p.setNationality(txtStdNat.getText());
				p.setPassword(txtStdPwd.getText());
				studentDao.updateStudent(s, p);
				txtStdCity.setEditable(false);
				txtStdPhone.setEditable(false);
				txtStdStreet.setEditable(false);
				txtStdAltPh.setEditable(false);
				txtStdZip.setEditable(false);
				txtStdNat.setEditable(false);
				txtStdPwd.setEditable(false);
				btnStdSave.setEnabled(false);
			}
		});
		btnStdSave.setEnabled(false);
		btnStdSave.setBounds(234, 275, 89, 23);
		panelStdProfile.add(btnStdSave);
		
		JLabel lblHold = new JLabel("Hold:");
		lblHold.setBounds(454, 230, 114, 14);
		panelStdProfile.add(lblHold);
		
		txtHold = new JTextArea();
		txtHold.setEditable(false);
		txtHold.setBounds(553, 225, 98, 22);
		panelStdProfile.add(txtHold);
		
		btnStdHome = new JButton("Home");
		btnStdHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelHome.setVisible(true);
				panelStdProfile.setVisible(false);
			}
		});
		btnStdHome.setBounds(10, 275, 89, 23);
		panelStdProfile.add(btnStdHome);
		
		panelFacProfile = new JPanel();
		panelFacProfile.setLayout(null);
		frame.getContentPane().add(panelFacProfile, "name_26363051624111");
		
		JLabel label = new JLabel("Profile Details:");
		label.setBounds(10, 11, 114, 14);
		panelFacProfile.add(label);
		
		txtFacUnity = new JTextArea();
		txtFacUnity.setEditable(false);
		txtFacUnity.setBounds(109, 46, 98, 22);
		panelFacProfile.add(txtFacUnity);
		
		JLabel label_1 = new JLabel("Unity ID:");
		label_1.setBounds(10, 51, 114, 14);
		panelFacProfile.add(label_1);
		
		JLabel label_2 = new JLabel("Password:");
		label_2.setBounds(10, 87, 114, 14);
		panelFacProfile.add(label_2);
		
		textFacPwd = new JTextArea();
		textFacPwd.setEditable(false);
		textFacPwd.setBounds(109, 82, 98, 22);
		panelFacProfile.add(textFacPwd);
		
		JLabel label_3 = new JLabel("First Name:");
		label_3.setBounds(10, 125, 114, 14);
		panelFacProfile.add(label_3);
		
		txtFacFirst = new JTextArea();
		txtFacFirst.setEditable(false);
		txtFacFirst.setBounds(109, 120, 98, 22);
		panelFacProfile.add(txtFacFirst);
		
		JLabel label_4 = new JLabel("Last name:");
		label_4.setBounds(10, 164, 114, 14);
		panelFacProfile.add(label_4);
		
		txtFacLast = new JTextArea();
		txtFacLast.setEditable(false);
		txtFacLast.setBounds(109, 159, 98, 22);
		panelFacProfile.add(txtFacLast);
		
		JLabel label_6 = new JLabel("Dept:");
		label_6.setBounds(10, 197, 114, 14);
		panelFacProfile.add(label_6);
		
		txtFacDept = new JTextArea();
		txtFacDept.setEditable(false);
		txtFacDept.setBounds(109, 192, 98, 22);
		panelFacProfile.add(txtFacDept);
		
		JLabel label_7 = new JLabel("Nationality:");
		label_7.setBounds(227, 169, 114, 14);
		panelFacProfile.add(label_7);
		
		txtFacNat = new JTextArea();
		txtFacNat.setEditable(false);
		txtFacNat.setBounds(326, 164, 98, 22);
		panelFacProfile.add(txtFacNat);
		
		JLabel label_8 = new JLabel("Sex:");
		label_8.setBounds(227, 51, 114, 14);
		panelFacProfile.add(label_8);
		
		txtFacSex = new JTextArea();
		txtFacSex.setEditable(false);
		txtFacSex.setBounds(326, 46, 98, 22);
		panelFacProfile.add(txtFacSex);
		
		JLabel lblFacultyNumber = new JLabel("Faculty Number:");
		lblFacultyNumber.setBounds(227, 87, 114, 14);
		panelFacProfile.add(lblFacultyNumber);
		
		txtFacNum = new JTextArea();
		txtFacNum.setEditable(false);
		txtFacNum.setBounds(326, 82, 98, 22);
		panelFacProfile.add(txtFacNum);
		
		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(227, 125, 114, 14);
		panelFacProfile.add(lblCategory);
		
		txtFacCat = new JTextArea();
		txtFacCat.setEditable(false);
		txtFacCat.setBounds(326, 120, 98, 22);
		panelFacProfile.add(txtFacCat);
		
		btnFacEdit = new JButton("Edit");
		btnFacEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFacNat.setEditable(true);
				txtStdPwd.setEditable(true);
				btnFacSave.setEnabled(true);
			}
		});
		btnFacEdit.setBounds(109, 237, 89, 23);
		panelFacProfile.add(btnFacEdit);
		
		btnFacSave = new JButton("Save");
		btnFacSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setNationality(txtFacNat.getText());
				p.setPassword(textFacPwd.getText());
				facultyDao.updateFaculty(p);
				txtFacNat.setEditable(false);
				textFacPwd.setEditable(false);
				btnFacSave.setEnabled(false);
			}
		});
		btnFacSave.setEnabled(false);
		btnFacSave.setBounds(208, 237, 89, 23);
		panelFacProfile.add(btnFacSave);
		
		JButton btnFacProfileHome = new JButton("Home");
		btnFacProfileHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelFacProfile.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		btnFacProfileHome.setBounds(10, 237, 89, 23);
		panelFacProfile.add(btnFacProfileHome);
		

		panelRooms = new JPanel();
		frame.getContentPane().add(panelRooms, "name_23036684702941");
		panelRooms.setLayout(null);
		
		JLabel lblSelectLibrary = new JLabel("Select Library: ");
		lblSelectLibrary.setBounds(10, 62, 96, 14);
		panelRooms.add(lblSelectLibrary);
		
		rdbtnJamesHuntLibrary = new JRadioButton("James Hunt Library");
		rdbtnJamesHuntLibrary.setBounds(136, 58, 169, 23);
		panelRooms.add(rdbtnJamesHuntLibrary);
		
		rdbtnDhHillLibrary = new JRadioButton("D.H. Hill Library");
		rdbtnDhHillLibrary.setBounds(307, 58, 169, 23);
		panelRooms.add(rdbtnDhHillLibrary);
		
		JLabel lblCapacity = new JLabel("Capacity:");
		lblCapacity.setBounds(10, 111, 96, 14);
		panelRooms.add(lblCapacity);
		
		txtCapacity = new JTextField();
		txtCapacity.setBounds(136, 108, 86, 20);
		panelRooms.add(txtCapacity);
		txtCapacity.setColumns(10);
		
		btnRoomsEnter = new JButton("Enter");
		btnRoomsEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				library = null;
				if(rdbtnJamesHuntLibrary.isSelected()) {
					library = "J. B. Hunt";
				}
				else if(rdbtnDhHillLibrary.isSelected()){
					library = "D. H. Hill";
				}
				else {
					JOptionPane.showMessageDialog(panelRooms, "Please select a library");
				}
				capacity = Integer.parseInt(txtCapacity.getText());
				if(s!=null)
					patronType = 's';
				else
					patronType = 'f';
				Object[] columnRooms = {"Room Number","Capacity","Floor","Type"};
				Object[][] dataRooms = RoomsData.getRoomData(roomDao.getAllRooms(library, capacity, patronType));
				tblRooms = new JTable(dataRooms, columnRooms);
				scrollPaneRooms.setViewportView(tblRooms);
				panelRooms.setVisible(false);
				panelDisplayRooms.setVisible(true);
			}
		});
		btnRoomsEnter.setBounds(10, 153, 89, 23);
		panelRooms.add(btnRoomsEnter);
		
		btnRoomsHome = new JButton("Home");
		btnRoomsHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelHome.setVisible(true);
				panelRooms.setVisible(false);
			}
		});
		btnRoomsHome.setBounds(10, 11, 134, 23);
		panelRooms.add(btnRoomsHome);
		
		panelDisplayRooms = new JPanel();
		frame.getContentPane().add(panelDisplayRooms, "name_30728905350375");
		
		btnDispRoomsHome = new JButton("Home");
		btnDispRoomsHome.setBounds(10, 31, 75, 23);
		btnDispRoomsHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelHome.setVisible(true);
				panelDisplayRooms.setVisible(false);
			}
		});
		panelDisplayRooms.setLayout(null);
		panelDisplayRooms.add(btnDispRoomsHome);
		btnDispRoomsHome.setMargin(new Insets(0,0,0,0));
		
		lblSelectTheCorresponding = new JLabel("Select the corresponding row and click 'Book the room'");
		lblSelectTheCorresponding.setBounds(10, 6, 361, 14);
		panelDisplayRooms.add(lblSelectTheCorresponding);
		
		btnBookTheRoom = new JButton("Book the room");
		btnBookTheRoom.setBounds(95, 31, 133, 23);
		btnBookTheRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBookRoomInput.setVisible(true);
				panelDisplayRooms.setVisible(false);
			}
		});
		panelDisplayRooms.add(btnBookTheRoom);
		btnBookTheRoom.setMargin(new Insets(0,0,0,0));
		
		scrollPaneRooms = new JScrollPane();
		scrollPaneRooms.setBounds(10, 65, 668, 292);
		panelDisplayRooms.add(scrollPaneRooms);
				

		
		
		
    tableBooks = new JTable(dataBooks,columnBooks);
		
     scrollPaneTableBooks = new JScrollPane();
     scrollPaneTableBooks.setBounds(10, 45, 658, 312);
		scrollPaneTableBooks.setViewportView(tableBooks);
		
		panelBooks = new JPanel();
		
		btnDisplayBooksHome = new JButton("Home");
		btnDisplayBooksHome.setBounds(10, 11, 59, 23);
		btnDisplayBooksHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBooks.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		panelBooks.setLayout(null);
		panelBooks.add(btnDisplayBooksHome);
		
		btnBookCheckOut = new JButton("Check-Out");
		btnBookCheckOut.setBounds(79, 11, 83, 23);
		btnBookCheckOut.addActionListener(new ActionListener() {
			private int waitListed;

			public void actionPerformed(ActionEvent e) {
				selectedRow = tableBooks.getSelectedRow();
				
				book_ResourceID= ((Integer) tableBooks.getValueAt(selectedRow, 5)).intValue();

				book_ResourceID = ((Integer) tableBooks.getValueAt(selectedRow, 5)).intValue();



				book = new Books();
				book.setResourceId(book_ResourceID);
				
				available = bookDao.checkifAvailable((String) tableBooks.getValueAt(selectedRow, 0),p.getUnityid());
				if(!available){
				  
					JOptionPane.showMessageDialog(new JFrame(), "Book is currently not available");
				
					
					panelBooks.setVisible(true);
					
					String choice = JOptionPane.showInputDialog("Do you want to be added to the wait list?");
					if("Yes".equals(choice)){
						panelBooks.setVisible(false);
						panelCheckOutBook.setVisible(true);
						
						
						
						checkoutMonth = (String) comboBoxMonth_2.getSelectedItem();
						checkoutDays = (String) comboDate_2.getSelectedItem();
						checkoutYear = (String) comboBoxYear_2.getSelectedItem();
						checkinMonth = (String) comboBoxMonth_1.getSelectedItem();
						checkinDays = (String) comboDate_1.getSelectedItem();
						checkinYear = (String) comboBoxYear_1.getSelectedItem();
						checkoutReserveHours = (String) comboBoxCheckOutHours.getSelectedItem();
						checkinReserveHours = (String) comboBoxCheckinHours.getSelectedItem();
						checkoutReserveMinutes = (String) comboBoxCheckoutMinutes.getSelectedItem();
						checkinReserveMinutes = (String) comboBoxCheckinMinutes.getSelectedItem();
					
						
						String checked_out = checkoutMonth+"/"+checkoutDays+"/"+checkoutYear+" "+checkoutReserveHours+":"+checkoutReserveMinutes+":00";
						String checked_in = checkinMonth+"/"+checkinDays+"/"+checkinYear+" "+checkinReserveHours+":"+checkinReserveMinutes+":00";
						
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
						
							
								try {
									checkedOut = (Date) sdf.parse(checked_out);
									checkedIn = (Date) sdf.parse(checked_in);
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
						checkDate = bookDao.validateReturnDate(checkedOut,checkedIn,p);
						
						if(checkDate)
						{
						
						waitListed = bookDao.addToWaitList(book_ResourceID,p.getUnityid(),checkedOut);
						if(waitListed>0)
						JOptionPane.showMessageDialog(new JFrame(), "You have been added to wait list."+"\n"+"A notification will be sent once the book is available");
						else
							JOptionPane.showMessageDialog(new JFrame(), "You are already waitlisted for this resource");
						}}
					
				}
					else
					{
						cid = bookDao.checkifReserved(book_ResourceID);
						if(cid!=0){
							if(bookDao.checkifEnrolled(p.getUnityid(), cid)){
								panelReserved.setVisible(true);
								panelBooks.setVisible(false);
							}
							
						}
						else{
						panelCheckOutBook.setVisible(true);
						panelBooks.setVisible(false);
						}
					}
				}
				
			
		});
		panelBooks.add(btnBookCheckOut);
		panelBooks.add(scrollPaneTableBooks);
		
		frame.getContentPane().add(panelBooks, "name_602659589146046");
		
		panelBookRoomInput = new JPanel();
		frame.getContentPane().add(panelBookRoomInput, "name_30624140540976");
		panelBookRoomInput.setLayout(null);
		
		JLabel lblEnterCheckoutDate = new JLabel("Enter Check-out date: ");
		lblEnterCheckoutDate.setBounds(10, 43, 117, 14);
		panelBookRoomInput.add(lblEnterCheckoutDate);
		
		JLabel lblEnterCheckoutTime = new JLabel("Enter Check-out time:");
		lblEnterCheckoutTime.setBounds(10, 88, 106, 14);
		panelBookRoomInput.add(lblEnterCheckoutTime);
		
		JLabel lblEnterDuration = new JLabel("Enter Check-in time: ");
		lblEnterDuration.setBounds(10, 132, 134, 14);
		panelBookRoomInput.add(lblEnterDuration);
		
		txtRoomCheckIn = new JTextField();
		txtRoomCheckIn.setBounds(164, 129, 86, 20);
		panelBookRoomInput.add(txtRoomCheckIn);
		txtRoomCheckIn.setColumns(10);
		
		txtCheckoutTime = new JTextField();
		txtCheckoutTime.setBounds(164, 85, 86, 20);
		panelBookRoomInput.add(txtCheckoutTime);
		txtCheckoutTime.setColumns(10);
		
		txtCheckoutDate = new JTextField();
		txtCheckoutDate.setBounds(164, 40, 86, 20);
		panelBookRoomInput.add(txtCheckoutDate);
		txtCheckoutDate.setColumns(10);
		
		btnEnterRoomInput = new JButton("Enter");
		btnEnterRoomInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String checked_out = null;
				int selectedRow = tblRooms.getSelectedRow();
				Object[][] dataRooms = RoomsData.getRoomData(roomDao.getAllRooms(library, capacity, patronType));
				int resource_id = (Integer)dataRooms[selectedRow][4];
				if(isValidDate(txtCheckoutDate.getText())){
					checked_out = txtCheckoutDate.getText();
				}
				else {
					JOptionPane.showMessageDialog(panelDisplayRooms, "Invalid Date Format");
				}
				String startTime = txtCheckoutTime.getText();
				String endTime = txtRoomCheckIn.getText();
				String check_out = checked_out + " " + startTime + ":00";
				String check_in = checked_out +  " " + endTime + ":00"; 
				if(isValidConfDueDateTime(check_out, check_in, 3)) {
					boolean b = checkoutDao.checkoutRoom(p, resource_id, check_out, check_in);
					if(b){
						JOptionPane.showMessageDialog(panelHome, "Room booked");
						panelBookRoomInput.setVisible(false);
						panelHome.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(panelDisplayRooms, "Selected room not available at given date and time");
						panelBookRoomInput.setVisible(false);
						panelDisplayRooms.setVisible(true);
					}
				}
				else {
					JOptionPane.showMessageDialog(panelBookRoomInput, "Invalid duration. Should not be more than 3 hours");
				}
			}
		});
		btnEnterRoomInput.setBounds(10, 176, 89, 23);
		panelBookRoomInput.add(btnEnterRoomInput);
		
		JLabel lblDateInstr = new JLabel("(Enter date in MM/DD/YYYY format)");
		lblDateInstr.setBounds(287, 43, 232, 14);
		panelBookRoomInput.add(lblDateInstr);
		
		JLabel lblenterTimeIn = new JLabel("(Enter time in HH24:MM format)");
		lblenterTimeIn.setBounds(287, 88, 232, 14);
		panelBookRoomInput.add(lblenterTimeIn);
		
		JLabel label_5 = new JLabel("(Enter time in HH24:MM format)");
		label_5.setBounds(287, 132, 232, 14);
		panelBookRoomInput.add(label_5);
		
		
		
		
		
		tableCameras = new JTable(dataCameras,columnCameras);
		scrollPaneCameras = new JScrollPane();
		scrollPaneCameras.setBounds(10, 40, 658, 317);
		scrollPaneCameras.setViewportView(tableCameras);
		panelCamera = new JPanel();
		panelCamera.setLayout(null);
		
		panelCamera.add(scrollPaneCameras);
		frame.getContentPane().add(panelCamera, "name_602659589146046");
		
		btnDispCamHome = new JButton("Home");
		btnDispCamHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelHome.setVisible(true);
				panelCamera.setVisible(false);
			}
		});
		btnDispCamHome.setBounds(10, 6, 89, 23);
		panelCamera.add(btnDispCamHome);
		
		btnReqCamera = new JButton("Request Camera");
		btnReqCamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCameraReqInput.setVisible(true);
				panelCamera.setVisible(false);
			}
		});
		btnReqCamera.setBounds(109, 6, 113, 23);
		panelCamera.add(btnReqCamera);
		btnReqCamera.setMargin(new Insets(0,0,0,0));
		
		btnCheckOutCamera = new JButton("Check Out Camera");
		btnCheckOutCamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableCameras.getSelectedRow();
				Object dataCameras[][] = CamerasData.getCameraData(cameraDao.getAllCameras());
				int resourceId = (Integer)dataCameras[selectedRow][5];
				String message = cameraDao.checkoutCamera(resourceId, p);
				JOptionPane.showMessageDialog(panelCamera, message);
			}
		});
		btnCheckOutCamera.setBounds(239, 6, 131, 23);
		panelCamera.add(btnCheckOutCamera);
		btnCheckOutCamera.setMargin(new Insets(0,0,0,0));
		
		panelCheckOutBook = new JPanel();
		frame.getContentPane().add(panelCheckOutBook, "name_799322398852891");
		panelCheckOutBook.setLayout(null);
		
		JLabel lblCheckOutDate = new JLabel("Check out date");
		lblCheckOutDate.setBounds(145, 63, 117, 14);
		panelCheckOutBook.add(lblCheckOutDate);
		
		JLabel lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setBounds(145, 133, 100, 14);
		panelCheckOutBook.add(lblReturnDate);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
				
				
				checkoutMonth = (String) comboBoxMonth_2.getSelectedItem();
				checkoutDays = (String) comboDate_2.getSelectedItem();
				checkoutYear = (String) comboBoxYear_2.getSelectedItem();
				checkinMonth = (String) comboBoxMonth_1.getSelectedItem();
				checkinDays = (String) comboDate_1.getSelectedItem();
				checkinYear = (String) comboBoxYear_1.getSelectedItem();
				checkoutReserveHours = (String) comboBoxCheckOutHours.getSelectedItem();
				checkinReserveHours = (String) comboBoxCheckinHours.getSelectedItem();
				checkoutReserveMinutes = (String) comboBoxCheckoutMinutes.getSelectedItem();
				checkinReserveMinutes = (String) comboBoxCheckinMinutes.getSelectedItem();
		
				
				String checked_out = checkoutMonth+"/"+checkoutDays+"/"+checkoutYear+" "+checkoutReserveHours+":"+checkoutReserveMinutes+":00";
				String checked_in = checkinMonth+"/"+checkinDays+"/"+checkinYear+" "+checkinReserveHours+":"+checkinReserveMinutes+":00";
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				
					
						try {
							checkedOut = (Date) sdf.parse(checked_out);
							checkedIn = (Date) sdf.parse(checked_in);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
				checkDate = bookDao.validateReturnDate(checkedOut,checkedIn,p);
				
				if(checkDate)
				{
				checkOut = bookDao.checkoutBook(checkedOut,checkedIn,book_ResourceID, p.getUnityid());
				if(checkOut)
				JOptionPane.showMessageDialog(new JFrame(), "Book has been successfully checked out");
				else
					JOptionPane.showMessageDialog(new JFrame(), "Book cannot be reserved again. You can renew your reservation");
				
				}else
					JOptionPane.showMessageDialog(new JFrame(), "Please enter valid check in date");
			}
		});
		btnConfirm.setBounds(259, 191, 89, 23);
		panelCheckOutBook.add(btnConfirm);
		
		btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBooks.setVisible(true);
				panelCheckOutBook.setVisible(false);
			}
		});
		btnBack_1.setBounds(142, 191, 89, 23);
		panelCheckOutBook.add(btnBack_1);
		
		comboBoxMonth_1 = new JComboBox();
		comboBoxMonth_1.setEditable(true);
		comboBoxMonth_1.setEnabled(true);
		comboBoxMonth_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxMonth_1.setBounds(259, 130, 77, 20);
		panelCheckOutBook.add(comboBoxMonth_1);
		
		comboDate_1 = new JComboBox();
		comboDate_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboDate_1.setEditable(true);
		comboDate_1.setBounds(358, 130, 40, 20);
		panelCheckOutBook.add(comboDate_1);
		
		comboBoxYear_1 = new JComboBox();
		comboBoxYear_1.setEditable(true);
		comboBoxYear_1.setModel(new DefaultComboBoxModel(new String[] {"2015", "2016", "2017"}));
		comboBoxYear_1.setBounds(419, 130, 62, 20);
		panelCheckOutBook.add(comboBoxYear_1);
		
		comboBoxMonth_2 = new JComboBox();
		comboBoxMonth_2.setEditable(true);
		comboBoxMonth_2.setEnabled(true);
		comboBoxMonth_2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxMonth_2.setBounds(259, 60, 77, 20);
		panelCheckOutBook.add(comboBoxMonth_2);
		
		comboDate_2 = new JComboBox();
		comboDate_2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboDate_2.setEditable(true);
		comboDate_2.setBounds(358, 60, 40, 20);
		panelCheckOutBook.add(comboDate_2);
		
		comboBoxYear_2 = new JComboBox();
		comboBoxYear_2.setEditable(true);
		comboBoxYear_2.setModel(new DefaultComboBoxModel(new String[] {"2015", "2016", "2017"}));
		comboBoxYear_2.setBounds(419, 60, 62, 20);
		panelCheckOutBook.add(comboBoxYear_2);
		
		comboBoxCheckOutHours = new JComboBox();
		comboBoxCheckOutHours.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
		comboBoxCheckOutHours.setBounds(508, 60, 40, 20);
		panelCheckOutBook.add(comboBoxCheckOutHours);
		
		comboBoxCheckinHours = new JComboBox();
		comboBoxCheckinHours.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
		comboBoxCheckinHours.setBounds(508, 130, 40, 20);
		panelCheckOutBook.add(comboBoxCheckinHours);
		
		comboBoxCheckoutMinutes = new JComboBox();
		comboBoxCheckoutMinutes.setModel(new DefaultComboBoxModel(new String[] {"00", "30"}));
		comboBoxCheckoutMinutes.setBounds(572, 60, 40, 20);
		panelCheckOutBook.add(comboBoxCheckoutMinutes);
		
		comboBoxCheckinMinutes = new JComboBox();
		comboBoxCheckinMinutes.setModel(new DefaultComboBoxModel(new String[] {"00", "30"}));
		comboBoxCheckinMinutes.setBounds(572, 130, 40, 20);
		panelCheckOutBook.add(comboBoxCheckinMinutes);
		
		JLabel lblMonth_1 = new JLabel("Month");
		lblMonth_1.setBounds(258, 38, 46, 14);
		panelCheckOutBook.add(lblMonth_1);
		
		JLabel lblDate_1 = new JLabel("Date");
		lblDate_1.setBounds(358, 35, 46, 14);
		panelCheckOutBook.add(lblDate_1);
		
		JLabel lblYear_1 = new JLabel("Year");
		lblYear_1.setBounds(419, 35, 46, 14);
		panelCheckOutBook.add(lblYear_1);
		
		JLabel lblHours_1 = new JLabel("Hours");
		lblHours_1.setBounds(508, 35, 46, 14);
		panelCheckOutBook.add(lblHours_1);
		
		JLabel lblMins_1 = new JLabel("Mins");
		lblMins_1.setBounds(572, 35, 46, 14);
		panelCheckOutBook.add(lblMins_1);
		
		tableConfProc = new JTable(dataConfProceedings,columnConfProceedings);
	
		scrollPaneConfProc = new JScrollPane();
		scrollPaneConfProc.setBounds(113, 5, 452, 427);
		scrollPaneConfProc.setViewportView(tableConfProc);
		
		panelConfProceedings = new JPanel();
		panelConfProceedings.setLayout(null);
		panelConfProceedings.add(scrollPaneConfProc);
		
		frame.getContentPane().add(panelConfProceedings, "name_905084396244979");	
		
		JButton btnHome_3 = new JButton("Home");
		btnHome_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelConfProceedings.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		btnHome_3.setBounds(10, 209, 89, 23);

		panelConfProceedings.add(btnHome_3);

		btnCheckoutConfProc = new JButton("Check out");
		btnCheckoutConfProc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelConfProceedings.setVisible(false);
				panelCheckoutConfProc.setVisible(true);
			}
		});
		btnCheckoutConfProc.setBounds(571, 206, 117, 29);
		panelConfProceedings.add(btnCheckoutConfProc);
		
		panelOccupyARoom = new JPanel();
		frame.getContentPane().add(panelOccupyARoom, "name_3754810226687");
		panelOccupyARoom.setLayout(null);
		
		JLabel lblEnterRoomNumber = new JLabel("Enter Room number:");
		lblEnterRoomNumber.setBounds(10, 29, 134, 14);
		panelOccupyARoom.add(lblEnterRoomNumber);
		
		btnOccupy = new JButton("Occupy");
		btnOccupy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String room_num = (String)comboBoxRoomNum.getSelectedItem();
		        if (room_num == "")
		        	JOptionPane.showMessageDialog(panelOccupyARoom, "Please enter the room number.");

		        java.util.Date occupyTime = null;
				try {
					occupyTime = sdf.parse(sdf.format(cal.getTime()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        String message = checkoutDao.occupyRoom(p, room_num, occupyTime);
				JOptionPane.showMessageDialog(panelHome, message);
				panelOccupyARoom.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		btnOccupy.setBounds(109, 73, 89, 23);
		panelOccupyARoom.add(btnOccupy);
		
		comboBoxRoomNum = new JComboBox();
		comboBoxRoomNum.setBounds(154, 26, 102, 20);
		panelOccupyARoom.add(comboBoxRoomNum);
		comboBoxRoomNum.setModel(new DefaultComboBoxModel(roomDao.getRoomNumbers().toArray()));
		
		btnOccupyBack = new JButton("Back");
		btnOccupyBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelOccupyARoom.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		btnOccupyBack.setBounds(10, 73, 89, 23);
		panelOccupyARoom.add(btnOccupyBack);
		
		panelReserved = new JPanel();
		frame.getContentPane().add(panelReserved, "name_1048547397801795");
		panelReserved.setLayout(null);
		
		comboBoxHours = new JComboBox();
		comboBoxHours.setBounds(250, 113, 70, 20);
		comboBoxHours.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		panelReserved.add(comboBoxHours);
		
		JLabel lblNewLabel_1 = new JLabel("No.of hours");
		lblNewLabel_1.setBounds(92, 116, 70, 14);
		panelReserved.add(lblNewLabel_1);
		
		comboReserveMonth = new JComboBox();
		comboReserveMonth.setBounds(250, 66, 48, 20);
		comboReserveMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		panelReserved.add(comboReserveMonth);
		
		comboReserveDay = new JComboBox();
		comboReserveDay.setBounds(330, 66, 48, 20);
		comboReserveDay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		panelReserved.add(comboReserveDay);
		
		comboReserveYear = new JComboBox();
		comboReserveYear.setBounds(400, 66, 70, 20);
		comboReserveYear.setModel(new DefaultComboBoxModel(new String[] {"2015", "2016", "2017"}));
		panelReserved.add(comboReserveYear);
		
		JLabel lblCheckOutDate_1 = new JLabel("Check out date");
		lblCheckOutDate_1.setBounds(92, 69, 81, 14);
		panelReserved.add(lblCheckOutDate_1);
		
		JButton btnReserveCheckOut = new JButton("Check out");
		btnReserveCheckOut.setBounds(349, 190, 89, 23);
		btnReserveCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkoutReserveDay = (String) comboReserveDay.getSelectedItem();
				checkoutReserveMonth = (String) comboReserveMonth.getSelectedItem();
				checkoutReserveYear = (String) comboReserveYear.getSelectedItem();
				checkoutReserveHours = (String) comboReserveHours.getSelectedItem();
				checkoutReserveMinutes = (String) comboBoxReserveMinutes.getSelectedItem();
				
				checkinReserveHours = (String) comboBoxHours.getSelectedItem();
				checkinHours = Integer.parseInt(checkoutReserveHours)+Integer.parseInt(checkinReserveHours);
				
				String checked_out = checkoutReserveMonth+"/"+checkoutReserveDay+"/"+checkoutReserveYear+" "+checkoutReserveHours+":"+checkoutReserveMinutes+":00";
				String checked_in = checkoutReserveMonth+"/"+checkoutReserveDay+"/"+checkoutReserveYear+" "+Integer.toString(checkinHours)+":"+checkoutReserveMinutes+":00";
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				
					
						try {
							checkedOut = (Date) sdf.parse(checked_out);
							checkedIn = (Date) sdf.parse(checked_in);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				
				if(bookDao.validateReturnDateReserved(checkedOut, checkedIn))
				{
				canCheckOut = bookDao.checkoutBook(checkedOut,checkedIn, book_ResourceID, p.getUnityid());
				if(canCheckOut){
					JOptionPane.showMessageDialog(new JFrame(), "Book has been successfully reserved");
				}
				else
					JOptionPane.showMessageDialog(new JFrame(), "Book cannot be reserved again. You can renew the book");
				}
				
			}
		});
		panelReserved.add(btnReserveCheckOut);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelReserved.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(231, 190, 89, 23);
		panelReserved.add(btnNewButton_1);
		
		comboReserveHours = new JComboBox();
		comboReserveHours.setBounds(498, 66, 46, 20);
		comboReserveHours.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
		panelReserved.add(comboReserveHours);
		
		comboBoxReserveMinutes = new JComboBox();
		comboBoxReserveMinutes.setBounds(567, 66, 54, 20);
		comboBoxReserveMinutes.setModel(new DefaultComboBoxModel(new String[] {"00", "30"}));
		panelReserved.add(comboBoxReserveMinutes);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(250, 52, 46, 14);
		panelReserved.add(lblMonth);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(330, 52, 46, 14);
		panelReserved.add(lblDate);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(400, 52, 46, 14);
		panelReserved.add(lblYear);
		
		JLabel lblHours = new JLabel("Hours");
		lblHours.setBounds(498, 52, 46, 14);
		panelReserved.add(lblHours);
		

		JLabel lblMins = new JLabel("Mins");
		lblMins.setBounds(567, 52, 46, 14);
		panelReserved.add(lblMins);

		
		
		panelCheckoutConfProc = new JPanel();
		frame.getContentPane().add(panelCheckoutConfProc, "name_1131552033303529");
		panelCheckoutConfProc.setLayout(null);
		
		JLabel label_9 = new JLabel("");
		label_9.setBounds(64, 46, 61, 16);
		panelCheckoutConfProc.add(label_9);
		
		JLabel lblEnterTheCheckout = new JLabel("Enter checkout time");
		lblEnterTheCheckout.setBounds(37, 75, 212, 23);
		panelCheckoutConfProc.add(lblEnterTheCheckout);
		
		txtCheckoutTimeConfProc = new JTextField();
		txtCheckoutTimeConfProc.setBounds(189, 73, 130, 26);
		panelCheckoutConfProc.add(txtCheckoutTimeConfProc);
		txtCheckoutTimeConfProc.setColumns(10);
		
		JLabel lblHhmm = new JLabel("hh24:mm");
		lblHhmm.setBounds(366, 78, 61, 16);
		panelCheckoutConfProc.add(lblHhmm);
		
		lblEnterDueDate = new JLabel("Enter return time");
		lblEnterDueDate.setBounds(37, 175, 212, 23);
		panelCheckoutConfProc.add(lblEnterDueDate);
		
		txtReturnTimeConfProc = new JTextField();
		txtReturnTimeConfProc.setColumns(10);
		txtReturnTimeConfProc.setBounds(189, 173, 130, 26);
		panelCheckoutConfProc.add(txtReturnTimeConfProc);
		
		label_10 = new JLabel("hh24:mm");
		label_10.setBounds(366, 178, 61, 16);
		panelCheckoutConfProc.add(label_10);
		
		lblEnterCheckoutDate_1 = new JLabel("Enter checkout date");
		lblEnterCheckoutDate_1.setBounds(37, 27, 130, 16);
		panelCheckoutConfProc.add(lblEnterCheckoutDate_1);
		
		txtCheckoutDateConfProc = new JTextField();
		txtCheckoutDateConfProc.setBounds(189, 22, 130, 26);
		panelCheckoutConfProc.add(txtCheckoutDateConfProc);
		txtCheckoutDateConfProc.setColumns(10);
		
		lblMmddyyyy = new JLabel("MM/DD/YYYY");
		lblMmddyyyy.setBounds(366, 27, 180, 16);
		panelCheckoutConfProc.add(lblMmddyyyy);
		
		lblEnterReturnDate = new JLabel("Enter return date");
		lblEnterReturnDate.setBounds(37, 128, 130, 16);
		panelCheckoutConfProc.add(lblEnterReturnDate);
		
		txtReturnDateConfProc = new JTextField();
		txtReturnDateConfProc.setColumns(10);
		txtReturnDateConfProc.setBounds(189, 123, 130, 26);
		panelCheckoutConfProc.add(txtReturnDateConfProc);
		
		label_12 = new JLabel("MM/DD/YYYY");
		label_12.setBounds(366, 128, 180, 16);
		panelCheckoutConfProc.add(label_12);
		
		btnCheckOutConfEnter = new JButton("Enter");
		btnCheckOutConfEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String checked_out = null;
				int selectedRow = tableConfProc.getSelectedRow();
				Object dataConfProceedings[][] = ConfProceedingData.getConfProceedingData(confProcDao.getAllConfProceedings());
				int resource_id = (Integer)dataConfProceedings[selectedRow][4];
				String startTime = txtCheckoutTimeConfProc.getText();
				String endTime = txtReturnTimeConfProc.getText();
				String checkedOutDate = txtCheckoutDateConfProc.getText();
				String dueDate = txtReturnDateConfProc.getText();
				String check_outDateTime = checkedOutDate + " " + startTime + ":00";
				String due_dateTime = dueDate +  " " + endTime + ":00";
				if(!isValidConfDueDateTime(check_outDateTime, due_dateTime, 12)){
					JOptionPane.showMessageDialog(panelCheckoutConfProc, "Invalid Return Date and/or Return Time. It should be less than 12 hours.");
				}
				else {
					boolean b = confProcDao.checkoutConfProceeding(p, resource_id, check_outDateTime, due_dateTime);
					if (b == false)
					{
							  
							JOptionPane.showMessageDialog(new JFrame(), "Conference Proceeding is currently not availabe");
						
							
							panelConfProceedings.setVisible(true);
							panelCheckoutConfProc.setVisible(false);
							
							String choice = JOptionPane.showInputDialog("Do you want to be added to the wait list?");
							if("Yes".equals(choice)){
								
								
								int waitListed = confProcDao.addToWaitList(resource_id,p.getUnityid(), check_outDateTime);
								if(waitListed > 0)
								JOptionPane.showMessageDialog(new JFrame(), "You have been added to wait list."+"\n"+"A notification will be sent once the book is available");
								else
									JOptionPane.showMessageDialog(new JFrame(), "You are already waitlisted for this resource");
							}
							
						}
					else JOptionPane.showMessageDialog(new JFrame(), "You have checkedout the Conference Proceeding successfully");

				}
				}
		});
		btnCheckOutConfEnter.setBounds(89, 256, 117, 29);
		panelCheckoutConfProc.add(btnCheckOutConfEnter);
		
		JButton btnReturnHomeConfProc = new JButton("Home");
		btnReturnHomeConfProc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCheckoutConfProc.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		btnReturnHomeConfProc.setBounds(301, 256, 117, 29);
		panelCheckoutConfProc.add(btnReturnHomeConfProc);
		//panelRenew.add(lblTodaysDate);		
		panelCheckedOutResources = new JPanel();
		frame.getContentPane().add(panelCheckedOutResources, "name_23933522607633");
		
		btnCCResourcesHome = new JButton("Home");
		btnCCResourcesHome.setBounds(10, 37, 59, 23);
		btnCCResourcesHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCheckedOutResources.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		panelCheckedOutResources.setLayout(null);
		panelCheckedOutResources.add(btnCCResourcesHome);
		btnCCResourcesHome.setMargin(new Insets(0,0,0,0));
		
		scrollPaneCheckedOutRes = new JScrollPane();
		scrollPaneCheckedOutRes.setBounds(10, 75, 658, 282);
		panelCheckedOutResources.add(scrollPaneCheckedOutRes);
		
		btnCCResourcesRenew = new JButton("Renew");
		btnCCResourcesRenew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Object[][] dataCCResources = CheckedOutResourcesData.getCCData(patronDao.getCheckedOutResources(p));
				
				int row = tblCheckedOutResources.getSelectedRow();

				resource_id_1 = (Integer) dataCCResources[row][0];
				canRenew = patronDao.mapResource(resource_id_1);
				panelRenew.setVisible(true);
				panelCheckedOutResources.setVisible(false);
				
				
			}});

				
		panelCheckedOutResources.add(btnCCResourcesRenew);
		
		
		
		
		panelLateFee = new JPanel();
		
		frame.getContentPane().add(panelLateFee, "name_1121709172949838");
		//panelLateFee.setLayout(null);
		
		btnReserve = new JButton("Reserve");
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelFacultyReserveBook.setVisible(true);
				panelBooks.setVisible(false);

			}
		});
		
		btnReserve.setBounds(177, 11, 89, 23);
		panelBooks.add(btnReserve);
		
		panelFacultyReserveBook = new JPanel();
		frame.getContentPane().add(panelFacultyReserveBook, "name_1226975220640576");
		panelFacultyReserveBook.setLayout(null);
		
		textFieldReserveStart = new JTextField();
		textFieldReserveStart.setBounds(268, 52, 86, 20);
		panelFacultyReserveBook.add(textFieldReserveStart);
		textFieldReserveStart.setColumns(10);
		
		textFieldReserveStartTime = new JTextField();
		textFieldReserveStartTime.setBounds(368, 52, 86, 20);
		panelFacultyReserveBook.add(textFieldReserveStartTime);
		textFieldReserveStartTime.setColumns(10);
		
		textFieldReserveEnd = new JTextField();
		textFieldReserveEnd.setBounds(268, 98, 86, 20);
		panelFacultyReserveBook.add(textFieldReserveEnd);
		textFieldReserveEnd.setColumns(10);
		
		textFieldReserveEndTime = new JTextField();
		textFieldReserveEndTime.setBounds(368, 98, 86, 20);
		panelFacultyReserveBook.add(textFieldReserveEndTime);
		textFieldReserveEndTime.setColumns(10);
		
		JLabel lblStartReservation = new JLabel("Start reservation");
		lblStartReservation.setBounds(139, 55, 86, 14);
		panelFacultyReserveBook.add(lblStartReservation);
		
		JLabel lblEndReservation = new JLabel("End reservation");
		lblEndReservation.setBounds(139, 101, 84, 14);
		panelFacultyReserveBook.add(lblEndReservation);
		
		JButton btnReserveConfirm = new JButton("Confirm");
		btnReserveConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableBooks.getSelectedRow();
				Object dataBooks[][] = BooksData.getBookData(bookDao.getAllBooks());
				int res_id = (Integer)dataBooks[row][5];
				
				
				reserveStartDate = textFieldReserveStart.getText();
				reserveStartTime = textFieldReserveStartTime.getText();
				
				reserveEndDate = textFieldReserveEnd.getText();
				reserveEndTime = textFieldReserveEndTime.getText();
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
				
				try {
					reservedStartDate = sdf.parse(reserveStartDate+" "+reserveStartTime+":00");
					reservedEndDate = sdf.parse(reserveEndDate+" "+reserveEndTime+":00");
					
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "Date is in incorrect format");
				}
				
				isValidReservation = facultyDao.checkifValidReservation(reservedStartDate,reservedEndDate);
				if(isValidReservation){
					if(facultyDao.reserveBook(res_id,p,reservedStartDate,reservedEndDate))
						JOptionPane.showMessageDialog(new JFrame(), "Book has been reserved");
					else
						JOptionPane.showMessageDialog(new JFrame(), "Books can only be reserved for courses taught by you");
				}
				else
					JOptionPane.showMessageDialog(new JFrame(), "Please enter valide date");
				
			}
		});
		btnReserveConfirm.setBounds(265, 153, 89, 23);
		panelFacultyReserveBook.add(btnReserveConfirm);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panelFacultyReserveBook.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		btnHome.setBounds(365, 153, 89, 23);
		panelFacultyReserveBook.add(btnHome);

	


		btnCCResourcesRenew.setBounds(199, 37, 65, 23);
		panelCheckedOutResources.add(btnCCResourcesRenew);
		btnCCResourcesRenew.setMargin(new Insets(0,0,0,0));
		
		btnResourceDetails = new JButton("Resource Details");
		btnResourceDetails.setBounds(79, 39, 110, 19);
		btnResourceDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = tblCheckedOutResources.getSelectedRow();
				Object[][] dataCCResources = CheckedOutResourcesData.getCCData(patronDao.getCheckedOutResources(p));
				int resource_id = (Integer)dataCCResources[selectedRow][0];
				char resource_type = patronDao.mapResource(resource_id);
				switch(resource_type){
				case 'B':
					Object[] columnBooks = {"ISBN", "Title", "Edition", "Year Published", "Publisher"};
					Object[][] dataBooks = BooksData.getBookData(patronDao.getBook(resource_id));
					tblCheckedOutResourcesDetails = new JTable(dataBooks,columnBooks);
					break;
				case 'J':
					Object[] columnJournals = {"ISSN", "Resource ID", "Title", "Year Published"};
					Object[][] dataJournals = JournalsData.getJournalData(patronDao.getJournal(resource_id));
					tblCheckedOutResourcesDetails = new JTable(dataJournals,columnJournals);
					break;
				case 'P':
					Object[] columnConfProc = {"Conference Number", "Resource ID", "Title", "Year Published", "Conference Name"};
					Object[][] dataConfProc = ConfProceedingData.getConfProceedingData(patronDao.getConfProc(resource_id));
					tblCheckedOutResourcesDetails = new JTable(dataConfProc,columnConfProc);
					break;
				case 'C':
					Object[] columnCamera = {"Camera ID", "Make", "Model", "Lens Configuration", "Available Memory"};
					Object[][] dataCamera = CamerasData.getCameraData(patronDao.getCamera(resource_id));
					tblCheckedOutResourcesDetails = new JTable(dataCamera,columnCamera);
					break;
				case 'R':
					Object[] columnRooms = {"Room Number", "Capacity", "Floor", "Type"};
					Object[][] dataRooms = RoomsData.getRoomData(patronDao.getRoom(resource_id));
					tblCheckedOutResourcesDetails = new JTable(dataRooms,columnRooms);
					break;
				}
				scrollPaneCCResDetails.setViewportView(tblCheckedOutResourcesDetails);
				panelCheckedOutResources.setVisible(false);
				panelCCResourcesDetails.setVisible(true);
			}
			});
		
		lblSelectRowFrom = new JLabel("Select row from table and click corresponding action");
		lblSelectRowFrom.setBounds(10, 15, 279, 14);
		panelCheckedOutResources.add(lblSelectRowFrom);
		panelCheckedOutResources.add(btnResourceDetails);
		btnResourceDetails.setMargin(new Insets(0,0,0,0));
		

		JButton btnCheckInResource = new JButton("Check in Resource");
		btnCheckInResource.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tblCheckedOutResources.getSelectedRow();
				
				Object[][] dataCCResources = CheckedOutResourcesData.getCCData(patronDao.getCheckedOutResources(p));
				int resource_id = (Integer)dataCCResources[selectedRow][0];
				char resource_type = patronDao.mapResource(resource_id);
				switch(resource_type){
				case 'B':
					if(bookDao.checkinBook(resource_id,p)>0)
						JOptionPane.showMessageDialog(new JFrame(), "Book has been successfully checked in");
					panelCheckedOutResources.repaint();
					break;
				case 'J':
					if(journalDao.checkinJournal(resource_id,p)>0)
						JOptionPane.showMessageDialog(new JFrame(), "Journal has been successfully checked in");
					panelCheckedOutResources.repaint();
					break;
				case 'P':
					if(confProcDao.checkinConfProc(resource_id,p)>0)
						JOptionPane.showMessageDialog(new JFrame(), "Conference Proceeding has been successfully checked in");
					panelCheckedOutResources.repaint();
					break;
				case 'C':
					if(cameraDao.checkinCamera(resource_id,p)>0)
						JOptionPane.showMessageDialog(new JFrame(), "Camera has been successfully checked in");
					panelCheckedOutResources.repaint();
					break;
				case 'R':
					JOptionPane.showMessageDialog(new JFrame(), "Rooms can be checked in from the Occupy Room Tab");
					break;
				}
				
			}
		});
		btnCheckInResource.setBounds(286, 37, 128, 23);
		panelCheckedOutResources.add(btnCheckInResource);
		panelCCResourcesDetails = new JPanel();
		frame.getContentPane().add(panelCCResourcesDetails, "name_3490726350645");

		btnCCResDetailsBack = new JButton("Back");
		btnCCResDetailsBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCCResourcesDetails.setVisible(false);
				panelCheckedOutResources.setVisible(true);
			}
		});

		
		scrollPaneCCResDetails = new JScrollPane();

		
		

		panelCCResourcesDetails.add(scrollPaneCCResDetails);
		panelCCResourcesDetails.add(btnCCResDetailsBack);
		scrollPaneCCResDetails.setBounds(664, 360, -653, -303);
		
		panelEbook = new JPanel();
		frame.getContentPane().add(panelEbook, "name_15000452752149");
		panelEbook.setLayout(null);
		
		JButton btnDisplayEBooksHome = new JButton("Home");
		btnDisplayEBooksHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelHome.setVisible(true);
				panelEbook.setVisible(false);
			}
		});
		btnDisplayEBooksHome.setBounds(41, 142, 103, 23);
		panelEbook.add(btnDisplayEBooksHome);
		
		JButton btnEBookCheckOut = new JButton("Check-Out");
		btnEBookCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCheckoutEbooks.setVisible(true);
				panelEbook.setVisible(false);
			}
		});
		btnEBookCheckOut.setBounds(41, 202, 103, 23);
		panelEbook.add(btnEBookCheckOut);
		
		scrollPaneTableEBooks = new JScrollPane();
		scrollPaneTableEBooks.setBounds(162, 0, 452, 427);
		panelEbook.add(scrollPaneTableEBooks);
		
		panelCheckoutEbooks = new JPanel();
		frame.getContentPane().add(panelCheckoutEbooks, "name_23919476606550");
		panelCheckoutEbooks.setLayout(null);
		
		JLabel label_111 = new JLabel("");
		label_111.setBounds(205, 152, 61, 16);
		panelCheckoutEbooks.add(label_111);
		
		JLabel label_13 = new JLabel("Enter checkout time");
		label_13.setBounds(47, 103, 212, 23);
		panelCheckoutEbooks.add(label_13);
		
		txtCheckOutDateEbook = new JTextField();
		txtCheckOutDateEbook.setColumns(10);
		txtCheckOutDateEbook.setBounds(181, 54, 130, 26);
		panelCheckoutEbooks.add(txtCheckOutDateEbook);
		
		txtCheckOutTimeEbook = new JTextField();
		txtCheckOutTimeEbook.setColumns(10);
		txtCheckOutTimeEbook.setBounds(181, 101, 130, 26);
		panelCheckoutEbooks.add(txtCheckOutTimeEbook);
		
		JLabel label_16 = new JLabel("hh24:mm");
		label_16.setBounds(350, 106, 61, 16);
		panelCheckoutEbooks.add(label_16);
		
		JLabel label_17 = new JLabel("Enter checkout date");
		label_17.setBounds(47, 59, 130, 16);
		panelCheckoutEbooks.add(label_17);
		
		JLabel label_20 = new JLabel("MM/DD/YYYY");
		label_20.setBounds(350, 59, 180, 16);
		panelCheckoutEbooks.add(label_20);
		
		JButton button_2 = new JButton("Enter");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String checked_out = null;
				int selectedRow = tableEBooks.getSelectedRow();
				Object dataEbooks[][] = EbookData.getEbookData(ebookDao.getAllEBooks());
				int resource_id = (Integer)dataEbooks[selectedRow][5];
				String startTime = txtCheckOutTimeEbook.getText();
				String endTime = "12:00";
				String checkedOutDate = txtCheckOutDateEbook.getText();
				String dueDate = "1/5/2100";
				String check_outDateTime = checkedOutDate + " " + startTime + ":00";
				String due_dateTime = dueDate +  " " + endTime + ":00";
				boolean b = ebookDao.checkoutEBook(p, resource_id, check_outDateTime, due_dateTime);
				if(b){
					JOptionPane.showMessageDialog(panelCheckoutEbooks, "Ebook Check out Successful");
				}
				else {
					JOptionPane.showMessageDialog(panelCheckoutEbooks, "You have already checked out this resource before.");
				}
			}
		});
		button_2.setBounds(108, 273, 117, 29);
		panelCheckoutEbooks.add(button_2);
		
		JButton button_3 = new JButton("Home");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCheckoutEbooks.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		button_3.setBounds(274, 273, 117, 29);
		panelCheckoutEbooks.add(button_3);

		scrollPaneCCResDetails.setBounds(664, 360, -653, -303);

		panelCCResourcesDetails.add(scrollPaneCCResDetails);
		

		panelReqResources = new JPanel();

		frame.getContentPane().add(panelReqResources, "name_1173081242563834");
		
		panelRenew = new JPanel();
		frame.getContentPane().add(panelRenew, "name_1205199817290785");
		panelRenew.setLayout(null);
		
		lblNewLabel_2 = new JLabel("Return Date");
		lblNewLabel_2.setBounds(132, 71, 78, 14);
		panelRenew.add(lblNewLabel_2);
		
		textFieldRenewDate = new JTextField();
		textFieldRenewDate.setBounds(220, 68, 86, 20);
		panelRenew.add(textFieldRenewDate);
		textFieldRenewDate.setColumns(10);
		
		btnNewButton = new JButton("Confirm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				renewDay = textFieldRenewDate.getText();
				renewTime = textFieldRenewTime.getText();
				renewDate = renewDay+" "+renewTime+":00";
				
				Date today = new Date();
				Date renewalDate = null;
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
				
				String todayDate = sdf.format(today);
				
				try {
					today = sdf.parse(todayDate);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				switch (canRenew) {

				case 'B':
					
					try {
						renewalDate = sdf.parse(renewDate);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if (bookDao.checkifReserved(resource_id_1) != 0) {
						
						if(bookDao.validateReturnDateReserved(today, renewalDate)){
							if(bookDao.renewBook(resource_id_1,p.getUnityid(),renewalDate)>0)
								JOptionPane.showMessageDialog(new JFrame(), "Book has been renewed");
						}
						else{
							JOptionPane.showMessageDialog(new JFrame(), "Enter valid date");
						}
						
						
						
					} else {
						if(bookDao.validateReturnDate(today, renewalDate, p)){
							if(bookDao.renewBook(resource_id_1,p.getUnityid(),renewalDate)>0)
								JOptionPane.showMessageDialog(new JFrame(), "Book has been renewed");
						}
						else{
							JOptionPane.showMessageDialog(new JFrame(), "Enter valid date");
						}
					}
					break;
				case 'E':
					JOptionPane.showMessageDialog(new JFrame(), "Ebooks cannot be renewed");
					break;
				case 'J':
					if(isValidConfDueDateTime(todayDate, renewDate, 12)){
						if(journalDao.renewJournal(resource_id_1,p.getUnityid(),renewalDate)>0)
							JOptionPane.showMessageDialog(new JFrame(), "Conference Proceeding has been renewed");
					}
					
					break;

				case 'P':
					
					if(isValidConfDueDateTime(todayDate, renewDate, 12)){
						if(confProcDao.renewConfProc(resource_id_1,p.getUnityid(),renewalDate)>0)
							JOptionPane.showMessageDialog(new JFrame(), "Conference Proceeding has been renewed");
					}
					
					else{
						JOptionPane.showMessageDialog(new JFrame(), "Enter valid date");
					}
					break;
				case 'R':
					JOptionPane.showMessageDialog(new JFrame(), "Resource cannot be renewed");
					break;
				case 'C':
					JOptionPane.showMessageDialog(new JFrame(), "Resource cannot be renewed");
					break;

				}
			}
				
	
		});
		btnNewButton.setBounds(252, 117, 89, 23);
		panelRenew.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCheckedOutResources.setVisible(true);
				panelRenew.setVisible(false);
			}
		});
		btnBack.setBounds(132, 117, 89, 23);
		panelRenew.add(btnBack);
		
		textFieldRenewTime = new JTextField();
		textFieldRenewTime.setBounds(356, 68, 86, 20);
		panelRenew.add(textFieldRenewTime);
		textFieldRenewTime.setColumns(10);
		
		JLabel lblDate_2 = new JLabel("Date in dd/mm/yyyy");
		lblDate_2.setBounds(220, 46, 121, 14);
		panelRenew.add(lblDate_2);
		
		JLabel lblTimeInHhmm = new JLabel("Time in hh:mm");
		lblTimeInHhmm.setBounds(356, 43, 96, 14);
		panelRenew.add(lblTimeInHhmm);

		frame.getContentPane().add(panelReqResources, "name_1173081242563834");
		
		panelJournals = new JPanel();
		frame.getContentPane().add(panelJournals, "name_16670215316820");
		panelJournals.setLayout(null);
		
		tblJournals = new JTable(dataJournals, columnJournals);
		scrollPanedisplayJournals = new JScrollPane();
		scrollPanedisplayJournals.setBounds(6, 6, 682, 336);
		scrollPanedisplayJournals.setViewportView(tblJournals);
		panelJournals.add(scrollPanedisplayJournals);
		
		JButton btnJournalHome = new JButton("Home");
		btnJournalHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelHome.setVisible(true);
				panelJournals.setVisible(false);
			}
		});
		btnJournalHome.setBounds(86, 354, 117, 29);
		panelJournals.add(btnJournalHome);
		
		btnCheckoutJournal = new JButton("Check Out");
		btnCheckoutJournal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelJournals.setVisible(false);
				panelCheckoutJournals.setVisible(true);			
				}
		});
		btnCheckoutJournal.setBounds(448, 350, 117, 29);
		panelJournals.add(btnCheckoutJournal);
		
		panelCheckoutJournals = new JPanel();
		panelCheckoutJournals.setLayout(null);
		frame.getContentPane().add(panelCheckoutJournals, "name_17418628663983");
		
		label_14 = new JLabel("");
		label_14.setBounds(64, 46, 61, 16);
		panelCheckoutJournals.add(label_14);
		
		label_15 = new JLabel("Enter checkout time");
		label_15.setBounds(37, 75, 212, 23);
		panelCheckoutJournals.add(label_15);
		
		textCheckOutTimeJournal = new JTextField();
		textCheckOutTimeJournal.setColumns(10);
		textCheckOutTimeJournal.setBounds(189, 73, 130, 26);
		panelCheckoutJournals.add(textCheckOutTimeJournal);
		
		label_18 = new JLabel("hh24:mm");
		label_18.setBounds(366, 78, 61, 16);
		panelCheckoutJournals.add(label_18);
		
		label_19 = new JLabel("Enter return time");
		label_19.setBounds(37, 175, 212, 23);
		panelCheckoutJournals.add(label_19);
		
		textReturnTimeJournal = new JTextField();
		textReturnTimeJournal.setColumns(10);
		textReturnTimeJournal.setBounds(189, 173, 130, 26);
		panelCheckoutJournals.add(textReturnTimeJournal);
		
		label_21 = new JLabel("hh24:mm");
		label_21.setBounds(366, 178, 61, 16);
		panelCheckoutJournals.add(label_21);
		
		label_22 = new JLabel("Enter checkout date");
		label_22.setBounds(37, 27, 130, 16);
		panelCheckoutJournals.add(label_22);
		
		textCheckOutDateJournal = new JTextField();
		textCheckOutDateJournal.setColumns(10);
		textCheckOutDateJournal.setBounds(189, 22, 130, 26);
		panelCheckoutJournals.add(textCheckOutDateJournal);
		
		label_23 = new JLabel("MM/DD/YYYY");
		label_23.setBounds(366, 27, 180, 16);
		panelCheckoutJournals.add(label_23);
		
		label_24 = new JLabel("Enter return date");
		label_24.setBounds(37, 128, 130, 16);
		panelCheckoutJournals.add(label_24);
		
		textReturnDateJournal = new JTextField();
		textReturnDateJournal.setColumns(10);
		textReturnDateJournal.setBounds(189, 123, 130, 26);
		panelCheckoutJournals.add(textReturnDateJournal);
		
		label_25 = new JLabel("MM/DD/YYYY");
		label_25.setBounds(366, 128, 180, 16);
		panelCheckoutJournals.add(label_25);
		
		button = new JButton("Enter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String checked_out = null;
				int selectedRow = ((JTable) tblJournals).getSelectedRow();
				Object dataJournals[][] = JournalsData.getJournalData(journalDao.getAllJournals());
				int resource_id = (Integer)dataJournals[selectedRow][1];
				String startTime = textCheckOutTimeJournal.getText();
				String endTime = textReturnTimeJournal.getText();
				String checkedOutDate = textCheckOutDateJournal.getText();
				String dueDate = textReturnDateJournal.getText();
				String check_outDateTime = checkedOutDate + " " + startTime + ":00";
				String due_dateTime = dueDate +  " " + endTime + ":00";
				if(!isValidConfDueDateTime(check_outDateTime, due_dateTime, 12)){
					JOptionPane.showMessageDialog(panelCheckoutConfProc, "Invalid Return Date and/or Return Time. It should be less than 12 hours.");
				}
				else {
					boolean b = journalDao.checkoutJournals(p, resource_id, check_outDateTime, due_dateTime);
					if (b == false)
					{
							  
							JOptionPane.showMessageDialog(new JFrame(), "Journal is currently not availabe");
						
							
							panelJournals.setVisible(true);
							panelCheckoutJournals.setVisible(false);
							
							String choice = JOptionPane.showInputDialog("Do you want to be added to the wait list?");
							if("Yes".equals(choice)){
								
								
								int waitListed = journalDao.addToWaitList(resource_id,p.getUnityid(), check_outDateTime);
								if(waitListed > 0)
								JOptionPane.showMessageDialog(new JFrame(), "You have been added to wait list."+"\n"+"A notification will be sent once the book is available");
								else
									JOptionPane.showMessageDialog(new JFrame(), "You are already waitlisted for this resource");
							}
							
						}
					else JOptionPane.showMessageDialog(new JFrame(), "You have successfully checked out the journal.");
				}
			}
		});
		button.setBounds(89, 256, 117, 29);
		panelCheckoutJournals.add(button);
		
		button_1 = new JButton("Home");
		button_1.setBounds(301, 256, 117, 29);
		panelCheckoutJournals.add(button_1);
		
		panelNotification = new JPanel();
		frame.getContentPane().add(panelNotification, "name_12602259332795");
		panelNotification.setLayout(null);
		
		scrollPaneNotifications = new JScrollPane();
		scrollPaneNotifications.setBounds(10, 45, 658, 312);
		panelNotification.add(scrollPaneNotifications);
		
		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelNotification.setVisible(false);
				panelHome.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 11, 55, 23);
		panelNotification.add(btnNewButton);
		btnNewButton.setMargin(new Insets(0,0,0,0));
		
		panelNotifications = new JPanel();
		
		JButton btnNewButton_9 = new JButton("Back");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelHome.setVisible(true);
				scrollPaneNotifications.setVisible(false);
			}
		});
		
		
		scrollPaneTableNotif = new JScrollPane();
		
		
		JTable tableNotif = new JTable(dataNotifications,columnNotifications);
		//frame.getContentPane().add(tableNotif, "name_257662324463943");
		tableNotif.setCellSelectionEnabled(true);
		tableNotif.setColumnSelectionAllowed(true);
		
		scrollPaneTableNotif.setViewportView(tableNotif);
		
		panelNotifications.add(btnNewButton_9);
		panelNotifications.add(scrollPaneTableNotif);
		

	}

	public boolean isValidDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		java.util.Date testDate = null;
		try {
			testDate = (java.util.Date) sdf.parse(date);
		}

		catch (ParseException e) {
			return false;
		}
		if (!sdf.format(testDate).equals(date)) {
			return false;
		}
		return true;
	}
	
	public boolean isValidConfDueDateTime(String checkout_dateTime, String dueDateTime, int limit){

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
		long diff = -1, diffHours = -1;
		try {
			d1 = format.parse(checkout_dateTime);
			d2 = format.parse(dueDateTime);
			diff = d2.getTime() - d1.getTime();

			diffHours = diff / (60 * 60 * 1000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (diffHours > limit || diffHours < 0)
			return false;
		else
			return true;

	}

	public void actionPerformed(ActionEvent event) {

		if (event.getSource() == btnLogin) {
			String unity = txtUnity.getText();
			String pwd = passwordField.getText();
			p = patronDao.loginSuccsessful(unity, pwd);
			if (p != null) {
				panelHome.setVisible(true);
				panelLogin.setVisible(false);
				lblWelcomeUser.setText("Welcome, " + p.getFirstname());
				boolean b = studentDao.isStudent(unity);
				if (b) {
					students = studentDao.getStudent(unity);
					if (!students.isEmpty()) {
						s = students.get(0);
					}
					txtStdUnity.setText(unity);
					txtStdPwd.setText(pwd);
					txtStdFirst.setText(p.getFirstname());
					txtStdLast.setText(p.getLastname());
					txtStdDept.setText(p.getDept());
					txtStdNat.setText(p.getNationality());
					txtStdSex.setText(p.getSex());
					txtStdNum.setText(s.getStdNumber());
					txtStdCat.setText(s.getStdCat());
					txtStdClsf.setText(s.getStdClassification());
					txtStdDeg.setText(s.getDegreeProgram());
					txtHold.setText(((Integer) s.getHold()).toString());
					txtStdDob.setText((s.getDob()).toString());
					txtStdStreet.setText(s.getStreet());
					txtStdCity.setText(s.getCity());
					txtStdZip.setText(((Integer) s.getPostalCode()).toString());
					txtStdPhone.setText(((Integer) s.getPhoneNum()).toString());
					txtStdAltPh.setText(((Integer) s.getAltPhone()).toString());
					lblHoldNote.setVisible(false);
					if (s.getHold() == 1) {
						btnResources.setEnabled(false);
						lblHoldNote.setVisible(true);
					}
				} else {
					faculty = facultyDao.getFaculty(unity);
					f = faculty.get(0);
					txtFacUnity.setText(unity);
					textFacPwd.setText(pwd);
					txtFacFirst.setText(p.getFirstname());
					txtFacLast.setText(p.getLastname());
					txtFacDept.setText(p.getDept());
					txtFacNat.setText(p.getNationality());
					txtFacSex.setText(p.getSex());
					txtFacCat.setText(f.getCategory());
					txtFacNum.setText(f.getFacultyNumber().toString());
					lblHoldNote.setVisible(false);
				}
			} else {
				JOptionPane.showMessageDialog(panelLogin, "Invalid Credentials");
			}
		}

	}
}
