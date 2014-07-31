package com.bignerdranch.android.geoquiz;

public class TrueFalse {

	/*Set up member variables for the question and the true question*/
	private int mQuestion;
	private boolean mTrueQuestion;

	public TrueFalse(int question, boolean trueQuestion) {
		setQuestion(question);
		setTrueQuestion(trueQuestion);
	}
	/*Getters and Setters for each memeber variable*/
	public int getQuestion() {
		return mQuestion;
	}

	public void setQuestion(int question) {
		mQuestion = question;
	}

	public boolean isTrueQuestion() {
		return mTrueQuestion;
	}

	public void setTrueQuestion(boolean trueQuestion) {
		mTrueQuestion = trueQuestion;
	}

}
