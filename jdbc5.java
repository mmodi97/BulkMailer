import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

public class jdbc5
    implements ActionListener
{

    JFrame f;
    JLabel jl1;
    JLabel jl2;
    JLabel jl3;
    JLabel jl4;
    JLabel jl5;
    JTextField jf1;
    JTextField jf2;
    JPasswordField jpas;
    Container con;
    JButton b1;
    JButton b2;
    Font fo;
    static String retrievedata;
    static String email;
    static String password;
    ResultSet rs;

    jdbc5(String string)
    {
        f = new JFrame("Login Page");
        con = new Container();
        b1 = new JButton("Login");
        con = f.getContentPane();
        f.setSize(1500, 800);
        f.setLayout(null);
        con.setBackground(Color.cyan);
        f.setVisible(true);
        jl1 = new JLabel("Username");
        jl1.setFont(new Font("Serif", 0, 24));
        jl1.setForeground(Color.blue);
        jl2 = new JLabel("Password");
        jl2.setFont(new Font("Serif", 0, 24));
        jl2.setForeground(Color.blue);
        jl3 = new JLabel("");
        jl3.setFont(new Font("Serif", 0, 16));
        jl3.setForeground(Color.red);
        jl4 = new JLabel("Login");
        jl4.setFont(new Font("Serif", 0, 30));
        jl4.setForeground(Color.black);
        jf1 = new JTextField();
        jpas = new JPasswordField();
        con.add(jl1);
        con.add(jpas);
        con.add(jf1);
        con.add(b1);
        con.add(jl2);
        con.add(jl3);
        con.add(jl4);
        jl1.setBounds(50, 100, 100, 30);
        jl2.setBounds(50, 150, 100, 30);
        jf1.setBounds(170, 100, 150, 30);
        jpas.setBounds(170, 150, 150, 30);
        jl3.setBounds(170, 180, 150, 20);
        jl4.setBounds(150, 10, 100, 100);
        b1.setBounds(170, 200, 90, 30);
        f.setDefaultCloseOperation(3);
        b1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == b1)
        {
            email = jf1.getText();
            password = jpas.getText();
            new SendingMail();
            f.setVisible(false);
        }
    }

    public static void main(String args[])
    {
        new jdbc5("frame");
    }
}
