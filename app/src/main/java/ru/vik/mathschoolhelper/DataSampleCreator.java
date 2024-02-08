package ru.vik.mathschoolhelper;

import ru.vik.mathschoolhelper.Room.Lesson;
import ru.vik.mathschoolhelper.Room.LessonDao;
import ru.vik.mathschoolhelper.Room.Task;
import ru.vik.mathschoolhelper.Room.TaskDao;

public class DataSampleCreator {

    public static void createDataSample(LessonDao lessonDao, TaskDao taskDao){
        Lesson lesson = new Lesson();
        Task task = new Task();

        // Заполнение базы данных значениями

        //-----------Таблица уроков-------------//
        //--------------------------------------//
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



        //----------Таблица домашних заданий---------//
        //-------------------------------------------//

        //-----------------Задание 1-----------------//
        //-------------------------------------------//

        task.taskType = "1";
        task.text = "В равнобедренной трапеции большее основание равно 25, боковая сторона равна 10, угол между ними 60°  Найдите меньшее основание.";
        task.taskImageUrl = "https://i.imgur.com/ULQSkER.png";
        task.answer = "15";
        taskDao.insertSample(task);

        task.text = "Найдите угол ACO, если его сторона CA касается окружности, O - центр окружности, сторона CO пересекает окружность в точках B и D, а дуга AD окружности, заключенная внутри этого угла, равна 116°. Ответ дайте в градусах.";
        task.taskImageUrl = "https://i.imgur.com/Bxemfee.png";
        task.answer = "26";
        taskDao.insertSample(task);

        task.text = "Боковая сторона равнобедренного треугольника равна 5, угол при вершине, противолежащей основанию, равен 120°. Найдите диаметр описанной окружности этого треугольника.";
        task.taskImageUrl = "https://i.imgur.com/2vyBbNF.png";
        task.answer = "10";
        taskDao.insertSample(task);

        task.text = "В треугольнике ABC угол C равен 90°, AC = 2, cosA = 0.1  Найдите AB";
        task.taskImageUrl = "https://i.imgur.com/4xIlh5O.png";
        task.answer = "20";
        taskDao.insertSample(task);

        task.text = "Чему равен тупой вписанный угол, опирающийся на хорду, равную радиусу окружности? Ответ дайте в градусах.";
        task.taskImageUrl = "https://i.imgur.com/90629Ti.png";
        task.answer = "150";
        taskDao.insertSample(task);

        task.text = "В треугольнике ABC AC = BC, AB = 8, cosA = 0.5 Найдите AC.";
        task.taskImageUrl = "https://i.imgur.com/Qgy9XoR.png";
        task.answer = "8";
        taskDao.insertSample(task);

        task.text = "Острый угол ромба равен 30°. Радиус вписанной в этот ромб окружности равен 9. Найдите сторону ромба.";
        task.taskImageUrl = "https://i.imgur.com/bwKjRhf.png";
        task.answer = "36";
        taskDao.insertSample(task);

        task.text = "В треугольнике ABC угол A равен 62°, внешний угол при вершине B равен 118° . Найдите угол C. Ответ дайте в градусах.";
        task.taskImageUrl = "https://i.imgur.com/Nn0MBMX.png";
        task.answer = "56";
        taskDao.insertSample(task);

        task.text = "Площадь параллелограмма ABCD равна 14. Найдите площадь параллелограмма A'B'C'D', вершинами которого являются середины сторон данного параллелограмма.";
        task.taskImageUrl = "";
        task.answer = "7";
        taskDao.insertSample(task);

        task.text = "В треугольнике ABC угол A равен 60°, угол B равен 82°. AD, BE и CF — высоты, пересекающиеся в точке O. Найдите угол AOF. Ответ дайте в градусах.";
        task.taskImageUrl = "https://i.imgur.com/mFc54PO.png";
        task.answer = "82";
        taskDao.insertSample(task);

        //-----------------Задание 2-----------------//
        //-------------------------------------------//

        task.taskType = "2";
        task.text = "Вектор AB→ с концом в точке B(3,7) имеет координаты (4,20). Найдите ординату точки A.";
        task.taskImageUrl = "";
        task.answer = "-13";
        taskDao.insertSample(task);

        task.text = "Найдите длину вектора d→ = (10; 24).";
        task.taskImageUrl = "";
        task.answer = "26";
        taskDao.insertSample(task);

        task.text = "Даны векторы a→ = (1; 2), b→ = (3; -6) и c→ = (4; -3). Найдите значение выражения (a→ + b→) * c→";
        task.taskImageUrl = "";
        task.answer = "28";
        taskDao.insertSample(task);

        task.text = "Вектор AB→ с началом в точке A(−22; −1) имеет координаты (8; 7). Найдите сумму координат точки B.";
        task.taskImageUrl = "";
        task.answer = "-8";
        taskDao.insertSample(task);

        task.text = "Даны векторы a→ = (3; 1), b→ = (2; -3) и c→ = (-2; 1). Найдите значение выражения (a→ - b→) * c→.";
        task.taskImageUrl = "";
        task.answer = "-30";
        taskDao.insertSample(task);

        task.text = "Вектор AB→ с началом в точке A(12; 1) имеет координаты (0; 5). Найдите абсциссу точки B.";
        task.taskImageUrl = "";
        task.answer = "12";
        taskDao.insertSample(task);

        task.text = "Вектор AB→ с началом в точке B(-22; -1) имеет координаты (8; 7). Найдите абсциссу точки A.";
        task.taskImageUrl = "";
        task.answer = "-30";
        taskDao.insertSample(task);

        task.text = "Найдите сумму координат вектора a→ + b→.";
        task.taskImageUrl = "https://i.imgur.com/XyueyAm.png";
        task.answer = "20";
        taskDao.insertSample(task);

        task.text = "Две стороны прямоугольника ABCD равны 6 и 8. Найдите длину суммы векторов AB→ и AD→.";
        task.taskImageUrl = "https://i.imgur.com/xtAR1SP.png";
        task.answer = "10";
        taskDao.insertSample(task);

        task.text = "Найдите скалярное произведение векторов a→ и b→.";
        task.taskImageUrl = "https://i.imgur.com/s9oc18G.png";
        task.answer = "40";
        taskDao.insertSample(task);

        //-----------------Задание 3-----------------//
        //-------------------------------------------//

        task.taskType = "3";
        task.text = "Объем прямоугольного параллелепипеда равен 24. Площадь одной его грани равна 12. Найдите ребро параллелепипеда, перпендикулярное этой грани.";
        task.taskImageUrl = "https://i.imgur.com/6MDvzWH.png";
        task.answer = "2";
        taskDao.insertSample(task);

        task.text = "Цилиндр и конус имеют общие основание и высоту. Найдите объем конуса, если объем цилиндра равен 150.";
        task.taskImageUrl = "https://i.imgur.com/DFFqRom.png";
        task.answer = "50";
        taskDao.insertSample(task);

        task.text = "Площадь боковой поверхности конуса в два раза больше площади основания. Найдите угол между образующей конуса и плоскостью основания. Ответ дайте в градусах.";
        task.taskImageUrl = "https://i.imgur.com/w0lewoN.png";
        task.answer = "60";
        taskDao.insertSample(task);

        task.text = "Найдите расстояние между вершинами B и C2 многогранника, изображенного на рисунке. Все двугранные углы многогранника прямые.";
        task.taskImageUrl = "https://i.imgur.com/JvScqNG.png";
        task.answer = "3";
        taskDao.insertSample(task);

        task.text = "Через среднюю линию основания треугольной призмы проведена плоскость, параллельная боковому ребру. Найдите объём этой призмы, если объём отсеченной треугольной призмы равен 5.";
        task.taskImageUrl = "https://i.imgur.com/cX3nEAM.png";
        task.answer = "20";
        taskDao.insertSample(task);

        task.text = "В цилиндрический сосуд налили 2000 см3 воды. Уровень воды при этом достигает высоты 12см. В жидкость полностью погрузили деталь. При этом уровень жидкости в сосуде поднялся на 9см. Чему равен объем детали? Ответ выразите в см3.";
        task.taskImageUrl = "https://i.imgur.com/6MekFDe.png";
        task.answer = "1500";
        taskDao.insertSample(task);

        task.text = "Объем треугольной пирамиды SABC, являющейся частью правильной шестиугольной пирамиды SABCDEF, равен 23. Найдите объем шестиугольной пирамиды.";
        task.taskImageUrl = "https://i.imgur.com/7sqqFFi.png";
        task.answer = "138";
        taskDao.insertSample(task);

        task.text = "Найдите площадь поверхности многогранника, изображенного на рисунке (все двугранные углы прямые).";
        task.taskImageUrl = "https://i.imgur.com/4p2CKCC.png";
        task.answer = "96";
        taskDao.insertSample(task);

        task.text = "Объем одного куба в 729 раз больше объема другого куба. Во сколько раз площадь поверхности первого куба больше площади поверхности второго куба?";
        task.taskImageUrl = "";
        task.answer = "81";
        taskDao.insertSample(task);

        task.text = "Радиусы трех шаров равны 1, 6 и 8. Найдите радиус шара, объем которого равен сумме их объемов.";
        task.taskImageUrl = "";
        task.answer = "9";
        taskDao.insertSample(task);

        //-----------------Задание 4-----------------//
        //-------------------------------------------//

        task.taskType = "4";
        task.text = "В случайном эксперименте симметричную монету бросают дважды. Найдите вероятность того, что оба раза монетка упадет одной и той же стороной.";
        task.taskImageUrl = "";
        task.answer = "0,5";
        taskDao.insertSample(task);

        task.text = "В сборнике билетов по биологии всего 20 билетов, в 14 из них встречается вопрос по теме \"Членистоногие\". Найдите вероятность того, что в случайно выбранном на экзамене билете школьнику не достанется вопроса по теме \"Членистоногие\".";
        task.taskImageUrl = "";
        task.answer = "0,3";
        taskDao.insertSample(task);

        task.text = "В случайном эксперименте бросают две игральные кости. Найдите вероятность того, что сумма выпавших очков равна 7. Результат округлите до сотых.";
        task.taskImageUrl = "";
        task.answer = "0,17";
        taskDao.insertSample(task);

        task.text = "Конкурс исполнителей проводится в 3 дня. Всего заявлено 55 выступлений — по одному от каждой страны. Исполнитель из России участвует в конкурсе. В первый день 33 выступления, остальные распределены поровну между оставшимися днями. Порядок выступлений определяется жеребьёвкой. Какова вероятность, что выступление представителя России состоится в третий день конкурса?";
        task.taskImageUrl = "";
        task.answer = "0,2";
        taskDao.insertSample(task);

        task.text = "Перед началом футбольного матча судья бросает монетку, чтобы определить, какая из команд начнёт игру с мячом. Команда «Сапфир» играет три матча с разными командами. Найдите вероятность того, что в этих играх «Сапфир» выиграет жребий ровно два раза.";
        task.taskImageUrl = "";
        task.answer = "0,375";
        taskDao.insertSample(task);

        task.text = "Фабрика выпускает сумки. В среднем 4 сумки из 200 имеют скрытые дефекты. Найдите вероятность того, что купленная сумка окажется без дефектов.";
        task.taskImageUrl = "";
        task.answer = "0,98";
        taskDao.insertSample(task);

        task.text = "На борту самолёта 15 мест рядом с запасными выходами и 24 мест за перегородками, разделяющими салоны. Остальные места неудобны для пассажира высокого роста. Пассажир В. высокого роста. Найдите вероятность того, что на регистрации при случайном выборе места пассажиру В. достанется удобное место, если всего в самолёте 300 мест.";
        task.taskImageUrl = "";
        task.answer = "0,13";
        taskDao.insertSample(task);

        task.text = "На конференцию приехали 4 ученых из Швеции, 4 из России и 2 из Италии. Каждый из них делает на конференции один доклад. Порядок докладов определяется жеребьёвкой. Найдите вероятность того, что четвертым окажется доклад ученого из Швеции.";
        task.taskImageUrl = "";
        task.answer = "0,4";
        taskDao.insertSample(task);

        task.text = "В сборнике билетов по географии всего 40 билетов, в 12 из них встречается вопрос по теме \"Реки и озера\". Найдите вероятность того, что в случайно выбранном на экзамене билете школьнику достанется вопрос по теме \"Реки и озера\".";
        task.taskImageUrl = "";
        task.answer = "0,3";
        taskDao.insertSample(task);

        task.text = "На олимпиаде по истории 400 участников разместили в трёх аудиториях. В первых двух удалось разместить по 150 человек, оставшихся перевели в запасную аудиторию в другом корпусе. Найдите вероятность того, что случайно выбранный участник писал олимпиаду в запасной аудитории.";
        task.taskImageUrl = "";
        task.answer = "0,25";
        taskDao.insertSample(task);

        //-----------------Задание 5-----------------//
        //-------------------------------------------//

        task.taskType = "5";
        task.text = "Агрофирма закупает куриные яйца в двух домашних хозяйствах. 85% яиц из первого хозяйства — яйца высшей категории, а из второго хозяйства — 65% яиц высшей категории. Всего высшую категорию получает 80% яиц. Найдите вероятность того, что яйцо, купленное у этой агрофирмы, окажется из первого хозяйства.";
        task.taskImageUrl = "";
        task.answer = "0,75";
        taskDao.insertSample(task);

        task.text = "В викторине участвуют 6 команд. Все команды разной силы, и в каждой встрече выигрывает та команда, которая сильнее. В первом раунде встречаются две случайно выбранные команды. Ничья невозможна. Проигравшая команда выбывает из викторины, а победившая команда играет со следующим случайно выбранным соперником. Известно, что в первых трёх играх победила команда А. Какова вероятность того, что эта команда выиграет четвёртый раунд?";
        task.taskImageUrl = "";
        task.answer = "0,8";
        taskDao.insertSample(task);

        task.text = "Игральную кость бросили два раза. Известно, что два очка не выпали ни разу. Найдите при этом условии вероятность события «сумма выпавших очков окажется равна 12».";
        task.taskImageUrl = "";
        task.answer = "0,04";
        taskDao.insertSample(task);

        task.text = "При подозрении на наличие некоторого заболевания пациента отправляют на ПЦР-тест. Если заболевание действительно есть, то тест подтверждает его в 91% случаев. Если заболевания нет, то тест выявляет отсутствие заболевания в среднем в 93% случаев. Известно, что в среднем тест оказывается положительным у 10% пациентов, направленных на тестирование.\n" +
                "\n" +
                "При обследовании некоторого пациента врач направил его на ПЦР-тест, который оказался положительным. Какова вероятность того, что пациент действительно имеет это заболевание?";
        task.taskImageUrl = "";
        task.answer = "0,325";
        taskDao.insertSample(task);

        task.text = "Чтобы поступить в институт на специальность «Переводчик», абитуриент должен набрать на ЕГЭ не менее 79 баллов по каждому из трёх предметов — математика, русский язык и иностранный язык. Чтобы поступить на специальность «Таможенное дело», нужно набрать не менее 79 баллов по каждому из трёх предметов — математика, русский язык и обществознание.\n" +
                "\n" +
                "Вероятность того, что абитуриент Б. получит не менее 79 баллов по математике, равна 0,9, по русскому языку — 0,7, по иностранному языку — 0,8 и по обществознанию — 0,9.\n" +
                "\n" +
                "Найдите вероятность того, что Б. сможет поступить хотя бы на одну из двух упомянутых специальностей.";
        task.taskImageUrl = "";
        task.answer = "0,6174";
        taskDao.insertSample(task);

        task.text = "В викторине участвуют 15 команд. Все команды разной силы, и в каждой встрече выигрывает та команда, которая сильнее. В первом раунде встречаются две случайно выбранные команды. Ничья невозможна. Проигравшая команда выбывает из викторины, а победившая команда играет со следующим случайно выбранным соперником. Известно, что в первых 8 играх победила команда А. Какова вероятность того, что эта команда выиграет девятый раунд?";
        task.taskImageUrl = "";
        task.answer = "0,9";
        taskDao.insertSample(task);

        task.text = "На диаграмме Эйлера показаны события A и B в некотором случайном эксперименте, в котором 10 равновозможных элементарных событий. Элементарные события показаны точками. Найдите Р(В|А) — условную вероятность события B при условии A.";
        task.taskImageUrl = "https://i.imgur.com/vOYA7bB.png";
        task.answer = "0,8";
        taskDao.insertSample(task);

        task.text = "Стрелок стреляет по пяти одинаковым мишеням. На каждую мишень даётся не более двух выстрелов, и известно, что вероятность поразить мишень каждым отдельным выстрелом равна 0,6. Во сколько раз вероятность события «стрелок поразит ровно три мишени» больше вероятности события «стрелок поразит ровно две мишени»?";
        task.taskImageUrl = "";
        task.answer = "5,25";
        taskDao.insertSample(task);

        task.text = "В торговом центре два одинаковых автомата продают кофе. Вероятность того, что к концу дня в автомате закончится кофе, равна 0,2. Вероятность того, что кофе закончится в обоих автоматах, равна 0,14. Найдите вероятность того, что к концу дня кофе останется в обоих автоматах.";
        task.taskImageUrl = "";
        task.answer = "0,74";
        taskDao.insertSample(task);

        task.text = "Телефон передаёт SMS-сообщение. В случае неудачи телефон делает следующую попытку. Вероятность того, что сообщение удастся передать без ошибок в каждой отдельной попытке, равна 0,5. Найдите вероятность того, что для передачи сообщения потребуется не больше трёх попыток.";
        task.taskImageUrl = "";
        task.answer = "0,875";
        taskDao.insertSample(task);

        //-----------------Задание 6-----------------//
        //-------------------------------------------//

        task.taskType = "6";
        task.text = "Решите уравнение. Если уравнение имеет более одного корня, в ответе укажите меньший из них.";
        task.taskImageUrl = "https://i.imgur.com/8yFF4xz.png";
        task.answer = "7";
        taskDao.insertSample(task);

        task.text = "Найдите корень уравнения";
        task.taskImageUrl = "https://i.imgur.com/O4wICZt.png";
        task.answer = "4,5";
        taskDao.insertSample(task);

        task.text = "Найдите корень уравнения";
        task.taskImageUrl = "https://i.imgur.com/XkLrVye.png";
        task.answer = "69";
        taskDao.insertSample(task);

        task.text = "Найдите корень уравнения. Если уравнение имеет более одного корня, в ответе запишите больший из корней.";
        task.taskImageUrl = "https://i.imgur.com/8rbkXXO.png";
        task.answer = "2";
        taskDao.insertSample(task);

        task.text = "Найдите корень уравнения";
        task.taskImageUrl = "https://i.imgur.com/5juuJ0m.png";
        task.answer = "-1";
        taskDao.insertSample(task);

        task.text = "Найдите корень уравнения";
        task.taskImageUrl = "https://i.imgur.com/uzAiGGO.png";
        task.answer = "-0,25";
        taskDao.insertSample(task);

        task.text = "Найдите корень уравнения";
        task.taskImageUrl = "https://i.imgur.com/9WKNGdF.png";
        task.answer = "-27";
        taskDao.insertSample(task);

        task.text = "Найдите корень уравнения";
        task.taskImageUrl = "https://i.imgur.com/MDxfDsH.png";
        task.answer = "30";
        taskDao.insertSample(task);

        task.text = "Найдите корень уравнения";
        task.taskImageUrl = "https://i.imgur.com/wNUa157.png";
        task.answer = "21";
        taskDao.insertSample(task);

        task.text = "Найдите корень уравнения";
        task.taskImageUrl = "https://i.imgur.com/IZHqs3u.png";
        task.answer = "-2";
        taskDao.insertSample(task);

        //-----------------Задание 7-----------------//
        //-------------------------------------------//

        task.taskType = "7";
        task.text = "Найдите значение выражения";
        task.taskImageUrl = "https://i.imgur.com/KGiwQL8.png";
        task.answer = "12";
        taskDao.insertSample(task);

        task.text = "Найдите значение выражения";
        task.taskImageUrl = "https://i.imgur.com/qXZJCpU.png";
        task.answer = "-56";
        taskDao.insertSample(task);

        task.text = "Найдите значение выражения";
        task.taskImageUrl = "https://i.imgur.com/j2wMNPf.png";
        task.answer = "18";
        taskDao.insertSample(task);

        task.text = "Найдите значение выражения";
        task.taskImageUrl = "https://i.imgur.com/KnLj89d.png";
        task.answer = "96";
        taskDao.insertSample(task);

        task.text = "Найдите значение выражения";
        task.taskImageUrl = "https://i.imgur.com/V5FndV7.png";
        task.answer = "0,6";
        taskDao.insertSample(task);

        task.text = "Найдите значение выражения";
        task.taskImageUrl = "https://i.imgur.com/JWmz43E.png";
        task.answer = "-136";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "https://i.imgur.com/e7ABYNV.png";
        task.answer = "0,04";
        taskDao.insertSample(task);

        task.text = "Найдите значение выражения";
        task.taskImageUrl = "https://i.imgur.com/a9y51eL.png";
        task.answer = "128";
        taskDao.insertSample(task);

        task.text = "Найдите значение выражения";
        task.taskImageUrl = "https://i.imgur.com/emLcpq6.png";
        task.answer = "-2";
        taskDao.insertSample(task);

        task.text = "Найдите значение выражения";
        task.taskImageUrl = "https://i.imgur.com/fI7Y4yE.png";
        task.answer = "9";
        taskDao.insertSample(task);

        //-----------------Задание 8-----------------//
        //-------------------------------------------//

        task.taskType = "8";
        task.text = "Прямая y = -5x + 8 является касательной к графику функции y = 28x^2 + bx + 15. Найдите b, учитывая, что абсцисса точки касания больше 0.";
        task.taskImageUrl = "";
        task.answer = "-33";
        taskDao.insertSample(task);

        task.text = "На рисунке изображён график функции y = f(x), определенной на интервале (-8; 3). Сколько из отмеченных точек x1,x2,x3,x4,x5,x6,x7,x8  принадлежат промежуткам убывания функции?";
        task.taskImageUrl = "https://i.imgur.com/0BkXsfm.png";
        task.answer = "4";
        taskDao.insertSample(task);

        task.text = "Материальная точка движется прямолинейно по закону, указанному на картинке (где x — расстояние от точки отсчета в метрах, t — время в секундах, измеренное с начала движения). Найдите ее скорость (в м/с) в момент времени t = 3с.";
        task.taskImageUrl = "https://i.imgur.com/DoIBj2e.png";
        task.answer = "8";
        taskDao.insertSample(task);

        task.text = "На рисунке изображён график функции y = F(x) одной из первообразных некоторой функции f(x) определённой на интервале (-2; 6) Пользуясь рисунком, определите количество решений уравнения f(x) = 0 на отрезке [-1; 5]";
        task.taskImageUrl = "https://i.imgur.com/rtTwOZZ.png";
        task.answer = "10";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "https://i.imgur.com/Y10nmc8.png";
        task.answer = "15";
        taskDao.insertSample(task);

        task.text = "На рисунке изображены график функции y = f(x) и касательная к этому графику, проведённая в точке x0. Уравнение касательной показано на рисунке. Найдите значение функции g(x) = f'(x) - f(x) + 3 в точке x0.";
        task.taskImageUrl = "https://i.imgur.com/WeMZW6F.png";
        task.answer = "0,75";
        taskDao.insertSample(task);

        task.text = "На рисунке изображен график функции y = f(x) и отмечены точки −2, −1, 1, 4. В какой из этих точек значение производной наименьшее? В ответе укажите эту точку.";
        task.taskImageUrl = "https://i.imgur.com/KMAW28H.png";
        task.answer = "4";
        taskDao.insertSample(task);

        task.text = "Материальная точка движется прямолинейно по закону, указанному на рисунке, где х — расстояние от точки отсчёта (в метрах), t — время движения (в секундах). Найдите её скорость (в метрах в секунду) в момент времени t = 6с.";
        task.taskImageUrl = "https://i.imgur.com/xgkpXlo.png";
        task.answer = "72";
        taskDao.insertSample(task);

        task.text = "На рисунке изображён график функции y = f(x) и касательная к нему в точке с абсциссой x0. Найдите значение производной функции f(x) в точке x0";
        task.taskImageUrl = "https://i.imgur.com/gh5CzdY.png";
        task.answer = "-2";
        taskDao.insertSample(task);

        task.text = "На рисунке изображен график функции y = f(x) определенной на интервале (-1; 12). Определите количество целых точек, в которых производная функции отрицательна.";
        task.taskImageUrl = "https://i.imgur.com/C5QgWYK.png";
        task.answer = "3";
        taskDao.insertSample(task);

        //-----------------Задание 9-----------------//
        //-------------------------------------------//

        task.taskType = "9";
        task.text = "Два мотоциклиста стартуют одновременно в одном направлении из двух диаметрально противоположных точек круговой трассы, длина которой равна 14 км. Через сколько минут мотоциклисты поравняются в первый раз, если скорость одного из них на 21 км/ч больше скорости другого?";
        task.taskImageUrl = "";
        task.answer = "20";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "https://i.imgur.com/vOLtNjv.png";
        task.answer = "0,02";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "https://i.imgur.com/XFb6B3u.png";
        task.answer = "2";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "https://i.imgur.com/Ox304hh.png";
        task.answer = "0,3";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "https://i.imgur.com/4ai5sF2.png";
        task.answer = "1";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "https://i.imgur.com/axMcPn8.png";
        task.answer = "20";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "https://i.imgur.com/vwbVL27.png";
        task.answer = "3,5";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "https://i.imgur.com/pjI76kD.png";
        task.answer = "35";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "https://i.imgur.com/bZ8RdqE.png";
        task.answer = "6000";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "https://i.imgur.com/s189aw9.png";
        task.answer = "30";
        taskDao.insertSample(task);

        //-----------------Задание 10-----------------//
        //-------------------------------------------//

        task.taskType = "10";
        task.text = "Два велосипедиста одновременно отправились в 240-километровый пробег. Первый ехал со скоростью, на 1 км/ч большей, чем скорость второго, и прибыл к финишу на 1 час раньше второго. Найти скорость велосипедиста, пришедшего к финишу первым. Ответ дайте в км/ч.";
        task.taskImageUrl = "";
        task.answer = "16";
        taskDao.insertSample(task);

        task.text = "Смешали некоторое количество 19-процентного раствора некоторого вещества с таким же количеством 17-процентного раствора этого вещества. Сколько процентов составляет концентрация получившегося раствора?";
        task.taskImageUrl = "";
        task.answer = "18";
        taskDao.insertSample(task);

        task.text = "В помощь садовому насосу, перекачивающему 9 литров воды за 1 минуту, подключили второй насос, перекачивающий тот же объем воды за 2 минуты. Сколько минут эти два насоса должны работать совместно, чтобы перекачать 54 литра воды?";
        task.taskImageUrl = "";
        task.answer = "4";
        taskDao.insertSample(task);

        task.text = "Моторная лодка прошла против течения реки 255 км и вернулась в пункт отправления, затратив на обратный путь на 2 часа меньше. Найдите скорость лодки в неподвижной воде, если скорость течения равна 1 км/ч. Ответ дайте в км/ч.";
        task.taskImageUrl = "";
        task.answer = "16";
        taskDao.insertSample(task);

        task.text = "Из одной точки круговой трассы, длина которой равна 6 км, одновременно в одном направлении стартовали два автомобиля. Скорость первого автомобиля равна 63 км/ч, и через 45 минут после старта он опережал второй автомобиль на один круг. Найдите скорость второго автомобиля. Ответ дайте в км/ч.";
        task.taskImageUrl = "";
        task.answer = "55";
        taskDao.insertSample(task);

        task.text = "Компания «Альфа» начала инвестировать средства в перспективную отрасль в 2001 году, имея капитал в размере 4000 долларов. Каждый год, начиная с 2002 года, она получала прибыль, которая составляла 100% от капитала предыдущего года. А компания «Бета» начала инвестировать средства в другую отрасль в 2004 году, имея капитал в размере 4500 долларов, и, начиная с 2005 года, ежегодно получала прибыль, составляющую 200% от капитала предыдущего года. На сколько долларов капитал одной из компаний был больше капитала другой к концу 2007 года, если прибыль из оборота не изымалась?";
        task.taskImageUrl = "";
        task.answer = "134500";
        taskDao.insertSample(task);

        task.text = "Завод получил заказ на партию штампованных деталей. Один автомат может отштамповать все детали за 19 часов. Через 1 час после того, как первый автомат начал штамповать детали, начал работу второй такой же автомат, и оставшиеся детали были распределены между двумя автоматами поровну. Сколько всего часов потребовалось на выполнение этого заказа?";
        task.taskImageUrl = "";
        task.answer = "10";
        taskDao.insertSample(task);

        task.text = "Из пункта A круговой трассы выехал велосипедист. Через 30 минут он еще не вернулся в пункт А и из пункта A следом за ним отправился мотоциклист. Через 10 минут после отправления он догнал велосипедиста в первый раз, а еще через 44 минуты после этого догнал его во второй раз. Найдите скорость мотоциклиста, если длина трассы равна 33 км. Ответ дайте в км/ч.";
        task.taskImageUrl = "";
        task.answer = "60";
        taskDao.insertSample(task);

        task.text = "От пристани  к пристани B, расстояние между которыми равно 154 км, отправился с постоянной скоростью первый теплоход, а через 3 часа после этого следом за ним со скоростью на 3 км/ч большей, отправился второй. Найдите скорость первого теплохода, если в пункт B оба теплохода прибыли одновременно. Ответ дайте в км/ч.";
        task.taskImageUrl = "";
        task.answer = "11";
        taskDao.insertSample(task);

        task.text = "Первую треть трассы автомобиль ехал со скоростью 60 км/ч, вторую треть  — со скоростью 90 км/ч, а последнюю  — со скоростью 45 км/ч. Найдите среднюю скорость автомобиля на протяжении всего пути. Ответ дайте в км/ч.";
        task.taskImageUrl = "";
        task.answer = "60";
        taskDao.insertSample(task);

        //-----------------Задание 11-----------------//
        //-------------------------------------------//

        task.taskType = "11";
        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        //-----------------Задание 12-----------------//
        //-------------------------------------------//

        task.taskType = "12";
        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.text = "";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        //-----------------Задание 13-----------------//
        //-------------------------------------------//

        task.taskType = "13";
        task.taskImageUrl = "https://i.imgur.com/6m59vzH.png";
        task.answer = "https://i.imgur.com/OeOppGi.png";
        taskDao.insertSample(task);

        task.taskImageUrl = "https://i.imgur.com/94qcJV9.png";
        task.answer = "https://i.imgur.com/SxGPTbK.png";
        taskDao.insertSample(task);

        task.taskImageUrl = "https://i.imgur.com/KstTCdd.png";
        task.answer = "https://i.imgur.com/E21K0Om.png";
        taskDao.insertSample(task);

        task.taskImageUrl = "https://i.imgur.com/eAbbC7z.png";
        task.answer = "https://i.imgur.com/wYApcOc.png";
        taskDao.insertSample(task);

        task.taskImageUrl = "https://i.imgur.com/ih0oq6y.png";
        task.answer = "https://i.imgur.com/aRaFGYO.png";
        taskDao.insertSample(task);

        task.taskImageUrl = "https://i.imgur.com/t9yTDhY.png";
        task.answer = "https://i.imgur.com/RIupmay.png";
        taskDao.insertSample(task);

        task.taskImageUrl = "https://i.imgur.com/1Gp3zbI.png";
        task.answer = "https://i.imgur.com/5eO9MAf.png";
        taskDao.insertSample(task);

        task.taskImageUrl = "https://i.imgur.com/WIU54cc.png";
        task.answer = "https://i.imgur.com/OuWpO6K.png";
        taskDao.insertSample(task);

        task.taskImageUrl = "https://i.imgur.com/lkS22Lm.png";
        task.answer = "https://i.imgur.com/izQnGUb.png";
        taskDao.insertSample(task);

        task.taskImageUrl = "https://i.imgur.com/Lmej7Ta.png";
        task.answer = "https://i.imgur.com/juWI6Rh.png";
        taskDao.insertSample(task);

        //-----------------Задание 14-----------------//
        //--------------------------------------------//

        task.taskType = "14";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        //-----------------Задание 15-----------------//
        //--------------------------------------------//

        task.taskType = "15";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        //-----------------Задание 16-----------------//
        //--------------------------------------------//

        task.taskType = "16";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        //-----------------Задание 17-----------------//
        //--------------------------------------------//

        task.taskType = "17";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        //-----------------Задание 18-----------------//
        //--------------------------------------------//

        task.taskType = "18";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        //-----------------Задание 19-----------------//
        //--------------------------------------------//

        task.taskType = "19";
        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);

        task.taskImageUrl = "";
        task.answer = "";
        taskDao.insertSample(task);
    }
}
