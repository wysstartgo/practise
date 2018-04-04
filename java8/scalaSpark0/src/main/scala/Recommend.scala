//import org.apache.spark.rdd.RDD
//
//
///**
//  * <pre>
//  *
//  * 【标题】:
//  * 【描述】:
//  * 【版权】: 润投科技
//  * 【作者】: wuys
//  * 【时间】: 2018-04-02 14:35
//  * </pre>
//  */
//class Recommend {
//
//  def Cooccurrence (
//
//                     user_rdd:RDD[(String,String,Double)]
//
//                   ) : (RDD[(String,String,Double)]) = {
//
//    //  0 数据做准备
//
//    val user_rdd2=user_rdd.map(f => (f._1,f._2)).sortByKey()
//
//    user_rdd2.cache
//
//    //  1 (用户：物品)笛卡尔积 (用户：物品) =>物品:物品组合
//
//    val user_rdd3=user_rdd2 join user_rdd2
//
//    val user_rdd4=user_rdd3.map(data=> (data._2,1))
//
//    //  2 物品:物品:频次
//
//    val user_rdd5=user_rdd4.reduceByKey((x,y) => x + y)
//
//    //  3 对角矩阵
//
//    val user_rdd6=user_rdd5.filter(f=> f._1._1 == f._1._2)
//
//    //  4 非对角矩阵
//
//    val user_rdd7=user_rdd5.filter(f=> f._1._1 != f._1._2)
//
//    //  5 计算同现相似度（物品1，物品2，同现频次）
//
//    val user_rdd8=user_rdd7.map(f=> (f._1._1, (f._1._1, f._1._2, f._2))).
//
//      join(user_rdd6.map(f=> (f._1._1, f._2)))
//
//    val user_rdd9=user_rdd8.map(f=> (f._2._1._2, (f._2._1._1,
//
//      f._2._1._2, f._2._1._3, f._2._2)))
//
//    val user_rdd10=user_rdd9.join(user_rdd6.map(f => (f._1._1, f._2)))
//
//    val user_rdd11 = user_rdd10.map(f => (f._2._1._1,f._2._1._2,f._2._1._3,f._2._1._4,f._2._2))
//
//    val user_rdd12=user_rdd11.map(f=> (f._1, f._2, (f._3 / Math.sqrt(f._4 * f._5)) ))
//
//    //   6结果返回
//
//    user_rdd12
//
//  }
//
//}
