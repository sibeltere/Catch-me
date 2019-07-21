package com.sibeltere.kenny;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    TextView scoreText;


    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;


    ImageView images[];

    int score;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText=findViewById(R.id.textTime);
        scoreText=findViewById(R.id.textScore);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        images=new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        score=0;

        hideImages();



        new CountDownTimer(10000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

                timeText.setText("Time: "+ millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

                timeText.setText("Time off");
                handler.removeCallbacks(runnable);

                for (ImageView image:images){

                    image.setVisibility(View.INVISIBLE);//görünmez yapar
                }

                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //restart

                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this,"Game Over",Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();

            }
        }.start();


    }


    public void Start(View view){

        score++;
        scoreText.setText("Score: "+score);

    }


    public void hideImages(){



         handler=new Handler();

        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image:images){

                    image.setVisibility(View.INVISIBLE);//görünmez yapar
                }

                Random random =new Random();
                int i=random.nextInt(9);//O ile 8 arasında rastgele sayı verir.
                images[i].setVisibility(View.VISIBLE);//görünür yapar

                handler.postDelayed(this,600);
            }


        };

          handler.post(runnable);


    }
}
