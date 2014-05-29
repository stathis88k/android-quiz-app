package cryodev.app.quizapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Results extends Activity {

	private static final String TAG = "Quiz Activity";

	private int score;
	private String finalScore;
	private int marginTop = 0;
	private int marginLeft = 0;
	private int textSize = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

	
		RelativeLayout layout = (RelativeLayout) View.inflate(
				getApplicationContext(), R.layout.result, null);
		RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		TextView scoreView = new TextView(this);

		Bundle extras = getIntent().getExtras();

		score = extras.getInt("SCORE");

		finalScore = score + "%";
		scoreView.setText(finalScore);
		scoreView.setTextSize(textSize);
		Typeface hindiFont = Typeface.createFromAsset(getAssets(),
				"fonts/arial.ttf");
		scoreView.setTypeface(hindiFont);

		scoreView.setId(0);
		llp.setMargins(marginLeft, marginTop, 0, 0);
		scoreView.setLayoutParams(llp);
		layout.addView(scoreView);

		setContentView(layout);

		Button retry = (Button) findViewById(R.id.buttonRetry);
		Button exit = (Button) findViewById(R.id.buttonExit);

		retry.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getBaseContext(), QuizActivity.class);
				startActivity(i);
				finish();
			}
		});

		exit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				exitAppDialog();
			}
		});

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
	    	exitAppDialog();
	    }

	    return super.onKeyDown(keyCode, event);
	}

	private void exitAppDialog() {

		Dialog exitApp = new Dialog(this);
		exitApp.setContentView(R.layout.dialog);
		exitApp.setTitle("Για μηνύσεις...");

		TextView textDialog = (TextView) exitApp
				.findViewById(R.id.dialogTextView);
		textDialog
				.setText("Exit?");
		Button exitBtn = (Button) exitApp.findViewById(R.id.dialogBtn);

		exitBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});

		exitApp.show();
	}

}
