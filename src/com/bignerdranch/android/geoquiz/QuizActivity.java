package com.bignerdranch.android.geoquiz;

import android.support.v7.app.ActionBarActivity;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends ActionBarActivity {

	// TAG UP
	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";

	/* Member Variables to represent widgets, currentIndex and boolean for cheat */
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private Button mCheatButton;
	private TextView mQuestionTextView;
	private int mCurrentIndex = 0;
	private boolean mIsCheater;

	/* Array for string questions */
	private TrueFalse[] mQuestionBank = new TrueFalse[] {
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_americas, true),
			new TrueFalse(R.string.question_asia, true),
			new TrueFalse(R.string.question_mideast, false)

	};

	/*
	 * Update a question with mCurrentIndex
	 * 
	 * @return the question
	 */
	private void updateQuestion() {
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}

	/*
	 * Check the answer of the question and implent toast messages
	 * 
	 * @param userPressedTrue
	 */
	private void checkAnswer(boolean userPressedTrue) {
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
		int messageResId = 0;

		if (mIsCheater) {
			messageResId = R.string.judgment_toast;
			Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
		} else {
			if (userPressedTrue == answerIsTrue) {
				messageResId = R.string.correct_toast;
			} else {
				messageResId = R.string.incorrect_toast;
			}
		}
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}

	/*
	 * OnCreate, inflate the view of activty_quiz layout
	 * 
	 * @param savedInstanceState - the lifecylce of the Instance state
	 */
	@TargetApi(11)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate(Bundle) called");
		setContentView(R.layout.activity_quiz);

		// Check the version code to display ActionBar
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			ActionBar actionBar = getActionBar();
			actionBar.setSubtitle("Bodies of Water");
		}

		/* mIsCheater = false; */

		// if statement to check if mCurrentindex exist
		if (savedInstanceState != null) {
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
		}

		// Wiring up the TextView
		mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

		// True Button
		mTrueButton = (Button) findViewById(R.id.true_button);
		// Setting Listeners
		mTrueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TOASTY MESSAGES
				checkAnswer(true);

			}
		});
		// False Button
		mFalseButton = (Button) findViewById(R.id.false_button);
		mFalseButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				checkAnswer(false);

			}
		});
		// Next Button
		mNextButton = (Button) findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
				updateQuestion();

			}
		});

		// Cheat Button
		mCheatButton = (Button) findViewById(R.id.cheat_button);
		mCheatButton.setOnClickListener(new View.OnClickListener() {

			/*
			 * Setting the onClick to CheatActivty,class Intent to check the
			 * current question and if the answer is true
			 * 
			 * @return updateQuestion
			 */
			@Override
			public void onClick(View v) {
				Intent i = new Intent(QuizActivity.this, CheatActivity.class);
				boolean answerIsTrue = mQuestionBank[mCurrentIndex]
						.isTrueQuestion();
				i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
				startActivityForResult(i, 0);

			}
		});

		updateQuestion();
	}

	/* Overriding onSaveInstanceState to save activity */
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSavedInstanceState");
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			return;
		}
		mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_IS_SHOWN,
				false);
	}

	/******************************* Activity Lifecycle *************************************/
	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "onStart() called");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume() called");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause() called");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStart() called");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onStart() called");
	}

	/*
	 * Creates the menu inflater option
	 * 
	 * @param menu
	 * 
	 * @return boolean true
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}

}
