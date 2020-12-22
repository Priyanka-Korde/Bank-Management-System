import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.util.Random;

class Alpha1{
public boolean isAlpha(String Name)
{
char[] chars=Name.toCharArray();
if(chars.length==0)
	return false;
for (char c:chars){
if(!Character.isLetter(c)){
	return false;
}
}
return true;
}
public boolean isGender(String Gender)
{
if(Gender=="male" || Gender=="female")
	return false;

char[] chars=Gender.toCharArray();
if(chars.length==0)
	return false;
return true;
}
}

class CreateFrame extends JFrame{
Container c;
JLabel lblBalance,lblName,lblGender,lblAge;
JTextField txtBalance,txtName,txtGender,txtAge;
JButton btnSave,btnBack;
JPanel p1,p2,p3,p4,p5;
//List lstGender;

CreateFrame(){
c=getContentPane();
c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));

p1=new JPanel();
p2=new JPanel();
p3=new JPanel();
p4=new JPanel();

lblBalance=new JLabel("Initial Balance");
lblName=new JLabel("Name");
lblGender=new JLabel("Gender");
lblAge=new JLabel("Age");
txtBalance=new JTextField(10);
txtName=new JTextField(10);
txtGender=new JTextField(10);
txtAge=new JTextField(10);

//lstGender = new List();
//lstGender.add("Female");
//lstGender.add("Male");
p1.setBackground(Color.cyan);
p1.add(lblName);
p1.add(txtName);
c.add(p1);
p2.setBackground(Color.cyan);
p2.add(lblGender);
p2.add(txtGender);
c.add(p2);
p3.setBackground(Color.cyan);
p3.add(lblAge);
p3.add(txtAge);
c.add(p3);
p4.setBackground(Color.cyan);
p4.add(lblBalance);
p4.add(txtBalance);
c.add(p4);


p5=new JPanel();
btnSave=new JButton("Create");
btnBack=new JButton("Back");
p5.setBackground(Color.cyan);
p5.add(btnSave);
p5.add(btnBack);
c.add(p5);

btnSave.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
try{
if(Integer.parseInt(txtAge.getText())>15){
Scanner sc=new Scanner(System.in);
String Age=txtAge.getText();
String Name=txtName.getText();
String Gender=txtGender.getText();     
String Bal=txtBalance.getText();

int Acc_No = 0; 
Acc_No = (int)((Math.random() * 9000)+1000); 
Scanner scan=new Scanner(Name);
Alpha1 af=new Alpha1();
if(!af.isAlpha(Name)){
txtName.setText("");
throw new InputMismatchException();
}
if(!af.isGender(Gender)){
txtGender.setText("");
throw new InputMismatchException();
}
if(scan.hasNextInt())
throw new InputMismatchException();
DbHandler db=new DbHandler();
db.Create(Acc_No,Name,Gender,Integer.parseInt(Age),Integer.parseInt(Bal));
txtGender.setText("");
txtAge.setText("");
txtBalance.setText("");
txtName.setText("");
txtName.requestFocus();
}
else
JOptionPane.showMessageDialog(c,"Age must be above 15");
}
catch(NumberFormatException e){
JOptionPane.showMessageDialog(c,"Enter Integer");}
catch(InputMismatchException e){
JOptionPane.showMessageDialog(c,"Enter String");}

}
});

btnBack.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
MainFrame a=new MainFrame();
dispose();
}
});

setTitle("Create Account");
setSize(500,500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}
