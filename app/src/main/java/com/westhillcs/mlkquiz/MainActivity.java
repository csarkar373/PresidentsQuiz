package com.westhillcs.mlkquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int NUMOFQUESTIONS = 4;
     // questions used in this quiz
    private Question [] q;

    // current score
    private int score;
    // keeps track of where we are in the quiz
    private int index;

    // layout buttons
    private static ImageView pres_iv;
    private static TextView quest_tv1;
    private static Button submit_b;
    private static RadioButton selected_rb;
    private static RadioGroup cluster_rg;
    private static RadioButton a_rb;
    private static RadioButton b_rb;
    private static RadioButton c_rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialze();
    }

    private void initialze() {

        pres_iv = (ImageView)findViewById((R.id.president_iv));
        quest_tv1 = (TextView)findViewById(R.id.tv_quest);
        submit_b = (Button)findViewById(R.id.b_submit);

        cluster_rg = (RadioGroup)findViewById(R.id.rg_cluster);
        a_rb = (RadioButton)findViewById(R.id.rb_a);
        b_rb = (RadioButton)findViewById(R.id.rb_b);
        c_rb = (RadioButton)findViewById(R.id.rb_c);

        index = 0;
        score = 0;

        Question q0 = new Question(R.mipmap.ic_roosevelt, "Question 1?", "Choice 1A", "Choice 1B", "Choice 1C", "C" );
        Question q1 = new Question(R.mipmap.ic_nixon, "Question 2?", "Choice 2A", "Choice 2B", "Choice 2C", "B" );
        Question q2 = new Question(R.mipmap.ic_bomb, "Question 3?", "Choice 3A", "Choice 3B", "Choice 3C", "A" );
        Question q3 = new Question(R.mipmap.ic_carter, "Question 3?", "Choice 3A", "Choice 3B", "Choice 3C", "A" );
        // load questions for quiz
        this.q = new Question[NUMOFQUESTIONS];
        q[0] = q0;
        q[1] = q1;
        q[2] = q2;
        q[3] = q3;
        quest_tv1.setText( q[0].getQuestionBody());
        a_rb.setText( q[index].getChoiceA());
        b_rb.setText( q[index].getChoiceB());
        c_rb.setText( q[index].getChoiceC());
        pres_iv.setImageResource(q[0].getImage());
        onClickListenerButton();

    }

    private void onClickListenerButton() {

       submit_b.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       int selected_id = cluster_rg.getCheckedRadioButtonId();
                       selected_rb = (RadioButton)findViewById(selected_id);
                       String answer = "D";
                       if (selected_rb == a_rb) answer = "A";
                       if (selected_rb == b_rb) answer = "B";
                       if (selected_rb == c_rb) answer = "C";
                       if ( isCorrect(answer) ) {
                           // first time answering this question correctly?
                           if (!q[index].isCreditGiven())
                               setScore(getScore() + 1);
                           advance();
                       } else {
                           // show a message on the screen telling user wrong answer
                           Toast.makeText(MainActivity.this, "WRong ANswer", Toast.LENGTH_SHORT).show();
                       }
                   }
               }
       );
    }

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        this.score = score;
        // update the score display
    }

    public boolean isCorrect(String choice) {
        return choice.equals(q[index].getAnswer());
    }

    private void advance() {
        // user got the current question correct if this method is called
        q[index].giveCredit();
        // advance index to point to next question
        index = (index + 1) % q.length;

        //update the choices
        a_rb.setText( q[index].getChoiceA());
        b_rb.setText( q[index].getChoiceB());
        c_rb.setText( q[index].getChoiceC());
        //update the question body
        quest_tv1.setText( q[index].getQuestionBody());
        //update the image
        pres_iv.setImageResource(q[index].getImage());
        // end update the image
    }


}


