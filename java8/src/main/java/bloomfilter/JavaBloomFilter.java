package bloomfilter;

import java.util.BitSet;

/**
 * <pre>
 *
 * 【标题】: java bloom filter
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-10-24 14:28
 * </pre>
 */
public class JavaBloomFilter {

    private   static   final   int    DEFAULT_SIZE  =   2   <<   24 ;
    private   static   final   int [] seeds         =   new   int [] {  7 ,  11 ,  13 ,  31 ,  37 ,  61 , };

    private BitSet bits          =   new  BitSet(DEFAULT_SIZE);
    private  SimpleHash[]       func          =   new  SimpleHash[seeds.length];

    public   static   void  main(String[] args) {
        String value  =   " stone2083@yahoo.cn " ;
        JavaBloomFilter filter  =   new  JavaBloomFilter();
        System.out.println(filter.contains(value));
        filter.add(value);
        System.out.println(filter.contains(value));
    }

    /*获取100内质数*/
    public static int[] getAllSeeds(){
        return new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
    }

    public  JavaBloomFilter() {
        for  ( int  i  =   0 ; i  <  seeds.length; i ++ ) {
            func[i]  =   new  SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    public   void  add(String value) {
        for  (SimpleHash f : func) {
            bits.set(f.hash(value),  true );
        }
    }

    public   boolean  contains(String value) {
        if  (value  ==   null ) {
            return   false ;
        }
        boolean  ret  =   true ;
        for  (SimpleHash f : func) {
            ret  =  ret  &&  bits.get(f.hash(value));
        }
        return  ret;
    }

    public   static   class  SimpleHash {

        private   int  cap;
        private   int  seed;

        public  SimpleHash( int  cap,  int  seed) {
            this .cap  =  cap;
            this .seed  =  seed;
        }

        public   int  hash(String value) {
            int  result  =   0 ;
            int  len  =  value.length();
            for  ( int  i  =   0 ; i  <  len; i ++ ) {
                result  =  seed  *  result  +  value.charAt(i);
            }
            return  (cap  -   1 )  &  result;
        }

    }
}
