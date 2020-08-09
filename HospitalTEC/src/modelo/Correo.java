package modelo;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author yasu8
 */
public class Correo {
    
        /**
     * Metodo encargado de generar el pedf de la factura
     * @return enviado
     * @param pCorreo
     * @param pFactura
     * @throws AddressException
     * @throws MessagingException
     */
    public  boolean enviarFactura(String pCorreo, String pFactura) throws AddressException, MessagingException{
        boolean enviado=false;
        String Host = "smtp.gmail.com";
        String Password = "Prueba123456789$$";
        String from = "pruebasprograpoo@gmail.com";
        String toAddress = pCorreo;
        String filename = pFactura;
        // Get system properties
        Properties props = System.getProperties();
        props.put("mail.smtp.Host", Host);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, toAddress);
        message.setSubject("Factura de compra");
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Esta es su factura");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName("Factura.pdf");
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        try {
            Transport tr = session.getTransport("smtps");
            tr.connect(Host, from, Password);
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("Factura enviada al correo del cliente");
            enviado=true;
            tr.close();
        } catch (SendFailedException sfe) {
            System.out.println(sfe);
        }
    return enviado;
    }
    
}

