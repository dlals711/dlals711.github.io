package code.codesample.httpexample;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Server {
    private static Server instance;
    private final ServerApi api;
    private Server(){
        String url="https://52c68623-a550-4cb2-9f97-737a1a6d85b8.mock.pstmn.io";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api= retrofit.create(ServerApi.class);
    }
    public static Server getInstance(){
        if(instance==null) instance=new Server();
        return instance;
    }
    public ServerApi getApi(){ return api; }
}