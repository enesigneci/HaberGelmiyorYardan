package tr.com.rdc.habergelmiyoryardan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tr.com.rdc.habergelmiyoryardan.api.HurriyetService;
import tr.com.rdc.habergelmiyoryardan.model.Article;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.newsRecycler)
    RecyclerView newsRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.hurriyet.com.tr/").addConverterFactory(GsonConverterFactory.create()).build();
        final HurriyetService service=retrofit.create(HurriyetService.class);
        Call<List<Article>> call=service.listArticles("4b987be8833a4ba8976a784c72b5b18d");
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, final Response<List<Article>> response) {

                ArticlesAdapter articlesAdapter = new ArticlesAdapter(response.body(), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int itemPosition=newsRecycler.getChildLayoutPosition(v);
                        Article article=response.body().get(itemPosition);
                        service.getArticle(article.getId(),"4b987be8833a4ba8976a784c72b5b18d").enqueue(new Callback<Article>() {
                            @Override
                            public void onResponse(Call<Article> call, Response<Article> response) {
                                Log.d("Article", response.body().getDescription());
                            }

                            @Override
                            public void onFailure(Call<Article> call, Throwable t) {

                            }
                        });
                    }
                });
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                newsRecycler.setLayoutManager(mLayoutManager);
                newsRecycler.setItemAnimator(new DefaultItemAnimator());
                newsRecycler.setAdapter(articlesAdapter);
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });

    }
}
