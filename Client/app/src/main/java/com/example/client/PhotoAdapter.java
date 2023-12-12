package com.example.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
private final LayoutInflater inflater;
private List<Photo> photos;

public PhotoAdapter(Context context, List<Photo> photos) {
        this.photos = photos;
        this.inflater = LayoutInflater.from(context);
        }

@Override
public PhotoAdapter.ViewHolder onCreateViewHolder(
        ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.photo_item, parent, false);
        return new PhotoAdapter.ViewHolder(view);
        }


@Override
public void onBindViewHolder(PhotoAdapter.ViewHolder holder, int position) {
        Photo photo = photos.get(position);
        byte[] img = photo.getPhoto();
        if (img != null) {
            Log.d(img.toString(), "DFSf");
            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            holder.image1.setImageBitmap(bitmap);
        }
}

@Override
public int getItemCount() {
        return photos.size();
        }
public static class ViewHolder extends RecyclerView.ViewHolder {

    final ImageView image1;

    ViewHolder(View view) {
        super(view);
        image1 = view.findViewById(R.id.image1);
    }
}
}
