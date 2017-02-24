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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import dev.elementsculmyca.ec2017.Utility.Utils;
import dev.elementsculmyca.ec2017.Utility.Validation;

public class RegisterationActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ProgressDialog regProgressDialog;
    EditText phone, email, fname, college;
    TextView eventInput;
    Button registerButton;
    RequestQueue queue;
    private final String CHECK_TAG = "check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        queue = Volley.newRequestQueue(RegisterationActivity.this);
        Intent i = getIntent();
        final String eventId = i.getStringExtra("eventId");
        String eventName = i.getStringExtra("eventName");
        phone = (EditText) findViewById(R.id.phone_input);
        email = (EditText) findViewById(R.id.email_input);
        fname = (EditText) findViewById(R.id.fullname_input);
        college = (EditText) findViewById(R.id.college_input);
        registerButton = (Button) findViewById(R.id.register_btn);
        progressBar = (ProgressBar) findViewById(R.id.main_activity_progress_bar);
        eventInput = (TextView) findViewById(R.id.event_input);
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
                final String phoneNo = phone.getText().toString();
                if (phoneNo.length() == 10) {
                    if (Utils.isNetConnected(RegisterationActivity.this)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.VISIBLE);

                            }
                        });
                        checkDetails(phoneNo);

                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                    queue.cancelAll(CHECK_TAG);
                }


            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.isEmailAddress(email, true)) {
                    showError(email);
                }
                if (Utils.isNetConnected(RegisterationActivity.this)) {
                    regProgressDialog = new ProgressDialog(RegisterationActivity.this);
                    regProgressDialog.setMessage("Registering you for the Event!");
                    regProgressDialog.setTitle("");
                    regProgressDialog.setCancelable(false);
                    regProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    regProgressDialog.show();
                    register(phone.getText().toString(), email.getText().toString()
                            , fname.getText().toString()
                            , college.getText().toString(), eventId);

                } else {
                    Utils.makeAlert("", "Connect To Internet and try again", RegisterationActivity.this);
                }

                Toast.makeText(RegisterationActivity.this, "Calling", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void register(final String phone, final String email, final String fullname, final String college, final String eventId) {
        String ur = "https://elementsculmyca2017.herokuapp.com/api/v1/register";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, ur,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String s = jsonObject.getString(jsonObject.names().get(0).toString());
                            if (s.equals("Registration Successful")) {

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        regProgressDialog.cancel();
                                        final AlertDialog alertDialog = new AlertDialog.Builder(RegisterationActivity.this)
                                                .setTitle("Successful")
                                                .setMessage("You are registered for the Event!")
                                                .setCancelable(false)
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        finish();
                                                        overridePendingTransition(R.anim.left_to_right_slide, R.anim.left_to_right_slide);

                                                    }
                                                })
                                                .create();
                                        alertDialog.show();

                                    }
                                });

                            } else {
                                regProgressDialog.cancel();
                                Utils.toastS(RegisterationActivity.this, s);
                            }
                        } catch (JSONException e) {
                            regProgressDialog.cancel();
                        }

                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                regProgressDialog.cancel();
                Utils.makeAlert("", "Failed to Connect! Please Try Again", RegisterationActivity.this);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("phoneno", phone);
                params.put("email", email);
                params.put("fullname", fullname);
                params.put("college", college);
                params.put("eventid", eventId);
                return params;
            }
        };
        queue.add(stringRequest);

    }

    public void checkDetails(final String phoneno) {
        String ur = "https://elementsculmyca2017.herokuapp.com/api/v1/userinfo/" + phoneno;
        queue.cancelAll(CHECK_TAG);
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, ur,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Reg", response);
                        try {
                            final JSONObject jsonObject = new JSONObject(response);
                            String s = jsonObject.names().get(0).toString();
                            if (!s.equals("message")) {

                                try {
                                    progressBar.setVisibility(View.GONE);
                                    if (phone.getText().toString().equals(phoneno)) {
                                        email.setText(jsonObject.getString("email"));
                                        fname.setText(jsonObject.getString("fullname"));
                                        college.setText(jsonObject.getString("college"));
                                    }
                                } catch (JSONException e) {
                                    progressBar.setVisibility(View.GONE);
                                }
                            } else {
                                progressBar.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
            }
        });
        stringRequest.setTag(CHECK_TAG);
        queue.add(stringRequest);
    }

    public void showError(EditText view) {

        Animation shake = AnimationUtils.loadAnimation(RegisterationActivity.this, R.anim.shake);
        view.startAnimation(shake);
    }
}
