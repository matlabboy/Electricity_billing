package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class deposit_details extends JFrame implements ActionListener {
    Choice searchMeterCho,searchmonthly;
    JTable table;
    JButton search,print,close;
    deposit_details(){
        super("Deposit Details");
        getContentPane().setBackground(new Color(192,186,154));
        setSize(700,500);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);

        JLabel searchm = new JLabel("Search By Meter Number");
        searchm.setBounds(20,20,150,20);
        add(searchm);

        searchMeterCho = new Choice();
        searchMeterCho.setBounds(180,20,150,20);
        add(searchMeterCho);

        try{
            database c= new database();
            ResultSet resultSet = c.statement.executeQuery("select * from bill");
            while (resultSet.next()){
                searchMeterCho.add(resultSet.getString("meter_no"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        JLabel searchmonth = new JLabel("Search By Month");
        searchmonth.setBounds(400,20,100,20);
        add(searchmonth);



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
        searchmonthly.setBounds(520,20,150,20);
        add(searchmonthly);


        table = new JTable();
        try{
            database c = new database();
            ResultSet resultSet  = c.statement.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception E) {
            E.printStackTrace();
        }
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,700,500);
        scrollPane.setBackground(Color.white);
        add(scrollPane);

        search = new JButton("Search");
        search.setBackground(Color.white);
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.white);
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.white);
        close.setBounds(600,70,80,20);
        close.addActionListener(this);
        add(close);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==search){
            String  query_search = "select * from bill where meter_no = '"+searchMeterCho.getSelectedItem()+"' and month = '"+searchmonthly.getSelectedItem()+"'";
            try{
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try {
                table.print();
            } catch (PrinterException E) {
                E.printStackTrace();
            }
        }
        else {
            setVisible(false);
        }
    }
    public static void main(String[] args){
        new deposit_details();
    }
}
