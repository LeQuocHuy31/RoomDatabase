package com.example.roomdatabaseandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabaseandroid.Database.UserDatabase;

public class UpdateActivity extends AppCompatActivity {
    private EditText edtUserName;
    private EditText edtAddress;
    private EditText edtYear;
    private Button btnUpdateUser;
    private user mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edtUserName=findViewById(R.id.edt_username);
        edtAddress=findViewById(R.id.edt_address);
        edtYear=findViewById(R.id.edt_year);
        btnUpdateUser=findViewById(R.id.btn_update_user);
        Bundle bundle = getIntent().getExtras();
        mUser= (user) bundle.get("object_user");

        if(mUser!=null){
            edtUserName.setText(mUser.getUsername());
            edtAddress.setText(mUser.getAddress());
            edtYear.setText(mUser.getYear());
            btnUpdateUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateUser();
                }
            });
        }
    }

    private void updateUser() {
        String strUsername=edtUserName.getText().toString().trim();
        String strAddress =edtAddress.getText().toString().trim();
        String strYear=edtYear.getText().toString().trim();
        if(TextUtils.isEmpty(strUsername)|| TextUtils.isEmpty(strAddress)|| TextUtils.isEmpty(strYear)){
            return;
        }
       //Update user
        mUser.setUsername(strUsername);
        mUser.setAddress(strAddress);
        mUser.setYear(strYear);
        UserDatabase.getInstance(this).UserDAO().updateUser(mUser);
        Toast.makeText(this,"Update user successfull",Toast.LENGTH_SHORT).show();
        Intent intentResult= new Intent();
        setResult(Activity.RESULT_OK,intentResult);
        finish();
    }

}