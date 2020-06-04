package com.example.fgt2;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private static int SPLASH_SCREEN = 5000;

    //Variables
    Animation topAnim, bottomAnim;
    ImageView profile_image;
    TextView slogan,slogan2,slogan3,num;

    SharedPreferences onBoardingScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);


        //Hooks
        num = findViewById(R.id.num);
        profile_image = findViewById(R.id.profile_image);
        slogan2 = findViewById(R.id.slogan2);
        slogan3 = findViewById(R.id.slogan3);
        slogan = findViewById(R.id.slogan);

        slogan.setAnimation(topAnim);
        slogan2.setAnimation(topAnim);
        slogan3.setAnimation(bottomAnim);
        num.setAnimation(bottomAnim);
        profile_image.setAnimation(topAnim);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);

                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

                if (isFirstTime){

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();

                    Intent intent = new Intent (MainActivity.this,OnBoarding.class);
                    startActivity(intent);
                    finish();

                }else{
                    Intent intent = new Intent (MainActivity.this,Dashboard.class);
                    startActivity(intent);
                    finish();
                }
            }
          },SPLASH_SCREEN);
        }
}

