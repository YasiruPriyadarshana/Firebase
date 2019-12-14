package com.wonder.firebaset3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_config {
    private Context mContext;
    private BookAdapter mBookAdapter;

    public  void  setConfig(RecyclerView recyclerView, Context context, List<Book> books, List<String> keys){
        mContext=context;
        mBookAdapter = new BookAdapter(books, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBookAdapter);
    }

    class BookItemView extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mAuthor;
        private TextView mISBN;
        private TextView mCategory;

        private String key;

        private  BookItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.book_list_item, parent, false));

            mTitle = (TextView) itemView.findViewById(R.id.textView);
            mAuthor = (TextView) itemView.findViewById(R.id.book_author);
            mISBN = (TextView) itemView.findViewById(R.id.book_isbn);
            mCategory = (TextView) itemView.findViewById(R.id.book_catagory);

        }
        public void  bind(Book book,String key){
            mTitle.setText(book.getTitle());
            mAuthor.setText(book.getAuthor());
            mCategory.setText(book.getCategory_name());
            mISBN.setText(book.getIsbn());
            this.key =key;
        }


    }
    class BookAdapter extends  RecyclerView.Adapter<BookItemView>{
        private List<Book> mBookList;
        private List<String> mkeys;


        public BookAdapter(List<Book> mBookList, List<String> mkeys) {
            this.mBookList = mBookList;
            this.mkeys = mkeys;
        }
        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookItemView(parent);
        }

        @NonNull
        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {
            holder.bind(mBookList.get(position), mkeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }


    }
}
