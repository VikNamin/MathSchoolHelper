package ru.vik.mathschoolhelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> firstTheme = new ArrayList<String>();

        for (int i = 0; i<5; i++){
            firstTheme.add("Урок №" + i+1);
        }

        List<String> secondTheme = new ArrayList<String>();

        for (int i = 0; i<3; i++){
            secondTheme.add("Урок №" + i+1);
        }

        List<String> thirdTheme = new ArrayList<String>();

        for (int i = 0; i<6; i++){
            thirdTheme.add("Урок №" + i+1);
        }

        expandableListDetail.put("Подготовка к первой части", firstTheme);
        expandableListDetail.put("Подготовка ко второй части", secondTheme);
        expandableListDetail.put("Разборы ЕГЭ", thirdTheme);
        return expandableListDetail;
    }
}
