package electricity.billing.system;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class viewinfo extends JFrame implements ActionListener {
    String view;
    JButton cancel;
    viewinfo(String view){
        this.view = view;
        setBounds(350,150,850,650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(250,0,500,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel namelabel = new JLabel("Name");
        namelabel.setBounds(70,80,100,20);
        add(namelabel);

        JLabel namelabeltext = new JLabel("");
        namelabeltext.setBounds(200,80,150,20);
        add(namelabeltext);

        JLabel meterno = new JLabel("Meter Number");
        meterno.setBounds(70,140,100,20);
        add(meterno);

        JLabel meternotext = new JLabel("");
        meternotext.setBounds(200,140,150,20);
        add(meternotext);

        JLabel address = new JLabel("Address");
        address.setBounds(70,200,100,20);
        add(address);

        JLabel addresstext = new JLabel("");
        addresstext.setBounds(200,200,100,20);
        add(addresstext);

        JLabel city = new JLabel("City");
        city.setBounds(70,260,100,20);
        add(city);

        JLabel citytext = new JLabel("");
        citytext.setBounds(200,260,100,20);
        add(citytext);

        JLabel state = new JLabel("State");
        state.setBounds(500,80,100,20);
        add(state);

        JLabel statetext = new JLabel("");
        statetext.setBounds(600,80,150,20);
        add(statetext);

        JLabel email = new JLabel("Email");
        email.setBounds(500,140,100,20);
        add(email);

        JLabel emailtext = new JLabel("");
        emailtext.setBounds(600,140,100,20);
        add(emailtext);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(500,200,100,20);
        add(phone);

        JLabel phonetext = new JLabel("");
        phonetext.setBounds(600,200,100,20);
        add(phonetext);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no = '"+view+"'");
            if (resultSet.next()){
                namelabeltext.setText(resultSet.getString("name"));
                meternotext.setText(resultSet.getString("meter_no"));
                addresstext.setText(resultSet.getString("address"));
                citytext.setText(resultSet.getString("city"));
                statetext.setText(resultSet.getString("state"));
                emailtext.setText(resultSet.getString("email"));
                phonetext.setText(resultSet.getString("phone"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cancel = new JButton("Cancel");
        cancel.setBackground(new Color(24,118,242));
        cancel.setForeground(Color.white);
        cancel.setBounds(220,350,120,25);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
        Image i2 = i1.getImage().getScaledInstance(600,300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgl = new JLabel(i3);
        imgl.setBounds(100,320,600,300);
        add(imgl);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cancel){
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new viewinfo("");
    }
}
