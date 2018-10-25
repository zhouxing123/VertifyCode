package com.yihui.vertifycode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private VerifyCodeView mVerifyCodeView;
    private String Tag = MainActivity.this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVerifyCodeView = findViewById(R.id.verify_code_view);
        mVerifyCodeView.setInputCompleteListener(new VerifyCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                //正确点击下一步
                Toast.makeText(MainActivity.this,"输入完成",Toast.LENGTH_SHORT).show();
                Log.e(Tag,"输入完成");
            }

            @Override
            public void invalidContent() {
//                Toast.makeText(MainActivity.this,"重新输入",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
