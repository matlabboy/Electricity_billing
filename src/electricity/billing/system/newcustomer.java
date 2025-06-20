package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class newcustomer extends JFrame implements ActionListener {
    JLabel heading,cname,meternumber,address,city,state,email,phone,meternumbertext;
    TextField nametext,addresstext,citytext,statetext,emailtext,phonetext;
    JButton next, cancel;
    newcustomer(){
        super("New Customer");
        setSize(700,500);
        setLocation(400,100);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252,186,3));
        add(panel);

         heading = new JLabel("New Customer");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);

        cname = new JLabel("New Customer");
        cname.setBounds(50,80,100,20);
        panel.add(cname);

        nametext = new TextField();
        nametext.setBounds(180,80,150,20);
        panel.add(nametext);

        meternumber = new JLabel("Meter Number");
        meternumber.setBounds(50,120,100,20);
        panel.add(meternumber);

        meternumbertext =new JLabel("");
        meternumbertext.setBounds(180,120,150,20);
        panel.add(meternumbertext);

        Random ran= new Random();
        long number = ran.nextLong() % 1000000;
        meternumbertext.setText(""+ Math.abs(number));

        address = new JLabel("Address");
        address.setBounds(50,160,100,20);
        panel.add(address);

        addresstext =new TextField();
        addresstext.setBounds(180,160,150,20);
        panel.add(addresstext);

        city = new JLabel("City");
        city.setBounds(50,200,100,20);
        panel.add(city);

        citytext =new TextField("");
        citytext.setBounds(180,200,150,20);
        panel.add(citytext);

        state = new JLabel("State");
        state.setBounds(50,240,100,20);
        panel.add(state);

        statetext =new TextField("");
        statetext.setBounds(180,240,150,20);
        panel.add(statetext);

        email = new JLabel("Email");
        email.setBounds(50,280,100,20);
        panel.add(email);

        emailtext =new TextField("");
        emailtext.setBounds(180,280,150,20);
        panel.add(emailtext);

        phone = new JLabel("Phone");
        phone.setBounds(50,320,100,20);
        panel.add(phone);

        phonetext =new TextField("");
        phonetext.setBounds(180,320,150,20);
        panel.add(phonetext);

        next = new JButton("Next");
        next.setBounds(120,390,100,25);
        next.setBackground(Color.black);
        next.setForeground(Color.white);
        next.addActionListener(this);
        panel.add(next);

        cancel= new JButton("Cancel");
        cancel.setBounds(230,390,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);


        setLayout(new BorderLayout());
        add(panel,"Center");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/boy.png"));
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgl= new JLabel(i3);
        add(imgl,"West");

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==next){
            String sname = nametext.getText();
            String sstringmeter = meternumbertext.getText();
            String sadd = addresstext.getText();
            String scity =citytext.getText();
            String sstate= statetext.getText();
            String semail=emailtext.getText();
            String sphone=phonetext.getText();
            
            String querycustomer = "insert into new_customer values('"+sname+"','"+sstringmeter+"','"+sadd+"','"+scity+"','"+sstate+"','"+semail+"','"+sphone+"')";
            String query_signup = "INSERT INTO signup (meter_no, username, name, password, usertype) " +
                    "VALUES ('" + sstringmeter + "', '', '" + sname + "', '', 'Customer')";

            try{
                database d = new database();
                d.statement.executeUpdate(querycustomer);
                d.statement.executeUpdate(query_signup);
                JOptionPane.showMessageDialog(null,"Customer details added successfully");
                setVisible(false);
                new meterinfo(sstringmeter);

            } catch (Exception E) {
                E.printStackTrace();
            }
        }
        else{
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new newcustomer();
    }
}
