package bloomfilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

/**
 * <pre>
 *
 * 【标题】: guava bloom filter
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-10-24 14:23
 * </pre>
 */
public class GuavaBloomFilter {

    public static void main(String[] args) {
        BloomFilter<String> bloomFilter = BloomFilter.create(new Funnel<String>() {
            @Override
            public void funnel(String from, PrimitiveSink into) {
                into.putString(from, Charsets.UTF_8);
            }
        },50000,0.000001);
        String str1 = "中国";
        String str2 = "中华人民共和国";
        boolean mightContain = bloomFilter.mightContain(str1);
        System.out.println(mightContain);
        bloomFilter.put(str1);
        bloomFilter.put(str2);
        System.out.println(bloomFilter.mightContain(str1));

    }
}
