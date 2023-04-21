package com.example.test_kt2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.test_kt2.dal.SQLiteHelper;
import com.example.test_kt2.model.Book;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name,author,day,publisher,price;
    Button btUpdate,btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    private void initView(){
        name = findViewById(R.id.edName);
        author = findViewById(R.id.edAuthor);
        day = findViewById(R.id.edDay);
        publisher = findViewById(R.id.edPublisher);
        price = findViewById(R.id.edPrice);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
    }

    @Override
    public void onClick(View view) {
        if (view == btCancel){
            finish();
        }
        if(view == btUpdate){
            String n = name.getText().toString();
            String a = author.getText().toString();
            String d = day.getText().toString();
            String p = publisher.getText().toString();
            String pr = price.getText().toString();
            if( (!n.isEmpty() || !a.isEmpty() || !d.isEmpty() || !p.isEmpty()) && pr.matches("\\d+") ){
                Book b = new Book(n,a,d,p,pr);
                SQLiteHelper db = new SQLiteHelper(this);
                db.addBook(b);
                finish();
            }
        }
    }
}