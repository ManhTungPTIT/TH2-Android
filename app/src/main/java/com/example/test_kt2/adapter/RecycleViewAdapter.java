package com.example.test_kt2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_kt2.R;
import com.example.test_kt2.model.Book;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{
    private List<Book> list;
    private ItemListener itemListener;
    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<Book> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Book getBook(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,parent,false);

        return new HomeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Book book = list.get(position);
        holder.name.setText(book.getName());
        holder.author.setText(book.getAuthor());
        holder.day.setText(book.getPublishDate());
        holder.publisher.setText(book.getPublisher());
        holder.price.setText(book.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name,author,day,publisher,price;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.tvName1);
            author = view.findViewById(R.id.tvAuthor);
            day = view.findViewById(R.id.tvPublishDate);
            publisher = view.findViewById(R.id.tvPublisher);
            price = view.findViewById(R.id.tvPrice);
            view.setOnClickListener(this);
        }

        public void bind(Book book){
            name.setText(book.getName());
            author.setText(book.getAuthor());
            day.setText(book.getPublishDate());
            publisher.setText(book.getPublisher());
            price.setText(book.getPrice());

        }

        @Override
        public void onClick(View view) {
            if(itemListener != null)
                itemListener.itemClick(view,getAdapterPosition());
        }
    }

    public interface ItemListener{
        void itemClick(View view, int position);
    }
}
