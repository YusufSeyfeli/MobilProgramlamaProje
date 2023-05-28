package com.gazi.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView soruSayisiTextView;
    TextView soruTextView;
    Button aSikki,bSikki,cSikki,dSikki;
    Button cevaplaBtn;

    int skor=0;
    int soruSayisi = SoruCevaplar.sorular.length;
    int guncelSoruIndex = 0;
    String secilenSoru = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soruSayisiTextView = findViewById(R.id.soru_sayisi);
        soruTextView = findViewById(R.id.soru);
        aSikki = findViewById(R.id.a_Sikki);
        bSikki = findViewById(R.id.b_Sikki);
        cSikki = findViewById(R.id.c_Sikki);
        dSikki = findViewById(R.id.d_Sikki);
        cevaplaBtn = findViewById(R.id.cevap_btn);

        aSikki.setOnClickListener(this);
        bSikki.setOnClickListener(this);
        cSikki.setOnClickListener(this);
        dSikki.setOnClickListener(this);
        cevaplaBtn.setOnClickListener(this);

        soruSayisiTextView.setText("Soru Sayısı : " + soruSayisi);

        YeniSoruYukle();




    }

    @Override
    public void onClick(View view) {

        aSikki.setBackgroundColor(Color.RED);
        bSikki.setBackgroundColor(Color.RED);
        cSikki.setBackgroundColor(Color.RED);
        dSikki.setBackgroundColor(Color.RED);




        Button basılanButton = (Button) view;
        if(basılanButton.getId()==R.id.cevap_btn){
            if(secilenSoru.equals(SoruCevaplar.dogruCevaplar[guncelSoruIndex])){
                skor++;
            }
            guncelSoruIndex++;
            YeniSoruYukle();



        }else{

            secilenSoru = basılanButton.getText().toString();
            basılanButton.setBackgroundColor(Color.GREEN);

        }


    }

    void YeniSoruYukle(){
        if(guncelSoruIndex == soruSayisi){
            sorulariBitir();
            return;
        }



        soruTextView.setText(SoruCevaplar.sorular[guncelSoruIndex]);
        aSikki.setText(SoruCevaplar.cevaplar[guncelSoruIndex][0]);
        bSikki.setText(SoruCevaplar.cevaplar[guncelSoruIndex][1]);
        cSikki.setText(SoruCevaplar.cevaplar[guncelSoruIndex][2]);
        dSikki.setText(SoruCevaplar.cevaplar[guncelSoruIndex][3]);
    }

    void sorulariBitir(){
        String durum = "";
        if(skor > soruSayisi*0.50){
            durum = "Tebrikler.Biz bir deprem ülkesiyiz ve sen bu konuda gayet bilinçlisin.";
        }else if(skor == soruSayisi*0.50){
            durum = "Bu konuda bilinçlisin ama kendini daha da geliştirmelisin";
        }
        else{
            durum = "Bu konuda daha çok çalışman lazım.Öğrenmeye devam et ve sonra tekrar dene.";
        }

        new AlertDialog.Builder(this)
                .setTitle(durum)
                .setMessage("Skorun : "+skor+" Toplam soru :"+soruSayisi )
                .setPositiveButton("Tekrar",(dialogInterface, i) -> sinavTekrar() )
                .setCancelable(false)
                .show();


    }

    void sinavTekrar(){
        skor =0;
        guncelSoruIndex =0;
        YeniSoruYukle();
    }

}