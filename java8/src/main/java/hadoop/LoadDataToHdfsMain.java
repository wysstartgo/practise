package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    public static final String DIR_PATH = "/wys/segments";


    public static void main(String[] args) throws URISyntaxException, IOException {
//        if(args == null || args.length <= 0){
//            System.out.println("参数中本地文件路径不能缺少!");
//            return;
//        }
//        String localFile = args[0];
        String localFile = "F:\\apache-ant-1.9.9-bin.zip";
        final FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH ), new Configuration());
        //加载当前目录中的文件并上传到hdfs中去
        Path path = new Path(DIR_PATH);
        //确认下该目录是否存在
        boolean exists = fileSystem.exists(path);
        if(!exists){
            fileSystem.mkdirs(new Path(DIR_PATH));
        }
        //上传本地文件到hdfs中去
        java.nio.file.Path localPath = Paths.get(localFile);
        Files.walkFileTree(localPath,new SimpleFileVisitor<java.nio.file.Path>(){
            @Override
            public FileVisitResult preVisitDirectory(java.nio.file.Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问："  + dir + "目录!");
                //到目录之后，在hdfs上创建一个目录
                String dirName = dir.getFileName().toString();
                //在hdfs中的目录为
                String hdfsDirName = new StringBuilder(DIR_PATH).append(File.separator).append(dirName).toString();
                Path dirPath = new Path(hdfsDirName);
                if(!fileSystem.exists(dirPath)){
                    fileSystem.mkdirs(dirPath);
                }
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(java.nio.file.Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("正在访问:" + file + "文件!");
                String parentDirName = file.getParent().getFileName().toString();
                String fileName = file.getFileName().toString();
                String fileAbPath = new StringBuilder(DIR_PATH).append(File.separator).append(parentDirName).append(File.separator).append(fileName).toString();
                Path filePath = new Path(fileAbPath);
                if(fileSystem.exists(filePath)){
                    fileSystem.delete(filePath,true);
                }
                //上传
                final FSDataOutputStream out = fileSystem.create(filePath);
                InputStream in = Files.newInputStream(file);
                IOUtils.copyBytes(in, out, 1024,true);
                return super.visitFile(file, attrs);
            }
        });
//        java.nio.fileSystem.Path path = Paths.get("F:\\tmp");
//        Files.walkFileTree(path,new SimpleFileVisitor<java.nio.fileSystem.Path>(){
//            @Override
//            public FileVisitResult preVisitDirectory(java.nio.fileSystem.Path dir, BasicFileAttributes attrs) throws IOException {
//                System.out.println(dir);
//                System.out.println(dir.getFileName());
//                return super.preVisitDirectory(dir, attrs);
//            }
//
//            @Override
//            public FileVisitResult visitFile(java.nio.fileSystem.Path fileSystem, BasicFileAttributes attrs) throws IOException {
//                System.out.println(fileSystem.getParent());
//                System.out.println(fileSystem.getParent().getFileName());
//                return super.visitFile(fileSystem, attrs);
//            }
//        });


    }
}
