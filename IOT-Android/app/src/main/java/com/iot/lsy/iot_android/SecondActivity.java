package com.iot.lsy.iot_android;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imgView;
    private EditText inputPhone;
    private TextInputLayout textInputLayout;

    private GestureDetector mGestureDetector = null;
    // 是否关闭手势操作
    private Boolean isNeedGestureDetector = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Second Activity");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.background_light));

        imgView = (ImageView) findViewById(R.id.icon_pre);
        inputPhone = (EditText) findViewById(R.id.phone_input);
        textInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout);
        inputPhone.addTextChangedListener(new MyTextWatcher(inputPhone));

        if(mGestureDetector == null) {
            mGestureDetector = new GestureDetector(getApplicationContext(),new BackGestureDetector(this));
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (isNeedGestureDetector) {
            return mGestureDetector.onTouchEvent(motionEvent) || super.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
       /* try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }*/
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.phone_input:
                    validatePhone();
            }
        }
    }

    private boolean validatePhone() {
        String phone = inputPhone.getText().toString().trim();

        if (!isValidatePhone(phone)) {
            textInputLayout.setError("输入的手机号码不正确!");
            requestFocus(inputPhone);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isValidatePhone(String phone) {
        return !TextUtils.isEmpty(phone) && Patterns.PHONE.matcher(phone).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        }
    }

    /*
    * 设置是否进行手势监听
    */
    public final void setNeedBackGesture(boolean mNeedBackGesture) {
        this.isNeedGestureDetector = mNeedBackGesture;
    }
}
