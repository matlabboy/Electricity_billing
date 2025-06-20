package electricity.billing.system;
import java.awt.Desktop;
import java.net.URI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class payment_bill extends JFrame implements ActionListener {
    JButton back;
    String meter;
    payment_bill(String meter){
        this.meter = meter;
        JEditorPane j = new JEditorPane();
        j.setEditable(false);

        try {
            Desktop.getDesktop().browse(new java.net.URI("https://paytm.com/online-payments"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        JScrollPane pane = new JScrollPane(j);
        add(pane);

        back = new JButton("Back");
        back.setBounds(640,20,80,30);
        back.addActionListener(this);
        j.add(back);


        setSize(800,600);
        setLocation(400,150);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new pay_bill(meter);
    }

    public static void main(String[] args) {
        new payment_bill("");
    }
}