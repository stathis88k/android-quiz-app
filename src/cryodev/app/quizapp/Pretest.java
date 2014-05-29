package cryodev.app.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Pretest extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.pretest);

		Button goToTest = (Button) findViewById(R.id.doTheTestBtn);

		goToTest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getBaseContext(), QuizActivity.class);

				startActivity(i);
				// Remove activity
				finish();

			}
		});

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();

	}
}