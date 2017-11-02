//package hadoop;
//
//import junit.framework.TestCase;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.*;
//import org.apache.hadoop.io.IOUtils;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.net.URI;
//
//
//public class app3{
//
//    public static String hdfsUrl = "hdfs://hadoop:9000";
//
//
//     // create HDFS folder 创建一个文件夹
//    public void testHDFSMkdir() throws Exception {
//        //一般url只认识http协议
//        //URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());//保证url也认识hdfs协议，这样就可以解析HDFS_PATH了
//
//        //普通操作
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), conf);
//        Path path = new Path("/test5");
//        fs.mkdirs(path);
//
//        //简化
//        FileSystem fs = FileSystem.get(new URI(hdfsUrl), new Configuration());
//        fs.mkdirs(new Path("/dir01"));
//
//    }
//
//     // create a file 创建一个文件
//    public void testCreateFile() throws Exception {
//        //标准操作
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), conf);
//        Path path = new Path("/test/a.txt");
//        FSDataOutputStream out = fs.create(path);
//        out.write("hello hadoop!".getBytes());
//
//        //简单操作
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), new Configuration());
//        FSDataOutputStream out = fs.create(new Path("/dir01/deom2.txt"));
//        out.write("hadoop 小试牛刀！".getBytes("utf-8"));
//
//    }
//
//     // rename a file 重命名
//    public void testRenameFile() throws Exception { // rename a file 重命名
//        //标准操作
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), conf);
//        Path path = new Path("/test/a.txt");
//        Path newPath = new Path("/test/bb.txt");
//        System.out.println(fs.rename(path, newPath));
//
//        //简洁操作
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), new Configuration());
//        System.out.print(fs.rename(new Path("/dir01/deom2.txt"), new Path("/dir01/demo03.txt")));
//    }
//
//    // 上传文件
//    public void testUploadLocalFile1() throws Exception { // upload a local file
//        //标准操作
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), conf);
//        Path src = new Path("E:/Demo.py");
//        Path dst = new Path("/test");
//        fs.copyFromLocalFile(src, dst);
//
//        //简洁操作
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), new Configuration());
//        fs.copyFromLocalFile(new Path("E:/Demo.py"), new Path("/dir01"));
//    }
//
//    //上传文件
//    public void testUploadLocalFile2() throws Exception {
//        //标准书写
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), conf);
//        Path src = new Path("E:/Demo.py");
//        Path dst = new Path("/test1");
//        InputStream in = new BufferedInputStream(new FileInputStream(new File(
//                "/home/hadoop/hadoop-1.2.1/bin/rcc")));
//        FSDataOutputStream out = fs.create(new Path("/test/rcc1"));
//        IOUtils.copyBytes(in, out, 4096);
//
//        //简单
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), new Configuration());
//        final FSDataOutputStream out = fs.create(new Path("/dir01/files"));
//        final FileInputStream in = new FileInputStream("E:/Demo.py");
//        IOUtils.copyBytes(in, out, 1024,true);
//    }
//
//    // 列出文件
//    public void testListFIles() throws Exception { // list files under folder
//        //标准书写
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), conf);
//        Path dst = new Path("/test");
//        FileStatus[] files = fs.listStatus(dst);
//        for (FileStatus file : files) {
//            System.out.println(file.getPath().toString());
//        }
//
//        //简洁书写
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl),new Configuration());
//        FileStatus[] files = fs.listStatus(new Path("/dir01"));
//        for (FileStatus file : files) {
//            System.out.println(file.getPath().toString());
//        }
//    }
//
//    // 查找文件所在的数据块
//    public void testGetBlockInfo() throws Exception { // list block info of file
//
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), conf);
//        Path dst = new Path("/dir01/Demo.py");
//        FileStatus fileStatus = fs.getFileStatus(dst);
//        BlockLocation[] blkloc = fs.getFileBlockLocations(fileStatus, 0,
//                fileStatus.getLen()); // 查找文件所在数据块
//        for (BlockLocation loc : blkloc) {
//            for (int i = 0; i < loc.getHosts().length; i++)
//                System.out.println(loc.getHosts()[i]);
//        }
//    }
//
//    //删除文件、文件夹
//    public void testRemoveFile () throws Exception {
//        FileSystem fs = FileSystem.get(URI.create(hdfsUrl), new Configuration());
//        fs.delete(new Path("/test5"), true);//是否递归删除
//    }
//}
//
