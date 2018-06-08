package com.ymsun.easyannotion;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ymsun.annotion.BindId;
import com.ymsun.annotion.OnClick;
import com.ymsun.bindapi.BindApi;
@BindId(R.layout.activity_main)
public class MainActivity extends Activity {

    @BindId(R.id.tv)
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BindApi.init(this);
        tv.setText("测试");

    }

    @OnClick(R.id.tv)
    public void setOnClick(View v){
        Toast.makeText(this,"aassdadas",Toast.LENGTH_SHORT).show();
    }
}
