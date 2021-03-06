import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;


class WithdrawFrame extends JFrame{
Container c;
JLabel lblAccno,lblAmount;
JTextField txtAccno,txtAmount;
JButton btnAccno,btnAmount,btnSave,btnBack;
JPanel p1,p2;

WithdrawFrame(){
c=getContentPane();
c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));

p1=new JPanel();
lblAccno=new JLabel("Account No");
lblAmount=new JLabel("Amount");
txtAccno=new JTextField(4);
txtAmount=new JTextField(10);
p1.setBackground(Color.cyan);
p1.add(lblAccno);
p1.add(txtAccno);
p1.add(lblAmount);
p1.add(txtAmount);

c.add(p1);

p2=new JPanel();
btnSave=new JButton("Save");
btnBack=new JButton("Back");
p2.setBackground(Color.cyan);
p2.add(btnSave);
p2.add(btnBack);
c.add(p2);

btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
try{
String Accno=txtAccno.getText();
String Amt=txtAmount.getText();

Scanner scan=new Scanner(Amt);
Handlerb res=new Handlerb();
String add=res.Retrieve(Integer.parseInt(Accno));
if(add=="")
JOptionPane.showMessageDialog(c,"Enter Valid AccNo");
else{
if(Integer.parseInt(Amt)<=Integer.parseInt(add))
{
   int Bal=Integer.parseInt(add)-Integer.parseInt(Amt);
   DbHandler db=new DbHandler();
db.Withdraw(Integer.parseInt(Accno),Bal);
}
else
 JOptionPane.showMessageDialog(c,"Not Enough Amount");
}
}

catch(NumberFormatException e){
JOptionPane.showMessageDialog(c,"Enter Integer");}
catch(Exception e){
JOptionPane.showMessageDialog(c,"No special characters allowed");}
txtAccno.setText("");
txtAmount.setText("");
txtAccno.requestFocus();
}
});

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a=new MainFrame();
dispose();
}
});

setTitle("Withdraw Money");
setSize(500,500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}

class Handlerb{
public String Retrieve(int Accno){
int bal=0;
StringBuffer sb=new StringBuffer();
try{

DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");

String sql="select Balance from Bankdetails where Acc_No="+Accno;
Statement stmt=con.createStatement();

ResultSet rs=stmt.executeQuery(sql);
while(rs.next()){
 bal=rs.getInt("Balance");
 sb.append(bal);
}
rs.close();
con.close();
}
catch(SQLException e){
System.out.println(e);
}
return sb.toString();
}
}