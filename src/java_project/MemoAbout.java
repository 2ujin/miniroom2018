	package java_project;

	import java.awt.Color;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JButton;
	import javax.swing.JDialog;
	import javax.swing.JFrame;
	import javax.swing.JLabel;

	public class MemoAbout extends JDialog implements ActionListener {

	 public MemoAbout(JFrame frame) {
	  super(frame);
	  setTitle("일기장 정보");
	  setModal(true);    // true : Modal, false : Modeless
	  
	  JLabel lbl = new JLabel("나만의 일기장");
	  JButton btn = new JButton("확인");
	  btn.addActionListener(this);
	  add("Center", lbl);    // BordLayout
	  add("South", btn);
	  
	  setBackground(Color.BLUE);
	  setBounds(350, 250, 150, 150);
	  setVisible(true);
	 }
	 
	 @Override
	 public void actionPerformed(ActionEvent e) {
	  dispose();  // dialog 닫기
	 }
	}