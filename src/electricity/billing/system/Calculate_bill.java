package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Calculate_bill extends JFrame implements ActionListener {
    Choice meternumchoice,monthcho;
    JLabel nameText,addresstext;
    TextField unittext;
    JButton cancel,submit;
    Calculate_bill(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214,195,247));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(70,10 ,300,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);

        JLabel meternum = new JLabel("Meter Number");
        meternum.setBounds(50,80,100,20);
        panel.add(meternum);

        meternumchoice = new Choice();
        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer");
            while(resultSet.next()){
                meternumchoice.add(resultSet.getString("meter_no"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        meternumchoice.setBounds(180,80,100,20);
        panel.add(meternumchoice);

        JLabel name = new JLabel("Name");
        name.setBounds(50,120,100,20);
        panel.add(name);

        nameText = new JLabel("");
        nameText.setBounds(180,120,120,20);
        panel.add(nameText);

        JLabel address = new JLabel("Address");
        address.setBounds(50,160,100,20);
        panel.add(address);

        addresstext = new JLabel("");
        addresstext.setBounds(180,160,120,20);
        panel.add(addresstext);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no = '"+meternumchoice.getSelectedItem()+"'");
            while(resultSet.next()){
                nameText.setText(resultSet.getString("name"));
                addresstext.setText(resultSet.getString("address"));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        meternumchoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from new_customer where meter_no = '"+meternumchoice.getSelectedItem()+"'");
                    while(resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                        addresstext.setText(resultSet.getString("address"));
                    }
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        });

        JLabel unitconsumed = new JLabel("Unit Consumed");
        unitconsumed.setBounds(50,200,100,20);
        panel.add(unitconsumed);

        unittext = new TextField();
        unittext.setBounds(180,200,150,20);
        panel.add(unittext);

        JLabel month = new JLabel("Month");
        month.setBounds(50,240,100,20);
        panel.add(month);

        monthcho = new Choice();
        monthcho.add("January");
        monthcho.add("February");
        monthcho.add("March");
        monthcho.add("April");
        monthcho.add("May");
        monthcho.add("June");
        monthcho.add("July");
        monthcho.add("August");
        monthcho.add("September");
        monthcho.add("October");
        monthcho.add("November");
        monthcho.add("December");
        monthcho.setBounds(180,240,150,20);
        panel.add(monthcho);

        submit = new JButton("Submit");
        submit.setBounds(80,300,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Submit");
        cancel.setBounds(80,300,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel,"Center");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgl= new JLabel(i3);
        add(imgl,"East");

      setSize(650,400);
      setLocation(400,260);
      setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
          String smeter = meternumchoice.getSelectedItem();
          String sunit = unittext.getText();
          String smonth = monthcho.getSelectedItem();

          int totalbill = 0;
          int units = Integer.parseInt(sunit);
          String query_tax = "select * from tax";
          try{
              database c =new database();
              ResultSet resultSet = c.statement.executeQuery(query_tax);
              while(resultSet.next()){
                  totalbill += units*Integer.parseInt(resultSet.getString("cost_per_unit"));
                  totalbill += Integer.parseInt(resultSet.getString("meter_rent"));
                  totalbill += Integer.parseInt(resultSet.getString("service_charge"));
                  totalbill += Integer.parseInt(resultSet.getString("swach_bharat"));
                  totalbill += Integer.parseInt(resultSet.getString("fixed_tax"));

              }
          } catch (Exception E) {
              E.printStackTrace();
          }
            String query_total_bill = "insert into bill values('"+smeter+"', '"+smonth+"','"+sunit+"', '"+totalbill+"','Not Paid')";
          try{
              database c= new database();
              c.statement.executeUpdate(query_total_bill);

              JOptionPane.showMessageDialog(null,"Calculated Bill Succesfully");
              setVisible(false);
          } catch (SQLException E) {
              E.printStackTrace();
          }
        }
        else{
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new Calculate_bill();
    }
}
