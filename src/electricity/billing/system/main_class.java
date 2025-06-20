package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_class extends JFrame implements ActionListener {
    String acctype;
    String meter;
    main_class(String acctype,String meter){
        this.meter = meter;
        this.acctype = acctype;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon Imageicon = new ImageIcon(ClassLoader.getSystemResource("icon/ebs.png"));
        Image image = Imageicon.getImage().getScaledInstance(1300,700,Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(image);
        JLabel imagelabel = new JLabel(imageIcon2);
        add(imagelabel);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Menu");
        menu.setFont(new Font("serif",Font.PLAIN,15));

        JMenuItem newcust = new JMenuItem("New customer");
        newcust.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon customerimg = new ImageIcon(ClassLoader.getSystemResource("icon/newcustomer.png"));
        Image custimage = customerimg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        newcust.setIcon(new ImageIcon(custimage));
        newcust.addActionListener(this);
        menu.add(newcust);

        JMenuItem custdet = new JMenuItem("Customer details");
        custdet.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon custdetimg = new ImageIcon(ClassLoader.getSystemResource("icon/customerDetails.png"));
        Image custdet1 = custdetimg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        custdet.setIcon(new ImageIcon(custdet1));
        custdet.addActionListener(this);
        menu.add(custdet);

        JMenuItem depodet = new JMenuItem("Deposit details");
        depodet.setFont(new Font("monospaced",Font.PLAIN,14));
        ImageIcon depodetimg = new ImageIcon(ClassLoader.getSystemResource("icon/depositdetails.png"));
        Image depodet1 = depodetimg.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        depodet.setIcon(new ImageIcon(depodet1));
        depodet.addActionListener(this);
        menu.add(depodet);

        JMenuItem calbill = new JMenuItem("Calculate Bill");
        calbill.setFont(new Font("monospaced", Font.PLAIN, 14));
        ImageIcon calbillimg = new ImageIcon(ClassLoader.getSystemResource("icon/calculatorbills.png"));
        Image calbill1 = calbillimg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calbill.setIcon(new ImageIcon(calbill1));
        calbill.addActionListener(this);
        menu.add(calbill);

        JMenu info = new JMenu("Info");
        info.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem updateinfo = new JMenuItem("Update Information");
        updateinfo.setFont(new Font("monospaced", Font.PLAIN, 14));
        ImageIcon updateimg = new ImageIcon(ClassLoader.getSystemResource("icon/refresh.png"));
        Image update1 = updateimg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        updateinfo.setIcon(new ImageIcon(update1));
        updateinfo.addActionListener(this);
        info.add(updateinfo);

        JMenuItem viewinfo = new JMenuItem("View Information");
        viewinfo.setFont(new Font("monospaced", Font.PLAIN, 14));
        ImageIcon viewimg = new ImageIcon(ClassLoader.getSystemResource("icon/information.png")); // you can change the image too
        Image view1 = viewimg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewinfo.setIcon(new ImageIcon(view1));
        viewinfo.addActionListener(this);
        info.add(viewinfo);

        JMenu user = new JMenu("User");
        user.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem paybill = new JMenuItem("Pay Bill");
        paybill.setFont(new Font("monospeed",Font.PLAIN,14));
        ImageIcon paybill1 = new ImageIcon(ClassLoader.getSystemResource("icon/pay.png"));
        Image paybill2 = paybill1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        paybill.setIcon(new ImageIcon(paybill2));
        paybill.addActionListener(this);
        user.add(paybill);

        JMenuItem billdetails = new JMenuItem("Bill Details");
        billdetails.setFont(new Font("monospaced", Font.PLAIN, 14)); // Fix typo in "monospaced"
        ImageIcon billimg = new ImageIcon(ClassLoader.getSystemResource("icon/detail.png")); // you can change icon if needed
        Image billimage = billimg.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billdetails.setIcon(new ImageIcon(billimage));
        billdetails.addActionListener(this);
        user.add(billdetails);

        JMenu bill = new JMenu("Bill");
        bill.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem genbill = new JMenuItem("Generate Bill");
        genbill.setFont(new Font("monospaced", Font.PLAIN, 14)); // Fix typo in "monospaced"
        ImageIcon genbill1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png")); // you can change icon if needed
        Image genbill2 = genbill1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        genbill.setIcon(new ImageIcon(genbill2));
        genbill.addActionListener(this);
        bill.add(genbill);


        JMenu util =  new JMenu("Utility");
        util.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("monospaced", Font.PLAIN, 14));
        ImageIcon noteicon = new ImageIcon(ClassLoader.getSystemResource("icon/notepad.png")); // replace with appropriate icon
        Image noteimg = noteicon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(noteimg));
        notepad.addActionListener(this);
        util.add(notepad);

        JMenuItem calculator = new JMenuItem("Calculator");
        calculator.setFont(new Font("monospaced", Font.PLAIN, 14));
        ImageIcon calcicon = new ImageIcon(ClassLoader.getSystemResource("icon/calculator.png")); // make sure this icon exists
        Image calcimg = calcicon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(calcimg));
        calculator.addActionListener(this);
        util.add(calculator);

        JMenu exit =  new JMenu("Exit");
        exit.setFont(new Font("serif",Font.PLAIN,15));


        JMenuItem exit1 = new JMenuItem("Exit");
        exit1.setFont(new Font("monospaced", Font.PLAIN, 14));
        ImageIcon exiticon = new ImageIcon(ClassLoader.getSystemResource("icon/exit.png")); // make sure this icon exists
        Image exitimg = exiticon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        exit1.setIcon(new ImageIcon(exitimg));
        exit1.addActionListener(this);
        exit.add(exit1);

        if (acctype.equals("Admin")){
        menuBar.add(menu);
        }
        else{
            menuBar.add(info);
            menuBar.add(user);
            menuBar.add(bill);
        }
        menuBar.add(util);
        menuBar.add(exit);

        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = e.getActionCommand();
        if(msg.equals("New customer")){
            new newcustomer();
        } else if (msg.equals("Customer details")) {
            new customer_details();
        } else if (msg.equals("Deposit details")) {
            new deposit_details();
        }
        else if (msg.equals("Calculate Bill")){
            new Calculate_bill();
        }
        else if(msg.equals("View Information")){
           new  viewinfo(meter);
        } else if (msg.equals("Update Information")) {
            new updateinfo(meter);
        }
        else if (msg.equals("Bill Details")){
            new billdetails(meter);
        }
        else if (msg.equals("Calculator")){
            try{
                Runtime.getRuntime().exec("calc.exe");

            }catch(Exception E) {
                E.printStackTrace();
            }
        }
        else if (msg.equals("Notepad")){
            try{
                Runtime.getRuntime().exec("notepad.exe");

            }catch(Exception E) {
                E.printStackTrace();
            }
        }
        else if (msg.equals("Exit")){
            setVisible(false);
            new Login();
        }
        else if (msg.equals("Pay Bill")){
            new pay_bill(meter);
        }
        else if(msg.equals("Generate Bill")){
            new generate_bill(meter);
        }


    }

    public static void main(String[] args){
        new main_class("","");
    }
}
