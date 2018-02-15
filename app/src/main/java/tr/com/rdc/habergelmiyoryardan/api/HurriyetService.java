package tr.com.rdc.habergelmiyoryardan.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import tr.com.rdc.habergelmiyoryardan.model.Article;

/**
 * Created by enesignecirdc on 09/02/2018.
 */

public interface HurriyetService {
    @GET("v1/articles")
    Call<List<Article>> listArticles(@Query("apikey") String apikey);
    @GET("v1/articles/{id}")
    Call<Article> getArticle(@Path("id") String id, @Query("apikey") String apikey);
}
