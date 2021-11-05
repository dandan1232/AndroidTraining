package online.danbao.studentinfomanager2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import online.danbao.studentinfomanager2.R;

public class ExamFourActivity extends AppCompatActivity {
    int score = 0;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_exam_four);



        final Intent intent = getIntent();

        score = intent.getIntExtra("data",0);



        TextView textView9 = (TextView)findViewById(R.id.textView9);

        textView9.setText(String.valueOf(score));



        Button button8 = (Button)findViewById(R.id.button8);

        button8.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                Intent intent1 = new Intent(ExamFourActivity.this,ExamThreeActivity.class);

                startActivity(intent1);

            }

        });



        Button btn_exit = (Button)findViewById(R.id.btn_exit);

        btn_exit.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                Intent intent = new Intent(ExamFourActivity.this, MainActivity.class);
                startActivity(intent);

            }

        });

    }
}