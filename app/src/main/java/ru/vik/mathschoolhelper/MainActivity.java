package ru.vik.mathschoolhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vik.mathschoolhelper.Room.AppDatabase;
import ru.vik.mathschoolhelper.Room.Lesson;
import ru.vik.mathschoolhelper.Room.LessonDao;
import ru.vik.mathschoolhelper.Room.Task;
import ru.vik.mathschoolhelper.Room.TaskDao;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "VikLog";
    private static final String API_KEY = "AIzaSyAlF8ruzxhT8WCNw9_aOSJFkOvghASJhDc";

    public static final String APP_PREFERENCES_NAME = "app_settings";
    public static final String APP_PREFERENCES_ID = "userId";
    public static final String APP_PREFERENCES_TOKEN = "token";
    public static final String APP_PREFERENCES_IS_LOGGED = "isLogged";
    public static final String DB_NAME = "mathDB";

    SharedPreferences mSettings;

    ConstraintLayout contentLayout, hometaskLayout, videoListLayout, videoLessonLayout, hometaskListLayout;
    LinearLayout profileLayout;

    ProgressBar progressBar;
    ImageView profileImageView, profileButton, videoButton, hometaskButton;
    TextView fioTextView, hometownTextView, birthTextView, numberTextView, nicknameTextView, descriptionTextView;

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    List<String> expandableListTitle;
    HashMap<String, List<Lesson>> expandableListData = new HashMap<>();

    List<Task> tasks;

    WebView webView;
    ProgressBar videoProgressBar;

    ScrollView videoScrollView;

    int videoNum;
    LessonDao lessonDao;
    TaskDao taskDao;

    Spinner taskTypeSpinner;


    // АЛГОРИТМ ПРОГРАММЫ
    // Пункт 1 алгоритма. Вызов метода onCreate при запуске приложения пользователем, начальная настройка приложения


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // 1.1 Вызов методов super.onCreate и setContentView.
        // Первый метод запускает активити с параметром сохраненного состояния приложения.
        // Второй устанавливает пользовательскую разметку экрана (activity_main.xml)

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.2 Создание БД Room
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        // 1.3 Создание переменных для работы с БД Room (Создание Data Access Object объектов)
        lessonDao = db.lessonDao();
        taskDao = db.taskDao();

        // 1.4 Заполнение таблицы БД данными, если она не существует
        if (!doesDatabaseExist(getApplicationContext())) {
            DataSampleCreator.createDataSample(lessonDao, taskDao);
        }

        // 1.5 Подключение файла, в котором хранятся сохраненные значения
        mSettings = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);

        // 1.6 Нахождение на View элементов и присвоение их переменным для последующей работы
        progressBar = findViewById(R.id.progressBar);
        profileLayout = findViewById(R.id.profileLayout);
        contentLayout = findViewById(R.id.contentConstraintLayout);
        hometaskLayout = findViewById(R.id.hometaskLayout);
        videoListLayout = findViewById(R.id.videoLessonListLayout);
        videoLessonLayout = findViewById(R.id.videoLessonLayout);
        hometaskListLayout = findViewById(R.id.taskRecyclerViewLayout);
        profileImageView = findViewById(R.id.profileImageView);
        profileButton = findViewById(R.id.cabinetButton);
        videoButton = findViewById(R.id.videoButton);
        hometaskButton = findViewById(R.id.homeTaskButton);
        fioTextView = findViewById(R.id.fioText);
        hometownTextView = findViewById(R.id.regionText);
        birthTextView = findViewById(R.id.bdText);
        numberTextView = findViewById(R.id.telText);
        nicknameTextView = findViewById(R.id.vkText);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        webView = findViewById(R.id.playerWebView);
        videoProgressBar = findViewById(R.id.videoProgressBar);
        videoScrollView = findViewById(R.id.videoScrollView);
        taskTypeSpinner = findViewById(R.id.spinnerAddType);

        // 1.7.1 Условие. Если пользователь НЕ залогинен в приложение через сервис VK, то отображаем
        // кнопку "Авторизация" с последующим запуска алгоритма авторизации пользователя в приложение
        if(!mSettings.getBoolean(APP_PREFERENCES_IS_LOGGED, false)){
            Log.d(TAG, "НЕ Залогинен");
            progressBar.setVisibility(View.GONE);
            Button authButton = findViewById(R.id.authButton);
            authButton.setVisibility(View.VISIBLE);
            findViewById(R.id.authButton).setOnClickListener(v -> {
                Intent toWeb = new Intent(getApplicationContext(), WebActivity.class);
                startActivity(toWeb);
            });
        }
        // 1.7.2 Если пользователь залогинен, то
        else {

            // Получаем сохраненные значения VK ID пользователя и токена
            String accessToken = mSettings.getString(APP_PREFERENCES_TOKEN, "");
            String userID = mSettings.getString(APP_PREFERENCES_ID, "");

            // Настраиваем пользовательский интерфейс
            progressBar.setVisibility(View.GONE);
            contentLayout.setVisibility(View.VISIBLE);
            profileLayout.setVisibility(View.VISIBLE);
            profileButton.setColorFilter(
                    ContextCompat.getColor(
                            this,
                            R.color.toolbar_button_color_tint
                    ), android.graphics.PorterDuff.Mode.MULTIPLY
            );

            // Выполняем API Запросы, вывод информации о профиле
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.vk.com/method/")
                    .build();
            VkAPIService vkAPIService = retrofit.create(VkAPIService.class);
            vkAPIService.getProfile(accessToken, Integer.parseInt(userID))
                    .enqueue(new Callback<WebActivity.VkResponse>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(@NonNull Call<WebActivity.VkResponse> call, @NonNull Response<WebActivity.VkResponse> response) {
                    if (response.body() != null) {
                        Picasso.get().load(response.body().getResponse().getPhotoUrl()).into(profileImageView);
                        fioTextView.setText(response.body().getResponse().getFirstName() + " " + response.body().getResponse().getLastName());
                        hometownTextView.setText(response.body().getResponse().getCity().getTitle());
                        birthTextView.setText(response.body().getResponse().getBirthDate());
                        numberTextView.setText(response.body().getResponse().getMobileNumber());
                        nicknameTextView.setText(response.body().getResponse().getNickname());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<WebActivity.VkResponse> call, @NonNull Throwable t) {
                    Log.d(TAG, "ОШИБКА - " + t.getMessage());
                }
            });

            // Создаем и заполняем данными из БД вложенный список
            expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
            fillAdapter(lessonDao);
            expandableListTitle = new ArrayList<>(expandableListData.keySet());
            expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListData);
            expandableListView.setAdapter(expandableListAdapter);

            // Устанавливаем слушатели нажатий на элементы списка
            expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
                // По нажатию открывается страница видеоурока
                showVideoLesson(expandableListData.get(expandableListTitle.get(groupPosition)).get(childPosition).htmlUrl);
                videoNum = expandableListData.get(expandableListTitle.get(groupPosition)).get(childPosition).id;
                return false;
            });

            // Создаем слушатели нажатий кнопок верхнего меню
            profileButton.setOnClickListener(v -> showProfile());
            videoButton.setOnClickListener(v -> showVideoList());
            hometaskButton.setOnClickListener(v -> showHometask());
        }
    }

    // Метод относится к пункту 1.7.2, заполнение адаптера вложенного списка уроков данными
    private void fillAdapter(LessonDao lessonDao) {
        List<Lesson> lessonsByType = lessonDao.getByTypeNum("1");
        expandableListData.put("Разбор первой части ЕГЭ", lessonsByType);

        lessonsByType = lessonDao.getByTypeNum("2");
        expandableListData.put("Разбор второй части ЕГЭ", lessonsByType);

        lessonsByType = lessonDao.getByTypeNum("3");
        expandableListData.put("Разбор вариантов ЕГЭ", lessonsByType);
    }

    // Метод относится к пункту 1.4, проверка существования БД
    private static boolean doesDatabaseExist(Context context) {
        File dbFile = context.getDatabasePath(MainActivity.DB_NAME);
        return dbFile.exists();
    }

    // Пункт 2 алгоритма. Вывод профиля пользователя
    private void showProfile(){

        // 2.1 Настройка пользовательского интерфейса
        clearButtonFilters();
        videoListLayout.setVisibility(View.GONE);
        hometaskLayout.setVisibility(View.GONE);
        killWebViewPlayer();
        videoLessonLayout.setVisibility(View.GONE);
        profileLayout.setVisibility(View.VISIBLE);
        profileButton.setColorFilter(
                ContextCompat.getColor(
                        this,
                        R.color.toolbar_button_color_tint
                ), android.graphics.PorterDuff.Mode.MULTIPLY
        );
    }

    // Пункт 3 алгоритма. Вывод списка видеоуроков
    private void showVideoList(){

        // 3.1 Настройка пользовательского интерфейса
        clearButtonFilters();
        profileLayout.setVisibility(View.GONE);
        hometaskLayout.setVisibility(View.GONE);

        killWebViewPlayer();
        videoLessonLayout.setVisibility(View.GONE);

        videoListLayout.setVisibility(View.VISIBLE);
        videoButton.setColorFilter(
                ContextCompat.getColor(
                        this,
                        R.color.toolbar_button_color_tint
                ), android.graphics.PorterDuff.Mode.MULTIPLY
        );
    }

    // Пункт 4 алгоритма. Вывод списка домашних заданий
    private void showHometask(){

        // 4.1 Настройка пользовательского интерфейса
        clearButtonFilters();
        profileLayout.setVisibility(View.GONE);
        videoListLayout.setVisibility(View.GONE);
        killWebViewPlayer();
        videoLessonLayout.setVisibility(View.GONE);
        hometaskLayout.setVisibility(View.VISIBLE);
        hometaskListLayout.setVisibility(View.GONE);
        hometaskButton.setColorFilter(
                ContextCompat.getColor(
                        this,
                        R.color.toolbar_button_color_tint
                ), android.graphics.PorterDuff.Mode.MULTIPLY
        );
        ConstraintLayout chooseTypeLayout = findViewById(R.id.chooseTypeLayout);
        ConstraintLayout taskRecyclerViewLayout = findViewById(R.id.taskRecyclerViewLayout);
        Button selectTypeButton = findViewById(R.id.selectTaskTypeButton);
        chooseTypeLayout.setVisibility(View.VISIBLE);
        taskRecyclerViewLayout.setVisibility(View.GONE);

        // 4.2 Заполнение выпадающего списка данными
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.taskType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskTypeSpinner.setAdapter(adapter);

        // 4.3 Создание слушателя нажатия на кнопку выбора домашнего задания
        selectTypeButton.setOnClickListener(v -> {

            // 4.4 По нажатию на кнопку, вывод выбранных пользователем домашних заданий списком
            int typeNum = taskTypeSpinner.getSelectedItemPosition() + 1;
            tasks = taskDao.getTaskByTypeNum(Integer.toString(taskTypeSpinner.getSelectedItemPosition() + 1));
            chooseTypeLayout.setVisibility(View.GONE);
            hometaskListLayout.setVisibility(View.VISIBLE);
            RecyclerView recyclerView = findViewById(R.id.taskListRecyclerView);
            TaskAdapter taskAdapter = new TaskAdapter(getApplicationContext(), tasks, taskDao, typeNum);
            recyclerView.setAdapter(taskAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    linearLayoutManager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
            taskRecyclerViewLayout.setVisibility(View.VISIBLE);
        });

    }

    // Пункт 5 алгоритма. Вывод видеоурока
    private void showVideoLesson(String html){
        // 5.1 Настройка пользовательского интерфейса
        clearButtonFilters();
        profileLayout.setVisibility(View.GONE);
        videoListLayout.setVisibility(View.GONE);
        hometaskLayout.setVisibility(View.GONE);

        videoProgressBar.setVisibility(View.VISIBLE);
        videoLessonLayout.setVisibility(View.VISIBLE);

        // 5.2 Инициализация и запуск плеера с выбранным видеоуроком
        initWebViewPlayer(html);
    }

    // Вспомогательный метод для настройки UI
    private void clearButtonFilters(){
        profileButton.clearColorFilter();
        videoButton.clearColorFilter();
        hometaskButton.clearColorFilter();
    }

    // Метод относится к пункту 5.2. Запуск плеера webView с выбранным видеоуроком
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewPlayer(String html){
        videoScrollView.setVisibility(View.INVISIBLE);
        webView.loadData(html, "text/html", "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.onResume();
        webView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                Log.d(TAG, "Загружено");
                videoScrollView.setVisibility(View.VISIBLE);
                videoProgressBar.setVisibility(View.GONE);
            }
        });

        new LoadVideoDescriptionTask().execute();
    }

    // Вспомогательный метод. Останавливает плеер для очистки памяти
    private void killWebViewPlayer(){
        videoProgressBar.setVisibility(View.GONE);
        webView.onPause();
    }

    // Вспомогательный класс для получения описания видео, в фоновом потоке. Используется YouTube API
    private class LoadVideoDescriptionTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                // Создаем экземпляр YouTube клиента с использованием API-ключа
                YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new GsonFactory(), new HttpRequestInitializer() {
                    public void initialize(HttpRequest request) {}
                }).setApplicationName("YourAppName").build();

                // Создаем запрос на получение данных о видео
                YouTube.Videos.List videosListRequest = youtube.videos().list("snippet");
                videosListRequest.setKey(API_KEY);
                String id = extractVideoId(lessonDao.getById(videoNum).url);
                videosListRequest.setId(id);

                // Отправляем запрос и получаем ответ
                VideoListResponse response = videosListRequest.execute();

                // Получаем список видео
                List<Video> videos = response.getItems();

                // Если видео найдено, получаем описание
                if (videos != null && !videos.isEmpty()) {
                    Video video = videos.get(0);
                    return video.getSnippet().getDescription();
                }

            } catch (Exception e) {
                Log.e(TAG, "Failed to load video description, " + e);
            }

            return "";
        }

        @Override
        protected void onPostExecute(String description) {
            descriptionTextView.setText(description);
        }
    }

    // Вспомогательный метод для получения из строки значения videoId. Использует регулярные выражения
    public static String extractVideoId(String url) {
        String videoId = null;
        if (url != null && url.trim().length() > 0) {
            String[] splitValues = url.split("v=");
            if (splitValues.length > 1) {
                videoId = splitValues[1];
                int ampersandPosition = videoId.indexOf('&');
                if (ampersandPosition != -1) {
                    videoId = videoId.substring(0, ampersandPosition);
                }
            }
        }
        return videoId;
    }

}

// 1. -> Авторизация по ВК ✅
// 2. -> Личный кабинет пользователя ✅
//  2.2 -> Вывод информации из полученного ответа на экран ✅

// 3. -> Список видеоуроков (вложенный список)
//  3.1 -> Несколько разделов видеоуроков, 1 часть, 2 часть, разборы ЕГЭ, прочее и т.д., информацию об элементах вложить в БД Room? ✅
//  3.3 -> Опись типов данных по структуре: Название типа данных, ограничения/диапазон, названия переменных и на основе этого причина, по которой выбрали именно этот тип данных. ✅
//  3.2 -> Сделать таблицу в БД, в которой хранятся: id, название урока, ссылка на YouTube, ссылка на превью ✅

// 4. -> Окно видео уроков (проигрыватель) ✅
// 4.1 -> Тайминги, описание и т.д. ✅

// 5. -> Домашние задания (ЕГЭ) 1 часть ✅
// 6. -> Домашние задания (ЕГЭ) 2 часть ✅