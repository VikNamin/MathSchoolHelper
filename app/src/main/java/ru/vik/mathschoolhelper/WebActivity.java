package ru.vik.mathschoolhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import retrofit2.Retrofit;
import retrofit2.http.GET;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView webView = (WebView) findViewById(R.id.authWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://oauth.vk.com/authorize?client_id=51778914&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.131");
        webView.setWebViewClient(new WebClient());
    }

    class WebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (url.contains("access_token")){
                Log.d("VikLog", "url = " + url);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.vk.com/method/")
                        .build();
            }
        }
    }

//    public interface GitHubService {
//        @GET("friends.get")
//        Call<List<Repo>> listRepos(@Path("user") String user);
//    }
}