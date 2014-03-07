package me.glfruit.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	private Button mTrueButton;

	private Button mFalseButton;

	private ImageButton mNextButton;

	private ImageButton mPrevButton;

	private TextView mQuestionTextView;

	private TrueFalse[] mQuestionBank = new TrueFalse[] {
			new TrueFalse(R.string.question_oceans, true),
			new TrueFalse(R.string.question_mideast, true),
			new TrueFalse(R.string.question_africa, false),
			new TrueFalse(R.string.question_americans, true),
			new TrueFalse(R.string.question_asia, true) };

	private int mCurrentIndex = 0;

	private static final String TAG = "QuizActivity";

	private static final String KEY_INDEX = "index";

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		Log.d(TAG, "onSaveInstanceState");
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart() called");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause() called");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume() called");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop() called");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy() called");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreated(Bundle) called");
		setContentView(R.layout.activity_quiz);

		if (savedInstanceState != null) {
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
		}
		mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
		updateQuestion();
		mQuestionTextView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				goToNextQuestion();
			}
		});

		mTrueButton = (Button) findViewById(R.id.true_button);
		mFalseButton = (Button) findViewById(R.id.false_button);
		mTrueButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				checkAnswer(true);
			}
		});

		mFalseButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				checkAnswer(false);
			}
		});

		mPrevButton = (ImageButton) findViewById(R.id.prev_button);
		mPrevButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mCurrentIndex == 0) {
					mCurrentIndex = mQuestionBank.length - 1;
				} else {
					mCurrentIndex--;
				}
				mQuestionTextView.setText(mQuestionBank[mCurrentIndex]
						.getQuestion());

			}
		});

		mNextButton = (ImageButton) findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				goToNextQuestion();
			}
		});
	}

	private void checkAnswer(boolean userPressedTrue) {
		boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

		int messageResId = 0;
		if (userPressedTrue == answerIsTrue) {
			messageResId = R.string.correct_toast;
		} else {
			messageResId = R.string.incorrect_toast;
		}
		Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
	}

	private void updateQuestion() {
		int question = mQuestionBank[mCurrentIndex].getQuestion();
		mQuestionTextView.setText(question);
	}

	private void goToNextQuestion() {
		mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
		updateQuestion();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quizi, menu);
		return true;
	}

}
