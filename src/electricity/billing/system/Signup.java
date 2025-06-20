package electricity.billing.system;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Signup extends JFrame implements ActionListener {
    Choice signupacc;
    TextField metertext,empid,usur,namee,passwo;
    JButton createx,back;
    Signup(){
        super("Sign up");
        getContentPane().setBackground(new Color(168,203,255));
        JLabel create = new JLabel("Create account ");
        create.setBounds(30,50,125,20);
        add(create);

        signupacc = new Choice();
        signupacc.add("Admin");
        signupacc.add("Customer");
        signupacc.setBounds(170,50,120,20);
        add(signupacc);

        JLabel meterno = new JLabel("Meter number");
        meterno.setBounds(30,100,125,20);
        meterno.setVisible(false);
        add(meterno);

        metertext = new TextField();
        metertext.setBounds(170,100,125,20);
        metertext.setVisible(false);
        add(metertext);

        JLabel emp = new JLabel("Employee ID");
        emp.setBounds(30,100,125,20);
        emp.setVisible(true);
        add(emp);

        empid = new TextField();
        empid.setBounds(170,100,125,20);
        empid.setVisible(true);
        add(empid);

        JLabel usu = new JLabel("Username");
        usu.setBounds(30,150,125,20);
        usu.setVisible(true);
        add(usu);

        usur = new TextField();
        usur.setBounds(170,150,125,20);
        usur.setVisible(true);
        add(usur);

        JLabel name = new JLabel("Name");
        name.setBounds(30,200,125,20);
        name.setVisible(true);
        add(name);

        namee = new TextField("");
        namee.setBounds(170,200,125,20);
        namee.setVisible(true);
        add(namee);

        metertext.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    database c = new database();
                    ResultSet resultset = c.statement.executeQuery("select * from signup where meter_no='"+metertext.getText()+"' ");
                    if (resultset.next()){
                        namee.setText(resultset.getString("name"));
                    }

                }catch(Exception E){
                    E.printStackTrace();
                }

            }
        });

        JLabel passw = new JLabel("Password");
        passw.setBounds(30,250,125,20);
        passw.setVisible(true);
        add(passw);

        passwo = new TextField();
        passwo.setBounds(170, 250, 125, 20);
        passwo.setVisible(true);
        add(passwo);

        signupacc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = signupacc.getSelectedItem();
                if(user.equals("Admin")){
                    meterno.setVisible(false);
                    metertext.setVisible(false);
                    emp.setVisible(true);
                    empid.setVisible(true);
                }else{
                    meterno.setVisible(true);
                    metertext.setVisible(true);
                    namee.setEditable(false);
                    emp.setVisible(false);
                    empid.setVisible(false);
                }
            }
        });

        createx = new JButton("Create");
        createx.setBackground(new Color(66,127,219));
        createx.setBounds(50,285,100,25);
        createx.addActionListener(this);
        add(createx);

        back = new JButton("Back");
        back.setBackground(new Color(66,127,219));
        back.setBounds(180,285,100,25);
        back.addActionListener(this);
        add(back);

        ImageIcon profile = new ImageIcon(ClassLoader.getSystemResource("icon/profile.png"));
        Image prof = profile.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon newpic = new ImageIcon(prof);
        JLabel profilepic = new JLabel(newpic);
        add(profilepic);
        profilepic.setBounds(320,50 ,250,250);

        setSize(600,380);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == createx){
            String slogin = signupacc.getSelectedItem();
            String suser = usur.getText();
            String sname = namee.getText();
            String password = passwo.getText();
            String smeter = metertext.getText();
            try {
            database c = new database();
            String query = null;
            if(signupacc.equals("Admin")) {
                query = "insert into signup value('" + smeter + "', '" + suser + "', '" + sname + "','" + password + "','" + slogin + "')";
            }else{
                query = "update signup set username = '" +suser+"',password = '"+password+"',usertype = '"+slogin+"' where meter_no = '"+smeter+"'";
            }
            c.statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Account created succefully");
            setVisible(false);
            new Login();
            }catch (Exception E){
            E.printStackTrace();
            }
        }
        else if(e.getSource() == back){
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args){
        new Signup();
    }
}
