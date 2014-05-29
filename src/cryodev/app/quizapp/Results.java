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

		DisplayMetrics display = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(display);
		float d = getResources().getDisplayMetrics().density;
		int width = display.widthPixels;
		int height = display.heightPixels;

		DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
		float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

		int screenDensity = display.densityDpi;

		Log.e(TAG, "" + width + "x" + height + " " + dpHeight + "x" + dpWidth
				+ " " + screenDensity);

		RelativeLayout layout = (RelativeLayout) View.inflate(
				getApplicationContext(), R.layout.result, null);
		RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		TextView scoreView = new TextView(this);

		Bundle extras = getIntent().getExtras();

		score = extras.getInt("SCORE");

		if (score >= 80) {
			layout.setBackgroundResource(R.drawable.r8090);
			scoreView.setTextAppearance(getApplicationContext(),
					R.style.resultBlue);
		} else if (score >= 60) {
			layout.setBackgroundResource(R.drawable.r6079);
			scoreView.setTextAppearance(getApplicationContext(),
					R.style.resultBlue);
		} else if (score >= 40) {
			layout.setBackgroundResource(R.drawable.r4059);
			scoreView.setTextAppearance(getApplicationContext(),
					R.style.resultYellow);
		} else if (score >= 20) {
			layout.setBackgroundResource(R.drawable.r2039);
			scoreView.setTextAppearance(getApplicationContext(),
					R.style.resultYellow);
		}

		if (width == 240 & height == 320
				& (screenDensity > 110 & screenDensity < 130)) {
			marginTop = (int) (85 * d);
			marginLeft = (int) (190 * d);
			textSize = 50;
		} else if (width == 320 & height == 480
				& (screenDensity > 150 & screenDensity < 170)) {
			marginTop = (int) (100 * d);
			marginLeft = (int) (180 * d);
			textSize = 60;
		} else if (width == 240 & height == 400
				& (screenDensity > 110 & screenDensity < 130)) {
			marginTop = (int) (115 * d);
			marginLeft = (int) (180 * d);
			textSize = 60;
		} else if (width == 240 & height == 432
				& (screenDensity > 110 & screenDensity < 130)) {
			marginTop = (int) (130 * d);
			marginLeft = (int) (180 * d);
			textSize = 60;
		} else if (width == 480 & height == 854
				& (screenDensity > 150 & screenDensity < 170)) {
			marginTop = (int) (190 * d);
			marginLeft = (int) (270 * d);
			textSize = 100;
		} else if (width == 480 & height == 854
				& (screenDensity > 230 & screenDensity < 250)) {
			marginTop = (int) (120 * d);
			marginLeft = (int) (170 * d);
			textSize = 70;
		} else if (width == 480 & height == 800
				& (screenDensity > 150 & screenDensity < 170)) {
			marginTop = (int) (180 * d);
			marginLeft = (int) (270 * d);
			textSize = 90;
		} else if (width == 480 & height == 800
				& (screenDensity > 230 & screenDensity < 250)) {
			marginTop = (int) (120 * d);
			marginLeft = (int) (180 * d);
			textSize = 60;
		} else if (width == 720 & height == 1280
				& (screenDensity > 310 & screenDensity < 330)) {
			marginTop = (int) (130 * d);
			marginLeft = (int) (200 * d);
			textSize = 70;
		} else if (width == 1280 & height == 720
				& (screenDensity > 310 & screenDensity < 330)) {
			marginTop = (int) (145 * d);
			marginLeft = (int) (200 * d);
			textSize = 70;
		} else if (width == 1024 & height == 600
				& (screenDensity > 150 & screenDensity < 170)) {
			marginTop = (int) (220 * d);
			marginLeft = (int) (340 * d);
			textSize = 120;
		} else if (width == 1280 & height == 800
				& (screenDensity > 150 & screenDensity < 170)) {
			marginTop = (int) (450 * d);
			marginLeft = (int) (280 * d);
			textSize = 160;
		} else if (width == 800 & (height < 1380 & height > 1180)
				& (screenDensity > 203 & screenDensity < 223)) {
			marginTop = (int) (200 * d);
			marginLeft = (int) (320 * d);
			textSize = 130;
		} else if (width == 800 & (height < 1380 & height > 1180)
				& (screenDensity > 150 & screenDensity < 170)) {
			marginTop = (int) (280 * d);
			marginLeft = (int) (430 * d);
			textSize = 160;
		} else if (width == 2560 & height == 1600
				& (screenDensity > 310 & screenDensity < 330)) {
			marginTop = (int) (450 * d);
			marginLeft = (int) (280 * d);
			textSize = 160;
		} else if (width == 768 & height == 1280
				& (screenDensity > 310 & screenDensity < 330)) {
			marginTop = (int) (130 * d);
			marginLeft = (int) (220 * d);
			textSize = 120;
		}

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
				.setText("Αυτή εδώ είναι μία φωτογραφία-παγίδα. Όχι!!!! Ο τυπάς στα δεξιά δεν είχε την ιδέα,"
						+ " δεν την υλοποίησε και δεν σχεδίασε τις ερωτήσεις. Και επίσης, όχι!!!! ο τυπάς στα αριστερά"
						+ " δεν βόηθησε στον σχεδιασμό των ερωτήσεων. Επειδή όμως είναι καλα παιδία, έχοντας εσωτερικές"
						+ " πληροφορίες και παίζοντας τη ζωή τους κορώνα γράμματα δίνουν \"στεγνά\" τον πραγματικό"
						+ " θύτη. Τι; Τι εννοείς δεν φαίνεται; Ε εντάξει, η οθόνη είναι μικρή και κόβει την εικόνα."
						+ " Η καλή πρόθεση πάντως υπήρχε. Φιλάκια!!!!! ");
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