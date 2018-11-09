package com.zt.poseidon.common.search.solr;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @Author: ZhouTian
 * @Description: solr工具类
 * @Date: 2018/7/4
 */
public class SolrManage {

    private SolrClient solrClient;

    public SolrManage(SolrClient solrClient) {
        this.solrClient = solrClient;
    }

    /**
     * 增加或更新索引
     * @param collection
     * @param name
     * @param value
     * @throws IOException
     * @throws SolrServerException
     */
    public void addSolrIndex(String collection,String name,Object value) throws IOException, SolrServerException {
        if (collection==null||name==null||value==null){
            return;
        }
        SolrInputDocument doc=new SolrInputDocument();
        doc.addField(name,value);
        solrClient.add(doc);
        solrClient.commit(collection);
    }

    /**
     * 批量增加或更新索引
     * @param collection
     * @param parms
     * @throws IOException
     * @throws SolrServerException
     */
    public void batchAddSolrIndex(String collection,Map<String,Object> parms) throws IOException, SolrServerException {
        if (collection==null||parms==null||parms.isEmpty()){
            return;
        }
        SolrInputDocument doc=new SolrInputDocument();
        parms.forEach((k,v)->{
            doc.addField(k,v);
        });
        solrClient.add(doc);
        solrClient.commit(collection);
    }

    /**
     * 删除索引
     * @param collection
     * @param id
     * @throws IOException
     * @throws SolrServerException
     */
    public void deleteSolrIndex(String collection,String id) throws IOException, SolrServerException {
        if (collection==null||id==null){
            return;
        }
        solrClient.deleteById(id);
        solrClient.commit(collection);
    }

    /**
     * 查询
     * @param collection
     * @param solrQuery
     * @return
     */
    public QueryResponse search(String collection,SolrQuery solrQuery){
        if (solrQuery==null){
            return null;
        }
        QueryResponse resp=null;
        try {
            if(StringUtils.isEmpty(collection)){
                resp=solrClient.query(solrQuery);
            }else{
                resp=solrClient.query(collection,solrQuery);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    /**
     * 获取list返回结果
     * @param queryResponse
     * @param clazz
     * @return
     */
    public <T> List<T> getResultList(QueryResponse queryResponse,Class<T> clazz){
        if (null == queryResponse||null == queryResponse.getResults()||null == clazz){
            return null;
        }
        List<T> list=new ArrayList<>();
        SolrDocumentList results = queryResponse.getResults();
        results.forEach(doc->{
            T bean = null;
            try {
                bean = clazz.newInstance();
                BeanUtils.populate(bean,doc);
                list.add(bean);
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        });
        return list;
    }


    /**
     * 查询（q：关键字查询；fq：过滤查询；sort：排序；fl：返回字段）
     * @param query 关键字查询
     * @param filters 过滤字段
     * @param sorts 排序字段
     * @param fields 返回字段
     * @param pageNo 页数
     * @param pageSize  每页大小
     * @param collection 集合
     * @param clazz 返回class类型
     * @return
     */
    public <T> List<T> searchList(String query,Map<String,Object> filters, Map<String,SolrQuery.ORDER> sorts,List<String> fields
        , Integer pageNo, Integer pageSize,String collection,Class<T> clazz){
        QueryResponse queryResponse = search(query, filters, sorts, fields, pageNo, pageSize, collection);
        return getResultList(queryResponse,clazz);
    }


    public <T> List<T> searchList(String query,String collection,Class<T> clazz){
        QueryResponse queryResponse = search(query, null, null, null, null, null, collection);
        return getResultList(queryResponse,clazz);
    }

    /**
     * 查询
     * @param query
     * @param filters
     * @param sorts
     * @param fields
     * @param pageNo
     * @param pageSize
     * @param collection
     * @return
     */
    public QueryResponse search(String query,Map<String,Object> filters, Map<String,SolrQuery.ORDER> sorts,List<String> fields
            , Integer pageNo, Integer pageSize,String collection){
        SolrQuery solrQuery=new SolrQuery();
        if(StringUtils.isEmpty(query)){
            query="*:*";
        }
        solrQuery.setQuery(query);
        if(filters!=null){
            filters.forEach((k,v)->{
                solrQuery.addFilterQuery(k+":"+v);
            });
        }
        if(sorts!=null){
            sorts.forEach((k,v) ->{
                solrQuery.addSort(k,v);
            });
        }
        if(fields!=null){
            fields.forEach(field -> {
                solrQuery.addField(field);
            });
        }
        if(pageSize!=null&&pageNo!=null&& pageNo > 0){
            Integer start = (pageNo - 1) * pageSize;
            solrQuery.setStart(start);
            solrQuery.setRows(pageSize);
        }
        QueryResponse resp=search(collection,solrQuery);
        return resp;
    }

    /**
     * 查询
     * @param collection
     * @param consumer  传递SolrQuery参数，自己封装查询参数
     * @param function  传递QueryResponse参数,自己封装返回类型
     * @return
     */
    public <T> T search(String collection,Consumer<? super SolrQuery> consumer,
                                Function<? super QueryResponse,? extends  T> function){
        SolrQuery solrQuery=new SolrQuery();
        consumer.accept(solrQuery);
        QueryResponse resp=search(collection,solrQuery);
        return function.apply(resp);
    }

    public <T> List<T> searchList(String collection,Consumer<? super SolrQuery> consumer,Class<T> clazz){
        SolrQuery solrQuery=new SolrQuery();
        consumer.accept(solrQuery);
        QueryResponse resp=search(collection,solrQuery);
        return getResultList(resp,clazz);
    }


}
