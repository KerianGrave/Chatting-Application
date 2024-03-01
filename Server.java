package chatting.Application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Server extends JFrame implements ActionListener{
	
	JTextField text;
	JPanel p2;
	static Box vertical = Box.createVerticalBox();
	Server(){
		
		setLayout(null);// becoz we will create our own layout
		JPanel p1 = new JPanel(); //creating a panel over the frame that consits of items such as dp , name etc
		p1.setBackground(new Color(7 , 94 , 84)); // setting customize background
		p1.setBounds(0 , 0 , 450, 70); // sets coordinate , hiegt and weight
		p1.setLayout(null); // set panel Layout as null
		add(p1);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("ImagesVK/3.png")); //set image from the folder
		Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel back = new JLabel(i3);  
		back.setBounds(10, 20, 25, 25); // set JLabel on the frame 
		p1.add(back);
		
		back.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent ae) {
				System.exit(0);
			}
			
			
		});
		
		
		
		
		
		// Setting Profile Pic
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("ImagesVK/1.png"));
		Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT); // Increase the size here
		ImageIcon i6 = new ImageIcon(i5);
		JLabel profilePic = new JLabel(i6);
		profilePic.setBounds(40, 9, 50, 50); // Adjusted size
		p1.add(profilePic);
		
		
		
		
		
		// Setting Call
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("ImagesVK/phone.png"));
		Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT); // Increase the size here
		ImageIcon i9 = new ImageIcon(i8);
		JLabel call = new JLabel(i9);
		call.setBounds(290, 9, 50, 50); // Adjusted size
		p1.add(call);
		
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("ImagesVK/video.png"));
		Image i11 = i10.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT); // Increase the size here
		ImageIcon i12 = new ImageIcon(i11);
		JLabel videoCall = new JLabel(i12);
		videoCall.setBounds(350, 9, 50, 50); // Adjusted size
		p1.add(videoCall);
		
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("ImagesVK/3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT); // Increase the size here
		ImageIcon i15 = new ImageIcon(i14);
		JLabel moreOptions = new JLabel(i15);
		moreOptions.setBounds(410, 20, 10, 25); // Adjusted size
		p1.add(moreOptions);
		
		
		JLabel name = new JLabel("Vijay");
		name.setBounds(110, 10, 70, 40);
		name.setForeground(Color.white);
		name.setFont(new Font("SAN_SARIE" , Font.BOLD , 18));
		p1.add(name);
		
		JLabel status = new JLabel("Active Now");
		status.setBounds(110, 35, 70, 30);
		status.setForeground(Color.white);
		status.setFont(new Font("SAN_SARIE" , Font.BOLD , 12));
		p1.add(status);
		
		p2 = new JPanel();
		p2.setBounds(5 , 75 , 440 , 570);
		add(p2);
		
		text = new JTextField();
		text.setBounds(5, 520, 310, 40);
		text.setFont(new Font("SAN_SERIF" , Font.PLAIN , 16));
//		text.setBackground(Color.GRAY);
		add(text);
		
		JButton send = new JButton("Send");
		send.setBounds(320 ,  520, 123, 40);
		send.setBackground(new Color(7, 94, 84));
		send.setBackground(new Color(7, 94, 84));
		send.setForeground(Color.WHITE);
		send.setFont(new Font("SAN_SERIF" , Font.PLAIN, 16));
		send.addActionListener(this);
		add(send);
		
		

		
		
		
		
		setSize(450 , 600);
		setLocation(570 , 200);
//		setUndecorated(true);
		getContentPane().setBackground(Color.WHITE);
		setVisible(true);
		
	}
	
	@Override
	// showing typed output on screen
	public void actionPerformed(ActionEvent e) {
		String out = text.getText();
		JLabel output = new JLabel(out);
		JPanel p3 = formatLabel(out);
//		p3.add(output);
//		System.out.println(out);
		p2.setLayout(new BorderLayout());
		
		//allign message right side
		JPanel right = new JPanel(new BorderLayout());
		right.add(p3 , BorderLayout.LINE_END);
		vertical.add(right);
		vertical.add(Box.createVerticalStrut(15));
		p2.add(vertical, BorderLayout.PAGE_START);
		text.setText("");
		
		repaint();
		invalidate();
		validate();
		
	}
	
	public static JPanel formatLabel(String out) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
		output.setFont(new Font("Tahoma" , Font.PLAIN, 16));
		output.setBackground(new Color(204, 255, 204));
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15, 15, 15, 20));
		panel.add(output);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        
        panel.add(time);
        
        
        
		return panel;
	}
	
	public static void main(String[] args) {
		
		
		new Server();
		
		//Exception Handling while creating server 
		try {
            ServerSocket skt = new ServerSocket(6001); // creating server at port 6001
            while(true) { // running infinite
                Socket s = skt.accept();// accepting messages
                DataInputStream din = new DataInputStream(s.getInputStream());
                DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                
                while(true) {
                    String msg = din.readUTF();
                    JPanel panel = formatLabel(msg);
                    
                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);
                    Container f = null;
					f.validate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
	}
		
	
	}

}
