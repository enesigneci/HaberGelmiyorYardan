package tr.com.rdc.habergelmiyoryardan;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import tr.com.rdc.habergelmiyoryardan.model.Article;

/**
 * Created by enesignecirdc on 09/02/2018.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.MyViewHolder> {

    private List<Article> articleList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }


    public ArticlesAdapter(List<Article> moviesList, View.OnClickListener listener) {
        this.articleList = moviesList;
        this.listener = listener;
    }

    private View.OnClickListener listener;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_row_layout, parent, false);
        itemView.setOnClickListener(listener);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.title.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
