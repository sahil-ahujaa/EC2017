package dev.elementsculmyca.ec2017;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import dev.elementsculmyca.ec2017.Utility.Utils;

public class RegisterationActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ProgressDialog regProgressDialog;
    EditText phone,email,fname,college,eventInput;
    Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        Intent i=getIntent();
        final String eventId=i.getStringExtra("eventId");
        String eventName=i.getStringExtra("eventName");
        phone=(EditText)findViewById(R.id.phone_input);
        email=(EditText)findViewById(R.id.email_input);
        fname=(EditText)findViewById(R.id.fullname_input);
        college=(EditText)findViewById(R.id.college_input);
        registerButton=(Button)findViewById(R.id.register_btn);
        progressBar=(ProgressBar)findViewById(R.id.main_activity_progress_bar);
        eventInput=(EditText)findViewById(R.id.event_input);
        eventInput.setText(eventName);

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                final String phoneNo=phone.getText().toString();
                if(phoneNo.length()==10) {
                    Toast.makeText(RegisterationActivity.this, "10", Toast.LENGTH_LONG).show();
                    if (Utils.isNetConnected(RegisterationActivity.this)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.VISIBLE);

                            }
                        });
                        checkDetails(phoneNo);

                    }
                    else{
                        Utils.makeAlert("","Please Connect to internet and try again",RegisterationActivity.this);
                    }
                }


            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isNetConnected(RegisterationActivity.this)) {
                    regProgressDialog=new ProgressDialog(RegisterationActivity.this);
                    regProgressDialog.setMessage("Registering you for the Event!");
                    regProgressDialog.setTitle("");
                    regProgressDialog.setCancelable(false);
                    regProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    regProgressDialog.show();
                    makingcall(phone.getText().toString(), email.getText().toString()
                            , fname.getText().toString()
                            , college.getText().toString(), eventId);

                }else{
                    Utils.makeAlert("","Connect To Internet and try again",RegisterationActivity.this);
                }

                Toast.makeText(RegisterationActivity.this,"Calling",Toast.LENGTH_SHORT).show();
            }
        });



    }



    public void makingcall( String phone,  String email,String fullname,String college,String eventId){

        String ur="https://elementsculmyca2017.herokuapp.com/api/v1/register";
        RequestBody requestBody=new FormEncodingBuilder()
                .add("phoneno",phone)
                .add("email",email)
                .add("fullname",fullname)
                .add("college",college)
                .add("eventid",eventId)
                .build();
        Request request=new Request.Builder()
                .post(requestBody)
                .url(ur)
                .build();
        OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Utils.makeAlert("","Failed to Connect! Please Try Again",RegisterationActivity.this);
                    }
                });
                Log.d("Failed","fail");
            }
            @Override
            public void onResponse(Response response) throws IOException {
                //{"token":"a6ee242d725498e288854f589fa3391b41dfce6e"}
                final String res = response.body().string();
                Log.d("Returned",res);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject=new JSONObject(res);
                            String s=jsonObject.getString(jsonObject.names().get(0).toString());
                            if (s.equals("Registration Successful")){

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        regProgressDialog.cancel();
                                        final AlertDialog alertDialog=new AlertDialog.Builder(RegisterationActivity.this)
                                                .setTitle("Successful")
                                                .setMessage("You are registered for the Event!")
                                                .setCancelable(false)
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        finish();
                                                        overridePendingTransition(R.anim.left_to_right_slide,R.anim.left_to_right_slide);

                                                    }
                                                })
                                                .create();
                                        alertDialog.show();

                                    }
                                });

                            }else{
                                regProgressDialog.cancel();
                                Toast.makeText(RegisterationActivity.this, s, Toast.LENGTH_SHORT).show();
                            }
                            /*9643763712*/

                        }catch (JSONException e){

                        }

                    }
                });
            }
        });
    }
    public void checkDetails(final String phone){

        String ur="https://elementsculmyca2017.herokuapp.com/api/v1/userinfo/"+phone;
        Request request=new Request.Builder()
                .get()
                .url(ur)
                .build();
        OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
            @Override
            public void onResponse(Response response) throws IOException {
                //{"token":"a6ee242d725498e288854f589fa3391b41dfce6e"}
                final String res = response.body().string();
                try {
                    final JSONObject jsonObject = new JSONObject(res);
                    String s = jsonObject.names().get(0).toString();
                    if(!s.equals("message")){

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    progressBar.setVisibility(View.GONE);
                                    email.setText(jsonObject.getString("email"));
                                    fname.setText(jsonObject.getString("fullname"));
                                    college.setText(jsonObject.getString("college"));



                                }
                                catch (JSONException e){

                                }
                            }
                        });
                    }
                    else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }
                }catch (JSONException e){

                }
                Log.d("Json:",res)
                ;
            }
        });
    }
}
