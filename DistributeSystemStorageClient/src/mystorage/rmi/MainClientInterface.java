package mystorage.rmi;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

// TODO: Auto-generated Javadoc
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The Class MainClientInterface.
 *
 * @author Lam
 */
public class MainClientInterface extends JFrame {

	/** The icon_home. */
	ImageIcon icon_home = new ImageIcon(getClass().getResource("pic/home.png"));

	/** The icon_right. */
	ImageIcon icon_right = new ImageIcon(getClass().getResource(
			"pic/right-arrow.png"));

	/** The icon_left. */
	ImageIcon icon_left = new ImageIcon(getClass().getResource(
			"pic/left-arrow.png"));

	/** The is done. */
	private static boolean isDone = false;

	/** The file table. */
	private JTable folderTable, fileTable;

	/** The jsp2. */
	private JScrollPane jsp, jsp2;

	/** The Log Text Area */
	private static JTextArea stateArea;

	/** The buttons */
	private JButton btnNext, btnPrev, btnSyn, btnAddFolder, btnAddFile, btnDel,
			btnHome;

	/** The current path. */
	private String currentPath = null;

	/** The current folder. */
	private String currentFolder = null;

	/** The lb folder. */
	private JLabel lbFolder;

	/** The is. */
	private InputStream is = null;

	/** The os. */
	private OutputStream os = null;

	/** The root file path. */
	private static String rootFilePath;

	/** The client. */
	private static FileClientInt client;

	/** The server. */
	private static FileServerInt server;

	/** The sync state. */
	private static String syncState = "Prepare to sync";

	/**
	 * Instantiates a new main client interface.
	 *
	 * @param rootFilePath
	 *            the root file path
	 * @param client
	 *            the client
	 * @param server
	 *            the server
	 * @throws RemoteException
	 *             the remote exception
	 */
	public MainClientInterface(final String rootFilePath, FileClientInt client,
			FileServerInt server) throws RemoteException {
		this.rootFilePath = rootFilePath;
		this.client = client;
		this.server = server;

		folderTable = new JTable();
		fileTable = new JTable();

		folderTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		loadTable(folderTable, rootFilePath);
		jsp = new JScrollPane(folderTable);
		jsp.setBounds(150, 80, 550, 170);
		jsp.setVisible(true);
		this.add(jsp);

		fileTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		loadFileTable(fileTable, rootFilePath);
		jsp2 = new JScrollPane(fileTable);
		jsp2.setBounds(150, 270, 550, 170);
		jsp2.setVisible(true);
		this.add(jsp2);

		btnHome = new JButton("");
		btnHome.setBounds(150, 10, 70, 70);
		btnHome.setIcon(icon_home);
		this.add(btnHome);

		btnNext = new JButton("");
		btnNext.setBounds(800, 10, 70, 70);
		btnNext.setIcon(icon_right);
		this.add(btnNext);

		btnPrev = new JButton("");
		btnPrev.setBounds(720, 10, 70, 70);
		btnPrev.setIcon(icon_left);
		this.add(btnPrev);

		btnSyn = new JButton("Sync");
		btnSyn.setBounds(750, 200, 120, 30);
		this.add(btnSyn);

		btnAddFolder = new JButton("New folder");
		btnAddFolder.setBounds(750, 250, 120, 30);
		this.add(btnAddFolder);

		btnAddFile = new JButton("New file");
		btnAddFile.setBounds(750, 300, 120, 30);
		this.add(btnAddFile);

		btnDel = new JButton("Delete");
		btnDel.setBounds(750, 350, 120, 30);
		this.add(btnDel);

		currentPath = rootFilePath;
		currentFolder = getParentFolder(rootFilePath);

		lbFolder = new JLabel("Current Path: " + currentPath);
		lbFolder.setBounds(250, 20, 370, 20);
		this.add(lbFolder);

		stateArea = new JTextArea();
		stateArea.setBounds(750, 90, 115, 80);
		stateArea.setText("SYNC STATE:\n");
		stateArea.setVisible(true);
		this.add(stateArea);

		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadTable(folderTable, rootFilePath);
				loadFileTable(fileTable, rootFilePath);
				currentPath = rootFilePath;
				lbFolder.setText(currentPath);
			}
		});

		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = folderTable.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null,
							"You must choose folder", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String direct = (String) folderTable.getValueAt(row, 1);
					currentPath = direct;
					currentFolder = getParentFolder(currentPath);
					lbFolder.setText("Current Path: " + currentPath);
					loadTable(folderTable, currentPath);
					loadFileTable(fileTable, currentPath);
				}
			}
		});

		btnPrev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(currentPath);
				if ((int) currentPath.charAt(currentPath.length() - 2) == 58) {
					JOptionPane.showMessageDialog(null, "This is a root path",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					currentPath = getPath(currentPath);
					currentFolder = getParentFolder(currentPath);
					lbFolder.setText("Current Path: " + currentPath);
					loadTable(folderTable, currentPath);
					loadFileTable(fileTable, currentPath);
				}
			}
		});

		btnAddFolder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String folderName = JOptionPane.showInputDialog(null,
						"Folder name: ");
				File f;
				if (System.getProperty("os.name").equalsIgnoreCase("linux")) {
					f = new File(currentPath + "/" + folderName);
				} else {
					f = new File(currentPath + "\\" + folderName);
				}

				if (f.mkdir()) {
					loadTable(folderTable, currentPath);
					loadFileTable(fileTable, currentPath);
				} else {
					JOptionPane.showMessageDialog(null,
							"Making folder was not successfull", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnAddFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser newFileChooser = new JFileChooser();
				while (true) {
					int returnValue = newFileChooser.showOpenDialog(null);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						try {
							String fileName = newFileChooser.getSelectedFile()
									.getName();
							String filePath = newFileChooser.getSelectedFile()
									.getPath();
							File aFile = new File(filePath);
							System.out.println("" + currentPath);
							File bFile = new File(currentPath, fileName);
							is = new FileInputStream(aFile);
							os = new FileOutputStream(bFile);
							byte[] buffer = new byte[1024];
							int length;
							// copy the file content in bytes
							while ((length = is.read(buffer)) > 0) {
								os.write(buffer, 0, length);
							}
							is.close();
							os.close();
							System.out.println("File copied successfull!");
							loadTable(folderTable, currentPath);
							loadFileTable(fileTable, currentPath);
							break;
						} catch (FileNotFoundException ex) {
							Logger.getLogger(
									MainClientInterface.class.getName()).log(
									Level.SEVERE, null, ex);
						} catch (IOException ex) {
							Logger.getLogger(
									MainClientInterface.class.getName()).log(
									Level.SEVERE, null, ex);
						}
					} else {
						break;
					}
				}
			}
		});

		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowFolder = folderTable.getSelectedRow();
				int rowFile = fileTable.getSelectedRow();
				if (rowFolder < 0 && rowFile < 0) {
					JOptionPane.showMessageDialog(null,
							"You must choose a file or folder", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (rowFile >= 0) {
						String pathDelFile = (String) fileTable.getValueAt(
								rowFile, 3);
						File delFile = new File(pathDelFile);
						delFile.delete();
						loadTable(folderTable, currentPath);
						loadFileTable(fileTable, currentPath);
					}

					if (rowFolder >= 0) {
						String pathDelFolder = (String) folderTable.getValueAt(
								rowFolder, 1);

						File delFolder = new File(pathDelFolder);

						deleteFolder(delFolder);
						delFolder.delete();
						loadTable(folderTable, currentPath);
						loadFileTable(fileTable, currentPath);
					}
				}
			}
		});

		btnSyn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (isDone
							|| btnSyn.getText().equalsIgnoreCase("Stop Sync")) {
						stopSync();
						btnSyn.setText("Start Sync");
					} else if (!isDone
							|| btnSyn.getText().equalsIgnoreCase("Start Sync")) {
						startSync();
						btnSyn.setText("Stop Sync");
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		// Reload Jtable to show change of sync folder
		Timer timer = new Timer(5000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				loadFileTable(fileTable, currentPath);
				loadTable(folderTable, currentPath);
				setTextArea();
			}
		});
		timer.start();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setSize(900, 550);
		this.setLocation(90, 0);
		this.setTitle("My Storage");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
	}

	/**
	 * Delete folder.
	 *
	 * @param delFolder
	 *            the del folder
	 * @return true, if successful
	 */
	public boolean deleteFolder(File delFolder) {
		if (delFolder.isDirectory()) {
			if (delFolder.list().length == 0) {
				delFolder.delete();
				return true;
			} else {
				File[] allSubFiles = delFolder.listFiles();
				for (File file : allSubFiles) {
					if (file.isDirectory()) {
						deleteFolder(file);
					} else {
						file.delete();
					}
				}
			}
		} else {
			delFolder.delete();
			return true;
		}
		return false;
	}

	/**
	 * Gets the path.
	 *
	 * @param currentPath
	 *            the current path
	 * @return the path
	 */
	public String getPath(String currentPath) {
		int endIndex = 0;
		for (int i = currentPath.length() - 1; i >= 0; i--) {
			if ((int) currentPath.charAt(i) == 92) {// dau /
				endIndex = i;
				break;
			}
		}
		if ((int) currentPath.charAt(endIndex - 1) == 58) {// dau :
			String checkPath = currentPath.substring(0, endIndex + 1);
			return checkPath;
		} else {
			String checkPath = currentPath.substring(0, endIndex);
			return checkPath;
		}

	}

	/**
	 * Gets the parent folder.
	 *
	 * @param currentPath
	 *            the current path
	 * @return the parent folder
	 */
	public String getParentFolder(String currentPath) {

		int beginIndex = 0;
		for (int i = currentPath.length() - 1; i >= 0; i--) {
			if ((int) currentPath.charAt(i) == 92) {
				beginIndex = i;
				break;
			}
		}
		String parentFolder = currentPath.substring(beginIndex + 1);
		return parentFolder;
	}

	/**
	 * Load table.
	 *
	 * @param tb
	 *            the tb
	 * @param ROOT_FILE_PATH
	 *            the root file path
	 */
	public void loadTable(JTable tb, String ROOT_FILE_PATH) {
		String[] title1 = new String[2];
		title1[0] = "Folder";
		title1[1] = "Path";

		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(title1);

		String[] link = new String[100];
		String[] linkName = new String[100];
		String[] array = new String[100];
		int i = 0;
		File f = new File(ROOT_FILE_PATH);
		File[] allSubFiles = f.listFiles();
		for (File file : allSubFiles) {
			if (file.isDirectory()) {
				link[i] = file.getAbsolutePath();
				linkName[i] = file.getName();
				array[0] = linkName[i];
				array[1] = link[i];
				i++;
				model.addRow(array);
				// Steps for directory
			}
		}
		tb.setModel(model);
		tb.getColumnModel().getColumn(0).setPreferredWidth(300);
		tb.getColumnModel().getColumn(1).setPreferredWidth(245);
	}

	/**
	 * Load file table.
	 *
	 * @param tb
	 *            the tb
	 * @param ROOT_FILE_PATH
	 *            the root file path
	 */
	public void loadFileTable(JTable tb, String ROOT_FILE_PATH) {
		String[] title1 = new String[4];
		title1[0] = "File name";
		title1[1] = "Length";
		title1[2] = "Date Modified";
		title1[3] = "Path";

		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(title1);

		String[] fileName = new String[100];
		String[] fileType = new String[100];
		String[] array = new String[100];
		int i = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		File f = new File(ROOT_FILE_PATH);
		File[] allSubFiles = f.listFiles();
		for (File file : allSubFiles) {
			if (!file.isDirectory()) {
				Long length = file.length();
				array[0] = file.getName();
				array[1] = length.toString();
				array[2] = sdf.format(file.lastModified());
				array[3] = file.getPath();
				model.addRow(array);
			}
		}
		tb.setModel(model);

		tb.getColumnModel().getColumn(0).setPreferredWidth(200);
		tb.getColumnModel().getColumn(1).setPreferredWidth(150);
		tb.getColumnModel().getColumn(2).setPreferredWidth(100);
		tb.getColumnModel().getColumn(3).setPreferredWidth(100);
	}

	/**
	 * Start sync.
	 *
	 * @throws RemoteException
	 *             the remote exception
	 */
	private static void startSync() throws RemoteException {
		if (server.connect(client)) {
			File clientFile = new File(rootFilePath);
			File serverFile = server.getServerFile();
			Synchronization sync = new Synchronization(client, server,
					serverFile, clientFile, isDone);
			new Thread(sync).start();
		}
	}

	/**
	 * Stop sync.
	 *
	 * @throws RemoteException
	 *             the remote exception
	 */
	private static void stopSync() throws RemoteException {
		Synchronization.stopSync();
	}

	/**
	 * Gets the sync state.
	 *
	 * @return the sync state
	 * @throws RemoteException
	 *             the remote exception
	 */
	private static String getSyncState() throws RemoteException {
		if (Synchronization.state == 1) {
			syncState = "Uploading....";
		} else if (Synchronization.state == 2) {
			syncState = "Downloading....";
		} else if (Synchronization.state == 0) {
			syncState = "Server and client is synchronized";
		}
		return syncState;
	}

	private static void setTextArea() {
		try {
			stateArea.append(getSyncState());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
