package ru.vik.mathschoolhelper;

import static ru.vik.mathschoolhelper.MainActivity.APP_PREFERENCES;
import static ru.vik.mathschoolhelper.MainActivity.APP_PREFERENCES_ID;
import static ru.vik.mathschoolhelper.MainActivity.APP_PREFERENCES_IS_LOGGED;
import static ru.vik.mathschoolhelper.MainActivity.APP_PREFERENCES_TOKEN;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.vik.mathschoolhelper.VkAPI.VkUser;

public class WebActivity extends AppCompatActivity {

    public static Integer userId;
    public static String accessToken;

    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Стандартные Java Android методы
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        // Создаём объект WebView - браузер в приложении
        WebView webView = findViewById(R.id.authWebView);

        // Настраиваем браузер для корректной работы JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        // Загружаем в него ссылку
        webView.loadUrl("https://oauth.vk.com/authorize?client_id=51778914&" +
                "display=page&" +
                "redirect_uri=https://oauth.vk.com/blank.html&" +
                "scope=account/offline&" +
                "response_type=token&" +
                "v=5.131&" +
                "state=123456");

        // Разрешаем файлы Cookie
        CookieManager.getInstance().setAcceptCookie(true);

        // Устанавливаем собственный WebViewClient, чтобы браузер открывался внутри приложения
        webView.setWebViewClient(new WebClient());
    }

    class WebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("VikLog", "url: " + url);
            if (url.contains("access_token")){
                userId = Integer.parseInt(url.substring(url.indexOf("user_id") + "user_id".length()+1, url.indexOf("state") - 1));
                accessToken = url.substring(url.indexOf("access_token") + "access_token".length()+1, url.indexOf("expires_in") - 1);
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://api.vk.com/method/")
                        .build();
                VkAPIService vkAPIService = retrofit.create(VkAPIService.class);
                vkAPIService.getProfile(accessToken, userId).enqueue(new Callback<VkResponse>() {
                    @Override
                    public void onResponse(Call<VkResponse> call, Response<VkResponse> response) {
                        findViewById(R.id.authWebView).setVisibility(View.GONE);
                        SharedPreferences.Editor editor = mSettings.edit();
                        editor.putBoolean(APP_PREFERENCES_IS_LOGGED, true);
                        editor.putString(APP_PREFERENCES_ID, userId.toString());
                        editor.putString(APP_PREFERENCES_TOKEN, accessToken);
                        editor.apply();
                        Intent toMain = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(toMain);
                    }

                    @Override
                    public void onFailure(Call<VkResponse> call, Throwable t) {
                        Log.d("VikLog", "ОШИБКА - " + t.getMessage());
                    }
                });
            }
        }
    }

    class VkResponse{
        VkUser response;

        VkResponse(){}

        public VkUser getResponse() {
            return response;
        }

        public void setResponse(VkUser response) {
            this.response = response;
        }
    }
}