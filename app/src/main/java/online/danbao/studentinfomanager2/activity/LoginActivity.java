package online.danbao.studentinfomanager2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import online.danbao.studentinfomanager2.databinding.ActivityLoginBinding;
import online.danbao.studentinfomanager2.db.StudentDateBaseHelper;

public class LoginActivity extends AppCompatActivity {

    private StudentDateBaseHelper dbHelper;
    private SharedPreferences sp;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new StudentDateBaseHelper(this,"UserDB.db",null,6);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);


        binding.btnLogin.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String username_str = binding.loginUsername.getText().toString();
            String userpassword_str = binding.loginPassword.getText().toString();

            Cursor cursor = db.rawQuery("select * from User where name=?", new String[]{username_str});
            if (cursor.getCount() == 0) {
                Toast.makeText(LoginActivity.this, "用户名不存在", Toast.LENGTH_SHORT).show();
            } else {
                if (cursor.moveToFirst()) {
                    String userpassword_db = cursor.getString(cursor.getColumnIndex("password"));
                    if (userpassword_str.equals(userpassword_db)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
                cursor.close();
                db.close();
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
