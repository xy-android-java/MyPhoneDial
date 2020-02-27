package com.xywei.android.java.myphonedialer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //出现的问题，没拿到电话号码，为什么？
        Button buttonCall = findViewById(R.id.buttonCallPhone);
        //不可以这样获取值
//       final String phoneNumber = phoneNumberText.getText().toString();

        //第一种方法
//        buttonCall.setOnClickListener(new MyOnClickListner(phoneNumberText));

        //第二种方法
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText phoneNumberText = findViewById(R.id.textPhoneNumber);
                Intent intent = new Intent();
                //这种写法要使用checkSelfPermission检查权限，而checkSelfPermission只在api23后
//                intent.setAction(Intent.ACTION_CALL);
                //这种是跳转到拨号面板
                //android 6之后兼容写法
                intent.setAction(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phoneNumberText.getText().toString());
                intent.setData(data);
                startActivity(intent);
            }
        });

    }

    class MyOnClickListner implements View.OnClickListener {

        private EditText phoneNumberText;

        public MyOnClickListner() {
        }

        public MyOnClickListner(EditText phoneNumberText) {
            this.phoneNumberText = phoneNumberText;
        }

        @Override
        public void onClick(View v) {
            //获取号码
            String phoneNO = phoneNumberText.getText().toString();

            if (TextUtils.isEmpty(phoneNO)) {
                Toast.makeText(MainActivity.this, "电话号码为空", Toast.LENGTH_LONG).show();
                System.out.println("电话号码空:" + phoneNO);
            } else {
                System.out.println("电话号码:" + phoneNO);
                //创建数据
                Uri data = Uri.parse("tel:" + phoneNO);
                //创建意图
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(data);
                //开始打电话
                startActivity(intent);
            }
        }
    }
}
