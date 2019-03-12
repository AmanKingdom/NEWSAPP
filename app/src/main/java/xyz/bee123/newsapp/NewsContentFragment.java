package xyz.bee123.newsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsContentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_content, container, false);
    }
    public void refresh(String news_title, String news_content){
        TextView contentTitle = getActivity().findViewById(R.id.contentTitleTextView);
        TextView content = getActivity().findViewById(R.id.contentTextView);
        contentTitle.setText(news_title);
        content.setText(news_content);
    }
}
