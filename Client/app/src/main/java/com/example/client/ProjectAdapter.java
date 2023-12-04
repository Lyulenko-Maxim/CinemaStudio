package com.example.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {


    private final LayoutInflater inflater;
    private List<Project> projects;

    public ProjectAdapter(Context context, List<Project> projects) {
        this.projects = projects;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.project_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectAdapter.ViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.nameText.setText(project.getName());
    }
    @Override
    public int getItemCount() {
        return projects.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameText;
        ViewHolder(View view) {
            super(view);
            nameText = view.findViewById(R.id.projectName);
        }
    }

}
