package java_project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class sign extends JFrame{
    public sign(String str){
        super(str);
        ImageIcon f_img = new ImageIcon("./image/main.png"); //이미지 사이즈 조절
        JLabel f_bg  = new JLabel(f_img);
        
        JButton bt = new JButton(new ImageIcon("./image/sign_up.png"));
        JButton bt1 = new JButton(new ImageIcon("./image/go.png"));
        
        JPanel panel = new JPanel();
    	JFrame frame = new JFrame();   	
    	
        frame.setTitle("시작");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	    

        panel.add(bt);
        panel.add(bt1);

        Container c = frame.getContentPane();

        bt.setBorderPainted(false);  //버튼 배경 투명하게!
        bt.setFocusPainted(false);
        bt.setContentAreaFilled(false);

        bt1.setBorderPainted(false);  //버튼 배경 투명하게!
        bt1.setFocusPainted(false);
        bt1.setContentAreaFilled(false);
        
        bt.setLocation(90, 400);
        bt.setSize(300,400);
        c.add(bt);
        
        bt1.setLocation(430, 400);
        bt1.setSize(300,400);
        c.add(bt1);      
        
        frame.add(panel);
        panel.add(f_bg);
        frame.setSize(820,820);
        frame.setVisible(true);
        
        bt.addActionListener(new ActionListener() { //버튼 이벤트
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
            	new sign_up(getTitle());
            }
        });
        
        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	new homepage1(); //로그인
            }
        });
        
    }
//    class MyPanel extends JPanel{
//        public void paintComponent(Graphics g){
//            super.paintComponent(g);
//            g.drawImage(im,0,0,getWidth(),getHeight(),this);
//        }
//    }
    
}


public class First {
    public static void main(String[] args) {
         new sign("");
    } 
}

