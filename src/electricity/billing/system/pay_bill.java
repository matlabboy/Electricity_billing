package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class pay_bill extends JFrame implements ActionListener
{
    String meter ;
    Choice searchmonthly;
    JButton pay,back;
    pay_bill(String meter){
        this.meter = meter;
    setSize(900,600);
    setLocation(300,150);
    setLayout(null);

        JLabel heading = new JLabel("Pay Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD,24));
        heading.setBounds(120,5,400,30);
        add(heading);

        JLabel meterno = new JLabel("Meter Number");
        meterno.setBounds(35,80,200,20);
        add(meterno);

        JLabel meternotext = new JLabel("");
        meternotext.setBounds(300,80,200,20);
        add(meternotext);

        JLabel name = new JLabel("Name");
        name.setBounds(35,140,200,20);
        add(name);

        JLabel nametext = new JLabel("");
        nametext.setBounds(300,140,200,20);
        add(nametext);

        JLabel month = new JLabel("Month");
        month.setBounds(35,200,200,20);
        add(month);

        searchmonthly = new Choice();
        searchmonthly.add("January");
        searchmonthly.add("February");
        searchmonthly.add("March");
        searchmonthly.add("April");
        searchmonthly.add("May");
        searchmonthly.add("June");
        searchmonthly.add("July");
        searchmonthly.add("August");
        searchmonthly.add("September");
        searchmonthly.add("October");
        searchmonthly.add("November");
        searchmonthly.add("December");
        searchmonthly.setBounds(300,200,150,20);
        add(searchmonthly);

        JLabel unit = new JLabel("Unit");
        unit.setBounds(35,260,200,20);
        add(unit);

        JLabel unittext = new JLabel();
        unittext.setBounds(300,260,200,20);
        add(unittext);

        JLabel totalbill = new JLabel("Total Bill");
        totalbill.setBounds(35,320,200,20);
        add(totalbill);

        JLabel totalbilltext = new JLabel();
        totalbilltext.setBounds(300,320,200,20);
        add(totalbilltext);

        JLabel status = new JLabel("Status");
        status.setBounds(35,380,200,20);
        add(status);

        JLabel statustext = new JLabel();
        statustext.setBounds(300,380,200,20);
        statustext.setForeground(Color.red);
        add(statustext);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no = '"+meter+"'");
            while(resultSet.next()){
                meternotext.setText(meter);
                nametext.setText(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchmonthly.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                database c = new database();
                try {
                    ResultSet resultSet = c.statement.executeQuery("select * from bill where meter_no = '"+meter+"' and month = '"+searchmonthly.getSelectedItem()+"'");
                    while (resultSet.next()){
                        unittext.setText(resultSet.getString("unit"));
                        totalbilltext.setText(resultSet.getString("total_bill"));
                        statustext.setText(resultSet.getString("status"));

                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        pay = new JButton("Pay");
        pay.setBackground(Color.white);
        pay.setForeground(Color.black);
        pay.setBounds(100,460,100,25);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBackground(Color.white);
       back.setForeground(Color.black);
        back.setBounds(230,460,100,25);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==pay){
            try{
                database c = new database();
                c.statement.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' and month = '"+searchmonthly.getSelectedItem()+"'");
            } catch (SQLException E) {
                E.printStackTrace();
            }
            setVisible(false);
            new payment_bill(meter);
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new pay_bill("");
    }
}
