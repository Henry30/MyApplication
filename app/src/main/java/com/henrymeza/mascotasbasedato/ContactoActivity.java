package com.henrymeza.mascotasbasedato;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactoActivity extends AppCompatActivity {

    Session session= null;
    ProgressDialog pdialog=null;
    Context context=null;
    private Toolbar mnuToolBar;
    private EditText txtNombre,txtEmail,txtComentario;
    String rec, subject, textMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        mnuToolBar=(Toolbar)findViewById(R.id.miactionbar);
        mnuToolBar.setTitle("");

        mnuToolBar.setLogo(R.drawable.cat_footprint_48);
        setSupportActionBar(mnuToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(false);

        context=this;
        Button btn=(Button)findViewById(R.id.btnEnviarAContacto);
        txtNombre=(EditText) findViewById(R.id.txtNombreAContaco);
        txtEmail=(EditText) findViewById(R.id.txtEmailAContacto);
        txtComentario=(EditText) findViewById(R.id.txtComentarioAContacto);

    }

    public  boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        MenuItem mnuRank=menu.findItem(R.id.mnuRanking);
        MenuItem mnuAbout=menu.findItem(R.id.mnuAbout);
        MenuItem mnuContacto=menu.findItem(R.id.mnuContacto);
        mnuRank.setVisible(false);
        mnuAbout.setVisible(false);
        mnuContacto.setVisible(false);
        return true;
    }

    public void btnEnviarAContacto_Click(View v) {

        rec=txtEmail.getText().toString();
        subject=txtNombre.getText().toString();
        textMessage=txtComentario.getText().toString();

        Properties prop= new Properties();

        //Correo de Hotmail
        prop.put("mail.smtp.host","smtp.live.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port","587");
        prop.put("mail.smtp.auth","true");

        session=Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("IngreseCorreoEmisor@hotmail.com","IngreseContraseña");
                //return super.getPasswordAuthentication();
            }
        });
        pdialog=ProgressDialog.show(context,"","Enviando Correo...",true);

        RetreiveFeedTask task= new RetreiveFeedTask();
        task.execute();
    }

    class  RetreiveFeedTask extends AsyncTask<String,Void,String> {
        protected String doInBackground(String... params) {
            try {
                Message msg= new MimeMessage(session);
                msg.setFrom(new InternetAddress("IngreseCorreoEmisor@hotmail.com"));
                msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(rec));
                msg.setSubject(subject);
                msg.setContent(textMessage,"text/html; charset=utf-8");
                Transport.send(msg);

            }catch (MessagingException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        public void btnEnviarAContacto_Click(View v) {

            rec=txtEmail.getText().toString();
            subject=txtNombre.getText().toString();
            textMessage=txtComentario.getText().toString();

            Properties prop= new Properties();

            //Correo de Hotmail
            prop.put("mail.smtp.host","smtp.live.com");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.port","587");
            prop.put("mail.smtp.auth","true");

            session= Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("IngreseCorreoEmisor@hotmail.com","IngreseContraseña");
                    //return super.getPasswordAuthentication();
                }
            });
            pdialog= ProgressDialog.show(context,"","Enviando Correo...",true);

            RetreiveFeedTask task= new RetreiveFeedTask();
            task.execute();
        }
        protected void onPostExecute(String result){
            View v=findViewById(R.id.activity_contacto);

            pdialog.dismiss();
            txtEmail.setText("");
            txtNombre.setText("");
            txtComentario.setText("");

            Snackbar.make(v,getResources().getString(R.string.mensajeenviado),Snackbar.LENGTH_SHORT)
                    .setAction(getResources().getString(R.string.texto_Accion), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("SNACKBAR","Click en SnackBar");
                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                    .show();
        }
    }
}
