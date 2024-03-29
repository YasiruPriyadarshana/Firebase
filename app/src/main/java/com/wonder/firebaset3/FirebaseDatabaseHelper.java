package com.wonder.firebaset3;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefarenceBooks;
    private List<Book> books= new ArrayList<>();

    public interface  DataStatus{
        void DataIsLoaded(List<Book> books, List<String> keys);
        void DataInserted();
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mRefarenceBooks= mDatabase.getReference("books");
    }

    public  void  readbooks(final DataStatus dataStatus){
        mRefarenceBooks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Book book = keyNode.getValue(Book.class);
                    books.add(book);
                }
                dataStatus.DataIsLoaded(books,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addBook(Book book,final DataStatus dataStatus){
       String key = mRefarenceBooks.push().getKey();
        mRefarenceBooks.child(key).setValue(book)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataInserted();
                    }
                });
    }

    public void  UpdateBook(String key,Book book,final DataStatus dataStatus){
        mRefarenceBooks.child(key).setValue(book)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }

    public void deleteBook(String key,final DataStatus dataStatus){
        mRefarenceBooks.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
