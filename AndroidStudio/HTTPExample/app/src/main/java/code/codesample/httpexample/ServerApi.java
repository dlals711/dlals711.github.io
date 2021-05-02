package code.codesample.httpexample;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerApi {
    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{userid}")
    Call<User> getUser(@Path("userid") String userid);
}