package com.example.client;

import java.util.ArrayList;
import java.util.List;

public class Review {

    private String name;

    private String text;

    private String imageURL;

    public Review(String name, String text, String imageURL) {
        this.name=name;
        this.text=text;
        this.imageURL=imageURL;
    }

    public String getName() {
        return name;
    }
    public String getText() {return text;}
    public String getImageURL() {return imageURL;}
    private static List<Review> reviews = new ArrayList<>();
    public static List<Review> getReviews() {
        reviews.clear();
        reviews.add(new Review("Максим Люленко", "Всем привет! Посмотрела я Фильм \"Солнце в воде\" (2023). Главная героиня молодая женщина, очнулась в больнице и ничего про себя не помнит, она пристегнута наручниками к кровати и ей кажется, что врачи что-то от нее скрывают. У фильма интересное название и начало и пожалуй это все что в нем я увидела хорошего. Фильм абсолютно не динамичный, при стандартном хронометраже...", "https://sun1-93.userapi.com/s/v1/if1/zOTOyC4CSP8VNyp6fWaLzPvzwoLPkbIqWFoSZNG3TK3tIDQZcH1Vu1gJnjHckl8IrzayhlNz.jpg?size=200x200&quality=96&crop=106,0,1260,1260&ava=1"));
        reviews.add(new Review("Егор Баранов", "Первая часть фильма О, Господи вышла ещё в 2012 и была в жанре фантазии, прошлая часть меня совершенно не зацепила и не понравилась, для меня это был просто очень странный фильм. Новая же, вторая часть...", "https://i.pinimg.com/originals/ea/29/f4/ea29f4f9cce337a195d9141efce3326e.jpg"));
        return reviews;
    }
}
