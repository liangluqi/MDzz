package com.liaoinstan.demospring.test;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.liaoinstan.demospring.R;
import com.liaoinstan.springview.widget.SpringView;

public class TestActivity extends Activity implements RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {
    private SpringView springView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ((RadioGroup) findViewById(R.id.group_type)).setOnCheckedChangeListener(this);
        ((RadioGroup) findViewById(R.id.group_give)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.check_enableheader)).setOnCheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.check_enableFooter)).setOnCheckedChangeListener(this);


        springView = (SpringView) findViewById(R.id.my);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(TestActivity.this, "onRefresh", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                Toast.makeText(TestActivity.this, "onLoadmore", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.overlap:
                springView.setType(SpringView.Type.OVERLAP);
                break;
            case R.id.follow:
                springView.setType(SpringView.Type.FOLLOW);
                break;
            case R.id.both:
                springView.setGive(SpringView.Give.BOTH);
                break;
            case R.id.top:
                springView.setGive(SpringView.Give.TOP);
                break;
            case R.id.bottom:
                springView.setGive(SpringView.Give.BOTTOM);
                break;
            case R.id.none:
                springView.setGive(SpringView.Give.NONE);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.check_enableheader:
                springView.setEnableHeader(!isChecked);
                break;
            case R.id.check_enableFooter:
                springView.setEnableFooter(!isChecked);
                break;
        }
    }
}

