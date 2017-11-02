package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.IOUtils;

import java.io.FileInputStream;
import java.net.URI;

/**
 * 
 * @author mis
 * 使用systemfile
 */
public class app2 {
        //查看hadoop的根目录下的hello文件
    public static final String HDFS_PATH = "hdfs://58server:9000";

    public static final String DIR_PATH = "/test";
    public static final String FILE_PATH = "/test/test1";
    private static FsPermission permission;

    public static void main(String[] args) throws Exception {
        final FileSystem file = FileSystem.get(new URI(HDFS_PATH ), new Configuration());

        //创建文件夹
        //file.mkdirs(f)
        file.mkdirs(new Path(DIR_PATH));

        //file.mkdirs(f, permission)
       // file.mkdirs(new Path("/DIR002"), permission);

        //file.mkdirs(fs, dir, permission)

        //上传文件
        final FSDataOutputStream out = file.create(new Path(FILE_PATH));
        final FileInputStream in = new FileInputStream("F:\\tools\\gc.log");
        IOUtils.copyBytes(in, out, 1024,true);


        //下载文件
//        final FSDataInputStream in = file.open(new Path(FILE_PATH));
//        IOUtils.copyBytes(in, System.out, 1024,true);
//
//        //删除文件（夹）
//        file.delete(new Path(DIR_PATH), true);
                //后边的boolean值是表示 递归与否（如果是删除文件夹就需要用true，问建true或false）


                //文件路径是否存在
//        boolean falg = file.exists(new Path("/DIR003/file/Demo.py"));
//        System.out.println(falg);

        //重命名
//        file.rename(new Path("/DIR003/Demo1.py"), new Path("/DIR003/Demo.py"));

        //是否是文件
//        boolean falg1 = file.isFile(new Path("/DIR003/Demo.py"));
//        System.out.println(falg1);

        //剪切本地文件到hdfs
//        file.moveFromLocalFile(new Path("E:/Demo.py"), new Path("/Demo.py"));
        //复制本地文件到hdfs
//        file.copyFromLocalFile(new Path("E:/Demo.py"), new Path("/Demo.py"));

    }
}

