package code.codesample.myapplicationdaw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.java_websocket.WebSocket;

import code.codesample.myapplicationdaw.R;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.client.StompClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            new LongOperation().execute("");
        });
    }

    private class LongOperation extends AsyncTask<String, Void, String> {
        private StompClient mStompClient;

        String TAG="LongOperation";
        @Override
        protected String doInBackground(String... params) {

            //mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://3.34.55.186:8088/ws-stomp/websocket");
            mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://192.168.1.51:8088/ws-stomp/websocket");
            mStompClient.send("/pub/jmj", "T!").subscribe();
//            mStompClient.connect();
//
//            mStompClient.topic("/sub/jmj").subscribe(topicMessage -> {
//                Log.d(TAG, topicMessage.getPayload());
//            });
//
//            mStompClient.send("/pub/jmj", "My first STOMP message!");
//
//            mStompClient.lifecycle().subscribe(lifecycleEvent -> {
//                switch (lifecycleEvent.getType()) {
//
//                    case OPENED:
//                        Log.d(TAG, "Stomp connection opened");
//                        break;
//
//                    case ERROR:
//                        Log.e(TAG, "Error", lifecycleEvent.getException());
//                        break;
//
//                    case CLOSED:
//                        Log.d(TAG, "Stomp connection closed");
//                        break;
//                }
//            });
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

        }

    }
}