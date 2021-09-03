package com.blk.blkpangkep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;

import co.nedim.maildroidx.MaildroidX;
import co.nedim.maildroidx.MaildroidXType;

public class RegisterActivity extends AppCompatActivity {

    private TextView tb_title;
    private Spinner jenis_kelamin,kejuruan;
    private Button submit;
    private EditText nama, nik, tl, nohp, email;
    private ImageView foto;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tb_title = (TextView) findViewById(R.id.toolbar_title);
        tb_title.setText(R.string.registrasi);

        //Initialie Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        //Initialize Spinner
        jenis_kelamin = (Spinner) findViewById(R.id.jenis_kelamin);
        kejuruan = (Spinner) findViewById(R.id.kejuruan);

        //Initialize Button
        submit = (Button) findViewById(R.id.bt_kirim);

        //Initialize EditText
        nama = (EditText) findViewById(R.id.edit_nama);
        nik = (EditText) findViewById(R.id.edit_nik);
        tl = (EditText) findViewById(R.id.edit_tl);
        nohp = (EditText) findViewById(R.id.edit_nohp);
        email = (EditText) findViewById(R.id.edit_email);

        //Initialize ImageView
        foto = (ImageView) findViewById(R.id.foto_register);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Email Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                if (nik.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "NIK Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                if (tl.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Tanggal Lahir Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                if (nohp.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "No HP Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                if (nama.getText().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                if (foto.getDrawable() == AppCompatResources.getDrawable(RegisterActivity.this, R.drawable.add_image)) {
                    Toast.makeText(RegisterActivity.this, "Foto Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                new MaildroidX.Builder()
                        .smtp("smtp.gmail.com")
                        .smtpUsername("Blkhebat@gmail.com")
                        .smtpPassword("Pangkep2021@")
                        .port("465")
                        .type(MaildroidXType.HTML)
                        .to(email.getText().toString().trim())
                        .from("Blkhebat@gmail.com")
                        .subject("Registrasi")
                        .attachment(uri.getPath())
                        .body("Data Pendaftaran Anda \n Nama: "+nama.getText().toString().trim()
                                +"\n NIK: "+nik.getText().toString().trim()
                                +"\n Tanggal Lahir: "+tl.getText().toString().trim()
                                +"\n Jenis Kelamin: "+jenis_kelamin.getSelectedItem().toString()
                                +"\n Kejuruan: "+kejuruan.getSelectedItem().toString()
                                +"\n No. HP: "+nohp.getText().toString().trim()
                                +"\n Email: "+email.getText().toString().trim())
                        .onCompleteCallback(new MaildroidX.onCompleteCallback() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(RegisterActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFail(@NonNull String s) {
                                Toast.makeText(RegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public long getTimeout() {
                                return 3000;
                            }
                        })
                        .mail();

                new MaildroidX.Builder()
                        .smtp("smtp.gmail.com")
                        .smtpUsername("Blkhebat@gmail.com")
                        .smtpPassword("Pangkep2021@")
                        .port("465")
                        .type(MaildroidXType.HTML)
                        .to("Blkhebat@gmail.com")
                        .from("Blkhebat@gmail.com")
                        .subject("Registrasi")
                        .attachment(uri.getPath())
                        .body("Data Pendaftaran Anda \n Nama: "+nama.getText().toString().trim()
                                +"<br> NIK: "+nik.getText().toString().trim()
                                +"<br> Tanggal Lahir: "+tl.getText().toString().trim()
                                +"<br> Jenis Kelamin: "+jenis_kelamin.getSelectedItem().toString()
                                +"<br> Kejuruan: "+kejuruan.getSelectedItem().toString()
                                +"<br> No. HP: "+nohp.getText().toString().trim()
                                +"<br> Email: "+email.getText().toString().trim())
                        .onCompleteCallback(new MaildroidX.onCompleteCallback() {
                            @Override
                            public void onSuccess() {
                                Toast.makeText(RegisterActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFail(@NonNull String s) {
                                Toast.makeText(RegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public long getTimeout() {
                                return 3000;
                            }
                        })
                        .mail();

            }
        });

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(RegisterActivity.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1000, 1000)
                        .start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            uri = data.getData();

            foto.setImageURI(uri);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Dibatalkan", Toast.LENGTH_SHORT).show();
        }
    }
}