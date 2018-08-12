import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;

public class SendingMail
    implements ActionListener
{

    JFrame jf;
    JTextField jf1;
    JTextField jf2;
    JTextField jf3;
    JTextField jf4;
    JTextArea ja1;
    JButton send;
    JButton attach;
    JButton attachto;
    JLabel jl1;
    JLabel jl2;
    JLabel jl3;
    JLabel jl4;
    JLabel jl5;
    Container con;
    static String filepath;
    static String reciverdata;

    SendingMail()
    {
        jf = new JFrame("send mail");
        jf1 = new JTextField();
        con = jf.getContentPane();
        jf.setVisible(true);
        jf.setLayout(null);
        jf.setSize(1000, 1000);
        jf.getDefaultCloseOperation();
        jf1 = new JTextField(jdbc5.email);
        jf1.disable();
        jf2 = new JTextField();
        jf3 = new JTextField();
        jf4 = new JTextField();
        ja1 = new JTextArea("Hello");
        send = new JButton("send mail");
        attach = new JButton("attach file");
        attachto = new JButton("attach File");
        jl1 = new JLabel("From");
        jl2 = new JLabel("To");
        jl3 = new JLabel("Subject");
        jl4 = new JLabel("Body");
        jl5 = new JLabel();
        jl1.setBounds(10, 10, 70, 30);
        jl2.setBounds(10, 90, 70, 30);
        jl3.setBounds(10, 150, 70, 30);
        jf1.setBounds(100, 10, 170, 30);
        jf2.setBounds(100, 90, 170, 30);
        jf3.setBounds(100, 150, 170, 30);
        jl4.setBounds(10, 220, 70, 30);
        ja1.setBounds(100, 220, 300, 300);
        jf4.setBounds(420, 250, 200, 30);
        jl5.setBounds(450, 600, 150, 30);
        attach.setBounds(420, 300, 100, 30);
        send.setBounds(290, 550, 100, 40);
        attachto.setBounds(290, 90, 140, 30);
        jf.add(jl1);
        jf.add(jl2);
        jf.add(jl3);
        jf.add(jf1);
        jf.add(jf2);
        jf.add(jf3);
        jf.add(ja1);
        jf.add(jf4);
        jf.add(jl4);
        jf.add(attach);
        jf.add(send);
        jf.add(attachto);
        jf.add(jl5);
        send.addActionListener(this);
        attach.addActionListener(this);
        attachto.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == send)
        {
            Properties p = System.getProperties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", "465");
            p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            Session session = Session.getDefaultInstance(p, new Authenticator() {

                final SendingMail this$0;

                protected PasswordAuthentication getPasswordAuthentication()
                {
                    return new PasswordAuthentication(jdbc5.email, jdbc5.password);
                }

            
            {
                this$0 = SendingMail.this;
                
            }
            });
            Message message = new MimeMessage(session);
            try
            {
                message.setFrom(new InternetAddress(jf1.getText()));
                message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(jf2.getText()));
                message.setSubject(jf3.getText());
                message.setSentDate(new Date());
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(ja1.getText());
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                messageBodyPart = new MimeBodyPart();
                String filename = filepath;
                javax.activation.DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
                Transport.send(message);
            }
            catch(AddressException e1)
            {
                e1.printStackTrace();
            }
            catch(MessagingException e1)
            {
                e1.printStackTrace();
            }
            jl5.setText("Success");
        }
        if(e.getSource() == attach)
        {
            JFileChooser fc = new JFileChooser();
            int i = fc.showOpenDialog(fc);
            if(i == 0)
            {
                File f = fc.getSelectedFile();
                filepath = f.getPath();
                jf4.setText(filepath);
            }
        }
        if(e.getSource() == attachto)
        {
            JFileChooser fc = new JFileChooser();
            int i = fc.showOpenDialog(fc);
            if(i == 0)
            {
                File f = fc.getSelectedFile();
                filepath = f.getPath();
                try
                {
                    FileReader fr = new FileReader(filepath);
                    BufferedReader br = new BufferedReader(fr);
                    String p;
                    while((p = br.readLine()) != null) 
                    {
                        System.out.print(p);
                        jf2.setText(p);
                    }
                    br.close();
                    fr.close();
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        
    }
    public static void main(String[] args) {
		new SendingMail();
	}
}
