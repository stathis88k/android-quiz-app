package cryodev.app.quizapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class QuizActivity extends Activity {

	private static final String TAG = "Quiz Activity";

	private List<Question> questions;
	private List<String> answers = new ArrayList<String>();
	private String correctAnswer, optionA, optionB, optionC;
	private Question currentQ;
	private int score;
	private int qNumber = 1;
	private TextView questionNumber;
	private TextView question;
	private Button button1, button2, button3, button4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DataBaseHelper myDbHelper = new DataBaseHelper(getApplicationContext());
		myDbHelper = new DataBaseHelper(this);

		try {

			myDbHelper.createDataBase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}

		try {

			questions = myDbHelper.getAllQuestions();

			Log.e(TAG, "Database opened");

		} catch (SQLException sqle) {

			throw sqle;

		}
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.fragment_quiz);

		questionNumber = (TextView) findViewById(R.id.questionNumberTextView);
		question = (TextView) findViewById(R.id.questionTextView);
		button1 = (Button) findViewById(R.id.answer1button);
		button2 = (Button) findViewById(R.id.answer2button);
		button3 = (Button) findViewById(R.id.answer3button);
		button4 = (Button) findViewById(R.id.answer4button);

		// set the listener to the buttons

		button1.setOnClickListener(answerButtonListener);
		button2.setOnClickListener(answerButtonListener);
		button3.setOnClickListener(answerButtonListener);
		button4.setOnClickListener(answerButtonListener);
		
		resetQuiz();

	}


	public void resetQuiz() {

		// reset the score
		score = 0;

		// shuffle the question list
		Collections.shuffle(questions);

		// load the 1st question
		loadNextQuestion();

	}

	private void loadNextQuestion() {
		
		Log.e(TAG,"load next question original");
		if (questions.size() > 4)
			currentQ = questions.remove(0);
		else {
			results();
			return;
		}

		// set the text to the question Number Section
		questionNumber.setText(getResources().getString(R.string.question,
				qNumber++));

		question.setText(currentQ.getQUESTION());

		// assign the results to values. These values will be used after for
		// result check
		correctAnswer = currentQ.getCorrectAnswer();
		optionA = currentQ.getOPTA();
		optionB = currentQ.getOPTB();
		optionC = currentQ.getOPTC();

		// make sure the list is empty
		answers.clear();

		// add the answers to the list
		answers.add(correctAnswer);
		answers.add(optionA);
		answers.add(optionB);
		answers.add(optionC);

		// shuffle the answers
		Collections.shuffle(answers);

		// assign the to buttons
		button1.setText(answers.remove(0));
		button2.setText(answers.remove(0));
		button3.setText(answers.remove(0));
		button4.setText(answers.remove(0));

	}

	private void results() {

		Intent results = new Intent(getBaseContext(), MainSplashScreen.class);
		results.putExtra("SCORE", score);
		results.putExtra("RESULTS", true);
		startActivity(results);
		finish();

	}

	private OnClickListener answerButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Button answerButton = ((Button) v);
			String answer = answerButton.getText().toString();

			updateScore(answer);
			
			loadNextQuestion();

		}
	};

	private void updateScore(String answer) {
		if (answer == correctAnswer)
			score += 9;
		else if (answer == optionA)
			score += 6;
		else if (answer == optionB)
			score += 4;
		else if (answer == optionC)
			score += 2;

		Log.e(TAG, "" + score);

	}

}
