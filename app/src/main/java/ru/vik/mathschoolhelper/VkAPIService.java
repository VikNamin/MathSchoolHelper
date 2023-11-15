package ru.vik.mathschoolhelper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface VkAPIService {
    @GET("account.getProfileInfo?access_token=token&v=5.154&user_id=123")
    Call<WebActivity.VkResponse> getProfile(@Query("access_token") String token, @Query("user_id") Integer id_user);
}
