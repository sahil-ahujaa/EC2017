package dev.elementsculmyca.ec2017;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import dev.elementsculmyca.ec2017.Adapters.MyTicketsAdapter;
import dev.elementsculmyca.ec2017.DatabaseHandlers.DbHelper;
import dev.elementsculmyca.ec2017.Utility.TicketDetails;
import dev.elementsculmyca.ec2017.Utility.Utils;

public class MyTicketsActivity extends AppCompatActivity {
    private static final String TAG="Tickets";
    final DbHelper dbHelper=new DbHelper(MyTicketsActivity.this);
    Button showBtn;
    EditText phnInput;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    TextView ticketNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tickets);
        recyclerView=(RecyclerView)findViewById(R.id.my_tickets_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(MyTicketsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        progressDialog=new ProgressDialog(MyTicketsActivity.this);
        progressDialog.setMessage("Retrieving");
        progressDialog.setCancelable(false);

        ticketNum=(TextView)findViewById(R.id.ticket_num);
        phnInput=(EditText)findViewById(R.id.ticket_phn_input);
        showBtn=(Button)findViewById(R.id.ticket_phn_btn);
        phnInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){

                }
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phn=phnInput.getText().toString();
                ticketNum.setVisibility(View.GONE);
                if (phn.length()==10) {

                    View view=MyTicketsActivity.this.getCurrentFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    progressDialog.show();
                    if (Utils.isNetConnected(MyTicketsActivity.this)) {

                        showList(phn);
                    }
                    else {
                        Utils.makeAlert("","Please connect to Internet!",MyTicketsActivity.this);
                    }
                }
                else {
                    Utils.toastS(MyTicketsActivity.this,"Enter phone number");
                }

            }

            });

    }
    public void showList(String phn){
        String url="https://elementsculmyca2017.herokuapp.com/api/v1/registrationdetails/"+phn;
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d(TAG,"Failed");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.cancel();
                        Utils.makeAlert("","Could not connect to servers!",MyTicketsActivity.this);
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String res=response.body().string();
                Log.d("Recievd",res);
                try{
                    JSONArray jsonArray=new JSONArray(res);
                    JSONObject jsonObject;
                    final int i=jsonArray.length();
                    if (i>0) {

                        final ArrayList<TicketDetails> ticketList = new ArrayList<TicketDetails>();
                        for (int j=0;j<i;j++){
                            jsonObject=jsonArray.getJSONObject(j);
                            String event=dbHelper.getEventNameById(jsonObject.getString("eventid"));
                            String qrcode=jsonObject.getString("qrcode");
                            ticketList.add(new TicketDetails(qrcode,event));
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ticketNum.setText("Hurray you have "+i+" tickets!");
                                ticketNum.setVisibility(View.VISIBLE);
                                progressDialog.cancel();
                                recyclerView.setAdapter(new MyTicketsAdapter(ticketList,MyTicketsActivity.this));
                            }
                        });

                    }
                    else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.cancel();
                                Utils.makeAlert("","You are not registered in any event!",MyTicketsActivity.this);
                            }
                        });

                    }

                }catch (JSONException e){
                    Log.d(TAG,"JSON error");
                }

            }
        });


    }
}
