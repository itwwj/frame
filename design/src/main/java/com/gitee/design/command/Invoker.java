package com.gitee.design.command;

import lombok.Setter;

/**
 * 负责人
 * @author jie
 */

public class Invoker {
    @Setter
    private Command command;

    public void action() {
        this.command.execute();
    }
}
