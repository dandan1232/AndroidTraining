package online.danbao.studentinfomanager2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import online.danbao.studentinfomanager2.R;
import online.danbao.studentinfomanager2.databinding.ActivityCallBinding;
import online.danbao.studentinfomanager2.databinding.ActivityExamOneBinding;

public class CallActivity extends AppCompatActivity {
    private ActivityCallBinding binding;
    Button btn_call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCallBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btn_call = (Button) findViewById(R.id.btn_call);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:18962494510"));
                startActivity(intent);
            }
        });
    }
}