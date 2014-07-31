package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

	/* Setting up private member varibles to wire up in methods */
	private boolean mAnswerIsTrue;
	private TextView mAnswerTextView;
	private Button mShowAnswer;

	// TAG UP THE KEY
	public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_IS_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";

	/*
	 * Setting the result from the intent made in MainActivity
	 * 
	 * @param boolean to reveal answer
	 */
	private void setAnswerShownResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_IS_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}

	/*
	 * Override onCreate to inflate the activity_cheat layout
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);

		// Get the Intent
		mAnswerIsTrue = getIntent()
				.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		// Finding the view by the ID and using private varibles
		mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
		mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
		// Set up onClick Listener for this button
		mShowAnswer.setOnClickListener(new View.OnClickListener() {

			/*
			 * onClick to reveal result
			 * 
			 * @param veiw
			 */
			@Override
			public void onClick(View v) {
				if (mAnswerIsTrue) {
					mAnswerTextView.setText(R.id.true_button);
				} else {
					mAnswerTextView.setText(R.id.false_button);
				}
				setAnswerShownResult(true);
			}
		});

	}
}
