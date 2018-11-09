package com.zt.poseidon.common.search.elasticsearch;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * @Author: ZhouTian
 * @Description:
 * @Date: 2018/8/27
 */
public class EsService {

    @Autowired
    private JestClient jestClient;


    public void addIndex(){
        Index index=new Index.Builder("").index("").type("").build();
        try {
            jestClient.execute(index);
        } catch (IOException e) {


        }
    }



    public JestResult search(Consumer<? super QueryBuilder> consumer,String... args) {
        String index=null;
        String indexType=null;
        if (null != args && args.length >= 2){
            index=args[0];
            indexType=args[1];
        }


        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("",""));
        Search search=new Search.Builder(searchSourceBuilder.toString()).addIndex(index)
                    .addType(indexType).build();
        try {
            JestResult result = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
