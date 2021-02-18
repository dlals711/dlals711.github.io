package org.techtown.push.send;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    static RequestQueue requestQueue;
    static String regId = "fBU54Voy4Lw:APA91bExKp3lOGS8LV3j4XnxNgX8Mxg4w5mwrS2Dn_HTQ-XG5P3ApSC_1RFutAUoThiTEzaY3CTfuy8WSRrRPYb7zsn7F102MWzB0GwC4K84q-pLOsVF5yPB6UEW-YmZuoICoKwnKO7m";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                send(input);
            }
        });

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

    }

    public void send(String input) {
        JSONObject requestData = new JSONObject();

        try {
            requestData.put("priority", "high");

            JSONObject dataObj = new JSONObject();

            dataObj.put("contents", input);
            requestData.put("data", dataObj);
            JSONArray idArray = new JSONArray();

            idArray.put(0, regId);
            requestData.put("registration_ids", idArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendData(requestData, new SendResponseListener() {

            @Override
            public void onRequestCompleted() {
                println("onRequestCompleted() 호출됨.");
            }

            @Override
            public void onRequestStarted() {
                println("onRequestStarted() 호출됨.");
            }

            @Override
            public void onRequestWithError(VolleyError error) {
                println("onRequestWithError() 호출됨.");
            }
        });
    }


    public interface SendResponseListener {
        public void onRequestStarted();
        public void onRequestCompleted();
        public void onRequestWithError(VolleyError error);
    }

    public void sendData(JSONObject requestData, final SendResponseListener listener) {
        JsonObjectRequest request = new JsonObjectRequest(

                Request.Method.POST, "https://fcm.googleapis.com/fcm/send", requestData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onRequestCompleted();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onRequestWithError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "key=AAAAZptL47o:APA91bFu74bmD6_9k3d4c4-SVlMvXeIhyXlhON5RHhdyiG-2amWQHTtdDv4z-dEItmHpvKNuxQZcArBXAWmDE0AvltMkRKdx86OjHs-BWVINcX0BkavSt0N99hVFt7-G5ft5btURwUh0");
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        request.setShouldCache(false);
        listener.onRequestStarted();
        requestQueue.add(request);
    }


    public void println(String data) {
        textView.append(data + "\n");
    }
}
