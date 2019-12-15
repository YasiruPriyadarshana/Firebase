package com.wonder.firebaset3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class NewBookActivity extends AppCompatActivity {

    private EditText mAuthor_edittxt;
    private EditText mTitle_edittxt;
    private EditText mISBN_edittxt;
    private Spinner mBookCategory_spinner;
    private Button mAdd_btn;
    private Button mBack_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);
        mAuthor_edittxt = (EditText)findViewById(R.id.author_txt);
        mTitle_edittxt = (EditText)findViewById(R.id.title_txt);
        mISBN_edittxt = (EditText)findViewById(R.id.isbn_txt);
        mBookCategory_spinner = (Spinner)findViewById(R.id.category_spin);

        mAdd_btn = (Button)findViewById(R.id.update_btn);
        mBack_btn = (Button)findViewById(R.id.back_btn);

        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book =new Book();
                book.setAuthor(mAuthor_edittxt.getText().toString());
                book.setTitle(mTitle_edittxt.getText().toString());
                book.setIsbn(mISBN_edittxt.getText().toString());
                book.setCategory_name(mBookCategory_spinner.getSelectedItem().toString());

                new FirebaseDatabaseHelper().addBook(book, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {

                    }

                    @Override
                    public void DataInserted() {
                        Toast.makeText(NewBookActivity.this, "The book record has been inserted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();return;
            }
        });
    }
}
