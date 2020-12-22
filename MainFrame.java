import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainFrame extends JFrame{
 
Container c;
JButton btnCreate,btnWithdraw,btnDeposit,btnCheckBal;

MainFrame(){
c=getContentPane();
c.setBackground(Color.cyan);
c.setLayout(null);

btnCreate=new JButton("Create Account");
btnWithdraw=new JButton("Withdraw");
btnDeposit=new JButton("Deposit");
btnCheckBal=new JButton("Check Balance");
btnCreate.setBounds(170,70,130,40);
btnWithdraw.setBounds(170,270,130,40);
btnDeposit.setBounds(170,170,130,40);
btnCheckBal.setBounds(170,370,130,40);

c.add(btnCreate);
c.add(btnWithdraw);
c.add(btnDeposit);
c.add(btnCheckBal);

btnCreate.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae){
CreateFrame a=new CreateFrame();
dispose();
}
});

btnWithdraw.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae){
WithdrawFrame a=new WithdrawFrame();
dispose();
}
});

btnDeposit.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae){
DepositFrame a=new DepositFrame();
dispose();
}
});

btnCheckBal.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent ae){
CheckBalFrame a=new CheckBalFrame();
dispose();
}
});

setTitle("B.M.S");
setSize(500,500);
setLocationRelativeTo(null);

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
public static void main(String args[]){
MainFrame m=new MainFrame();
}
}



class DbHandler{
public void Create(int Acc_No,String Name,String Gender,int Age,int Balance)
{
try
{
 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
String sql="insert into Bankdetails values(?,?,?,?,?)";
PreparedStatement pst=con.prepareStatement(sql);
pst.setInt(1,Acc_No);
pst.setString(2,Name);
pst.setString(3,Gender);
pst.setInt(4,Age);
pst.setInt(5,Balance);
int r=pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(),r+"Account Created");
con.close();
}
catch(SQLException e){
JOptionPane.showMessageDialog(new JDialog(),"ii"+e);
}
}

public String CheckBalance(int Acc_No)
{
StringBuffer sb=new StringBuffer();
try
{
 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
String sql="select Name,Balance from Bankdetails where Acc_No="+Acc_No;

Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery(sql);
while(rs.next()){
	String Name=rs.getString("Name");
	int Balance=rs.getInt("Balance");
	sb.append("Name "+Name+" "+" Balance "+Balance+" \n");
}
rs.close();
con.close();
}
catch(SQLException e){
}
return sb.toString();
}

public void Withdraw(int Acc_No,int Balance)
{
try
{
 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
String sql="update Bankdetails set Balance=? where Acc_No=?";
PreparedStatement pst=con.prepareStatement(sql);
pst.setInt(1,Balance);
pst.setInt(2,Acc_No);

int r=pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(),r+"Balance withdrawn");
con.close();
}
catch(SQLException e){
JOptionPane.showMessageDialog(new JDialog(),"ui"+e);
}
}

public void Deposit(int Accno,int Amount)
{
try
{
 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","abc123");
String sql="Update Bankdetails set Balance=? where Acc_No=?";
PreparedStatement pst=con.prepareStatement(sql);
pst.setInt(1,Amount);
pst.setInt(2,Accno);
int r=pst.executeUpdate();
JOptionPane.showMessageDialog(new JDialog(),r + "Balance Deposited");
con.close();
}
catch(SQLException e){
JOptionPane.showMessageDialog(new JDialog(),"ui"+e);
}
}

}
