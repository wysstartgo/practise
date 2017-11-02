//package bloomfilter;
//
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.util.IOUtils;
//
//import java.io.Reader;
//
//public class MyIkAnalyzer extends Analyzer {
//
//    @Override
//    protected TokenStreamComponents createComponents(String s, Reader reader) {
//        try {
//            MyIKTokenizer it = new MyIKTokenizer(reader);
//            return new Analyzer.TokenStreamComponents(it);
//        } finally {
//            IOUtils.closeWhileHandlingException(reader);
//        }
//    }
//}