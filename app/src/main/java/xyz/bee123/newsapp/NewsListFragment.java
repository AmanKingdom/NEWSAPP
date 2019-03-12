package xyz.bee123.newsapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsListFragment extends Fragment {
    //模拟数据存放在这里
    public List<News> news_data = new ArrayList<>();

    //判断单页双页
    private boolean isTwoPane;

    //创建模拟数据
    private void createNewsData(){
        for(int i = 0; i < 40; i++){
            StringBuffer content_temp = new StringBuffer();
            for(int j = 0; j < (new Random().nextInt(20)+1); j++){
                content_temp.append("这是内容哈哈哈");
            }
            News news_item = new News("标题"+String.valueOf(i), content_temp.toString());
            this.news_data.add(news_item);
        }
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        if(getActivity().findViewById(R.id.news_content_fragment) != null){
            isTwoPane = true;
        }else{
            isTwoPane = false;
        }
        RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerView);
        RecyclerAdapter adapter = new RecyclerAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //创建模拟数据
        createNewsData();

        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        return view;
    }


    //内部类，列表适配器
    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
            final ViewHolder holder = new ViewHolder(view);

            view.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    News news_item = news_data.get(holder.getAdapterPosition());
                    if(isTwoPane){
                        NewsContentFragment newsContentFragment = (NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news_item.getTitle(), news_item.getContent());
                    }else{
                        NewsContentActivity.actionStart(getActivity(), news_item);
                    }
                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            News news_item = news_data.get(i);
            viewHolder.title.setText(news_item.getTitle());
        }

        @Override
        public int getItemCount() {
            return news_data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView title;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.titleTextView);
            }
        }
    }
}
