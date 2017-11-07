package com.rt.airleft;

import io.airlift.airline.Cli;
import io.airlift.airline.Help;
import io.airlift.airline.ParseArgumentsUnexpectedException;

import java.util.Arrays;

/**
 * v1.0:20161115
 * 组的概念.
 * 右击Run As - Run Configurations - Arguments - Program arguments
 * 输入: g1 G1MyCommand test, g1 G1MyCommand2 test,
 * 或g2 G2MyCommand test, 运行, 查看Console窗口.
 * @author Ilucky
 */
public class MainTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        System.out.println("===> " + Arrays.asList(args));
        Cli.CliBuilder<Runnable> builder = Cli.<Runnable>builder("MyCommand:Builder");

        builder.withGroup("g1")
            .withDescription("g1:Description")
            .withDefaultCommand(Help.class)
            .withCommands(Help.class, G1MyCommand.class, G1MyCommand2.class);

        builder.withGroup("g2")
            .withDescription("g2:Description")
            .withDefaultCommand(Help.class)
            .withCommands(Help.class, G2MyCommand.class);

        Cli<Runnable> cliParser = builder.build();
        try {
            Runnable command = cliParser.parse(args);
            System.out.println(command);
            command.run();
        } catch (ParseArgumentsUnexpectedException e) {
            System.out.println("Invalid command:"+e.toString());
        }
    }
}