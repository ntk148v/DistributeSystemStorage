package mystorage.rmi;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Enumeration;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.joda.time.DateTimeUtils;

// TODO: Auto-generated Javadoc
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The Class StartClientInterface.
 *
 * @author Lam
 */
public class StartClientInterface extends javax.swing.JFrame {

	/** The chooser. */
	JFileChooser chooser;

	/** The server. */
	private static FileServerInt server;

	/** The client. */
	private static FileClientInt client;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	/** The bt connect. */
	private static javax.swing.JButton btConnect;

	/** The bt sync folder. */
	private static javax.swing.JButton btSyncFolder;

	/** The j label1. */
	private static javax.swing.JLabel jLabel1;

	/** The lb server ip. */
	private static javax.swing.JLabel lbServerIP;

	/** The lb sync folder. */
	private static javax.swing.JLabel lbSyncFolder;

	/** The tf choose sync folder. */
	private static javax.swing.JTextField tfChooseSyncFolder;

	/** The tf server ip. */
	private static javax.swing.JTextField tfServerIP;

	// End of variables declaration//GEN-END:variables

	/**
	 * Creates new form StartClientInterface.
	 */
	public StartClientInterface() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		lbServerIP = new javax.swing.JLabel();
		tfServerIP = new javax.swing.JTextField();
		lbSyncFolder = new javax.swing.JLabel();
		btSyncFolder = new javax.swing.JButton();
		tfChooseSyncFolder = new javax.swing.JTextField();
		btConnect = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel1.setText("MY STORAGE");

		lbServerIP.setText("Server IP");

		tfServerIP.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tfServerIPActionPerformed(evt);
			}
		});

		lbSyncFolder.setText("Sync Folder");

		btSyncFolder.setText("Choose Sync Folder");
		btSyncFolder.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btSyncFolderActionPerformed(evt);
			}
		});

		tfChooseSyncFolder.setText(Constants.defaultClientFilePath);
		tfChooseSyncFolder.setEnabled(false);
		tfChooseSyncFolder
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						tfChooseSyncFolderActionPerformed(evt);
					}
				});

		btConnect.setText("Connect");
		btConnect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					btConnectActionPerformed(evt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(45, 45, 45)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														lbServerIP,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														80,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lbSyncFolder))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										65, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														tfServerIP,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														150,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																false)
																.addComponent(
																		tfChooseSyncFolder,
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(
																		btSyncFolder,
																		javax.swing.GroupLayout.Alignment.LEADING,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)))
								.addGap(60, 60, 60))
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(113,
																		113,
																		113)
																.addComponent(
																		jLabel1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		167,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(163,
																		163,
																		163)
																.addComponent(
																		btConnect)))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(22, 22, 22)
								.addComponent(jLabel1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										36,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(38, 38, 38)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														lbServerIP,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														24,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														tfServerIP,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(35, 35, 35)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lbSyncFolder)
												.addComponent(btSyncFolder))
								.addGap(18, 18, 18)
								.addComponent(tfChooseSyncFolder,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										27, Short.MAX_VALUE)
								.addComponent(btConnect,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										38,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(19, 19, 19)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * Connect to server.
	 *
	 * @throws Exception
	 *             the exception
	 */
	private static void connectToServer() throws Exception {
		System.getProperty("java.rmi.server.hostname", getIp());

		String url = "rmi://" + tfServerIP.getText() + ":" + Constants.port
				+ "/" + Constants.connName;
		server = (FileServerInt) Naming.lookup(url);
		System.out.println("Connected");
		client = new FileClientImpl(InetAddress.getLocalHost());
		clockSync();
	}

	/**
	 * Clock sync.
	 *
	 * @throws Exception
	 *             the exception
	 */
	private static void clockSync() throws Exception {
		String serverIP = tfServerIP.getText();

		// Send request
		DatagramSocket socket = new DatagramSocket();
		InetAddress address = InetAddress.getByName(serverIP);
		byte[] buf = new NtpMessage().toByteArray();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address,
				123);

		// Set the transmit timestamp *just* before sending the packet
		// ToDo: Does this actually improve performance or not?
		NtpMessage.encodeTimestamp(packet.getData(), 40,
				(System.currentTimeMillis() / 1000.0) + 2208988800.0);

		socket.send(packet);

		// Get response
		System.out.println("NTP request sent, waiting for response...\n");
		packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);

		// Immediately record the incoming timestamp
		double destinationTimestamp = (System.currentTimeMillis() / 1000.0) + 2208988800.0;

		// Process response
		NtpMessage msg = new NtpMessage(packet.getData());

		// Corrected, according to RFC2030 errata
		double roundTripDelay = (destinationTimestamp - msg.originateTimestamp)
				- (msg.transmitTimestamp - msg.receiveTimestamp);

		double localClockOffset = ((msg.receiveTimestamp - msg.originateTimestamp) + (msg.transmitTimestamp - destinationTimestamp)) / 2;

		// Display response
		System.out.println("NTP server: " + serverIP);
		System.out.println(msg.toString());

		System.out.println("Dest. timestamp:     "
				+ NtpMessage.timestampToString(destinationTimestamp));

		System.out.println("Round-trip delay: "
				+ new DecimalFormat("0.00").format(roundTripDelay * 1000)
				+ " ms");

		System.out.println("Local clock offset: "
				+ new DecimalFormat("0.00").format(localClockOffset * 1000)
				+ " ms");
		System.out.println("Current time " + DateTimeUtils.currentTimeMillis());
		DateTimeUtils.setCurrentMillisOffset((long) (localClockOffset * 1000));
		System.out.println("Current time " + DateTimeUtils.currentTimeMillis());

		socket.close();
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	private static String getIp() {
		String ipAddress = null;
		Enumeration<NetworkInterface> net = null;
		try {
			net = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}

		while (net.hasMoreElements()) {
			NetworkInterface element = net.nextElement();
			Enumeration<InetAddress> addresses = element.getInetAddresses();
			while (addresses.hasMoreElements()) {
				InetAddress ip = addresses.nextElement();
				if (ip instanceof Inet4Address) {
					if (ip.isSiteLocalAddress()) {
						ipAddress = ip.getHostAddress();
					}
				}
			}
		}
		return ipAddress;
	}

	/**
	 * Tf server ip action performed.
	 *
	 * @param evt
	 *            the evt
	 */
	private void tfServerIPActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tfServerIPActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_tfServerIPActionPerformed

	/**
	 * Bt sync folder action performed.
	 *
	 * @param evt
	 *            the evt
	 */
	private void btSyncFolderActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btSyncFolderActionPerformed
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("C:\\"));
		chooser.setDialogTitle("Choose Sync Folder");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			tfChooseSyncFolder.setText("" + chooser.getSelectedFile());
		} else {
			System.out.println("No Selection ");
		}
	}// GEN-LAST:event_btSyncFolderActionPerformed

	/**
	 * Tf choose sync folder action performed.
	 *
	 * @param evt
	 *            the evt
	 */
	private void tfChooseSyncFolderActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tfChooseSyncFolderActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_tfChooseSyncFolderActionPerformed

	/**
	 * Bt connect action performed.
	 *
	 * @param evt
	 *            the evt
	 * @throws Exception
	 *             the exception
	 */
	private void btConnectActionPerformed(java.awt.event.ActionEvent evt)
			throws Exception {// GEN-FIRST:event_btConnectActionPerformed
		if (tfServerIP.getText().equals("")) {
			JOptionPane.showMessageDialog(btConnect, "Bạn chưa nhập serverIP",
					"Lỗi kết nối", JOptionPane.ERROR_MESSAGE);
		} else if (tfChooseSyncFolder.getText().equals(
				Constants.defaultClientFilePath)) {
			File defaultFile = new File(Constants.defaultClientFilePath);
			if (!defaultFile.exists()) {
				if (defaultFile.mkdir()) {
					System.out.println("Directory created!");
				} else {
					System.out.println("Failed create directory!");
				}
			}
			connectToServer();
			new MainClientInterface(defaultFile.getAbsolutePath(), client,
					server);
		} else {
			connectToServer();
			new MainClientInterface(
					chooser.getSelectedFile().getAbsolutePath(), client, server);
		}
		this.setVisible(false);

	}// GEN-LAST:event_btConnectActionPerformed

	/**
	 * The main method.
	 *
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(
					StartClientInterface.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(
					StartClientInterface.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(
					StartClientInterface.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(
					StartClientInterface.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new StartClientInterface().setVisible(true);
			}
		});
	}
}
