package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField userText,passText;
    Choice loginchoice;
    JButton loginbut,signup,cancel;
    Login(){

        super("Login");
        getContentPane().setBackground(Color.white);
        JLabel username = new JLabel("Username");
        username.setBounds(300,60,100,20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400,60,150,20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300,100,100,20);
        add(password);

        passText = new JTextField();
        passText.setBounds(400,100,150,20);
        add(passText);

        JLabel loggin = new JLabel("Login in as");
        loggin.setBounds(300,140,100,20);
        add(loggin);

        loginchoice = new Choice();
        loginchoice.add("Admin");
        loginchoice.add("Customer");
        loginchoice.setBounds(400,140,150,20);
        add(loginchoice);

        loginbut = new JButton("LOGIN");
        loginbut.setBounds(300,180,100,20);
        loginbut.addActionListener(this);
        add(loginbut);

        cancel = new JButton("CANCEL");
        cancel.setBounds(460,180,100,20);
        cancel.addActionListener(this);
        add(cancel);

        signup = new JButton("SIGN UP");
        signup.setBounds(380,215,100,20);
        signup.addActionListener(this);
        add(signup);

        ImageIcon profile = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image prof = profile.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon newpic = new ImageIcon(prof);
        JLabel profilepic = new JLabel(newpic);
        add(profilepic);
        profilepic.setBounds(10,5,250,250);

        setSize(650,300);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource() == cancel){
        setVisible(false);
    }
    else if(e.getSource() == signup){
        setVisible(false);
        new Signup();
    }
    else if(e.getSource() == loginbut){
        String susername = userText.getText();
        String spassword = passText.getText();
        String user = loginchoice.getSelectedItem();
        try{
            database c = new database();
            String query = "select * from signup where username = '"+susername+"' and password = '"+spassword+"'and usertype = '"+user+"'";
            ResultSet resultset = c.statement.executeQuery(query);
            if(resultset.next()){
                String meter =resultset.getString("meter_no");
                setVisible(false);
                new main_class(user,meter);
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid login");
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
    }

    public static void main(String[] args){
        new Login();

    }
}