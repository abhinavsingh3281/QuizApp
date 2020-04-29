package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTxtQuestion;


    private final String SCORE_KEY="SCORE";
    private final String INDEX="INDEX";
    private Button btnTrue;
    private Button btnWrong;
    private int mQuestionIndex;
    private int mQuizQuestion;
    private ProgressBar mProgressBar;
    private TextView mQuizStatsTextView;
    private int mUserScore;

    private QuizModel[] questionCollection=new QuizModel[]{

           new QuizModel(R.string.q1,true),
           new QuizModel(R.string.q2,false),
           new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,false),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,false),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,false),

    };
    final int USER_PROGRESS=(int)Math.ceil(100.0/questionCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState!=null){
            mUserScore=savedInstanceState.getInt(SCORE_KEY);
            mQuestionIndex=savedInstanceState.getInt(INDEX);
            mQuizStatsTextView=findViewById(R.id.txtQuizStats);
            mQuizStatsTextView.setText(mUserScore+"");
        }
        else{
            mUserScore=0;
            mQuestionIndex=0;
        }

        //first life cycle method
        Toast.makeText(getApplicationContext(), "On create method is called", Toast.LENGTH_SHORT).show();




        mTxtQuestion=findViewById(R.id.txtQuestion);
        mProgressBar=findViewById(R.id.quizPB);
        mQuizStatsTextView=findViewById(R.id.txtQuizStats);




        QuizModel q1=questionCollection[mQuestionIndex];
        mQuizQuestion=q1.getQuestion();
       mTxtQuestion.setText(mQuizQuestion);

        btnTrue=findViewById(R.id.btnTrue);



       btnTrue.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
             //  Toast.makeText(getApplicationContext(),"Btn true is tapped",Toast.LENGTH_SHORT).show();
               evaluateUsersAnswer(true);
               changeQuestionOnButtonClick();

           }
       });
       btnWrong=findViewById(R.id.btnWrong);


       //btnWrong.setOnClickListener(myClickListner);

        //declaration of anonyms on click listner

        btnWrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //created on the spopt we don't need onclick listner anywhere

              //      Log.i("MyApp","btnWrong is tapped now");
               //Toast.makeText(getApplicationContext(),"Btn wrong is tapped",Toast.LENGTH_SHORT).show();
                evaluateUsersAnswer(false);
             changeQuestionOnButtonClick();



            }
        });
    }
    private void changeQuestionOnButtonClick(){



        // 5%10=5   1%4=1
        mQuestionIndex=(mQuestionIndex+1)%10;//0=0+1
        if (mQuestionIndex == 0){
            AlertDialog.Builder quizAlert=new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);//this won't allow user to cancel the alert box

            quizAlert.setTitle("The Quiz is Finished");
            quizAlert.setMessage("Your score is "+mUserScore);
            quizAlert.setPositiveButton("Finish the quiz", new DialogInterface.OnClickListener() {

                //
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();



                }
            });
            quizAlert.show();
                    // this means the instance of current main activity

                    //context is the current state of application
        }


        //0=1%10=1
        //1=2%10=2
        //9=9+1=10--10%10=0

        mQuizQuestion=questionCollection[mQuestionIndex].getQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuizStatsTextView.setText(mUserScore+"");

        //1
    }


    private void evaluateUsersAnswer(boolean userGuess) {
        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].getAnswer();

        if (currentQuestionAnswer==userGuess){
            Toast.makeText(getApplicationContext(),R.string.correct_toast_message,Toast.LENGTH_SHORT).show();
        mUserScore=mUserScore+1;
        }
        else
        {
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast_message,Toast.LENGTH_SHORT).show();
        }

    }


    //Life cycle method
    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(getApplicationContext(),"On start method is called",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),"On resume method is called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //when application is somehow visible and we can't access the elements
        Toast.makeText(getApplicationContext(),"On Pause method is called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //when the application is not visible at all
        Toast.makeText(getApplicationContext(),"On Stop method is called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"On Destroy method is called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    outState.putInt(SCORE_KEY,mUserScore);
    outState.putInt(INDEX,mQuestionIndex);
    }

}

//when the application starts first on create is runned then on start method and at last on resume is runned
// when the application is closed then the on pause method is runned and when we resume our app on create doesn't run
//only onstart and onresume are runned
//when the application is not visible at all then on stop is called


