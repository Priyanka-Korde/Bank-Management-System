import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class CheckBalFrame extends JFrame{
Container c;
TextArea ta;
JTextField txtAccno;
JButton btnBack,btnCheck;
JPanel p1,p2,p0;

CheckBalFrame()
{
c=getContentPane();
c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));

p0=new JPanel();
txtAccno=new JTextField(4);
btnCheck=new JButton("Check");
p0.setBackground(Color.cyan);
p0.add(txtAccno);
p0.add(btnCheck);
c.add(p0);

p1=new JPanel();
ta=new TextArea(4,35);
p1.setBackground(Color.cyan);
p1.add(ta);
c.add(p1);

p2=new JPanel();
btnBack=new JButton("Back");
p2.setBackground(Color.cyan);
p2.add(btnBack);
c.add(p2);

btnCheck.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
try{
String Accno=txtAccno.getText();
DbHandler db=new DbHandler();
String data=db.CheckBalance(Integer.parseInt(Accno));
ta.setText(data);
}
catch(Exception e){
JOptionPane.showMessageDialog(c,"Invalid Account Number");}
}
});
btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a=new MainFrame();
dispose();
}
});

setTitle("Check Balance");
setSize(500,500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}




