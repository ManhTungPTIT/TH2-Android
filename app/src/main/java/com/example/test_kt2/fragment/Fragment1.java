package com.example.test_kt2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_kt2.R;
import com.example.test_kt2.UpdateDeleteActivity;
import com.example.test_kt2.adapter.RecycleViewAdapter;
import com.example.test_kt2.dal.SQLiteHelper;
import com.example.test_kt2.model.Book;

import java.util.List;

public class Fragment1 extends Fragment implements RecycleViewAdapter.ItemListener {
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment1,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());
        Book b1 = new Book(1,"Dac nhan tam","Dale Carnegie","20/4/2023","Kim Dong","76000");
        db.addBook(b1);
        List<Book> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void itemClick(View view, int position) {
        Book book = adapter.getBook(position);
        Intent intent = new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("book",book);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
//        List<Book> list = db.getAll();
//        adapter.setList(list);
    }
}
