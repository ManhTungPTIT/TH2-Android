package com.example.test_kt2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

import java.util.ArrayList;
import java.util.List;

public class Fragment3 extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private EditText edFrom,edTo;
    private Button btSearch,btThongke;
    private RecycleViewAdapter adapter;
    private SQLiteHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment3 ,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());

        List<Book> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                List<Book> bookbyPrice = sqLiteHelper.findBookByprice(edFrom.getText().toString().trim(),
                        edTo.getText().toString().trim());
                adapter.setList(bookbyPrice);
            }
        });
        btThongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());
                List<Book> thongke =sqLiteHelper.getStatic();
                adapter.setList(thongke);
            }
        });


    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.recycleView);
        edFrom = view.findViewById(R.id.edFrom);
        edTo = view.findViewById(R.id.edTo);
        btSearch = view.findViewById(R.id.btSearch);
        btThongke = view.findViewById(R.id.btThongKe);
    }

    @Override
    public void onClick(View view) {

    }
}
