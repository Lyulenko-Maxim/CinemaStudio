package com.example.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private List<Review> reviews;

    public ReviewAdapter(Context context, List<Review> reviews) {
        this.reviews = reviews;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.review_item, parent, false);
        return new ReviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.nameText.setText(review.getName());
        holder.textText.setText(review.getText());
        Picasso.get().load(review.getImageURL()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return reviews.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameText;
        final TextView textText;
        final ImageView imageView;
        ViewHolder(View view) {
            super(view);
            nameText = view.findViewById(R.id.textName);
            textText = view.findViewById(R.id.textD);
            imageView = view.findViewById(R.id.imageView);
        }
    }
}
