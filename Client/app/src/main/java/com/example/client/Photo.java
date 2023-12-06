package com.example.client;

import java.util.ArrayList;
import java.util.List;

public class Photo {
    private String photo;


    public Photo(String photo) {
        this.photo=photo;
    }

    public String getPhoto() {
        return photo;
    }
    private static List<Photo> photos = new ArrayList<>();
    public static List<Photo> getPhotos() {
        photos.clear();
        photos.add(new Photo("https://sun9-6.userapi.com/impg/SLjD6M2nL4_wcibsFhM49AAlHA00BnjOq15wHQ/9dNOt-Z0cgo.jpg?size=1080x1080&quality=95&sign=b4acfc1f1f7704457017a5071c5737f1&c_uniq_tag=4GlY9mV129i5GpS_3sX2R1IAHmOsagwB_qrVqidg0LY&type=album"));
        photos.add(new Photo("https://i.pinimg.com/originals/3d/a2/f3/3da2f3da060f15af369b7d0413c90562.jpg"));
        photos.add(new Photo("https://klike.net/uploads/posts/2023-01/1674798729_3-156.jpg"));
        photos.add(new Photo("https://klike.net/uploads/posts/2023-01/1674798744_3-50.jpg"));
        photos.add(new Photo("https://klike.net/uploads/posts/2023-01/1674798706_2-2.jpg"));
        return photos;
    }
}
