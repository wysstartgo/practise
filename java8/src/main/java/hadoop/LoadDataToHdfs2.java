package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.TestDistributedFileSystem;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * <pre>
 *
 * 【标题】:
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: 王鸿芳
 * 【时间】: 2017/11/2 0002 17:20
 * </pre>
 */
public class LoadDataToHdfs2 {
    public static final String HDFS_PATH = "hdfs://58server:9000";

    public static final String DIR_PATH = "/test/wikiticker";


    public static void main(String[] args) throws Exception {
        long l = System.currentTimeMillis();
        String locaFilePath = "F:\\data\\wikiticker";
        java.nio.file.Path localPath = Paths.get(locaFilePath);
        final FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH ), new Configuration());
        Path path = new Path(DIR_PATH);
        //确认下该目录是否存在
        boolean exists = fileSystem.exists(path);
        if(!exists){
            fileSystem.mkdirs(new Path(DIR_PATH));
        }
        Files.walkFileTree(localPath, new FileVisitor<java.nio.file.Path>(){

            @Override
            public FileVisitResult preVisitDirectory(java.nio.file.Path dir, BasicFileAttributes attrs) throws IOException {
//                String dirName = dir.getFileName().toString();
//                String prefix = DIR_PATH + File.separator;
//                String dfsPath = prefix + dirName;
//                Path path1 = new Path(dfsPath);
//                if(!fileSystem.exists(path1)){
//                    fileSystem.mkdirs(new Path(dfsPath));
//                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(java.nio.file.Path file, BasicFileAttributes attrs) throws IOException {
                //System.out.println(file.toString());
                String s = DIR_PATH + file.getParent().toString().substring(locaFilePath.length()) +  File.separator + file.getFileName().toString();
                //String decode = URLDecoder.decode(s, "UTF-8");
                //上传文件
                final FSDataOutputStream out = fileSystem.create(new Path(s));
                System.out.println(new Path(s));
                final FileInputStream in = new FileInputStream(file.toString());
                IOUtils.copyBytes(in, out, 1024,true);

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(java.nio.file.Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(java.nio.file.Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }


        });
        long j = System.currentTimeMillis();
        System.out.println(j-l);
    }
}
