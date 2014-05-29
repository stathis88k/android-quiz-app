package cryodev.app.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
 
public class MainSplashScreen extends Activity {
 
	private static final String TAG = "Quiz Activity";
	private static boolean result = false;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		 final Bundle extras = getIntent().getExtras();
		 LinearLayout layout = new LinearLayout(this);
		 
		 if(extras!=null)
			 result=extras.getBoolean("RESULTS");
		 
		 if(result)
			 layout.setBackgroundResource(R.drawable.preresult);
		 else
			 layout.setBackgroundResource(R.drawable.ikatbg);
        
		 setContentView(layout);
         
// METHOD 1    
         
         /****** Create Thread that will sleep for 5 seconds *************/        
        Thread background = new Thread() {
            public void run() {
                 
                try {
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);
                    
                    Intent i;
                 // After 5 seconds redirect to another intent
                    if(result){
                    	int score = extras.getInt("SCORE");
                        i=new Intent(getBaseContext(),Results.class); 
                        i.putExtra("SCORE", score);
                        result=false;
                    }
                    else
                    {
                        i=new Intent(getBaseContext(),Pretest.class);
                    }
                    
                    startActivity(i);
                    //Remove activity
                    finish();
                     
                } catch (Exception e) {
                	Log.e(TAG,e.toString());
                 
                }
            }
        };
         
        // start thread
        background.start();
         
//METHOD 2 
         
        /*
        new Handler().postDelayed(new Runnable() {
              
            // Using handler with postDelayed called runnable run method
  
            @Override
            public void run() {
                Intent i = new Intent(MainSplashScreen.this, FirstScreen.class);
                startActivity(i);
  
                // close this activity
                finish();
            }
        }, 5*1000); // wait for 5 seconds
        */
    }
     
    @Override
    protected void onDestroy() {
         
        super.onDestroy();
  
    }
}