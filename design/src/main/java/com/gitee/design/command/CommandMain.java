package com.gitee.design.command;

/**
 * @author jie
 */
public class CommandMain {
    public static void main(String[] args) {
        Invoker invoker=new Invoker();
        Command command=new AddRequirementCommand();
        invoker.setCommand(command);
        invoker.action();
    }
}
