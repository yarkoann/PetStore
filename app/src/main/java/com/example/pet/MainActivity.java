package com.example.pet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private TextView mTEXTview;

    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImage = (ImageView) findViewById(R.id.imageView);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.INVISIBLE);

        mTEXTview = (TextView) findViewById(R.id.textView);
        OnClick();


    }

    public void OnClick(){
        PetAPI api = PetAPI.retrofit.create(PetAPI.class);
        Call<Pet> MYPET = api.getPet();
        mProgressBar.setVisibility(View.VISIBLE);
        MYPET.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                if (response.isSuccessful()){
                    Pet cute = response.body();
                    mTEXTview.setText(cute.getName());
                    Picasso.get().load(cute.getPhotoUrls().get(0)).into(mImage);
                    mProgressBar.setVisibility(View.INVISIBLE);


                } else {
                    Toast.makeText(MainActivity.this, "Ошибка на сервере: "+response.errorBody()
                            , Toast.LENGTH_SHORT).show();
                    mProgressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Что-то не так " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                Log.d("GIT", ""+t.getMessage());
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}