# RickAndMortyApi
This app was created to handle response from https://rickandmortyapi.com/ using REST API.\
Project contains three base folders: Activity, AppObject, AsyncTask.
##
Приложение создано для того, чтобы обрабатывать ответы с https://rickandmortyapi.com/ с использованием REST API. \
Проект состоит из трёх основных папок: Activity, AppObject, AsyncTask.

## Activity
Activity folder has all activities using in this app.
##
Папка Activity содержит все activity, используемые в приложении.

## AppObject 
AppObject folder has a supportive classes such as: Parser (using for parsing different responses),
 Downloader (get String perfomance of JSON response), ACAdapter (for RecyclerView) and classes are described main objects:
CartoonPers (Character's class), Episode (Episode's class), DoubleKey(for Database).

##
Папка AppObject хранит вспомогательные классы: Parser (используется для парсинга различных ответов), Downloader (получает JSON ответ в String),
ACAdapter (для RecyclerView) и классы, описывающие основные объекты: CartoonPers (класс персонажа), Episode (класс эпизода), DoubleKey (для базы данных)

## AsyncTasks
AsyncTasks folder contains all AsyncTasks using in this app. There is one for update checking and three for each Activity.

##
Папка AsyncTasks содержит все AsyncTasks, используемые в приложении.  Для каждой Activity свой AsyncTask. Также есть дополнительный AsyncTask для проверки обновлений.



##
For cashing using Room 2.1.0 \
For pagination using Paging 2.1.2 \
For loading picture using Picasso 2.8 \
Min sdk: 21 \
Target sdk: 32 \
This app was tested on Samsung Galaxy A520-F


##
Для кеширования используется Room 2.1.0 \
Для пагинации используется Paging 2.1.2 \
Для загрузки изображений используется Picasso 2.8 \
Мин sdk: 21 \
Основное sdk: 32 \
Приложение тестировалось на Samsung Galaxy A520-F

&#169;  made by Biruckoff special for red_mad_robot \
&#169;  сделал Biruckoff специально для red_mad_robot
