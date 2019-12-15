package com.wonder.firebaset3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class BookDetailsActivity extends AppCompatActivity {

    private EditText mAuthor_edittxt;
    private EditText mTitle_edittxt;
    private EditText mISBN_edittxt;
    private Spinner mBookCategory_spinner;
    private Button mUpdate_btn;
    private Button mDelete_btn;
    private Button mBack_btn;

    private String key;
    private String author;
    private String title;
    private String category;
    private String isbn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        key=getIntent().getStringExtra("key");
        author=getIntent().getStringExtra("author");
        title=getIntent().getStringExtra("title");
        category=getIntent().getStringExtra("category");
        isbn=getIntent().getStringExtra("isbn");

        mAuthor_edittxt = (EditText)findViewById(R.id.author2_txt);
        mTitle_edittxt = (EditText)findViewById(R.id.title2_txt);
        mISBN_edittxt = (EditText)findViewById(R.id.isbn2_txt);
        mBookCategory_spinner = (Spinner)findViewById(R.id.category2_spin);

        mBookCategory_spinner.setSelection(getIndex_spinnerItem(mBookCategory_spinner,category));
        mISBN_edittxt.setText(isbn);
        mAuthor_edittxt.setText(author);
        mTitle_edittxt.setText(title);

        mUpdate_btn = (Button)findViewById(R.id.update_btn);
        mDelete_btn = (Button)findViewById(R.id.delete_btn);
        mBack_btn = (Button)findViewById(R.id.back2_btn);

        mUpdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setTitle(mTitle_edittxt.getText().toString());
                book.setAuthor(mAuthor_edittxt.getText().toString());
                book.setIsbn(mISBN_edittxt.getText().toString());
                book.setCategory_name(mBookCategory_spinner.getSelectedItem().toString());

                new FirebaseDatabaseHelper().UpdateBook(key, book, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Book> books, List<String> keys) {
                        
                    }

                    @Override
                    public void DataInserted() {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(BookDetailsActivity.this, "Book record has been update successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        mDelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new FirebaseDatabaseHelper().deleteBook(key, new FirebaseDatabaseHelper.DataStatus() {
                   @Override
                   public void DataIsLoaded(List<Book> books, List<String> keys) {
                       
                   }

                   @Override
                   public void DataInserted() {

                   }

                   @Override
                   public void DataIsInserted() {

                   }

                   @Override
                   public void DataIsUpdated() {

                   }

                   @Override
                   public void DataIsDeleted() {
                       Toast.makeText(BookDetailsActivity.this, "Book record has been delete record successfully", Toast.LENGTH_SHORT).show();
                        finish();return;
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


    private int getIndex_spinnerItem(Spinner spinner,String item){
        int index = 0;
        for (int i=0; i<spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).equals(item)){
                index=i;
                break;
            }
        }

        return index;
    }
}
