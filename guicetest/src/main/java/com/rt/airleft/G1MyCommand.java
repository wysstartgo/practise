package com.rt.airleft;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;

@Command(name="G1MyCommand",description="This is G1MyCommand")
public class G1MyCommand implements Runnable {

    @Arguments
    private String command;

    @Override
    public void run() {
        System.out.println("G1MyCommand=" + command);
    }
}