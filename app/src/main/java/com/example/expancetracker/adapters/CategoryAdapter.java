package com.example.expancetracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expancetracker.R;
import com.example.expancetracker.databinding.SampleCatagoryItemBinding;
import com.example.expancetracker.models.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    ArrayList<Category>categories;
    public interface CategoryClickListener{
        void onCategoryClicked(Category category);

    }

    CategoryClickListener categoryClickListener;
    public CategoryAdapter(Context context, ArrayList<Category> categories, CategoryClickListener categoryClickListener){
        this.context =context;
        this.categories = categories;
        this.categoryClickListener = categoryClickListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_catagory_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        Category category= categories.get(position);
        holder.binding.categoryText.setText(category.getCategoryName());
        holder.binding.categoryicon.setImageResource(category.getCategoryImage());

        holder.binding.categoryicon.setBackgroundTintList(context.getColorStateList(category.getCategoryColor()));
        holder.itemView.setOnClickListener(c-> {
            categoryClickListener.onCategoryClicked(category);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        SampleCatagoryItemBinding binding;
        public CategoryViewHolder(@NonNull View itemView) {

            super(itemView);
            binding =SampleCatagoryItemBinding.bind(itemView);
        }
    }
}
