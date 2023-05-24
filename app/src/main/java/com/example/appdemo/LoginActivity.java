package com.example.appdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private EditText phone;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RadioGroup loginMethod = findViewById(R.id.login_radioGroup);
        loginMethod.setOnCheckedChangeListener(this);

        phone = findViewById(R.id.phone_editText);
        password = findViewById(R.id.password_editText);
        phone.addTextChangedListener(new HideTextWatcher(phone, 11));
        password.addTextChangedListener(new HideTextWatcher(password, 6));

        Button forgetPassword = findViewById(R.id.forget_password_button);
        forgetPassword.setOnClickListener(this);
        Button login = findViewById(R.id.login_button);
        login.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.password_toggle:
                break;
            case R.id.verify_code_toggle:
                // 弹出提醒对话框，说明未完成
                Toast.makeText(this, "该功能还未完成", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch (view.getId()) {
            case R.id.forget_password_button:
                builder.setTitle("抱歉");
                builder.setMessage("还没有实现这个功能。");
                builder.setPositiveButton("好的", null);
                break;
            case R.id.login_button:
                if (phone.getText().toString().isEmpty()
                        || password.getText().toString().isEmpty())
                {
                    Toast.makeText(this, "请输入手机号码和密码", Toast.LENGTH_SHORT).show();
                }
                else {
                    builder.setTitle("登录成功！");
                    builder.setMessage("要离开登录页面吗？");
                    builder.setPositiveButton("离开", (dialogInterface, i) -> {
                        finish();
                    });
                    builder.setNegativeButton("再看看", null);
                }
                break;
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private class HideTextWatcher implements TextWatcher {

        private final EditText editText;
        private final int maxLength;

        public HideTextWatcher(EditText editText, int maxLength) {
            this.editText = editText;
            this.maxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == this.maxLength) {
                // 隐藏输入法软键盘
                // 从系统服务中获取输入法管理器
                InputMethodManager imm = (InputMethodManager) LoginActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                // 关闭屏幕上的输入法软键盘
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        }
    }
}