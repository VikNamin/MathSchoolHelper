package ru.vik.mathschoolhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.authButton).setOnClickListener(v -> {
            Intent toWeb = new Intent(getApplicationContext(), WebActivity.class);
            startActivity(toWeb);
        });
    }
}

// 1. -> Авторизация по ВК
// 2. -> Личный кабинет пользователя
// 3. -> Список видеоуроков (вложенный список)
// 4. -> Окно видеоуроков (тайминги, просмотнено/не просмотрено)
// 5. -> Домашние задания (ЕГЭ) 1 часть
// 6. -> Домашние задания (ЕГЭ) 2 часть
// 7. -> Обработку ответов пользователя