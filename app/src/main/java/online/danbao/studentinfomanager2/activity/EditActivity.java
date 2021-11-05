package online.danbao.studentinfomanager2.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import online.danbao.studentinfomanager2.R;
import online.danbao.studentinfomanager2.db.StudentDateBaseHelper;

public class EditActivity extends Activity implements View.OnClickListener {
    //控件
    private Button btnBack;
    private Button btnSure;
    private EditText etNumber;
    private EditText etName;
    private EditText etNativePlace;
    private EditText etSpecialty;
    private EditText etPhone;
    private TextView tvGender;
    private TableRow trGender;
    private TextView tvBirth;
    private TableRow trBirth;
    private TextView tvDelete;
    //数据存储
    private StudentDateBaseHelper mStudentDateBaseHelper;
    private SQLiteDatabase mSQLiteDatabase;
    //变量与常量
    public static final int TYPE_ADD = 111;
    public static final int TYPE_EDIT = 222;
    private int currentType;
    private String initNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mStudentDateBaseHelper = new StudentDateBaseHelper(this, "StudentInfo.db", null, 6);
        mSQLiteDatabase = mStudentDateBaseHelper.getReadableDatabase();

        receiveType();
        initView();
        receiveInfo();
    }

    private void receiveType() {
        Intent intent = this.getIntent();
        currentType = intent.getIntExtra("type", TYPE_ADD);
    }

    private void receiveInfo() {

        if (currentType == TYPE_EDIT) {
            Intent intent = this.getIntent();
            initNumber = intent.getStringExtra("number");
            String name;
            String gender;
            String birth;
            String nativePlace;
            String specialty;
            String phone;

            Cursor mCursor = mSQLiteDatabase.query("student", null, "number=?", new String[]{initNumber}, null, null, null);

            if (mCursor.moveToNext()) {
                name = mCursor.getString(mCursor.getColumnIndex("name"));
                gender = mCursor.getString(mCursor.getColumnIndex("gender"));
                birth = mCursor.getString(mCursor.getColumnIndex("birth"));
                nativePlace = mCursor.getString(mCursor.getColumnIndex("native_place"));
                specialty = mCursor.getString(mCursor.getColumnIndex("specialty"));
                phone = mCursor.getString(mCursor.getColumnIndex("phone"));

                etNumber.setText(initNumber);
                etName.setText(name);
                tvGender.setText(gender);
                tvBirth.setText(birth);
                etNativePlace.setText(nativePlace);
                etSpecialty.setText(specialty);
                etPhone.setText(phone);
            }
        }
    }


    private void initView() {
        btnBack = (Button) findViewById(R.id.btn_edit_back);
        btnSure = (Button) findViewById(R.id.btn_edit_sure);
        etNumber = (EditText) findViewById(R.id.et_edit_number);
        etName = (EditText) findViewById(R.id.et_edit_name);
        etNativePlace = (EditText) findViewById(R.id.et_edit_native_place);
        etSpecialty = (EditText) findViewById(R.id.et_edit_specialty);
        etPhone = (EditText) findViewById(R.id.et_edit_phone);
        tvGender = (TextView) findViewById(R.id.tv_edit_gender);
        trGender = (TableRow) findViewById(R.id.tr_edit_gender);
        tvBirth = (TextView) findViewById(R.id.tv_edit_birth);
        trBirth = (TableRow) findViewById(R.id.tr_edit_birth);
        tvDelete = (TextView) findViewById(R.id.tv_edit_delete);

        btnBack.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        trGender.setOnClickListener(this);
        trBirth.setOnClickListener(this);
        tvDelete.setOnClickListener(this);

        switch (currentType) {
            case TYPE_ADD:
                //如果是增添信息模式，就让删除按钮不可见
                tvDelete.setVisibility(View.GONE);
                break;
            case TYPE_EDIT:
                tvDelete.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit_back:
                finish();
                break;
            case R.id.btn_edit_sure:
                btnSureAction();
                break;
            case R.id.tr_edit_gender:
                trGenderAction();
                break;
            case R.id.tr_edit_birth:
                trBirthAction();
                break;
            case R.id.tv_edit_delete:
                tvDeleteAction();
                break;
        }
    }

    private void trGenderAction() {
        final String[] arrayGender = new String[]{"男", "女"};
        new AlertDialog.Builder(EditActivity.this)
                .setTitle("修改性别")
                .setItems(arrayGender, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvGender.setText(arrayGender[which]);
                    }
                })
                .create()
                .show();
    }

    private void tvDeleteAction() {
        new AlertDialog.Builder(EditActivity.this)
                .setTitle("删除")
                .setMessage("确认删除此学生信息？\n学号：" + initNumber)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSQLiteDatabase.delete("student", "number=?", new String[]{initNumber});
                        finish();
                        Toast.makeText(EditActivity.this, "该学生信息删除成功！", Toast.LENGTH_SHORT).show();
                    }
                })
                //由于“取消”的button我们没有设置点击效果，直接设为null就可以了
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    private void trBirthAction() {
        final DatePicker dpBirth = (DatePicker) getLayoutInflater().inflate(R.layout.dialog_edit_birth, null);

        new AlertDialog.Builder(EditActivity.this)
                .setTitle("修改出生日期")
                .setView(dpBirth)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //将Activity中的textview显示AlertDialog中EditText中的内容
                        //并且用Toast显示一下
                        tvBirth.setText(dpBirth.getYear() + "年" + dpBirth.getMonth() + "月" + dpBirth.getDayOfMonth() + "日");
                    }
                })
                //由于“取消”的button我们没有设置点击效果，直接设为null就可以了
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    private void btnSureAction() {
        String number = etNumber.getText().toString();
        String name = etName.getText().toString();
        String gender = tvGender.getText().toString();
        String nativePlace = etNativePlace.getText().toString();
        String specialty = etSpecialty.getText().toString();
        String phone = etPhone.getText().toString();
        String birth = tvBirth.getText().toString();

        if (notNull(number, name, gender, nativePlace, specialty, phone, birth)) {
            if (notSameNumber(number)) {
                ContentValues values = new ContentValues();
                values.put("number", number);
                values.put("name", name);
                values.put("gender", gender);
                values.put("native_place", nativePlace);
                values.put("specialty", specialty);
                values.put("phone", phone);
                values.put("birth", birth);

                switch (currentType) {
                    case TYPE_ADD:
                        mSQLiteDatabase.insert("student", null, values);
                        Toast.makeText(EditActivity.this, "添加数据成功", Toast.LENGTH_SHORT).show();
                        break;
                    case TYPE_EDIT:
                        mSQLiteDatabase.update("student", values, "number=?", new String[]{initNumber});
                        Toast.makeText(EditActivity.this, "数据修改成功", Toast.LENGTH_SHORT).show();
                        break;
                }
                finish();
            } else {
                Toast.makeText(EditActivity.this, "该学号的学生已经存在！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(EditActivity.this, "数据不可以为空！", Toast.LENGTH_SHORT).show();
        }
    }
    
    private boolean notNull(String number, String name, String gender, String nativePlace,
                            String specialty, String phone, String birth) {
        if (number.equals(""))
            return false;
        if (name.equals(""))
            return false;
        if (gender.equals(""))
            return false;
        if (nativePlace.equals(""))
            return false;
        if (specialty.equals(""))
            return false;
        if (phone.equals(""))
            return false;
        if (birth.equals(""))
            return false;
        return true;
    }

    private boolean notSameNumber(String number) {

        Cursor cursor = mSQLiteDatabase.query("student", null, "number=?", new String[]{number}, null, null, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

}
