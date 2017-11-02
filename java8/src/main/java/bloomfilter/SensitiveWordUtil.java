package bloomfilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * 敏感词管理，查询所有的敏感词，查看是否存在
 * @author Administrator
 *
 */
public class SensitiveWordUtil {
    private static String docsPath = "F:\\data\\docs";
    private static String fileName = "analysis.txt";
    private static String indexPath = "F:\\data\\indexs";
    private static double baseScore = 0.02;
    
    public static void main(String[] args) throws Exception {

        /*String docsPath = "D:\\CMSDocs";
        String fileName = "analysis.txt";
        String indexPath = "D:\\CMSIndex";*/
        
        //startWork( indexPath,docsPath,fileName, content);
        String [] allSensitiveWord = {"使命","持续创新","没出事","十二号","辈数儿","否存在"};
        /*String sensitiveWord =  null;
        for (int i = 0; i < allSensitiveWord.length; i++) {
            sensitiveWord = allSensitiveWord[i];
            judeIsExsist(sensitiveWord,indexPath);
        }*/
        //judeIsExsist(sensitiveWord,indexPath);
        String content = "bseurhg87e略后来持续改了良好社会了十二和谁零售额好了粉色，哦然否存在后首尔玫我们的使命：让IT更简单 数据是未来商业的核心，IT是企业数据存储和分析的基础，SMARTX让IT更简单，帮助企业挖掘数据价值，让商业更有效率。 我们相信： 1. 持续创新 SMARTX由热爱挑战的团队组建，永远解决用户最痛、最难的问题，不断创新为用户创造价值。 2. 简胜于繁 不论是产品的设计实现，还是用户体验，我们把简单原则贯彻如一。让企业用更直觉、更简单的红色还是摩尔佛爱问还是没饿坏了分数而肥瘦儿";
        
        Map<String,Boolean> resMap = getSearchResult(allSensitiveWord,content);
        System.out.println(resMap);
        
    }
    /**
     * 将所有的敏感词纳入文件中查询，返回所有的敏感词的存在情况
     * @param allSensitiveWord
     * @param content
     * @return
     * @throws IOException
     * @throws ParseException
     */
    
    public static  Map<String,Boolean> getSearchResult(String []allSensitiveWord,String content) throws IOException, ParseException{
        Map<String,Boolean> resultMap = new HashMap<String,Boolean>();
        if (content == null) {
            return null;
        }
        else{
            startWork( indexPath,docsPath,fileName, content);   //先做准备工作
            String sensitiveWord =  null;
            for (int i = 0; i < allSensitiveWord.length; i++) {
                boolean isFind = false;
                sensitiveWord = allSensitiveWord[i];
                Map<String, Object> resMap= judeIsExsist(sensitiveWord,indexPath);
                ScoreDoc scoreDoc = (ScoreDoc) resMap.get("scoreDoc");
                int totalHits = (int) resMap.get("totalHits");
                if (totalHits >= 1 && scoreDoc.score >= baseScore) {
                    isFind = true;
                }
                else{
                    isFind = false;
                }
                resultMap.put(sensitiveWord, isFind);
            }
        }
        return resultMap;
    }
    
    /**
     * 做一些准备工作，将内容写入txt文件，并建立索引
     * @param indexPath
     * @param docsPath
     * @param fileName
     * @param content
     * @return
     */
    private static boolean startWork(String indexPath, String docsPath,String fileName,String content) {
        boolean create = true;
        String getContent = writeContentTotxt(docsPath, fileName, content);
        if(getContent != null){
            createIndex(indexPath,docsPath,create);
            return true;
        }
        else{
            System.out.println("写入文件失败！");
        }
        return false;
    }
    
    /**
     * 将文章写入txt文件的方法
     * @param filePath
     * @param fileName
     * @param content
     * @return
     */
    
    private static String writeContentTotxt(String filePath, String fileName,String content) {
        File fileFolder = new File(filePath);
        if (!fileFolder.exists() || !fileFolder.isDirectory()) {
            createFolder(filePath);
        }
        File contentFile = new File(filePath + "\\" + fileName);
        if (!contentFile.exists() || contentFile.isDirectory()) {
            try {
                createFile(filePath, fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //如果没有该文件就要先创建该文件，如果有了就直接进行交给下一步
        }
        try {
            boolean isSuccess = printStream(filePath + "\\" + fileName, content, false);  //将要搜索的文章先写入到txt临时文件中
            if (isSuccess) {
                return content;
            }
            else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用字符流写入的具体实现
     * @param filePath
     * @param content
     * @param isTrue
     * @return
     * @throws IOException
     */
    private static boolean printStream(String filePath, String content,boolean isTrue) throws IOException {
        boolean isSuccess = false;
        FileOutputStream out = new FileOutputStream(filePath, isTrue); // true意为是在其后追加，false会将原有的内容覆盖
        PrintStream p = new PrintStream(out, true);
        if (content != null) {
            p.println(content);
            isSuccess = true;
        } else {
            System.out.println("对不起你的要写入的内容为空");
        }
        out.close();
        p.close();
        return isSuccess;
    }
    /**
     * 建立索引的具体实现
     * @param indexPath
     * @param docsPath
     * @param create
     */
    private static void createIndex(String indexPath,String docsPath,boolean create){
        final File docDir = new File(docsPath);
        if (!docDir.exists() || !docDir.canRead()) {
            System.out.println("资源文件目录 '" + docDir.getAbsolutePath() + "' 不存在或不可读，请检查！");
            System.exit(1);
        }else{
            Date start = new Date();
            try {
                System.out.println("建立索引文件到该目录 '" + indexPath + "'...");
                Directory dir = FSDirectory.open(Paths.get(indexPath));
                Analyzer analyzer = new StandardAnalyzer();
                IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
                if (create) {
                    // 创建新的索引文件，删除所有其他的索引文件
                    //（指的是该资源文件目录下的旧的索引文件，其他资源的索引文件不影响）
                    iwc.setOpenMode(OpenMode.CREATE);
                } else {
                    // 如果有旧的索引文件，则更新索引文件
                    iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
                }
                IndexWriter writer = new IndexWriter(dir, iwc);
                indexDocs(writer, docDir);
                writer.close();
                Date end = new Date();
                System.out.println(end.getTime() - start.getTime() + " total milliseconds");
            } catch (IOException e) {
                System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
            }
        }
    }
    
    /**
     * 索引文件的义务处理
     * @param writer
     * @param file
     * @throws IOException
     */
    private static void indexDocs(IndexWriter writer, File file) throws IOException {
        if (!file.canRead()) {
            return;
        }
        else if (file.isDirectory()) {
            String[] files = file.list();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    indexDocs(writer, new File(file, files[i]));
                }
            }
        } else {
            FileInputStream fis;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException fnfe) {
                return;
            }
            try {
 
                // 每一个文档最终被封装成了一个 Document 对象
                // Document 是用来描述文档的，这里的文档可以指一个 HTML 页面，一封电子邮件，或者是一个文本文件。
                // 一个 Document 对象由多个 Field 对象组成的。
                // 可以把一个 Document 对象想象成数据库中的一个记录，而每个 Field 对象就是记录的一个字段。
                Document doc = new Document();
                // Field 对象是用来描述一个文档的某个属性的，比如一封电子邮件的标题和内容可以用两个 Field 对象分别描述。
                Field pathField = new StringField("path", file.getPath(),Field.Store.YES);
                // pathField指的是资源文件的路径的field
                doc.add(pathField);
                // 这个field指的是最后的修改时间
                doc.add(new LongField("modified", file.lastModified(),Field.Store.NO));
 
                // 把资源文件中的内容分词后，索引到索引文件中，指定为UTF-8编码
                doc.add(new TextField("contents", new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8))));
 
                if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
                    System.out.println("adding " + file);
                    writer.addDocument(doc);
                } else {
                    System.out.println("updating " + file);
                    writer.updateDocument(new Term("path", file.getPath()), doc);
                }
            } finally {
                fis.close();
            }
            }
        }
    /**
     * 创建文件
     * @param filePath
     * @param fileName
     * @throws IOException
     */
    private static void createFile(String filePath, String fileName) throws IOException {
        File file = new File(filePath+"\\"+fileName);
        if (!file.exists())
            file.createNewFile();
        else{
            return;
        }
    }

    /**
     * 创建文件夹
     * @param filePath
     * @return
     */
    private static String createFolder(String filePath) {
        File indexFile = new File(filePath);
        if (!indexFile.exists() || !indexFile.isDirectory()) {
            indexFile.mkdir();
        }
        else{
            //文件夹已经存在，直接返回
        }
        return filePath;
    }
    
    /**
     * lucene的具体查询方法
     * @param in
     * @param searcher
     * @param query
     * @param hitsPerPage
     * @param raw
     * @param interactive
     * @return
     * @throws IOException
     */
    public static Map<String, Object> doPagingSearch(BufferedReader in,IndexSearcher searcher, Query query, int hitsPerPage, boolean raw,boolean interactive) throws IOException {
        Map<String,Object> resMap = new HashMap<String,Object>();
        TopDocs results = searcher.search(query, 5 * hitsPerPage); // 对应的每一页
        ScoreDoc[] hitDocs = results.scoreDocs;

        int numTotalHits = results.totalHits;
        resMap.put("totalHits", numTotalHits);
        if (hitDocs.length!=0 && numTotalHits > 0 && hitDocs[0]!=null) {
            resMap.put("scoreDoc", hitDocs[0]);
            System.out.println(hitDocs[0].toString());
            System.out.println(numTotalHits + " total matching documents");
        }else{
            resMap.put("scoreDoc", null);
        }
        return resMap;
    }

    /**
     * 根据索引与关键字来查询是否存在
     * @param sensitiveWord
     * @param indexPath
     * @return
     * @throws IOException
     * @throws ParseException
     */
    private static Map<String,Object> judeIsExsist(String sensitiveWord, String indexPath)throws IOException, ParseException {
        String field = "contents";
        String queries = null;
        int repeat = 0;
        boolean raw = false;
        String queryString = null;
        int hitsPerPage = 10;
        
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexPath)));
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new StandardAnalyzer();

        BufferedReader in = null;
        in = new BufferedReader(new InputStreamReader(System.in,StandardCharsets.UTF_8));
        QueryParser parser = new QueryParser(field, analyzer);
        sensitiveWord = sensitiveWord.trim(); // 去除两边的看的空格 
        Query query = parser.parse(sensitiveWord); // 创建查询器
        System.out.println("Searching for: " + query.toString(field));
        if (repeat > 0) { // repeat & time as benchmark 
            Date start = new Date();
            for (int j = 0; j < repeat; j++) {    
                searcher.search(query, null, 100);
            }
            Date end = new Date();
            System.out.println("Time: " + (end.getTime() - start.getTime()) + "ms");
        }
        Map<String, Object> resMap = doPagingSearch(in, searcher, query, hitsPerPage, raw, queries == null && queryString == null);
        reader.close();
        return resMap;
    }
}