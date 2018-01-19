import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * <pre>
 *
 * 【标题】:
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2018-01-16 11:01
 * </pre>
 */
public class MainTest {

    public static void main(String[] args) {
        String ss = "\\xF0\\x9F\\x91\\xBF";
        try {
            String encode = URLEncoder.encode(ss, "utf-8");
            System.out.println(encode);
            String decode = URLDecoder.decode(encode, "utf-8");
            System.out.println(decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
