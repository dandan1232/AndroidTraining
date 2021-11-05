package online.danbao.studentinfomanager2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import online.danbao.studentinfomanager2.R;
import online.danbao.studentinfomanager2.databinding.ActivityExamOneBinding;

public class ExamOneActivity extends AppCompatActivity {
    private ActivityExamOneBinding binding;
    int score = 0;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityExamOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        final EditText editText3 = (EditText) findViewById(R.id.editText3);

        final EditText editText4 = (EditText) findViewById(R.id.editText4);



        Button button3 = (Button) findViewById(R.id.button3);
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                if (editText3.getText().toString().equals("敬业"))

                    score = score + 10;

                if (editText4.getText().toString().equals("乐群"))

                    score = score + 10;



                int data = score;

                Intent intent = new Intent(ExamOneActivity.this, ExamTwoActivity.class);

                intent.putExtra("data", data);

                startActivity(intent);

            }

        });

    }

}