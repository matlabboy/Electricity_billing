package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class updateinfo extends JFrame implements ActionListener {
    String meter;
    JLabel nametext,addresstext,phonetext,citytext,statetext,emailtext;
    JButton cancel , update;

    updateinfo(String meter){
        this.meter = meter;
        setBounds(400,150,777,450);
        getContentPane().setBackground(new Color(229,255,227));
        setLayout(null);

        JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(50,10,400,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(30,70,100,20);
        add(name);

        nametext = new JLabel("");
        nametext.setBounds(150,70,200,20);
        add(nametext);

        JLabel meterno = new JLabel("Meter Number");
        meterno.setBounds(30,100,100,20);
        add(meterno);

        JLabel metertext = new JLabel("");
        metertext.setBounds(150,100,100,20);
        add(metertext);

        JLabel address = new JLabel("Address");
        address.setBounds(30,150,100,10);
        add(address);

        addresstext = new JLabel();
        addresstext.setBounds(150,150,200,20);
        add(addresstext);

        JLabel city = new JLabel("City");
        city.setBounds(30,190,100,20);
        add(city);

        citytext = new JLabel();
        citytext.setBounds(150,190,200,20);
        add(citytext);

        JLabel state = new JLabel("State");
        state.setBounds(30,230,100,20);
        add(state);

        statetext = new JLabel();
        statetext.setBounds(150,230,200,20);
        add(statetext);

        JLabel email = new JLabel("Email");
        email.setBounds(30,270,100,20);
        add(email);

        emailtext = new JLabel();
        emailtext.setBounds(150,270,200,20);
        add(emailtext);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(30,310,100,20);
        add(phone);

        phonetext = new JLabel();
        phonetext.setBounds(150,310,200,20);
        add(phonetext);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no = '"+meter+"'");
            if (resultSet.next()){
                nametext.setText(resultSet.getString("name"));
                metertext.setText(resultSet.getString("meter_no"));
                addresstext.setText(resultSet.getString("address"));
                citytext.setText(resultSet.getString("city"));
                statetext.setText(resultSet.getString("state"));
                emailtext.setText(resultSet.getString("email"));
                phonetext.setText(resultSet.getString("phone"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        update = new JButton("Update");
        update.setBackground(new Color(24,118,242));
        update.setForeground(Color.white);
        update.setBounds(50,350,120,25);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBackground(new Color(24,118,242));
        cancel.setForeground(Color.white);
        cancel.setBounds(200,350,120,25);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
        Image i2 = i1.getImage().getScaledInstance(400,410,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgl = new JLabel(i3);
        imgl.setBounds(360,0,400,410);
        add(imgl);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== update) {
            String saddress = addresstext.getText();
            String scity = citytext.getText();
            String sstate = statetext.getText();
            String semail = emailtext.getText();
            String sphone = phonetext.getText();

            try {
                database c = new database();
                c.statement.executeUpdate("update new_customer set address ='" + saddress + "',city ='" + scity + "', state='" + sstate + "', email='" + semail + "', phone='" + sphone + "' where meter_no ='" + meter + "'");

                JOptionPane.showMessageDialog(null, "User Information Updated Successfully");
                setVisible(false);
            } catch (Exception E) {
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }


    public static void main(String[] args){
        new updateinfo("");
    }
}
