package code.codesample.httpexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import code.codesample.httpexample.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private retrofit2.Call<User> request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        request = Server.getInstance().getApi().getUser("1000");
        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code()==200){
                    User user = response.body();
                    Log.d("Main-Retrofit", user.name);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        //new Thread(runnable).start();

//        String url="https://52c68623-a550-4cb2-9f97-737a1a6d85b8.mock.pstmn.io/users/100";
//        JsonObjectRequest request=new JsonObjectRequest(
//                Request.Method.GET,
//                url,
//                null,
//                response->{
//                    Log.d("Main-Volley", response.toString());
//                },
//                error->{}
//        );
//        SingleQueue.getInstance(this).getRequestQueue().add(request);
    }

    private final Runnable runnable = ()->{
        try {
            User user;
            String result;
            URL url = new URL("https://52c68623-a550-4cb2-9f97-737a1a6d85b8.mock.pstmn.io/users/1");
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is=conn.getInputStream();
            StringBuilder builder=new StringBuilder();
            BufferedReader br=new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            while((line=br.readLine())!=null){
                builder.append(line).append("\n");
            }
            result=builder.toString();
            Gson gson=new Gson();
            user=gson.fromJson(result, User.class);
            Log.d("Main", result);
            Log.d("Main", user.userid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(request!=null) request.cancel();
    }
}