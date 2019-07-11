package com.example.apijson;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class sendMail extends AsyncTask<Void,Void,Void> {
    private Context context;
    private Session session;

    private String to;
    private String subject;
    private String message;
    private ProgressDialog progressDialog;


    public sendMail(Context context,String to,String subject, String message) {
        this.context=context;
        this.to=to;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");//465
        props.put("mail.smtp.starttls.enable","true");

        session=Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Config.email,Config.pass);
            }
        });
        try {
            MimeMessage mm = new MimeMessage(session);

            mm.setFrom(new InternetAddress(Config.email));
            mm.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mm.setSubject(subject);
            mm.setText(message);

            Transport.send(mm);
        }  catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,"جاري إرسال التذكرة","الرجاء الإنتظار ....",false,false);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Toast.makeText(context,"تم إرسال التذكرة للإيميل",Toast.LENGTH_LONG).show();
    }
}
