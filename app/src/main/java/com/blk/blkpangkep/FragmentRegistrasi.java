package com.blk.blkpangkep;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import co.nedim.maildroidx.MaildroidX;
import co.nedim.maildroidx.MaildroidXType;

public class FragmentRegistrasi extends Fragment {

    private TextView tb_title;
    private Spinner jenis_kelamin,kejuruan;
    private Button submit;
    private EditText nama, nik, tl, nohp, email;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registrasi, parent, false);
    }

    // Method ini dipanggil sesaat setelah onCreateView().
    // Semua pembacaan view dan penambahan listener dilakukan disini (atau
    // bisa juga didalam onCreateView)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //Initialize TextView
        tb_title = (TextView) view.findViewById(R.id.toolbar_title);
        tb_title.setText(R.string.registrasi);

        //Initialie Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar_main);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        //Initialize Spinner
        jenis_kelamin = (Spinner) view.findViewById(R.id.jenis_kelamin);
        kejuruan = (Spinner) view.findViewById(R.id.kejuruan);

        //Initialize Button
        submit = (Button) view.findViewById(R.id.bt_kirim);

        //Initialize EditText
        nama = (EditText) view.findViewById(R.id.edit_nama);
        nik = (EditText) view.findViewById(R.id.edit_nik);
        tl = (EditText) view.findViewById(R.id.edit_tl);
        nohp = (EditText) view.findViewById(R.id.edit_nohp);
        email = (EditText) view.findViewById(R.id.edit_email);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().length() == 0) {
                    Toast.makeText(getContext(), "Email Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                if (nik.getText().length() == 0) {
                    Toast.makeText(getContext(), "NIK Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                if (tl.getText().length() == 0) {
                    Toast.makeText(getContext(), "Tanggal Lahir Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                if (nohp.getText().length() == 0) {
                    Toast.makeText(getContext(), "No HP Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }
                if (nama.getText().length() == 0) {
                    Toast.makeText(getContext(), "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getContext(), "Sukses", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFail(@NonNull String s) {
                                Toast.makeText(getContext(), "Gagal", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(getContext(), "Sukses", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFail(@NonNull String s) {
                                Toast.makeText(getContext(), "Gagal", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public long getTimeout() {
                                return 3000;
                            }
                        })
                        .mail();

            }
        });
    }
}
