package java_project;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

import javax.swing.*;

public class MemoJang extends JFrame implements ActionListener{
 JButton btnCopy = new JButton("복사");
 JButton btnPaste = new JButton("붙여넣기");
 JButton btnCut = new JButton("잘라내기");
 JButton btnDel = new JButton("삭제");
 JTextArea txtMemo = new JTextArea("", 10, 40);
 String copyText;
 
 // 그러므로 메뉴아이템을 여기다가  메뉴
 JMenuItem mnuNew, mnuSave, mnuOpen, mnuExit;
 JMenuItem mnuCopy, mnuPaste, mnuCut, mnuDel;
 JMenuItem mnuAbout, mnuEtc1, mnuEtc2;
 
 // 팝업 메뉴
 JPopupMenu popup;
 JMenuItem m_white, m_blue, m_yellow;
 
 public MemoJang() {
  super("다이어리");
  
  initLayout();
  menuLayout();
  setBounds(0, 0, 820, 820);
  setVisible(true);
//  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
 
 private void initLayout() {
  JPanel panel = new JPanel();
  panel.add(btnCopy);
  panel.add(btnPaste);
  panel.add(btnCut);
  panel.add(btnDel);
  add("South", panel);
  add("Center", txtMemo);
  
  btnCopy.addActionListener(this);
  btnPaste.addActionListener(this);
  btnCut.addActionListener(this);
  btnDel.addActionListener(this);
 }
 
 private void menuLayout() {        // 메뉴아이템은 여기서 만들고
  JMenuBar menuBar = new JMenuBar();
  
  JMenu mnuFile = new JMenu("파일");     // 주메뉴
  mnuNew = new JMenuItem("새 문서");     // 부메뉴
  mnuOpen  = new JMenuItem("열기...");
  mnuSave = new JMenuItem("저장...");
  mnuExit = new JMenuItem("종료");
  mnuFile.add(mnuNew);
  mnuFile.add(mnuOpen);
  mnuFile.add(mnuSave);
  mnuFile.addSeparator();        // 구분선
  mnuFile.add(mnuExit);
  
  JMenu mnuEdit = new JMenu("편집");     // 주메뉴
  mnuCopy = new JMenuItem("복사");
  mnuPaste = new JMenuItem("붙여넣기");
  mnuCut = new JMenuItem("잘라내기");
  mnuDel = new JMenuItem("삭제");
  mnuEdit.add(mnuCopy);
  mnuEdit.add(mnuPaste);
  mnuEdit.add(mnuCut);
  mnuEdit.add(mnuDel);
  
  JMenu mnuHelp = new JMenu("도움말");
  mnuAbout = new JMenuItem("다이어리란...");
  mnuHelp.add(mnuAbout);
  JMenu mnuEtc = new JMenu("기타");   // 부메뉴에 부메뉴
   mnuEtc1 = new JMenuItem("계산기");
   mnuEtc2 = new JMenuItem("프리셀");
   mnuEtc.add(mnuEtc1);
   mnuEtc.add(mnuEtc2);
  mnuHelp.add(mnuEtc);
  
  menuBar.add(mnuFile);
  menuBar.add(mnuEdit);
  menuBar.add(mnuHelp);
  
  setJMenuBar(menuBar);        // Frame에 메뉴바 장착
  
  mnuNew.addActionListener(this);      // 메뉴에 리스너 장착
  mnuSave.addActionListener(this);
  mnuOpen.addActionListener(this);
  mnuExit.addActionListener(this);
  mnuCopy.addActionListener(this);
  mnuPaste.addActionListener(this);
  mnuCut.addActionListener(this);
  mnuDel.addActionListener(this);
  mnuAbout.addActionListener(this);
  mnuEtc1.addActionListener(this);
  mnuEtc2.addActionListener(this);
  
  // 팝업 메뉴(우클릭시 출현)
  popup = new JPopupMenu();
  JMenu m_col = new JMenu("색상 선택");
  m_white = new JMenuItem("하양");
  m_blue = new JMenuItem("파랑");
  m_yellow = new JMenuItem("노랑");
  m_col.add(m_white);
  m_col.add(m_blue);
  m_col.add(m_yellow);
  m_white.addActionListener(this);
  m_blue.addActionListener(this);
  m_yellow.addActionListener(this);
  popup.add(m_col);   // 팝업에 주는것
  txtMemo.add(popup);   // 팝업 메뉴 추가
  
  txtMemo.addMouseListener(new MouseAdapter() {
   @Override
   public void mousePressed(MouseEvent e) {
    // 오른쪽 버튼 클릭 시 ...
    if(e.getModifiers() == MouseEvent.BUTTON3_MASK) { // 왼쪽이 1 가운데가 2 오른쪽이 3   BUTTON3_MASK - 오른쪽 버튼
     // System.out.println("반응");
     popup.show(txtMemo, e.getX(), e.getY());
    }
   }
  });
 }
 
 @Override
 public void actionPerformed(ActionEvent e) {   // 메뉴아이템 실행은 여기
  //복사
  if(e.getSource() == btnCopy || e.getSource() == mnuCopy) {
   // copyText = txtMemo.getText();  // 전체가 다 복사가 된다
   copyText = txtMemo.getSelectedText();
   // System.out.println(copyText);
  
  //붙여넣기
  } else if(e.getSource() == btnPaste || e.getSource() == mnuPaste) {
   // txtMemo.setText(copyText);
   int position = txtMemo.getCaretPosition();  // 위치 잡기
   txtMemo.insert(copyText, position);    // append 추가 insert 삽입
  
  //잘라내기
  } else if(e.getSource() == btnCut || e.getSource() == mnuCut) {
   copyText = txtMemo.getSelectedText();
   int start = txtMemo.getSelectionStart();
   int end = txtMemo.getSelectionEnd();
   txtMemo.replaceRange("", start, end);
  
  //지우기
  } else if(e.getSource() == btnDel || e.getSource() == mnuDel) {
   int start = txtMemo.getSelectionStart();
   int end = txtMemo.getSelectionEnd();
   txtMemo.replaceRange("", start, end);   // 공백으로 치환하는 법 replaceRange("", 시작위치, 끝위치)
  
  //새문서
  } else if(e.getSource() == mnuNew) {   
   txtMemo.setText("");
   setTitle("제목없음 - 메모장");
  
  //열기
  } else if(e.getSource() == mnuOpen) {    // 열기
   FileDialog dialog = new FileDialog(this, "열기", FileDialog.LOAD);
   dialog.setDirectory(".");   // .은 지금폴더
   dialog.setVisible(true);   // 박스는 그냥 틀이고
   if(dialog.getFile() == null) return;
   String dfName = dialog.getDirectory() + dialog.getFile();  // 경로명 파일명
   
   try {
    BufferedReader reader = new BufferedReader(new FileReader(dfName));
    txtMemo.setText("");
    String line;
    while((line = reader.readLine()) != null) {     // 읽어온 문서의 줄이 없어지면
     txtMemo.append(line + "\n");       // txtMemo에 추가
    }
    reader.close();
    
    setTitle(dialog.getFile() + " - 메모장" );
   } catch (Exception e2) {
    JOptionPane.showMessageDialog(this, "열기 오류");
   }
   
  //저장
  } else if(e.getSource() == mnuSave) {    
   // 파일 작업을 위한 경로명 및 파일명 얻기
   FileDialog dialog = new FileDialog(this, "저장", FileDialog.SAVE);
   dialog.setDirectory(".");   // .은 지금폴더
   dialog.setVisible(true);   // 박스는 그냥 틀이고
   if(dialog.getFile() == null) return; // 이걸빼면 취소를 해도 저장이됨
   String dfName = dialog.getDirectory() + dialog.getFile();  // 경로명 파일명
   // System.out.println(dfName);
   // 실제 저장은 여기에서
   try {
    BufferedWriter writer = new BufferedWriter(new FileWriter(dfName));
    writer.write(txtMemo.getText());
    writer.close();
    
    setTitle(dialog.getFile() + " - 메모장" );
   } catch (Exception e2) {
    JOptionPane.showMessageDialog(this, "저장 오류");
   }
   
  //종료
  } else if(e.getSource() == mnuExit) {
   System.exit(0);
   
  //메모장이란..
  } else if(e.getSource() == mnuAbout) {
   new MemoAbout(this);
   // System.out.println("다음 계속");
   
  //기타1 : 계산기열기
  } else if(e.getSource() == mnuEtc1) {
   try {    // 외부에 있는 파일을 읽을거기 때문에
    Runtime runtime = Runtime.getRuntime();
    runtime.exec("calc.exe");
   } catch (Exception e2) {
    // TODO: handle exception
   }
   
  //기타2 : 프리셀 열기
  } else if(e.getSource() == mnuEtc2) {    // 기타2
   try {    // 외부에 있는 파일을 읽을거기 때문에
    Runtime.getRuntime().exec("freesell.exe");
   } catch (Exception e2) {
    // TODO: handle exception
   }
   
  //우클릭시에 나타나는 팝업메뉴에 대한 설정
  } else if(e.getSource() == m_white) {   // 팝업메뉴
   txtMemo.setBackground(Color.WHITE);
  } else if(e.getSource() == m_blue) {   // 팝업메뉴
   txtMemo.setBackground(Color.BLUE);
  } else if(e.getSource() == m_yellow) {   // 팝업메뉴
   txtMemo.setBackground(Color.YELLOW);
  } 
  txtMemo.requestFocus();
 }
 
 public static void main(String[] args) {
  new MemoJang();
 }

}