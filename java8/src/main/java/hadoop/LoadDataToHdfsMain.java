package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * <pre>
 *
 * 【标题】: 将本地数据加载到hdfs中
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-11-02 14:03
 * </pre>
 */
public class LoadDataToHdfsMain {

    public static final String HDFS_PATH = "hdfs://58server:9000";

    public static final String DIR_PATH = "/druid/segments";


    public static void main(String[] args) throws URISyntaxException, IOException {
//        if(args == null || args.length <= 0){
//            System.out.println("参数中本地文件路径不能缺少!");
//            return;
//        }
//        final FileSystem file = FileSystem.get(new URI(HDFS_PATH ), new Configuration());
//        //加载当前目录中的文件并上传到hdfs中去
//        Path path = new Path(DIR_PATH);
//        //确认下该目录是否存在
//        boolean exists = file.exists(path);
//        if(!exists){
//            file.mkdirs(new Path(DIR_PATH));
//        }
//        //上传本地文件到hdfs中去
//        java.nio.file.Path localPath = Paths.get(new URI(args[0]));
//        Files.walkFileTree(localPath,new SimpleFileVisitor<java.nio.file.Path>(){
//            @Override
//            public FileVisitResult preVisitDirectory(java.nio.file.Path dir, BasicFileAttributes attrs) throws IOException {
//                System.out.println("正在访问："  + dir + "目录!");
//                //到目录之后，在hdfs上创建一个目录
//                Files.getFileStore(dir).getAttribute()
//                return super.preVisitDirectory(dir, attrs);
//            }
//
//            @Override
//            public FileVisitResult visitFile(java.nio.file.Path file, BasicFileAttributes attrs) throws IOException {
//                System.out.println("正在访问:" + file + "文件!");
//                return super.visitFile(file, attrs);
//            }
//        });
        java.nio.file.Path path = Paths.get("F:\\tmp");
        Files.walkFileTree(path,new SimpleFileVisitor<java.nio.file.Path>(){
            @Override
            public FileVisitResult preVisitDirectory(java.nio.file.Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println(dir);
                System.out.println(dir.getFileName());
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(java.nio.file.Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file.getParent());
                System.out.println(file.getParent().getFileName());
                return super.visitFile(file, attrs);
            }
        });


    }
}
