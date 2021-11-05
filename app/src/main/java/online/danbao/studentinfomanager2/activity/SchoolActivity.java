package online.danbao.studentinfomanager2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import online.danbao.studentinfomanager2.R;
import online.danbao.studentinfomanager2.databinding.ActivitySchoolBinding;

public class SchoolActivity extends AppCompatActivity{
    private ActivitySchoolBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySchoolBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SchoolActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}