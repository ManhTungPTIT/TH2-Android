package com.example.test_kt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.test_kt2.dal.SQLiteHelper;
import com.example.test_kt2.fragment.Fragment2;
import com.example.test_kt2.model.Book;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name,author,day,publisher,price;
    Button btUpdate,btRemove,btBack;
    private Book book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btUpdate.setOnClickListener(this);
        btBack.setOnClickListener(this);
        btRemove.setOnClickListener(this);
        Intent intent = getIntent();
        book =(Book)intent.getSerializableExtra("book");
        name.setText(book.getName());
        author.setText(book.getAuthor());
        day.setText(book.getPublishDate());
        publisher.setText(book.getPublisher());
        price.setText(book.getPrice());


    }
    private void initView(){
        name = findViewById(R.id.edName);
        author = findViewById(R.id.edAuthor);
        day = findViewById(R.id.edDay);
        publisher = findViewById(R.id.edPublisher);
        price = findViewById(R.id.edPrice);
        btUpdate = findViewById(R.id.btUpdate);
        btBack = findViewById(R.id.btBack);
        btRemove = findViewById(R.id.btRemove);

    }

    @Override
    public void onClick(View view) {
        SQLiteHelper db = new SQLiteHelper(this);
        if (view == btBack){
            finish();
        }
        if(view == btUpdate){
            String n = name.getText().toString();
            String a = author.getText().toString();
            String d = day.getText().toString();
            String p = publisher.getText().toString();
            String pr = price.getText().toString();
            if( !n.isEmpty()  && pr.matches("\\d+") ){
                int id = book.getId();
                Book b = new Book(id,n,a,d,p,pr);
                db.update(b);
                finish();
            }
        }
        if(view == btRemove){
            int id = book.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle("Thong bao xoa");
            builder.setMessage("ban co muon xoa " + book.getName() + " khong?");
            builder.setIcon(R.drawable.ic_baseline_remove_24);
            builder.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SQLiteHelper bb = new SQLiteHelper(getApplicationContext());
                    bb.delete(id);
                    finish();
                }
            });
            builder.setPositiveButton("Khong", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}