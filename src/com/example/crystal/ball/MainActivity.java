package com.example.crystal.ball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private CrystalBall mCrystalBall = new CrystalBall();
	private TextView mAnswerLabel;
	private Button mGetAnswerButton;
	private ImageView mcbImage;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Assign views from layout file
        mcbImage = (ImageView) findViewById(R.id.imageView1);
        mAnswerLabel = (TextView) findViewById(R.id.textView1);
        mGetAnswerButton = (Button) findViewById(R.id.button1);
        
        mGetAnswerButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				String answer = mCrystalBall.getAnAnswer();
				
				// Update the label with our dynamic answer
				mAnswerLabel.setText(answer);
				animateCrystalBall();
				animateAnswer();
				playSound();
			}
		});
        
        
//        String toastMessage = "yay!";
        //static method to initialize toast
        //Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
//        Toast welcomeToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
//        welcomeToast.setGravity(Gravity.TOP, 0, 0);
//        welcomeToast.show();
    }
    
    private void animateCrystalBall(){
    	
    	mcbImage.setImageResource(R.drawable.ball_animation);
    	AnimationDrawable bAnimation = (AnimationDrawable) mcbImage.getDrawable();
    	if (bAnimation.isRunning()){
    		bAnimation.stop();
    	}
    	bAnimation.start();
    }
    
    private void animateAnswer(){
    	AlphaAnimation fadeInAnimation = new AlphaAnimation(0,1);
    	fadeInAnimation.setDuration(1500);//1.5s
    	fadeInAnimation.setFillAfter(true);//persist after animation
    	
    	mAnswerLabel.setAnimation(fadeInAnimation);
    }
    
    private void playSound(){
    	MediaPlayer player = MediaPlayer.create(this, R.raw.crystal_ball);
    	player.setOnCompletionListener(new OnCompletionListener() {
			
			public void onCompletion(MediaPlayer mp) {
				mp.release();
				
			}
		});
    	player.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
