package electricity.billing.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class meterinfo extends JFrame implements ActionListener {
    JLabel meternumtext;
    Choice meterloc1,metertype1,phasecode1,billtype1;
    JButton submit;
    String meternum;
    meterinfo(String meternum){
        this.meternum = meternum;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(252,186,3));
        add(panel);
        
        JLabel heading =new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahome",Font.BOLD,20));
        panel.add(heading);
        
        JLabel meternum2= new JLabel("Meter Number");
        meternum2.setBounds(50,80,100,20);
        panel.add(meternum2);

        meternumtext = new JLabel(meternum);
        meternumtext.setBounds(180,80,150,20);
        panel.add(meternumtext);

        JLabel meterloc= new JLabel("Meter Loc");
        meterloc.setBounds(50,120,100,20);
        panel.add(meterloc);

        meterloc1 = new Choice();
        meterloc1.add("Outside");
        meterloc1.add("Inside");
        meterloc1.setBounds(180,120,150,20);
        panel.add(meterloc1);

        JLabel metertype= new JLabel("Meter Type");
        metertype.setBounds(50,160,100,20);
        panel.add(metertype);

        metertype1 = new Choice();
        metertype1.add("Electric Meter");
        metertype1.add("Solar Meter");
        metertype1.add("Smart Meter");
        metertype1.setBounds(180,160,150,20);
        panel.add(metertype1);

        JLabel phasecode = new JLabel("Phase Code");
        phasecode.setBounds(50,200,100,20);
        panel.add(phasecode);

        phasecode1 = new Choice();
        phasecode1.add("011");
        phasecode1.add("022");
        phasecode1.add("033");
        phasecode1.add("044");
        phasecode1.add("055");
        phasecode1.add("066");
        phasecode1.add("077");
        phasecode1.add("088");
        phasecode1.add("099");
        phasecode1.setBounds(180,200,150,20);
        panel.add(phasecode1);

        JLabel billtype = new JLabel("Bill Type");
        billtype.setBounds(50,240,100,20);
        panel.add(billtype);

        billtype1 = new Choice();
        billtype1.add("Normal");
        billtype1.add("Industrial");
        billtype1.setBounds(180,240,150,20);
        panel.add(billtype1);

        JLabel billdays = new JLabel("30 Days Billing Time...");
        billdays.setBounds(50,280,300,20);
        panel.add(billdays);

        JLabel note = new JLabel("Note:-");
        note.setBounds(50,320,100,20);
        panel.add(note);

        JLabel def = new JLabel("By default bill is calculated for 30 days only");
        def.setBounds(50,340,300,20);
        panel.add(def);

        submit= new JButton("Submit");
        submit.setBounds(230,390,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel,"Center");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
        Image i2 = i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imgl= new JLabel(i3);
        add(imgl,"East");

        setSize(700,500);
        setLocation(400,200);
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
            String smeternum = meternum;
            String smeterloc = meterloc1.getSelectedItem();
            String smetertype = metertype1.getSelectedItem();
            String sphasecode = phasecode1.getSelectedItem();
            String sbilltype = billtype1.getSelectedItem();
            String sday = "30";

            String query_meterinfo = "insert into meterinfo values('"+smeternum+"','"+smeterloc+"','"+smetertype+"','"+sphasecode+"','"+sbilltype+"','"+sday+"')";
            try{
                database c = new database();
                c.statement.executeUpdate(query_meterinfo);

                JOptionPane.showMessageDialog(null,"Meter Information Submitted Successfully");
                setVisible(false);
            }catch(Exception E){
                E.printStackTrace();

            }
        }
        else{
            setVisible(false);
        }
    }

    public static void main(String[] args){
        new meterinfo("");
    }
}
