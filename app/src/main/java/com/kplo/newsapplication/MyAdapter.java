package com.kplo.newsapplication;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<NewsData> mDataset;
    private static View.OnClickListener onClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView TextView_title;
        public TextView TextView_content;
        public SimpleDraweeView ImageView_title;
        public View rootView;
        public MyViewHolder(View v) {
            super(v);
            TextView_title = v.findViewById(R.id.TextView_title);
            TextView_content = v.findViewById(R.id.TextView_content);
            ImageView_title = v.findViewById(R.id.ImageView_title);
            rootView = v;

            v.setClickable(true);
            v.setEnabled(true);
            v.setOnClickListener(onClickListener);

        }
    }

    public MyAdapter(List<NewsData> myDataset, Context context, View.OnClickListener onClick) {
        mDataset = myDataset;
        onClickListener = onClick;
        Fresco.initialize(context);
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        NewsData news = mDataset.get(position);
        holder.TextView_title.setText(news.getTitle());

        String content = news.getContent();

        if(content != null && content.length() > 0) {
            holder.TextView_content.setText(content);
        }
        else {
            holder.TextView_content.setText("-");
        }

        Uri uri = Uri.parse(news.getUrlToImage());

        holder.ImageView_title.setImageURI(uri);

        holder.rootView.setTag(position);

    }

    @Override
    public int getItemCount() {
        if(mDataset == null)
            return 0;
        return mDataset.size();

        //삼항 연산자
//        return mDataset == null ? 0 :  mDataset.size();
    }

    public NewsData getNews(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }

}