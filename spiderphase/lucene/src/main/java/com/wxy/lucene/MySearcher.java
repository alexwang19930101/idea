package com.wxy.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.document.Field.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MySearcher {
    public static final String INDEX_PATH = "E:/tmp/index";

    public static void main(String[] args) throws ParseException, IOException {


    }

    @Test
    public void indexCreate() throws IOException {
        // 创建文档对象
        Document document = new Document();
        // 添加字段，参数Field是一个接口，要new实现类的对象(StringField, TextField)
        // StringField的实例化需要3个参数：1-字段名，2-字段值，3-是否保存到文档，Store.YES存储，NO不存储
        document.add(new TextField("id", "1", Store.YES));
        // TextField：创建索引并提供分词，StringField创建索引但不分词
        document.add(new StringField("title", "谷歌地图之父跳槽FaceBook", Store.YES));

        /*
        // 创建目录对象，指定索引库的存放位置；FSDirectory文件系统；RAMDirectory内存
        Directory directory = FSDirectory.open(new File("E:\\temp\\index"));
        // 创建分词器对象
        Analyzer analyzer = new StandardAnalyzer();
        // 创建索引写入器配置对象，第一个参数版本VerSion.LATEST,第一个参数分词器
        IndexWriterConfig conf = new IndexWriterConfig(Version.LATEST, analyzer);
        // 创建索引写入器
        IndexWriter indexWriter = new IndexWriter(directory , conf);
        */
        IndexWriter indexWriter = new IndexWriter(FSDirectory.open(new File(INDEX_PATH)),
                new IndexWriterConfig(Version.LATEST,new IKAnalyzer()));
        // 向索引库写入文档对象
        indexWriter.addDocument(document);
        // 提交
        indexWriter.commit();
        // 关闭
        indexWriter.close();
    }


    // 删除索引库
    @Test
    public void deleteIndex() throws Exception {
        // 第一个参数：索引库的路径
        Directory indexDir = FSDirectory.open(new File("E:/tmp/index"));
        // 第二个参数：索引库的相关配置
        IndexWriterConfig indexConfig = new IndexWriterConfig(Version.LATEST, new StandardAnalyzer());
        // 索引写入器
        IndexWriter indexWriter = new IndexWriter(indexDir, indexConfig);

        // 删除索引库的所有内容
        indexWriter.deleteAll();

        // indexWriter.deleteDocuments(new Term("title","数据"));

        // 提交操作
        indexWriter.commit();
        // 关闭资源
        indexWriter.close();

    }

    /*
     * 使用lucene的api创建索引库 IK分词器
     */
    @Test
    public void createIndex2WithIK() throws Exception {
        List<Document> docs = new ArrayList<Document>();
        for (int i = 15; i < 30; i++) {

            // 2.创建文档
            Document doc = new Document();

            LongField idField = new LongField("id", i, Store.YES);
            // 3.指定文档的字段 三个参数：第一个：字段名称，第二个：字段的值，第三个：是否存储（存储与不存储有区别）
            TextField titleField = new TextField("title","烽火科技集团程序员"+i, Store.YES);
            TextField contentField = new TextField("content", "邮科院的研究生已经是社会精英程序员" + i, Store.YES);

            if (i == 20) {
                contentField.setBoost(1000f);
            }
            // 把字段添加到文档对象里
            doc.add(idField);
            doc.add(titleField);
            doc.add(contentField);

            // 把每一个文档对象加入集合
            docs.add(doc);
        }

        // 4.把文档写入索引库
        // 第一个参数：索引库的路径
        Directory indexDir = FSDirectory.open(new File(INDEX_PATH));
        // 第二个参数：索引库的相关配置
        IndexWriterConfig indexConfig = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
        // 索引写入器
        IndexWriter indexWriter = new IndexWriter(indexDir, indexConfig);
        // 把文档写入索引库
        indexWriter.addDocuments(docs);

        // 提交操作
        indexWriter.commit();
        // 关闭资源
        indexWriter.close();

    }

    // 单字段查询
    @Test
    public void searchIndex() throws Exception {
        // 查询解析器 第一个参数：针对哪个字段查询 第二个参数：分词器
        QueryParser queryParser = new QueryParser(Version.LATEST,"titleupdate", new IKAnalyzer());
        // 参数：你要查询的条件
        Query query = queryParser.parse("标题");

        baseSearch(query);
    }

    // 多字段查询
    @Test
    public void searchIndex2() throws Exception {
        // 查询解析器 第一个参数：针对哪个字段查询 第二个参数：分词器
        QueryParser queryParser = new MultiFieldQueryParser(Version.LATEST,new String[] { "title", "content" }, new IKAnalyzer());
        // 参数：你要查询的条件
        Query query = queryParser.parse("程序员");

        baseSearch(query);
    }


    /**
     * TermQuery : 词条搜索  单个词条的搜索，输入的内容会被当做一个完整的词条，不会再对搜索参数进行分词
     */
    @Test
    public void termQuery(){
//		Term term = new Term("查询的目标字段", "查询的参数");
        Term term = new Term("content", "程序");
        Query query = new TermQuery(term);
        try {
            baseSearch(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * WildcardQuery : 通配符搜索
     *  ?号代表单个字符，*号代表N个字符
     *
     */
    @Test
    public void wildcardQueryTest(){
        Term term = new Term("content", "?科*");
        Query query = new WildcardQuery(term);
        try {
            baseSearch(query);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * FuzzyQuery : 模糊查询
     *
     *  自动补齐或切换位置，至多两次机会
     *
     *  若大于2？查不出来
     */
    @Test
    public void fuzzyQueryTest(){
        Term term = new Term("content", "究生研");
        Query query = new FuzzyQuery(term,2);
        try {
            baseSearch(query);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
    /**
     * NumericRangeQuery:数值范围查询
     */
    @Test
    public void NumericRangeQueryTest(){
//		 NumericRangeQuery.newLongRange("搜索的目标字段", 起始范围, 结束范围, 是否包含最小, 是否包含最大);

        Query query = NumericRangeQuery.newLongRange("id", 25L, 30L,true,true);
        try {
            baseSearch(query);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * BooleanQuery : 组合搜索
     */
    @Test
    public void booleanQueryTest(){
        BooleanQuery query = new BooleanQuery();
        NumericRangeQuery<Long> rangeQuery = NumericRangeQuery.newLongRange("id", 5L, 30L, true, true);

        Term term = new Term("content", "武汉");
        FuzzyQuery fuzzyQuery = new FuzzyQuery(term);

        // 交集： A结果集must(必须)  + B结果集must(必须) = A和B之间共同的部分
        // 并集： should + should = A和B的结果集合并
        //数值范围查询   返回5——29
        query.add(rangeQuery, BooleanClause.Occur.SHOULD);
        //模糊查询  返回一条
        query.add(fuzzyQuery,BooleanClause.Occur.MUST);

        try {
            baseSearch(query);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    public static void  baseSearch(Query query) throws ParseException, IOException {
        IndexSearcher indexSearcher = null;
        try {
            IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(INDEX_PATH)));
            indexSearcher = new IndexSearcher(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*MultiFieldQueryParser queryParser = new MultiFieldQueryParser(Version.LATEST, new String[]{"title,content"}, new IKAnalyzer());
//        QueryParser queryParser = new QueryParser(Version.LATEST,"title",new IKAnalyzer());
        Query query = queryParser.parse("大数据火吗");*/

        TopDocs topDocs = indexSearcher.search(query,10);
        int totalHits = topDocs.totalHits;

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc:scoreDocs) {
            Integer docID = scoreDoc.doc;
            float score = scoreDoc.score;
            Document document = indexSearcher.doc(docID);
            System.out.println(score);
            System.out.println("搜索到的结果集id = " + document.get("id"));
            System.out.println("搜索到的结果集title = " + document.get("title"));
            System.out.println("搜索到的结果集content = " + document.get("content"));

        }
    }

    // 修改索引
    @Test
    public void updateIndex() throws Exception {
        // 第一个参数：索引库的路径
        Directory indexDir = FSDirectory.open(new File(INDEX_PATH));
        // 第二个参数：索引库的相关配置
        IndexWriterConfig indexConfig = new IndexWriterConfig(Version.LATEST, new IKAnalyzer());
        // 索引写入器
        IndexWriter indexWriter = new IndexWriter(indexDir, indexConfig);

        // 创建文档
        Document doc = new Document();
        // 创建字段
        LongField idField = new LongField("idupdate", 9L, Store.YES);
        TextField titleField = new TextField("titleupdate", "标题", Store.YES);
        StringField contentField = new StringField("contentupdate", "内容", Store.YES);

        // 把字段加到文档对象中
        doc.add(idField);
        doc.add(titleField);
        doc.add(contentField);

        // 修改索引
        indexWriter.updateDocument(new Term("title", "数据"), doc);

        // 提交操作
        indexWriter.commit();
        // 关闭资源
        indexWriter.close();
    }

    // 高亮显示
    @Test
    public void highlighterTest() throws Exception {
        // 查询解析器 第一个参数：针对哪个字段查询 第二个参数：分词器
        QueryParser queryParser = new QueryParser(Version.LATEST,"content", new IKAnalyzer());
        // 参数：你要查询的条件
        Query query = queryParser.parse("程序");

        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(INDEX_PATH)));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 格式化器
        Formatter formatter = new SimpleHTMLFormatter("<span color='red'>", "</span>");
        QueryScorer fragmentScorer = new QueryScorer(query);
        // 高亮的工具
        Highlighter highlighter = new Highlighter(formatter, fragmentScorer);

        // 查询，得到满足条件的前10条数据
        TopDocs topDocs = indexSearcher.search(query, 30);
        // 具体查到的满足条件的总记录数
        int totalHits = topDocs.totalHits;
        // System.out.println("满足条件的总记录数="+totalHits);
        // 结果集
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            // 文档的编号
            Integer docID = scoreDoc.doc;
            float score = scoreDoc.score;
            // System.out.println("文档编号="+docID+" 每一个文档的得分"+score);
            Document doc = indexSearcher.doc(docID);
            System.out.println(doc.get("id") + " " + doc.get("title") + " " + doc.get("content"));

            // 针对content这个字段，以高亮的形式显示结果
            String content = highlighter.getBestFragment(new IKAnalyzer(), "content", doc.get("content"));
            System.out.println("高亮后的content=" + content);
        }
    }

    // 排序
    @Test
    public void sortTest() throws Exception {
        // 查询解析器 第一个参数：针对哪个字段查询 第二个参数：分词器
        QueryParser queryParser = new QueryParser(Version.LATEST,"content", new IKAnalyzer());
        // 参数：你要查询的条件
        Query query = queryParser.parse("程序");

        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(INDEX_PATH)));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 格式化器
        Formatter formatter = new SimpleHTMLFormatter("<em>", "</em>");
        QueryScorer fragmentScorer = new QueryScorer(query);
        // 高亮的工具
        Highlighter highlighter = new Highlighter(formatter, fragmentScorer);

        // 第一个参数：字段名 第二个参数：字段类型 第三个参数：默认是升序，如果反转是降序 false:升序 true:降序
        Sort sort = new Sort(new SortField("id", SortField.Type.LONG, false));

        // 查询，得到满足条件的前10条数据
        TopDocs topDocs = indexSearcher.search(query, 10, sort);
        // 具体查到的满足条件的总记录数
        int totalHits = topDocs.totalHits;
        // System.out.println("满足条件的总记录数="+totalHits);
        // 结果集
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            // 文档的编号
            Integer docID = scoreDoc.doc;
            float score = scoreDoc.score;
            // System.out.println("文档编号="+docID+" 每一个文档的得分"+score);
            Document doc = indexSearcher.doc(docID);
            System.out.println(doc.get("id") + " " + doc.get("title") + " " + doc.get("content"));

            // 针对content这个字段，以高亮的形式显示结果
            String content = highlighter.getBestFragment(new IKAnalyzer(), "content", doc.get("content"));
            System.out.println("高亮后的content=" + content);
        }
    }

    // 分页
    @Test
    public void pageSortTest() throws Exception {
        int pageSize = 3;
        int pageNo = 1;
        int start = (pageNo - 1) * pageSize;
        int end = pageNo * pageSize;

        // 查询解析器 第一个参数：针对哪个字段查询 第二个参数：分词器
        QueryParser queryParser = new QueryParser(Version.LATEST,"content", new IKAnalyzer());
        // 参数：你要查询的条件
        Query query = queryParser.parse("程序");

        IndexReader indexReader = DirectoryReader.open(FSDirectory.open(new File(INDEX_PATH)));
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 格式化器
        Formatter formatter = new SimpleHTMLFormatter("<em>", "</em>");
        QueryScorer fragmentScorer = new QueryScorer(query);
        // 高亮的工具
        Highlighter highlighter = new Highlighter(formatter, fragmentScorer);

        // 第一个参数：字段名 第二个参数：字段类型 第三个参数：默认是升序，如果反转是降序 false:升序 true:降序
        Sort sort = new Sort(new SortField("id", SortField.Type.LONG, true));

        // 查询，得到满足条件的前10条数据
        TopDocs topDocs = indexSearcher.search(query, end, sort);
        // 具体查到的满足条件的总记录数
        int totalHits = topDocs.totalHits;
        // System.out.println("满足条件的总记录数="+totalHits);
        // 结果集
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (int i = start+1; i < end; i++) {
            ScoreDoc scoreDoc = scoreDocs[i];
            // 文档的编号
            Integer docID = scoreDoc.doc;
            float score = scoreDoc.score;
            // System.out.println("文档编号="+docID+" 每一个文档的得分"+score);
            Document doc = indexSearcher.doc(docID);
            System.out.println(doc.get("id") + " " + doc.get("title") + " " + doc.get("content"));

            // 针对content这个字段，以高亮的形式显示结果
            String content = highlighter.getBestFragment(new IKAnalyzer(), "content", doc.get("content"));
            System.out.println("高亮后的content=" + content);
        }
    }
}
