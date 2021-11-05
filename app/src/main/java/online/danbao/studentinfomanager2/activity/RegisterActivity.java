package online.danbao.studentinfomanager2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import online.danbao.studentinfomanager2.R;
import online.danbao.studentinfomanager2.databinding.ActivityRegisterBinding;
import online.danbao.studentinfomanager2.db.StudentDateBaseHelper;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private StudentDateBaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new StudentDateBaseHelper(this,"UserDB.db",null,6);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                String username_str = binding.registerUsername.getText().toString();
                String userpassword_str = binding.registerPassword.getText().toString();
                String repassword_str = binding.registerRepassword.getText().toString();

                if (username_str.equals("") || userpassword_str.equals("") || repassword_str.equals("")) {
                    Toast.makeText(RegisterActivity.this, "必填信息不可以为空！", Toast.LENGTH_SHORT).show();
                }else if (userpassword_str.equals(repassword_str)) {
                    ContentValues values = new ContentValues();
                    //组装数据
                    values.put("name", username_str);
                    values.put("password", userpassword_str);

                    db.insert("User", null, values);

                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "两次密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                }
                db.close();

            }
        });
    }
}


