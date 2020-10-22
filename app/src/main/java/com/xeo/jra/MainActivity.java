package com.xeo.jra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1603266218544-cbea802084ea?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1189&q=80"));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1603266144600-48865b46030b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1488&q=80"));
        slideModels.add(new SlideModel("https://images.unsplash.com/photo-1603267276798-c69da0853f80?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1530&q=80"));

        imageSlider.setImageList(slideModels, true);

    }

    public void silaturahmiKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void websiteKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void EmailKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void CallcenterKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void jraKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void diagnosisKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void ruqyahKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void alquranKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void kitabKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void ayatKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void dzikirKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void ziarahMakamKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void ThibbunKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void BekamKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void TotokKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void GurahKlik(View view) {
        Toast.makeText(this, "Mohon Maaf fitur ini belum bisa diakses :(", Toast.LENGTH_SHORT).show();
    }

    public void searchKlik(View view) {
        Intent maps= new Intent(MainActivity.this, search.class);
        startActivity(maps);
    }
}