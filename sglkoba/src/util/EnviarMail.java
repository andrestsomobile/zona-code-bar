package util;

import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EnviarMail {
	public EnviarMail() {
		super();
	}

	public boolean enviarMail(String destinatario, String subject, String mensaje)  {

		
		String host = "smtp.office365.com";
		String puerto = "587";
		String usuario = "avisos.logim@koba-group.com";
		String from = "avisos.logim@koba-group.com";
		String pass = "Akoba3020..";
		
		try {
			Properties props = new Properties();

			// Nombre del host de correo, es smtp.gmail.com
			props.setProperty("mail.smtp.host", host);

			// TLS si est� disponible
			props.setProperty("mail.smtp.starttls.enable", "true"); /** estaba false*/

			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port", puerto);

			// Nombre del usuario
			props.setProperty("mail.smtp.user", usuario);

			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth", "true");

			// Session session = Session.getDefaultInstance(props);
			sistemas auth = new sistemas();
			Session session = Session.getInstance(props, auth);

			// Para obtener un log de salida m�s extenso
			session.setDebug(true);

			// genero el mensaje
			MimeMessage message = new MimeMessage(session);

			// Quien envia el correo
			message.setFrom(new InternetAddress(from));

			// A quien va dirigido
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			StringTokenizer stk = new StringTokenizer(destinatario, ",");
			int i = 0;
			while (stk.hasMoreElements()) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(stk.nextToken().trim()));
			}
			// asunto y texto del mensaje
			message.setSubject(subject);
			mensaje = "<table><tr><td><img style='margin-right: auto; margin-left: auto; display: block;' src='http://www.sli.com.co/images/correokoba.png' alt='' width='50%' height='50%'></td><td nowrap='nowrap'>Notificaci&oacute;n Autom&aacute;tica de SGL</td></tr></table>" 
			+ mensaje +
			 "<br>" + "<br>" + "Este correo es autom�tico y no es monitoreado por favor no responder, cualquier solicitud por favor dirigirla al Coordinador de Sistemas de SLI.";
			message.setText(mensaje, "UTF-8", "html");
			Transport t = session.getTransport("smtp");

			// Aqui usuario y password del servidor de correo
			t.connect(host, from, pass);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean enviarMail_Adjunto(String destinatario, String subject, String mensaje, String filename) {
		String host = "mail.sli.com.co";
		String puerto = "25";
		String usuario = "aviso@sli.com.co";
		String from = "aviso@sli.com.co";
		String pass = "U6sz9tBzo9M";
		try {
			Properties props = new Properties();

			// Nombre del host de correo, es smtp.gmail.com
			props.setProperty("mail.smtp.host", host);

			// TLS si est� disponible
			props.setProperty("mail.smtp.starttls.enable", "false");

			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port", puerto);

			// Nombre del usuario
			props.setProperty("mail.smtp.user", usuario);

			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth", "true");

			// Session session = Session.getDefaultInstance(props);
			sistemas auth = new sistemas();
			Session session = Session.getInstance(props, auth);

			// Para obtener un log de salida m�s extenso
			session.setDebug(false);

			// genero el mensaje
			MimeMessage message = new MimeMessage(session);

			// Quien envia el correo
			message.setFrom(new InternetAddress(from));

			// A quien va dirigido
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			//message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			StringTokenizer stk = new StringTokenizer(destinatario, ",");
			int i = 0;
			while (stk.hasMoreElements()) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(stk.nextToken().trim()));
			}
			// asunto y texto del mensaje
			message.setSubject(subject);
			//message.setText(mensaje);

			MimeBodyPart mbp1 = new MimeBodyPart();
		      mbp1.setText(mensaje + "\n" + "\n" + "Este correo es autom�tico y no es monitoreado por favor no responder, cualquier solicitud por favor dirigirla al coordinador de sistemas.");


		      // create the second message part
		      MimeBodyPart mbp2 = new MimeBodyPart();

		            // attach the file to the message
		         FileDataSource fds = new FileDataSource(filename);
		      mbp2.setDataHandler(new DataHandler(fds));
		      mbp2.setFileName(fds.getName());

		      // create the Multipart and add its parts to it
		      Multipart mp = new MimeMultipart();
		      mp.addBodyPart(mbp1);
		      mp.addBodyPart(mbp2);

		      // add the Multipart to the message
		      message.setContent(mp);

			Transport t = session.getTransport("smtp");

			// Aqui usuario y password del servidor de correo
			t.connect(host, from, pass);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean enviarMail_Adjuntos(String destinatario, String subject, String mensaje, String[] filename) {
		String host = "mail.sli.com.co";
		String puerto = "25";
		String usuario = "aviso@sli.com.co";
		String from = "aviso@sli.com.co";
		String pass = "U6sz9tBzo9M";
		try {
			Properties props = new Properties();

			// Nombre del host de correo, es smtp.gmail.com
			props.setProperty("mail.smtp.host", host);

			// TLS si est� disponible
			props.setProperty("mail.smtp.starttls.enable", "false");

			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port", puerto);

			// Nombre del usuario
			props.setProperty("mail.smtp.user", usuario);

			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth", "true");

			// Session session = Session.getDefaultInstance(props);
			sistemas auth = new sistemas();
			Session session = Session.getInstance(props, auth);

			// Para obtener un log de salida m�s extenso
			session.setDebug(false);

			// genero el mensaje
			MimeMessage message = new MimeMessage(session);

			// Quien envia el correo
			message.setFrom(new InternetAddress(from));

			// A quien va dirigido
			// message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			// message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			StringTokenizer stk = new StringTokenizer(destinatario, ",");
			int i = 0;
			while (stk.hasMoreElements()) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(stk.nextToken().trim()));
			}
			// asunto y texto del mensaje
			message.setSubject(subject);
			// message.setText(mensaje);

			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(mensaje + "\n" + "\n" + "Este correo es autom�tico y no es monitoreado por favor no responder, cualquier solicitud por favor dirigirla al coordinador de sistemas.");

			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message
			
			FileDataSource fds = new FileDataSource(filename);
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());

			// create the Multipart and add its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);

			// add the Multipart to the message
			message.setContent(mp);

			Transport t = session.getTransport("smtp");

			// Aqui usuario y password del servidor de correo
			t.connect(host, from, pass);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

	class sistemas extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			String username = "usuario@servidor.com";
			String password = "password";

			return new PasswordAuthentication(username, password);
		}
	}
}
