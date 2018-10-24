package com.wxy.mysolr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySolrJDemoR {
    @Test
    public void searchSolrCore() throws IOException, SolrServerException {
        String url = "http://localhost:8080/solr/MyCore1";
        HttpSolrServer server = new HttpSolrServer(url);
        //字段名：参数值
//		SolrQuery query = new SolrQuery("title:互*");
        //布尔搜索
//		SolrQuery query = new SolrQuery("title:solr OR title:iphone");
        //子表达式查询
//		SolrQuery query = new SolrQuery("(title:solr OR title:聪明) AND (name:产品)");
        //相似度搜索，最大允许编辑次数为2次
//		SolrQuery query = new SolrQuery("iphon~1");
        //数字范围搜索  包含起始值和结束值
        SolrQuery query = new SolrQuery("id:[1 TO 7]");
        //根据id字段倒叙排列
        query.setSort("id", SolrQuery.ORDER.desc);
        QueryResponse response = server.query(query);
        //解析搜索的结果集
		SolrDocumentList list = response.getResults();
		for(SolrDocument sd : list){
			System.out.println("id===="+sd.get("id"));
			System.out.println("name===="+sd.get("name"));
			System.out.println("title===="+sd.get("title"));
			System.out.println("===========next===========");
		}
        //解析搜索结果集的方式二  ：直接返回javaBean的方式
        List<ProductCopy> listCopy = response.getBeans(ProductCopy.class);
        for(ProductCopy productCopy : listCopy){
            System.out.println(productCopy);
        }
    }


    /**
     * 搜索结果集的分页
     * 分页关键： 需要页码和每页显示条数
     *   获取结果集时，指定开始位置start和结束位置end
     * @throws SolrServerException
     */
    @Test
    public void searchSolrCorePageList() throws SolrServerException{
        String url = "http://localhost:8080/solr/MyCore1";
        HttpSolrServer server = new HttpSolrServer(url);
        //页码
        Integer page = 1;
        //每页显示条数
        Integer pageSize = 15;
        Integer start = (page -1 ) * pageSize;
        Integer end = page * pageSize;
        //字段名：参数值
        SolrQuery query = new SolrQuery("title:产品");
        //根据id字段倒叙排列
        query.setSort("id", SolrQuery.ORDER.desc);
        query.setStart(start);
        query.setRows(end);

        //开启高亮显示
        query.setHighlight(true);
        //高亮显示的标签前缀
        query.setHighlightSimplePre("<em color='red'>");
        //高亮显示的标签后缀
        query.setHighlightSimplePost("</em>");
        //添加需要高亮显示的字段
        query.addHighlightField("title");
        query.addHighlightField("name");
        QueryResponse response = server.query(query);
        //高亮显示的结果集
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        //遍历的方式获取高亮的结果集
        for(String key : highlighting.keySet()){
            Map<String, List<String>> map = highlighting.get(key);
            for(String key2 : map.keySet()){
                List<String> list = map.get(key2);
//                for(String str : list){
//                    System.out.println(str);
//                }
                System.out.println(list);
            }
        }

        //解析搜索结果集的方式二  ：直接返回javaBean的方式
        List<ProductCopy> list = response.getBeans(ProductCopy.class);
        for(ProductCopy u : list){
            String highlightName = highlighting.get(u.getId()).get("name").get(0);
//			System.out.println(highlightName);
            u.setName(highlightName);

            List<String> highLightTitle = highlighting.get(u.getId()).get("title");
//            String highLightTitle = highlighting.get(u.getId()).get("title").get(0);
            u.setTitle(highLightTitle);

            System.out.println(u);
        }
    }
}
