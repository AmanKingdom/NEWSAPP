package xyz.bee123.newsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NewsContentActivity extends AppCompatActivity {
    public static void actionStart(Context context, News news_item){
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", news_item.getTitle());
        intent.putExtra("news_content", news_item.getContent());
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(getIntent().getStringExtra("news_title"), getIntent().getStringExtra("news_content"));
    }
}
