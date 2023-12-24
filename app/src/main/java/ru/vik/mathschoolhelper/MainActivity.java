package ru.vik.mathschoolhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    private static final String TAG = "VikLog";
    private static final String API_KEY = "AIzaSyAlF8ruzxhT8WCNw9_aOSJFkOvghASJhDc";

    public static final String APP_PREFERENCES_NAME = "app_settings";
    public static final String APP_PREFERENCES_ID = "userId";
    public static final String APP_PREFERENCES_TOKEN = "token";
    public static final String APP_PREFERENCES_IS_LOGGED = "isLogged";
    public static final String DB_NAME = "mathDB";

    SharedPreferences mSettings;

    ConstraintLayout contentLayout, hometaskLayout, videoListLayout, videoLessonLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создание БД Room
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DB_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        lessonDao = db.lessonDao();
        taskDao = db.taskDao();

        // Заполнение таблицы БД данными, если она не существует
        if (!doesDatabaseExist(getApplicationContext(), DB_NAME)) {
            setSampleData(lessonDao, taskDao);
        }

        mSettings = getSharedPreferences(APP_PREFERENCES_NAME, Context.MODE_PRIVATE);
        progressBar = findViewById(R.id.progressBar);

        profileLayout = findViewById(R.id.profileLayout);
        contentLayout = findViewById(R.id.contentConstraintLayout);
        hometaskLayout = findViewById(R.id.hometaskLayout);
        videoListLayout = findViewById(R.id.videoLessonListLayout);
        videoLessonLayout = findViewById(R.id.videoLessonLayout);

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
        else {
            Log.d(TAG, "Залогинен");
            String accessToken = mSettings.getString(APP_PREFERENCES_TOKEN, "");
            Log.d(TAG, "accessToken: " + accessToken);
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

            // API Запросы, вывод информации о профиле
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

            // Вложенный список
            expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

            fillAdapter(lessonDao);

            expandableListTitle = new ArrayList<>(expandableListData.keySet());
            expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListData);
            expandableListView.setAdapter(expandableListAdapter);

            expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v,
                                            int groupPosition, int childPosition, long id) {
                    // Открывается страница видеоурока
                    Log.d(TAG, "url: " + expandableListData.get(expandableListTitle.get(groupPosition)).get(childPosition).htmlUrl);
                    showVideolesson(expandableListData.get(expandableListTitle.get(groupPosition)).get(childPosition).htmlUrl);
                    videoNum = expandableListData.get(expandableListTitle.get(groupPosition)).get(childPosition).id;
                    return false;
                }
            });

            // Слушатели нажатий кнопок меню
            profileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showProfile();
                }
            });

            videoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showVideoList();
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

    private void fillAdapter(LessonDao lessonDao) {
        List<Lesson> lessonsByType = lessonDao.getByTypeNum("1");
        expandableListData.put("Разбор первой части ЕГЭ", lessonsByType);

        lessonsByType = lessonDao.getByTypeNum("2");
        expandableListData.put("Разбор второй части ЕГЭ", lessonsByType);

        lessonsByType = lessonDao.getByTypeNum("3");
        expandableListData.put("Разбор вариантов ЕГЭ", lessonsByType);
    }

    private void setSampleData(LessonDao lessonDao, TaskDao taskDao) {
        Lesson lesson = new Lesson();

        lesson.title = "Урок 1";
        lesson.htmlUrl = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ByVHQrB-KcA?si=-7M7YmHnguIIMds7\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        lesson.url = "https://www.youtube.com/watch?v=ByVHQrB-KcA";
        lesson.previewUrl = "https://pers-school.ru/upload/iblock/902/902f4345aa8e4617ca4a7aa6b888cf50.jpg";
        lesson.typeNum = "1";
        lessonDao.insertSample(lesson);

        lesson.title = "Урок 2";
        lesson.url = "https://www.youtube.com/watch?v=u4TJsCNEqS4";
        lesson.htmlUrl = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/u4TJsCNEqS4?si=DF0QFDGKDYadVln3\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        lesson.previewUrl = "https://pers-school.ru/upload/iblock/902/902f4345aa8e4617ca4a7aa6b888cf50.jpg";
        lesson.typeNum = "1";
        lessonDao.insertSample(lesson);

        lesson.title = "Урок 1";
        lesson.url = "https://www.youtube.com/watch?v=IrI_s-dLCVw";
        lesson.htmlUrl = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/IrI_s-dLCVw?si=eK3OmQn9an2_EFNJ\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        lesson.previewUrl = "https://pers-school.ru/upload/iblock/902/902f4345aa8e4617ca4a7aa6b888cf50.jpg";
        lesson.typeNum = "2";
        lessonDao.insertSample(lesson);

        lesson.title = "Урок 2";
        lesson.url = "https://www.youtube.com/watch?v=F3KuWG_HuVQ";
        lesson.htmlUrl = "https://www.youtube.com/live/F3KuWG_HuVQ?si=wfkz9kU563Hx4tLP";
        lesson.previewUrl = "https://pers-school.ru/upload/iblock/902/902f4345aa8e4617ca4a7aa6b888cf50.jpg";
        lesson.typeNum = "2";
        lessonDao.insertSample(lesson);

        lesson.title = "Урок 3";
        lesson.url = "https://www.youtube.com/watch?v=kMarWrCwul8";
        lesson.htmlUrl = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/F3KuWG_HuVQ?si=YHStrnUgWGJTGO7o\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        lesson.previewUrl = "https://pers-school.ru/upload/iblock/902/902f4345aa8e4617ca4a7aa6b888cf50.jpg";
        lesson.typeNum = "2";
        lessonDao.insertSample(lesson);

        lesson.title = "Разбор Ященко вариант 9";
        lesson.url = "https://www.youtube.com/watch?v=UVTMzhwPy94";
        lesson.htmlUrl = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/UVTMzhwPy94?si=GskM63i4VxH8nGkW\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        lesson.previewUrl = "https://pers-school.ru/upload/iblock/902/902f4345aa8e4617ca4a7aa6b888cf50.jpg";
        lesson.typeNum = "3";
        lessonDao.insertSample(lesson);

        lesson.title = "Разбор Ященко вариант 11";
        lesson.url = "https://www.youtube.com/watch?v=-12qzgVyVcQ";
        lesson.htmlUrl = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/-12qzgVyVcQ?si=jt_oOHvqngF-dGxC\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        lesson.previewUrl = "https://pers-school.ru/upload/iblock/902/902f4345aa8e4617ca4a7aa6b888cf50.jpg";
        lesson.typeNum = "3";
        lessonDao.insertSample(lesson);

        //-------------------------------------------//

        Task task = new Task();

        task.taskType = "1";
        task.text = "В равнобедренной трапеции большее основание равно 25, боковая сторона равна 10, угол между ними 60°  Найдите меньшее основание.";
        task.taskImageUrl = "https://i.imgur.com/ULQSkER.png";
        task.answer = "15";
        taskDao.insertSample(task);

        task.taskType = "1";
        task.text = "Найдите угол ACO, если его сторона CA касается окружности, O - центр окружности, сторона CO пересекает окружность в точках B и D, а дуга AD окружности, заключенная внутри этого угла, равна 116°. Ответ дайте в градусах.";
        task.taskImageUrl = "https://i.imgur.com/Bxemfee.png";
        task.answer = "26";
        taskDao.insertSample(task);

        task.taskType = "1";
        task.text = "Боковая сторона равнобедренного треугольника равна 5, угол при вершине, противолежащей основанию, равен 120°. Найдите диаметр описанной окружности этого треугольника.";
        task.taskImageUrl = "https://i.imgur.com/2vyBbNF.png";
        task.answer = "10";
        taskDao.insertSample(task);

        task.taskType = "1";
        task.text = "В треугольнике ABC угол C равен 90°, AC = 2, cosA = 0.1  Найдите AB";
        task.taskImageUrl = "https://i.imgur.com/4xIlh5O.png";
        task.answer = "20";
        taskDao.insertSample(task);

        task.taskType = "1";
        task.text = "Чему равен тупой вписанный угол, опирающийся на хорду, равную радиусу окружности? Ответ дайте в градусах.";
        task.taskImageUrl = "https://i.imgur.com/90629Ti.png";
        task.answer = "150";
        taskDao.insertSample(task);

        task.taskType = "1";
        task.text = "В треугольнике ABC AC = BC, AB = 8, cosA = 0.5 Найдите AC.";
        task.taskImageUrl = "https://i.imgur.com/Qgy9XoR.png";
        task.answer = "8";
        taskDao.insertSample(task);

        task.taskType = "1";
        task.text = "Острый угол ромба равен 30°. Радиус вписанной в этот ромб окружности равен 9. Найдите сторону ромба.";
        task.taskImageUrl = "https://i.imgur.com/bwKjRhf.png";
        task.answer = "36";
        taskDao.insertSample(task);

        task.taskType = "1";
        task.text = "В треугольнике ABC угол A равен 62°, внешний угол при вершине B равен 118° . Найдите угол C. Ответ дайте в градусах.";
        task.taskImageUrl = "https://i.imgur.com/Nn0MBMX.png";
        task.answer = "56";
        taskDao.insertSample(task);

        task.taskType = "1";
        task.text = "Площадь параллелограмма ABCD равна 14. Найдите площадь параллелограмма A'B'C'D', вершинами которого являются середины сторон данного параллелограмма.";
        task.taskImageUrl = "";
        task.answer = "7";
        taskDao.insertSample(task);

        task.taskType = "1";
        task.text = "В треугольнике ABC угол A равен 60°, угол B равен 82°. AD, BE и CF — высоты, пересекающиеся в точке O. Найдите угол AOF. Ответ дайте в градусах.";
        task.taskImageUrl = "https://i.imgur.com/mFc54PO.png";
        task.answer = "82";
        taskDao.insertSample(task);
    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    private void showProfile(){
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

    private void showVideoList(){
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

    private void showHometask(){
        clearButtonFilters();
        profileLayout.setVisibility(View.GONE);
        videoListLayout.setVisibility(View.GONE);

        killWebViewPlayer();
        videoLessonLayout.setVisibility(View.GONE);

        hometaskLayout.setVisibility(View.VISIBLE);
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

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.taskType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        taskTypeSpinner.setAdapter(adapter);

        selectTypeButton.setOnClickListener(v -> {
            tasks = taskDao.getTaskByTypeNum(Integer.toString(taskTypeSpinner.getSelectedItemPosition() + 1));
            chooseTypeLayout.setVisibility(View.GONE);
            RecyclerView recyclerView = findViewById(R.id.taskListRecyclerView);
            TaskAdapter taskAdapter = new TaskAdapter(getApplicationContext(), tasks, taskDao);
            recyclerView.setAdapter(taskAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            taskRecyclerViewLayout.setVisibility(View.VISIBLE);
        });

    }

    private void showVideolesson(String html){
        clearButtonFilters();
        profileLayout.setVisibility(View.GONE);
        videoListLayout.setVisibility(View.GONE);
        hometaskLayout.setVisibility(View.GONE);

        videoProgressBar.setVisibility(View.VISIBLE);
        videoLessonLayout.setVisibility(View.VISIBLE);
        initWebViewPlayer(html);
    }

    private void clearButtonFilters(){
        profileButton.clearColorFilter();
        videoButton.clearColorFilter();
        hometaskButton.clearColorFilter();
    }

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

    private void killWebViewPlayer(){
        videoProgressBar.setVisibility(View.GONE);
        webView.onPause();
    }

    private class LoadVideoDescriptionTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                // Создаем экземпляр YouTube клиента с использованием API-ключа
                YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new GsonFactory(), new HttpRequestInitializer() {
                    public void initialize(HttpRequest request) throws IOException {}
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
                    String description = video.getSnippet().getDescription();
                    return description;
                }

            } catch (Exception e) {
                Log.e(TAG, "Failed to load video description", e);
            }

            return "";
        }

        @Override
        protected void onPostExecute(String description) {
            descriptionTextView.setText(description);
        }
    }

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

// 4. -> Окно видеоуроков (проигрыватель) ✅
// 4.1 -> Тайминги, описание и т.д. ✅

// 5. -> Домашние задания (ЕГЭ) 1 часть ✅
// 6. -> Домашние задания (ЕГЭ) 2 часть
// 7. -> Обработку ответов пользователя












//            expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//                @Override
//                public void onGroupExpand(int groupPosition) {
////                    Toast.makeText(getApplicationContext(),
////                            expandableListTitle.get(groupPosition) + " List Expanded.",
////                            Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//                @Override
//                public void onGroupCollapse(int groupPosition) {
////                    Toast.makeText(getApplicationContext(),
////                            expandableListTitle.get(groupPosition) + " List Collapsed.",
////                            Toast.LENGTH_SHORT).show();
//                }
//            });

//                    Toast.makeText(
//                            getApplicationContext(),
//                            expandableListTitle.get(groupPosition)
//                                    + " -> "
//                                    + expandableListData.get(
//                                    expandableListTitle.get(groupPosition)).get(
//                                    childPosition).title, Toast.LENGTH_SHORT
//                    ).show();