package com.wxy.mysolr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MySolrJDemoCUD {
    /**
     * 需求： 添加数据到solr MyCore1
     * 当前处于java程序，solr MyCore1交由tomcat启动的solr服务器管理
     * solr对外提供接口，用户通过http请求调用接口，进行数据的CRUD
     * @throws IOException
     * @throws SolrServerException
     */
    @Test
    public void addDateToSolr() throws SolrServerException, IOException {
        String url = "http://localhost:8080/solr/MyCore1";
        //solrj连接对象
        HttpSolrServer server = new HttpSolrServer(url);
        SolrInputDocument document = new SolrInputDocument();
        // 新增数据，数据封装对象 SolrInputDocument  提供了addField("字段名称", "字段值")设置数据
        document.addField("id", 3);
        document.addField("name_ik", "ES介绍");
        document.addField("title_ik", "ES是独立的开源的企业级搜索服务器");
        List<SolrInputDocument> list = new ArrayList<SolrInputDocument>();
        list.add(document);
        //添加数据
        server.add(list);
        //提交数据
        server.commit();
    }

    @Test
    public void addJavaBeanToCore() throws IOException, SolrServerException{
        String url = "http://localhost:8080/solr/MyCore1";
        HttpSolrServer server = new HttpSolrServer(url);
        //同时新增多条数据
        List<ProductCopy> list = new ArrayList<>();
        for(int i = 1;i<9;i++){
            ProductCopy productCopy = new ProductCopy();
            productCopy.setId(""+i);
            productCopy.setName("产品"+i+"号");
            productCopy.setPrice(Float.valueOf(""+i));
            ArrayList<String> titleList = new ArrayList<>();
            titleList.add("产品title第一个");
            titleList.add("产品title第二个");
            productCopy.setTitle(titleList);
            list.add(productCopy);
        }

        //直接添加javaBean  添加单条数据
//		server.addBean(user);

        server.addBeans(list);
        //提交
        server.commit();
    }

    /**
     * 更新solr core中的数据
     * 注意： solr中并没有更新数据的方法，没有更新这个概念
     *  更新是怎么回事？
     *   ==》 通过唯一字段来判断 schema.xml中定义了uniqueKey就是唯一字段
     *   如果新增数据的唯一键已经存在，新增时会直接全覆盖
     *   如果新增数据时唯一键不存在，直接新增
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void updateSolrCore() throws IOException, SolrServerException{
        String url = "http://localhost:8080/solr/MyCore1";
        HttpSolrServer server = new HttpSolrServer(url);
        Product product = new Product();
        product.setId("5");
        product.setName("张飞手办");
        product.setTitle("一夫当关黑张飞");
        server.addBean(product);
        server.commit();
    }

    /**
     * 删除solr core中的数据
     * @throws IOException
     * @throws SolrServerException
     *
     */
    @Test
    public void deleteById() throws SolrServerException, IOException{
        String url = "http://localhost:8080/solr/MyCore1";
        HttpSolrServer server = new HttpSolrServer(url);
        //根据id删除对应的数据
        server.deleteById("7");
        server.commit();
    }

    /**
     * 根据id的集合同时删除多条数据
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void deleteByIds() throws SolrServerException, IOException{
        String url = "http://localhost:8080/solr/MyCore1";
        HttpSolrServer server = new HttpSolrServer(url);
        //根据id删除对应的数据
        List<String> ids = new ArrayList<String>();
        ids.add("1");
        ids.add("8");
        //根据ids删除指定的数据
        server.deleteById(ids);
        server.commit();
    }

    /**
     * 根据查询条件删除
     * @throws SolrServerException
     * @throws IOException
     */
    @Test
    public void deleteByQuery() throws SolrServerException, IOException{
        String url = "http://localhost:8080/solr/MyCore1";
        HttpSolrServer server = new HttpSolrServer(url);
        //删除了所有数据，能够成功执行    安全越界  慎用
        String query = "*:*";

        //根据指定的查询条件删除  删除所有匹配的结果
//        String query = "title:聪明";
        server.deleteByQuery(query);
        server.commit();
    }
}
