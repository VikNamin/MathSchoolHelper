package ru.vik.mathschoolhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "app_settings";
    public static final String APP_PREFERENCES_ID = "userId";
    public static final String APP_PREFERENCES_TOKEN = "token";
    public static final String APP_PREFERENCES_IS_LOGGED = "isLogged";

    SharedPreferences mSettings;

    ConstraintLayout contentLayout, hometaskLayout, videoLayout;
    LinearLayout profileLayout;

    ProgressBar progressBar;
    ImageView profileImageView, profileButton, videoButton, hometaskButton;
    TextView fioTextView, hometownTextView, birthTextView, numberTextView, nicknameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        progressBar = findViewById(R.id.progressBar);

        profileLayout = findViewById(R.id.profileLayout);
        contentLayout = findViewById(R.id.contentConstraintLayout);
        hometaskLayout = findViewById(R.id.hometaskLayout);
        videoLayout = findViewById(R.id.videoLessonLayout);

        profileImageView = findViewById(R.id.profileImageView);

        profileButton = findViewById(R.id.cabinetButton);
        videoButton = findViewById(R.id.videoButton);
        hometaskButton = findViewById(R.id.homeTaskButton);

        fioTextView = findViewById(R.id.fioText);
        hometownTextView = findViewById(R.id.regionText);
        birthTextView = findViewById(R.id.bdText);
        numberTextView = findViewById(R.id.telText);
        nicknameTextView = findViewById(R.id.vkText);

        if(!mSettings.getBoolean(APP_PREFERENCES_IS_LOGGED, false)){
            Log.d("VikLog", "НЕ Залогинен");
            progressBar.setVisibility(View.GONE);
            Button authButton = findViewById(R.id.authButton);
            authButton.setVisibility(View.VISIBLE);
            findViewById(R.id.authButton).setOnClickListener(v -> {
                Intent toWeb = new Intent(getApplicationContext(), WebActivity.class);
                startActivity(toWeb);
            });
        }
        else {
            Log.d("VikLog", "Залогинен");
            String accessToken = mSettings.getString(APP_PREFERENCES_TOKEN, "");
            Log.d("VikLog", "accessToken: " + accessToken);
            String userID = mSettings.getString(APP_PREFERENCES_ID, "");

            progressBar.setVisibility(View.GONE);
            contentLayout.setVisibility(View.VISIBLE);
            profileLayout.setVisibility(View.VISIBLE);

            profileButton.setColorFilter(
                    ContextCompat.getColor(
                            this,
                            R.color.toolbar_button_color_tint
                    ), android.graphics.PorterDuff.Mode.MULTIPLY
            );

            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.vk.com/method/")
                    .build();
            VkAPIService vkAPIService = retrofit.create(VkAPIService.class);
            vkAPIService.getProfile(accessToken, Integer.parseInt(userID))
                    .enqueue(new Callback<WebActivity.VkResponse>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<WebActivity.VkResponse> call, Response<WebActivity.VkResponse> response) {
                    Picasso.get().load(response.body().getResponse().getPhotoUrl()).into(profileImageView);
                    fioTextView.setText(response.body().getResponse().getFirstName() + " " + response.body().getResponse().getLastName());
                    hometownTextView.setText(response.body().getResponse().getCity().getTitle());
                    birthTextView.setText(response.body().getResponse().getBirthDate());
                    numberTextView.setText(response.body().getResponse().getMobileNumber());
                    nicknameTextView.setText(response.body().getResponse().getNickname());
                }

                @Override
                public void onFailure(Call<WebActivity.VkResponse> call, Throwable t) {
                    Log.d("VikLog", "ОШИБКА - " + t.getMessage());
                }
            });

            profileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showProfile();
                }
            });

            videoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showVideo();
                }
            });

            hometaskButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showHometask();
                }
            });

//            trainButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showTrain();
//                }
//            });
        }
    }

    private void showProfile(){
        clearButtonFilters();
        videoLayout.setVisibility(View.GONE);
        hometaskLayout.setVisibility(View.GONE);

        profileLayout.setVisibility(View.VISIBLE);
        profileButton.setColorFilter(
                ContextCompat.getColor(
                        this,
                        R.color.toolbar_button_color_tint
                ), android.graphics.PorterDuff.Mode.MULTIPLY
        );
    }

    private void showVideo(){
        clearButtonFilters();
        profileLayout.setVisibility(View.GONE);
        hometaskLayout.setVisibility(View.GONE);

        videoLayout.setVisibility(View.VISIBLE);
        videoButton.setColorFilter(
                ContextCompat.getColor(
                        this,
                        R.color.toolbar_button_color_tint
                ), android.graphics.PorterDuff.Mode.MULTIPLY
        );
    }

    private void showHometask(){
        clearButtonFilters();
        profileLayout.setVisibility(View.GONE);
        videoLayout.setVisibility(View.GONE);

        hometaskLayout.setVisibility(View.VISIBLE);
        hometaskButton.setColorFilter(
                ContextCompat.getColor(
                        this,
                        R.color.toolbar_button_color_tint
                ), android.graphics.PorterDuff.Mode.MULTIPLY
        );
    }

    private void clearButtonFilters(){
        profileButton.clearColorFilter();
        videoButton.clearColorFilter();
        hometaskButton.clearColorFilter();
    }
}

// 1. -> Авторизация по ВК ✅
// 2. -> Личный кабинет пользователя ✅
//  2.2 -> Вывод информации из полученного ответа на экран ✅

// 3. -> Список видеоуроков (вложенный список)

// 4. -> Окно видеоуроков (тайминги, просмотнено/не просмотрено)
// 5. -> Домашние задания (ЕГЭ) 1 часть
// 6. -> Домашние задания (ЕГЭ) 2 часть
// 7. -> Обработку ответов пользователя