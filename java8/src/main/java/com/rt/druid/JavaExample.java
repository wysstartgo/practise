package com.rt.druid;

import com.google.common.collect.ImmutableMap;
import com.metamx.common.logger.Logger;
import com.metamx.tranquility.config.DataSourceConfig;
import com.metamx.tranquility.config.PropertiesBasedConfig;
import com.metamx.tranquility.config.TranquilityConfig;
import com.metamx.tranquility.druid.DruidBeams;
import com.metamx.tranquility.tranquilizer.MessageDroppedException;
import com.metamx.tranquility.tranquilizer.Tranquilizer;
import com.twitter.util.FutureEventListener;
import scala.runtime.BoxedUnit;

import java.io.InputStream;
import java.util.Map;

/**
 * 功能描述,该部分必须以中文句号结尾。
 *
 * @author panqingcui
 * @create 2017-10-12 16:22
 */
public class JavaExample {
    private static final Logger log = new Logger(JavaExample.class);

    public static void main(String[] args) {
        Tranquilizer<Map<String, Object>> sender = null;
        try {
            // Read config from "example.json" on the classpath.
            final InputStream configStream = JavaExample.class.getClassLoader().getResourceAsStream("example.json");
            final TranquilityConfig<PropertiesBasedConfig> config = TranquilityConfig.read(configStream);
            final DataSourceConfig<PropertiesBasedConfig> wikipediaConfig = config.getDataSource("wikipedia");
            sender = DruidBeams.fromConfig(wikipediaConfig)
                .buildTranquilizer(wikipediaConfig.tranquilizerBuilder());
            sender.start();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(" line 40");
        try {
            // Send 10000 objects
            for (int i = 0; i < 1; i++) {
                System.out.println(" line 44");
                // Build a sample event to send; make sure we use a current date
                final Map<String, Object> obj = ImmutableMap.<String, Object> of(
                                                                                 "timestamp",
                                                                                 System.currentTimeMillis(),
                                                                                 "page", "foo",
                                                                                 "added", i);
                // Asynchronously send event to Druid:
                sender.send(obj).addEventListener(
                                                  new FutureEventListener<BoxedUnit>() {
                                                      @Override
                                                      public void onSuccess(BoxedUnit value) {
                                                          log.info("Sent message: %s", obj);
                                                          System.out.println(String.format("Send message:%s", obj));
                                                      }

                                                      @Override
                                                      public void onFailure(Throwable e) {
                                                          if (e instanceof MessageDroppedException) {
                                                              log.warn(e, "Dropped message: %s", obj);
                                                              System.out
                                                                  .println(String.format("Dropped message:%s %s", e,
                                                                                         obj));
                                                          } else {
                                                              log.error(e, "Failed to send message: %s", obj);
                                                              System.out
                                                                  .println(String.format("Failed to send message:%s %s",
                                                                                         e,
                                                                                         obj));
                                                          }
                                                      }
                                                  });
            }
        } catch (Throwable e) {
            System.out.println(e);
        } finally {
            sender.flush();
            sender.stop();
        }
    }
}
