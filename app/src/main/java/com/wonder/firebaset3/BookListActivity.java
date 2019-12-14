package com.wonder.firebaset3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class BookListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        new FirebaseDatabaseHelper().readbooks(new FirebaseDatabaseHelper.DataStatus() {

            @Override
            public void DataIsLoaded(List<Book> books, List<String> keys) {
                findViewById(R.id.loading_book_pb).setVisibility(View.GONE);
                new RecyclerView_config().setConfig(mRecyclerView, BookListActivity.this, books, keys);
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

            }
        });
    }
}
