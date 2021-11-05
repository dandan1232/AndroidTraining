package online.danbao.studentinfomanager2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import online.danbao.studentinfomanager2.R;

public class ExamTwoActivity extends AppCompatActivity {

    int score;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exam_two);



        Intent intent = getIntent();

        score = intent.getIntExtra("data",0);



        Button button4 = (Button) findViewById(R.id.button4);

        button4.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                Intent intent1 = new Intent(ExamTwoActivity.this,ExamOneActivity.class);

                startActivity(intent1);

            }

        });



        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == R.id.radioButton1)

                {

                    score = score + 20;

                }

                else

                {

                    score = score;

                }

            }

        });



        Button button5 = (Button) findViewById(R.id.button5);

        button5.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                int data = score;

                Intent intent2 = new Intent(ExamTwoActivity.this,ExamThreeActivity.class);

                intent2.putExtra("data",data);

                startActivity(intent2);

            }

        });

    }
}