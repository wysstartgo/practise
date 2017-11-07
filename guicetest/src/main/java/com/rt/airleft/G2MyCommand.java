package com.rt.airleft;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;

@Command(name="G2MyCommand",description="This is G2MyCommand")
public class G2MyCommand implements Runnable {

    @Arguments
    private String command;

    @Override
    public void run() {
        System.out.println("G2MyCommand=" + command);
    }
}