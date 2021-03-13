package com.gitee.design.command;

/**
 *
 * 增加需求的命令
 * @author jie
 */
public class AddRequirementCommand extends Command {
    @Override
    public void execute() {
        super.rg.find();
        super.rg.add();
        super.pg.add();
        super.cg.add();
        super.rg.plan();
    }
}
